package me.offsetpaladin89.MoreArmors.listeners;

import java.util.ArrayList;
import java.util.UUID;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;

public class MorePotionsListener implements Listener {
//
//	private final MoreArmorsMain plugin;
//
//	public ArrayList<Player> interactcooldown  = new ArrayList<>();
//
//	public MorePotionsListener(MoreArmorsMain plugin) {
//		this.plugin = plugin;
//		plugin.getServer().getPluginManager().registerEvents(this, plugin);
//	}
//
//	@EventHandler
//	public void onPlayerInteract(PlayerInteractEvent event) {
//		Player player = event.getPlayer();
//		PlayerInventory inventory = player.getInventory();
//		if(!interactcooldown.contains(player)) {
//			if(!plugin.isAirOrNull(inventory.getItemInMainHand())) {
//				ItemStack hand = inventory.getItemInMainHand();
//				NBTItem nbtItem = new NBTItem(hand);
//				if(nbtItem.getBoolean("IsCustomItem")) {
//					if(nbtItem.getString("CustomItemType").equals("potion")) {
//						if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) { DrinkHandler(player, hand); }
//					}
//				}
//			}
//		}
//		interactcooldown.add(player);
//		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> interactcooldown.remove(player), 2L);
//	}
//
//	public void DrinkHandler(Player player, ItemStack potion) {
//		UUID id = player.getUniqueId();
//		NBTItem nbtItem = new NBTItem(potion);
//		String itemID = nbtItem.getString("CustomItemID");
//		int usesRemaining = nbtItem.getInteger("PotionUses");
//		switch(itemID) {
//		case "titan_potion":
//			if(usesRemaining != 0) {
//				if(!plugin.data.getBoolean(id + ".titan.enabled")) {
//					player.sendMessage(plugin.convertColoredString("&5(&dMorePotions&5) &dActivated &5Titan Potion&d!"));
//					TitanPotion(player, potion);
//				}
//				else {player.sendMessage(plugin.convertColoredString("&5(&dMorePotions&5) &dYou already have a &5Titan Potion &dactive!"));}
//			}
//			else {player.sendMessage(plugin.convertColoredString("&5(&dMorePotions&5) &dYour &5Titan Potion &dhas no uses remaining!"));}
//			break;
//		case "emerald_potion":
//			break;
//		}
//	}
//
//	public void TitanPotion(Player player, ItemStack potion) {
//		UUID id = player.getUniqueId();
//		NBTItem nbtItem = new NBTItem(potion);
//		Integer level = nbtItem.getInteger("PotionLevel");
//		Integer duration = nbtItem.getInteger("PotionDuration");
//		Integer uses = nbtItem.getInteger("PotionUses");
//		PotionUseUpdater(player, potion, level, duration, uses, "Titan");
//		plugin.data.set(id + ".titan.enabled", true);
//		plugin.data.set(id + ".titan.potion_level", level);
//		plugin.data.set(id + ".titan.potion_duration", duration);
//		plugin.config.saveConfig(plugin.data, "data");
//	}
//
//	public void PotionUseUpdater(Player player, ItemStack potion, Integer level, Integer duration, Integer uses, String name) {
//		PlayerInventory inventory = player.getInventory();
//		NBTItem nbtItem = new NBTItem(potion);
//		SkullMeta potionMeta = (SkullMeta) potion.getItemMeta();
//		if(uses - 1 == 0) { player.sendMessage(plugin.convertColoredString("&5(&dMorePotions&5) &dYour &5" + name + " Potion &dhas run out of uses!")); }
//		potion.setItemMeta(potionMeta);
//		nbtItem.setInteger("PotionUses", nbtItem.getInteger("PotionUses") - 1);
//		potion = nbtItem.getItem();
//		potion = UpdatePotionLore(potion, level, duration, uses - 1);
//		inventory.setItemInMainHand(potion);
//	}
//
//	public ItemStack UpdatePotionLore(ItemStack potion, Integer level, Integer duration, Integer uses) {
//		NBTItem nbtItem = new NBTItem(potion);
//		if ("titan_potion".equals(nbtItem.getString("CustomItemID"))) {
//			// potion = plugin.morepotions.titandata.addLore(potion, level, duration, uses);
//			return potion;
//		}
//		return potion;
//	}
}
