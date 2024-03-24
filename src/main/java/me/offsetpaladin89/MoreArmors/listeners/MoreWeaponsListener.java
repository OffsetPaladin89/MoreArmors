package me.offsetpaladin89.MoreArmors.listeners;

import java.util.ArrayList;
import java.util.UUID;

import com.cryptomorin.xseries.particles.ParticleDisplay;
import com.cryptomorin.xseries.particles.XParticle;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MoreWeaponsListener implements Listener {
//
//	private final MorePluginsCoreMain plugin;
//
//	public ArrayList<Player> interactcooldown  = new ArrayList<>();
//	public ArrayList<Player> titanswordcooldown = new ArrayList<>();
//	public ArrayList<Player> titanbowcooldown  = new ArrayList<>();
//	public ArrayList<Player> titandaggercooldown  = new ArrayList<>();
//	public ArrayList<Player> titanbattleaxecooldown  = new ArrayList<>();
//
//	public ArrayList<Player> emeraldswordcooldown = new ArrayList<>();
//	public ArrayList<Player> emeraldbowcooldown = new ArrayList<>();
//	public ArrayList<Player> emeralddaggercooldown = new ArrayList<>();
//	public ArrayList<Player> emeraldbattleaxecooldown = new ArrayList<>();
//
//	public ArrayList<Player> netherswordcooldown = new ArrayList<>();
//	public ArrayList<Player> netherbowcooldown = new ArrayList<>();
//	public ArrayList<Player> netherbattleaxecooldown = new ArrayList<>();
//
//	public MoreWeaponsListener(MorePluginsCoreMain plugin) {
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
//					if(nbtItem.getString("CustomItemType").equals("weapon")) {
//						if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
//							if(nbtItem.getString("CustomitemID").equals("titan_sword")) { TitanSwordAbility(player); }
//							if(nbtItem.getString("CustomitemID").equals("titan_dagger")) { TitanDaggerAbility(player); }
//							if(nbtItem.getString("CustomitemID").equals("titan_battleaxe")) { TitanBattleAxeAbility(player); }
//							if(nbtItem.getString("CustomitemID").equals("emerald_sword")) { EmeraldSwordAbility(player, nbtItem.getInteger("WeaponLevel")); }
//							if(nbtItem.getString("CustomitemID").equals("emerald_dagger")) { EmeraldDaggerAbility(player, nbtItem.getInteger("WeaponLevel")); }
//							if(nbtItem.getString("CustomitemID").equals("emerald_battleaxe")) { EmeraldBattleAxeAbility(player, nbtItem.getInteger("WeaponLevel")); }
//							if(nbtItem.getString("CustomitemID").equals("nether_sword")) { NetherSwordAbility(player); }
//							if(nbtItem.getString("CustomitemID").equals("nether_battleaxe")) { NetherBattleAxeAbility(player); }
//						}
//						if(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
//							if(nbtItem.getString("CustomItemID").equals("titan_bow")) { TitanBowAbility(player); }
//							if(nbtItem.getString("CustomItemID").equals("emerald_bow")) { EmeraldBowAbility(player, nbtItem.getInteger("WeaponLevel")); }
//							if(nbtItem.getString("CustomItemID").equals("nether_bow")) {NetherBowAbility(player); }
//						}
//					}
//				}
//			}
//		}
//		interactcooldown.add(player);
//		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> interactcooldown.remove(player), 2L);
//	}
//
//	private void control(WitherSkull witherskull, Plugin plugin) {
//		new BukkitRunnable() {
//			public void run() {
//				LivingEntity target = determineTarget(witherskull, null, plugin);
//				if (witherskull.isDead()) {cancel();}
//				else {
//					witherskull.setVelocity(getVelocity(witherskull.getLocation(), target.getLocation()));}
//			}
//		}.runTaskTimer(plugin, 1L, 1L);
//	}
//
//	private Vector getVelocity(Location from, Location to) {
//		double dX = from.getX() - to.getX();
//		double dY = from.getY() - to.getY();
//		double dZ = from.getZ() - to.getZ();
//		double yaw = Math.atan2(dZ, dX);
//		double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
//		double x = Math.sin(pitch) * Math.cos(yaw);
//		double y = Math.sin(pitch) * Math.sin(yaw);
//		double z = Math.cos(pitch);
//		return new Vector(x, z, y);
//	}
//
//	private LivingEntity determineTarget(WitherSkull witherskull, LivingEntity target, Plugin plugin) {
//		Location loc = witherskull.getLocation();
//		LivingEntity shooter = (LivingEntity) witherskull.getShooter();
//		UUID id = shooter.getUniqueId();
//		for (Entity e : witherskull.getNearbyEntities(30, 30, 30)) {
//			if (e instanceof LivingEntity) {
//				boolean cancel = false;
//				if(e instanceof Player eplayer) {
//					if(eplayer.getGameMode().equals(GameMode.CREATIVE) || eplayer.getGameMode().equals(GameMode.SPECTATOR) || eplayer.getUniqueId().equals(id)) {cancel = true;}
//				}
//				if(!cancel) {
//					double distance = loc.distance(e.getLocation());
//					if (distance < 11.0D) { return (LivingEntity) e; }
//				}
//			}
//		}
//		return null;
//	}
//
//	public boolean getPlayerGamemodes(Player player, Integer x, Integer y, Integer z) {
//		int invalidGamemode = 0;
//		for(Entity entity : player.getNearbyEntities(x, y, z)) {
//			if(entity instanceof LivingEntity livingentity) {
//				if(livingentity instanceof Player playertarget) {
//					if(playertarget.getGameMode().equals(GameMode.CREATIVE) || playertarget.getGameMode().equals(GameMode.SPECTATOR)) {invalidGamemode++;}
//				}
//			}
//		}
//		return player.getNearbyEntities(x, y, z).size() - invalidGamemode != 0;
//	}
//
//	public boolean isCreativeSpectator(LivingEntity entity) {
//		if(entity instanceof Player player) {
//			return !player.getGameMode().equals(GameMode.CREATIVE) && !player.getGameMode().equals(GameMode.SPECTATOR);
//		}
//		return true;
//	}
//
//	public void TitanSwordAbility(Player player) {
//		if(!titanswordcooldown.contains(player)) {
//			player.sendMessage(plugin.convertColoredString("&eUsed &6Titan Guard&e."));
//			player.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 0);
//			plugin.weaponabilityhandler.TitanSwordAbility(player);
//			titanswordcooldown.add(player);
//			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> titanswordcooldown.remove(player), 150L);
//		}
//		else {player.sendMessage(plugin.convertColoredString("&cYou must wait 7.5 seconds before using this ability again."));}
//	}
//
//	public void TitanBowAbility(Player player) {
//		if(!titanbowcooldown.contains(player)) {
//			player.sendMessage(plugin.convertColoredString("&eUsed &6Titan Shot&e."));
//			plugin.weaponabilityhandler.TitanBowAbility(player);
//			titanbowcooldown.add(player);
//			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> titanbowcooldown.remove(player), 100L);
//		}
//		else {player.sendMessage(plugin.convertColoredString("&cYou must wait 5 seconds before using this ability again."));}
//	}
//
//	public void TitanDaggerAbility(Player player) {
//		if(!titandaggercooldown.contains(player)) {
//			player.sendMessage(plugin.convertColoredString("&eUsed &6Titan Slash&e."));
//			player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0);
//			plugin.weaponabilityhandler.TitanDaggerAbility(player);
//			titandaggercooldown.add(player);
//			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> titandaggercooldown.remove(player), 200L);
//		}
//		else {player.sendMessage(plugin.convertColoredString("&cYou must wait 10 seconds before using this ability again."));}
//	}
//
//	public void TitanBattleAxeAbility(Player player) {
//		if(!titanbattleaxecooldown.contains(player)) {
//			player.sendMessage(plugin.convertColoredString("&eUsed &6Titan Breaker&e."));
//			plugin.weaponabilityhandler.TitanBattleAxeAbility(player);
//			titanbattleaxecooldown.add(player);
//			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> titanbattleaxecooldown.remove(player), 200L);
//		}
//		else {player.sendMessage(plugin.convertColoredString("&cYou must wait 10 seconds before using this ability again."));}
//	}
//
//	public void EmeraldSwordAbility(Player player, Integer level) {
//		if(!emeraldswordcooldown.contains(player)) {
//			if(getPlayerGamemodes(player, 8, 8, 8)) {
//				player.sendMessage(plugin.convertColoredString("&eUsed &6Emerald Explosion&e."));
//				emeraldswordcooldown.add(player);
//				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> emeraldswordcooldown.remove(player), 20L);
//				for(Entity entity : player.getNearbyEntities(8, 8, 8)) {
//					if(entity instanceof LivingEntity livingentity) {
//						if(isCreativeSpectator(livingentity)) {
//							player.getLocation().getWorld().playSound(entity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
//							for(int x = 0; x < level; x++) {
//								livingentity.damage(3 * level, player);
//								XParticle.circle(1.2, 30, ParticleDisplay.colored(entity.getLocation().add(0, 0.5 * (x + 1), 0), 220, 0, 0, 1));
//							}
//						}
//					}
//				}
//			}
//			else {player.sendMessage(plugin.convertColoredString("&cThere are no entities close enough to activate this ability."));}
//		}
//		else {player.sendMessage(plugin.convertColoredString("&cYou must wait 1 second before using this ability again."));}
//	}
//
//	public void EmeraldBowAbility(Player player, Integer level) {
//		if(!emeraldbowcooldown.contains(player)) {
//			if(getPlayerGamemodes(player, 15, 15, 15)) {
//				player.sendMessage(plugin.convertColoredString("&eUsed &6Emerald Rain&e."));
//				emeraldbowcooldown.add(player);
//				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> emeraldbowcooldown.remove(player), 60L);
//				for(Entity entity : player.getNearbyEntities(15, 15, 15)) {
//					if(entity instanceof LivingEntity livingentity) {
//						if(isCreativeSpectator(livingentity)) {
//							Location location = entity.getLocation();
//							XParticle.circle(1.2, 30, ParticleDisplay.colored(location.add(0, 0.3, 0), 255, 255, 255, 1));
//							player.getLocation().getWorld().playSound(entity.getLocation().add(0, 8 + 0.25 * level, 0), Sound.ENTITY_ARROW_SHOOT, 1, 1);
//							for(int x = 0; x < level + 2; x++) {location.getWorld().spawnArrow(entity.getLocation().add(0, 8 + 0.25 * level, 0), new Vector(0, -90, 0), 2f + 0.2f * level, 10 + 0.25f * level).setDamage(3 + 2 * level);}
//						}
//					}
//				}
//			}
//			else { player.sendMessage(plugin.convertColoredString("&cThere are no entities close enough to activate this ability."));}
//			if(player.getNearbyEntities(15, 15, 15).size() == 0) { player.sendMessage(plugin.convertColoredString("&cThere are no mobs close enough to activate this ability.")); }
//		}
//		else { player.sendMessage(plugin.convertColoredString("&cYou must wait 2 seconds before using this ability again.")); }
//	}
//
//	public void EmeraldDaggerAbility(Player player, Integer level) {
//		if(!emeralddaggercooldown.contains(player)) {
//			if(getPlayerGamemodes(player, 10, 10, 10)) {
//				player.sendMessage(plugin.convertColoredString("&eUsed &6Emerald Pierce&e."));
//				emeralddaggercooldown.add(player);
//				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> emeralddaggercooldown.remove(player), 200L);
//				for(Entity entity : player.getNearbyEntities(10, 10, 10)) {
//					if(entity instanceof LivingEntity livingentity) {
//						if(isCreativeSpectator(livingentity)) {
//							player.getLocation().getWorld().playSound(entity.getLocation(), Sound.ENTITY_WITHER_HURT, 1, 1);
//							XParticle.circle(1.2, 30, ParticleDisplay.colored(entity.getLocation().add(0, 0.3, 0), 30, 30, 30, 1));
//							livingentity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
//							double health = livingentity.getHealth() - (3 + level);
//							if(health < 0) {health = 0;}
//							livingentity.setHealth(health);
//						}
//					}
//				}
//			}
//			else {player.sendMessage(plugin.convertColoredString("&cThere are no entities close enough to activate this ability."));}
//		}
//		else {player.sendMessage(plugin.convertColoredString("&cYou must wait 10 seconds before using this ability again."));}
//	}
//
//	public void EmeraldBattleAxeAbility(Player player, Integer level) {
//		if(!emeraldbattleaxecooldown.contains(player)) {
//			if(getPlayerGamemodes(player, 5, 5, 5)) {
//				player.sendMessage(plugin.convertColoredString("&eUsed &6Emerald Smash&e."));
//				emeraldbattleaxecooldown.add(player);
//				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> emeraldbattleaxecooldown.remove(player), 100L);
//				player.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 5, 1);
//				XParticle.filledCircle(5, 30, 0.2, ParticleDisplay.display(player.getLocation().add(0, 0.3, 0), Particle.SMOKE_NORMAL));
//				for(Entity entity : player.getNearbyEntities(5, 5, 5)) {
//					if(entity instanceof LivingEntity livingentity) {
//						if(isCreativeSpectator(livingentity)) {
//							livingentity.damage(3 * (level + 1));
//							livingentity.setVelocity(new Vector(0, 0.35 * (level + 1), 0));
//						}
//					}
//				}
//			}
//			else {player.sendMessage(plugin.convertColoredString("&cThere are no entities close enough to activate this ability."));}
//		}
//		else {player.sendMessage(plugin.convertColoredString("&cYou must wait 5 seconds before using this ability again."));}
//	}
//
//	public void NetherSwordAbility(Player player) {
//		if(!netherswordcooldown.contains(player)) {
//			player.sendMessage(plugin.convertColoredString("&eUsed &6Wither Skull Shot&e."));
//			netherswordcooldown.add(player);
//			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> netherswordcooldown.remove(player), 80L);
//			player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1, 1);
//			XParticle.circle(0.8, 30, ParticleDisplay.colored(player.getLocation().add(0, 0.3, 0), 30, 30, 30, 1));
//			WitherSkull entity = player.getLocation().getWorld().spawn(player.getLocation().add(player.getLocation().getDirection().multiply(1.1)).add(0, 1.5, 0), WitherSkull.class);
//			entity.setCharged(true);
//			entity.setVelocity(player.getLocation().getDirection().multiply(2));
//			entity.setShooter(player);
//			entity.setMetadata("NetherWeaponWitherSkullDamage", new FixedMetadataValue(plugin, 20));
//		}
//		else {player.sendMessage(plugin.convertColoredString("&cYou must wait 4 seconds before using this ability again."));}
//	}
//
//	public void NetherBowAbility(Player player) {
//		if(!netherbowcooldown.contains(player)) {
//			player.sendMessage(plugin.convertColoredString("&eUsed &6Wither Storm&e."));
//			netherbowcooldown.add(player);
//			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> netherbowcooldown.remove(player), 300L);
//			new BukkitRunnable() {
//				int x = 0;
//				public void run() {
////					float offsetx = new Random().nextFloat();
////					float offsety = new Random().nextFloat();
////					float offsetz = new Random().nextFloat();
//					player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1, 1);
//					WitherSkull entity = player.getLocation().getWorld().spawn(player.getLocation().add(player.getLocation().getDirection().multiply(1.1)).add(0, 1.5, 0), WitherSkull.class);
//					entity.setCharged(true);
//					entity.setVelocity(player.getLocation().getDirection().multiply(2));
////					if(new Random().nextInt(1) == 1) {entity.setVelocity(entity.getVelocity().add(new Vector(offsetx, offsety, offsetz)));}
////					else {entity.setVelocity(entity.getVelocity().subtract(new Vector(offsetx, offsety, offsetz)));}
//					entity.setShooter(player);
//					entity.setMetadata("NetherWeaponWitherSkullDamage", new FixedMetadataValue(plugin, 15));
//					control(entity, plugin);
//					x++;
//					if(x == 8) {cancel();}
//				}
//			}.runTaskTimer(plugin, 0, 1L);
//		}
//		else {player.sendMessage(plugin.convertColoredString("&cYou must wait 15 seconds before using this ability again."));}
//	}
//
//	public void NetherBattleAxeAbility(Player player) {
//		if(!netherbattleaxecooldown.contains(player)) {
//			if(getPlayerGamemodes(player, 10, 10, 10)) {
//				player.sendMessage(plugin.convertColoredString("&eUsed &6Burning Mark&e."));
//				netherbattleaxecooldown.add(player);
//				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> netherbattleaxecooldown.remove(player), 300L);
//				for(Entity entity : player.getNearbyEntities(10, 10, 10)) {
//					if(entity instanceof LivingEntity livingentity) {
//						if(isCreativeSpectator(livingentity)) {
//							player.getLocation().getWorld().playSound(entity.getLocation(), Sound.ENTITY_BLAZE_AMBIENT, 1, 1);
//							livingentity.setFireTicks(200);
//						}
//					}
//				}
//			}
//			else {player.sendMessage(plugin.convertColoredString("&cThere are no entities close enough to activate this ability."));}
//		}
//		else {player.sendMessage(plugin.convertColoredString("&cYou must wait 15 seconds before using this ability again."));}
//	}
}
