package com.Duckelekuuk.PPF.Utils;

import com.Duckelekuuk.PPF.Events.PixelPartySwitchStateEvent;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashSet;

/**
 * @AUTHOR Duco.
 * Description
 */

@Getter
@Setter
public class PixelPartyConstant {

    private Location lobbyLocation;
    private PixelPartyState pixelPartyState;
    private HashSet<GamePlayer> gamePlayers;

    private boolean isCounting;
    private Scoreboard scoreBoard_Transition;
    private Scoreboard scoreBoard_PreStart;
    private Scoreboard scoreBoard_transition;

    public PixelPartyConstant() {
        this.lobbyLocation = new Location(Bukkit.getServer().getWorld("world"), 0, 0, 0);
        this.gamePlayers = new HashSet<>();
        this.isCounting = false;

        setPixelPartyState(PixelPartyState.LOBBY);
    }

    public void setPixelPartyState(PixelPartyState pixelPartyState) {
        this.pixelPartyState = pixelPartyState;
        Bukkit.getServer().getPluginManager().callEvent(new PixelPartySwitchStateEvent(pixelPartyState));
    }


    public boolean canStart() {
        return getGamePlayers().size() >= 2;
    }
}
