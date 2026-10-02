package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCHellRatEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Port of the Hellrat's renderer registration: reuses the Rat's own model, 1.3x bigger, with its
 *  own 2-frame animated texture instead of a colour variant. */
public class MoCHellRatRenderer extends MoCMobRenderer<MoCHellRatEntity, MoCRatModel<MoCHellRatEntity>> {

    private static final float SHADOW_RADIUS = 0.4F;
    private static final float SCALE = 1.3F;

    public MoCHellRatRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCRatModel<>(context.bakeLayer(MoCRatRenderer.MOC_RAT_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCHellRatEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_hell_rat/" + entity.getTextureFrameName() + ".png");
    }

    @Override
    protected void scale(MoCHellRatEntity entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(SCALE, SCALE, SCALE);
    }
}