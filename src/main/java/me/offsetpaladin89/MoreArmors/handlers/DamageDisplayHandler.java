package me.offsetpaladin89.MoreArmors.handlers;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.Location;

import java.text.DecimalFormat;

public record DamageDisplayHandler(MoreArmorsMain plugin) {
	public void displayDamage(Float baseDamage, Float strength, Float criticalchance, Float criticaldamage, Location location) {
		Hologram hologram = HologramsAPI.createHologram(plugin, location);
		if (isCritical(criticalchance)) {
			Float damage = calculateDamage(baseDamage, strength, true, criticaldamage);
			String formattedString = "&e" + new DecimalFormat("#").format(damage) + "✦";
			hologram.appendTextLine(plugin.convertColoredString(formattedString));
		} else {
			Float damage = calculateDamage(baseDamage, strength, false, criticaldamage);
			String formattedString = "&7" + new DecimalFormat("#").format(damage);
			hologram.appendTextLine(plugin.convertColoredString(formattedString));
		}
	}


	public Float calculateDamage(Float damage, Float strength, Boolean isCritical, Float criticaldamage) {
		float basedamage = (damage + strength / 5) * (1 + strength / 100);
		if (isCritical) {
			return basedamage * (1 + criticaldamage / 100);
		}
		return basedamage;
	}

	public boolean isCritical(Float criticalchance) {
		return Math.random() * 100 <= criticalchance;
	}
}
