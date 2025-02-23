package me.offsetpaladin89.MoreArmors;

import me.offsetpaladin89.MoreArmors.armors.EmeraldArmor;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.materials.Cobblestone;
import me.offsetpaladin89.MoreArmors.materials.SugarCane;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class RecipeHandler {

    private final MoreArmorsMain plugin;

    RecipeHandler(MoreArmorsMain plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.configHandler.getConfig("config");

        registerArmorRecipes(config);
        registerMaterialRecipes(config);
    }

    private void registerArmorRecipes(FileConfiguration config) {
        registerEmeraldArmor(config.getBoolean("emeraldarmor.crafting"));
    }

    private void registerMaterialRecipes(FileConfiguration config) {
        if(!config.getBoolean("materials.crafting")) return;

        sugarCane0Material();

        cobblestone0Material();
        cobblestone1Material();
        cobblestone2Material();

    }

    private void defaultMaterialRecipe(ItemStack result, ItemStack material, MaterialType materialType) {
        NamespacedKey key = new NamespacedKey(plugin, materialType.toString().toLowerCase());

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("AAA", "AAA", "AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(material));

        plugin.getServer().addRecipe(recipe);
    }

    private void sugarCane0Material() {
        SugarCane item = new SugarCane.SugarCane0(1);
        ItemStack material = new ItemStack(Material.SUGAR_CANE);

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void cobblestone0Material() {
        Cobblestone item = new Cobblestone.Cobblestone0(1);
        ItemStack material = new ItemStack(Material.COBBLESTONE);

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void cobblestone1Material() {
        Cobblestone item = new Cobblestone.Cobblestone1(1);
        ItemStack material = new Cobblestone.Cobblestone0(1).getItem();

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void cobblestone2Material() {
        Cobblestone item = new Cobblestone.Cobblestone2(1);
        ItemStack material = new Cobblestone.Cobblestone1(1).getItem();

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void registerEmeraldArmor(boolean register) {
        if(!register) return;

        EmeraldArmor helmetItem = new EmeraldArmor(SlotType.HELMET);
        EmeraldArmor chestplateItem = new EmeraldArmor(SlotType.CHESTPLATE);
        EmeraldArmor leggingsItem = new EmeraldArmor(SlotType.LEGGINGS);
        EmeraldArmor bootsItem = new EmeraldArmor(SlotType.BOOTS);

        NamespacedKey helmetKey = new NamespacedKey(plugin, "emerald_helmet");
        NamespacedKey chestplateKey = new NamespacedKey(plugin, "emerald_chestplate");
        NamespacedKey leggingsKey = new NamespacedKey(plugin, "emerald_leggings");
        NamespacedKey bootsKey = new NamespacedKey(plugin, "emerald_boots");

        ShapedRecipe helmetRecipe = new ShapedRecipe(helmetKey, helmetItem.getItem());
        ShapedRecipe chestplateRecipe = new ShapedRecipe(chestplateKey, chestplateItem.getItem());
        ShapedRecipe leggingsRecipe = new ShapedRecipe(leggingsKey, leggingsItem.getItem());
        ShapedRecipe bootsRecipe = new ShapedRecipe(bootsKey, bootsItem.getItem());

        helmetRecipe.shape("AAA", "A A");
        chestplateRecipe.shape("A A", "AAA", "AAA");
        leggingsRecipe.shape("AAA", "A A", "A A");
        bootsRecipe.shape("A A", "A A");

        helmetRecipe.setIngredient('A', Material.EMERALD_BLOCK);
        chestplateRecipe.setIngredient('A', Material.EMERALD_BLOCK);
        leggingsRecipe.setIngredient('A', Material.EMERALD_BLOCK);
        bootsRecipe.setIngredient('A', Material.EMERALD_BLOCK);

        plugin.getServer().addRecipe(helmetRecipe);
        plugin.getServer().addRecipe(chestplateRecipe);
        plugin.getServer().addRecipe(leggingsRecipe);
        plugin.getServer().addRecipe(bootsRecipe);
    }
}
