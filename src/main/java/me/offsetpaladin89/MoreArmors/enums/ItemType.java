package me.offsetpaladin89.MoreArmors.enums;

import me.offsetpaladin89.MoreArmors.items.misc.CustomItem;
import me.offsetpaladin89.MoreArmors.items.misc.TierUpgraderItem;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum ItemType {
    TIER_UPGRADER(Material.EMERALD), INVALID;

    public Material baseMaterial = null;

    ItemType() {}

    ItemType(Material baseMaterial) {
        this.baseMaterial = baseMaterial;
    }

    public static ItemType itemType(String s) {
        try {
            return ItemType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }

    public static CustomItem itemFromType(ItemType type) {
        return switch(type) {
            case TIER_UPGRADER -> new TierUpgraderItem();
            case INVALID -> null;
        };
    }

    public static List<String> allItemTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(ItemType type : ItemType.values()) {
            if(type != INVALID) arrayList.add(type.toString().toLowerCase());
        }
        return arrayList;
    }
}
