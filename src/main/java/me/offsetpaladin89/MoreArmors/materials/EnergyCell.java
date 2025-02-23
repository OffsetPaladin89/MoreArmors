package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;

import java.util.UUID;

public class EnergyCell extends CustomMaterial {

    private static final String DEFAULT_NAME = "Energy Cell";
    private static final Material MATERIAL = Material.PLAYER_HEAD;
    private static final Rarity DEFAULT_RARITY = Rarity.LEGENDARY;
    private static final int UPGRADE_TIER = 0;
    private static final MaterialType MATERIAL_TYPE = MaterialType.ENERGY_CELL;
    private static final UUID SKULL_UUID = UUID.nameUUIDFromBytes("ENERGY_CELL".getBytes());

    public EnergyCell() {
        super(DEFAULT_RARITY, UPGRADE_TIER, DEFAULT_NAME, MATERIAL_TYPE);
        createItem(MATERIAL);

        assignSkull();
    }

    private void assignSkull() {
        MoreArmorsMain.modifySkullSkin(item, "9ac52419b99025828c89fa825945e6948e45bb5a22e4425a59e9096e4c1ac38", SKULL_UUID);
    }
}
