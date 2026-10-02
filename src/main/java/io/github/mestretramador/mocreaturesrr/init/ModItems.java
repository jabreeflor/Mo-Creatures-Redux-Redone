package io.github.mestretramador.mocreaturesrr.init;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.item.ScorpionSwordItem;
import io.github.mestretramador.mocreaturesrr.item.WildHorseSpawnEggItem;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MoCreaturesReduxRedone.MODID);

    // Raw meats: 30% chance of Hunger (600 ticks). Food effects moved from FoodProperties to the Consumable component in 1.21.2.
    private static final Consumable RAW_MEAT_HUNGER = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F))
            .build();

    public static final DeferredItem<Item> WILD_HORSE_SPAWN_EGG = ITEMS.registerItem("wild_horse_spawn_egg",
            p -> new WildHorseSpawnEggItem(ModEntities.MOC_HORSE, p));

    public static final DeferredItem<Item> MOC_HORSE_MOB_SPAWN_EGG = ITEMS.registerItem("moc_horse_mob_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(ModEntities.MOC_HORSE_MOB.get()));
    public static final DeferredItem<Item> WYVERN_SPAWN_EGG = ITEMS.registerItem("wyvern_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.WyvernSpawnEggItem(p));
    public static final DeferredItem<Item> MOC_ELEPHANT_SPAWN_EGG = ITEMS.registerItem("moc_elephant_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(ModEntities.MOC_ELEPHANT.get()));
    public static final DeferredItem<Item> LION_SPAWN_EGG = ITEMS.registerItem("lion_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BigCatSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bigcat.BigCatVariant.SpawnFamily.LION, p));
    public static final DeferredItem<Item> TIGER_SPAWN_EGG = ITEMS.registerItem("tiger_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BigCatSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bigcat.BigCatVariant.SpawnFamily.TIGER, p));
    public static final DeferredItem<Item> LEOPARD_SPAWN_EGG = ITEMS.registerItem("leopard_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BigCatSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bigcat.BigCatVariant.SpawnFamily.LEOPARD, p));
    public static final DeferredItem<Item> PANTHER_SPAWN_EGG = ITEMS.registerItem("panther_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BigCatSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bigcat.BigCatVariant.SpawnFamily.PANTHER, p));
    public static final DeferredItem<Item> LIGER_SPAWN_EGG = ITEMS.registerItem("liger_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BigCatSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bigcat.BigCatVariant.LIGER, p));
    public static final DeferredItem<Item> LIARD_SPAWN_EGG = ITEMS.registerItem("liard_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BigCatSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bigcat.BigCatVariant.LIARD, p));
    public static final DeferredItem<Item> LEOGER_SPAWN_EGG = ITEMS.registerItem("leoger_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BigCatSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bigcat.BigCatVariant.LEOGER, p));
    public static final DeferredItem<Item> LITHER_SPAWN_EGG = ITEMS.registerItem("lither_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BigCatSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bigcat.BigCatVariant.LITHER, p));
    public static final DeferredItem<Item> PANTHARD_SPAWN_EGG = ITEMS.registerItem("panthard_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BigCatSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bigcat.BigCatVariant.PANTHARD, p));
    public static final DeferredItem<Item> PANTHGER_SPAWN_EGG = ITEMS.registerItem("panthger_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BigCatSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bigcat.BigCatVariant.PANTHGER, p));
    public static final DeferredItem<Item> MANTICORE_SPAWN_EGG = ITEMS.registerItem("manticore_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.ManticoreSpawnEggItem(p));
    public static final DeferredItem<Item> SCORPION_SPAWN_EGG = ITEMS.registerItem("scorpion_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.ScorpionSpawnEggItem(p));
    public static final DeferredItem<Item> OSTRICH_SPAWN_EGG = ITEMS.registerItem("ostrich_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.OstrichSpawnEggItem(p));
    public static final DeferredItem<Item> BLACK_BEAR_SPAWN_EGG = ITEMS.registerItem("black_bear_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BearSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bear.BearVariant.BLACK, p));
        public static final DeferredItem<Item> GRIZZLY_BEAR_SPAWN_EGG = ITEMS.registerItem("grizzly_bear_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BearSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bear.BearVariant.GRIZZLY, p));
        public static final DeferredItem<Item> POLAR_BEAR_SPAWN_EGG = ITEMS.registerItem("polar_bear_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BearSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bear.BearVariant.POLAR, p));
        public static final DeferredItem<Item> PANDA_BEAR_SPAWN_EGG = ITEMS.registerItem("panda_bear_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BearSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.bear.BearVariant.PANDA, p));
        public static final DeferredItem<Item> FOX_SPAWN_EGG = ITEMS.registerItem("fox_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.FoxSpawnEggItem(p));
        public static final DeferredItem<Item> RACCOON_SPAWN_EGG = ITEMS.registerItem("raccoon_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_RACCOON.get()));
        public static final DeferredItem<Item> TURKEY_SPAWN_EGG = ITEMS.registerItem("turkey_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_TURKEY.get()));
        public static final DeferredItem<Item> KOMODO_DRAGON_SPAWN_EGG = ITEMS.registerItem("komodo_dragon_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_KOMODO_DRAGON.get()));
        public static final DeferredItem<Item> GOAT_SPAWN_EGG = ITEMS.registerItem("goat_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_GOAT.get()));
        public static final DeferredItem<Item> KITTY_SPAWN_EGG = ITEMS.registerItem("kitty_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_KITTY.get()));
        public static final DeferredItem<Item> SNAKE_SPAWN_EGG = ITEMS.registerItem("snake_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SnakeSpawnEggItem(p));
        public static final DeferredItem<Item> BUNNY_SPAWN_EGG = ITEMS.registerItem("bunny_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BunnySpawnEggItem(p));
        public static final DeferredItem<Item> BIRD_SPAWN_EGG = ITEMS.registerItem("bird_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.BirdSpawnEggItem(p));
        public static final DeferredItem<Item> SHARK_SPAWN_EGG = ITEMS.registerItem("shark_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SharkSpawnEggItem(p));
        public static final DeferredItem<Item> TURTLE_SPAWN_EGG = ITEMS.registerItem("turtle_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_TURTLE.get()));
        public static final DeferredItem<Item> STINGRAY_SPAWN_EGG = ITEMS.registerItem("stingray_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_STINGRAY.get()));
        public static final DeferredItem<Item> DOLPHIN_SPAWN_EGG = ITEMS.registerItem("dolphin_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_DOLPHIN.get()));
        public static final DeferredItem<Item> MANTA_RAY_SPAWN_EGG = ITEMS.registerItem("manta_ray_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_MANTA_RAY.get()));
        public static final DeferredItem<Item> FISHY_SPAWN_EGG = ITEMS.registerItem("fishy_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_FISHY.get()));
        public static final DeferredItem<Item> COD_FISH_SPAWN_EGG = ITEMS.registerItem("cod_fish_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_COD.get()));
        public static final DeferredItem<Item> SALMON_FISH_SPAWN_EGG = ITEMS.registerItem("salmon_fish_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SALMON.get()));
        public static final DeferredItem<Item> BASS_FISH_SPAWN_EGG = ITEMS.registerItem("bass_fish_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_BASS.get()));
        public static final DeferredItem<Item> ANCHOVY_SPAWN_EGG = ITEMS.registerItem("anchovy_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.smallfish.SmallFishVariant.ANCHOVY, p));
        public static final DeferredItem<Item> ANGELFISH_SPAWN_EGG = ITEMS.registerItem("angelfish_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.smallfish.SmallFishVariant.ANGELFISH, p));
        public static final DeferredItem<Item> ANGLERFISH_SPAWN_EGG = ITEMS.registerItem("anglerfish_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.smallfish.SmallFishVariant.ANGLER, p));
        public static final DeferredItem<Item> CLOWNFISH_SPAWN_EGG = ITEMS.registerItem("clownfish_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.smallfish.SmallFishVariant.CLOWNFISH, p));
        public static final DeferredItem<Item> GOLDFISH_SPAWN_EGG = ITEMS.registerItem("goldfish_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.smallfish.SmallFishVariant.GOLDFISH, p));
        public static final DeferredItem<Item> HIPPOTANG_SPAWN_EGG = ITEMS.registerItem("hippotang_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.smallfish.SmallFishVariant.HIPPOTANG, p));
        public static final DeferredItem<Item> MANDARINFISH_SPAWN_EGG = ITEMS.registerItem("mandarinfish_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.smallfish.SmallFishVariant.MANDARIN, p));
        public static final DeferredItem<Item> PIRANHA_SPAWN_EGG = ITEMS.registerItem("piranha_spawn_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishSpawnEggItem(
                io.github.mestretramador.mocreaturesrr.entity.smallfish.SmallFishVariant.PIRANHA, p));
        public static final DeferredItem<Item> JELLYFISH_SPAWN_EGG = ITEMS.registerItem("jellyfish_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_JELLYFISH.get()));
        public static final DeferredItem<Item> CRAB_SPAWN_EGG = ITEMS.registerItem("crab_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_CRAB.get()));
        public static final DeferredItem<Item> CROCODILE_SPAWN_EGG = ITEMS.registerItem("crocodile_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_CROCODILE.get()));
        public static final DeferredItem<Item> GREEN_OGRE_SPAWN_EGG = ITEMS.registerItem("green_ogre_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_GREEN_OGRE.get()));
        public static final DeferredItem<Item> FIRE_OGRE_SPAWN_EGG = ITEMS.registerItem("fire_ogre_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_FIRE_OGRE.get()));
        public static final DeferredItem<Item> CAVE_OGRE_SPAWN_EGG = ITEMS.registerItem("cave_ogre_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_CAVE_OGRE.get()));
        public static final DeferredItem<Item> WEREWOLF_SPAWN_EGG = ITEMS.registerItem("werewolf_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_WEREWOLF.get()));
        public static final DeferredItem<Item> WILD_WOLF_SPAWN_EGG = ITEMS.registerItem("wild_wolf_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_WILD_WOLF.get()));
        public static final DeferredItem<Item> BOAR_SPAWN_EGG = ITEMS.registerItem("boar_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_BOAR.get()));
        public static final DeferredItem<Item> DEER_SPAWN_EGG = ITEMS.registerItem("deer_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_DEER.get()));
        public static final DeferredItem<Item> RAT_SPAWN_EGG = ITEMS.registerItem("rat_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_RAT.get()));
        public static final DeferredItem<Item> HELL_RAT_SPAWN_EGG = ITEMS.registerItem("hellrat_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_HELL_RAT.get()));
        public static final DeferredItem<Item> MOUSE_SPAWN_EGG = ITEMS.registerItem("mouse_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_MOUSE.get()));
        public static final DeferredItem<Item> MOLE_SPAWN_EGG = ITEMS.registerItem("mole_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_MOLE.get()));
        public static final DeferredItem<Item> DUCK_SPAWN_EGG = ITEMS.registerItem("duck_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_DUCK.get()));
        public static final DeferredItem<Item> FLY_SPAWN_EGG = ITEMS.registerItem("fly_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_FLY.get()));
        public static final DeferredItem<Item> BUTTERFLY_SPAWN_EGG = ITEMS.registerItem("butterfly_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_BUTTERFLY.get()));
        public static final DeferredItem<Item> DRAGONFLY_SPAWN_EGG = ITEMS.registerItem("dragonfly_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_DRAGONFLY.get()));
        public static final DeferredItem<Item> FIREFLY_SPAWN_EGG = ITEMS.registerItem("firefly_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_FIREFLY.get()));
        public static final DeferredItem<Item> BEE_SPAWN_EGG = ITEMS.registerItem("bee_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_BEE.get()));
        public static final DeferredItem<Item> SNAIL_SPAWN_EGG = ITEMS.registerItem("snail_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SNAIL.get()));
        public static final DeferredItem<Item> ROACH_SPAWN_EGG = ITEMS.registerItem("roach_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_ROACH.get()));
        public static final DeferredItem<Item> ANT_SPAWN_EGG = ITEMS.registerItem("ant_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_ANT.get()));
        public static final DeferredItem<Item> MAGGOT_SPAWN_EGG = ITEMS.registerItem("maggot_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_MAGGOT.get()));
        public static final DeferredItem<Item> CRICKET_SPAWN_EGG = ITEMS.registerItem("cricket_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_CRICKET.get()));
        public static final DeferredItem<Item> GRASSHOPPER_SPAWN_EGG = ITEMS.registerItem("grasshopper_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_GRASSHOPPER.get()));
        public static final DeferredItem<Item> SILVER_SKELETON_SPAWN_EGG = ITEMS.registerItem("silverskeleton_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SILVER_SKELETON.get()));
        public static final DeferredItem<Item> WRAITH_SPAWN_EGG = ITEMS.registerItem("wraith_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_WRAITH.get()));
        public static final DeferredItem<Item> FLAME_WRAITH_SPAWN_EGG = ITEMS.registerItem("flamewraith_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_FLAME_WRAITH.get()));
        public static final DeferredItem<Item> ENT_SPAWN_EGG = ITEMS.registerItem("ent_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_ENT.get()));
        public static final DeferredItem<Item> MINI_GOLEM_SPAWN_EGG = ITEMS.registerItem("minigolem_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_MINI_GOLEM.get()));
        public static final DeferredItem<Item> BIG_GOLEM_SPAWN_EGG = ITEMS.registerItem("biggolem_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_BIG_GOLEM.get()));
        public static final DeferredItem<Item> FILCH_LIZARD_SPAWN_EGG = ITEMS.registerItem("filchlizard_spawn_egg",
            SpawnEggItem::new,
            p -> p.spawnEgg(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_FILCH_LIZARD.get()));

    public static final DeferredItem<Item> HEART_OF_UNDEAD = ITEMS.registerSimpleItem("heart_of_undead");
    public static final DeferredItem<Item> HEART_OF_FIRE = ITEMS.registerSimpleItem("heart_of_fire");
    public static final DeferredItem<Item> HEART_OF_DARKNESS = ITEMS.registerSimpleItem("heart_of_darkness");
    public static final DeferredItem<Item> ESSENCE_OF_UNDEAD = ITEMS.registerSimpleItem("essence_of_undead");
    public static final DeferredItem<Item> ESSENCE_OF_FIRE = ITEMS.registerSimpleItem("essence_of_fire");
    public static final DeferredItem<Item> ESSENCE_OF_DARKNESS = ITEMS.registerSimpleItem("essence_of_darkness");
    public static final DeferredItem<Item> ESSENCE_OF_LIGHT = ITEMS.registerSimpleItem("essence_of_light");

    // ---- Amulets ----
    public static final DeferredItem<Item> AMULET_BONE = ITEMS.registerSimpleItem("amulet_bone", p -> p.stacksTo(1));
    public static final DeferredItem<Item> AMULET_BONE_FULL = ITEMS.registerItem("amulet_bone_full",
            p -> new io.github.mestretramador.mocreaturesrr.item.FilledAmuletItem(p, false, AMULET_BONE.get()),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> AMULET_FAIRY = ITEMS.registerSimpleItem("amulet_fairy", p -> p.stacksTo(1));
    public static final DeferredItem<Item> AMULET_FAIRY_FULL = ITEMS.registerItem("amulet_fairy_full",
            p -> new io.github.mestretramador.mocreaturesrr.item.FilledAmuletItem(p, true, AMULET_FAIRY.get()),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> AMULET_GHOST = ITEMS.registerSimpleItem("amulet_ghost", p -> p.stacksTo(1));
    public static final DeferredItem<Item> AMULET_GHOST_FULL = ITEMS.registerItem("amulet_ghost_full",
            p -> new io.github.mestretramador.mocreaturesrr.item.FilledAmuletItem(p, false, AMULET_GHOST.get()),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> AMULET_PEGASUS = ITEMS.registerSimpleItem("amulet_pegasus", p -> p.stacksTo(1));
    public static final DeferredItem<Item> AMULET_PEGASUS_FULL = ITEMS.registerItem("amulet_pegasus_full",
            p -> new io.github.mestretramador.mocreaturesrr.item.FilledAmuletItem(p, false, AMULET_PEGASUS.get()),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> PET_AMULET = ITEMS.registerSimpleItem("pet_amulet", p -> p.stacksTo(1));
    public static final DeferredItem<Item> PET_AMULET_FULL = ITEMS.registerItem("pet_amulet_full",
            p -> new io.github.mestretramador.mocreaturesrr.item.FilledAmuletItem(p, false, PET_AMULET.get()),
            p -> p.stacksTo(1));
    // ---- Ancient Silver ----
    public static final DeferredItem<Item> ANCIENT_SILVER_HELMET = ITEMS.registerSimpleItem("ancient_silver_helmet",
            p -> p.humanoidArmor(ModArmorMaterials.SILVER, ArmorType.HELMET).durability(77));
    public static final DeferredItem<Item> ANCIENT_SILVER_CHESTPLATE = ITEMS.registerSimpleItem("ancient_silver_chestplate",
            p -> p.humanoidArmor(ModArmorMaterials.SILVER, ArmorType.CHESTPLATE).durability(112));
    public static final DeferredItem<Item> ANCIENT_SILVER_LEGGINGS = ITEMS.registerSimpleItem("ancient_silver_leggings",
            p -> p.humanoidArmor(ModArmorMaterials.SILVER, ArmorType.LEGGINGS).durability(105));
    public static final DeferredItem<Item> ANCIENT_SILVER_BOOTS = ITEMS.registerSimpleItem("ancient_silver_boots",
            p -> p.humanoidArmor(ModArmorMaterials.SILVER, ArmorType.BOOTS).durability(91));
    public static final DeferredItem<Item> ANCIENT_SILVER_INGOT = ITEMS.registerSimpleItem("ancient_silver_ingot");
    public static final DeferredItem<Item> ANCIENT_SILVER_NUGGET = ITEMS.registerSimpleItem("ancient_silver_nugget");
    public static final DeferredItem<Item> ANCIENT_SILVER_SCRAP = ITEMS.registerSimpleItem("ancient_silver_scrap");

    // ---- Fur / Hide / Reptile Armor ----
    public static final DeferredItem<Item> FUR = ITEMS.registerSimpleItem("fur");
    public static final DeferredItem<Item> FUR_HELMET = ITEMS.registerSimpleItem("fur_helmet",
            p -> p.humanoidArmor(ModArmorMaterials.FUR, ArmorType.HELMET).durability(165));
    public static final DeferredItem<Item> FUR_CHEST = ITEMS.registerSimpleItem("fur_chest",
            p -> p.humanoidArmor(ModArmorMaterials.FUR, ArmorType.CHESTPLATE).durability(240));
    public static final DeferredItem<Item> FUR_LEGS = ITEMS.registerSimpleItem("fur_legs",
            p -> p.humanoidArmor(ModArmorMaterials.FUR, ArmorType.LEGGINGS).durability(225));
    public static final DeferredItem<Item> FUR_BOOTS = ITEMS.registerSimpleItem("fur_boots",
            p -> p.humanoidArmor(ModArmorMaterials.FUR, ArmorType.BOOTS).durability(195));
    public static final DeferredItem<Item> HIDE = ITEMS.registerSimpleItem("hide");
    public static final DeferredItem<Item> HIDE_HELMET = ITEMS.registerSimpleItem("hide_helmet",
            p -> p.humanoidArmor(ModArmorMaterials.HIDE, ArmorType.HELMET).durability(165));
    public static final DeferredItem<Item> HIDE_CHEST = ITEMS.registerSimpleItem("hide_chest",
            p -> p.humanoidArmor(ModArmorMaterials.HIDE, ArmorType.CHESTPLATE).durability(240));
    public static final DeferredItem<Item> HIDE_LEGS = ITEMS.registerSimpleItem("hide_legs",
            p -> p.humanoidArmor(ModArmorMaterials.HIDE, ArmorType.LEGGINGS).durability(225));
    public static final DeferredItem<Item> HIDE_BOOTS = ITEMS.registerSimpleItem("hide_boots",
            p -> p.humanoidArmor(ModArmorMaterials.HIDE, ArmorType.BOOTS).durability(195));
    public static final DeferredItem<Item> REPTILE_HIDE = ITEMS.registerSimpleItem("reptile_hide");
    public static final DeferredItem<Item> REPTILE_HELMET = ITEMS.registerSimpleItem("reptile_helmet",
            p -> p.humanoidArmor(ModArmorMaterials.REPTILE, ArmorType.HELMET).durability(165));
    public static final DeferredItem<Item> REPTILE_PLATE = ITEMS.registerSimpleItem("reptile_plate",
            p -> p.humanoidArmor(ModArmorMaterials.REPTILE, ArmorType.CHESTPLATE).durability(240));
    public static final DeferredItem<Item> REPTILE_LEGS = ITEMS.registerSimpleItem("reptile_legs",
            p -> p.humanoidArmor(ModArmorMaterials.REPTILE, ArmorType.LEGGINGS).durability(225));
    public static final DeferredItem<Item> REPTILE_BOOTS = ITEMS.registerSimpleItem("reptile_boots",
            p -> p.humanoidArmor(ModArmorMaterials.REPTILE, ArmorType.BOOTS).durability(195));

    // ---- Scorpion Gear (5 biome variants) ----
    public static final DeferredItem<Item> SCORP_AXE_CAVE = ITEMS.registerItem("scorp_axe_cave",
            p -> new io.github.mestretramador.mocreaturesrr.item.ScorpionAxeItem(p,
            () -> new net.minecraft.world.effect.MobEffectInstance(net.minecraft.world.effect.MobEffects.NAUSEA, 100, 0)),
            p -> p.stacksTo(1)
                .axe(ToolMaterial.DIAMOND, 5.0F, -3.0F));

    public static final DeferredItem<Item> SCORP_AXE_DIRT = ITEMS.registerItem("scorp_axe_dirt",
            p -> new io.github.mestretramador.mocreaturesrr.item.ScorpionAxeItem(p,
            () -> new net.minecraft.world.effect.MobEffectInstance(net.minecraft.world.effect.MobEffects.POISON, 100, 0)),
            p -> p.stacksTo(1)
                .axe(ToolMaterial.DIAMOND, 5.0F, -3.0F));

    public static final DeferredItem<Item> SCORP_AXE_NETHER = ITEMS.registerItem("scorp_axe_nether",
            p -> new io.github.mestretramador.mocreaturesrr.item.ScorpionAxeItem(p,
            4),
            p -> p.stacksTo(1)
                .axe(ToolMaterial.DIAMOND, 5.0F, -3.0F));

    public static final DeferredItem<Item> SCORP_AXE_FROST = ITEMS.registerItem("scorp_axe_frost",
            p -> new io.github.mestretramador.mocreaturesrr.item.ScorpionAxeItem(p,
            () -> new net.minecraft.world.effect.MobEffectInstance(net.minecraft.world.effect.MobEffects.SLOWNESS, 100, 0)),
            p -> p.stacksTo(1)
                .axe(ToolMaterial.DIAMOND, 5.0F, -3.0F));

    public static final DeferredItem<Item> SCORP_AXE_UNDEAD = ITEMS.registerItem("scorp_axe_undead",
            p -> new io.github.mestretramador.mocreaturesrr.item.ScorpionAxeItem(p,
            () -> new net.minecraft.world.effect.MobEffectInstance(net.minecraft.world.effect.MobEffects.WEAKNESS, 100, 0)),
            p -> p.stacksTo(1)
                .axe(ToolMaterial.DIAMOND, 5.0F, -3.0F));
    public static final DeferredItem<Item> SCORP_HELMET_CAVE = ITEMS.registerSimpleItem("scorp_helmet_cave",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_CAVE, ArmorType.HELMET).durability(166));
    public static final DeferredItem<Item> SCORP_PLATE_CAVE = ITEMS.registerSimpleItem("scorp_plate_cave",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_CAVE, ArmorType.CHESTPLATE).durability(241));
    public static final DeferredItem<Item> SCORP_LEGS_CAVE = ITEMS.registerSimpleItem("scorp_legs_cave",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_CAVE, ArmorType.LEGGINGS).durability(226));
    public static final DeferredItem<Item> SCORP_BOOTS_CAVE = ITEMS.registerSimpleItem("scorp_boots_cave",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_CAVE, ArmorType.BOOTS).durability(196));

    public static final DeferredItem<Item> SCORP_HELMET_DIRT = ITEMS.registerSimpleItem("scorp_helmet_dirt",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_DIRT, ArmorType.HELMET).durability(166));
    public static final DeferredItem<Item> SCORP_PLATE_DIRT = ITEMS.registerSimpleItem("scorp_plate_dirt",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_DIRT, ArmorType.CHESTPLATE).durability(241));
    public static final DeferredItem<Item> SCORP_LEGS_DIRT = ITEMS.registerSimpleItem("scorp_legs_dirt",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_DIRT, ArmorType.LEGGINGS).durability(226));
    public static final DeferredItem<Item> SCORP_BOOTS_DIRT = ITEMS.registerSimpleItem("scorp_boots_dirt",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_DIRT, ArmorType.BOOTS).durability(196));

    public static final DeferredItem<Item> SCORP_HELMET_NETHER = ITEMS.registerSimpleItem("scorp_helmet_nether",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_NETHER, ArmorType.HELMET).durability(166));
    public static final DeferredItem<Item> SCORP_PLATE_NETHER = ITEMS.registerSimpleItem("scorp_plate_nether",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_NETHER, ArmorType.CHESTPLATE).durability(241));
    public static final DeferredItem<Item> SCORP_LEGS_NETHER = ITEMS.registerSimpleItem("scorp_legs_nether",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_NETHER, ArmorType.LEGGINGS).durability(226));
    public static final DeferredItem<Item> SCORP_BOOTS_NETHER = ITEMS.registerSimpleItem("scorp_boots_nether",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_NETHER, ArmorType.BOOTS).durability(196));

    public static final DeferredItem<Item> SCORP_HELMET_FROST = ITEMS.registerSimpleItem("scorp_helmet_frost",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_FROST, ArmorType.HELMET).durability(166));
    public static final DeferredItem<Item> SCORP_PLATE_FROST = ITEMS.registerSimpleItem("scorp_plate_frost",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_FROST, ArmorType.CHESTPLATE).durability(241));
    public static final DeferredItem<Item> SCORP_LEGS_FROST = ITEMS.registerSimpleItem("scorp_legs_frost",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_FROST, ArmorType.LEGGINGS).durability(226));
    public static final DeferredItem<Item> SCORP_BOOTS_FROST = ITEMS.registerSimpleItem("scorp_boots_frost",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_FROST, ArmorType.BOOTS).durability(196));

    public static final DeferredItem<Item> SCORP_HELMET_UNDEAD = ITEMS.registerSimpleItem("scorp_helmet_undead",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_UNDEAD, ArmorType.HELMET).durability(166));
    public static final DeferredItem<Item> SCORP_PLATE_UNDEAD = ITEMS.registerSimpleItem("scorp_plate_undead",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_UNDEAD, ArmorType.CHESTPLATE).durability(241));
    public static final DeferredItem<Item> SCORP_LEGS_UNDEAD = ITEMS.registerSimpleItem("scorp_legs_undead",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_UNDEAD, ArmorType.LEGGINGS).durability(226));
    public static final DeferredItem<Item> SCORP_BOOTS_UNDEAD = ITEMS.registerSimpleItem("scorp_boots_undead",
            p -> p.humanoidArmor(ModArmorMaterials.SCORP_UNDEAD, ArmorType.BOOTS).durability(196));

    public static final DeferredItem<Item> SCORP_STING_CAVE = ITEMS.registerSimpleItem("scorp_sting_cave", p -> p.stacksTo(16));
    public static final DeferredItem<Item> SCORP_STING_DIRT = ITEMS.registerSimpleItem("scorp_sting_dirt", p -> p.stacksTo(16));
    public static final DeferredItem<Item> SCORP_STING_FROST = ITEMS.registerSimpleItem("scorp_sting_frost", p -> p.stacksTo(16));
    public static final DeferredItem<Item> SCORP_STING_NETHER = ITEMS.registerSimpleItem("scorp_sting_nether", p -> p.stacksTo(16));
    public static final DeferredItem<Item> SCORP_STING_UNDEAD = ITEMS.registerSimpleItem("scorp_sting_undead", p -> p.stacksTo(16));
    public static final DeferredItem<Item> SCORP_SWORD_CAVE = ITEMS.registerItem("scorp_sword_cave",
            p -> new ScorpionSwordItem(
            p,
            () -> new MobEffectInstance(
                MobEffects.NAUSEA,
                100,
                0
            )),
            p -> p
                .stacksTo(1)
                .sword(ToolMaterial.DIAMOND, 3, -2.4F));
    public static final DeferredItem<Item> SCORP_SWORD_DIRT = ITEMS.registerItem("scorp_sword_dirt",
            p -> new ScorpionSwordItem(
            p,
            () -> new MobEffectInstance(
                MobEffects.POISON,
                100,
                0
            )),
            p -> p
                .stacksTo(1)
                .sword(ToolMaterial.DIAMOND, 3, -2.4F));
    public static final DeferredItem<Item> SCORP_SWORD_NETHER = ITEMS.registerItem("scorp_sword_nether",
            p -> new ScorpionSwordItem(
            p,
            4),
            p -> p
                .stacksTo(1)
                .sword(ToolMaterial.DIAMOND, 3, -2.4F));
    public static final DeferredItem<Item> SCORP_SWORD_FROST = ITEMS.registerItem("scorp_sword_frost",
            p -> new ScorpionSwordItem(
            p,
            () -> new MobEffectInstance(
                MobEffects.SLOWNESS,
                100,
                0
            )),
            p -> p
                .stacksTo(1)
                .sword(ToolMaterial.DIAMOND, 3, -2.4F));
    public static final DeferredItem<Item> SCORP_SWORD_UNDEAD = ITEMS.registerItem("scorp_sword_undead",
            p -> new ScorpionSwordItem(
            p,
            () -> new MobEffectInstance(
                MobEffects.WEAKNESS,
                100,
                0
            )),
            p -> p
                .stacksTo(1)
                .sword(ToolMaterial.DIAMOND, 3, -2.4F));

    // ---- Weapons ----
    public static final DeferredItem<Item> BIG_CAT_CLAW = ITEMS.registerSimpleItem("big_cat_claw");
    public static final DeferredItem<Item> BO = ITEMS.registerSimpleItem("bo",
            p -> p
                .stacksTo(1)
                .sword(ToolMaterial.IRON, 3, -2.4F));
    public static final DeferredItem<Item> KATANA = ITEMS.registerSimpleItem("katana",
            p -> p
                .stacksTo(1)
                .sword(ToolMaterial.IRON, 3, -2.4F));
    public static final DeferredItem<Item> NUNCHAKU = ITEMS.registerSimpleItem("nunchaku",
            p -> p
                .stacksTo(1)
                .sword(ToolMaterial.IRON, 3, -2.4F));
    public static final DeferredItem<Item> SAI = ITEMS.registerSimpleItem("sai",
            p -> p
                .stacksTo(1)
                .sword(ToolMaterial.IRON, 3, -2.4F));
    public static final DeferredItem<Item> SHARK_AXE = ITEMS.registerSimpleItem("shark_axe",
            p -> p.stacksTo(1)
                .axe(ToolMaterial.IRON, 6.0F, -3.1F));
    public static final DeferredItem<Item> SHARK_SWORD = ITEMS.registerSimpleItem("shark_sword",
            p -> p
                .stacksTo(1)
                .sword(ToolMaterial.IRON, 3, -2.4F));
    public static final DeferredItem<Item> SHARK_TEETH = ITEMS.registerSimpleItem("shark_teeth");
    public static final DeferredItem<Item> SILVER_AXE = ITEMS.registerSimpleItem("silver_axe",
            p -> p.stacksTo(1)
                .axe(ToolMaterial.GOLD, 6.0F, -3.0F));
    public static final DeferredItem<Item> SILVER_SWORD = ITEMS.registerSimpleItem("silver_sword",
            p -> p
                .stacksTo(1)
                .sword(ToolMaterial.GOLD, 3, -2.4F));
    public static final DeferredItem<Item> TUSKS_DIAMOND = ITEMS.registerSimpleItem("tusks_diamond", p -> p.stacksTo(1).durability(1562));
    public static final DeferredItem<Item> TUSKS_IRON = ITEMS.registerSimpleItem("tusks_iron", p -> p.stacksTo(1).durability(251));
    public static final DeferredItem<Item> TUSKS_WOOD = ITEMS.registerSimpleItem("tusks_wood", p -> p.stacksTo(1).durability(60));

    // ---- Foods ----
    public static final DeferredItem<Item> CRAB_COOKED = ITEMS.registerSimpleItem("crab_cooked", p -> p.food(new FoodProperties.Builder()
            .nutrition(8).saturationModifier(0.6F).build()).stacksTo(64));

    public static final DeferredItem<Item> CRAB_RAW = ITEMS.registerSimpleItem("crab_raw", p -> p.food(new FoodProperties.Builder()
            .nutrition(2).saturationModifier(0.3F).build()).stacksTo(64));

    public static final DeferredItem<Item> DUCK_COOKED = ITEMS.registerSimpleItem("duck_cooked", p -> p.food(new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.6F).build()).stacksTo(64));

    public static final DeferredItem<Item> DUCK_RAW = ITEMS.registerSimpleItem("duck_raw", p -> p.food(new FoodProperties.Builder()
            .nutrition(2).saturationModifier(0.3F).build(), RAW_MEAT_HUNGER).stacksTo(64));

    public static final DeferredItem<Item> OMELET = ITEMS.registerSimpleItem("omelet", p -> p.food(new FoodProperties.Builder()
            .nutrition(4).saturationModifier(0.6F).build()).stacksTo(64));

    public static final DeferredItem<Item> OSTRICH_COOKED = ITEMS.registerSimpleItem("ostrich_cooked", p -> p.food(new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.6F).build()).stacksTo(64));

    public static final DeferredItem<Item> OSTRICH_RAW = ITEMS.registerSimpleItem("ostrich_raw", p -> p.food(new FoodProperties.Builder()
            .nutrition(2).saturationModifier(0.3F).build(), RAW_MEAT_HUNGER).stacksTo(64));

    public static final DeferredItem<Item> RAT_BURGER = ITEMS.registerSimpleItem("rat_burger", p -> p.food(new FoodProperties.Builder()
            .nutrition(8).saturationModifier(0.6F).build()).stacksTo(64));

    public static final DeferredItem<Item> RAT_COOKED = ITEMS.registerSimpleItem("rat_cooked", p -> p.food(new FoodProperties.Builder()
            .nutrition(4).saturationModifier(0.6F).build()).stacksTo(64));

    public static final DeferredItem<Item> RAT_RAW = ITEMS.registerSimpleItem("rat_raw", p -> p.food(new FoodProperties.Builder()
            .nutrition(2).saturationModifier(0.3F).build(), RAW_MEAT_HUNGER).stacksTo(64));

    public static final DeferredItem<Item> TURKEY_COOKED = ITEMS.registerSimpleItem("turkey_cooked", p -> p.food(new FoodProperties.Builder()
            .nutrition(8).saturationModifier(0.6F).build()).stacksTo(64));

    public static final DeferredItem<Item> TURKEY_RAW = ITEMS.registerSimpleItem("turkey_raw", p -> p.food(new FoodProperties.Builder()
            .nutrition(3).saturationModifier(0.3F).build(), RAW_MEAT_HUNGER).stacksTo(64));

    public static final DeferredItem<Item> TURTLE_COOKED = ITEMS.registerSimpleItem("turtle_cooked", p -> p.food(new FoodProperties.Builder()
            .nutrition(4).saturationModifier(0.6F).build()).stacksTo(64));

    public static final DeferredItem<Item> TURTLE_RAW = ITEMS.registerSimpleItem("turtle_raw", p -> p.food(new FoodProperties.Builder()
            .nutrition(2).saturationModifier(0.3F).build(), RAW_MEAT_HUNGER).stacksTo(64));

    // When eaten, returns the empty bowl — same mechanism as vanilla's mushroom stew.
    public static final DeferredItem<Item> TURTLE_SOUP = ITEMS.registerItem("turtle_soup",
            p -> new io.github.mestretramador.mocreaturesrr.item.BowlFoodItem(p),
            p -> p
        .food(new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).build())
        .stacksTo(1));

    public static final DeferredItem<Item> VENISON_COOKED = ITEMS.registerSimpleItem("venison_cooked", p -> p.food(new FoodProperties.Builder()
            .nutrition(8).saturationModifier(0.6F).build()).stacksTo(64));

    public static final DeferredItem<Item> VENISON_RAW = ITEMS.registerSimpleItem("venison_raw", p -> p.food(new FoodProperties.Builder()
            .nutrition(3).saturationModifier(0.3F).build()).stacksTo(64));


    // ---- Horse-related ----
    public static final DeferredItem<Item> HORSE_ARMOR_CRYSTAL = ITEMS.registerItem("horse_armor_crystal",
            p -> new Item(p),
            p -> p.stacksTo(1)
            .attributes(net.minecraft.world.item.component.ItemAttributeModifiers.builder()
                .add(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR,
                     new net.minecraft.world.entity.ai.attributes.AttributeModifier(
                         net.minecraft.resources.Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "horse_armor_crystal"),
                         19.0D,
                         net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_VALUE),
                     net.minecraft.world.entity.EquipmentSlotGroup.BODY)
                .build()));
    // SaddleItem no longer exists (1.21.5+): saddles are equippable into the SADDLE slot.
    public static final DeferredItem<Item> HORSE_SADDLE = ITEMS.registerSimpleItem("horse_saddle",
            p -> p.stacksTo(16)
                .component(net.minecraft.core.component.DataComponents.EQUIPPABLE,
                        net.minecraft.world.item.equipment.Equippable.saddle()));
    public static final DeferredItem<Item> UNICORN_HORN = ITEMS.registerSimpleItem("unicorn_horn");
    public static final DeferredItem<Item> WHIP = ITEMS.registerItem("whip",
            p -> new io.github.mestretramador.mocreaturesrr.item.WhipItem(p),
            p -> p.stacksTo(1));
    // ---- Elephant / Mammoth ----
    public static final DeferredItem<Item> ELEPHANT_CHEST = ITEMS.registerSimpleItem("elephant_chest");
    public static final DeferredItem<Item> ELEPHANT_GARMENT = ITEMS.registerSimpleItem("elephant_garment", p -> p.stacksTo(1));
    public static final DeferredItem<Item> ELEPHANT_HARNESS = ITEMS.registerSimpleItem("elephant_harness", p -> p.stacksTo(1));
    public static final DeferredItem<Item> ELEPHANT_HOWDAH = ITEMS.registerSimpleItem("elephant_howdah", p -> p.stacksTo(1));
    public static final DeferredItem<Item> MAMMOTH_PLATFORM = ITEMS.registerSimpleItem("mammoth_platform", p -> p.stacksTo(1));

    // ---- Misc ----
    public static final DeferredItem<Item> CHITIN = ITEMS.registerSimpleItem("chitin");
    public static final DeferredItem<Item> CHITIN_BLACK = ITEMS.registerSimpleItem("chitin_black");
    public static final DeferredItem<Item> CHITIN_FROST = ITEMS.registerSimpleItem("chitin_frost");
    public static final DeferredItem<Item> CHITIN_NETHER = ITEMS.registerSimpleItem("chitin_nether");
    public static final DeferredItem<Item> CHITIN_UNDEAD = ITEMS.registerSimpleItem("chitin_undead");
    public static final DeferredItem<Item> BUILDER_HAMMER = ITEMS.registerSimpleItem("builder_hammer", p -> p.stacksTo(1));
    public static final DeferredItem<Item> FIRESTONE_CHUNK = ITEMS.registerSimpleItem("firestone_chunk");
    public static final DeferredItem<Item> FISH_NET = ITEMS.registerItem("fish_net",
            p -> new io.github.mestretramador.mocreaturesrr.item.FishNetItem(p),
            p -> p.stacksTo(16));
    public static final DeferredItem<Item> FISH_NET_FULL = ITEMS.registerItem("fish_net_full",
            p -> new io.github.mestretramador.mocreaturesrr.item.FilledFishNetItem(p, FISH_NET.get()),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> HAYSTACK = ITEMS.registerSimpleItem("haystack", p -> p.stacksTo(16));
    public static final DeferredItem<Item> KEY = ITEMS.registerSimpleItem("key");
    public static final DeferredItem<Item> KITTY_BED = ITEMS.registerSimpleItem("kitty_bed", p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_BLACK = ITEMS.registerItem("kitty_bed_black",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.BLACK, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_BLUE = ITEMS.registerItem("kitty_bed_blue",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.BLUE, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_BROWN = ITEMS.registerItem("kitty_bed_brown",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.BROWN, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_CYAN = ITEMS.registerItem("kitty_bed_cyan",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.CYAN, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_GRAY = ITEMS.registerItem("kitty_bed_gray",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.GRAY, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_GREEN = ITEMS.registerItem("kitty_bed_green",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.GREEN, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_LIGHT_BLUE = ITEMS.registerItem("kitty_bed_light_blue",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.LIGHT_BLUE, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_LIME = ITEMS.registerItem("kitty_bed_lime",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.LIME, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_MAGENTA = ITEMS.registerItem("kitty_bed_magenta",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.MAGENTA, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_ORANGE = ITEMS.registerItem("kitty_bed_orange",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.ORANGE, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_PINK = ITEMS.registerItem("kitty_bed_pink",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.PINK, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_PURPLE = ITEMS.registerItem("kitty_bed_purple",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.PURPLE, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_RED = ITEMS.registerItem("kitty_bed_red",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.RED, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_SILVER = ITEMS.registerItem("kitty_bed_silver",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.LIGHT_GRAY, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_WHITE = ITEMS.registerItem("kitty_bed_white",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.WHITE, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_BED_YELLOW = ITEMS.registerItem("kitty_bed_yellow",
            p -> new io.github.mestretramador.mocreaturesrr.item.KittyBedItem(net.minecraft.world.item.DyeColor.YELLOW, p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> KITTY_LITTER = ITEMS.registerItem("kitty_litter",
            p -> new io.github.mestretramador.mocreaturesrr.item.LitterBoxItem(p),
            p -> p.stacksTo(1));
    public static final DeferredItem<Item> MEDALLION = ITEMS.registerSimpleItem("medallion");
    // Places a MoCEggEntity when used on a block — see MoCEggItem.
    public static final DeferredItem<Item> MOC_EGG = ITEMS.registerItem("moc_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p));

    // One egg per naturally-occurring wyvern variant (the 8 wild biome
    // textures + the plain mother) — each only ever hatches its own species.
    // The 8 wild ones have a 30% chance of hatching tier 2 instead of tier 1;
    // the mother egg always hatches the bigger, tamed-only mother form.
    public static final DeferredItem<Item> JUNGLE_WYVERN_EGG = ITEMS.registerItem("jungle_wyvern_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.WYVERN, ModEntities.WYVERN_TIER2, 0.3D, "JUNGLE")));
    public static final DeferredItem<Item> SWAMP_WYVERN_EGG = ITEMS.registerItem("swamp_wyvern_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.WYVERN, ModEntities.WYVERN_TIER2, 0.3D, "SWAMP")));
    public static final DeferredItem<Item> SAND_WYVERN_EGG = ITEMS.registerItem("sand_wyvern_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.WYVERN, ModEntities.WYVERN_TIER2, 0.3D, "SAND")));
    public static final DeferredItem<Item> SUN_WYVERN_EGG = ITEMS.registerItem("sun_wyvern_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.WYVERN, ModEntities.WYVERN_TIER2, 0.3D, "SUN")));
    public static final DeferredItem<Item> ARCTIC_WYVERN_EGG = ITEMS.registerItem("arctic_wyvern_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.WYVERN, ModEntities.WYVERN_TIER2, 0.3D, "ARCTIC")));
    public static final DeferredItem<Item> CAVE_WYVERN_EGG = ITEMS.registerItem("cave_wyvern_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.WYVERN, ModEntities.WYVERN_TIER2, 0.3D, "CAVE")));
    public static final DeferredItem<Item> MOUNTAIN_WYVERN_EGG = ITEMS.registerItem("mountain_wyvern_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.WYVERN, ModEntities.WYVERN_TIER2, 0.3D, "MOUNTAIN")));
    public static final DeferredItem<Item> SEA_WYVERN_EGG = ITEMS.registerItem("sea_wyvern_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.WYVERN, ModEntities.WYVERN_TIER2, 0.3D, "SEA")));
    public static final DeferredItem<Item> MOTHER_WYVERN_EGG = ITEMS.registerItem("mother_wyvern_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.WYVERN_MOTHER_TAMED, null, 0.0D, "MOTHER")));
    public static final DeferredItem<Item> DIRT_SCORPION_EGG = ITEMS.registerItem("dirt_scorpion_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
                    new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                            io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SCORPION, null, 0.0D, "DIRT")));
    public static final DeferredItem<Item> CAVE_SCORPION_EGG = ITEMS.registerItem("cave_scorpion_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
                    new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                            io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SCORPION, null, 0.0D, "CAVE")));
    public static final DeferredItem<Item> FROST_SCORPION_EGG = ITEMS.registerItem("frost_scorpion_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
                    new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                            io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SCORPION, null, 0.0D, "FROST")));
    public static final DeferredItem<Item> FIRE_SCORPION_EGG = ITEMS.registerItem("fire_scorpion_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
                    new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                            io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SCORPION, null, 0.0D, "NETHER")));
    public static final DeferredItem<Item> UNDEAD_SCORPION_EGG = ITEMS.registerItem("undead_scorpion_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
                    new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                            io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SCORPION, null, 0.0D, "UNDEAD")));
    public static final DeferredItem<Item> PLAIN_MANTICORE_EGG = ITEMS.registerItem("plain_manticore_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
                new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                        io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_MANTICORE, null, 0.0D, "PLAIN")));
    public static final DeferredItem<Item> DARK_MANTICORE_EGG = ITEMS.registerItem("dark_manticore_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
                    new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                            io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_MANTICORE, null, 0.0D, "DARK")));
    public static final DeferredItem<Item> FROST_MANTICORE_EGG = ITEMS.registerItem("frost_manticore_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
                    new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                            io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_MANTICORE, null, 0.0D, "FROST")));
    public static final DeferredItem<Item> FIRE_MANTICORE_EGG = ITEMS.registerItem("fire_manticore_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
                    new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                            io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_MANTICORE, null, 0.0D, "FIRE")));
    public static final DeferredItem<Item> TOXIC_MANTICORE_EGG = ITEMS.registerItem("toxic_manticore_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
                    new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                            io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_MANTICORE, null, 0.0D, "TOXIC")));
    public static final DeferredItem<Item> OSTRICH_EGG = ITEMS.registerItem("ostrich_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
                        new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_OSTRICH, null, 0.0D, null)));
    public static final DeferredItem<Item> KOMODO_DRAGON_EGG = ITEMS.registerItem("komodo_dragon_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
                        new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_KOMODO_DRAGON, null, 0.0D, null)));
    public static final DeferredItem<Item> SNAKE_EGG_GREEN_DARK = ITEMS.registerItem("snake_egg_green_dark",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.MOC_SNAKE, null, 0.0D, "GREEN_DARK")));
    public static final DeferredItem<Item> SNAKE_EGG_WOLF = ITEMS.registerItem("snake_egg_wolf",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.MOC_SNAKE, null, 0.0D, "WOLF")));
    public static final DeferredItem<Item> SNAKE_EGG_ORANGE = ITEMS.registerItem("snake_egg_orange",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.MOC_SNAKE, null, 0.0D, "ORANGE")));
    public static final DeferredItem<Item> SNAKE_EGG_GREEN_BRIGHT = ITEMS.registerItem("snake_egg_green_bright",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.MOC_SNAKE, null, 0.0D, "GREEN_BRIGHT")));
    public static final DeferredItem<Item> SNAKE_EGG_CORAL = ITEMS.registerItem("snake_egg_coral",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.MOC_SNAKE, null, 0.0D, "CORAL")));
    public static final DeferredItem<Item> SNAKE_EGG_COBRA = ITEMS.registerItem("snake_egg_cobra",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.MOC_SNAKE, null, 0.0D, "COBRA")));
    public static final DeferredItem<Item> SNAKE_EGG_RATTLE = ITEMS.registerItem("snake_egg_rattle",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.MOC_SNAKE, null, 0.0D, "RATTLE")));
    public static final DeferredItem<Item> SNAKE_EGG_PYTHON = ITEMS.registerItem("snake_egg_python",
            p -> new io.github.mestretramador.mocreaturesrr.item.MoCEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.MOC_SNAKE, null, 0.0D, "PYTHON")));
    public static final DeferredItem<Item> SHARK_EGG = ITEMS.registerItem("shark_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SharkEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                ModEntities.MOC_SHARK, null, 0.0D, null)));
    public static final DeferredItem<Item> COD_EGG = ITEMS.registerItem("cod_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MediumFishEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_COD, null, 0.0D, null)));
    public static final DeferredItem<Item> SALMON_EGG = ITEMS.registerItem("salmon_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MediumFishEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SALMON, null, 0.0D, null)));
    public static final DeferredItem<Item> BASS_EGG = ITEMS.registerItem("bass_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.MediumFishEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_BASS, null, 0.0D, null)));
    public static final DeferredItem<Item> ANCHOVY_EGG = ITEMS.registerItem("anchovy_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SMALL_FISH, null, 0.0D, "ANCHOVY")));
    public static final DeferredItem<Item> ANGELFISH_EGG = ITEMS.registerItem("angelfish_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SMALL_FISH, null, 0.0D, "ANGELFISH")));
    public static final DeferredItem<Item> ANGLERFISH_EGG = ITEMS.registerItem("anglerfish_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SMALL_FISH, null, 0.0D, "ANGLER")));
    public static final DeferredItem<Item> CLOWNFISH_EGG = ITEMS.registerItem("clownfish_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SMALL_FISH, null, 0.0D, "CLOWNFISH")));
    public static final DeferredItem<Item> GOLDFISH_EGG = ITEMS.registerItem("goldfish_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SMALL_FISH, null, 0.0D, "GOLDFISH")));
    public static final DeferredItem<Item> HIPPOTANG_EGG = ITEMS.registerItem("hippotang_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SMALL_FISH, null, 0.0D, "HIPPOTANG")));
    public static final DeferredItem<Item> MANDARINFISH_EGG = ITEMS.registerItem("mandarinfish_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SMALL_FISH, null, 0.0D, "MANDARIN")));
   public static final DeferredItem<Item> PIRANHA_EGG = ITEMS.registerItem("piranha_egg",
            p -> new io.github.mestretramador.mocreaturesrr.item.SmallFishEggItem(p,
            new io.github.mestretramador.mocreaturesrr.item.MoCEggItem.HatchSpec(
                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SMALL_FISH, null, 0.0D, "PIRANHA")));

    public static final DeferredItem<Item> MYSTIC_PEAR = ITEMS.registerSimpleItem("mystic_pear");
    public static final DeferredItem<Item> NETHER_CANNON = ITEMS.registerSimpleItem("nether_cannon");
    public static final DeferredItem<Item> PET_FOOD = ITEMS.registerSimpleItem("pet_food");
    public static final ResourceKey<JukeboxSong> SHUFFLING_SONG = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "shuffling"));
    public static final DeferredItem<Item> RECORD_SHUFFLE = ITEMS.registerSimpleItem("record_shuffle", p -> p.stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(SHUFFLING_SONG));
    public static final DeferredItem<Item> ROPE = ITEMS.registerSimpleItem("rope");
    public static final DeferredItem<Item> SCROLL_OF_FREEDOM = ITEMS.registerItem("scroll_of_freedom",
            p -> new io.github.mestretramador.mocreaturesrr.item.ScrollOfFreedomItem(p));
    public static final DeferredItem<Item> SCROLL_OF_OWNER = ITEMS.registerItem("scroll_of_owner",
            p -> new io.github.mestretramador.mocreaturesrr.item.ScrollOfResetOwnerItem(p));
    public static final DeferredItem<Item> SCROLL_OF_SALE = ITEMS.registerItem("scroll_of_sale",
            p -> new io.github.mestretramador.mocreaturesrr.item.ScrollOfSaleItem(p));
    public static final DeferredItem<Item> STAFF = ITEMS.registerSimpleItem("staff", p -> p.stacksTo(1));
    public static final DeferredItem<Item> STAFF2 = ITEMS.registerSimpleItem("staff2", p -> p.stacksTo(1));
    public static final DeferredItem<Item> STAFF3 = ITEMS.registerSimpleItem("staff3", p -> p.stacksTo(1));
    public static final DeferredItem<Item> STAFF_PORTAL = ITEMS.registerItem("staff_portal",
            p -> new io.github.mestretramador.mocreaturesrr.item.StaffPortalItem(p),
            p -> p.stacksTo(1).durability(4));
    public static final DeferredItem<Item> STAFF_TELEPORT = ITEMS.registerSimpleItem("staff_teleport", p -> p.stacksTo(1));
    public static final DeferredItem<Item> SUGAR_LUMP = ITEMS.registerSimpleItem("sugar_lump", p -> p.food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.6F).build()).stacksTo(32));
    public static final DeferredItem<Item> WOOL_BALL = ITEMS.registerSimpleItem("wool_ball");

    // ==== Wyvern block set — BlockItems ====
    public static final DeferredItem<BlockItem> WYVSTONE_ITEM = ITEMS.registerSimpleBlockItem("wyvstone", ModBlocks.WYVSTONE);
    public static final DeferredItem<BlockItem> COBBLED_WYVSTONE_ITEM = ITEMS.registerSimpleBlockItem("cobbled_wyvstone", ModBlocks.COBBLED_WYVSTONE);
    public static final DeferredItem<BlockItem> DEEP_WYVSTONE_ITEM = ITEMS.registerSimpleBlockItem("deep_wyvstone", ModBlocks.DEEP_WYVSTONE);
    public static final DeferredItem<BlockItem> COBBLED_DEEP_WYVSTONE_ITEM = ITEMS.registerSimpleBlockItem("cobbled_deep_wyvstone", ModBlocks.COBBLED_DEEP_WYVSTONE);
    public static final DeferredItem<BlockItem> MOSSY_COBBLED_WYVSTONE_ITEM = ITEMS.registerSimpleBlockItem("mossy_cobbled_wyvstone", ModBlocks.MOSSY_COBBLED_WYVSTONE);
    public static final DeferredItem<BlockItem> MOSSY_COBBLED_DEEP_WYVSTONE_ITEM = ITEMS.registerSimpleBlockItem("mossy_cobbled_deep_wyvstone", ModBlocks.MOSSY_COBBLED_DEEP_WYVSTONE);
    public static final DeferredItem<BlockItem> WYVDIRT_ITEM = ITEMS.registerSimpleBlockItem("wyvdirt", ModBlocks.WYVDIRT);
    public static final DeferredItem<BlockItem> WYVGRASS_ITEM = ITEMS.registerSimpleBlockItem("wyvgrass", ModBlocks.WYVGRASS);
    public static final DeferredItem<BlockItem> TALL_WYVGRASS_ITEM = ITEMS.registerSimpleBlockItem("tall_wyvgrass", ModBlocks.TALL_WYVGRASS,
            p -> p.compostable(ContextIntProviders.COMPOSTABLE_LOW));
    public static final DeferredItem<BlockItem> WYVWOOD_LOG_ITEM = ITEMS.registerSimpleBlockItem("wyvwood_log", ModBlocks.WYVWOOD_LOG);
    public static final DeferredItem<BlockItem> WYVWOOD_LEAVES_ITEM = ITEMS.registerSimpleBlockItem("wyvwood_leaves", ModBlocks.WYVWOOD_LEAVES,
            p -> p.compostable(ContextIntProviders.COMPOSTABLE_LOW));
    public static final DeferredItem<BlockItem> WYVWOOD_PLANKS_ITEM = ITEMS.registerSimpleBlockItem("wyvwood_planks", ModBlocks.WYVWOOD_PLANKS);
    public static final DeferredItem<BlockItem> WYVWOOD_SAPLING_ITEM = ITEMS.registerSimpleBlockItem("wyvwood_sapling", ModBlocks.WYVWOOD_SAPLING,
            p -> p.compostable(ContextIntProviders.COMPOSTABLE_LOW));
    public static final DeferredItem<BlockItem> WYVERN_DIAMOND_ORE_ITEM = ITEMS.registerSimpleBlockItem("wyvern_diamond_ore", ModBlocks.WYVERN_DIAMOND_ORE);
    public static final DeferredItem<BlockItem> WYVERN_EMERALD_ORE_ITEM = ITEMS.registerSimpleBlockItem("wyvern_emerald_ore", ModBlocks.WYVERN_EMERALD_ORE);
    public static final DeferredItem<BlockItem> WYVERN_LAPIS_ORE_ITEM = ITEMS.registerSimpleBlockItem("wyvern_lapis_ore", ModBlocks.WYVERN_LAPIS_ORE);
    public static final DeferredItem<BlockItem> WYVERN_GOLD_ORE_ITEM = ITEMS.registerSimpleBlockItem("wyvern_gold_ore", ModBlocks.WYVERN_GOLD_ORE);
    public static final DeferredItem<BlockItem> WYVERN_IRON_ORE_ITEM = ITEMS.registerSimpleBlockItem("wyvern_iron_ore", ModBlocks.WYVERN_IRON_ORE);
    public static final DeferredItem<BlockItem> WYVERN_ANCIENT_ORE_ITEM = ITEMS.registerSimpleBlockItem("wyvern_ancient_ore", ModBlocks.WYVERN_ANCIENT_ORE);
    
    // ==== Ogre Lair block set — BlockItems ====
    public static final DeferredItem<BlockItem> DIRT_OGRE_LAIR_ITEM = ITEMS.registerSimpleBlockItem("dirt_ogre_lair", ModBlocks.DIRT_OGRE_LAIR);
    public static final DeferredItem<BlockItem> GRASS_OGRE_LAIR_ITEM = ITEMS.registerSimpleBlockItem("grass_ogre_lair", ModBlocks.GRASS_OGRE_LAIR);
    public static final DeferredItem<BlockItem> TALL_GRASS_OGRE_LAIR_ITEM = ITEMS.registerSimpleBlockItem("tall_grass_ogre_lair", ModBlocks.TALL_GRASS_OGRE_LAIR,
            p -> p.compostable(ContextIntProviders.COMPOSTABLE_LOW));
    public static final DeferredItem<BlockItem> LOG_OGRE_LAIR_ITEM = ITEMS.registerSimpleBlockItem("log_ogre_lair", ModBlocks.LOG_OGRE_LAIR);
    public static final DeferredItem<BlockItem> LEAVES_OGRE_LAIR_ITEM = ITEMS.registerSimpleBlockItem("leaves_ogre_lair", ModBlocks.LEAVES_OGRE_LAIR,
            p -> p.compostable(ContextIntProviders.COMPOSTABLE_LOW));
    public static final DeferredItem<BlockItem> WOOD_PLANKS_OGRE_LAIR_ITEM = ITEMS.registerSimpleBlockItem("wood_planks_ogre_lair", ModBlocks.WOOD_PLANKS_OGRE_LAIR);

    public static final DeferredItem<BlockItem> ANCIENT_SILVER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("ancient_silver_block", ModBlocks.ANCIENT_SILVER_BLOCK);
    public static final DeferredItem<BlockItem> SILVER_SAND_ITEM = ITEMS.registerSimpleBlockItem("silver_sand", ModBlocks.SILVER_SAND);
    public static final DeferredItem<BlockItem> SILVER_SANDSTONE_ITEM = ITEMS.registerSimpleBlockItem("silver_sandstone", ModBlocks.SILVER_SANDSTONE);
    public static final DeferredItem<BlockItem> SILVER_SANDSTONE_CARVED_ITEM = ITEMS.registerSimpleBlockItem("silver_sandstone_carved", ModBlocks.SILVER_SANDSTONE_CARVED);
    public static final DeferredItem<BlockItem> SILVER_SANDSTONE_SMOOTH_ITEM = ITEMS.registerSimpleBlockItem("silver_sandstone_smooth", ModBlocks.SILVER_SANDSTONE_SMOOTH);

    // ==== Loose pieces — BlockItems ====
    public static final DeferredItem<BlockItem> ORE_FIRESTONE_ITEM = ITEMS.registerSimpleBlockItem("ore_firestone", ModBlocks.ORE_FIRESTONE);
    public static final DeferredItem<BlockItem> GLASS_GLEAMING_ITEM = ITEMS.registerSimpleBlockItem("glass_gleaming", ModBlocks.GLASS_GLEAMING);
    public static final DeferredItem<BlockItem> BLOCK_WYVERN_NEST_ITEM = ITEMS.registerSimpleBlockItem("block_wyvern_nest", ModBlocks.BLOCK_WYVERN_NEST);
    }