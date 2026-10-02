package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCBigGolemEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import com.mojang.math.Axis;

/** Port of {@code MoCRenderGolem}: the block body drawn 1.8x bigger, with its glowing state overlay on top. */
public class MoCBigGolemRenderer extends MoCMobRenderer<MoCBigGolemEntity, MoCBigGolemModel<MoCBigGolemEntity>> {

    public static final ModelLayerLocation MOC_BIG_GOLEM_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_big_golem"), "main");

    /** Original: getSizeFactor() = 1.8. */
    private static final float MODEL_SCALE = 1.8F;
    /** Original shadow 0.5 before scaling. */
    private static final float SHADOW_RADIUS = 0.5F * MODEL_SCALE;
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_golem/golem.png");

    public MoCBigGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCBigGolemModel<>(context.bakeLayer(MOC_BIG_GOLEM_LAYER)), SHADOW_RADIUS);
        this.addLayer(new CubeLayer(this));
        this.addLayer(new MoCBigGolemEffectLayer(this));
    }

    @Override
    public Identifier getTextureLocation(MoCBigGolemEntity golem) {
        return TEXTURE;
    }

    @Override
    protected void scale(MoCBigGolemEntity golem, PoseStack poseStack, float partialTick) {
        poseStack.scale(MODEL_SCALE, MODEL_SCALE, MODEL_SCALE);
        float lean = golem.getLeanDegrees();
        if (lean != 0.0F) {
            poseStack.rotateDegrees(Axis.ZN, lean);
        }
    }

    /**
     * Draws the body cubes, each shifted to its texture tile (see {@link MoCBigGolemModel#submitCubes});
     * the main model draw only holds the head and chest core. Mirrors the body's invisibility handling.
     */
    private static final class CubeLayer extends RenderLayer<MoCRenderState, MoCBigGolemModel<MoCBigGolemEntity>> {

        CubeLayer(MoCBigGolemRenderer renderer) {
            super(renderer);
        }

        @Override
        public void submit(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords, MoCRenderState state,
                           float yRot, float xRot) {
            RenderType renderType;
            int color = -1;
            if (!state.isInvisible) {
                renderType = this.getParentModel().renderType(TEXTURE);
            } else if (!state.isInvisibleToPlayer) {
                renderType = RenderTypes.entityTranslucentCull(TEXTURE);
                color = 0x26FFFFFF;
            } else if (state.appearsGlowing()) {
                renderType = RenderTypes.outline(TEXTURE);
            } else {
                return;
            }
            MoCBigGolemEntity golem = state.entity();
            this.getParentModel().submitCubes(golem, state, poseStack, collector, renderType, lightCoords,
                    LivingEntityRenderer.getOverlayCoords(state, 0.0F), color);
        }
    }
}
