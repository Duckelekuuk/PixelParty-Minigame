package com.Duckelekuuk.PPF.GamePlayers;

import lombok.Getter;
import org.bukkit.entity.Player;

/**
 * @AUTHOR Duco.
 * Description
 */
@Getter
public class GamePlayer {

    /* Personal details */
    private Player player;
    private GamePlayerType rank;


    /* Game information */
    private int score;
    private boolean ingame;

    /* Personal stats */

    public GamePlayer(Player player) {
        this.player = player;
    }
}
