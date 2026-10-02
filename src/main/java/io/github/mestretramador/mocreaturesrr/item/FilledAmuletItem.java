package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.FairyColor;
import io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species;
import io.github.mestretramador.mocreaturesrr.entity.MoCHorseEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCScorpionEntity;
import io.github.mestretramador.mocreaturesrr.entity.scorpion.ScorpionVariant;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;
import io.github.mestretramador.mocreaturesrr.init.ModParticles;
import io.github.mestretramador.mocreaturesrr.init.ModSounds;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

public class FilledAmuletItem extends Item {

    private final boolean fairyVariant;
    private final Item emptyVariant;

    public FilledAmuletItem(Properties properties, boolean fairyVariant, Item emptyVariant) {
        super(properties);
        this.fairyVariant = fairyVariant;
        this.emptyVariant = emptyVariant;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, net.minecraft.world.item.component.TooltipDisplay tooltipDisplay,
            java.util.function.Consumer<net.minecraft.network.chat.Component> tooltip, net.minecraft.world.item.TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltip, flag);
        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = data.copyTag();

        if (tag.contains("Species")) {
            String display = io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.displayName(
                    io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species.valueOf(tag.getStringOr("Species", "")));
            tooltip.accept(net.minecraft.network.chat.Component.literal(display).withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("WyvernVariant")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.WYVERN.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("ElephantVariant")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_ELEPHANT.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("BigCatVariant")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_BIG_CAT.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("ManticoreVariant")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_MANTICORE.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("ScorpionVariant")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SCORPION.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("OstrichVariant")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_OSTRICH.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("BearVariant")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_BEAR.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("KomodoDragon")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_KOMODO_DRAGON.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("Fox")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_FOX.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("Raccoon")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_RACCOON.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("Turkey")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_TURKEY.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("Goat")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_GOAT.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("Kitty")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_KITTY.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("Snake")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_SNAKE.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("Bunny")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_BUNNY.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("Bird")) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_BIRD.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains(io.github.mestretramador.mocreaturesrr.entity.MoCTurtleEntity.AMULET_KEY)) {
            tooltip.accept(io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_TURTLE.get().getDescription()
                    .copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        } else if (tag.contains("EntityType")) {
            Identifier typeId = Identifier.parse(tag.getStringOr("EntityType", ""));
            net.minecraft.world.entity.EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.getValue(typeId);
            tooltip.accept(type.getDescription().copy().withStyle(net.minecraft.ChatFormatting.GRAY));
        }

        String name = tag.getStringOr("Name", "");
        if (!name.isEmpty()) {
            tooltip.accept(net.minecraft.network.chat.Component.literal(name)
                    .withStyle(net.minecraft.ChatFormatting.GRAY, net.minecraft.ChatFormatting.ITALIC));
        }
    }

