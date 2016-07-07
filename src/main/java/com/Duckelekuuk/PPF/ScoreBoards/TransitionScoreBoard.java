package com.Duckelekuuk.PPF.ScoreBoards;

import com.Duckelekuuk.PPF.GameFrame.Widgets.ScoreboardBuilder;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

/**
 * @AUTHOR Duco.
 * Description
 **/

/** SCOREBOARD LAYOUT **/
/** [    Pixelparty    ] **/
/** [Username:        8] **/
/** [30               7] **/
/** [                 6] **/
/** [NextGame:        5] **/
/** [Cooney Island    4] **/
/** [                 3] **/
/** [Players          2] **/

/** [3/12             1] **/


@Getter
public class TransitionScoreBoard {

    private PixelPartyFrame plugin;
    private Scoreboard scoreboard;
    private GamePlayer gamePlayer;

    public TransitionScoreBoard(PixelPartyFrame plugin, GamePlayer gamePlayer) {
        this.plugin = plugin;
        this.gamePlayer = gamePlayer;

        this.scoreboard =
                new ScoreboardBuilder("Lobby", DisplaySlot.SIDEBAR, "&6&lPixel&e&lParty")
                        .addScore("blank_0", " ", ChatColor.AQUA.toString(), "", 9)
                        .addScore("username", "" + ChatColor.GRAY + ChatColor.BOLD + "Username: ", ChatColor.BLACK + "", "", 8)
                        .addScore("playername", ChatColor.GREEN + gamePlayer.getPlayer().getDisplayName(), ChatColor.BLUE.toString(), "", 7)
                        .addScore("blank_1", "", ChatColor.BOLD.toString(), "", 6)
                        .addScore("NextGame", "NextGame: ", ChatColor.DARK_AQUA.toString(), "", 5)
                        .addScore("game", ChatColor.GREEN + (plugin.getGameManager().getNextGame() == null ? "No game" : plugin.getGameManager().getNextGame().getGameName()), ChatColor.DARK_BLUE.toString(), "", 4)
                        .addScore("blank_2", " ", ChatColor.DARK_GRAY.toString(), "", 3)
                        .addScore("Wins", "Players: ", ChatColor.DARK_GREEN.toString(), "", 2)
                        .addScore("wins", ChatColor.GREEN + "" +  gamePlayer.getScore(), ChatColor.DARK_PURPLE.toString(), "", 1)
                        .getScoreboard();

    }

    public void updateScoreboard() {
        scoreboard.getTeam("game").setPrefix(plugin.getGameManager().getNextGame() == null ? ChatColor.RED + "No game" : ChatColor.GREEN + plugin.getGameManager().getNextGame().getGameName());
        scoreboard.getTeam("wins").setPrefix(gamePlayer.getScore() + "");
    }
}
