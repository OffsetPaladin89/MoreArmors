package me.offsetpaladin89.MoreArmors.enums;

public enum Rarity {
    COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC, DIVINE, SPECIAL, ADMIN, DEVELOPER;

    public final String color;

    Rarity() {
        this.color = switch (ordinal()) {
            case 0 -> "&f";
            case 1 -> "&a";
            case 2 -> "&9";
            case 3 -> "&5";
            case 4 -> "&6";
            case 5 -> "&d";
            case 6 -> "&b";
            case 7 -> "&c";
            case 8 -> "&4";
            default -> "&3";
        };
    }

    public static Rarity getRarity(int id) {
        for(Rarity rarity : values()) if(rarity.ordinal() == id) return rarity;
        return DEVELOPER;
    }
}
