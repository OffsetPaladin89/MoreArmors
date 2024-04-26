package me.offsetpaladin89.MoreArmors.items;

import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.text.MessageFormat;
import java.util.ArrayList;

import static me.offsetpaladin89.MoreArmors.Main.convertColoredString;
import static me.offsetpaladin89.MoreArmors.enums.Rarity.UNCOMMON;

public class MinerArmor extends Armor {

	public MinerArmor(SlotType slot) {
		super(getItem(slot), getRarity(), getName(slot), getArmor(slot), slot);
	}

	private static String getName(SlotType slot) {
		return convertColoredString(MessageFormat.format("{0}Miner {1}", Rarity.getColorRarity(getRarity()), WordUtils.capitalizeFully(SlotType.slotName(slot))));
	}

	private static Rarity getRarity() {
		return UNCOMMON;
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
			case HELMET, BOOTS -> 1;
			case CHESTPLATE -> 5;
			case LEGGINGS -> 4;
		};
	}

	private ArrayList<String> getLore() {
		ArrayList<String> lore = new ArrayList<>();
		addLine(lore, "&6Full Set Bonus: Haste");
		addLine(lore, "&7Gives &aHaste II &7for &a5 seconds");
		addLine(lore, "&7after mining a block.");
		addFooter(lore);
		return lore;
	}

	public ItemStack getItem() {
		ItemStack item = getBaseItem(getLore(), "miner");
		LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
		itemMeta.setColor(Color.GRAY);
		item.setItemMeta(itemMeta);
		return item;
	}
}
