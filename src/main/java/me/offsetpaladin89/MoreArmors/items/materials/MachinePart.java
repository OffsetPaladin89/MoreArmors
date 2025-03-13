package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.UUID;

public class MachinePart extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.EPIC;
    private static final MaterialType MATERIAL_TYPE = MaterialType.MACHINE_PART;
    private static final UUID SKULL_UUID = UUID.nameUUIDFromBytes("MACHINE_PART".getBytes());

    public MachinePart(int tier) {
        super(BASE_RARITY, tier, MATERIAL_TYPE);
    }

    public static void getRecipe(MoreArmorsMain plugin) {
        for(int i = 0; i <= 1; i++) {
            CustomMaterial result = new MachinePart(i);

            NamespacedKey key = new NamespacedKey(plugin, String.format("%s_%d", result.getID(), i));

            ShapedRecipe recipe = new ShapedRecipe(key, result.getItem());
            recipe.shape("AAA", "ABA", "AAA");
            recipe.setIngredient('A', new RecipeChoice.ExactChoice(new IronBlock(i).getItem()));
            if(i == 0) recipe.setIngredient('B', new RecipeChoice.ExactChoice(new RedstoneBlock(0).getItem()));
            else recipe.setIngredient('B', new RecipeChoice.ExactChoice(new MachinePart(0).getItem()));

            plugin.getServer().addRecipe(recipe);
        }
    }

    // Override Methods

    protected String getDefaultName() {
        return "Machine Part";
    }

    protected void setTexture() {
        Util.modifySkullSkin(item, "6131a36e70ffaa7ca7e672ae6ac20b7fc1e457c43a8e1069e7b14ecdb8576", SKULL_UUID);
    }
}