package com.Duckelekuuk.PPF.Commands;

import com.Duckelekuuk.PPF.GameFrame.PPCore;
import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import com.Duckelekuuk.PPF.GameFrame.Widgets.JsonMessage;
import com.Duckelekuuk.PPF.Messages.MessageBase;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PPForceNextGame extends PPCommand {

    private PixelPartyFrame plugin;
    private JsonMessage message;

    public PPForceNextGame(PixelPartyFrame plugin) {
        super(false, new String[]{"ForceNext", "ForceNextGame", "f", "force"}, new String[]{"<game>"});
        this.plugin = plugin;

        setupMessage();
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {

        PPCore ppCore = plugin.getGameManager().getGame(args[1]);

        if (ppCore == null) {
            if (sender instanceof Player) {
                sender.sendMessage(MessageBase.header);
                sender.sendMessage("");
                message.send((Player) sender);
                sender.sendMessage("");
                sender.sendMessage(MessageBase.footer);
                return;
            }

            Utils.sendMessage(sender, "&cThat game does not exist!", "&aType /pixelparty list!");
            return;
        }

        if (plugin.getGameManager().getCurrentGame() == ppCore || plugin.getGameManager().getNextGame() == ppCore) {
            Utils.sendMessage(sender, "&cIt is already the current or next game!");
            return;
        }

        plugin.getGameManager().setNextGame(ppCore);
        Utils.sendMessage(sender, "&aNext game is going to be &6" + ppCore.getGameName());
    }

    public void setupMessage() {
        this.message = new JsonMessage("&cThat game does not exist!\n")
                .then("&cClick ")
                .then("&b&l[HERE]")
                .addCommand("/pp list")
                .then("&c to see all the messages");
    }
}
