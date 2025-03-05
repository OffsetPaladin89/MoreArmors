package me.offsetpaladin89.MoreArmors;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import me.offsetpaladin89.MoreArmors.commands.CommandCompleter;
import me.offsetpaladin89.MoreArmors.commands.Commands;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.handlers.*;
import me.offsetpaladin89.MoreArmors.listeners.MainListener;
import me.offsetpaladin89.MoreArmors.listeners.MoreArmorsListener;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.NumberFormat;
import java.util.*;

public class MoreArmorsMain extends JavaPlugin {

	public HologramHandler hologramHandler;
	public ArmorSetAbilityHandler armorSetAbilities;
	public ConfigHandler configHandler;
	public Commands commands;
	public InventoryHandler inventoryHandler;

	public void onEnable() {
		new NamespacedKey(this, "morearmors");

		configHandler = new ConfigHandler(this);

		new MainListener(this);
		new MoreArmorsListener(this);

		commands = new Commands(this);
		new CommandCompleter(this);

		new DamageHandler(this);
		hologramHandler = new HologramHandler(this);

		armorSetAbilities = new ArmorSetAbilityHandler(this);
		ArmorChecker();

		new RecipeHandler(this);
		inventoryHandler = new InventoryHandler(this);

		registerConfig();
	}

	public void reloadConfig(CommandSender s) {
		registerConfig();
		getServer().resetRecipes();
		new RecipeHandler(this);
		sendColoredMessage(s, commands.prefix() + " &aSuccessfully reloaded config!");
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
	}

	public static String colorString(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public void sendConsoleMessage(String m) {
		getServer().getConsoleSender().sendMessage(m);
	}

	public void sendColoredMessage(CommandSender s, String m) {
		s.sendMessage(colorString(m));
	}

	public boolean IsFullCustomSet(ArmorType tag, PlayerInventory inventory) {
		ItemStack helmet = inventory.getHelmet();
		ItemStack chestplate = inventory.getChestplate();
		ItemStack leggings = inventory.getLeggings();
		ItemStack boots = inventory.getBoots();

		if(isAirOrNull(helmet) || isAirOrNull(chestplate) || isAirOrNull(leggings) || isAirOrNull(boots)) return false;

        boolean hasHelmet = matchingCustomItem(helmet, tag);
		boolean hasChestplate = matchingCustomItem(chestplate, tag);
		boolean hasLeggings = matchingCustomItem(leggings, tag);
		boolean hasBoots = matchingCustomItem(boots, tag);

		return hasHelmet && hasChestplate && hasLeggings && hasBoots;
	}

	public boolean isCustomItem(ItemStack item) {
		boolean isMaterial = NBT.get(item, nbt -> nbt.getEnum("MaterialID", MaterialType.class) != null);
		boolean isArmor = NBT.get(item, nbt -> nbt.getEnum("ArmorID", ArmorType.class) != null);
		return isMaterial || isArmor;
	}

	public boolean matchingCustomItem(ItemStack item, ArmorType itemID) {
		if(isAirOrNull(item)) return false;
		ArmorType type = NBT.get(item, nbt -> (ArmorType) nbt.getEnum("ArmorID", ArmorType.class));
		if(type == null) return false;
		return type.equals(itemID);
	}

	public boolean isAirOrNull(ItemStack item) {
		return item == null || item.getType().equals(Material.AIR);
	}

	public static void modifySkullSkin(ItemStack item, String textureValue, UUID uuid) {

		String convertBase64Texture = Base64.getEncoder().encodeToString(String.format("{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/%s\"}}}", textureValue).getBytes());

		NBT.modifyComponents(item, nbt -> {
			ReadWriteNBT profileNbt = nbt.getOrCreateCompound("minecraft:profile");
			profileNbt.setUUID("id", uuid);
			ReadWriteNBT propertiesNbt = profileNbt.getCompoundList("properties").addCompound();
			propertiesNbt.setString("name", "textures");
			propertiesNbt.setString("value", convertBase64Texture);
		});
	}

	public static String formatNumber(int n) {
		NumberFormat formatter = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
		formatter.setMinimumFractionDigits(2);
		return formatter.format(n);
	}

	public boolean isInteger(String string) {
		try {
			Integer.parseInt(string);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void ArmorChecker() {
		new BukkitRunnable() {
			public void run() {
				armorSetAbilities.scanPlayers(getServer().getOnlinePlayers().toArray());
			}
		}.runTaskTimer(this, 0, 10);
	}

	public void addItem(PlayerInventory inventory, Player target, ItemStack item, int stackSize) {
		item.setAmount(stackSize);
		if (inventory.firstEmpty() == -1) target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
		else inventory.addItem(item);
	}
}
