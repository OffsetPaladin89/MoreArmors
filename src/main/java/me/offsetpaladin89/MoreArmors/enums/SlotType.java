package me.offsetpaladin89.MoreArmors.enums;

import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public enum SlotType {
    HELMET, CHESTPLATE, LEGGINGS, BOOTS, INVALID;

    public static SlotType matchType(ItemStack item) {
        if (Util.isAirOrNull(item)) return INVALID;

        String type = item.getType().toString();

        if (type.endsWith("HELMET") || type.endsWith("HEAD")) return HELMET;
        else if (type.endsWith("CHESTPLATE") || type.endsWith("ELYTRA")) return CHESTPLATE;
        else if (type.endsWith("LEGGINGS")) return LEGGINGS;
        else if (type.endsWith("BOOTS")) return BOOTS;
        return INVALID;
    }

    public static SlotType slotType(String s) {
        try {
            return SlotType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }

    public static List<String> allSlotTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (SlotType type : SlotType.values()) {
            if (type != INVALID) arrayList.add(type.toString().toLowerCase());
        }
        return arrayList;
    }
}