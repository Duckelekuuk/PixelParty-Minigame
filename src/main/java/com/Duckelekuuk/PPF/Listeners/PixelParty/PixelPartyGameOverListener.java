package com.Duckelekuuk.PPF.Listeners.PixelParty;

import com.Duckelekuuk.PPF.Events.GameOverEvent;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import com.Duckelekuuk.PPF.Utils.PixelPartyState;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PixelPartyGameOverListener implements Listener {

    private PixelPartyFrame plugin;

    public PixelPartyGameOverListener(PixelPartyFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onGameOver(GameOverEvent event) {
        if (plugin.getGameManager().getNextGame() == null) {
            plugin.getGameManager().pickRandomGame();
        }

        for (GamePlayer gamePlayer : event.getWinners()) {
            gamePlayer.setScore(gamePlayer.getScore() + 1);
            gamePlayer.getScoreboard().updateScoreboard();
        }
        if (plugin.getGameManager().wasFinalGame()) {
            plugin.getPixelPartyConstant().setPixelPartyState(PixelPartyState.CLEANING_UP);
            return;
        }

        for (GamePlayer gamePlayer : PixelPartyFrame.getPlugin().getPixelPartyConstant().getPlayers()) {
            gamePlayer.getPlayer().setScoreboard(null);
        }

        plugin.getPixelPartyConstant().setPixelPartyState(PixelPartyState.TRANSITION);
    }
}
