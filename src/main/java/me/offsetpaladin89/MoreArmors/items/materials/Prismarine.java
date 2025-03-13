package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.inventory.ItemStack;

public class Prismarine extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.UNCOMMON;
    private static final MaterialType MATERIAL_TYPE = MaterialType.PRISMARINE;

    public Prismarine(int tier) {
        super(BASE_RARITY, tier, MATERIAL_TYPE);
    }

    // Override Methods

    protected String getDefaultName() {
        return "Prismarine";
    }

    protected ItemStack getPrevious(int tier) {
        return new Prismarine(tier - 1).getItem();
    }
}