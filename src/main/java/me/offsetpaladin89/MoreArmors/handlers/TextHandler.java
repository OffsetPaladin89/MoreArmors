package me.offsetpaladin89.MoreArmors.handlers;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.command.CommandSender;

public class TextHandler {
	
	private static MoreArmorsMain plugin;
	
	public TextHandler(MoreArmorsMain plugin) { TextHandler.plugin = plugin; }
	
	public static String noPermission() {return plugin.convertColoredString("&e(&6MorePluginsCore&e) &cYou do not have permission to use this command!");}
	public static String invalidArgument(String arg) {return plugin.convertColoredString("&e(&6MorePluginsCore&e) &cInvalid argument " + arg + "!");}
	public static String tooManyArguments() {return plugin.convertColoredString("&e(&6MorePluginsCore&e) &cToo many arguments!");}
	public static String playerNotOnline(String arg) {return plugin.convertColoredString("&e(&6MorePluginsCore&e) &c" + arg + " is not online or does not exist!");}
	
	public static void sendInfo(CommandSender sender, String pluginname, String description, boolean hasSite, String link) {
		sender.sendMessage(plugin.convertColoredString("&e(&6MoreArmors&e) &eRunning &6" + pluginname + " " + plugin.getDescription().getVersion() + " &ecreated by &6OffsetPaladin89&e."));
		sender.sendMessage(plugin.convertColoredString("&6> &e" + description));
		if(hasSite) {sender.sendMessage(plugin.convertColoredString("&6> &eOfficial Site: " + link));}
	}
	
	public static String textGenerator(String type, String args0, String args1, String args2, String args3, String args4, String args5) {
		return plugin.convertColoredString("&e(&6MorePluginsCore&e) &6" + type + " Command Options: &e(/morearmors " + args0.toLowerCase() + " " + args1.toLowerCase() + " " + args2.toLowerCase() + " " + args3.toLowerCase() + " " + args4.toLowerCase() + "" + args5.toLowerCase() + ")");
	}
	
	public static void editText(CommandSender sender, String args1) {
		sender.sendMessage(textGenerator("Edit", "edit", args1, "...", "", "", ""));
		sender.sendMessage(plugin.convertColoredString("&6> &eEmeraldCount"));
		sender.sendMessage(plugin.convertColoredString("&6> &eArmorLevel"));
		sender.sendMessage(plugin.convertColoredString("&6> &eDiamondSacrifice"));
//		if(getPlugin("MoreArmorsExtra") != null) {
//			sender.sendMessage(plugin.convertColoredString("&6> &eKills"));
//		}
	}
	
	public static void giveText(CommandSender sender, String args1) {
		sender.sendMessage(textGenerator("Give", "give", args1, "...", "", "", ""));
		sender.sendMessage(plugin.convertColoredString("&6> &eArmor"));
//		if(getPlugin("MoreWeapons") != null || getPlugin("MoreWeaponsExtra") != null) {
//			sender.sendMessage(plugin.convertColoredString("&6> &eWeapon"));
//		}
		sender.sendMessage(plugin.convertColoredString("&6> &eMaterial"));
	}
	
	public static void giveArmorText(CommandSender sender, String args1) {
		sender.sendMessage(textGenerator("Armor", "give", args1, "...", "", "", ""));
		sender.sendMessage(plugin.convertColoredString("&6> &eEmerald"));
		sender.sendMessage(plugin.convertColoredString("&6> &eEnd"));
		sender.sendMessage(plugin.convertColoredString("&6> &eExperience"));
		sender.sendMessage(plugin.convertColoredString("&6> &eMiner"));
		sender.sendMessage(plugin.convertColoredString("&6> &eNether"));
		sender.sendMessage(plugin.convertColoredString("&6> &eSpeedster"));
		sender.sendMessage(plugin.convertColoredString("&6> &eTitan"));
		sender.sendMessage(plugin.convertColoredString("&6> &eTrueDiamond"));
//		if(getPlugin("MoreArmorsExtra") != null) {
//			sender.sendMessage(plugin.convertColoredString("&6> &eDestroyer"));
//		}
	}
	
	public static void giveWeaponText(CommandSender sender, String args1) {
		sender.sendMessage(textGenerator("Weapon", "give", args1, "...", "", "", ""));
//		if(getPlugin("MoreWeapons") != null) {
//			sender.sendMessage(plugin.convertColoredString("&6> &eTitan"));
//			sender.sendMessage(plugin.convertColoredString("&6> &eEmerald"));
//		}
//		if(getPlugin("MoreWeaponsExtra") != null) {
//			sender.sendMessage(plugin.convertColoredString("&6> &eDestroyer"));
//		}
	}
	
