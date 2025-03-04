package me.offsetpaladin89.MoreArmors.enums;

import java.util.ArrayList;
import java.util.List;

public enum CommandType {
    GIVE, EDIT, RELOAD, INFO, VIEWRECIPES, INVALID;

    public static CommandType commandType(String s) {
        try {
            return CommandType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }

    public static List<String> allCommandTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(CommandType type : CommandType.values()) {
            if(type != INVALID) arrayList.add(type.toString().toLowerCase());
        }
        return arrayList;
    }
}
