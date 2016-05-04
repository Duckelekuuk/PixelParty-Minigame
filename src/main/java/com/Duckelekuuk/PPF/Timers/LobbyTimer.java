package com.Duckelekuuk.PPF.Timers;

import com.Duckelekuuk.PPF.Managers.GameManager;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @AUTHOR Duco.
 * Description
 */
public class LobbyTimer extends BukkitRunnable {

    int time = 30;

    @Override
    public void run() {
        if (GameManager.getInstance().getNextGame() == null) {
            GameManager.getInstance().pickRandomGame();
            //TODO: SETSCOREBOARD NEXT GAME
        }

        if (!GameManager.getInstance().getPixelPartyConstant().canStart()) {
            time = 30;
            return;
        }

        time--;
        //TODO: SCOREBOARD UPDATE
    }
}
