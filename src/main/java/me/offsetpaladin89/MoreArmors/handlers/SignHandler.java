package me.offsetpaladin89.MoreArmors.handlers;

import com.cryptomorin.xseries.ReflectionUtils;
import io.netty.util.internal.ReflectionUtil;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

import static com.cryptomorin.xseries.ReflectionUtils.getNMSClass;

public class SignHandler implements Listener {

	private final MoreArmorsMain plugin;

	public SignHandler(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	public void open(Player p){
		try {
			MethodHandles.Lookup lookup = MethodHandles.lookup();
			Location l = p.getLocation();
			Object blockPos = lookup.findConstructor(getNMSClass("core", "BlockPosition"), MethodType.methodType(void.class, int.class, int.class, int.class)).invoke(l.getBlockX(), l.getBlockY(), l.getBlockZ());
			Object packet = lookup.findConstructor(getNMSClass("network.protocol.game", "PacketPlayOutOpenSignEditor"), MethodType.methodType(void.class, getNMSClass("core", "BlockPosition"), boolean.class)).invoke(blockPos, true);
			ReflectionUtils.sendPacket(p, packet);
		}
		catch(Throwable e) {
			e.printStackTrace();
		}
	}

}
