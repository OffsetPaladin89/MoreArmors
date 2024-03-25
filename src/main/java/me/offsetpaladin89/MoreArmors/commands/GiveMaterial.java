package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public record GiveMaterial(MoreArmorsMain plugin) {

	public ItemStack GiveMaterialItem(MaterialType type, Integer amount) {
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
			case DIAMOND_SINGULARITY -> plugin.materials.DiamondSingularity();
			case COMPACTED_GOLD -> plugin.materials.CompactedGold(amount);
			case COMPACTED_GOLD_BLOCK -> plugin.materials.CompactedGoldBlock(amount);
			case COMPACTED_PRISMARINE -> plugin.materials.CompactedPrismarine(amount);
		};
	}

	public void GiveMaterialCommand(CommandSender sender, Player target, MaterialType type, int amount) {
		ItemStack item = GiveMaterialItem(type, amount);
		PlayerInventory inventory = target.getInventory();
		if (type.equals(MaterialType.DIAMOND_SINGULARITY) || type.equals(MaterialType.NETHER_CROWN)) {
			for (int x = 0; x < amount; x++) {
				item = GiveMaterialItem(type, amount);
				if (inventory.firstEmpty() == -1)
					target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
				else inventory.addItem(item);
			}
		} else if (inventory.firstEmpty() == -1)
			target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
		else inventory.addItem(item);
		target.sendMessage(plugin.convertColoredString("&e(&6MoreArmors&e) Received " + amount + "x " + item.getItemMeta().getDisplayName() + "&e."));
		sender.sendMessage(plugin.convertColoredString("&e(&6MoreArmors&e) Gave " + target.getName() + " " + amount + "x " + item.getItemMeta().getDisplayName() + "&e."));
	}
}
