package me.offsetpaladin89.MoreArmors.listeners;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

import static org.bukkit.event.inventory.InventoryType.*;

public class MainListener implements Listener {

	public MoreArmorsMain plugin;

	private final InventoryType[] blockedInventories = {GRINDSTONE, BLAST_FURNACE, FURNACE, MERCHANT};

	public MainListener(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		NBTItem nbtItem = new NBTItem(event.getItemInHand());
		if (nbtItem.getBoolean("IsCustomItem")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (!plugin.isAirOrNull(inventory.getItemInMainHand())) {
				NBTItem nbtItem = new NBTItem(inventory.getItemInMainHand());
				if (nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender")) {
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void InventoryClick(InventoryClickEvent event) {
		if (event.getClickedInventory() != null) {
			if (event.isShiftClick()) {
				Inventory inventory = event.getInventory();
				anvilClick(inventory, event);
				for (InventoryType i : blockedInventories) {
					if (plugin.isAirOrNull(event.getCurrentItem())) return;
					NBTItem nbtItem = new NBTItem(event.getCurrentItem());
					if (inventory.getType().equals(i) && nbtItem.getBoolean("IsCustomItem")) {
						event.setCancelled(true);
					}
				}
			} else {
				Inventory inventory = event.getClickedInventory();
				anvilClick(inventory, event);
				for (InventoryType i : blockedInventories) {
					if (plugin.isAirOrNull(event.getCursor())) return;
					NBTItem nbtItem = new NBTItem(event.getCursor());
					if (inventory.getType().equals(i) && nbtItem.getBoolean("IsCustomItem")) {
						event.setCancelled(true);
					}
				}
			}
		}
	}

	public void anvilClick(Inventory inventory, InventoryClickEvent event) {
		if (inventory.getType().equals(ANVIL) && event.getSlot() == 2) {
			if (!plugin.isAirOrNull(inventory.getItem(2))) {
				if (!inventory.getItem(0).getItemMeta().getDisplayName().equals(inventory.getItem(2).getItemMeta().getDisplayName())) {
					NBTItem nbtItem = new NBTItem(inventory.getItem(0));
					if (nbtItem.getBoolean("IsCustomItem")) {
						event.setCancelled(true);
					}
				}
			}
		}
	}
}