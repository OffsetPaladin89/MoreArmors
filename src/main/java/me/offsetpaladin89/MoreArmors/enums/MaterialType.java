package me.offsetpaladin89.MoreArmors.enums;

public enum MaterialType {
	COMPACTED_BLAZE_ROD, COMPACTED_COBBLESTONE, COMPACTED_END_STONE, COMPACTED_EYE_OF_ENDER, COMPACTED_SOUL_SAND, COMPACTED_SUGAR_CANE, NETHER_CROWN, COMPACTED_DIAMOND, COMPACTED_DIAMOND_BLOCK, COMPACTED_GOLD, COMPACTED_GOLD_BLOCK, COMPACTED_PRISMARINE, COMPACTED_REDSTONE, COMPACTED_IRON, COMPACTED_IRON_BLOCK, STAR_DUST, MACHINE_PART, ENERGY_CELL, MACHINE_CORE;

	public static MaterialType getMaterialType(String s) {
		return switch (s.toLowerCase()) {
			case "compacted_blaze_rod" -> COMPACTED_BLAZE_ROD;
			case "compacted_cobblestone" -> COMPACTED_COBBLESTONE;
			case "compacted_endstone" -> COMPACTED_END_STONE;
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
}
