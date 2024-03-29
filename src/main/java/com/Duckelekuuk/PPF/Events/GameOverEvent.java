package com.Duckelekuuk.PPF.Events;

import com.Duckelekuuk.PPF.GameFrame.Core.Game;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.List;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

@Getter
public class GameOverEvent extends Event {

    private Game game;
    private List<GamePlayer> winners;
    private static HandlerList handlerList = new HandlerList();

    public GameOverEvent(Game game, List<GamePlayer> winners) {
        this.game = game;
        this.winners = winners;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
