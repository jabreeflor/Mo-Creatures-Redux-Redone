package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.entity.MoCMiniGolemEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.RenderShape;

/** Draws the block the Mini Golem is holding above its head, turning with its body (like the Enderman's carried block). */
public class MoCMiniGolemHeldRockLayer extends RenderLayer<MoCRenderState, MoCMiniGolemModel<MoCMiniGolemEntity>> {

    /** LivingEntityRenderer shifts model space 1.501 blocks down before calling layers. */
    private static final double MODEL_ORIGIN_HEIGHT = 1.501D;
    private static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();

    public MoCMiniGolemHeldRockLayer(RenderLayerParent<MoCRenderState, MoCMiniGolemModel<MoCMiniGolemEntity>> parent) {
        super(parent);
    }

    /** Resolves the held block into the render state (called from the renderer's extractRenderState). */
    public static void extractHeldRock(MoCMiniGolemEntity golem, HeldRockRenderState state, BlockModelResolver blockModelResolver) {
        state.heldRock.clear();
        golem.getHeldBlock()
                .filter(block -> block.getRenderShape() == RenderShape.MODEL)
                .ifPresent(block -> blockModelResolver.update(state.heldRock, block, BLOCK_DISPLAY_CONTEXT));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int packedLight, MoCRenderState state,
                       float yRot, float xRot) {
        if (!(state instanceof HeldRockRenderState rockState) || rockState.heldRock.isEmpty()) {
            return;
        }
        poseStack.pushPose();
        // Undo the model's upside-down flip so the block is drawn upright, sitting on its raised hands.
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.translate(-0.5D, MoCMiniGolemEntity.HELD_ROCK_HEIGHT - MODEL_ORIGIN_HEIGHT, -0.5D);
        rockState.heldRock.submit(poseStack, collector, packedLight, OverlayTexture.NO_OVERLAY, state.outlineColor);
        poseStack.popPose();
    }

    /** Mini Golem render state: also carries the resolved held block. */
    public static class HeldRockRenderState extends MoCRenderState {
        public final BlockModelRenderState heldRock = new BlockModelRenderState();
    }
}
