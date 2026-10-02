package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCScorpionEntity;
import io.github.mestretramador.mocreaturesrr.entity.scorpion.ScorpionVariant;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SpawnEggItem;

/** A single spawn egg — picks a random color among the 5 (including Undead) each time it's used. */
public class ScorpionSpawnEggItem extends SpawnEggItem {

    public ScorpionSpawnEggItem(Item.Properties properties) {
        super(properties.spawnEgg(ModEntities.MOC_SCORPION.get()));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        MoCScorpionEntity scorpion = ModEntities.MOC_SCORPION.get().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (scorpion == null) {
            return InteractionResult.FAIL;
        }

        scorpion.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D,
                serverLevel.getRandom().nextFloat() * 360F, 0F);
        ScorpionVariant[] variants = ScorpionVariant.values();
        ScorpionVariant variant = variants[serverLevel.getRandom().nextInt(variants.length)];
        scorpion.setVariant(variant);
        if (variant != ScorpionVariant.UNDEAD && serverLevel.getRandom().nextInt(4) == 0) {
            scorpion.setHasBabiesPublic(true);
        }
        serverLevel.addFreshEntity(scorpion);

        var player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}