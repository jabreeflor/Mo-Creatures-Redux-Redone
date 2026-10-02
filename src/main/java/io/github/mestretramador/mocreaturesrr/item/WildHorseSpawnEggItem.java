package io.github.mestretramador.mocreaturesrr.item;

import java.util.function.Supplier;

import io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Coat;
import io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species;
import io.github.mestretramador.mocreaturesrr.entity.MoCHorseEntity;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SpawnEggItem;

/**
 * Right-click on the ground: spawns ONE random pick from the "found in the
 * wild" list (5 tier-1 horse coats, zebra, donkey) as an ADULT.
 * Right-click on an existing MoC horse: spawns a BABY matching that horse's
 * species/coat exactly — same behavior as vanilla's horse spawn egg used
 * on an existing horse.
 */
public class WildHorseSpawnEggItem extends SpawnEggItem {

    private record WildOption(Species species, Coat coat) {}

    private static final WildOption[] WILD_OPTIONS = {
            new WildOption(Species.HORSE, Coat.WHITE),
            new WildOption(Species.HORSE, Coat.CREAMY),
            new WildOption(Species.HORSE, Coat.BROWN),
            new WildOption(Species.HORSE, Coat.DARKBROWN),
            new WildOption(Species.HORSE, Coat.BLACK),
            new WildOption(Species.ZEBRA, Coat.WHITE),
            new WildOption(Species.DONKEY, Coat.WHITE),
    };

    public WildHorseSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type, Item.Properties properties) {
        super(properties.spawnEgg(type.get()));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        WildOption pick = WILD_OPTIONS[serverLevel.getRandom().nextInt(WILD_OPTIONS.length)];
        spawnHorse(serverLevel, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, pick.species(), pick.coat(), false);

        Player player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!(target.level() instanceof ServerLevel serverLevel) || !(target instanceof MoCHorseEntity parent)) {
            return InteractionResult.PASS;
        }

        spawnHorse(serverLevel, target.getX(), target.getY(), target.getZ(), parent.getSpecies(), parent.getCoat(), true);

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }

    private void spawnHorse(ServerLevel level, double x, double y, double z, Species species, Coat coat, boolean baby) {
        MoCHorseEntity horse = ModEntities.MOC_HORSE.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (horse == null) return;

        double offsetX = (level.getRandom().nextDouble() - 0.5D) * 2.0D;
        double offsetZ = (level.getRandom().nextDouble() - 0.5D) * 2.0D;
        horse.snapTo(x + offsetX, y, z + offsetZ, level.getRandom().nextFloat() * 360F, 0F);
        horse.setSpecies(species);
        horse.setCoat(coat);
        if (baby) {
            horse.setAge(-24000);
        }
        level.addFreshEntity(horse);
    }
}