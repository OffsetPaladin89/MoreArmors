package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;

public class Prismarine extends CustomMaterial {

    private static final String DEFAULT_NAME = "Prismarine";
    private static final Material MATERIAL = Material.PRISMARINE;

    Prismarine(Rarity rarity, int upgradeTier, MaterialType materialType) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType);
        createItem(MATERIAL);
    }

    public static class Prismarine0 extends Prismarine {
        private static final Rarity DEFAULT_RARITY = Rarity.UNCOMMON;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.PRISMARINE_0;

        public Prismarine0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }
    }
}