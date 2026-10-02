package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCSnakeEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class MoCSnakeRenderer extends MoCMobRenderer<MoCSnakeEntity, MoCSnakeModel> {

    public static final ModelLayerLocation MOC_SNAKE_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_snake"), "main");

    public MoCSnakeRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCSnakeModel(context.bakeLayer(MOC_SNAKE_LAYER)), 0.3F);
    }

    @Override
    public Identifier getTextureLocation(MoCSnakeEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_snake/" + entity.getVariant().getTextureName() + ".png");
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}