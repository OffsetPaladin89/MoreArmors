package me.offsetpaladin89.MoreArmors.commands;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.Main;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.PermissionType;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.text.MessageFormat;
import java.util.List;

import static me.offsetpaladin89.MoreArmors.Main.convertColoredString;

public class Commands implements CommandExecutor {

	private final Main plugin;

	public Commands(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginCommand("morearmors").setExecutor(this);
	}

	private boolean isCommand(Command cmd, String name) {
		return cmd.getName().equalsIgnoreCase(name);
	}

	private enum CommandTypes {
		INFO,
		GIVE,
		EDIT,
		RELOAD,
		GUI,
		INVALID;

		public static CommandTypes getCommand(String type) {
			return switch(type.toLowerCase()) {
				case "info" -> INFO;
				case "give" -> GIVE;
				case "edit" -> EDIT;
				case "reload" -> RELOAD;
				case "gui" -> GUI;
				default -> INVALID;
			};
		}
	}

	private boolean isSame(String arg, String compare) {
		return arg.equalsIgnoreCase(compare);
	}

	private static void sendColoredMessage(CommandSender sender, String message) {
		sender.sendMessage(convertColoredString(message));
	}

	private boolean hasPermission(CommandSender sender, PermissionType permission) {
		return sender.hasPermission(PermissionType.getPermission(permission));
	}

	private void noPermission(CommandSender sender) {
		sendColoredMessage(sender, MessageFormat.format("{0} &cYou do not have permission to use this command!", prefix()));
	}

	private void playersOnly(CommandSender sender) {
		sendColoredMessage(sender, MessageFormat.format("{0} &cOnly players can use this command!", prefix()));
	}

	private void tooManyArguments(CommandSender sender) {
		sendColoredMessage(sender, MessageFormat.format("{0} &cToo many arguments!", prefix()));
	}

	private void infoMessage(CommandSender sender) {
		sendColoredMessage(sender, "");
		sendColoredMessage(sender, MessageFormat.format("{0} &eRunning &6 {1} {2} &ecreated by &6OffsetPaladin89&e.", prefix(), plugin.getName(), plugin.getDescription().getVersion()));
		sendColoredMessage(sender, "&6> &eOfficial Site: &6https://dev.bukkit.org/projects/MoreArmors");
	}

	private void commandHeader(CommandSender sender) {
		sendColoredMessage(sender, "");
		sendColoredMessage(sender, MessageFormat.format("{0} &eAvailable Command Options", prefix()));
	}

	private void addFeedbackOption(CommandSender sender, String option) {
		sendColoredMessage(sender, MessageFormat.format("&6> &e{0}", option));
	}

	private void commandType(CommandSender sender) {
		commandHeader(sender);
		addFeedbackOption(sender, "info");
		if(hasPermission(sender, PermissionType.GIVE)) addFeedbackOption(sender, "give");
		if(hasPermission(sender, PermissionType.EDIT)) addFeedbackOption(sender, "edit");
		if(hasPermission(sender, PermissionType.RELOAD)) addFeedbackOption(sender, "reload");
		if(hasPermission(sender, PermissionType.GUI)) addFeedbackOption(sender, "gui");
	}

	private void giveType(CommandSender sender) {
		commandHeader(sender);
		addFeedbackOption(sender, "armor");
		addFeedbackOption(sender, "material");
	}

	private void armorGiveType(CommandSender sender) {
		commandHeader(sender);
		for(String s : plugin.armorTypes) addFeedbackOption(sender, s);
	}

	private void handleGive(CommandSender sender, String[] arguments) {
		if(!hasPermission(sender, PermissionType.GIVE)) return;
		if(arguments.length == 2) giveType(sender);
		if(arguments.length > 2 && !(isSame(arguments[2], "armor") || isSame(arguments[2], "material"))) return;
		if(isSame(arguments[2], "armor")) handleGiveArmor(sender, arguments);
		else handleGiveMaterial(sender, arguments);
	}

