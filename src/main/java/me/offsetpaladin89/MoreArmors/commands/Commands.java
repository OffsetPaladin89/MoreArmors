package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.handlers.CommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

	public MoreArmorsMain plugin;
	public CommandHandler messages;

	public Commands(MoreArmorsMain plugin) {
		this.plugin = plugin;
		messages = new CommandHandler(plugin);
		plugin.getServer().getPluginCommand("morearmors").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("morearmors")) {
			if (args.length == 0) {
				messages.helpMessage(sender);
			} else {
				switch (args[0].toLowerCase()) {
					case "info" -> {
						if (args.length == 1) {
							messages.pluginInfoMessage(sender);
						} else {
							messages.tooManyArguments(sender);
						}
					}
					case "give" -> {
						if (sender.hasPermission("morearmors.give")) {
							if (args.length < 7) {
								messages.giveMessage(args.length, sender,
										args.length == 1 ? "<user>" : args[1],
										args.length > 2 ? args[2] : null,
										args.length > 3 ? args[3] : null,
										args.length > 4 ? args[4] : null,
										args.length > 5 ? args[5] : null);
							} else messages.tooManyArguments(sender);
						} else {
							messages.noPermission(sender);
						}
					}
//					case "edit" -> {
//						if (sender.hasPermission("morearmors.edit")) {
//							if (args.length < 5) {
//								messages.editMessage(args.length, sender,
//										args.length == 1 ? "user" : args[1],
//										args.length > 2 ? args[2] : null,
//										args.length > 3 ? args[3] : null);
//							} else messages.tooManyArguments(sender);
//						}
//					}
					case "reload" -> {
						if(sender.hasPermission("morearmors.reload")) plugin.reloadConfig(sender);
					}
				}
			}
		}
		return true;
	}
}
