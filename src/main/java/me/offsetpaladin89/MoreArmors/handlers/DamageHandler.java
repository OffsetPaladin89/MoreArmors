package me.offsetpaladin89.MoreArmors.handlers;

import com.cryptomorin.xseries.XSound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.World.Environment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;

public class DamageHandler implements Listener {

	public static MoreArmorsMain plugin;

	public DamageHandler(MoreArmorsMain plugin) {
		DamageHandler.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
//
//	@EventHandler
//	public void onBowShoot(EntityShootBowEvent event) {
//		NBTItem nbtItem = new NBTItem(event.getBow());
//		if(event.getEntity() instanceof Player) {
//			if(nbtItem.getBoolean("IsCustomItem")) {
//				event.getProjectile().setMetadata("MorePluginsCoreBowForce", new FixedMetadataValue(plugin, event.getForce()));
//				if(nbtItem.getString("CustomItemID").equals("nether_bow")) {
//					event.setCancelled(true);
//					float powerlevel = 1;
//					WitherSkull entity = event.getEntity().launchProjectile(WitherSkull.class);
//					entity.setVelocity(event.getEntity().getLocation().getDirection().multiply(event.getForce() * 2.5));
//					if(event.getBow().getEnchantmentLevel(Enchantment.ARROW_DAMAGE) != 0) {powerlevel = 1 + 0.25f * (event.getBow().getEnchantmentLevel(Enchantment.ARROW_DAMAGE) + 1);}
//					entity.setMetadata("NetherBowWitherSkullDamage", new FixedMetadataValue(plugin, 16 * powerlevel * event.getForce()));
//				}
//			}
//		}
//	}
//
//	@EventHandler
//	public void EntityDamaged(EntityDamageEvent event) {
//		if (event.getEntity() instanceof Player) {
//			Player player = (Player) event.getEntity();
//			if (plugin.weaponabilityhandler.titansword.contains(player)) {
//				event.setDamage(event.getDamage() * 0.3);
//				player.getLocation().getWorld().playSound(player.getLocation(), XSound.ITEM_SHIELD_BREAK.parseSound(), 1, 0);
//				plugin.weaponabilityhandler.titansword.remove(player);
//			}
//		}
//	}
//
	@EventHandler
	public void DamageEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			PlayerInventory inventory = player.getInventory();
			ItemStack helmet = inventory.getHelmet();
			ItemStack chestplate = inventory.getChestplate();
			ItemStack leggings = inventory.getLeggings();
			ItemStack boots = inventory.getBoots();
			NBTItem nbtHelmet = null;
			NBTItem nbtChestplate = null;
			NBTItem nbtLeggings = null;
			NBTItem nbtBoots = null;

			int damage = 0;
//			if(!plugin.isAirOrNull(helmet)) {
//				nbtHelmet = new NBTItem(helmet);
//				if(nbtHelmet.getString("CustomItemID").equals("destroyer")) {
//					int kills = nbtHelmet.getInteger("Kills");
//					if (kills >= 2500) {
//						damage += 10;
//					} else {
//						damage += Math.floorMod(kills, 250);
//					}
//				}
//			}
//			if(!plugin.isAirOrNull(chestplate)) {
//				nbtChestplate = new NBTItem(chestplate);
//				if(nbtChestplate.getString("CustomItemID").equals("destroyer")) {
//					int kills = nbtChestplate.getInteger("Kills");
//					if (kills >= 2500) {
//						damage += 10;
//					} else {
//						damage += Math.floorMod(kills, 250);
//					}
//				}
//			}
//			if(!plugin.isAirOrNull(leggings)) {
//				nbtLeggings = new NBTItem(leggings);
//				if(nbtLeggings.getString("CustomItemID").equals("destroyer")) {
//					int kills = nbtLeggings.getInteger("Kills");
//					if (kills >= 2500) {
//						damage += 10;
//					} else {
//						damage += Math.floorMod(kills, 250);
//					}
//				}
//			}
//			if(!plugin.isAirOrNull(boots)) {
//				nbtBoots = new NBTItem(boots);
//				if(nbtBoots.getString("CustomItemID").equals("destroyer")) {
//					int kills = nbtBoots.getInteger("Kills");
//					if (kills >= 2500) {
//						damage += 10;
//					} else {
//						damage += Math.floorMod(kills, 250);
//					}
//				}
//			}
            event.setDamage(event.getDamage() + damage);
            event.setDamage(event.getDamage() * getScaledDamage(player, player.getWorld().getEnvironment()));
            plugin.hologramHandler.createDamageHologram(player.getLocation(), event.getEntity().getLocation(), 20L, event.getDamage());
//			player.sendMessage("Damage - " + event.getDamage());
//			 plugin.createHologram(event.getEntity().getLocation(), "&4&lTest");

			// if(plugin.weaponabilityhandler.titanbattleaxe.contains(player)) {if(plugin.versionhandler.hasNBTBooleanTag(mainhand, "IsCustomItem", true)) {if(plugin.versionhandler.hasNBTStringTag(mainhand, "CustomItemID", "titan_battleaxe")) {plugin.weaponabilityhandler.titanbattleaxe.remove(player);}}}
		}
		if(event.getDamager() instanceof Arrow) {
			Arrow damager = (Arrow) event.getDamager();
			if(damager.getShooter() instanceof Player) {
				Player player = (Player) damager.getShooter();
				PlayerInventory inventory = player.getInventory();
				ItemStack mainhand = inventory.getItemInMainHand();
				NBTItem nbtItem = new NBTItem(mainhand);

                event.setDamage(event.getDamage() + getBowDamage(player));
                event.setDamage(event.getDamage() * getScaledDamage(player, player.getWorld().getEnvironment()));

//				float bonusDamage = 0;

//				if(nbtItem.getBoolean("IsCustomItem")) {
//					if(nbtItem.getString("WeaponType").equals("bow")) {
//						if(nbtItem.getFloat("attackDamage") != null) { bonusDamage += nbtItem.getFloat("attackDamage"); }
//						if(plugin.weaponabilityhandler.titanbow.contains(player)) {
//							if(nbtItem.getString("CustomItemID").equals("titan_bow")) {
//								plugin.weaponabilityhandler.titanbow.remove(player);
//								bonusDamage *= 2;
//							}
//						}
//					}
//				}
//				if(damager.hasMetadata("MorePluginsCoreBowForce")) {bonusDamage *= Float.parseFloat(damager.getMetadata("MorePluginsCoreBowForce").get(0).value().toString());}
//				event.setDamage(event.getDamage() + bonusDamage);
//				float chance = getCriticalHitChance(player) / 100;
//				if(Math.random() < chance) {
//					// player.sendMessage(plugin.convertColoredString("&4&lCRITICAL HIT!"));
//					event.setDamage(event.getDamage() * 2);
//				}
			}
		}
//		if(event.getDamager() instanceof WitherSkull) {
//			WitherSkull damager = (WitherSkull) event.getDamager();
//			if(damager.getShooter() instanceof Player) {
//				Player player = (Player) damager.getShooter();
//				PlayerInventory inventory = player.getInventory();
//				ItemStack mainhand = inventory.getItemInMainHand();
//				NBTItem nbtItem = new NBTItem(mainhand);
//				float damage = 0;
//
//				if(damager.hasMetadata("NetherWeaponWitherSkullDamage") || damager.hasMetadata("NetherBowWitherSkullDamage")) {
//					if(event.getEntity() instanceof Player) {
//						if(player.equals(event.getEntity())) {
//							event.setDamage(0);
//							return;
//						}
//					}
//					if(nbtItem.getBoolean("IsCustomItem")) {
//						if(nbtItem.getString("WeaponType").equals("bow")) { if(nbtItem.getFloat("attackDamage") != null) {damage += nbtItem.getFloat("attackDamage"); }}
//						if(damager.hasMetadata("MorePluginsCoreBowForce")) {damage *= Float.parseFloat(damager.getMetadata("MorePluginsCoreBowForce").get(0).value().toString());}
//						event.setDamage(damage);
//					}
//					if(damager.hasMetadata("NetherWeaponWitherSkullDamage")) {event.setDamage(Float.parseFloat(damager.getMetadata("NetherWeaponWitherSkullDamage").get(0).value().toString()));}
//					else if(damager.hasMetadata("NetherBowWitherSkullDamage")) {event.setDamage(Float.parseFloat(damager.getMetadata("NetherBowWitherSkullDamage").get(0).value().toString()));}
//				}
//			}
//		}
	}
