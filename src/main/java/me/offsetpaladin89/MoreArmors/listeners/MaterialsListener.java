package me.offsetpaladin89.MoreArmors.listeners;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

public class MaterialsListener implements Listener {

	private final MoreArmorsMain plugin;

	public MaterialsListener(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(!plugin.isAirOrNull(inventory.getItemInMainHand())) {
				NBTItem nbtItem = new NBTItem(inventory.getItemInMainHand());
				if(nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender")) { event.setCancelled(true); }
			}
		}
	}
}
