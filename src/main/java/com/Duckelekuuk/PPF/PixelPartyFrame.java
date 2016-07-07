package com.Duckelekuuk.PPF;

import com.Duckelekuuk.PPF.Listeners.PixelParty.PixelPartyGameOverListener;
import com.Duckelekuuk.PPF.Listeners.PixelParty.PixelPartySwitchStateListener;
import com.Duckelekuuk.PPF.Listeners.Player.*;
import com.Duckelekuuk.PPF.Listeners.World.WorldWeatherChangeListener;
import com.Duckelekuuk.PPF.Managers.CommandManager;
import com.Duckelekuuk.PPF.Managers.GameManager;
import com.Duckelekuuk.PPF.Managers.GamePlayerManager;
import com.Duckelekuuk.PPF.MongoDB.MongoDB;
import com.Duckelekuuk.PPF.ScoreBoards.LobbyScoreBoard;
import com.Duckelekuuk.PPF.Utils.PixelPartyConstant;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
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
        this.gamePlayerManager = new GamePlayerManager(plugin);
        this.gameManager = new GameManager();
        this.commandManager = new CommandManager(plugin);
        this.pixelPartyConstant = new PixelPartyConstant(plugin);


        pixelPartyConstant.setScoreBoard_Lobby(new LobbyScoreBoard(plugin));
    }

    public void registerListeners() {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(new PixelPartyGameOverListener(this), this);
        pluginManager.registerEvents(new PixelPartySwitchStateListener(this), this);
        pluginManager.registerEvents(new PlayerBlockDamageListener(this), this);
        pluginManager.registerEvents(new PlayerBlockDestroyListener(this), this);
        pluginManager.registerEvents(new PlayerBlockPlaceListener(this), this);
        pluginManager.registerEvents(new PlayerChatListener(this), this);
        pluginManager.registerEvents(new PlayerInteractListener(this), this);
        pluginManager.registerEvents(new PlayerPreLoginListener(this), this);
        pluginManager.registerEvents(new PlayerJoinListener(this), this);
        pluginManager.registerEvents(new PlayerPVEListener(this), this);
        pluginManager.registerEvents(new PlayerPVPListener(this), this);
        pluginManager.registerEvents(new PlayerQuitListener(this), this);

        pluginManager.registerEvents(new WorldWeatherChangeListener(), this);
    }

    public void registerCommands() {
        getCommand("pixelparty").setExecutor(commandManager);
        getCommand("pp").setExecutor(commandManager);
        getCommand("pparty").setExecutor(commandManager);
    }
}
