package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCFoxEntity;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;

/**
 * Single egg for both fox colorations. Placing it in the world (inherited
 * default behaviour, not overridden here) already spawns a random-variant
 * adult, since MoCFoxEntity#finalizeSpawn rolls isSnow() for
 * MobSpawnType.SPAWN_EGG. The only thing added here is: right-clicking an
 * adult fox with this egg spawns a cub matching THAT adult's own variant —
 * unlike BearSpawnEggItem, there's no per-egg variant to match against.
 */
public class FoxSpawnEggItem extends SpawnEggItem {

    public FoxSpawnEggItem(Item.Properties properties) {
        super(properties.spawnEgg(ModEntities.MOC_FOX.get()));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!(target instanceof MoCFoxEntity adult) || adult.isBaby()) {
            return InteractionResult.PASS;
        }
        if (!(target.level() instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }
        MoCFoxEntity cub = ModEntities.MOC_FOX.get().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (cub != null) {
            cub.snapTo(target.getX(), target.getY(), target.getZ(), 0F, 0F);
            cub.setSnow(adult.isSnow());
            cub.setBaby(true);
            serverLevel.addFreshEntity(cub);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        return InteractionResult.SUCCESS;
    }
}