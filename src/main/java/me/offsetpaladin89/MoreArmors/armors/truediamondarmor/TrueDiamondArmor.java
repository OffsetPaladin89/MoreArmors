package me.offsetpaladin89.MoreArmors.armors.truediamondarmor;

import com.cryptomorin.xseries.SkullUtils;
import com.github.fracpete.romannumerals4j.RomanNumeralFormat;
import de.tr7zw.changeme.nbtapi.NBTItem;
import dev.dbassett.skullcreator.SkullCreator;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.UUID;

public record TrueDiamondArmor(MoreArmorsMain plugin) {

  public ItemStack TrueDiamondHelmet(Integer level, Integer diamondsacrifice) {
    int rarity = (int) Math.floor(((level - 1) / 4)) + 3;
    ItemStack item = SkullCreator.createSkull();
    ItemMeta itemmeta = item.getItemMeta();
    itemmeta = SkullUtils.applySkin(itemmeta, "77b9dfd281deaef2628ad5840d45bcda436d6626847587f3ac76498a51c861");
    RomanNumeralFormat romanNumeralFormat = new RomanNumeralFormat();
    itemmeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "Level " + romanNumeralFormat.format(level) + " True Diamond Helmet"));
    item.setItemMeta(itemmeta);
    item = plugin.truediamonddata.addLore(item, level, diamondsacrifice);
    item = plugin.truediamonddata.addAttributes(item, 3.0F, EquipmentSlot.HEAD, level, diamondsacrifice);
    item = plugin.truediamonddata.addFlags(item);
    item = plugin.truediamonddata.addNBTTags(item, level, diamondsacrifice);

    NBTItem nbtItem = new NBTItem(item);
    nbtItem.setString("UUID", UUID.randomUUID().toString());
    return nbtItem.getItem();
  }

  public ItemStack TrueDiamondChestplate(Integer level, Integer diamondsacrifice) {
    Integer rarity = (int) Math.floor(((level - 1) / 4)) + 3;
    ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    RomanNumeralFormat romanNumeralFormat = new RomanNumeralFormat();
    itemmeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "Level " + romanNumeralFormat.format(level) + " True Diamond Chestplate"));
    itemmeta.setColor(Color.fromRGB(145, 214, 218));
    item.setItemMeta(itemmeta);
    item = plugin.truediamonddata.addLore(item, level, diamondsacrifice);
    item = plugin.truediamonddata.addAttributes(item, 8.0F, EquipmentSlot.CHEST, level, diamondsacrifice);
    item = plugin.truediamonddata.addFlags(item);
    item = plugin.truediamonddata.addNBTTags(item, level, diamondsacrifice);
    return item;
  }

  public ItemStack TrueDiamondLeggings(Integer level, Integer diamondsacrifice) {
    Integer rarity = (int) Math.floor(((level - 1) / 4)) + 3;
    ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    RomanNumeralFormat romanNumeralFormat = new RomanNumeralFormat();
    itemmeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "Level " + romanNumeralFormat.format(level) + " True Diamond Leggings"));
    itemmeta.setColor(Color.fromRGB(145, 214, 218));
    item.setItemMeta(itemmeta);
    item = plugin.truediamonddata.addLore(item, level, diamondsacrifice);
    item = plugin.truediamonddata.addAttributes(item, 6.0F, EquipmentSlot.LEGS, level, diamondsacrifice);
    item = plugin.truediamonddata.addFlags(item);
    item = plugin.truediamonddata.addNBTTags(item, level, diamondsacrifice);
    return item;
  }

  public ItemStack TrueDiamondBoots(Integer level, Integer diamondsacrifice) {
    Integer rarity = (int) Math.floor(((level - 1) / 4)) + 3;
    ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    RomanNumeralFormat romanNumeralFormat = new RomanNumeralFormat();
    itemmeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "Level " + romanNumeralFormat.format(level) + " True Diamond Boots"));
    itemmeta.setColor(Color.fromRGB(145, 214, 218));
    item.setItemMeta(itemmeta);
    item = plugin.truediamonddata.addLore(item, level, diamondsacrifice);
    item = plugin.truediamonddata.addAttributes(item, 3.0F, EquipmentSlot.FEET, level, diamondsacrifice);
    item = plugin.truediamonddata.addFlags(item);
    item = plugin.truediamonddata.addNBTTags(item, level, diamondsacrifice);
    return item;
  }

  public void TrueDiamondHelmetRecipe() {
    ItemStack item = TrueDiamondHelmet(1, 0);
    NamespacedKey key = new NamespacedKey(plugin, "true_diamond_helmet");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "X X");
    ItemRecipe.setIngredient('X', Material.DIAMOND_BLOCK);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TrueDiamondChestplateRecipe() {
    ItemStack item = TrueDiamondChestplate(1, 0);
    NamespacedKey key = new NamespacedKey(plugin, "true_diamond_chestplate");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("X X", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.DIAMOND_BLOCK);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TrueDiamondLeggingsRecipe() {
    ItemStack item = TrueDiamondLeggings(1, 0);
    NamespacedKey key = new NamespacedKey(plugin, "true_diamond_leggings");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "X X", "X X");
    ItemRecipe.setIngredient('X', Material.DIAMOND_BLOCK);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TrueDiamondBootsRecipe() {
    ItemStack item = TrueDiamondBoots(1, 0);
    NamespacedKey key = new NamespacedKey(plugin, "true_diamond_boots");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("X X", "X X");
    ItemRecipe.setIngredient('X', Material.DIAMOND_BLOCK);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TrueDiamondHelmetUpgradeRecipe() {
    ItemStack item = TrueDiamondHelmet(100, 50);
    NamespacedKey key = new NamespacedKey(plugin, "true_diamond_helmet_upgrade");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "XSX");
    ItemRecipe.setIngredient('X', Material.DIAMOND_BLOCK);
    ItemRecipe.setIngredient('S', Material.PLAYER_HEAD);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TrueDiamondChestplateUpgradeRecipe() {
    ItemStack item = TrueDiamondChestplate(100, 50);
    NamespacedKey key = new NamespacedKey(plugin, "true_diamond_chestplate_upgrade");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XSX", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.DIAMOND_BLOCK);
    ItemRecipe.setIngredient('S', Material.LEATHER_CHESTPLATE);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TrueDiamondLeggingsUpgradeRecipe() {
    ItemStack item = TrueDiamondLeggings(100, 50);
    NamespacedKey key = new NamespacedKey(plugin, "true_diamond_leggings_upgrade");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "XSX", "X X");
    ItemRecipe.setIngredient('X', Material.DIAMOND_BLOCK);
    ItemRecipe.setIngredient('S', Material.LEATHER_LEGGINGS);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TrueDiamondBootsUpgradeRecipe() {
    ItemStack item = TrueDiamondBoots(100, 50);
    NamespacedKey key = new NamespacedKey(plugin, "true_diamond_boots_upgrade");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XSX", "X X");
    ItemRecipe.setIngredient('X', Material.DIAMOND_BLOCK);
    ItemRecipe.setIngredient('S', Material.LEATHER_BOOTS);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TrueDiamondHelmetSingularityUpgradeRecipe() {
    ItemStack item = TrueDiamondHelmet(50, 100);
    NamespacedKey key = new NamespacedKey(plugin, "true_diamond_helmet_singularity_upgrade");
    ShapelessRecipe ItemRecipe = new ShapelessRecipe(key, item);
    ItemRecipe.addIngredient(Material.PLAYER_HEAD);
    ItemRecipe.addIngredient(Material.PLAYER_HEAD);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TrueDiamondChestplateSingularityUpgradeRecipe() {
    ItemStack item = TrueDiamondChestplate(50, 100);
    NamespacedKey key = new NamespacedKey(plugin, "true_diamond_chestplate_singularity_upgrade");
    ShapelessRecipe ItemRecipe = new ShapelessRecipe(key, item);
    ItemRecipe.addIngredient(Material.LEATHER_CHESTPLATE);
    ItemRecipe.addIngredient(Material.PLAYER_HEAD);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TrueDiamondLeggingsSingularityUpgradeRecipe() {
    ItemStack item = TrueDiamondLeggings(50, 100);
    NamespacedKey key = new NamespacedKey(plugin, "true_diamond_leggings_singularity_upgrade");
    ShapelessRecipe ItemRecipe = new ShapelessRecipe(key, item);
    ItemRecipe.addIngredient(Material.LEATHER_LEGGINGS);
    ItemRecipe.addIngredient(Material.PLAYER_HEAD);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void TrueDiamondBootsSingularityUpgradeRecipe() {
    ItemStack item = TrueDiamondBoots(50, 100);
    NamespacedKey key = new NamespacedKey(plugin, "true_diamond_boots_singularity_upgrade");
    ShapelessRecipe ItemRecipe = new ShapelessRecipe(key, item);
    ItemRecipe.addIngredient(Material.LEATHER_BOOTS);
    ItemRecipe.addIngredient(Material.PLAYER_HEAD);
    plugin.getServer().addRecipe(ItemRecipe);
  }
}
