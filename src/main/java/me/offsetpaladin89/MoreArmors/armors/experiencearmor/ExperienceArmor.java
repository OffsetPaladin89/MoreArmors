package me.offsetpaladin89.MoreArmors.armors.experiencearmor;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public record ExperienceArmor(MoreArmorsMain plugin) {

  public ItemStack ExperienceHelmet() {
    ItemStack item = new ItemStack(Material.LEATHER_HELMET);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&9Experience Helmet"));
    itemmeta.setColor(Color.BLUE);
    item.setItemMeta(itemmeta);
    item = plugin.experiencedata.addLore(item);
    item = plugin.experiencedata.addAttributes(item, 1, EquipmentSlot.HEAD);
    item = plugin.experiencedata.addFlags(item);
    item = plugin.experiencedata.addNBTTags(item);
    return item;
  }

  public ItemStack ExperienceChestplate() {
    ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&9Experience Chestplate"));
    itemmeta.setColor(Color.BLUE);
    item.setItemMeta(itemmeta);
    item = plugin.experiencedata.addLore(item);
    item = plugin.experiencedata.addAttributes(item, 5, EquipmentSlot.CHEST);
    item = plugin.experiencedata.addFlags(item);
    item = plugin.experiencedata.addNBTTags(item);
    return item;
  }

  public ItemStack ExperienceLeggings() {
    ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&9Experience Leggings"));
    itemmeta.setColor(Color.BLUE);
    item.setItemMeta(itemmeta);
    item = plugin.experiencedata.addLore(item);
    item = plugin.experiencedata.addAttributes(item, 4, EquipmentSlot.LEGS);
    item = plugin.experiencedata.addFlags(item);
    item = plugin.experiencedata.addNBTTags(item);
    return item;
  }

  public ItemStack ExperienceBoots() {
    ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&9Experience Boots"));
    itemmeta.setColor(Color.BLUE);
    item.setItemMeta(itemmeta);
    item = plugin.experiencedata.addLore(item);
    item = plugin.experiencedata.addAttributes(item, 1, EquipmentSlot.FEET);
    item = plugin.experiencedata.addFlags(item);
    item = plugin.experiencedata.addNBTTags(item);
    return item;
  }

  public void ExperienceHelmetRecipe() {
    ItemStack item = ExperienceHelmet();
    NamespacedKey key = new NamespacedKey(plugin, "experience_helmet");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "X X");
    ItemRecipe.setIngredient('X', Material.LAPIS_BLOCK);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void ExperienceChestplateRecipe() {
    ItemStack item = ExperienceChestplate();
    NamespacedKey key = new NamespacedKey(plugin, "experience_chestplate");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("X X", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.LAPIS_BLOCK);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void ExperienceLeggingsRecipe() {
    ItemStack item = ExperienceLeggings();
    NamespacedKey key = new NamespacedKey(plugin, "experience_leggings");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "X X", "X X");
    ItemRecipe.setIngredient('X', Material.LAPIS_BLOCK);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void ExperienceBootsRecipe() {
    ItemStack item = ExperienceBoots();
    NamespacedKey key = new NamespacedKey(plugin, "experience_boots");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("X X", "X X");
    ItemRecipe.setIngredient('X', Material.LAPIS_BLOCK);
    plugin.getServer().addRecipe(ItemRecipe);
  }
}
