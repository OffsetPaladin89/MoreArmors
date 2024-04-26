package me.offsetpaladin89.MoreArmors.items;

import com.cryptomorin.xseries.SkullUtils;
import de.tr7zw.changeme.nbtapi.NBTItem;
import dev.dbassett.skullcreator.SkullCreator;
import me.offsetpaladin89.MoreArmors.Main;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

import static me.offsetpaladin89.MoreArmors.Main.convertColoredString;

public record Materials(Main plugin) {
	public static ItemStack CompactedSugarCane(Integer amount) {
		return addData(new ItemStack(Material.SUGAR_CANE, amount), Rarity.UNCOMMON, "Compacted Sugar Cane", "compacted_sugar_cane");
	}

	public static ItemStack CompactedCobblestone(Integer amount) {
		return addData(new ItemStack(Material.COBBLESTONE, amount), Rarity.COMMON, "Compacted Cobblestone", "compacted_cobblestone");
	}

	public static ItemStack CompactedSoulSand(Integer amount) {
		return addData(new ItemStack(Material.SOUL_SAND, amount), Rarity.UNCOMMON, "Compacted Soul Sand", "compacted_soul_sand");
	}

	public static ItemStack CompactedBlazeRod(Integer amount) {
		return addData(new ItemStack(Material.BLAZE_ROD, amount), Rarity.UNCOMMON, "Compacted Blaze Rod", "compacted_blaze_rod");
	}

	public static ItemStack NetherCrown() {
		return createMaterialSkull("3e4f49535a276aacc4dc84133bfe81be5f2a4799a4c04d9a4ddb72d819ec2b2b", Rarity.EPIC, "Nether Crown", "nether_crown");
	}

	public static ItemStack CompactedEndStone(Integer amount) {
		return addData(new ItemStack(Material.END_STONE, amount), Rarity.COMMON, "Compacted End Stone", "compacted_end_stone");
	}

	public static ItemStack CompactedEyeOfEnder(Integer amount) {
		return addData(new ItemStack(Material.ENDER_EYE, amount), Rarity.UNCOMMON, "Compacted Eye of Ender", "compacted_eye_of_ender");
	}

	public static ItemStack CompactedDiamond(Integer amount) {
		return addData(new ItemStack(Material.DIAMOND, amount), Rarity.UNCOMMON, "Compacted Diamond", "compacted_diamond");
	}

	public static ItemStack CompactedDiamondBlock(Integer amount) {
		return addData(new ItemStack(Material.DIAMOND_BLOCK, amount), Rarity.RARE, "Compacted Diamond Block", "compacted_diamond_block");
	}

	public static ItemStack CompactedGold(Integer amount) {
		return addData(new ItemStack(Material.GOLD_INGOT, amount), Rarity.UNCOMMON, "Compacted Gold Ingot", "compacted_gold");
	}

	public static ItemStack CompactedGoldBlock(Integer amount) {
		return addData(new ItemStack(Material.GOLD_BLOCK, amount), Rarity.RARE, "Compacted Gold Block", "compacted_gold_block");
	}

	public static ItemStack CompactedPrismarine(Integer amount) {
		return addData(new ItemStack(Material.PRISMARINE, amount), Rarity.UNCOMMON, "Compacted Prismarine", "compacted_prismarine");
	}

	public static ItemStack CompactedRedstone(Integer amount) {
		return addData(new ItemStack(Material.REDSTONE, amount), Rarity.UNCOMMON, "Compacted Redstone", "compacted_redstone");
	}

	public static ItemStack CompactedIron(Integer amount) {
		return addData(new ItemStack(Material.IRON_INGOT, amount), Rarity.UNCOMMON, "Compacted Iron Ingot", "compacted_iron");
	}

	public static ItemStack CompactedIronBlock(Integer amount) {
		return addData(new ItemStack(Material.IRON_BLOCK, amount), Rarity.RARE, "Compacted Iron Block", "compacted_iron_block");
	}

	public static ItemStack StarDust(Integer amount) {
		return addData(new ItemStack(Material.GHAST_TEAR, amount), Rarity.RARE, "Star Dust", "star_dust");
	}

	public static ItemStack MachinePart(Integer amount) {
		return createMaterialSkull("6131a36e70ffaa7ca7e672ae6ac20b7fc1e457c43a8e1069e7b14ecdb8576", Rarity.RARE, "Machine Part", "machine_part", amount);
	}

	public static ItemStack EnergyCell() {
		return createMaterialSkull("9ac52419b99025828c89fa825945e6948e45bb5a22e4425a59e9096e4c1ac38", Rarity.EPIC, "Energy Cell", "energy_cell");
	}

	public static ItemStack MachineCore() {
		return createMaterialSkull("76856a8f37b6c3146854f2caa7101b9dd592f4669a3c75f941e2859552bd1ae8", Rarity.LEGENDARY, "Machine Core", "machine_core");
	}

	private static ItemStack addData(ItemStack item, Rarity rarity, String name, String itemID) {
		ItemMeta itemmeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(convertColoredString(Rarity.getColorRarity(rarity) + "&l" + rarity.toString() + " MATERIAL"));
		itemmeta.setDisplayName(convertColoredString(Rarity.getColorRarity(rarity) + name));
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

	private static ItemStack createMaterialSkull(String skinID, Rarity rarity, String itemName, String itemID, Integer amount) {
		ItemStack item = SkullCreator.createSkull();
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta = SkullUtils.applySkin(itemmeta, skinID);
		item.setItemMeta(itemmeta);
		item.setAmount(amount);
		NBTItem nbtItem = new NBTItem(addData(item, rarity, itemName, itemID));
		return nbtItem.getItem();
	}

	private static ItemStack createMaterialSkull(String skinID, Rarity rarity, String itemName, String itemID) {
		ItemStack item = SkullCreator.createSkull();
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta = SkullUtils.applySkin(itemmeta, skinID);
		item.setItemMeta(itemmeta);

		NBTItem nbtItem = new NBTItem(addData(item, rarity, itemName, itemID));
		nbtItem.setString("UUID", UUID.randomUUID().toString());
		return nbtItem.getItem();
	}
}
