package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCBigCatEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

public class MoCBigCatRenderer extends MoCMobRenderer<MoCBigCatEntity, MoCBigCatModel> {

    public static final ModelLayerLocation MOC_BIG_CAT_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_big_cat"), "main");

    public MoCBigCatRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCBigCatModel(context.bakeLayer(MOC_BIG_CAT_LAYER)), 0.7F);
    }

    @Override
    public Identifier getTextureLocation(MoCBigCatEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_big_cat/" + entity.getVariant().getTextureName() + ".png");
    }

    @Override
    protected RenderType getRenderType(MoCBigCatEntity entity, boolean bodyVisible, boolean translucent, boolean showOutline) {
        return super.getRenderType(entity, bodyVisible, translucent || entity.isGhost(), showOutline);
    }

    /** Original: ghost big cats were drawn at 0.35 alpha (through an alpha-replacing vertex consumer). */
    @Override
    protected int getModelTint(MoCRenderState state) {
        if (!(state.entity instanceof MoCBigCatEntity entity) || !entity.isGhost()) {
            return -1;
        }
        int alphaInt = (int) (0.35F * 255F);
        return ARGB.color(alphaInt, 0xFFFFFF);
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}