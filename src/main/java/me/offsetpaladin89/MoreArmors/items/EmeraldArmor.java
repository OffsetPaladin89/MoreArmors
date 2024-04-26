package me.offsetpaladin89.MoreArmors.items;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

import static me.offsetpaladin89.MoreArmors.Main.convertColoredString;
import static me.offsetpaladin89.MoreArmors.enums.Rarity.EPIC;

public class EmeraldArmor extends Armor {

	private final int armorToughness;
	private final int emeraldAmount;

	public EmeraldArmor(SlotType slot, int emeraldAmount) {
		super(getItem(slot), getRarity(), getName(slot), getArmor(slot), slot);
		this.armorToughness = 2;
		this.emeraldAmount = emeraldAmount;
	}

	public EmeraldArmor(ItemStack item, int emeraldAmount) {
		super(item, getRarity(), getName(getSlot(item)), getArmor(getSlot(item)), getSlot(item));
		this.armorToughness = 2;
		this.emeraldAmount = emeraldAmount;

	}

	private static String getName(SlotType slot) {
		return convertColoredString(MessageFormat.format("{0}Emerald {1}", Rarity.getColorRarity(getRarity()), WordUtils.capitalizeFully(SlotType.slotName(slot))));
	}

	private static Rarity getRarity() {
		return EPIC;
	}

	private static ItemStack getItem(SlotType slot) {
		return switch (slot) {
			case HELMET -> new ItemStack(Material.LEATHER_HELMET);
			case CHESTPLATE -> new ItemStack(Material.LEATHER_CHESTPLATE);
			case LEGGINGS -> new ItemStack(Material.LEATHER_LEGGINGS);
			case BOOTS -> new ItemStack(Material.LEATHER_BOOTS);
		};
	}

	private static int getArmor(SlotType slot) {
		return switch(slot) {
			case HELMET, BOOTS -> 3;
			case CHESTPLATE -> 8;
			case LEGGINGS -> 6;
		};
	}

	private ArrayList<String> getLore() {
		ArrayList<String> lore = new ArrayList<>();
		addLine(lore, "&6Piece Upgrade: Emerald Blood");
		addLine(lore, "&7Mine emeralds to increase your max health.");
		if(emeraldAmount >= 250) addLine(lore, "&7Current Bonus (&a5&8/&a5&7): &e+10 Health &a&lMAXED OUT");
		else {
			addLine(lore, MessageFormat.format("&7Current Bonus (&a{0}&8/&a5&7): &e+{0} Health", emeraldAmount / 50));
			addLine(lore, MessageFormat.format("&7Next Upgrade: &e+{0} Health &8(&a{1}&7/&c50&8)", emeraldAmount / 50 + 2, emeraldAmount % 50));
			addLine(lore, "&8Max +10 Health");
		}
		addFooter(lore);
		return lore;
	}

	public ItemStack getItem() {
		ItemStack item = getBaseItem(getLore(), "emerald");
		LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
		itemMeta.setColor(Color.LIME);
		int healthBoost = emeraldAmount / 50 > 5 ? 10 : emeraldAmount / 50 * 2;
		itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "maxHealth", healthBoost, AttributeModifier.Operation.ADD_NUMBER, SlotType.matchSlot(slot)));
		itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "armorToughness", armorToughness, AttributeModifier.Operation.ADD_NUMBER, SlotType.matchSlot(slot)));
		item.setItemMeta(itemMeta);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setInteger("EmeraldCount", emeraldAmount);
		return nbtItem.getItem();
	}
}