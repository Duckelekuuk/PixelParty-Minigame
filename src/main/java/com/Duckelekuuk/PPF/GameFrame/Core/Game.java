package com.Duckelekuuk.PPF.GameFrame.Core;

import com.Duckelekuuk.PPF.Events.GameOverEvent;
import com.Duckelekuuk.PPF.Events.GameStartEvent;
import com.Duckelekuuk.PPF.Execeptions.GameWorldNotFoundExeption;
import com.Duckelekuuk.PPF.GameFrame.Extenders.GameEndExecutor;
import com.Duckelekuuk.PPF.GameFrame.Extenders.GameStartExecutor;
import com.Duckelekuuk.PPF.GameFrame.Extenders.GameTimerTickExecutor;
import com.Duckelekuuk.PPF.GameFrame.PPCore;
import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import com.Duckelekuuk.PPF.GameFrame.Utils.WeatherType;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import com.Duckelekuuk.PPF.Timers.GameTimer;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

@Getter
public class Game {

    private String name;
    private PPCore ppCore;
    private String prefix;
    private PreventionSet preventionSet;
    private String worldLocation;
    private GameState currentState;
    private Location spawnLocation;

    @Setter
    private int gameTimerTick;

    @Setter
    private GameTimerTickExecutor gameTimerTickExecutor;

    @Setter
    private BukkitRunnable gameTimer;

    @Setter
    private WeatherType weatherType;

    @Setter
    private HashSet<GameState> gameStates;

    @Setter
    private GameStartExecutor startExecutor;

    @Setter
    private GameEndExecutor endExecutor;

    public Game(PPCore ppCore, PreventionSet preventionSet, String worldLocation) {
        this.ppCore = ppCore;
        this.name = ppCore.getGameName();
        this.prefix = Utils.color(ppCore.getGamePrefix());
        this.preventionSet = preventionSet;
        this.worldLocation = worldLocation;
        this.gameTimerTick = 20;
        this.spawnLocation = null;
        this.gameStates = new HashSet<>();

        if (!ppCore.getPlugin().getDataFolder().exists()) {
            ppCore.getPlugin().getDataFolder().mkdir();
        }

        try {
            loadGameWorld();
            ppCore.getPlugin().getLogger().info("World loaded!");
        } catch (GameWorldNotFoundExeption gameWorldNotFoundExeption) {
            gameWorldNotFoundExeption.printStackTrace();
        }

        this.startExecutor = null;
        this.endExecutor = null;
    }

    public void broadcast(String message) {
        Utils.color(message);
        Bukkit.getServer().broadcastMessage(prefix + message);
    }

    public void setGameState(Integer gameStateID) {
        for (GameState gamestate : gameStates) {
            if (gamestate.getID() == gameStateID) {
                currentState = gamestate;
            }
        }
    }

    public void startGame() {
        this.gameTimer = new GameTimer();
        if (startExecutor == null) {
            startExecutor.execute();
        }

        Bukkit.getServer().getPluginManager().callEvent(new GameStartEvent(this));
        if (gameTimer != null) {
            gameTimer.runTaskTimerAsynchronously(PixelPartyFrame.getPlugin(), 0, gameTimerTick);
        }
    }

    public void setSpawnLocation(double x, double y, double z, float pitch, float yaw) {
        this.spawnLocation = new Location(Bukkit.getWorld("GameWorld"), x, y, z, pitch, yaw);
    }

    public void endGame(Player... winners) {
        if (endExecutor == null) {
            endExecutor.execute();
        }
        gameTimer.cancel();

        List<GamePlayer> winners_list = new ArrayList<>();

        for (Player player : winners) {
            winners_list.add(PixelPartyFrame.getPlugin().getGamePlayerManager().getGamePlayer(player));
        }

        Bukkit.getServer().getPluginManager().callEvent(new GameOverEvent(this, winners_list));

        for (GamePlayer gamePlayer : PixelPartyFrame.getPlugin().getPixelPartyConstant().getPlayers()) {
            gamePlayer.getPlayer().teleport(PixelPartyFrame.getPlugin().getPixelPartyConstant().getLobbyLocation());
        }
    }

    public void loadGameWorld() throws GameWorldNotFoundExeption {
        try {

            File mirror = new File(ppCore.getPlugin().getDataFolder() + "/GameWorld");

            if (!mirror.exists()) {
                //            PixelPartyFrame.getPlugin().getGameManager().getGames().remove(this);
                throw new GameWorldNotFoundExeption(this);
            }

            File gameFolder = new File(Bukkit.getServer().getWorldContainer().getAbsolutePath() + "/GameWorld");

            if (gameFolder.exists()) {
                if (Bukkit.getServer().getWorld("GameWorld") != null) {
                    Bukkit.getServer().unloadWorld("GameWorld", true);
                }

                FileUtils.deleteDirectory(gameFolder);
            }

            if (!gameFolder.exists()) {
                gameFolder.mkdirs();
            }

            FileUtils.copyDirectory(mirror, gameFolder);

            Bukkit.getServer().createWorld(new WorldCreator("GameWorld").type(WorldType.CUSTOMIZED));

        } catch (Exception e) {
        }
    }

    public void setWeatherType(WeatherType weatherType) {
        WeatherType.setWeather(Bukkit.getServer().getWorld("GameWorld"), weatherType);
        this.weatherType = weatherType;
    }
}