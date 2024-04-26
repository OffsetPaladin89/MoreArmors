package me.offsetpaladin89.MoreArmors;

import com.cryptomorin.xseries.XSound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.items.DestroyerArmor;
import me.offsetpaladin89.MoreArmors.items.EmeraldArmor;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.bukkit.event.inventory.InventoryType.*;

public class Listener implements org.bukkit.event.Listener {

	private final Main plugin;

	private final InventoryType[] blockedInventories = { GRINDSTONE, BLAST_FURNACE, FURNACE, MERCHANT };

	private ArrayList<UUID> cooldowns = new ArrayList<>();

	List<Player> teleportCooldown = new ArrayList<>();

	public Listener(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	public void scanPlayers(Object[] players) {
		FileConfiguration config = plugin.config.getConfig("config");
		for (Object obj : players) {
			if (obj instanceof Player) {
				Player p = (Player) obj;
				PlayerInventory inv = p.getInventory();
				World w = p.getLocation().getWorld();
				if (plugin.IsFullCustomSet("nether", p.getInventory()) && w.getEnvironment().equals(World.Environment.NETHER) && config.getBoolean("netherarmor.enabled")) {
					setHelmetHealth(inv, 20);
					p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 60, 0, false, false));
				} else if (plugin.IsFullCustomSet("end", p.getInventory()) && w.getEnvironment().equals(World.Environment.THE_END) && config.getBoolean("endarmor.enabled")) setHelmetHealth(inv, 20);
				else if (plugin.IsFullCustomSet("seagreed", p.getInventory()) && inWater(p) && config.getBoolean("seagreedarmor.enabled")) p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 60, 0, false, false));
				else if (plugin.IsFullCustomSet("destroyer", p.getInventory()) && config.getBoolean("destroyerarmor.enabled")) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 1, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60, 1, false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 1, false, false));
				} else if (!plugin.isAirOrNull(inv.getHelmet())) {
					ItemStack i = inv.getHelmet();
					NBTItem nbt = new NBTItem(i);
					String cID = nbt.getString("CustomItemID");
					if (cID.equals("nether") || cID.equals("end")) {
						ItemMeta im = i.getItemMeta();
						im.removeAttributeModifier(Attribute.GENERIC_MAX_HEALTH);
						i.setItemMeta(im);
						inv.setHelmet(i);
					}
					else if(cID.equalsIgnoreCase("destroyer") && config.getBoolean("destroyerarmor.enabled")) p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 60, 0, false, false));
				}
			}

		}
	}

	public void setHelmetHealth(PlayerInventory inv, Integer amount) {
		ItemStack effectItem = inv.getHelmet();
		ItemMeta im = effectItem.getItemMeta();
		if (im.getAttributeModifiers(Attribute.GENERIC_MAX_HEALTH) == null) im.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "maxHealth", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		effectItem.setItemMeta(im);
		inv.setHelmet(effectItem);
	}

	private boolean inWater(Player p) {return p.getLocation().getBlock().getType().equals(Material.WATER);}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		NBTItem nbtItem = new NBTItem(event.getItemInHand());
		if (nbtItem.getBoolean("IsCustomItem")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void InventoryClick(InventoryClickEvent event) {
		if (event.getClickedInventory() != null) {
			if (event.isShiftClick()) {
				Inventory inventory = event.getInventory();
				anvilClick(inventory, event);
				for (InventoryType i : blockedInventories) {
					if (plugin.isAirOrNull(event.getCurrentItem())) return;
					NBTItem nbtItem = new NBTItem(event.getCurrentItem());
					if (inventory.getType().equals(i) && nbtItem.getBoolean("IsCustomItem")) {
						event.setCancelled(true);
					}
				}
			} else {
				Inventory inventory = event.getClickedInventory();
				anvilClick(inventory, event);
				for (InventoryType i : blockedInventories) {
					if (plugin.isAirOrNull(event.getCursor())) return;
					NBTItem nbtItem = new NBTItem(event.getCursor());
					if (inventory.getType().equals(i) && nbtItem.getBoolean("IsCustomItem")) {
						event.setCancelled(true);
					}
				}
			}
		}
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
		if (player.getGameMode().equals(GameMode.SPECTATOR) || player.getGameMode().equals(GameMode.CREATIVE) || !plugin.config.getConfig("config").getBoolean("destroyerarmor.enabled")) return;

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
		if (event.getEntity() instanceof Player && plugin.config.getConfig("config").getBoolean("destroyerarmor.enabled")) {
			Player player = (Player) event.getEntity();
			PlayerInventory inventory = player.getInventory();
			if (plugin.isAirOrNull(inventory.getBoots())) {return;}
			NBTItem nbtItem = new NBTItem(inventory.getBoots());
			if (nbtItem.getString("CustomItemID").equals("destroyer")) {event.setCancelled(true);}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {

		FileConfiguration config = plugin.config.getConfig("config");

		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();

		for (int i = 0; i < inventory.getSize(); i++) {
			ItemStack currentItem = inventory.getItem(i);
			if (!plugin.isAirOrNull(currentItem)) {
				NBTItem nbtItem = new NBTItem(currentItem);
				if (nbtItem.getString("CustomItemID").equals("emerald") && config.getBoolean("emeraldarmor.enabled")) {
					if (event.getBlock().getType().equals(Material.EMERALD_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_EMERALD_ORE)) {
						inventory.setItem(i, new EmeraldArmor(currentItem, nbtItem.getInteger("EmeraldCount") + 1).getItem());
					}
				}
			}
		}
		if (plugin.IsFullCustomSet("experience", player.getInventory()) && config.getBoolean("experiencearmor.enabled")) {event.setExpToDrop(event.getExpToDrop() * 2);}
		if (plugin.IsFullCustomSet("miner", player.getInventory()) && config.getBoolean("minerarmor.enabled")) {
			if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {if (player.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier() == 1) {player.removePotionEffect(PotionEffectType.FAST_DIGGING);}}
			player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 1));
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
	}

	private Float oreMultiplier(Player p) {
		PlayerInventory inventory = p.getInventory();
		return ((plugin.matchingCustomItem(inventory.getHelmet(), "seagreed") && plugin.config.getConfig("config").getBoolean("seagreedarmor.enabled") ? 0.5f : 0f)) + ((plugin.matchingCustomItem(inventory.getChestplate(), "seagreed") ? 0.5f : 0f)) + ((plugin.matchingCustomItem(inventory.getLeggings(), "seagreed") ? 0.5f : 0f)) + ((plugin.matchingCustomItem(inventory.getBoots(), "seagreed") ? 0.5f : 0f));
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {

		FileConfiguration config = plugin.config.getConfig("config");

		Player p = e.getPlayer();
		PlayerInventory inv = p.getInventory();
		if (plugin.IsFullCustomSet("seagreed", p.getInventory()) && config.getBoolean("seagreedarmor.enabled")) {
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

		FileConfiguration config = plugin.config.getConfig("config");

		Entity entity = event.getEntity();
		if (!(entity instanceof Player)) {
			if (event.getEntity().getKiller() != null) {
				Player killer = event.getEntity().getKiller();
				PlayerInventory inventory = killer.getInventory();
				if (plugin.IsFullCustomSet("experience", inventory) && config.getBoolean("experiencearmor.enabled")) {event.setDroppedExp(event.getDroppedExp() * 2);}
				if (plugin.IsFullCustomSet("titan", inventory) && config.getBoolean("titanarmor.enabled")) {
					if (killer.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {if (killer.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier() == 0) {killer.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);}}
					killer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0, false, false));
				}
				if (plugin.IsFullCustomSet("seagreed", inventory) && config.getBoolean("seagreedarmor.enabled")) {
					Random r = new Random();
					if (entity.getType().equals(EntityType.ELDER_GUARDIAN) && r.nextDouble() <= 0.25d) {
						killer.sendTitle(Main.convertColoredString("&c&l&kzzz &r&4&lBLESSING OF THE SEA GOD &c&l&kzzz"), "", -1, -1, -1);
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
							inventory.setItem(i, new DestroyerArmor(currentItem, nbtItem.getInteger("KillAmount") + 1).getItem());
						}
					}
				}
			}
		}
	}

	public void seaGreedEffects(Player player) {
		giveItem(new ItemStack(Material.DIAMOND_BLOCK, 20), player);
		giveItem(new ItemStack(Material.GOLD_BLOCK, 100), player);
		player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 72000, 2, false, false));
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 72000, 1, false, false));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 72000, 1, false, false));
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 72000, 1, false, false));
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
			if (plugin.IsFullCustomSet("speedster", inventory) && plugin.config.getConfig("config").getBoolean("speedsterarmor.enabled")) {
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
			if (plugin.IsFullCustomSet("end", inventory) && player.getWorld().getEnvironment().equals(World.Environment.THE_END) && plugin.config.getConfig("config").getBoolean("endarmor.enabled")) {
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
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (!plugin.isAirOrNull(inventory.getItemInMainHand())) {
				NBTItem nbtItem = new NBTItem(inventory.getItemInMainHand());
				if (nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender")) {
					event.setCancelled(true);
				}
			}
		}
	}

	public void anvilClick(Inventory inventory, InventoryClickEvent event) {
		if (inventory.getType().equals(ANVIL) && event.getSlot() == 2) {
			if (!plugin.isAirOrNull(inventory.getItem(2))) {
				if (!inventory.getItem(0).getItemMeta().getDisplayName().equals(inventory.getItem(2).getItemMeta().getDisplayName())) {
					NBTItem nbtItem = new NBTItem(inventory.getItem(0));
					if (nbtItem.getBoolean("IsCustomItem")) {
						event.setCancelled(true);
					}
				}
			}
		}
	}
}