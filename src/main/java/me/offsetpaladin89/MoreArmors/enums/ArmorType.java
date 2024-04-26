package me.offsetpaladin89.MoreArmors.enums;

import me.offsetpaladin89.MoreArmors.items.*;
import org.bukkit.inventory.ItemStack;

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
	
	public static ItemStack getItem(ArmorType type, SlotType slot, int amount) {
		return switch(type) {
			case EMERALD -> new EmeraldArmor(slot, amount).getItem();
			case END -> new EndArmor(slot).getItem();
			case EXPERIENCE -> new ExperienceArmor(slot).getItem();
			case MINER -> new MinerArmor(slot).getItem();
			case NETHER -> new NetherArmor(slot).getItem();
			case SEA_GREED -> new SeaGreedArmor(slot).getItem();
			case SPEEDSTER -> new SpeedsterArmor(slot).getItem();
			case TITAN -> new TitanArmor(slot).getItem();
			case DESTROYER -> new DestroyerArmor(slot, amount).getItem();
		};
	}
}
