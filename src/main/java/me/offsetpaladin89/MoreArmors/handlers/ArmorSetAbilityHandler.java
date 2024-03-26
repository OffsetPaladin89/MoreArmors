package me.offsetpaladin89.MoreArmors.handlers;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.checkerframework.checker.units.qual.N;

import java.util.UUID;

public class ArmorSetAbilityHandler {

	private static MoreArmorsMain plugin;

	public ArmorSetAbilityHandler(MoreArmorsMain plugin) {
		ArmorSetAbilityHandler.plugin = plugin;
	}

	public void scanPlayers(Object[] players) {
		for (Object obj : players) {
			if (obj instanceof Player) {
				Player p = (Player) obj;
				PlayerInventory inv = p.getInventory();
				World w = p.getLocation().getWorld();
				if (plugin.IsFullCustomSet("nether", p.getInventory()) && w.getEnvironment().equals(Environment.NETHER)) {
					setHelmetHealth(inv, 20);
					p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 60, 0, false, false));
				} else if (plugin.IsFullCustomSet("end", p.getInventory()) && w.getEnvironment().equals(Environment.THE_END)) {
					setHelmetHealth(inv, 20);
				} else if (plugin.IsFullCustomSet("seagreed", p.getInventory()) && inWater(p)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 60, 0, false, false));
				} else if (plugin.IsFullCustomSet("destroyer", p.getInventory())) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 1, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60, 1, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 1, false, false));
				}
				else if (!plugin.isAirOrNull(inv.getHelmet())) {
					NBTItem nbt = new NBTItem(inv.getHelmet());
					if(nbt.getString("CustomItemID").equals("destroyer")) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 60, 0, false, false));
					}
				} else {
					ItemStack i = inv.getHelmet();
					if (!plugin.isAirOrNull(i)) {
						NBTItem nbt = new NBTItem(i);
						String cID = nbt.getString("CustomItemID");
						if (cID.equals("nether") || cID.equals("end")) {
							ItemMeta im = i.getItemMeta();
							im.removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH);
							i.setItemMeta(im);
							inv.setHelmet(i);
						}
					}
				}
			}

		}
	}

	public void setHelmetHealth(PlayerInventory inv, Integer amount) {
		ItemStack effectItem = inv.getHelmet();
		ItemMeta im = effectItem.getItemMeta();
		if (im.getAttributeModifiers(Attribute.GENERIC_MAX_HEALTH) == null)
			im.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "maxHealth", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		effectItem.setItemMeta(im);
		inv.setHelmet(effectItem);
	}

	private boolean inWater(Player p) {
		return p.getLocation().getBlock().getType().equals(Material.WATER);
	}

	public static void DestroyerArmor(Player player) {
		PlayerInventory inventory = player.getInventory();
		if (plugin.isAirOrNull(inventory.getHelmet())) {
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			plugin.destroyerhelmet.remove(player);
		}
		if (!plugin.IsFullCustomSet("destroyer", inventory)) {
			player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			player.removePotionEffect(PotionEffectType.REGENERATION);
			plugin.destroyerarmor.remove(player);
		}
		if (!plugin.isAirOrNull(inventory.getHelmet())) {
			NBTItem nbtItem = new NBTItem(inventory.getHelmet());
			if (plugin.destroyerhelmet.contains(player) && !nbtItem.getString("CustomItemID").equals("destroyer")) {
				player.removePotionEffect(PotionEffectType.NIGHT_VISION);
				plugin.destroyerhelmet.remove(player);
			}
			if (!plugin.destroyerhelmet.contains(player) && nbtItem.getString("CustomItemID").equals("destroyer")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false));
				plugin.destroyerhelmet.add(player);
			}
		}

		if (plugin.destroyerarmor.contains(player) && !plugin.IsFullCustomSet("destroyer", inventory)) {
			player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			player.removePotionEffect(PotionEffectType.REGENERATION);
			plugin.destroyerarmor.remove(player);
		}
		if (!plugin.destroyerarmor.contains(player) && plugin.IsFullCustomSet("destroyer", inventory)) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1, false, false));
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1, false, false));
			plugin.destroyerarmor.add(player);
		}
	}
}
