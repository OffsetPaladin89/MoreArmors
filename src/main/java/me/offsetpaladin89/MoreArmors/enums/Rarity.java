package me.offsetpaladin89.MoreArmors.enums;

public enum Rarity {
    COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC, DIVINE, SPECIAL, ADMIN, DEVELOPER;

    public static String getColorRarity(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> "&f";
            case UNCOMMON -> "&a";
            case RARE -> "&9";
            case EPIC -> "&5";
            case LEGENDARY -> "&6";
            case MYTHIC -> "&d";
            case DIVINE -> "&b";
            case SPECIAL -> "&c";
            case ADMIN -> "&4";
            default -> "&3";
        };
    }

    public static Rarity getRarity(Integer rarity) {
        return switch (rarity) {
            case 1 -> COMMON;
            case 2 -> UNCOMMON;
            case 3 -> RARE;
            case 4 -> EPIC;
            case 5 -> LEGENDARY;
            case 6 -> MYTHIC;
            case 7 -> DIVINE;
            case 8 -> SPECIAL;
            case 9 -> ADMIN;
            default -> DEVELOPER;
        };
    }
}
