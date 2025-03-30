package me.offsetpaladin89.MoreArmors.items.armors;

import me.offsetpaladin89.MoreArmors.enums.*;
import me.offsetpaladin89.MoreArmors.utils.Lore;
import me.offsetpaladin89.MoreArmors.utils.skills.SkillTreeNode;
import me.offsetpaladin89.MoreArmors.utils.stats.ArmorStats;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.offsetpaladin89.MoreArmors.MoreArmorsMain.config;

public class TitanArmor extends CustomArmor {

    private static final Rarity BASE_RARITY = Rarity.UNCOMMON;

    public TitanArmor() {
        super();
        if(config.getBoolean("titan_armor.enabled")) setStats.setStat(Location.ALL, StatType.P_SCALE, 0.5d);
    }

    public TitanArmor(ItemStack item) {
        super(item);
    }

    public TitanArmor(SlotType slot) {
        super(slot);
    }

    // Override Methods

    protected ArmorType getArmorID() {
        return ArmorType.TITAN;
    }
    protected ItemStack getBaseItem() {
        return switch (slot) {
            case HELMET -> new ItemStack(Material.IRON_HELMET);
            case CHESTPLATE -> new ItemStack(Material.IRON_CHESTPLATE);
            case LEGGINGS -> new ItemStack(Material.IRON_LEGGINGS);
            case BOOTS -> new ItemStack(Material.IRON_BOOTS);
            default -> null;
        };
    }
    protected String getDefaultName() {
        return switch (slot) {
            case HELMET -> "Titan Helmet";
            case CHESTPLATE -> "Titan Chestplate";
            case LEGGINGS -> "Titan Leggings";
            case BOOTS -> "Titan Boots";
            default -> null;
        };
    }
    protected ArrayList<String> getDescription(SkillTreeNode node) {
        ArrayList<String> description = new ArrayList<>();
        switch (node.id) {
            case 0, 2, 4, 6, 7, 11, 14, 1, 3, 5, 8, 10, 12, 13 -> description.add("&eUnlocks for 1 Skill Point");
            case 9 -> description.add("&eUnlocks for 2 Skill Points");
            default -> description.add("Impossible");
        }
//        switch (node.id) {
//            case 0 -> {
//                lore.addColoredLine("&4Testing 1");
//                lore.addColoredLine("&aHello World");
//            }
//            case 1 -> {
//                lore.addColoredLine("&4Testing 2");
//                lore.addColoredLine("&aHello World");
//            }
//            case 2 -> {
//                lore.addColoredLine("&4Testing 3");
//                lore.addColoredLine("&aHello World");
//            }
//            case 3 -> {
//                lore.addColoredLine("&4Testing 4");
//                lore.addColoredLine("&aHello World");
//            }
//            case 4 -> {
//                lore.addColoredLine("&4Testing 5");
//                lore.addColoredLine("&aHello World");
//            }
//            case 5 -> {
//                lore.addColoredLine("&4Testing 6");
//                lore.addColoredLine("&aHello World");
//            }
//            case 6 -> {
//                lore.addColoredLine("&4Testing 7");
//                lore.addColoredLine("&aHello World");
//            }
//            case 7 -> {
//                lore.addColoredLine("&4Testing 8");
//                lore.addColoredLine("&aHello World");
//            }
//            case 8 -> {
//                lore.addColoredLine("&4Testing 9");
//                lore.addColoredLine("&aHello World");
//            }
//            case 9 -> {
//                lore.addColoredLine("&4Testing 10");
//                lore.addColoredLine("&aHello World");
//            }
//            case 10 -> {
//                lore.addColoredLine("&4Testing 11");
//                lore.addColoredLine("&aHello World");
//            }
//            case 11 -> {
//                lore.addColoredLine("&4Testing 12");
//                lore.addColoredLine("&aHello World");
//            }
//            case 12 -> {
//                lore.addColoredLine("&4Testing 13");
//                lore.addColoredLine("&aHello World");
//            }
//            case 13 -> {
//                lore.addColoredLine("&4Testing 14");
//                lore.addColoredLine("&aHello World");
//            }
//            case 14 -> {
//                lore.addColoredLine("&4Testing 15");
//                lore.addColoredLine("&aHello World");
//            }
//            case 15 -> {
//                lore.addColoredLine("&4Testing 16");
//                lore.addColoredLine("&aHello World");
//            }
//            case 16 -> {
//                lore.addColoredLine("&4Testing 17");
//                lore.addColoredLine("&aHello World");
//            }
//            default -> lore.addColoredLine("&5How Did You Find This?");
//        }

        return description;
    }
    protected String getDisplayName(SkillTreeNode node) {
        return switch (node.id) {
            case 0, 2, 4, 6, 7, 11, 14 -> "&aMinor Node";
            case 1, 3, 5, 8, 10, 12, 13 -> "&6Major Node";
            case 9 -> "&5Main Node";
            default -> "&4Impossible";
        };
//        return switch (node.id) {
//            case 0 -> "&5Testing 1";
//            case 1 -> "&5Testing 2";
//            case 2 -> "&5Testing 3";
//            case 3 -> "&5Testing 4";
//            case 4 -> "&5Testing 5";
//            case 5 -> "&5Testing 6";
//            case 6 -> "&5Testing 7";
//            case 7 -> "&5Testing 8";
//            case 8 -> "&5Testing 9";
//            case 9 -> "&5Testing 10";
//            case 10 -> "&6Testing 11";
//            case 11 -> "&5Testing 12";
//            case 12 -> "&5Testing 13";
//            case 13 -> "&5Testing 14";
//            case 14 -> "&5Testing 15";
//            case 15 -> "&5Testing 16";
//            case 16 -> "&5Testing 17";
//            default -> "&4How Did You Find This?";
//        };
    }
    protected Rarity getRarity() {
        return BASE_RARITY;
    }

    protected void setArmorStats() {
        double armor = switch (slot) {
            case HELMET, BOOTS -> 2;
            case CHESTPLATE -> 6;
            case LEGGINGS -> 5;
            default -> 0;
        };

        ArmorStats stats = new ArmorStats(armor);
        stats.setStat(Location.ALL, StatType.MAX_HP, 2d);

        this.stats = stats;
    }

    protected void setLore() {
        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addColoredLine("&6Item Ability: Health");
        lore.addColoredLine("&7Increases max health by &a2&7.");
        lore.addEmpty();
        lore.addColoredLine("&6Full Set Bonus: Resistance");
        lore.addColoredLine("&7Grants &aResistance I &7for &a10 seconds");
        lore.addColoredLine("&7after defeating an enemy.");
        lore.addArmorRarity(rarity);
        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }
}