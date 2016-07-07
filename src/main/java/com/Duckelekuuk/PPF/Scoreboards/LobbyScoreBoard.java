package com.Duckelekuuk.PPF.ScoreBoards;

import com.Duckelekuuk.PPF.GameFrame.Widgets.ScoreboardBuilder;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 **/

/** SCOREBOARD LAYOUT **/
/** [    Pixelparty    ] **/
/** [TimeLeft         8] **/
/** [30               7] **/
/** [                 6] **/
/** [NextGame:        5] **/
/** [Cooney Island    4] **/
/** [                 3] **/
/** [Players          2] **/

/** [3/12             1] **/


@Getter
public class LobbyScoreBoard {

    private PixelPartyFrame plugin;
    private Scoreboard scoreboard;

    public LobbyScoreBoard(PixelPartyFrame plugin) {
        this.plugin = plugin;
        this.scoreboard =
                new ScoreboardBuilder("Lobby", DisplaySlot.SIDEBAR, "&6&lPixel&e&lParty")
                        .addScore("blank_0", " ", ChatColor.AQUA.toString(), "", 9)
                        .addScore("TimeLeft", "TimeLeft: ", ChatColor.BLACK + "", "", 8)
                        .addScore("time", ChatColor.GREEN + "30", ChatColor.BLUE.toString(), "", 7)
                        .addScore("blank_1", "", ChatColor.BOLD.toString(), "", 6)
                        .addScore("NextGame", ChatColor.WHITE + "NextGame: ", ChatColor.DARK_AQUA.toString(), "", 5)
                        .addScore("game", ChatColor.GREEN + (plugin.getGameManager().getNextGame() == null ? "No game" : plugin.getGameManager().getNextGame().getGameName()), ChatColor.DARK_BLUE.toString(), "", 4)
                        .addScore("blank_2", " ", ChatColor.DARK_GRAY.toString(), "", 3)
                        .addScore("Players", ChatColor.WHITE + "Players: ", ChatColor.DARK_GREEN.toString(), "", 2)
                        .addScore("player", ChatColor.GREEN + (plugin.getPixelPartyConstant().getPlayers().size() + "/" + plugin.getPixelPartyConstant().getMaxPlayers()), ChatColor.DARK_PURPLE.toString(), "", 1)
                        .getScoreboard();

    }

    public void updateScoreboard(int timeLeft) {
        scoreboard.getTeam("time").setPrefix("" + ChatColor.GREEN + timeLeft);
        scoreboard.getTeam("game").setPrefix(plugin.getGameManager().getNextGame() == null ? ChatColor.RED + "No game" : ChatColor.GREEN + plugin.getGameManager().getNextGame().getGameName());
        scoreboard.getTeam("player").setPrefix(ChatColor.GREEN + (plugin.getPixelPartyConstant().getPlayers().size() + "/" + plugin.getServer().getMaxPlayers()));
    }
}
