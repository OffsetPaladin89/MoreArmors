package me.offsetpaladin89.MoreArmors.enums;

import org.bukkit.inventory.ItemStack;

import static me.offsetpaladin89.MoreArmors.items.Materials.*;

public enum MaterialType {

	/** Compacted Blaze Rod Material Type */
	COMPACTED_BLAZE_ROD,

	/** Compacted Cobblestone Material Type */
	COMPACTED_COBBLESTONE,

	/** Compacted End Stone Material Type */
	COMPACTED_END_STONE,

	/** Compacted Eye of Ender Material Type */
	COMPACTED_EYE_OF_ENDER,

	/** Compacted Soul Sand Material Type */
	COMPACTED_SOUL_SAND,

	/** Compacted Sugar Cane Material Type */
	COMPACTED_SUGAR_CANE,

	/** Compacted Nether Crown Material Type */
	NETHER_CROWN,

	/** Compacted Diamond Material Type */
	COMPACTED_DIAMOND,

	/** Compacted Diamond Block Material Type */
	COMPACTED_DIAMOND_BLOCK,

	/** Compacted Gold Material Type */
	COMPACTED_GOLD,

	/** Compacted Gold Block Material Type */
	COMPACTED_GOLD_BLOCK,

	/** Compacted Prismarine Material Type */
	COMPACTED_PRISMARINE,

	/** Compacted Redstone Material Type */
	COMPACTED_REDSTONE,

	/** Compacted Iron Material Type */
	COMPACTED_IRON,

	/** Compacted Iron Block Material Type */
	COMPACTED_IRON_BLOCK,

	/** Star Dust Material Type */
	STAR_DUST,

	/** Machine Part Material Type */
	MACHINE_PART,

	/** Energy Cell Material Type */
	ENERGY_CELL,

	/** Machine Core Material Type */
	MACHINE_CORE;

	/**
	 * Returns the MaterialType depending on a String s.
	 * @param s the String to get the MaterialType
	 * @return the MaterialType depending on a String s
	 */
	public static MaterialType getMaterialType(String s) {
		return switch (s.toLowerCase()) {
			case "compacted_blaze_rod" -> COMPACTED_BLAZE_ROD;
			case "compacted_cobblestone" -> COMPACTED_COBBLESTONE;
			case "compacted_end_stone" -> COMPACTED_END_STONE;
			case "compacted_eye_of_ender" -> COMPACTED_EYE_OF_ENDER;
			case "compacted_soul_sand" -> COMPACTED_SOUL_SAND;
			case "compacted_sugar_cane" -> COMPACTED_SUGAR_CANE;
			case "nether_crown" -> NETHER_CROWN;
			case "compacted_diamond" -> COMPACTED_DIAMOND;
			case "compacted_diamond_block" -> COMPACTED_DIAMOND_BLOCK;
			case "compacted_gold" -> COMPACTED_GOLD;
			case "compacted_gold_block" -> COMPACTED_GOLD_BLOCK;
			case "compacted_prismarine" -> COMPACTED_PRISMARINE;
			case "compacted_redstone" -> COMPACTED_REDSTONE;
			case "compacted_iron" -> COMPACTED_IRON;
			case "compacted_iron_block" -> COMPACTED_IRON_BLOCK;
			case "star_dust" -> STAR_DUST;
			case "machine_part" -> MACHINE_PART;
			case "energy_cell" -> ENERGY_CELL;
			case "machine_core" -> MACHINE_CORE;
			default -> null;
		};
	}

	/**
	 * Returns the ItemStack of the specified MaterialType and Integer amount.
	 * @param type the MaterialType of the item
	 * @param amount the Integer amount of the item
	 * @return the ItemStack of the specified MaterialType and Integer amount
	 */
	public static ItemStack getItem(MaterialType type, int amount) {
		return switch(type) {
			case COMPACTED_BLAZE_ROD -> CompactedBlazeRod(amount);
			case COMPACTED_COBBLESTONE -> CompactedCobblestone(amount);
			case COMPACTED_END_STONE -> CompactedEndStone(amount);
			case COMPACTED_EYE_OF_ENDER -> CompactedEyeOfEnder(amount);
			case COMPACTED_SOUL_SAND -> CompactedSoulSand(amount);
			case COMPACTED_SUGAR_CANE -> CompactedSugarCane(amount);
			case NETHER_CROWN -> NetherCrown();
			case COMPACTED_DIAMOND -> CompactedDiamond(amount);
			case COMPACTED_DIAMOND_BLOCK -> CompactedDiamondBlock(amount);
			case COMPACTED_GOLD -> CompactedGold(amount);
			case COMPACTED_GOLD_BLOCK -> CompactedGoldBlock(amount);
			case COMPACTED_PRISMARINE -> CompactedPrismarine(amount);
			case COMPACTED_REDSTONE -> CompactedRedstone(amount);
			case COMPACTED_IRON -> CompactedIron(amount);
			case COMPACTED_IRON_BLOCK -> CompactedIronBlock(amount);
			case STAR_DUST -> StarDust(amount);
			case MACHINE_PART -> MachinePart(amount);
			case ENERGY_CELL -> EnergyCell();
			case MACHINE_CORE -> MachineCore();
		};
	}
}
