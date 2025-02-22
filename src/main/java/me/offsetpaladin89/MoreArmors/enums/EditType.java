package me.offsetpaladin89.MoreArmors.enums;

import java.util.ArrayList;
import java.util.List;

public enum EditType {
    EMERALD_COUNT, KILL_COUNT, INVALID;

    public static EditType editType(String s) {
        return switch (s.toLowerCase()) {
            case "emerald_count" -> EMERALD_COUNT;
            case "kill_count" -> KILL_COUNT;
            default -> INVALID;
        };
    }

    public static List<String> allEditTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(EditType type : EditType.values()) arrayList.add(type.toString().toLowerCase());
        return arrayList;
    }
}
