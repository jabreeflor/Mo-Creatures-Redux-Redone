package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCFlyEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class MoCFlyRenderer extends MoCInsectRenderer<MoCFlyEntity, MoCFlyModel<MoCFlyEntity>> {

    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_fly"), "main");

    public MoCFlyRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCFlyModel<>(context.bakeLayer(LAYER)));
    }

    @Override
    public Identifier getTextureLocation(MoCFlyEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_fly/" + "fly" + ".png");
    }
}
