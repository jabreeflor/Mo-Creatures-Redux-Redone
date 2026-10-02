package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.entity.CarriedBlockEntity;

import net.minecraft.world.entity.Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

/** Port of {@code MoCRenderTRock}: draws the carried block itself, tumbling around its vertical axis. Shared by thrown and summoned rocks. */
public class MoCThrowableRockRenderer<T extends Entity & CarriedBlockEntity>
        extends EntityRenderer<T, MoCThrowableRockRenderer.RockRenderState> {

    private static final float SHADOW_RADIUS = 0.5F;
    private static final float SPIN_DEGREES_PER_TICK = 18.0F;
    private static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();

    private final BlockModelResolver blockModelResolver;

    public MoCThrowableRockRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = SHADOW_RADIUS;
        this.blockModelResolver = context.getBlockModelResolver();
    }

    @Override
    public RockRenderState createRenderState() {
        return new RockRenderState();
    }

    @Override
    public void extractRenderState(T entity, RockRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.spinDegrees = (entity.tickCount + partialTick) * SPIN_DEGREES_PER_TICK;
        BlockState blockState = entity.getBlockState();
        if (blockState.getRenderShape() == RenderShape.MODEL) {
            this.blockModelResolver.update(state.block, blockState, BLOCK_DISPLAY_CONTEXT);
        } else {
            state.block.clear();
        }
    }

    @Override
    public void submit(RockRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
        if (!state.block.isEmpty()) {
            poseStack.pushPose();
            // Spin around the centre of the 1x1x1 hitbox, then draw the block from its corner.
            poseStack.translate(0.0D, 0.5D, 0.0D);
            poseStack.rotateDegrees(Axis.YP, state.spinDegrees);
            poseStack.translate(-0.5D, -0.5D, -0.5D);
            state.block.submit(poseStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
            poseStack.popPose();
        }
        super.submit(state, poseStack, collector, camera);
    }

    /** Render state of a thrown / summoned rock: its block model and current spin. */
    public static class RockRenderState extends EntityRenderState {
        public final BlockModelRenderState block = new BlockModelRenderState();
        public float spinDegrees;
    }
}
