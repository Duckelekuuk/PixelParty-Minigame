package com.Duckelekuuk.PPF.Commands;

import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.command.CommandSender;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */
public class PPHelp extends PPCommand {

    private PixelPartyFrame plugin;

    public PPHelp(PixelPartyFrame plugin) {
        super(true, new String[]{"help", "h"}, new String[]{});
        this.plugin = plugin;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        sender.sendMessage("");
        sender.sendMessage(Utils.color("&7------------ &6&lPixel&e&lParty &7 ------------"));
        sender.sendMessage("");

        for (PPCommand command : plugin.getCommandManager().getCommands()) {
            if (sender.hasPermission(command.getPermission())) {
                String args_required = "";
                for (String str : command.getArgs()) {
                    args_required = args_required + str + " ";
                }
                sender.sendMessage(Utils.color("/PixelParty " + command.getLabels()[0] + " " + args_required.trim()));
            }
        }

        sender.sendMessage("");
        sender.sendMessage(Utils.color("&7-------------------------------------"));
        sender.sendMessage("");
    }
}
