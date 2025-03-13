package me.offsetpaladin89.MoreArmors.items.materials;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.UUID;

public class MachineCore extends CustomMaterial {

    private static final Rarity DEFAULT_RARITY = Rarity.MYTHIC;
    private static final MaterialType MATERIAL_TYPE = MaterialType.MACHINE_CORE;
    private static final UUID SKULL_UUID = UUID.nameUUIDFromBytes("MACHINE_CORE".getBytes());

    public MachineCore(int tier) {
        super(DEFAULT_RARITY, tier, MATERIAL_TYPE);
    }

    public static void getRecipe(MoreArmorsMain plugin) {
        CustomMaterial result = new MachineCore(0);

        NamespacedKey key = new NamespacedKey(plugin, result.getID());

        ShapedRecipe recipe = new ShapedRecipe(key, result.getItem());
        recipe.shape(" B ", " C ", "AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(new IronBlock(2).getItem()));
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new MachinePart(1).getItem()));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new EnergyCell(0).getItem()));

        plugin.getServer().addRecipe(recipe);
    }

    // Override Methods

    protected String getDefaultName() {
        return "Machine Core";
    }

    protected void setTexture() {
        Util.modifySkullSkin(item, "76856a8f37b6c3146854f2caa7101b9dd592f4669a3c75f941e2859552bd1ae8", SKULL_UUID);
    }
}
