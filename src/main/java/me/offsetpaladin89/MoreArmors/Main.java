package me.offsetpaladin89.MoreArmors;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.commands.CommandCompleter;
import me.offsetpaladin89.MoreArmors.commands.Commands;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.fonts.GrayFont;
import me.offsetpaladin89.MoreArmors.handlers.DamageHandler;
import me.offsetpaladin89.MoreArmors.handlers.HologramHandler;
import me.offsetpaladin89.MoreArmors.inventories.Inventories;
import me.offsetpaladin89.MoreArmors.items.Materials;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static me.offsetpaladin89.MoreArmors.enums.MaterialType.*;

public class Main extends JavaPlugin {

	public final MaterialType[] noStackMaterials = {NETHER_CROWN, ENERGY_CELL, MACHINE_CORE};
	public final String[] giveTypes = {"armor", "material"};
	public final String[] editTypes = {"emerald_count", "kill_amount"};
	public final String[] armorTypes = {"emerald", "end", "experience", "miner", "nether", "seagreed", "speedster", "titan", "destroyer"};
	public final String[] materialTypes = {"compacted_blaze_rod", "compacted_cobblestone", "compacted_end_stone", "compacted_eye_of_ender", "compacted_soul_sand", "compacted_sugar_cane", "nether_crown", "compacted_diamond", "compacted_diamond_block", "compacted_gold", "compacted_gold_block", "compacted_prismarine", "compacted_iron", "compacted_iron_block", "compacted_redstone", "machine_part", "machine_core", "energy_cell", "star_dust"};
	public final String[] slotTypes = {"helmet", "chestplate", "leggings", "boots"};
	public Materials materials;

	public ArrayList<CustomRecipe> recipeList;

	public HologramHandler hologramHandler;
	public Config config;
	public Commands commands;
	public Listener listener;
	public Inventories inv;
	public GrayFont grayFont;
	public RegisterRecipes recipeRegistry;

	public void onEnable() {
		new DamageHandler(this);
		new CommandCompleter(this);

		grayFont = new GrayFont(this);
		inv = new Inventories(this);
		listener = new Listener(this);
		commands = new Commands(this);
		config = new Config(this);
		hologramHandler = new HologramHandler(this);
		materials = new Materials(this);
		recipeRegistry = new RegisterRecipes(this);

		recipeRegistry.registerRecipes();
		ArmorChecker();
		registerConfig();
	}

	public void reloadConfig(CommandSender s) {
		registerConfig();
		getServer().resetRecipes();
		sendColoredMessage(s, prefix() + " &aSuccessfully reloaded config!");
		sendConsoleMessage("&aConfig has been successfully reloaded!");
	}

	public void registerConfig() {
		Map<String, Object> defaultValues = new HashMap<>();
		defaultValues.put("damageindicators", true);
		defaultValues.put("materials.crafting", true);
		for (String s : armorTypes) {
			s += "armor";
			defaultValues.put(s + ".enabled", true);
			defaultValues.put(s + ".crafting", true);
		}
		config.saveConfigDefaults("config", defaultValues);
	}

	public static String convertColoredString(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	public void sendConsoleMessage(String s) {
		getServer().getConsoleSender().sendMessage(convertColoredString(s));
	}

	public void sendPlayerMessage(Player p, String s) {
		p.sendMessage(convertColoredString(s));
	}

	public boolean IsFullCustomSet(String tag, PlayerInventory inventory) {
		if (WearingFullSet(inventory)) return false;
		return new NBTItem(inventory.getHelmet()).getString("CustomItemID").equals(tag) && new NBTItem(inventory.getChestplate()).getString("CustomItemID").equals(tag) && new NBTItem(inventory.getLeggings()).getString("CustomItemID").equals(tag) && new NBTItem(inventory.getBoots()).getString("CustomItemID").equals(tag);
	}

	public boolean WearingFullSet(PlayerInventory inventory) {
		return isAirOrNull(new ItemStack[]{inventory.getHelmet(), inventory.getChestplate(), inventory.getLeggings(), inventory.getBoots()});
	}

	public static Boolean matchingCustomItem(ItemStack item, ArmorType itemID) {
		return !isAirOrNull(item) && new NBTItem(item).getString("CustomItemID").equalsIgnoreCase(ArmorType.getSetName(itemID));
	}

	public static boolean isAirOrNull(ItemStack item) {
		return item == null || item.getType().equals(Material.AIR);
	}

	public boolean isAirOrNull(ItemStack[] items) {
		for (ItemStack i : items) {
			if (i == null || i.getType().equals(Material.AIR)) return true;
		}
		return false;
	}

	public boolean isWholeNumber(String string) {
		try {
			int x = Integer.parseInt(string);
			return x > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public static void sendColoredMessage(CommandSender sender, String message) {
		sender.sendMessage(convertColoredString(message));
	}

	public static String prefix() {
		return convertColoredString("&e(&6MoreArmors&e)");
	}

	public void ArmorChecker() {
		new BukkitRunnable() {
			public void run() {
				listener.scanPlayers(getServer().getOnlinePlayers().toArray());
			}
		}.runTaskTimer(this, 0, 5);
	}
}
