package me.offsetpaladin89.MoreArmors.commands;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.Main;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.PermissionType;
import me.offsetpaladin89.MoreArmors.items.DestroyerArmor;
import me.offsetpaladin89.MoreArmors.items.EmeraldArmor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.text.MessageFormat;
import java.util.List;

import static me.offsetpaladin89.MoreArmors.Main.prefix;
import static me.offsetpaladin89.MoreArmors.Main.sendColoredMessage;
import static me.offsetpaladin89.MoreArmors.enums.ArmorType.getSetType;
import static me.offsetpaladin89.MoreArmors.enums.SlotType.typeFromString;

public class Commands implements CommandExecutor {

	/**
	 * Main object for local use.
	 */
	private final Main plugin;

	/**
	 * Constructs a new Commands object with the specified Main class.
	 *
	 * @param plugin the Main plugin
	 */
	public Commands(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginCommand("morearmors").setExecutor(this);
	}

	private enum CommandTypes {
		/**
		 * Info Command Type
		 */
		INFO,

		/**
		 * Give Command Type
		 */
		GIVE,

		/**
		 * Edit Command Type
		 */
		EDIT,

		/**
		 * Reload Command Type
		 */
		RELOAD,

		/**
		 * GUI Command Type
		 */
		GUI,

		/**
		 * Invalid Command Type
		 */
		INVALID;

		/**
		 * Returns the CommandType from a given string type.
		 *
		 * @param type the string to check for
		 * @return the CommandType from a given string type
		 */
		public static CommandTypes getCommand(String type) {
			return switch (type.toLowerCase()) {
				case "info" -> INFO;
				case "give" -> GIVE;
				case "edit" -> EDIT;
				case "reload" -> RELOAD;
				case "gui" -> GUI;
				default -> INVALID;
			};
		}
	}

	/**
	 * Sends the sender a message that they are not holding a valid item.
	 *
	 * @param s the sender to send the message to
	 */
	private void notHoldingItem(CommandSender s) {
		sendColoredMessage(s, prefix() + " &cYou are not holding a valid item!");
	}

	/**
	 * Sends the sender a message that they have an invalid argument in their command.
	 *
	 * @param sender   who to send the message to
	 * @param argument the argument which has an error
	 */
	private void invalidArgument(CommandSender sender, String argument) {
		sendColoredMessage(sender, prefix() + " &cInvalid argument " + argument + ".");
	}

	/**
	 * Sends the target a message that their item has been reset.
	 * Sends the sender a message that the operation was completed.
	 *
	 * @param sender who to send the operation completed message
	 * @param target who to send the item reset message
	 * @param item   the item which has been reset
	 */
	private void resetItemMessage(CommandSender sender, Player target, ItemStack item) {
		sendColoredMessage(sender, prefix() + " &eReset " + item.getItemMeta().getDisplayName() + " &efor " + target.getName() + "&e.");
		sendColoredMessage(target, prefix() + " &eReset " + item.getItemMeta().getDisplayName() + "&e.");
	}

	/**
	 * Sends the sender the help message.
	 *
	 * @param sender who to send the help message
	 */
	private void helpMessage(CommandSender sender) {
		commandHeader(sender);
		sendColoredMessage(sender, "&6> &e/morearmors edit <user>");
		sendColoredMessage(sender, "&6> &e/morearmors give <user>");
		sendColoredMessage(sender, "&6> &e/morearmors info");
	}

	/**
	 * Returns whether the sender has a specified permission.
	 *
	 * @param sender     who to check has a specified permission
	 * @param permission the permission to check for
	 * @return whether the sender has a specified permission
	 */
	private boolean hasPermission(CommandSender sender, PermissionType permission) {
		return sender.hasPermission(PermissionType.getPermission(permission));
	}

	/**
	 * Sends the sender the no permission message.
	 *
	 * @param sender who to send the no permission message
	 */
	private void noPermission(CommandSender sender) {
		sendColoredMessage(sender, MessageFormat.format("{0} &cYou do not have permission to use this command!", prefix()));
	}

