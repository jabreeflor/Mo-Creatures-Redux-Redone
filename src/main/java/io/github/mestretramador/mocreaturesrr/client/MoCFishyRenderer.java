package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCFishyEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

/**
 * Port of the fishy's renderer registration ({@code MoCRenderMoC} with {@code MoCModelFishy},
 * shadow 0.1). The 0.6x size is the entity's scale attribute, which the base renderer already applies.
 */
public class MoCFishyRenderer extends MoCMobRenderer<MoCFishyEntity, MoCFishyModel> {

    public static final ModelLayerLocation MOC_FISHY_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_fishy"), "main");

    private static final float SHADOW_RADIUS = 0.1F;
    /** Original: out of water the fish lies on its side (rollRotationOffset() = -90). */
    private static final float STRANDED_ROLL_DEGREES = 90.0F;
    /**
     * Keeps the fish over its hitbox while it lies on its side, in the already scaled, flipped model
     * space of the scale hook: the roll swings its centre out to the side and the ground below it.
     */
    private static final float STRANDED_SIDE_SHIFT = -0.14F;
    private static final float STRANDED_LIFT = -0.02F;

    public MoCFishyRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCFishyModel(context.bakeLayer(MOC_FISHY_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCFishyEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_fishy/" + entity.getVariant().getTextureName() + ".png");
    }

    @Override
    protected void scale(MoCFishyEntity fishy, PoseStack poseStack, float partialTick) {
        if (!fishy.isInWater()) {
            poseStack.translate(STRANDED_SIDE_SHIFT, STRANDED_LIFT, 0.0F);
            poseStack.rotateDegrees(Axis.ZP, STRANDED_ROLL_DEGREES);
        }
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}