package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;

public class BlazeRod extends CustomMaterial {

    private static final String DEFAULT_NAME = "Blaze Rod";
    private static final Material MATERIAL = Material.BLAZE_ROD;

    BlazeRod(Rarity rarity, int upgradeTier, MaterialType materialType) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType);
        createItem(MATERIAL);
    }

    public static class BlazeRod0 extends BlazeRod {

        private static final Rarity DEFAULT_RARITY = Rarity.UNCOMMON;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.BLAZE_ROD_0;

        public BlazeRod0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }
    }

    public static class BlazeRod1 extends BlazeRod {

        private static final Rarity DEFAULT_RARITY = Rarity.RARE;
        private static final int UPGRADE_TIER = 1;
        private static final MaterialType MATERIAL_TYPE = MaterialType.BLAZE_ROD_1;

        public BlazeRod1() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }
    }

}