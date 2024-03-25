package me.offsetpaladin89.MoreArmors.listeners;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.bukkit.Material.*;

public class MoreArmorsListener implements Listener {

	private final MoreArmorsMain plugin;

	List<Player> teleportCooldown = new ArrayList<>();

	public MoreArmorsListener(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {

		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();

		for(int i = 0; i < inventory.getSize(); i++) {
			ItemStack currentItem = inventory.getItem(i);
			if(!plugin.isAirOrNull(currentItem)) {
				NBTItem nbtItem = new NBTItem(currentItem);
				if (nbtItem.getString("CustomItemID").equals("emerald")) {
					if (event.getBlock().getType().equals(Material.EMERALD_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_EMERALD_ORE)) {
						nbtItem.setInteger("EmeraldCount", nbtItem.getInteger("EmeraldCount") + 1);
						currentItem = nbtItem.getItem();
						inventory.setItem(i, plugin.armorConstructor.createEmeraldArmor(currentItem));
					}
				}
			}
		}
		if (plugin.IsFullCustomSet("experience", player.getInventory())) { event.setExpToDrop(event.getExpToDrop() * 2); }
		if (plugin.IsFullCustomSet("miner", player.getInventory())) {
			if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)) { if (player.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier() == 1) { player.removePotionEffect(PotionEffectType.FAST_DIGGING); }}
			player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 1));
		}

		if(player.isInWater()) {
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
		return ((plugin.matchingCustomItem(inventory.getHelmet(), "seagreed") ? 0.5f : 0f)) +
				((plugin.matchingCustomItem(inventory.getChestplate(), "seagreed") ? 0.5f : 0f)) +
				((plugin.matchingCustomItem(inventory.getLeggings(), "seagreed") ? 0.5f : 0f)) +
				((plugin.matchingCustomItem(inventory.getBoots(), "seagreed") ? 0.5f : 0f));
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (plugin.IsFullCustomSet("seagreed", p.getInventory())) {
			p.setWalkSpeed(0.2F);
			if (p.isSwimming()) {
				Vector dir = p.getLocation().getDirection().normalize().multiply(1.4D); // 3 - 1.6
				Vector vec = new Vector(dir.getX(), dir.getY(), dir.getZ());
				p.setVelocity(vec);
			}
		}
	}

	@EventHandler
	public void onEntityDeath (EntityDeathEvent event){
		Entity entity = event.getEntity();
		if (!(entity instanceof Player)) {
			if (event.getEntity().getKiller() != null) {
				Player killer = event.getEntity().getKiller();
				PlayerInventory inventory = killer.getInventory();
				if (plugin.IsFullCustomSet("experience", inventory)) { event.setDroppedExp(event.getDroppedExp() * 2); }
				if (plugin.IsFullCustomSet("titan", inventory)) {
					if (killer.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) { if (killer.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier() == 0) { killer.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE); }}
					killer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0, false, false));
				}
				if(plugin.IsFullCustomSet("seagreed", inventory)) {
					Random r = new Random();

					if(entity.getType().equals(EntityType.ELDER_GUARDIAN) && r.nextDouble() <= 0.25d) {
						killer.sendTitle(plugin.convertColoredString("&c&l&kzzz &r&4&lBLESSING OF THE SEA GOD &c&l&kzzz"), "", -1, -1, -1);
						killer.playSound(killer, Sound.BLOCK_END_PORTAL_SPAWN, SoundCategory.MASTER, 1, 1.4f);
						for(int i = 0; i < 3; i++) { killer.getWorld().strikeLightningEffect(killer.getLocation().subtract((r.nextInt(0, 280) - 140f) / 100f, 1, (r.nextInt(0, 280) - 140f) / 100f)); }
						seaGreedEffects(killer);
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
		} else { inventory.addItem(item); }
	}

	@EventHandler
	public void onEntityDamage (EntityDamageEvent event){
		if (event.getEntity() instanceof Player player) {
			PlayerInventory inventory = player.getInventory();
			if (plugin.IsFullCustomSet("speedster", inventory)) {
				if (player.hasPotionEffect(PotionEffectType.SPEED)) { if (player.getPotionEffect(PotionEffectType.SPEED).getAmplifier() == 1) { player.removePotionEffect(PotionEffectType.SPEED); }}
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
			}
		}
	}

	@EventHandler
	public void onPlayerInteract (PlayerInteractEvent event){
		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();
		if (event.getAction().equals(Action.LEFT_CLICK_AIR) && player.isSneaking()) {
			if (plugin.IsFullCustomSet("end", inventory) && player.getWorld().getEnvironment().equals(Environment.THE_END)) {
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
