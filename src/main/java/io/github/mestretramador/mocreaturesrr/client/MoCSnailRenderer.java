package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCSnailEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Port of the Snail's renderer registration (the generic renderer with shadow size 0). */
public class MoCSnailRenderer extends MoCMobRenderer<MoCSnailEntity, MoCSnailModel<MoCSnailEntity>> {

    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_snail"), "main");

    public MoCSnailRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCSnailModel<>(context.bakeLayer(LAYER)), 0.0F);
    }

    @Override
    public Identifier getTextureLocation(MoCSnailEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_snail/" + entity.getVariant().getTextureName() + ".png");
    }
}
