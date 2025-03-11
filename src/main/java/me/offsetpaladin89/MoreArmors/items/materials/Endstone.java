package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Endstone extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.COMMON;
    private static final String DEFAULT_NAME = "Endstone";
    private static final MaterialType MATERIAL_TYPE = MaterialType.ENDSTONE;

    public Endstone(int tier) {
        super(BASE_RARITY, tier, DEFAULT_NAME, MATERIAL_TYPE);
        this.previousItem = getPrevious(tier);
        createItem(getBase());
    }

    public static ItemStack getPrevious(int tier) {
        if(tier == 0) return getBase();
        if(tier <= MATERIAL_TYPE.maxTier) return new Endstone(tier - 1).getItem();
        return null;
    }

    private static ItemStack getBase() {
        return new ItemStack(Material.END_STONE);
    }
}