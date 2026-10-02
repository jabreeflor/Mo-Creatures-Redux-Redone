package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCRoachEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class MoCRoachRenderer extends MoCInsectRenderer<MoCRoachEntity, MoCRoachModel<MoCRoachEntity>> {

    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_roach"), "main");

    public MoCRoachRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCRoachModel<>(context.bakeLayer(LAYER)));
    }

    @Override
    public Identifier getTextureLocation(MoCRoachEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_roach/" + "roach" + ".png");
    }
}
