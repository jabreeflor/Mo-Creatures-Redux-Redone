package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCCrocodileEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

/** Port of the crocodile's renderer registration ({@code MoCRenderCrocodile}, shadow 0.7). */
public class MoCCrocodileRenderer extends MoCMobRenderer<MoCCrocodileEntity, MoCCrocodileModel> {

    public static final ModelLayerLocation MOC_CROCODILE_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_crocodile"), "main");

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_crocodile/crocodile.png");

    private static final float SHADOW_RADIUS = 0.7F;

    public MoCCrocodileRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCCrocodileModel(context.bakeLayer(MOC_CROCODILE_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCCrocodileEntity entity) {
        return TEXTURE;
    }

    /** The real "death roll": tumbles the model around its own forward axis, not around the vertical
     *  (yaw) axis — a barrel roll, matching what real crocodiles actually do. */
    @Override
    protected void scale(MoCCrocodileEntity entity, PoseStack poseStack, float partialTick) {
        if (entity.getRollAngle() != 0.0F) {
            poseStack.rotateDegrees(Axis.ZP, entity.getRollAngle());
        }
    }
}