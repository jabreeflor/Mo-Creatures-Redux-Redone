package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCSilverSkeletonEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Port of the Silver Skeleton's renderer registration (shadow 0.5). */
public class MoCSilverSkeletonRenderer extends MoCMobRenderer<MoCSilverSkeletonEntity, MoCSilverSkeletonModel<MoCSilverSkeletonEntity>> {

    public static final ModelLayerLocation MOC_SILVER_SKELETON_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_silver_skeleton"), "main");

    private static final float SHADOW_RADIUS = 0.5F;

    public MoCSilverSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCSilverSkeletonModel<>(context.bakeLayer(MOC_SILVER_SKELETON_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCSilverSkeletonEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_silver_skeleton/silver_skeleton.png");
    }
}