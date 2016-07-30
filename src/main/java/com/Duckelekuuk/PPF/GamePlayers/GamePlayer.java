package com.Duckelekuuk.PPF.GamePlayers;

import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import com.Duckelekuuk.PPF.ScoreBoards.TransitionScoreBoard;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

@Getter
@Setter
public class GamePlayer {

    /* Personal details */
    private Player player;
//    private GamePlayerType rank;


    /* Game information */
    private int score;
    private boolean ingame;
    private TransitionScoreBoard scoreboard;
//    private boolean loaded;

    /* Personal stats */

    public GamePlayer(Player player) {
        this.player = player;
        this.ingame = true;
        this.score = 0;

        this.scoreboard = new TransitionScoreBoard(PixelPartyFrame.getPlugin(), this);
    }

    public void teleport(final Location location) {
        Bukkit.getServer().getScheduler().runTask(PixelPartyFrame.getPlugin(), new Runnable() {
            @Override
            public void run() {
                player.teleport(location);
            }
        });
    }


    public void kickPlayer(final String reason) {
        Bukkit.getServer().getScheduler().runTask(PixelPartyFrame.getPlugin(), new Runnable() {
            @Override
            public void run() {
                player.kickPlayer(Utils.rawPrefix() + Utils.color(reason));
            }
        });

    }
}
