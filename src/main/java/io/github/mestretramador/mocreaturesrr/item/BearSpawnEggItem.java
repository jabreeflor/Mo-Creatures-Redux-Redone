package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCBearEntity;
import io.github.mestretramador.mocreaturesrr.entity.bear.BearVariant;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SpawnEggItem;

/** One egg per species — unlike BigCat, bears have no families/hybrids, so each egg always spawns the same fixed variant. */
public class BearSpawnEggItem extends SpawnEggItem {

    private final BearVariant variant;

    public BearSpawnEggItem(BearVariant variant, Item.Properties properties) {
        super(properties.spawnEgg(ModEntities.MOC_BEAR.get()));
        this.variant = variant;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        MoCBearEntity bear = ModEntities.MOC_BEAR.get().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (bear == null) {
            return InteractionResult.FAIL;
        }

        bear.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D,
                serverLevel.getRandom().nextFloat() * 360F, 0F);
        bear.setVariant(variant);
        serverLevel.addFreshEntity(bear);

        var player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public net.minecraft.world.InteractionResult interactLivingEntity(
            net.minecraft.world.item.ItemStack stack,
            net.minecraft.world.entity.player.Player player,
            net.minecraft.world.entity.LivingEntity target,
            net.minecraft.world.InteractionHand hand) {
        if (!(target instanceof MoCBearEntity adult) || adult.isBaby() || adult.getVariant() != this.variant) {
            return net.minecraft.world.InteractionResult.PASS;
        }
        if (!(target.level() instanceof ServerLevel serverLevel)) {
            return net.minecraft.world.InteractionResult.SUCCESS;
        }
        MoCBearEntity cub = ModEntities.MOC_BEAR.get().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (cub != null) {
            cub.snapTo(target.getX(), target.getY(), target.getZ(), 0F, 0F);
            cub.setVariant(this.variant);
            cub.setBaby(true);
            serverLevel.addFreshEntity(cub);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        return net.minecraft.world.InteractionResult.SUCCESS;
    }
}