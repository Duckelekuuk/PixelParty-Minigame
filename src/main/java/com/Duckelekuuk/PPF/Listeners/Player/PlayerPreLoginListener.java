package com.Duckelekuuk.PPF.Listeners.Player;

import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PlayerPreLoginListener implements Listener {

    private PixelPartyFrame plugin;

    public PlayerPreLoginListener(PixelPartyFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
    }
}
