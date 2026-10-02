package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCWyvernEntity;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * One egg, three possible outcomes: tier 1 wyvern, tier 2 wyvern (same 8
 * textures, bigger), or a plain "wyvern_mother" (the only mother texture
 * allowed to spawn naturally — see MoCWyvernEntity's constructor).
 * Weights below are a starting guess (mother should be rare); tune freely.
 */
public class WyvernSpawnEggItem extends SpawnEggItem {

    private record WeightedType(DeferredHolder<EntityType<?>, EntityType<MoCWyvernEntity>> type, int weight) {}

    private static final WeightedType[] OPTIONS = {
            new WeightedType(ModEntities.WYVERN, 70),
            new WeightedType(ModEntities.WYVERN_TIER2, 25),
            new WeightedType(ModEntities.WYVERN_MOTHER, 5),
    };
    private static final int TOTAL_WEIGHT =
            OPTIONS[0].weight() + OPTIONS[1].weight() + OPTIONS[2].weight();

    public WyvernSpawnEggItem(Item.Properties properties) {
        super(properties.spawnEgg(ModEntities.WYVERN.get()));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        EntityType<MoCWyvernEntity> pickedType = pickType(serverLevel);

        MoCWyvernEntity wyvern = pickedType.create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (wyvern == null) {
            return InteractionResult.FAIL;
        }

        wyvern.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D,
                serverLevel.getRandom().nextFloat() * 360F, 0F);
        serverLevel.addFreshEntity(wyvern);

        var player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }

    private EntityType<MoCWyvernEntity> pickType(ServerLevel level) {
        int roll = level.getRandom().nextInt(TOTAL_WEIGHT);
        int cumulative = 0;
        for (WeightedType option : OPTIONS) {
            cumulative += option.weight();
            if (roll < cumulative) {
                return option.type().get();
            }
        }
        return ModEntities.WYVERN.get();
    }
}