package com.Duckelekuuk.PPF.Listeners.Player;

import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PlayerQuitListener implements Listener {

    private PixelPartyFrame plugin;

    public PlayerQuitListener(PixelPartyFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        plugin.getPixelPartyConstant().getPlayers().remove(plugin.getGamePlayerManager().getGamePlayer(event.getPlayer()));
    }
}
