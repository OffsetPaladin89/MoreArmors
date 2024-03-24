package me.offsetpaladin89.MoreArmors.listeners;

import com.cryptomorin.xseries.XSound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.GameMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

public class MoreArmorsExtraListener implements Listener {
//
//	public MoreArmorsMain plugin;
//	private ArrayList<Player> cooldown = new ArrayList<Player>();
//
//	public MoreArmorsExtraListener(MoreArmorsMain plugin) {
//		this.plugin = plugin;
//		plugin.getServer().getPluginManager().registerEvents(this, plugin);
//	}
//
//	@EventHandler
//	public void DamageEntity(EntityDamageByEntityEvent event) {
//		if(event.getEntity() instanceof Player) {
//			Player player = (Player) event.getEntity();
//			PlayerInventory inventory = player.getInventory();
//			if(plugin.isAirOrNull(inventory.getChestplate())) { return; }
//			NBTItem nbtItem = new NBTItem(inventory.getChestplate());
//			if(nbtItem.getString("CustomItemID").equals("destroyer")) {
//				Random random = new Random();
//				int number = random.nextInt(5) + 1;
//				if(number == 1) {event.setDamage(event.getDamage() / 2);}
//			}
//		}
//	}
//
//	@EventHandler
//	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
//		Player player = event.getPlayer();
//		PlayerInventory inventory = player.getInventory();
//		if(plugin.isAirOrNull(inventory.getBoots())) { return; }
//		NBTItem nbtItem = new NBTItem(inventory.getBoots());
//		if(nbtItem.getString("CustomItemID").equals("destroyer")) {
//			event.setCancelled(true);
//			player.setFlying(false);
//			player.setAllowFlight(false);
//			player.playSound(player.getLocation(), XSound.ENTITY_WITHER_BREAK_BLOCK.parseSound(), 0.8f, 1f);
//
//			Vector unitVector = new Vector(player.getLocation().getDirection().getX(), 0, player.getLocation().getDirection().getZ());
//			unitVector = unitVector.normalize();
//			player.setVelocity(unitVector.multiply(1.2));
//			player.setFallDistance(0);
//			player.getLocation().getWorld().createExplosion(player.getLocation(), 5, false, false, player);
//		}
//	}
//
//	@EventHandler
//	public void onPlayerExplode(EntityExplodeEvent event) {
//		if(event.getEntity() instanceof Player) {
//			Player player = (Player) event.getEntity();
//			PlayerInventory inventory = player.getInventory();
//			if(plugin.isAirOrNull(inventory.getBoots())) { return; }
//			NBTItem nbtItem = new NBTItem(inventory.getBoots());
//			if(nbtItem.getString("CustomItemID").equals("destroyer")) { event.setCancelled(true); }
//		}
//	}
//
//	@EventHandler
//	public void onPlayerMove(PlayerMoveEvent event) {
//		Player player = event.getPlayer();
//		PlayerInventory inventory = player.getInventory();
//		if(plugin.isAirOrNull(inventory.getBoots())) { return; }
//		NBTItem nbtItem = new NBTItem(inventory.getBoots());
//		if(!(player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR))) {
//			if(nbtItem.getString("CustomItemID").equals("destroyer")) {
//				if(!cooldown.contains(player)) {
//					player.setAllowFlight(true);
//					cooldown.add(player);
//					new BukkitRunnable() {public void run() { cooldown.remove(player); }}.runTaskLater(plugin, 20);
//				}
//			}
//		}
//	}
//
//	@EventHandler
//	public void onPlayerRespawn(PlayerRespawnEvent event) {
//		Player player = event.getPlayer();
//		if(TextHandler.getPlugin("MoreArmors") != null) {
//			if(plugin.endarmor.contains(player)) {plugin.endarmor.remove(player);}
//			if(plugin.netherarmor.contains(player)) {plugin.netherarmor.remove(player);}
//		}
//		if(TextHandler.getPlugin("MoreArmorsExtra") != null) {
//			if(plugin.destroyerarmor.contains(player)) {plugin.destroyerarmor.remove(player);}
//			if(plugin.destroyerhelmet.contains(player)) {plugin.destroyerhelmet.remove(player);}
//		}
//	}
//
//	@EventHandler
//	public void onKill(EntityDeathEvent event) {
//		LivingEntity deadentity = event.getEntity();
//		if(deadentity.getKiller() instanceof Player) {
//			Player player = deadentity.getKiller();
//			PlayerInventory inventory = player.getInventory();
//	    	ItemStack helmet = inventory.getHelmet();
//	    	ItemStack chestplate = inventory.getChestplate();
//	    	ItemStack leggings = inventory.getLeggings();
//	    	ItemStack boots = inventory.getBoots();
//	    	NBTItem nbtHelmet = new NBTItem(helmet);
//			NBTItem nbtChestplate = new NBTItem(chestplate);
//			NBTItem nbtLeggings = new NBTItem(leggings);
//			NBTItem nbtBoots = new NBTItem(boots);
//
//			if(!(deadentity instanceof Player)) {
//		    	if(!(plugin.isAirOrNull(helmet) || plugin.isAirOrNull(chestplate) || plugin.isAirOrNull(leggings) || plugin.isAirOrNull(boots))) {
//					if(nbtHelmet.getString("CustomItemID").equals("destroyer")) { inventory.setHelmet(IncreaseKills(helmet, 3, EquipmentSlot.HEAD)); }
//					if(nbtChestplate.getString("CustomItemID").equals("destroyer")) { inventory.setChestplate(IncreaseKills(chestplate, 8, EquipmentSlot.CHEST)); }
//					if(nbtLeggings.getString("CustomItemID").equals("destroyer")) { inventory.setLeggings(IncreaseKills(leggings, 6, EquipmentSlot.LEGS)); }
//					if(nbtBoots.getString("CustomItemID").equals("destroyer")) { inventory.setBoots(IncreaseKills(boots, 3, EquipmentSlot.FEET)); }
//				}
//			}
//		}
//	}
	
//	public ItemStack IncreaseKills(ItemStack item, Integer armor, EquipmentSlot slot) {
//		NBTItem nbtItem = new NBTItem(item);
//		nbtItem.setInteger("Kills", nbtItem.getInteger("Kills") + 1);
//		item = nbtItem.getItem();
//		// item = plugin.morearmorsextra.destroyerdata.addLore(item, nbtItem.getInteger("Kills"), ArmorType.matchType(item));
//		if(Math.floorMod(nbtItem.getInteger("Kills"), 250) == 0) { item = plugin.morearmorsextra.destroyerdata.addAttributes(item, armor, slot, nbtItem.getInteger("Kills")); }
//		return item;
//	}
}
