package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCGoatEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class MoCGoatRenderer extends MoCMobRenderer<MoCGoatEntity, MoCGoatModel> {

    public static final ModelLayerLocation MOC_GOAT_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_goat"), "main");

    // Deliberately a single shared pool now, not one array per sex — any of
    // these 6 colors can show up on either a male or a female goat.
    private static final String[] TEXTURES = {
            "goat_gray", "goat_brown", "goat_white",
            "goat_brown_light", "goat_brown_spotted", "goat_gray_spotted"
    };

    public MoCGoatRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCGoatModel(context.bakeLayer(MOC_GOAT_LAYER)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(MoCGoatEntity entity) {
        // colorIndex is rolled once in finalizeSpawn regardless of whether
        // the goat spawns as a kid or an adult, and never changes — so a kid
        // already wears the texture of whatever color it'll grow into. The
        // original forced babies to always render white, but that was an
        // accidental side effect of its switch-statement falling through to
        // a default case, not an intentional "kids are always white" design.
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_goat/" + TEXTURES[entity.getColorIndex()] + ".png");
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}