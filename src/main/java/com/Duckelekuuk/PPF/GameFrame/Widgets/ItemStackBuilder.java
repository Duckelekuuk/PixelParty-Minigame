package com.Duckelekuuk.PPF.GameFrame.Widgets;

import com.Duckelekuuk.PPF.GameFrame.Utils.Utils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class ItemStackBuilder {

    private ItemStack ITEM_STACK;

    public ItemStackBuilder(ItemStack item) {
        this.ITEM_STACK = item;
    }

    public void setDisplayName(String displayName) {
        ItemMeta meta = ITEM_STACK.getItemMeta();
        meta.setDisplayName(displayName);
        ITEM_STACK.setItemMeta(meta);
    }

    public void addLore(String lore) {
        ITEM_STACK.getItemMeta().getLore().add(Utils.color(lore));
    }

    public void clearLore() {
        ITEM_STACK.getItemMeta().getLore().clear();
    }

    public void setAmount(int amount) {
        ITEM_STACK.setAmount(amount);
    }

    public void setDurability(short durability) {
        ITEM_STACK.setDurability(durability);
    }

    public void setEnchantment(Enchantment enchantment, int level) {
        ITEM_STACK.addUnsafeEnchantment(enchantment, level);
    }

    public void removeEnchantments() {
        ITEM_STACK.getEnchantments().clear();
    }

    public void setUnbreakable(boolean value) {
        ITEM_STACK.getItemMeta().spigot().setUnbreakable(value);
    }

    public void addItemFlag(ItemFlag itemFlag) {
        ITEM_STACK.getItemMeta().getItemFlags().add(itemFlag);
    }

    public void clearItemFlags() {
        ITEM_STACK.getItemMeta().getItemFlags().clear();
    }

    public ItemStack build() {
        return ITEM_STACK;
    }
}