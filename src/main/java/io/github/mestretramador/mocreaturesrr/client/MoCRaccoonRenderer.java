package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCRaccoonEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class MoCRaccoonRenderer extends MoCMobRenderer<MoCRaccoonEntity, MoCRaccoonModel> {

    public static final ModelLayerLocation MOC_RACCOON_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_raccoon"), "main");

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_raccoon/raccoon.png");

    public MoCRaccoonRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCRaccoonModel(context.bakeLayer(MOC_RACCOON_LAYER)), 0.4F);
    }

    @Override
    public Identifier getTextureLocation(MoCRaccoonEntity entity) {
        return TEXTURE;
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}