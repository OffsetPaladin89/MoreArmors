package me.offsetpaladin89.MoreArmors.handlers;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.World.Environment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.PlayerInventory;

import static org.bukkit.World.Environment.NETHER;
import static org.bukkit.World.Environment.THE_END;

public class DamageHandler implements Listener {

	private final MoreArmorsMain plugin;
	private final FileConfiguration config;

	public DamageHandler(MoreArmorsMain plugin) {
		this.plugin = plugin;
		this.config = plugin.configHandler.getConfig("config");
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void DamageEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player player && event.getEntity() instanceof LivingEntity) {
			event.setDamage(event.getDamage() + destroyerDamage(player));
            event.setDamage(event.getDamage() * netherDamage(player) * seaGreedDamage(player) * endDamage(player));
            if(config.getBoolean("damageindicators")) plugin.hologramHandler.createDamageHologram(player, player.getLocation(), (LivingEntity) event.getEntity(), 20L, event.getDamage());
		}

		if(event.getEntity() instanceof Player player) {
			Environment env = player.getWorld().getEnvironment();
			if(Util.IsFullCustomSet(ArmorType.NETHER, player.getInventory()) && env.equals(NETHER)) event.setDamage(event.getDamage() * 0.5);
			if(Util.IsFullCustomSet(ArmorType.END, player.getInventory()) && env.equals(THE_END)) event.setDamage(event.getDamage() * 0.5);
		}

		if(event.getDamager() instanceof Arrow damager && event.getEntity() instanceof LivingEntity) {
			if(damager.getShooter() instanceof Player player) {
                event.setDamage(event.getDamage() + destroyerDamage(player));
                event.setDamage(event.getDamage() * netherDamage(player) * seaGreedDamage(player) * endDamage(player));
				if(config.getBoolean("damageindicators")) plugin.hologramHandler.createDamageHologram(player, damager.getLocation(), (LivingEntity) event.getEntity(), 20L, event.getDamage());
			}
		}
	}

	public Float destroyerDamage(Player p) {
		if(!plugin.configHandler.getConfig("config").getBoolean("destroyer_armor.enabled")) return 0f;

		PlayerInventory inv = p.getInventory();
		int helmetBonus = 0, chestplateBonus = 0, leggingsBonus = 0, bootsBonus = 0;
		if(Util.matchingCustomItem(inv.getHelmet(), ArmorType.DESTROYER)) helmetBonus = NBT.get(inv.getHelmet(), nbt -> (int) nbt.getInteger("DamageBonus"));
		if(Util.matchingCustomItem(inv.getChestplate(), ArmorType.DESTROYER)) chestplateBonus = NBT.get(inv.getChestplate(), nbt -> (int) nbt.getInteger("DamageBonus"));
		if(Util.matchingCustomItem(inv.getLeggings(), ArmorType.DESTROYER)) leggingsBonus = NBT.get(inv.getLeggings(), nbt -> (int) nbt.getInteger("DamageBonus"));
		if(Util.matchingCustomItem(inv.getBoots(), ArmorType.DESTROYER)) bootsBonus = NBT.get(inv.getBoots(), nbt -> (int) nbt.getInteger("DamageBonus"));

		return (float) (helmetBonus + chestplateBonus + leggingsBonus + bootsBonus);
	}

    public Float netherDamage(Player p) {
		float damageBonus = 1f;
		if(!config.getBoolean("nether_armor.enabled")) return damageBonus;
		PlayerInventory inv = p.getInventory();
		if(p.getWorld().getEnvironment().equals(NETHER)) {
			if(Util.matchingCustomItem(inv.getHelmet(), ArmorType.NETHER)) damageBonus += 0.1f;
			if(Util.matchingCustomItem(inv.getChestplate(), ArmorType.NETHER)) damageBonus += 0.1f;
			if(Util.matchingCustomItem(inv.getLeggings(), ArmorType.NETHER)) damageBonus += 0.1f;
			if(Util.matchingCustomItem(inv.getBoots(), ArmorType.NETHER)) damageBonus += 0.1f;
			if(Util.IsFullCustomSet(ArmorType.NETHER, inv)) damageBonus += 1f;
		}
		return damageBonus;
    }

	public Float endDamage(Player p) {
		float damageBonus = 1f;
		if(!config.getBoolean("end_armor.enabled")) return damageBonus;
		PlayerInventory inv = p.getInventory();
		if(p.getWorld().getEnvironment().equals(THE_END)) {
			if(Util.matchingCustomItem(inv.getHelmet(), ArmorType.END)) damageBonus += 0.1f;
			if(Util.matchingCustomItem(inv.getChestplate(), ArmorType.END)) damageBonus += 0.1f;
			if(Util.matchingCustomItem(inv.getLeggings(), ArmorType.END)) damageBonus += 0.1f;
			if(Util.matchingCustomItem(inv.getBoots(), ArmorType.END)) damageBonus += 0.1f;
			if(Util.IsFullCustomSet(ArmorType.END, inv)) damageBonus += 1f;
		}
		return damageBonus;
	}

	public Float seaGreedDamage(Player p) {
		if(!(config.getBoolean("sea_greed_armor.enabled") && p.isInWater() && Util.IsFullCustomSet(ArmorType.SEA_GREED, p.getInventory()))) return 1f;
		return 2f;
	}
}
