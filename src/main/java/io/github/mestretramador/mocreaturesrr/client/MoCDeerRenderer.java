package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCDeerEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Port of the Deer's renderer registration (shadow 0.7). */
public class MoCDeerRenderer extends MoCMobRenderer<MoCDeerEntity, MoCDeerModel<MoCDeerEntity>> {

    public static final ModelLayerLocation MOC_DEER_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_deer"), "main");

    private static final float SHADOW_RADIUS = 0.7F;

    public MoCDeerRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCDeerModel<>(context.bakeLayer(MOC_DEER_LAYER)), SHADOW_RADIUS);
    }

    private float prevLeapTilt;

    @Override
    public Identifier getTextureLocation(MoCDeerEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_deer/" + entity.getVariant().getTextureName() + ".png");
    }

    @Override
    protected void scale(MoCDeerEntity entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        float scale = entity.getAgeScale();
        poseStack.scale(scale, scale, scale);

        // Applied directly (no cross-frame smoothing) — an earlier attempt at interpolating this
        // per render frame instead of per game tick left it stuck partway instead of resetting
        // cleanly to 0 on the ground.
        float tilt = entity.getLeapTiltDegrees();
        if (tilt != 0.0F) {
            poseStack.rotateDegrees(com.mojang.math.Axis.ZP, tilt);
        }
    }
}