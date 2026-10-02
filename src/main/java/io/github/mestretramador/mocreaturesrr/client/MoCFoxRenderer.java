package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCFoxEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class MoCFoxRenderer extends MoCMobRenderer<MoCFoxEntity, MoCFoxModel> {

    public static final ModelLayerLocation MOC_FOX_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_fox"), "main");

    public MoCFoxRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCFoxModel(context.bakeLayer(MOC_FOX_LAYER)), 0.4F);
    }

    @Override
    public Identifier getTextureLocation(MoCFoxEntity entity) {
        String file = entity.isSnow() ? "fox_snow" : (entity.isBaby() ? "fox_cub" : "fox");
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_fox/" + file + ".png");
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}