	/**
	 * Sends the sender the players only message.
	 *
	 * @param sender who to send the players only message
	 */
	private void playersOnly(CommandSender sender) {
		sendColoredMessage(sender, MessageFormat.format("{0} &cOnly players can use this command!", prefix()));
	}

	/**
	 * Sends the sender the too many arguments message.
	 *
	 * @param sender who to send the too many arguments message
	 */
	private void tooManyArguments(CommandSender sender) {
		sendColoredMessage(sender, MessageFormat.format("{0} &cToo many arguments!", prefix()));
	}

	/**
	 * Sends the sender the info message.
	 *
	 * @param sender who to send the info message
	 */
	private void infoMessage(CommandSender sender) {
		sendColoredMessage(sender, "");
		sendColoredMessage(sender, MessageFormat.format("{0} &eRunning &6 {1} {2} &ecreated by &6OffsetPaladin89&e.", prefix(), plugin.getName(), plugin.getDescription().getVersion()));
		sendColoredMessage(sender, "&6> &eOfficial Site: &6https://dev.bukkit.org/projects/MoreArmors");
	}

	/**
	 * Sends the sender the header
	 *
	 * @param sender who to send the header
	 */
	private void commandHeader(CommandSender sender) {
		sendColoredMessage(sender, "");
		sendColoredMessage(sender, MessageFormat.format("{0} &eAvailable Command Options", prefix()));
	}

	/**
	 * Sends the sender an option.
	 *
	 * @param sender who to send the option
	 * @param option the option to send
	 */
	private void addFeedbackOption(CommandSender sender, String option) {
		sendColoredMessage(sender, MessageFormat.format("&6> &e{0}", option));
	}

	/**
	 * Sends the sender the command types.
	 *
	 * @param sender who to send the command types
	 */
	private void commandType(CommandSender sender) {
		commandHeader(sender);
		addFeedbackOption(sender, "info");
		if (hasPermission(sender, PermissionType.GIVE)) addFeedbackOption(sender, "give");
		if (hasPermission(sender, PermissionType.EDIT)) addFeedbackOption(sender, "edit");
		if (hasPermission(sender, PermissionType.RELOAD)) addFeedbackOption(sender, "reload");
		if (hasPermission(sender, PermissionType.GUI)) addFeedbackOption(sender, "gui");
	}

	/**
	 * Sends the sender all the options in an array.
	 *
	 * @param sender who to send all the options to
	 * @param arr    the array to send
	 */
	private void sendFeedback(CommandSender sender, String[] arr) {
		commandHeader(sender);
		for (String s : arr) addFeedbackOption(sender, s);
	}

	/**
	 * Handles the command the sender sent with the specified args.
	 *
	 * @param sender who send the command
	 * @param args   the arguments to handle
	 */
	private void handleGive(CommandSender sender, String[] args) {
		if (!hasPermission(sender, PermissionType.GIVE)) return;
		if (args.length > 1 && Bukkit.getPlayer(args[1]) == null) return;
		if (args.length > 2 && containsArg(plugin.giveTypes, args[3])) return;
		if (args.length == 2) sendFeedback(sender, plugin.giveTypes);
		switch (args[2]) {
			case "armor":
				handleGiveArmor(sender, args, Bukkit.getPlayer(args[1]));
			case "material":
				handleGiveMaterial(sender, args, Bukkit.getPlayer(args[1]));
		}
	}

	/**
	 * Returns if a specified array contains a specified argument.
	 *
	 * @param arr the array to check in
	 * @param arg the argument to check for
	 * @return if a specified array contains a specified argument
	 */
	private boolean containsArg(String[] arr, String arg) {
		return List.of(arr).contains(arg.toLowerCase());
	}

