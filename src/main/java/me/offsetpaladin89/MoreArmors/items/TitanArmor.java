package me.offsetpaladin89.MoreArmors.items;

import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

import static me.offsetpaladin89.MoreArmors.Main.convertColoredString;
import static me.offsetpaladin89.MoreArmors.enums.Rarity.UNCOMMON;

public class TitanArmor extends Armor {

	public TitanArmor(SlotType slot) {
		super(getItem(slot), getRarity(), getName(slot), getArmor(slot), slot);
	}

	private static String getName(SlotType slot) {
		return convertColoredString(MessageFormat.format("{0}Titan {1}", Rarity.getColorRarity(getRarity()), WordUtils.capitalizeFully(SlotType.slotName(slot))));
	}

	private static Rarity getRarity() {
		return UNCOMMON;
	}

	private static ItemStack getItem(SlotType slot) {
		return switch (slot) {
			case HELMET -> new ItemStack(Material.IRON_HELMET);
			case CHESTPLATE -> new ItemStack(Material.IRON_CHESTPLATE);
			case LEGGINGS -> new ItemStack(Material.IRON_LEGGINGS);
			case BOOTS -> new ItemStack(Material.IRON_BOOTS);
		};
	}

	private static int getArmor(SlotType slot) {
		return switch(slot) {
			case HELMET, BOOTS -> 2;
			case CHESTPLATE -> 6;
			case LEGGINGS -> 5;
		};
	}

	private ArrayList<String> getLore() {
		ArrayList<String> lore = new ArrayList<>();
		addLine(lore, "&6Item Ability: Health");
		addLine(lore, "&7Increases max health by &a2&7.");
		addLine(lore);
		addLine(lore, "&6Full Set Bonus: Resistance");
		addLine(lore, "&7Grants &aResistance I &7for &a10 seconds");
		addLine(lore, "&7after defeating an enemy.");
		addFooter(lore);
		return lore;
	}

	public ItemStack getItem() {
		ItemStack item = getBaseItem(getLore());
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "maxHealth", 2, AttributeModifier.Operation.ADD_NUMBER, SlotType.matchSlot(slot)));
		item.setItemMeta(itemMeta);
		return item;
	}
}
