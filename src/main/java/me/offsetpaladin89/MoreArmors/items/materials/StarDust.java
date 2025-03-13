package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class StarDust extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.RARE;
    private static final MaterialType MATERIAL_TYPE = MaterialType.STAR_DUST;

    public StarDust(int tier) {
        super(BASE_RARITY, tier, MATERIAL_TYPE);
    }

    public static void getRecipe(MoreArmorsMain plugin) {
        CustomMaterial result = new StarDust(0);
        result.setAmount(8);

        NamespacedKey key = new NamespacedKey(plugin, result.getID());

        ShapedRecipe recipe = new ShapedRecipe(key, result.getItem());
        recipe.shape("AAA", "ABA", "AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(new IronBlock(0).getItem()));
        recipe.setIngredient('B', Material.NETHER_STAR);

        plugin.getServer().addRecipe(recipe);
    }

    // Override Methods

    protected String getDefaultName() {
        return "Star Dust";
    }

    protected ItemStack getPrevious(int tier) {
        return new StarDust(tier - 1).getItem();
    }
}