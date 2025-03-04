package me.offsetpaladin89.MoreArmors.handlers;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArmorSetAbilityHandler {

	private static MoreArmorsMain plugin;

	public ArmorSetAbilityHandler(MoreArmorsMain plugin) {
		ArmorSetAbilityHandler.plugin = plugin;
	}

	public void scanPlayers(Object[] players) {
		for (Object obj : players) {
			if (obj instanceof Player p) {
                PlayerInventory inv = p.getInventory();
				World w = p.getLocation().getWorld();

				if (plugin.IsFullCustomSet(ArmorType.TITAN, inv)) p.getAttribute(Attribute.SCALE).setBaseValue(1.5);
				else p.getAttribute(Attribute.SCALE).setBaseValue(1);

				if (plugin.IsFullCustomSet(ArmorType.NETHER, inv) && w.getEnvironment().equals(Environment.NETHER)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 30, 0, false, false));
				}

				if (plugin.IsFullCustomSet(ArmorType.DESTROYER, inv)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 30, 1, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 1, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 30, 1, false, false));
				}

				if (plugin.matchingCustomItem(inv.getHelmet(), ArmorType.DESTROYER)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 30, 0, false, false));
				}

				if (plugin.IsFullCustomSet(ArmorType.SEA_GREED, inv)) {
					if (p.isInWater())
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 30, 0, false, false));
				}
			}
		}
	}
}