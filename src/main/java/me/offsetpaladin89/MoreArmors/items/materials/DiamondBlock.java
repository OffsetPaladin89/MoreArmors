package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DiamondBlock extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.EPIC;
    private static final String DEFAULT_NAME = "Diamond Block";
    private static final MaterialType MATERIAL_TYPE = MaterialType.DIAMOND_BLOCK;

    public DiamondBlock(int tier) {
        super(BASE_RARITY, tier, DEFAULT_NAME, MATERIAL_TYPE);
        this.previousItem = getPrevious(tier);
        createItem(getBase());
    }

    public static ItemStack getPrevious(int tier) {
        if(tier == 0) return getBase();
        if(tier <= MATERIAL_TYPE.maxTier) return new DiamondBlock(tier - 1).getItem();
        return null;
    }

    private static ItemStack getBase() {
        return new ItemStack(Material.DIAMOND_BLOCK);
    }
}