package me.offsetpaladin89.MoreArmors.armors.titanarmor;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public record TitanArmor(MoreArmorsMain plugin) {

  public ItemStack TitanHelmet() {
    ItemStack item = new ItemStack(Material.IRON_HELMET, 1);
    ItemMeta itemmeta = item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&aTitan Helmet"));
    item.setItemMeta(itemmeta);
    item = plugin.titandata.addLore(item);
    item = plugin.titandata.addAttributes(item, 2, EquipmentSlot.HEAD);
    item = plugin.titandata.addFlags(item);
    item = plugin.titandata.addNBTTags(item);
    return item;
  }

  public ItemStack TitanChestplate() {
    ItemStack item = new ItemStack(Material.IRON_CHESTPLATE, 1);
    ItemMeta itemmeta = item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&aTitan Chestplate"));
    item.setItemMeta(itemmeta);
    item = plugin.titandata.addLore(item);
    item = plugin.titandata.addAttributes(item, 6, EquipmentSlot.CHEST);
    item = plugin.titandata.addFlags(item);
    item = plugin.titandata.addNBTTags(item);
    return item;
  }

  public ItemStack TitanLeggings() {
    ItemStack item = new ItemStack(Material.IRON_LEGGINGS, 1);
    ItemMeta itemmeta = item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&aTitan Leggings"));
    item.setItemMeta(itemmeta);
    item = plugin.titandata.addLore(item);
    item = plugin.titandata.addAttributes(item, 5, EquipmentSlot.LEGS);
    item = plugin.titandata.addFlags(item);
    item = plugin.titandata.addNBTTags(item);
    return item;
  }

  public ItemStack TitanBoots() {
    ItemStack item = new ItemStack(Material.IRON_BOOTS, 1);
    ItemMeta itemmeta = item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&aTitan Boots"));
    item.setItemMeta(itemmeta);
    item = plugin.titandata.addLore(item);
    item = plugin.titandata.addAttributes(item, 2, EquipmentSlot.FEET);
    item = plugin.titandata.addFlags(item);
    item = plugin.titandata.addNBTTags(item);
    return item;
  }

  public void TitanHelmetRecipe() {
    ItemStack item = TitanHelmet();
    NamespacedKey key = new NamespacedKey(plugin, "titan_helmet");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "X X");
    ItemRecipe.setIngredient('X', Material.IRON_BLOCK);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TitanChestplateRecipe() {
    ItemStack item = TitanChestplate();
    NamespacedKey key = new NamespacedKey(plugin, "titan_chestplate");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("X X", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.IRON_BLOCK);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TitanLeggingsRecipe() {
    ItemStack item = TitanLeggings();
    NamespacedKey key = new NamespacedKey(plugin, "titan_leggings");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "X X", "X X");
    ItemRecipe.setIngredient('X', Material.IRON_BLOCK);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TitanBootsRecipe() {
    ItemStack item = TitanBoots();
    NamespacedKey key = new NamespacedKey(plugin, "titan_boots");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("X X", "X X");
    ItemRecipe.setIngredient('X', Material.IRON_BLOCK);
    plugin.getServer().addRecipe(ItemRecipe);
  }
}
