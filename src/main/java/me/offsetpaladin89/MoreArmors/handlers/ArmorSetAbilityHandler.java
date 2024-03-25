package me.offsetpaladin89.MoreArmors.handlers;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
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
			if (obj instanceof Player) {
				Player p = (Player) obj;
				if (plugin.IsFullCustomSet("nether", p.getInventory())) {
					if (p.getLocation().getWorld().getEnvironment().equals(Environment.NETHER)) {
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
						p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100, 0, false, false));
					} else {
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
						p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);

					}

				} else if (plugin.IsFullCustomSet("end", p.getInventory())) {
					if (p.getLocation().getWorld().getEnvironment().equals(Environment.THE_END)) {
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
					} else {
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
					}
				} else if (plugin.IsFullCustomSet("seagreed", p.getInventory())) {
					if (inWater(p)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 100, 0, false, false));
					} else {
						p.removePotionEffect(PotionEffectType.CONDUIT_POWER);
					}
				}
			}

		}
	}

	private boolean inWater(Player p) {
		return p.getLocation().getBlock().getType().equals(Material.WATER);
	}

	public static void TrueDiamondArmor(Player player) {
		if (plugin.IsFullCustomSet("truediamond", player.getInventory())) {
			int playercount = 0;
			for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
				if (entity instanceof Player) {
					playercount++;
				}
			}
			if (playercount > 10) {
				playercount = 10;
			}
			plugin.armydamageincrease.put(player, 1 + (float) playercount / 50);
		}
	}

	public static void DestroyerArmor(Player player) {
		PlayerInventory inventory = player.getInventory();
		if (plugin.isAirOrNull(inventory.getHelmet())) {
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			plugin.destroyerhelmet.remove(player);
		}
		if (!plugin.IsFullCustomSet("destroyer", inventory)) {
			player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			player.removePotionEffect(PotionEffectType.REGENERATION);
			plugin.destroyerarmor.remove(player);
		}
		if (!plugin.isAirOrNull(inventory.getHelmet())) {
			NBTItem nbtItem = new NBTItem(inventory.getHelmet());
			if (plugin.destroyerhelmet.contains(player) && !nbtItem.getString("CustomItemID").equals("destroyer")) {
				player.removePotionEffect(PotionEffectType.NIGHT_VISION);
				plugin.destroyerhelmet.remove(player);
			}
			if (!plugin.destroyerhelmet.contains(player) && nbtItem.getString("CustomItemID").equals("destroyer")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false));
				plugin.destroyerhelmet.add(player);
			}
		}

		if (plugin.destroyerarmor.contains(player) && !plugin.IsFullCustomSet("destroyer", inventory)) {
			player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			player.removePotionEffect(PotionEffectType.REGENERATION);
			plugin.destroyerarmor.remove(player);
		}
		if (!plugin.destroyerarmor.contains(player) && plugin.IsFullCustomSet("destroyer", inventory)) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1, false, false));
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1, false, false));
			plugin.destroyerarmor.add(player);
		}
	}
}
