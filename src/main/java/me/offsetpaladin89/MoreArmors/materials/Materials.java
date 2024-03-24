package me.offsetpaladin89.MoreArmors.materials;

import com.cryptomorin.xseries.SkullUtils;
import de.tr7zw.changeme.nbtapi.NBTItem;
import dev.dbassett.skullcreator.SkullCreator;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public record Materials(MoreArmorsMain plugin) {
	public ItemStack CompactedSugarCane(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.SUGAR_CANE, amount), Rarity.UNCOMMON, "Compacted Sugar Cane", "compacted_sugar_cane"); }
	public ItemStack CompactedCobblestone(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.COBBLESTONE, amount), Rarity.COMMON, "Compacted Cobblestone", "compacted_cobblestone"); }
	public ItemStack CompactedSoulSand(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.SOUL_SAND, amount), Rarity.UNCOMMON, "Compacted Soul Sand", "compacted_soul_sand"); }
	public ItemStack CompactedBlazeRod(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.BLAZE_ROD, amount), Rarity.UNCOMMON, "Compacted Blaze Rod", "compacted_blaze_rod"); }
	public ItemStack NetherCrown() { return createMaterialSkull("3e4f49535a276aacc4dc84133bfe81be5f2a4799a4c04d9a4ddb72d819ec2b2b", Rarity.EPIC, "Nether Crown", "nether_crown"); }
	public ItemStack CompactedEndStone(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.END_STONE, amount), Rarity.COMMON, "Compacted End Stone", "compacted_end_stone"); }
	public ItemStack CompactedEyeOfEnder(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.ENDER_EYE, amount), Rarity.UNCOMMON, "Compacted Eye of Ender", "compacted_eye_of_ender"); }
	public ItemStack CompactedDiamond(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.DIAMOND, amount), Rarity.UNCOMMON, "Compacted Diamond", "compacted_diamond"); }
	public ItemStack CompactedDiamondBlock(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.DIAMOND_BLOCK, amount), Rarity.RARE, "Compacted Diamond Block", "compacted_diamond_block"); }
	public ItemStack DiamondSingularity() { return createMaterialSkull("f073d84d6fda6a3404e77ad8d0f190893ae66d195fbb44d3c4607a6b71d9b9d5", Rarity.EPIC, "Diamond Singularity", "diamond_singularity"); }
	public void RegisterMaterialsRecipes() {
		plugin.getServer().addRecipe(registerRecipe("compacted_sugar_cane", CompactedSugarCane(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.SUGAR_CANE));
		plugin.getServer().addRecipe(registerRecipe("compacted_cobblestone", CompactedCobblestone(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.COBBLESTONE));
		plugin.getServer().addRecipe(registerRecipe("compacted_soul_sand", CompactedSoulSand(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.SOUL_SAND));
		plugin.getServer().addRecipe(registerRecipe("compacted_blaze_rod", CompactedBlazeRod(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.BLAZE_ROD));
		plugin.getServer().addRecipe(registerRecipe("nether_crown", NetherCrown()).shape("XXX", "XSX", "XXX").setIngredient('X', Material.BLAZE_ROD).setIngredient('S', Material.NETHER_STAR));
		plugin.getServer().addRecipe(registerRecipe("compacted_end_stone", CompactedEndStone(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.END_STONE));
		plugin.getServer().addRecipe(registerRecipe("compacted_eye_of_ender", CompactedEyeOfEnder(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.ENDER_EYE));
		plugin.getServer().addRecipe(registerRecipe("compacted_diamond", CompactedDiamond(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.DIAMOND));
		plugin.getServer().addRecipe(registerRecipe("compacted_diamond_block", CompactedDiamondBlock(1)).shape("XXX", "XXX", "XXX").setIngredient('X', Material.DIAMOND));
		plugin.getServer().addRecipe(registerRecipe("diamond_singularity", DiamondSingularity()).shape("XXX", "XSX", "XXX").setIngredient('X', Material.DIAMOND_BLOCK).setIngredient('S', Material.DIAMOND));
	}
	private ItemStack createMaterialSkull(String skinID, Rarity rarity, String itemName, String itemID) {
		ItemStack item = SkullCreator.createSkull();
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta = SkullUtils.applySkin(itemmeta, skinID);
		item.setItemMeta(itemmeta);
		item = plugin.materialsdata.addInfo(item, rarity, itemName, itemID);

		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setString("UUID", UUID.randomUUID().toString());
		return nbtItem.getItem();
	}
	private ShapedRecipe registerRecipe(String key, ItemStack item) { return new ShapedRecipe(new NamespacedKey(plugin, key), item); }
}
