package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCRatEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Port of the Rat's renderer registration (shadow 0.3). */
public class MoCRatRenderer extends MoCMobRenderer<MoCRatEntity, MoCRatModel<MoCRatEntity>> {

    public static final ModelLayerLocation MOC_RAT_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_rat"), "main");

    private static final float SHADOW_RADIUS = 0.3F;

    public MoCRatRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCRatModel<>(context.bakeLayer(MOC_RAT_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCRatEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_rat/" + entity.getVariant().getTextureName() + ".png");
    }
}