package me.offsetpaladin89.MoreArmors;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.armors.Armors;
import me.offsetpaladin89.MoreArmors.armors.truediamondarmor.TrueDiamondArmor;
import me.offsetpaladin89.MoreArmors.armors.truediamondarmor.TrueDiamondArmorData;
import me.offsetpaladin89.MoreArmors.commands.CommandCompleter;
import me.offsetpaladin89.MoreArmors.commands.Commands;
import me.offsetpaladin89.MoreArmors.commands.GiveArmor;
import me.offsetpaladin89.MoreArmors.commands.GiveMaterial;
import me.offsetpaladin89.MoreArmors.handlers.*;
import me.offsetpaladin89.MoreArmors.listeners.MainListener;
import me.offsetpaladin89.MoreArmors.listeners.MaterialsListener;
import me.offsetpaladin89.MoreArmors.listeners.MoreArmorsListener;
import me.offsetpaladin89.MoreArmors.materials.Materials;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MoreArmorsMain extends JavaPlugin {

	private final String[] armors = {"emerald", "end", "experience", "miner", "nether", "seagreed", "speedster", "titan", "truediamond"};
	public List<String> validArmors = new ArrayList<>(Arrays.asList(armors));
	private final String[] slots = {"helmet", "chestplate", "leggings", "boots"};
	public List<String> validSlots = new ArrayList<>(Arrays.asList(armors));
	public ArrayList<Player> endarmor = new ArrayList<>();
	public ArrayList<Player> destroyerarmor = new ArrayList<>();
	public ArrayList<Player> destroyerhelmet = new ArrayList<>();
	public HashMap<Player, Float> armydamageincrease = new HashMap<>();
	public TrueDiamondArmor truediamond;
	public TrueDiamondArmorData truediamonddata;
	public Materials materials;

	public HologramHandler hologramHandler;
	public GiveMaterial givematerial;

	public GiveArmor givearmor;

	public ArmorConstructor armorConstructor;

	public Armors armorSets;

	public ArmorSetAbilityHandler armorSetAbilities;

	public void onEnable() {

		new MainListener(this);
		new MaterialsListener(this);
		new MoreArmorsListener(this);
		new CraftHandler(this);
		new DamageHandler(this);
		new Commands(this);
		new CommandCompleter(this);
		new TextHandler(this);

		hologramHandler = new HologramHandler(this);
		armorConstructor = new ArmorConstructor(this);
		armorSets = new Armors(this);
		armorSetAbilities = new ArmorSetAbilityHandler(this);
		truediamond = new TrueDiamondArmor(this);
		truediamonddata = new TrueDiamondArmorData(this);
		materials = new Materials(this);
		givematerial = new GiveMaterial(this);
		givearmor = new GiveArmor(this);

		ArmorChecker();
		armorSets.RegisterArmorRecipes();
		materials.RegisterMaterialsRecipes();
		removeHolograms();
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

	public String convertColoredString(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	public boolean IsFullCustomSet(String tag, PlayerInventory inventory) {
		if (WearingFullSet(inventory)) {
			return false;
		}

		return new NBTItem(inventory.getHelmet()).getString("CustomItemID").equals(tag) &&
				new NBTItem(inventory.getChestplate()).getString("CustomItemID").equals(tag) &&
				new NBTItem(inventory.getLeggings()).getString("CustomItemID").equals(tag) &&
				new NBTItem(inventory.getBoots()).getString("CustomItemID").equals(tag);
	}

	public boolean WearingFullSet(PlayerInventory inventory) {
		return isAirOrNull(new ItemStack[]{inventory.getHelmet(), inventory.getChestplate(), inventory.getLeggings(), inventory.getBoots()});
	}

	public Boolean matchingCustomItem(ItemStack item, String itemID) {
		return !isAirOrNull(item) && new NBTItem(item).getString("CustomItemID").equals(itemID);
	}

	public void sendConsoleMessage(String s) {
		getServer().getConsoleSender().sendMessage(convertColoredString(s));
	}

	public void sendPlayerMessage(Player p, String s) {
		p.sendMessage(convertColoredString(s));
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

	public void ArmorChecker() {
		new BukkitRunnable() {
			public void run() {
				armorSetAbilities.scanPlayers(getServer().getOnlinePlayers().toArray());
			}
		}.runTaskTimer(this, 0, 20);
	}
}
