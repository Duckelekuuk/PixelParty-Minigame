package com.Duckelekuuk.PPF.Listeners.Player;

import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @AUTHOR Duco.
 * Description
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

        if (!plugin.getGameManager().getCurrentGame().getGame().getPreventionSet().isAllowedToBuild()) {
            event.setCancelled(true);
        }
    }
}
