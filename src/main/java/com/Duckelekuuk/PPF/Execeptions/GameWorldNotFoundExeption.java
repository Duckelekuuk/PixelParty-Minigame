package com.Duckelekuuk.PPF.Execeptions;

import com.Duckelekuuk.PPF.GameFrame.Core.Game;

/**
 * @AUTHOR Duco.
 * Description
 */
public class GameWorldNotFoundExeption extends Exception {

    public GameWorldNotFoundExeption(Game game)
    {
        super("Game: " + game.getName() + "`s world does not exist. Game removed from list.");
    }
}