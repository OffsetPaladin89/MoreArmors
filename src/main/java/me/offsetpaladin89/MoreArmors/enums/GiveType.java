package me.offsetpaladin89.MoreArmors.enums;

import java.util.ArrayList;
import java.util.List;

public enum GiveType {
    ARMOR, MATERIAL, INVALID;

    public static GiveType giveType(String s) {
        return switch (s.toLowerCase()) {
            case "armor" -> ARMOR;
            case "material" -> MATERIAL;
            default -> INVALID;
        };
    }

    public static List<String> allGiveTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(GiveType type : GiveType.values()) arrayList.add(type.toString().toLowerCase());
        return arrayList;
    }
}