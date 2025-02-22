package me.offsetpaladin89.MoreArmors.enums;

import java.util.ArrayList;
import java.util.List;

public enum CommandType {
    GIVE, EDIT, RELOAD, INFO, INVALID;

    public static CommandType commandType(String s) {
        return switch (s.toLowerCase()) {
            case "give" -> GIVE;
            case "edit" -> EDIT;
            case "reload" -> RELOAD;
            case "info" -> INFO;
            default -> INVALID;
        };
    }

    public static List<String> allCommandTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(CommandType type : CommandType.values()) arrayList.add(type.toString().toLowerCase());
        return arrayList;
    }
}
