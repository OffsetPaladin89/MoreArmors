package me.offsetpaladin89.MoreArmors.armors.titanarmor;

import java.util.ArrayList;
import java.util.UUID;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public record TitanArmorData(MoreArmorsMain plugin) {

  public ItemStack addLore(ItemStack item) {
    ItemMeta itemmeta = item.getItemMeta();
    ArrayList<String> lore = new ArrayList<>();
    lore.add(plugin.convertColoredString("&6Item Ability: Health"));
    lore.add(plugin.convertColoredString("&7Increases max health by &a2&7."));
    lore.add("");
    lore.add(plugin.convertColoredString("&6Full Set Bonus: Resistance"));
    lore.add(plugin.convertColoredString("&7Gives &aResistance I &7for &a10 seconds"));
    lore.add(plugin.convertColoredString("&7after killing something."));
    lore.add("");
    lore.add(plugin.convertColoredString("&a&lUNCOMMON"));
    itemmeta.setLore(lore);
    item.setItemMeta(itemmeta);
    return item;
  }

  public ItemStack addFlags(ItemStack item) {
    ItemMeta itemmeta = item.getItemMeta();
    itemmeta.setUnbreakable(true);
    itemmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
    itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    item.setItemMeta(itemmeta);
    return item;
  }

  public ItemStack addAttributes(ItemStack item, Integer armor, EquipmentSlot slot) {
    ItemMeta itemmeta = item.getItemMeta();
    AttributeModifier healthmodifier = new AttributeModifier(UUID.randomUUID(), "maxHealth", 2.0D, AttributeModifier.Operation.ADD_NUMBER, slot);
    AttributeModifier armormodifier = new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, slot);
    itemmeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, healthmodifier);
    itemmeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armormodifier);
    item.setItemMeta(itemmeta);
    return item;
  }

  public ItemStack addNBTTags(ItemStack item) {
    NBTItem nbtItem = new NBTItem(item);
    nbtItem.setBoolean("IsCustomItem", true);
    nbtItem.setString("CustomItemID", "titan");
    nbtItem.setString("CustomItemType", "armor");
    return nbtItem.getItem();
  }
}
