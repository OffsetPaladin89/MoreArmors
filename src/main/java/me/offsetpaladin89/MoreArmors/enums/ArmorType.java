package me.offsetpaladin89.MoreArmors.enums;

import me.offsetpaladin89.MoreArmors.items.armors.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public enum ArmorType {
    EMERALD, END, EXPERIENCE, MINER, NETHER, SEA_GREED, SPEEDSTER, TITAN, DESTROYER, INVALID;

    public static ArmorType armorType(String s) {
        try {
            return ArmorType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }

    public static CustomArmor armorFromType(ArmorType type, ItemStack item) {
        return switch(type) {
            case EMERALD -> new EmeraldArmor(item);
//            case END -> new EndArmor(item);
//            case EXPERIENCE -> new ExperienceArmor(item);
//            case MINER -> new MinerArmor(item);
//            case NETHER -> new NetherArmor(item);
//            case SEA_GREED -> new SeaGreedArmor(item);
//            case SPEEDSTER -> new SpeedsterArmor(item);
//            case TITAN -> new TitanArmor(item);
            case DESTROYER -> new DestroyerArmor(item);
//            case INVALID -> null;
            default -> null;
        };
    }

    public static CustomArmor armorFromType(ArmorType type, SlotType slot) {
        return switch(type) {
            case EMERALD -> new EmeraldArmor(slot);
            case END -> new EndArmor(slot);
            case EXPERIENCE -> new ExperienceArmor(slot);
            case MINER -> new MinerArmor(slot);
            case NETHER -> new NetherArmor(slot);
            case SEA_GREED -> new SeaGreedArmor(slot);
            case SPEEDSTER -> new SpeedsterArmor(slot);
            case TITAN -> new TitanArmor(slot);
            case DESTROYER -> new DestroyerArmor(slot);
            case INVALID -> null;
        };
    }

    public static List<String> allArmorTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(ArmorType type : ArmorType.values()) {
            if(type != INVALID) arrayList.add(type.toString().toLowerCase());
        }
        return arrayList;
    }
}
