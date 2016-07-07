package com.Duckelekuuk.PPF.GameFrame.Widgets;

import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class TabBar {

    private static PacketPlayOutPlayerListHeaderFooter packet;

    private static IChatBaseComponent setPlayerListHeader(String header) {
        try {
            IChatBaseComponent baseComponent = IChatBaseComponent.ChatSerializer.a("{'text':'" + Utils.color(header) + "'}");
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, baseComponent);
            headerField.setAccessible(headerField.isAccessible());
            return baseComponent;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static IChatBaseComponent setPlayerListFooter(String footer) {
        try {
            IChatBaseComponent baseComponent = IChatBaseComponent.ChatSerializer.a("{'text':'" + Utils.color(footer) + "'}");
            Field headerField = packet.getClass().getDeclaredField("b");
            headerField.setAccessible(true);
            headerField.set(packet, baseComponent);
            headerField.setAccessible(headerField.isAccessible());
            return baseComponent;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sendTab(Player player, String header, String footer) {
        packet = new PacketPlayOutPlayerListHeaderFooter();
        setPlayerListHeader(header);
        setPlayerListFooter(footer);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
