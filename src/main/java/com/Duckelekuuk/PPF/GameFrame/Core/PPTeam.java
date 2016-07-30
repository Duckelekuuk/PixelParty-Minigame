package com.Duckelekuuk.PPF.GameFrame.Core;

import com.Duckelekuuk.PPF.GameFrame.Extenders.PPTeamTemplate;
import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import com.Duckelekuuk.PPF.GamePlayers.GamePlayer;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

@Getter
@Setter
public class PPTeam {

    private Scoreboard scoreboard;
    private Team team;
    private int score;
    private PPTeamTemplate teamTemplate;
    private HashSet<GamePlayer> members;

    public PPTeam(Scoreboard scoreboard ,PPTeamTemplate teamTemplate) {
        this.scoreboard = scoreboard;
        this.team = scoreboard.registerNewTeam(teamTemplate.getTeamName());
        this.score = 0;
        this.teamTemplate = teamTemplate;
        team.setPrefix(Utils.color(teamTemplate.getTeamPrefix()));

        for (Map.Entry<Team.Option, Team.OptionStatus> entry : teamTemplate.getTeamOption().entrySet()) {
            Team.Option option = entry.getKey();
            Team.OptionStatus status = entry.getValue();
            team.setOption(option, status);
        }

        this.members = new HashSet<>();
    }

    public void addMember(GamePlayer gamePlayer) {
        if (!members.contains(gamePlayer)) {
            members.add(gamePlayer);
            team.addPlayer(gamePlayer.getPlayer());
        }
    }

    public void enableTeam() {
        for (GamePlayer gamePlayer : members) {
            gamePlayer.getPlayer().setScoreboard(scoreboard);
            gamePlayer.teleport(teamTemplate.getSpawnocation());

            gamePlayer.getPlayer().getInventory().setHeldItemSlot(0);

            gamePlayer.getPlayer().getInventory().setHelmet(new ItemStack(teamTemplate.getSpawnItems().getHelmet()));
            gamePlayer.getPlayer().getInventory().setChestplate(new ItemStack(teamTemplate.getSpawnItems().getChestplate()));
            gamePlayer.getPlayer().getInventory().setLeggings(new ItemStack(teamTemplate.getSpawnItems().getLeggings()));
            gamePlayer.getPlayer().getInventory().setBoots(new ItemStack(teamTemplate.getSpawnItems().getBoots()));

            for (Map.Entry<ItemStack, Integer> entry : teamTemplate.getSpawnItems().getItems().entrySet()) {
                gamePlayer.getPlayer().getInventory().setItem(entry.getValue(), entry.getKey());
            }
        }
    }
}
