package me.offsetpaladin89.MoreArmors.listeners;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class MainListener implements Listener {
	
	public MoreArmorsMain plugin;
	
	public MainListener(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		NBTItem nbtItem = new NBTItem(event.getItemInHand());
		if(nbtItem.getBoolean("IsCustomItem")) {
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void InventoryClick(InventoryClickEvent event) {
		if(event.getClickedInventory() != null) {
			if(event.getClickedInventory().getType().equals(InventoryType.ANVIL)) {
				if(!plugin.isAirOrNull(event.getClickedInventory().getItem(2))) {
					NBTItem nbtItem = new NBTItem(event.getClickedInventory().getItem(0));
					if(nbtItem.getBoolean("IsCustomItem")) {
						if(!event.getClickedInventory().getItem(0).getItemMeta().getDisplayName().equals(event.getClickedInventory().getItem(2).getItemMeta().getDisplayName())) {
							if(event.getSlot() == 2) {
								event.setCancelled(true);
							}
						}
					}
				}
			}
			if(event.getClickedInventory().getType().equals(InventoryType.GRINDSTONE) || event.getClickedInventory().getType().equals(InventoryType.BLAST_FURNACE) || event.getClickedInventory().getType().equals(InventoryType.FURNACE) || event.getClickedInventory().getType().equals(InventoryType.MERCHANT)) {
				NBTItem nbtItem = new NBTItem(event.getCursor());
				if(nbtItem.getBoolean("IsCustomItem")) { event.setCancelled(true); }
			}
			if(event.getInventory().getType().equals(InventoryType.GRINDSTONE) || event.getInventory().getType().equals(InventoryType.BLAST_FURNACE) || event.getInventory().getType().equals(InventoryType.FURNACE)) {
				if(event.isShiftClick()) {
					NBTItem nbtItem = new NBTItem(event.getCurrentItem());
					if(nbtItem.getBoolean("IsCustomItem")) { event.setCancelled(true); }
				}
			}
		}
	}
}