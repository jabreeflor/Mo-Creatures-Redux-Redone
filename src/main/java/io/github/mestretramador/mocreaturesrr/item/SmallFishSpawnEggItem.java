package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCSmallFishEntity;
import io.github.mestretramador.mocreaturesrr.entity.smallfish.SmallFishVariant;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SpawnEggItem;

/** Unlike BirdSpawnEggItem/SnakeSpawnEggItem, this always spawns one fixed variant — the wiki and
 *  the original both give each small fish species its own dedicated spawn egg. */
public class SmallFishSpawnEggItem extends SpawnEggItem {

    private final SmallFishVariant variant;

    public SmallFishSpawnEggItem(SmallFishVariant variant, Item.Properties properties) {
        super(properties.spawnEgg(ModEntities.MOC_SMALL_FISH.get()));
        this.variant = variant;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        MoCSmallFishEntity fish = ModEntities.MOC_SMALL_FISH.get().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (fish == null) {
            return InteractionResult.FAIL;
        }

        fish.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D,
                serverLevel.getRandom().nextFloat() * 360F, 0F);
        fish.setVariant(this.variant);
        serverLevel.addFreshEntity(fish);

        var player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}