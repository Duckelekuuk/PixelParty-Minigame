package com.Duckelekuuk.PPF.Listeners.Player;

import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PlayerJoinListener implements Listener {

    private PixelPartyFrame plugin;

    public PlayerJoinListener(PixelPartyFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        GamePlayer gamePlayer = plugin.getGamePlayerManager().getGamePlayer(event.getPlayer());
        gamePlayer.getPlayer().teleport(plugin.getPixelPartyConstant().getLobbyLocation());

        switch (plugin.getPixelPartyConstant().getPixelPartyState()) {
            case LOBBY:
                event.getPlayer().setScoreboard(plugin.getPixelPartyConstant().getScoreBoard_Lobby().getScoreboard());
                break;

            case IN_GAME:
                event.getPlayer().setScoreboard(plugin.getPixelPartyConstant().getScoreBoard_Spectator());
                //SET PLAYER SPECTATOR
                break;

            case TRANSITION:
                event.getPlayer().setScoreboard(gamePlayer.getScoreboard().getScoreboard());
                break;
        }
    }
}