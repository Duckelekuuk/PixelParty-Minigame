package com.Duckelekuuk.PPF.GameFrame;

import com.Duckelekuuk.PPF.GameFrame.Core.Game;
import org.bukkit.plugin.Plugin;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public interface PPCore {

    Game game = null;

    Game getGame();

    String getGameName();

    String getGamePrefix();

    Plugin getPlugin();

    void setup();
}
