package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCKittyEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import org.joml.Matrix4f;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

/** Floating mood emoticon above a Kitty's head — same 15-icon set as the original. */
public final class MoCKittyEmoticonRenderer {

    private static final double SHOW_DISTANCE_SQR = 64.0D;
    private static final int SIZE = 16;

    private MoCKittyEmoticonRenderer() {
    }

    private static String textureFor(int kittyState) {
        return switch (kittyState) {
            case -1 -> "emoticon_blank";
            case 3 -> "emoticon_3";
            case 4 -> "emoticon_4";
            case 5 -> "emoticon_5";
            case 7 -> "emoticon_7";
            case 8 -> "emoticon_8";
            case 9, 18 -> "emoticon_9";
            case 10, 21 -> "emoticon_10";
            case 11 -> "emoticon_11";
            case 12 -> "emoticon_12";
            case 13 -> "emoticon_13";
            case 16 -> "emoticon_16";
            case 17 -> "emoticon_17";
            case 19, 20 -> "emoticon_19";
            default -> "emoticon_1";
        };
    }

    /** Submits the emoticon above the kitty in {@code state} (pose stack at the entity's render origin). */
    public static void submit(MoCRenderState state, PoseStack poseStack, SubmitNodeCollector collector,
                              CameraRenderState camera) {
        if (!(state.entity instanceof MoCKittyEntity entity)) {
            return;
        }
        if (!entity.isTame() || !entity.showEmoteIcon() || state.distanceToCameraSq > SHOW_DISTANCE_SQR) {
            return;
        }

        Identifier texture = Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/misc/" + textureFor(entity.getKittyState()) + ".png");

        poseStack.pushPose();
        poseStack.translate(0.0, entity.getBbHeight() + 0.85F, 0.0);
        poseStack.rotate(camera.orientation);
        poseStack.scale(0.025F, -0.025F, 0.025F);

        float half = SIZE / 2F;
        int lightU = 240; // full brightness on both lightmap axes — the standard "always lit" trick
        int lightV = 240;
        collector.submitCustomGeometry(poseStack, RenderTypes.text(texture), (pose, vc) -> {
            Matrix4f matrix = pose.pose();
            vc.addVertex(matrix, -half, half, 0F).setColor(1F, 1F, 1F, 1F).setUv(0F, 1F).setUv2(lightU, lightV);
            vc.addVertex(matrix, half, half, 0F).setColor(1F, 1F, 1F, 1F).setUv(1F, 1F).setUv2(lightU, lightV);
            vc.addVertex(matrix, half, -half, 0F).setColor(1F, 1F, 1F, 1F).setUv(1F, 0F).setUv2(lightU, lightV);
            vc.addVertex(matrix, -half, -half, 0F).setColor(1F, 1F, 1F, 1F).setUv(0F, 0F).setUv2(lightU, lightV);
        });

        poseStack.popPose();
    }
}
