package me.offsetpaladin89.MoreArmors.items.misc;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.enums.ItemType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.utils.Lore;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TierUpgraderItem extends CustomItem {

    private ItemType itemID;

    private static final Rarity BASE_RARITY = Rarity.LEGENDARY;
    private static final String DEFAULT_NAME = "Tier Upgrader";

    public TierUpgraderItem() {
        super(BASE_RARITY, DEFAULT_NAME);
        this.item = getBaseItem();

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

    private ItemStack getBaseItem() {
        return new ItemStack(Material.EMERALD);
    }

    protected String getFormattedName(String displayName) {
        return Util.colorString(String.format("%s%s &b", rarity.color, displayName));
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
        lore.addColoredLine("&7Allows upgrading armors from");
        lore.addColoredLine("&6MoreArmors &7up to &b(+35)&7, granting");
        lore.addColoredLine("&a+1 Skill Point &7every 5 tiers.");
        lore.addItemRarity(rarity);

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
