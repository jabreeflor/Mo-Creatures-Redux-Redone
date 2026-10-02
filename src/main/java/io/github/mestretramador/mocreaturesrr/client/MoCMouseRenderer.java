package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCMouseEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Port of the Mouse's renderer registration (shadow 0.2). */
public class MoCMouseRenderer extends MoCMobRenderer<MoCMouseEntity, MoCMouseModel<MoCMouseEntity>> {

    public static final ModelLayerLocation MOC_MOUSE_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_mouse"), "main");

    private static final float SHADOW_RADIUS = 0.2F;

    public MoCMouseRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCMouseModel<>(context.bakeLayer(MOC_MOUSE_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCMouseEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_mouse/" + entity.getVariant().getTextureName() + ".png");
    }

    @Override
    protected void scale(MoCMouseEntity entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        float scale = 0.6F;
        poseStack.scale(scale, scale, scale);
    }
}