package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.network.DescendInputPayload;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public class ModKeyMappings {

    /** Translation key: key.category.mocreaturesrr.main */
    public static final KeyMapping.Category CATEGORY =
            new KeyMapping.Category(Identifier.fromNamespaceAndPath("mocreaturesrr", "main"));

    public static final KeyMapping DESCEND = new KeyMapping(
            "key.mocreaturesrr.descend",
            InputConstants.Type.KEYBOARD,
            InputConstants.KEY_Z,
            CATEGORY
    );

    public static void register(RegisterKeyMappingsEvent event) {
        event.registerCategory(CATEGORY);
        event.register(DESCEND);
    }

    @EventBusSubscriber(modid = "mocreaturesrr", value = Dist.CLIENT)
    public static class TickHandler {
        private static boolean wasDescendPressed = false;
        private static boolean wasAscendPressed = false;

        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Post event) {
            boolean descendPressed = DESCEND.isDown();
            if (descendPressed != wasDescendPressed) {
                wasDescendPressed = descendPressed;
                if (Minecraft.getInstance().player != null) {
                    ClientPacketDistributor.sendToServer(new io.github.mestretramador.mocreaturesrr.network.DescendInputPayload(descendPressed));
                }
            }

            boolean ascendPressed = Minecraft.getInstance().options.keyJump.isDown();
            if (ascendPressed != wasAscendPressed) {
                wasAscendPressed = ascendPressed;
                if (Minecraft.getInstance().player != null) {
                    ClientPacketDistributor.sendToServer(new io.github.mestretramador.mocreaturesrr.network.AscendInputPayload(ascendPressed));
                }
            }

            // WASD is NOT sent separately anymore — vanilla's own movement
            // packet handling already feeds the rider's real forward/strafe
            // into the mount's travel() call once it has a controlling
            // passenger, same as it does for the horse.
        }
    }
}