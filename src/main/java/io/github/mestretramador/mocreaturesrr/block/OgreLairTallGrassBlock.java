package io.github.mestretramador.mocreaturesrr.block;

import io.github.mestretramador.mocreaturesrr.init.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class OgreLairTallGrassBlock extends VegetationBlock {

    public OgreLairTallGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModBlocks.GRASS_OGRE_LAIR.get()) || state.is(ModBlocks.DIRT_OGRE_LAIR.get());
    }

    // Burns like vanilla short grass (was FireBlock#setFlammable(block, 60, 100), now private in 26.x)
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return ModBlocks.fireOdds(state, 100);
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return ModBlocks.fireOdds(state, 60);
    }
}
