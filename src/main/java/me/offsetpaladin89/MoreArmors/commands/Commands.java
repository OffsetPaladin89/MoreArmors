package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.handlers.TextHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

//public class Commands implements CommandExecutor {
//
//	public MoreArmorsMain plugin;
//	public CommandsMessages messages;
//
//	public Commands(MoreArmorsMain plugin) {
//		this.plugin = plugin;
//		new TextHandler(plugin);
//		messages = new CommandsMessages(plugin);
//		plugin.getServer().getPluginCommand("morepluginscore").setExecutor(this);
//
//	}
//
//	@Override
//	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//		if(cmd.getName().equalsIgnoreCase("morepluginscore")) {
//			if(args.length == 0) {TextHandler.sendInfo(sender, "MorePluginsCore", "Core Plugin for MorePlugins.", true, "https://dev.bukkit.org/projects/MorePluginsCore");}
//			else {
//				switch(args[0].toLowerCase()) {
//				case "help":
//					if(sender.hasPermission("morepluginscore.help")) {
//						if (args.length == 1) { messages.helpMessage(sender); }
//						else { messages.tooManyArguments(sender); }
//					}
//					else { messages.noPermission(sender); }
//					break;
//				case "info":
//					if(sender.hasPermission("morepluginscore.info")) {
//						switch (args.length) {
//							case 1 -> messages.infoHelpMessage(sender);
//							case 2 -> messages.pluginInfoMessage(sender, args[1]);
//							default -> messages.tooManyArguments(sender);
//						}
//					}
//					else {messages.noPermission(sender);}
//					break;
//				case "give":
//					if(sender.hasPermission("morepluginscore.give")) {
//						switch (args.length) {
//							case 1 -> messages.giveMessage(1, sender, "<user>", null, null, null, null, null);
//							case 2 -> messages.giveMessage(2, sender, args[1], null, null, null, null, null);
//							case 3 -> messages.giveMessage(3, sender, args[1], args[2], null, null, null, null);
//							case 4 -> messages.giveMessage(4, sender, args[1], args[2], args[3], null, null, null);
//							case 5 -> messages.giveMessage(5, sender, args[1], args[2], args[3], args[4], null, null);
//							case 6 -> messages.giveMessage(6, sender, args[1], args[2], args[3], args[4], args[5], null);
//							case 7 -> messages.giveMessage(7, sender, args[1], args[2], args[3], args[4], args[5], args[6]);
//							default -> messages.tooManyArguments(sender);
//						}
//					}
//					else {messages.noPermission(sender);}
//					break;
//				case "edit":
//					if(sender.hasPermission("morepluginscore.edit")) {
//						switch (args.length) {
//							case 1 -> messages.editMessage(1, sender, "<user>", null, null);
//							case 2 -> messages.editMessage(2, sender, args[1], null, null);
//							case 3 -> messages.editMessage(3, sender, args[1], args[2], null);
//							case 4 -> messages.editMessage(4, sender, args[1], args[2], args[3]);
//							default -> messages.tooManyArguments(sender);
//						}
//					}
//					break;
//				}
//			}
//		}
//		return true;
//	}
//}
