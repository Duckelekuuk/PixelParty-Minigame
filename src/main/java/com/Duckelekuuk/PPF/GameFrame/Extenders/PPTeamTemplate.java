package com.Duckelekuuk.PPF.GameFrame.Extenders;

import org.bukkit.Location;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public interface PPTeamTemplate {

    String getTeamName();

    String getTeamPrefix();

    HashMap<Team.Option, Team.OptionStatus> getTeamOption();

    PPSpawnItems getSpawnItems();

    Location getSpawnocation();
}
