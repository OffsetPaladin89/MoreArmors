package me.offsetpaladin89.MoreArmors.armors.emeraldarmor;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public record EmeraldArmorData(MoreArmorsMain plugin) {

  public ItemStack addLore(ItemStack item, int emeraldcount, boolean maxed) {
    int currentbonus, nextbonus;
    int currentBonus = emeraldcount / 50;
    if (Math.floor((emeraldcount / 50)) >= 5.0D)
      maxed = true;
    if (maxed) {
      currentbonus = 10;
      nextbonus = 10;
    } else {
      currentbonus = (int) (2 * Math.floor((emeraldcount / 50)));
      nextbonus = (int) ((2 * Math.floor((emeraldcount / 50))) + 2);
    }
    ItemMeta itemmeta = item.getItemMeta();
    ArrayList<String> lore = new ArrayList<>();
    lore.add(plugin.convertColoredString("&6Piece Upgrade: Emerald Blood"));
    lore.add(plugin.convertColoredString("&7Mine emeralds to increase your max health."));
    if(emeraldcount >= 250) lore.add(plugin.convertColoredString("&7Current Bonus (&a5&8/&a5&7): &e+10 Health &a&lMAXED OUT"));
    else {
      lore.add(plugin.convertColoredString("&7Current Bonus (&a" + emeraldcount / 50 + "&8/&a5&7): &e+" + emeraldcount / 50 + " Health"));
      lore.add(plugin.convertColoredString("&7Next Upgrade: &e+" + (emeraldcount / 50 + 2) + " Health &8(&a" + emeraldcount / 50 + "&7/&c50&8)"));
      lore.add(plugin.convertColoredString("&8Max +10 Health"));
    }
    lore.add("");
    lore.add(plugin.convertColoredString("&5&lEPIC"));
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

  public ItemStack addAttributes(ItemStack item, int armor, EquipmentSlot slot, int emeraldcount) {
    int healthBoost = emeraldcount / 50 > 5 ? 10 : emeraldcount / 50 * 2;
    ItemMeta itemmeta = item.getItemMeta();
    itemmeta.removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH);
    itemmeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
    AttributeModifier healthValue = new AttributeModifier(UUID.randomUUID(), "maxHealth", healthBoost, AttributeModifier.Operation.ADD_NUMBER, slot);
    AttributeModifier armorValue = new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, slot);
    AttributeModifier armorToughnessValue = new AttributeModifier(UUID.randomUUID(), "armorToughness", 2, AttributeModifier.Operation.ADD_NUMBER, slot);
    itemmeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, healthValue);
    itemmeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorValue);
    itemmeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, armorToughnessValue);
    item.setItemMeta(itemmeta);
    return item;
  }

  public ItemStack addNBTTags(ItemStack item, Integer amount) {
    NBTItem nbtItem = new NBTItem(item);
    nbtItem.setBoolean("IsCustomItem", true);
    nbtItem.setString("CustomItemID", "emerald");
    nbtItem.setString("CustomItemType", "armor");
    nbtItem.setInteger("EmeraldCount", amount);
    return nbtItem.getItem();
  }
}
