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
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static me.offsetpaladin89.MoreArmors.MoreArmorsMain.colorString;
import static me.offsetpaladin89.MoreArmors.MoreArmorsMain.modifySkullSkin;

public record InventoryHandler(MoreArmorsMain plugin) {

    public void viewRecipes(HumanEntity humanEntity) {
        ChestGui gui = new ChestGui(6, colorString("&8MoreArmors Recipes"));

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane pages = new PaginatedPane(1, 1, 7, 4);
        pages.populateWithGuiItems(getCustomItems());

        StaticPane emptyPanes = new StaticPane(0, 0, 9, 6);
        emptyPanes.fillWith(getEmptyPane());
        emptyPanes.addItem(getExitItem(), 4, 5);

        PagingButtons pagingButtons = new PagingButtons(Slot.fromXY(0, 5), 9, pages);

        pagingButtons.setBackwardButton(getBackwardButton());
        pagingButtons.setForwardButton(getForwardButton());

        gui.addPane(pages);
        gui.addPane(pagingButtons);
        gui.addPane(emptyPanes);

        gui.show(humanEntity);
    }

    private List<GuiItem> getCustomItems() {
        List<GuiItem> customItems = new ArrayList<>();
        Iterator<Recipe> recipeIterator = plugin.getServer().recipeIterator();
        while(recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();

            ItemStack result = recipe.getResult();

            if(plugin.isAirOrNull(result)) continue;

            NBT.get(result, nbt -> {
                ArmorType armorID = nbt.getEnum("ArmorID", ArmorType.class);
                MaterialType materialID = nbt.getEnum("MaterialID", MaterialType.class);

                GuiItem recipeItem = new GuiItem(result, event -> {
                    if(recipe instanceof ShapedRecipe shapedRecipe) {
                        getRecipeGUI(shapedRecipe, event.getWhoClicked());
                    }
                });

                if(armorID != null || materialID != null) customItems.add(recipeItem);
            });
        }

        while(customItems.size() % 28 != 0) customItems.add(new GuiItem(new ItemStack(Material.AIR)));


        return customItems;
    }

    private void getRecipeGUI(ShapedRecipe recipe, HumanEntity humanEntity) {
        ChestGui gui = new ChestGui(6, colorString(recipe.getResult().getItemMeta().getDisplayName() + " &8Recipe"));

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        StaticPane pane = new StaticPane(0, 0, 9, 6);
        pane.fillWith(getEmptyPane());
        pane.addItem(getBackItem(), 4, 5);

        int i = 0;
        for(ItemStack item : recipe.getIngredientMap().values()) {
            GuiItem guiItem;
            if(plugin.isAirOrNull(item)) guiItem = new GuiItem(new ItemStack(Material.AIR));
            else guiItem = new GuiItem(item);

            pane.addItem(guiItem, Slot.fromXY(1 + i % 3, 1 + i / 3));
            i++;
        }

        pane.addItem(getResultItem(), 5, 2);

        GuiItem result = new GuiItem(recipe.getResult());

        pane.addItem(result, 7, 2);

        gui.addPane(pane);

        gui.show(humanEntity);
    }

    private void setBlackRightArrow(ItemStack item) {
        UUID SKULL_UUID = UUID.nameUUIDFromBytes("BLACK_RIGHT_ARROW".getBytes());
        modifySkullSkin(item, "682ad1b9cb4dd21259c0d75aa315ff389c3cef752be3949338164bac84a96e", SKULL_UUID);
    }

    private void setBlackLeftArrow(ItemStack item) {
        UUID SKULL_UUID = UUID.nameUUIDFromBytes("BLACK_LEFT_ARROW".getBytes());
        modifySkullSkin(item, "37aee9a75bf0df7897183015cca0b2a7d755c63388ff01752d5f4419fc645", SKULL_UUID);
    }

    private void setLimeRightArrow(ItemStack item) {
        UUID SKULL_UUID = UUID.nameUUIDFromBytes("LIME_RIGHT_ARROW".getBytes());
        modifySkullSkin(item, "4ef356ad2aa7b1678aecb88290e5fa5a3427e5e456ff42fb515690c67517b8", SKULL_UUID);
    }

    private GuiItem getResultItem() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(colorString("&aResult"));
        item.setItemMeta(itemMeta);

        setLimeRightArrow(item);

        return new GuiItem(item);
    }

    private GuiItem getBackItem() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(colorString("&cBack"));
        item.setItemMeta(itemMeta);

        setBlackLeftArrow(item);

        return new GuiItem(item, event -> viewRecipes((Player) event.getWhoClicked()));
    }

    private GuiItem getExitItem() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(colorString("&cClose"));
        item.setItemMeta(itemMeta);
        return new GuiItem(item, event -> event.getWhoClicked().closeInventory());
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
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(colorString("&ePrevious Page"));
        item.setItemMeta(itemMeta);

        setBlackLeftArrow(item);

        return new GuiItem(item);
    }

    private GuiItem getForwardButton() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(colorString("&eNext Page"));
        item.setItemMeta(itemMeta);

        setBlackRightArrow(item);

        return new GuiItem(item);
    }
}
