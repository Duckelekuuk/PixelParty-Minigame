package com.Duckelekuuk.PPF.GameFrame.Core;

import com.Duckelekuuk.PPF.Events.GameOverEvent;
import com.Duckelekuuk.PPF.Execeptions.GameWorldNotFoundExeption;
import com.Duckelekuuk.PPF.GameFrame.Extenders.GameEndExecutor;
import com.Duckelekuuk.PPF.GameFrame.Extenders.GameStartExecutor;
import com.Duckelekuuk.PPF.GameFrame.PPCore;
import com.Duckelekuuk.PPF.GameFrame.Utils.FileAPI;
import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.Managers.GameManager;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.HashSet;

/**
 * Created by Duco on 23-2-2016.
 */

@Getter
public class Game {

    private String name;
    private PPCore ppCore;
    private String prefix;
    private GameType gameType;
    private PreventionSet preventionSet;
    private String worldLocation;
    private GameState currentState;
    private int gameTimerTick;
    private BukkitRunnable gameTimer;

    @Setter
    private HashSet<GameState> gameStates;

    @Setter
    private GameStartExecutor startExecutor;

    @Setter
    private GameEndExecutor endExecutor;

    public Game(PPCore ppCore, GameType gameType, PreventionSet preventionSet, String worldLocation) {
        this.ppCore = ppCore;
        this.name = ppCore.getGameName();
        this.prefix = Utils.color(ppCore.getGamePrefix());
        this.gameType = gameType;
        this.preventionSet = preventionSet;
        this.worldLocation = worldLocation;
        this.gameStates = new HashSet<>();

        loadGameWorld();
    }

    public void broadcast(String message) {
        Utils.color(message);
        Bukkit.getServer().broadcastMessage(prefix + message);
    }

    public void setGameState(Integer gameStateID) {
        for(GameState gamestate : gameStates) {
            if (gamestate.getID() == gameStateID) {
                currentState = gamestate;
            }
        }
    }

    public void endGame() {
        endExecutor.execute();
        gameTimer.cancel();
        Bukkit.getServer().getPluginManager().callEvent(new GameOverEvent(this));

        for(GamePlayer gamePlayer : GameManager.getInstance().getPixelPartyConstant().getGamePlayers()) {
            gamePlayer.getPlayer().teleport(GameManager.getInstance().getPixelPartyConstant().getLobbyLocation());
        }
    }

    public void startGame() {
        startExecutor.execute();
        gameTimer.runTaskTimerAsynchronously(PixelPartyFrame.getPlugin(), 40, gameTimerTick);
    }

    public void loadGameWorld() {
        File file = new File(ppCore.getPlugin().getDataFolder() + "/world");
        if (!file.exists()) {
            GameManager.getInstance().getGames().remove(this);
            new GameWorldNotFoundExeption(this);
            return;
        }

        if (Bukkit.getServer().getWorld("GameWorld") != null) {
            Bukkit.getServer().unloadWorld("GameWorld", true);
        }

        FileAPI.removeFolderContent(Bukkit.getServer().getWorld("GameWorld").getWorldFolder());

        FileAPI.copyFolderContent(file, ppCore.getPlugin().getDataFolder());

    }
}