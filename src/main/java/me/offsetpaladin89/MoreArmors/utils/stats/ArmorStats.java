package me.offsetpaladin89.MoreArmors.utils.stats;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import de.tr7zw.changeme.nbtapi.iface.ReadableNBT;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.enums.WorldType;
import me.offsetpaladin89.MoreArmors.items.armors.CustomArmor;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.attribute.AttributeModifier.Operation.ADD_NUMBER;
import static org.bukkit.attribute.AttributeModifier.Operation.ADD_SCALAR;
import static org.bukkit.inventory.EquipmentSlotGroup.ARMOR;

public class ArmorStats extends Stats {

    protected double maxHealth;
    protected double armor, armorToughness;
    protected double movementSpeed;
    protected double blockBreakSpeed;
    protected double submergedMiningSpeed;

    public ArmorStats(double armor, double armorToughness) {
        this.armor = armor;
        this.armorToughness = armorToughness;
    }
    public ArmorStats(double armor) {
        this.armor = armor;
    }

    public ArmorStats() {
    }

    public void setStats(ItemStack item, ArmorType armorID, SlotType slot) {
        ItemMeta itemMeta = item.getItemMeta();

        ListMultimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();

        NamespacedKey pluginKey = new NamespacedKey("morearmors", String.format("%s_%s", armorID, slot).toLowerCase());

        if(maxHealth != 0) attributeModifiers.put(Attribute.MAX_HEALTH, new AttributeModifier(pluginKey, maxHealth, ADD_NUMBER, ARMOR));
        if(armor != 0) attributeModifiers.put(Attribute.ARMOR, new AttributeModifier(pluginKey, armor, ADD_NUMBER, ARMOR));
        if(armorToughness != 0) attributeModifiers.put(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(pluginKey, armorToughness, ADD_NUMBER, ARMOR));
        if(blockBreakSpeed != 0) attributeModifiers.put(Attribute.BLOCK_BREAK_SPEED, new AttributeModifier(pluginKey, blockBreakSpeed, ADD_SCALAR, ARMOR));
        if(submergedMiningSpeed != 0) attributeModifiers.put(Attribute.SUBMERGED_MINING_SPEED, new AttributeModifier(pluginKey, submergedMiningSpeed, ADD_SCALAR, ARMOR));
        if(movementSpeed != 0) attributeModifiers.put(Attribute.MOVEMENT_SPEED, new AttributeModifier(pluginKey, submergedMiningSpeed, ADD_NUMBER, ARMOR));

        itemMeta.setAttributeModifiers(attributeModifiers);

        item.setItemMeta(itemMeta);

        NBT.modify(item, nbt -> {

            ReadWriteNBT baseCompound = nbt.resolveOrCreateCompound("Stats");

            if(maxHealth != 0) baseCompound.setDouble("MaxHealth", maxHealth);
            if(armor != 0) baseCompound.setDouble("Armor", armor);
            if(armorToughness != 0) baseCompound.setDouble("ArmorToughness", armorToughness);
            if(movementSpeed != 0) baseCompound.setDouble("MovementSpeed", movementSpeed);
            if(blockBreakSpeed != 0) baseCompound.setDouble("BlockBreakSpeed", blockBreakSpeed);
            if(submergedMiningSpeed != 0) baseCompound.setDouble("SubmergedMiningSpeed", submergedMiningSpeed);

            for(WorldType type : WorldType.values()) {

                ReadWriteNBT worldCompound = baseCompound.resolveOrCreateCompound("Worlds").resolveOrCreateCompound(type.name);

                if(getAdditionalDamage(type) != 0) worldCompound.setDouble("AdditionalDamage", additionalDamage.get(type));
                if(getDamageMultiplier(type) != 0) worldCompound.setDouble("DamageMultiplier", damageMultiplier.get(type));
                if(getDamageReduction(type) != 0) worldCompound.setDouble("DamageReduction", damageReduction.get(type));
                if(getCriticalHitChance(type) != 0) worldCompound.setDouble("CriticalHitChance", criticalHitChance.get(type));
                if(getCriticalHitDamage(type) != 0) worldCompound.setDouble("CriticalHitDamage", criticalHitDamage.get(type));
                if(getExperienceMultiplier(type) != 0) worldCompound.setDouble("ExperienceMultiplier", experienceMultiplier.get(type));
                if(getPlayerScale(type) != 0) worldCompound.setDouble("PlayerScale", playerScale.get(type));
            }
        });
    }

    public void getStats(ItemStack[] armor, World.Environment env) {
        for(ItemStack i : armor) {
            if(Util.isAirOrNull(i) || !Util.isCustomItem(i)) continue;

            WorldType worldType = WorldType.getWorldType(env);

            NBT.get(i, nbt -> {

                for(WorldType type : WorldType.values()) {

                    ReadableNBT worldCompound = nbt.resolveCompound("Stats.Worlds").resolveCompound(type.name);

                    if(worldType == type || type == WorldType.ALL) {
                        setAdditionalDamage(worldCompound.getOrDefault("AdditionalDamage", 0d) + getAdditionalDamage(type), type);
                        setDamageMultiplier(worldCompound.getOrDefault("DamageMultiplier", 0d) + getDamageMultiplier(type), type);
                        setDamageReduction(worldCompound.getOrDefault("DamageReduction", 0d) + getDamageReduction(type), type);
                        setCriticalHitChance(worldCompound.getOrDefault("CriticalHitChance", 0d) + getCriticalHitChance(type), type);
                        setCriticalHitDamage(worldCompound.getOrDefault("CriticalHitDamage", 0d) + getCriticalHitDamage(type), type);
                        setExperienceMultiplier(worldCompound.getOrDefault("ExperienceMultiplier", 0d) + getExperienceMultiplier(type), type);
                        setPlayerScale(worldCompound.getOrDefault("PlayerScale", 0d) + getPlayerScale(type), type);
                    }
                }
            });
        }

        CustomArmor fullSet = ArmorType.fullSetType(armor);

        if(fullSet == null) return;

        Stats setStats = fullSet.getSetStats();

        for(WorldType type : WorldType.values()) {

            WorldType worldType = WorldType.getWorldType(env);


            if(worldType == type || type == WorldType.ALL) {

                setAdditionalDamage(setStats.getAdditionalDamage(type) + getAdditionalDamage(type), type);
                setDamageMultiplier(setStats.getDamageMultiplier(type) + getDamageMultiplier(type), type);
                setDamageReduction(setStats.getDamageReduction(type) + getDamageReduction(type), type);
                setCriticalHitChance(setStats.getCriticalHitChance(type) + getCriticalHitChance(type), type);
                setCriticalHitDamage(setStats.getCriticalHitDamage(type) + getCriticalHitDamage(type), type);
                setExperienceMultiplier(setStats.getExperienceMultiplier(type) + getExperienceMultiplier(type), type);
                setPlayerScale(setStats.getPlayerScale(type) + getPlayerScale(type), type);

            }
        }
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public double getArmorToughness() {
        return armorToughness;
    }

    public void setArmorToughness(double armorToughness) {
        this.armorToughness = armorToughness;
    }

    public double getBlockBreakSpeed() {
        return blockBreakSpeed;
    }

    public void setBlockBreakSpeed(double blockBreakSpeed) {
        this.blockBreakSpeed = blockBreakSpeed;
    }

    public double getSubmergedMiningSpeed() {
        return submergedMiningSpeed;
    }

    public void setSubmergedMiningSpeed(double submergedMiningSpeed) {
        this.submergedMiningSpeed = submergedMiningSpeed;
    }
}
