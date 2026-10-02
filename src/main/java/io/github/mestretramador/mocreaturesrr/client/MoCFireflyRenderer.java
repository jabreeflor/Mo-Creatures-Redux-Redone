package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCFireflyEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;

/** Port of {@code MoCRenderFirefly}: tilts 40 degrees nose-up while flying, with a glowing overlay
 *  pass of the whole model. */
public class MoCFireflyRenderer extends MoCInsectRenderer<MoCFireflyEntity, MoCFireflyModel<MoCFireflyEntity>> {

    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_firefly"), "main");

    private static final float FLYING_TILT_DEGREES = 40.0F;

    public MoCFireflyRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCFireflyModel<>(context.bakeLayer(LAYER)));
        this.addLayer(new GlowLayer(this));
    }

    @Override
    public Identifier getTextureLocation(MoCFireflyEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_firefly/firefly.png");
    }

    @Override
    protected void scale(MoCFireflyEntity entity, PoseStack poseStack, float partialTick) {
        if (entity.isFlying()) {
            poseStack.rotateDegrees(Axis.XP, FLYING_TILT_DEGREES);
        }
    }

    /** Original: LayerMoCFirefly - redraws the whole model with {@code firefly_glow.png}. Drawn with
     *  the fullbright "eyes" render type so it glows regardless of the surrounding light. */
    private static final class GlowLayer extends RenderLayer<MoCRenderState, MoCFireflyModel<MoCFireflyEntity>> {

        private static final Identifier GLOW =
                Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_firefly/firefly_glow.png");

        private final MoCModelPass wings;

        GlowLayer(MoCFireflyRenderer parent) {
            super(parent);
            this.wings = new MoCModelPass(parent.getModel(), 1);
        }

        @Override
        public void submit(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords, MoCRenderState state,
                           float yRot, float xRot) {
            MoCFireflyEntity entity = state.entity();
            // Wiki: they only glow at night, and purely as ambience (it isn't a light source).
            if (entity.level().isBrightOutside()) {
                return;
            }
            RenderType glow = RenderTypes.eyes(GLOW);
            // The whole model (body pass + the 60% opaque wing pass), full-bright.
            collector.submitModel(this.getParentModel(), state, poseStack, glow, LightCoordsUtil.FULL_BRIGHT,
                    OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor);
            MoCInsectRenderer.submitWings(this.getParentModel(), this.wings, state, poseStack, collector, glow,
                    LightCoordsUtil.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, -1);
        }
    }
}