    @Override
    public net.minecraft.world.InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide()) {
            return net.minecraft.world.InteractionResult.SUCCESS;
        }

        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = data.copyTag();
        if (tag.isEmpty()) {
            return net.minecraft.world.InteractionResult.FAIL;
        }
        BlockPos pos = player.blockPosition().relative(player.getDirection());
        Entity spawned;
        if (tag.contains("Species")) {
            spawned = spawnHorse(level, tag, pos);
        } else if (tag.contains("WyvernVariant")) {
            spawned = spawnWyvern(level, tag, pos);
        } else if (tag.contains("ElephantVariant")) {
            spawned = spawnElephant(level, tag, pos);
        } else if (tag.contains("BigCatVariant")) {
            spawned = spawnBigCat(level, tag, pos);
        } else if (tag.contains("ManticoreVariant")) {
            spawned = spawnManticore(level, tag, pos);
        } else if (tag.contains("ScorpionVariant")) {
            spawned = spawnScorpion(level, tag, pos);
        } else if (tag.contains("OstrichVariant")) {
            spawned = spawnOstrich(level, tag, pos);
        } else if (tag.contains("BearVariant")) {
            spawned = spawnBear(level, tag, pos);
        } else if (tag.contains("KomodoDragon")) {
            spawned = spawnKomodo(level, tag, pos);
        } else if (tag.contains("Fox")) {
            spawned = spawnFox(level, tag, pos);
        } else if (tag.contains("Raccoon")) {
            spawned = spawnRaccoon(level, tag, pos);
        } else if (tag.contains("Turkey")) {
            spawned = spawnTurkey(level, tag, pos);
        } else if (tag.contains("Goat")) {
            spawned = spawnGoat(level, tag, pos);
        } else if (tag.contains("Kitty")) {
            spawned = spawnKitty(level, tag, pos);
        } else if (tag.contains("Snake")) {
            spawned = spawnSnake(level, tag, pos);
        } else if (tag.contains("Bunny")) {
            spawned = spawnBunny(level, tag, pos);
        } else if (tag.contains("Bird")) {
            spawned = spawnBird(level, tag, pos);
        } else if (tag.contains(io.github.mestretramador.mocreaturesrr.entity.MoCTurtleEntity.AMULET_KEY)) {
            spawned = spawnTurtle(level, tag, pos);
        } else {
            spawned = spawnGeneric(level, tag, pos);
        }
        if (spawned == null) {
            return net.minecraft.world.InteractionResult.FAIL;
        }
        ((ServerLevel) level).sendParticles(ModParticles.VANISH_FX.get(),
                spawned.getX(), spawned.getY() + 0.5D, spawned.getZ(), 111, 0.4, 0.4, 0.4, 0.02);
        level.playSound(null, spawned.blockPosition(),
                fairyVariant ? ModSounds.AMULET_APPEAR_MAGIC.get() : ModSounds.AMULET_APPEAR.get(),
                SoundSource.NEUTRAL, 1.0F, 1.0F);

        ItemStack empty = new ItemStack(this.emptyVariant);
        player.setItemInHand(hand, empty);
        return net.minecraft.world.InteractionResult.CONSUME.heldItemTransformedTo(empty);
    }

    private Entity spawnHorse(Level level, CompoundTag tag, BlockPos pos) {
        MoCHorseEntity horse = ModEntities.MOC_HORSE.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (horse == null) return null;
        horse.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        horse.setSpecies(Species.valueOf(tag.getStringOr("Species", "")));
        horse.setTamed(true);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            horse.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        horse.setHealth((float) tag.getFloatOr("Health", 0.0F));
                if (tag.contains("Age")) {
            horse.setAge(tag.getIntOr("Age", 0));
        } else {
            horse.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.contains("Coat")) {
            horse.setCoat(io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Coat.valueOf(tag.getStringOr("Coat", "")));
        }
        if (horse.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH) != null && tag.contains("MaxHealth")) {
            horse.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(tag.getDoubleOr("MaxHealth", 0.0D));
        }
        if (horse.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED) != null && tag.contains("MovementSpeed")) {
            horse.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(tag.getDoubleOr("MovementSpeed", 0.0D));
        }
        if (horse.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH) != null && tag.contains("JumpStrength")) {
            horse.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH).setBaseValue(tag.getDoubleOr("JumpStrength", 0.0D));
        }
        if (tag.contains("UndeadStage")) {
            horse.setUndeadStagePublic(tag.getIntOr("UndeadStage", 0));
            horse.setUndeadLockedPublic(tag.getBooleanOr("UndeadLocked", false));
            horse.setUndeadDecayTicksPublic(tag.getIntOr("UndeadDecayTicks", 0));
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            horse.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        if (tag.contains("FairyColor")) {
            horse.setFairyColor(FairyColor.valueOf(tag.getStringOr("FairyColor", "")));
            horse.setFairyColorLocked(true);
        }
        if (tag.contains("SaddleItem")) {
            Item saddleItem = BuiltInRegistries.ITEM.getValue(Identifier.parse(tag.getStringOr("SaddleItem", "")));
            horse.setSaddle(new ItemStack(saddleItem));
        }
        if (tag.contains("ArmorItem")) {
            Item armorItem = BuiltInRegistries.ITEM.getValue(Identifier.parse(tag.getStringOr("ArmorItem", "")));
            horse.setItemSlot(net.minecraft.world.entity.EquipmentSlot.BODY, new ItemStack(armorItem));
        }
        if (tag.getBooleanOr("HasChest", false)) {
            horse.setHasChestPublic(true);
            if (tag.contains("ChestItems")) {
                net.minecraft.nbt.ListTag items = tag.getListOrEmpty("ChestItems");
                var ops = level.registryAccess().createSerializationContext(net.minecraft.nbt.NbtOps.INSTANCE);
                for (int i = 0; i < items.size(); i++) {
                    CompoundTag itemTag = items.getCompoundOrEmpty(i);
                    int slot = itemTag.getIntOr("Slot", 0);
                    ItemStack.CODEC.parse(ops, itemTag).result().ifPresent(is -> horse.setChestSlotPublic(slot, is));
                }
            }
        }
        level.addFreshEntity(horse);
        return horse;
    }

    private Entity spawnGeneric(Level level, CompoundTag tag, BlockPos pos) {
        Identifier typeId = Identifier.parse(tag.getStringOr("EntityType", ""));
        EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.getValue(typeId);
        Entity entity = type.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (entity == null) return null;
        entity.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        if (entity instanceof net.minecraft.world.entity.LivingEntity living) {
            living.setHealth((float) tag.getFloatOr("Health", 0.0F));
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            entity.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        if (entity instanceof net.minecraft.world.entity.TamableAnimal tamable && tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            tamable.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
            tamable.setTame(true, false);
        }
        level.addFreshEntity(entity);
        return entity;
    }

    private Entity spawnWyvern(Level level, CompoundTag tag, BlockPos pos) {
        var tier = io.github.mestretramador.mocreaturesrr.entity.wyvern.WyvernTier.valueOf(tag.getStringOr("WyvernTier", ""));
        EntityType<io.github.mestretramador.mocreaturesrr.entity.MoCWyvernEntity> type = switch (tier) {
            case MOTHER_TAMED -> ModEntities.WYVERN_MOTHER_TAMED.get();
            case TIER_2 -> ModEntities.WYVERN_TIER2.get();
            default -> ModEntities.WYVERN.get();
        };
        io.github.mestretramador.mocreaturesrr.entity.MoCWyvernEntity wyvern = type.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (wyvern == null) return null;
        wyvern.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        wyvern.setVariant(io.github.mestretramador.mocreaturesrr.entity.wyvern.WyvernVariant.valueOf(tag.getStringOr("WyvernVariant", "")));
        wyvern.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            wyvern.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        wyvern.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            wyvern.setAge(tag.getIntOr("Age", 0));
        } else {
            wyvern.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            wyvern.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(wyvern);
        return wyvern;
    }

    private Entity spawnElephant(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCElephantEntity elephant =
                ModEntities.MOC_ELEPHANT.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (elephant == null) return null;
        elephant.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        elephant.setVariant(io.github.mestretramador.mocreaturesrr.entity.elephant.ElephantVariant.valueOf(tag.getStringOr("ElephantVariant", "")));
        elephant.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            elephant.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        elephant.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            elephant.setAge(tag.getIntOr("Age", 0));
        } else {
            elephant.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            elephant.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(elephant);
        return elephant;
    }

    private Entity spawnBigCat(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCBigCatEntity bigCat = ModEntities.MOC_BIG_CAT.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (bigCat == null) return null;
        bigCat.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        bigCat.setVariant(io.github.mestretramador.mocreaturesrr.entity.bigcat.BigCatVariant.valueOf(tag.getStringOr("BigCatVariant", "")));
        bigCat.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            bigCat.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        bigCat.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            bigCat.setAge(tag.getIntOr("Age", 0));
        } else {
            bigCat.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.getBooleanOr("Wings", false)) {
            bigCat.setWings(true);
        }
        if (tag.getBooleanOr("Medallion", false)) {
            bigCat.setMedallion(true);
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            bigCat.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(bigCat);
        return bigCat;
    }

    private Entity spawnManticore(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCManticoreEntity manticore = ModEntities.MOC_MANTICORE.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (manticore == null) return null;
        manticore.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        manticore.setVariant(io.github.mestretramador.mocreaturesrr.entity.manticore.ManticoreVariant.valueOf(tag.getStringOr("ManticoreVariant", "")));
        manticore.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            manticore.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        manticore.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            manticore.setAge(tag.getIntOr("Age", 0));
        } else {
            manticore.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            manticore.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(manticore);
        return manticore;
    }

    private Entity spawnScorpion(Level level, CompoundTag tag, BlockPos pos) {
        MoCScorpionEntity scorpion = ModEntities.MOC_SCORPION.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (scorpion == null) return null;
        scorpion.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        scorpion.setVariant(ScorpionVariant.valueOf(tag.getStringOr("ScorpionVariant", "")));
        scorpion.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            scorpion.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        scorpion.setBaby(!tag.getBooleanOr("Adult", false));
        scorpion.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            scorpion.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(scorpion);
        return scorpion;
    }

    private Entity spawnOstrich(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCOstrichEntity ostrich = ModEntities.MOC_OSTRICH.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (ostrich == null) return null;
        ostrich.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        ostrich.setVariant(io.github.mestretramador.mocreaturesrr.entity.ostrich.OstrichVariant.valueOf(tag.getStringOr("OstrichVariant", "")));
        if (tag.contains("OstrichEssence")) {
            ostrich.setEssence(tag.getIntOr("OstrichEssence", 0));
        }
        ostrich.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            ostrich.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        ostrich.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            ostrich.setAge(tag.getIntOr("Age", 0));
        } else {
            ostrich.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            ostrich.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(ostrich);
        return ostrich;
    }

    private Entity spawnBear(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCBearEntity bear =
                ModEntities.MOC_BEAR.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (bear == null) return null;
        bear.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        bear.setVariant(io.github.mestretramador.mocreaturesrr.entity.bear.BearVariant.byId(tag.getIntOr("BearVariant", 0)));
        bear.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            bear.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        bear.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            bear.setAge(tag.getIntOr("Age", 0));
        } else {
            bear.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            bear.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(bear);
        return bear;
    }

    private Entity spawnKomodo(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCKomodoDragonEntity komodo =
                ModEntities.MOC_KOMODO_DRAGON.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (komodo == null) return null;
        komodo.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        if (tag.contains("IndividualAdultScale")) {
            komodo.setIndividualAdultScale(tag.getFloatOr("IndividualAdultScale", 0.0F));
        }
        komodo.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            komodo.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        komodo.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            komodo.setAge(tag.getIntOr("Age", 0));
        } else {
            komodo.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            komodo.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(komodo);
        return komodo;
    }

    private Entity spawnFox(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCFoxEntity fox =
                ModEntities.MOC_FOX.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (fox == null) return null;
        fox.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        fox.setSnow(tag.getBooleanOr("Snow", false));
        fox.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            fox.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        fox.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            fox.setAge(tag.getIntOr("Age", 0));
        } else {
            fox.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            fox.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(fox);
        return fox;
    }

    private Entity spawnRaccoon(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCRaccoonEntity raccoon =
                ModEntities.MOC_RACCOON.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (raccoon == null) return null;
        raccoon.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        raccoon.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            raccoon.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        raccoon.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            raccoon.setAge(tag.getIntOr("Age", 0));
        } else {
            raccoon.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            raccoon.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(raccoon);
        return raccoon;
    }

    private Entity spawnTurkey(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCTurkeyEntity turkey =
                ModEntities.MOC_TURKEY.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (turkey == null) return null;
        turkey.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        turkey.setMale(tag.getBooleanOr("Male", false));
        turkey.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            turkey.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        turkey.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            turkey.setAge(tag.getIntOr("Age", 0));
        } else {
            turkey.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            turkey.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(turkey);
        return turkey;
    }

    private Entity spawnGoat(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCGoatEntity goat =
                ModEntities.MOC_GOAT.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (goat == null) return null;
        goat.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        goat.setMale(tag.getBooleanOr("Male", false));
        goat.setColorIndex(tag.getIntOr("Color", 0));
        goat.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            goat.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        goat.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            goat.setAge(tag.getIntOr("Age", 0));
        } else {
            goat.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            goat.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(goat);
        return goat;
    }

    private Entity spawnKitty(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCKittyEntity kitty =
                io.github.mestretramador.mocreaturesrr.init.ModEntities.MOC_KITTY.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (kitty == null) return null;
        kitty.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        kitty.setVariant(io.github.mestretramador.mocreaturesrr.entity.kitty.KittyVariant.byId(tag.getIntOr("KittyVariant", 0)));
        kitty.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            kitty.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        kitty.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            kitty.setAge(tag.getIntOr("Age", 0));
        } else {
            kitty.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            kitty.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(kitty);
        return kitty;
    }

    private Entity spawnSnake(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCSnakeEntity snake =
                ModEntities.MOC_SNAKE.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (snake == null) return null;
        snake.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        snake.setVariant(io.github.mestretramador.mocreaturesrr.entity.snake.SnakeVariant.byId(tag.getIntOr("SnakeVariant", 0)));
        snake.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            snake.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        snake.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            snake.setAge(tag.getIntOr("Age", 0));
        } else {
            snake.setAge(tag.getBooleanOr("Adult", false) ? 0 : -24000);
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            snake.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(snake);
        return snake;
    }

        private Entity spawnBunny(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCBunnyEntity bunny =
                ModEntities.MOC_BUNNY.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (bunny == null) return null;
        bunny.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        try {
            bunny.setVariant(io.github.mestretramador.mocreaturesrr.entity.bunny.BunnyVariant.valueOf(tag.getStringOr("BunnyVariant", "")));
        } catch (IllegalArgumentException ignored) {
        }
        bunny.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            bunny.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        bunny.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Age")) {
            bunny.setAge(tag.getIntOr("Age", 0));
        } else {
            bunny.setBaby(!tag.getBooleanOr("Adult", false));
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            bunny.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(bunny);
        return bunny;
    }

        private Entity spawnBird(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCBirdEntity bird =
                ModEntities.MOC_BIRD.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (bird == null) return null;
        bird.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        try {
            bird.setVariant(io.github.mestretramador.mocreaturesrr.entity.bird.BirdVariant.valueOf(tag.getStringOr("BirdVariant", "")));
        } catch (IllegalArgumentException ignored) {
        }
        bird.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            bird.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        bird.setHealth((float) tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            bird.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(bird);
        return bird;
    }

    private Entity spawnTurtle(Level level, CompoundTag tag, BlockPos pos) {
        io.github.mestretramador.mocreaturesrr.entity.MoCTurtleEntity turtle =
                ModEntities.MOC_TURTLE.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (turtle == null) return null;
        turtle.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        turtle.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            turtle.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        turtle.setHealth(tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Scale")) {
            turtle.setGrowthScale(tag.getFloatOr("Scale", 0.0F));
        }
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            turtle.setCustomName(net.minecraft.network.chat.Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(turtle);
        return turtle;
    }
}