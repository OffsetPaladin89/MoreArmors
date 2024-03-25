package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.SetType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public record CommandsMessages(MoreArmorsMain plugin) {

	public String prefix() {
		return plugin.convertColoredString("&e(&6MoreArmors&e)");
	}

	public void sendColoredMessage(CommandSender sender, String message) {
		sender.sendMessage(plugin.convertColoredString(message));
	}

	public void tooManyArguments(CommandSender sender) {
		sendColoredMessage(sender, prefix() + " &cToo many arguments! Do /morearmors help for help.");
	}

	public void noPermission(CommandSender sender) {
		sendColoredMessage(sender, prefix() + " &cYou do not have permission to do this!");
	}

	public void invalidArgument(CommandSender sender, String argument) {
		sendColoredMessage(sender, prefix() + " &cInvalid argument " + argument + ".");
	}

	public void sendMaterialTypes(CommandSender sender, String player, String type) {
		sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morearmors give " + player + " " + type.toLowerCase() + " ... )");
		sendColoredMessage(sender, "&6> &eCompactedBlazeRod");
		sendColoredMessage(sender, "&6> &eCompactedCobblestone");
		sendColoredMessage(sender, "&6> &eCompactedEndStone");
		sendColoredMessage(sender, "&6> &eCompactedEyeOfEnder");
		sendColoredMessage(sender, "&6> &eCompactedSoulSand");
		sendColoredMessage(sender, "&6> &eCompactedSugarCane");
		sendColoredMessage(sender, "&6> &eNetherCrown");
		sendColoredMessage(sender, "&6> &eCompactedDiamond");
		sendColoredMessage(sender, "&6> &eCompactedDiamondBlock");
		sendColoredMessage(sender, "&6> &eDiamondSingularity");
		sendColoredMessage(sender, "&6> &eCompactedIronIngot");
		sendColoredMessage(sender, "&6> &eCompactedIronBlock");
		sendColoredMessage(sender, "&6> &eCompactedRedstone");
		sendColoredMessage(sender, "&6> &eEnergyCell");
		sendColoredMessage(sender, "&6> &eMachineCore");
		sendColoredMessage(sender, "&6> &eMachinePart");
		sendColoredMessage(sender, "&6> &eStarDust");
	}

	public void sendArmorTypes(CommandSender sender, String player, String type) {
		sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morearmors give " + player + " " + type.toLowerCase() + " ... )");
		sendColoredMessage(sender, "&6> &eEmerald");
		sendColoredMessage(sender, "&6> &eEnd");
		sendColoredMessage(sender, "&6> &eExperience");
		sendColoredMessage(sender, "&6> &eMiner");
		sendColoredMessage(sender, "&6> &eNether");
		sendColoredMessage(sender, "&6> &eSeaGreed");
		sendColoredMessage(sender, "&6> &eSpeedster");
		sendColoredMessage(sender, "&6> &eTitan");
		sendColoredMessage(sender, "&6> &eTrueDiamond");
		sendColoredMessage(sender, "&6> &eDestroyer");
	}

	public void sendEquipmentTypes(CommandSender sender, String player, String type, String item) {
		sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morearmors give " + player + " " + type.toLowerCase() + " " + item.toLowerCase() + " ... )");
		sendColoredMessage(sender, "&6> &eHelmet");
		sendColoredMessage(sender, "&6> &eChestplate");
		sendColoredMessage(sender, "&6> &eLeggings");
		sendColoredMessage(sender, "&6> &eBoots");
	}

	public void editItemMessage(CommandSender sender, Player target, ItemStack item) {
		sendColoredMessage(sender, prefix() + " &eEdited " + item.getItemMeta().getDisplayName() + " &efor " + target.getName() + "&e.");
		sendColoredMessage(target, prefix() + " &eEdited " + item.getItemMeta().getDisplayName() + "&e.");
	}

	public void resetItemMessage(CommandSender sender, Player target, ItemStack item) {
		sendColoredMessage(sender, prefix() + " &eReset " + item.getItemMeta().getDisplayName() + " &efor " + target.getName() + "&e.");
		sendColoredMessage(target, prefix() + " &eReset " + item.getItemMeta().getDisplayName() + "&e.");
	}

	public boolean isInteger(String string) {
		try {
			Integer.parseInt(string);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void helpMessage(CommandSender sender) {
		sendColoredMessage(sender, prefix() + " &eRunning &6" + plugin.getName() + " " + plugin.getDescription().getVersion() + "&e.");
		sendColoredMessage(sender, "&6> &e/morearmors edit <user>");
		sendColoredMessage(sender, "&6> &e/morearmors give <user>");
		sendColoredMessage(sender, "&6> &e/morearmors help");
		sendColoredMessage(sender, "&6> &e/morearmors info");
	}

	public void infoHelpMessage(CommandSender sender) {
		sendColoredMessage(sender, prefix() + " &6Info Command Options: &e(/morearmors info ...)");
		sendColoredMessage(sender, "&6> &eMoreArmors");
	}

	public void pluginInfoMessage(CommandSender sender) {
		sendColoredMessage(sender, prefix() + " &eRunning &6" + plugin.getName() + " " + plugin.getDescription().getVersion() + " &ecreated by &6OffsetPaladin89&e.");
		sendColoredMessage(sender, "&6> &eOfficial Site: https://dev.bukkit.org/projects/MoreArmors");
	}

	private boolean isGreaterThanAndTrueDiamond(String level, int value) {
		return Integer.parseInt(level) > value && level.equalsIgnoreCase("truediamond");
	}

	public void giveMessage(Integer length, CommandSender sender, String player, String type, String item, String slot, String specialValue) {

		if (length > 1) {
			if (plugin.getServer().getPlayer(player) == null) {
				invalidArgument(sender, player);
				return;
			}
		}
		if (length < 3) {
			sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morearmors give " + player + " ... )");
			if (length == 2) {
				sendColoredMessage(sender, "&6> &eArmor");
				sendColoredMessage(sender, "&6> &eMaterial");
			}
			return;
		}
		Player target = plugin.getServer().getPlayer(player);

		switch (type.toLowerCase()) {
			case "armor" -> {
				if (length == 3) {
					sendArmorTypes(sender, player, type);
				} else {
					if (SetType.getSetType(item) != null) {
						SetType setType = SetType.getSetType(item);
						if (length == 4) {
							sendEquipmentTypes(sender, player, type, item);
						} else {
							if (ArmorType.typeFromString(slot) != null) {
								ArmorType slotType = ArmorType.typeFromString(slot);
								if (length == 5) {
									plugin.givearmor.GiveArmorCommand(sender, target, item.toLowerCase(), slotType, 0);
								} else {
									if (setType.equals(SetType.EMERALD)) {
										plugin.givearmor.GiveArmorCommand(sender, target, item.toLowerCase(), slotType, Integer.parseInt(specialValue));
									} else {
										invalidArgument(sender, specialValue);
									}
								}
							}
						}
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
							plugin.givematerial.GiveMaterialCommand(sender, target, materialType, 1);
						} else {
							if (isInteger(slot) && Integer.parseInt(slot) > 0) {
								plugin.givematerial.GiveMaterialCommand(sender, target, materialType, Integer.parseInt(slot));
							} else {
								invalidArgument(sender, slot);
							}
						}
					}
				}
			}
			default -> {
				invalidArgument(sender, type);
			}
		}
	}

