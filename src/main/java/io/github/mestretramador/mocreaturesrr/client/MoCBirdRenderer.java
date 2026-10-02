package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCBirdEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class MoCBirdRenderer extends MoCMobRenderer<MoCBirdEntity, MoCBirdModel> {

    public static final ModelLayerLocation MOC_BIRD_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_bird"), "main");

    public MoCBirdRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCBirdModel(context.bakeLayer(MOC_BIRD_LAYER)), 0.2F);
    }

    @Override
    public Identifier getTextureLocation(MoCBirdEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_bird/" + entity.getVariant().getTextureName() + ".png");
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}