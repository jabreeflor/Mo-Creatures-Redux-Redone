package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCJellyfishEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;

/**
 * Port of the jellyfish's renderer registration ({@code MoCRenderMoC} with
 * {@code MoCModelJellyFish}, shadow 0.3). The night glow is approximated by forcing the whole model
 * to full brightness rather than true light emission (see the entity's class comment for why).
 */
public class MoCJellyfishRenderer extends MoCMobRenderer<MoCJellyfishEntity, MoCJellyfishModel> {

    public static final ModelLayerLocation MOC_JELLYFISH_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_jellyfish"), "main");

    private static final float SHADOW_RADIUS = 0.3F;
    private static final int FULL_BRIGHT = 15;

    public MoCJellyfishRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCJellyfishModel(context.bakeLayer(MOC_JELLYFISH_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCJellyfishEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_jellyfish/" + entity.getVariant().getTextureName() + ".png");
    }

    @Override
    protected int getBlockLightLevel(MoCJellyfishEntity entity, BlockPos pos) {
        if (entity.isGlowingAtNight()) {
            return FULL_BRIGHT;
        }
        return super.getBlockLightLevel(entity, pos);
    }

    /** Original: always drawn semi-transparent (0.7 alpha), so this always forces the translucent
     *  render type instead of the model's default cutout. */
    @Override
    protected RenderType getRenderType(MoCJellyfishEntity entity, boolean bodyVisible, boolean translucent, boolean showOutline) {
        return super.getRenderType(entity, bodyVisible, true, showOutline);
    }

    /** Original: the whole model was drawn at 0.7 alpha (through an alpha-replacing vertex consumer). */
    @Override
    protected int getModelTint(MoCRenderState state) {
        int alphaInt = (int) (0.7F * 255F);
        return ARGB.color(alphaInt, 0xFFFFFF);
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}