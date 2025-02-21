package me.offsetpaladin89.MoreArmors;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.armors.ArmorsRecord;
import me.offsetpaladin89.MoreArmors.armors.CustomArmor;
import me.offsetpaladin89.MoreArmors.commands.CommandCompleter;
import me.offsetpaladin89.MoreArmors.commands.Commands;
import me.offsetpaladin89.MoreArmors.commands.Give;
import me.offsetpaladin89.MoreArmors.handlers.*;
import me.offsetpaladin89.MoreArmors.listeners.MainListener;
import me.offsetpaladin89.MoreArmors.listeners.MoreArmorsListener;
import me.offsetpaladin89.MoreArmors.materials.Materials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;

public class MoreArmorsMain extends JavaPlugin {

	public final String[] armorTypes = {"emerald", "end", "experience", "miner", "nether", "seagreed", "speedster", "titan", "destroyer"};
	public final String[] materialTypes = {"compacted_blaze_rod", "compacted_cobblestone", "compacted_end_stone", "compacted_eye_of_ender", "compacted_soul_sand", "compacted_sugar_cane", "nether_crown", "compacted_diamond", "compacted_diamond_block", "compacted_gold", "compacted_gold_block", "compacted_prismarine", "compacted_iron", "compacted_iron_block", "compacted_redstone", "machine_part", "machine_core", "energy_cell", "star_dust"};
	public final String[] slotTypes = {"helmet", "chestplate", "leggings", "boots"};
	public Materials materials;

	public HologramHandler hologramHandler;
	public Give give;
	public ArmorsRecord armorSets;
	public ArmorConstructor armorConstructor;
	public ArmorSetAbilityHandler armorSetAbilities;
	public ConfigHandler configHandler;
	public Commands commands;

	public void onEnable() {

		new NamespacedKey(this, "morearmors");

		new MainListener(this);
		new MoreArmorsListener(this);
		new CraftHandler(this);
		new DamageHandler(this);
		new CommandCompleter(this);

		commands = new Commands(this);
		configHandler = new ConfigHandler(this);
		hologramHandler = new HologramHandler(this);
		armorSets = new ArmorsRecord(this);
		armorConstructor = new ArmorConstructor(this);
//		armorSetAbilities = new ArmorSetAbilityHandler(this);
		materials = new Materials(this);
		give = new Give(this);

//		ArmorChecker();
		registerConfig();
//		armorSets.RegisterArmorRecipes();
		materials.RegisterMaterialsRecipes();
	}

	public void reloadConfig(CommandSender s) {
		registerConfig();
		getServer().resetRecipes();
//		armorSets.RegisterArmorRecipes();
		materials.RegisterMaterialsRecipes();
		sendColoredMessage(s, commands.messages.prefix() + " &aSuccessfully reloaded config!");
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
		configHandler.saveConfigDefaults("config", defaultValues);
	}

	public static String colorString(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public void sendConsoleMessage(String s) {
		getServer().getConsoleSender().sendMessage(colorString(s));
	}

	public static void sendDebugMessage(String s) {
		Bukkit.getLogger().log(Level.INFO, s);
	}

	public void sendPlayerMessage(Player p, String s) {
		p.sendMessage(colorString(s));
	}

	public void sendColoredMessage(CommandSender s, String m) {
		s.sendMessage(colorString(m));
	}

	public ShapedRecipe shapedRecipe(String key, ItemStack i) {
		return new ShapedRecipe(new NamespacedKey(this, key), i);
	}

	// Checks if the user is wearing a full set of custom armor
	public boolean IsFullCustomSet(String tag, PlayerInventory inventory) {
		ItemStack helmet = inventory.getHelmet();
		ItemStack chestplate = inventory.getChestplate();
		ItemStack leggings = inventory.getLeggings();
		ItemStack boots = inventory.getBoots();

		if(isAirOrNull(helmet) || isAirOrNull(chestplate) || isAirOrNull(leggings) || isAirOrNull(boots)) return false;

        boolean hasHelmet = NBT.get(helmet, nbt -> (String) nbt.getString("CustomItemID")).equals(tag);
		boolean hasChestplate = NBT.get(chestplate, nbt -> (String) nbt.getString("CustomItemID")).equals(tag);
		boolean hasLeggings = NBT.get(leggings, nbt -> (String) nbt.getString("CustomItemID")).equals(tag);
		boolean hasBoots = NBT.get(boots, nbt -> (String) nbt.getString("CustomItemID")).equals(tag);

		return hasHelmet && hasChestplate && hasLeggings && hasBoots;
	}

	public boolean matchingCustomItem(ItemStack item, String itemID) {
		if(isAirOrNull(item)) return false;
		return NBT.get(item, nbt -> (String) nbt.getString("CustomItemID")).equals(itemID);
	}

	public boolean isAirOrNull(ItemStack item) {
		return item == null || item.getType().equals(Material.AIR);
	}

//	public ProfileInstruction<ItemMeta> getSkull(ItemMeta iMeta, String skullID) {
//		return XSkull.of(iMeta).profile(Profileable.of(ProfileInputType.TEXTURE_HASH.getProfile(skullID)));
//	}

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
//				armorSetAbilities.scanPlayers(getServer().getOnlinePlayers().toArray());
			}
		}.runTaskTimer(this, 0, 5);
	}
}
