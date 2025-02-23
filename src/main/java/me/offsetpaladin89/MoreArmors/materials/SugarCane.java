package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;

public class SugarCane extends CustomMaterial {

    private static final String DEFAULT_NAME = "Sugar Cane";
    private static final Material MATERIAL = Material.SUGAR_CANE;

    public SugarCane(Rarity rarity, int upgradeTier, MaterialType materialType, int amount) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType, amount);
        createItem(MATERIAL);
    }

    public static class SugarCane0 extends SugarCane {
        private static final Rarity DEFAULT_RARITY = Rarity.UNCOMMON;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.SUGAR_CANE_0;

        public SugarCane0(int amount) {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, amount);
        }
    }
}