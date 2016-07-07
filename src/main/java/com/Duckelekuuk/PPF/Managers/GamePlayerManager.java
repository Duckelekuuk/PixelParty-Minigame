package com.Duckelekuuk.PPF.Managers;

import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashSet;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

@Getter
public class GamePlayerManager {

    private PixelPartyFrame plugin;
    private HashSet<GamePlayer> gamePlayers;

    public GamePlayerManager(PixelPartyFrame plugin) {
        this.plugin = plugin;
        this.gamePlayers = new HashSet<>();
    }

    public GamePlayer getGamePlayer(Player player) {
        for (GamePlayer gameplayer : gamePlayers) {
            if (gameplayer.getPlayer() == player) {
                return gameplayer;
            }
        }
        GamePlayer gamePlayer = new GamePlayer(player);
        gamePlayers.add(gamePlayer);
        PixelPartyFrame.getPlugin().getPixelPartyConstant().getPlayers().add(gamePlayer);
        return gamePlayer;
    }

    public static GamePlayerManager getInstance() {
        return PixelPartyFrame.getPlugin().getGamePlayerManager();
    }
}