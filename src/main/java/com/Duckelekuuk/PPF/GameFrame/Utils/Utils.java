package com.Duckelekuuk.PPF.GameFrame.Utils;

import com.Duckelekuuk.PPF.Commands.PPCommand;
import com.avaje.ebeaninternal.server.cluster.mcast.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Duco on 22-2-2016.
 */
public class Utils {

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String remakeTime(int time) {
        int format = time%3600;
        return format/60 + " : " + format%60;
    }

    public static String prefix() {
        return Utils.color("&6&lPixel&e&lParty &6&l\u00BB &7");
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
        sender.sendMessage(Utils.color("&7------------ &6&lPixel&e&lParty &7 ------------"));
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
