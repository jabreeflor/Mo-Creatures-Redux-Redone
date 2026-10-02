package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCManticoreEntity;
import io.github.mestretramador.mocreaturesrr.entity.manticore.ManticoreVariant;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SpawnEggItem;

/** A single spawn egg, per the wiki — picks a random color among the 5 each time it's used. */
public class ManticoreSpawnEggItem extends SpawnEggItem {

    public ManticoreSpawnEggItem(Item.Properties properties) {
        super(properties.spawnEgg(ModEntities.MOC_MANTICORE.get()));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        MoCManticoreEntity manticore = ModEntities.MOC_MANTICORE.get().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (manticore == null) {
            return InteractionResult.FAIL;
        }

        manticore.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D,
                serverLevel.getRandom().nextFloat() * 360F, 0F);
        ManticoreVariant[] variants = ManticoreVariant.values();
        manticore.setVariant(variants[serverLevel.getRandom().nextInt(variants.length)]);
        serverLevel.addFreshEntity(manticore);

        var player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}