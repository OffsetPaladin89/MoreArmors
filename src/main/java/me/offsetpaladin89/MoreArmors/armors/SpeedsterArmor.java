package me.offsetpaladin89.MoreArmors.armors;

import me.offsetpaladin89.MoreArmors.Lore;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpeedsterArmor extends CustomArmor {

    private static final Color LEATHER_COLOR = Color.WHITE;

    public SpeedsterArmor(SlotType slot) {
        super(slot);
        this.item = getBaseItem();
        this.rarity = getDefaultRarity();
        this.displayName = getFormattedName(getDefaultName());
        this.armor = getDefaultArmor();

        createItem();
    }

    private ItemStack getBaseItem() {
        return switch (slot) {
            case HELMET -> new ItemStack(Material.LEATHER_HELMET);
            case CHESTPLATE -> new ItemStack(Material.LEATHER_CHESTPLATE);
            case LEGGINGS -> new ItemStack(Material.LEATHER_LEGGINGS);
            case BOOTS -> new ItemStack(Material.LEATHER_BOOTS);
            default -> null;
        };
    }

    private Rarity getDefaultRarity() {
        return switch (slot) {
            case HELMET, CHESTPLATE, LEGGINGS, BOOTS -> Rarity.UNCOMMON;
            default -> Rarity.DEVELOPER;
        };
    }

    private int getDefaultArmor() {
        return switch (slot) {
            case HELMET, BOOTS -> 1;
            case CHESTPLATE -> 5;
            case LEGGINGS -> 4;
            default -> 0;
        };
    }

    private String getDefaultName() {
        return switch (slot) {
            case HELMET -> "Speedster Helmet";
            case CHESTPLATE -> "Speedster Chestplate";
            case LEGGINGS -> "Speedster Leggings";
            case BOOTS -> "Speedster Boots";
            default -> null;
        };
    }

    public void updateItem() {
        this.armorID = ArmorType.SPEEDSTER;

        setLeatherColor(LEATHER_COLOR);
        setLore();

        setFlags();

        addAttributes();

        baseNBT();
    }

    private void addAttributes() {
        baseAttributes();
        AttributeModifier blockBreakSpeed = new AttributeModifier(pluginKey(), 0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);

        attributeModifiers.put(Attribute.MOVEMENT_SPEED, blockBreakSpeed);

        setAttributes();
    }

    private void setLore() {
        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addColoredLine("&6Item Ability: Speed");
        lore.addColoredLine("&7Increased movement speed by &a10%&7.");
        lore.addEmpty();
        lore.addColoredLine("&6Full Set Bonus: Retreat");
        lore.addColoredLine("&7Grants &aSpeed I &7for &a20 seconds &7when damaged.");
        lore.addArmorRarity(rarity);
        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }
}
