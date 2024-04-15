package me.offsetpaladin89.MoreArmors.enums;

public enum ArmorType {
    EMERALD, END, EXPERIENCE, MINER, NETHER, SEA_GREED, SPEEDSTER, TITAN, DESTROYER;

    public static ArmorType getSetType(String s) {
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
	        default -> null;
        };
    }

    public static String getSetName(ArmorType type) {
        return switch(type) {
	        case EMERALD -> "Emerald";
	        case END -> "End";
	        case EXPERIENCE -> "Experience";
	        case MINER -> "Miner";
	        case NETHER -> "Nether";
	        case SEA_GREED -> "Sea Greed";
	        case SPEEDSTER -> "Speedster";
	        case TITAN -> "Titan";
	        case DESTROYER -> "Destroyer";
        };
    }
}
