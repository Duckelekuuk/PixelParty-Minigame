package com.Duckelekuuk.PPF.GameFrame.Core;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

@Getter
@Setter
public class PPTeam {

    private String name;
    private String prefix;
    private Scoreboard scoreboard;
    private HashSet<UUID> members;

    public List<Player> getPlayers() {
        List<Player> list = new ArrayList<>();
        for (UUID uuid : members) {
            list.add(Bukkit.getServer().getPlayer(uuid));
        }
        return list;
    }

    public void addMember(Player player) {
        if (!members.contains(player.getUniqueId())) {
            members.add(player.getUniqueId());
        }
    }
}
