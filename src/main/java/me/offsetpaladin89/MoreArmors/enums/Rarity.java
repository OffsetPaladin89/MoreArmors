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
}
