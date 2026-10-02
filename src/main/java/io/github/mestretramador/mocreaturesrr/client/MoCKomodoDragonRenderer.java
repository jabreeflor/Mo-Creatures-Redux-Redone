package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCKomodoDragonEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class MoCKomodoDragonRenderer extends MoCMobRenderer<MoCKomodoDragonEntity, MoCKomodoDragonModel> {

    public static final ModelLayerLocation MOC_KOMODO_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_komodo"), "main");

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_komodo/komodo_dragon.png");

    public MoCKomodoDragonRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCKomodoDragonModel(context.bakeLayer(MOC_KOMODO_LAYER)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(MoCKomodoDragonEntity entity) {
        return TEXTURE;
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}