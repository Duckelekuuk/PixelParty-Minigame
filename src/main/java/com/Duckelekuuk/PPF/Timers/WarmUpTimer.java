package com.Duckelekuuk.PPF.Timers;

import com.Duckelekuuk.PPF.GameFrame.Widgets.Titles;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class WarmUpTimer extends BukkitRunnable {

    private int time = 10;
    private ArrayList<GamePlayer> gamePlayers;

    public WarmUpTimer(ArrayList<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    @Override
    public void run() {
        if (time == 10) {
            PixelPartyFrame.getPlugin().getGameManager().getCurrentGame().getGame().divide();
        }
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
            this.cancel();
            //TODO START NEW TIMER
            PixelPartyFrame.getPlugin().getGameManager().getCurrentGame().getGame().startGame();

            for (GamePlayer gamePlayer : gamePlayers) {
                Titles.sendCompleteTitle(gamePlayer.getPlayer(), "&eThe game has begun", "&aMay the best player win", 20, 30, 10);
            }
        }


        for (GamePlayer gamePlayer : gamePlayers) {
//            Titles.sendActionBar(gamePlayer.getPlayer(), "&e&lThe game will begin in " + time + " seconds");
        }
        time--;
    }
}