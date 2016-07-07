package com.Duckelekuuk.PPF.Utils;

import com.Duckelekuuk.PPF.Events.PixelPartySwitchStateEvent;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import com.Duckelekuuk.PPF.ScoreBoards.LobbyScoreBoard;
import com.Duckelekuuk.PPF.Timers.LobbyTimer;
import com.Duckelekuuk.PPF.Timers.TransitionTimer;
import com.Duckelekuuk.PPF.Timers.WarmUpTimer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashSet;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

@Getter
@Setter
public class PixelPartyConstant {

    private PixelPartyFrame plugin;

    private Location lobbyLocation;
    private PixelPartyState pixelPartyState;
    private HashSet<GamePlayer> players;

    private int maxGames;
    private boolean isCounting;
    private int maxPlayers;

    private BukkitTask lobbyTimer;
    private TransitionTimer transitionTimer;
    private WarmUpTimer warmUpTimer;

    private LobbyScoreBoard scoreBoard_Lobby;
    private Scoreboard scoreBoard_Spectator;
    private Scoreboard scoreBoard_PreStart;

    public PixelPartyConstant(PixelPartyFrame plugin) {
        this.plugin = plugin;
        this.lobbyLocation = new Location(Bukkit.getServer().getWorld("world"), 0, 0, 0);
        this.players = new HashSet<>();
        this.maxGames = 5;
        this.isCounting = false;
        this.maxPlayers = 24;


        this.lobbyTimer = new LobbyTimer().runTaskTimerAsynchronously(plugin, 0, 20);
        this.transitionTimer = new TransitionTimer();
        this.warmUpTimer = new WarmUpTimer(players);

        setPixelPartyState(PixelPartyState.LOBBY);
    }

    public void setPixelPartyState(PixelPartyState pixelPartyState) {
        this.pixelPartyState = pixelPartyState;
        Bukkit.getServer().getPluginManager().callEvent(new PixelPartySwitchStateEvent(pixelPartyState));
    }


    public boolean canStart() {
        return players.size() >= 2;
    }
}