package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.inventory.ItemStack;

public class EyeOfEnder extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.UNCOMMON;
    private static final MaterialType MATERIAL_TYPE = MaterialType.EYE_OF_ENDER;

    public EyeOfEnder(int tier) {
        super(BASE_RARITY, tier, MATERIAL_TYPE);
    }

    // Override Methods

    protected String getDefaultName() {
        return "Eye of Ender";
    }

    protected ItemStack getPrevious(int tier) {
        return new EyeOfEnder(tier - 1).getItem();
    }
}