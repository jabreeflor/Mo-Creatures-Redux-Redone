package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCSharkEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class MoCSharkRenderer extends MoCMobRenderer<MoCSharkEntity, MoCSharkModel> {

    public static final ModelLayerLocation MOC_SHARK_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_shark"), "main");

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_shark/shark.png");

    public MoCSharkRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCSharkModel(context.bakeLayer(MOC_SHARK_LAYER)), 0.7F);
    }

    @Override
    public Identifier getTextureLocation(MoCSharkEntity entity) {
        return TEXTURE;
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}