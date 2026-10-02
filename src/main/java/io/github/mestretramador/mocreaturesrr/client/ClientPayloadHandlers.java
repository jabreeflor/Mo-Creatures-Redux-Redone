package io.github.mestretramador.mocreaturesrr.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;

/**
 * Client-side bodies of the play-to-client payload handlers. Kept out of {@code ModNetworking} so the
 * common networking class never references client-only classes on a dedicated server.
 */
public final class ClientPayloadHandlers {

    private ClientPayloadHandlers() {
    }

    public static void openNamingScreen(int entityId) {
        Minecraft.getInstance().gui.setScreen(new MoCNamingScreen(entityId));
    }

    public static void openPlayerInventory() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.gui.setScreen(new InventoryScreen(mc.player));
        }
    }
}
