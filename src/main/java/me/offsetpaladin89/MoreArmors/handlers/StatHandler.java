package me.offsetpaladin89.MoreArmors.handlers;

import java.util.HashMap;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class StatHandler {
	
	private final MoreArmorsMain plugin;
	
	public HashMap<String, Float> basestats = new HashMap<>();
	public HashMap<String, Float> additionalstats = new HashMap<>();
	
	public StatHandler(MoreArmorsMain plugin) { this.plugin = plugin; }

	public void setBaseStats() {
		basestats.put("health", 20f);
		basestats.put("strength", 0f);
		basestats.put("movementSpeed", 0.1f);
		basestats.put("defense", 0f);
		basestats.put("attackSpeed", 4f);
		basestats.put("criticalHitChance", 0f);
		basestats.put("criticalHitDamage", 100f);
	}
	
	public void setAdditionalStats() {
		additionalstats.put("health", 0f);
		additionalstats.put("strength", 0f);
		additionalstats.put("movementSpeed", 0f);
		additionalstats.put("defense", 0f);
		additionalstats.put("attackSpeed", 0f);
		additionalstats.put("criticalHitChance", 0f);
		additionalstats.put("criticalHitDamage", 0f);
	}
	
	public Float getStat(ItemStack item, String stat) {
		NBTItem nbtItem = new NBTItem(item);
		return nbtItem.getFloat(stat);
	}
	
	public void getAdditionalStats(ItemStack item, String slot, Player player) {
		if(!plugin.isAirOrNull(item)) {
			NBTItem nbtItem = new NBTItem(item);
			if(nbtItem.getBoolean("IsCustomItem")) {
				if(nbtItem.getString("slot").equals(slot)) {
					if(getStat(item, "health") != null) {additionalstats.put("health", getStat(item, "health") + additionalstats.get("health"));}
					if(getStat(item, "strength") != null) {additionalstats.put("strength", getStat(item, "strength") + additionalstats.get("strength"));}
					if(getStat(item, "movementSpeed") != null) {additionalstats.put("movementSpeed", getStat(item, "movementSpeed") + additionalstats.get("movementSpeed"));}
					if(getStat(item, "defense") != null) {additionalstats.put("defense", getStat(item, "defense") + additionalstats.get("defense"));}
					if(getStat(item, "criticalHitChance") != null) {additionalstats.put("criticalHitChance", getStat(item, "criticalHitChance") + additionalstats.get("criticalHitChance"));}
					if(getStat(item, "criticalHitDamage") != null) {additionalstats.put("criticalHitDamage", getStat(item, "criticalHitDamage") + additionalstats.get("criticalHitDamage"));}
				}
			}
		}
	}
	
	public void setStats(Player player) {
		player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(basestats.get("movementSpeed") + additionalstats.get("movementSpeed"));
		player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(basestats.get("attackSpeed") + additionalstats.get("attackSpeed"));
	}
	
	public void updateStats(Player player) {
		PlayerInventory inventory = player.getInventory();
		ItemStack mainhand = inventory.getItemInMainHand();
		ItemStack offhand = inventory.getItemInOffHand();
		ItemStack helmet = inventory.getHelmet();
		ItemStack chestplate = inventory.getChestplate();
		ItemStack leggings = inventory.getLeggings();
		ItemStack boots = inventory.getBoots();
		
		setAdditionalStats();
		
		getAdditionalStats(mainhand, "mainhand", player);
		getAdditionalStats(offhand, "offhand", player);
		getAdditionalStats(helmet, "helmet", player);
		getAdditionalStats(chestplate, "chestplate", player);
		getAdditionalStats(leggings, "leggings", player);
		getAdditionalStats(boots, "boots", player);
		
		for(int i = 0; i < inventory.getSize(); i++) {
			ItemStack item = inventory.getItem(i);
			getAdditionalStats(item, "accessory", player);
		}
	}
}
