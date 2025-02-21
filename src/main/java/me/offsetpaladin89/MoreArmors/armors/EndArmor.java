package me.offsetpaladin89.MoreArmors.armors;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.CustomItemID;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EndArmor extends CustomArmor {

    private static final Color LEATHER_COLOR = Color.PURPLE;

    private static final CustomItemID armorID = CustomItemID.END;

    public EndArmor(ItemStack item) {
        super(item);
    }

    public EndArmor(SlotType slot) {
        super(slot);
        this.item = getBaseItem();
        this.customItemID = armorID;
        this.rarity = getDefaultRarity();
        this.displayName = MoreArmorsMain.colorString(Rarity.getColorRarity(rarity) + getDefaultName());
        this.armor = getDefaultArmor();
        this.armorToughness = getDefaultArmorToughness();
    }

    public final ItemStack getItem() {
        return item;
    }

    private int getDefaultArmor() {
        return switch (slot) {
            case HELMET, BOOTS -> 3;
            case CHESTPLATE -> 8;
            case LEGGINGS -> 6;
            default -> 0;
        };
    }

    private int getDefaultArmorToughness() {
        return switch (slot) {
            case HELMET, CHESTPLATE, LEGGINGS, BOOTS -> 2;
            default -> 0;
        };
    }

    private String getDefaultName() {
        return switch (slot) {
            case HELMET -> "End Helmet";
            case CHESTPLATE -> "End Chestplate";
            case LEGGINGS -> "End Leggings";
            case BOOTS -> "End Boots";
            default -> null;
        };
    }

    private Rarity getDefaultRarity() {
        return switch (slot) {
            case HELMET, CHESTPLATE, LEGGINGS, BOOTS -> Rarity.LEGENDARY;
            default -> Rarity.DEVELOPER;
        };
    }

    private ItemStack getBaseItem() {
        return switch (slot) {
            case HELMET -> null; // TODO Add Helmet Skin
            case CHESTPLATE -> new ItemStack(Material.LEATHER_CHESTPLATE);
            case LEGGINGS -> new ItemStack(Material.LEATHER_LEGGINGS);
            case BOOTS -> new ItemStack(Material.LEATHER_BOOTS);
            default -> null;
        };
    }
}
