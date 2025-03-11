package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Endstone extends CustomMaterial {

    private static final String DEFAULT_NAME = "Endstone";
    private static final Material MATERIAL = Material.END_STONE;

    Endstone(Rarity rarity, int upgradeTier, MaterialType materialType, ItemStack prevMaterial) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType, prevMaterial);
        createItem(MATERIAL);
    }

    public static class Endstone0 extends Endstone {

        private static final Rarity DEFAULT_RARITY = Rarity.COMMON;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.ENDSTONE_0;
        private static final ItemStack PREVIOUS_MATERIAL = new ItemStack(Material.END_STONE);

        public Endstone0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }

    public static class Endstone1 extends Endstone {

        private static final Rarity DEFAULT_RARITY = Rarity.UNCOMMON;
        private static final int UPGRADE_TIER = 1;
        private static final MaterialType MATERIAL_TYPE = MaterialType.ENDSTONE_1;
        private static final ItemStack PREVIOUS_MATERIAL = new Endstone0().getItem();

        public Endstone1() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }

    public static class Endstone2 extends Endstone {

        private static final Rarity DEFAULT_RARITY = Rarity.RARE;
        private static final int UPGRADE_TIER = 2;
        private static final MaterialType MATERIAL_TYPE = MaterialType.ENDSTONE_2;
        private static final ItemStack PREVIOUS_MATERIAL = new Endstone1().getItem();

        public Endstone2() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE, PREVIOUS_MATERIAL);
        }
    }

}