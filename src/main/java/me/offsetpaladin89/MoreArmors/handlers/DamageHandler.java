package me.offsetpaladin89.MoreArmors.handlers;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.Main;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.PlayerInventory;

import static me.offsetpaladin89.MoreArmors.Main.matchingCustomItem;
import static me.offsetpaladin89.MoreArmors.enums.ArmorType.*;
import static me.offsetpaladin89.MoreArmors.enums.SlotType.*;

public class DamageHandler implements Listener {

	private final Main plugin;

	public DamageHandler(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void DamageEntity(EntityDamageByEntityEvent event) {
		FileConfiguration config = plugin.config.getConfig("config");
		if(event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity) {
			Player player = (Player) event.getDamager();
            event.setDamage(event.getDamage() + destroyerDamage(player));
			event.setDamage(event.getDamage() * netherDamage(player) * seaGreedDamage(player) * endDamage(player));
            if(config.getBoolean("damageindicators")) plugin.hologramHandler.createDamageHologram(player, player.getLocation(), (LivingEntity) event.getEntity(), 20L, event.getDamage());
		}
		if(event.getDamager() instanceof Arrow && event.getEntity() instanceof LivingEntity) {
			Arrow damager = (Arrow) event.getDamager();
			if(damager.getShooter() instanceof Player) {
				Player player = (Player) damager.getShooter();
                event.setDamage(event.getDamage() + destroyerDamage(player));
                event.setDamage(event.getDamage() * netherDamage(player) * seaGreedDamage(player) * endDamage(player));
				if(config.getBoolean("damageindicators")) plugin.hologramHandler.createDamageHologram(player, damager.getLocation(), (LivingEntity) event.getEntity(), 20L, event.getDamage());
			}
		}
	}

	private Float getDestroyerBonus(PlayerInventory i, SlotType type) {
		NBTItem nbtItem = new NBTItem(getItem(i, type));
		int killAmount = nbtItem.getInteger("KillAmount");
		return killAmount / 100 > 10 ? 10f : killAmount / 100;
	}

	private Float getSlotDamage(PlayerInventory i, ArmorType armorType, SlotType slotType) {
		return switch (armorType) {
			case DESTROYER -> matchingCustomItem(getItem(i, slotType), armorType) ? getDestroyerBonus(i, slotType) : 0f;
			case NETHER, END -> matchingCustomItem(getItem(i, slotType), armorType) ? 0.1f : 0f;
			default -> 0f;
		};
	}

	private Float destroyerDamage(Player p) {
		PlayerInventory i = p.getInventory();
		return getSlotDamage(i, DESTROYER, HELMET)
				+ getSlotDamage(i, DESTROYER, CHESTPLATE)
				+ getSlotDamage(i, DESTROYER, LEGGINGS)
				+ getSlotDamage(i, DESTROYER, BOOTS);
	}

	private Float endDamage(Player p) {
		PlayerInventory i = p.getInventory();
		if(!p.getWorld().getEnvironment().equals(World.Environment.THE_END)) return 1f;
		if(!plugin.config.getConfig("config").getBoolean("endarmor.enabled")) return 1f;
		return 1 + getSlotDamage(i, END, HELMET)
				+ getSlotDamage(i, END, CHESTPLATE)
				+ getSlotDamage(i, END, LEGGINGS)
				+ getSlotDamage(i, END, BOOTS);
	}

	private Float netherDamage(Player p) {
		PlayerInventory i = p.getInventory();
		if(!p.getWorld().getEnvironment().equals(World.Environment.NETHER)) return 1f;
		if(!plugin.config.getConfig("config").getBoolean("netherarmor.enabled")) return 1f;
		return 1 + getSlotDamage(i, NETHER, HELMET)
				+ getSlotDamage(i, NETHER, CHESTPLATE)
				+ getSlotDamage(i, NETHER, LEGGINGS)
				+ getSlotDamage(i, NETHER, BOOTS);
	}

	private Float seaGreedDamage(Player p) {
		PlayerInventory inventory = p.getInventory();
		if(!p.isInWater()) return 1f;
		if(!plugin.config.getConfig("config").getBoolean("seagreedarmor.enabled")) return 1f;
		if(!plugin.IsFullCustomSet("seagreed", inventory)) return 1f;
		return 2f;
	}
}
