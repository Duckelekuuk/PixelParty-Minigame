package com.Duckelekuuk.PPF.Managers;

import com.Duckelekuuk.PPF.Commands.PPCommand;
import com.Duckelekuuk.PPF.Commands.PPForceNextGame;
import com.Duckelekuuk.PPF.Commands.PPHelp;
import com.Duckelekuuk.PPF.Commands.PPList;
import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */
public class CommandManager implements CommandExecutor, TabCompleter {

    @Getter
    private HashSet<PPCommand> commands;
    private PixelPartyFrame plugin;

    public CommandManager(PixelPartyFrame plugin) {
        this.plugin = plugin;

        this.commands = new HashSet<>();
        commands.add(new PPHelp(plugin));
        commands.add(new PPList(plugin));
        commands.add(new PPForceNextGame(plugin));
    }

    public PPCommand getCommand(String label) {
        for (PPCommand command : commands) {
            for (String labels_found : command.getLabels()) {
                if (labels_found.equalsIgnoreCase(label)) {
                    return command;
                }
            }
        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(Utils.prefixMessage("Please specify you arguments."));
            getCommand("help").onCommand(sender, args);
            return true;
        }

        PPCommand ppCommand = getCommand(args[0]);

        if (args.length < ppCommand.getArgs().length) {
            sender.sendMessage(Utils.prefixMessage("Please specify you arguments."));
            getCommand("help").onCommand(sender, args);
            return true;
        }

        if (!(sender instanceof Player) && ppCommand.isPlayerOnly()) {
            sender.sendMessage(Utils.color("&cYou need to be a player to use this command!"));
            return true;
        }

        if (ppCommand == null) {
            sender.sendMessage(Utils.prefixMessage("That command is not recognised."));
            getCommand("help").onCommand(sender, args);
            return true;
        }

        if (!(sender.hasPermission(ppCommand.getPermission()))) {
            sender.sendMessage(Utils.color("&cYou dont have permission for that command."));
            return true;
        }

        ppCommand.onCommand(sender, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("pixelparty") || command.getName().equalsIgnoreCase("pparty") || command.getName().equalsIgnoreCase("pp")) {
            if (args.length == 1) {
                ArrayList<String> commands = new ArrayList<String>();

                if (!args[0].equals("")) {
                    for (PPCommand ppCommand : getCommands()) {
                        for (String commandLabel : ppCommand.getLabels()) {
                            if (commandLabel.toLowerCase().startsWith(args[0].toLowerCase())) {
                                commands.add(commandLabel);
                            }
                        }
                    }
                }

                else {
                    for (PPCommand ppCommand : getCommands()) {
                        for (String commandLabel : ppCommand.getLabels()) {
                            commands.add(commandLabel);
                        }
                    }
                }

                Collections.sort(commands);

                return commands;
            }
        }

        return null;
    }
}
