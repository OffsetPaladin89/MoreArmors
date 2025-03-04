package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class BlazeRod extends CustomMaterial {

    private static final String DEFAULT_NAME = "Blaze Rod";
    private static final Material MATERIAL = Material.BLAZE_ROD;

    BlazeRod(Rarity rarity, int upgradeTier, MaterialType materialType, ItemStack prevMaterial) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType, prevMaterial);
        createItem(MATERIAL);
    }

    public static class BlazeRod0 extends BlazeRod {

        private static final Rarity DEFAULT_RARITY = Rarity.UNCOMMON;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.BLAZE_ROD_0;
        private static final ItemStack PREVIOUS_MATERIAL = new ItemStack(Material.BLAZE_ROD);

        public BlazeRod0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }

        public ItemStack getPrev() {
            return new ItemStack(Material.BLAZE_ROD);
        }
    }

    public static class BlazeRod1 extends BlazeRod {

        private static final Rarity DEFAULT_RARITY = Rarity.RARE;
        private static final int UPGRADE_TIER = 1;
        private static final MaterialType MATERIAL_TYPE = MaterialType.BLAZE_ROD_1;
        private static final ItemStack PREVIOUS_MATERIAL = new BlazeRod0().getItem();

        public BlazeRod1() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }

}