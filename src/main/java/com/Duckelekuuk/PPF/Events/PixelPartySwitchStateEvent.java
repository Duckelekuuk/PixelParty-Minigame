package com.Duckelekuuk.PPF.Events;

import com.Duckelekuuk.PPF.Utils.PixelPartyState;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PixelPartySwitchStateEvent extends Event {

    @Getter
    private PixelPartyState pixelPartyState;
    private static HandlerList handlerList = new HandlerList();

    public PixelPartySwitchStateEvent(PixelPartyState pixelPartyState) {
        this.pixelPartyState = pixelPartyState;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
