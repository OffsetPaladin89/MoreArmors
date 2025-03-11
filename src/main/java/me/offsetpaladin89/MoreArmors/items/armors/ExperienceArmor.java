package me.offsetpaladin89.MoreArmors.items.armors;

import me.offsetpaladin89.MoreArmors.utils.Lore;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ExperienceArmor extends CustomArmor {

    private static final Color LEATHER_COLOR = Color.BLUE;

    public ExperienceArmor(SlotType slot) {
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
            case HELMET -> "Experience Helmet";
            case CHESTPLATE -> "Experience Chestplate";
            case LEGGINGS -> "Experience Leggings";
            case BOOTS -> "Experience Boots";
            default -> null;
        };
    }

    public void updateItem() {
        this.armorID = ArmorType.EXPERIENCE;

        setLeatherColor(LEATHER_COLOR);
        setLore();

        setFlags();

        baseAttributes();
        setAttributes();

        baseNBT();
    }

    private void setLore() {
        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addColoredLine("&6Full Set Bonus: Experience");
        lore.addColoredLine("&7Recieve &adouble experience &7from killing");
        lore.addColoredLine("&7mobs and mining ores.");
        lore.addArmorRarity(rarity);
        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }
}
