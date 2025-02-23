package me.offsetpaladin89.MoreArmors.enums;

import java.util.ArrayList;
import java.util.List;

public enum GiveType {
    ARMOR, MATERIAL, INVALID;

    public static GiveType giveType(String s) {
        GiveType giveType;
        try {
            giveType = GiveType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            giveType = INVALID;
        }
        return giveType;
    }

    public static List<String> allGiveTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(GiveType type : GiveType.values()) {
            if(type != INVALID) arrayList.add(type.toString().toLowerCase());
        }
        return arrayList;
    }
}