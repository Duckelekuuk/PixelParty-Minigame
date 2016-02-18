package com.Duckelekuuk.PixelPartyFrame.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @AUTHOR Duco.
 * Description
 */
public class CommandManager implements CommandExecutor {

    private static HashSet<GameCommand> commandList = new HashSet<GameCommand>();


    public static GameCommand getCommand(String label) {
        for (GameCommand gameCommand : commandList) {
            if (gameCommand.getLabel().equalsIgnoreCase(label)) {
                return gameCommand;
            }
        }
        return null;
    }

    public CommandManager() {
        commandList = new HashSet<GameCommand>();
        commandList.add(new EscapeHelp());
    }

    public static HashSet<GameCommand> getCommandList() {
        return commandList;
    }


    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("Escape")) {
            if (strings.length < 1) {
                commandSender.sendMessage(ChatAPI.prefix + ChatColor.RED + "Please specify you arguments.");
                new EscapeHelp().executer(commandSender, strings);
                return true;
            }

            GameCommand gameCommand = getCommand(strings[0]);

            if (gameCommand == null) {
                commandSender.sendMessage(ChatAPI.prefix + ChatColor.RED + "Please enter a valid.");
                new EscapeHelp().executer(commandSender, strings);
                return true;
            }
            else {
                if (!(commandSender.hasPermission(gameCommand.getPermision()))) {
                    commandSender.sendMessage(ChatAPI.noPerm);
                    return true;
                }
                gameCommand.executer(commandSender, strings);
                return true;
            }
        }
        return true;
    }
}
