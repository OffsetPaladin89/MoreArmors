package me.offsetpaladin89.MoreArmors.materials;

import com.cryptomorin.xseries.SkullUtils;
import de.tr7zw.changeme.nbtapi.NBTItem;
import dev.dbassett.skullcreator.SkullCreator;
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
	public ItemStack CompactedSugarCane(Integer amount) { return addData(new ItemStack(Material.SUGAR_CANE, amount), Rarity.UNCOMMON, "Compacted Sugar Cane", "compacted_sugar_cane"); }
	public ItemStack CompactedCobblestone(Integer amount) { return addData(new ItemStack(Material.COBBLESTONE, amount), Rarity.COMMON, "Compacted Cobblestone", "compacted_cobblestone"); }
	public ItemStack CompactedSoulSand(Integer amount) { return addData(new ItemStack(Material.SOUL_SAND, amount), Rarity.UNCOMMON, "Compacted Soul Sand", "compacted_soul_sand"); }
	public ItemStack CompactedBlazeRod(Integer amount) { return addData(new ItemStack(Material.BLAZE_ROD, amount), Rarity.UNCOMMON, "Compacted Blaze Rod", "compacted_blaze_rod"); }
	public ItemStack NetherCrown() { return createMaterialSkull("3e4f49535a276aacc4dc84133bfe81be5f2a4799a4c04d9a4ddb72d819ec2b2b", Rarity.EPIC, "Nether Crown", "nether_crown"); }
	public ItemStack CompactedEndStone(Integer amount) { return addData(new ItemStack(Material.END_STONE, amount), Rarity.COMMON, "Compacted End Stone", "compacted_end_stone"); }
	public ItemStack CompactedEyeOfEnder(Integer amount) { return addData(new ItemStack(Material.ENDER_EYE, amount), Rarity.UNCOMMON, "Compacted Eye of Ender", "compacted_eye_of_ender"); }
	public ItemStack CompactedDiamond(Integer amount) { return addData(new ItemStack(Material.DIAMOND, amount), Rarity.UNCOMMON, "Compacted Diamond", "compacted_diamond"); }
	public ItemStack CompactedDiamondBlock(Integer amount) { return addData(new ItemStack(Material.DIAMOND_BLOCK, amount), Rarity.RARE, "Compacted Diamond Block", "compacted_diamond_block"); }
	public ItemStack CompactedGold(Integer amount) { return addData(new ItemStack(Material.GOLD_INGOT, amount), Rarity.UNCOMMON, "Compacted Gold Ingot", "compacted_gold_ingot"); }
	public ItemStack CompactedGoldBlock(Integer amount) { return addData(new ItemStack(Material.GOLD_BLOCK, amount), Rarity.RARE, "Compacted Gold Block", "compacted_gold_block"); }
	public ItemStack CompactedPrismarine(Integer amount) { return addData(new ItemStack(Material.PRISMARINE, amount), Rarity.UNCOMMON, "Compacted Prismarine", "compacted_prismarine"); }
	public void RegisterMaterialsRecipes() {
		plugin.getServer().addRecipe(registerRecipe("compacted_sugar_cane", CompactedSugarCane(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.SUGAR_CANE));
		plugin.getServer().addRecipe(registerRecipe("compacted_cobblestone", CompactedCobblestone(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.COBBLESTONE));
		plugin.getServer().addRecipe(registerRecipe("compacted_soul_sand", CompactedSoulSand(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.SOUL_SAND));
		plugin.getServer().addRecipe(registerRecipe("compacted_blaze_rod", CompactedBlazeRod(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.BLAZE_ROD));
		plugin.getServer().addRecipe(registerRecipe("nether_crown", NetherCrown()).shape("XXX", "XSX", "XXX").setIngredient('X', Material.BLAZE_ROD).setIngredient('S', Material.NETHER_STAR));
		plugin.getServer().addRecipe(registerRecipe("compacted_end_stone", CompactedEndStone(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.END_STONE));
		plugin.getServer().addRecipe(registerRecipe("compacted_eye_of_ender", CompactedEyeOfEnder(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.ENDER_EYE));
		plugin.getServer().addRecipe(registerRecipe("compacted_diamond", CompactedDiamond(1)).shape(" X ", "XXX", " X ").setIngredient('X', Material.DIAMOND));
		plugin.getServer().addRecipe(registerRecipe("compacted_diamond_block", CompactedDiamondBlock(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.DIAMOND));
		plugin.getServer().addRecipe(registerRecipe("compacted_gold_ingot", CompactedGold(1)).shape(" X ", "XXX", " X ").setIngredient('X', Material.GOLD_INGOT));
		plugin.getServer().addRecipe(registerRecipe("compacted_gold_block", CompactedGoldBlock(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.GOLD_INGOT));
		plugin.getServer().addRecipe(registerRecipe("compacted_prismarine", CompactedPrismarine(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.PRISMARINE));
	}
	private ItemStack addData(ItemStack item, Rarity rarity, String name, String itemID) {
		ItemMeta itemmeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "&l" + rarity.toString() + " MATERIAL"));
		itemmeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + name));
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
	private ItemStack createMaterialSkull(String skinID, Rarity rarity, String itemName, String itemID) {
		ItemStack item = SkullCreator.createSkull();
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta = SkullUtils.applySkin(itemmeta, skinID);
		item.setItemMeta(itemmeta);

		NBTItem nbtItem = new NBTItem(addData(item, rarity, itemName, itemID));
		nbtItem.setString("UUID", UUID.randomUUID().toString());
		return nbtItem.getItem();
	}
	private ShapedRecipe registerRecipe(String key, ItemStack item) { return new ShapedRecipe(new NamespacedKey(plugin, key), item); }
}
