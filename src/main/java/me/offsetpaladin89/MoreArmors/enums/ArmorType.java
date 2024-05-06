package me.offsetpaladin89.MoreArmors.enums;

import me.offsetpaladin89.MoreArmors.items.*;
import org.bukkit.inventory.ItemStack;

public enum ArmorType {
    /** Emerald armor type */
	EMERALD,

	/** End armor type */
	END,

	/** Experience armor type */
	EXPERIENCE,

	/** Miner armor type */
	MINER,

	/** Nether armor type */
	NETHER,

	/** Sea Greed armor type */
	SEA_GREED,

	/** Speedster armor type */
	SPEEDSTER,

	/** Titan armor type */
	TITAN,

	/** Destroyer armor type */
	DESTROYER;

	/**
	 * Returns the ArmorType given a string.
	 * @param s the name of the armor
	 * @return the ArmorType given a string
	 */
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

	/**
	 * Returns the name of the specified ArmorType.
	 * @param type the ArmorType to search for
	 * @return the name of the specified ArmorType
	 */
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

	/**
	 * Returns the base item with the specified ArmorType, SlotType and an amount.
	 * @param type the ArmorType to get the item for
	 * @param slot the SlotType to get the item for
	 * @param amount the special amount, this argument is optional/can be 0
	 * @return the base item with the specified ArmorType, SlotType and an amount
	 */
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
