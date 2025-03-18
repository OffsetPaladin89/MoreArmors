package me.offsetpaladin89.MoreArmors.utils.stats;

public class Stats {

    protected double additionalDamage, damageMultiplier;
    protected double damageReduction;
    protected double criticalHitChance, criticalHitDamage;
    protected double experienceMultiplier;
    protected double playerScale;

    public Stats() {

    }

    public double getExperienceMultiplier() {
        return experienceMultiplier;
    }

    public void setExperienceMultiplier(double experienceMultiplier) {
        this.experienceMultiplier = experienceMultiplier;
    }

    public double getAdditionalDamage() {
        return additionalDamage;
    }

    public void setAdditionalDamage(double additionalDamage) {
        this.additionalDamage = additionalDamage;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(double damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public double getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(double damageReduction) {
        this.damageReduction = damageReduction;
    }

    public double getCriticalHitChance() {
        return criticalHitChance;
    }

    public void setCriticalHitChance(double criticalHitChance) {
        this.criticalHitChance = criticalHitChance;
    }

    public double getCriticalHitDamage() {
        return criticalHitDamage;
    }

    public void setCriticalHitDamage(double criticalHitDamage) {
        this.criticalHitDamage = criticalHitDamage;
    }

    public double getPlayerScale() {
        return playerScale;
    }

    public void setPlayerScale(double playerScale) {
        this.playerScale = playerScale;
    }
}