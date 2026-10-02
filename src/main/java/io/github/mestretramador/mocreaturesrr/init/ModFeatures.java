package io.github.mestretramador.mocreaturesrr.init;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.worldgen.FirestoneClusterFeature;
import io.github.mestretramador.mocreaturesrr.worldgen.WyvernIslandFeature;
import io.github.mestretramador.mocreaturesrr.worldgen.WyvernNestFeature;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Feature types (26.3: the feature *type* registry holds the MapCodec of each Feature implementation;
 * the configured instances live in data/mocreaturesrr/worldgen/feature).
 */
public class ModFeatures {

    public static final DeferredRegister<MapCodec<? extends Feature>> FEATURES =
            DeferredRegister.create(Registries.FEATURE_TYPE, MoCreaturesReduxRedone.MODID);

    public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<FirestoneClusterFeature>> FIRESTONE_CLUSTER =
            FEATURES.register("firestone_cluster", () -> FirestoneClusterFeature.CODEC);

    public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<WyvernNestFeature>> WYVERN_NEST =
            FEATURES.register("wyvern_nest", () -> WyvernNestFeature.CODEC);

   public static final DeferredHolder<MapCodec<? extends Feature>, MapCodec<WyvernIslandFeature>> WYVERN_ISLAND =
        FEATURES.register("wyvern_island", () -> WyvernIslandFeature.CODEC);
}
