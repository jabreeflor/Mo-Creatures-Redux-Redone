package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCBunnyEntity;
import io.github.mestretramador.mocreaturesrr.entity.bunny.BunnyVariant;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SpawnEggItem;

/** A single spawn egg — picks a random colour among the 5, same pattern as SnakeSpawnEggItem. */
public class BunnySpawnEggItem extends SpawnEggItem {

    public BunnySpawnEggItem(Item.Properties properties) {
        super(properties.spawnEgg(ModEntities.MOC_BUNNY.get()));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        MoCBunnyEntity bunny = ModEntities.MOC_BUNNY.get().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (bunny == null) {
            return InteractionResult.FAIL;
        }

        bunny.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D,
                serverLevel.getRandom().nextFloat() * 360F, 0F);
        bunny.setVariant(BunnyVariant.random(serverLevel.getRandom()));
        serverLevel.addFreshEntity(bunny);

        var player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}