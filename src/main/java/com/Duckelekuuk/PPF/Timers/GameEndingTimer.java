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

public class GameEndingTimer extends BukkitRunnable {

    private int time = 20;

    private ArrayList<GamePlayer> gamePlayers;

    public GameEndingTimer(ArrayList<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    @Override
    public void run() {
        if (time == 20) {
            sendTitle("Thank you all so much for playing!", "", 20, 40, 20);
        }

        if (time == 13) {
            sendTitle("The server is going to restart!", "You can join back after that", 20, 40, 20);
        }

        if (time == 6) {
            sendTitle("See you guys and girls soon!", "", 20, 40, 20);
        }

        if (time == 0) {
            this.cancel();
            PixelPartyFrame.getPlugin().getPixelPartyConstant().cleanup();
        }
        time--;
    }

    public void sendTitle(String title, String subTitle, int fadein, int duration, int fadeout) {
        for (GamePlayer gamePlayer : gamePlayers) {
            Titles.sendCompleteTitle(gamePlayer.getPlayer(), title, subTitle, fadein, duration, fadeout);
        }
    }
}
