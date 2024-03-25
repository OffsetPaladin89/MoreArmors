package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.handlers.TextHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

	public MoreArmorsMain plugin;
	public CommandsMessages messages;

	public Commands(MoreArmorsMain plugin) {
		this.plugin = plugin;
		messages = new CommandsMessages(plugin);
		plugin.getServer().getPluginCommand("morearmors").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("morearmors")) {
			if (args.length == 0) {
				TextHandler.sendInfo(sender, "MoreArmors", "Custom Armors.", true, "https://dev.bukkit.org/projects/MoreArmors");
			} else {
				switch (args[0].toLowerCase()) {
					case "help" -> {
						if (sender.hasPermission("morearmors.help")) {
							if (args.length == 1) {
								messages.helpMessage(sender);
							} else {
								messages.tooManyArguments(sender);
							}
						} else {
							messages.noPermission(sender);
						}
					}
					case "info" -> {
						if (sender.hasPermission("morearmors.info")) {
							switch (args.length) {
								case 1 -> messages.infoHelpMessage(sender);
								case 2 -> messages.pluginInfoMessage(sender);
								default -> messages.tooManyArguments(sender);
							}
						} else {
							messages.noPermission(sender);
						}
					}
					case "give" -> {
						if (sender.hasPermission("morepluginscore.give")) {
							if (args.length < 7) {
								messages.giveMessage(args.length, sender,
										args.length == 1 ? "<user>" : args[1],
										args.length > 2 ? args[2] : null,
										args.length > 3 ? args[3] : null,
										args.length > 4 ? args[4] : null,
										args.length > 5 ? args[5] : null);
							} else {
								messages.tooManyArguments(sender);
							}
						} else {
							messages.noPermission(sender);
						}
					}
					case "edit" -> {
						if (sender.hasPermission("morepluginscore.edit")) {
							if (args.length < 5) {
								messages.editMessage(args.length, sender,
										args.length == 1 ? "user" : args[1],
										args.length > 2 ? args[2] : null,
										args.length > 3 ? args[3] : null);
							} else {
								messages.tooManyArguments(sender);
							}
						}
					}
				}
			}
		}
		return true;
	}
}
