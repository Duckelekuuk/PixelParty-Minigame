package com.Duckelekuuk.PPF.Timers;

import com.Duckelekuuk.PPF.GameFrame.Widgets.Titles;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.Managers.GameManager;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import com.Duckelekuuk.PPF.Utils.PixelPartyState;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class LobbyTimer extends BukkitRunnable {

    private int time = 10;

    @Override
    public void run() {
        if (GameManager.getInstance().getCurrentGame() == null) {
            if (GameManager.getInstance().getNextGame() == null) {
                GameManager.getInstance().pickRandomGame();
            }
            if (!PixelPartyFrame.getPlugin().getGameManager().isLoading()) {
                GameManager.getInstance().switchGame();
            }
        }

        time--;
        if (!PixelPartyFrame.getPlugin().getPixelPartyConstant().canStart()) {
            time = 10;
        }

        if (time == 0) {
            this.cancel();
            PixelPartyFrame.getPlugin().getPixelPartyConstant().setPixelPartyState(PixelPartyState.IN_GAME);
            for (GamePlayer gamePlayer : PixelPartyFrame.getPlugin().getPixelPartyConstant().getPlayers()) {
                gamePlayer.teleport(PixelPartyFrame.getPlugin().getGameManager().getCurrentGame().getGame().getSpawnLocation());
                Titles.sendActionBar(gamePlayer.getPlayer(), "&a&bYou were teleported to the gameworld!");
            }
            return;
        }

        PixelPartyFrame.getPlugin().getPixelPartyConstant().getScoreBoard_Lobby().updateScoreboard(time);
    }
}