	private void handleGiveArmor(CommandSender sender, String[] arguments) {
		if(arguments.length == 3) {} // TODO send armor type message
		if(arguments.length > 3) {} // TODO if armor type is valid
		if(arguments.length == 4) {} // TODO send slot type message if armor
		if(arguments.length > 4) {} // TODO if slot type is valid
		if(arguments.length == 5) {} // TODO give the item
		if(arguments.length > 5) {} // TODO if is an integer
		if(arguments.length == 6) {} // TODO give the item with the special value modified
		if(arguments.length > 6) {} // TODO send too many args
	}

	private void handleGiveMaterial(CommandSender sender, String[] arguments) {
		if(arguments.length == 3) {} // TODO send material type message
		if(arguments.length > 3) {} // TODO if material type is valid
		if(arguments.length == 4) {} // TODO give the item
		if(arguments.length > 4) {} // TODO if it is an integer
		if(arguments.length == 5) {} // TODO give the item with that amount
	}

	private void handleEdit(CommandSender sender, String[] arguments) {
		if(!hasPermission(sender, PermissionType.EDIT)) return;
		if(arguments.length == 2) {} // TODO send type message
		if(arguments.length > 2) {} // TODO check if type is valid
		if(arguments.length == 3) {} // TODO reset the item
		if(arguments.length > 4) {} // TODO check if argument is an integer
		if(arguments.length == 4) {} // TODO edit the item to the specified value
		if(arguments.length > 5) {} // TODO send too many args
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(isCommand(cmd, "morearmors")) {
			if(args.length == 0){
				helpMessage(sender);
				return true;
			}
			CommandTypes commandType = CommandTypes.getCommand(args[0]);
			if(args.length == 1 && (commandType.equals(CommandTypes.GIVE) || commandType.equals(CommandTypes.EDIT))) {
				commandType(sender);
				return true;
			}
			switch(commandType) {
				case INFO:
					if(args.length != 1) {
						tooManyArguments(sender);
						break;
					}
					infoMessage(sender);
				case GIVE: handleGive(sender, args);
				case EDIT: handleEdit(sender, args);
				case RELOAD:
					if (args.length != 1) {
						tooManyArguments(sender);
						break;
					}
					if (!hasPermission(sender, PermissionType.RELOAD)) {
						noPermission(sender);
						break;
					}
					plugin.reloadConfig(sender);
				case GUI:
					if (args.length != 1) {
						tooManyArguments(sender);
						break;
					}
					if (!hasPermission(sender, PermissionType.GUI)) {
						noPermission(sender);
						break;
					}
					if (sender instanceof Player player) plugin.inv.mainInventory(player).show(player);
					else {
						playersOnly(sender);
						break;
					}
				case INVALID: invalidArgument(sender, args[0]);
			}
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("morearmors")) {
			if (args.length == 0) {
				helpMessage(sender);
			} else {
				switch (args[0].toLowerCase()) {
					case "give" -> {
						if (sender.hasPermission("morearmors.give")) {
							if (args.length < 7) {
								giveMessage(args.length, sender,
										args.length == 1 ? "<user>" : args[1],
										args.length > 2 ? args[2] : null,
										args.length > 3 ? args[3] : null,
										args.length > 4 ? args[4] : null,
										args.length > 5 ? args[5] : null);
							} else tooManyArguments(sender);
						} else {
							noPermission(sender);
						}
					}
					case "edit" -> {
						if (sender.hasPermission("morearmors.edit")) {
							if (args.length < 5) {
								editMessage(args.length, sender,
										args.length == 1 ? "user" : args[1],
										args.length > 2 ? args[2] : null,
										args.length > 3 ? args[3] : null);
							} else tooManyArguments(sender);
						}
					}
					case "reload" -> {
						if(sender.hasPermission("morearmors.reload")) plugin.reloadConfig(sender);
					}
					case "gui" -> {
						if(sender instanceof Player p) {
							plugin.inv.mainInventory(p).show(p);
						}
					}
				}
			}
		}
		return true;
	}

