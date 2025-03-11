package me.offsetpaladin89.MoreArmors.commands;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.items.armors.CustomArmor;
import me.offsetpaladin89.MoreArmors.items.armors.DestroyerArmor;
import me.offsetpaladin89.MoreArmors.items.armors.EmeraldArmor;
import me.offsetpaladin89.MoreArmors.enums.*;
import me.offsetpaladin89.MoreArmors.handlers.RecipeHandler;
import me.offsetpaladin89.MoreArmors.items.materials.CustomMaterial;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static me.offsetpaladin89.MoreArmors.enums.ArmorType.*;
import static me.offsetpaladin89.MoreArmors.enums.CommandType.allCommandTypes;
import static me.offsetpaladin89.MoreArmors.enums.CommandType.commandType;
import static me.offsetpaladin89.MoreArmors.enums.EditType.*;
import static me.offsetpaladin89.MoreArmors.enums.GiveType.allGiveTypes;
import static me.offsetpaladin89.MoreArmors.enums.GiveType.giveType;
import static me.offsetpaladin89.MoreArmors.enums.MaterialType.*;
import static me.offsetpaladin89.MoreArmors.enums.SlotType.allSlotTypes;
import static me.offsetpaladin89.MoreArmors.enums.SlotType.slotType;

public class Commands implements CommandExecutor {

	public MoreArmorsMain plugin;
	public Commands(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginCommand("morearmors").setExecutor(this);
		plugin.getServer().getPluginCommand("openplayerinventory").setExecutor(this);
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, @NotNull String[] args) {
		if (cmd.getName().equalsIgnoreCase("morearmors")) {
			CommandType commandType;

			if (args.length == 0) {
				listCommandOptions(sender, allCommandTypes());
				return true;
			}

			commandType = commandType(args[0]);
			if(commandType == CommandType.INVALID) {
				invalidArgument(sender, args[0]);
				return true;
			}

			switch (commandType) {
				case GIVE -> giveCommand(sender, args);
				case EDIT -> editCommand(sender, args);
				case INFO -> {
					if(args.length == 1) {
						pluginInfoMessage(sender);
						return true;
					}

					tooManyArguments(sender);
				}
				case RELOAD -> reloadCommand(sender, args);
				case VIEWRECIPES -> viewRecipeCommand(sender, args);
				case INVALID -> invalidArgument(sender, args[0]);
			}
		}
		else if (cmd.getName().equalsIgnoreCase("openplayerinventory")) {
			if(!(sender instanceof Player p)) return true;
			p.openInventory(p.getInventory());
		}
		return true;
	}

	/*
	1st Arg -> give / edit / reload / info
	2nd Arg ->
		1st Arg is ->
		- "give" -> playerName
		- "edit" -> emerald_count / kill_count
	3rd Arg ->
		1st Arg is ->
		- "give" ->
			2nd Arg is -> A Valid Name -> armor / material
		- "edit" ->
			2nd Arg is ->
			- "emerald_count" -> Integer
			- "kill_count" -> Integer
	4th Arg ->
		1st Arg is -> "give" ->
			3rd Arg is ->
			- "armor" -> List Armors
			- "material" -> List Materials
	5th Arg ->
		1st Arg is -> "give" ->
			3rd Arg is ->
			- "armor" ->
				4th Arg is ->
				A Valid Armor -> List Slots
				5th Arg is ->
				A Valid Slot -> Give Armor of Slot
			- "material" ->
				4th Arg is ->
				A Valid Material -> Give 1 of Material
				5th Arg is ->
				A Valid Integer -> Give # of Material
	Exceptions:
	0 Args -> Help Message
	1st Arg is -> "reload" / "info" -> args.length > 1 -> Error Message
	1st Arg is -> "edit" -> args.length > 3 -> Error Message
	*/

	private void reloadCommand(CommandSender sender, String[] args) {
		if (args.length == 1) {
			if(!sender.hasPermission("morearmors.reload")) {
				noPermission(sender);
				return;
			}
			reloadConfig(sender);
			return;
		}

		tooManyArguments(sender);
	}

