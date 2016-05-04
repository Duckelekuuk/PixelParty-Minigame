package com.Duckelekuuk.PPF.Listeners.Player;

import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @AUTHOR Duco.
 * Description
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

        if (!plugin.getGameManager().getCurrentGame().getGame().getPreventionSet().isAllowedToDestroy()) {
            event.setCancelled(true);
        }
    }
}
