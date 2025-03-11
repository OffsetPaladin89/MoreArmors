package me.offsetpaladin89.MoreArmors.handlers;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.items.armors.*;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.items.materials.*;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import static me.offsetpaladin89.MoreArmors.enums.MaterialType.INVALID;

public class RecipeHandler {

    private final MoreArmorsMain plugin;

    public RecipeHandler(MoreArmorsMain plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.configHandler.getConfig("config");

        registerArmorRecipes(config);
        registerMaterialRecipes(config);
    }

    private void registerArmorRecipes(FileConfiguration config) {
        registerDestroyerArmor(config.getBoolean("destroyer_armor.craftable"));
        registerEmeraldArmor(config.getBoolean("emerald_armor.craftable"));
        registerEndArmor(config.getBoolean("end_armor.craftable"));
        registerExperienceArmor(config.getBoolean("experience_armor.craftable"));
        registerMinerArmor(config.getBoolean("miner_armor.craftable"));
        registerNetherArmor(config.getBoolean("nether_armor.craftable"));
        registerSeaGreedArmor(config.getBoolean("sea_greed_armor.craftable"));
        registerSpeedsterArmor(config.getBoolean("speedster_armor.craftable"));
        registerTitanArmor(config.getBoolean("titan_armor.craftable"));
    }

    private void registerMaterialRecipes(FileConfiguration config) {
        if(!config.getBoolean("materials.craftable")) return;

        materialRecipe(MaterialType.values());

        energyCell();
        machineCore();
        machinePart0();
        machinePart1();
        netherCrown();
        starDust();
    }

    public void materialRecipe(MaterialType[] types) {
        for(MaterialType type : types) materialRecipe(type);
    }

