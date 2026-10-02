package io.github.mestretramador.mocreaturesrr.init;

import java.util.Map;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

/**
 * Armor materials. Since 1.21.2 these are plain {@link ArmorMaterial} records (no registry):
 * use them with {@code Item.Properties#humanoidArmor(material, ArmorType.X)}.
 * The worn texture is resolved from the equipment asset id:
 * assets/mocreaturesrr/equipment/&lt;name&gt;.json, which points at
 * textures/entity/equipment/humanoid/&lt;name&gt;.png (old layer_1) and
 * textures/entity/equipment/humanoid_leggings/&lt;name&gt;.png (old layer_2).
 */
public class ModArmorMaterials {

    private static final Map<ArmorType, Integer> DIAMOND_DEFENSE = Map.of(
            ArmorType.BOOTS, 3,
            ArmorType.LEGGINGS, 6,
            ArmorType.CHESTPLATE, 8,
            ArmorType.HELMET, 3
    );

    private static final Map<ArmorType, Integer> GOLD_DEFENSE = Map.of(
            ArmorType.BOOTS, 2,
            ArmorType.LEGGINGS, 3,
            ArmorType.CHESTPLATE, 5,
            ArmorType.HELMET, 2
    );

    private static final Map<ArmorType, Integer> IRON_DEFENSE = Map.of(
            ArmorType.BOOTS, 2,
            ArmorType.LEGGINGS, 5,
            ArmorType.CHESTPLATE, 6,
            ArmorType.HELMET, 2
    );

    private static ResourceKey<EquipmentAsset> asset(String name) {
        return ResourceKey.create(
                EquipmentAssets.ROOT_ID,
                Identifier.fromNamespaceAndPath(
                        io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone.MODID,
                        name
                )
        );
    }

    private static ArmorMaterial register(
            String name,
            int durabilityMultiplier,
            Map<ArmorType, Integer> defense,
            int enchantmentValue,
            float toughness,
            float knockbackResistance,
            TagKey<Item> repairIngredient,
            String textureLayerName) {

        return new ArmorMaterial(
                durabilityMultiplier,
                defense,
                enchantmentValue,
                SoundEvents.ARMOR_EQUIP_IRON,
                toughness,
                knockbackResistance,
                repairIngredient,
                asset(textureLayerName)
        );
    }

    // ==== Scorpion (5 biomas) ====
    // Stats de diamante, enchantability 10

    public static final ArmorMaterial SCORP_CAVE =
            register(
                    "scorpc",
                    15,
                    DIAMOND_DEFENSE,
                    10,
                    2.0F,
                    0.0F,
                    ModTags.REPAIRS_SCORP_CAVE,
                    "scorpc"
            );

    public static final ArmorMaterial SCORP_DIRT =
            register(
                    "scorpd",
                    15,
                    DIAMOND_DEFENSE,
                    10,
                    2.0F,
                    0.0F,
                    ModTags.REPAIRS_SCORP_DIRT,
                    "scorpd"
            );

    public static final ArmorMaterial SCORP_NETHER =
            register(
                    "scorpn",
                    15,
                    DIAMOND_DEFENSE,
                    10,
                    2.0F,
                    0.0F,
                    ModTags.REPAIRS_SCORP_NETHER,
                    "scorpn"
            );

    public static final ArmorMaterial SCORP_FROST =
            register(
                    "scorpf",
                    15,
                    DIAMOND_DEFENSE,
                    10,
                    2.0F,
                    0.0F,
                    ModTags.REPAIRS_SCORP_FROST,
                    "scorpf"
            );

    public static final ArmorMaterial SCORP_UNDEAD =
            register(
                    "scorpu",
                    15,
                    DIAMOND_DEFENSE,
                    10,
                    2.0F,
                    0.0F,
                    ModTags.REPAIRS_SCORP_UNDEAD,
                    "scorpu"
            );

    // ==== Silver (Ancient Silver) ====
    // Clon de oro:
    // defensa de oro, enchantability 25, toughness 0

    public static final ArmorMaterial SILVER =
            register(
                    "silver",
                    7,
                    GOLD_DEFENSE,
                    25,
                    0.0F,
                    0.0F,
                    ModTags.REPAIRS_SILVER,
                    "silver"
            );

    // ==== Fur ====
    // Clon de hierro

    public static final ArmorMaterial FUR =
            register(
                    "fur",
                    15,
                    IRON_DEFENSE,
                    9,
                    0.0F,
                    0.0F,
                    ModTags.REPAIRS_FUR,
                    "fur"
            );

    // ==== Reptile ====
    // Clon de hierro

    public static final ArmorMaterial REPTILE =
            register(
                    "croc",
                    15,
                    IRON_DEFENSE,
                    9,
                    0.0F,
                    0.0F,
                    ModTags.REPAIRS_REPTILE,
                    "croc"
            );

    // ==== Hide ====
    // Clon de hierro

    public static final ArmorMaterial HIDE =
            register(
                    "hide",
                    15,
                    IRON_DEFENSE,
                    9,
                    0.0F,
                    0.0F,
                    ModTags.REPAIRS_HIDE,
                    "hide"
            );
}
