package com.Duckelekuuk.PPF.Timers;

import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @AUTHOR Duco.
 * Description
 */
public class TransitionTimer extends BukkitRunnable {

    int time = 30;
    @Override
    public void run() {
        //TODO: RANDOM GG MESSAGE
        PixelPartyFrame.getPlugin().getGameManager().switchGame();
        time--;
    }
}