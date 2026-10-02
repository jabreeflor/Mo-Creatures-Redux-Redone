package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCOgreEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Shared renderer for the 3 ogre species ({@code MoCRenderMoC} with {@code MoCModelOgre}, shadow 0.9). */
public class MoCOgreRenderer<T extends MoCOgreEntity> extends MoCMobRenderer<T, MoCOgreModel<T>> {

    public static final ModelLayerLocation MOC_OGRE_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_ogre"), "main");

    private static final float SHADOW_RADIUS = 0.9F;

    public MoCOgreRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCOgreModel<>(context.bakeLayer(MOC_OGRE_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(T entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_ogre/" + entity.getTextureName() + ".png");
    }
}