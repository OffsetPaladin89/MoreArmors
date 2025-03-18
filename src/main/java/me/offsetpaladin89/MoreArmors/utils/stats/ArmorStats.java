package me.offsetpaladin89.MoreArmors.utils.stats;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
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
            if(maxHealth != 0) nbt.resolveOrCreateCompound("Stats").setDouble("MaxHealth", maxHealth);
            if(armor != 0) nbt.resolveOrCreateCompound("Stats").setDouble("Armor", armor);
            if(armorToughness != 0) nbt.resolveOrCreateCompound("Stats").setDouble("ArmorToughness", armorToughness);
            if(movementSpeed != 0) nbt.resolveOrCreateCompound("Stats").setDouble("MovementSpeed", movementSpeed);
            if(blockBreakSpeed != 0) nbt.resolveOrCreateCompound("Stats").setDouble("BlockBreakSpeed", blockBreakSpeed);
            if(submergedMiningSpeed != 0) nbt.resolveOrCreateCompound("Stats").setDouble("SubmergedMiningSpeed", submergedMiningSpeed);

            if(additionalDamage != 0) nbt.resolveOrCreateCompound("Stats").setDouble("AdditionalDamage", additionalDamage);
            if(damageMultiplier != 0) nbt.resolveOrCreateCompound("Stats").setDouble("DamageMultiplier", damageMultiplier);
            if(damageReduction != 0) nbt.resolveOrCreateCompound("Stats").setDouble("DamageReduction", damageReduction);
            if(criticalHitChance != 0) nbt.resolveOrCreateCompound("Stats").setDouble("CriticalHitChance", criticalHitChance);
            if(criticalHitDamage != 0) nbt.resolveOrCreateCompound("Stats").setDouble("CriticalHitDamage", criticalHitDamage);
        });
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
