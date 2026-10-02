package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.egg.MoCEggEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class MoCEggRenderer extends MoCMobRenderer<MoCEggEntity, MoCEggModel> {

    public static final ModelLayerLocation MOC_EGG_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_egg"), "main");

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_egg/egg.png");

    public MoCEggRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCEggModel(context.bakeLayer(MOC_EGG_LAYER)), 0.3F);
    }

    @Override
    public Identifier getTextureLocation(MoCEggEntity entity) {
        // Same texture no matter what's inside — appearance never depends on
        // the hatch type, only the hatched creature does.
        return TEXTURE;
    }
}