package com.Duckelekuuk.PPF.GameFrame.Extenders;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public abstract class PPSpawnItems {

    public abstract Material getHelmet();

    public abstract Material getChestplate();

    public abstract Material getLeggings();

    public abstract Material getBoots();

    public abstract HashMap<ItemStack, Integer> getItems();
}
