package me.offsetpaladin89.MoreArmors.items.misc;

import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.inventory.ItemStack;

public class CustomItem {

    protected ItemStack item;
    protected Rarity rarity = Rarity.DEVELOPER;
    protected String displayName = "Offset";
    protected int tier = 0;

    protected CustomItem(Rarity rarity, String displayName) {
        this.rarity = rarity;
        this.displayName = getFormattedName(displayName);
    }

    protected CustomItem(Rarity rarity, int tier, String displayName) {
        this.rarity = rarity;
        this.tier = tier;
        this.displayName = getFormattedName(displayName);
    }

    protected CustomItem() {}

    public ItemStack getItem() {
        return item;
    }

    protected String getFormattedName(String displayName) {
        return Util.colorString(String.format("%s%s &b(+%d)", rarity.color, displayName, tier));
    }
}