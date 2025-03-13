package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.inventory.ItemStack;

public class DiamondBlock extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.EPIC;
    private static final MaterialType MATERIAL_TYPE = MaterialType.DIAMOND_BLOCK;

    public DiamondBlock(int tier) {
        super(BASE_RARITY, tier, MATERIAL_TYPE);
    }

    // Override Methods

    protected String getDefaultName() {
        return "Diamond Block";
    }

    protected ItemStack getPrevious(int tier) {
        return new DiamondBlock(tier - 1).getItem();
    }
}