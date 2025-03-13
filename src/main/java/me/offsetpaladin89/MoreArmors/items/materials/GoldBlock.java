package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.inventory.ItemStack;

public class GoldBlock extends CustomMaterial {
    private static final Rarity BASE_RARITY = Rarity.RARE;
    private static final MaterialType MATERIAL_TYPE = MaterialType.GOLD_BLOCK;

    public GoldBlock(int tier) {
        super(BASE_RARITY, tier, MATERIAL_TYPE);
    }

    // Override Methods

    protected String getDefaultName() {
        return "Gold Block";
    }

    protected ItemStack getPrevious(int tier) {
        return new GoldBlock(tier - 1).getItem();
    }
}