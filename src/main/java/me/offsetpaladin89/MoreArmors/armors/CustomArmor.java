package me.offsetpaladin89.MoreArmors.armors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.UUID;

public class CustomArmor {

    protected ItemStack item;
    protected Rarity rarity = Rarity.DEVELOPER;
    protected int armor = 0;
    protected int armorToughness = 0;
    protected SlotType slot;
    protected String displayName = "Offset";
    protected ArmorType armorID;
    protected int upgradeTier = 0;

    protected ListMultimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();

    CustomArmor(ItemStack item) {
        this.item = item;
    }

    CustomArmor(SlotType slot) {
        this.slot = slot;
    }

    public void createItem() {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);

        updateItem();
    }

    protected void updateItem() {

    }

    protected void setFlags() {
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(itemMeta);
    }

    protected void setLeatherColor(Color LEATHER_COLOR) {
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();

        itemMeta.setColor(LEATHER_COLOR);

        item.setItemMeta(itemMeta);
    }

    protected void baseAttributes() {
        AttributeModifier armorAttribute = new AttributeModifier(pluginKey(), armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);
        AttributeModifier armorToughnessAttribute = new AttributeModifier(pluginKey(), armorToughness, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);

        attributeModifiers.put(Attribute.ARMOR, armorAttribute);
        attributeModifiers.put(Attribute.ARMOR_TOUGHNESS, armorToughnessAttribute);
    }

    protected NamespacedKey pluginKey() {
        return new NamespacedKey("morearmors", String.format("%s_%s", armorID, slot).toLowerCase());
    }

    protected void setAttributes() {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setAttributeModifiers(attributeModifiers);
        item.setItemMeta(itemMeta);
    }

    protected void baseNBT() {
        NBT.modify(item, nbt -> {
            nbt.setEnum("Rarity", rarity);
            nbt.setInteger("Armor", armor);
            nbt.setInteger("ArmorToughness", armorToughness);
            nbt.setInteger("UpgradeTier", upgradeTier);
            if(item.getType().equals(Material.PLAYER_HEAD)) nbt.setUUID("UUID", UUID.randomUUID());
            nbt.setEnum("ArmorID", armorID);
        });
    }

    public ItemStack getItem() {
        return item;
    }

    protected String getFormattedName(String displayName) {
        return Util.colorString(String.format("%s%s &b(+%d)", Rarity.getColorRarity(rarity), displayName, upgradeTier));
    }
}