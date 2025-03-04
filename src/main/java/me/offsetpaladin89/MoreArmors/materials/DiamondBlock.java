package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DiamondBlock extends CustomMaterial {

    private static final String DEFAULT_NAME = "Diamond Block";
    private static final Material MATERIAL = Material.DIAMOND_BLOCK;

    DiamondBlock(Rarity rarity, int upgradeTier, MaterialType materialType, ItemStack prevMaterial) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType, prevMaterial);
        createItem(MATERIAL);
    }

    public static class DiamondBlock0 extends DiamondBlock {
        private static final Rarity DEFAULT_RARITY = Rarity.EPIC;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.DIAMOND_BLOCK_0;
        private static final ItemStack PREVIOUS_MATERIAL = new ItemStack(Material.DIAMOND_BLOCK);

        public DiamondBlock0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }

    public static class DiamondBlock1 extends DiamondBlock {
        private static final Rarity DEFAULT_RARITY = Rarity.LEGENDARY;
        private static final int UPGRADE_TIER = 1;
        private static final MaterialType MATERIAL_TYPE = MaterialType.DIAMOND_BLOCK_1;
        private static final ItemStack PREVIOUS_MATERIAL = new DiamondBlock0().getItem();

        public DiamondBlock1() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }
}