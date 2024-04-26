package me.offsetpaladin89.MoreArmors;

import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.items.Materials;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class RegisterRecipes {

	private final Main plugin;
	private final Materials materials;

	public RegisterRecipes(Main plugin) {
		this.plugin = plugin;
		this.materials = plugin.materials;
	}

	/** Registers all the armor sets in MoreArmors. */
	public void registerRecipes() {
		registerArmorSet(ArmorType.TITAN);
		registerArmorSet(ArmorType.EMERALD);
		registerArmorSet(ArmorType.EXPERIENCE);
		registerArmorSet(ArmorType.SPEEDSTER);
		registerArmorSet(ArmorType.MINER);
		registerArmorSet(ArmorType.NETHER);
		registerArmorSet(ArmorType.END);
		registerArmorSet(ArmorType.SEA_GREED);
		registerArmorSet(ArmorType.DESTROYER);
	}

	/**
	 * Registers an armor set with the specified type
	 * @param armor Type of armor set
	 */
	private void registerArmorSet(ArmorType armor) {
		// Registers
//		registerRecipe(armorRecipes(armor, SlotType.HELMET));
//		registerRecipe(armorRecipes(armor, SlotType.CHESTPLATE));
//		registerRecipe(armorRecipes(armor, SlotType.LEGGINGS));
//		registerRecipe(armorRecipes(armor, SlotType.BOOTS));
	}

	/**
	 * Returns the recipe for the specified armor set and slot.
	 * @param armor the armor set for the recipe
	 * @param slot the slot for the recipe
	 * @return the recipe for the specified armor set and slot
	 */
//	private CustomRecipe armorRecipes(ArmorType armor, SlotType slot) {
//		return switch (armor) {
//			case TITAN: yield titanArmorRecipes(slot);
//			case EMERALD: yield emeraldArmorRecipes(slot);
//		};
//	}

	/**
	 * Returns the Titan armor recipe for the specified slot.
	 * @param slot the slot for the recipe
	 * @return the Titan armor recipe for the specified slot
	 */
	private CustomRecipe titanArmorRecipes(SlotType slot) {
		List<ItemStack> materials = new ArrayList<>();
		return switch (slot) {
			case HELMET:
				materials.add(new ItemStack(Material.IRON_BLOCK, 5));
				yield new CustomRecipe("titan_helmet", materials);
			case CHESTPLATE:
				materials.add(new ItemStack(Material.IRON_BLOCK, 8));
				yield new CustomRecipe("titan_chestplate", materials);
			case LEGGINGS:
				materials.add(new ItemStack(Material.IRON_BLOCK, 7));
				yield new CustomRecipe("titan_leggings", materials);
			case BOOTS:
				materials.add(new ItemStack(Material.IRON_BLOCK, 4));
				yield new CustomRecipe("titan_boots", materials);
		};
	}

	/**
	 * Returns the Emerald armor recipe for the specified slot.
	 * @param slot the slot for the recipe
	 * @return the Emerald armor recipe for the specified slot
	 */
	private CustomRecipe emeraldArmorRecipes(SlotType slot) {
		List<ItemStack> materials = new ArrayList<>();
		return switch (slot) {
			case HELMET:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 5));
				yield new CustomRecipe("emerald_helmet", materials);
			case CHESTPLATE:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 8));
				yield new CustomRecipe("emerald_chestplate", materials);
			case LEGGINGS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 7));
				yield new CustomRecipe("emerald_leggings", materials);
			case BOOTS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 4));
				yield new CustomRecipe("emerald_boots", materials);
		};
	}

	/**
	 * Returns the Experience armor recipe for the specified slot.
	 * @param slot the slot for the recipe
	 * @return the Experience armor recipe for the specified slot
	 */
	private CustomRecipe experienceArmorRecipe(SlotType slot) {
		List<ItemStack> materials = new ArrayList<>();
		return switch (slot) {
			case HELMET:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 5));
				yield new CustomRecipe("emerald_helmet", materials);
			case CHESTPLATE:
				materials.add(new ItemStack(Material.LAPIS_BLOCK, 8));
				yield new CustomRecipe("emerald_chestplate", materials);
			case LEGGINGS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 7));
				yield new CustomRecipe("emerald_leggings", materials);
			case BOOTS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 4));
				yield new CustomRecipe("emerald_boots", materials);
		};
	}

	/**
	 * Returns the Experience armor recipe for the specified slot.
	 * @param slot the slot for the recipe
	 * @return the Experience armor recipe for the specified slot
	 */
	private CustomRecipe speedsterArmorRecipe(SlotType slot) {
		List<ItemStack> materials = new ArrayList<>();
		return switch (slot) {
			case HELMET:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 5));
				yield new CustomRecipe("emerald_helmet", materials);
			case CHESTPLATE:
				materials.add(new ItemStack(Material.LAPIS_BLOCK, 8));
				yield new CustomRecipe("emerald_chestplate", materials);
			case LEGGINGS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 7));
				yield new CustomRecipe("emerald_leggings", materials);
			case BOOTS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 4));
				yield new CustomRecipe("emerald_boots", materials);
		};
	}

	/**
	 * Returns the Experience armor recipe for the specified slot.
	 * @param slot the slot for the recipe
	 * @return the Experience armor recipe for the specified slot
	 */
	private CustomRecipe minerArmorRecipe(SlotType slot) {
		List<ItemStack> materials = new ArrayList<>();
		return switch (slot) {
			case HELMET:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 5));
				yield new CustomRecipe("emerald_helmet", materials);
			case CHESTPLATE:
				materials.add(new ItemStack(Material.LAPIS_BLOCK, 8));
				yield new CustomRecipe("emerald_chestplate", materials);
			case LEGGINGS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 7));
				yield new CustomRecipe("emerald_leggings", materials);
			case BOOTS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 4));
				yield new CustomRecipe("emerald_boots", materials);
		};
	}

	/**
	 * Returns the Experience armor recipe for the specified slot.
	 * @param slot the slot for the recipe
	 * @return the Experience armor recipe for the specified slot
	 */
	private CustomRecipe netherArmorRecipe(SlotType slot) {
		List<ItemStack> materials = new ArrayList<>();
		return switch (slot) {
			case HELMET:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 5));
				yield new CustomRecipe("emerald_helmet", materials);
			case CHESTPLATE:
				materials.add(new ItemStack(Material.LAPIS_BLOCK, 8));
				yield new CustomRecipe("emerald_chestplate", materials);
			case LEGGINGS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 7));
				yield new CustomRecipe("emerald_leggings", materials);
			case BOOTS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 4));
				yield new CustomRecipe("emerald_boots", materials);
		};
	}

	/**
	 * Returns the Experience armor recipe for the specified slot.
	 * @param slot the slot for the recipe
	 * @return the Experience armor recipe for the specified slot
	 */
	private CustomRecipe endArmorRecipe(SlotType slot) {
		List<ItemStack> materials = new ArrayList<>();
		return switch (slot) {
			case HELMET:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 5));
				yield new CustomRecipe("emerald_helmet", materials);
			case CHESTPLATE:
				materials.add(new ItemStack(Material.LAPIS_BLOCK, 8));
				yield new CustomRecipe("emerald_chestplate", materials);
			case LEGGINGS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 7));
				yield new CustomRecipe("emerald_leggings", materials);
			case BOOTS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 4));
				yield new CustomRecipe("emerald_boots", materials);
		};
	}

	/**
	 * Returns the Experience armor recipe for the specified slot.
	 * @param slot the slot for the recipe
	 * @return the Experience armor recipe for the specified slot
	 */
	private CustomRecipe seaGreedArmorRecipe(SlotType slot) {
		List<ItemStack> materials = new ArrayList<>();
		return switch (slot) {
			case HELMET:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 5));
				yield new CustomRecipe("emerald_helmet", materials);
			case CHESTPLATE:
				materials.add(new ItemStack(Material.LAPIS_BLOCK, 8));
				yield new CustomRecipe("emerald_chestplate", materials);
			case LEGGINGS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 7));
				yield new CustomRecipe("emerald_leggings", materials);
			case BOOTS:
				materials.add(new ItemStack(Material.EMERALD_BLOCK, 4));
				yield new CustomRecipe("emerald_boots", materials);
		};
	}

	/**
	 * Returns the Destroyer armor recipe for the specified slot.
	 * @param slot the slot for the recipe
	 * @return the Destroyer armor recipe for the specified slot
	 */
	private CustomRecipe destroyerArmorRecipe(SlotType slot) {
		List<ItemStack> materialList = new ArrayList<>();
		return switch (slot) {
			case HELMET:
				materialList.add(new ItemStack(Material.EMERALD_BLOCK, 5));
				yield new CustomRecipe("destroyer_helmet", materialList);
			case CHESTPLATE:
				materialList.add(new ItemStack(Material.LAPIS_BLOCK, 8));
				yield new CustomRecipe("destroyer_chestplate", materialList);
			case LEGGINGS:
				materialList.add(new ItemStack(Material.EMERALD_BLOCK, 7));
				yield new CustomRecipe("destroyer_leggings", materialList);
			case BOOTS:
				materialList.add(materials.CompactedIronBlock(16));
				yield new CustomRecipe("destroyer_boots", materialList);
		};
	}

	/**
	 * Registers a recipe.
	 * @param recipe the recipe to be registered
	 */
	private void registerRecipe(CustomRecipe recipe) {
		plugin.recipeList.add(recipe);
	}
}
