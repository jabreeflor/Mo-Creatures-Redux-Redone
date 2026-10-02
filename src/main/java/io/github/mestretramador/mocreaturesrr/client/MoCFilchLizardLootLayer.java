package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.entity.MoCFilchLizardEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;

/** Port of {@code MoCRenderFilchLizard.LayerHeldItemCustom}: draws the stolen item held up in front of it. */
public class MoCFilchLizardLootLayer extends RenderLayer<MoCRenderState, MoCFilchLizardModel<MoCFilchLizardEntity>> {

    public MoCFilchLizardLootLayer(RenderLayerParent<MoCRenderState, MoCFilchLizardModel<MoCFilchLizardEntity>> parent) {
        super(parent);
    }

    /** Resolves the carried loot into the render state (called from the renderer's extractRenderState). */
    public static void extractLoot(MoCFilchLizardEntity lizard, LootRenderState state, ItemModelResolver itemModelResolver) {
        itemModelResolver.updateForLiving(state.loot, lizard.getCarriedLoot(), ItemDisplayContext.THIRD_PERSON_LEFT_HAND, lizard);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int packedLight, MoCRenderState state,
                       float yRot, float xRot) {
        if (!(state instanceof LootRenderState lootState) || lootState.loot.isEmpty()) {
            return;
        }
        poseStack.pushPose();
        // Original transforms, applied in model space.
        poseStack.rotateDegrees(Axis.YP, 90.0F);
        poseStack.rotateDegrees(Axis.ZP, 180.0F);
        poseStack.rotateDegrees(Axis.ZP, 20.0F);
        poseStack.translate(-0.55F, -1.0F, -0.05F);
        lootState.loot.submit(poseStack, collector, packedLight, OverlayTexture.NO_OVERLAY, state.outlineColor);
        poseStack.popPose();
    }

    /** Filch Lizard render state: also carries the resolved loot item. */
    public static class LootRenderState extends MoCRenderState {
        public final ItemStackRenderState loot = new ItemStackRenderState();
    }
}
