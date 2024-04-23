package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandCompleter implements TabCompleter {

	/**
	 * Command types that can be used
	 */
	private enum CommandTypes {
		/**
		 * Give options
		 */
		GIVE,

		/**
		 * Edit options
		 */
		EDIT,

		/**
		 * Armor options
		 */
		ARMOR,

		/**
		 * Material options
		 */
		MATERIAL,

		/**
		 * Slot options
		 */
		SLOT
	}

	/**
	 * Main object for local use.
	 */
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
	 * Adds all the base commands to the command completer for a specified sender.
	 * @param sender who is preparing to send a command
	 * @param list the list for the command completer
	 */
	private void addBaseCommands(CommandSender sender, List<String> list) {
		if (sender.hasPermission("morearmors.edit")) list.add("edit");
		if (sender.hasPermission("morearmors.give")) list.add("give");
		if(sender.hasPermission("morearmors.reload")) list.add("reload");
		list.add("info");
		list.add("gui");
	}

	/**
	 * Adds types depending on the specified command type to the command completer list.
	 * @param list the list for the command completer
	 * @param type the type of command being handled
	 */
	private void addTypes(List<String> list, CommandTypes type) {
		switch (type) {
			case GIVE:
				list.add("armor");
				list.add("material");
			case EDIT:
				list.add("emerald_count");
				list.add("kill_amount");
			case MATERIAL: list.addAll(Arrays.asList(plugin.materialTypes));
			case ARMOR: list.addAll(Arrays.asList(plugin.armorTypes));
			case SLOT: list.addAll(Arrays.asList(plugin.slotTypes));
		}
	}

	/**
	 * Adds armors or materials to the command completer list depending on the specified arg.
	 * @param list the list for the command completer
	 * @param arg the 3rd argument which handles whether to add materials or armors
	 */
	private void addItems(List<String> list, String arg) {
		if(arg.equalsIgnoreCase("material")) addTypes(list, CommandTypes.MATERIAL);
		if(arg.equalsIgnoreCase("armor")) addTypes(list, CommandTypes.ARMOR);
	}

	/**
	 * Returns whether the argument being handled from the sender is allowed.
	 * @param arg0 the 1st argument which handles whether to give or edit
	 * @param sender who is preparing to send a command
	 * @return whether the argument being handled from the sender is allowed
	 */
	private boolean isGive(String arg0, CommandSender sender) {
		return arg0.equalsIgnoreCase("give") && sender.hasPermission("morearmors.give");
	}

	private boolean isValid(CommandSender sender, String arg, CommandTypes type) {
		return arg.equalsIgnoreCase(type.toString()) && sender.hasPermission(MessageFormat.format("morearmors.{0}", type.toString().toLowerCase()));
	}

	/**
	 * Returns whether
	 * @param arg0
	 * @param sender
	 * @return
	 */
	private boolean isEdit(String arg0, CommandSender sender) {
		return arg0.equalsIgnoreCase("edit") && sender.hasPermission("morearmors.edit");
	}

	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> arguments = new ArrayList<>();
		if(args.length < 1) return null;
		if(args.length == 1) addBaseCommands(sender, arguments);
		if(isGive(args[0], sender)) {
			switch(args.length) {
				case 3 -> addTypes(arguments, CommandTypes.GIVE);
				case 4 -> addItems(arguments, args[2]);
				case 5 -> addTypes(arguments, CommandTypes.SLOT);
			}
		}
		if(isEdit(args[0], sender) && args.length == 3) addTypes(arguments, CommandTypes.EDIT);

		List<String> options = new ArrayList<>();
		for (String s : arguments) {
			if (s.toLowerCase().contains(args[args.length - 1])) options.add(s);
		}
		return options;
	}
}
