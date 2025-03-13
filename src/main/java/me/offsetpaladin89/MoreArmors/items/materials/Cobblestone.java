package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.inventory.ItemStack;

public class Cobblestone extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.COMMON;
    private static final MaterialType MATERIAL_TYPE = MaterialType.COBBLESTONE;

    public Cobblestone(int tier) {
        super(BASE_RARITY, tier, MATERIAL_TYPE);
    }

    // Override Methods

    protected String getDefaultName() {
        return "Cobblestone";
    }

    protected ItemStack getPrevious(int tier) {
        return new Cobblestone(tier - 1).getItem();
    }
}