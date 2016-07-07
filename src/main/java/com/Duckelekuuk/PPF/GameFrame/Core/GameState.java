package com.Duckelekuuk.PPF.GameFrame.Core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

@Getter
@AllArgsConstructor
public class GameState {

    private String name;
    private int ID;
    private Game game;

    public GameState() {
        game.getGameStates().add(this);
    }
}
