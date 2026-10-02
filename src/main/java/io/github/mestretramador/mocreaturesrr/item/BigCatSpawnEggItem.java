package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCBigCatEntity;
import io.github.mestretramador.mocreaturesrr.entity.bigcat.BigCatVariant;
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
 * One spawn egg per species family (Lion/Tiger/Leopard/Panther) — matches the
 * original mod exactly, which never had a separate white lion/white tiger/snow
 * leopard egg. The rare look is rolled after spawning, same as the wild
 * population: 25% white for lion/tiger, biome-based for leopard, none for panther.
 */
public class BigCatSpawnEggItem extends SpawnEggItem {

    private final BigCatVariant.SpawnFamily family;
    private final BigCatVariant hybridVariant;

    /** For the 4 species-family eggs (Lion/Tiger/Leopard/Panther). */
    public BigCatSpawnEggItem(BigCatVariant.SpawnFamily family, Item.Properties properties) {
        super(properties.spawnEgg(ModEntities.MOC_BIG_CAT.get()));
        this.family = family;
        this.hybridVariant = null;
    }

    /** For a single hybrid egg — always produces the same exact variant. */
    public BigCatSpawnEggItem(BigCatVariant hybridVariant, Item.Properties properties) {
        super(properties.spawnEgg(ModEntities.MOC_BIG_CAT.get()));
        this.family = BigCatVariant.SpawnFamily.HYBRID;
        this.hybridVariant = hybridVariant;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        MoCBigCatEntity bigCat = ModEntities.MOC_BIG_CAT.get().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (bigCat == null) {
            return InteractionResult.FAIL;
        }

        bigCat.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D,
                serverLevel.getRandom().nextFloat() * 360F, 0F);
        bigCat.setVariant(pickVariant(serverLevel, pos));
        // finalizeSpawn() never runs here (we're building the entity by hand, not
        // through the normal spawn pipeline), so the cub chance has to be rolled
        // separately — matches the same 25% MoCBigCatEntity#finalizeSpawn uses.
        if (serverLevel.getRandom().nextInt(4) == 0) {
            bigCat.setAge(-MoCBigCatEntity.getGrowthTicks(bigCat.getVariant()));
        }
        serverLevel.addFreshEntity(bigCat);

        var player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }

    private BigCatVariant pickVariant(ServerLevel level, BlockPos pos) {
        return switch (family) {
            case LION -> BigCatVariant.randomLion(level.getRandom());
            case TIGER -> BigCatVariant.randomTiger(level.getRandom());
            case LEOPARD -> MoCBigCatEntity.isSnowyBiome(level, pos) ? BigCatVariant.SNOW_LEOPARD : BigCatVariant.LEOPARD;
            case PANTHER -> BigCatVariant.randomPanther(level.getRandom());
            case HYBRID -> hybridVariant;
        };
    }
}