package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.inventory.ItemStack;

public class RedstoneBlock extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.RARE;
    private static final MaterialType MATERIAL_TYPE = MaterialType.REDSTONE_BLOCK;

    public RedstoneBlock(int tier) {
        super(BASE_RARITY, tier, MATERIAL_TYPE);
    }

    // Override Methods

    protected String getDefaultName() {
        return "Redstone Block";
    }

    protected ItemStack getPrevious(int tier) {
        return new RedstoneBlock(tier - 1).getItem();
    }
}