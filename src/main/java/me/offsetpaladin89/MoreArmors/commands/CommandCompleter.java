package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandCompleter implements TabCompleter {

	public CommandCompleter(MoreArmorsMain plugin) {
		plugin.getServer().getPluginCommand("morearmors").setTabCompleter(this);
	}

	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		List<String> arguments = new ArrayList<>();

		if (args.length == 1) {
			if (sender.hasPermission("morearmors.edit")) arguments.add("edit");
			if (sender.hasPermission("morearmors.give")) arguments.add("give");
			if(sender.hasPermission("morearmors.reload")) arguments.add("reload");
			arguments.add("info");

			return returnVals(arguments, args);
		}

		if (args[0].equalsIgnoreCase("give") && sender.hasPermission("morearmors.give")) {
			if (args.length == 2) return null;

			if (args.length == 3) {
				arguments.add("armor");
				arguments.add("material");
			}

			if(args.length == 4) {
				if (args[2].equalsIgnoreCase("armor")) arguments.addAll(ArmorType.allArmorTypes());
				else if(args[2].equalsIgnoreCase("material")) arguments.addAll(MaterialType.allMaterialTypes());
			}

			if(args.length == 5) {
				if (args[2].equalsIgnoreCase("armor")) arguments.addAll(SlotType.allSlotTypes());
			}
		}
		else if (args[0].equalsIgnoreCase("edit") && sender.hasPermission("morearmors.edit")) {
			if(args.length == 2) {
				arguments.add("emerald_count");
				arguments.add("kill_amount");
			}
		}
		return returnVals(arguments, args);
	}

	public List<String> returnVals(List<String> arguments, String[] args) {
		List<String> options = new ArrayList<>();
		for (String s : arguments) {
			if (s.toLowerCase().contains(args[args.length - 1])) {
				options.add(s);
			}
		}
		return options;
	}
}
