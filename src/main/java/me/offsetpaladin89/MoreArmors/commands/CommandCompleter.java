package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandCompleter implements TabCompleter {

	private final MoreArmorsMain plugin;

	public CommandCompleter(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginCommand("morearmors").setTabCompleter(this);
	}

	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> arguments = new ArrayList<>();
		if (args.length == 1) {
			if (sender.hasPermission("morearmors.edit")) {
				arguments.add("Edit");
			}
			if (sender.hasPermission("morearmors.give")) {
				arguments.add("Give");
			}
			if (sender.hasPermission("morearmors.help")) {
				arguments.add("Help");
			}
			if (sender.hasPermission("morearmors.info")) {
				arguments.add("Info");
			}
		} else if (args.length > 2) {
			if (args[0].equalsIgnoreCase("give") && sender.hasPermission("morearmors.give")) {
				if (args.length > 3) {
					if (args[2].equalsIgnoreCase("armor")) {
						if (args.length == 5) {
							arguments.addAll(Arrays.asList(plugin.slotTypes));
						} else if (args.length == 4) {
							arguments.addAll(Arrays.asList(plugin.armorTypes));
						}
					} else if (args[2].equalsIgnoreCase("material")) {
						arguments.addAll(Arrays.asList(plugin.materialTypes));
					}
				} else {
					arguments.add("Armor");
					arguments.add("Material");
				}
			} else if (args[0].equalsIgnoreCase("edit") && sender.hasPermission("morearmors.edit")) {
				if (args.length == 3) {
					arguments.add("EmeraldCount");
				}
			}
		} else {
			return null;
		}

		return arguments;
	}
}
