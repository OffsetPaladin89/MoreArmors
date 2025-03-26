package me.offsetpaladin89.MoreArmors.utils.stats;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBTCompoundList;
import de.tr7zw.changeme.nbtapi.iface.ReadableNBT;
import de.tr7zw.changeme.nbtapi.iface.ReadableNBTList;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Location;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.items.armors.CustomArmor;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

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

            for(Location type : Location.values()) {

                ReadWriteNBT locationCompound = baseCompound.resolveOrCreateCompound("Locations").resolveOrCreateCompound(type.name);

                if(getAdditionalDamage(type) != 0) locationCompound.setDouble("AdditionalDamage", additionalDamage.get(type));
                if(getDamageMultiplier(type) != 0) locationCompound.setDouble("DamageMultiplier", damageMultiplier.get(type));
                if(getDamageReduction(type) != 0) locationCompound.setDouble("DamageReduction", damageReduction.get(type));
                if(getCriticalHitChance(type) != 0) locationCompound.setDouble("CriticalHitChance", criticalHitChance.get(type));
                if(getCriticalHitDamage(type) != 0) locationCompound.setDouble("CriticalHitDamage", criticalHitDamage.get(type));
                if(getExperienceMultiplier(type) != 0) locationCompound.setDouble("ExperienceMultiplier", experienceMultiplier.get(type));
                if(getPlayerScale(type) != 0) locationCompound.setDouble("PlayerScale", playerScale.get(type));

                ReadWriteNBTCompoundList effectList = locationCompound.getCompoundList("PotionEffects");

                for(PotionEffect potionEffect : potionEffects.get(type)) {
                    ReadWriteNBT effect = effectList.addCompound();

                    String effectKey = potionEffect.getType().getKeyOrNull().toString();
                    effect.setString("Type", effectKey);
                    effect.setInteger("Level", potionEffect.getAmplifier());
                }
            }
        });
    }

    public void getStats(ItemStack[] armor, Player p) {
        for(ItemStack i : armor) {
            if(Util.isAirOrNull(i) || !Util.isCustomItem(i)) continue;

            ArrayList<Location> locations = Location.getLocation(p);

            NBT.get(i, nbt -> {

                for(Location type : Location.values()) {

                    ReadableNBT locationCompound = nbt.resolveCompound("Stats.Locations").resolveCompound(type.name);

                    if(locations.contains(type)) {
                        setAdditionalDamage(locationCompound.getOrDefault("AdditionalDamage", 0d) + getAdditionalDamage(type), type);
                        setDamageMultiplier(locationCompound.getOrDefault("DamageMultiplier", 0d) + getDamageMultiplier(type), type);
                        setDamageReduction(locationCompound.getOrDefault("DamageReduction", 0d) + getDamageReduction(type), type);
                        setCriticalHitChance(locationCompound.getOrDefault("CriticalHitChance", 0d) + getCriticalHitChance(type), type);
                        setCriticalHitDamage(locationCompound.getOrDefault("CriticalHitDamage", 0d) + getCriticalHitDamage(type), type);
                        setExperienceMultiplier(locationCompound.getOrDefault("ExperienceMultiplier", 0d) + getExperienceMultiplier(type), type);
                        setPlayerScale(locationCompound.getOrDefault("PlayerScale", 0d) + getPlayerScale(type), type);


                        ArrayList<PotionEffect> effects = new ArrayList<>();

                        ReadableNBTList<ReadWriteNBT> effectList = locationCompound.getCompoundList("PotionEffects");

                        for(int n = 0; n < effectList.size(); n++) {
                            ReadWriteNBT compound = effectList.get(n);
                            PotionEffectType potionEffectType = Registry.EFFECT.get(NamespacedKey.fromString(compound.getString("Type")));
                            int level = compound.getInteger("Level");
                            effects.add(new PotionEffect(potionEffectType, 30, level, false, false));
                        }

                        potionEffects.put(type, effects);
                    }
                }
            });
        }

        CustomArmor fullSet = ArmorType.fullSetType(armor);

        if(fullSet == null) return;

        Stats setStats = fullSet.getSetStats();

        for(Location type : Location.values()) {

            ArrayList<Location> locations = Location.getLocation(p);

            if(locations.contains(type)) {

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
