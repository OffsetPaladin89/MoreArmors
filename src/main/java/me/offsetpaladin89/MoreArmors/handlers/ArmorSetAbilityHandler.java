package me.offsetpaladin89.MoreArmors.handlers;

import me.offsetpaladin89.MoreArmors.enums.StatType;
import me.offsetpaladin89.MoreArmors.utils.stats.BaseStats;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class ArmorSetAbilityHandler {

	public static void scanPlayers(Object[] players) {
		for (Object obj : players) {
			if (obj instanceof Player p) {
				BaseStats stats = BaseStats.getStats(p.getInventory().getArmorContents(), p);

				p.getAttribute(Attribute.SCALE).setBaseValue(1 + stats.getStat(p, StatType.P_SCALE));
				p.addPotionEffects(stats.getPotionEffects(p));
			}
		}
	}
}