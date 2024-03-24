package me.offsetpaladin89.MoreArmors.armors;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MorePluginsCore.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public record ArmorSets(MoreArmorsMain plugin) {

    public ItemStack EmeraldHelmet(Integer emeraldAmount) { return plugin.armorConstructor.createEmeraldArmor(new ItemStack(Material.LEATHER_HELMET, 1), "Emerald Helmet", Rarity.EPIC, 3, 2, emeraldAmount, EquipmentSlot.HEAD); }
    public ItemStack EmeraldChestplate(Integer emeraldAmount) { return plugin.armorConstructor.createEmeraldArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "Emerald Chestplate", Rarity.EPIC, 8, 2, emeraldAmount, EquipmentSlot.CHEST); }
    public ItemStack EmeraldLeggings(Integer emeraldAmount) { return plugin.armorConstructor.createEmeraldArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "Emerald Leggings", Rarity.EPIC, 6, 2, emeraldAmount, EquipmentSlot.LEGS); }
    public ItemStack EmeraldBoots(Integer emeraldAmount) { return plugin.armorConstructor.createEmeraldArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "Emerald Boots", Rarity.EPIC, 3, 2, emeraldAmount, EquipmentSlot.FEET); }

    public void EmeraldArmorRecipes() {
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "emerald_helmet"), EmeraldHelmet(0)).shape("XXX", "X X").setIngredient('X', Material.EMERALD_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "emerald_chestplate"), EmeraldChestplate(0)).shape("X X", "XXX", "XXX").setIngredient('X', Material.EMERALD_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "emerald_leggings"), EmeraldLeggings(0)).shape("XXX", "X X", "X X").setIngredient('X', Material.EMERALD_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "emerald_boots"), EmeraldBoots(0)).shape("X X", "X X").setIngredient('X', Material.EMERALD_BLOCK));
    }

    public ItemStack EndHelmet() { return plugin.armorConstructor.createEndArmor(new ItemStack(Material.PLAYER_HEAD, 1), "End Helmet", Rarity.LEGENDARY, 3, 2, EquipmentSlot.HEAD); }
    public ItemStack EndChestplate() { return plugin.armorConstructor.createEndArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "End Chestplate", Rarity.LEGENDARY, 8, 2, EquipmentSlot.CHEST); }
    public ItemStack EndLeggings() { return plugin.armorConstructor.createEndArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "End Leggings", Rarity.LEGENDARY, 6, 2, EquipmentSlot.LEGS); }
    public ItemStack EndBoots() { return plugin.armorConstructor.createEndArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "End Boots", Rarity.LEGENDARY, 3, 2, EquipmentSlot.FEET); }

    public void EndArmorRecipes() {
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "end_helmet"), EndHelmet()).shape("XXX", "XZX").setIngredient('X', Material.END_STONE).setIngredient('Z', Material.DRAGON_HEAD));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "end_chestplate"), EndChestplate()).shape("XZX", "XXX", "XXX").setIngredient('X', Material.END_STONE).setIngredient('Z', Material.ENDER_EYE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "end_leggings"), EndLeggings()).shape("XXX", "XZX", "X X").setIngredient('X', Material.END_STONE).setIngredient('Z', Material.ENDER_EYE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "end_boots"), EndBoots()).shape("XZX", "X X").setIngredient('X', Material.END_STONE).setIngredient('Z', Material.ENDER_EYE));
    }

    public ItemStack ExperienceHelmet() { return plugin.armorConstructor.createExperienceArmor(new ItemStack(Material.LEATHER_HELMET, 1), "Experience Helmet", Rarity.RARE, 1, EquipmentSlot.HEAD); }
    public ItemStack ExperienceChestplate() { return plugin.armorConstructor.createExperienceArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "Experience Chestplate", Rarity.RARE, 5, EquipmentSlot.CHEST); }
    public ItemStack ExperienceLeggings() { return plugin.armorConstructor.createExperienceArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "Experience Leggings", Rarity.RARE, 4, EquipmentSlot.LEGS); }
    public ItemStack ExperienceBoots() { return plugin.armorConstructor.createExperienceArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "Experience Boots", Rarity.RARE, 1, EquipmentSlot.FEET); }

    public void ExperienceArmorRecipes() {
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "experience_helmet"), ExperienceHelmet()).shape("XXX", "X X").setIngredient('X', Material.LAPIS_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "experience_chestplate"), ExperienceChestplate()).shape("X X", "XXX", "XXX").setIngredient('X', Material.LAPIS_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "experience_leggings"), ExperienceLeggings()).shape("XXX", "X X", "X X").setIngredient('X', Material.LAPIS_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "experience_boots"), ExperienceBoots()).shape("X X", "X X").setIngredient('X', Material.LAPIS_BLOCK));
    }

    public ItemStack MinerHelmet() { return plugin.armorConstructor.createMinerArmor(new ItemStack(Material.LEATHER_HELMET, 1), "Miner Helmet", Rarity.UNCOMMON, 1, EquipmentSlot.HEAD); }
    public ItemStack MinerChestplate() { return plugin.armorConstructor.createMinerArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "Miner Helmet", Rarity.UNCOMMON, 5, EquipmentSlot.CHEST); }
    public ItemStack MinerLeggings() { return plugin.armorConstructor.createMinerArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "Miner Helmet", Rarity.UNCOMMON, 4, EquipmentSlot.LEGS); }
    public ItemStack MinerBoots() { return plugin.armorConstructor.createMinerArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "Miner Helmet", Rarity.UNCOMMON, 1, EquipmentSlot.FEET); }

    public void MinerArmorRecipes() {
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "miner_helmet"), MinerHelmet()).shape("XXX", "X X").setIngredient('X', Material.COBBLESTONE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "miner_chestplate"), MinerChestplate()).shape("X X", "XXX", "XXX").setIngredient('X', Material.COBBLESTONE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "miner_leggings"), MinerLeggings()).shape("XXX", "X X", "X X").setIngredient('X', Material.COBBLESTONE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "miner_boots"), MinerBoots()).shape("X X", "X X").setIngredient('X', Material.COBBLESTONE));
    }

    public ItemStack NetherHelmet() { return plugin.armorConstructor.createNetherArmor(new ItemStack(Material.PLAYER_HEAD, 1), "Nether Helmet", Rarity.LEGENDARY, 3, 2, EquipmentSlot.HEAD); }
    public ItemStack NetherChestplate() { return plugin.armorConstructor.createNetherArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "Nether Chestplate", Rarity.LEGENDARY, 8, 2, EquipmentSlot.CHEST); }
    public ItemStack NetherLeggings() { return plugin.armorConstructor.createNetherArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "Nether Leggings", Rarity.LEGENDARY, 6, 2, EquipmentSlot.LEGS); }
    public ItemStack NetherBoots() { return plugin.armorConstructor.createNetherArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "Nether Boots", Rarity.LEGENDARY, 3, 2, EquipmentSlot.FEET); }

    public void NetherArmorRecipes() {
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "nether_helmet"), NetherHelmet()).shape("XXX", "XZX").setIngredient('X', Material.SOUL_SAND).setIngredient('Z', Material.PLAYER_HEAD));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "nether_chestplate"), NetherChestplate()).shape("XZX", "XXX", "XXX").setIngredient('X', Material.SOUL_SAND).setIngredient('Z', Material.NETHER_STAR));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "nether_leggings"), NetherLeggings()).shape("XXX", "XZX", "X X").setIngredient('X', Material.SOUL_SAND).setIngredient('Z', Material.NETHER_STAR));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "nether_boots"), NetherBoots()).shape("XZX", "XXX").setIngredient('X', Material.SOUL_SAND).setIngredient('Z', Material.NETHER_STAR));
    }

    public ItemStack SeaGreedHelmet() { return plugin.armorConstructor.createSeaGreedArmor(new ItemStack(Material.PLAYER_HEAD, 1), "Sea Greed Helmet", Rarity.MYTHIC, 3, 2, EquipmentSlot.HEAD); }
    public ItemStack SeaGreedChestplate() { return plugin.armorConstructor.createSeaGreedArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "Sea Greed Chestplate", Rarity.MYTHIC, 8, 2, EquipmentSlot.CHEST); }
    public ItemStack SeaGreedLeggings() { return plugin.armorConstructor.createSeaGreedArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "Sea Greed Leggings", Rarity.MYTHIC, 6, 2, EquipmentSlot.LEGS); }
    public ItemStack SeaGreedBoots() { return plugin.armorConstructor.createSeaGreedArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "Sea Greed Boots", Rarity.MYTHIC, 3, 2, EquipmentSlot.FEET); }

    public void SeaGreedArmorRecipes() {
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "seagreed_helmet"), SeaGreedHelmet()).shape("XZX", "C C").setIngredient('X', Material.PRISMARINE).setIngredient('C', Material.GOLD_BLOCK).setIngredient('Z', Material.HEART_OF_THE_SEA));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "seagreed_chestplate"), SeaGreedChestplate()).shape("V V", "XZX", "CCC").setIngredient('X', Material.PRISMARINE).setIngredient('C', Material.GOLD_BLOCK).setIngredient('Z', Material.HEART_OF_THE_SEA).setIngredient('V', Material.DIAMOND_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "seagreed_leggings"), SeaGreedLeggings()).shape("XZX", "C C", "V V").setIngredient('X', Material.PRISMARINE).setIngredient('C', Material.GOLD_BLOCK).setIngredient('Z', Material.HEART_OF_THE_SEA).setIngredient('V', Material.DIAMOND_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "seagreed_boots"), SeaGreedBoots()).shape("X X", "C C").setIngredient('X', Material.PRISMARINE).setIngredient('C', Material.GOLD_BLOCK));
    }

    public ItemStack SpeedsterHelmet() { return plugin.armorConstructor.createSpeedsterArmor(new ItemStack(Material.LEATHER_HELMET, 1), "Speedster Helmet", Rarity.RARE, 2, EquipmentSlot.HEAD); }
    public ItemStack SpeedsterChestplate() { return plugin.armorConstructor.createSpeedsterArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "Speedster Chestplate", Rarity.RARE, 6, EquipmentSlot.CHEST); }
    public ItemStack SpeedsterLeggings() { return plugin.armorConstructor.createSpeedsterArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "Speedster Leggings", Rarity.RARE, 5, EquipmentSlot.LEGS); }
    public ItemStack SpeedsterBoots() { return plugin.armorConstructor.createSpeedsterArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "Speedster Boots", Rarity.RARE, 2, EquipmentSlot.FEET); }

    public void SpeedsterArmorRecipes() {
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "speedster_helmet"), SpeedsterHelmet()).shape("XXX", "X X").setIngredient('X', Material.SUGAR_CANE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "speedster_chestplate"), SpeedsterChestplate()).shape("X X", "XXX", "XXX").setIngredient('X', Material.SUGAR_CANE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "speedster_leggings"), SpeedsterLeggings()).shape("XXX", "X X", "X X").setIngredient('X', Material.SUGAR_CANE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "speedster_boots"), SpeedsterBoots()).shape("X X", "X X").setIngredient('X', Material.SUGAR_CANE));
    }

    public ItemStack TitanHelmet() { return plugin.armorConstructor.createTitanArmor(new ItemStack(Material.IRON_HELMET, 1), "Titan Helmet", Rarity.UNCOMMON, 2, EquipmentSlot.HEAD); }
}
