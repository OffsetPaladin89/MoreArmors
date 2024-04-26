package me.offsetpaladin89.MoreArmors.items;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

import static me.offsetpaladin89.MoreArmors.Main.convertColoredString;

public class Armor {

	protected ItemStack item;
	protected Rarity rarity;
	protected String name;
	protected int armor;
	protected SlotType slot;

	protected Armor(ItemStack item, Rarity rarity, String name, int armor, SlotType slot) {
		this.item = item;
		this.rarity = rarity;
		this.name = name;
		this.armor = armor;
		this.slot = slot;
	}

	protected NBTItem getNBTItem(String id) {
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setBoolean("IsCustomItem", true);
		nbtItem.setString("CustomItemType", "armor");
		nbtItem.setString("CustomItemID", id);
		nbtItem.setInteger("Rarity", rarity.ordinal() + 1);
		nbtItem.setInteger("Armor", armor);
		nbtItem.setString("Slot", slot.name());
		if (item.getType().equals(Material.PLAYER_HEAD)) nbtItem.setString("UUID", UUID.randomUUID().toString());
		return nbtItem;
	}

	protected ItemStack getBaseItem(ArrayList<String> lore, String id) {
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		itemMeta.setLore(lore);
		itemMeta.setUnbreakable(true);
		itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
		itemMeta.removeAttributeModifier(SlotType.matchSlot(slot));
		itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, SlotType.matchSlot(slot)));
		item.setItemMeta(itemMeta);
		return getNBTItem(id).getItem();
	}

	protected void addFooter(ArrayList<String> lore) {
		addLine(lore);
		addLine(lore, MessageFormat.format("{0}&l{1}", Rarity.getColorRarity(rarity), rarity));
	}

	protected static SlotType getSlot(ItemStack i) {
		return SlotType.matchSlot(i.getType().getEquipmentSlot());
	}

	protected void addLine(ArrayList<String> lore) {
		lore.add("");
	}

	protected void addLine(ArrayList<String> lore, String str) {
		lore.add(convertColoredString(str));
	}
}