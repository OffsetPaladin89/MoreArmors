package me.offsetpaladin89.MoreArmors.inventories;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.offsetpaladin89.MoreArmors.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public record Inventories(Main plugin) {

	public ChestGui mainInventory(Player p) {
		ChestGui g = new ChestGui(3, plugin.convertColoredString("&0MoreArmors Main Menu"));

		g.setOnGlobalClick(event -> event.setCancelled(true));

		OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
		background.addItem(new GuiItem(paneItem()));
		background.setRepeat(true);

		StaticPane navItems = new StaticPane(2, 1, 5, 1);
		navItems.addItem(new GuiItem(craftingItem(), event -> {
			plugin.sendPlayerMessage(p, "&aClicked Crafting Menu!");
		}), 0, 0);
		navItems.addItem(new GuiItem(commandsItem(p), event -> {
			plugin.sendPlayerMessage(p, "&aClicked Command Menu!");
		}), 2, 0);
		navItems.addItem(new GuiItem(configItem(p), event -> {
			plugin.sendPlayerMessage(p, "&aClicked Config Menu!");
		}), 4, 0);

		g.addPane(background);
		g.addPane(navItems);
		return g;
	}

	public ItemStack craftingItem() {
		ItemStack i = new ItemStack(Material.CRAFTING_TABLE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(plugin.convertColoredString("&6Craft Items"));
		ArrayList<String> l = new ArrayList<>();
		addLore(l, "&7Click to open the crafting menu");
		addLore(l, "&7to &acraft armor and materials&7.");
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}

	public ItemStack commandsItem(Player p) {
		ItemStack i = new ItemStack(Material.COMMAND_BLOCK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(plugin.convertColoredString("&6Commands"));
		ArrayList<String> l = new ArrayList<>();
		addLore(l, "&7Click to open the commands");
		addLore(l, "&7menu to &arun commands&7.");
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}

	public ItemStack configItem(Player p) {
		ItemStack i = new ItemStack(Material.PAPER);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(plugin.convertColoredString("&6Config"));
		ArrayList<String> l = new ArrayList<>();
		addLore(l, "&7Click to open the config");
		addLore(l, "&7menu to &aedit the config&7.");
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}

	public void addLore(ArrayList<String> l, String s) { l.add(plugin.convertColoredString(s)); }

	public ItemStack paneItem() {
		ItemStack i = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(plugin.convertColoredString("&0"));
		i.setItemMeta(im);
		return i;
	}
}
