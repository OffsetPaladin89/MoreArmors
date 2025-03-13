package me.offsetpaladin89.MoreArmors.items.armors;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.utils.Lore;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.utils.Util;
import me.offsetpaladin89.MoreArmors.utils.skilltree.SkillTreeNode;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class EmeraldArmor extends CustomArmor {


    private static final Color LEATHER_COLOR = Color.LIME;
    private static final Rarity BASE_RARITY = Rarity.EPIC;
    private static final int UPGRADE_THRESHOLD = 50;
    private static final int MAX_EMERALD_COUNT = 250;

    private int emeraldCount = 0;
    private int healthBoost = 0;

    public EmeraldArmor(ItemStack item) {
        super(item);
    }

    public EmeraldArmor(SlotType slot) {
        super(slot);
    }

    // Class Methods

    public void setEmeraldCount(int emeraldCount) {
        this.emeraldCount = emeraldCount;
    }

    public void increaseEmeraldCount(int emeraldCount) {
        if(this.emeraldCount == Integer.MAX_VALUE) return;
        this.emeraldCount += emeraldCount;
    }

    private void updateHealth() {
        if(emeraldCount >= 250) healthBoost = 10;
        else healthBoost = 2 * (emeraldCount / UPGRADE_THRESHOLD);
    }

    // Override Methods

    protected void armorAttributes() {
        updateHealth();
        AttributeModifier healthAttribute = new AttributeModifier(pluginKey(), healthBoost, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);

        attributeModifiers.put(Attribute.MAX_HEALTH, healthAttribute);
    }
    protected void armorNBT() {
        NBT.modify(item, nbt -> {
            nbt.setInteger("EmeraldCount", emeraldCount);
        });
    }

    protected ArmorType getArmorID() { return ArmorType.EMERALD; }
    protected ItemStack getBaseItem() {
        return switch (slot) {
            case HELMET -> new ItemStack(Material.LEATHER_HELMET);
            case CHESTPLATE -> new ItemStack(Material.LEATHER_CHESTPLATE);
            case LEGGINGS -> new ItemStack(Material.LEATHER_LEGGINGS);
            case BOOTS -> new ItemStack(Material.LEATHER_BOOTS);
            default -> null;
        };
    }
    protected int getDefaultArmor() {
        return switch (slot) {
            case HELMET, BOOTS -> 3;
            case CHESTPLATE -> 8;
            case LEGGINGS -> 6;
            default -> 0;
        };
    }
    protected int getDefaultArmorToughness() {
        return 2;
    }
    protected String getDefaultName() {
        return switch (slot) {
            case HELMET -> "Emerald Helmet";
            case CHESTPLATE -> "Emerald Chestplate";
            case LEGGINGS -> "Emerald Leggings";
            case BOOTS -> "Emerald Boots";
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
    protected void getSpecialValues() {
        emeraldCount = NBT.get(item, nbt -> (int) nbt.getInteger("EmeraldCount"));
    }

    protected void setLore() {
        updateHealth();
        int currentStage = emeraldCount / UPGRADE_THRESHOLD;
        int nextBonus = healthBoost + 2;
        int upgradeProgress = emeraldCount % UPGRADE_THRESHOLD;
        String integerLimit = emeraldCount == Integer.MAX_VALUE ? "★" : "";

        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addColoredLine("&6Piece Upgrade: Emerald Blood");
        lore.addColoredLine("&7Mine emeralds to increase your max health.");
        if(emeraldCount >= MAX_EMERALD_COUNT) {
            lore.addColoredLine("&7Current Bonus (&a5&8/&a5&7): &e+10 Health &a&lMAXED OUT");
            lore.addColoredLine(String.format("&8%s Emeralds Mined &6%s", Util.formatNumber(emeraldCount), integerLimit));
        }
        else {
            lore.addColoredLine(String.format("&7Current Bonus (&a%d&8/&a5&7): &e+%d Health", currentStage, healthBoost));
            lore.addColoredLine(String.format("&7Next Upgrade: &e+%d Health &8(&a%d&7/&c50&8)", nextBonus, upgradeProgress));
            lore.addColoredLine("&8Max +10 Health");
        }
        lore.addArmorRarity(rarity);
        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }
    protected void setTextures() {
        setLeatherColor(LEATHER_COLOR);
    }
}