//
//	@EventHandler
//	public void TNTPrime(ExplosionPrimeEvent event) {
//		if(event.getEntity() instanceof WitherSkull) {
//			WitherSkull entity = (WitherSkull) event.getEntity();
//			if(entity.getShooter() instanceof Player) {if(entity.hasMetadata("NetherSwordWitherSkullDamage")) {event.setRadius(1.2f);}}
//			if(entity.getShooter() instanceof Player) {if(entity.hasMetadata("NetherBowWitherSkullDamage")) {event.setRadius(1.2f);}}
//		}
//	}
//
    public Float getScaledDamage(Player p, Environment env) {
        PlayerInventory inventory = p.getInventory();
        return env.equals(Environment.NETHER) ? (plugin.IsFullCustomSet("nether", inventory) ? 1f : 0f) +
                (plugin.matchingCustomItem(inventory.getHelmet(), "nether") ? 0.1f : 0f) +
                (plugin.matchingCustomItem(inventory.getChestplate(), "nether") ? 0.1f : 0f) +
                (plugin.matchingCustomItem(inventory.getLeggings(), "nether") ? 0.1f : 0f) +
                (plugin.matchingCustomItem(inventory.getBoots(), "nether") ? 0.1f : 0f) + 1f : 1f;
    }

	public float getBowDamage(Player player) {
		PlayerInventory inventory = player.getInventory();
		ItemStack helmet = inventory.getHelmet();
		ItemStack chestplate = inventory.getChestplate();
		ItemStack leggings = inventory.getLeggings();
		ItemStack boots = inventory.getBoots();
		NBTItem nbtHelmet = new NBTItem(helmet);
		NBTItem nbtChestplate = new NBTItem(chestplate);
		NBTItem nbtLeggings = new NBTItem(leggings);
		NBTItem nbtBoots = new NBTItem(boots);

		float damage = 0;
        if(nbtHelmet.getString("CustomItemID").equals("destroyer")) {
            int kills = nbtHelmet.getInteger("Kills");
            if(kills >= 2500) {damage += 10;}
            else {damage += Math.floorMod(kills, 250);}
        }
        if(nbtChestplate.getString("CustomItemID").equals("destroyer")) {
            int kills = nbtChestplate.getInteger("Kills");
            if(kills >= 2500) {damage += 10;}
            else {damage += Math.floorMod(kills, 250);}
        }
        if(nbtLeggings.getString("CustomItemID").equals("destroyer")) {
            int kills = nbtLeggings.getInteger("Kills");
            if(kills >= 2500) {damage += 10;}
            else {damage += Math.floorMod(kills, 250);}
        }
        if(nbtBoots.getString("CustomItemID").equals("destroyer")) {
            int kills = nbtBoots.getInteger("Kills");
            if(kills >= 2500) {damage += 10;}
            else {damage += Math.floorMod(kills, 250);}
        }
        if(nbtHelmet.getString("CustomItemID").equals("truediamond")) {
            float multiplier = (float) (1 + (Math.floor(nbtHelmet.getInteger("DiamondSacrifice") / 5) * 5) / 100);
            float level = nbtHelmet.getFloat("ArmorLevel");
            damage += (level * multiplier) / 2;
        }
        if(nbtChestplate.getString("CustomItemID").equals("truediamond")) {
            float multiplier = (float) (1 + (Math.floor(nbtChestplate.getInteger("DiamondSacrifice") / 5) * 5) / 100);
            float level = nbtChestplate.getFloat("ArmorLevel");
            damage += (level * multiplier) / 2;
        }
        if(nbtLeggings.getString("CustomItemID").equals("truediamond")) {
            float multiplier = (float) (1 + (Math.floor(nbtLeggings.getInteger("DiamondSacrifice") / 5) * 5) / 100);
            float level = nbtLeggings.getFloat("ArmorLevel");
            damage += (level * multiplier) / 2;
        }
        if(nbtBoots.getString("CustomItemID").equals("truediamond")) {
            float multiplier = (float) (1 + (Math.floor(nbtBoots.getInteger("DiamondSacrifice") / 5) * 5) / 100);
            float level = nbtBoots.getFloat("ArmorLevel");
            damage += (level * multiplier) / 2;
        }
		return damage;
	}
