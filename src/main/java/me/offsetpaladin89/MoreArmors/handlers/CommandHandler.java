package me.offsetpaladin89.MoreArmors.handlers;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.armors.EmeraldArmor;
import me.offsetpaladin89.MoreArmors.armors.EndArmor;

import me.offsetpaladin89.MoreArmors.enums.GiveType;
import me.offsetpaladin89.MoreArmors.enums.EditType;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.CommandType;

import me.offsetpaladin89.MoreArmors.materials.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

import static me.offsetpaladin89.MoreArmors.enums.GiveType.*;
import static me.offsetpaladin89.MoreArmors.enums.EditType.*;
import static me.offsetpaladin89.MoreArmors.enums.SlotType.*;
import static me.offsetpaladin89.MoreArmors.enums.ArmorType.*;
import static me.offsetpaladin89.MoreArmors.enums.MaterialType.*;
import static me.offsetpaladin89.MoreArmors.enums.CommandType.*;


public record CommandHandler(MoreArmorsMain plugin) {

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
	public void commandMessage(CommandSender sender, String[] args) {
		CommandType commandType;

		if (args.length == 0) {
			helpMessage(sender);
			return;
		}

		commandType = commandType(args[0]);
		if(commandType == CommandType.INVALID) {
			invalidArgument(sender, args[0]);
			return;
		}

		switch (commandType) {
			case GIVE -> giveCommand(sender, args);
			case EDIT -> editCommand(sender, args);
			case INFO -> {
				if(args.length == 1) {
					pluginInfoMessage(sender);
					return;
				}

				tooManyArguments(sender);
			}
			case RELOAD -> reloadCommand(sender, args);
			case INVALID -> invalidArgument(sender, args[0]);
		}
	}

	private void reloadCommand(CommandSender sender, String[] args) {
		if (args.length == 1) {
			if(!sender.hasPermission("morearmors.reload")) {
				noPermission(sender);
				return;
			}
			plugin.reloadConfig(sender);
			return;
		}

		tooManyArguments(sender);
	}

