package me.offsetpaladin89.MoreArmors.armors.minerarmor;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public record MinerArmor(MoreArmorsMain plugin) {

  public ItemStack MinerHelmet() {
    ItemStack item = new ItemStack(Material.LEATHER_HELMET, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&aMiner Helmet"));
    itemmeta.setColor(Color.GRAY);
    item.setItemMeta(itemmeta);
    item = plugin.minerdata.addLore(item);
    item = plugin.minerdata.addAttributes(item, 2, EquipmentSlot.HEAD);
    item = plugin.minerdata.addFlags(item);
    item = plugin.minerdata.addNBTTags(item);
    return item;
  }

  public ItemStack MinerChestplate() {
    ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&aMiner Chestplate"));
    itemmeta.setColor(Color.GRAY);
    item.setItemMeta(itemmeta);
    item = plugin.minerdata.addLore(item);
    item = plugin.minerdata.addAttributes(item, 6, EquipmentSlot.CHEST);
    item = plugin.minerdata.addFlags(item);
    item = plugin.minerdata.addNBTTags(item);
    return item;
  }

  public ItemStack MinerLeggings() {
    ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&aMiner Leggings"));
    itemmeta.setColor(Color.GRAY);
    item.setItemMeta(itemmeta);
    item = plugin.minerdata.addLore(item);
    item = plugin.minerdata.addAttributes(item, 5, EquipmentSlot.LEGS);
    item = plugin.minerdata.addFlags(item);
    item = plugin.minerdata.addNBTTags(item);
    return item;
  }

  public ItemStack MinerBoots() {
    ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&aMiner Boots"));
    itemmeta.setColor(Color.GRAY);
    item.setItemMeta(itemmeta);
    item = plugin.minerdata.addLore(item);
    item = plugin.minerdata.addAttributes(item, 2, EquipmentSlot.FEET);
    item = plugin.minerdata.addFlags(item);
    item = plugin.minerdata.addNBTTags(item);
    return item;
  }

  public void MinerHelmetRecipe() {
    ItemStack item = MinerHelmet();
    NamespacedKey key = new NamespacedKey(plugin, "miner_helmet");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "X X");
    ItemRecipe.setIngredient('X', Material.COBBLESTONE);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void MinerChestplateRecipe() {
    ItemStack item = MinerChestplate();
    NamespacedKey key = new NamespacedKey(plugin, "miner_chestplate");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("X X", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.COBBLESTONE);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void MinerLeggingsRecipe() {
    ItemStack item = MinerLeggings();
    NamespacedKey key = new NamespacedKey(plugin, "miner_leggings");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "X X", "X X");
    ItemRecipe.setIngredient('X', Material.COBBLESTONE);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void MinerBootsRecipe() {
    ItemStack item = MinerBoots();
    NamespacedKey key = new NamespacedKey(plugin, "miner_boots");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("X X", "X X");
    ItemRecipe.setIngredient('X', Material.COBBLESTONE);
    plugin.getServer().addRecipe(ItemRecipe);
  }
}
