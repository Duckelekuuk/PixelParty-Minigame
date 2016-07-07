package com.Duckelekuuk.PPF.Timers;

import com.Duckelekuuk.PPF.GameFrame.Core.Game;
import com.Duckelekuuk.PPF.Managers.GameManager;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class GameTimer extends BukkitRunnable {

    private Game game = GameManager.getInstance().getCurrentGame().getGame();

    @Override
    public void run() {
        if (game.getEndExecutor().canFinish()) {
            game.endGame();
        }
        if (game.getGameTimerTickExecutor() != null) {
            game.getGameTimerTickExecutor().gameTick();
        }
        //TODO: UPDATE SCOREBOARD
    }
}