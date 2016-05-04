package com.Duckelekuuk.PPF.Listeners.PixelParty;

import com.Duckelekuuk.PPF.Events.PixelPartySwitchStateEvent;
import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import com.Duckelekuuk.PPF.Timers.LobbyTimer;
import com.Duckelekuuk.PPF.Timers.WarmUpTimer;
import com.Duckelekuuk.PPF.Utils.PixelPartyState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @AUTHOR Duco.
 * Description
 */
public class PixelPartySwitchStateListener implements Listener {

    private PixelPartyFrame core;

    public PixelPartySwitchStateListener(PixelPartyFrame core) {
        this.core = core;
    }

    @EventHandler
    public void PPSwitch(PixelPartySwitchStateEvent event) {
        PixelPartyState pixelPartyState = event.getPixelPartyState();

        switch (pixelPartyState) {
            case LOBBY:
                new LobbyTimer().runTaskTimerAsynchronously(PixelPartyFrame.getPlugin(), 0, 20);
                break;

            case IN_GAME:
                new WarmUpTimer().runTaskTimerAsynchronously(PixelPartyFrame.getPlugin(), 0, 20);
                break;

            case SWITCHING_MODES:
                new LobbyTimer().runTaskTimerAsynchronously(PixelPartyFrame.getPlugin(), 20, 20);
                break;

            case CLEANING_UP:
                for (GamePlayer gamePlayer : PixelPartyFrame.getPlugin().getPixelPartyConstant().getGamePlayers()) {
                    gamePlayer.getPlayer().kickPlayer(Utils.prefixMessage("\n&aThe server is restarting"));
                }
                break;
        }
    }
}