//
//	public float getCriticalHitChance(Player player) {
//		PlayerInventory inventory = player.getInventory();
//		ItemStack helmet = inventory.getHelmet();
//		ItemStack chestplate = inventory.getChestplate();
//		ItemStack leggings = inventory.getLeggings();
//		ItemStack boots = inventory.getBoots();
//		NBTItem nbtHelmet = new NBTItem(helmet);
//		NBTItem nbtChestplate = new NBTItem(chestplate);
//		NBTItem nbtLeggings = new NBTItem(leggings);
//		NBTItem nbtBoots = new NBTItem(boots);
//
//		float criticalhitchance = 0;
//
//		if(plugin.enabledPlugins().contains(plugin.getServer().getPluginManager().getPlugin("MoreArmors"))) {
//			if(nbtHelmet.getString("CustomItemID").equals("truediamond")) {
//				float multiplier = (float) (1 + (Math.floor(nbtHelmet.getInteger("DiamondSacrifice") / 5) * 5) / 100);
//				float level = nbtHelmet.getFloat("ArmorLevel");
//				criticalhitchance += (level * multiplier) / 2;
//			}
//			if(nbtChestplate.getString("CustomItemID").equals("truediamond")) {
//				float multiplier = (float) (1 + (Math.floor(nbtChestplate.getInteger("DiamondSacrifice") / 5) * 5) / 100);
//				float level = nbtChestplate.getFloat("ArmorLevel");
//				criticalhitchance += (level * multiplier) / 2;
//			}
//			if(nbtLeggings.getString("CustomItemID").equals("truediamond")) {
//				float multiplier = (float) (1 + (Math.floor(nbtLeggings.getInteger("DiamondSacrifice") / 5) * 5) / 100);
//				float level = nbtLeggings.getFloat("ArmorLevel");
//				criticalhitchance += (level * multiplier) / 2;
//			}
//			if(nbtBoots.getString("CustomItemID").equals("truediamond")) {
//				float multiplier = (float) (1 + (Math.floor(nbtBoots.getInteger("DiamondSacrifice") / 5) * 5) / 100);
//				float level = nbtBoots.getFloat("ArmorLevel");
//				criticalhitchance += (level * multiplier) / 2;
//			}
//		}
//		return criticalhitchance;
//	}
}
