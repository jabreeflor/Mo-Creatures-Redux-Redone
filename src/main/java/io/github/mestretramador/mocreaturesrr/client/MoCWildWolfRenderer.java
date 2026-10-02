package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCWildWolfEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Port of the Wild Wolf's renderer registration ({@code MoCRenderWWolf}, shadow 0.5). */
public class MoCWildWolfRenderer extends MoCMobRenderer<MoCWildWolfEntity, MoCWildWolfModel<MoCWildWolfEntity>> {

    public static final ModelLayerLocation MOC_WILD_WOLF_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_wild_wolf"), "main");

    private static final float SHADOW_RADIUS = 0.5F;

    public MoCWildWolfRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCWildWolfModel<>(context.bakeLayer(MOC_WILD_WOLF_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCWildWolfEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_wild_wolf/" + entity.getVariant().getTextureName() + ".png");
    }
}