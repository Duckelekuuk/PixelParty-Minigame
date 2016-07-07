package com.Duckelekuuk.PPF.Listeners.Player;

import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import com.Duckelekuuk.PPF.Utils.PixelPartyState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PlayerBlockDestroyListener implements Listener {

    private PixelPartyFrame plugin;

    public PlayerBlockDestroyListener(PixelPartyFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDestroy(BlockBreakEvent event) {

        GamePlayer gamePlayer = plugin.getGamePlayerManager().getGamePlayer(event.getPlayer());

        if (!gamePlayer.isIngame()) {
            return;
        }

        if (plugin.getPixelPartyConstant().getPixelPartyState() != PixelPartyState.IN_GAME) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(!plugin.getGameManager().getCurrentGame().getGame().getPreventionSet().isAllowedToDestroyBlocks());
    }
}