	public static void giveMaterialText(CommandSender sender, String args1) {
		sender.sendMessage(textGenerator("Material", "give", args1, "...", "", "", ""));
		sender.sendMessage(plugin.convertColoredString("&6> &eCompactedBlazeRod"));
		sender.sendMessage(plugin.convertColoredString("&6> &eCompactedCobblestone"));
		sender.sendMessage(plugin.convertColoredString("&6> &eCompactedEndStone"));
		sender.sendMessage(plugin.convertColoredString("&6> &eCompactedEyeOfEnder"));
		sender.sendMessage(plugin.convertColoredString("&6> &eCompactedSoulSand"));
		sender.sendMessage(plugin.convertColoredString("&6> &eCompactedSugarCane"));
		sender.sendMessage(plugin.convertColoredString("&6> &eNetherCrown"));
		sender.sendMessage(plugin.convertColoredString("&6> &eCompactedDiamond"));
		sender.sendMessage(plugin.convertColoredString("&6> &eCompactedDiamondBlock"));
		sender.sendMessage(plugin.convertColoredString("&6> &eDiamondSingularity"));
//		if(getPlugin("MoreWeapons") != null) {
//			// sender.sendMessage(plugin.convertColoredString("&6> &e"));
//		}
//		if(getPlugin("MoreArmorsExtra") != null) {
//			sender.sendMessage(plugin.convertColoredString("&6> &eCompactedIronIngot"));
//			sender.sendMessage(plugin.convertColoredString("&6> &eCompactedIronBlock"));
//			sender.sendMessage(plugin.convertColoredString("&6> &eCompactedRedstone"));
//			sender.sendMessage(plugin.convertColoredString("&6> &eEnergyCell"));
//			sender.sendMessage(plugin.convertColoredString("&6> &eMachineCore"));
//			sender.sendMessage(plugin.convertColoredString("&6> &eMachinePart"));
//			sender.sendMessage(plugin.convertColoredString("&6> &eStarDust"));
//		}
//		if(getPlugin("MoreWeaponsExtra") != null) {
//			// sender.sendMessage(plugin.convertColoredString("&6> &e"));
//		}
	}
	
	public static void editArmorText(CommandSender sender, String args1, String args2) {
		sender.sendMessage(textGenerator("Armor", "edit", args1, args2, "...", "", ""));
		if(args2.equalsIgnoreCase("emeraldcount")) {
			sender.sendMessage(plugin.convertColoredString("&6> &eCount (Integer)"));
		}
		else if(args2.equalsIgnoreCase("armorlevel")) {
			sender.sendMessage(plugin.convertColoredString("&6> &eArmorLevel (Integer, Max 10)"));
		}
		else if(args2.equalsIgnoreCase("diamondsacrifice")) {
			sender.sendMessage(plugin.convertColoredString("&6> &eDiamondSacrifice (Integer, Max 50)"));
		}
//		if(getPlugin("MoreArmorsExtra") != null) {
//			if(args2.equalsIgnoreCase("kills")) {
//				sender.sendMessage(plugin.convertColoredString("&6> &eKills (Integer)"));
//			}
//		}
	}
	
	public static void validSlots(CommandSender sender, String args1, String args2, String args3) {
		sender.sendMessage(textGenerator("Armor", "give", args1, args2, args3, "...", ""));
		sender.sendMessage(plugin.convertColoredString("&6> &eHelmet"));
		sender.sendMessage(plugin.convertColoredString("&6> &eChestplate"));
		sender.sendMessage(plugin.convertColoredString("&6> &eLeggings"));
		sender.sendMessage(plugin.convertColoredString("&6> &eBoots"));
	}
	
	public static void giveArmorLevel(CommandSender sender, String args1, String args2, String args3, String args4) {
		sender.sendMessage(textGenerator("Armor", "give", args1, args2, args3, args4, "..."));
		if(args3.equalsIgnoreCase("emerald")) {
			sender.sendMessage(plugin.convertColoredString("&6> &eCount (Integer)"));
		}
		if(args3.equalsIgnoreCase("truediamond")) {
			sender.sendMessage(plugin.convertColoredString("&6> &eArmorLevel (Integer, Max 10)"));
		}
//		if(getPlugin("MoreArmorsExtra") != null) {
//			if(args3.equalsIgnoreCase("destroyer")) {
//				sender.sendMessage(plugin.convertColoredString("&6> &eDiamondSacrifice (Integer, Max 50)"));
//			}
//		}
	}
}
