package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCManticoreEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class MoCManticoreRenderer extends MoCMobRenderer<MoCManticoreEntity, MoCManticoreModel> {

    public static final ModelLayerLocation MOC_MANTICORE_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_manticore"), "main");

    public MoCManticoreRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCManticoreModel(context.bakeLayer(MOC_MANTICORE_LAYER)), 0.7F);
    }

    @Override
    public Identifier getTextureLocation(MoCManticoreEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_manticore/" + entity.getVariant().getTextureName() + ".png");
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}