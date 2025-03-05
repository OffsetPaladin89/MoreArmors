package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class IronBlock extends CustomMaterial {

    private static final String DEFAULT_NAME = "Iron Block";
    private static final Material MATERIAL = Material.IRON_BLOCK;

    IronBlock(Rarity rarity, int upgradeTier, MaterialType materialType, ItemStack prevMaterial) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType, prevMaterial);
        createItem(MATERIAL);
    }

    public static class IronBlock0 extends IronBlock {
        private static final Rarity DEFAULT_RARITY = Rarity.RARE;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.IRON_BLOCK_0;
        private static final ItemStack PREVIOUS_MATERIAL = new ItemStack(Material.IRON_BLOCK);

        public IronBlock0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }

    public static class IronBlock1 extends IronBlock {
        private static final Rarity DEFAULT_RARITY = Rarity.EPIC;
        private static final int UPGRADE_TIER = 1;
        private static final MaterialType MATERIAL_TYPE = MaterialType.IRON_BLOCK_1;
        private static final ItemStack PREVIOUS_MATERIAL = new IronBlock0().getItem();

        public IronBlock1() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }

    public static class IronBlock2 extends IronBlock {
        private static final Rarity DEFAULT_RARITY = Rarity.LEGENDARY;
        private static final int UPGRADE_TIER = 2;
        private static final MaterialType MATERIAL_TYPE = MaterialType.IRON_BLOCK_2;
        private static final ItemStack PREVIOUS_MATERIAL = new IronBlock1().getItem();

        public IronBlock2() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }
}