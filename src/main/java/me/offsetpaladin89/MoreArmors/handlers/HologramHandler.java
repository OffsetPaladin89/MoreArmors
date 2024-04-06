package me.offsetpaladin89.MoreArmors.handlers;

import com.cryptomorin.xseries.ReflectionUtils;
import joptsimple.internal.Reflection;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityTypes;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftLivingEntity;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.w3c.dom.Text;

import java.awt.*;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

import static com.cryptomorin.xseries.ReflectionUtils.getCraftClass;
import static com.cryptomorin.xseries.ReflectionUtils.getNMSClass;

public class HologramHandler {

	private static MoreArmorsMain plugin;

	public HologramHandler(MoreArmorsMain plugin) {
		HologramHandler.plugin = plugin;
	}

	public void createDamageHologram(Player p, Location playerLoc, Location loc, String s, Long delay) throws Throwable {

		Vector dir = playerLoc.getDirection().normalize();
		Location spawnLoc = playerLoc.add(loc.toVector().subtract(playerLoc.toVector()).multiply(0.65));
		Random random = new Random();
		Vector hDir = new Vector(dir.getZ(), 0, -dir.getX()).normalize();
		spawnLoc.add(hDir.multiply(random.nextDouble() * 1.5 - 0.75)).add(0, random.nextDouble() * 1.5 - 0.5, 0);
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		Object worldHandle = lookup.findVirtual(ReflectionUtils.getCraftClass("CraftWorld"), "getHandle", MethodType.methodType(getNMSClass("server.level", "WorldServer"))).invoke(loc.getWorld());
		Object textEntity = lookup.findConstructor(getNMSClass("world.entity","Display$TextDisplay"), MethodType.methodType(void.class, getNMSClass("world.entity", "EntityTypes"), getNMSClass("world.level", "World"))).invoke(EntityTypes.aY, worldHandle);
		Object convertedEntity = lookup.findVirtual(getNMSClass("world.entity","Display"), "getBukkitEntity", MethodType.methodType(getCraftClass("entity.CraftEntity"))).invoke(textEntity);
		Object entityHandle = lookup.findVirtual(ReflectionUtils.getCraftClass("entity.CraftEntity"), "getHandle", MethodType.methodType(getNMSClass("world.entity", "Entity"))).invoke(convertedEntity);
		Object dataWatcher = lookup.findVirtual(getNMSClass("world.entity", "Entity"), "an", MethodType.methodType(getNMSClass("network.syncher", "DataWatcher"))).invoke(entityHandle);

		Object spawnPacket = lookup.findConstructor(getNMSClass("network.protocol.game", "PacketPlayOutSpawnEntity"), MethodType.methodType(void.class, getNMSClass("world.entity", "Entity"))).invoke(textEntity);
		ReflectionUtils.sendPacket(p, spawnPacket);

//		Object metaDataPacket = lookup.findConstructor(getNMSClass("network.protocol.game", "PacketPlayOutEntityMetadata"), MethodType.methodType(void.class, int.class, List.class)).invoke(100);
//		ReflectionUtils.sendPacket(p, metaDataPacket);

//		Entity init = loc.getWorld().spawnEntity(spawnLoc, EntityType.TEXT_DISPLAY);
//		TextDisplay textDisplay = (TextDisplay) init;
//		PersistentDataContainer pdc = textDisplay.getPersistentDataContainer();
//		pdc.set(new NamespacedKey(plugin, "HologramEntity"), PersistentDataType.BOOLEAN, true);
//		textDisplay.setText(plugin.convertColoredString(s));
//		textDisplay.setBillboard(Display.Billboard.CENTER);

//		new BukkitRunnable() {
//			public void run() {
//				init.remove();
//			}
//		}.runTaskLater(plugin, delay);
	}

	public void createDamageHologram(Player p, Location pLoc, Location loc, Long duration, Double damage) {
		try {
			createDamageHologram(p, pLoc, loc, "&7" + new DecimalFormat("#.#").format(damage), duration);
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
	}

}