    public void materialRecipe(MaterialType type) {
        if(type == INVALID) return;

        CustomMaterial material = MaterialType.materialFromType(type);
        if(material.getPrev() == null) return;
        NamespacedKey key = new NamespacedKey(plugin, material.getID());

        ShapedRecipe recipe = new ShapedRecipe(key, material.getItem());
        recipe.shape("AAA", "AAA", "AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(material.getPrev()));

        plugin.getServer().addRecipe(recipe);
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

    private void machineCore() {
        CustomMaterial item = new MachineCore();

        NamespacedKey key = new NamespacedKey(plugin, item.getType().toString().toLowerCase());

        ShapedRecipe recipe = new ShapedRecipe(key, item.getItem());
        recipe.shape(" B ", " C ", "AAA");
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(new IronBlock.IronBlock2().getItem()));
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

    private void registerDestroyerArmor(boolean register) {
        if(!register) return;

        CustomArmor helmetItem = new DestroyerArmor(SlotType.HELMET);
        CustomArmor chestplateItem = new DestroyerArmor(SlotType.CHESTPLATE);
        CustomArmor leggingsItem = new DestroyerArmor(SlotType.LEGGINGS);
        CustomArmor bootsItem = new DestroyerArmor(SlotType.BOOTS);

        NamespacedKey helmetKey = new NamespacedKey(plugin, "destroyer_helmet");
        NamespacedKey chestplateKey = new NamespacedKey(plugin, "destroyer_chestplate");
        NamespacedKey leggingsKey = new NamespacedKey(plugin, "destroyer_leggings");
        NamespacedKey bootsKey = new NamespacedKey(plugin, "destroyer_boots");

        ShapedRecipe helmetRecipe = new ShapedRecipe(helmetKey, helmetItem.getItem());
        ShapedRecipe chestplateRecipe = new ShapedRecipe(chestplateKey, chestplateItem.getItem());
        ShapedRecipe leggingsRecipe = new ShapedRecipe(leggingsKey, leggingsItem.getItem());
        ShapedRecipe bootsRecipe = new ShapedRecipe(bootsKey, bootsItem.getItem());

        helmetRecipe.shape("AAA", "BCB");
        chestplateRecipe.shape("BCB", "BAB", "AAA");
        leggingsRecipe.shape("AAA", "BCB", "B B");
        bootsRecipe.shape("BCB", "A A");

        RecipeChoice ironBlock = new RecipeChoice.ExactChoice(new IronBlock.IronBlock1().getItem());
        RecipeChoice machinePart = new RecipeChoice.ExactChoice(new MachinePart.MachinePart1().getItem());
        RecipeChoice machineCore = new RecipeChoice.ExactChoice(new MachineCore().getItem());

        helmetRecipe.setIngredient('A', ironBlock);
        helmetRecipe.setIngredient('B', machinePart);
        helmetRecipe.setIngredient('C', machineCore);

        chestplateRecipe.setIngredient('A', ironBlock);
        chestplateRecipe.setIngredient('B', machinePart);
        chestplateRecipe.setIngredient('C', machineCore);

        leggingsRecipe.setIngredient('A', ironBlock);
        leggingsRecipe.setIngredient('B', machinePart);
        leggingsRecipe.setIngredient('C', machineCore);

        bootsRecipe.setIngredient('A', ironBlock);
        bootsRecipe.setIngredient('B', machinePart);
        bootsRecipe.setIngredient('C', machineCore);

        plugin.getServer().addRecipe(helmetRecipe);
        plugin.getServer().addRecipe(chestplateRecipe);
        plugin.getServer().addRecipe(leggingsRecipe);
        plugin.getServer().addRecipe(bootsRecipe);
    }

    private void registerEmeraldArmor(boolean register) {
        if(!register) return;

        CustomArmor helmetItem = new EmeraldArmor(SlotType.HELMET);
        CustomArmor chestplateItem = new EmeraldArmor(SlotType.CHESTPLATE);
        CustomArmor leggingsItem = new EmeraldArmor(SlotType.LEGGINGS);
        CustomArmor bootsItem = new EmeraldArmor(SlotType.BOOTS);

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

    private void registerEndArmor(boolean register) {
        if(!register) return;

        CustomArmor helmetItem = new EndArmor(SlotType.HELMET);
        CustomArmor chestplateItem = new EndArmor(SlotType.CHESTPLATE);
        CustomArmor leggingsItem = new EndArmor(SlotType.LEGGINGS);
        CustomArmor bootsItem = new EndArmor(SlotType.BOOTS);

        NamespacedKey helmetKey = new NamespacedKey(plugin, "end_helmet");
        NamespacedKey chestplateKey = new NamespacedKey(plugin, "end_chestplate");
        NamespacedKey leggingsKey = new NamespacedKey(plugin, "end_leggings");
        NamespacedKey bootsKey = new NamespacedKey(plugin, "end_boots");

        ShapedRecipe helmetRecipe = new ShapedRecipe(helmetKey, helmetItem.getItem());
        ShapedRecipe chestplateRecipe = new ShapedRecipe(chestplateKey, chestplateItem.getItem());
        ShapedRecipe leggingsRecipe = new ShapedRecipe(leggingsKey, leggingsItem.getItem());
        ShapedRecipe bootsRecipe = new ShapedRecipe(bootsKey, bootsItem.getItem());

        helmetRecipe.shape("AAA", "ACA");
        chestplateRecipe.shape("ABA", "AAA", "AAA");
        leggingsRecipe.shape("AAA", "ABA", "A A");
        bootsRecipe.shape("ABA", "A A");

        RecipeChoice endStone = new RecipeChoice.ExactChoice(new Endstone.Endstone2().getItem());
        RecipeChoice eyeOfEnder = new RecipeChoice.ExactChoice(new EyeOfEnder.EyeOfEnder1().getItem());

        helmetRecipe.setIngredient('A', endStone);
        helmetRecipe.setIngredient('C', Material.DRAGON_HEAD);

        chestplateRecipe.setIngredient('A', endStone);
        chestplateRecipe.setIngredient('B', eyeOfEnder);

        leggingsRecipe.setIngredient('A', endStone);
        leggingsRecipe.setIngredient('B', eyeOfEnder);

        bootsRecipe.setIngredient('A', endStone);
        bootsRecipe.setIngredient('B', eyeOfEnder);

        plugin.getServer().addRecipe(helmetRecipe);
        plugin.getServer().addRecipe(chestplateRecipe);
        plugin.getServer().addRecipe(leggingsRecipe);
        plugin.getServer().addRecipe(bootsRecipe);
    }

    private void registerExperienceArmor(boolean register) {
        if(!register) return;

        CustomArmor helmetItem = new ExperienceArmor(SlotType.HELMET);
        CustomArmor chestplateItem = new ExperienceArmor(SlotType.CHESTPLATE);
        CustomArmor leggingsItem = new ExperienceArmor(SlotType.LEGGINGS);
        CustomArmor bootsItem = new ExperienceArmor(SlotType.BOOTS);

        NamespacedKey helmetKey = new NamespacedKey(plugin, "experience_helmet");
        NamespacedKey chestplateKey = new NamespacedKey(plugin, "experience_chestplate");
        NamespacedKey leggingsKey = new NamespacedKey(plugin, "experience_leggings");
        NamespacedKey bootsKey = new NamespacedKey(plugin, "experience_boots");

        ShapedRecipe helmetRecipe = new ShapedRecipe(helmetKey, helmetItem.getItem());
        ShapedRecipe chestplateRecipe = new ShapedRecipe(chestplateKey, chestplateItem.getItem());
        ShapedRecipe leggingsRecipe = new ShapedRecipe(leggingsKey, leggingsItem.getItem());
        ShapedRecipe bootsRecipe = new ShapedRecipe(bootsKey, bootsItem.getItem());

        helmetRecipe.shape("AAA", "A A");
        chestplateRecipe.shape("A A", "AAA", "AAA");
        leggingsRecipe.shape("AAA", "A A", "A A");
        bootsRecipe.shape("A A", "A A");

        helmetRecipe.setIngredient('A', Material.LAPIS_BLOCK);
        chestplateRecipe.setIngredient('A', Material.LAPIS_BLOCK);
        leggingsRecipe.setIngredient('A', Material.LAPIS_BLOCK);
        bootsRecipe.setIngredient('A', Material.LAPIS_BLOCK);

        plugin.getServer().addRecipe(helmetRecipe);
        plugin.getServer().addRecipe(chestplateRecipe);
        plugin.getServer().addRecipe(leggingsRecipe);
        plugin.getServer().addRecipe(bootsRecipe);
    }

    private void registerMinerArmor(boolean register) {
        if(!register) return;

        CustomArmor helmetItem = new MinerArmor(SlotType.HELMET);
        CustomArmor chestplateItem = new MinerArmor(SlotType.CHESTPLATE);
        CustomArmor leggingsItem = new MinerArmor(SlotType.LEGGINGS);
        CustomArmor bootsItem = new MinerArmor(SlotType.BOOTS);

        NamespacedKey helmetKey = new NamespacedKey(plugin, "miner_helmet");
        NamespacedKey chestplateKey = new NamespacedKey(plugin, "miner_chestplate");
        NamespacedKey leggingsKey = new NamespacedKey(plugin, "miner_leggings");
        NamespacedKey bootsKey = new NamespacedKey(plugin, "miner_boots");

        ShapedRecipe helmetRecipe = new ShapedRecipe(helmetKey, helmetItem.getItem());
        ShapedRecipe chestplateRecipe = new ShapedRecipe(chestplateKey, chestplateItem.getItem());
        ShapedRecipe leggingsRecipe = new ShapedRecipe(leggingsKey, leggingsItem.getItem());
        ShapedRecipe bootsRecipe = new ShapedRecipe(bootsKey, bootsItem.getItem());

        helmetRecipe.shape("AAA", "A A");
        chestplateRecipe.shape("A A", "AAA", "AAA");
        leggingsRecipe.shape("AAA", "A A", "A A");
        bootsRecipe.shape("A A", "A A");

        RecipeChoice cobblestone = new RecipeChoice.ExactChoice(new Cobblestone.Cobblestone2().getItem());

        helmetRecipe.setIngredient('A', cobblestone);
        chestplateRecipe.setIngredient('A', cobblestone);
        leggingsRecipe.setIngredient('A', cobblestone);
        bootsRecipe.setIngredient('A', cobblestone);

        plugin.getServer().addRecipe(helmetRecipe);
        plugin.getServer().addRecipe(chestplateRecipe);
        plugin.getServer().addRecipe(leggingsRecipe);
        plugin.getServer().addRecipe(bootsRecipe);
    }

    private void registerNetherArmor(boolean register) {
        if(!register) return;

        CustomArmor helmetItem = new NetherArmor(SlotType.HELMET);
        CustomArmor chestplateItem = new NetherArmor(SlotType.CHESTPLATE);
        CustomArmor leggingsItem = new NetherArmor(SlotType.LEGGINGS);
        CustomArmor bootsItem = new NetherArmor(SlotType.BOOTS);

        NamespacedKey helmetKey = new NamespacedKey(plugin, "nether_helmet");
        NamespacedKey chestplateKey = new NamespacedKey(plugin, "nether_chestplate");
        NamespacedKey leggingsKey = new NamespacedKey(plugin, "nether_leggings");
        NamespacedKey bootsKey = new NamespacedKey(plugin, "nether_boots");

        ShapedRecipe helmetRecipe = new ShapedRecipe(helmetKey, helmetItem.getItem());
        ShapedRecipe chestplateRecipe = new ShapedRecipe(chestplateKey, chestplateItem.getItem());
        ShapedRecipe leggingsRecipe = new ShapedRecipe(leggingsKey, leggingsItem.getItem());
        ShapedRecipe bootsRecipe = new ShapedRecipe(bootsKey, bootsItem.getItem());

        helmetRecipe.shape("AAA", "ACA");
        chestplateRecipe.shape("ABA", "AAA", "AAA");
        leggingsRecipe.shape("AAA", "ABA", "A A");
        bootsRecipe.shape("ABA", "A A");

        RecipeChoice soulSand = new RecipeChoice.ExactChoice(new SoulSand.SoulSand1().getItem());
        RecipeChoice netherCrown = new RecipeChoice.ExactChoice(new NetherCrown().getItem());

        helmetRecipe.setIngredient('A', soulSand);
        helmetRecipe.setIngredient('C', netherCrown);

        chestplateRecipe.setIngredient('A', soulSand);
        chestplateRecipe.setIngredient('B', Material.NETHER_STAR);

        leggingsRecipe.setIngredient('A', soulSand);
        leggingsRecipe.setIngredient('B', Material.NETHER_STAR);

        bootsRecipe.setIngredient('A', soulSand);
        bootsRecipe.setIngredient('B', Material.NETHER_STAR);

        plugin.getServer().addRecipe(helmetRecipe);
        plugin.getServer().addRecipe(chestplateRecipe);
        plugin.getServer().addRecipe(leggingsRecipe);
        plugin.getServer().addRecipe(bootsRecipe);
    }

    private void registerSeaGreedArmor(boolean register) {
        if(!register) return;

        CustomArmor helmetItem = new SeaGreedArmor(SlotType.HELMET);
        CustomArmor chestplateItem = new SeaGreedArmor(SlotType.CHESTPLATE);
        CustomArmor leggingsItem = new SeaGreedArmor(SlotType.LEGGINGS);
        CustomArmor bootsItem = new SeaGreedArmor(SlotType.BOOTS);

        NamespacedKey helmetKey = new NamespacedKey(plugin, "sea_greed_helmet");
        NamespacedKey chestplateKey = new NamespacedKey(plugin, "sea_greed_chestplate");
        NamespacedKey leggingsKey = new NamespacedKey(plugin, "sea_greed_leggings");
        NamespacedKey bootsKey = new NamespacedKey(plugin, "sea_greed_boots");

        ShapedRecipe helmetRecipe = new ShapedRecipe(helmetKey, helmetItem.getItem());
        ShapedRecipe chestplateRecipe = new ShapedRecipe(chestplateKey, chestplateItem.getItem());
        ShapedRecipe leggingsRecipe = new ShapedRecipe(leggingsKey, leggingsItem.getItem());
        ShapedRecipe bootsRecipe = new ShapedRecipe(bootsKey, bootsItem.getItem());

        helmetRecipe.shape("AAA", "BDB");
        chestplateRecipe.shape("CDC", "AAA", "BBB");
        leggingsRecipe.shape("AAA", "BDB", "C C");
        bootsRecipe.shape("ADA", "B B");

        RecipeChoice prismarine = new RecipeChoice.ExactChoice(new Prismarine.Prismarine1().getItem());
        RecipeChoice goldBlock = new RecipeChoice.ExactChoice(new GoldBlock.GoldBlock1().getItem());
        RecipeChoice diamondBlock = new RecipeChoice.ExactChoice(new DiamondBlock.DiamondBlock1().getItem());

        helmetRecipe.setIngredient('A', prismarine);
        helmetRecipe.setIngredient('B', goldBlock);
        helmetRecipe.setIngredient('D', Material.HEART_OF_THE_SEA);

        chestplateRecipe.setIngredient('A', prismarine);
        chestplateRecipe.setIngredient('B', goldBlock);
        chestplateRecipe.setIngredient('C', diamondBlock);
        chestplateRecipe.setIngredient('D', Material.HEART_OF_THE_SEA);

        leggingsRecipe.setIngredient('A', prismarine);
        leggingsRecipe.setIngredient('B', goldBlock);
        leggingsRecipe.setIngredient('C', diamondBlock);
        leggingsRecipe.setIngredient('D', Material.HEART_OF_THE_SEA);

        bootsRecipe.setIngredient('A', prismarine);
        bootsRecipe.setIngredient('B', goldBlock);
        bootsRecipe.setIngredient('D', Material.HEART_OF_THE_SEA);

        plugin.getServer().addRecipe(helmetRecipe);
        plugin.getServer().addRecipe(chestplateRecipe);
        plugin.getServer().addRecipe(leggingsRecipe);
        plugin.getServer().addRecipe(bootsRecipe);
    }

    private void registerSpeedsterArmor(boolean register) {
        if(!register) return;

        CustomArmor helmetItem = new SpeedsterArmor(SlotType.HELMET);
        CustomArmor chestplateItem = new SpeedsterArmor(SlotType.CHESTPLATE);
        CustomArmor leggingsItem = new SpeedsterArmor(SlotType.LEGGINGS);
        CustomArmor bootsItem = new SpeedsterArmor(SlotType.BOOTS);

        NamespacedKey helmetKey = new NamespacedKey(plugin, "speedster_helmet");
        NamespacedKey chestplateKey = new NamespacedKey(plugin, "speedster_chestplate");
        NamespacedKey leggingsKey = new NamespacedKey(plugin, "speedster_leggings");
        NamespacedKey bootsKey = new NamespacedKey(plugin, "speedster_boots");

        ShapedRecipe helmetRecipe = new ShapedRecipe(helmetKey, helmetItem.getItem());
        ShapedRecipe chestplateRecipe = new ShapedRecipe(chestplateKey, chestplateItem.getItem());
        ShapedRecipe leggingsRecipe = new ShapedRecipe(leggingsKey, leggingsItem.getItem());
        ShapedRecipe bootsRecipe = new ShapedRecipe(bootsKey, bootsItem.getItem());

        helmetRecipe.shape("AAA", "A A");
        chestplateRecipe.shape("A A", "AAA", "AAA");
        leggingsRecipe.shape("AAA", "A A", "A A");
        bootsRecipe.shape("A A", "A A");

        RecipeChoice sugarCane = new RecipeChoice.ExactChoice(new SugarCane.SugarCane0().getItem());

        helmetRecipe.setIngredient('A', sugarCane);
        chestplateRecipe.setIngredient('A', sugarCane);
        leggingsRecipe.setIngredient('A', sugarCane);
        bootsRecipe.setIngredient('A', sugarCane);

        plugin.getServer().addRecipe(helmetRecipe);
        plugin.getServer().addRecipe(chestplateRecipe);
        plugin.getServer().addRecipe(leggingsRecipe);
        plugin.getServer().addRecipe(bootsRecipe);
    }

    private void registerTitanArmor(boolean register) {
        if(!register) return;

        CustomArmor helmetItem = new TitanArmor(SlotType.HELMET);
        CustomArmor chestplateItem = new TitanArmor(SlotType.CHESTPLATE);
        CustomArmor leggingsItem = new TitanArmor(SlotType.LEGGINGS);
        CustomArmor bootsItem = new TitanArmor(SlotType.BOOTS);

        NamespacedKey helmetKey = new NamespacedKey(plugin, "titan_helmet");
        NamespacedKey chestplateKey = new NamespacedKey(plugin, "titan_chestplate");
        NamespacedKey leggingsKey = new NamespacedKey(plugin, "titan_leggings");
        NamespacedKey bootsKey = new NamespacedKey(plugin, "titan_boots");

        ShapedRecipe helmetRecipe = new ShapedRecipe(helmetKey, helmetItem.getItem());
        ShapedRecipe chestplateRecipe = new ShapedRecipe(chestplateKey, chestplateItem.getItem());
        ShapedRecipe leggingsRecipe = new ShapedRecipe(leggingsKey, leggingsItem.getItem());
        ShapedRecipe bootsRecipe = new ShapedRecipe(bootsKey, bootsItem.getItem());

        helmetRecipe.shape("AAA", "A A");
        chestplateRecipe.shape("A A", "AAA", "AAA");
        leggingsRecipe.shape("AAA", "A A", "A A");
        bootsRecipe.shape("A A", "A A");

        helmetRecipe.setIngredient('A', Material.IRON_BLOCK);
        chestplateRecipe.setIngredient('A', Material.IRON_BLOCK);
        leggingsRecipe.setIngredient('A', Material.IRON_BLOCK);
        bootsRecipe.setIngredient('A', Material.IRON_BLOCK);

        plugin.getServer().addRecipe(helmetRecipe);
        plugin.getServer().addRecipe(chestplateRecipe);
        plugin.getServer().addRecipe(leggingsRecipe);
        plugin.getServer().addRecipe(bootsRecipe);
    }
}
