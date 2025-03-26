package me.offsetpaladin89.MoreArmors.utils.stats;

import me.offsetpaladin89.MoreArmors.enums.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;

public class Stats {

    protected HashMap<Location, Double> additionalDamage = new HashMap<>(), damageMultiplier = new HashMap<>();
    protected HashMap<Location, Double> damageReduction = new HashMap<>();
    protected HashMap<Location, Double> criticalHitChance = new HashMap<>(), criticalHitDamage = new HashMap<>();
    protected HashMap<Location, Double> experienceMultiplier = new HashMap<>();
    protected HashMap<Location, Double> playerScale = new HashMap<>();
    protected HashMap<Location, ArrayList<PotionEffect>> potionEffects = new HashMap<>();

    public Stats() {
        for(Location type : Location.values()) {
            additionalDamage.put(type, 0.0);
            damageMultiplier.put(type, 0.0);
            damageReduction.put(type, 0.0);
            criticalHitChance.put(type, 0.0);
            criticalHitDamage.put(type, 0.0);
            experienceMultiplier.put(type, 0.0);
            playerScale.put(type, 0.0);
        }
    }

    // Accessors

    public double getAdditionalDamage() {
        double additionalDamage = 0.0;
        for(Location type : Location.values()) additionalDamage += this.additionalDamage.get(type);
        return additionalDamage;
    }

    public double getAdditionalDamage(Location type) {
        return additionalDamage.get(type);
    }

    public double getDamageMultiplier() {
        double damageMultiplier = 0.0;
        for(Location type : Location.values()) damageMultiplier += this.damageMultiplier.get(type);
        return damageMultiplier;
    }

    public double getDamageMultiplier(Location type) {
        return damageMultiplier.get(type);
    }

    public double getDamageReduction() {
        double damageReduction = 0.0;
        for(Location type : Location.values()) damageReduction += this.damageReduction.get(type);
        return damageReduction;
    }

    public double getDamageReduction(Location type) {
        return damageReduction.get(type);
    }

    public double getCriticalHitChance() {
        double criticalHitChance = 0.0;
        for(Location type : Location.values()) criticalHitChance += this.criticalHitChance.get(type);
        return criticalHitChance;
    }

    public double getCriticalHitChance(Location type) {
        return criticalHitChance.get(type);
    }

    public double getCriticalHitDamage() {
        double criticalHitDamage = 0.0;
        for(Location type : Location.values()) criticalHitDamage += this.criticalHitDamage.get(type);
        return criticalHitDamage;
    }

    public double getCriticalHitDamage(Location type) {
        return criticalHitDamage.get(type);
    }

    public double getExperienceMultiplier() {
        double experienceMultiplier = 0.0;
        for(Location type : Location.values()) experienceMultiplier += this.experienceMultiplier.get(type);
        return experienceMultiplier;
    }

    public double getExperienceMultiplier(Location type) {
        return experienceMultiplier.get(type);
    }

    public double getPlayerScale() {
        double playerScale = 0.0;
        for(Location type : Location.values()) playerScale += this.playerScale.get(type);
        return playerScale;
    }

    public double getPlayerScale(Location type) {
        return playerScale.get(type);
    }

    public ArrayList<PotionEffect> getPotionEffects() {
        ArrayList<PotionEffect> effects = new ArrayList<>();
        for(Location type : Location.values()) effects.addAll(potionEffects.get(type));
        return effects;
    }

    public ArrayList<PotionEffect> getPotionEffects(Location type) {
        return potionEffects.get(type);
    }

    // Mutators

    public void setExperienceMultiplier(double experienceMultiplier) {
        this.experienceMultiplier.put(Location.ALL, experienceMultiplier);
    }

    public void setExperienceMultiplier(double experienceMultiplier, Location type) {
        this.experienceMultiplier.put(type, experienceMultiplier);
    }

    public void setAdditionalDamage(double additionalDamage) {
        this.additionalDamage.put(Location.ALL, additionalDamage);
    }

    public void setAdditionalDamage(double additionalDamage, Location type) {
        this.additionalDamage.put(type, additionalDamage);
    }

    public void setDamageMultiplier(double damageMultiplier) {
        this.damageMultiplier.put(Location.ALL, damageMultiplier);
    }

    public void setDamageMultiplier(double damageMultiplier, Location type) {
        this.damageMultiplier.put(type, damageMultiplier);
    }

    public void setDamageReduction(double damageReduction) {
        this.damageReduction.put(Location.ALL, damageReduction);
    }

    public void setDamageReduction(double damageReduction, Location type) {
        this.damageReduction.put(type, damageReduction);
    }

    public void setCriticalHitChance(double criticalHitChance) {
        this.criticalHitChance.put(Location.ALL, criticalHitChance);
    }

    public void setCriticalHitChance(double criticalHitChance, Location type) {
        this.criticalHitChance.put(type, criticalHitChance);
    }

    public void setCriticalHitDamage(double criticalHitDamage) {
        this.criticalHitDamage.put(Location.ALL, criticalHitDamage);
    }

    public void setCriticalHitDamage(double criticalHitDamage, Location type) {
        this.criticalHitDamage.put(type, criticalHitDamage);
    }

    public void setPlayerScale(double playerScale) {
        this.playerScale.put(Location.ALL, playerScale);
    }

    public void setPlayerScale(double playerScale, Location type) {
        this.playerScale.put(type, playerScale);
    }

    public void addPotionEffect(PotionEffectType effectType, int level, Location type) {
        this.potionEffects.get(type).add(new PotionEffect(effectType, 30, level, false, false));
    }
}