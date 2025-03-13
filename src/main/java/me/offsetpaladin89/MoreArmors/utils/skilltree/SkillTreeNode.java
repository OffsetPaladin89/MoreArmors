package me.offsetpaladin89.MoreArmors.utils.skilltree;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.items.armors.CustomArmor;
import me.offsetpaladin89.MoreArmors.utils.Lore;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.offsetpaladin89.MoreArmors.enums.ArmorType.armorFromType;
import static me.offsetpaladin89.MoreArmors.utils.Util.colorString;

public class SkillTreeNode {

    private ItemStack item;
    private boolean canAccess, isUnlocked;
    public final int id;
    private String displayName;
    private ArrayList<String> description;

    public SkillTreeNode(int id, boolean canAccess, boolean isUnlocked) {
        this.id = id;
        this.canAccess = canAccess;
        this.isUnlocked = isUnlocked;
        this.item = getBaseItem();
    }

    private ItemStack getBaseItem() {
        if(isUnlocked) return new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        else if(canAccess) return new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        else return new ItemStack(Material.RED_STAINED_GLASS_PANE);
    }

    private void addName() {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(colorString(displayName));
        item.setItemMeta(itemMeta);
    }

    private void addLore() {
        Lore lore = new Lore();
        for(String s : description) lore.addColoredLine(s);

        lore.addUnlockedStatus(isUnlocked, canAccess);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore.getLore());
        item.setItemMeta(itemMeta);
    }

    private void unlockNode(InventoryClickEvent event) {
        if(!canAccess || isUnlocked) return;

        isUnlocked = true;
        canAccess = false;

        item = getBaseItem();

        NBT.modify(event.getWhoClicked().getInventory().getItemInMainHand(), nbt -> { nbt.resolveCompound("SkillTree.SkillTreeNodes").setBoolean("Node" + id, true); });

        ItemStack item = event.getWhoClicked().getInventory().getItemInMainHand();
        ArmorType type = NBT.get(item, nbt -> (ArmorType) nbt.getEnum("ArmorID", ArmorType.class));
        CustomArmor armor = armorFromType(type, item);
        armor.openSkillTree(event.getWhoClicked());
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    public GuiItem getItem() {
        addName();
        addLore();
        return new GuiItem(item, this::unlockNode);
    }
}