package me.offsetpaladin89.MoreArmors.enums;

public enum Rarity {
    COMMON(0), UNCOMMON(1), RARE(2), EPIC(3), LEGENDARY(4), MYTHIC(5), DIVINE(6), SPECIAL(7), ADMIN(8), DEVELOPER(9);

    public final int id;
    public final String color;

    Rarity(int id) {
        this.id = id;
        this.color = switch (id) {
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
        for(Rarity rarity : values()) if(rarity.id == id) return rarity;
    }
}
