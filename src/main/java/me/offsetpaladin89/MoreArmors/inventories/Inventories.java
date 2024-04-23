package me.offsetpaladin89.MoreArmors.inventories;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.offsetpaladin89.MoreArmors.Main;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;

public record Inventories(Main plugin) {

	public ChestGui mainInventory(Player p) {
		ChestGui g = new ChestGui(4, plugin.convertColoredString("&0MoreArmors Main Menu"));

		g.setOnGlobalClick(event -> event.setCancelled(true));

		OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
		background.addItem(new GuiItem(paneItem()));
		background.setRepeat(true);

		OutlinePane navItems = new OutlinePane(2, 1, 5, 1);
		navItems.addItem(new GuiItem(craftingItem(), event -> craftingInventory(p).show(p)));
		navItems.addItem(new GuiItem(commandsItem(), event -> {
			plugin.sendPlayerMessage(p, "&aClicked Command Menu!");
		}));
		navItems.addItem(new GuiItem(configItem(), event -> {
			plugin.sendPlayerMessage(p, "&aClicked Config Menu!");
		}));
		navItems.setGap(1);

		StaticPane navBar = navBar(3);
		navBar.addItem(new GuiItem(closeItem(), event -> event.getView().close()), 4, 0);

		g.addPane(background);
		g.addPane(navItems);
		g.addPane(navBar);
		return g;
	}

	public ChestGui craftingInventory(Player p) {
		ChestGui g = new ChestGui(5, plugin.convertColoredString("&0Crafting Menu"));

		g.setOnGlobalClick(event -> event.setCancelled(true));

		OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
		background.addItem(new GuiItem(paneItem()));
		background.setRepeat(true);

		OutlinePane craftableItems = new OutlinePane(1, 1, 7, 2);
		craftableItems.addItem(new GuiItem(armorItem(new ItemStack(Material.IRON_BLOCK), "titan", Rarity.UNCOMMON), event -> armorCraftingInventory(p, ArmorType.TITAN).show(p)));
		craftableItems.addItem(new GuiItem(armorItem(new ItemStack(Material.EMERALD_BLOCK), "emerald", Rarity.EPIC), event -> armorCraftingInventory(p, ArmorType.EMERALD).show(p)));
		craftableItems.addItem(new GuiItem(armorItem(new ItemStack(Material.LAPIS_BLOCK), "experience", Rarity.RARE), event -> armorCraftingInventory(p, ArmorType.EXPERIENCE).show(p)));

		craftableItems.addItem(new GuiItem(armorItem(createPotion(PotionType.LONG_SWIFTNESS), "speedster", Rarity.RARE), event -> armorCraftingInventory(p, ArmorType.SPEEDSTER).show(p)));
		craftableItems.addItem(new GuiItem(armorItem(new ItemStack(Material.GOLDEN_PICKAXE), "miner", Rarity.UNCOMMON), event -> armorCraftingInventory(p, ArmorType.MINER).show(p)));
		craftableItems.addItem(new GuiItem(armorItem(plugin.armorSets.NetherArmor(EquipmentSlot.HEAD), "nether", Rarity.LEGENDARY), event -> armorCraftingInventory(p, ArmorType.NETHER).show(p)));
		craftableItems.addItem(new GuiItem(armorItem(plugin.armorSets.EndArmor(EquipmentSlot.HEAD), "end", Rarity.LEGENDARY), event -> armorCraftingInventory(p, ArmorType.END).show(p)));
		craftableItems.addItem(new GuiItem(armorItem(plugin.armorSets.SeaGreedArmor(EquipmentSlot.HEAD), "sea greed", Rarity.MYTHIC), event -> armorCraftingInventory(p, ArmorType.SEA_GREED).show(p)));
		craftableItems.addItem(new GuiItem(armorItem(plugin.armorSets.DestroyerArmor(EquipmentSlot.HEAD, 0), "destroyer", Rarity.MYTHIC), event -> armorCraftingInventory(p, ArmorType.DESTROYER).show(p)));
		craftableItems.addItem(new GuiItem(materialItem(), event -> materialCraftingInventory(p).show(p)));
		craftableItems.align(OutlinePane.Alignment.CENTER);

		StaticPane navBar = navBar(4);
		navBar.addItem(new GuiItem(backItem(), event -> mainInventory(p).show(p)), 0, 0);
		navBar.addItem(new GuiItem(closeItem(), event -> event.getView().close()), 4, 0);

		g.addPane(background);
		g.addPane(craftableItems);
		g.addPane(navBar);
		return g;
	}

