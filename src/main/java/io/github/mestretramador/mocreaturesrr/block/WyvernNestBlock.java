package io.github.mestretramador.mocreaturesrr.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Reduces fall damage (like a HayBlock/fluffy nest).
 */
public class WyvernNestBlock extends Block {

    public WyvernNestBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        entity.causeFallDamage(fallDistance, 0.2F, level.damageSources().fall());
    }
}