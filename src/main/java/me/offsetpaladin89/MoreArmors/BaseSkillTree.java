package me.offsetpaladin89.MoreArmors;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.gui.type.util.Gui;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;

import java.util.ArrayList;

import static me.offsetpaladin89.MoreArmors.handlers.InventoryHandler.getEmptyPane;
import static me.offsetpaladin89.MoreArmors.handlers.InventoryHandler.getExitItem;
import static me.offsetpaladin89.MoreArmors.utils.Util.colorString;

public class BaseSkillTree {

    protected ArrayList<GuiItem> minorNodes, majorNodes;
    protected GuiItem mainNode;
    private static ChestGui gui;

    public BaseSkillTree(ArrayList<GuiItem> minorNodes, ArrayList<GuiItem> majorNodes, GuiItem mainNode) {
        this.minorNodes = minorNodes;
        this.majorNodes = majorNodes;
        this.mainNode = mainNode;
        gui = getBaseSkillTree();
    }

    public ChestGui getBaseSkillTree() {
        ChestGui gui = new ChestGui(6, colorString("&8Skill Tree"));
        gui.setOnGlobalClick(event -> {
            event.setCancelled(true);

        });

        StaticPane emptyPanes = new StaticPane(0, 0, 9, 6);
        emptyPanes.setPriority(Pane.Priority.LOWEST);
        emptyPanes.fillWith(getEmptyPane());
        emptyPanes.addItem(getExitItem(), 4, 5);

        Pattern pattern = new Pattern("00a00", "bcdef", "g000h", "i0j0k", "lm0no");

        PatternPane skillTreeNodes = new PatternPane(2, 0, 5, 5, pattern);

        skillTreeNodes.bindItem('j', mainNode);

        skillTreeNodes.bindItem('b', majorNodes.getFirst());
        skillTreeNodes.bindItem('d', majorNodes.get(1));
        skillTreeNodes.bindItem('f', majorNodes.get(2));
        skillTreeNodes.bindItem('i', majorNodes.get(3));
        skillTreeNodes.bindItem('k', majorNodes.get(4));
        skillTreeNodes.bindItem('m', majorNodes.get(5));
        skillTreeNodes.bindItem('n', majorNodes.get(6));

        skillTreeNodes.bindItem('a', minorNodes.getFirst());
        skillTreeNodes.bindItem('c', minorNodes.get(1));
        skillTreeNodes.bindItem('e', minorNodes.get(2));
        skillTreeNodes.bindItem('g', minorNodes.get(3));
        skillTreeNodes.bindItem('h', minorNodes.get(4));
        skillTreeNodes.bindItem('l', minorNodes.get(5));
        skillTreeNodes.bindItem('o', minorNodes.get(6));

        gui.addPane(emptyPanes);
        gui.addPane(skillTreeNodes);

        return gui;
    }

    public void setMinorNodes(ArrayList<GuiItem> minorNodes) {
        this.minorNodes = minorNodes;
    }

    public void setMajorNodes(ArrayList<GuiItem> majorNodes) {
        this.majorNodes = majorNodes;
    }

    public void setMainNode(GuiItem mainNode) {
        this.mainNode = mainNode;
    }

    public static void updateGUI() {
        gui.update();
    }
}
