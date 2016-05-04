package com.Duckelekuuk.PPF.Managers;

import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.entity.Player;

import java.util.HashSet;

/**
 * @AUTHOR Duco.
 * Description
 */
public class GamePlayerManager {

    private HashSet<GamePlayer> gamePlayers;

    public GamePlayerManager() {
        this.gamePlayers = new HashSet<>();
    }

    public GamePlayer getGamePlayer(Player player) {
        for (GamePlayer gameplayer : gamePlayers) {
            if (gameplayer.getPlayer() == player) {
                return gameplayer;
            }
        }
        return null;
    }

    public static GamePlayerManager getInstance() {
        return PixelPartyFrame.getPlugin().getGamePlayerManager();
    }
}