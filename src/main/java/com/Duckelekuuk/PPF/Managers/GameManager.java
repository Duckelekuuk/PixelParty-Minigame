package com.Duckelekuuk.PPF.Managers;

import com.Duckelekuuk.PPF.Events.GameReadyEvent;
import com.Duckelekuuk.PPF.GameFrame.Core.Game;
import com.Duckelekuuk.PPF.GameFrame.PPCore;
import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Random;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

@Getter
public class GameManager {

    @Getter
    private ArrayList<PPCore> games = new ArrayList<>();
    private ArrayList<PPCore> playedGames = new ArrayList<>();
    private PPCore currentGame = null;
    private PPCore nextGame = null;
    private boolean loading;

    public void registerGame(PPCore ppCore) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getGameName().equalsIgnoreCase(ppCore.getGameName())) {
                PixelPartyFrame.getPlugin().getLogger().info(Utils.prefix());
                continue;
            }
        }
        games.add(ppCore);
        Bukkit.getServer().broadcastMessage(Utils.prefix() + Utils.color("Loaded game: &6" + ppCore.getPlugin().getName()));
    }

    public PPCore getGame(String gameName) {
        for (PPCore ppCore : games) {
            if (ppCore.getPlugin().getName().equalsIgnoreCase(gameName)) {
                return ppCore;
            }
        }
        return null;
    }

    public void setNextGame(PPCore nextGame) {
        this.nextGame = nextGame;
    }

    public PPCore pickRandomGame() {
        int random = new Random().nextInt(games.size());
        nextGame = games.get(random);
        return games.get(random);
    }

    public void switchGame() {
        loading = true;
        if (nextGame == null) {
            nextGame = pickRandomGame();

            if (playedGames.contains(nextGame)) {
                nextGame = null;
                switchGame();
                return;
            }
        }
        final PPCore nextGameF = nextGame;

        playedGames.add(nextGame);
        Bukkit.getServer().getScheduler().runTask(PixelPartyFrame.getPlugin(), new Runnable() {
            @Override
            public void run() {
                nextGameF.setup();
            }
        });
        Bukkit.getServer().getPluginManager().callEvent(new GameReadyEvent(nextGame.getGame()));
        currentGame = nextGame;
        nextGame = null;
        loading = false;
    }

    public boolean wasFinalGame() {
        return (playedGames.size() >= PixelPartyFrame.getPlugin().getPixelPartyConstant().getMaxGames() || playedGames.size() == games.size());
    }

    public static GameManager getInstance() {
        return PixelPartyFrame.getPlugin().getGameManager();
    }
}
