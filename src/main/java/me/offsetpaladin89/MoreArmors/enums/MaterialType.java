package me.offsetpaladin89.MoreArmors.enums;

public enum MaterialType {
	COMPACTED_BLAZE_ROD, COMPACTED_COBBLESTONE, COMPACTED_END_STONE, COMPACTED_EYE_OF_ENDER, COMPACTED_SOUL_SAND, COMPACTED_SUGAR_CANE, NETHER_CROWN, COMPACTED_DIAMOND, COMPACTED_DIAMOND_BLOCK, DIAMOND_SINGULARITY, COMPACTED_GOLD, COMPACTED_GOLD_BLOCK, COMPACTED_PRISMARINE;

	public static MaterialType getMaterialType(String s) {
		return switch (s.toLowerCase()) {
			case "compactedblazerod" -> COMPACTED_BLAZE_ROD;
			case "compactedcobblestone" -> COMPACTED_COBBLESTONE;
			case "compactedendstone" -> COMPACTED_END_STONE;
			case "compactedeyeofender" -> COMPACTED_EYE_OF_ENDER;
			case "compactedsoulsand" -> COMPACTED_SOUL_SAND;
			case "compactedsugarcane" -> COMPACTED_SUGAR_CANE;
			case "nethercrown" -> NETHER_CROWN;
			case "compacteddiamond" -> COMPACTED_DIAMOND;
			case "compacteddiamondblock" -> COMPACTED_DIAMOND_BLOCK;
			case "diamondsingularity" -> DIAMOND_SINGULARITY;
			case "compactedgold" -> COMPACTED_GOLD;
			case "compactedgoldblock" -> COMPACTED_GOLD_BLOCK;
			case "compactedprismarine" -> COMPACTED_PRISMARINE;
			default -> null;
		};
	}
}
