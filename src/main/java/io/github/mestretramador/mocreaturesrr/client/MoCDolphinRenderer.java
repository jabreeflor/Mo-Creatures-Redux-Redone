package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCDolphinEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

/**
 * Port of the dolphin's renderer registration ({@code MoCRenderDolphin} with
 * {@code MoCModelDolphin}, shadow 0.6). The original's age-based model stretch is the entity's
 * scale attribute here, which the base renderer already applies.
 */
public class MoCDolphinRenderer extends MoCMobRenderer<MoCDolphinEntity, MoCDolphinModel> {

    public static final ModelLayerLocation MOC_DOLPHIN_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_dolphin"), "main");

    private static final float SHADOW_RADIUS = 0.6F;

    public MoCDolphinRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCDolphinModel(context.bakeLayer(MOC_DOLPHIN_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCDolphinEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_dolphin/" + entity.getVariant().getTextureName() + ".png");
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}