package me.offsetpaladin89.MoreArmors;

import me.offsetpaladin89.MoreArmors.commands.CommandCompleter;
import me.offsetpaladin89.MoreArmors.commands.Commands;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.handlers.*;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

import static me.offsetpaladin89.MoreArmors.handlers.ArmorSetAbilityHandler.scanPlayers;

public class MoreArmorsMain extends JavaPlugin {

	public HologramHandler hologramHandler;
	public ConfigHandler configHandler;
	public InventoryHandler inventoryHandler;

	public static FileConfiguration config;

	public void onEnable() {
		new NamespacedKey(this, "morearmors");

		configHandler = new ConfigHandler(this);

		new MoreArmorsListener(this);

		new Commands(this);
		new CommandCompleter(this);

		new DamageHandler(this);
		hologramHandler = new HologramHandler(this);

		ArmorChecker();

		new RecipeHandler(this);
		inventoryHandler = new InventoryHandler(this);

		registerConfig();
	}

	public void registerConfig() {
		Map<String, Object> defaultValues = new HashMap<>();
		defaultValues.put("damageindicators", true);
		defaultValues.put("materials.craftable", true);
		for (String s : ArmorType.allArmorTypes()) {
			defaultValues.put(String.format("%s_armor.enabled", s), true);
			defaultValues.put(String.format("%s_armor.craftable", s), true);
		}
		configHandler.saveConfigDefaults("config", defaultValues);

		config = configHandler.getConfig("config");

	}

	private void ArmorChecker() {
		new BukkitRunnable() {
			public void run() {
				scanPlayers(getServer().getOnlinePlayers().toArray());
			}
		}.runTaskTimer(this, 0, 10);
	}
}
