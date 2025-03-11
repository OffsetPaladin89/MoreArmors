package me.offsetpaladin89.MoreArmors.utils;

import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.ChatColor;

import java.util.ArrayList;

public class Lore {

    ArrayList<String> lore;

    public Lore() {
        lore = new ArrayList<>();
    }

    public void addColoredLine(String s) {
        lore.add(colorString(s));
    }

    public void addArmorRarity(Rarity rarity) {
        addEmpty();
        lore.add(colorString(String.format("%s&l%s", rarity.color, rarity)));
    }

    public void addMaterialRarity(Rarity rarity) {
        addEmpty();
        lore.add(colorString(String.format("%s&l%s MATERIAL", rarity.color, rarity)));
    }

    public void addItemRarity(Rarity rarity) {
        addEmpty();
        lore.add(colorString(String.format("%s&l%s ITEM", rarity.color, rarity)));
    }

    public void addEmpty() {
        lore.add("");
    }

    public ArrayList<String> getLore() {
        return lore;
    }

    private String colorString(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
