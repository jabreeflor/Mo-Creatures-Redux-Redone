package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCOstrichEntity;
import io.github.mestretramador.mocreaturesrr.entity.ostrich.OstrichVariant;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SpawnEggItem;

/**
 * One egg, three possible outcomes: an adult male, an adult female, or a
 * chick (1/3 chance each). A chick that hatches carries a "destined" adult
 * skin picked right away — 25% White (albino), the rest split evenly
 * between Male/Female — so its eventual grown-up color is decided at birth,
 * not re-rolled later.
 */
public class OstrichSpawnEggItem extends SpawnEggItem {

    private static final int GROWTH_TICKS = 48000; // 2 Minecraft days, matches MoCOstrichEntity

    public OstrichSpawnEggItem(Item.Properties properties) {
        super(properties.spawnEgg(ModEntities.MOC_OSTRICH.get()));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        MoCOstrichEntity ostrich = ModEntities.MOC_OSTRICH.get().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (ostrich == null) {
            return InteractionResult.FAIL;
        }

        ostrich.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D,
                serverLevel.getRandom().nextFloat() * 360F, 0F);

        int roll = serverLevel.getRandom().nextInt(4);
        if (roll == 0) {
            ostrich.setVariant(OstrichVariant.MALE);
        } else if (roll == 1) {
            ostrich.setVariant(OstrichVariant.FEMALE);
        } else if (roll == 2) {
            ostrich.setVariant(OstrichVariant.WHITE);
        } else {
            // Chick — 25% destined White, otherwise split evenly Male/Female.
            OstrichVariant destined = serverLevel.getRandom().nextFloat() < 0.25F
                    ? OstrichVariant.WHITE
                    : (serverLevel.getRandom().nextBoolean() ? OstrichVariant.MALE : OstrichVariant.FEMALE);
    ostrich.setVariant(destined);
    ostrich.setBaby(true);
    ostrich.setAge(-GROWTH_TICKS);
}

        serverLevel.addFreshEntity(ostrich);

        var player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}