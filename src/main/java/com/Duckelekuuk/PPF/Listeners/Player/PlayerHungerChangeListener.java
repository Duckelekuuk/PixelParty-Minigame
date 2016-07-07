package com.Duckelekuuk.PPF.Listeners.Player;

import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PlayerHungerChangeListener implements Listener {

    private PixelPartyFrame plugin;

    public PlayerHungerChangeListener(PixelPartyFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChange(FoodLevelChangeEvent event) {

    }
}
