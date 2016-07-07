package com.Duckelekuuk.PPF.Listeners.PixelParty;

import com.Duckelekuuk.PPF.Events.PixelPartySwitchStateEvent;
import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import com.Duckelekuuk.PPF.Utils.PixelPartyState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class PixelPartySwitchStateListener implements Listener {

    private PixelPartyFrame plugin;

    public PixelPartySwitchStateListener(PixelPartyFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PPSwitch(PixelPartySwitchStateEvent event) {
        PixelPartyState pixelPartyState = event.getPixelPartyState();

        switch (pixelPartyState) {
            case LOBBY:
                break;

            case IN_GAME:
                plugin.getPixelPartyConstant().getWarmUpTimer().runTaskTimerAsynchronously(PixelPartyFrame.getPlugin(), 20, 20);
                break;

            case TRANSITION:
                plugin.getPixelPartyConstant().getTransitionTimer().runTaskTimerAsynchronously(PixelPartyFrame.getPlugin(), 40, 20);
                break;

            case CLEANING_UP:
                for (GamePlayer gamePlayer : PixelPartyFrame.getPlugin().getGamePlayerManager().getGamePlayers()) {
                    gamePlayer.getPlayer().kickPlayer(Utils.prefixMessage("\n&aThe server is restarting"));
                }
                break;
        }
    }
}
