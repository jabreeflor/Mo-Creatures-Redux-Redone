package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCDuckEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Port of the Duck's renderer registration (shadow 0.3). */
public class MoCDuckRenderer extends MoCMobRenderer<MoCDuckEntity, MoCDuckModel<MoCDuckEntity>> {

    public static final ModelLayerLocation MOC_DUCK_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_duck"), "main");

    private static final float SHADOW_RADIUS = 0.3F;

    public MoCDuckRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCDuckModel<>(context.bakeLayer(MOC_DUCK_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCDuckEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_duck/duck.png");
    }
}