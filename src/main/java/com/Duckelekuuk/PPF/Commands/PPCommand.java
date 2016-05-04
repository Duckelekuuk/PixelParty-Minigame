package com.Duckelekuuk.PPF.Commands;

import lombok.Getter;
import org.bukkit.command.CommandSender;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

@Getter
public abstract class PPCommand {

    private boolean playerOnly;
    private String[] labels;
    private String permission;
    private String[] args;

    public PPCommand(boolean playerOnly, String[] labels, String[] args) {
        this.playerOnly = playerOnly;
        this.labels = labels;
        this.permission = "PixelParty.default";
        this.args = args;
    }

    public abstract void onCommand(CommandSender sender, String[] args);
}
