package me.offsetpaladin89.MoreArmors;

import me.offsetpaladin89.MoreArmors.armors.EmeraldArmor;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.materials.*;
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

        blazeRod0();
        blazeRod1();
        cobblestone0();
        cobblestone1();
        cobblestone2();
        diamondBlock0();
        endstone0();
        endstone1();
        endstone2();
        energyCell();
        eyeOfEnder0();
        eyeOfEnder1();
        goldBlock0();
        ironBlock0();
        ironBlock1();
        machineCore();
        machinePart0();
        machinePart1();
        netherCrown();
        prismarine0();
        redstoneBlock0();
        soulSand0();
        soulSand1();
        starDust();
        sugarCane0();
    }

    public void defaultMaterialRecipe(ItemStack result, ItemStack material, MaterialType materialType) {
        NamespacedKey key = new NamespacedKey(plugin, materialType.toString().toLowerCase());

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("AAA", "AAA", "AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(material));

        plugin.getServer().addRecipe(recipe);
    }

    private void blazeRod0() {
        CustomMaterial item = new BlazeRod.BlazeRod0();
        ItemStack material = new ItemStack(Material.BLAZE_ROD);

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void blazeRod1() {
        CustomMaterial item = new BlazeRod.BlazeRod1();
        ItemStack material = new BlazeRod.BlazeRod0().getItem();

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void cobblestone0() {
        CustomMaterial item = new Cobblestone.Cobblestone0();
        ItemStack material = new ItemStack(Material.COBBLESTONE);

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void cobblestone1() {
        CustomMaterial item = new Cobblestone.Cobblestone1();
        ItemStack material = new Cobblestone.Cobblestone0().getItem();

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void cobblestone2() {
        CustomMaterial item = new Cobblestone.Cobblestone2();
        ItemStack material = new Cobblestone.Cobblestone1().getItem();

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void diamondBlock0() {
        CustomMaterial item = new DiamondBlock.DiamondBlock0();
        ItemStack material = new ItemStack(Material.DIAMOND_BLOCK);

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void endstone0() {
        CustomMaterial item = new Endstone.Endstone0();
        ItemStack material = new ItemStack(Material.END_STONE);

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void endstone1() {
        CustomMaterial item = new Endstone.Endstone1();
        ItemStack material = new Endstone.Endstone0().getItem();

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void endstone2() {
        CustomMaterial item = new Endstone.Endstone2();
        ItemStack material = new Endstone.Endstone1().getItem();

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void energyCell() {
        CustomMaterial item = new EnergyCell();

        NamespacedKey key = new NamespacedKey(plugin, item.getType().toString().toLowerCase());

        ShapedRecipe recipe = new ShapedRecipe(key, item.getItem());
        recipe.shape("AAA", "ABA", "AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(new IronBlock.IronBlock0().getItem()));
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new StarDust().getItem()));

        plugin.getServer().addRecipe(recipe);
    }

    private void eyeOfEnder0() {
        CustomMaterial item = new EyeOfEnder.EyeOfEnder0();
        ItemStack material = new ItemStack(Material.ENDER_EYE);

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void eyeOfEnder1() {
        CustomMaterial item = new EyeOfEnder.EyeOfEnder1();
        ItemStack material = new EyeOfEnder.EyeOfEnder0().getItem();

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void goldBlock0() {
        CustomMaterial item = new GoldBlock.GoldBlock0();
        ItemStack material = new ItemStack(Material.GOLD_BLOCK);

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void ironBlock0() {
        CustomMaterial item = new IronBlock.IronBlock0();
        ItemStack material = new ItemStack(Material.IRON_BLOCK);

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void ironBlock1() {
        CustomMaterial item = new IronBlock.IronBlock1();
        ItemStack material = new IronBlock.IronBlock0().getItem();

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void machineCore() {
        CustomMaterial item = new MachineCore();

        NamespacedKey key = new NamespacedKey(plugin, item.getType().toString().toLowerCase());

        ShapedRecipe recipe = new ShapedRecipe(key, item.getItem());
        recipe.shape("ABA", "ACA", " A ");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(new IronBlock.IronBlock1().getItem()));
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new MachinePart.MachinePart1().getItem()));
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(new EnergyCell().getItem()));

        plugin.getServer().addRecipe(recipe);
    }

    private void machinePart0() {
        CustomMaterial item = new MachinePart.MachinePart0();

        NamespacedKey key = new NamespacedKey(plugin, item.getType().toString().toLowerCase());

        ShapedRecipe recipe = new ShapedRecipe(key, item.getItem());
        recipe.shape("AAA", "ABA", "AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(new IronBlock.IronBlock0().getItem()));
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new RedstoneBlock.RedstoneBlock0().getItem()));

        plugin.getServer().addRecipe(recipe);
    }

    private void machinePart1() {
        CustomMaterial item = new MachinePart.MachinePart1();

        NamespacedKey key = new NamespacedKey(plugin, item.getType().toString().toLowerCase());

        ShapedRecipe recipe = new ShapedRecipe(key, item.getItem());
        recipe.shape("AAA", "ABA", "AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(new IronBlock.IronBlock1().getItem()));
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new MachinePart.MachinePart0().getItem()));

        plugin.getServer().addRecipe(recipe);
    }

    private void netherCrown() {
        CustomMaterial item = new NetherCrown();

        NamespacedKey key = new NamespacedKey(plugin, item.getType().toString().toLowerCase());

        ShapedRecipe recipe = new ShapedRecipe(key, item.getItem());
        recipe.shape("AAA", "ABA", "AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(new BlazeRod.BlazeRod1().getItem()));
        recipe.setIngredient('B', Material.NETHER_STAR);

        plugin.getServer().addRecipe(recipe);
    }

    private void prismarine0() {
        CustomMaterial item = new Prismarine.Prismarine0();
        ItemStack material = new ItemStack(Material.PRISMARINE);

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void redstoneBlock0() {
        CustomMaterial item = new RedstoneBlock.RedstoneBlock0();
        ItemStack material = new ItemStack(Material.REDSTONE_BLOCK);

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void soulSand0() {
        CustomMaterial item = new SoulSand.SoulSand0();
        ItemStack material = new ItemStack(Material.SOUL_SAND);

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void soulSand1() {
        CustomMaterial item = new SoulSand.SoulSand1();
        ItemStack material = new SoulSand.SoulSand0().getItem();

        defaultMaterialRecipe(item.getItem(), material, item.getType());
    }

    private void starDust() {
        CustomMaterial item = new StarDust();
        item.setAmount(8);

        NamespacedKey key = new NamespacedKey(plugin, item.getType().toString().toLowerCase());

        item.getItem().setAmount(8);

        ShapedRecipe recipe = new ShapedRecipe(key, item.getItem());
        recipe.shape("AAA", "ABA", "AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(new IronBlock.IronBlock0().getItem()));
        recipe.setIngredient('B', Material.NETHER_STAR);

        plugin.getServer().addRecipe(recipe);
    }

    private void sugarCane0() {
        CustomMaterial item = new SugarCane.SugarCane0();
        ItemStack material = new ItemStack(Material.SUGAR_CANE);

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
