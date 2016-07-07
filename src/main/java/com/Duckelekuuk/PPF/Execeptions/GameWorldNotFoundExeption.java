package com.Duckelekuuk.PPF.Execeptions;

import com.Duckelekuuk.PPF.GameFrame.Core.Game;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class GameWorldNotFoundExeption extends Exception {

    public GameWorldNotFoundExeption(Game game) {
        super("Game: " + game.getName() + "`s world does not exist. Game removed from list.");
    }
}