package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.UUID;

public class MachinePart extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.EPIC;
    private static final String DEFAULT_NAME = "Machine Part";
    private static final MaterialType MATERIAL_TYPE = MaterialType.MACHINE_PART;
    private static final UUID SKULL_UUID = UUID.nameUUIDFromBytes("MACHINE_PART".getBytes());

    public MachinePart(int tier) {
        super(BASE_RARITY, tier, DEFAULT_NAME, MATERIAL_TYPE);
        createItem(getBase());
        assignSkull();
    }

    private void assignSkull() {
        Util.modifySkullSkin(item, "6131a36e70ffaa7ca7e672ae6ac20b7fc1e457c43a8e1069e7b14ecdb8576", SKULL_UUID);
    }

    private static ItemStack getBase() {
        return new ItemStack(Material.PLAYER_HEAD);
    }

    public static void getRecipe(MoreArmorsMain plugin, int tier) {
        if(tier == 0) {
            CustomMaterial result = new MachinePart(0);

            NamespacedKey key = new NamespacedKey(plugin, String.format("%s_%d", result.getID(), tier));

            ShapedRecipe recipe = new ShapedRecipe(key, result.getItem());
            recipe.shape("AAA", "ABA", "AAA");
            recipe.setIngredient('A', new RecipeChoice.ExactChoice(new IronBlock(0).getItem()));
            recipe.setIngredient('B', new RecipeChoice.ExactChoice(new RedstoneBlock(0).getItem()));

            plugin.getServer().addRecipe(recipe);
        }
        else if(tier == 1) {
            CustomMaterial result = new MachinePart(1);

            NamespacedKey key = new NamespacedKey(plugin, String.format("%s_%d", result.getID(), tier));

            ShapedRecipe recipe = new ShapedRecipe(key, result.getItem());
            recipe.shape("AAA", "ABA", "AAA");
            recipe.setIngredient('A', new RecipeChoice.ExactChoice(new IronBlock(1).getItem()));
            recipe.setIngredient('B', new RecipeChoice.ExactChoice(new MachinePart(0).getItem()));
            plugin.getServer().addRecipe(recipe);
        }
    }
}