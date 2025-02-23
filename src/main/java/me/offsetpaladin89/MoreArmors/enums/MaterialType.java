package me.offsetpaladin89.MoreArmors.enums;

import java.util.ArrayList;
import java.util.List;

public enum MaterialType {

    SUGAR_CANE_0, COBBLESTONE_0, COBBLESTONE_1, COBBLESTONE_2, SOUL_SAND_0, SOUL_SAND_1, BLAZE_ROD_0, BLAZE_ROD_1,
    NETHER_CROWN_0, ENDSTONE_0, ENDSTONE_1, ENDSTONE_2, EYE_OF_ENDER_0, EYE_OF_ENDER_1, DIAMOND_BLOCK_0, GOLD_BLOCK_0,
    PRISMARINE_0, REDSTONE_BLOCK_0, IRON_BLOCK_0, STAR_DUST, MACHINE_PART, ENERGY_CELL, MACHINE_CORE, INVALID;

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
