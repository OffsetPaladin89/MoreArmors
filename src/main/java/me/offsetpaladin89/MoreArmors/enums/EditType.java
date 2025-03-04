package me.offsetpaladin89.MoreArmors.enums;

import java.util.ArrayList;
import java.util.List;

public enum EditType {
    EMERALD_COUNT, KILL_COUNT, INVALID;

    public static EditType editType(String s) {
        try {
            return EditType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }

    public static List<String> allEditTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(EditType type : EditType.values()) {
            if(type != INVALID) arrayList.add(type.toString().toLowerCase());
        }
        return arrayList;
    }
}
