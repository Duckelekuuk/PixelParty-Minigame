package com.Duckelekuuk.PPF.GameFrame.Core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Duco on 7-2-2016.
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
