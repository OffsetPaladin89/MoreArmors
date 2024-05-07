package me.offsetpaladin89.MoreArmors.enums;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public enum SlotType {

    /** Helmet Slot Type */
    HELMET,

    /** Chestplate Slot Type */
    CHESTPLATE,

    /** Leggings Slot Type */
    LEGGINGS,

    /** Boots Slot Type */
    BOOTS;

    /**
     * Returns the SlotType for a specified String.
     * @param s the String to get the SlotType for
     * @return the SlotType for a specified String
     */
    public static SlotType typeFromString(String s) {
        return switch (s.toLowerCase()) {
            case "helmet" -> HELMET;
            case "chestplate" -> CHESTPLATE;
            case "leggings" -> LEGGINGS;
            case "boots" -> BOOTS;
	        default -> null;
        };
    }

    /**
     * Returns the name of the slot for a specified SlotType
     * @param type the slot type to get the name for
     * @return the name of the slot for a specified SlotType
     */
    public static String slotName(SlotType type) {
        return switch(type) {
            case HELMET: yield "helmet";
            case CHESTPLATE: yield "chestplate";
            case LEGGINGS: yield "leggings";
            case BOOTS: yield "boots";
        };
    }

    /**
     * Returns the matching SlotType for a specified EquipmentSlot
     * @param type the EquipmentSlot to match to a SlotType
     * @return the matching SlotType for a specified EquipmentSlot
     */
    public static SlotType matchSlot(EquipmentSlot type) {
        return switch(type) {
            case HEAD: yield HELMET;
            case CHEST: yield CHESTPLATE;
            case LEGS: yield LEGGINGS;
            case FEET: yield BOOTS;
            default: yield null;
        };
    }

    /**
     * Returns the ItemStack in the specified PlayerInventory and SlotType.
     * @param inventory the PlayerInventory to check
     * @param type the SlotType to get the item of
     * @return the ItemStack in the specified PlayerInventory and SlotType
     */
    public static ItemStack getItem(PlayerInventory inventory, SlotType type) {
        return switch (type) {
            case HELMET -> inventory.getHelmet();
            case CHESTPLATE -> inventory.getChestplate();
            case LEGGINGS -> inventory.getLeggings();
            case BOOTS -> inventory.getBoots();
        };
    }

    /**
     * Returns the matching EquipmentSlot for a specified SlotType
     * @param type the SlotType to match to a EquipmentSlot
     * @return the matching EquipmentSlot for a specified SlotType
     */
    public static EquipmentSlot matchSlot(SlotType type) {
        return switch (type) {
            case HELMET -> EquipmentSlot.HEAD;
            case CHESTPLATE -> EquipmentSlot.CHEST;
            case LEGGINGS -> EquipmentSlot.LEGS;
            case BOOTS -> EquipmentSlot.FEET;
        };
    }
}
