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
        return null;
    }
    protected String getDisplayName(SkillTreeNode node) {
        return null;
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