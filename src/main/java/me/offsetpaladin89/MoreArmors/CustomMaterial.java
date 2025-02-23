package me.offsetpaladin89.MoreArmors;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomMaterial {

    protected ItemStack item;
    protected Rarity rarity;
    protected int upgradeTier;
    protected String displayName = "Offset";
    protected MaterialType materialID;

    protected CustomMaterial(Rarity rarity, int upgradeTier, String displayName, MaterialType materialID) {
        this.rarity = rarity;
        this.upgradeTier = upgradeTier;
        this.displayName = getFormattedName(displayName);
        this.materialID = materialID;
    }

    protected void createItem(Material material) {
        item = new ItemStack(material, 1);

        setDisplayName();
        setLore();
        addGlowing();
        baseNBT();
    }

    protected void setLore() {
        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addMaterialRarity(rarity);

        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }

    public ItemStack getItem() {
        return this.item;
    }

    public MaterialType getType() {
        return this.materialID;
    }

    protected String getFormattedName(String displayName) {
        return MoreArmorsMain.colorString(String.format("%s%s &b(+%d)", Rarity.getColorRarity(rarity), displayName, upgradeTier));
    }

    protected void addGlowing() {
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.addEnchant(Enchantment.MENDING, 1, false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(itemMeta);
    }

    public void setAmount(int amount) {
        item.setAmount(amount);
    }

    protected void setDisplayName() {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);
    }

    protected void baseNBT() {
        NBT.modify(item, nbt -> {
            nbt.setEnum("Rarity", rarity);
            nbt.setEnum("MaterialID", materialID);
        });
    }
}
