package me.offsetpaladin89.MoreArmors.armors.seagreedarmor;

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

public record SeaGreedArmor(MoreArmorsMain plugin) {

    public ItemStack SeaGreedHelmet() {
        ItemStack item = SkullCreator.createSkull();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta = SkullUtils.applySkin(itemMeta, "935541523f14c78d8de98cc296c798f0b867ba85344ed77f6dae12455a174");
        itemMeta.setDisplayName(plugin.convertColoredString("&b&l&kaa &r&bSea's Greed Helmet &b&l&kaa&r"));
        item.setItemMeta(itemMeta);
        item = plugin.seagreeddata.addLore(item);
        item = plugin.seagreeddata.addAttributes(item, 5, 3, EquipmentSlot.HEAD);
        item = plugin.seagreeddata.addFlags(item);
        item = plugin.seagreeddata.addNBTTags(item);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("UUID", UUID.randomUUID().toString());
        return nbtItem.getItem();
    }

    public void SeaGreedHelmetRecipe() {
        ItemStack item = SeaGreedHelmet();
        NamespacedKey key = new NamespacedKey(plugin, "sea_greed_helmet");
        ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
        ItemRecipe.shape("YZY", "W W");
        ItemRecipe.setIngredient('Z', Material.HEART_OF_THE_SEA);
        ItemRecipe.setIngredient('Y', Material.PRISMARINE);
        ItemRecipe.setIngredient('W', Material.GOLD_BLOCK);
        plugin.getServer().addRecipe(ItemRecipe);
    }

    public ItemStack SeaGreedChestplate() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setDisplayName(plugin.convertColoredString("&b&l&kaa &r&bSea's Greed Chestplate &b&l&kaa&r"));
        itemMeta.setColor(Color.fromRGB(131, 140, 96));
        item.setItemMeta(itemMeta);
        item = plugin.seagreeddata.addLore(item);
        item = plugin.seagreeddata.addAttributes(item, 10, 3, EquipmentSlot.CHEST);
        item = plugin.seagreeddata.addFlags(item);
        item = plugin.seagreeddata.addNBTTags(item);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("UUID", UUID.randomUUID().toString());
        return nbtItem.getItem();
    }

    public void SeaGreedChestplateRecipe() {
        ItemStack item = SeaGreedChestplate();
        NamespacedKey key = new NamespacedKey(plugin, "sea_greed_chestplate");
        ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
        ItemRecipe.shape("X X", "YZY", "WWW");
        ItemRecipe.setIngredient('X', Material.DIAMOND_BLOCK);
        ItemRecipe.setIngredient('Z', Material.HEART_OF_THE_SEA);
        ItemRecipe.setIngredient('Y', Material.PRISMARINE);
        ItemRecipe.setIngredient('W', Material.GOLD_BLOCK);
        plugin.getServer().addRecipe(ItemRecipe);
    }

    public ItemStack SeaGreedLeggings() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setDisplayName(plugin.convertColoredString("&b&l&kaa &r&bSea's Greed Leggings &b&l&kaa&r"));
        itemMeta.setColor(Color.fromRGB(131, 140, 96));
        item.setItemMeta(itemMeta);
        item = plugin.seagreeddata.addLore(item);
        item = plugin.seagreeddata.addAttributes(item, 8, 3, EquipmentSlot.LEGS);
        item = plugin.seagreeddata.addFlags(item);
        item = plugin.seagreeddata.addNBTTags(item);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("UUID", UUID.randomUUID().toString());
        return nbtItem.getItem();
    }

    public void SeaGreedLeggingsRecipe() {
        ItemStack item = SeaGreedLeggings();
        NamespacedKey key = new NamespacedKey(plugin, "sea_greed_leggings");
        ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
        ItemRecipe.shape("YZY", "W W", "X X");
        ItemRecipe.setIngredient('X', Material.DIAMOND_BLOCK);
        ItemRecipe.setIngredient('Z', Material.HEART_OF_THE_SEA);
        ItemRecipe.setIngredient('Y', Material.PRISMARINE);
        ItemRecipe.setIngredient('W', Material.GOLD_BLOCK);
        plugin.getServer().addRecipe(ItemRecipe);
    }

    public ItemStack SeaGreedBoots() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setDisplayName(plugin.convertColoredString("&b&l&kaa &r&bSea's Greed Leggings &b&l&kaa&r"));
        itemMeta.setColor(Color.fromRGB(90, 98, 60));
        item.setItemMeta(itemMeta);
        item = plugin.seagreeddata.addLore(item);
        item = plugin.seagreeddata.addAttributes(item, 5, 3, EquipmentSlot.FEET);
        item = plugin.seagreeddata.addFlags(item);
        item = plugin.seagreeddata.addNBTTags(item);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("UUID", UUID.randomUUID().toString());
        return nbtItem.getItem();
    }

    public void SeaGreedBootsRecipe() {
        ItemStack item = SeaGreedBoots();
        NamespacedKey key = new NamespacedKey(plugin, "sea_greed_boots");
        ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
        ItemRecipe.shape("Y Y", "W W");
        ItemRecipe.setIngredient('W', Material.GOLD_BLOCK);
        ItemRecipe.setIngredient('Y', Material.PRISMARINE);
        plugin.getServer().addRecipe(ItemRecipe);
    }
}
