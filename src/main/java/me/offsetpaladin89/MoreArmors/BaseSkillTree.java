package me.offsetpaladin89.MoreArmors;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;

import static me.offsetpaladin89.MoreArmors.handlers.InventoryHandler.getEmptyPane;
import static me.offsetpaladin89.MoreArmors.handlers.InventoryHandler.getExitItem;
import static me.offsetpaladin89.MoreArmors.utils.Util.colorString;

public class BaseSkillTree {

    protected GuiItem[] minorNodes, majorNodes;
    protected GuiItem mainNode;

    public BaseSkillTree(GuiItem[] minorNodes, GuiItem[] majorNodes, GuiItem mainNode) {
        this.minorNodes = minorNodes;
        this.majorNodes = majorNodes;
        this.mainNode = mainNode;
    }

    public ChestGui getBaseSkillTree() {
        ChestGui gui = new ChestGui(6, colorString("&8Skill Tree"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        StaticPane emptyPanes = new StaticPane(0, 0, 9, 6);
        emptyPanes.setPriority(Pane.Priority.LOWEST);
        emptyPanes.fillWith(getEmptyPane());
        emptyPanes.addItem(getExitItem(), 4, 5);

        gui.addPane(emptyPanes);

        gui.addPane(minorNodes());
        gui.addPane(majorNodes());
        gui.addPane(mainNode());

        return gui;
    }

    private StaticPane minorNodes() {
        StaticPane pane = new StaticPane(2, 0, 5, 5);
        pane.addItem(minorNodes[0], 2, 0);
        pane.addItem(minorNodes[1], 1, 1);
        pane.addItem(minorNodes[2], 3, 1);
        pane.addItem(minorNodes[3], 0, 2);
        pane.addItem(minorNodes[4], 4, 2);
        pane.addItem(minorNodes[5], 0, 4);
        pane.addItem(minorNodes[6], 4, 4);

        return pane;
    }

    private StaticPane majorNodes() {
        StaticPane pane = new StaticPane(2, 1, 5, 4);
        pane.addItem(majorNodes[0], 0, 0);
        pane.addItem(majorNodes[1], 2, 0);
        pane.addItem(majorNodes[2], 4, 0);
        pane.addItem(majorNodes[3], 0, 2);
        pane.addItem(majorNodes[4], 4, 2);
        pane.addItem(majorNodes[5], 1, 3);
        pane.addItem(majorNodes[6], 3, 3);

        return pane;
    }

    private StaticPane mainNode() {
        StaticPane pane = new StaticPane(4, 3, 1, 1);
        pane.addItem(mainNode, 0, 0);

        return pane;
    }
}
