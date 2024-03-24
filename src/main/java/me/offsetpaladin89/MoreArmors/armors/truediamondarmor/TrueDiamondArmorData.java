package me.offsetpaladin89.MoreArmors.armors.truediamondarmor;

import com.github.fracpete.romannumerals4j.RomanNumeralFormat;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MorePluginsCore.enums.ArmorType;
import me.offsetpaladin89.MorePluginsCore.enums.Rarity;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public record TrueDiamondArmorData(MoreArmorsMain plugin) {

  public ItemStack setDisplayName(ItemStack item, Integer level, ArmorType slot) {
    Integer rarity = (int) Math.floor(((level - 1) / 4)) + 3;
    ItemMeta itemmeta = item.getItemMeta();
    RomanNumeralFormat romanNumeralFormat = new RomanNumeralFormat();
    itemmeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "Level " + romanNumeralFormat.format(level) + " True Diamond " + StringUtils.capitalize(slot.toString().toLowerCase())));
    item.setItemMeta(itemmeta);
    return item;
  }

  public ItemStack addLore(ItemStack item, Integer level, Integer diamondsacrifice) {
    Integer rarity = (int) Math.floor(((level - 1) / 4)) + 3;
    ItemMeta itemmeta = item.getItemMeta();
    ArrayList<String> lore = new ArrayList<>();
    boolean maxed = false;
    if (Math.floor((diamondsacrifice / 5)) >= 10.0D)
      maxed = true;
    float multiplier = (float) (1.0D + diamondsacrifice / 5 * 5.0D / 100.0D);
    float damage = Math.round(level * multiplier * 50.0F) / 100.0F;
    float health = Math.round(level * multiplier * 100.0F) / 100.0F;
    double movementspeed = Math.round(level * multiplier * 250.0F) / 100.0D;
    float criticalhitchance = Math.round(level * multiplier * 50.0F) / 100.0F;
    int currentdiamondsingularities = Math.floorMod(diamondsacrifice, 5);
    int currentbonus = (int) Math.floor((diamondsacrifice / 5)) * 5;
    int nextbonus = (int) Math.floor((diamondsacrifice / 5)) * 5 + 5;
    lore.add(plugin.convertColoredString("&6Item Ability: Ultimate Unit"));
    if (maxed) {
      lore.add(plugin.convertColoredString("&e(+50% from Diamond Sacrifice)"));
    } else {
      lore.add(plugin.convertColoredString("&e(+" + currentbonus + "% from Diamond Sacrifice)"));
    }
    lore.add(plugin.convertColoredString("&7Increases &cDamage &7by &c" + damage + "&7."));
    lore.add(plugin.convertColoredString("&7Increases &aMax Health &7by &a" + health + "&7."));
    lore.add(plugin.convertColoredString("&7Increases &aMovement Speed &7by &a" + movementspeed + "%&7."));
    lore.add(plugin.convertColoredString("&7Increases &aCritical Hit Chance &7by &a" + criticalhitchance + "%&7."));
    lore.add("");
    lore.add(plugin.convertColoredString("&6Piece Upgrade: Diamond Sacrifice"));
    lore.add(plugin.convertColoredString("&7Sacrifice &5Diamond Singularities &7into this armor"));
    lore.add(plugin.convertColoredString("&7piece to improve the stats of it."));
    if (maxed) {
      lore.add(plugin.convertColoredString("&7Piece Bonus: &e+50% Stats"));
      lore.add(plugin.convertColoredString("&7Next Bonus: &e+50% Stats &a&lMAXED"));
    } else {
      lore.add(plugin.convertColoredString("&7Piece Bonus: &e+" + currentbonus + "% Stats"));
      lore.add(plugin.convertColoredString("&7Next Bonus: &e+" + nextbonus + "% Stats &8(&a" + currentdiamondsingularities + "&7/&c5&8)"));
    }
    lore.add(plugin.convertColoredString("&8Max +50% Stats"));
    lore.add("");
    lore.add(plugin.convertColoredString("&6Full Set Bonus: Army"));
    lore.add(plugin.convertColoredString("&7Increases &cDamage &7by &c2% &7for each"));
    lore.add(plugin.convertColoredString("&7player within &a10 blocks&7."));
    lore.add(plugin.convertColoredString("&8Max of 10 players"));
    lore.add("");
    lore.add(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "&l" + rarity));
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

  public ItemStack addAttributes(ItemStack item, float armor, EquipmentSlot slot, Integer level, Integer diamondsacrifice) {
    float multiplier = (float) (1.0D + Math.floor((diamondsacrifice / 5)) * 5.0D / 100.0D);
    float damage = level * multiplier / 2.0F;
    float health = level * multiplier;
    double movementspeed = level / 400.0D;
    armor *= multiplier;
    ItemMeta itemmeta = item.getItemMeta();
    itemmeta.removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH);
    itemmeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
    itemmeta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
    itemmeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
    AttributeModifier healthmodifier = new AttributeModifier(UUID.randomUUID(), "maxhealth", health, AttributeModifier.Operation.ADD_NUMBER, slot);
    AttributeModifier armormodifier = new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, slot);
    AttributeModifier movementspeedmodifier = new AttributeModifier(UUID.randomUUID(), "movementSpeed", movementspeed, AttributeModifier.Operation.ADD_NUMBER, slot);
    AttributeModifier damagemodifier = new AttributeModifier(UUID.randomUUID(), "attackDamage", damage, AttributeModifier.Operation.ADD_NUMBER, slot);
    itemmeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, healthmodifier);
    itemmeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armormodifier);
    itemmeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, movementspeedmodifier);
    itemmeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damagemodifier);
    item.setItemMeta(itemmeta);
    return item;
  }

  public ItemStack addNBTTags(ItemStack item, Integer level, Integer diamondsacrifice) {
    NBTItem nbtItem = new NBTItem(item);
    nbtItem.setBoolean("IsCustomItem", true);
    nbtItem.setString("CustomItemID", "truediamond");
    nbtItem.setString("CustomItemType", "armor");
    nbtItem.setInteger("ArmorLevel", level);
    nbtItem.setInteger("DiamondSacrifice", diamondsacrifice);
    return nbtItem.getItem();
  }
}
