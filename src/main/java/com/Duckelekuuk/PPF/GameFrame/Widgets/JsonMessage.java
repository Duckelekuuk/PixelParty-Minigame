package com.Duckelekuuk.PPF.GameFrame.Widgets;

import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class JsonMessage {

    private String message;

    public JsonMessage(String message) {
        this.message = "[\"\", { \"text\": \"" + Utils.color(message) + "\"";
    }

    public JsonMessage then(String message) {
        this.message = this.message + "}, { \"text\":\"" + Utils.color(message) + "\"";
        return this;
    }

    public JsonMessage addCommand(String command) {
        this.message = this.message + ", \"clickEvent\": {\"action\":\"run_command\",\"value\":\"" + command + "\"}";
        return this;
    }

    public JsonMessage addSuggestion(String command) {
        this.message = this.message + ", \"clickEvent\": {\"action\":\"suggest_command\",\"value\":\"" + command + "\"}";
        return this;
    }

    public JsonMessage addURL(String url) {
        this.message = this.message + ", \"clickEvent\": {\"action\":\"open_url\",\"value\":\"" + url + "\"}";
        return this;
    }

    public JsonMessage addToolTip(String... text) {
        String message = "";
        for (int i = 0; i < text.length; i++) {
            if (i == text.length - 1) {
                message = message + text[i];
                continue;
            }
            message = message + text[i] + "\n";
        }


        this.message = this.message + ", \"hoverEvent\": {\"action\":\"show_text\",\"value\":\"" + Utils.color(message) + "\"}";
        return this;
    }

    public void broadcast() {
        IChatBaseComponent comp = new IChatBaseComponent.ChatSerializer().a(this.message + "}]");
        PacketPlayOutChat packet = new PacketPlayOutChat(comp);

        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public void send(Player... player) {
        IChatBaseComponent comp = new IChatBaseComponent.ChatSerializer().a(this.message + "}]");
        PacketPlayOutChat packet = new PacketPlayOutChat(comp);

        for (Player players : player) {
            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public String getMessage() {
        return message + "}]";
    }
}