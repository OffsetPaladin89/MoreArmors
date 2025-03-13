package me.offsetpaladin89.MoreArmors.enums;

import me.offsetpaladin89.MoreArmors.items.materials.*;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;


public enum MaterialType {

    BLAZE_ROD(1, Material.BLAZE_ROD), COBBLESTONE(2, Material.COBBLESTONE), DIAMOND_BLOCK(1, Material.DIAMOND_BLOCK),
    ENDSTONE(2, Material.END_STONE), ENERGY_CELL(Material.PLAYER_HEAD, false), EYE_OF_ENDER(1, Material.ENDER_EYE),
    GOLD_BLOCK(1, Material.GOLD_BLOCK), IRON_BLOCK(2, Material.IRON_BLOCK), MACHINE_CORE(Material.PLAYER_HEAD, false),
    MACHINE_PART(1, Material.PLAYER_HEAD, false), NETHER_CROWN(Material.PLAYER_HEAD, false), PRISMARINE(1, Material.PRISMARINE),
    REDSTONE_BLOCK(Material.REDSTONE_BLOCK), SOUL_SAND(1, Material.SOUL_SAND), STAR_DUST(Material.GHAST_TEAR, false),
    SUGAR_CANE(Material.SUGAR_CANE), INVALID;

    public int maxTier = 0;
    public Material baseMaterial = null;
    public boolean isBasic = true;

    MaterialType() {}

    MaterialType(Material baseMaterial) {
        this.baseMaterial = baseMaterial;
    }

    MaterialType(Material baseMaterial, boolean isBasic) {
        this.baseMaterial = baseMaterial;
        this.isBasic = isBasic;
    }

    MaterialType(int maxTier, Material baseMaterial) {
        this.maxTier = maxTier;
        this.baseMaterial = baseMaterial;
    }

    MaterialType(int maxTier, Material baseMaterial, boolean isBasic) {
        this.maxTier = maxTier;
        this.baseMaterial = baseMaterial;
        this.isBasic = isBasic;
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
