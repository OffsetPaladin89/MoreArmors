package me.offsetpaladin89.MoreArmors.armors.destroyerarmor;

import com.cryptomorin.xseries.SkullUtils;
import de.tr7zw.changeme.nbtapi.NBTItem;
import dev.dbassett.skullcreator.SkullCreator;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.UUID;

public record DestroyerArmor(MoreArmorsMain plugin) {
//
//  public ItemStack DestroyerHelmet(Integer killcount) {
//    ItemStack item = SkullCreator.createSkull();
//    ItemMeta itemmeta = item.getItemMeta();
//    itemmeta = SkullUtils.applySkin(itemmeta, "ea0076ab9a5c0ed8ebd08bb18137321df0fdc8abc7499465cc32221ca192ad43");
//    itemmeta.setDisplayName(plugin.convertColoredString("&dDestroyer Helmet"));
//    item.setItemMeta(itemmeta);
//    item = plugin.destroyerdata.addLore(item, killcount, ArmorType.HELMET);
//    item = plugin.destroyerdata.addAttributes(item, 3, EquipmentSlot.HEAD, killcount);
//    item = plugin.destroyerdata.addFlags(item);
//    item = plugin.destroyerdata.addNBTTags(item, killcount);
//
//    NBTItem nbtItem = new NBTItem(item);
//    nbtItem.setString("UUID", UUID.randomUUID().toString());
//    return nbtItem.getItem();
//  }
//
//  public ItemStack DestroyerChestplate(Integer killcount) {
//    ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
//    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
//    itemmeta.setDisplayName(plugin.convertColoredString("&dDestroyer Chestplate"));
//    itemmeta.setColor(Color.fromRGB(228, 232, 235));
//    item.setItemMeta(itemmeta);
//    item = plugin.destroyerdata.addLore(item, killcount, ArmorType.CHESTPLATE);
//    item = plugin.destroyerdata.addAttributes(item, 8, EquipmentSlot.CHEST, killcount);
//    item = plugin.destroyerdata.addFlags(item);
//    item = plugin.destroyerdata.addNBTTags(item, killcount);
//    return item;
//  }
//
//  public ItemStack DestroyerLeggings(Integer killcount) {
//    ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
//    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
//    itemmeta.setDisplayName(plugin.convertColoredString("&dDestroyer Leggings"));
//    itemmeta.setColor(Color.fromRGB(228, 232, 235));
//    item.setItemMeta(itemmeta);
//    item = plugin.destroyerdata.addLore(item, killcount, ArmorType.LEGGINGS);
//    item = plugin.destroyerdata.addAttributes(item, 6, EquipmentSlot.LEGS, killcount);
//    item = plugin.destroyerdata.addFlags(item);
//    item = plugin.destroyerdata.addNBTTags(item, killcount);
//    return item;
//  }
//
//  public ItemStack DestroyerBoots(Integer killcount) {
//    ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
//    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
//    itemmeta.setDisplayName(plugin.convertColoredString("&dDestroyer Boots"));
//    itemmeta.setColor(Color.fromRGB(228, 232, 235));
//    item.setItemMeta(itemmeta);
//    item = plugin.destroyerdata.addLore(item, killcount, ArmorType.BOOTS);
//    item = plugin.destroyerdata.addAttributes(item, 3, EquipmentSlot.FEET, killcount);
//    item = plugin.destroyerdata.addFlags(item);
//    item = plugin.destroyerdata.addNBTTags(item, killcount);
//    return item;
//  }
//
//  public void DestroyerHelmetRecipe() {
//    ItemStack item = DestroyerHelmet(0);
//    NamespacedKey key = new NamespacedKey(this.plugin, "destroyer_helmet");
//    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
//    ItemRecipe.shape("XXX", "VZV");
//    ItemRecipe.setIngredient('X', Material.IRON_BLOCK);
//    ItemRecipe.setIngredient('V', Material.PLAYER_HEAD);
//    ItemRecipe.setIngredient('Z', Material.PLAYER_HEAD);
//    plugin.getServer().addRecipe(ItemRecipe);
//  }
//
//  public void DestroyerChestplateRecipe() {
//    ItemStack item = DestroyerChestplate(0);
//    NamespacedKey key = new NamespacedKey(this.plugin, "destroyer_chestplate");
//    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
//    ItemRecipe.shape("VZV", "VXV", "XXX");
//    ItemRecipe.setIngredient('X', Material.IRON_BLOCK);
//    ItemRecipe.setIngredient('V', Material.PLAYER_HEAD);
//    ItemRecipe.setIngredient('Z', Material.PLAYER_HEAD);
//    plugin.getServer().addRecipe(ItemRecipe);
//  }
//
//  public void DestroyerLeggingsRecipe() {
//    ItemStack item = DestroyerLeggings(0);
//    NamespacedKey key = new NamespacedKey(this.plugin, "destroyer_leggings");
//    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
//    ItemRecipe.shape("XXX", "VZV", "V V");
//    ItemRecipe.setIngredient('X', Material.IRON_BLOCK);
//    ItemRecipe.setIngredient('V', Material.PLAYER_HEAD);
//    ItemRecipe.setIngredient('Z', Material.PLAYER_HEAD);
//    plugin.getServer().addRecipe(ItemRecipe);
//  }
//
//  public void DestroyerBootsRecipe() {
//    ItemStack item = DestroyerBoots(0);
//    NamespacedKey key = new NamespacedKey(this.plugin, "destroyer_boots");
//    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
//    ItemRecipe.shape("VZV", "X X");
//    ItemRecipe.setIngredient('X', Material.IRON_BLOCK);
//    ItemRecipe.setIngredient('V', Material.PLAYER_HEAD);
//    ItemRecipe.setIngredient('Z', Material.PLAYER_HEAD);
//    plugin.getServer().addRecipe(ItemRecipe);
//  }
//
//  public ItemStack UpdateItem(ItemStack item, Integer amount) {
//    int armor = 0;
//    EquipmentSlot slot = null;
//    switch (ArmorType.matchType(item)) {
//      case HELMET -> {
//        armor = 3;
//        slot = EquipmentSlot.HEAD;
//      }
//      case CHESTPLATE -> {
//        armor = 8;
//        slot = EquipmentSlot.CHEST;
//      }
//      case LEGGINGS -> {
//        armor = 6;
//        slot = EquipmentSlot.LEGS;
//      }
//      case BOOTS -> {
//        armor = 3;
//        slot = EquipmentSlot.FEET;
//      }
//    }
//    item = plugin.destroyerdata.addLore(item, amount, ArmorType.matchType(item));
//    item = plugin.destroyerdata.addAttributes(item, armor, slot, amount);
//    item = plugin.destroyerdata.addNBTTags(item, amount);
//    return item;
//  }
}
