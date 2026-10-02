package io.github.mestretramador.mocreaturesrr.client;

import java.util.EnumMap;
import java.util.Map;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCEntEntity;
import io.github.mestretramador.mocreaturesrr.entity.ent.EntVariant;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Port of the Ent's renderer registration (shadow 0.5, one texture per variant). */
public class MoCEntRenderer extends MoCMobRenderer<MoCEntEntity, MoCEntModel<MoCEntEntity>> {

    public static final ModelLayerLocation MOC_ENT_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_ent"), "main");

    private static final float SHADOW_RADIUS = 0.5F;
    private static final Map<EntVariant, Identifier> TEXTURES = new EnumMap<>(EntVariant.class);

    static {
        for (EntVariant variant : EntVariant.values()) {
            TEXTURES.put(variant, Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                    "textures/entity/moc_ent/" + variant.getTextureName() + ".png"));
        }
    }

    public MoCEntRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCEntModel<>(context.bakeLayer(MOC_ENT_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCEntEntity entity) {
        return TEXTURES.get(entity.getVariant());
    }
}