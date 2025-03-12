package me.offsetpaladin89.MoreArmors;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;

public class DestroyerSkillTree {

    public GuiItem[] getMinorNodes() {
        return new GuiItem[]{ minorNode(0), minorNode(1), minorNode(2), minorNode(3), minorNode(4), minorNode(5), minorNode(6) };
    }

    private GuiItem minorNode(int n) {
        return switch (n) {
            case 0 -> {

            }
        };
    }


}
