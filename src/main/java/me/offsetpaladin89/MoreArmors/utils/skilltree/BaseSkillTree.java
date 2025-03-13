package me.offsetpaladin89.MoreArmors.utils.skilltree;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;

import java.util.ArrayList;

import static me.offsetpaladin89.MoreArmors.handlers.InventoryHandler.getEmptyPane;
import static me.offsetpaladin89.MoreArmors.handlers.InventoryHandler.getExitItem;
import static me.offsetpaladin89.MoreArmors.utils.Util.colorString;

public class BaseSkillTree {

    protected ArrayList<GuiItem> nodes;

    public BaseSkillTree(ArrayList<GuiItem> nodes) {
        this.nodes = nodes;
    }

    public ChestGui getBaseSkillTree() {
        ChestGui gui = new ChestGui(6, colorString("&8Skill Tree"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        StaticPane emptyPanes = new StaticPane(0, 0, 9, 6);
        emptyPanes.setPriority(Pane.Priority.LOWEST);
        emptyPanes.fillWith(getEmptyPane());
        emptyPanes.addItem(getExitItem(), 4, 5);

        Pattern pattern = new Pattern("00a00", "bcdef", "g000h", "i0j0k", "lm0no");

        PatternPane skillTreeNodes = new PatternPane(2, 0, 5, 5, pattern);


        skillTreeNodes.bindItem('a', nodes.getFirst());
        skillTreeNodes.bindItem('b', nodes.get(1));
        skillTreeNodes.bindItem('c', nodes.get(2));
        skillTreeNodes.bindItem('d', nodes.get(3));
        skillTreeNodes.bindItem('e', nodes.get(4));
        skillTreeNodes.bindItem('f', nodes.get(5));
        skillTreeNodes.bindItem('g', nodes.get(6));
        skillTreeNodes.bindItem('h', nodes.get(7));
        skillTreeNodes.bindItem('i', nodes.get(8));
        skillTreeNodes.bindItem('j', nodes.get(9));
        skillTreeNodes.bindItem('k', nodes.get(10));
        skillTreeNodes.bindItem('l', nodes.get(11));
        skillTreeNodes.bindItem('m', nodes.get(12));
        skillTreeNodes.bindItem('n', nodes.get(13));
        skillTreeNodes.bindItem('o', nodes.get(14));

        gui.addPane(emptyPanes);
        gui.addPane(skillTreeNodes);

        return gui;
    }
}
