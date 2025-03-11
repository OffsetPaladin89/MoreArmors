package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.Material;

import java.util.UUID;

public class NetherCrown extends CustomMaterial {

    private static final String DEFAULT_NAME = "Nether Crown";
    private static final Material MATERIAL = Material.PLAYER_HEAD;
    private static final Rarity DEFAULT_RARITY = Rarity.EPIC;
    private static final int UPGRADE_TIER = 0;
    private static final MaterialType MATERIAL_TYPE = MaterialType.NETHER_CROWN;
    private static final UUID SKULL_UUID = UUID.nameUUIDFromBytes("NETHER_CROWN".getBytes());

    public NetherCrown() {
        super(DEFAULT_RARITY, UPGRADE_TIER, DEFAULT_NAME, MATERIAL_TYPE);
        createItem(MATERIAL);

        assignSkull();
    }

    private void assignSkull() {
        Util.modifySkullSkin(item, "3e4f49535a276aacc4dc84133bfe81be5f2a4799a4c04d9a4ddb72d819ec2b2b", SKULL_UUID);
    }
}
