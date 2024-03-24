package me.offsetpaladin89.MoreArmors.handlers;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
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

    public static void EndArmor(Player player) {
    	if(!plugin.endarmor.contains(player)) {
    		if(plugin.IsFullCustomSet("end", player.getInventory()) && player.getLocation().getWorld().getEnvironment().equals(Environment.THE_END)) {
    			player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
    			plugin.endarmor.add(player);
    		}
    	}
    	if(plugin.endarmor.contains(player)) {
    		if(!(plugin.IsFullCustomSet("end", player.getInventory()) && player.getLocation().getWorld().getEnvironment().equals(Environment.THE_END))) {
    			player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
    			plugin.endarmor.remove(player);
    		}
    	}
    }

    public static void NetherArmor(Player player) {
    	if(!plugin.netherarmor.contains(player)) {
    		if(plugin.IsFullCustomSet("nether", player.getInventory()) && player.getLocation().getWorld().getEnvironment().equals(Environment.NETHER)) {
    			player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
    			player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, false, false));
    			plugin.netherarmor.add(player);
    		}
    	}
    	if(plugin.netherarmor.contains(player)) {
    		if(!(plugin.IsFullCustomSet("nether", player.getInventory()) && player.getLocation().getWorld().getEnvironment().equals(Environment.NETHER))) {
    			player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
    			player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
    			plugin.netherarmor.remove(player);
    		}
    	}
    }

    public static void TrueDiamondArmor(Player player) {
    	if(plugin.IsFullCustomSet("truediamond", player.getInventory())) {
			int playercount = 0;
			for(Entity entity : player.getNearbyEntities(10, 10, 10)) {if(entity instanceof Player) {playercount++;}}
			if(playercount > 10) {playercount = 10;}
			plugin.armydamageincrease.put(player, 1 + (float) playercount / 50);
		}
    }

    public static void DestroyerArmor(Player player) {
    	PlayerInventory inventory = player.getInventory();
		if(plugin.isAirOrNull(inventory.getHelmet())) {
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			plugin.destroyerhelmet.remove(player);
		}
		if(!plugin.IsFullCustomSet("destroyer", inventory)) {
			player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			player.removePotionEffect(PotionEffectType.REGENERATION);
			plugin.destroyerarmor.remove(player);
		}
		if(!plugin.isAirOrNull(inventory.getHelmet())) {
			NBTItem nbtItem = new NBTItem(inventory.getHelmet());
			if(plugin.destroyerhelmet.contains(player) && !nbtItem.getString("CustomItemID").equals("destroyer")) {
				player.removePotionEffect(PotionEffectType.NIGHT_VISION);
				plugin.destroyerhelmet.remove(player);
			}
			if(!plugin.destroyerhelmet.contains(player) && nbtItem.getString("CustomItemID").equals("destroyer")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false));
				plugin.destroyerhelmet.add(player);
			}
		}

		if(plugin.destroyerarmor.contains(player) && !plugin.IsFullCustomSet("destroyer", inventory)) {
			player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			player.removePotionEffect(PotionEffectType.REGENERATION);
			plugin.destroyerarmor.remove(player);
		}
    	if(!plugin.destroyerarmor.contains(player) && plugin.IsFullCustomSet("destroyer", inventory)) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1, false, false));
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1, false, false));
			plugin.destroyerarmor.add(player);
		}
    }
}
