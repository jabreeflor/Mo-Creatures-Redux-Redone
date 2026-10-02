package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.entity.MoCInsectEntity;

import net.minecraft.client.model.geom.ModelPart;

/**
 * Base for the flying-insect models whose wings used to be drawn semi-transparent from a custom
 * {@code renderToBuffer}. In 26.x that method is final, so the translucent wings are drawn as a second
 * pass ({@link MoCModelPass} pass 1, submitted by {@link MoCInsectRenderer}) tinted with
 * {@link #wingAlpha()}; pass 0 draws everything else.
 */
public abstract class MoCInsectModel<T extends MoCInsectEntity> extends MoCEntityModel<T> {

    private static final ModelPart[] NONE = new ModelPart[0];

    protected MoCInsectModel(ModelPart root) {
        super(root);
    }

    /** Alpha the translucent wings are drawn with. */
    public float wingAlpha() {
        return 0.6F;
    }

    /**
     * Parts drawn translucent in the wing pass for the frame animated last by {@code setupAnim}. Return an
     * empty array when the wings are drawn opaque (or not at all) this frame.
     */
    protected abstract ModelPart[] translucentWings();

    protected static ModelPart[] none() {
        return NONE;
    }

    @Override
    protected void setupPass(T entity, MoCRenderState state, int pass) {
        ModelPart[] wings = this.translucentWings();
        if (pass == 0) {
            for (ModelPart wing : wings) {
                wing.skipDraw = true;
            }
        } else {
            for (ModelPart part : this.allParts()) {
                part.skipDraw = true;
            }
            for (ModelPart wing : wings) {
                wing.skipDraw = false;
            }
        }
    }
}
