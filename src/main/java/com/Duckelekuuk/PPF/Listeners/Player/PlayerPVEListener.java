package com.Duckelekuuk.PPF.Listeners.Player;

import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @AUTHOR Duco.
 * Description
 */
public class PlayerPVEListener implements Listener {

    private PixelPartyFrame plugin;

    public PlayerPVEListener(PixelPartyFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getDamager();
        GamePlayer gamePlayer = plugin.getGamePlayerManager().getGamePlayer(player);

        if (!gamePlayer.isIngame()) {
            return;
        }

        if (!plugin.getGameManager().getCurrentGame().getGame().getPreventionSet().isAllowedToPVE()) {
            event.setCancelled(true);
        }
    }
}
