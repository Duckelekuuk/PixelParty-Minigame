package com.Duckelekuuk.PPF.Events;

import com.Duckelekuuk.PPF.GameFrame.Core.Game;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Duco on 23-2-2016.
 */
public class GameOverEvent extends Event {

    @Getter
    private Game game;
    private static HandlerList handlerList = new HandlerList();

    public GameOverEvent(Game game) {
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
