package me.offsetpaladin89.MoreArmors.commands;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

public record CommandsMessages(MoreArmorsMain plugin) {
//
//	public String prefix() { return plugin.convertColoredString("&e(&6MorePluginsCore&e)"); }
//
//	public String version(Plugin plugin) { return plugin.getDescription().getVersion(); }
//
//	public String pluginName(Plugin plugin) { return plugin.getName(); }
//
//	public void sendColoredMessage(CommandSender sender, String message) { sender.sendMessage(plugin.convertColoredString(message)); }
//
//	public Plugin getPlugin(String string) { return plugin.getServer().getPluginManager().getPlugin(string); }
//
//	public void tooManyArguments(CommandSender sender) { sendColoredMessage(sender, prefix() + " &cToo many arguments! Do /morepluginscore help for help."); }
//
//	public void noPermission(CommandSender sender) { sendColoredMessage(sender, prefix() + " &cYou do not have permission to do this!"); }
//
//	public void invalidArgument(CommandSender sender, String argument) { sendColoredMessage(sender, prefix() + " &cInvalid argument " + argument + "."); }
//
//	public void editItemMessage(CommandSender sender, Player target, ItemStack item) {
//		sendColoredMessage(sender, prefix() + " &eEdited " + item.getItemMeta().getDisplayName() + " &efor " + target.getName() + "&e.");
//		sendColoredMessage(target, prefix() + " &eEdited " + item.getItemMeta().getDisplayName() + "&e.");
//	}
//
//	public void resetItemMessage(CommandSender sender, Player target, ItemStack item) {
//		sendColoredMessage(sender, prefix() + " &eReset " + item.getItemMeta().getDisplayName() + " &efor " + target.getName() + "&e.");
//		sendColoredMessage(target, prefix() + " &eReset " + item.getItemMeta().getDisplayName() + "&e.");
//	}
//
//	public boolean isInteger(String string) {
//		try { Integer.parseInt(string); }
//		catch (Exception e) { return false; }
//		return true;
//	}
//
//	public void helpMessage(CommandSender sender) {
//		sendColoredMessage(sender, prefix() + " &eRunning &6" + pluginName(getPlugin("MorePluginsCore")) + " " + version(getPlugin("MorePluginsCore")) + "&e.");
//		sendColoredMessage(sender, "&6> &e/morepluginscore edit <user>");
//		sendColoredMessage(sender, "&6> &e/morepluginscore give <user>");
//		sendColoredMessage(sender, "&6> &e/morepluginscore help");
//		sendColoredMessage(sender, "&6> &e/morepluginscore info");
//	}
//
//	public void infoHelpMessage(CommandSender sender) {
//		sendColoredMessage(sender, prefix() + " &6Info Command Options: &e(/morepluginscore info ...)");
//		sendColoredMessage(sender, "&6> &eMorePluginsCore");
//		if (getPlugin("MoreArmors") != null) { sendColoredMessage(sender, "&6> &eMoreArmors"); }
//		if (getPlugin("MoreArmorsExtra") != null) { sendColoredMessage(sender, "&6> &eMoreArmorsExtra"); }
//		if (getPlugin("MoreWeapons") != null) { sendColoredMessage(sender, "&6> &eMoreWeapons"); }
//	}
//
//	public void pluginInfoMessage(CommandSender sender, String plugin) {
//		String link;
//		switch (plugin.toLowerCase()) {
//			case "morepluginscore" -> {
//				plugin = "MorePluginsCore";
//				link = "https://dev.bukkit.org/projects/MorePluginsCore";
//			}
//			case "morearmors" -> {
//				plugin = "MoreArmors";
//				link = "https://dev.bukkit.org/projects/MoreArmors";
//			}
//			case "moreweapons" -> {
//				plugin = "MoreWeapons";
//				link = "https://dev.bukkit.org/projects/MoreWeapons";
//			}
//			case "morearmorsextra" -> {
//				plugin = "MoreArmorsExtra";
//				link = "https://www.patreon.com/OffsetPaladin89";
//			}
//			default -> {
//				invalidArgument(sender, plugin);
//				return;
//			}
//		}
//		sendColoredMessage(sender, prefix() + " &eRunning &6" + pluginName(getPlugin(plugin)) + " " + version(getPlugin(plugin)) + " &ecreated by &6OffsetPaladin89&e.");
//		sendColoredMessage(sender, "&6> &eOfficial Site: " + link);
//	}
//
//	private boolean isGreaterThanAndTrueDiamond(String level, int value) { return Integer.parseInt(level) > value && level.equalsIgnoreCase("truediamond"); }
//
//	public void giveMessage(Integer length, CommandSender sender, String player, String type, String item, String slot, String level, String amount) {
//
//		Player target = null;
//
//		if (length == 2) {
//			try { player = plugin.getServer().getPlayer(player).getName(); }
//			catch (Exception e) {
//				invalidArgument(sender, player);
//				return;
//			}
//		}
//		else if (length > 2) {
//			try { target = plugin.getServer().getPlayer(player); }
//			catch (Exception e) {
//				invalidArgument(sender, player);
//				return;
//			}
//		}
//
//		if (type == null) {
//			sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morepluginscore give " + player + " ... )");
//			if ((getPlugin("MoreArmors") != null) || (getPlugin("MoreArmorsExtra") != null)) { sendColoredMessage(sender, "&6> &eArmor"); }
//			if ((getPlugin("MoreArmors") != null) || (getPlugin("MoreArmorsExtra") != null)) { sendColoredMessage(sender, "&6> &eMaterial"); }
//			return;
//		}
//		switch (type.toLowerCase()) {
//			case "armor":
//				if (getPlugin("MoreArmors") != null || getPlugin("MoreArmorsExtra") != null) {
//					if (item == null) {
//						sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morepluginscore give " + player + " " + type.toLowerCase() + " ... )");
//						if (getPlugin("MoreArmors") != null) {
//							sendColoredMessage(sender, "&6> &eEmerald");
//							sendColoredMessage(sender, "&6> &eEnd");
//							sendColoredMessage(sender, "&6> &eExperience");
//							sendColoredMessage(sender, "&6> &eMiner");
//							sendColoredMessage(sender, "&6> &eNether");
//							sendColoredMessage(sender, "&6> &eSeaGreed");
//							sendColoredMessage(sender, "&6> &eSpeedster");
//							sendColoredMessage(sender, "&6> &eTitan");
//							sendColoredMessage(sender, "&6> &eTrueDiamond");
//						}
//						if (getPlugin("MoreArmorsExtra") != null) { sendColoredMessage(sender, "&6> &eDestroyer"); }
//					} else {
//						if (SetTypes.valueOf(slot.toUpperCase()) != null) {
//							if (slot == null) {
//								sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morepluginscore give " + player + " " + type.toLowerCase() + " " + item.toLowerCase() + " ... )");
//								sendColoredMessage(sender, "&6> &eHelmet");
//								sendColoredMessage(sender, "&6> &eChestplate");
//								sendColoredMessage(sender, "&6> &eLeggings");
//								sendColoredMessage(sender, "&6> &eBoots");
//							} else {
//								if (plugin.validArmors.contains(item.toLowerCase())) {
//									if (plugin.validSlots.contains(null)) {
//										switch (length) {
//											case 5:
//												// plugin.morearmors.givearmor.GiveArmorCommand(sender, target, item.toLowerCase(), null, 1, 0);
//												break;
////												switch (plugin.validArmors.get(item.toLowerCase())) {
////													case "morearmors":
////
////													case "morearmorsextra":
////														if (plugin.validArmors.get(item.toLowerCase()).equals("morearmorsextra")) {
////															// plugin.morearmorsextra.givearmor.GiveArmorCommand(sender, target, item.toLowerCase(), null, 0, 0);
////															break;
////														}
////													default:
////														invalidArgument(sender, item);
////														return;
////												}
//											case 6:
//												if (isInteger(level)) {
//													if (!(Integer.parseInt(level) > 0)) {
//														invalidArgument(sender, level);
//														return;
//													}
//													if (sender instanceof Player) {
//														if (isGreaterThanAndTrueDiamond(level, 10)) {
//															invalidArgument(sender, level);
//															return;
//														}
//													} else {
//														if (isGreaterThanAndTrueDiamond(level, 24)) {
//															invalidArgument(sender, level);
//															return;
//														}
//													}
//													if(item.equalsIgnoreCase("emerald")) {
//														// plugin.morearmors.givearmor.GiveArmorCommand(sender, target, item.toLowerCase(), null, 0, Integer.parseInt(level));
//													}
//													else {
//														// plugin.morearmors.givearmor.GiveArmorCommand(sender, target, item.toLowerCase(), null, Integer.parseInt(level), 0);
//													}
//													break;
////													switch (plugin.validArmors) {
////														case "morearmors":
////															if (plugin.validArmors.get(item.toLowerCase()).equals("morearmors")) {
////
////															}
////														case "morearmorsextra":
////															if (plugin.validArmors.get(item.toLowerCase()).equals("morearmorsextra")) {
////																// plugin.morearmorsextra.givearmor.GiveArmorCommand(sender, target, item.toLowerCase(), null, 0, Integer.parseInt(level));
////																break;
////															}
////														default:
////															invalidArgument(sender, item);
////															return;
////													}
//												}
//												else { invalidArgument(sender, level); }
//												break;
//											case 7:
//												if (isInteger(level)) {
//													if (!(Integer.parseInt(level) > 0)) {
//														invalidArgument(sender, level);
//														return;
//													}
//													if (sender instanceof Player) {
//														if (Integer.parseInt(level) > 10 && item.equalsIgnoreCase("truediamond")) {
//															invalidArgument(sender, level);
//															return;
//														}
//													} else {
//														if (Integer.parseInt(level) > 24 && item.equalsIgnoreCase("truediamond")) {
//															invalidArgument(sender, level);
//															return;
//														}
//													}
//													if (isInteger(amount)) {
//														if (Integer.parseInt(amount) > 50 && item.equalsIgnoreCase("truediamond") && Integer.parseInt(amount) >= 0) {
//															invalidArgument(sender, amount);
//															return;
//														} else {
////															if ("morearmors".equals(plugin.validArmors.get(item.toLowerCase()))) {
////
////																if (plugin.validArmors.get(item.toLowerCase()).equals("morearmors")) {
////																	// plugin.morearmors.givearmor.GiveArmorCommand(sender, target, item.toLowerCase(), null, Integer.parseInt(level), Integer.parseInt(amount));
////																	break;
////																}
////															}
//															invalidArgument(sender, item);
//															return;
//														}
//													}
//													else { invalidArgument(sender, amount); }
//												}
//												else { invalidArgument(sender, level); }
//												break;
//											default:
//												tooManyArguments(sender);
//												break;
//										}
//									}
//									else {
//										invalidArgument(sender, slot);
//										return;
//									}
//								}
//								else {
//									invalidArgument(sender, item);
//									return;
//								}
//							}
//						}
//						else {
//							invalidArgument(sender, item);
//						}
//
//					}
//					break;
//				}
//			case "material":
//				if (getPlugin("MoreArmors") != null || getPlugin("MoreArmorsExtra") != null) {
//					if (item == null) {
//						sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morepluginscore give " + player + " " + type.toLowerCase() + " ... )");
//						if (getPlugin("MoreArmors") != null) {
//							sendColoredMessage(sender, "&6> &eCompactedBlazeRod");
//							sendColoredMessage(sender, "&6> &eCompactedCobblestone");
//							sendColoredMessage(sender, "&6> &eCompactedEndStone");
//							sendColoredMessage(sender, "&6> &eCompactedEyeOfEnder");
//							sendColoredMessage(sender, "&6> &eCompactedSoulSand");
//							sendColoredMessage(sender, "&6> &eCompactedSugarCane");
//							sendColoredMessage(sender, "&6> &eNetherCrown");
//							sendColoredMessage(sender, "&6> &eCompactedDiamond");
//							sendColoredMessage(sender, "&6> &eCompactedDiamondBlock");
//							sendColoredMessage(sender, "&6> &eDiamondSingularity");
//						}
//						if (getPlugin("MoreArmorsExtra") != null) {
//							sendColoredMessage(sender, "&6> &eCompactedIronIngot");
//							sendColoredMessage(sender, "&6> &eCompactedIronBlock");
//							sendColoredMessage(sender, "&6> &eCompactedRedstone");
//							sendColoredMessage(sender, "&6> &eEnergyCell");
//							sendColoredMessage(sender, "&6> &eMachineCore");
//							sendColoredMessage(sender, "&6> &eMachinePart");
//							sendColoredMessage(sender, "&6> &eStarDust");
//						}
//					} else {
//						if (slot == null) {
//							if (plugin.validMaterials.containsKey(item.toLowerCase())) {
//								switch (plugin.validMaterials.get(item.toLowerCase())) {
//									case "morearmors" -> plugin.givematerial.GiveMaterialCommand(sender, target, item.toLowerCase(), 1);
////									case "morearmorsextra" -> plugin.givematerial.GiveMaterialCommand(sender, target, item.toLowerCase(), 1);
//									default -> {
//										invalidArgument(sender, item);
//										return;
//									}
//								}
//							}
//						} else {
//							if (length > 5) {
//								tooManyArguments(sender);
//								return;
//							}
//							if (plugin.validMaterials.containsKey(item.toLowerCase())) {
//								if (isInteger(slot)) {
//									if (!(Integer.parseInt(slot) > 0)) {
//										invalidArgument(sender, slot);
//										return;
//									}
//									switch (plugin.validMaterials.get(item.toLowerCase())) {
//										case "morearmors" -> plugin.morearmors.givematerial.GiveMaterialCommand(sender, target, item.toLowerCase(), Integer.parseInt(slot));
//										case "morearmorsextra" -> plugin.morearmorsextra.givematerial.GiveMaterialCommand(sender, target, item.toLowerCase(), Integer.parseInt(slot));
//										default -> {
//											invalidArgument(sender, item);
//											return;
//										}
//									}
//								}
//								else { invalidArgument(sender, slot); }
//							}
//							else {
//								invalidArgument(sender, item);
//								return;
//							}
//						}
//					}
//					break;
//				}
//			default:
//				invalidArgument(sender, type);
//				break;
//		}
//	}
//
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
