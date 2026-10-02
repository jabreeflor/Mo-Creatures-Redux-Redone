package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCHorseEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCWyvernEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCElephantEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCBigCatEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCManticoreEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCScorpionEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCOstrichEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCBearEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCKomodoDragonEntity;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


public class ScrollOfFreedomItem extends Item {

    public ScrollOfFreedomItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        boolean isMoCTameable = target instanceof AbstractHorse || target instanceof TamableAnimal;
        if (!isMoCTameable || !(target instanceof OwnableEntity ownable)) {
            return InteractionResult.PASS;
        }
        if (ownable.getOwnerReference() == null || !ownable.getOwnerReference().getUUID().equals(player.getUUID())) {
            return InteractionResult.PASS;
        }

        if (player.level().isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        // Drops saddle/armor/chest before freeing it
        if (target instanceof MoCHorseEntity horse) {
            horse.dropSaddleAndArmor();
            horse.dropChestAndContents();
        } else if (target instanceof MoCWyvernEntity wyvern) {
            wyvern.dropSaddleAndArmor();
            wyvern.dropChestAndContents();
        } else if (target instanceof MoCElephantEntity elephant) {
            elephant.dropAllEquipment();
        } else if (target instanceof MoCBigCatEntity bigCat) {
            bigCat.dropAllEquipment();
        } else if (target instanceof MoCManticoreEntity manticore) {
            manticore.dropAllEquipment();
        } else if (target instanceof MoCScorpionEntity scorpion) {
            scorpion.dropAllEquipment();
        } else if (target instanceof MoCOstrichEntity ostrich) {
            ostrich.dropAllEquipment();
        } else if (target instanceof MoCBearEntity bear) {
            bear.dropAllEquipment();
        } else if (target instanceof MoCKomodoDragonEntity komodo) {
            komodo.dropAllEquipment();
        }
        // (future entities with their own equipment: add their drop here)

        if (target instanceof TamableAnimal tamable) {
            tamable.setOwnerReference(null);
            tamable.setTame(false, true);
        } else if (target instanceof AbstractHorse horse) {
            horse.setOwner(null);
            horse.setTamed(false);
        }
        target.setCustomName(null);
        target.setCustomNameVisible(false);

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}