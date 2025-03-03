package me.offsetpaladin89.MoreArmors.handlers;

import com.cryptomorin.xseries.XSound;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import org.bukkit.World.Environment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
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

	private final MoreArmorsMain plugin;

	public DamageHandler(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void DamageEntity(EntityDamageByEntityEvent event) {
		FileConfiguration config = plugin.configHandler.getConfig("config");
		if(event.getDamager() instanceof Player player && event.getEntity() instanceof LivingEntity) {
            event.setDamage(event.getDamage() + destroyerDamage(player));
            event.setDamage(event.getDamage() * netherDamage(player, player.getWorld().getEnvironment()) * seaGreedDamage(player) * endDamage(player, player.getWorld().getEnvironment()));
            if(config.getBoolean("damageindicators")) plugin.hologramHandler.createDamageHologram(player, player.getLocation(), (LivingEntity) event.getEntity(), 20L, event.getDamage());
		}
		if(event.getDamager() instanceof Arrow damager && event.getEntity() instanceof LivingEntity) {
			if(damager.getShooter() instanceof Player player) {
                event.setDamage(event.getDamage() + destroyerDamage(player));
                event.setDamage(event.getDamage() * netherDamage(player, player.getWorld().getEnvironment()) * seaGreedDamage(player) * endDamage(player, player.getWorld().getEnvironment()));
				if(config.getBoolean("damageindicators")) plugin.hologramHandler.createDamageHologram(player, damager.getLocation(), (LivingEntity) event.getEntity(), 20L, event.getDamage());
			}
		}
	}

	public Float destroyerDamage(Player p) {
		if(!plugin.configHandler.getConfig("config").getBoolean("destroyerarmor.enabled")) return 0f;
		PlayerInventory inv = p.getInventory();
		int helmetBonus = NBT.get(inv.getHelmet(), nbt -> (int) nbt.getInteger("DamageBonus"));
		int chestplateBonus = NBT.get(inv.getHelmet(), nbt -> (int) nbt.getInteger("DamageBonus"));
		int leggingsBonus = NBT.get(inv.getHelmet(), nbt -> (int) nbt.getInteger("DamageBonus"));
		int bootsBonus = NBT.get(inv.getHelmet(), nbt -> (int) nbt.getInteger("DamageBonus"));

		return (float) (helmetBonus + chestplateBonus + leggingsBonus + bootsBonus);
	}

    public Float netherDamage(Player p, Environment env) {
        PlayerInventory inventory = p.getInventory();
        return env.equals(Environment.NETHER) && plugin.configHandler.getConfig("config").getBoolean("netherarmor.enabled") ? (plugin.IsFullCustomSet(ArmorType.NETHER, inventory) ? 1f : 0f) +
                (plugin.matchingCustomItem(inventory.getHelmet(), ArmorType.NETHER) ? 0.1f : 0f) +
                (plugin.matchingCustomItem(inventory.getChestplate(), ArmorType.NETHER) ? 0.1f : 0f) +
                (plugin.matchingCustomItem(inventory.getLeggings(), ArmorType.NETHER) ? 0.1f : 0f) +
                (plugin.matchingCustomItem(inventory.getBoots(), ArmorType.NETHER) ? 0.1f : 0f) + 1f : 1f;
    }

	public Float endDamage(Player p, Environment env) {
		PlayerInventory inventory = p.getInventory();
		return env.equals(Environment.THE_END) && plugin.configHandler.getConfig("config").getBoolean("endarmor.enabled") ? (plugin.IsFullCustomSet(ArmorType.END, inventory) ? 1f : 0f) +
				(plugin.matchingCustomItem(inventory.getHelmet(), ArmorType.END) ? 0.1f : 0f) +
				(plugin.matchingCustomItem(inventory.getChestplate(), ArmorType.END) ? 0.1f : 0f) +
				(plugin.matchingCustomItem(inventory.getLeggings(), ArmorType.END) ? 0.1f : 0f) +
				(plugin.matchingCustomItem(inventory.getBoots(), ArmorType.END) ? 0.1f : 0f) + 1f : 1f;
	}

	public Float seaGreedDamage(Player p) {
		PlayerInventory inventory = p.getInventory();
		return p.isInWater() && plugin.configHandler.getConfig("config").getBoolean("seagreedarmor.enabled") ? (plugin.IsFullCustomSet(ArmorType.SEA_GREED, inventory) ? 1f : 0f) + 1f : 1f;
	}
}
