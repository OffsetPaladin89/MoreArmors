package me.offsetpaladin89.MoreArmors.enums;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public enum SlotType {
    HELMET, CHESTPLATE, LEGGINGS, BOOTS;

    public static SlotType typeFromString(String s) {
        return switch (s.toLowerCase()) {
            case "helmet" -> HELMET;
            case "chestplate" -> CHESTPLATE;
            case "leggings" -> LEGGINGS;
            case "boots" -> BOOTS;
	        default -> null;
        };
    }

    public static String slotName(SlotType type) {
        return switch(type) {
            case HELMET: yield "helmet";
            case CHESTPLATE: yield "chestplate";
            case LEGGINGS: yield "leggings";
            case BOOTS: yield "boots";
        };
    }

    public static SlotType matchSlot(EquipmentSlot type) {
        return switch(type) {
            case HEAD: yield HELMET;
            case CHEST: yield CHESTPLATE;
            case LEGS: yield LEGGINGS;
            case FEET: yield BOOTS;
            default: yield null;
        };
    }

    public static EquipmentSlot matchSlot(SlotType type) {
        return switch (type) {
            case HELMET -> EquipmentSlot.HEAD;
            case CHESTPLATE -> EquipmentSlot.CHEST;
            case LEGGINGS -> EquipmentSlot.LEGS;
            case BOOTS -> EquipmentSlot.FEET;
        };
    }
}
