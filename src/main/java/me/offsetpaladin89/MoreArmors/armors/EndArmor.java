package me.offsetpaladin89.MoreArmors.armors;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.Lore;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EndArmor extends CustomArmor {

    private static final Color LEATHER_COLOR = Color.PURPLE;

    public EndArmor(ItemStack item) {
        super(item);
    }

    public EndArmor(SlotType slot) {
        super(slot);
        this.item = getBaseItem();
        this.rarity = getDefaultRarity();
        this.displayName = getFormattedName(getDefaultName());
        this.armor = getDefaultArmor();
        this.armorToughness = getDefaultArmorToughness();

        createItem();
    }

    private void setLore() {
        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addColoredLine("&6Item Ability: Bane of the End");
        lore.addColoredLine("&7Deal &c+10% &7damage while in &5The End&7.");
        lore.addEmpty();
        lore.addColoredLine("&6Full Set Bonus: End King");
        lore.addColoredLine("&7Take &a50% &7reduced damage while in &5The End&7.");
        lore.addColoredLine("&7Deal &c+100% &7damage while in &5The End&7.");
        lore.addEmpty();
        lore.addColoredLine("&6Full Set Ability: Ender Warp &e&lSHIFT LEFT CLICK");
        lore.addColoredLine("&7Teleport &a10 blocks &7forwards while in &5The End&7.");
        lore.addColoredLine("&81s Cooldown");
        lore.addArmorRarity(rarity);
        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }

    public void createItem() {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);

        updateItem();
    }

    public void createItemFromNBT() {
        NBT.get(item, nbt -> {
            rarity = nbt.getEnum("Rarity", Rarity.class);
            armor = nbt.getInteger("Armor");
            armorToughness = nbt.getInteger("ArmorToughness");
            armorID = nbt.getEnum("ArmorID", ArmorType.class);
        });

        slot = SlotType.matchType(item);
        displayName = getFormattedName(item.getItemMeta().getDisplayName());
    }

    public void updateItem() {
        this.armorID = ArmorType.END;

        if(this.item.getType().equals(Material.PLAYER_HEAD)) assignSkull(item);
        else setLeatherColor(LEATHER_COLOR);
        setLore();

        setFlags();

        baseAttributes();
        setAttributes();

        baseNBT();
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
            case HELMET -> new ItemStack(Material.PLAYER_HEAD);
            case CHESTPLATE -> new ItemStack(Material.LEATHER_CHESTPLATE);
            case LEGGINGS -> new ItemStack(Material.LEATHER_LEGGINGS);
            case BOOTS -> new ItemStack(Material.LEATHER_BOOTS);
            default -> null;
        };
    }

    private void assignSkull(ItemStack item) {
        MoreArmorsMain.modifySkullSkin(item, "fee4eabeb72f19088ade78266191c8f77398cc0d80cdd27563a5d66b71912b28", null);
    }
}