//	public void editMessage(Integer length, CommandSender sender, String player, String type, String amount) {
//
//		Player target = null;
//
//		if (length > 1) {
//			if (plugin.getServer().getPlayerExact(player) != null) {
//				target = plugin.getServer().getPlayerExact(player);
//				if (length == 2) { player = target.getName(); }
//			} else {
//				invalidArgument(sender, player);
//				return;
//			}
//		}
//
//		if (length == 1 || type == null) {
//			sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morepluginscore edit " + player + " ... )");
//			if (getPlugin("MoreArmors") != null) {
//				sendColoredMessage(sender, "&6> &eEmeraldCount");
//				sendColoredMessage(sender, "&6> &eArmorLevel");
//				sendColoredMessage(sender, "&6> &eDiamondSingularity");
//			}
//			if (getPlugin("MoreArmorsExtra") != null) { sendColoredMessage(sender, "&6> &eKills"); }
//			return;
//		}
//
//		PlayerInventory inventory = target.getInventory();
//		if (!plugin.isAirOrNull(inventory.getItemInMainHand())) {
//			ItemStack hand = inventory.getItemInMainHand();
//			NBTItem nbtItem = new NBTItem(hand);
//			if (type != null) {
//				switch (type.toLowerCase()) {
//					case "emeraldcount":
//						if (getPlugin("MoreArmors") != null) {
//							if (nbtItem.getString("CustomItemID").equals("emerald")) {
//								switch (length) {
//									case 3:
//										// inventory.setItemInMainHand(plugin.morearmors.emerald.UpdateItem(hand, 0));
//										resetItemMessage(sender, target, hand);
//										break;
//									case 4:
//										if (isInteger(amount)) {
//											if (!(Integer.parseInt(amount) > 0)) {
//												invalidArgument(sender, amount);
//												return;
//											}
//											// inventory.setItemInMainHand(plugin.morearmors.emerald.UpdateItem(hand, Integer.parseInt(amount)));
//											editItemMessage(sender, target, hand);
//										}
//										else { invalidArgument(sender, amount); }
//										break;
//									default:
//										tooManyArguments(sender);
//										break;
//								}
//							}
//						}
//						break;
//					case "armorlevel":
//						if (getPlugin("MoreArmors") != null) {
//							if (nbtItem.getString("CustomItemID").equals("truediamond")) {
//								int diamondsacrifice = nbtItem.getInteger("DiamondSacrifice");
//								switch (length) {
//									case 3:
////										inventory.setItemInMainHand(plugin.morearmors.truediamond.UpdateItem(hand, Integer.parseInt(amount), diamondsacrifice));
//										resetItemMessage(sender, target, hand);
//										break;
//									case 4:
//										if (isInteger(amount)) {
//											if (!(Integer.parseInt(amount) > 0 && Integer.parseInt(amount) <= 10)) {
//												invalidArgument(sender, amount);
//												return;
//											}
////											inventory.setItemInMainHand(plugin.morearmors.truediamond.UpdateItem(hand, Integer.parseInt(amount), diamondsacrifice));
//											editItemMessage(sender, target, hand);
//										}
//										else { invalidArgument(sender, amount); }
//										break;
//									default:
//										tooManyArguments(sender);
//										break;
//								}
//							}
//						}
//						break;
//					case "diamondsacrifice":
//						if (getPlugin("MoreArmors") != null) {
//							if (nbtItem.getString("CustomItemID").equals("truediamond")) {
//								int armorlevel = nbtItem.getInteger("ArmorLevel");
//								switch (length) {
//									case 3:
////										inventory.setItemInMainHand(plugin.morearmors.truediamond.UpdateItem(hand, armorlevel, Integer.parseInt(amount)));
//										resetItemMessage(sender, target, hand);
//										break;
//									case 4:
//										if (isInteger(amount)) {
//											if (!(Integer.parseInt(amount) > 0 && Integer.parseInt(amount) <= 50)) {
//												invalidArgument(sender, amount);
//												return;
//											}
////											inventory.setItemInMainHand(plugin.morearmors.truediamond.UpdateItem(hand, armorlevel, Integer.parseInt(amount)));
//											editItemMessage(sender, target, hand);
//										}
//										else { invalidArgument(sender, amount); }
//										break;
//									default:
//										tooManyArguments(sender);
//										break;
//								}
//							}
//						}
//						break;
//					case "kills":
//						if (getPlugin("MoreArmorsExtra") != null) {
//							if (nbtItem.getString("CustomItemID").equals("destroyer")) {
//								switch (length) {
//									case 3:
//										inventory.setItemInMainHand(plugin.morearmorsextra.destroyer.UpdateItem(hand, 0));
//										resetItemMessage(sender, target, hand);
//										break;
//									case 4:
//										if (isInteger(amount)) {
//											if (!(Integer.parseInt(amount) > 0)) {
//												invalidArgument(sender, amount);
//												return;
//											}
//											inventory.setItemInMainHand(plugin.morearmorsextra.destroyer.UpdateItem(hand, Integer.parseInt(amount)));
//											editItemMessage(sender, target, hand);
//										}
//										else { invalidArgument(sender, amount); }
//										break;
//									default:
//										tooManyArguments(sender);
//										break;
//								}
//							}
//						}
//						break;
//					default:
//						invalidArgument(sender, type);
//						break;
//				}
//			}
//		}
//	}
}
