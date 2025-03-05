package me.offsetpaladin89.MoreArmors.enums;

import me.offsetpaladin89.MoreArmors.materials.CustomMaterial;
import me.offsetpaladin89.MoreArmors.materials.*;

import java.util.ArrayList;
import java.util.List;

public enum MaterialType {

    BLAZE_ROD_0, BLAZE_ROD_1, COBBLESTONE_0, COBBLESTONE_1, COBBLESTONE_2, DIAMOND_BLOCK_0,
    DIAMOND_BLOCK_1, ENDSTONE_0, ENDSTONE_1, ENDSTONE_2, ENERGY_CELL, EYE_OF_ENDER_0, EYE_OF_ENDER_1,
    GOLD_BLOCK_0, GOLD_BLOCK_1, IRON_BLOCK_0, IRON_BLOCK_1, IRON_BLOCK_2, MACHINE_CORE, MACHINE_PART_0,
    MACHINE_PART_1, NETHER_CROWN, PRISMARINE_0, PRISMARINE_1, REDSTONE_BLOCK_0, SOUL_SAND_0, SOUL_SAND_1,
    STAR_DUST, SUGAR_CANE_0, INVALID;

    public static MaterialType materialType(String s) {
        try {
            return MaterialType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }

    public static List<String> allMaterialTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(MaterialType type : MaterialType.values()) {
            if(type != INVALID) arrayList.add(type.toString().toLowerCase());
        }
        return arrayList;
    }

    public static CustomMaterial materialFromType(MaterialType type) {
        return switch (type) {
            case BLAZE_ROD_0 -> new BlazeRod.BlazeRod0();
            case BLAZE_ROD_1 -> new BlazeRod.BlazeRod1();
            case COBBLESTONE_0 -> new Cobblestone.Cobblestone0();
            case COBBLESTONE_1 -> new Cobblestone.Cobblestone1();
            case COBBLESTONE_2 -> new Cobblestone.Cobblestone2();
            case DIAMOND_BLOCK_0 -> new DiamondBlock.DiamondBlock0();
            case DIAMOND_BLOCK_1 -> new DiamondBlock.DiamondBlock1();
            case ENDSTONE_0 -> new Endstone.Endstone0();
            case ENDSTONE_1 -> new Endstone.Endstone1();
            case ENDSTONE_2 -> new Endstone.Endstone2();
            case ENERGY_CELL -> new EnergyCell();
            case EYE_OF_ENDER_0 -> new EyeOfEnder.EyeOfEnder0();
            case EYE_OF_ENDER_1 -> new EyeOfEnder.EyeOfEnder1();
            case GOLD_BLOCK_0 -> new GoldBlock.GoldBlock0();
            case GOLD_BLOCK_1 -> new GoldBlock.GoldBlock1();
            case IRON_BLOCK_0 -> new IronBlock.IronBlock0();
            case IRON_BLOCK_1 -> new IronBlock.IronBlock1();
            case IRON_BLOCK_2 -> new IronBlock.IronBlock2();
            case MACHINE_CORE -> new MachineCore();
            case MACHINE_PART_0 -> new MachinePart.MachinePart0();
            case MACHINE_PART_1 -> new MachinePart.MachinePart1();
            case NETHER_CROWN -> new NetherCrown();
            case PRISMARINE_0 -> new Prismarine.Prismarine0();
            case PRISMARINE_1 -> new Prismarine.Prismarine1();
            case REDSTONE_BLOCK_0 -> new RedstoneBlock.RedstoneBlock0();
            case SOUL_SAND_0 -> new SoulSand.SoulSand0();
            case SOUL_SAND_1 -> new SoulSand.SoulSand1();
            case STAR_DUST -> new StarDust();
            case SUGAR_CANE_0 -> new SugarCane.SugarCane0();
            case INVALID -> null;
        };
    }
}
