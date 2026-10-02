package io.github.mestretramador.mocreaturesrr.entity;

import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

/**
 * Port of the thrown behaviour of {@code drzhark.mocreatures.entity.item.MoCEntityThrowableRock}.
 * A block torn out of the world and thrown by a {@link RockThrower}. Unlike the original (which
 * bounced around for 15 seconds and then dropped an item that vanished after 1 minute), it breaks
 * as soon as it hits something and drops the block itself as an item — but only when the block
 * was really taken out of the world, so a rock thrown with mobGriefing off never duplicates.
 */
public class MoCThrowableRockEntity extends ThrowableProjectile implements CarriedBlockEntity {

    /** Original: MoCEntityThrowableRock hits for 4. */
    private static final float IMPACT_DAMAGE = 4.0F;
    /** Original: motion reduced by 0.04 per tick (vanilla throwables use 0.03). */
    private static final double GRAVITY = 0.04D;
    /** Safety net: breaks after 15 s even if it never hits anything (e.g. thrown into the void). */
    private static final int MAX_LIFE_TICKS = 300;

    private static final String TAG_BLOCK_STATE = "BlockState";
    private static final String TAG_DROPS_LOOT = "DropsLoot";
    private static final String TAG_LIFE = "Life";

    private static final EntityDataAccessor<BlockState> DATA_BLOCK_STATE =
            SynchedEntityData.defineId(MoCThrowableRockEntity.class, EntityDataSerializers.BLOCK_STATE);

    /** Server-only: true when the block was really removed from the world, so landing gives it back. */
    private boolean dropsLoot;
    private int life;

    public MoCThrowableRockEntity(EntityType<? extends MoCThrowableRockEntity> type, Level level) {
        super(type, level);
    }

    /** Spawns at the thrower's eyes, owned by it — the caller still sets the velocity. */
    public MoCThrowableRockEntity(Level level, LivingEntity thrower, BlockState state, boolean dropsLoot) {
        super(ModEntities.MOC_THROWABLE_ROCK.get(), thrower.getX(), thrower.getEyeY() - 0.1F, thrower.getZ(), level);
        this.setOwner(thrower);
        this.setBlockState(state);
        this.dropsLoot = dropsLoot;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_BLOCK_STATE, Blocks.STONE.defaultBlockState());
    }

    public BlockState getBlockState() {
        return this.entityData.get(DATA_BLOCK_STATE);
    }

    private void setBlockState(BlockState state) {
        this.entityData.set(DATA_BLOCK_STATE, state.isAir() ? Blocks.STONE.defaultBlockState() : state);
    }

    @Override
    protected double getDefaultGravity() {
        return GRAVITY;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide() && !this.isRemoved() && ++this.life >= MAX_LIFE_TICKS) {
            this.shatter();
        }
    }

    // ---------------------------------------------------------------------
    // Impact
    // ---------------------------------------------------------------------

    @Override
    protected boolean canHitEntity(Entity target) {
        return super.canHitEntity(target) && !(target instanceof RockThrower);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().hurtOrSimulate(this.damageSources().thrown(this, this.getOwner()), IMPACT_DAMAGE);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide() && !this.isRemoved()) {
            this.shatter();
        }
    }

    /** Breaks apart with the block's own break particles and sound, giving the block back as an item when allowed. */
    private void shatter() {
        BlockState state = this.getBlockState();
        this.level().levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, this.blockPosition(), Block.getId(state));
        if (this.dropsLoot) {
            ItemStack blockItem = new ItemStack(state.getBlock());
            if (!blockItem.isEmpty() && this.level() instanceof ServerLevel serverLevel) {
                this.spawnAtLocation(serverLevel, blockItem);
            }
        }
        this.discard();
    }

    // ---------------------------------------------------------------------
    // Persistence
    // ---------------------------------------------------------------------

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store(TAG_BLOCK_STATE, BlockState.CODEC, this.getBlockState());
        output.putBoolean(TAG_DROPS_LOOT, this.dropsLoot);
        output.putInt(TAG_LIFE, this.life);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        input.read(TAG_BLOCK_STATE, BlockState.CODEC).ifPresent(this::setBlockState);
        this.dropsLoot = input.getBooleanOr(TAG_DROPS_LOOT, false);
        this.life = input.getIntOr(TAG_LIFE, 0);
    }
}