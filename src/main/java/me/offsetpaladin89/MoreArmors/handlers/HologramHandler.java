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

    public void createHologram(Location playerLoc, Location loc, String s, Long delay) {

        Vector direction = playerLoc.getDirection().normalize();
        Location spawnLoc = playerLoc.add(loc.toVector().subtract(playerLoc.toVector()).multiply(0.65)).subtract(0, 0.5, 0);

        Random random = new Random();
        double offsetX = (random.nextDouble() * 1.5 - 0.75);
        double offsetY = (random.nextDouble() - 0.5);

        Vector horizontalDirection = new Vector(direction.getZ(), 0, -direction.getX()).normalize();
        spawnLoc.add(horizontalDirection.multiply(offsetX));
        spawnLoc.add(new Vector(0, offsetY, 0));  // Vertical movement

        Entity main = loc.getWorld().spawnEntity(spawnLoc, EntityType.BAT);
        Entity rider = loc.getWorld().spawnEntity(spawnLoc, EntityType.ARMOR_STAND);

        LivingEntity bat = (LivingEntity) main;
        ArmorStand armorStand = (ArmorStand) rider;

        setPersistentData(armorStand, "HologramEntity", true);
        setPersistentData(bat, "HologramEntity", true);

        configureBat(bat, armorStand);
        configureArmorStand(armorStand, s);

        new BukkitRunnable() {
            public void run() {
                bat.remove();
                armorStand.remove();
            }
        }.runTaskLater(plugin, delay);
    }

    private void setPersistentData(Entity entity, String keyName, boolean value) {
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, keyName);
        pdc.set(key, PersistentDataType.BOOLEAN, value);
    }

    private void configureArmorStand(ArmorStand armorStand, String s) {
        armorStand.setSmall(true);
        armorStand.setInvisible(true);
        armorStand.setInvulnerable(true);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(plugin.convertColoredString(s));
    }

    private void configureBat(LivingEntity bat, ArmorStand armorStand) {
        bat.setInvisible(true);
        bat.setInvulnerable(true);
        bat.setSilent(true);
        bat.setAI(false);
        bat.addPassenger(armorStand);
    }

    public void createDamageHologram(Location playerLoc, Location loc, Long duration, Double damage) {
        createHologram(playerLoc, loc, "&7" + new DecimalFormat("#.#").format(damage), duration);
    }

}
