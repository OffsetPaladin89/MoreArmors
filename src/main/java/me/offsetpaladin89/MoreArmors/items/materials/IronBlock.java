package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.inventory.ItemStack;

public class IronBlock extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.RARE;
    private static final MaterialType MATERIAL_TYPE = MaterialType.IRON_BLOCK;

    public IronBlock(int tier) {
        super(BASE_RARITY, tier, MATERIAL_TYPE);
    }

    // Override Methods

    protected String getDefaultName() {
        return "Iron Block";
    }

    protected ItemStack getPrevious(int tier) {
        return new IronBlock(tier - 1).getItem();
    }
}