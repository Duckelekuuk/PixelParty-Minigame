package com.Duckelekuuk.PPF.Listeners.Player;

import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import com.Duckelekuuk.PPF.Utils.PixelPartyState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PlayerChatListener implements Listener {

    private PixelPartyFrame plugin;

    public PlayerChatListener(PixelPartyFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        GamePlayer gamePlayer = plugin.getGamePlayerManager().getGamePlayer(event.getPlayer());

        if (!gamePlayer.isIngame()) {
            return;
        }

        if (plugin.getPixelPartyConstant().getPixelPartyState() != PixelPartyState.IN_GAME) {
            event.setCancelled(false);
            return;
        }

        event.setCancelled(!plugin.getGameManager().getCurrentGame().getGame().getPreventionSet().isAllowedToChat());
    }
}
