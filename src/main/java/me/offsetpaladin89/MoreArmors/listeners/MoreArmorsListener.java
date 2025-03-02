package me.offsetpaladin89.MoreArmors.listeners;

import com.cryptomorin.xseries.XSound;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.armors.EmeraldArmor;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
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
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MoreArmorsListener implements Listener {

	private final MoreArmorsMain plugin;

	private ArrayList<UUID> cooldowns = new ArrayList<>();

	List<Player> teleportCooldown = new ArrayList<>();

	public MoreArmorsListener(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void DamageEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			PlayerInventory inventory = player.getInventory();
			if (plugin.isAirOrNull(inventory.getChestplate())) return;
			NBTItem nbtItem = new NBTItem(inventory.getChestplate());
			if (nbtItem.getString("CustomItemID").equals("destroyer")) {
				Random random = new Random();
				if (random.nextInt(4) == 0) {
					event.setDamage(0D);
					player.playSound(player.getLocation(), XSound.ENTITY_PLAYER_ATTACK_CRIT.parseSound(), 0.8f, 1f);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();
		if (player.getGameMode().equals(GameMode.SPECTATOR) || player.getGameMode().equals(GameMode.CREATIVE) || !plugin.configHandler.getConfig("config").getBoolean("destroyerarmor.enabled")) return;

		PlayerInventory inventory = player.getInventory();
		if (plugin.isAirOrNull(inventory.getBoots())) return;
		NBTItem nbtItem = new NBTItem(inventory.getBoots());
		if (nbtItem.getString("CustomItemID").equals("destroyer")) {
			event.setCancelled(true);
			player.setFlying(false);
			player.setAllowFlight(false);
			player.playSound(player.getLocation(), XSound.ENTITY_WITHER_BREAK_BLOCK.parseSound(), 0.8f, 1f);
			player.setVelocity(new Vector(player.getLocation().getDirection().getX(), 0, player.getLocation().getDirection().getZ()).normalize().multiply(1.2));
			player.setFallDistance(0);
			player.getLocation().getWorld().createExplosion(player.getLocation(), 5, false, false, player);
		}
	}

	@EventHandler
	public void onPlayerExplode(EntityExplodeEvent event) {
		if (event.getEntity() instanceof Player && plugin.configHandler.getConfig("config").getBoolean("destroyerarmor.enabled")) {
			Player player = (Player) event.getEntity();
			PlayerInventory inventory = player.getInventory();
			if (plugin.isAirOrNull(inventory.getBoots())) {return;}
			NBTItem nbtItem = new NBTItem(inventory.getBoots());
			if (nbtItem.getString("CustomItemID").equals("destroyer")) {event.setCancelled(true);}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {

		FileConfiguration config = plugin.configHandler.getConfig("config");

		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();

		if (plugin.IsFullCustomSet(ArmorType.EXPERIENCE, player.getInventory()) && config.getBoolean("experiencearmor.enabled")) {event.setExpToDrop(event.getExpToDrop() * 2);}
		if (plugin.IsFullCustomSet(ArmorType.MINER, player.getInventory()) && config.getBoolean("minerarmor.enabled")) {
			if (player.hasPotionEffect(PotionEffectType.HASTE)) {if (player.getPotionEffect(PotionEffectType.HASTE).getAmplifier() == 1) {player.removePotionEffect(PotionEffectType.HASTE);}}
			player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 100, 1));
		}

		if (player.isInWater()) {
			if (!inventory.getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
				Block b = event.getBlock();
				Random r = new Random();
				int m = (int) Math.floor(oreMultiplier(player)) + (r.nextDouble() <= oreMultiplier(player) % 1 ? 1 : 0);
				if (b.getType().toString().endsWith("_ORE")) {
					for (ItemStack i : b.getDrops()) {
						b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(i.getType(), i.getAmount() * m));
					}
				}
			}
		}

		for (int i = 0; i < inventory.getSize(); i++) {
			ItemStack currentItem = inventory.getItem(i);
			if(plugin.isAirOrNull(currentItem)) continue;

			ArmorType armorType = NBT.get(currentItem, nbt -> (ArmorType) nbt.getEnum("ArmorID", ArmorType.class));

			if(armorType == null) continue;

			if(armorType.equals(ArmorType.EMERALD)) {
				if(!config.getBoolean("emeraldarmor.enabled")) continue;
				if(!(event.getBlock().getType().equals(Material.EMERALD_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_EMERALD_ORE))) continue;

				EmeraldArmor emeraldArmor = new EmeraldArmor(currentItem);

				emeraldArmor.createItemFromNBT();
				emeraldArmor.increaseEmeraldCount(1);

				emeraldArmor.updateItem();

				inventory.setItem(i, emeraldArmor.getItem());
			}
		}
	}

	private Float oreMultiplier(Player p) {
		PlayerInventory inventory = p.getInventory();
		return ((plugin.matchingCustomItem(inventory.getHelmet(), ArmorType.SEA_GREED) && plugin.configHandler.getConfig("config").getBoolean("seagreedarmor.enabled") ? 0.5f : 0f)) + ((plugin.matchingCustomItem(inventory.getChestplate(), ArmorType.SEA_GREED) ? 0.5f : 0f)) + ((plugin.matchingCustomItem(inventory.getLeggings(), ArmorType.SEA_GREED) ? 0.5f : 0f)) + ((plugin.matchingCustomItem(inventory.getBoots(), ArmorType.SEA_GREED) ? 0.5f : 0f));
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {

		FileConfiguration config = plugin.configHandler.getConfig("config");

		Player p = e.getPlayer();
		PlayerInventory inv = p.getInventory();
		if (plugin.IsFullCustomSet(ArmorType.SEA_GREED, p.getInventory()) && config.getBoolean("seagreedarmor.enabled")) {
			p.setWalkSpeed(0.2F);
			if (p.isSwimming()) {
				Vector dir = p.getLocation().getDirection().normalize().multiply(1.4D); // 3 - 1.6
				Vector vec = new Vector(dir.getX(), dir.getY(), dir.getZ());
				p.setVelocity(vec);
			}
		} else {
			if (plugin.isAirOrNull(inv.getBoots())) return;
			NBTItem nbt = new NBTItem(inv.getBoots());
			if (p.getGameMode().equals(GameMode.ADVENTURE) || p.getGameMode().equals(GameMode.SURVIVAL)) {
				if (nbt.getString("CustomItemID").equals("destroyer") && config.getBoolean("destroyerarmor.enabled")) {
					if (!cooldowns.contains(p.getUniqueId())) {
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
				}
			}
		}
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {

		FileConfiguration config = plugin.configHandler.getConfig("config");

		Entity entity = event.getEntity();
		if (!(entity instanceof Player)) {
			if (event.getEntity().getKiller() != null) {
				Player killer = event.getEntity().getKiller();
				PlayerInventory inventory = killer.getInventory();
				if (plugin.IsFullCustomSet(ArmorType.EXPERIENCE, inventory) && config.getBoolean("experiencearmor.enabled")) {event.setDroppedExp(event.getDroppedExp() * 2);}
				if (plugin.IsFullCustomSet(ArmorType.TITAN, inventory) && config.getBoolean("titanarmor.enabled")) {
					if (killer.hasPotionEffect(PotionEffectType.RESISTANCE)) {if (killer.getPotionEffect(PotionEffectType.RESISTANCE).getAmplifier() == 0) {killer.removePotionEffect(PotionEffectType.RESISTANCE);}}
					killer.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 200, 0, false, false));
				}
				if (plugin.IsFullCustomSet(ArmorType.SEA_GREED, inventory) && config.getBoolean("seagreedarmor.enabled")) {
					Random r = new Random();
					if (entity.getType().equals(EntityType.ELDER_GUARDIAN) && r.nextDouble() <= 0.25d) {
						killer.sendTitle(MoreArmorsMain.colorString("&c&l&kzzz &r&4&lBLESSING OF THE SEA GOD &c&l&kzzz"), "", -1, -1, -1);
						killer.playSound(killer, Sound.BLOCK_END_PORTAL_SPAWN, SoundCategory.MASTER, 1, 1.4f);
						for (int i = 0; i < 3; i++) {killer.getWorld().strikeLightningEffect(killer.getLocation().subtract((r.nextInt(0, 280) - 140f) / 100f, 1, (r.nextInt(0, 280) - 140f) / 100f));}
						seaGreedEffects(killer);
					}
				}
				for (int i = 0; i < inventory.getSize(); i++) {
					ItemStack currentItem = inventory.getItem(i);
					if (!plugin.isAirOrNull(currentItem)) {
						NBTItem nbtItem = new NBTItem(currentItem);
						if (nbtItem.getString("CustomItemID").equals("destroyer") && config.getBoolean("destroyerarmor.enabled")) {
							nbtItem.setInteger("KillAmount", nbtItem.getInteger("KillAmount") + 1);
							currentItem = nbtItem.getItem();
//							inventory.setItem(i, plugin.armorConstructor.createDestroyerArmor(currentItem));
						}
					}
				}
			}
		}
	}

	public void seaGreedEffects(Player player) {
		giveItem(new ItemStack(Material.DIAMOND_BLOCK, 20), player);
		giveItem(new ItemStack(Material.GOLD_BLOCK, 100), player);
		player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 72000, 2, false, false));
		player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 72000, 1, false, false));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 72000, 1, false, false));
		player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 72000, 1, false, false));
	}

	public void giveItem(ItemStack item, Player player) {
		PlayerInventory inventory = player.getInventory();
		if (inventory.firstEmpty() == -1) {
			player.getWorld().dropItem(player.getLocation().add(0.0D, 0.5D, 0.0D), item);
		} else {inventory.addItem(item);}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player player) {
			PlayerInventory inventory = player.getInventory();
			if (plugin.IsFullCustomSet(ArmorType.SPEEDSTER, inventory) && plugin.configHandler.getConfig("config").getBoolean("speedsterarmor.enabled")) {
				if (player.hasPotionEffect(PotionEffectType.SPEED)) {if (player.getPotionEffect(PotionEffectType.SPEED).getAmplifier() == 1) {player.removePotionEffect(PotionEffectType.SPEED);}}
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();
		if (event.getAction().equals(Action.LEFT_CLICK_AIR) && player.isSneaking()) {
			if (plugin.IsFullCustomSet(ArmorType.END, inventory) && player.getWorld().getEnvironment().equals(Environment.THE_END) && plugin.configHandler.getConfig("config").getBoolean("endarmor.enabled")) {
				if (!(teleportCooldown.contains(player))) {
					Block block = player.getTargetBlock(null, 10);
					Location playerlocation = player.getLocation();
					World world = block.getWorld();
					double teleportX = block.getX();
					double teleportY = block.getY() + 1;
					double teleportZ = block.getZ();
					float teleportYaw = playerlocation.getYaw();
					float teleportPitch = playerlocation.getPitch();
					Location teleportlocation = new Location(world, teleportX, teleportY, teleportZ, teleportYaw, teleportPitch);
					player.teleport(teleportlocation);
					teleportCooldown.add(player);
					player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 5, 5);
					//Teleport Cooldown
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> teleportCooldown.remove(player), 20L);
				}
			}
		}
	}
}
