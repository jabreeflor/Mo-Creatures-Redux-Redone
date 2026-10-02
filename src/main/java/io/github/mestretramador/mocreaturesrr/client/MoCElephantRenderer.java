package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCElephantEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;

public class MoCElephantRenderer extends MoCMobRenderer<MoCElephantEntity, MoCElephantModel> {

    public static final ModelLayerLocation MOC_ELEPHANT_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_elephant"), "main");

    public MoCElephantRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCElephantModel(context.bakeLayer(MOC_ELEPHANT_LAYER)), 1.2F);
    }

    @Override
    public Identifier getTextureLocation(MoCElephantEntity entity) {
        // The garment alone re-skins an Asian to the decorated texture. The howdah is
        // a separate physical throne sitting on top (see MoCElephantModel) — it doesn't
        // affect which texture is used.
        String textureName = entity.getVariant() == io.github.mestretramador.mocreaturesrr.entity.elephant.ElephantVariant.ASIAN
                && entity.hasGarment()
                ? io.github.mestretramador.mocreaturesrr.entity.elephant.ElephantVariant.ASIAN_DECORATED.getTextureName()
                : entity.getVariant().getTextureName();
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_elephant/" + textureName + ".png");
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}