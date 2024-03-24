package me.offsetpaladin89.MoreArmors.armors.endarmor;

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

public record EndArmorData(MoreArmorsMain plugin) {

  public ItemStack addLore(ItemStack item) {
    ItemMeta itemmeta = item.getItemMeta();
    ArrayList<String> lore = new ArrayList<>();
    lore.add(plugin.convertColoredString("&6Item Ability: Bane of the End"));
    lore.add(plugin.convertColoredString("&7Deal &c+10% &7damage while in the End."));
    lore.add("");
    lore.add(plugin.convertColoredString("&6Full Set Bonus: End King"));
    lore.add(plugin.convertColoredString("&7Increases max health by &a20"));
    lore.add(plugin.convertColoredString("&7while in the End."));
    lore.add("");
    lore.add(plugin.convertColoredString("&7Deal &c+100% &7damage while in"));
    lore.add(plugin.convertColoredString("&7the End."));
    lore.add("");
    lore.add(plugin.convertColoredString("&6Full Set Ability: Ender Warp &e&lSHIFT LEFT CLICK"));
    lore.add(plugin.convertColoredString("&7Teleport &a10 blocks &7forwards"));
    lore.add(plugin.convertColoredString("&7while in the End."));
    lore.add(plugin.convertColoredString("&8Cooldown: &a1s"));
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
    nbtItem.setString("CustomItemID", "end");
    nbtItem.setString("CustomItemType", "armor");
    return nbtItem.getItem();
  }
}
