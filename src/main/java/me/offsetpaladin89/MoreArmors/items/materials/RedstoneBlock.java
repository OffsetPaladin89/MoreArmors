package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RedstoneBlock extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.RARE;
    private static final String DEFAULT_NAME = "Redstone Block";
    private static final MaterialType MATERIAL_TYPE = MaterialType.REDSTONE_BLOCK;

    public RedstoneBlock(int tier) {
        super(BASE_RARITY, tier, DEFAULT_NAME, MATERIAL_TYPE);
        this.previousItem = getPrevious(tier);
        createItem(getBase());
    }

    public static ItemStack getPrevious(int tier) {
        if(tier == 0) return getBase();
        if(tier <= MATERIAL_TYPE.maxTier) return new RedstoneBlock(tier - 1).getItem();
        return null;
    }

    private static ItemStack getBase() {
        return new ItemStack(Material.REDSTONE_BLOCK);
    }
}