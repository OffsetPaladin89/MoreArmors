package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandCompleter implements TabCompleter {

	private final MoreArmorsMain plugin;

	public CommandCompleter(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginCommand("morearmors").setTabCompleter(this);
	}

	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		List<String> arguments = new ArrayList<>();
		if (args.length == 1) {
			if (sender.hasPermission("morearmors.edit")) {
				arguments.add("edit");
			}
			if (sender.hasPermission("morearmors.give")) {
				arguments.add("give");
			}
			if(sender.hasPermission("morearmors.reload")) {
				arguments.add("reload");
			}
			arguments.add("info");
		} else if (args.length > 2) {
			if (args[0].equalsIgnoreCase("give") && sender.hasPermission("morearmors.give")) {
				if (args.length > 3) {
					if (args[2].equalsIgnoreCase("armor")) {
						if (args.length == 5) {
							arguments.addAll(Arrays.asList(plugin.slotTypes));
						} else if (args.length == 4) {
							arguments.addAll(Arrays.asList(plugin.armorTypes));
						}
					} else if (args[2].equalsIgnoreCase("material") && args.length == 4) {
						arguments.addAll(Arrays.asList(plugin.materialTypes));
					}
				} else {
					arguments.add("armor");
					arguments.add("material");
				}
			} else if (args[0].equalsIgnoreCase("edit") && sender.hasPermission("morearmors.edit")) {
				if (args.length == 3) {
					arguments.add("emerald_count");
					arguments.add("kill_amount");
				}
			}
		}
		else {
			return null;
		}

		List<String> options = new ArrayList<>();
		for (String s : arguments) {
			if (s.toLowerCase().contains(args[args.length - 1])) {
				options.add(s);
			}
		}
		return options;
	}
}
