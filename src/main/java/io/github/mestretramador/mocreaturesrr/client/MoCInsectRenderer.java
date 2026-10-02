package io.github.mestretramador.mocreaturesrr.client;

import org.jspecify.annotations.Nullable;

import io.github.mestretramador.mocreaturesrr.entity.MoCInsectEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;

/** Port of {@code MoCRenderInsect}: no shadow, uniform size factor, and a translucent render type so
 *  the wings can be drawn semi-transparent (as an extra wing pass, see {@link MoCInsectModel}). */
public abstract class MoCInsectRenderer<T extends MoCInsectEntity, M extends MoCInsectModel<T>> extends MoCMobRenderer<T, M> {

    protected MoCInsectRenderer(EntityRendererProvider.Context context, M model) {
        super(context, model, 0.0F);
        this.addLayer(new WingLayer<>(this));
    }

    @Override
    protected void scale(T entity, PoseStack poseStack, float partialTick) {
        float size = entity.getSizeFactor();
        poseStack.scale(size, size, size);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(T entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (bodyVisible) {
            return RenderTypes.entityTranslucent(this.getTextureLocation(entity));
        }
        return super.getRenderType(entity, bodyVisible, translucent, glowing);
    }

    /** Draws the translucent wings (pass 1 of the model) on top of the body. */
    private static final class WingLayer<T extends MoCInsectEntity, M extends MoCInsectModel<T>>
            extends RenderLayer<MoCRenderState, M> {

        private final MoCInsectRenderer<T, M> renderer;
        private final MoCModelPass wings;

        WingLayer(MoCInsectRenderer<T, M> renderer) {
            super(renderer);
            this.renderer = renderer;
            this.wings = new MoCModelPass(renderer.getModel(), 1);
        }

        @Override
        @SuppressWarnings("unchecked")
        public void submit(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords, MoCRenderState state,
                           float yRot, float xRot) {
            if (state.isInvisibleToPlayer) {
                return;
            }
            int color = InsectModelUtil.withAlpha(-1, this.getParentModel().wingAlpha());
            collector.submitModel(this.wings, state, poseStack,
                    RenderTypes.entityTranslucent(this.renderer.getTextureLocation((T) state.entity)), lightCoords,
                    LivingEntityRenderer.getOverlayCoords(state, 0.0F), color, null, state.outlineColor);
        }
    }

    /** Submits the wing pass of {@code model} with the given render type, light and colour (used by
     *  extra layers such as the firefly glow that redraw the whole insect). */
    static void submitWings(MoCInsectModel<?> model, MoCModelPass wings, MoCRenderState state, PoseStack poseStack,
                            SubmitNodeCollector collector, RenderType renderType, int light, int overlay, int color) {
        collector.submitModel(wings, state, poseStack, renderType, light, overlay,
                InsectModelUtil.withAlpha(color, model.wingAlpha()), null, state.outlineColor);
    }
}
