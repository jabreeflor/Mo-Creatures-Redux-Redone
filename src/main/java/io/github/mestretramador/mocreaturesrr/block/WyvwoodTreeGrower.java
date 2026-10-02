package io.github.mestretramador.mocreaturesrr.block;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.Feature;

public class WyvwoodTreeGrower {

    // 26.3: configured features are now plain Features in the minecraft:worldgen/feature registry
    public static final ResourceKey<Feature> WYVWOOD_TREE = ResourceKey.create(
            net.minecraft.core.registries.Registries.FEATURE,
            Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "wyvwood_tree"));

    public static final ResourceKey<Feature> WYVWOOD_MEGA_TREE = ResourceKey.create(
            net.minecraft.core.registries.Registries.FEATURE,
            Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "wyvwood_mega_tree"));

    // Same as before: a 2x2 of saplings grows the mega tree, a single sapling the normal one, no flower variant.
    public static final TreeGrower WYVWOOD = new TreeGrower(
            "wyvwood",
            WeightedList.of(WYVWOOD_TREE),
            WeightedList.of(WYVWOOD_MEGA_TREE),
            WeightedList.of(),
            WYVWOOD_TREE
    );
}
