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
import static me.offsetpaladin89.MoreArmors.enums.Rarity.MYTHIC;

public class SeaGreedArmor extends Armor {

	private final int armorToughness;

	public SeaGreedArmor(SlotType slot) {
		super(getItem(slot), getRarity(), getName(slot), getArmor(slot), slot);
		this.armorToughness = 2;
	}

	private static String getName(SlotType slot) {
		return convertColoredString(MessageFormat.format("{0}Sea Greed {1}", Rarity.getColorRarity(getRarity()), WordUtils.capitalizeFully(SlotType.slotName(slot))));
	}

	private static Rarity getRarity() {
		return MYTHIC;
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
		addLine(lore, "&6Item Ability: Ore Greed");
		addLine(lore, "&7Gain &a+50% &7additional ore drops while in water.");
		addLine(lore);
		addLine(lore, "&6Full Set Bonus: Sea's Greed");
		addLine(lore, "&7While in water, gain &a+200% &7increased swim speed,");
		addLine(lore, "&5Conduit Power&7, and &c+100% &7damage.");
		addLine(lore);
		addLine(lore, "&4Special Item Ability: &cBlessing of the Sea God");
		addLine(lore, "&7There is a &a25% &7chance when killing an &5Elder Guardian");
		addLine(lore, "&7to trigger &4&lBlessing of the Sea God &7granting:");
		addLine(lore, "&7- &a25 &7Diamond Blocks");
		addLine(lore, "&7- &a100 &7Gold Blocks");
		addLine(lore, "&7- &aHaste III &7for &a1 hour");
		addLine(lore, "&7- &aStrength II &7for &a1 hour");
		addLine(lore, "&7- &aSpeed II &7for &a1 hour");
		addLine(lore, "&7- &aResistance II &7for &a1 hour");
		addFooter(lore);
		return lore;
	}

	public ItemStack getItem() {
		ItemStack item = getBaseItem(getLore(), "seagreed");
		ItemMeta itemMeta = item.getItemMeta();
		LeatherArmorMeta leatherItemMeta;
		if (item.getType().equals(Material.PLAYER_HEAD)) SkullUtils.applySkin(itemMeta, "935541523f14c78d8de98cc296c798f0b867ba85344ed77f6dae12455a174");
		else {
			leatherItemMeta = (LeatherArmorMeta) item.getItemMeta();
			leatherItemMeta.setColor(Color.fromRGB(130, 140, 100));
			itemMeta = leatherItemMeta;
		}
		itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "armorToughness", armorToughness, AttributeModifier.Operation.ADD_NUMBER, SlotType.matchSlot(slot)));
		item.setItemMeta(itemMeta);
		return item;
	}
}
