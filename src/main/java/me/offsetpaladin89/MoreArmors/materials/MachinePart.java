package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;

import java.util.UUID;

public class MachinePart extends CustomMaterial {

    private static final String DEFAULT_NAME = "Machine Part";
    private static final Material MATERIAL = Material.PLAYER_HEAD;
    protected UUID SKULL_UUID = UUID.nameUUIDFromBytes("MACHINE_PART".getBytes());

    MachinePart(Rarity rarity, int upgradeTier, MaterialType materialType) {
        super(rarity, upgradeTier, DEFAULT_NAME, materialType);
        createItem(MATERIAL);
        assignSkull();
    }

    public static class MachinePart0 extends MachinePart {
        private static final Rarity DEFAULT_RARITY = Rarity.EPIC;
        private static final int UPGRADE_TIER = 0;
        private static final MaterialType MATERIAL_TYPE = MaterialType.MACHINE_PART_0;

        public MachinePart0() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }
    }

    public static class MachinePart1 extends MachinePart {
        private static final Rarity DEFAULT_RARITY = Rarity.LEGENDARY;
        private static final int UPGRADE_TIER = 1;
        private static final MaterialType MATERIAL_TYPE = MaterialType.MACHINE_PART_1;

        public MachinePart1() {
            super(DEFAULT_RARITY, UPGRADE_TIER, MATERIAL_TYPE);
        }
    }

    private void assignSkull() {
        MoreArmorsMain.modifySkullSkin(item, "6131a36e70ffaa7ca7e672ae6ac20b7fc1e457c43a8e1069e7b14ecdb8576", SKULL_UUID);
    }
}
