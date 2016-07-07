package com.Duckelekuuk.PPF.Execeptions;

import com.Duckelekuuk.PPF.GameFrame.PPCore;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class GameDuplicationExeption extends Exception {

    public GameDuplicationExeption(PPCore ppCore) {
        super("Game: " + ppCore.getGameName() + " is duplicated.");
    }
}