	/**
	 * Handles the give armor command a sender sent with specified arguments and a specified target.
	 *
	 * @param sender who sent the command
	 * @param args   the arguments to handle
	 * @param target who to give the armor to, if applicable
	 */
	private void handleGiveArmor(CommandSender sender, String[] args, Player target) {
		if (args.length > 3 && !containsArg(plugin.armorTypes, args[3])) {
			invalidArgument(sender, args[3]);
			return;
		}
		if (args.length > 4 && !containsArg(plugin.slotTypes, args[4])) {
			invalidArgument(sender, args[4]);
			return;
		}
		if (args.length > 5 && !plugin.isWholeNumber(args[5])) {
			invalidArgument(sender, args[5]);
			return;
		}
		if (args.length > 6) tooManyArguments(sender);
		switch (args.length) {
			case 3 -> sendFeedback(sender, plugin.armorTypes);
			case 4 -> sendFeedback(sender, plugin.slotTypes);
			case 5 -> giveItem(sender, target, args[3], args[4], 0);
			case 6 -> giveItem(sender, target, args[3], args[4], Integer.parseInt(args[5]));
		}
	}

	/**
	 * Handles the give material command a sender sent with specified arguments and a specified target.
	 *
	 * @param sender who sent the command
	 * @param args   the arguments to handle
	 * @param target who to give the material(s) to, if applicable
	 */
	private void handleGiveMaterial(CommandSender sender, String[] args, Player target) {
		if (args.length > 3 && !containsArg(plugin.materialTypes, args[3])) {
			invalidArgument(sender, args[3]);
			return;
		}
		if (args.length > 4 && !plugin.isWholeNumber(args[4])) {
			invalidArgument(sender, args[4]);
			return;
		}
		switch (args.length) {
			case 3 -> sendFeedback(sender, plugin.materialTypes);
			case 4 -> giveItem(sender, target, args[3], 1);
			case 5 -> giveItem(sender, target, args[3], Integer.parseInt(args[4]));
		}
	}

	/**
	 * Handles the edit command a sender sent with specified arguments.
	 * @param sender who send the command
	 * @param args the arguments to handle
	 */
	private void handleEdit(CommandSender sender, String[] args) {
		if (!hasPermission(sender, PermissionType.EDIT)) {
			noPermission(sender);
			return;
		}
		if (args.length > 1 && Bukkit.getPlayer(args[1]) == null) {
			invalidArgument(sender, args[1]);
			return;
		}
		if (args.length > 2 && !containsArg(plugin.editTypes, args[2])) {
			invalidArgument(sender, args[2]);
			return;
		}
		if (args.length > 3 && !plugin.isWholeNumber(args[3])) {
			invalidArgument(sender, args[3]);
			return;
		}
		if (args.length > 4) {
			tooManyArguments(sender);
			return;
		}
		switch (args.length) {
			case 2 -> sendFeedback(sender, plugin.editTypes);
			case 3 -> applyEdit(sender, Bukkit.getPlayer(args[1]), args[2], 0);
			case 4 -> applyEdit(sender, Bukkit.getPlayer(args[1]), args[2], Integer.parseInt(args[3]));
		}
	}

	/**
	 * Handles a command sent from a sender.
	 * Applies an edit to a specified target with a specified type and specified amount.
	 * @param sender who sent the edit
	 * @param target who to apply the edit to
	 * @param type the type of edit to apply
	 * @param amount the amount of an edit to apply
	 */
	private void applyEdit(CommandSender sender, Player target, String type, Integer amount) {
		PlayerInventory inventory = target.getInventory();
		if (plugin.isAirOrNull(inventory.getItemInMainHand())) {
			notHoldingItem(sender);
			return;
		}
		ItemStack hand = inventory.getItemInMainHand();
		NBTItem nbtItem = new NBTItem(hand);
		if (type.equalsIgnoreCase("emerald_count")) {
			if (!nbtItem.getString("CustomItemID").equals("emerald")) return;
			inventory.setItemInMainHand(new EmeraldArmor(hand, amount).getItem());
			resetItemMessage(sender, target, hand);
		} else if (type.equalsIgnoreCase("kill_amount")) {
			if (!nbtItem.getString("CustomItemID").equals("destroyer")) return;
			inventory.setItemInMainHand(new DestroyerArmor(hand, amount).getItem());
			resetItemMessage(sender, target, hand);
		}
	}

