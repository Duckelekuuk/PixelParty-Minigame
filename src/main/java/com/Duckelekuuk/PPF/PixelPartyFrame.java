package com.Duckelekuuk.PPF;

import com.Duckelekuuk.PPF.GameFrame.Widgets.ScoreboardAPI;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.Listeners.PixelParty.PixelPartySwitchStateListener;
import com.Duckelekuuk.PPF.Listeners.Player.*;
import com.Duckelekuuk.PPF.Managers.CommandManager;
import com.Duckelekuuk.PPF.Managers.GameManager;
import com.Duckelekuuk.PPF.Managers.GamePlayerManager;
import com.Duckelekuuk.PPF.MongoDB.MongoDB;
import com.Duckelekuuk.PPF.Utils.PixelPartyConstant;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * Created by Duco on 7-2-2016.
 */
@Getter
public class PixelPartyFrame extends JavaPlugin implements Listener {

    @Getter
    private static PixelPartyFrame plugin;
    private PixelPartyConstant pixelPartyConstant;
    private GameManager gameManager;
    private GamePlayerManager gamePlayerManager;
    private CommandManager commandManager;

    @Getter
    private static MongoDB mongoDB;

    @Override
    public void onEnable() {
        registerConstants();
        registerListeners();
        registerCommands();
        /* ---------- SETTING UP DATABASE ---------- */
//        mongoDB = new MongoDB("127.0.0.1", 222222);
    }

    public void registerConstants() {
        this.plugin = this;
        this.pixelPartyConstant = new PixelPartyConstant();
        this.gameManager = new GameManager(pixelPartyConstant);
        this.gamePlayerManager = new GamePlayerManager();
        this.commandManager = new CommandManager(plugin);
    }

    public void registerListeners() {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(this, this);
        pluginManager.registerEvents(new PixelPartySwitchStateListener(this), this);
        pluginManager.registerEvents(new PlayerBlockDestroyListener(this), this);
        pluginManager.registerEvents(new PlayerBlockPlaceListener(this), this);
        pluginManager.registerEvents(new PlayerChatListener(this), this);
        pluginManager.registerEvents(new PlayerInteractListener(this), this);
        pluginManager.registerEvents(new PlayerPVEListener(this), this);
        pluginManager.registerEvents(new PlayerPVPListener(this), this);
    }

    public void registerCommands(){
        getCommand("pixelparty").setExecutor(commandManager);
        getCommand("pp").setExecutor(commandManager);
        getCommand("pparty").setExecutor(commandManager);
    }

    @EventHandler
    public void onjoin(PlayerJoinEvent event) {
        ScoreboardAPI api = new ScoreboardAPI(event.getPlayer());
        api.setScoreboard();

        pixelPartyConstant.getGamePlayers().add(new GamePlayer(event.getPlayer()));

//        new WarmUpTimer().runTaskTimerAsynchronously(this, 60, 20);
    }
}
