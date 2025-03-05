package me.offsetpaladin89.MoreArmors.handlers;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArmorSetAbilityHandler {

	private static MoreArmorsMain plugin;
	private static FileConfiguration config;

	public ArmorSetAbilityHandler(MoreArmorsMain plugin) {
		config = plugin.configHandler.getConfig("config");
		ArmorSetAbilityHandler.plugin = plugin;
	}

	public void scanPlayers(Object[] players) {
		for (Object obj : players) {
			if (obj instanceof Player p) {
				destroyerArmor(p);
				netherArmor(p);
				seaGreedArmor(p);
				titanArmor(p);
			}
		}
	}

	private void destroyerArmor(Player p) {
		if(!config.getBoolean("destroyer_armor.enabled")) return;
		if (plugin.matchingCustomItem(p.getInventory().getHelmet(), ArmorType.DESTROYER)) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 30, 0, false, false));
		}

		if (plugin.IsFullCustomSet(ArmorType.DESTROYER, p.getInventory())) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 30, 1, false, false));
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 1, false, false));
			p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 30, 1, false, false));
		}
	}

	private void netherArmor(Player p) {
		if(!(p.getWorld().getEnvironment().equals(Environment.NETHER) && config.getBoolean("nether_armor.enabled"))) return;
		if (plugin.IsFullCustomSet(ArmorType.NETHER, p.getInventory())) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 30, 0, false, false));
		}
	}

	private void seaGreedArmor(Player p) {
		if(!(config.getBoolean("sea_greed_armor.enabled") && p.isInWater())) return;
		if (plugin.IsFullCustomSet(ArmorType.SEA_GREED, p.getInventory())) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 30, 0, false, false));
		}
	}

	private void titanArmor(Player p) {
		if(!config.getBoolean("titan_armor.enabled")) return;
		if (plugin.IsFullCustomSet(ArmorType.TITAN, p.getInventory())) p.getAttribute(Attribute.SCALE).setBaseValue(1.5);
		else p.getAttribute(Attribute.SCALE).setBaseValue(1);
	}
}