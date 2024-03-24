package me.offsetpaladin89.MoreArmors.materials;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public record MaterialsData(MoreArmorsMain plugin) {

  public ItemStack addInfo(ItemStack item, Rarity rarity, String itemName, String itemid) {
    item = addName(item, rarity, itemName);
    item = addLore(item, rarity);
    item = addGlowHide(item);
    item = addNBTTags(item, itemid);
    return item;
  }

  public ItemStack addName(ItemStack item, Rarity rarity, String itemName) {
    ItemMeta itemmeta = item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + itemName));
    item.setItemMeta(itemmeta);
    return item;
  }

  public ItemStack addLore(ItemStack item, Rarity rarity) {
    ItemMeta itemmeta = item.getItemMeta();
    ArrayList<String> lore = new ArrayList<>();
    lore.add("");
    lore.add(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "&l" + rarity.toString() + " MATERIAL"));
    itemmeta.setLore(lore);
    item.setItemMeta(itemmeta);
    return item;
  }

  public ItemStack addGlowHide(ItemStack item) {
    ItemMeta itemmeta = item.getItemMeta();
    itemmeta.addEnchant(Enchantment.MENDING, 1, false);
    itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    item.setItemMeta(itemmeta);
    return item;
  }

  public ItemStack addNBTTags(ItemStack item, String itemid) {
    NBTItem nbtItem = new NBTItem(item);
    nbtItem.setBoolean("IsCustomItem", true);
    nbtItem.setString("CustomItemID", itemid);
    nbtItem.setString("CustomItemType", "material");
    return nbtItem.getItem();
  }
}
