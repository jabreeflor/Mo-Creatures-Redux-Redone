package io.github.mestretramador.mocreaturesrr.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.equine.AbstractHorse;

/**
 * Draws a small green/red health bar above a tamed creature's name tag,
 * matching the original Mo' Creatures behavior. {@link MoCMobRenderer} calls this for every
 * renderer that opts in through {@link MoCMobRenderer#showsHealthBar()}.
 */
public final class TameableOverlayRenderer {

    private static final double SHOW_DISTANCE_SQR = 64.0D; // 8 blocks, same as the name tag
    /** Exposed so a renderer's nameplate check can match this exact range. */
    public static final double NAME_AND_HEALTH_SHOW_DISTANCE_SQR = SHOW_DISTANCE_SQR;
    private static final int BAR_WIDTH = 40;
    private static final int BAR_HEIGHT = 4;

    private TameableOverlayRenderer() {
    }

    public static boolean isOwnedAndTamed(Entity entity) {
        if (entity instanceof TamableAnimal tamable) return tamable.isTame();
        if (entity instanceof AbstractHorse horse) return horse.isTamed();
        return false;
    }

    public static void submitHealthBar(MoCRenderState state, PoseStack poseStack, SubmitNodeCollector collector,
                                       CameraRenderState camera) {
        if (!(state.entity instanceof LivingEntity entity) || !isOwnedAndTamed(entity)
                || state.distanceToCameraSq > SHOW_DISTANCE_SQR) {
            return;
        }

        poseStack.pushPose();
        poseStack.translate(0.0, entity.getBbHeight() + 0.65F, 0.0);
        poseStack.rotate(camera.orientation);
        poseStack.scale(0.025F, -0.025F, 0.025F);

        float maxHealth = entity.getMaxHealth();
        float pct = maxHealth > 0F ? Mth.clamp(entity.getHealth() / maxHealth, 0F, 1F) : 0F;
        float left = -BAR_WIDTH / 2F;
        float filledWidth = BAR_WIDTH * pct;
        if (filledWidth < BAR_WIDTH) {
            collector.submitTextBackground(poseStack, left + filledWidth, 0, left + BAR_WIDTH, BAR_HEIGHT,
                    0xFFAA0000, Font.DisplayMode.SEE_THROUGH, state.lightCoords);
        }
        if (filledWidth > 0) {
            collector.submitTextBackground(poseStack, left, 0, left + filledWidth, BAR_HEIGHT,
                    0xFF00AA00, Font.DisplayMode.SEE_THROUGH, state.lightCoords);
        }

        poseStack.popPose();
    }
}
