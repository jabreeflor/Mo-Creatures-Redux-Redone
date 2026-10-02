package io.github.mestretramador.mocreaturesrr.init;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.block.OgreLairGrassBlock;
import io.github.mestretramador.mocreaturesrr.block.OgreLairTallGrassBlock;
import io.github.mestretramador.mocreaturesrr.block.WyvGrassBlock;
import io.github.mestretramador.mocreaturesrr.block.WyvSaplingBlock;
import io.github.mestretramador.mocreaturesrr.block.WyvTallGrassBlock;
import io.github.mestretramador.mocreaturesrr.block.WyvernNestBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.sounds.AmbientLeavesBlockSoundPlayer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColoredFallingBlock;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MoCreaturesReduxRedone.MODID);

    // ==== Stone / cobble / mossy ====
    public static final DeferredBlock<Block> WYVSTONE = BLOCKS.registerSimpleBlock("wyvstone",
            p -> p.mapColor(MapColor.STONE).sound(SoundType.STONE)
                    .strength(1.5F, 10.0F).requiresCorrectToolForDrops());

    public static final DeferredBlock<Block> COBBLED_WYVSTONE = BLOCKS.registerSimpleBlock("cobbled_wyvstone",
            p -> p.mapColor(MapColor.STONE).sound(SoundType.STONE)
                    .strength(2.0F, 10.0F).requiresCorrectToolForDrops());

    public static final DeferredBlock<Block> DEEP_WYVSTONE = BLOCKS.registerSimpleBlock("deep_wyvstone",
            p -> p.mapColor(MapColor.STONE).sound(SoundType.STONE)
                    .strength(3.0F, 10.0F).requiresCorrectToolForDrops());

    public static final DeferredBlock<Block> COBBLED_DEEP_WYVSTONE = BLOCKS.registerSimpleBlock("cobbled_deep_wyvstone",
            p -> p.mapColor(MapColor.STONE).sound(SoundType.STONE)
                    .strength(3.5F, 10.0F).requiresCorrectToolForDrops());

    public static final DeferredBlock<Block> MOSSY_COBBLED_WYVSTONE = BLOCKS.registerSimpleBlock("mossy_cobbled_wyvstone",
            p -> p.mapColor(MapColor.STONE).sound(SoundType.STONE)
                    .strength(1.5F, 10.0F).requiresCorrectToolForDrops());

    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEP_WYVSTONE = BLOCKS.registerSimpleBlock("mossy_cobbled_deep_wyvstone",
            p -> p.mapColor(MapColor.STONE).sound(SoundType.STONE)
                    .strength(1.5F, 10.0F).requiresCorrectToolForDrops());

    // ==== Dirt ====
    public static final DeferredBlock<Block> WYVDIRT = BLOCKS.registerSimpleBlock("wyvdirt",
            p -> p.mapColor(MapColor.DIRT).sound(SoundType.GRAVEL).strength(0.6F));

    public static final DeferredBlock<WyvGrassBlock> WYVGRASS = BLOCKS.registerBlock("wyvgrass",
            props -> new WyvGrassBlock(props),
            p -> p.mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.GRASS)
                    .strength(0.7F).randomTicks());

    public static final DeferredBlock<WyvTallGrassBlock> TALL_WYVGRASS = BLOCKS.registerBlock("tall_wyvgrass",
            props -> new WyvTallGrassBlock(props),
            p -> p.mapColor(MapColor.PLANT).sound(SoundType.GRASS)
                    .noCollision().instabreak().replaceable().pushReaction(PushReaction.POPPED));

    public static final DeferredBlock<WyvSaplingBlock> WYVWOOD_SAPLING = BLOCKS.registerBlock("wyvwood_sapling",
            props -> new WyvSaplingBlock(props),
            p -> p.mapColor(MapColor.PLANT).sound(SoundType.GRASS)
                    .noCollision().randomTicks().instabreak().pushReaction(PushReaction.POPPED));

    // ==== Wood ====
    public static final DeferredBlock<Block> WYVWOOD_LOG = BLOCKS.registerBlock("wyvwood_log",
            props -> new RotatedPillarBlock(props) {
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return fireOdds(state, 5);
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return fireOdds(state, 5);
                }
            },
            p -> p.mapColor(MapColor.COLOR_CYAN).sound(SoundType.WOOD)
                    .strength(2.0F).ignitedByLava());

    public static final DeferredBlock<LeavesBlock> WYVWOOD_LEAVES = BLOCKS.registerBlock("wyvwood_leaves",
            props -> new LeavesBlock(AmbientLeavesBlockSoundPlayer.noAmbientSound(), props) {
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return fireOdds(state, 60);
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return fireOdds(state, 30);
                }
            },
            p -> p.mapColor(MapColor.PLANT).sound(SoundType.GRASS)
                    .strength(0.2F).randomTicks().noOcclusion().isValidSpawn((state, level, pos, type) -> false)
                    .isSuffocating((state, level, pos) -> false).isViewBlocking((state, level, pos, box) -> false)
                    .ignitedByLava().pushReaction(PushReaction.POPPED));

    public static final DeferredBlock<Block> WYVWOOD_PLANKS = BLOCKS.registerBlock("wyvwood_planks",
            props -> new Block(props) {
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return fireOdds(state, 20);
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return fireOdds(state, 5);
                }
            },
            p -> p.mapColor(MapColor.COLOR_CYAN).sound(SoundType.WOOD)
                    .strength(2.0F, 5.0F).ignitedByLava());

    // ==== Ores (drop XP when mined, like vanilla) ====
    public static final DeferredBlock<net.minecraft.world.level.block.DropExperienceBlock> WYVERN_DIAMOND_ORE = BLOCKS.registerBlock("wyvern_diamond_ore",
            props -> new net.minecraft.world.level.block.DropExperienceBlock(net.minecraft.util.valueproviders.UniformInt.of(4, 8), props),
            p -> p.mapColor(MapColor.STONE).sound(SoundType.STONE)
                    .strength(4.5F, 5.0F).requiresCorrectToolForDrops());

    public static final DeferredBlock<net.minecraft.world.level.block.DropExperienceBlock> WYVERN_EMERALD_ORE = BLOCKS.registerBlock("wyvern_emerald_ore",
            props -> new net.minecraft.world.level.block.DropExperienceBlock(net.minecraft.util.valueproviders.UniformInt.of(4, 8), props),
            p -> p.mapColor(MapColor.STONE).sound(SoundType.STONE)
                    .strength(4.5F, 5.0F).requiresCorrectToolForDrops());

    public static final DeferredBlock<net.minecraft.world.level.block.DropExperienceBlock> WYVERN_LAPIS_ORE = BLOCKS.registerBlock("wyvern_lapis_ore",
            props -> new net.minecraft.world.level.block.DropExperienceBlock(net.minecraft.util.valueproviders.UniformInt.of(3, 6), props),
            p -> p.mapColor(MapColor.STONE).sound(SoundType.STONE)
                    .strength(1.5F, 5.0F).requiresCorrectToolForDrops());

    // Gold and iron don't give direct XP when mined (same as their vanilla equivalents)
    public static final DeferredBlock<Block> WYVERN_GOLD_ORE = BLOCKS.registerSimpleBlock("wyvern_gold_ore",
            p -> p.mapColor(MapColor.STONE).sound(SoundType.STONE)
                    .strength(3.0F, 5.0F).requiresCorrectToolForDrops());

    public static final DeferredBlock<Block> WYVERN_IRON_ORE = BLOCKS.registerSimpleBlock("wyvern_iron_ore",
            p -> p.mapColor(MapColor.STONE).sound(SoundType.STONE)
                    .strength(3.0F, 5.0F).requiresCorrectToolForDrops());

    public static final DeferredBlock<Block> WYVERN_ANCIENT_ORE = BLOCKS.registerSimpleBlock("wyvern_ancient_ore",
            p -> p.mapColor(MapColor.STONE).sound(SoundType.STONE)
                    .strength(3.0F, 5.0F).requiresCorrectToolForDrops());

    // ==== Ogre Lair block set ====
    public static final DeferredBlock<Block> DIRT_OGRE_LAIR = BLOCKS.registerSimpleBlock("dirt_ogre_lair",
            p -> p.mapColor(MapColor.DIRT).sound(SoundType.GRAVEL).strength(0.6F));

        public static final DeferredBlock<OgreLairGrassBlock> GRASS_OGRE_LAIR = BLOCKS.registerBlock("grass_ogre_lair",
            props -> new OgreLairGrassBlock(props),
            p -> p.mapColor(MapColor.COLOR_PURPLE).sound(SoundType.GRASS)
                    .strength(0.7F).randomTicks());

        public static final DeferredBlock<OgreLairTallGrassBlock> TALL_GRASS_OGRE_LAIR = BLOCKS.registerBlock("tall_grass_ogre_lair",
            props -> new OgreLairTallGrassBlock(props),
            p -> p.mapColor(MapColor.PLANT).sound(SoundType.GRASS)
                    .noCollision().instabreak().replaceable().pushReaction(PushReaction.POPPED));

    public static final DeferredBlock<Block> LOG_OGRE_LAIR = BLOCKS.registerBlock("log_ogre_lair",
            props -> new RotatedPillarBlock(props) {
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return fireOdds(state, 5);
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return fireOdds(state, 5);
                }
            },
            p -> p.mapColor(MapColor.COLOR_PURPLE).sound(SoundType.WOOD)
                    .strength(2.0F).ignitedByLava());

    public static final DeferredBlock<LeavesBlock> LEAVES_OGRE_LAIR = BLOCKS.registerBlock("leaves_ogre_lair",
            props -> new LeavesBlock(AmbientLeavesBlockSoundPlayer.noAmbientSound(), props) {
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return fireOdds(state, 60);
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return fireOdds(state, 30);
                }
            },
            p -> p.mapColor(MapColor.PLANT).sound(SoundType.GRASS)
                    .strength(0.2F).randomTicks().noOcclusion().isValidSpawn((state, level, pos, type) -> false)
                    .isSuffocating((state, level, pos) -> false).isViewBlocking((state, level, pos, box) -> false)
                    .ignitedByLava().pushReaction(PushReaction.POPPED));

    public static final DeferredBlock<Block> WOOD_PLANKS_OGRE_LAIR = BLOCKS.registerBlock("wood_planks_ogre_lair",
            props -> new Block(props) {
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return fireOdds(state, 20);
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return fireOdds(state, 5);
                }
            },
            p -> p.mapColor(MapColor.COLOR_PURPLE).sound(SoundType.WOOD)
                    .strength(2.0F, 5.0F).ignitedByLava());

    // ==== Ancient Silver / Silver Sandstone block set ====

   public static final DeferredBlock<Block> ANCIENT_SILVER_BLOCK = BLOCKS.registerBlock(
        "ancient_silver_block",
        Block::new,
        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                .strength(3.0F, 10.0F)
        );

   public static final DeferredBlock<ColoredFallingBlock> SILVER_SAND = BLOCKS.registerBlock(
        "silver_sand",
        props -> new ColoredFallingBlock(
                new net.minecraft.util.ColorRGBA(0xC0C0C0FF),
                props
        ),
        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)
                .strength(0.6F, 0.6F)
        );

    public static final DeferredBlock<Block> SILVER_SANDSTONE = BLOCKS.registerBlock(
        "silver_sandstone",
        Block::new,
        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE)
                .strength(1.2F, 1.2F)
        );

    public static final DeferredBlock<Block> SILVER_SANDSTONE_CARVED = BLOCKS.registerBlock(
        "silver_sandstone_carved",
        Block::new,
        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_SANDSTONE)
                .strength(1.2F, 1.2F)
        );

    public static final DeferredBlock<Block> SILVER_SANDSTONE_SMOOTH = BLOCKS.registerBlock(
        "silver_sandstone_smooth",
        Block::new,
        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE)
                .strength(1.2F, 1.2F)
        );

    // ==== Loose pieces ====
    public static final DeferredBlock<Block> ORE_FIRESTONE = BLOCKS.registerSimpleBlock("ore_firestone",
            p -> p.mapColor(MapColor.TERRACOTTA_ORANGE).sound(SoundType.GLASS)
                    .strength(3.0F, 3.0F).lightLevel(state -> 7).requiresCorrectToolForDrops());

   public static final DeferredBlock<TransparentBlock> GLASS_GLEAMING = BLOCKS.registerBlock("glass_gleaming",
            props -> new TransparentBlock(props),
            p -> p.instrument(NoteBlockInstrument.HAT).sound(SoundType.GLASS)
                    .strength(0.4F).lightLevel(state -> 7).noOcclusion()
                    .isValidSpawn((state, level, pos, type) -> false)
                    .isRedstoneConductor((state, level, pos) -> false)
                    .isSuffocating((state, level, pos) -> false)
                    .isViewBlocking((state, level, pos, box) -> false));

    public static final DeferredBlock<WyvernNestBlock> BLOCK_WYVERN_NEST = BLOCKS.registerBlock("block_wyvern_nest",
            props -> new WyvernNestBlock(props),
            p -> p.mapColor(MapColor.COLOR_YELLOW).sound(SoundType.GRASS).strength(0.5F, 0.5F));


    /**
     * Makes the mod's wood, leaves and grass burn and spread fire like their vanilla counterparts
     * (ignitedByLava() alone only lets lava light them). Same values vanilla uses.
     * 26.x: FireBlock#setFlammable is private, so the values are now provided by overriding
     * NeoForge's IBlockExtension#getFlammability / #getFireSpreadSpeed on the blocks themselves
     * (logs/leaves/planks above, tall grass in WyvTallGrassBlock / OgreLairTallGrassBlock).
     * Kept as a no-op so the common-setup call site keeps working.
     */
    public static void registerFlammability() {
    }

    /** Same rule as vanilla FireBlock: waterlogged blocks never burn. */
    public static int fireOdds(BlockState state, int odds) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)
                ? 0
                : odds;
    }

}