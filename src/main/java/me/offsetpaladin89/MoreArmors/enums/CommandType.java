package me.offsetpaladin89.MoreArmors.enums;

import java.util.ArrayList;
import java.util.List;

public enum CommandType {
    GIVE, EDIT, RELOAD, INFO, INVALID;

    public static CommandType commandType(String s) {
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = INVALID;
        }
        return commandType;
    }

    public static List<String> allCommandTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(CommandType type : CommandType.values()) {
            if(type != INVALID) arrayList.add(type.toString().toLowerCase());
        }
        return arrayList;
    }
}
