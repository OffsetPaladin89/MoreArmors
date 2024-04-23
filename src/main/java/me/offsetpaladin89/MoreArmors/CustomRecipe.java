package me.offsetpaladin89.MoreArmors;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CustomRecipe {

	/** The ID of the recipe */
	private final String id;
	/** The materials for the recipe */
	private final List<ItemStack> materials;
	/** The result of the recipe */
//	private final ItemStack result;

	/**
	 * Constructs a new CustomRecipe object with the specified id, materials, and result.
	 * @param id the ID of the recipe
	 * @param materials the materials in the recipe
//	 * @param result the result of the recipe
	 */
	public CustomRecipe(String id, List<ItemStack> materials) {
		this.id = id;
		this.materials = materials;
//		this.result = result;
	}

	/**
	 * Returns the ID of the recipe.
	 * @return the ID of the recipe
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns a ItemStack list of materials.
	 * @return a ItemStack list of materials
	 */
	public List<ItemStack> getMaterials() {
		return materials;
	}

	/**
	 * Returns the result for the recipe.
	 * @return the result for the recipe
	 */
//	public ItemStack getResult() {
//		return result;
//	}
}
