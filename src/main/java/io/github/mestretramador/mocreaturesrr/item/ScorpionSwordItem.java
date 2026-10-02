package io.github.mestretramador.mocreaturesrr.item;

import java.util.function.Supplier;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Diamond-tier base sword that applies a status effect, or sets fire, on hit.
 * (SwordItem/Tier no longer exist: the sword stats come from Item.Properties#sword.)
 */
public class ScorpionSwordItem extends Item {

    private final Supplier<MobEffectInstance> effect;
    private final int fireSeconds;

    public ScorpionSwordItem(Properties properties, Supplier<MobEffectInstance> effect) {
        this(properties, effect, 0);
    }

    public ScorpionSwordItem(Properties properties, int fireSeconds) {
        this(properties, null, fireSeconds);
    }

    private ScorpionSwordItem(Properties properties, Supplier<MobEffectInstance> effect, int fireSeconds) {
        super(properties);
        this.effect = effect;
        this.fireSeconds = fireSeconds;
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.hurtEnemy(stack, target, attacker);
        if (!target.level().isClientSide()) {
            if (this.effect != null) {
                target.addEffect(this.effect.get());
            }
            if (this.fireSeconds > 0) {
                target.igniteForSeconds(this.fireSeconds);
            }
        }
    }
}