	private void editCommand(CommandSender sender, String[] args) {
		EditType editType;
		int editAmount;
		ItemStack item;

		if(!(sender instanceof Player player)) {
			plugin.sendColoredMessage(sender, prefix() + " &cCommand sender must be a player!");
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

        if(plugin.isAirOrNull(player.getInventory().getItemInMainHand())) {
			notHoldingItem(sender);
			return;
		}

		item = player.getInventory().getItemInMainHand();

		if(args.length == 2) {
			editItem(sender, item, editType, 0);
			return;
		}

		if(plugin.isInteger(args[2])) editAmount = Integer.parseInt(args[2]);
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

		if (plugin.isInteger(args[4])) giveAmount = Integer.parseInt(args[4]);
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

	//	public void editMessage(Integer length, CommandSender sender, String player, String type, String specialValue) {
//
//		if (length > 1) {
//			if (plugin.getServer().getPlayer(player) == null) {
//				invalidArgument(sender, player);
//				return;
//			}
//		}
//		if (length < 3) {
//			plugin.sendColoredMessage(sender, "");
//			plugin.sendColoredMessage(sender, prefix() + " &6Edit Command Options: &e(/morearmors edit " + player + " ... )");
//			if (length == 2) {
//				plugin.sendColoredMessage(sender, "&6> &eemerald_count");
//				plugin.sendColoredMessage(sender, "&6> &ekill_amount");
//			}
//			return;
//		}
//		Player target = plugin.getServer().getPlayer(player);
//		PlayerInventory inventory = target.getInventory();
//		if (plugin.isAirOrNull(inventory.getItemInMainHand())) {
//			notHoldingItem(sender);
//			return;
//		}
//		ItemStack hand = inventory.getItemInMainHand();
//		NBTItem nbtItem = new NBTItem(hand);
//		if (type.equalsIgnoreCase("emerald_count")) {
//			if (nbtItem.getString("CustomItemID").equals("emerald")) {
//				if (length == 3) {
//					nbtItem.setInteger("EmeraldCount", 0);
//					inventory.setItemInMainHand(plugin.armorConstructor.createEmeraldArmor(nbtItem.getItem()));
//					resetItemMessage(sender, target, hand);
//				} else if (length == 4) {
//					if (plugin.isInteger(specialValue)) {
//						nbtItem.setInteger("EmeraldCount", Integer.parseInt(specialValue));
//						inventory.setItemInMainHand(plugin.armorConstructor.createEmeraldArmor(nbtItem.getItem()));
//						editItemMessage(sender, target, hand);
//					} else invalidArgument(sender, specialValue);
//				} else tooManyArguments(sender);
//			} else notHoldingItem(sender);
//		} else if (type.equals("kill_amount")) {
//			if (length == 3) {
//				nbtItem.setInteger("KillAmount", 0);
//				inventory.setItemInMainHand(plugin.armorConstructor.createDestroyerArmor(nbtItem.getItem()));
//				resetItemMessage(sender, target, hand);
//			} else if (length == 4) {
//				if (plugin.isInteger(specialValue)) {
//					nbtItem.setInteger("KillAmount", Integer.parseInt(specialValue));
//					inventory.setItemInMainHand(plugin.armorConstructor.createDestroyerArmor(nbtItem.getItem()));
//					editItemMessage(sender, target, hand);
//				} else invalidArgument(sender, specialValue);
//			} else tooManyArguments(sender);
//		} else invalidArgument(sender, type);
//	}

	// TODO Edit Item
	private void editItem(CommandSender sender, ItemStack item, EditType editType, int amount) {

	}

	private void giveItem(CommandSender sender, Player target, ArmorType armorType, SlotType slotType) {
		PlayerInventory inventory = target.getInventory();

		ItemStack item = switch (armorType) {
			case EMERALD -> new EmeraldArmor(slotType).getItem();
			case END -> new EndArmor(slotType).getItem();
			default -> null;
		};

		addItem(inventory, target, item, 1);
		giveMessage(sender, target, item, 1);
	}

	// TODO Give Material
	private void giveItem(CommandSender sender, Player target, MaterialType materialType, int amount) {
		PlayerInventory inventory = target.getInventory();

		ItemStack item = switch (materialType) {
			case BLAZE_ROD_0 -> new BlazeRod.BlazeRod0().getItem();
			case BLAZE_ROD_1 -> new BlazeRod.BlazeRod1().getItem();
            case COBBLESTONE_0 -> new Cobblestone.Cobblestone0().getItem();
            case COBBLESTONE_1 -> new Cobblestone.Cobblestone1().getItem();
            case COBBLESTONE_2 -> new Cobblestone.Cobblestone2().getItem();
			case DIAMOND_BLOCK_0 -> new DiamondBlock.DiamondBlock0().getItem();
			case ENDSTONE_0 -> new Endstone.Endstone0().getItem();
			case ENDSTONE_1 -> new Endstone.Endstone1().getItem();
			case ENDSTONE_2 -> new Endstone.Endstone2().getItem();
			case ENERGY_CELL -> new EnergyCell().getItem();
			case EYE_OF_ENDER_0 -> new EyeOfEnder.EyeOfEnder0().getItem();
			case EYE_OF_ENDER_1 -> new EyeOfEnder.EyeOfEnder1().getItem();
			case GOLD_BLOCK_0 -> new GoldBlock.GoldBlock0().getItem();
			case IRON_BLOCK_0 -> new IronBlock.IronBlock0().getItem();
			case IRON_BLOCK_1 -> new IronBlock.IronBlock1().getItem();
			case MACHINE_CORE -> new MachineCore().getItem();
			case MACHINE_PART_0 -> new MachinePart.MachinePart0().getItem();
			case MACHINE_PART_1 -> new MachinePart.MachinePart1().getItem();
			case NETHER_CROWN -> new NetherCrown().getItem();
			case PRISMARINE_0 -> new Prismarine.Prismarine0().getItem();
			case REDSTONE_BLOCK_0 -> new RedstoneBlock.RedstoneBlock0().getItem();
            case SOUL_SAND_0 -> new SoulSand.SoulSand0().getItem();
            case SOUL_SAND_1 -> new SoulSand.SoulSand1().getItem();
            case STAR_DUST -> new StarDust().getItem();
			case SUGAR_CANE_0 -> new SugarCane.SugarCane0().getItem();
            case INVALID -> null;
        };

		for(int i = 0; i < amount / 64; i++) addItem(inventory, target, item, 64);
		addItem(inventory, target, item, amount % 64);

		giveMessage(sender, target, item, amount);
	}

	private void addItem(PlayerInventory inventory, Player target, ItemStack item, int stackSize) {
		item.setAmount(stackSize);
		if (inventory.firstEmpty() == -1) target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
		else inventory.addItem(item);
	}

	private void giveMessage(CommandSender sender, Player target, ItemStack item, int amount) {
		target.sendMessage(MoreArmorsMain.colorString(String.format("&e(&6MoreArmors&e) Received %dx %s&e.", amount, item.getItemMeta().getDisplayName())));
		sender.sendMessage(MoreArmorsMain.colorString(String.format("&e(&6MoreArmors&e) Gave %s %dx %s&e.", target.getName(), amount, item.getItemMeta().getDisplayName())));
	}

	public String prefix() {
		return MoreArmorsMain.colorString("&e(&6MoreArmors&e)");
	}

	private void listCommandOptions(CommandSender sender, List<String> list) {
		plugin.sendColoredMessage(sender, prefix() + " &6Command Options:");
		for(String s : list) {
			if(!s.equals("invalid")) plugin.sendColoredMessage(sender, "&6> &e" + s);
		}
	}

	private void tooManyArguments(CommandSender sender) {
		plugin.sendColoredMessage(sender, prefix() + " &cToo many arguments! Do /morearmors help for help.");
	}

	private void invalidArgument(CommandSender sender, String argument) {
		plugin.sendColoredMessage(sender, prefix() + " &cInvalid argument " + argument + ".");
	}

	private void noPermission(CommandSender sender) {
		plugin.sendColoredMessage(sender, prefix() + " &cYou do not have permission to do this!");
	}

	private void helpMessage(CommandSender sender) {
		plugin.sendColoredMessage(sender, "");
		plugin.sendColoredMessage(sender, prefix() + " &6Available Commands:");
		plugin.sendColoredMessage(sender, "&6> &e/morearmors edit <user>");
		plugin.sendColoredMessage(sender, "&6> &e/morearmors give <user>");
		plugin.sendColoredMessage(sender, "&6> &e/morearmors info");
		plugin.sendColoredMessage(sender, "&6> &e/morearmors reload");
	}

	private void pluginInfoMessage(CommandSender sender) {
		plugin.sendColoredMessage(sender, "");
		plugin.sendColoredMessage(sender, prefix() + " &eRunning &6" + plugin.getName() + " " + plugin.getDescription().getVersion() + " &ecreated by &6OffsetPaladin89&e.");
		plugin.sendColoredMessage(sender, "&6> &eOfficial Site: &6https://dev.bukkit.org/projects/MoreArmors");
	}

	public void notHoldingItem(CommandSender s) {
		plugin.sendColoredMessage(s, prefix() + " &cYou are not holding a valid item!");
	}

	public void editItemMessage(Player target, ItemStack item) {
		plugin.sendColoredMessage(target, String.format("%s &eEdited %s&e.", prefix(), item.getItemMeta().getDisplayName()));
	}

	public void resetItemMessage(Player target, ItemStack item) {
		plugin.sendColoredMessage(target, String.format("%s &eReset %s&e.", prefix(), item.getItemMeta().getDisplayName()));
	}
}
