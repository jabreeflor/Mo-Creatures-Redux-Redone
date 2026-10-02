package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCTurkeyEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class MoCTurkeyRenderer extends MoCMobRenderer<MoCTurkeyEntity, MoCTurkeyModel> {

    public static final ModelLayerLocation MOC_TURKEY_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_turkey"), "main");

    public MoCTurkeyRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCTurkeyModel(context.bakeLayer(MOC_TURKEY_LAYER)), 0.4F);
    }

    @Override
    public Identifier getTextureLocation(MoCTurkeyEntity entity) {
        String file = (entity.isMale() && !entity.isBaby()) ? "turkey_male" : "turkey_female";
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_turkey/" + file + ".png");
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}