	private void viewRecipeCommand(CommandSender sender, String[] args) {
		if (args.length == 1) {
			if(!(sender instanceof Player player)) {
				Util.sendColoredMessage(sender, prefix() + " &cCommand sender must be a player!");
				return;
			}
			plugin.inventoryHandler.viewRecipes(player);
			return;
		}
		tooManyArguments(sender);
	}

	private void editCommand(CommandSender sender, String[] args) {
		EditType editType;
		int editAmount;
		ItemStack item;

		if(!(sender instanceof Player player)) {
			Util.sendColoredMessage(sender, prefix() + " &cCommand sender must be a player!");
			return;
		}

		if(args.length == 1) {
			listCommandOptions(sender, allEditTypes());
			return;
		}

		editType = editType(args[1]);
		if (editType == EditType.INVALID) {
			invalidArgument(sender, args[1]);
			return;
		}

		if(Util.isAirOrNull(player.getInventory().getItemInMainHand())) {
			notHoldingItem(sender);
			return;
		}

		item = player.getInventory().getItemInMainHand();

		if(args.length == 2) {
			editItem(sender, item, editType, 0);
			return;
		}

		if(Util.isInteger(args[2])) editAmount = Integer.parseInt(args[2]);
		else {
			invalidArgument(sender, args[2]);
			return;
		}

		if(args.length == 3) {
			editItem(sender, item, editType, editAmount);
			return;
		}

		tooManyArguments(sender);
	}

	private void giveArmorCommand(CommandSender sender, String[] args, Player player) {
		ArmorType armorType;
		SlotType slotType;

		if (args.length == 3) {
			listCommandOptions(sender, allArmorTypes());
			return;
		}

		armorType = armorType(args[3]);
		if (armorType == ArmorType.INVALID) {
			invalidArgument(sender, args[3]);
			return;
		}

		if (args.length == 4) {
			listCommandOptions(sender, allSlotTypes());
			return;
		}

		slotType = slotType(args[4]);
		if (slotType == SlotType.INVALID) {
			invalidArgument(sender, args[4]);
			return;
		}

		if (args.length == 5) {
			giveItem(sender, player, armorType, slotType);
			return;
		}

		tooManyArguments(sender);
	}

	private void giveMaterialCommand(CommandSender sender, String[] args, Player player) {
		MaterialType materialType;
		int giveAmount;

		if (args.length == 3) {
			listCommandOptions(sender, allMaterialTypes());
			return;
		}

		materialType = materialType(args[3]);
		if(materialType == MaterialType.INVALID){
			invalidArgument(sender, args[3]);
			return;
		}

		if (args.length == 4) {
			giveItem(sender, player, materialType, 1);
			return;
		}

		if (Util.isInteger(args[4])) giveAmount = Integer.parseInt(args[4]);
		else {
			invalidArgument(sender, args[4]);
			return;
		}

		if (args.length == 5) {
			giveItem(sender, player, materialType, giveAmount);
			return;
		}

		tooManyArguments(sender);
	}

	private void giveCommand(CommandSender sender, String[] args) {
		Player player;
		GiveType giveType;

		if (args.length == 1) {
			listCommandOptions(sender, List.of("player_name"));
			return;
		}

		player = plugin.getServer().getPlayer(args[1]);
		if (player == null) {
			invalidArgument(sender, args[1]);
			return;
		}

		if (args.length == 2) {
			listCommandOptions(sender, allGiveTypes());
			return;
		}

		giveType = giveType(args[2]);

		switch (giveType) {
			case ARMOR -> giveArmorCommand(sender, args, player);
			case MATERIAL -> giveMaterialCommand(sender, args, player);
			case INVALID -> invalidArgument(sender, args[2]);
		}
	}

