package io.github.mestretramador.mocreaturesrr.init;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;

/**
 * Central registry of the custom dimension keys added by this mod.
 * <p>
 * Dimension keys are not registered through a {@code DeferredRegister} like
 * items or blocks: the dimension itself is defined by the datapack files in
 * {@code data/mocreaturesrr/dimension} and {@code data/mocreaturesrr/dimension_type}.
 * This class only exposes a typed {@link ResourceKey} so the rest of the
 * codebase never has to hardcode the raw resource location string.
 */
public final class ModDimensions {

    private ModDimensions() {
    }

    public static final ResourceKey<Level> WYVERN_LAIR = ResourceKey.create(
            Registries.DIMENSION,
            Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "wyvernlairworld"));
}