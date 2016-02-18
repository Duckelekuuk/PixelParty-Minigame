package com.Duckelekuuk.PixelPartyFrame.game;

import com.Duckelekuuk.PixelPartyFrame.PPFLogger;
import lombok.NonNull;
import lombok.Setter;

import java.util.Iterator;

/**
 * @AUTHOR Duco.
 * Description
 */
public class PPGame {

    @Setter
    private PPFLogger logger;
    @Setter
    private String rawName;
    @Setter
    private String name;
    private Map<String, GamePlayer> players;
    private Map<String, GamePlayer> spectators;
    @NonNull
    private GameState currentstate = null; //State of the Game
    private Iterator stateIterator;
}