	private void editItem(CommandSender sender, ItemStack item, EditType editType, int amount) {
		if(!(sender instanceof Player p)) return;

		PlayerInventory inv = p.getInventory();

		ArmorType type = NBT.get(item, nbt -> (ArmorType) nbt.getEnum("ArmorID", ArmorType.class));
		if(type == null) {
			notHoldingItem(sender);
			return;
		}

		if(editType == EMERALD_COUNT) {
			if(type != EMERALD) {
				notHoldingItem(sender);
				return;
			}

			EmeraldArmor armor = new EmeraldArmor(item);
			armor.createItemFromNBT();

			armor.setEmeraldCount(amount);

			armor.updateItem();

			inv.setItemInMainHand(armor.getItem());
		}
		else if(editType == KILL_COUNT) {
			if(type != DESTROYER) {
				notHoldingItem(sender);
				return;
			}

			DestroyerArmor armor = new DestroyerArmor(item);
			armor.createItemFromNBT();

			armor.setKillCount(amount);

			armor.updateItem();

			inv.setItemInMainHand(armor.getItem());
		}

		if(amount == 0) resetItemMessage(p, item);
		else editItemMessage(p, item);
	}

	private void reloadConfig(CommandSender s) {
		plugin.registerConfig();
		plugin.getServer().resetRecipes();
		new RecipeHandler(plugin);
		Util.sendColoredMessage(s, prefix() + " &aSuccessfully reloaded config!");
	}

	private void giveItem(CommandSender sender, Player target, ArmorType armorType, SlotType slotType) {
		PlayerInventory inventory = target.getInventory();

		CustomArmor item = armorFromType(armorType, slotType);

		Util.addItem(inventory, target, item.getItem(), 1);
		giveMessage(sender, target, item.getItem(), 1);
	}

	private void giveItem(CommandSender sender, Player target, MaterialType materialType, int amount) {
		PlayerInventory inventory = target.getInventory();

		CustomMaterial item = materialFromType(materialType);

		for(int i = 0; i < amount / 64; i++) Util.addItem(inventory, target, item.getItem(), 64);
		Util.addItem(inventory, target, item.getItem(), amount % 64);

		giveMessage(sender, target, item.getItem(), amount);
	}

	private void giveMessage(CommandSender sender, Player target, ItemStack item, int amount) {
		target.sendMessage(Util.colorString(String.format("&e(&6MoreArmors&e) Received %dx %s&e.", amount, item.getItemMeta().getDisplayName())));
		sender.sendMessage(Util.colorString(String.format("&e(&6MoreArmors&e) Gave %s %dx %s&e.", target.getName(), amount, item.getItemMeta().getDisplayName())));
	}

	public String prefix() {
		return Util.colorString("&e(&6MoreArmors&e)");
	}

	private void listCommandOptions(CommandSender sender, List<String> list) {
		Util.sendColoredMessage(sender, prefix() + " &6Command Options:");
		for(String s : list) {
			if(!s.equals("invalid")) Util.sendColoredMessage(sender, "&6> &e" + s);
		}
	}

	private void tooManyArguments(CommandSender sender) {
		Util.sendColoredMessage(sender, prefix() + " &cToo many arguments! Do /morearmors help for help.");
	}

	private void invalidArgument(CommandSender sender, String argument) {
		Util.sendColoredMessage(sender, prefix() + " &cInvalid argument " + argument + ".");
	}

	private void noPermission(CommandSender sender) {
		Util.sendColoredMessage(sender, prefix() + " &cYou do not have permission to do this!");
	}

	private void pluginInfoMessage(CommandSender sender) {
		Util.sendColoredMessage(sender, "");
		Util.sendColoredMessage(sender, prefix() + " &eRunning &6" + plugin.getName() + " " + plugin.getDescription().getVersion() + " &ecreated by &6OffsetPaladin89&e.");
		Util.sendColoredMessage(sender, "&6> &eOfficial Site: &6https://dev.bukkit.org/projects/MoreArmors");
	}

	private void notHoldingItem(CommandSender s) {
		Util.sendColoredMessage(s, prefix() + " &cYou are not holding a valid item!");
	}

	private void editItemMessage(Player target, ItemStack item) {
		Util.sendColoredMessage(target, String.format("%s &eEdited %s&e.", prefix(), item.getItemMeta().getDisplayName()));
	}

	private void resetItemMessage(Player target, ItemStack item) {
		Util.sendColoredMessage(target, String.format("%s &eReset %s&e.", prefix(), item.getItemMeta().getDisplayName()));
	}
}


