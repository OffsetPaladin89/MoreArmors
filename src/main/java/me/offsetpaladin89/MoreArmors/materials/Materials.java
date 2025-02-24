package me.offsetpaladin89.MoreArmors.materials;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public record Materials(MoreArmorsMain plugin) {
	public ItemStack CompactedSugarCane(int amount) { return addData(new ItemStack(Material.SUGAR_CANE, amount), Rarity.UNCOMMON, "Compacted Sugar Cane", "compacted_sugar_cane"); }
	public ItemStack CompactedCobblestone(int amount) { return addData(new ItemStack(Material.COBBLESTONE, amount), Rarity.COMMON, "Compacted Cobblestone", "compacted_cobblestone"); }
	public ItemStack CompactedSoulSand(int amount) { return addData(new ItemStack(Material.SOUL_SAND, amount), Rarity.UNCOMMON, "Compacted Soul Sand", "compacted_soul_sand"); }
	public ItemStack CompactedBlazeRod(int amount) { return addData(new ItemStack(Material.BLAZE_ROD, amount), Rarity.UNCOMMON, "Compacted Blaze Rod", "compacted_blaze_rod"); }
//	public ItemStack NetherCrown() { return createMaterialSkull("3e4f49535a276aacc4dc84133bfe81be5f2a4799a4c04d9a4ddb72d819ec2b2b", Rarity.EPIC, "Nether Crown", "nether_crown"); }
	public ItemStack CompactedEndStone(int amount) { return addData(new ItemStack(Material.END_STONE, amount), Rarity.COMMON, "Compacted End Stone", "compacted_end_stone"); }
	public ItemStack CompactedEyeOfEnder(int amount) { return addData(new ItemStack(Material.ENDER_EYE, amount), Rarity.UNCOMMON, "Compacted Eye of Ender", "compacted_eye_of_ender"); }
	public ItemStack CompactedDiamond(int amount) { return addData(new ItemStack(Material.DIAMOND, amount), Rarity.UNCOMMON, "Compacted Diamond", "compacted_diamond"); }
	public ItemStack CompactedDiamondBlock(int amount) { return addData(new ItemStack(Material.DIAMOND_BLOCK, amount), Rarity.RARE, "Compacted Diamond Block", "compacted_diamond_block"); }
	public ItemStack CompactedGold(int amount) { return addData(new ItemStack(Material.GOLD_INGOT, amount), Rarity.UNCOMMON, "Compacted Gold Ingot", "compacted_gold"); }
	public ItemStack CompactedGoldBlock(int amount) { return addData(new ItemStack(Material.GOLD_BLOCK, amount), Rarity.RARE, "Compacted Gold Block", "compacted_gold_block"); }
	public ItemStack CompactedPrismarine(int amount) { return addData(new ItemStack(Material.PRISMARINE, amount), Rarity.UNCOMMON, "Compacted Prismarine", "compacted_prismarine"); }
	public ItemStack CompactedRedstone(int amount) { return addData(new ItemStack(Material.REDSTONE, amount), Rarity.UNCOMMON, "Compacted Redstone", "compacted_redstone"); }
	public ItemStack CompactedIron(int amount) { return addData(new ItemStack(Material.IRON_INGOT, amount), Rarity.UNCOMMON, "Compacted Iron Ingot", "compacted_iron"); }
	public ItemStack CompactedIronBlock(int amount) { return addData(new ItemStack(Material.IRON_BLOCK, amount), Rarity.RARE, "Compacted Iron Block", "compacted_iron_block"); }
	public ItemStack StarDust(int amount) { return addData(new ItemStack(Material.GHAST_TEAR, amount), Rarity.RARE, "Star Dust", "star_dust"); }
//	public ItemStack MachinePart(int amount) { return createMaterialSkull("6131a36e70ffaa7ca7e672ae6ac20b7fc1e457c43a8e1069e7b14ecdb8576", Rarity.RARE, "Machine Part", "machine_part", amount); }
//	public ItemStack EnergyCell() { return createMaterialSkull("9ac52419b99025828c89fa825945e6948e45bb5a22e4425a59e9096e4c1ac38", Rarity.EPIC, "Energy Cell", "energy_cell"); }
//	public ItemStack MachineCore() { return createMaterialSkull("76856a8f37b6c3146854f2caa7101b9dd592f4669a3c75f941e2859552bd1ae8", Rarity.LEGENDARY, "Machine Core", "machine_core"); }
	public void RegisterMaterialsRecipes() {
		if(plugin.configHandler.getConfig("config").getBoolean("materials.crafting")) {
			plugin.getServer().addRecipe(registerRecipe("compacted_sugar_cane", CompactedSugarCane(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.SUGAR_CANE));
			plugin.getServer().addRecipe(registerRecipe("compacted_cobblestone", CompactedCobblestone(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.COBBLESTONE));
			plugin.getServer().addRecipe(registerRecipe("compacted_soul_sand", CompactedSoulSand(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.SOUL_SAND));
			plugin.getServer().addRecipe(registerRecipe("compacted_blaze_rod", CompactedBlazeRod(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.BLAZE_ROD));
//			plugin.getServer().addRecipe(registerRecipe("nether_crown", NetherCrown()).shape("XXX", "XSX", "XXX").setIngredient('X', Material.BLAZE_ROD).setIngredient('S', Material.NETHER_STAR));
			plugin.getServer().addRecipe(registerRecipe("compacted_end_stone", CompactedEndStone(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.END_STONE));
			plugin.getServer().addRecipe(registerRecipe("compacted_eye_of_ender", CompactedEyeOfEnder(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.ENDER_EYE));
			plugin.getServer().addRecipe(registerRecipe("compacted_diamond", CompactedDiamond(1)).shape(" X ", "XXX", " X ").setIngredient('X', Material.DIAMOND));
			plugin.getServer().addRecipe(registerRecipe("compacted_diamond_block", CompactedDiamondBlock(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.DIAMOND));
			plugin.getServer().addRecipe(registerRecipe("compacted_gold", CompactedGold(1)).shape(" X ", "XXX", " X ").setIngredient('X', Material.GOLD_INGOT));
			plugin.getServer().addRecipe(registerRecipe("compacted_gold_block", CompactedGoldBlock(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.GOLD_INGOT));
			plugin.getServer().addRecipe(registerRecipe("compacted_prismarine", CompactedPrismarine(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.PRISMARINE));
			plugin.getServer().addRecipe(registerRecipe("compacted_redstone", CompactedRedstone(1)).shape(" X ", "XXX", " X ").setIngredient('X', Material.REDSTONE));
			plugin.getServer().addRecipe(registerRecipe("compacted_iron", CompactedIron(1)).shape(" X ", "XXX", " X ").setIngredient('X', Material.IRON_INGOT));
			plugin.getServer().addRecipe(registerRecipe("compacted_iron_block", CompactedIronBlock(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.IRON_INGOT));
			plugin.getServer().addRecipe(registerRecipe("star_dust", StarDust(8)).shape("XXX", "XSX", "XXX").setIngredient('X', Material.IRON_INGOT).setIngredient('S', Material.NETHER_STAR));
//			plugin.getServer().addRecipe(registerRecipe("machine_part", MachinePart(1)).shape("XXX", "XSX", "XXX").setIngredient('X', Material.IRON_INGOT).setIngredient('S', Material.REDSTONE));
//			plugin.getServer().addRecipe(registerRecipe("energy_cell", EnergyCell()).shape("XXX", "XSX", "XXX").setIngredient('X', Material.IRON_INGOT).setIngredient('S', Material.GHAST_TEAR));
//			plugin.getServer().addRecipe(registerRecipe("machine_core", MachineCore()).shape("XZX", "XSX", "XZX").setIngredient('X', Material.IRON_BLOCK).setIngredient('S', Material.PLAYER_HEAD).setIngredient('Z', Material.PLAYER_HEAD));
		}
	}
	private ItemStack addData(ItemStack item, Rarity rarity, String name, String itemID) {
		ItemMeta itemmeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(MoreArmorsMain.colorString(Rarity.getColorRarity(rarity) + "&l" + rarity.toString() + " MATERIAL"));
		itemmeta.setDisplayName(MoreArmorsMain.colorString(Rarity.getColorRarity(rarity) + name));
		itemmeta.setLore(lore);
		itemmeta.addEnchant(Enchantment.MENDING, 1, false);
		itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(itemmeta);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setBoolean("IsCustomItem", true);
		nbtItem.setString("CustomItemID", itemID);
		nbtItem.setString("CustomItemType", "material");
		return nbtItem.getItem();
	}

//	private ItemStack createMaterialSkull(String skinID, Rarity rarity, String itemName, String itemID, Integer amount) {
//		ItemStack item = SkullCreator.createSkull();
//		ItemMeta itemmeta = item.getItemMeta();
////		itemmeta = plugin.getSkull(itemmeta, skinID).apply();
//		item.setItemMeta(itemmeta);
//		item.setAmount(amount);
//		NBTItem nbtItem = new NBTItem(addData(item, rarity, itemName, itemID));
//		return nbtItem.getItem();
//	}
//
//	private ItemStack createMaterialSkull(String skinID, Rarity rarity, String itemName, String itemID) {
//		ItemStack item = SkullCreator.createSkull();
//		ItemMeta itemmeta = item.getItemMeta();
////		itemmeta = plugin.getSkull(itemmeta, skinID).apply();
//		item.setItemMeta(itemmeta);
//
//		NBTItem nbtItem = new NBTItem(addData(item, rarity, itemName, itemID));
//		nbtItem.setString("UUID", UUID.randomUUID().toString());
//		return nbtItem.getItem();
//	}
	private ShapedRecipe registerRecipe(String key, ItemStack item) { return new ShapedRecipe(new NamespacedKey(plugin, key), item); }
}
