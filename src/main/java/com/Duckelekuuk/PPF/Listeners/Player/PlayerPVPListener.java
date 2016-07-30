package com.Duckelekuuk.PPF.Listeners.Player;

import com.Duckelekuuk.PPF.Events.PlayerEliminateEvent;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import com.Duckelekuuk.PPF.Utils.PixelPartyState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PlayerPVPListener implements Listener {

    private PixelPartyFrame plugin;

    public PlayerPVPListener(PixelPartyFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        GamePlayer gamePlayer = plugin.getGamePlayerManager().getGamePlayer(player);

        if (!gamePlayer.isIngame()) {
            return;
        }

        if (plugin.getPixelPartyConstant().getPixelPartyState() != PixelPartyState.IN_GAME) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(!plugin.getGameManager().getCurrentGame().getGame().getPreventionSet().isAllowedToPVP());

        if (plugin.getGameManager().getCurrentGame().getGame().getPreventionSet().isAllowedToPVP()) {
            if (event.getDamage() > ((Player) event.getEntity()).getHealth()) {
                event.setCancelled(true);

                GamePlayer damager = plugin.getGamePlayerManager().getGamePlayer((Player) event.getDamager());

                Bukkit.getServer().getPluginManager().callEvent(new PlayerEliminateEvent(plugin.getGameManager().getCurrentGame().getGame(), gamePlayer, damager));
            }
        }
    }
}
