package me.offsetpaladin89.MoreArmors.armors.netherarmor;

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

public record NetherArmor(MoreArmorsMain plugin) {

  public ItemStack NetherHelmet() {
    ItemStack item = SkullCreator.createSkull();
    ItemMeta itemmeta = item.getItemMeta();
    itemmeta = SkullUtils.applySkin(itemmeta, "cdf74e323ed41436965f5c57ddf2815d5332fe999e68fbb9d6cf5c8bd4139f");
    itemmeta.setDisplayName(plugin.convertColoredString("&6Nether Helmet"));
    item.setItemMeta(itemmeta);
    item = plugin.netherdata.addLore(item);
    item = plugin.netherdata.addAttributes(item, 3, EquipmentSlot.HEAD);
    item = plugin.netherdata.addFlags(item);
    item = plugin.netherdata.addNBTTags(item);

    NBTItem nbtItem = new NBTItem(item);
    nbtItem.setString("UUID", UUID.randomUUID().toString());
    return nbtItem.getItem();
  }

  public ItemStack NetherChestplate() {
    ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&6Nether Chestplate"));
    itemmeta.setColor(Color.GRAY);
    item.setItemMeta(itemmeta);
    item = plugin.netherdata.addLore(item);
    item = plugin.netherdata.addAttributes(item, 8, EquipmentSlot.CHEST);
    item = plugin.netherdata.addFlags(item);
    item = plugin.netherdata.addNBTTags(item);
    return item;
  }

  public ItemStack NetherLeggings() {
    ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&6Nether Leggings"));
    itemmeta.setColor(Color.GRAY);
    item.setItemMeta(itemmeta);
    item = plugin.netherdata.addLore(item);
    item = plugin.netherdata.addAttributes(item, 6, EquipmentSlot.LEGS);
    item = plugin.netherdata.addFlags(item);
    item = plugin.netherdata.addNBTTags(item);
    return item;
  }

  public ItemStack NetherBoots() {
    ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setDisplayName(plugin.convertColoredString("&6Nether Boots"));
    itemmeta.setColor(Color.GRAY);
    item.setItemMeta(itemmeta);
    item = plugin.netherdata.addLore(item);
    item = plugin.netherdata.addAttributes(item, 3, EquipmentSlot.FEET);
    item = plugin.netherdata.addFlags(item);
    item = plugin.netherdata.addNBTTags(item);
    return item;
  }

  public void NetherHelmetRecipe() {
    ItemStack item = NetherHelmet();
    NamespacedKey key = new NamespacedKey(plugin, "nether_helmet");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "XZX");
    ItemRecipe.setIngredient('X', Material.SOUL_SAND);
    ItemRecipe.setIngredient('Z', Material.PLAYER_HEAD);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void NetherChestplateRecipe() {
    ItemStack item = NetherChestplate();
    NamespacedKey key = new NamespacedKey(plugin, "nether_chestplate");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XZX", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.SOUL_SAND);
    ItemRecipe.setIngredient('Z', Material.NETHER_STAR);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void NetherLeggingsRecipe() {
    ItemStack item = NetherLeggings();
    NamespacedKey key = new NamespacedKey(plugin, "nether_leggings");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "XZX", "X X");
    ItemRecipe.setIngredient('X', Material.SOUL_SAND);
    ItemRecipe.setIngredient('Z', Material.NETHER_STAR);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void NetherBootsRecipe() {
    ItemStack item = NetherBoots();
    NamespacedKey key = new NamespacedKey(plugin, "nether_boots");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XZX", "X X");
    ItemRecipe.setIngredient('X', Material.SOUL_SAND);
    ItemRecipe.setIngredient('Z', Material.NETHER_STAR);
    plugin.getServer().addRecipe(ItemRecipe);
  }
}
