package me.offsetpaladin89.MoreArmors.items.armors;

import me.offsetpaladin89.MoreArmors.utils.Lore;
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

public class MinerArmor extends CustomArmor {

    private static final Color LEATHER_COLOR = Color.GRAY;

    public MinerArmor(SlotType slot) {
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
            case HELMET, CHESTPLATE, LEGGINGS, BOOTS -> Rarity.RARE;
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
            case HELMET -> "Miner Helmet";
            case CHESTPLATE -> "Miner Chestplate";
            case LEGGINGS -> "Miner Leggings";
            case BOOTS -> "Miner Boots";
            default -> null;
        };
    }

    public void updateItem() {
        this.armorID = ArmorType.MINER;

        setLeatherColor(LEATHER_COLOR);
        setLore();

        setFlags();

        addAttributes();

        baseNBT();
    }

    private void addAttributes() {
        baseAttributes();
        AttributeModifier blockBreakSpeed = new AttributeModifier(pluginKey(), 0.2, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR);

        attributeModifiers.put(Attribute.BLOCK_BREAK_SPEED, blockBreakSpeed);

        setAttributes();
    }

    private void setLore() {
        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addColoredLine("&6Item Ability: Mining Speed");
        lore.addColoredLine("&7Increases mining speed by &a+20%&7.");
        lore.addEmpty();
        lore.addColoredLine("&6Full Set Bonus: Haste");
        lore.addColoredLine("&7Gives &aHaste II &7for &a5 seconds");
        lore.addColoredLine("&7after mining a block.");
        lore.addArmorRarity(rarity);
        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }
}
