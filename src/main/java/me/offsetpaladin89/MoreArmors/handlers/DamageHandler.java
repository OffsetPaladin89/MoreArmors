package me.offsetpaladin89.MoreArmors.handlers;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.StatType;
import me.offsetpaladin89.MoreArmors.utils.stats.BaseStats;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

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

		BaseStats e1stats = new BaseStats();
		BaseStats e2stats = new BaseStats();
		Location damageLoc = null, damageLoc2 = null;
		Player p = null, p2 = null;

		if(e1 instanceof Player) {
			p = (Player) e1;
			e1stats = BaseStats.getStats(p.getInventory().getArmorContents(), p);
			damageLoc = p.getLocation();
		}
		else if(e1 instanceof Arrow a) {
			if(a.getShooter() instanceof Player) {
				p = (Player) a.getShooter();
				e1stats = BaseStats.getStats(p.getInventory().getArmorContents(), p);
				damageLoc = a.getLocation();
			}
			else return initDamage;
		}

		if(e2 instanceof Player) {
			p2 = (Player) e2;
			e2stats = BaseStats.getStats(p2.getInventory().getArmorContents(), p2);
			damageLoc2 = p2.getLocation();
		}

		double damageMultiplier = 1 + e1stats.getStat(p, StatType.MULT_DMG);

		Random r = new Random();

		double critChance = e1stats.getStat(p, StatType.CRIT_CHANCE);
		double critDamage = 0.5 + e1stats.getStat(p, StatType.CRIT_DMG);

		int critLevel = 1 + (int) critChance;

		double factoredCritChance = critChance % 1;

		boolean isCrit = r.nextDouble() < factoredCritChance;

		if(critLevel != 1 && !isCrit) {
			isCrit = true;
			critLevel -= 1;
		}

		System.out.println("Crit Damage: " + critDamage + " | Crit Level: " + critLevel);

		double critDamageBonus = isCrit ? Math.pow(1 + critDamage, critLevel) : 1d;

		double finalDamage = (initDamage + e1stats.getStat(p, StatType.ADD_DMG)) * damageMultiplier * (1 - e2stats.getStat(p2, StatType.DMG_REDUC)) * critDamageBonus;

		if(config.getBoolean("damageindicators")) {
			if(p != null) plugin.hologramHandler.createDamageHologram(p, damageLoc, (LivingEntity) e2, 20L, finalDamage, isCrit, critLevel);
			if(p2 != null) plugin.hologramHandler.createDamageHologram(p2, damageLoc2, (LivingEntity) e2, 20L, finalDamage, isCrit, critLevel);

		}
		return finalDamage;
	}
}