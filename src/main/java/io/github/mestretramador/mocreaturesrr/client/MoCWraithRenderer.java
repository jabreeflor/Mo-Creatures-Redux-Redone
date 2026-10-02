package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCFlameWraithEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCWraithEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

/** Port of the real {@code MoCRenderWraith}: same standard render pipeline every other entity in
 *  this mod uses (position, body/head rotation, walk animation all handled automatically), just
 *  drawn semi-transparent with a colour tint — grey for the Wraith, reddish for the Flame Wraith. */
public class MoCWraithRenderer extends MoCMobRenderer<MoCWraithEntity, MoCWraithModel<MoCWraithEntity>> {

    public static final ModelLayerLocation MOC_WRAITH_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_wraith"), "main");

    public MoCWraithRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCWraithModel<>(context.bakeLayer(MOC_WRAITH_LAYER)), 0.5F);
    }

    /** Original: drawn through an alpha-replacing, colour-multiplying vertex consumer. */
    @Override
    protected int getModelTint(MoCRenderState state) {
        boolean isFlame = state.entity instanceof MoCFlameWraithEntity;
        int alpha = isFlame ? 102 : 153; // 0.4 / 0.6 as a 0-255 value
        int tintR = isFlame ? 255 : 204; // 1.0 / 0.8
        int tintG = isFlame ? 153 : 204; // 0.6 / 0.8
        int tintB = isFlame ? 153 : 204; // 0.6 / 0.8
        return ARGB.color(alpha, tintR, tintG, tintB);
    }

    @Override
    protected net.minecraft.client.renderer.rendertype.RenderType getRenderType(MoCWraithEntity entity, boolean bodyVisible,
                       boolean translucent, boolean glowing) {
        return net.minecraft.client.renderer.rendertype.RenderTypes.entityTranslucent(this.getTextureLocation(entity));
    }

    @Override
    public Identifier getTextureLocation(MoCWraithEntity entity) {
        String texture = entity instanceof MoCFlameWraithEntity ? "wraith_flame" : "wraith";
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_wraith/" + texture + ".png");
    }
}