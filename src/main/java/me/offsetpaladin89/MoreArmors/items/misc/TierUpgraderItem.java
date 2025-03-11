package me.offsetpaladin89.MoreArmors.items.misc;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.enums.ItemType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.utils.Lore;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

public class TierUpgraderItem extends CustomItem {

    private ItemType itemID;

    private static final Rarity BASE_RARITY = Rarity.LEGENDARY;
    private static final String DEFAULT_NAME = "Tier Upgrader";

    public TierUpgraderItem() {
        super(BASE_RARITY, DEFAULT_NAME);

        createItem();
    }

    public void createItem() {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);

        updateItem();
    }

    private void updateItem() {
        itemID = ItemType.TIER_UPGRADER;

        setLore();
        addGlowing();
        baseNBT();
    }

    private void addGlowing() {
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.addEnchant(Enchantment.MENDING, 1, false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(itemMeta);
    }

    private void setLore() {
        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addColoredLine("&7Allows upgrading armors from &6MoreArmors");
        lore.addColoredLine("&7up to &b(+35)&7, granting &a+1 Skill Point");
        lore.addColoredLine("&7every 5 tiers.");
        lore.addMaterialRarity(rarity);

        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }

    private void baseNBT() {
        NBT.modify(item, nbt -> {
            nbt.setEnum("Rarity", rarity);
            nbt.setEnum("ItemID", itemID);
        });
    }
}
