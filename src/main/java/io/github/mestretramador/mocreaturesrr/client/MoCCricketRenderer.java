package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCCricketEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Port of the Cricket's renderer registration (the generic renderer with shadow size 0). */
public class MoCCricketRenderer extends MoCMobRenderer<MoCCricketEntity, MoCCricketModel<MoCCricketEntity>> {

    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_cricket"), "main");

    public MoCCricketRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCCricketModel<>(context.bakeLayer(LAYER)), 0.0F);
    }

    @Override
    public Identifier getTextureLocation(MoCCricketEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_cricket/" + entity.getVariant().getTextureName() + ".png");
    }

    /** Original: while airborne it tilts nose-up when rising and nose-down when falling. */
    @Override
    protected void scale(MoCCricketEntity entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        if (!entity.onGround()) {
            double vertical = entity.getDeltaMovement().y;
            float angle = vertical > 0.5D ? 35.0F : vertical < -0.5D ? -35.0F : (float) (vertical * 70.0D);
            poseStack.rotateDegrees(com.mojang.math.Axis.XP, angle);
        }
    }
}
