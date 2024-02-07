package io.felipeandrade.passinggas.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayChannelHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class JumpPacketHandler implements PlayChannelHandler {

    @Override
    public void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        if (player.getEntityWorld().isClient) {
            double jumpHeight = 0.5; // Adjust the jump height as needed
            player.setVelocity(player.getVelocity().x, jumpHeight, player.getVelocity().z);
        }
    }

    // Send the custom jump packet to the server
    public static void sendJumpPacket(PlayerEntity player) {
        ServerPlayNetworking.send((ServerPlayerEntity) player, new Identifier("passinggas", "jump"), new PacketByteBuf(Unpooled.buffer()));
    }
}
