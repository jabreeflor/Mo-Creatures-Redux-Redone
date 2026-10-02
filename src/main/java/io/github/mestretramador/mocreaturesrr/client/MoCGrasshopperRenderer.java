package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCGrasshopperEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class MoCGrasshopperRenderer extends MoCInsectRenderer<MoCGrasshopperEntity, MoCGrasshopperModel<MoCGrasshopperEntity>> {

    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_grasshopper"), "main");

    public MoCGrasshopperRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCGrasshopperModel<>(context.bakeLayer(LAYER)));
    }

    @Override
    public Identifier getTextureLocation(MoCGrasshopperEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_grasshopper/" + entity.getVariant().getTextureName() + ".png");
    }

    /** Original: while airborne it tilts nose-up when rising and nose-down when falling. */
    @Override
    protected void scale(MoCGrasshopperEntity entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        super.scale(entity, poseStack, partialTick);
        if (!entity.onGround()) {
            double vertical = entity.getDeltaMovement().y;
            float angle = vertical > 0.5D ? 35.0F : vertical < -0.5D ? -35.0F : (float) (vertical * 70.0D);
            poseStack.rotateDegrees(com.mojang.math.Axis.XP, angle);
        }
    }
}
