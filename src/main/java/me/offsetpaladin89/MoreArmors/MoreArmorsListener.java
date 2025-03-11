package me.offsetpaladin89.MoreArmors;

import com.cryptomorin.xseries.XSound;
import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.armors.DestroyerArmor;
import me.offsetpaladin89.MoreArmors.armors.EmeraldArmor;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.utils.Util;
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
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.CraftingInventory;
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
		if (!(event.getEntity() instanceof Player p)) return;
		destroyerArmorDamageTaken(event, p);
	}
	private void destroyerArmorDamageTaken(EntityDamageByEntityEvent event, Player p) {
		if(!config.getBoolean("destroyer_armor.enabled")) return;
		PlayerInventory inv = p.getInventory();
		Random random = new Random();
		if(!Util.isAirOrNull(inv.getChestplate()) || !(Util.matchingCustomItem(inv.getChestplate(), ArmorType.DESTROYER) && random.nextInt(4) != 0)) return;
		event.setDamage(0D);
		p.playSound(p.getLocation(), XSound.ENTITY_PLAYER_ATTACK_CRIT.get(), 0.8f, 1f);
	}

	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
		Player p = event.getPlayer();
		destroyerArmorFlying(event, p);
	}
	private void destroyerArmorFlying(PlayerToggleFlightEvent event, Player p) {
		if(!config.getBoolean("destroyer_armor.enabled") || p.getGameMode().equals(GameMode.SPECTATOR) || p.getGameMode().equals(GameMode.CREATIVE) || !Util.isAirOrNull(p.getInventory().getBoots()) || !Util.matchingCustomItem(p.getInventory().getBoots(), ArmorType.DESTROYER)) return;
		Location pLoc = p.getLocation();
		event.setCancelled(true);
		p.setFlying(false);
		p.setAllowFlight(false);
		p.playSound(pLoc, XSound.ENTITY_WITHER_BREAK_BLOCK.get(), 0.8f, 1f);
		p.setVelocity(new Vector(pLoc.getDirection().getX(), 0, pLoc.getDirection().getZ()).normalize().multiply(1.2));
		p.setFallDistance(0);
		pLoc.getWorld().createExplosion(pLoc, 5, false, false, p);
	}

	@EventHandler
	public void onPlayerExplode(EntityExplodeEvent event) {
		if(!(event.getEntity() instanceof Player p)) return;
		destroyerArmorExplode(event, p.getInventory());
	}
	private void destroyerArmorExplode(EntityExplodeEvent event, PlayerInventory inv) {
		if(!config.getBoolean("destroyer_armor.enabled") || !Util.isAirOrNull(inv.getBoots()) || !Util.matchingCustomItem(inv.getBoots(), ArmorType.DESTROYER)) return;
		event.setCancelled(true);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		destroyerArmorMove(p);
		seaGreedArmorMove(p);
	}
	private void destroyerArmorMove(Player p) {
		if(!config.getBoolean("destroyer_armor.enabled") || p.getGameMode().equals(GameMode.SPECTATOR) || p.getGameMode().equals(GameMode.CREATIVE) || !Util.isAirOrNull(p.getInventory().getBoots()) || !Util.matchingCustomItem(p.getInventory().getBoots(), ArmorType.DESTROYER) || cooldowns.contains(p.getUniqueId())) return;

		p.setAllowFlight(true);
		cooldowns.add(p.getUniqueId());
		new BukkitRunnable() {
			public void run() {
				p.setAllowFlight(false);
				p.setFlying(false);
				cooldowns.remove(p.getUniqueId());
			}
		}.runTaskLater(plugin, 20);
	}
	private void seaGreedArmorMove(Player p) {
		if(!config.getBoolean("sea_greed_armor.enabled") || !Util.IsFullCustomSet(ArmorType.SEA_GREED, p.getInventory()) || !p.isSwimming()) return;
		p.setWalkSpeed(0.2F);
		Vector dir = p.getLocation().getDirection().normalize().multiply(1.4D); // 3 - 1.6
		Vector vec = new Vector(dir.getX(), dir.getY(), dir.getZ());
		p.setVelocity(vec);
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Player) return;
		if (!(event.getEntity().getKiller() instanceof Player killer)) return;

		PlayerInventory inv = killer.getInventory();

		experienceArmorEntityKill(event, inv);
		destroyerArmorEntityKill(inv);
		seaGreedArmorEntityKill(killer, entity);
		titanArmorEntityKill(killer);
	}
	private void destroyerArmorEntityKill(PlayerInventory inv) {
		for (int i = 0; i < inv.getSize(); i++) {
			ItemStack currentItem = inv.getItem(i);
			if(Util.isAirOrNull(currentItem) || !Util.matchingCustomItem(currentItem, ArmorType.DESTROYER)) continue;
			DestroyerArmor destroyerArmor = new DestroyerArmor(currentItem);

			destroyerArmor.createItemFromNBT();
			destroyerArmor.increaseKillCount(1);

			destroyerArmor.createItem();

			inv.setItem(i, destroyerArmor.getItem());
		}
	}
	private void seaGreedArmorEntityKill(Player killer, Entity killed) {
		Random r = new Random();
		if (!(config.getBoolean("sea_greed_armor.enabled") && Util.IsFullCustomSet(ArmorType.SEA_GREED, killer.getInventory()) && killed.getType().equals(EntityType.ELDER_GUARDIAN) && r.nextDouble() <= 0.25d)) return;

		killer.sendTitle(Util.colorString("&c&l&kzzz &r&4&lBLESSING OF THE SEA GOD &c&l&kzzz"), "", -1, -1, -1);
		killer.playSound(killer, Sound.BLOCK_END_PORTAL_SPAWN, SoundCategory.MASTER, 1, 1.4f);
		for (int i = 0; i < 3; i++) killer.getWorld().strikeLightningEffect(killer.getLocation().subtract((r.nextInt(0, 280) - 140f) / 100f, 1, (r.nextInt(0, 280) - 140f) / 100f));

		Util.addItem(killer.getInventory(), killer, new ItemStack(Material.DIAMOND_BLOCK), 25);
		Util.addItem(killer.getInventory(), killer, new ItemStack(Material.GOLD_BLOCK), 100);

		killer.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 72000, 2, false, false));
		killer.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 72000, 1, false, false));
		killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 72000, 1, false, false));
		killer.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 72000, 1, false, false));
	}
	private void experienceArmorEntityKill(EntityDeathEvent event, PlayerInventory inv) {
		if(!( config.getBoolean("experience_armor.enabled") && Util.IsFullCustomSet(ArmorType.EXPERIENCE, inv))) return;
		event.setDroppedExp(event.getDroppedExp() * 2);
	}
	private void titanArmorEntityKill(Player killer) {
		if (!(config.getBoolean("titan_armor.enabled") && Util.IsFullCustomSet(ArmorType.TITAN, killer.getInventory()))) return;
		if (killer.hasPotionEffect(PotionEffectType.RESISTANCE) && killer.getPotionEffect(PotionEffectType.RESISTANCE).getAmplifier() == 0) killer.removePotionEffect(PotionEffectType.RESISTANCE);
		killer.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 200, 0, false, false));
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		speedsterArmorDamageTaken(event.getEntity());
	}
	private void speedsterArmorDamageTaken(Entity e) {
		if (!(e instanceof Player p)) return;
		PlayerInventory inventory = p.getInventory();
		if(!(config.getBoolean("speedster_armor.enabled") && Util.IsFullCustomSet(ArmorType.SPEEDSTER, inventory))) return;
		if(p.hasPotionEffect(PotionEffectType.SPEED) && p.getPotionEffect(PotionEffectType.SPEED).getAmplifier() == 1) p.removePotionEffect(PotionEffectType.SPEED);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		PlayerInventory inv = p.getInventory();
		materialInteract(event, inv);
		endArmorInteract(p, event.getAction());
	}
	private void materialInteract(PlayerInteractEvent event, PlayerInventory inv) {
		if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;
		ItemStack item = inv.getItemInMainHand();
		if(Util.isAirOrNull(item)) return;
		MaterialType type = NBT.get(item, nbt -> (MaterialType) nbt.getEnum("MaterialID", MaterialType.class));
		if(type == null || !(type.equals(MaterialType.EYE_OF_ENDER_0) || type.equals(MaterialType.EYE_OF_ENDER_1))) return;
		event.setCancelled(true);
	}
	private void endArmorInteract(Player p, Action a) {
		PlayerInventory inv = p.getInventory();
		if(!(a.equals(Action.LEFT_CLICK_AIR) && p.isSneaking() && config.getBoolean("end_armor.enabled") && Util.IsFullCustomSet(ArmorType.END, inv) && p.getWorld().getEnvironment().equals(Environment.THE_END) && cooldowns.contains(p.getUniqueId()))) return;
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

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(Util.isCustomItem(event.getItemInHand())) event.setCancelled(true);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		PlayerInventory inv = p.getInventory();

		emeraldArmorBlockMined(inv, event.getBlock());
		experienceArmorBlockMined(event, inv);
		minerArmorBlockMined(p);
		seaGreedArmorBlockMined(p, event.getBlock());
	}
	private void emeraldArmorBlockMined(PlayerInventory inv, Block b) {
		if(!config.getBoolean("emerald_armor.enabled")) return;
		for (int i = 0; i < inv.getSize(); i++) {
			ItemStack currentItem = inv.getItem(i);
			if(Util.isAirOrNull(currentItem) || !(Util.matchingCustomItem(currentItem, ArmorType.EMERALD) && b.getType().toString().endsWith("EMERALD_ORE"))) continue;

			EmeraldArmor emeraldArmor = new EmeraldArmor(currentItem);

			emeraldArmor.createItemFromNBT();

			emeraldArmor.increaseEmeraldCount(1);

			emeraldArmor.createItem();

			inv.setItem(i, emeraldArmor.getItem());
		}
	}
	private void experienceArmorBlockMined(BlockBreakEvent event, PlayerInventory inv) {
		if (!(config.getBoolean("experience_armor.enabled") && Util.IsFullCustomSet(ArmorType.EXPERIENCE, inv))) return;
		event.setExpToDrop(event.getExpToDrop() * 2);
	}
	private void minerArmorBlockMined(Player p) {
		if (!(config.getBoolean("miner_armor.enabled") && Util.IsFullCustomSet(ArmorType.MINER, p.getInventory()))) return;
		if (p.hasPotionEffect(PotionEffectType.HASTE) && p.getPotionEffect(PotionEffectType.HASTE).getAmplifier() == 1) p.removePotionEffect(PotionEffectType.HASTE);
		p.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 100, 1));
	}
	private void seaGreedArmorBlockMined(Player p, Block b) {
		PlayerInventory inv = p.getInventory();
		if (!(config.getBoolean("sea_greed_armor.enabled") && p.isInWater() && inv.getItemInMainHand().hasItemMeta()) || inv.getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) return;
		Random r = new Random();

		float oreMulti = 0f;

		if(Util.matchingCustomItem(inv.getHelmet(), ArmorType.SEA_GREED)) oreMulti += 0.5f;
		if(Util.matchingCustomItem(inv.getChestplate(), ArmorType.SEA_GREED)) oreMulti += 0.5f;
		if(Util.matchingCustomItem(inv.getLeggings(), ArmorType.SEA_GREED)) oreMulti += 0.5f;
		if(Util.matchingCustomItem(inv.getBoots(), ArmorType.SEA_GREED)) oreMulti += 0.5f;

		int dropAmount = (int) oreMulti;

		if(oreMulti % 1 != 0 && r.nextDouble() <= oreMulti % 1) dropAmount++;

		if (b.getType().toString().endsWith("_ORE")) return;
		for (ItemStack i : b.getDrops()) b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(i.getType(), i.getAmount() * dropAmount));
	}

	@EventHandler
	public void preCraftEvent(PrepareItemCraftEvent event) {
		CraftingInventory inv = event.getInventory();
		ItemStack result = inv.getResult();

		if (Util.isAirOrNull(result)) return;
		ItemStack[] craftingMatrix = inv.getMatrix();

		if (Util.isCustomItem(result)) return;
		for (ItemStack item : craftingMatrix) {
			if (Util.isAirOrNull(item)) continue;
			if (Util.isCustomItem(item)) inv.setResult(null);
		}
	}
}
