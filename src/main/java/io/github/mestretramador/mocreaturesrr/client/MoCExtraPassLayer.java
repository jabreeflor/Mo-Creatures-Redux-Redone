package io.github.mestretramador.mocreaturesrr.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

/**
 * A render layer that submits extra draws (usually {@link MoCModelPass}es) in the body's pose.
 *
 * <p>In 1.21.1 several models drew some of their parts a second time from a custom
 * {@code renderToBuffer} (translucent wings, tinted sheets, ...). Those extra draws have to happen with
 * the exact transform the body is drawn with, which in 26.x is only available to render layers. Like the
 * old {@code renderToBuffer}, nothing is drawn while the entity is invisible.
 */
public class MoCExtraPassLayer<M extends MoCEntityModel<?>> extends RenderLayer<MoCRenderState, M> {

    @FunctionalInterface
    public interface PassSubmitter {
        void submit(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords, MoCRenderState state);
    }

    private final PassSubmitter submitter;

    public MoCExtraPassLayer(RenderLayerParent<MoCRenderState, M> parent, PassSubmitter submitter) {
        super(parent);
        this.submitter = submitter;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords, MoCRenderState state,
                       float yRot, float xRot) {
        if (state.isInvisible || state.entity == null) {
            return;
        }
        this.submitter.submit(poseStack, collector, lightCoords, state);
    }

    /**
     * For an extra pass: skips the cubes of every part of {@code model} except {@code parts} and their
     * descendants. Visibility set by {@code setupAnim} is left untouched, so hidden parts stay hidden.
     */
    public static void drawOnly(MoCEntityModel<?> model, ModelPart... parts) {
        for (ModelPart part : model.allParts()) {
            part.skipDraw = true;
        }
        for (ModelPart part : parts) {
            for (ModelPart child : part.getAllParts()) {
                child.skipDraw = false;
            }
        }
    }

    /** For the main pass: skips the cubes of {@code parts} and their descendants (drawn by an extra pass). */
    public static void skip(ModelPart... parts) {
        for (ModelPart part : parts) {
            for (ModelPart child : part.getAllParts()) {
                child.skipDraw = true;
            }
        }
    }
}
