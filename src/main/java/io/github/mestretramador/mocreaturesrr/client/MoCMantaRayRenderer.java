package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCMantaRayEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

/**
 * Port of the manta ray's renderer registration ({@code MoCRenderMoC} with {@code MoCModelRay},
 * shadow 0.4). The 1.5x size is the entity's scale attribute, which the base renderer already applies.
 */
public class MoCMantaRayRenderer extends MoCMobRenderer<MoCMantaRayEntity, MoCMantaRayModel> {

    public static final ModelLayerLocation MOC_MANTA_RAY_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_manta_ray"), "main");

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_manta_ray/ray_manta.png");

    private static final float SHADOW_RADIUS = 0.4F;

    public MoCMantaRayRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCMantaRayModel(context.bakeLayer(MOC_MANTA_RAY_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCMantaRayEntity entity) {
        return TEXTURE;
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}