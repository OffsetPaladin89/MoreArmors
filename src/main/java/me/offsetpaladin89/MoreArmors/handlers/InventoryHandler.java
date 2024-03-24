package me.offsetpaladin89.MoreArmors.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import dev.dbassett.skullcreator.SkullCreator;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class InventoryHandler {
//
//	private final MoreArmorsMain plugin;
//
//	public InventoryHandler(MoreArmorsMain plugin) { this.plugin = plugin; }
//
//	public HashMap<Player, Inventory> statsInventory = new HashMap<>();
//
//	public ItemStack healthDisplayItem(Player player) {
//		float overallHealth = plugin.stathandler.basestats.get("health") + plugin.stathandler.additionalstats.get("health");
//		ItemStack item = new ItemStack(Material.APPLE, 1);
//		ItemMeta itemmeta = item.getItemMeta();
//		ArrayList<String> lore = new ArrayList<>();
//		itemmeta.setDisplayName(plugin.convertColoredString("&cHealth &f" + overallHealth));
//		lore.add(plugin.convertColoredString("&cHealth &7is your maximum health."));
//		lore.add("");
//		lore.add(plugin.convertColoredString("&7Base Health: &c" + plugin.stathandler.basestats.get("health")));
//		lore.add(plugin.convertColoredString("&7Bonus Health: &c+" + plugin.stathandler.additionalstats.get("health")));
//		itemmeta.setLore(lore);
//		item.setItemMeta(itemmeta);
//		return item;
//	}
//
//	public ItemStack defenseDisplayItem(Player player) {
//		float overallMagicDefense = plugin.stathandler.basestats.get("defense") + plugin.stathandler.additionalstats.get("defense");
//		String damageReduction = ((float) Math.round((overallMagicDefense / (overallMagicDefense + 100) * 10000)) / 100) + "%";
//		ItemStack item = new ItemStack(Material.IRON_CHESTPLATE, 1);
//		ItemMeta itemmeta = item.getItemMeta();
//		ArrayList<String> lore = new ArrayList<>();
//		itemmeta.setDisplayName(plugin.convertColoredString("&aDefense &f" + overallMagicDefense));
//		lore.add(plugin.convertColoredString("&aDefense &7reduces damage taken from"));
//		lore.add(plugin.convertColoredString("&7all &cphysical damage &7sources."));
//		lore.add("");
//		lore.add(plugin.convertColoredString("&7Base Defense: &c" + plugin.stathandler.basestats.get("defense")));
//		lore.add(plugin.convertColoredString("&7Bonus Defense: &c+" + plugin.stathandler.additionalstats.get("defense")));
//		lore.add("");
//		lore.add(plugin.convertColoredString("&7Damage Reduction: &a" + damageReduction));
//		itemmeta.setLore(lore);
//		itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//		item.setItemMeta(itemmeta);
//		return item;
//	}
//
//	public ItemStack magicDefenseDisplayItem(Player player) {
//		float overallDefense = plugin.stathandler.basestats.get("magicDefense") + plugin.stathandler.additionalstats.get("magicDefense");
//		String damageReduction = ((float) Math.round((overallDefense / (overallDefense + 100) * 10000)) / 100) + "%";
//		ItemStack item = new ItemStack(Material.MAGMA_CREAM, 1);
//		ItemMeta itemmeta = item.getItemMeta();
//		ArrayList<String> lore = new ArrayList<>();
//		itemmeta.setDisplayName(plugin.convertColoredString("&dMagical Defense &f" + overallDefense));
//		lore.add(plugin.convertColoredString("&dMagical Defense &7reduces damage taken from"));
//		lore.add(plugin.convertColoredString("&7all &cmagical damage &7sources."));
//		lore.add("");
//		lore.add(plugin.convertColoredString("&7Base Magical Defense: &c" + plugin.stathandler.basestats.get("magicDefense")));
//		lore.add(plugin.convertColoredString("&7Bonus Magica; Defense: &c+" + plugin.stathandler.additionalstats.get("magicDefense")));
//		lore.add("");
//		lore.add(plugin.convertColoredString("&7Damage Reduction: &a" + damageReduction));
//		lore.add("");
//		lore.add(plugin.convertColoredString("&f&lCOMING SOON"));
//		itemmeta.setLore(lore);
//		item.setItemMeta(itemmeta);
//		return item;
//	}
//
//	public ItemStack attackDamageDisplayItem(Player player) {
//		float overallAttackDamage = plugin.stathandler.basestats.get("attackDamage") + plugin.stathandler.additionalstats.get("attackDamage");
//		ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
//		ItemMeta itemmeta = item.getItemMeta();
//		ArrayList<String> lore = new ArrayList<>();
//		itemmeta.setDisplayName(plugin.convertColoredString("&cPhysical Damage &f" + overallAttackDamage));
//		lore.add(plugin.convertColoredString("&cPhysical Damage &7is how much &cphysical"));
//		lore.add(plugin.convertColoredString("&cdamage &7you deal."));
//		lore.add("");
//		lore.add(plugin.convertColoredString("&7Base Physical Damage: &c" + plugin.stathandler.basestats.get("attackDamage")));
//		lore.add(plugin.convertColoredString("&7Bonus Physical Damage: &c+" + plugin.stathandler.additionalstats.get("attackDamage")));
//		itemmeta.setLore(lore);
//		itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//		item.setItemMeta(itemmeta);
//		return item;
//	}
//
//	public ItemStack magicDamageDisplayItem(Player player) {
//		float overallMagicDamage = plugin.stathandler.basestats.get("magicDamage") + plugin.stathandler.additionalstats.get("magicDamage");
//		ItemStack item = new ItemStack(Material.POTION, 1);
//		PotionMeta itemmeta = (PotionMeta) item.getItemMeta();
//		ArrayList<String> lore = new ArrayList<>();
//		itemmeta.setDisplayName(plugin.convertColoredString("&dMagical Damage &f" + overallMagicDamage));
//		lore.add(plugin.convertColoredString("&dMagical Damage &7is how much &cmagical"));
//		lore.add(plugin.convertColoredString("&cdamage &7you deal."));
//		lore.add("");
//		lore.add(plugin.convertColoredString("&7Base Magical Damage: &c" + plugin.stathandler.basestats.get("magicDamage")));
//		lore.add(plugin.convertColoredString("&7Bonus Magical Damage: &c+" + plugin.stathandler.additionalstats.get("magicDamage")));
//		lore.add("");
//		lore.add(plugin.convertColoredString("&f&lCOMING SOON"));
//		itemmeta.setLore(lore);
//		itemmeta.setColor(Color.fromRGB(230, 0, 230));
//		itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//		item.setItemMeta(itemmeta);
//		return item;
//	}
//
//	public ItemStack attackSpeedDisplayItem(Player player) {
//		float overallAttackSpeed = plugin.stathandler.basestats.get("attackSpeed") + plugin.stathandler.additionalstats.get("attackSpeed");
//		ItemStack item = new ItemStack(Material.GOLDEN_PICKAXE, 1);
//		ItemMeta itemmeta = item.getItemMeta();
//		ArrayList<String> lore = new ArrayList<>();
//		String attackSpeedPercentage = ((float) Math.round(overallAttackSpeed * 2500) / 100) + "%";
//		itemmeta.setDisplayName(plugin.convertColoredString("&eAttack Speed &f" + attackSpeedPercentage));
//		lore.add(plugin.convertColoredString("&eAttack Speed &7is how fast you can attack."));
//		lore.add("");
//		lore.add(plugin.convertColoredString("&7Base Attack Speed: &c" + ((float) Math.round(plugin.stathandler.basestats.get("attackSpeed") * 2500) / 100) + "%"));
//		if(plugin.stathandler.additionalstats.get("attackSpeed") < 0) {lore.add(plugin.convertColoredString("&7Bonus Attack Speed: &c" + ((float) Math.round(plugin.stathandler.additionalstats.get("attackSpeed") * 2500) / 100) + "%"));}
//		else {lore.add(plugin.convertColoredString("&7Bonus Attack Speed: &c+" + ((float) Math.round(plugin.stathandler.additionalstats.get("attackSpeed") * 2500) / 100) + "%"));}
//		itemmeta.setLore(lore);
//		itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//		item.setItemMeta(itemmeta);
//		return item;
//	}
//
//	public ItemStack criticalHitChanceDisplayItem(Player player) {
//		float overallCriticalHitChance = plugin.stathandler.basestats.get("criticalHitChance") + plugin.stathandler.additionalstats.get("criticalHitChance");
//		ItemStack item = SkullCreator.itemFromUrl("969da4e7b8d558af02e80e2e1669ceb6084898db55907ef3e66d9a329329e941");
//		ItemMeta itemmeta = item.getItemMeta();
//		ArrayList<String> lore = new ArrayList<>();
//		itemmeta.setDisplayName(plugin.convertColoredString("&9Critical Hit Chance &f" + overallCriticalHitChance + "%"));
//		lore.add(plugin.convertColoredString("&9Critical Hit Chance &7is your chance to"));
//		lore.add(plugin.convertColoredString("&7deal &cextra damage&7."));
//		lore.add("");
//		lore.add(plugin.convertColoredString("&7Base Critical Hit Chance: &c" + plugin.stathandler.basestats.get("criticalHitChance") + "%"));
//		lore.add(plugin.convertColoredString("&7Bonus Critical Hit Chance: &c+" + plugin.stathandler.additionalstats.get("criticalHitChance") + "%"));
//		itemmeta.setLore(lore);
//		item.setItemMeta(itemmeta);
//		return item;
//	}
//
//	public ItemStack criticalHitDamageDisplayItem(Player player) {
//		float overallCriticalHitDamage = plugin.stathandler.basestats.get("criticalHitDamage") + plugin.stathandler.additionalstats.get("criticalHitDamage");
//		ItemStack item = SkullCreator.itemFromUrl("3aa4a7bb2cb88c7d11ee1adfa40d9acd75d8fb06b11a4969ce0715cf98e0c5ca");
//		ItemMeta itemmeta = item.getItemMeta();
//		ArrayList<String> lore = new ArrayList<>();
//		itemmeta.setDisplayName(plugin.convertColoredString("&9Critical Hit Damage &f" + overallCriticalHitDamage + "%"));
//		lore.add(plugin.convertColoredString("&9Critical Hit Damage &7is how much more"));
//		lore.add(plugin.convertColoredString("&7damage your &cCritical Hit &7deals."));
//		lore.add("");
//		lore.add(plugin.convertColoredString("&7Base Critical Hit Damage: &c" + plugin.stathandler.basestats.get("criticalHitDamage") + "%"));
//		lore.add(plugin.convertColoredString("&7Bonus Critical Hit Damage: &c+" + plugin.stathandler.additionalstats.get("criticalHitDamage") + "%"));
//		itemmeta.setLore(lore);
//		item.setItemMeta(itemmeta);
//		return item;
//	}
//
//	public void viewStatsInventory(Integer length, CommandSender sender, String name) {
//		Player player = null;
//
//		switch(length) {
//		case 1: if(sender instanceof Player) {player = plugin.getServer().getPlayerExact(sender.getName());} break;
//		case 2:
//			player = plugin.getServer().getPlayerExact(name);
//			if(player == null) {return;}
//			break;
//		}
//		statsInventory(player);
//		player.openInventory(statsInventory.get(player));
//	}
//
//	public void updateSlots(Player player, Inventory inventory) {
//		inventory.setItem(13, healthDisplayItem(player));
//		inventory.setItem(21, defenseDisplayItem(player));
//		inventory.setItem(22, magicDefenseDisplayItem(player));
//		inventory.setItem(23, attackDamageDisplayItem(player));
//		inventory.setItem(29, magicDamageDisplayItem(player));
//		inventory.setItem(30, attackSpeedDisplayItem(player));
//		inventory.setItem(32, criticalHitChanceDisplayItem(player));
//		inventory.setItem(33, criticalHitDamageDisplayItem(player));
//	}
//
//	public void statsInventory(Player player) {
//		Inventory inventory = plugin.getServer().createInventory(null, 45, plugin.convertColoredString("&8MorePlugins Stats Menu"));
//		for(int x = 0; x < 45; x++) {
//			ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
//			ItemMeta meta = item.getItemMeta();
//			meta.setDisplayName(" ");
//			item.setItemMeta(meta);
//			inventory.setItem(x, item);
//		}
//		updateSlots(player, inventory);
//		statsInventory.put(player, inventory);
//	}
}
