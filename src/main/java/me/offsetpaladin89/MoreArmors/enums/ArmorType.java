package me.offsetpaladin89.MoreArmors.enums;

import java.util.ArrayList;
import java.util.List;

public enum ArmorType {
    EMERALD, END, EXPERIENCE, MINER, NETHER, SEA_GREED, SPEEDSTER, TITAN, DESTROYER, INVALID;

    public static ArmorType armorType(String s) {
        ArmorType armorType;
        try {
            armorType = ArmorType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            armorType = INVALID;
        }
        return armorType;
    }

    public static List<String> allArmorTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(ArmorType type : ArmorType.values()) {
            if(type != INVALID) arrayList.add(type.toString().toLowerCase());
        }
        return arrayList;
    }
}
