package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.inventory.ItemStack;

public class BlazeRod extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.UNCOMMON;
    private static final MaterialType MATERIAL_TYPE = MaterialType.BLAZE_ROD;

    public BlazeRod(int tier) {
        super(BASE_RARITY, tier, MATERIAL_TYPE);
    }

    // Override Methods

    protected String getDefaultName() { return "Blaze Rod"; }

    protected ItemStack getPrevious(int tier) {
        return new BlazeRod(tier - 1).getItem();
    }
}