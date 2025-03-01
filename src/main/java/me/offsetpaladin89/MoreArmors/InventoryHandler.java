package me.offsetpaladin89.MoreArmors;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.component.PagingButtons;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static me.offsetpaladin89.MoreArmors.MoreArmorsMain.colorString;
import static me.offsetpaladin89.MoreArmors.MoreArmorsMain.modifySkullSkin;

public record InventoryHandler(MoreArmorsMain plugin) {

    public void viewRecipes(Player player) {
        ChestGui gui = new ChestGui(6, colorString("&7MoreArmors Recipes"));

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane pages = new PaginatedPane(0, 0, 9, 5);
        pages.populateWithGuiItems(getCustomItems());

        StaticPane emptyPanes = new StaticPane(1, 6, 7, 1);
        emptyPanes.fillWith(getEmptyPane());

        PagingButtons pagingButtons = new PagingButtons(Slot.fromXY(0, 6), 9, pages);
        pagingButtons.setBackwardButton(getBackwardButton());
        pagingButtons.setForwardButton(getForwardButton());

        gui.addPane(pages);
        gui.addPane(emptyPanes);

        gui.show(player);
    }

    private List<GuiItem> getCustomItems() {
        List<GuiItem> customItems = new ArrayList<>();
        Iterator<Recipe> recipeIterator = plugin.getServer().recipeIterator();
        while(recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();

            ItemStack result = recipe.getResult();

            NBT.get(result, nbt -> {
                ArmorType armorID = nbt.getEnum("ArmorID", ArmorType.class);
                MaterialType materialID = nbt.getEnum("MaterialID", MaterialType.class);

                if(armorID != null || materialID != null) customItems.add(new GuiItem(result));
            });
        }
        return customItems;
    }

    private ItemStack getEmptyPane() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(" ");
        item.setItemMeta(itemMeta);

        return item;
    }

    private GuiItem getBackwardButton() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        UUID SKULL_UUID = UUID.nameUUIDFromBytes("BACKWARD_BUTTON".getBytes());
        modifySkullSkin(item, "37aee9a75bf0df7897183015cca0b2a7d755c63388ff01752d5f4419fc645", SKULL_UUID);
        return new GuiItem(item);
    }

    private GuiItem getForwardButton() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        UUID SKULL_UUID = UUID.nameUUIDFromBytes("FORWARD_BUTTON".getBytes());
        modifySkullSkin(item, "682ad1b9cb4dd21259c0d75aa315ff389c3cef752be3949338164bac84a96e", SKULL_UUID);
        return new GuiItem(item);
    }
}
