package io.github.mestretramador.mocreaturesrr.client;

import java.util.EnumMap;
import java.util.Map;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCFilchLizardEntity;
import io.github.mestretramador.mocreaturesrr.entity.filchlizard.FilchLizardVariant;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Port of {@code MoCRenderFilchLizard}: one texture per variant, plus the loot drawn in its mouth. */
public class MoCFilchLizardRenderer extends MoCMobRenderer<MoCFilchLizardEntity, MoCFilchLizardModel<MoCFilchLizardEntity>> {

    public static final ModelLayerLocation MOC_FILCH_LIZARD_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_filch_lizard"), "main");

    private static final float SHADOW_RADIUS = 0.5F;
    private static final Map<FilchLizardVariant, Identifier> TEXTURES = new EnumMap<>(FilchLizardVariant.class);

    static {
        for (FilchLizardVariant variant : FilchLizardVariant.values()) {
            TEXTURES.put(variant, Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                    "textures/entity/moc_filch_lizard/" + variant.getTextureName() + ".png"));
        }
    }

    public MoCFilchLizardRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCFilchLizardModel<>(context.bakeLayer(MOC_FILCH_LIZARD_LAYER)), SHADOW_RADIUS);
        this.addLayer(new MoCFilchLizardLootLayer(this));
    }

    @Override
    public Identifier getTextureLocation(MoCFilchLizardEntity lizard) {
        return TEXTURES.get(lizard.getVariant());
    }

    @Override
    public MoCRenderState createRenderState() {
        return new MoCFilchLizardLootLayer.LootRenderState();
    }

    @Override
    public void extractRenderState(MoCFilchLizardEntity lizard, MoCRenderState state, float partialTicks) {
        super.extractRenderState(lizard, state, partialTicks);
        if (state instanceof MoCFilchLizardLootLayer.LootRenderState lootState) {
            MoCFilchLizardLootLayer.extractLoot(lizard, lootState, this.itemModelResolver);
        }
    }
}
