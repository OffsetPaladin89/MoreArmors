package me.offsetpaladin89.MoreArmors.armors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class CustomArmor {

    protected ItemStack item;
    protected Rarity rarity = Rarity.DEVELOPER;
    protected int armor = 0;
    protected int armorToughness = 0;
    protected String displayName = "Offset";

    protected ListMultimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();

    CustomArmor(ItemStack item) {
        this.item = item;

        setFlags();
    }

    CustomArmor(ItemStack item, String displayName, Rarity rarity, int armor, int armorToughness) {
        this.item = item;
        this.displayName = MoreArmorsMain.colorString(Rarity.getColorRarity(rarity) + displayName);
        this.rarity = rarity;
        this.armor = armor;
        this.armorToughness = armorToughness;

        setFlags();
    }

    private void setFlags() {
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(itemMeta);
    }

    protected void baseAttributes() {
        AttributeModifier armorAttribute = new AttributeModifier(MoreArmorsMain.pluginKey, armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);
        AttributeModifier armorToughnessAttribute = new AttributeModifier(MoreArmorsMain.pluginKey, armorToughness, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);

        attributeModifiers.put(Attribute.ARMOR, armorAttribute);
        attributeModifiers.put(Attribute.ARMOR_TOUGHNESS, armorToughnessAttribute);
    }

    protected void setAttributes() {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setAttributeModifiers(attributeModifiers);
    }

    protected void baseNBT() {
        NBT.modify(item, nbt -> {
            nbt.setEnum("Rarity", rarity);
            nbt.setInteger("Armor", armor);
            nbt.setInteger("ArmorToughness", armorToughness);
            if(item.getType().equals(Material.PLAYER_HEAD)) nbt.setUUID("UUID", UUID.randomUUID());
        });
    }

    protected void setDisplayName(String s) {
        this.displayName = MoreArmorsMain.colorString(Rarity.getColorRarity(rarity) + s);
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setArmorToughness(int armorToughness) {
        this.armorToughness = armorToughness;
    }
}