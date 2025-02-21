package me.offsetpaladin89.MoreArmors.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum SlotType {
    HELMET, CHESTPLATE, LEGGINGS, BOOTS, NONE;

    public static SlotType matchType(ItemStack item) {
        if(isAirOrNull(item)) return NONE;

        String type = item.getType().toString();

        if(type.endsWith("HELMET") || type.endsWith("HEAD")) return HELMET;
        else if(type.endsWith("CHESTPLATE") || type.endsWith("ELYTRA")) return CHESTPLATE;
        else if(type.endsWith("LEGGINGS")) return LEGGINGS;
        else if(type.endsWith("BOOTS")) return BOOTS;
        return NONE;
    }

    public static SlotType typeFromString(String s) {
        return switch (s.toLowerCase()) {
            case "helmet" -> HELMET;
            case "chestplate" -> CHESTPLATE;
            case "leggings" -> LEGGINGS;
            case "boots" -> BOOTS;
            default -> NONE;
        };
    }

    private static boolean isAirOrNull(ItemStack item) {
        return item == null || item.getType().equals(Material.AIR);
    }
}
