package com.Duckelekuuk.PPF.Listeners.Player;

import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import com.Duckelekuuk.PPF.Utils.PixelPartyState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PlayerBlockPlaceListener implements Listener {

    private PixelPartyFrame plugin;

    public PlayerBlockPlaceListener(PixelPartyFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        GamePlayer gamePlayer = plugin.getGamePlayerManager().getGamePlayer(event.getPlayer());

        if (!gamePlayer.isIngame()) {
            return;
        }

        if (plugin.getPixelPartyConstant().getPixelPartyState() != PixelPartyState.IN_GAME) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(!plugin.getGameManager().getCurrentGame().getGame().getPreventionSet().isAllowedToPlaceBlocks());
    }
}
