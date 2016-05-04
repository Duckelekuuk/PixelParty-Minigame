package com.Duckelekuuk.PPF.GameFrame;

import com.Duckelekuuk.PPF.GameFrame.Core.Game;
import org.bukkit.plugin.Plugin;

/**
 * @AUTHOR Duco.
 * Description
 */
public interface PPCore {

    Game game = null;

    Game getGame();

    String getGameName();

    String getGamePrefix();

    Plugin getPlugin();

    void setup();
}
