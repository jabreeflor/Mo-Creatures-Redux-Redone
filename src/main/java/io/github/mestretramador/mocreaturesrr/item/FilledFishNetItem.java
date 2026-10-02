package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCSharkEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCStingrayEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCDolphinEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCMantaRayEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCFishyEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCMediumFishEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCSmallFishEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCJellyfishEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCCrabEntity;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;
import io.github.mestretramador.mocreaturesrr.util.NamingHelper;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.function.Consumer;

/** A fish net holding a caught tamed aquatic pet — same "use anywhere in front of you" pattern as FilledAmuletItem. Extend the if-chain in use() for future sea creatures beyond Shark. */
public class FilledFishNetItem extends Item {

    private final Item emptyNet;

    public FilledFishNetItem(Properties properties, Item emptyNet) {
        super(properties);
        this.emptyNet = emptyNet;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        if (tag.contains("Shark")) {
            tooltip.accept(ModEntities.MOC_SHARK.get().getDescription().copy().withStyle(ChatFormatting.GRAY));
        } if (tag.contains(MoCStingrayEntity.NET_KEY)) {
            tooltip.accept(ModEntities.MOC_STINGRAY.get().getDescription().copy().withStyle(ChatFormatting.GRAY));
        } if (tag.contains(MoCDolphinEntity.NET_KEY)) {
            tooltip.accept(ModEntities.MOC_DOLPHIN.get().getDescription().copy().withStyle(ChatFormatting.GRAY));
        } if (tag.contains(MoCMantaRayEntity.NET_KEY)) {
            tooltip.accept(ModEntities.MOC_MANTA_RAY.get().getDescription().copy().withStyle(ChatFormatting.GRAY));
        } if (tag.contains(MoCFishyEntity.NET_KEY)) {
            tooltip.accept(ModEntities.MOC_FISHY.get().getDescription().copy().withStyle(ChatFormatting.GRAY));
        } if (tag.contains(MoCMediumFishEntity.NET_KEY)) {
            tooltip.accept(net.minecraft.network.chat.Component.translatable("entity.mocreaturesrr.moc_cod")
                    .withStyle(ChatFormatting.GRAY));
        } if (tag.contains(MoCSmallFishEntity.NET_KEY)) {
            tooltip.accept(net.minecraft.network.chat.Component.translatable("entity.mocreaturesrr.moc_small_fish")
                    .withStyle(ChatFormatting.GRAY));
        } if (tag.contains(MoCJellyfishEntity.NET_KEY)) {
            tooltip.accept(net.minecraft.network.chat.Component.translatable("entity.mocreaturesrr.moc_jellyfish")
                    .withStyle(ChatFormatting.GRAY));
        } if (tag.contains(MoCCrabEntity.NET_KEY)) {
            tooltip.accept(net.minecraft.network.chat.Component.translatable("entity.mocreaturesrr.moc_crab")
                    .withStyle(ChatFormatting.GRAY));
        } if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            tooltip.accept(Component.literal(tag.getStringOr("Name", ""))
                    .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        if (tag.isEmpty()) {
            return InteractionResult.FAIL;
        }

        // Same fluid-only ray trace vanilla buckets use, so releasing works while looking at water
        // from the shore or the surface, not only when standing inside the water block itself.
        BlockHitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        BlockPos pos = hit.getType() == HitResult.Type.BLOCK
                ? hit.getBlockPos()
                : player.blockPosition().relative(player.getDirection());
        Entity spawned = null;
        if (tag.contains("Shark")) {
            // Wiki: shark eggs only hatch in water — the same restriction
            // makes sense for releasing an adult one back out.
            if (level.getFluidState(pos).is(net.minecraft.tags.FluidTags.WATER)) {
                spawned = spawnShark((ServerLevel) level, tag, pos);
            }
        } if (tag.contains(MoCStingrayEntity.NET_KEY)) {
            if (level.getFluidState(pos).is(net.minecraft.tags.FluidTags.WATER)) {
                spawned = spawnStingray((ServerLevel) level, tag, pos);
                promptNamingIfUnnamed(spawned, tag, player);
            }
        } if (tag.contains(MoCDolphinEntity.NET_KEY)) {
            if (level.getFluidState(pos).is(net.minecraft.tags.FluidTags.WATER)) {
                spawned = spawnDolphin((ServerLevel) level, tag, pos);
                promptNamingIfUnnamed(spawned, tag, player);
            }
        } if (tag.contains(MoCMantaRayEntity.NET_KEY)) {
            if (level.getFluidState(pos).is(net.minecraft.tags.FluidTags.WATER)) {
                spawned = spawnMantaRay((ServerLevel) level, tag, pos);
                promptNamingIfUnnamed(spawned, tag, player);
            }
        } if (tag.contains(MoCFishyEntity.NET_KEY)) {
            if (level.getFluidState(pos).is(net.minecraft.tags.FluidTags.WATER)) {
                spawned = spawnFishy((ServerLevel) level, tag, pos);
                promptNamingIfUnnamed(spawned, tag, player);
            }
        } if (tag.contains(MoCMediumFishEntity.NET_KEY)) {
            if (level.getFluidState(pos).is(net.minecraft.tags.FluidTags.WATER)) {
                spawned = spawnMediumFish((ServerLevel) level, tag, pos);
                promptNamingIfUnnamed(spawned, tag, player);
            }
        } if (tag.contains(MoCSmallFishEntity.NET_KEY)) {
            if (level.getFluidState(pos).is(net.minecraft.tags.FluidTags.WATER)) {
                spawned = spawnSmallFish((ServerLevel) level, tag, pos);
                promptNamingIfUnnamed(spawned, tag, player);
            }
        } if (tag.contains(MoCJellyfishEntity.NET_KEY)) {
            if (level.getFluidState(pos).is(net.minecraft.tags.FluidTags.WATER)) {
                spawned = spawnJellyfish((ServerLevel) level, tag, pos);
                promptNamingIfUnnamed(spawned, tag, player);
            }
        } if (tag.contains(MoCCrabEntity.NET_KEY)) {
            spawned = spawnCrab((ServerLevel) level, tag, pos);
            promptNamingIfUnnamed(spawned, tag, player);
        } if (spawned == null) {
            return InteractionResult.FAIL;
        }

        ItemStack empty = new ItemStack(this.emptyNet);
        player.setItemInHand(hand, empty);
        return InteractionResult.CONSUME.heldItemTransformedTo(empty);
    }

    private Entity spawnShark(ServerLevel level, CompoundTag tag, BlockPos pos) {
        MoCSharkEntity shark = ModEntities.MOC_SHARK.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (shark == null) return null;
        shark.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        shark.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            shark.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        shark.setHealth((float) tag.getFloatOr("Health", 0.0F));
        shark.setBaby(!tag.getBooleanOr("Adult", false));
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            shark.setCustomName(Component.literal(tag.getStringOr("Name", "")));
        }
        level.addFreshEntity(shark);
        return shark;
    }

