package com.Duckelekuuk.PPF.GameFrame.Widgets;

import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import com.Duckelekuuk.PPF.PixelPartyFrame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class ScoreboardAPI {

    private Scoreboard scoreboard;
    private Player player;

    public ScoreboardAPI(final Player player) {

        this.player = player;
        this.scoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("PPBoard", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Utils.color("&6&lPixel&e&lParty"));

        Score timerTitle = objective.getScore(Utils.color("&aTime left"));
        timerTitle.setScore(2);

        Team timerValue = scoreboard.registerNewTeam("timer");
        timerValue.addEntry("Time left: ");
        timerValue.setPrefix("");
        timerValue.setSuffix(ChatColor.YELLOW + "0");

        objective.getScore("Time left: ").setScore(1);

        new BukkitRunnable() {
            int num = 0;

            @Override
            public void run() {
                scoreboard.getTeam("timer").setSuffix(ChatColor.YELLOW + "" + num);
                num++;
            }
        }.runTaskTimerAsynchronously(PixelPartyFrame.getPlugin(), 20, 20);
    }

    public void setScoreboard() {
        player.setScoreboard(scoreboard);
    }
}
