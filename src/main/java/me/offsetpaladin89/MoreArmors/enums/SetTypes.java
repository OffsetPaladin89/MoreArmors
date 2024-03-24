package me.offsetpaladin89.MoreArmors.enums;

public enum SetTypes {
    EMERALD, END, EXPERIENCE, MINER, NETHER, SEA_GREED, SPEEDSTER, TITAN, TRUE_DIAMOND;

    public static SetTypes getSetType(String s) {
        return switch(s.toLowerCase()) {
            case "emerald" -> EMERALD;
            case "end" -> END;
            case "experience" -> EXPERIENCE;
            case "miner" -> MINER;
            case "nether" -> NETHER;
            case "seagreed" -> SEA_GREED;
            case "speedster" -> SPEEDSTER;
            case "titan" -> TITAN;
            case "true_diamond" -> TRUE_DIAMOND;
	        default -> null;
        };
    }
}
