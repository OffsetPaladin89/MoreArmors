package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SoulSand extends CustomMaterial {

    private static final String DEFAULT_NAME = "Soul Sand";
    private static final Material MATERIAL = Material.SOUL_SAND;

    SoulSand(Rarity rarity, int upgradeTier, MaterialType materialType, ItemStack prevMaterial) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType, prevMaterial);
        createItem(MATERIAL);
    }

    public static class SoulSand0 extends SoulSand {

        private static final Rarity DEFAULT_RARITY = Rarity.UNCOMMON;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.SOUL_SAND_0;
        private static final ItemStack PREVIOUS_MATERIAL = new ItemStack(Material.SOUL_SAND);

        public SoulSand0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }

    public static class SoulSand1 extends SoulSand {

        private static final Rarity DEFAULT_RARITY = Rarity.RARE;
        private static final int UPGRADE_TIER = 1;
        private static final MaterialType MATERIAL_TYPE = MaterialType.SOUL_SAND_1;
        private static final ItemStack PREVIOUS_MATERIAL = new SoulSand0().getItem();

        public SoulSand1() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }
}