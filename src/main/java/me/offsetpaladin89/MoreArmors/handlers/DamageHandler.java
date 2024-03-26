package me.offsetpaladin89.MoreArmors.handlers;

import com.cryptomorin.xseries.XSound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.World.Environment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;

public class DamageHandler implements Listener {

	public static MoreArmorsMain plugin;

	public DamageHandler(MoreArmorsMain plugin) {
		DamageHandler.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void DamageEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			plugin.sendPlayerMessage(player, "&5Destroyer Damage Bonus : " + destroyerDamage(player));
            event.setDamage(event.getDamage() + destroyerDamage(player));
            event.setDamage(event.getDamage() * netherDamage(player, player.getWorld().getEnvironment()) * seaGreedDamage(player) * endDamage(player, player.getWorld().getEnvironment()));
            plugin.hologramHandler.createDamageHologram(player.getLocation(), event.getEntity().getLocation(), 20L, event.getDamage());
		}
		if(event.getDamager() instanceof Arrow) {
			Arrow damager = (Arrow) event.getDamager();
			if(damager.getShooter() instanceof Player) {
				Player player = (Player) damager.getShooter();
                event.setDamage(event.getDamage() + destroyerDamage(player));
                event.setDamage(event.getDamage() * netherDamage(player, player.getWorld().getEnvironment()) * seaGreedDamage(player) * endDamage(player, player.getWorld().getEnvironment()));
				plugin.hologramHandler.createDamageHologram(damager.getLocation(), event.getEntity().getLocation(), 20L, event.getDamage());
			}
		}
	}

	public Float destroyerDamage(Player p) {
		PlayerInventory inv = p.getInventory();
		return (plugin.matchingCustomItem(inv.getHelmet(), "destroyer") ? (new NBTItem(inv.getHelmet()).getInteger("KillAmount") / 100 > 10 ? 10f : new NBTItem(inv.getHelmet()).getInteger("KillAmount") / 100) : 0f) +
				(plugin.matchingCustomItem(inv.getChestplate(), "destroyer") ? (new NBTItem(inv.getChestplate()).getInteger("KillAmount") / 100 > 10 ? 10f : new NBTItem(inv.getChestplate()).getInteger("KillAmount") / 100) : 0f) +
				(plugin.matchingCustomItem(inv.getLeggings(), "destroyer") ? (new NBTItem(inv.getLeggings()).getInteger("KillAmount") / 100 > 10 ? 10f : new NBTItem(inv.getLeggings()).getInteger("KillAmount") / 100) : 0f) +
				(plugin.matchingCustomItem(inv.getBoots(), "destroyer") ? (new NBTItem(inv.getBoots()).getInteger("KillAmount") / 100 > 10 ? 10f : new NBTItem(inv.getBoots()).getInteger("KillAmount") / 100) : 0f);
	}

    public Float netherDamage(Player p, Environment env) {
        PlayerInventory inventory = p.getInventory();
        return env.equals(Environment.NETHER) ? (plugin.IsFullCustomSet("nether", inventory) ? 1f : 0f) +
                (plugin.matchingCustomItem(inventory.getHelmet(), "nether") ? 0.1f : 0f) +
                (plugin.matchingCustomItem(inventory.getChestplate(), "nether") ? 0.1f : 0f) +
                (plugin.matchingCustomItem(inventory.getLeggings(), "nether") ? 0.1f : 0f) +
                (plugin.matchingCustomItem(inventory.getBoots(), "nether") ? 0.1f : 0f) + 1f : 1f;
    }

	public Float endDamage(Player p, Environment env) {
		PlayerInventory inventory = p.getInventory();
		return env.equals(Environment.THE_END) ? (plugin.IsFullCustomSet("end", inventory) ? 1f : 0f) +
				(plugin.matchingCustomItem(inventory.getHelmet(), "end") ? 0.1f : 0f) +
				(plugin.matchingCustomItem(inventory.getChestplate(), "end") ? 0.1f : 0f) +
				(plugin.matchingCustomItem(inventory.getLeggings(), "end") ? 0.1f : 0f) +
				(plugin.matchingCustomItem(inventory.getBoots(), "end") ? 0.1f : 0f) + 1f : 1f;
	}

	public Float seaGreedDamage(Player p) {
		PlayerInventory inventory = p.getInventory();
		return p.isInWater() ? (plugin.IsFullCustomSet("seagreed", inventory) ? 1f : 0f) + 1f : 1f;
	}
}