	public void giveMessage(Integer length, CommandSender sender, String player, String type, String item, String slot, String specialValue) {
		if (length > 1) {
			if (plugin.getServer().getPlayer(player) == null) {
				invalidArgument(sender, player);
				return;
			}
		}
		if (length < 3) {
			plugin.sendColoredMessage(sender, "");
			plugin.sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morearmors give " + player + " ... )");
			if (length == 2) {
				plugin.sendColoredMessage(sender, "&6> &earmor");
				plugin.sendColoredMessage(sender, "&6> &ematerial");
			}
			return;
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
			plugin.sendColoredMessage(sender, "");
			plugin.sendColoredMessage(sender, prefix() + " &6Edit Command Options: &e(/morearmors edit " + player + " ... )");
			if (length == 2) {
				plugin.sendColoredMessage(sender, "&6> &eemerald_count");
				plugin.sendColoredMessage(sender, "&6> &ekill_amount");
			}
			return;
		}
		Player target = plugin.getServer().getPlayer(player);
		PlayerInventory inventory = target.getInventory();
		if (plugin.isAirOrNull(inventory.getItemInMainHand())) {
			notHoldingItem(sender);
			return;
		}
		ItemStack hand = inventory.getItemInMainHand();
		NBTItem nbtItem = new NBTItem(hand);
		if (type.equalsIgnoreCase("emerald_count")) {
			if (nbtItem.getString("CustomItemID").equals("emerald")) {
				if (length == 3) {
					nbtItem.setInteger("EmeraldCount", 0);
					inventory.setItemInMainHand(plugin.armorConstructor.createEmeraldArmor(nbtItem.getItem()));
					resetItemMessage(sender, target, hand);
				} else if (length == 4) {
					if (plugin.isInteger(specialValue)) {
						nbtItem.setInteger("EmeraldCount", Integer.parseInt(specialValue));
						inventory.setItemInMainHand(plugin.armorConstructor.createEmeraldArmor(nbtItem.getItem()));
						editItemMessage(sender, target, hand);
					} else invalidArgument(sender, specialValue);
				} else tooManyArguments(sender);
			} else notHoldingItem(sender);
		} else if (type.equals("kill_amount")) {
			if (length == 3) {
				nbtItem.setInteger("KillAmount", 0);
				inventory.setItemInMainHand(plugin.armorConstructor.createDestroyerArmor(nbtItem.getItem()));
				resetItemMessage(sender, target, hand);
			} else if (length == 4) {
				if (plugin.isInteger(specialValue)) {
					nbtItem.setInteger("KillAmount", Integer.parseInt(specialValue));
					inventory.setItemInMainHand(plugin.armorConstructor.createDestroyerArmor(nbtItem.getItem()));
					editItemMessage(sender, target, hand);
				} else invalidArgument(sender, specialValue);
			} else tooManyArguments(sender);
		} else invalidArgument(sender, type);
	}

	public void notHoldingItem(CommandSender s) {
		plugin.sendColoredMessage(s, prefix() + " &cYou are not holding a valid item!");
	}

	public static String prefix() {
		return convertColoredString("&e(&6MoreArmors&e)");
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
		plugin.sendColoredMessage(sender, "");
		plugin.sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morearmors give " + player + " " + type.toLowerCase() + " ... )");
		for (String s : plugin.materialTypes) plugin.sendColoredMessage(sender, "&6> &e" + s);
	}

	public void sendArmorTypes(CommandSender sender, String player, String type) {
		plugin.sendColoredMessage(sender, "");
		plugin.sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morearmors give " + player + " " + type.toLowerCase() + " ... )");
		for (String s : plugin.armorTypes) plugin.sendColoredMessage(sender, "&6> &e" + s);
	}

	public void sendEquipmentTypes(CommandSender sender, String player, String type, String item) {
		plugin.sendColoredMessage(sender, "");
		plugin.sendColoredMessage(sender, prefix() + " &6Give Command Options: &e(/morearmors give " + player + " " + type.toLowerCase() + " " + item.toLowerCase() + " ... )");
		for (String s : plugin.slotTypes) plugin.sendColoredMessage(sender, "&6> &e" + s);
	}

	public boolean helpMessage(CommandSender sender) {
		plugin.sendColoredMessage(sender, "");
		plugin.sendColoredMessage(sender, prefix() + " &6Available Commands:");
		plugin.sendColoredMessage(sender, "&6> &e/morearmors edit <user>");
		plugin.sendColoredMessage(sender, "&6> &e/morearmors give <user>");
		plugin.sendColoredMessage(sender, "&6> &e/morearmors info");
		return true;
	}
}
