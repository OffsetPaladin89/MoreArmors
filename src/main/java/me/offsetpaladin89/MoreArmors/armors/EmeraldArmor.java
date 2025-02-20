package me.offsetpaladin89.MoreArmors.armors;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.Lore;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.CustomItemID;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class EmeraldArmor extends CustomArmor {

    private int emeraldCount = 0;
    private int healthBoost = 0;

    private static final Color LEATHER_COLOR = Color.LIME;

    private static final CustomItemID customItemID = CustomItemID.EMERALD;

    private static final int UPGRADE_THRESHOLD = 50;
    private static final int MAX_EMERALD_COUNT = 250;

    public EmeraldArmor(ItemStack item) {
        super(item);
    }

    public EmeraldArmor(ItemStack item, String displayName, Rarity rarity, int armor, int armorToughness, int emeraldCount) {
        super(item, displayName, rarity, armor, armorToughness);

        this.emeraldCount = emeraldCount;
        this.healthBoost = 2 * emeraldCount / UPGRADE_THRESHOLD;

        createItem();

        baseNBT();
        addNBT();
    }

    public void createItem() {
        setDisplayName(item.getItemMeta().getDisplayName());

        setLeatherColor();
        setLore();

        baseAttributes();
        addAttributes();
        setAttributes();
    }

    public void createItemFromNBT() {
        NBT.get(item, nbt -> {
            rarity = nbt.getEnum("Rarity", Rarity.class);
            armor = nbt.getInteger("Armor");
            armorToughness = nbt.getInteger("ArmorToughness");
            emeraldCount = nbt.getInteger("EmeraldCount");
        });

        createItem();
    }

    public void updateItem() {
        setLeatherColor();
        setLore();

        baseAttributes();
        addAttributes();
        setAttributes();

        addNBT();
    }

    public void setEmeraldCount(int emeraldCount) {
        this.emeraldCount = emeraldCount;
    }

    public void increaseEmeraldCount(int emeraldCount) {
        this.emeraldCount += emeraldCount;
    }

    private void setLeatherColor() {
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();

        itemMeta.setColor(LEATHER_COLOR);

        item.setItemMeta(itemMeta);
    }

    public final ItemStack getItem() {
        return item;
    }

    private void setLore() {
        int currentStage = emeraldCount / UPGRADE_THRESHOLD;
        int nextBonus = healthBoost + 2;
        int upgradeProgress = emeraldCount % UPGRADE_THRESHOLD;

        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addColoredLine("&6Piece Upgrade: Emerald Blood");
        lore.addColoredLine("&7Mine emeralds to increase your max health.");
        if(emeraldCount >= MAX_EMERALD_COUNT) lore.addColoredLine("&7Current Bonus (&a5&8/&a5&7): &e+10 Health &a&lMAXED OUT");
        else {
            lore.addColoredLine(String.format("&7Current Bonus (&a%d&8/&a5&7): &e+%d Health", currentStage, healthBoost));
            lore.addColoredLine(String.format("&7Next Upgrade: &e+%d Health &8(&a%d&7/&c50&8)", nextBonus, upgradeProgress));
            lore.addColoredLine("&8Max +10 Health");
        }
        lore.addRarity(rarity);
        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }

    private void addAttributes() {
        AttributeModifier healthAttribute = new AttributeModifier(MoreArmorsMain.pluginKey, healthBoost, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);

        attributeModifiers.put(Attribute.MAX_HEALTH, healthAttribute);
    }

    private void addNBT() {
        NBT.modify(item, nbt -> {
            nbt.setInteger("EmeraldCount", emeraldCount);
            nbt.setEnum("CustomItemID", customItemID);
        });
    }
}
