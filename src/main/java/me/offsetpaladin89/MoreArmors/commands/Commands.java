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
		if (cmd.getName().equalsIgnoreCase("morearmors")) messages.commandMessage(sender, args);
		return true;
	}
}


