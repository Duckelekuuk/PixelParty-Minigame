package com.Duckelekuuk.PPF.GameFrame.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class Utils {

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String remakeTime(int time) {
        int format = time % 3600;
        return format / 60 + " : " + format % 60;
    }

    public static String rawPrefix() {
        return color("&6&lPixel&e&lParty&r");
    }

    public static String prefix() {
        return color("&6&lPixel&e&lParty &6&l\u00BB &7");
    }

    public static void broadcast(String message) {
        message = prefix() + color(message);
        Bukkit.getServer().broadcastMessage(message);
    }

    public static void broadcast(String message, Player... players) {
        message = prefix() + color(message);

        for (Player player : players) {
            player.sendMessage(message);
        }
    }

    public static void sendMessage(CommandSender sender, String... messages) {
        sender.sendMessage("");
        sender.sendMessage(Utils.color("&7------------ &6&lPixel&e&lParty &7------------"));
        sender.sendMessage("");
        for (String message : messages) {
            sender.sendMessage(Utils.color("&7\u00BB " + message));
        }
        sender.sendMessage("");
        sender.sendMessage(Utils.color("&7-------------------------------------"));
        sender.sendMessage("");
    }

    public static String prefixMessage(String message) {
        return prefix() + color(message);
    }
}
