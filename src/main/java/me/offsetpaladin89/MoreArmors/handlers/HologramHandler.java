package me.offsetpaladin89.MoreArmors.handlers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.cryptomorin.xseries.ReflectionUtils;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import net.minecraft.network.chat.ChatModifier;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.server.level.WorldServer;
import net.minecraft.util.FormattedString;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.awt.*;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
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

		PacketContainer textDisplay = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);
		textDisplay.getIntegers().write(0, random.nextInt());
		textDisplay.getUUIDs().write(0, UUID.randomUUID());
		textDisplay.getIntegers().write(1, 103);
		textDisplay.getDoubles().write(0, spawnLoc.getX());
		textDisplay.getDoubles().write(1, spawnLoc.getY());
		textDisplay.getDoubles().write(2, spawnLoc.getZ());
		ProtocolLibrary.getProtocolManager().sendServerPacket(p, textDisplay);

//		Entity init = loc.getWorld().spawnEntity(spawnLoc, EntityType.TEXT_DISPLAY);
//		TextDisplay textDisplay = (TextDisplay) init;
//		PersistentDataContainer pdc = textDisplay.getPersistentDataContainer();
//		pdc.set(new NamespacedKey(plugin, "HologramEntity"), PersistentDataType.BOOLEAN, true);
//		textDisplay.setText(plugin.convertColoredString(s));
//		textDisplay.setBillboard(Display.Billboard.CENTER);

		new BukkitRunnable() {
			public void run() {
				PacketContainer destroyDisplay = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
				destroyDisplay.getIntegers().write(0, textDisplay.getIntegers().getValues().get(0));
				ProtocolLibrary.getProtocolManager().sendServerPacket(p, textDisplay);
			}
		}.runTaskLater(plugin, delay);

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
