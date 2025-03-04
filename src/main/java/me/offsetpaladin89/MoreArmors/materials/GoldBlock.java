package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GoldBlock extends CustomMaterial {

    private static final String DEFAULT_NAME = "Gold Block";
    private static final Material MATERIAL = Material.GOLD_BLOCK;

    GoldBlock(Rarity rarity, int upgradeTier, MaterialType materialType, ItemStack prevMaterial) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType, prevMaterial);
        createItem(MATERIAL);
    }

    public static class GoldBlock0 extends GoldBlock {
        private static final Rarity DEFAULT_RARITY = Rarity.RARE;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.GOLD_BLOCK_0;
        private static final ItemStack PREVIOUS_MATERIAL = new ItemStack(Material.GOLD_BLOCK);

        public GoldBlock0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }

    public static class GoldBlock1 extends GoldBlock {
        private static final Rarity DEFAULT_RARITY = Rarity.EPIC;
        private static final int UPGRADE_TIER = 1;
        private static final MaterialType MATERIAL_TYPE = MaterialType.GOLD_BLOCK_1;
        private static final ItemStack PREVIOUS_MATERIAL = new GoldBlock0().getItem();


        public GoldBlock1() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }
}