package com.Duckelekuuk.PPF.GamePlayers;

import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public enum GamePlayerType {

    PLAYER(0, ""),
    SPECTATOR(0, Utils.color("&7SPECTATOR")),
    BUILDER(1, Utils.color("&2&lBUILD TEAM&2")),
    ADMIN(2, Utils.color("&c&lADMIN&c"));

    int permissionLevel;
    String prefix;


    GamePlayerType(int permissionLevel, String prefix) {
        this.permissionLevel = permissionLevel;
        this.prefix = prefix;
    }
}