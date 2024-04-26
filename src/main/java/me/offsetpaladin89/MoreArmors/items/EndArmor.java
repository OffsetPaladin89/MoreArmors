package me.offsetpaladin89.MoreArmors.items;

import com.cryptomorin.xseries.SkullUtils;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

import static me.offsetpaladin89.MoreArmors.Main.convertColoredString;
import static me.offsetpaladin89.MoreArmors.enums.Rarity.LEGENDARY;

public class EndArmor extends Armor {

	private final int armorToughness;

	public EndArmor(SlotType slot) {
		super(getItem(slot), getRarity(), getName(slot), getArmor(slot), slot);
		this.armorToughness = 2;
	}

	private static String getName(SlotType slot) {
		return convertColoredString(MessageFormat.format("{0}End {1}", Rarity.getColorRarity(getRarity()), WordUtils.capitalizeFully(SlotType.slotName(slot))));
	}

	private static Rarity getRarity() {
		return LEGENDARY;
	}

	private static ItemStack getItem(SlotType slot) {
		return switch (slot) {
			case HELMET -> new ItemStack(Material.PLAYER_HEAD);
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
		addLine(lore, "&6Item Ability: Bane of the End");
		addLine(lore, "&7Deal &c+10% &7damage while in the End.");
		addLine(lore);
		addLine(lore, "&6Full Set Bonus: End King");
		addLine(lore, "&7Increases max health by &a20");
		addLine(lore, "&7while in the End.");
		addLine(lore);
		addLine(lore, "&7Deal &c+100% &7damage while in");
		addLine(lore, "&7the End.");
		addLine(lore);
		addLine(lore, "&6Full Set Ability: Ender Warp &e&lSHIFT LEFT CLICK");
		addLine(lore, "&7Teleport &a10 blocks &7forwards");
		addLine(lore, "&7while in the End.");
		addLine(lore, "&8Cooldown: &a1s");
		addFooter(lore);
		return lore;
	}

	public ItemStack getItem() {
		ItemStack item = getBaseItem(getLore());
		ItemMeta itemMeta = item.getItemMeta();
		LeatherArmorMeta leatherItemMeta;
		if (item.getType().equals(Material.PLAYER_HEAD)) SkullUtils.applySkin(itemMeta, "fee4eabeb72f19088ade78266191c8f77398cc0d80cdd27563a5d66b71912b28");
		else {
			leatherItemMeta = (LeatherArmorMeta) item.getItemMeta();
			leatherItemMeta.setColor(Color.PURPLE);
			itemMeta = leatherItemMeta;
		}
		itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "armorToughness", armorToughness, AttributeModifier.Operation.ADD_NUMBER, SlotType.matchSlot(slot)));
		item.setItemMeta(itemMeta);
		return item;
	}
}
