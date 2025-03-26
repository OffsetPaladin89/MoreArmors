package me.offsetpaladin89.MoreArmors.items.armors;

import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.enums.Location;
import me.offsetpaladin89.MoreArmors.utils.Lore;
import me.offsetpaladin89.MoreArmors.utils.Util;
import me.offsetpaladin89.MoreArmors.utils.skills.SkillTreeNode;
import me.offsetpaladin89.MoreArmors.utils.stats.ArmorStats;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.offsetpaladin89.MoreArmors.MoreArmorsMain.config;
import static me.offsetpaladin89.MoreArmors.enums.SlotType.HELMET;

public class SeaGreedArmor extends CustomArmor {

    private static final Color LEATHER_COLOR = Color.fromRGB(130, 140, 100);
    private static final Rarity BASE_RARITY = Rarity.MYTHIC;

    public SeaGreedArmor() {
        super();
        if(config.getBoolean("sea_greed.enabled")) setStats.setDamageMultiplier(1d, Location.OVERWORLD);
    }

    public SeaGreedArmor(ItemStack item) {
        super(item);
    }

    public SeaGreedArmor(SlotType slot) {
        super(slot);
    }

    // Override Methods

    protected ArmorType getArmorID() { return ArmorType.SEA_GREED; }
    protected ItemStack getBaseItem() {
        return switch (slot) {
            case HELMET -> new ItemStack(Material.PLAYER_HEAD);
            case CHESTPLATE -> new ItemStack(Material.LEATHER_CHESTPLATE);
            case LEGGINGS -> new ItemStack(Material.LEATHER_LEGGINGS);
            case BOOTS -> new ItemStack(Material.LEATHER_BOOTS);
            default -> null;
        };
    }
    protected String getDefaultName() {
        return switch (slot) {
            case HELMET -> "Sea Greed Helmet";
            case CHESTPLATE -> "Sea Greed Chestplate";
            case LEGGINGS -> "Sea Greed Leggings";
            case BOOTS -> "Sea Greed Boots";
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
            case HELMET, BOOTS -> 3;
            case CHESTPLATE -> 8;
            case LEGGINGS -> 6;
            default -> 0;
        };
        double armorToughness = 2;
        double submergedMiningSpeed = 1;

        ArmorStats armorStats = new ArmorStats(armor, armorToughness);
        armorStats.setSubmergedMiningSpeed(submergedMiningSpeed);

        this.armorStats = armorStats;
    }
    protected void setLore() {
        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addColoredLine("&6Item Ability: Ore Greed");
        lore.addColoredLine("&7Gain &a+50% &7additional ore drops while in water.");
        lore.addColoredLine("&7Gain &a+100% &7mining speed while underwater.");
        lore.addEmpty();
        lore.addColoredLine("&6Full Set Bonus: Sea's Greed");
        lore.addColoredLine("&7Deal &c+100% &7damage in the &aOverworld&7.");
        lore.addColoredLine("&7Gain &a+200% &7swim speed while in water.");
        lore.addColoredLine("&7Gain &5Conduit Power &7while in water.");
        lore.addEmpty();
        lore.addColoredLine("&4Special Item Ability: &cBlessing of the Sea God");
        lore.addColoredLine("&7There is a &a25% &7chance when killing an &5Elder Guardian");
        lore.addColoredLine("&7to trigger &4&lBlessing of the Sea God &7granting:");
        lore.addColoredLine("&7- &a25 &7Diamond Blocks");
        lore.addColoredLine("&7- &a100 &7Gold Blocks");
        lore.addColoredLine("&7- &aHaste III &7for &a1 hour");
        lore.addColoredLine("&7- &aStrength II &7for &a1 hour");
        lore.addColoredLine("&7- &aSpeed II &7for &a1 hour");
        lore.addColoredLine("&7- &aResistance II &7for &a1 hour");
        lore.addArmorRarity(rarity);
        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }
    protected void setTextures() {
        if (slot.equals(HELMET)) Util.modifySkullSkin(item, "935541523f14c78d8de98cc296c798f0b867ba85344ed77f6dae12455a174", null);
        else setLeatherColor(LEATHER_COLOR);
    }
}
