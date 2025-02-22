package me.offsetpaladin89.MoreArmors.enums;

import java.util.ArrayList;
import java.util.List;

public enum ArmorType {
    EMERALD, END, EXPERIENCE, MINER, NETHER, SEA_GREED, SPEEDSTER, TITAN, DESTROYER, INVALID;

    public static ArmorType armorType(String s) {
        return switch(s.toLowerCase()) {
            case "emerald" -> EMERALD;
            case "end" -> END;
            case "destroyer" -> DESTROYER;
            case "experience" -> EXPERIENCE;
            case "miner" -> MINER;
            case "nether" -> NETHER;
            case "seagreed" -> SEA_GREED;
            case "speedster" -> SPEEDSTER;
            case "titan" -> TITAN;
            default -> INVALID;
        };
    }

    public static List<String> allArmorTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(ArmorType type : ArmorType.values()) arrayList.add(type.toString().toLowerCase());
        return arrayList;
    }
}
