package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCBirdEntity;
import io.github.mestretramador.mocreaturesrr.entity.bird.BirdVariant;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SpawnEggItem;

/** A single spawn egg — picks a random colour among the 6, same pattern as BunnySpawnEggItem. */
public class BirdSpawnEggItem extends SpawnEggItem {

    public BirdSpawnEggItem(Item.Properties properties) {
        super(properties.spawnEgg(ModEntities.MOC_BIRD.get()));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        MoCBirdEntity bird = ModEntities.MOC_BIRD.get().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (bird == null) {
            return InteractionResult.FAIL;
        }

        bird.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D,
                serverLevel.getRandom().nextFloat() * 360F, 0F);
        bird.setVariant(BirdVariant.random(serverLevel.getRandom()));
        serverLevel.addFreshEntity(bird);

        var player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}