package me.offsetpaladin89.MoreArmors;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import me.offsetpaladin89.MoreArmors.enums.NodeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SkillTreeNode {

    protected ItemStack item;
    protected NodeType type;
    protected boolean canAccess, isUnlocked;

    public SkillTreeNode(NodeType type, boolean canAccess, boolean isUnlocked) {
        this.type = type;
        this.canAccess = canAccess;
        this.isUnlocked = isUnlocked;
        this.item = getBaseItem();
    }

    private ItemStack getBaseItem() {
        if(isUnlocked) return new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        else return new ItemStack(Material.RED_STAINED_GLASS_PANE);
    }

    public GuiItem getItem() {
        return new GuiItem(item);
    }
}