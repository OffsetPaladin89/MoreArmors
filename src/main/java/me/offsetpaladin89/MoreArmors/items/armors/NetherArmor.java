package me.offsetpaladin89.MoreArmors.items.armors;

import me.offsetpaladin89.MoreArmors.utils.Lore;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.offsetpaladin89.MoreArmors.enums.SlotType.HELMET;

public class NetherArmor extends CustomArmor {

    private static final Color LEATHER_COLOR = Color.GRAY;

    public NetherArmor(SlotType slot) {
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
        lore.addColoredLine("&6Item Ability: Bane of the Nether");
        lore.addColoredLine("&7Deal &c+10% &7damage while in &4The Nether&7.");
        lore.addEmpty();
        lore.addColoredLine("&6Full Set Bonus: Nether King");
        lore.addColoredLine("&7Grants &aFire Resistance &7while");
        lore.addColoredLine("&7in the Nether.");
        lore.addEmpty();
        lore.addColoredLine("&7Take &a50% &7reduced damage while in &4The Nether&7.");
        lore.addEmpty();
        lore.addColoredLine("&7Deal &c+100% &7damage while in &4The Nether&7.");
        lore.addArmorRarity(rarity);
        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }

    public void updateItem() {
        this.armorID = ArmorType.NETHER;

        if(slot.equals(HELMET)) assignSkull(item);
        else setLeatherColor(LEATHER_COLOR);
        setLore();

        setFlags();

        baseAttributes();
        setAttributes();

        baseNBT();
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
            case HELMET -> "Nether Helmet";
            case CHESTPLATE -> "Nether Chestplate";
            case LEGGINGS -> "Nether Leggings";
            case BOOTS -> "Nether Boots";
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
        Util.modifySkullSkin(item, "cdf74e323ed41436965f5c57ddf2815d5332fe999e68fbb9d6cf5c8bd4139f", null);
    }
}
