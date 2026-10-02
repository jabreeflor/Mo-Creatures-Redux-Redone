package io.github.mestretramador.mocreaturesrr.network;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record OpenPlayerInventoryPayload() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<OpenPlayerInventoryPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "open_player_inventory"));

    public static final StreamCodec<io.netty.buffer.ByteBuf, OpenPlayerInventoryPayload> STREAM_CODEC =
            StreamCodec.unit(new OpenPlayerInventoryPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}