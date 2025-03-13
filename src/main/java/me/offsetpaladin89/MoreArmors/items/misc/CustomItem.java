package me.offsetpaladin89.MoreArmors.items.misc;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.enums.ItemType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem {

    protected ItemStack item;
    protected Rarity rarity = Rarity.DEVELOPER;
    protected String displayName = "Offset";
    protected ItemType itemID;
    protected int tier = 0;
    protected Material baseMaterial;

    protected CustomItem(Rarity rarity) {
        this.itemID = getItemID();
        this.baseMaterial = itemID.baseMaterial;
        this.rarity = rarity;

        this.item = new ItemStack(baseMaterial);

        createItem();
    }

    protected CustomItem(Rarity rarity, int tier) {
        this.rarity = rarity;
        this.tier = tier;
    }

    protected CustomItem() {}

    public ItemStack getItem() {
        return item;
    }

    protected void addGlowing() {
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.addEnchant(Enchantment.MENDING, 1, false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(itemMeta);
    }

    protected void setDisplayName() {
        this.displayName = getFormattedName(getDefaultName());

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);
    }

    // Overridden Methods

    protected void baseNBT() {
        NBT.modify(item, nbt -> {
            nbt.setEnum("Rarity", rarity);
            nbt.setEnum("ItemID", itemID);
        });
    }
    protected void createItem() {
        setDisplayName();
        setLore();
        addGlowing();

        baseNBT();
    }
    protected void setLore() {}
    protected ItemType getItemID() {
        return ItemType.INVALID;
    }
    protected String getDefaultName() { return null; }
    protected String getFormattedName(String displayName) {
        return Util.colorString(String.format("%s%s &b(+%d)", rarity.color, displayName, tier));
    }
}