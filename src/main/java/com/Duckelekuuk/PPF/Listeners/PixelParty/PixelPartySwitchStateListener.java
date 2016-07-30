package com.Duckelekuuk.PPF.Listeners.PixelParty;

import com.Duckelekuuk.PPF.Events.PixelPartySwitchStateEvent;
import com.Duckelekuuk.PPF.GameFrame.Core.PPTeam;
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
                for (PPTeam team : plugin.getGameManager().getCurrentGame().getGame().getTeams()) {
                    team.enableTeam();
                }
                plugin.getPixelPartyConstant().getWarmUpTimer().runTaskTimerAsynchronously(PixelPartyFrame.getPlugin(), 20, 20);
                break;

            case TRANSITION:
                plugin.getPixelPartyConstant().getTransitionTimer().runTaskTimerAsynchronously(PixelPartyFrame.getPlugin(), 40, 20);
                break;

            case CLEANING_UP:
                plugin.getPixelPartyConstant().getGameEndingTimer().runTaskTimerAsynchronously(PixelPartyFrame.getPlugin(), 40, 20);
                break;
        }
    }
}
