package me.offsetpaladin89.MoreArmors.enums;

import org.bukkit.entity.Entity;

import java.util.ArrayList;

public enum Location {
    ALL("All"), OVERWORLD("Overworld"), NETHER("Nether"), END("End"), IN_WATER("Water");

    public final String name;

    Location(String name) {
        this.name = name;
    }

    public static ArrayList<Location> getLocation(Entity e) {

        ArrayList<Location> locations = new ArrayList<>();

        if(e.isInWater()) locations.add(IN_WATER);

        switch (e.getLocation().getWorld().getEnvironment()) {
            case NORMAL -> locations.add(OVERWORLD);
            case NETHER -> locations.add(NETHER);
            case THE_END -> locations.add(END);
        }

        locations.add(ALL);

        return locations;
    }
}
