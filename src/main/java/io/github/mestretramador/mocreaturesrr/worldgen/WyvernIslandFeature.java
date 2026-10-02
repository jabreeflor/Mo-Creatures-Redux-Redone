package io.github.mestretramador.mocreaturesrr.worldgen;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Optional;

public record WyvernIslandFeature() implements Feature {

    public static final MapCodec<WyvernIslandFeature> CODEC = MapCodec.unit(WyvernIslandFeature::new);

    private static final String[] STRUCTURE_NAMES = {"wyvernisland1", "wyvernisland2", "wyvernisland3", "wyvernisland4"};

    @Override
    public MapCodec<WyvernIslandFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        ResourceKey<Level> dimensionKey = level.getLevel().dimension();
        if (!dimensionKey.identifier().toString().contains("wyvernlairworld")) {
            return false;
        }

        int chunkX = (origin.getX() >> 4) << 4;
        int chunkZ = (origin.getZ() >> 4) << 4;
        int count = random.nextInt(2) + 1;

        for (int a = 0; a < count; a++) {
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x, z);
            String structureName = STRUCTURE_NAMES[random.nextInt(STRUCTURE_NAMES.length)];
            Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
            Mirror mirror = Mirror.values()[random.nextInt(Mirror.values().length)];
            BlockPos spawnPos = new BlockPos(x, y + random.nextInt(84) + 16, z);

            StructureTemplateManager templateManager = level.getLevel().getStructureTemplateManager();
            Identifier location = Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, structureName);

            Optional<StructureTemplate> optionalTemplate = templateManager.get(location);
            if (optionalTemplate.isEmpty()) {
                continue;
            }
            StructureTemplate template = optionalTemplate.get();
            Vec3i size = template.getSize();
            if (size.getX() == 0 || size.getY() == 0 || size.getZ() == 0) {
                continue;
            }

            StructurePlaceSettings placementSettings = new StructurePlaceSettings()
                    .setRotation(rotation)
                    .setMirror(mirror)
                    .setRandom(random)
                    .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK)
                    .setIgnoreEntities(false)
                    .setKnownShape(false);
            template.placeInWorld(level, spawnPos, spawnPos, placementSettings, random, 2);
        }

        return true;
    }
}