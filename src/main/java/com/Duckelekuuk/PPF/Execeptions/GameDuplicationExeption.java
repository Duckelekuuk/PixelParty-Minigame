package com.Duckelekuuk.PPF.Execeptions;

import com.Duckelekuuk.PPF.GameFrame.PPCore;

/**
 * @AUTHOR Duco.
 * Description
 */
public class GameDuplicationExeption extends Exception {

    public GameDuplicationExeption(PPCore ppCore)
    {
        super("Game: " + ppCore.getGameName() + " is duplicated.");
    }
}