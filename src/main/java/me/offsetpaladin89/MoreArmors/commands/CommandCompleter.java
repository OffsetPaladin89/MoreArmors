package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CommandCompleter implements TabCompleter {
	
	List<String> arguments = new ArrayList<>();
	private final MoreArmorsMain plugin;
	
	public CommandCompleter(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginCommand("morearmors").setTabCompleter(this);
	}
	
	public Plugin getPlugin(String plugin) {return this.plugin.getServer().getPluginManager().getPlugin(plugin);}
	
	public boolean isInteger(String string) {
	    try {Integer.parseInt(string);}
	    catch(Exception e) {return false;}
	    return true;
	}
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		arguments.clear();
		if(args.length == 1) {
			if(sender.hasPermission("morepluginscore.edit")) {arguments.add("Edit");}
			if(sender.hasPermission("morepluginscore.give")) {arguments.add("Give");}
			if(sender.hasPermission("morepluginscore.help")) {arguments.add("Help");}
			if(sender.hasPermission("morepluginscore.info")) {arguments.add("Info");}
		}
		else if(args[0].equalsIgnoreCase("info")) {
			if(args.length == 2) {
				arguments.add("MorePluginsCore");
				if(getPlugin("MoreArmors") != null) {arguments.add("MoreArmors");}
				if(getPlugin("MoreArmorsExtra") != null) {arguments.add("MoreArmorsExtra");}
			}
		}
		else if(args[0].equalsIgnoreCase("give") && sender.hasPermission("morepluginscore.give")) {
			if(args.length == 3) {
				if(getPlugin("MoreArmors") != null || getPlugin("MoreArmorsExtra") != null) {arguments.add("Armor");}
				arguments.add("Material");
			}
			else if(args.length > 3) {
				if(getPlugin("MoreArmors") != null || getPlugin("MoreArmorsExtra") != null) {
					if(args[2].equalsIgnoreCase("armor")) {
						if(args.length == 4) {
							if(getPlugin("MoreArmors") != null) {
								arguments.add("Emerald");
								arguments.add("End");
								arguments.add("Experience");
								arguments.add("Miner");
								arguments.add("Nether");
								arguments.add("SeaGreed");
								arguments.add("Speedster");
								arguments.add("Titan");
//								arguments.add("TrueDiamond");
							}
							if(getPlugin("MoreArmorsExtra") != null) {
								arguments.add("Destroyer");
							}
						}
						else if(plugin.validArmors.contains(args[3].toLowerCase())){
							if(args.length == 5) {
								arguments.add("Helmet");
								arguments.add("Chestplate");
								arguments.add("Leggings");
								arguments.add("Boots");
							}
							else if(plugin.validSlots.contains(args[4].toLowerCase())) {
								if(getPlugin("MoreArmors") != null) {
									if(args[3].equalsIgnoreCase("truediamond")) {
										if(args.length == 6) {for(int i = 1; i < 11; i++) {arguments.add("" + i);}}
										else if(isInteger(args[5])) {if(args.length == 7) {for(int i = 1; i < 51; i++) {arguments.add("" + i);}}}
									}
								}
							}
						}
					}
				}
				if(args[2].equalsIgnoreCase("material")) {
					arguments.add("CompactedBlazeRod");
					arguments.add("CompactedCobblestone");
					arguments.add("CompactedEndStone");
					arguments.add("CompactedEyeOfEnder");
					arguments.add("CompactedSoulSand");
					arguments.add("CompactedSugarCane");
					arguments.add("NetherCrown");
					arguments.add("CompactedDiamond");
					arguments.add("CompactedDiamondBlock");
					arguments.add("DiamondSingularity");
					arguments.add("CompactedGold");
					arguments.add("CompactedGoldBlock");
					arguments.add("CompactedPrismarine");
//					arguments.add("CompactedIronIngot");
//					arguments.add("CompactedIronBlock");
//					arguments.add("CompactedRedstone");
//					arguments.add("EnergyCell");
//					arguments.add("MachineCore");
//					arguments.add("MachinePart");
//					arguments.add("StarDust");
				}
			}
		}
		else if(args[0].equalsIgnoreCase("edit") && sender.hasPermission("morepluginscore.edit")) {
			if(args.length == 3) {
				if(getPlugin("MoreArmors") != null) {
					arguments.add("EmeraldCount");
					arguments.add("ArmorLevel");
					arguments.add("DiamondSacrifice");
				}
				if(getPlugin("MoreArmorsExtra") != null) {
					arguments.add("Kills");
				}
			}
			else if(args.length > 3) {
				if(getPlugin("MoreArmors") != null) {
					if(args[2].equalsIgnoreCase("ArmorLevel")) {if(args.length == 4) {for(int i = 1; i < 11; i++) {arguments.add("" + i);}}}
					if(args[2].equalsIgnoreCase("DiamondSacrifice")) {if(args.length == 4) {for(int i = 1; i < 51; i++) {arguments.add("" + i);}}}
				}
			}
		}
		List<String> result = new ArrayList<>();
		if(args.length == 2 && args[0].equalsIgnoreCase("info")) {
			for(String a : arguments) {
				if (a.toLowerCase().contains(args[1].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		}
		
		if(args.length != 2 && args.length <= 7) {
			for(String a : arguments) {
				if(args.length == 1) {if(a.toLowerCase().contains(args[0].toLowerCase())) {result.add(a);}}
				if(args.length == 3) {if(a.toLowerCase().contains(args[2].toLowerCase())) {result.add(a);}}
				if(args.length == 4) {if(a.toLowerCase().contains(args[3].toLowerCase())) {result.add(a);}}
				if(args.length >= 3) {
					if(getPlugin("MoreArmors") != null) {
						if(args[2].equalsIgnoreCase("ArmorLevel") || args[2].equalsIgnoreCase("DiamondSacrifice")) {if(args.length == 4) {if(a.toLowerCase().startsWith(args[3].toLowerCase())) {result.add(a);}}}
					}
					if(getPlugin("MoreArmors") != null || getPlugin("MoreArmorsExtra") != null) {
						if(args[2].equalsIgnoreCase("armor")) {
							if(args.length == 5) {if(a.toLowerCase().startsWith(args[4].toLowerCase())) {result.add(a);}}
							if(args.length == 6) {if(a.toLowerCase().startsWith(args[5].toLowerCase())) {result.add(a);}}
							if(args.length == 7) {if(a.toLowerCase().startsWith(args[6].toLowerCase())) {result.add(a);}}
						}
					}
					if(args[2].equalsIgnoreCase("material")) {if(args.length == 5 || args.length == 6 || args.length == 7) {return null;}}
				}
			}
			return result;
		}
		return null;
	}
}
