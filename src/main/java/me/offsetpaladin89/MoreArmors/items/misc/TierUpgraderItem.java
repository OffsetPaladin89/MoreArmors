package me.offsetpaladin89.MoreArmors.items.misc;

import me.offsetpaladin89.MoreArmors.enums.ItemType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.utils.Lore;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.inventory.meta.ItemMeta;

public class TierUpgraderItem extends CustomItem {

    private static final Rarity BASE_RARITY = Rarity.LEGENDARY;

    public TierUpgraderItem() {
        super(BASE_RARITY);
    }

    // Override Methods

    protected void setLore() {
        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addColoredLine("&7Allows upgrading armors from");
        lore.addColoredLine("&6MoreArmors &7up to &b(+50)&7, granting");
        lore.addColoredLine("&a+1 Skill Point &7every 5 tiers.");
        lore.addItemRarity(rarity);

        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }

    protected String getFormattedName(String displayName) {
        return Util.colorString(String.format("%s%s &b", rarity.color, displayName));
    }

    protected String getDefaultName() { return "Tier Upgrader"; }

    protected ItemType getItemID() {
        return ItemType.TIER_UPGRADER;
    }
}