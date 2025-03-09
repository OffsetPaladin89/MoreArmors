package me.offsetpaladin89.MoreArmors.listeners;

import com.cryptomorin.xseries.XSound;
import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.armors.DestroyerArmor;
import me.offsetpaladin89.MoreArmors.armors.EmeraldArmor;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class MoreArmorsListener implements Listener {

	private final MoreArmorsMain plugin;

	private final FileConfiguration config;

	public final ArrayList<UUID> cooldowns = new ArrayList<>();

	public MoreArmorsListener(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		config = plugin.configHandler.getConfig("config");
	}

	@EventHandler
	public void DamageEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player player) {
			PlayerInventory inv = player.getInventory();
			if (plugin.isAirOrNull(inv.getChestplate())) return;
			if(plugin.matchingCustomItem(inv.getChestplate(), ArmorType.DESTROYER)) {
				Random random = new Random();
				if (random.nextInt(4) == 0) {
					event.setDamage(0D);
					player.playSound(player.getLocation(), XSound.ENTITY_PLAYER_ATTACK_CRIT.get(), 0.8f, 1f);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();

		if(!config.getBoolean("destroyer_armor.enabled")) return;
		if(player.getGameMode().equals(GameMode.SPECTATOR) || player.getGameMode().equals(GameMode.CREATIVE)) return;

		PlayerInventory inv = player.getInventory();
		if (plugin.isAirOrNull(inv.getBoots())) return;

		if(plugin.matchingCustomItem(inv.getBoots(), ArmorType.DESTROYER)) {
			event.setCancelled(true);
			player.setFlying(false);
			player.setAllowFlight(false);
			player.playSound(player.getLocation(), XSound.ENTITY_WITHER_BREAK_BLOCK.get(), 0.8f, 1f);
			player.setVelocity(new Vector(player.getLocation().getDirection().getX(), 0, player.getLocation().getDirection().getZ()).normalize().multiply(1.2));
			player.setFallDistance(0);
			player.getLocation().getWorld().createExplosion(player.getLocation(), 5, false, false, player);
		}
	}

	@EventHandler
	public void onPlayerExplode(EntityExplodeEvent event) {
		if(!config.getBoolean("destroyer_armor.enabled")) return;
		if(event.getEntity() instanceof Player player) {
			PlayerInventory inv = player.getInventory();
			if(plugin.isAirOrNull(inv.getBoots())) return;
			if(plugin.matchingCustomItem(inv.getBoots(), ArmorType.DESTROYER)) event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();

		if (plugin.IsFullCustomSet(ArmorType.EXPERIENCE, player.getInventory()) && config.getBoolean("experience_armor.enabled")) event.setExpToDrop(event.getExpToDrop() * 2);
		if (plugin.IsFullCustomSet(ArmorType.MINER, player.getInventory()) && config.getBoolean("miner_armor.enabled")) {
			if (player.hasPotionEffect(PotionEffectType.HASTE) && player.getPotionEffect(PotionEffectType.HASTE).getAmplifier() == 1) player.removePotionEffect(PotionEffectType.HASTE);
			player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 100, 1));
		}

		seaGreedArmorBlockMined(player, event.getBlock());
		emeraldArmorBlockMined(player, event.getBlock());
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		PlayerInventory inv = player.getInventory();

		if (plugin.IsFullCustomSet(ArmorType.SEA_GREED, player.getInventory()) && config.getBoolean("sea_greed_armor.enabled")) {
			player.setWalkSpeed(0.2F);
			if (player.isSwimming()) {
				Vector dir = player.getLocation().getDirection().normalize().multiply(1.4D); // 3 - 1.6
				Vector vec = new Vector(dir.getX(), dir.getY(), dir.getZ());
				player.setVelocity(vec);
			}
		}

		if(plugin.isAirOrNull(inv.getBoots())) return;
		if(!config.getBoolean("destroyer_armor.enabled")) return;
		if(player.getGameMode().equals(GameMode.SPECTATOR) || player.getGameMode().equals(GameMode.CREATIVE)) return;

		if(plugin.matchingCustomItem(inv.getBoots(), ArmorType.DESTROYER)) {

			if(cooldowns.contains(player.getUniqueId())) return;

			player.setAllowFlight(true);
			cooldowns.add(player.getUniqueId());
			new BukkitRunnable() {
				public void run() {
					player.setAllowFlight(false);
					player.setFlying(false);
					cooldowns.remove(player.getUniqueId());
				}
			}.runTaskLater(plugin, 20);
		}
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Player) return;
		if (!(event.getEntity().getKiller() instanceof Player killer)) return;

		PlayerInventory inventory = killer.getInventory();

		if (plugin.IsFullCustomSet(ArmorType.EXPERIENCE, inventory) && config.getBoolean("experience_armor.enabled")) event.setDroppedExp(event.getDroppedExp() * 2);

		if (plugin.IsFullCustomSet(ArmorType.TITAN, inventory) && config.getBoolean("titan_armor.enabled")) {
			if (killer.hasPotionEffect(PotionEffectType.RESISTANCE) && killer.getPotionEffect(PotionEffectType.RESISTANCE).getAmplifier() == 0) killer.removePotionEffect(PotionEffectType.RESISTANCE);
			killer.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 200, 0, false, false));
		}

		if (plugin.IsFullCustomSet(ArmorType.SEA_GREED, inventory) && config.getBoolean("sea_greed_armor.enabled")) {
			Random r = new Random();
			if (entity.getType().equals(EntityType.ELDER_GUARDIAN) && r.nextDouble() <= 0.25d) {
				killer.sendTitle(MoreArmorsMain.colorString("&c&l&kzzz &r&4&lBLESSING OF THE SEA GOD &c&l&kzzz"), "", -1, -1, -1);
				killer.playSound(killer, Sound.BLOCK_END_PORTAL_SPAWN, SoundCategory.MASTER, 1, 1.4f);
				for (int i = 0; i < 3; i++) killer.getWorld().strikeLightningEffect(killer.getLocation().subtract((r.nextInt(0, 280) - 140f) / 100f, 1, (r.nextInt(0, 280) - 140f) / 100f));
				seaGreedEffects(killer);
			}
		}

		destroyerArmorEntityKill(inventory);
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		speedsterArmorDamageTaken(event.getEntity());
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		endArmorTeleport(event.getPlayer(), event.getAction());
	}

	private void seaGreedArmorEntityKill() {
			}

	private void destroyerArmorEntityKill(PlayerInventory inv) {
		for (int i = 0; i < inv.getSize(); i++) {
			ItemStack currentItem = inv.getItem(i);
			if(plugin.isAirOrNull(currentItem) || !plugin.matchingCustomItem(currentItem, ArmorType.DESTROYER)) continue;
			DestroyerArmor destroyerArmor = new DestroyerArmor(currentItem);

			destroyerArmor.createItemFromNBT();
			destroyerArmor.increaseKillCount(1);

			destroyerArmor.createItem();

			inv.setItem(i, destroyerArmor.getItem());
		}
	}

	private void speedsterArmorDamageTaken(Entity e) {
		if (!(e instanceof Player p)) return;
		PlayerInventory inventory = p.getInventory();
		if(!(config.getBoolean("speedster_armor.enabled") && plugin.IsFullCustomSet(ArmorType.SPEEDSTER, inventory))) return;
		if(p.hasPotionEffect(PotionEffectType.SPEED) && p.getPotionEffect(PotionEffectType.SPEED).getAmplifier() == 1) p.removePotionEffect(PotionEffectType.SPEED);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
	}

	private void endArmorTeleport(Player p, Action a) {
		PlayerInventory inv = p.getInventory();
		if(!(a.equals(Action.LEFT_CLICK_AIR) && p.isSneaking() && config.getBoolean("end_armor.enabled") && plugin.IsFullCustomSet(ArmorType.END, inv) && p.getWorld().getEnvironment().equals(Environment.THE_END) && cooldowns.contains(p.getUniqueId()))) return;
		Block b = p.getTargetBlock(null, 10);
		Location pLoc = p.getLocation();
		Location newLoc = new Location(b.getWorld(), b.getX(), b.getY() + 1, b.getZ(), pLoc.getYaw(), pLoc.getPitch());
		p.playSound(newLoc, Sound.ENTITY_ENDERMAN_TELEPORT, 5, 5);
		cooldowns.add(p.getUniqueId());
		new BukkitRunnable() {
			public void run() {
				cooldowns.remove(p.getUniqueId());
			}
		}.runTaskLater(plugin, 20);
	}

	private void emeraldArmorBlockMined(Player p, Block b) {
		if(!config.getBoolean("emerald_armor.enabled")) return;
		PlayerInventory inv = p.getInventory();
		for (int i = 0; i < inv.getSize(); i++) {
			ItemStack currentItem = inv.getItem(i);
			if(plugin.isAirOrNull(currentItem) || !(plugin.matchingCustomItem(currentItem, ArmorType.EMERALD) && b.getType().toString().endsWith("EMERALD_ORE"))) continue;

			EmeraldArmor emeraldArmor = new EmeraldArmor(currentItem);

			emeraldArmor.createItemFromNBT();

			emeraldArmor.increaseEmeraldCount(1);

			emeraldArmor.createItem();

			inv.setItem(i, emeraldArmor.getItem());
		}
	}

	private void seaGreedArmorBlockMined(Player p, Block b) {
		PlayerInventory inv = p.getInventory();
		if (!(config.getBoolean("sea_greed_armor.enabled") && p.isInWater() && inv.getItemInMainHand().hasItemMeta()) || inv.getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) return;
		Random r = new Random();

		float oreMulti = 0f;

		if(plugin.matchingCustomItem(inv.getHelmet(), ArmorType.SEA_GREED)) oreMulti += 0.5f;
		if(plugin.matchingCustomItem(inv.getChestplate(), ArmorType.SEA_GREED)) oreMulti += 0.5f;
		if(plugin.matchingCustomItem(inv.getLeggings(), ArmorType.SEA_GREED)) oreMulti += 0.5f;
		if(plugin.matchingCustomItem(inv.getBoots(), ArmorType.SEA_GREED)) oreMulti += 0.5f;

		int dropAmount = (int) oreMulti;

		if(oreMulti % 1 != 0 && r.nextDouble() <= oreMulti % 1) dropAmount++;

		if (b.getType().toString().endsWith("_ORE")) return;
		for (ItemStack i : b.getDrops()) b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(i.getType(), i.getAmount() * dropAmount));
	}

	private void seaGreedEffects(Player player) {
		plugin.addItem(player.getInventory(), player, new ItemStack(Material.DIAMOND_BLOCK), 25);
		plugin.addItem(player.getInventory(), player, new ItemStack(Material.GOLD_BLOCK), 100);

		player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 72000, 2, false, false));
		player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 72000, 1, false, false));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 72000, 1, false, false));
		player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 72000, 1, false, false));
	}
}