	public ChestGui materialCraftingInventory(Player p) {
		ChestGui g = new ChestGui(6, plugin.convertColoredString("&0Materials Crafting Menu"));

		g.setOnGlobalClick(event -> event.setCancelled(true));

		OutlinePane craftableItems = new OutlinePane(1, 1, 7, 3);
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_BLAZE_ROD), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_COBBLESTONE), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_END_STONE), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_EYE_OF_ENDER), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_SOUL_SAND), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_SUGAR_CANE), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.NETHER_CROWN), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_DIAMOND), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_DIAMOND_BLOCK), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_GOLD), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_GOLD_BLOCK), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_PRISMARINE), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_REDSTONE), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_IRON), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.COMPACTED_IRON_BLOCK), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.STAR_DUST), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.MACHINE_PART), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.ENERGY_CELL), event -> {

		}));
		craftableItems.addItem(new GuiItem(getMaterial(MaterialType.MACHINE_CORE), event -> {

		}));
		craftableItems.align(OutlinePane.Alignment.CENTER);

		StaticPane navBar = navBar(5);
		navBar.addItem(new GuiItem(backItem(), event -> craftingInventory(p).show(p)), 0, 0);
		navBar.addItem(new GuiItem(closeItem(), event -> event.getView().close()), 4, 0);

		g.addPane(background(6));
		g.addPane(craftableItems);
		g.addPane(navBar);
		return g;
	}

	public ChestGui craft(Player p, ArmorType type, EquipmentSlot slot) {
		ChestGui g = new ChestGui(4, plugin.convertColoredString("&0Crafting: " + getArmor(type, slot).getItemMeta().getDisplayName()));

		g.addPane(background(3));
		return g;
	}

	public ChestGui armorCraftingInventory(Player p, ArmorType type) {
		ChestGui g = new ChestGui(4, plugin.convertColoredString("&0" + ArmorType.getSetName(type) + " Armor Crafting Menu"));

		g.setOnGlobalClick(event -> event.setCancelled(true));

		OutlinePane craftableItems = new OutlinePane(1, 1, 7, 1);
		craftableItems.addItem(new GuiItem(getArmor(type, EquipmentSlot.HEAD), event -> {

		}));
		craftableItems.addItem(new GuiItem(getArmor(type, EquipmentSlot.CHEST), event -> {

		}));
		craftableItems.addItem(new GuiItem(getArmor(type, EquipmentSlot.LEGS), event -> {

		}));
		craftableItems.addItem(new GuiItem(getArmor(type, EquipmentSlot.FEET), event -> {

		}));
		craftableItems.setGap(1);

		StaticPane navBar = navBar(3);
		navBar.addItem(new GuiItem(backItem(), event -> craftingInventory(p).show(p)), 0, 0);
		navBar.addItem(new GuiItem(closeItem(), event -> event.getView().close()), 4, 0);

		g.addPane(background(4));
		g.addPane(craftableItems);
		g.addPane(navBar);
		return g;
	}

	private ItemStack getMaterial(MaterialType type) {
		ItemStack i = switch (type) {
			case COMPACTED_BLAZE_ROD -> plugin.materials.CompactedBlazeRod(1);
			case COMPACTED_COBBLESTONE -> plugin.materials.CompactedCobblestone(1);
			case COMPACTED_END_STONE -> plugin.materials.CompactedEndStone(1);
			case COMPACTED_EYE_OF_ENDER -> plugin.materials.CompactedEyeOfEnder(1);
			case COMPACTED_SOUL_SAND -> plugin.materials.CompactedSoulSand(1);
			case COMPACTED_SUGAR_CANE -> plugin.materials.CompactedSugarCane(1);
			case NETHER_CROWN -> plugin.materials.NetherCrown();
			case COMPACTED_DIAMOND -> plugin.materials.CompactedDiamond(1);
			case COMPACTED_DIAMOND_BLOCK -> plugin.materials.CompactedDiamondBlock(1);
			case COMPACTED_GOLD -> plugin.materials.CompactedGold(1);
			case COMPACTED_GOLD_BLOCK -> plugin.materials.CompactedGoldBlock(1);
			case COMPACTED_PRISMARINE -> plugin.materials.CompactedPrismarine(1);
			case COMPACTED_REDSTONE -> plugin.materials.CompactedRedstone(1);
			case COMPACTED_IRON -> plugin.materials.CompactedIron(1);
			case COMPACTED_IRON_BLOCK -> plugin.materials.CompactedIronBlock(1);
			case STAR_DUST -> plugin.materials.StarDust(1);
			case MACHINE_PART -> plugin.materials.MachinePart(1);
			case ENERGY_CELL -> plugin.materials.EnergyCell();
			case MACHINE_CORE -> plugin.materials.MachineCore();
		};
		ItemMeta im = i.getItemMeta();
		ArrayList<String> lore = (ArrayList<String>) im.getLore();
		addLore(lore, "");
		addLore(lore, "&eClick to craft!");
		im.setLore(lore);
		i.setItemMeta(im);
		return i;

	}

	private ItemStack getArmor(ArmorType type, EquipmentSlot slot) {
		ItemStack i = switch(type) {
			case EMERALD -> plugin.armorSets.EmeraldArmor(slot, 0);
			case END -> plugin.armorSets.EndArmor(slot);
			case EXPERIENCE -> plugin.armorSets.ExperienceArmor(slot);
			case MINER -> plugin.armorSets.MinerArmor(slot);
			case NETHER -> plugin.armorSets.NetherArmor(slot);
			case SEA_GREED -> plugin.armorSets.SeaGreedArmor(slot);
			case SPEEDSTER -> plugin.armorSets.SpeedsterArmor(slot);
			case TITAN -> plugin.armorSets.TitanArmor(slot);
			case DESTROYER -> plugin.armorSets.DestroyerArmor(slot, 0);
		};
		ItemMeta im = i.getItemMeta();
		ArrayList<String> lore = (ArrayList<String>) im.getLore();
		addLore(lore, "");
		addLore(lore, "&eClick to craft!");
		im.setLore(lore);
		i.setItemMeta(im);
		return i;
	}

	private OutlinePane background(int h) {
		OutlinePane background = new OutlinePane(0, 0, 9, h, Pane.Priority.LOWEST);
		background.addItem(new GuiItem(paneItem()));
		background.setRepeat(true);
		return background;
	}

	private StaticPane navBar(int y) { return new StaticPane(0, y, 9, 1); }

	private ItemStack backItem() {
		ItemStack i = plugin.grayFont.getText('←');
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(plugin.convertColoredString("&cGo Back"));
		i.setItemMeta(im);
		return i;
	}

	private ItemStack closeItem() {
		ItemStack i = new ItemStack(Material.BARRIER);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(plugin.convertColoredString("&cClose"));
		i.setItemMeta(im);
		return i;
	}

	private ItemStack materialItem() {
		ItemStack i = new ItemStack(Material.ANVIL);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(plugin.convertColoredString("&6Material Recipes"));
		ArrayList<String> l = new ArrayList<>();
		addLore(l, "&7Click to craft and view");
		addLore(l, "&7material recipes.");
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}

	private ItemStack createPotion(PotionType type) {
		ItemStack i = new ItemStack(Material.POTION);
		PotionMeta im = (PotionMeta) i.getItemMeta();
		im.setBasePotionType(type);
		im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		i.setItemMeta(im);
		return i;
	}
	private ItemStack armorItem(ItemStack i, String s, Rarity r) {
		s = WordUtils.capitalizeFully(s + " armor");
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(plugin.convertColoredString("&6" + s + " Recipes"));
		ArrayList<String> l = new ArrayList<>();
		addLore(l, "&7Click to craft and view");
		addLore(l, Rarity.getColorRarity(r) + s + " &7recipes.");
		im.setLore(l);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		i.setItemMeta(im);
		return i;
	}

	private ItemStack craftingItem() {
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

	private ItemStack commandsItem() {
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

	private ItemStack configItem() {
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

	private ItemStack paneItem() {
		ItemStack i = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(plugin.convertColoredString("&0"));
		i.setItemMeta(im);
		return i;
	}
}
