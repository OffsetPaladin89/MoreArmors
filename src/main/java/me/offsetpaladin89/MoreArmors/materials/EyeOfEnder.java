package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EyeOfEnder extends CustomMaterial {

    private static final String DEFAULT_NAME = "Eye of Ender";
    private static final Material MATERIAL = Material.ENDER_EYE;

    EyeOfEnder(Rarity rarity, int upgradeTier, MaterialType materialType, ItemStack prevMaterial) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType, prevMaterial);
        createItem(MATERIAL);
    }

    public static class EyeOfEnder0 extends EyeOfEnder {

        private static final Rarity DEFAULT_RARITY = Rarity.UNCOMMON;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.EYE_OF_ENDER_0;
        private static final ItemStack PREVIOUS_MATERIAL = new ItemStack(Material.ENDER_EYE);

        public EyeOfEnder0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }

    public static class EyeOfEnder1 extends EyeOfEnder {

        private static final Rarity DEFAULT_RARITY = Rarity.RARE;
        private static final int UPGRADE_TIER = 1;
        private static final MaterialType MATERIAL_TYPE = MaterialType.EYE_OF_ENDER_1;
        private static final ItemStack PREVIOUS_MATERIAL = new EyeOfEnder0().getItem();

        public EyeOfEnder1() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }
}