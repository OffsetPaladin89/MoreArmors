package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.Main;
import me.offsetpaladin89.MoreArmors.enums.PermissionType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandCompleter implements TabCompleter {

	/** Main object for local use. */
	private final Main plugin;

	/**
	 * Constructs a new CommandCompleter object with the specified Main class.
	 * @param plugin the Main plugin
	 */
	public CommandCompleter(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginCommand("morearmors").setTabCompleter(this);
	}

	/**
	 * Returns a tab completer with a specified sender, command, label, and arguments.
	 * @param sender the object preparing to send a command
	 * @param cmd the command being used
	 * @param label alias of the command being used
	 * @param args array of arguments.
	 * @return a tab completer with a specified sender, command, label, and arguments.
	 */
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> arguments = new ArrayList<>();
		if(args.length < 1) return null;
		if(args.length == 1) addBaseCommands(sender, arguments);
		if(isValid(sender, args[0], PermissionType.GIVE)) {
			switch(args.length) {
				case 2 -> addPlayers(arguments);
				case 3 -> addTypes(arguments, ArgumentTypes.GIVE);
				case 4 -> addItems(arguments, args[2]);
				case 5 -> addTypes(arguments, ArgumentTypes.SLOT);
			}
		}
		if(isValid(sender, args[0], PermissionType.EDIT)) {
			switch (args.length) {
				case 2 -> addPlayers(arguments);
				case 3 -> addTypes(arguments, ArgumentTypes.EDIT);
			}
		}

		List<String> options = new ArrayList<>();
		for (String s : arguments) {
			if (s.toLowerCase().contains(args[args.length - 1])) options.add(s);
		}
		return options;
	}

	/**
	 * Adds all the players up to 10 to the command completer.
	 * @param list the list for the command completer
	 */
	private void addPlayers(List<String> list) {
		int i = 0;
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(i == 10) return;
			list.add(p.getName());
			i++;
		}
	}

	/**
	 * Adds all the base commands to the command completer for a specified sender.
	 * @param sender the object preparing to send a command
	 * @param list the list for the command completer
	 */
	private void addBaseCommands(CommandSender sender, List<String> list) {
		if (hasPermission(sender, PermissionType.EDIT)) list.add("edit");
		if (hasPermission(sender, PermissionType.GIVE)) list.add("give");
		if(hasPermission(sender, PermissionType.RELOAD)) list.add("reload");
		list.add("info");
		list.add("gui");
	}

	/**
	 * Adds types depending on the specified command type to the command completer list.
	 * @param list the list for the command completer
	 * @param type the type of command being handled
	 */
	private void addTypes(List<String> list, ArgumentTypes type) {
		switch (type) {
			case GIVE -> {
				list.add("armor");
				list.add("material");
			}
			case EDIT -> {
				list.add("emerald_count");
				list.add("kill_amount");
			}
			case MATERIAL -> list.addAll(Arrays.asList(plugin.materialTypes));
			case ARMOR -> list.addAll(Arrays.asList(plugin.armorTypes));
			case SLOT -> list.addAll(Arrays.asList(plugin.slotTypes));
		}
	}

	/**
	 * Adds armors or materials to the command completer list depending on the specified arg.
	 * @param list the list for the command completer
	 * @param arg the 3rd argument which handles whether to add materials or armors
	 */
	private void addItems(List<String> list, String arg) {
		if(arg.equalsIgnoreCase("material")) addTypes(list, ArgumentTypes.MATERIAL);
		if(arg.equalsIgnoreCase("armor")) addTypes(list, ArgumentTypes.ARMOR);
	}

	/**
	 * Returns whether the argument handled by the sender of the specified type is allowed.
	 * @param sender the object preparing to send a command
	 * @param arg the 1st argument which handles whether to give or edit
	 * @param type the type of command being handled
	 * @return whether the argument handled by the sender of the specified type is allowed
	 */
	private boolean isValid(CommandSender sender, String arg, PermissionType type) {
		return correctArgument(arg, type.name()) && hasPermission(sender, type);
	}

	/**
	 * Returns whether a specified argument is the same as a specified expected argument.
	 * @param arg the specified argument
	 * @param expected the expected argument
	 * @return whether a specified argument is the same as a specified expected argument
	 */
	private boolean correctArgument(String arg, String expected) {
		return arg.equalsIgnoreCase(expected);
	}

	/**
	 * Returns whether the specified sender has the specified permission.
	 * @param sender the object preparing to send a command
	 * @param permission the permission to look for
	 * @return whether the specified sender has the specified permission
	 */
	private boolean hasPermission(CommandSender sender, PermissionType permission) {
		return sender.hasPermission(PermissionType.getPermission(permission));
	}

	/** Command types that can be used */
	private enum ArgumentTypes {
		/** Give options */
		GIVE,

		/** Edit options */
		EDIT,

		/** Armor options */
		ARMOR,

		/** Material options */
		MATERIAL,

		/** Slot options */
		SLOT
	}
}
