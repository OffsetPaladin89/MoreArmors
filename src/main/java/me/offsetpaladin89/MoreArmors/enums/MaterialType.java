package me.offsetpaladin89.MoreArmors.enums;

import me.offsetpaladin89.MoreArmors.items.materials.*;

import java.util.ArrayList;
import java.util.List;

public enum MaterialType {

    BLAZE_ROD(1), COBBLESTONE(2), DIAMOND_BLOCK(1),
    ENDSTONE(2), ENERGY_CELL(0), EYE_OF_ENDER(1),
    GOLD_BLOCK(1), IRON_BLOCK(2), MACHINE_CORE(0),
    MACHINE_PART(1), NETHER_CROWN(0), PRISMARINE(1),
    REDSTONE_BLOCK(0), SOUL_SAND(1), STAR_DUST(0),
    SUGAR_CANE(0), INVALID;

    public final int maxTier;

    MaterialType() {
        this.maxTier = 0;
    }

    MaterialType(int maxTier) {
        this.maxTier = maxTier;
    }

    public static CustomMaterial getMaterial(MaterialType type, int tier) {
        return switch (type) {
            case BLAZE_ROD -> new BlazeRod(tier);
            case COBBLESTONE -> new Cobblestone(tier);
            case DIAMOND_BLOCK -> new DiamondBlock(tier);
            case ENDSTONE -> new Endstone(tier);
            case ENERGY_CELL -> new EnergyCell(tier);
            case EYE_OF_ENDER -> new EyeOfEnder(tier);
            case GOLD_BLOCK -> new GoldBlock(tier);
            case IRON_BLOCK -> new IronBlock(tier);
            case MACHINE_CORE -> new MachineCore(tier);
            case MACHINE_PART -> new MachinePart(tier);
            case NETHER_CROWN -> new NetherCrown(tier);
            case PRISMARINE -> new Prismarine(tier);
            case REDSTONE_BLOCK -> new RedstoneBlock(tier);
            case SOUL_SAND -> new SoulSand(tier);
            case STAR_DUST -> new StarDust(tier);
            case SUGAR_CANE -> new SugarCane(tier);
            default -> null;
        };
    }

    public static MaterialType materialType(String s) {
        try {
            return MaterialType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }

    public static List<String> allMaterialTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (MaterialType type : MaterialType.values()) {
            if (type != INVALID) arrayList.add(type.toString().toLowerCase());
        }
        return arrayList;
    }
}
