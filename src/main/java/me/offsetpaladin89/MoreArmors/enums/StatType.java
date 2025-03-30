package me.offsetpaladin89.MoreArmors.enums;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;

public enum StatType {
    ADD_DMG("AdditionalDamage"),
    ARMOR("Armor", Attribute.ARMOR),
    ARMOR_TOUGH("ArmorToughness", Attribute.ARMOR_TOUGHNESS),
    BREAK_SPD("BlockBreakSpeed", Attribute.BLOCK_BREAK_SPEED),
    CRIT_CHANCE("CriticalChance"),
    CRIT_DMG("CriticalDamage"),
    DMG_REDUC("DamageReduction"),
    MAX_HP("MaxHealth", Attribute.MAX_HEALTH),
    MOVE_SPD("MovementSpeed", Attribute.MOVEMENT_SPEED),
    MULT_DMG("DamageMultiplier"),
    P_SCALE("PlayerScale"),
    WATER_BREAK_SPD("SubmergedMiningSpeed", Attribute.SUBMERGED_MINING_SPEED),
    XP_MULT("ExperienceMultiplier");

    public final String name;
    public final Attribute attribute;

    StatType(String name) {
        this.name = name;
        this.attribute = null;
    }

    StatType(String name, Attribute attribute) {
        this.name = name;
        this.attribute = attribute;
    }

    public static AttributeModifier.Operation getAttributeMethod(StatType type) {
        return switch (type) {
            case ARMOR, ARMOR_TOUGH, MAX_HP, MOVE_SPD -> AttributeModifier.Operation.ADD_NUMBER;
            case BREAK_SPD, WATER_BREAK_SPD -> AttributeModifier.Operation.ADD_SCALAR;
            default -> null;
        };
    }
}