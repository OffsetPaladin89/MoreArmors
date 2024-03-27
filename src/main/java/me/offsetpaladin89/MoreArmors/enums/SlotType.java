package me.offsetpaladin89.MoreArmors.enums;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public enum SlotType {
    HELMET, CHESTPLATE, LEGGINGS, BOOTS, NONE;

    public static SlotType matchType(final ItemStack item){
        if(isAirOrNull(item)) return null;
        String type = item.getType().toString();
        if(item.getType().equals(Material.PLAYER_HEAD)) return HELMET;
        if(type.endsWith("_HELMET")) return HELMET;
        else if (type.endsWith("_CHESTPLATE") || type.endsWith("ELYTRA")) return CHESTPLATE;
        else if (type.endsWith("_LEGGINGS")) return LEGGINGS;
        else if (type.endsWith("_BOOTS")) return BOOTS;
        else return null;
    }

    public static SlotType typeFromString(String s) {
        return switch (s.toLowerCase()) {
            case "helmet" -> HELMET;
            case "chestplate" -> CHESTPLATE;
            case "leggings" -> LEGGINGS;
            case "boots" -> BOOTS;
	        default -> null;
        };
    }

    public static Boolean validSlot(String s) {
        switch(s.toLowerCase()) {
            case "helmet", "chestplate", "leggings", "boots" -> { return true; }
        };
        return false;
    }

    public static EquipmentSlot matchSlot(SlotType type) {
        return switch (type) {
            case HELMET -> EquipmentSlot.HEAD;
            case CHESTPLATE -> EquipmentSlot.CHEST;
            case LEGGINGS -> EquipmentSlot.LEGS;
            case BOOTS -> EquipmentSlot.FEET;
            case NONE -> null;
        };
    }

    private static boolean isAirOrNull(ItemStack item) { return item == null || item.getType().equals(Material.AIR); }
}
