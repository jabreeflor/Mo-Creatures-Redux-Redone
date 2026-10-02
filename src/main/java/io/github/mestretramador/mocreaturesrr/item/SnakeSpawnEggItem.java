package io.github.mestretramador.mocreaturesrr.item;

import io.github.mestretramador.mocreaturesrr.entity.MoCSnakeEntity;
import io.github.mestretramador.mocreaturesrr.entity.snake.SnakeVariant;
import io.github.mestretramador.mocreaturesrr.init.ModEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SpawnEggItem;

/** A single spawn egg — picks a random colour among the 8, same pattern as ScorpionSpawnEggItem. */
public class SnakeSpawnEggItem extends SpawnEggItem {

    public SnakeSpawnEggItem(Item.Properties properties) {
        super(properties.spawnEgg(ModEntities.MOC_SNAKE.get()));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        MoCSnakeEntity snake = ModEntities.MOC_SNAKE.get().create(serverLevel, EntitySpawnReason.SPAWN_ITEM_USE);
        if (snake == null) {
            return InteractionResult.FAIL;
        }

        snake.snapTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D,
                serverLevel.getRandom().nextFloat() * 360F, 0F);
        snake.setVariant(SnakeVariant.random(serverLevel.getRandom()));
        serverLevel.addFreshEntity(snake);

        var player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}