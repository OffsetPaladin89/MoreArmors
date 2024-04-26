package me.offsetpaladin89.MoreArmors.items;

import com.cryptomorin.xseries.SkullUtils;
import de.tr7zw.changeme.nbtapi.NBTItem;
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

public class DestroyerArmor extends Armor {

	private final int armorToughness;
	private final int killAmount;

	public DestroyerArmor(SlotType slot, int emeraldAmount) {
		super(getItem(slot), getRarity(), getName(slot), getArmor(slot), slot);
		this.armorToughness = 2;
		this.killAmount = emeraldAmount;
	}

	public DestroyerArmor(ItemStack item, int emeraldAmount) {
		super(item, getRarity(), getName(getSlot(item)), getArmor(getSlot(item)), getSlot(item));
		this.armorToughness = 2;
		this.killAmount = emeraldAmount;

	}

	private static String getName(SlotType slot) {
		return convertColoredString(MessageFormat.format("{0}Destroyer {1}", Rarity.getColorRarity(getRarity()), WordUtils.capitalizeFully(SlotType.slotName(slot))));
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

	private ArrayList<String> getLore(SlotType slot) {
		ArrayList<String> lore = new ArrayList<>();
		switch (slot) {
			case HELMET -> {
				addLine(lore, "&6Item Ability: Night Vision");
				addLine(lore, "&7Grants &5Night Vision&7.");
				addLine(lore);
			}
			case CHESTPLATE -> {
				addLine(lore, "&6Item Ability: True Shielding");
				addLine(lore, "&a20% &7chance to &anegate an attack&7.");
				addLine(lore);
			}
			case BOOTS -> {
				addLine(lore, "&6Item Ability: Boost");
				addLine(lore, "&7Launch yourself in your &afacing direction");
				addLine(lore, "&7and &acreate an explosion &7behind you.");
				addLine(lore, "&8Cooldown: &a1s");
				addLine(lore);
			}
		}
		addLine(lore, "&6Piece Upgrade: Slayer");
		addLine(lore, "&7Kill mobs to increase your damage.");
		if (killAmount / 100 >= 10) addLine(lore, "&7Current Bonus: &e+10 Damage &a&lMAXED OUT");
		else {
			addLine(lore, MessageFormat.format("&7Current Bonus: &e+{0} Damage", killAmount / 100));
			addLine(lore, MessageFormat.format("&7Next Bonus: &e+{0} Damage &8(&a{1}&7/&c100&8)", killAmount / 100 + 1, killAmount % 100));
			addLine(lore, "&8Max +10 Damage");
		}
		addLine(lore);
		addLine(lore, "&6Full Set Bonus: Warrior");
		addLine(lore, "&7Grants &aStrength II&7.");
		addLine(lore, "&7Grants &aRegeneration II&7.");
		addLine(lore, "&7Grants &aResistance II&7.");
		addFooter(lore);
		return lore;
	}

	public ItemStack getItem() {
		ItemStack item = getBaseItem(getLore(slot), "destroyer");
		ItemMeta itemMeta = item.getItemMeta();
		LeatherArmorMeta leatherItemMeta;
		if (item.getType().equals(Material.PLAYER_HEAD)) SkullUtils.applySkin(itemMeta, "ea0076ab9a5c0ed8ebd08bb18137321df0fdc8abc7499465cc32221ca192ad43");
		else {
			leatherItemMeta = (LeatherArmorMeta) item.getItemMeta();
			leatherItemMeta.setColor(Color.fromRGB(228, 232, 235));
			itemMeta = leatherItemMeta;
		}
		itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "armorToughness", armorToughness, AttributeModifier.Operation.ADD_NUMBER, SlotType.matchSlot(slot)));
		item.setItemMeta(itemMeta);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setInteger("KillAmount", killAmount);
		return nbtItem.getItem();
	}
}