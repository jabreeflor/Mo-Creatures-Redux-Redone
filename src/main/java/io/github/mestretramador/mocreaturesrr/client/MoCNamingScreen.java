package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.network.SetPetNamePayload;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.renderpearl.api.textures.FilterMode;
import com.mojang.renderpearl.api.textures.GpuSampler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

/**
 * Reusable naming prompt for ANY tamed creature — not horse-specific.
 * Opened client-side by ModNetworking in response to OpenNamingScreenPayload;
 * sends SetPetNamePayload back to the server when the player confirms.
 */
public class MoCNamingScreen extends Screen {

    private int previousBlur;
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/gui/mocname.png");
    private static final int TEX_WIDTH = 256;
    private static final int TEX_HEIGHT = 181;

    private final int entityId;
    private EditBox nameBox;

    public MoCNamingScreen(int entityId) {
        super(Component.literal("Name your pet"));
        this.entityId = entityId;
    }

    @Override
    protected void init() {
        var options = Minecraft.getInstance().options;
        this.previousBlur = options.menuBackgroundBlurriness().get();
        options.menuBackgroundBlurriness().set(0);
        int left = (this.width - TEX_WIDTH) / 2;
        int top = (this.height - TEX_HEIGHT) / 2;

        this.nameBox = new CenteredEditBox(this.font, this.width / 2, top + 85, 200, 20, Component.literal("Name"));
        this.nameBox.setMaxLength(32);
        this.nameBox.setBordered(false);
        this.addRenderableWidget(this.nameBox);
        this.setInitialFocus(this.nameBox);

        this.addRenderableWidget(Button.builder(Component.literal("Done"), button -> this.confirmName())
                .bounds(left + 78, top + 155, 100, 20)
                .build());
    }

    private void confirmName() {
        String name = this.nameBox.getValue();
        if (!name.isBlank()) {
            ClientPacketDistributor.sendToServer(new SetPetNamePayload(this.entityId, name));
        }
        this.onClose();
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().options.menuBackgroundBlurriness().set(this.previousBlur);
        super.onClose();
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        // The menu background is now extracted separately (Screen#extractBackground) before this.
        int left = (this.width - TEX_WIDTH) / 2;
        int top = (this.height - TEX_HEIGHT) / 2;
        // Old code forced nearest filtering on the texture (setFilter(false, false)); 26.x picks the
        // sampler per draw, so blit with an explicit nearest/repeat sampler instead.
        // The old setShaderColor(1.15, 1.15, 1.08) brightening cannot be expressed (tint colours cap
        // at 1.0); it compensated for the background being drawn twice over the texture in 1.21.1,
        // which no longer happens, so the texture is drawn untinted.
        AbstractTexture texture = Minecraft.getInstance().getTextureManager().getTexture(TEXTURE);
        GpuSampler nearest = RenderSystem.getSamplerCache().getRepeat(FilterMode.NEAREST);
        graphics.blit(texture.getTextureView(), nearest,
                left, top, left + TEX_WIDTH, top + TEX_HEIGHT,
                0.0F, TEX_WIDTH / 256.0F, 0.0F, TEX_HEIGHT / 256.0F);
        graphics.centeredText(this.font, "Choose your Pet's name:", this.width / 2, top + 30, 0xFFFFFFFF);
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        if (event.isConfirmation()) { // Enter / numpad Enter
            this.confirmName();
            return true;
        }
        return super.keyPressed(event);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}