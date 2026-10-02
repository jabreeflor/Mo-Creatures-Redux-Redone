package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCDragonflyEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class MoCDragonflyRenderer extends MoCInsectRenderer<MoCDragonflyEntity, MoCDragonflyModel<MoCDragonflyEntity>> {

    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_dragonfly"), "main");

    public MoCDragonflyRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCDragonflyModel<>(context.bakeLayer(LAYER)));
    }

    @Override
    public Identifier getTextureLocation(MoCDragonflyEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_dragonfly/" + entity.getVariant().getTextureName() + ".png");
    }
}
