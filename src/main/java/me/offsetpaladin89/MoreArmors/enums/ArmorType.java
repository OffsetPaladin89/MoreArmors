package me.offsetpaladin89.MoreArmors.enums;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.items.armors.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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

    public static CustomArmor armorFromItem(ItemStack item) {
        ArmorType type = NBT.get(item, nbt -> (ArmorType) nbt.getEnum("ArmorID", ArmorType.class));
        if(type == null) return null;
        return switch(type) {
            case EMERALD -> new EmeraldArmor(item);
            case END -> new EndArmor(item);
            case EXPERIENCE -> new ExperienceArmor(item);
            case MINER -> new MinerArmor(item);
            case NETHER -> new NetherArmor(item);
            case SEA_GREED -> new SeaGreedArmor(item);
            case SPEEDSTER -> new SpeedsterArmor(item);
            case TITAN -> new TitanArmor(item);
            case DESTROYER -> new DestroyerArmor(item);
            case INVALID -> null;
        };
    }

    public static CustomArmor armorFromType(ArmorType type) {
        return switch(type) {
            case EMERALD -> new EmeraldArmor();
            case END -> new EndArmor();
            case EXPERIENCE -> new ExperienceArmor();
            case MINER -> new MinerArmor();
            case NETHER -> new NetherArmor();
            case SEA_GREED -> new SeaGreedArmor();
            case SPEEDSTER -> new SpeedsterArmor();
            case TITAN -> new TitanArmor();
            case DESTROYER -> new DestroyerArmor();
            case INVALID -> null;
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

    public static CustomArmor fullSetType(ItemStack[] armor) {
        for(ItemStack i : armor) if(i == null) return null;

        ArmorType boots = armorFromItem(armor[0]).getType(), leggings = armorFromItem(armor[1]).getType(), chestplate = armorFromItem(armor[2]).getType(), helmet = armorFromItem(armor[3]).getType();

        if(boots == leggings && leggings == chestplate && chestplate == helmet) return armorFromType(boots);

        return null;
    }

    public static List<String> allArmorTypes() {
        ArrayList<String> arrayList = new ArrayList<>();
        for(ArmorType type : ArmorType.values()) {
            if(type != INVALID) arrayList.add(type.toString().toLowerCase());
        }
        return arrayList;
    }
}
