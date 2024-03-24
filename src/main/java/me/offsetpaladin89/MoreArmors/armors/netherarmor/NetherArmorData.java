package me.offsetpaladin89.MoreArmors.armors.netherarmor;

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

public record NetherArmorData(MoreArmorsMain plugin) {

  public ItemStack addLore(ItemStack item) {
    ItemMeta itemmeta = item.getItemMeta();
    ArrayList<String> lore = new ArrayList<>();
    lore.add(plugin.convertColoredString("&6Item Ability: Bane of the Nether"));
    lore.add(plugin.convertColoredString("&7Deal &c+10% &7damage to mobs in the Nether."));
    lore.add("");
    lore.add(plugin.convertColoredString("&6Full Set Bonus: Nether King"));
    lore.add(plugin.convertColoredString("&7Gives &aFire Resistance &7while"));
    lore.add(plugin.convertColoredString("&7in the Nether."));
    lore.add("");
    lore.add(plugin.convertColoredString("&7Increases max health by &a20"));
    lore.add(plugin.convertColoredString("&7while in the Nether."));
    lore.add("");
    lore.add(plugin.convertColoredString("&7Deal &c+100% &7damage while in"));
    lore.add(plugin.convertColoredString("&7the Nether."));
    lore.add("");
    lore.add(plugin.convertColoredString("&6&lLEGENDARY"));
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

  public ItemStack addAttributes(ItemStack item, int armor, EquipmentSlot slot) {
    ItemMeta itemmeta = item.getItemMeta();
    AttributeModifier armormodifier = new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, slot);
    itemmeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armormodifier);
    item.setItemMeta(itemmeta);
    return item;
  }

  public ItemStack addNBTTags(ItemStack item) {
    NBTItem nbtItem = new NBTItem(item);
    nbtItem.setBoolean("IsCustomItem", true);
    nbtItem.setString("CustomItemID", "nether");
    nbtItem.setString("CustomItemType", "armor");
    return nbtItem.getItem();
  }
}
