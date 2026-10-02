package io.github.mestretramador.mocreaturesrr.event;

import io.github.mestretramador.mocreaturesrr.entity.MoCKittyEntity;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

/**
 * Mirrors vanilla's CatSpawner: not the normal per-chunk biome spawn cycle —
 * periodically checks each player's surroundings for "is this inside a
 * village" (ServerLevel#isVillage, the same loose check iron golems/cats use,
 * not "is this exact block part of a structure piece") and tops up the local
 * kitty population if it's under the cap.
 */
public class KittyVillageSpawner {

    private static final int CHECK_INTERVAL_TICKS = 200;
    private static final int SEARCH_RADIUS = 24;
    private static final int MIN_POPULATION = 4;
    private static final int MAX_POPULATION = 7;

    private static int tickCounter;

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level) || level.dimension() != Level.OVERWORLD) {
            return;
        }
        if (++tickCounter < CHECK_INTERVAL_TICKS) {
            return;
        }
        tickCounter = 0;

        for (ServerPlayer player : level.players()) {
            if (player.isSpectator()) {
                continue;
            }
            BlockPos base = player.blockPosition();
            BlockPos candidate = base.offset(
                    level.getRandom().nextInt(SEARCH_RADIUS * 2) - SEARCH_RADIUS,
                    0,
                    level.getRandom().nextInt(SEARCH_RADIUS * 2) - SEARCH_RADIUS);

            if (!level.isVillage(candidate)) {
                continue;
            }

            int cap = MIN_POPULATION + level.getRandom().nextInt(MAX_POPULATION - MIN_POPULATION + 1);
            long nearby = level.getEntitiesOfClass(MoCKittyEntity.class,
                    new AABB(candidate).inflate(SEARCH_RADIUS)).size();
            if (nearby >= cap) {
                continue;
            }

            BlockPos spawnPos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, candidate);
            if (!level.getBlockState(spawnPos.below()).canOcclude()
                    || !Animal.checkAnimalSpawnRules(ModEntities.MOC_KITTY.get(), level, EntitySpawnReason.NATURAL, spawnPos, level.getRandom())) {
                continue;
            }

            MoCKittyEntity kitty = ModEntities.MOC_KITTY.get().create(level, EntitySpawnReason.NATURAL);
            if (kitty == null) {
                continue;
            }
            kitty.snapTo(spawnPos.getX() + 0.5D, spawnPos.getY(), spawnPos.getZ() + 0.5D, 0F, 0F);
            kitty.finalizeSpawn(level, level.getCurrentDifficultyAt(spawnPos), EntitySpawnReason.NATURAL, null);
            level.addFreshEntity(kitty);
        }
    }
}