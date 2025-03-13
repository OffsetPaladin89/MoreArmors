package me.offsetpaladin89.MoreArmors.utils.skilltree;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SkillTree {

    private static int availableSkillPoints = 0;
    private final ItemStack item;

    public SkillTree(ItemStack item) {
        this.item = item;
        getAvailableSkillPoints();
    }

    public ArrayList<SkillTreeNode> baseNodes() {
        ArrayList<SkillTreeNode> nodes = new ArrayList<>();
        nodes.add(new SkillTreeNode(0, availableSkillPoints > 0, isNodeUnlocked(0)));
        nodes.add(new SkillTreeNode(1, canAccess(2), isNodeUnlocked(1)));
        nodes.add(new SkillTreeNode(2, canAccess(3), isNodeUnlocked(2)));
        nodes.add(new SkillTreeNode(3, canAccess(0), isNodeUnlocked(3)));
        nodes.add(new SkillTreeNode(4, canAccess(3), isNodeUnlocked(4)));
        nodes.add(new SkillTreeNode(5, canAccess(4), isNodeUnlocked(5)));
        nodes.add(new SkillTreeNode(6, canAccess(1), isNodeUnlocked(6)));
        nodes.add(new SkillTreeNode(7, canAccess(5), isNodeUnlocked(7)));
        nodes.add(new SkillTreeNode(8, canAccess(6), isNodeUnlocked(8)));
        nodes.add(new SkillTreeNode(9, availableSkillPoints > 1 && (isNodeUnlocked(12) || isNodeUnlocked(13)), isNodeUnlocked(9)));
        nodes.add(new SkillTreeNode(10, canAccess(7), isNodeUnlocked(10)));
        nodes.add(new SkillTreeNode(11, canAccess(8), isNodeUnlocked(11)));
        nodes.add(new SkillTreeNode(12, canAccess(11), isNodeUnlocked(12)));
        nodes.add(new SkillTreeNode(13, canAccess(14), isNodeUnlocked(13)));
        nodes.add(new SkillTreeNode(14, canAccess(10), isNodeUnlocked(14)));

        return nodes;
    }

    private boolean canAccess(int key) {
        return availableSkillPoints > 0 && isNodeUnlocked(key);
    }

    protected void getAvailableSkillPoints() {
        int unlockedNodes = 0;
        for(int n = 0; n < 15; n++) {
            if(isNodeUnlocked(n)) {
                unlockedNodes++;
                if(n == 9) unlockedNodes++;
            }
        }

        availableSkillPoints = NBT.get(item, nbt -> (int) nbt.resolveOrDefault("SkillTree.SkillPoints", 0)) - unlockedNodes;
    }

    protected boolean isNodeUnlocked(int key) {
        return NBT.get(item, nbt -> (boolean) nbt.resolveOrDefault("SkillTree.SkillTreeNodes.Node" + key, false));
    }

}
