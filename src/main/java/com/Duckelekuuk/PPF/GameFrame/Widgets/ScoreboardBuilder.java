package com.Duckelekuuk.PPF.GameFrame.Widgets;

import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

@Getter
public class ScoreboardBuilder {

    private Scoreboard scoreboard;
    private Objective objective;

    public ScoreboardBuilder(String name, DisplaySlot displaySlot, String displayName) {
        this.scoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective(name, "dummy");

        objective.setDisplaySlot(displaySlot);
        objective.setDisplayName(Utils.color(displayName));
    }


    public ScoreboardBuilder addScore(String objectiveName, String prefix, String name, String suffix, int score) {
        Team team = scoreboard.registerNewTeam(objectiveName);
        team.setPrefix(Utils.color(prefix));
        team.addEntry(Utils.color(name));
        team.setSuffix(Utils.color(suffix));

        objective.getScore(name).setScore(score);

        return this;
    }

    public void removeScore(String objectiveName) {
        scoreboard.getTeam(objectiveName).unregister();
    }
}
