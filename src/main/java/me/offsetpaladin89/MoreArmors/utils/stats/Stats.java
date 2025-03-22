package me.offsetpaladin89.MoreArmors.utils.stats;

import me.offsetpaladin89.MoreArmors.enums.WorldType;
import org.bukkit.World;

import java.util.HashMap;

public class Stats {

    protected HashMap<WorldType, Double> additionalDamage = new HashMap<>(), damageMultiplier = new HashMap<>();
    protected HashMap<WorldType, Double> damageReduction = new HashMap<>();
    protected HashMap<WorldType, Double> criticalHitChance = new HashMap<>(), criticalHitDamage = new HashMap<>();
    protected HashMap<WorldType, Double> experienceMultiplier = new HashMap<>();
    protected HashMap<WorldType, Double> playerScale = new HashMap<>();

    public Stats() {
        for(WorldType type : WorldType.values()) {
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
        for(WorldType type : WorldType.values()) additionalDamage += this.additionalDamage.get(type);
        return additionalDamage;
    }

    public double getAdditionalDamage(WorldType type) {
        return additionalDamage.get(type);
    }

    public double getDamageMultiplier() {
        double damageMultiplier = 0.0;
        for(WorldType type : WorldType.values()) damageMultiplier += this.damageMultiplier.get(type);
        return damageMultiplier;
    }

    public double getDamageMultiplier(WorldType type) {
        return damageMultiplier.get(type);
    }

    public double getDamageReduction() {
        double damageReduction = 0.0;
        for(WorldType type : WorldType.values()) damageReduction += this.damageReduction.get(type);
        return damageReduction;
    }

    public double getDamageReduction(WorldType type) {
        return damageReduction.get(type);
    }

    public double getCriticalHitChance() {
        double criticalHitChance = 0.0;
        for(WorldType type : WorldType.values()) criticalHitChance += this.criticalHitChance.get(type);
        return criticalHitChance;
    }

    public double getCriticalHitChance(WorldType type) {
        return criticalHitChance.get(type);
    }

    public double getCriticalHitDamage() {
        double criticalHitDamage = 0.0;
        for(WorldType type : WorldType.values()) criticalHitDamage += this.criticalHitDamage.get(type);
        return criticalHitDamage;
    }

    public double getCriticalHitDamage(WorldType type) {
        return criticalHitDamage.get(type);
    }

    public double getExperienceMultiplier() {
        double experienceMultiplier = 0.0;
        for(WorldType type : WorldType.values()) experienceMultiplier += this.experienceMultiplier.get(type);
        return experienceMultiplier;
    }

    public double getExperienceMultiplier(WorldType type) {
        return experienceMultiplier.get(type);
    }

    public double getPlayerScale() {
        double playerScale = 0.0;
        for(WorldType type : WorldType.values()) playerScale += this.playerScale.get(type);
        return playerScale;
    }

    public double getPlayerScale(WorldType type) {
        return playerScale.get(type);
    }

    // Mutators

    public void setExperienceMultiplier(double experienceMultiplier) {
        this.experienceMultiplier.put(WorldType.ALL, experienceMultiplier);
    }

    public void setExperienceMultiplier(double experienceMultiplier, WorldType type) {
        this.experienceMultiplier.put(type, experienceMultiplier);
    }

    public void setAdditionalDamage(double additionalDamage) {
        this.additionalDamage.put(WorldType.ALL, additionalDamage);
    }

    public void setAdditionalDamage(double additionalDamage, WorldType type) {
        this.additionalDamage.put(type, additionalDamage);
    }

    public void setDamageMultiplier(double damageMultiplier) {
        this.damageMultiplier.put(WorldType.ALL, damageMultiplier);
    }

    public void setDamageMultiplier(double damageMultiplier, WorldType type) {
        this.damageMultiplier.put(type, damageMultiplier);
    }

    public void setDamageReduction(double damageReduction) {
        this.damageReduction.put(WorldType.ALL, damageReduction);
    }

    public void setDamageReduction(double damageReduction, WorldType type) {
        this.damageReduction.put(type, damageReduction);
    }

    public void setCriticalHitChance(double criticalHitChance) {
        this.criticalHitChance.put(WorldType.ALL, criticalHitChance);
    }

    public void setCriticalHitChance(double criticalHitChance, WorldType type) {
        this.criticalHitChance.put(type, criticalHitChance);
    }

    public void setCriticalHitDamage(double criticalHitDamage) {
        this.criticalHitDamage.put(WorldType.ALL, criticalHitDamage);
    }

    public void setCriticalHitDamage(double criticalHitDamage, WorldType type) {
        this.criticalHitDamage.put(type, criticalHitDamage);
    }

    public void setPlayerScale(double playerScale) {
        this.playerScale.put(WorldType.ALL, playerScale);
    }

    public void setPlayerScale(double playerScale, WorldType type) {
        this.playerScale.put(type, playerScale);
    }
}