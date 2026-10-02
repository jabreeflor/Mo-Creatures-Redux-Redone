package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCKittyBedEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;

public class MoCKittyBedRenderer extends MoCMobRenderer<MoCKittyBedEntity, MoCKittyBedModel> {

    public static final ModelLayerLocation MOC_KITTY_BED_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_kitty_bed"), "main");

    public MoCKittyBedRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCKittyBedModel(context.bakeLayer(MOC_KITTY_BED_LAYER)), 0.3F);
        // The dyed sheet: second pass of the same model, tinted (was the second half of renderToBuffer).
        MoCModelPass sheetPass = new MoCModelPass(this.model, 1);
        this.addLayer(new MoCExtraPassLayer<>(this, (poseStack, collector, light, state) -> {
            MoCKittyBedEntity bed = state.entity();
            collector.submitModel(sheetPass, state, poseStack, this.model.renderType(this.getTextureLocation(bed)), light,
                    LivingEntityRenderer.getOverlayCoords(state, 0.0F), this.model.sheetTint(), null, state.outlineColor);
        }));
    }

    @Override
    public Identifier getTextureLocation(MoCKittyBedEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_kitty_bed/kitty_bed.png");
    }
}