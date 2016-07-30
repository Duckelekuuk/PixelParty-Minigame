package com.Duckelekuuk.PPF.GameFrame.Core;

import com.Duckelekuuk.PPF.Events.GameOverEvent;
import com.Duckelekuuk.PPF.Events.GameStartEvent;
import com.Duckelekuuk.PPF.Execeptions.GameWorldNotFoundExeption;
import com.Duckelekuuk.PPF.GameFrame.Extenders.GameEndExecutor;
import com.Duckelekuuk.PPF.GameFrame.Extenders.GameStartExecutor;
import com.Duckelekuuk.PPF.GameFrame.Extenders.GameTimerTickExecutor;
import com.Duckelekuuk.PPF.GameFrame.Extenders.PPTeamTemplate;
import com.Duckelekuuk.PPF.GameFrame.PPCore;
import com.Duckelekuuk.PPF.GameFrame.Utils.DevideType;
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
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

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
    private GameState currentState;
    private Location spawnLocation;
    private Scoreboard teamScoreboard;
    private ArrayList<PPTeam> teams;

    private DevideType devideType;
    private PPTeamTemplate[] ppTeamplates;

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

    public Game(PPCore ppCore, PreventionSet preventionSet) {
        this.ppCore = ppCore;
        this.name = ppCore.getGameName();
        this.prefix = Utils.color(ppCore.getGamePrefix());
        this.preventionSet = preventionSet;
        this.gameTimerTick = 20;
        this.spawnLocation = null;
        this.teamScoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        this.teams = new ArrayList<>();
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
            gamePlayer.teleport(PixelPartyFrame.getPlugin().getPixelPartyConstant().getLobbyLocation());
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

    public void registerTeam(DevideType devideType, PPTeamTemplate... team) {
        this.devideType = devideType;
        this.ppTeamplates = team;

    }

    public void divide() {
        switch (devideType) {
            case CUSTOM:
                for (PPTeamTemplate template : ppTeamplates) {
                    PPTeam teamOBJ = new PPTeam(teamScoreboard, template);
                    this.teams.add(teamOBJ);
                }
                divideInTeams();
                break;
            case DUO:
                divideInDuos();
                break;

            case SOLO:
                devideInSolo(ppTeamplates[0]);
                break;

            case TEAMVSTEAM:
                divideInPairs(ppTeamplates[0]);
                break;
        }

        for (GamePlayer gamePlayer : PixelPartyFrame.getPlugin().getPixelPartyConstant().getPlayers()) {
            gamePlayer.getPlayer().setScoreboard(teamScoreboard);
        }
    }

    public PPTeam getTeam(String name) {
        for (PPTeam team : teams) {
            if (team.getTeamTemplate().getTeamName().equalsIgnoreCase(name)) {
                return team;
            }
        }
        return null;
    }

    public PPTeam getTeam(GamePlayer gamePlayer) {
        for (PPTeam team : teams) {
            if (team.getMembers().contains(gamePlayer)) {
                return team;
            }
        }
        return null;
    }

    public void setWeatherType(WeatherType weatherType) {
        WeatherType.setWeather(Bukkit.getServer().getWorld("GameWorld"), weatherType);
        this.weatherType = weatherType;
    }

    private void divideInTeams() {
        ArrayList<GamePlayer> dividedPlayers = new ArrayList<>();
        int module = teams.size();
        Random random = new Random();

        int num = 0;

        while (dividedPlayers.size() < PixelPartyFrame.getPlugin().getPixelPartyConstant().getPlayers().size()) {
            int randomNum = random.nextInt(PixelPartyFrame.getPlugin().getPixelPartyConstant().getPlayers().size());
            GamePlayer gamePlayer = PixelPartyFrame.getPlugin().getPixelPartyConstant().getPlayers().get(randomNum);
            if (!dividedPlayers.contains(gamePlayer)) {
                teams.get(num % module).addMember(gamePlayer);
                dividedPlayers.add(gamePlayer);
                gamePlayer.getPlayer().sendMessage(Utils.color("You are on team: " + teams.get(num % module).getTeamTemplate().getTeamPrefix()));
                num++;
            }
        }
    }

    private void divideInDuos() {

    }

    private void devideInSolo(PPTeamTemplate teamTemplate) {
        for (GamePlayer gamePlayer : PixelPartyFrame.getPlugin().getPixelPartyConstant().getPlayers()) {
            PPTeam teamOBJ = new PPTeam(teamScoreboard, teamTemplate);
            teams.add(teamOBJ);
            teamOBJ.addMember(gamePlayer);
        }
    }

    private void divideInPairs(PPTeamTemplate teamTemplate) {
        ArrayList<GamePlayer> dividedPlayers = new ArrayList<>();

        Random random = new Random();

        PPTeam team = null;

        while (dividedPlayers.size() < PixelPartyFrame.getPlugin().getPixelPartyConstant().getPlayers().size()) {
            int randomNum = random.nextInt(PixelPartyFrame.getPlugin().getPixelPartyConstant().getPlayers().size());
            GamePlayer gamePlayer = PixelPartyFrame.getPlugin().getPixelPartyConstant().getPlayers().get(randomNum);

            if (!dividedPlayers.contains(gamePlayer)) {
                dividedPlayers.add(gamePlayer);
                if (team == null) {
                    PPTeam teamOBJ = new PPTeam(teamScoreboard, teamTemplate);
                    teams.add(teamOBJ);
                    team = teamOBJ;

                    team.addMember(gamePlayer);
                } else {
                    team.addMember(gamePlayer);
                    team = null;
                }
            }
        }
    }
}