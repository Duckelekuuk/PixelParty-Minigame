package com.Duckelekuuk.PPF.Timers;

import com.Duckelekuuk.PPF.GameFrame.Widgets.Titles;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;

/**
 * @AUTHOR Duco.
 * Description
 */
public class WarmUpTimer extends BukkitRunnable {

    int time = 30;
    private HashSet<GamePlayer> gamePlayers = PixelPartyFrame.getPlugin().getPixelPartyConstant().getGamePlayers();

    @Override
    public void run() {
        if (time % 10 == 0) {
            for (GamePlayer gamePlayer : gamePlayers) {
                Titles.sendCompleteTitle(gamePlayer.getPlayer(), "&a&l" + time, "&eSeconds until the game starts", 5, 30, 10);
            }
        }
        if (time <= 10 && time > 5) {
            for (GamePlayer gamePlayer : gamePlayers) {
                Titles.sendCompleteTitle(gamePlayer.getPlayer(), "&6&l" + time, "&eSeconds until the game starts", 5, 30, 10);
            }
        }
        if (time <= 5) {
            for (GamePlayer gamePlayer : gamePlayers) {
                Titles.sendCompleteTitle(gamePlayer.getPlayer(), "&c&l" + time, "&eSeconds until the game starts", 5, 12, 10);
            }
        }
        if (time == 0) {
            cancel();

            for (GamePlayer gamePlayer : gamePlayers) {
                Titles.sendCompleteTitle(gamePlayer.getPlayer(), "&eThe game has begun", "&aMay the best player win", 20, 30, 10);
            }
        }


        for (GamePlayer gamePlayer : gamePlayers) {
            Titles.sendActionBar(gamePlayer.getPlayer(), "&e&lThe game will begin in " + time +  " seconds");
        }
        time--;
    }
}