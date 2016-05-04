package com.Duckelekuuk.PPF.Managers;

import com.Duckelekuuk.PPF.GameFrame.PPCore;
import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import com.Duckelekuuk.PPF.Utils.PixelPartyConstant;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Random;

/**
 * @AUTHOR Duco.
 * Description
 */

@Getter
public class GameManager {

    private PixelPartyConstant pixelPartyConstant;
    @Getter
    private ArrayList<PPCore> games = new ArrayList<>();
    private PPCore currentGame = null;
    private PPCore nextGame = null;

    public GameManager(PixelPartyConstant pixelPartyConstant) {
        this.pixelPartyConstant = pixelPartyConstant;

//        this.nextGame = pickRandomGame();
    }

    public void registerGame(PPCore ppCore) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getGame().getName() == ppCore.getGame().getName()) {
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
        currentGame = nextGame;
        //TODO: Check for force next game
        this.nextGame = pickRandomGame();
    }

    public static GameManager getInstance() {
        return PixelPartyFrame.getPlugin().getGameManager();
    }
}
