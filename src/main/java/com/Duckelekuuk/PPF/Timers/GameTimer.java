package com.Duckelekuuk.PPF.Timers;

import com.Duckelekuuk.PPF.GameFrame.Core.Game;
import com.Duckelekuuk.PPF.Managers.GameManager;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @AUTHOR Duco.
 * Description
 */
public class GameTimer extends BukkitRunnable {

    Game game = GameManager.getInstance().getCurrentGame().getGame();

    @Override
    public void run() {
        game.getGameTimerTick();
    }
}