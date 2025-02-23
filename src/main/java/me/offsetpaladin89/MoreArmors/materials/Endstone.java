package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;

public class Endstone extends CustomMaterial {

    private static final String DEFAULT_NAME = "Endstone";
    private static final Material MATERIAL = Material.END_STONE;

    Endstone(Rarity rarity, int upgradeTier, MaterialType materialType) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType);
        createItem(MATERIAL);
    }

    public static class Endstone0 extends Endstone {

        private static final Rarity DEFAULT_RARITY = Rarity.COMMON;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.ENDSTONE_0;

        public Endstone0(int amount) {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }

        public Endstone0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }
    }

    public static class Endstone1 extends Endstone {

        private static final Rarity DEFAULT_RARITY = Rarity.UNCOMMON;
        private static final int UPGRADE_TIER = 1;
        private static final MaterialType MATERIAL_TYPE = MaterialType.ENDSTONE_1;

        public Endstone1(int amount) {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }

        public Endstone1() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }
    }

    public static class Endstone2 extends Endstone {

        private static final Rarity DEFAULT_RARITY = Rarity.RARE;
        private static final int UPGRADE_TIER = 2;
        private static final MaterialType MATERIAL_TYPE = MaterialType.ENDSTONE_2;

        public Endstone2(int amount) {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }

        public Endstone2() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }
    }

}