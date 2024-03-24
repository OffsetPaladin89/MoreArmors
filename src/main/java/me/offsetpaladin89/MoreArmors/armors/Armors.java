package me.offsetpaladin89.MoreArmors.armors;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public record Armors(MoreArmorsMain plugin) {
    public ItemStack EmeraldArmor(EquipmentSlot slot, Integer emeraldAmount) {
        switch (slot) {
            case HEAD -> { return plugin.armorConstructor.createEmeraldArmor(new ItemStack(Material.LEATHER_HELMET, 1), "Emerald Helmet", Rarity.EPIC, 3, 2, emeraldAmount, slot); }
            case CHEST -> { return plugin.armorConstructor.createEmeraldArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "Emerald Chestplate", Rarity.EPIC, 8, 2, emeraldAmount, slot); }
            case LEGS -> { return plugin.armorConstructor.createEmeraldArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "Emerald Leggings", Rarity.EPIC, 6, 2, emeraldAmount, slot); }
            case FEET -> { return plugin.armorConstructor.createEmeraldArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "Emerald Boots", Rarity.EPIC, 3, 2, emeraldAmount, slot); }
        }
        return null;
    }
    public ItemStack EndArmor(EquipmentSlot slot) {
        switch (slot) {
            case HEAD -> { return plugin.armorConstructor.createEndArmor(new ItemStack(Material.PLAYER_HEAD, 1), "End Helmet", Rarity.LEGENDARY, 3, 2, slot); }
            case CHEST -> { return plugin.armorConstructor.createEndArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "End Chestplate", Rarity.LEGENDARY, 8, 2, slot); }
            case LEGS -> { return plugin.armorConstructor.createEndArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "End Leggings", Rarity.LEGENDARY, 6, 2, slot); }
            case FEET -> { return plugin.armorConstructor.createEndArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "End Boots", Rarity.LEGENDARY, 3, 2, slot); }
        }
        return null;
    }
    public ItemStack ExperienceArmor(EquipmentSlot slot) {
        switch (slot) {
            case HEAD -> { return plugin.armorConstructor.createExperienceArmor(new ItemStack(Material.LEATHER_HELMET, 1), "Experience Helmet", Rarity.RARE, 1, slot); }
            case CHEST -> { return plugin.armorConstructor.createExperienceArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "Experience Chestplate", Rarity.RARE, 5, slot); }
            case LEGS -> { return plugin.armorConstructor.createExperienceArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "Experience Leggings", Rarity.RARE, 4, slot); }
            case FEET -> { return plugin.armorConstructor.createExperienceArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "Experience Boots", Rarity.RARE, 1, slot); }
        }
        return null;
    }
    public ItemStack MinerArmor(EquipmentSlot slot) {
        switch (slot) {
            case HEAD -> { return plugin.armorConstructor.createMinerArmor(new ItemStack(Material.LEATHER_HELMET, 1), "Miner Helmet", Rarity.UNCOMMON, 1, slot); }
            case CHEST -> { return plugin.armorConstructor.createMinerArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "Miner Chestplate", Rarity.UNCOMMON, 5, slot); }
            case LEGS -> { return plugin.armorConstructor.createMinerArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "Miner Leggings", Rarity.UNCOMMON, 4, slot); }
            case FEET -> { return plugin.armorConstructor.createMinerArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "Miner Boots", Rarity.UNCOMMON, 1, slot); }
        }
        return null;
    }
    public ItemStack NetherArmor(EquipmentSlot slot) {
        switch (slot) {
            case HEAD -> { return plugin.armorConstructor.createNetherArmor(new ItemStack(Material.PLAYER_HEAD, 1), "Nether Helmet", Rarity.LEGENDARY, 3, 2, slot); }
            case CHEST -> { return plugin.armorConstructor.createNetherArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "Nether Chestplate", Rarity.LEGENDARY, 8, 2, slot); }
            case LEGS -> { return plugin.armorConstructor.createNetherArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "Nether Leggings", Rarity.LEGENDARY, 6, 2, slot); }
            case FEET -> { return plugin.armorConstructor.createNetherArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "Nether Boots", Rarity.LEGENDARY, 3, 2, slot); }
        }
        return null;
    }
    public ItemStack SeaGreedArmor(EquipmentSlot slot) {
        switch (slot) {
            case HEAD -> { return plugin.armorConstructor.createSeaGreedArmor(new ItemStack(Material.PLAYER_HEAD, 1), "Sea Greed Helmet", Rarity.MYTHIC, 3, 2, slot); }
            case CHEST -> { return plugin.armorConstructor.createSeaGreedArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "Sea Greed Chestplate", Rarity.MYTHIC, 8, 2, slot); }
            case LEGS -> { return plugin.armorConstructor.createSeaGreedArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "Sea Greed Leggings", Rarity.MYTHIC, 6, 2, slot); }
            case FEET -> { return plugin.armorConstructor.createSeaGreedArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "Sea Greed Boots", Rarity.MYTHIC, 3, 2, slot); }
        }
        return null;
    }
    public ItemStack SpeedsterArmor(EquipmentSlot slot) {
        switch (slot) {
            case HEAD -> { return plugin.armorConstructor.createSpeedsterArmor(new ItemStack(Material.LEATHER_HELMET, 1), "Speedster Helmet", Rarity.RARE, 2, slot); }
            case CHEST -> { return plugin.armorConstructor.createSpeedsterArmor(new ItemStack(Material.LEATHER_CHESTPLATE, 1), "Speedster Chestplate", Rarity.RARE, 6, slot); }
            case LEGS -> { return plugin.armorConstructor.createSpeedsterArmor(new ItemStack(Material.LEATHER_LEGGINGS, 1), "Speedster Leggings", Rarity.RARE, 5, slot); }
            case FEET -> { return plugin.armorConstructor.createSpeedsterArmor(new ItemStack(Material.LEATHER_BOOTS, 1), "Speedster Boots", Rarity.RARE, 2, slot); }
        }
        return null;
    }
    public ItemStack TitanArmor(EquipmentSlot slot) {
        switch (slot) {
            case HEAD -> { return plugin.armorConstructor.createTitanArmor(new ItemStack(Material.IRON_HELMET, 1), "Titan Helmet", Rarity.UNCOMMON, 2, slot); }
            case CHEST -> { return plugin.armorConstructor.createTitanArmor(new ItemStack(Material.IRON_CHESTPLATE, 1), "Titan Chestplate", Rarity.UNCOMMON, 6, slot); }
            case LEGS -> { return plugin.armorConstructor.createTitanArmor(new ItemStack(Material.IRON_LEGGINGS, 1), "Titan Leggings", Rarity.UNCOMMON, 5, slot); }
            case FEET -> { return plugin.armorConstructor.createTitanArmor(new ItemStack(Material.IRON_BOOTS, 1), "Titan Boots", Rarity.UNCOMMON, 2, slot); }
        }
        return null;
    }
    public void RegisterArmorRecipes() {
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "emerald_helmet"), EmeraldArmor(EquipmentSlot.HEAD, 0)).shape("XXX", "X X").setIngredient('X', Material.EMERALD_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "emerald_chestplate"), EmeraldArmor(EquipmentSlot.CHEST, 0)).shape("X X", "XXX", "XXX").setIngredient('X', Material.EMERALD_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "emerald_leggings"), EmeraldArmor(EquipmentSlot.LEGS, 0)).shape("XXX", "X X", "X X").setIngredient('X', Material.EMERALD_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "emerald_boots"), EmeraldArmor(EquipmentSlot.FEET, 0)).shape("X X", "X X").setIngredient('X', Material.EMERALD_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "end_helmet"), EndArmor(EquipmentSlot.HEAD)).shape("XXX", "XZX").setIngredient('X', Material.END_STONE).setIngredient('Z', Material.DRAGON_HEAD));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "end_chestplate"), EndArmor(EquipmentSlot.CHEST)).shape("XZX", "XXX", "XXX").setIngredient('X', Material.END_STONE).setIngredient('Z', Material.ENDER_EYE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "end_leggings"), EndArmor(EquipmentSlot.LEGS)).shape("XXX", "XZX", "X X").setIngredient('X', Material.END_STONE).setIngredient('Z', Material.ENDER_EYE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "end_boots"), EndArmor(EquipmentSlot.FEET)).shape("XZX", "X X").setIngredient('X', Material.END_STONE).setIngredient('Z', Material.ENDER_EYE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "experience_helmet"), ExperienceArmor(EquipmentSlot.HEAD)).shape("XXX", "X X").setIngredient('X', Material.LAPIS_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "experience_chestplate"), ExperienceArmor(EquipmentSlot.CHEST)).shape("X X", "XXX", "XXX").setIngredient('X', Material.LAPIS_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "experience_leggings"), ExperienceArmor(EquipmentSlot.LEGS)).shape("XXX", "X X", "X X").setIngredient('X', Material.LAPIS_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "experience_boots"), ExperienceArmor(EquipmentSlot.FEET)).shape("X X", "X X").setIngredient('X', Material.LAPIS_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "miner_helmet"), MinerArmor(EquipmentSlot.HEAD)).shape("XXX", "X X").setIngredient('X', Material.COBBLESTONE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "miner_chestplate"), MinerArmor(EquipmentSlot.CHEST)).shape("X X", "XXX", "XXX").setIngredient('X', Material.COBBLESTONE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "miner_leggings"), MinerArmor(EquipmentSlot.LEGS)).shape("XXX", "X X", "X X").setIngredient('X', Material.COBBLESTONE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "miner_boots"), MinerArmor(EquipmentSlot.FEET)).shape("X X", "X X").setIngredient('X', Material.COBBLESTONE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "nether_helmet"), NetherArmor(EquipmentSlot.HEAD)).shape("XXX", "XZX").setIngredient('X', Material.SOUL_SAND).setIngredient('Z', Material.PLAYER_HEAD));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "nether_chestplate"), NetherArmor(EquipmentSlot.CHEST)).shape("XZX", "XXX", "XXX").setIngredient('X', Material.SOUL_SAND).setIngredient('Z', Material.NETHER_STAR));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "nether_leggings"), NetherArmor(EquipmentSlot.LEGS)).shape("XXX", "XZX", "X X").setIngredient('X', Material.SOUL_SAND).setIngredient('Z', Material.NETHER_STAR));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "nether_boots"), NetherArmor(EquipmentSlot.FEET)).shape("XZX", "X X").setIngredient('X', Material.SOUL_SAND).setIngredient('Z', Material.NETHER_STAR));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "seagreed_helmet"), SeaGreedArmor(EquipmentSlot.HEAD)).shape("XZX", "C C").setIngredient('X', Material.PRISMARINE).setIngredient('C', Material.GOLD_BLOCK).setIngredient('Z', Material.HEART_OF_THE_SEA));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "seagreed_chestplate"), SeaGreedArmor(EquipmentSlot.CHEST)).shape("V V", "XZX", "CCC").setIngredient('X', Material.PRISMARINE).setIngredient('C', Material.GOLD_BLOCK).setIngredient('Z', Material.HEART_OF_THE_SEA).setIngredient('V', Material.DIAMOND_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "seagreed_leggings"), SeaGreedArmor(EquipmentSlot.LEGS)).shape("XZX", "C C", "V V").setIngredient('X', Material.PRISMARINE).setIngredient('C', Material.GOLD_BLOCK).setIngredient('Z', Material.HEART_OF_THE_SEA).setIngredient('V', Material.DIAMOND_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "seagreed_boots"), SeaGreedArmor(EquipmentSlot.FEET)).shape("X X", "C C").setIngredient('X', Material.PRISMARINE).setIngredient('C', Material.GOLD_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "speedster_helmet"), SpeedsterArmor(EquipmentSlot.HEAD)).shape("XXX", "X X").setIngredient('X', Material.SUGAR_CANE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "speedster_chestplate"), SpeedsterArmor(EquipmentSlot.CHEST)).shape("X X", "XXX", "XXX").setIngredient('X', Material.SUGAR_CANE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "speedster_leggings"), SpeedsterArmor(EquipmentSlot.LEGS)).shape("XXX", "X X", "X X").setIngredient('X', Material.SUGAR_CANE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "speedster_boots"), SpeedsterArmor(EquipmentSlot.FEET)).shape("X X", "X X").setIngredient('X', Material.SUGAR_CANE));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "titan_helmet"), TitanArmor(EquipmentSlot.HEAD)).shape("XXX", "X X").setIngredient('X', Material.IRON_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "titan_chestplate"), TitanArmor(EquipmentSlot.CHEST)).shape("X X", "XXX", "XXX").setIngredient('X', Material.IRON_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "titan_leggings"), TitanArmor(EquipmentSlot.LEGS)).shape("XXX", "X X", "X X").setIngredient('X', Material.IRON_BLOCK));
        plugin.getServer().addRecipe(new ShapedRecipe(new NamespacedKey(plugin, "titan_boots"), TitanArmor(EquipmentSlot.FEET)).shape("X X", "X X").setIngredient('X', Material.IRON_BLOCK));
    }
}
