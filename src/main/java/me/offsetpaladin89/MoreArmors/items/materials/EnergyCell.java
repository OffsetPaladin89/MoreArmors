package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.UUID;

public class EnergyCell extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.LEGENDARY;
    private static final MaterialType MATERIAL_TYPE = MaterialType.ENERGY_CELL;
    private static final UUID SKULL_UUID = UUID.nameUUIDFromBytes("ENERGY_CELL".getBytes());

    public EnergyCell(int tier) {
        super(BASE_RARITY, tier, MATERIAL_TYPE);
    }

    public static void getRecipe(MoreArmorsMain plugin) {
        CustomMaterial result = new EnergyCell(0);

        NamespacedKey key = new NamespacedKey(plugin, result.getID());

        ShapedRecipe recipe = new ShapedRecipe(key, result.getItem());
        recipe.shape("AAA", "ABA", "AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(new IronBlock(0).getItem()));
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new StarDust(0).getItem()));

        plugin.getServer().addRecipe(recipe);
    }

    // Override Methods

    protected String getDefaultName() {
        return "Energy Cell";
    }

    protected void setTexture() {
        Util.modifySkullSkin(item, "9ac52419b99025828c89fa825945e6948e45bb5a22e4425a59e9096e4c1ac38", SKULL_UUID);
    }
}