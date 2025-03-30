package me.offsetpaladin89.MoreArmors.handlers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataValue;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class HologramHandler {

	private static MoreArmorsMain plugin;

	public HologramHandler(MoreArmorsMain plugin) {
		HologramHandler.plugin = plugin;
	}

	public void createDamageHologram(Player p, Location playerLoc, LivingEntity e, String s, Long delay) {

		Vector dir = playerLoc.getDirection().normalize();
		Location spawnLoc = playerLoc.add(e.getLocation().toVector().subtract(playerLoc.toVector()).multiply(0.65));
		Random random = new Random();
		Vector hDir = new Vector(dir.getZ(), 0, -dir.getX()).normalize();
		spawnLoc.add(hDir.multiply(random.nextDouble() * 1.5 - 0.75)).add(0, (p.getEyeHeight() + e.getEyeHeight()) / 2 + random.nextDouble() - 0.5, 0);

		// create a unique entity ID.
		int entityID = random.nextInt();

		// register spawn entity packet
		PacketContainer textDisplay = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);
		// apply the created entity ID to the packet
		textDisplay.getIntegers().write(0, entityID);
		// set the entity type to a text display
		textDisplay.getEntityTypeModifier().write(0, EntityType.TEXT_DISPLAY);
		// give the text display a UUID
		textDisplay.getUUIDs().write(0, UUID.randomUUID());
		// sets position of the text display
		textDisplay.getDoubles().write(0, spawnLoc.getX());
		textDisplay.getDoubles().write(1, spawnLoc.getY());
		textDisplay.getDoubles().write(2, spawnLoc.getZ());
		// send the spawn entity packet to the client
		ProtocolLibrary.getProtocolManager().sendServerPacket(p, textDisplay);

		// register the entity meta data packet
		PacketContainer textDisplayData = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
		// provide what entity we are modifying
		textDisplayData.getIntegers().write(0, entityID);
		// create a list of the values to change
		List<WrappedDataValue> dataValues = List.of(
				// make the display pivot around the center
				new WrappedDataValue(15, WrappedDataWatcher.Registry.get(Byte.class), (byte) 0x03),
				// apply the string to the text display
				new WrappedDataValue(23, WrappedDataWatcher.Registry.getChatComponentSerializer(), WrappedChatComponent.fromText(Util.colorString(s)).getHandle())
		);
		// write the data values to the packet
		textDisplayData.getDataValueCollectionModifier().write(0, dataValues);
		// send the metadata packet to the client
		ProtocolLibrary.getProtocolManager().sendServerPacket(p, textDisplayData);

		// register the destroy entity packet
		PacketContainer destroyDisplay = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
		// provide a list of the entities we want to destroy
		destroyDisplay.getIntLists().write(0, List.of(entityID));

		new BukkitRunnable() {
			public void run() {
				// send the destroy entity packet after a set amount of time, delay
				ProtocolLibrary.getProtocolManager().sendServerPacket(p, destroyDisplay);
			}
		}.runTaskLater(plugin, delay);
	}

	public void createDamageHologram(Player p, Location pLoc, LivingEntity e, Long duration, Double damage, boolean isCrit, int critLevel) {
		try {

			String color;
			if(!isCrit) color = "&7";
			else {
				color = switch (critLevel) {
					case 1 -> "&e";
					case 2 -> "&6";
					case 3 -> "&c";
					case 4 -> "&4";
					default -> "&4&l";
				};
			}

			createDamageHologram(p, pLoc, e, String.format("%s%,.1f", color, damage), duration);
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
	}

}
