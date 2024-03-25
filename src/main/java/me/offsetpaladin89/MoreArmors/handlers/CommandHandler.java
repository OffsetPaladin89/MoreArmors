package me.offsetpaladin89.MoreArmors.handlers;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public record CommandHandler(MoreArmorsMain plugin) {

	public void giveMessage(Integer length, CommandSender sender, String player, String type, String item, String slot, String specialValue) {
		if (length > 1) {
			if (plugin.getServer().getPlayer(player) == null) {
				invalidArgument(sender, player);
				return;
			}
		}
		if (length < 3) {
			plugin.sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morearmors give " + player + " ... )");
			if (length == 2) {
				plugin.sendColoredMessage(sender, "&6> &eArmor");
				plugin.sendColoredMessage(sender, "&6> &eMaterial");
			}
			return;
		}
		Player target = plugin.getServer().getPlayer(player);

		switch (type.toLowerCase()) {
			case "armor" -> {
				if (length == 3) {
					sendArmorTypes(sender, player, type);
				} else {
					if (ArmorType.getSetType(item) != null) {
						ArmorType armorType = ArmorType.getSetType(item);
						if (length == 4) {
							sendEquipmentTypes(sender, player, type, item);
						} else {
							if (SlotType.typeFromString(slot) != null) {
								SlotType slotType = SlotType.typeFromString(slot);
								if (length == 5) {
									plugin.give.giveCommand(sender, target, item.toLowerCase(), slotType, 0);
								} else {
									if (plugin.isInteger(specialValue)) {
										if (armorType.equals(ArmorType.EMERALD)) {
											plugin.give.giveCommand(sender, target, item.toLowerCase(), slotType, Integer.parseInt(specialValue));
										} else {
											tooManyArguments(sender);
										}
									} else {
										invalidArgument(sender, specialValue);
									}
								}
							} else {
								invalidArgument(sender, slot);
							}
						}
					} else {
						invalidArgument(sender, item);
					}
				}
			}
			case "material" -> {
				if (length == 3) {
					sendMaterialTypes(sender, player, type);
				} else {
					if (MaterialType.getMaterialType(item) != null) {
						MaterialType materialType = MaterialType.getMaterialType(item);
						if (length == 4) {
							plugin.give.giveCommand(sender, target, materialType, 1);
						} else if (length == 5) {
							if (plugin.isInteger(slot) && Integer.parseInt(slot) > 0) {
								plugin.give.giveCommand(sender, target, materialType, Integer.parseInt(slot));
							} else {
								invalidArgument(sender, slot);
							}
						} else {
							tooManyArguments(sender);
						}
					}
				}
			}
			default -> invalidArgument(sender, type);
		}
	}

	public void editMessage(Integer length, CommandSender sender, String player, String type, String specialValue) {

		if (length > 1) {
			if (plugin.getServer().getPlayer(player) == null) {
				invalidArgument(sender, player);
				return;
			}
		}
		if (length < 3) {
			plugin.sendColoredMessage(sender, prefix() + " &6Edit Command Options: &e(/morearmors give " + player + " ... )");
			if (length == 2) {
				plugin.sendColoredMessage(sender, "&6> &eEmeraldCount");
				plugin.sendColoredMessage(sender, "&6> &eArmorLevel");
				plugin.sendColoredMessage(sender, "&6> &eDiamondSingularity");
			}
			return;
		}
		Player target = plugin.getServer().getPlayer(player);
		PlayerInventory inventory = target.getInventory();
		if (plugin.isAirOrNull(inventory.getItemInMainHand())) return;
		ItemStack hand = inventory.getItemInMainHand();
		NBTItem nbtItem = new NBTItem(hand);
		String cID = nbtItem.getString("CustomItemID");
		if (type.equalsIgnoreCase("emeraldcount")) {
			if (cID.equals("emerald")) {
				if (length == 3) {
					nbtItem.setInteger("EmeraldCount", 0);
					inventory.setItemInMainHand(plugin.armorConstructor.createEmeraldArmor(nbtItem.getItem()));
					resetItemMessage(sender, target, hand);
				} else if (length == 4) {
					if (plugin.isInteger(specialValue)) {
						nbtItem.setInteger("EmeraldCount", Integer.parseInt(specialValue));
						inventory.setItemInMainHand(plugin.armorConstructor.createEmeraldArmor(nbtItem.getItem()));
						editItemMessage(sender, target, hand);
					} else {
						invalidArgument(sender, specialValue);
					}
				} else {
					tooManyArguments(sender);
				}
			}
		} else {
			invalidArgument(sender, type);
		}
	}

	public String prefix() {
		return plugin.convertColoredString("&e(&6MoreArmors&e)");
	}

	public void tooManyArguments(CommandSender sender) {
		plugin.sendColoredMessage(sender, prefix() + " &cToo many arguments! Do /morearmors help for help.");
	}

	public void noPermission(CommandSender sender) {
		plugin.sendColoredMessage(sender, prefix() + " &cYou do not have permission to do this!");
	}

	public void invalidArgument(CommandSender sender, String argument) {
		plugin.sendColoredMessage(sender, prefix() + " &cInvalid argument " + argument + ".");
	}

	public void editItemMessage(CommandSender sender, Player target, ItemStack item) {
		plugin.sendColoredMessage(sender, prefix() + " &eEdited " + item.getItemMeta().getDisplayName() + " &efor " + target.getName() + "&e.");
		plugin.sendColoredMessage(target, prefix() + " &eEdited " + item.getItemMeta().getDisplayName() + "&e.");
	}

	public void resetItemMessage(CommandSender sender, Player target, ItemStack item) {
		plugin.sendColoredMessage(sender, prefix() + " &eReset " + item.getItemMeta().getDisplayName() + " &efor " + target.getName() + "&e.");
		plugin.sendColoredMessage(target, prefix() + " &eReset " + item.getItemMeta().getDisplayName() + "&e.");
	}

	public void sendMaterialTypes(CommandSender sender, String player, String type) {
		plugin.sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morearmors give " + player + " " + type.toLowerCase() + " ... )");
		for (String s : plugin.materialTypes) {
			plugin.sendColoredMessage(sender, "&6> &e" + s);
		}
	}

	public void sendArmorTypes(CommandSender sender, String player, String type) {
		plugin.sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morearmors give " + player + " " + type.toLowerCase() + " ... )");
		for (String s : plugin.armorTypes) {
			plugin.sendColoredMessage(sender, "&6> &e" + s);
		}
	}

	public void sendEquipmentTypes(CommandSender sender, String player, String type, String item) {
		plugin.sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morearmors give " + player + " " + type.toLowerCase() + " " + item.toLowerCase() + " ... )");
		for (String s : plugin.slotTypes) {
			plugin.sendColoredMessage(sender, "&6> &e" + s);
		}
	}

	public void helpMessage(CommandSender sender) {
		plugin.sendColoredMessage(sender, prefix() + " &eRunning &6" + plugin.getName() + " " + plugin.getDescription().getVersion() + "&e.");
		plugin.sendColoredMessage(sender, "&6> &e/morearmors edit <user>");
		plugin.sendColoredMessage(sender, "&6> &e/morearmors give <user>");
		plugin.sendColoredMessage(sender, "&6> &e/morearmors help");
		plugin.sendColoredMessage(sender, "&6> &e/morearmors info");
	}

	public void pluginInfoMessage(CommandSender sender) {
		plugin.sendColoredMessage(sender, prefix() + " &eRunning &6" + plugin.getName() + " " + plugin.getDescription().getVersion() + " &ecreated by &6OffsetPaladin89&e.");
		plugin.sendColoredMessage(sender, "&6> &eOfficial Site: https://dev.bukkit.org/projects/MoreArmors");
	}
}
