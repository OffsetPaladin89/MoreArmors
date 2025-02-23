package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;

import java.util.UUID;

public class MachineCore extends CustomMaterial {

    private static final String DEFAULT_NAME = "Machine Core";
    private static final Material MATERIAL = Material.PLAYER_HEAD;
    private static final Rarity DEFAULT_RARITY = Rarity.MYTHIC;
    private static final int UPGRADE_TIER = 0;
    private static final MaterialType MATERIAL_TYPE = MaterialType.MACHINE_CORE;
    private static final UUID SKULL_UUID = UUID.nameUUIDFromBytes("MACHINE_CORE".getBytes());

    public MachineCore() {
        super(DEFAULT_RARITY, UPGRADE_TIER, DEFAULT_NAME, MATERIAL_TYPE);
        createItem(MATERIAL);

        assignSkull();
    }

    private void assignSkull() {
        MoreArmorsMain.modifySkullSkin(item, "76856a8f37b6c3146854f2caa7101b9dd592f4669a3c75f941e2859552bd1ae8", SKULL_UUID);
    }
}
