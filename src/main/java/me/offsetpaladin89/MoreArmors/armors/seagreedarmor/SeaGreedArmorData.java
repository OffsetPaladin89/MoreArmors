package me.offsetpaladin89.MoreArmors.armors.seagreedarmor;

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

public record SeaGreedArmorData(MoreArmorsMain plugin) {
    public ItemStack addLore(ItemStack item) {
        ItemMeta itemmeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(plugin.convertColoredString("&6Item Ability: Ore Greed"));
        lore.add(plugin.convertColoredString("&7Gain &a+50% &7ore drops while in water."));
        lore.add("");
        lore.add(plugin.convertColoredString("&6Full Set Bonus: Sea's Greed"));
        lore.add(plugin.convertColoredString("&7Increases movement speed by &a100% &7while in water."));
        lore.add("");
        lore.add(plugin.convertColoredString("&7Deal &c+100% &7damage while in water."));
        lore.add("");
        lore.add(plugin.convertColoredString("&4Special Item Ability: &cBlessing of the Sea God"));
        lore.add(plugin.convertColoredString("&7There is a &a1% &7chance when killing an Elder Guardian"));
        lore.add(plugin.convertColoredString("&7to trigger &4&lBlessing of the Sea God &7granting:"));
        lore.add(plugin.convertColoredString("&7- &a10 &7Diamond Blocks"));
        lore.add(plugin.convertColoredString("&7- &a10 &7Gold Blocks"));
        lore.add(plugin.convertColoredString("&7- &aHaste III &7for &a30 minutes"));
        lore.add(plugin.convertColoredString("&7- &aStrength III &7for &a30 minutes"));
        lore.add(plugin.convertColoredString("&7- &aSpeed III &7for &a30 minutes"));
        lore.add(plugin.convertColoredString("&7- &aResistance II &7for &a30 minutes"));
        lore.add(plugin.convertColoredString("&8Cooldown: 1h"));
        lore.add("");
        lore.add(plugin.convertColoredString("&b&lDIVINE"));
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

    public ItemStack addAttributes(ItemStack item, int armor, int toughness, EquipmentSlot slot) {
        ItemMeta itemmeta = item.getItemMeta();
        AttributeModifier armormodifier = new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, slot);
        itemmeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armormodifier);
        AttributeModifier toughnessmodifier = new AttributeModifier(UUID.randomUUID(), "toughness", toughness, AttributeModifier.Operation.ADD_NUMBER, slot);
        itemmeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessmodifier);
        item.setItemMeta(itemmeta);
        return item;
    }

    public ItemStack addNBTTags(ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("IsCustomItem", true);
        nbtItem.setString("CustomItemID", "seagreed");
        nbtItem.setString("CustomItemType", "armor");
        return nbtItem.getItem();
    }
}
