package me.offsetpaladin89.MoreArmors.handlers;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class HologramHandler {

    private static MoreArmorsMain plugin;

    private ArrayList<Entity> entityList;

    public HologramHandler(MoreArmorsMain plugin) {
        HologramHandler.plugin = plugin;
    }

    public void createDamageHologram(Location playerLoc, Location loc, String s, Long delay) {

        Vector dir = playerLoc.getDirection().normalize();
        Location spawnLoc = playerLoc.add(loc.toVector().subtract(playerLoc.toVector()).multiply(0.65));

        Random random = new Random();
        Vector hDir = new Vector(dir.getZ(), 0, -dir.getX()).normalize();
        spawnLoc.add(hDir.multiply(random.nextDouble() * 1.5 - 0.75)).add(0, random.nextDouble() * 1.5 - 0.5, 0);

        Entity init = loc.getWorld().spawnEntity(spawnLoc, EntityType.ARMOR_STAND);
        ArmorStand armorStand = (ArmorStand) init;
        PersistentDataContainer pdc = armorStand.getPersistentDataContainer();
        pdc.set(new NamespacedKey(plugin, "HologramEntity"), PersistentDataType.BOOLEAN, true);
        armorStand.setSmall(true);
        armorStand.setInvisible(true);
        armorStand.setMarker(true);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(plugin.convertColoredString(s));

        new BukkitRunnable() {
            public void run() { armorStand.remove(); }
        }.runTaskLater(plugin, delay);
    }

    public void createDamageHologram(Location playerLoc, Location loc, Long duration, Double damage) {
        createDamageHologram(playerLoc, loc, "&7" + new DecimalFormat("#.#").format(damage), duration);
    }

}
