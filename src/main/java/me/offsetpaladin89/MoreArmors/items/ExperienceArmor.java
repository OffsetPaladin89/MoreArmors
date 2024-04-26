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
import static me.offsetpaladin89.MoreArmors.enums.Rarity.RARE;

public class ExperienceArmor extends Armor {

	public ExperienceArmor(SlotType slot) {
		super(getItem(slot), getRarity(), getName(slot), getArmor(slot), slot);
	}

	private static String getName(SlotType slot) {
		return convertColoredString(MessageFormat.format("{0}Experience {1}", Rarity.getColorRarity(getRarity()), WordUtils.capitalizeFully(SlotType.slotName(slot))));
	}

	private static Rarity getRarity() {
		return RARE;
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
		addLine(lore, "&6Full Set Bonus: Experience");
		addLine(lore, "&7Recieve &adouble experience &7from killing");
		addLine(lore, "&7mobs and mining ores.");
		addFooter(lore);
		return lore;
	}

	public ItemStack getItem() {
		ItemStack item = getBaseItem(getLore(), "experience");
		LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
		itemMeta.setColor(Color.BLUE);
		item.setItemMeta(itemMeta);
		return item;
	}
}