	/**
	 * Handles a command sent from a sender.
	 * Give the specified target an armor of a specified type, slot, and , optionally, amount.
	 * @param sender who sent the command
	 * @param target who to give the armor to
	 * @param type the type of armor
	 * @param slot the slot of the armor
	 * @param amount the special amount to apply to an armor
	 */
	public void giveItem(CommandSender sender, Player target, String type, String slot, Integer amount) {
		ItemStack item = ArmorType.getItem(getSetType(type), typeFromString(slot), amount);
		PlayerInventory inventory = target.getInventory();
		if (inventory.firstEmpty() == -1) target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
		else inventory.addItem(item);
		giveMessage(sender, target, 1, item);
	}

	/**
	 * Handles a command sent from a sender.
	 * Gives the specified target materials of a specified type, and, optionally, amount.
	 * @param sender who sent the command
	 * @param target who to give the material(s) to
	 * @param type the type of material to give
	 * @param amount the amount of the material to give, optional
	 */
	public void giveItem(CommandSender sender, Player target, String type, Integer amount) {
		MaterialType mType = MaterialType.getMaterialType(type);
		ItemStack item = MaterialType.getItem(mType, amount);
		PlayerInventory inventory = target.getInventory();
		if (List.of(plugin.noStackMaterials).contains(mType)) {
			for (int x = 0; x < amount; x++) {
				item = MaterialType.getItem(mType, amount);
				if (inventory.firstEmpty() == -1) target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
				else inventory.addItem(item);
			}
		} else if (inventory.firstEmpty() == -1) target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
		else inventory.addItem(item);
		giveMessage(sender, target, amount, item);
	}

	/**
	 * Sends a specified sender the give operation was handled successfully.
	 * Sends a specified target that they received specified item(s) of specified a amount.
	 * @param sender who to send the gave message to
	 * @param target who to send the received message to
	 * @param n the amount given
	 * @param i the item given
	 */
	private void giveMessage(CommandSender sender, Player target, Integer n, ItemStack i) {
		sendColoredMessage(target, MessageFormat.format("{0} Received {1}x {2}&e.", prefix(), n, i.getItemMeta().getDisplayName()));
		sendColoredMessage(sender, MessageFormat.format("{0} Gave {1} {2}x {3}&e.", prefix(), target.getName(), n, i.getItemMeta().getDisplayName()));
	}

	/**
	 * Handles commands sent from a specified sender.
	 * Handles a specified command, the specified command name used, and the specified arguments to handle.
	 * Returns whether the command was handled successfully
	 * @param sender the sender to handle commands from
	 * @param cmd the command to handle
	 * @param label alternate names of the command
	 * @param args the arguments to handle
	 * @return whether the command was handled successfully
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("morearmors")) {
			if (args.length == 0) {
				helpMessage(sender);
				return true;
			}
			CommandTypes commandType = CommandTypes.getCommand(args[0]);
			if (args.length == 1 && (commandType.equals(CommandTypes.GIVE) || commandType.equals(CommandTypes.EDIT))) {
				commandType(sender);
				return true;
			}
			switch (commandType) {
				case INFO -> {
					if (args.length != 1) {
						tooManyArguments(sender);
						break;
					}
					infoMessage(sender);
				}
				case GIVE -> handleGive(sender, args);
				case EDIT -> handleEdit(sender, args);
				case RELOAD -> {
					if (args.length != 1) {
						tooManyArguments(sender);
						break;
					}
					if (!hasPermission(sender, PermissionType.RELOAD)) {
						noPermission(sender);
						break;
					}
					plugin.reloadConfig(sender);
				}
				case GUI -> {
					if (args.length != 1) {
						tooManyArguments(sender);
						break;
					}
					if (!hasPermission(sender, PermissionType.GUI)) {
						noPermission(sender);
						break;
					}
					if (sender instanceof Player player) plugin.inv.mainInventory(player).show(player);
					else playersOnly(sender);
				}
				case INVALID -> invalidArgument(sender, args[0]);
			}
		}
		return true;
	}
}
