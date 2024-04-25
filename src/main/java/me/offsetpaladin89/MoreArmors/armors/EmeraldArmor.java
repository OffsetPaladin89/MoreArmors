package me.offsetpaladin89.MoreArmors.armors;

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

public class EmeraldArmor extends Armor {

	private final int armorToughness;
	private final int emeraldAmount;

	public EmeraldArmor(SlotType slot, int emeraldAmount) {
		super(
				switch(slot) {
					case HELMET -> new ItemStack(Material.LEATHER_HELMET, 1);
					case CHESTPLATE -> new ItemStack(Material.LEATHER_CHESTPLATE, 1);
					case LEGGINGS -> new ItemStack(Material.LEATHER_LEGGINGS, 1);
					case BOOTS -> new ItemStack(Material.LEATHER_BOOTS, 1);
				},
				Rarity.EPIC,
				convertColoredString(MessageFormat.format("{0}Emerald {1}", Rarity.getColorRarity(Rarity.EPIC), WordUtils.capitalizeFully(SlotType.slotName(slot)))),
				switch(slot) {
					case HELMET, BOOTS -> 3;
					case CHESTPLATE -> 8;
					case LEGGINGS -> 6;
				},
				slot
		);
		this.armorToughness = 2;
		this.emeraldAmount = emeraldAmount;
	}

	public EmeraldArmor(ItemStack item, int emeraldAmount) {
		super(
				item,
				Rarity.EPIC,
				convertColoredString(MessageFormat.format("{0}Emerald {1}", Rarity.getColorRarity(Rarity.EPIC), WordUtils.capitalizeFully(SlotType.slotName(SlotType.matchSlot(item.getType().getEquipmentSlot()))))),
				switch(SlotType.matchSlot(item.getType().getEquipmentSlot())) {
					case HELMET, BOOTS -> 3;
					case CHESTPLATE -> 8;
					case LEGGINGS -> 6;
				},
				SlotType.matchSlot(item.getType().getEquipmentSlot())
		);
		this.armorToughness = 2;
		this.emeraldAmount = emeraldAmount;

	}

	private ArrayList<String> getLore() {
		ArrayList<String> lore = new ArrayList<>();
		addLine(lore, "&6Piece Upgrade: Emerald Blood");
		addLine(lore, "&7Mine emeralds to increase your max health.");
		if(emeraldAmount >= 250) {
			addLine(lore, "&7Current Bonus (&a5&8/&a5&7): &e+10 Health &a&lMAXED OUT");
		}
		else {
			addLine(lore, MessageFormat.format("&7Current Bonus (&a{0}&8/&a5&7): &e+{0} Health", emeraldAmount / 50));
			addLine(lore, MessageFormat.format("&7Next Upgrade: &e+{0} Health &8(&a{1}&7/&c50&8)", emeraldAmount / 50 + 2, emeraldAmount % 50));
			addLine(lore, "&8Max +10 Health");
		}
		addLine(lore);
		addLine(lore, MessageFormat.format("{0}&l{1}", Rarity.getColorRarity(rarity), rarity));
		return lore;
	}

	public ItemStack getItem() {
		ItemStack item = getBaseItem(getLore());
		LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
		itemMeta.setColor(Color.LIME);
		int healthBoost = emeraldAmount / 50 > 5 ? 10 : emeraldAmount / 50 * 2;
		itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "maxHealth", healthBoost, AttributeModifier.Operation.ADD_NUMBER, SlotType.matchSlot(slot)));
		itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "armorToughness", armorToughness, AttributeModifier.Operation.ADD_NUMBER, SlotType.matchSlot(slot)));
		item.setItemMeta(itemMeta);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setInteger("EmeraldCount", emeraldAmount);
		nbtItem.setInteger("ArmorToughness", armorToughness);
		return nbtItem.getItem();
	}
}