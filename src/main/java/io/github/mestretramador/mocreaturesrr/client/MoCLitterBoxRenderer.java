package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCLitterBoxEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class MoCLitterBoxRenderer extends MoCMobRenderer<MoCLitterBoxEntity, MoCLitterBoxModel> {

    public static final ModelLayerLocation MOC_LITTER_BOX_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_litter_box"), "main");

    public MoCLitterBoxRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCLitterBoxModel(context.bakeLayer(MOC_LITTER_BOX_LAYER)), 0.3F);
    }

    @Override
    public Identifier getTextureLocation(MoCLitterBoxEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_litter_box/litter_box.png");
    }
}