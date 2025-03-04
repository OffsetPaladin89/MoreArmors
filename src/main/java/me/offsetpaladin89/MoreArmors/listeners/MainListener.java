package me.offsetpaladin89.MoreArmors.listeners;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class MainListener implements Listener {

	public MoreArmorsMain plugin;

	public MainListener(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(plugin.isCustomItem(event.getItemInHand())) event.setCancelled(true);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		PlayerInventory inv = p.getInventory();
		if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;
		ItemStack item = inv.getItemInMainHand();
		MaterialType type = NBT.get(item, nbt -> (MaterialType) nbt.getEnum("MaterialID", MaterialType.class));
		if(type == null) return;
		if(type.equals(MaterialType.EYE_OF_ENDER_0) || type.equals(MaterialType.EYE_OF_ENDER_1)) event.setCancelled(true);
	}

	@EventHandler
	public void preCraftEvent(PrepareItemCraftEvent event) {
		CraftingInventory inventory = event.getInventory();
		ItemStack result = inventory.getResult();

		if (plugin.isAirOrNull(result)) return;
		ItemStack[] craftingMatrix = inventory.getMatrix();

		if (plugin.isCustomItem(result)) return;
		for (ItemStack item : craftingMatrix) {
			if (plugin.isAirOrNull(item)) continue;
			if (plugin.isCustomItem(item)) inventory.setResult(null);
		}
	}
}