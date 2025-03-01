package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;

public class Cobblestone extends CustomMaterial {

    private static final String DEFAULT_NAME = "Cobblestone";
    private static final Material MATERIAL = Material.COBBLESTONE;

     Cobblestone(Rarity rarity, int upgradeTier, MaterialType materialType) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType);
        createItem(MATERIAL);
    }

    public static class Cobblestone0 extends Cobblestone {

        private static final Rarity DEFAULT_RARITY = Rarity.COMMON;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.COBBLESTONE_0;

        public Cobblestone0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }
    }

    public static class Cobblestone1 extends Cobblestone {

        private static final Rarity DEFAULT_RARITY = Rarity.UNCOMMON;
        private static final int UPGRADE_TIER = 1;
        private static final MaterialType MATERIAL_TYPE = MaterialType.COBBLESTONE_1;

        public Cobblestone1() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }
    }

    public static class Cobblestone2 extends Cobblestone {

        private static final Rarity DEFAULT_RARITY = Rarity.RARE;
        private static final int UPGRADE_TIER = 2;
        private static final MaterialType MATERIAL_TYPE = MaterialType.COBBLESTONE_2;

        public Cobblestone2() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }
    }

}