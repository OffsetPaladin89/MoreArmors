package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.armors.EmeraldArmor;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public record Give(MoreArmorsMain plugin) {
	public ItemStack give(MaterialType type, Integer amount) {
		return switch (type) {
			case COMPACTED_BLAZE_ROD -> plugin.materials.CompactedBlazeRod(amount);
			case COMPACTED_COBBLESTONE -> plugin.materials.CompactedCobblestone(amount);
			case COMPACTED_END_STONE -> plugin.materials.CompactedEndStone(amount);
			case COMPACTED_EYE_OF_ENDER -> plugin.materials.CompactedEyeOfEnder(amount);
			case COMPACTED_SOUL_SAND -> plugin.materials.CompactedSoulSand(amount);
			case COMPACTED_SUGAR_CANE -> plugin.materials.CompactedSugarCane(amount);
			case NETHER_CROWN -> plugin.materials.NetherCrown();
			case COMPACTED_DIAMOND -> plugin.materials.CompactedDiamond(amount);
			case COMPACTED_DIAMOND_BLOCK -> plugin.materials.CompactedDiamondBlock(amount);
			case COMPACTED_GOLD -> plugin.materials.CompactedGold(amount);
			case COMPACTED_GOLD_BLOCK -> plugin.materials.CompactedGoldBlock(amount);
			case COMPACTED_PRISMARINE -> plugin.materials.CompactedPrismarine(amount);
			case COMPACTED_REDSTONE -> plugin.materials.CompactedRedstone(amount);
			case COMPACTED_IRON -> plugin.materials.CompactedIron(amount);
			case COMPACTED_IRON_BLOCK -> plugin.materials.CompactedIronBlock(amount);
			case STAR_DUST -> plugin.materials.StarDust(amount);
			case MACHINE_PART -> plugin.materials.MachinePart(amount);
			case ENERGY_CELL -> plugin.materials.EnergyCell();
			case MACHINE_CORE -> plugin.materials.MachineCore();
		};
	}

	public ItemStack give(ArmorType type, SlotType slotType, Integer specialValue) {
		EmeraldArmor emeraldHelmet = new EmeraldArmor(new ItemStack(Material.LEATHER_HELMET));

		emeraldHelmet.setArmor(3);
		emeraldHelmet.setArmorToughness(2);
		emeraldHelmet.setEmeraldCount(0);
		emeraldHelmet.createItem();

		return emeraldHelmet.getItem();

//		return switch (type) {
//			case EMERALD -> plugin.armorSets.EmeraldArmor(SlotType.matchSlot(slotType), specialValue);
//			case END -> plugin.armorSets.EndArmor(SlotType.matchSlot(slotType));
//			case EXPERIENCE -> plugin.armorSets.ExperienceArmor(SlotType.matchSlot(slotType));
//			case MINER -> plugin.armorSets.MinerArmor(SlotType.matchSlot(slotType));
//			case NETHER -> plugin.armorSets.NetherArmor(SlotType.matchSlot(slotType));
//			case SEA_GREED -> plugin.armorSets.SeaGreedArmor(SlotType.matchSlot(slotType));
//			case SPEEDSTER -> plugin.armorSets.SpeedsterArmor(SlotType.matchSlot(slotType));
//			case TITAN -> plugin.armorSets.TitanArmor(SlotType.matchSlot(slotType));
//			case DESTROYER -> plugin.armorSets.DestroyerArmor(SlotType.matchSlot(slotType), specialValue);
//		};
	}

	public void giveCommand(CommandSender sender, Player target, String type, SlotType slotType, Integer specialValue) {
		ItemStack item = give(ArmorType.getSetType(type), slotType, specialValue);
		PlayerInventory inventory = target.getInventory();
		if (inventory.firstEmpty() == -1) target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
		else inventory.addItem(item);
		giveMessage(sender, target, 1, item);
	}

	public void giveCommand(CommandSender sender, Player target, MaterialType type, Integer amount) {
		ItemStack item = give(type, amount);
		PlayerInventory inventory = target.getInventory();
		if (type.equals(MaterialType.NETHER_CROWN) || type.equals(MaterialType.ENERGY_CELL) || type.equals(MaterialType.MACHINE_CORE)) {
			for (int x = 0; x < amount; x++) {
				item = give(type, amount);
				if (inventory.firstEmpty() == -1) target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
				else inventory.addItem(item);
			}
		} else if (inventory.firstEmpty() == -1) target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
		else inventory.addItem(item);
		giveMessage(sender, target, amount, item);
	}

	public void giveMessage(CommandSender sender, Player target, Integer n, ItemStack i) {
//		target.sendMessage(plugin.convertColoredString("&e(&6MoreArmors&e) Received " + n + "x " + i.getItemMeta().getDisplayName() + "&e."));
//		sender.sendMessage(plugin.convertColoredString("&e(&6MoreArmors&e) Gave " + target.getName() + " " + n + "x " + i.getItemMeta().getDisplayName() + "&e."));
	}
}
