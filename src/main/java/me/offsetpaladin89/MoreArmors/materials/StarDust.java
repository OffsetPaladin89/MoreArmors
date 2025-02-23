package me.offsetpaladin89.MoreArmors.materials;

import me.offsetpaladin89.MoreArmors.CustomMaterial;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;

public class StarDust extends CustomMaterial {

    private static final String DEFAULT_NAME = "Star Dust";
    private static final Material MATERIAL = Material.GHAST_TEAR;
    private static final Rarity DEFAULT_RARITY = Rarity.RARE;
    private static final int UPGRADE_TIER = 0;
    private static final MaterialType MATERIAL_TYPE = MaterialType.STAR_DUST;

    public StarDust() {
        super(DEFAULT_RARITY, UPGRADE_TIER, DEFAULT_NAME, MATERIAL_TYPE);
        createItem(MATERIAL);
    }
}