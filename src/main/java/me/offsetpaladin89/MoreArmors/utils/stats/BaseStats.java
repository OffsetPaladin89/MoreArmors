package me.offsetpaladin89.MoreArmors.utils.stats;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import de.tr7zw.changeme.nbtapi.iface.ReadableNBT;
import de.tr7zw.changeme.nbtapi.iface.ReadableNBTList;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Location;
import me.offsetpaladin89.MoreArmors.enums.StatType;
import me.offsetpaladin89.MoreArmors.items.armors.CustomArmor;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseStats {

    protected HashMap<Location, HashMap<StatType, Double>> stats;
    protected HashMap<Location, ArrayList<PotionEffect>> potions;

    public BaseStats() {
        this.stats = new HashMap<>();
        this.potions = new HashMap<>();

        for(Location loc : Location.values()) {
            stats.put(loc, new HashMap<>());
            for(StatType stat : StatType.values()) stats.get(loc).put(stat, 0d);
            potions.put(loc, new ArrayList<>());
        }
    }

    public double getStat(Location loc, StatType type) {
        return stats.get(loc).get(type);
    }

    public double getStat(Entity e, StatType type) {
        if(e == null) return 0d;

        double val = 0d;
        ArrayList<Location> locations = Location.getLocation(e);
        for(Location loc : locations) {
            val += stats.get(loc).get(type);
        }

        return val;
    }

    public void increaseStat(Location loc, StatType type, Double val) {
        stats.get(loc).put(type, stats.get(loc).get(type) + val);
    }

    public void setStat(Location loc, StatType type, Double val) {
        stats.get(loc).put(type, val);
    }

    public void addPotionEffect(PotionEffectType effectType, int level, Location type) {
        this.potions.get(type).add(new PotionEffect(effectType, 30, level, false, false));
    }

    public void addPotionEffect(PotionEffectType effectType, int duration, int level, Location type) {
        this.potions.get(type).add(new PotionEffect(effectType, duration, level, false, false));
    }

    public ArrayList<PotionEffect> getPotionEffects(Player p) {
        ArrayList<Location> locations = Location.getLocation(p);

        ArrayList<PotionEffect> effects = new ArrayList<>();
        for(Location loc : locations) effects.addAll(potions.get(loc));

        return effects;
    }

    public static BaseStats getStats(ItemStack[] armor, Player p) {
        BaseStats stats = new BaseStats();

        ArrayList<Location> locations = Location.getLocation(p);

        for(ItemStack i : armor) {
            if(Util.isAirOrNull(i) || !Util.isCustomItem(i)) continue;

            NBT.get(i, nbt -> {
                for(Location loc : locations) {

                    ReadableNBT base = nbt.resolveCompound("Stats").resolveCompound(loc.name);
                    for (StatType stat : StatType.values()) {
                        if (base.getOrNull(stat.name, Double.class) == null) continue;
                        stats.increaseStat(loc, stat, base.getDouble(stat.name));
                    }

                    ArrayList<PotionEffect> effects = stats.potions.get(loc);

                    ReadableNBTList<ReadWriteNBT> potions = base.getCompoundList("PotionEffects");
                    for (int n = 0; n < potions.size(); n++) {
                        ReadWriteNBT compound = potions.get(n);
                        PotionEffectType potionEffectType = Registry.EFFECT.get(NamespacedKey.fromString(compound.getString("Type")));
                        int level = compound.getInteger("Level");
                        effects.add(new PotionEffect(potionEffectType, 30, level, false, false));
                    }

                    stats.potions.put(loc, effects);
                }
            });
        }

        CustomArmor fullSet = ArmorType.fullSetType(armor);

        if(fullSet == null) return stats;

        BaseStats setStats = fullSet.getSetStats();

        for(Location loc : locations) {
            for (StatType stat : StatType.values()) stats.increaseStat(loc, stat, setStats.getStat(loc, stat));

            ArrayList<PotionEffect> effects = stats.potions.get(loc);

            effects.addAll(setStats.potions.get(loc));
            stats.potions.put(loc, effects);
        }

        return stats;
    }
}