package me.offsetpaladin89.MoreArmors.armors;

import me.offsetpaladin89.MoreArmors.utils.Lore;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TitanArmor extends CustomArmor {

    public TitanArmor(SlotType slot) {
        super(slot);
        this.item = getBaseItem();
        this.rarity = getDefaultRarity();
        this.displayName = getFormattedName(getDefaultName());
        this.armor = getDefaultArmor();

        createItem();
    }

    private void setLore() {
        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addColoredLine("&6Item Ability: Health");
        lore.addColoredLine("&7Increases max health by &a2&7.");
        lore.addEmpty();
        lore.addColoredLine("&6Full Set Bonus: Resistance");
        lore.addColoredLine("&7Grants &aResistance I &7for &a10 seconds");
        lore.addColoredLine("&7after defeating an enemy.");
        lore.addArmorRarity(rarity);
        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }

    public void updateItem() {
        this.armorID = ArmorType.TITAN;

        setLore();

        setFlags();

        addAttributes();

        baseNBT();
    }

    public final ItemStack getItem() {
        return item;
    }

    private int getDefaultArmor() {
        return switch (slot) {
            case HELMET, BOOTS -> 2;
            case CHESTPLATE -> 6;
            case LEGGINGS -> 5;
            default -> 0;
        };
    }

    private void addAttributes() {
        baseAttributes();
        AttributeModifier healthAttribute = new AttributeModifier(pluginKey(), 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);

        attributeModifiers.put(Attribute.MAX_HEALTH, healthAttribute);

        setAttributes();
    }

    private String getDefaultName() {
        return switch (slot) {
            case HELMET -> "Titan Helmet";
            case CHESTPLATE -> "Titan Chestplate";
            case LEGGINGS -> "Titan Leggings";
            case BOOTS -> "Titan Boots";
            default -> null;
        };
    }

    private Rarity getDefaultRarity() {
        return switch (slot) {
            case HELMET, CHESTPLATE, LEGGINGS, BOOTS -> Rarity.UNCOMMON;
            default -> Rarity.DEVELOPER;
        };
    }

    private ItemStack getBaseItem() {
        return switch (slot) {
            case HELMET -> new ItemStack(Material.IRON_HELMET);
            case CHESTPLATE -> new ItemStack(Material.IRON_CHESTPLATE);
            case LEGGINGS -> new ItemStack(Material.IRON_LEGGINGS);
            case BOOTS -> new ItemStack(Material.IRON_BOOTS);
            default -> null;
        };
    }
}
