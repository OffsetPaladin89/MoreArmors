package me.offsetpaladin89.MoreArmors.armors.speedsterarmor;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public record SpeedsterArmor(MoreArmorsMain plugin) {

  public ItemStack SpeedsterHelmet() {
    ItemStack item = new ItemStack(Material.LEATHER_HELMET, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&9Speedster Helmet"));
    itemmeta.setColor(Color.WHITE);
    item.setItemMeta(itemmeta);
    item = plugin.speedsterdata.addLore(item);
    item = plugin.speedsterdata.addAttributes(item, 2, EquipmentSlot.HEAD);
    item = plugin.speedsterdata.addFlags(item);
    item = plugin.speedsterdata.addNBTTags(item);
    return item;
  }

  public ItemStack SpeedsterChestplate() {
    ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&9Speedster Chestplate"));
    itemmeta.setColor(Color.WHITE);
    item.setItemMeta(itemmeta);
    item = plugin.speedsterdata.addLore(item);
    item = plugin.speedsterdata.addAttributes(item, 6, EquipmentSlot.CHEST);
    item = plugin.speedsterdata.addFlags(item);
    item = plugin.speedsterdata.addNBTTags(item);
    return item;
  }

  public ItemStack SpeedsterLeggings() {
    ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&9Speedster Leggings"));
    itemmeta.setColor(Color.WHITE);
    item.setItemMeta(itemmeta);
    item = plugin.speedsterdata.addLore(item);
    item = plugin.speedsterdata.addAttributes(item, 5, EquipmentSlot.LEGS);
    item = plugin.speedsterdata.addFlags(item);
    item = plugin.speedsterdata.addNBTTags(item);
    return item;
  }

  public ItemStack SpeedsterBoots() {
    ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&9Speedster Boots"));
    itemmeta.setColor(Color.WHITE);
    item.setItemMeta(itemmeta);
    item = plugin.speedsterdata.addLore(item);
    item = plugin.speedsterdata.addAttributes(item, 2, EquipmentSlot.FEET);
    item = plugin.speedsterdata.addFlags(item);
    item = plugin.speedsterdata.addNBTTags(item);
    return item;
  }

  public void SpeedsterHelmetRecipe() {
    ItemStack item = SpeedsterHelmet();
    NamespacedKey key = new NamespacedKey(plugin, "speedster_helmet");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "X X");
    ItemRecipe.setIngredient('X', Material.SUGAR_CANE);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void SpeedsterChestplateRecipe() {
    ItemStack item = SpeedsterChestplate();
    NamespacedKey key = new NamespacedKey(plugin, "speedster_chestplate");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("X X", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.SUGAR_CANE);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void SpeedsterLeggingsRecipe() {
    ItemStack item = SpeedsterLeggings();
    NamespacedKey key = new NamespacedKey(plugin, "speedster_leggings");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "X X", "X X");
    ItemRecipe.setIngredient('X', Material.SUGAR_CANE);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void SpeedsterBootsRecipe() {
    ItemStack item = SpeedsterBoots();
    NamespacedKey key = new NamespacedKey(plugin, "speedster_boots");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("X X", "X X");
    ItemRecipe.setIngredient('X', Material.SUGAR_CANE);
    plugin.getServer().addRecipe(ItemRecipe);
  }
}
