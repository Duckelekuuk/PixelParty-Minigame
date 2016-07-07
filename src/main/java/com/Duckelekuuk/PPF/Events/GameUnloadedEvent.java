package com.Duckelekuuk.PPF.Events;

import com.Duckelekuuk.PPF.GameFrame.Core.Game;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class GameUnloadedEvent extends Event {

    @Getter
    private Game game;
    private static HandlerList handlerList = new HandlerList();

    public GameUnloadedEvent(Game game) {
        this.game = game;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
