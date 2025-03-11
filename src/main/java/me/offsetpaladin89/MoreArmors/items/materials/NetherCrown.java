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

public class NetherCrown extends CustomMaterial {

    private static final Rarity BASE_RARITY = Rarity.EPIC;
    private static final String DEFAULT_NAME = "Nether Crown";
    private static final MaterialType MATERIAL_TYPE = MaterialType.NETHER_CROWN;
    private static final UUID SKULL_UUID = UUID.nameUUIDFromBytes("NETHER_CROWN".getBytes());

    public NetherCrown(int tier) {
        super(BASE_RARITY, tier, DEFAULT_NAME, MATERIAL_TYPE);
        createItem(getBase());
        assignSkull();
    }

    private void assignSkull() {
        Util.modifySkullSkin(item, "3e4f49535a276aacc4dc84133bfe81be5f2a4799a4c04d9a4ddb72d819ec2b2b", SKULL_UUID);
    }

    private static ItemStack getBase() {
        return new ItemStack(Material.PLAYER_HEAD);
    }

    public static void getRecipe(MoreArmorsMain plugin) {
        CustomMaterial result = new NetherCrown(0);

        NamespacedKey key = new NamespacedKey(plugin, result.getID());

        ShapedRecipe recipe = new ShapedRecipe(key, result.getItem());
        recipe.shape("AAA", "ABA", "AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(new BlazeRod(1).getItem()));
        recipe.setIngredient('B', Material.NETHER_STAR);

        plugin.getServer().addRecipe(recipe);
    }
}
