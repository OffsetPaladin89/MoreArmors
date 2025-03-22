package me.offsetpaladin89.MoreArmors.enums;

import org.bukkit.World;

public enum WorldType {
    ALL("All"), OVERWORLD("Overworld"), NETHER("Nether"), END("End");

    public final String name;

    WorldType(String name) {
        this.name = name;
    }

    public static WorldType getWorldType(World.Environment env) {
        return switch (env) {
            case NORMAL -> OVERWORLD;
            case NETHER -> NETHER;
            case THE_END-> END;
            default -> ALL;
        };
    }
}
