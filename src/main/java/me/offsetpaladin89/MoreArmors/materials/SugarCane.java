package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SugarCane extends CustomMaterial {

    private static final String DEFAULT_NAME = "Sugar Cane";
    private static final Material MATERIAL = Material.SUGAR_CANE;

    SugarCane(Rarity rarity, int upgradeTier, MaterialType materialType, ItemStack prevMaterial) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType, prevMaterial);
        createItem(MATERIAL);
    }

    public static class SugarCane0 extends SugarCane {
        private static final Rarity DEFAULT_RARITY = Rarity.UNCOMMON;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.SUGAR_CANE_0;
        private static final ItemStack PREVIOUS_MATERIAL = new ItemStack(Material.SUGAR_CANE);

        public SugarCane0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }
}