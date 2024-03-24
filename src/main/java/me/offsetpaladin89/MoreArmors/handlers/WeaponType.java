package me.offsetpaladin89.MorePluginsCore.handlers;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum WeaponType {
	SWORD, BOW, DAGGER, BATTLEAXE;
	
	public static WeaponType matchType(ItemStack item) {
		if(isAirOrNull(item)) {return null;}
		NBTItem nbti = new NBTItem(item);
		return switch (nbti.getString("WeaponType")) {
			case "sword" -> SWORD;
			case "bow" -> BOW;
			case "dagger" -> DAGGER;
			case "battleaxe" -> BATTLEAXE;
			default -> null;
		};
	}
	
	private static boolean isAirOrNull(ItemStack item) { return item == null || item.getType().equals(Material.AIR); }
}
