package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCStingrayEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;

/**
 * Port of the stingray's renderer registration ({@code MoCRenderMoC} with {@code MoCModelRay}, shadow 0.4).
 * The 0.9x size is the entity's scale attribute, which the base renderer already applies.
 */
public class MoCStingrayRenderer extends MoCMobRenderer<MoCStingrayEntity, MoCStingrayModel> {


    /** How far the body sinks into the block below when the stingray rests on the bottom. */
    private static final float BOTTOM_SINK = 0.12F;

    public static final ModelLayerLocation MOC_STINGRAY_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_stingray"), "main");

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_stingray/ray_sting.png");

    public MoCStingrayRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCStingrayModel(context.bakeLayer(MOC_STINGRAY_LAYER)), 0.4F);
    }

    @Override
    protected void scale(MoCStingrayEntity ray, PoseStack poseStack, float partialTick) {
        // Resting on the bottom, the wings sink into the sand: it looks like the stingray is hiding in the block.
        if (ray.onGround()) {
            poseStack.translate(0.0F, BOTTOM_SINK, 0.0F);
        }
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }

    @Override
    public Identifier getTextureLocation(MoCStingrayEntity entity) {
        return TEXTURE;
    }
}