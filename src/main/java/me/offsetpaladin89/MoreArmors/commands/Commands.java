package me.offsetpaladin89.MoreArmors.commands;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.Main;
import me.offsetpaladin89.MoreArmors.armors.EmeraldArmor;
import me.offsetpaladin89.MoreArmors.enums.PermissionType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.text.MessageFormat;
import java.util.List;

import static me.offsetpaladin89.MoreArmors.Main.*;

public class Commands implements CommandExecutor {

	// TODO Documentation

	private final Main plugin;

	public Commands(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginCommand("morearmors").setExecutor(this);
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

	private void notHoldingItem(CommandSender s) {
		sendColoredMessage(s, prefix() + " &cYou are not holding a valid item!");
	}

	private void invalidArgument(CommandSender sender, String argument) {
		sendColoredMessage(sender, prefix() + " &cInvalid argument " + argument + ".");
	}

	private void resetItemMessage(CommandSender sender, Player target, ItemStack item) {
		sendColoredMessage(sender, prefix() + " &eReset " + item.getItemMeta().getDisplayName() + " &efor " + target.getName() + "&e.");
		sendColoredMessage(target, prefix() + " &eReset " + item.getItemMeta().getDisplayName() + "&e.");
	}

	private void helpMessage(CommandSender sender) {
		commandHeader(sender);
		sendColoredMessage(sender, "&6> &e/morearmors edit <user>");
		sendColoredMessage(sender, "&6> &e/morearmors give <user>");
		sendColoredMessage(sender, "&6> &e/morearmors info");
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

	private void sendFeedback(CommandSender sender, String[] arr) {
		commandHeader(sender);
		for(String s : arr) addFeedbackOption(sender, s);
	}

	private void handleGive(CommandSender sender, String[] args) {
		if(!hasPermission(sender, PermissionType.GIVE)) return;
		if(args.length > 1 && Bukkit.getPlayer(args[1]) == null) return;
		if(args.length == 2) sendFeedback(sender, plugin.giveTypes);
		if(args.length > 2 && containsArg(plugin.giveTypes, args[3])) return;
		switch(args[2]) {
			case "armor": handleGiveArmor(sender, args, Bukkit.getPlayer(args[1]));
			case "material": handleGiveMaterial(sender, args, Bukkit.getPlayer(args[1]));
		}
	}

	private boolean containsArg(String[] arr, String arg) {
		return List.of(arr).contains(arg.toLowerCase());
	}

	private void handleGiveArmor(CommandSender sender, String[] args, Player target) {
		if(args.length == 3) sendFeedback(sender, plugin.armorTypes);
		if(args.length > 3 && !containsArg(plugin.armorTypes, args[3])) {
			invalidArgument(sender, args[3]);
			return;
		}
		if(args.length == 4) sendFeedback(sender, plugin.slotTypes);
		if(args.length > 4 && !containsArg(plugin.slotTypes, args[4])) {
			invalidArgument(sender, args[4]);
			return;
		}
		if(args.length == 5) plugin.give.giveCommand(sender, target, args[3], args[4], 0);
		if(args.length > 5 && !plugin.isWholeNumber(args[5])) {
			invalidArgument(sender, args[5]);
			return;
		}
		if(args.length == 6) plugin.give.giveCommand(sender, target, args[3], args[4], Integer.parseInt(args[5]));
		if(args.length > 6) tooManyArguments(sender);
	}

	private void handleGiveMaterial(CommandSender sender, String[] args, Player target) {
		if(args.length == 3) sendFeedback(sender, plugin.materialTypes);
		if(args.length > 3 && !containsArg(plugin.materialTypes, args[3])) {
			invalidArgument(sender, args[3]);
			return;
		}
		if(args.length == 4) plugin.give.giveCommand(sender, target, args[3], 1);
		if(args.length > 4 && !plugin.isWholeNumber(args[4])) {
			invalidArgument(sender, args[4]);
			return;
		}
		if(args.length == 5) plugin.give.giveCommand(sender, target, args[3], Integer.parseInt(args[4]));
	}

	private void handleEdit(CommandSender sender, String[] args) {
		if(!hasPermission(sender, PermissionType.EDIT)) {
			noPermission(sender);
			return;
		}
		if(args.length > 1 && Bukkit.getPlayer(args[1]) == null) {
			invalidArgument(sender, args[1]);
			return;
		}
		if(args.length == 2) sendFeedback(sender, plugin.editTypes);
		if(args.length > 2 && !containsArg(plugin.editTypes, args[2])) {
			invalidArgument(sender, args[2]);
			return;
		}
		if(args.length == 3) applyEdit(sender, Bukkit.getPlayer(args[1]), args[2], 0);
		if(args.length > 3 && !plugin.isWholeNumber(args[3])) {
			invalidArgument(sender, args[3]);
			return;
		}
		if(args.length == 4) applyEdit(sender, Bukkit.getPlayer(args[1]), args[2], Integer.parseInt(args[3]));
		if(args.length > 5) tooManyArguments(sender);
	}

	private void applyEdit(CommandSender sender, Player target, String type, Integer amount) {
		PlayerInventory inventory = target.getInventory();
		if (plugin.isAirOrNull(inventory.getItemInMainHand())) {
			notHoldingItem(sender);
			return;
		}
		ItemStack hand = inventory.getItemInMainHand();
		NBTItem nbtItem = new NBTItem(hand);
		if(type.equalsIgnoreCase("emerald_count")) {
			if(!nbtItem.getString("CustomItemID").equals("emerald")) return;
			inventory.setItemInMainHand(new EmeraldArmor(hand, amount).getItem());
			resetItemMessage(sender, target, hand);
		}
		else if(type.equalsIgnoreCase("kill_amount")) {
			if(!nbtItem.getString("CustomItemID").equals("destroyer")) return;
			nbtItem.setInteger("KillAmount", amount);
			inventory.setItemInMainHand(plugin.armorConstructor.createDestroyerArmor(nbtItem.getItem()));
			resetItemMessage(sender, target, hand);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("morearmors")) {
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
		}
		return true;
	}
}
