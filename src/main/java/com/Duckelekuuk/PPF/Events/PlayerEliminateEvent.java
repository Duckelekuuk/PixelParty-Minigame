package com.Duckelekuuk.PPF.Events;

import com.Duckelekuuk.PPF.GameFrame.Core.Game;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PlayerEliminateEvent extends Event {

    @Getter
    private Game game;
    @Getter
    private GamePlayer gamePlayer;
    @Getter
    private GamePlayer damager;

    private static HandlerList handlerList = new HandlerList();

    public PlayerEliminateEvent(Game game, GamePlayer gamePlayer, GamePlayer damager) {
        this.game = game;
        this.gamePlayer = gamePlayer;
        this.damager = damager;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
