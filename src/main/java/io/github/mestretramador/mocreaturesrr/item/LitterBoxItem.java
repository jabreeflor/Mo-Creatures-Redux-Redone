package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCLitterBoxEntity;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class LitterBoxItem extends Item {

    public LitterBoxItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        MoCLitterBoxEntity box = ModEntities.MOC_LITTER_BOX.get().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (box == null) {
            return InteractionResult.FAIL;
        }

        box.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        serverLevel.addFreshEntity(box);

        var player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}