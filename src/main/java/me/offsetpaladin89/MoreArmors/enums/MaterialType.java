package me.offsetpaladin89.MoreArmors.enums;

import java.util.ArrayList;
import java.util.List;

public enum MaterialType {

    BLAZE_ROD_0, BLAZE_ROD_1, COBBLESTONE_0, COBBLESTONE_1, COBBLESTONE_2, DIAMOND_BLOCK_0,
    ENDSTONE_0, ENDSTONE_1, ENDSTONE_2, ENERGY_CELL, EYE_OF_ENDER_0, EYE_OF_ENDER_1, GOLD_BLOCK_0,
    IRON_BLOCK_0, IRON_BLOCK_1, MACHINE_CORE, MACHINE_PART_0, MACHINE_PART_1, NETHER_CROWN,
    PRISMARINE_0, REDSTONE_BLOCK_0, SOUL_SAND_0, SOUL_SAND_1, STAR_DUST, SUGAR_CANE_0, INVALID;

    public static MaterialType materialType(String s) {
        MaterialType materialType;
        try {
            materialType = MaterialType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            materialType = INVALID;
        }
        return materialType;
    }

    public static List<String> allMaterialTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(MaterialType type : MaterialType.values()) {
            if(type != INVALID) arrayList.add(type.toString().toLowerCase());
        }
        return arrayList;
    }
}
