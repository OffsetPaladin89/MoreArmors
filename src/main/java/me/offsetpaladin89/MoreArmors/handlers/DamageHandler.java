package me.offsetpaladin89.MoreArmors.handlers;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.utils.Util;
import me.offsetpaladin89.MoreArmors.utils.stats.ArmorStats;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
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
		event.setDamage(getDamage(event.getDamager(), event.getEntity(), event.getFinalDamage()));
	}

	private double getDamage(Entity e1, Entity e2, double initDamage) {
		if(!(e2 instanceof LivingEntity)) return initDamage;

		ArmorStats e1stats = new ArmorStats();
		ArmorStats e2stats = new ArmorStats();
		Location damageLoc = null, damageLoc2 = null;
		Player p = null, p2 = null;

		if(e1 instanceof Player) {
			p = (Player) e1;
			e1stats.getStats(p.getInventory().getArmorContents(), p);
			damageLoc = p.getLocation();
		}
		else if(e1 instanceof Arrow a) {
			if(a.getShooter() instanceof Player) {
				p = (Player) a.getShooter();
				e1stats.getStats(p.getInventory().getArmorContents(), p);
				damageLoc = a.getLocation();
			}
			else return initDamage;
		}

		if(e2 instanceof Player) {
			p2 = (Player) e2;
			e2stats.getStats(p2.getInventory().getArmorContents(), p2);
			damageLoc2 = p2.getLocation();
		}

		double damageMultiplier = 1 + e1stats.getDamageMultiplier();

		double finalDamage = (initDamage + e1stats.getAdditionalDamage()) * damageMultiplier * (1 - e2stats.getDamageReduction());

		if(config.getBoolean("damageindicators")) {
			if(p != null) plugin.hologramHandler.createDamageHologram(p, damageLoc, (LivingEntity) e2, 20L, finalDamage);
			if(p2 != null) plugin.hologramHandler.createDamageHologram(p2, damageLoc2, (LivingEntity) e2, 20L, finalDamage);

		}
		return finalDamage;
	}

	public Float destroyerDamage(Player p) {
		if(!plugin.configHandler.getConfig("config").getBoolean("destroyer_armor.enabled")) return 0f;

		PlayerInventory inv = p.getInventory();
		int helmetBonus = NBT.get(inv.getHelmet(), nbt -> (int) nbt.getInteger("DamageBonus"));
		int chestplateBonus = NBT.get(inv.getChestplate(), nbt -> (int) nbt.getInteger("DamageBonus"));
		int leggingsBonus = NBT.get(inv.getLeggings(), nbt -> (int) nbt.getInteger("DamageBonus"));
		int bootsBonus = NBT.get(inv.getHelmet(), nbt -> (int) nbt.getInteger("DamageBonus"));

		return (float) (helmetBonus + chestplateBonus + leggingsBonus + bootsBonus);
	}
}