    private Entity spawnStingray(ServerLevel level, CompoundTag tag, BlockPos pos) {
        MoCStingrayEntity ray = ModEntities.MOC_STINGRAY.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (ray == null) return null;
        ray.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        ray.setTame(true, false);
        if (tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent()) {
            ray.setOwnerReference(net.minecraft.world.entity.EntityReference.of(tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get()));
        }
        ray.setHealth(tag.getFloatOr("Health", 0.0F));
        if (tag.contains("Name") && !tag.getStringOr("Name", "").isEmpty()) {
            ray.setCustomName(Component.literal(tag.getStringOr("Name", "")));
            ray.setCustomNameVisible(true);
        }
        level.addFreshEntity(ray);
        return ray;
    }

    private Entity spawnDolphin(ServerLevel level, CompoundTag tag, BlockPos pos) {
        MoCDolphinEntity dolphin = ModEntities.MOC_DOLPHIN.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (dolphin == null) return null;
        dolphin.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        dolphin.restoreFromNet(tag);
        level.addFreshEntity(dolphin);
        return dolphin;
    }

    private Entity spawnMantaRay(ServerLevel level, CompoundTag tag, BlockPos pos) {
        MoCMantaRayEntity ray = ModEntities.MOC_MANTA_RAY.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (ray == null) return null;
        ray.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        ray.restoreFromNet(tag);
        level.addFreshEntity(ray);
        return ray;
    }

    private Entity spawnFishy(ServerLevel level, CompoundTag tag, BlockPos pos) {
        MoCFishyEntity fishy = ModEntities.MOC_FISHY.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (fishy == null) return null;
        fishy.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        fishy.restoreFromNet(tag);
        level.addFreshEntity(fishy);
        return fishy;
    }

    /** One release path for all 3 species: the tag itself says which EntityType to spawn. */
    private Entity spawnMediumFish(ServerLevel level, CompoundTag tag, BlockPos pos) {
        net.minecraft.resources.Identifier id = net.minecraft.resources.Identifier.parse(tag.getStringOr("EntityId", ""));
        net.minecraft.world.entity.EntityType<?> type = net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getValue(id);
        net.minecraft.world.entity.Entity entity = type.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (!(entity instanceof MoCMediumFishEntity fish)) return null;
        fish.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        fish.restoreFromNet(tag);
        level.addFreshEntity(fish);
        return fish;
    }

    private Entity spawnSmallFish(ServerLevel level, CompoundTag tag, BlockPos pos) {
        MoCSmallFishEntity fish = ModEntities.MOC_SMALL_FISH.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (fish == null) return null;
        fish.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        fish.restoreFromNet(tag);
        level.addFreshEntity(fish);
        return fish;
    }

    private Entity spawnJellyfish(ServerLevel level, CompoundTag tag, BlockPos pos) {
        MoCJellyfishEntity jellyfish = ModEntities.MOC_JELLYFISH.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (jellyfish == null) return null;
        jellyfish.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        jellyfish.restoreFromNet(tag);
        level.addFreshEntity(jellyfish);
        return jellyfish;
    }

    private Entity spawnCrab(ServerLevel level, CompoundTag tag, BlockPos pos) {
        MoCCrabEntity crab = ModEntities.MOC_CRAB.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (crab == null) return null;
        crab.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0F, 0F);
        crab.restoreFromNet(tag);
        level.addFreshEntity(crab);
        return crab;
    }

    /** A freshly released pet without a name asks its owner to name it (original: tameWithName on release). */
    private void promptNamingIfUnnamed(Entity pet, CompoundTag tag, Player releaser) {
        boolean unnamed = !tag.contains("Name") || tag.getStringOr("Name", "").isEmpty();
        boolean releaserIsOwner = !tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).isPresent() || tag.read("OwnerUUID", net.minecraft.core.UUIDUtil.CODEC).get().equals(releaser.getUUID());
        if (pet != null && unnamed && releaserIsOwner) {
            NamingHelper.promptRename(pet, releaser.getUUID());
        }
    }
}