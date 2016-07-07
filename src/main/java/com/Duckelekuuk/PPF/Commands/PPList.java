package com.Duckelekuuk.PPF.Commands;

import com.Duckelekuuk.PPF.GameFrame.PPCore;
import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import com.Duckelekuuk.PPF.GameFrame.Widgets.JsonMessage;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PPList extends PPCommand {

    private PixelPartyFrame plugin;

    public PPList(PixelPartyFrame plugin) {
        super(false, new String[]{"list", "gamelist", "l"}, new String[]{});
        this.plugin = plugin;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        sender.sendMessage("");
        sender.sendMessage(Utils.color("&7&m----------&6&l Pixel&e&lParty &6games &7&m-----------"));
        sender.sendMessage("");
        sender.sendMessage(Utils.color("&7&m-----------&a&l Current game &7&m-----------"));
        sender.sendMessage("");
        sender.sendMessage(Utils.color("&7&l\u00BB &6" + (plugin.getGameManager().getCurrentGame() == null ? "&cNo current game!" : plugin.getGameManager().getCurrentGame().getGameName())));
        sender.sendMessage("");
        sender.sendMessage(Utils.color("&7&m-------------&3&l Next game &7&m-------------"));
        sender.sendMessage("");
        sender.sendMessage(Utils.color("&7&l\u00BB &6" + (plugin.getGameManager().getNextGame() == null ? "&cLast game!" : plugin.getGameManager().getNextGame().getGameName())));
        sender.sendMessage("");
        sender.sendMessage(Utils.color("&7&m-------------&c&l All games &7&m-------------"));
        sender.sendMessage("");

        for (PPCore core : plugin.getGameManager().getGames()) {
            if (sender instanceof Player) {
                new JsonMessage("&7&l\u00BB &6" + core.getGameName()).addCommand("/pp f " + core.getGameName()).addToolTip("&bClick to force this game").send((Player) sender);
            } else {
                sender.sendMessage(Utils.color("&7&l\u00BB &6" + core.getGameName()));
            }
        }

        sender.sendMessage("");
        sender.sendMessage(Utils.color("&7&m-------------------------------------"));
//        sender.sendMessage(Utils.color("&7&m[][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
//        sender.sendMessage(Utils.color("&7&m======================================================"));
//        sender.sendMessage(Utils.color("&7&m:::::::::::::::::::::::::::::::::::::::::::::::::::::::"));
//        sender.sendMessage(Utils.color("&7&m^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"));
//        sender.sendMessage(Utils.color("&7&m[IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII]"));
//        sender.sendMessage(Utils.color("&7&m+++++++++++++++++++++++++++++++++++++++++++++++++++"));
//        sender.sendMessage(Utils.color("&7&m[]-[]-[]-[]-[]-[]-[]-[]-[]-[]-[]-[]-[]-[]"));
//        sender.sendMessage(Utils.color("&7&m=+=+=+=+=+=+=+=+=+=+=+=+="));
//        sender.sendMessage(Utils.color("&7&l<X><X><X><X><X><X><X><X><X><X><X><X><X><X><X>"));
//        sender.sendMessage(Utils.color("&7&m&l<>[]<>[]<>[]<>[]<>[]<>[]<>[]<>[]<>[]<>"));
//        sender.sendMessage(Utils.color("&e\u2730\u2730\u2730\u2730\u2730\u2730\u2730\u2730\u2730"));
        sender.sendMessage("");
    }
}
