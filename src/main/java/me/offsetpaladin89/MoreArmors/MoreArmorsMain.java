package me.offsetpaladin89.MoreArmors;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.armors.Armors;
import me.offsetpaladin89.MoreArmors.commands.CommandCompleter;
import me.offsetpaladin89.MoreArmors.commands.Commands;
import me.offsetpaladin89.MoreArmors.commands.Give;
import me.offsetpaladin89.MoreArmors.handlers.*;
import me.offsetpaladin89.MoreArmors.listeners.MainListener;
import me.offsetpaladin89.MoreArmors.listeners.MoreArmorsListener;
import me.offsetpaladin89.MoreArmors.materials.Materials;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class MoreArmorsMain extends JavaPlugin {

	public final String[] armorTypes = {"emerald", "end", "experience", "miner", "nether", "seagreed", "speedster", "titan", "destroyer"};
	public final String[] materialTypes = {"compacted_blaze_rod", "compacted_cobblestone", "compacted_end_stone", "compacted_eye_of_ender", "compacted_soul_sand", "compacted_sugar_cane", "nether_crown", "compacted_diamond", "compacted_diamond_block", "compacted_gold", "compacted_gold_block", "compacted_prismarine", "compacted_iron", "compacted_iron_block", "compacted_redstone", "machine_part", "machine_core", "energy_cell", "star_dust"};
	public final String[] slotTypes = {"helmet", "chestplate", "leggings", "boots"};
	public ArrayList<Player> destroyerarmor = new ArrayList<>();
	public ArrayList<Player> destroyerhelmet = new ArrayList<>();
	public Materials materials;

	public HologramHandler hologramHandler;
	public Give give;
	public Armors armorSets;
	public ArmorConstructor armorConstructor;
	public ArmorSetAbilityHandler armorSetAbilities;
	public SignHandler signHandler;
	public void onEnable() {


		new MainListener(this);
		new MoreArmorsListener(this);
		new CraftHandler(this);
		new DamageHandler(this);
		new Commands(this);
		new CommandCompleter(this);

		signHandler = new SignHandler(this);
		hologramHandler = new HologramHandler(this);
		armorSets = new Armors(this);
		armorConstructor = new ArmorConstructor(this);
		armorSetAbilities = new ArmorSetAbilityHandler(this);
		materials = new Materials(this);
		give = new Give(this);

		ArmorChecker();
		removeHolograms();
		armorSets.RegisterArmorRecipes();
		materials.RegisterMaterialsRecipes();
	}

	public String convertColoredString(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	public void sendConsoleMessage(String s) {
		getServer().getConsoleSender().sendMessage(convertColoredString(s));
	}

	public void sendPlayerMessage(Player p, String s) {
		p.sendMessage(convertColoredString(s));
	}

	public void sendColoredMessage(CommandSender s, String m) {
		s.sendMessage(convertColoredString(m));
	}

	public ShapedRecipe shapedRecipe(String key, ItemStack i) {
		return new ShapedRecipe(new NamespacedKey(this, key), i);
	}

	public boolean IsFullCustomSet(String tag, PlayerInventory inventory) {
		if (WearingFullSet(inventory)) return false;
		return new NBTItem(inventory.getHelmet()).getString("CustomItemID").equals(tag) && new NBTItem(inventory.getChestplate()).getString("CustomItemID").equals(tag) && new NBTItem(inventory.getLeggings()).getString("CustomItemID").equals(tag) && new NBTItem(inventory.getBoots()).getString("CustomItemID").equals(tag);
	}

	public boolean WearingFullSet(PlayerInventory inventory) {
		return isAirOrNull(new ItemStack[]{inventory.getHelmet(), inventory.getChestplate(), inventory.getLeggings(), inventory.getBoots()});
	}

	public Boolean matchingCustomItem(ItemStack item, String itemID) {
		return !isAirOrNull(item) && new NBTItem(item).getString("CustomItemID").equals(itemID);
	}

	public void removeHolograms() {
		for (World world : getServer().getWorlds()) {
			for (Entity entity : world.getEntities()) {
				if (entity.getType().equals(EntityType.ARMOR_STAND) || entity.getType().equals(EntityType.BAT)) {
					PersistentDataContainer pdc = entity.getPersistentDataContainer();
					NamespacedKey key = new NamespacedKey(this, "HologramEntity");
					if (Boolean.TRUE.equals(pdc.get(key, PersistentDataType.BOOLEAN))) {
						entity.remove();
					}
				}
			}
		}
	}

	public boolean isAirOrNull(ItemStack item) {
		return item == null || item.getType().equals(Material.AIR);
	}

	public boolean isAirOrNull(ItemStack[] items) {
		for (ItemStack i : items) {
			if (i == null || i.getType().equals(Material.AIR)) {
				return true;
			}
		}
		return false;
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
		}.runTaskTimer(this, 0, 5);
	}
}
