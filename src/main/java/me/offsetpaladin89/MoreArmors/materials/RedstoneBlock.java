package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;

public class RedstoneBlock extends CustomMaterial {

    private static final String DEFAULT_NAME = "Redstone Block";
    private static final Material MATERIAL = Material.REDSTONE_BLOCK;

    RedstoneBlock(Rarity rarity, int upgradeTier, MaterialType materialType) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType);
        createItem(MATERIAL);
    }

    public static class RedstoneBlock0 extends RedstoneBlock {
        private static final Rarity DEFAULT_RARITY = Rarity.RARE;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.REDSTONE_BLOCK_0;

        public RedstoneBlock0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }
    }
}