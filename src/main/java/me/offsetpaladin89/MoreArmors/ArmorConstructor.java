package me.offsetpaladin89.MoreArmors;

import com.cryptomorin.xseries.SkullUtils;
import com.github.fracpete.romannumerals4j.RomanNumeralFormat;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.UUID;

public record ArmorConstructor(MoreArmorsMain plugin) {

    private NBTItem addNBT(ItemStack item, String customItemID, Rarity rarity, int armor, int armorToughness, EquipmentSlot slot) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setBoolean("IsCustomItem", true);
        nbtItem.setString("CustomItemID", customItemID);
        nbtItem.setString("CustomItemType", "armor");
        nbtItem.setInteger("Rarity", rarity.ordinal() + 1);
        nbtItem.setInteger("Armor", armor);
        nbtItem.setInteger("ArmorToughness", armorToughness);
        nbtItem.setString("AttributeSlot", slot.name());
        if(item.getType().equals(Material.PLAYER_HEAD)) nbtItem.setString("UUID", UUID.randomUUID().toString());
        return nbtItem;
    }

    private String getTrueDiamondName(String displayName, int level) { return "Level " + new RomanNumeralFormat().format(level) + " " + displayName; }

    private Rarity getTrueDiamondRarity(int level) { return Rarity.getRarity((level - 1) / 4 + 3); }

    public ItemStack createEmeraldArmor(@NotNull ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        return createEmeraldArmor(item, item.getItemMeta().getDisplayName(), Rarity.getRarity(nbtItem.getInteger("Rarity")), nbtItem.getInteger("Armor"), nbtItem.getInteger("ArmorToughness"), nbtItem.getInteger("EmeraldCount"), EquipmentSlot.valueOf(nbtItem.getString("AttributeSlot")));
    }
    public ItemStack createEmeraldArmor(@NotNull ItemStack item, String displayName, Rarity rarity, int armor, int armorToughness, int emeraldAmount, EquipmentSlot equipmentSlot) {
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        int healthBoost = emeraldAmount / 50 > 5 ? 10 : emeraldAmount / 50 * 2;
        // Display
        itemMeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + displayName));
        itemMeta.setColor(Color.LIME);
        // Lore
        lore.add(plugin.convertColoredString("&6Piece Upgrade: Emerald Blood"));
        lore.add(plugin.convertColoredString("&7Mine emeralds to increase your max health."));
        if(emeraldAmount >= 250) lore.add(plugin.convertColoredString("&7Current Bonus (&a5&8/&a5&7): &e+10 Health &a&lMAXED OUT"));
        else {
            lore.add(plugin.convertColoredString("&7Current Bonus (&a" + emeraldAmount / 50 + "&8/&a5&7): &e+" + emeraldAmount / 50 + " Health"));
            lore.add(plugin.convertColoredString("&7Next Upgrade: &e+" + (emeraldAmount / 50 + 2) + " Health &8(&a" + emeraldAmount % 50 + "&7/&c50&8)"));
            lore.add(plugin.convertColoredString("&8Max +10 Health"));
        }
        lore.add("");
        lore.add(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "&l" + rarity));
        itemMeta.setLore(lore);
        // Flags
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        // Attributes
        itemMeta.removeAttributeModifier(equipmentSlot);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "maxHealth", healthBoost, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "armorToughness", armorToughness, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        item.setItemMeta(itemMeta);
        // NBT Values
        NBTItem nbtItem = addNBT(item, "emerald", rarity, armor, armorToughness, equipmentSlot);
        nbtItem.setInteger("EmeraldCount", emeraldAmount);
        return nbtItem.getItem();
    }

    public ItemStack createEndArmor(@NotNull ItemStack item, String displayName, Rarity rarity, int armor, int armorToughness, EquipmentSlot equipmentSlot) {
        ItemMeta itemMeta = item.getItemMeta();
        LeatherArmorMeta leatherItemMeta;
        ArrayList<String> lore = new ArrayList<>();
        // Display
        if(item.getType().equals(Material.PLAYER_HEAD)) SkullUtils.applySkin(itemMeta, "fee4eabeb72f19088ade78266191c8f77398cc0d80cdd27563a5d66b71912b28");
        else {
            leatherItemMeta = (LeatherArmorMeta) item.getItemMeta();
            leatherItemMeta.setColor(Color.PURPLE);
            itemMeta = leatherItemMeta;
        }
        itemMeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + displayName));
        // Lore
        lore.add(plugin.convertColoredString("&6Item Ability: Bane of the End"));
        lore.add(plugin.convertColoredString("&7Deal &c+10% &7damage while in the End."));
        lore.add("");
        lore.add(plugin.convertColoredString("&6Full Set Bonus: End King"));
        lore.add(plugin.convertColoredString("&7Increases max health by &a20"));
        lore.add(plugin.convertColoredString("&7while in the End."));
        lore.add("");
        lore.add(plugin.convertColoredString("&7Deal &c+100% &7damage while in"));
        lore.add(plugin.convertColoredString("&7the End."));
        lore.add("");
        lore.add(plugin.convertColoredString("&6Full Set Ability: Ender Warp &e&lSHIFT LEFT CLICK"));
        lore.add(plugin.convertColoredString("&7Teleport &a10 blocks &7forwards"));
        lore.add(plugin.convertColoredString("&7while in the End."));
        lore.add(plugin.convertColoredString("&8Cooldown: &a1s"));
        lore.add("");
        lore.add(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "&l" + rarity));
        itemMeta.setLore(lore);
        // Flags
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        // Attributes
        itemMeta.removeAttributeModifier(equipmentSlot);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "armorToughness", armorToughness, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        item.setItemMeta(itemMeta);
        // NBT Values
        return addNBT(item, "end", rarity, armor, armorToughness, equipmentSlot).getItem();
    }

    public ItemStack createExperienceArmor(@NotNull ItemStack item, String displayName, Rarity rarity, int armor, EquipmentSlot equipmentSlot) {
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        // Display
        itemMeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + displayName));
        itemMeta.setColor(Color.BLUE);
        // Lore
        lore.add(plugin.convertColoredString("&6Full Set Bonus: Experience"));
        lore.add(plugin.convertColoredString("&7Recieve &adouble experience &7from killing"));
        lore.add(plugin.convertColoredString("&7mobs and mining ores."));
        lore.add("");
        lore.add(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "&l" + rarity));
        itemMeta.setLore(lore);
        // Flags
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        // Attributes
        itemMeta.removeAttributeModifier(equipmentSlot);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        item.setItemMeta(itemMeta);
        // NBT Values
        return addNBT(item, "experience", rarity, armor, 0, equipmentSlot).getItem();
    }

    public ItemStack createMinerArmor(@NotNull ItemStack item, String displayName, Rarity rarity, int armor, EquipmentSlot equipmentSlot) {
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        // Display
        itemMeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + displayName));
        itemMeta.setColor(Color.GRAY);
        // Lore
        lore.add(plugin.convertColoredString("&6Full Set Bonus: Haste"));
        lore.add(plugin.convertColoredString("&7Gives &aHaste II &7for &a5 seconds"));
        lore.add(plugin.convertColoredString("&7after mining a block."));
        lore.add("");
        lore.add(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "&l" + rarity));
        itemMeta.setLore(lore);
        // Flags
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        // Attributes
        itemMeta.removeAttributeModifier(equipmentSlot);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        item.setItemMeta(itemMeta);
        // NBT Values
        return addNBT(item, "miner", rarity, armor, 0, equipmentSlot).getItem();
    }

    public ItemStack createNetherArmor(@NotNull ItemStack item, String displayName, Rarity rarity, int armor, int armorToughness, EquipmentSlot equipmentSlot) {
        ItemMeta itemMeta = item.getItemMeta();
        LeatherArmorMeta leatherItemMeta;
        ArrayList<String> lore = new ArrayList<>();
        // Display
        if(item.getType().equals(Material.PLAYER_HEAD)) SkullUtils.applySkin(itemMeta, "cdf74e323ed41436965f5c57ddf2815d5332fe999e68fbb9d6cf5c8bd4139f");
        else {
            leatherItemMeta = (LeatherArmorMeta) item.getItemMeta();
            leatherItemMeta.setColor(Color.GRAY);
            itemMeta = leatherItemMeta;
        }
        itemMeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + displayName));
        // Lore
        lore.add(plugin.convertColoredString("&6Item Ability: Bane of the Nether"));
        lore.add(plugin.convertColoredString("&7Deal &c+10% &7damage to mobs in the Nether."));
        lore.add("");
        lore.add(plugin.convertColoredString("&6Full Set Bonus: Nether King"));
        lore.add(plugin.convertColoredString("&7Grants &aFire Resistance &7while"));
        lore.add(plugin.convertColoredString("&7in the Nether."));
        lore.add("");
        lore.add(plugin.convertColoredString("&7Increases max health by &a20"));
        lore.add(plugin.convertColoredString("&7while in the Nether."));
        lore.add("");
        lore.add(plugin.convertColoredString("&7Deal &c+100% &7damage while in"));
        lore.add(plugin.convertColoredString("&7the Nether."));
        lore.add("");
        lore.add(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "&l" + rarity));
        itemMeta.setLore(lore);
        // Flags
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        // Attributes
        itemMeta.removeAttributeModifier(equipmentSlot);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "armorToughness", armorToughness, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        item.setItemMeta(itemMeta);
        // NBT Values
        return addNBT(item, "nether", rarity, armor, armorToughness, equipmentSlot).getItem();
    }

    public ItemStack createSeaGreedArmor(@NotNull ItemStack item, String displayName, Rarity rarity, int armor, int armorToughness, EquipmentSlot equipmentSlot) {
        ItemMeta itemMeta = item.getItemMeta();
        LeatherArmorMeta leatherItemMeta;
        ArrayList<String> lore = new ArrayList<>();
        // Display
        if(item.getType().equals(Material.PLAYER_HEAD)) SkullUtils.applySkin(itemMeta, "935541523f14c78d8de98cc296c798f0b867ba85344ed77f6dae12455a174");
        else {
            leatherItemMeta = (LeatherArmorMeta) item.getItemMeta();
            leatherItemMeta.setColor(Color.fromRGB(130, 140, 100));
            itemMeta = leatherItemMeta;
        }
        itemMeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + displayName));
        // Lore
        lore.add(plugin.convertColoredString("&6Item Ability: Ore Greed"));
        lore.add(plugin.convertColoredString("&7Gain &a+50% &7ore drops while in water."));
        lore.add("");
        lore.add(plugin.convertColoredString("&6Full Set Bonus: Sea's Greed"));
        lore.add(plugin.convertColoredString("&7Increases movement speed by &a100% &7while in water."));
        lore.add("");
        lore.add(plugin.convertColoredString("&7Deal &c+100% &7damage while in water."));
        lore.add("");
        lore.add(plugin.convertColoredString("&4Special Item Ability: &cBlessing of the Sea God"));
        lore.add(plugin.convertColoredString("&7There is a &a1% &7chance when killing an Elder Guardian"));
        lore.add(plugin.convertColoredString("&7to trigger &4&lBlessing of the Sea God &7granting:"));
        lore.add(plugin.convertColoredString("&7- &a10 &7Diamond Blocks"));
        lore.add(plugin.convertColoredString("&7- &a10 &7Gold Blocks"));
        lore.add(plugin.convertColoredString("&7- &aHaste III &7for &a30 minutes"));
        lore.add(plugin.convertColoredString("&7- &aStrength III &7for &a30 minutes"));
        lore.add(plugin.convertColoredString("&7- &aSpeed III &7for &a30 minutes"));
        lore.add(plugin.convertColoredString("&7- &aResistance II &7for &a30 minutes"));
        lore.add(plugin.convertColoredString("&8Cooldown: 1h"));
        lore.add("");
        lore.add(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "&l" + rarity));
        itemMeta.setLore(lore);
        // Flags
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        // Attributes
        itemMeta.removeAttributeModifier(equipmentSlot);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "armorToughness", armorToughness, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        item.setItemMeta(itemMeta);
        // NBT Values
        return addNBT(item, "seagreed", rarity, armor, armorToughness, equipmentSlot).getItem();
    }

    public ItemStack createSpeedsterArmor(@NotNull ItemStack item, String displayName, Rarity rarity, int armor, EquipmentSlot equipmentSlot) {
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        // Display
        itemMeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + displayName));
        itemMeta.setColor(Color.WHITE);
        // Lore
        lore.add(plugin.convertColoredString("&6Item Ability: Speed"));
        lore.add(plugin.convertColoredString("&7Increases movement speed by &a20%&7."));
        lore.add("");
        lore.add(plugin.convertColoredString("&6Full Set Bonus: Retreat"));
        lore.add(plugin.convertColoredString("&7Grants &aSpeed II &7for &a5 seconds &7when damaged."));
        lore.add("");
        lore.add(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "&l" + rarity));
        itemMeta.setLore(lore);
        // Flags
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        // Attributes
        itemMeta.removeAttributeModifier(equipmentSlot);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        item.setItemMeta(itemMeta);
        // NBT Values
        return addNBT(item, "speedster", rarity, armor, 0, equipmentSlot).getItem();
    }

    public ItemStack createTitanArmor(@NotNull ItemStack item, String displayName, Rarity rarity, int armor, EquipmentSlot equipmentSlot) {
        ItemMeta itemMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        // Display
        itemMeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(rarity) + displayName));
        // Lore
        lore.add(plugin.convertColoredString("&6Item Ability: Health"));
        lore.add(plugin.convertColoredString("&7Increases max health by &a2&7."));
        lore.add("");
        lore.add(plugin.convertColoredString("&6Full Set Bonus: Resistance"));
        lore.add(plugin.convertColoredString("&7Gives &aResistance I &7for &a10 seconds"));
        lore.add(plugin.convertColoredString("&7after killing something."));
        lore.add("");
        lore.add(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "&l" + rarity));
        itemMeta.setLore(lore);
        // Flags
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        // Attributes
        itemMeta.removeAttributeModifier(equipmentSlot);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "maxHealth", 2, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        item.setItemMeta(itemMeta);
        // NBT Values
        return addNBT(item, "titan", rarity, armor, 0, equipmentSlot).getItem();
    }

    public ItemStack createTrueDiamondArmor(@NotNull ItemStack item, String displayName, Rarity rarity, int armor, int armorToughness, EquipmentSlot equipmentSlot, int armorLevel, int diamondSacrifice) {
        ItemMeta itemMeta = item.getItemMeta();
        LeatherArmorMeta leatherItemMeta;
        ArrayList<String> lore = new ArrayList<>();
        // Display
        if(item.getType().equals(Material.PLAYER_HEAD)) SkullUtils.applySkin(itemMeta, "77b9dfd281deaef2628ad5840d45bcda436d6626847587f3ac76498a51c861");
        else {
            leatherItemMeta = (LeatherArmorMeta) item.getItemMeta();
            leatherItemMeta.setColor(Color.fromRGB(150, 210, 220));
            itemMeta = leatherItemMeta;
        }
        itemMeta.setDisplayName(plugin.convertColoredString(Rarity.getColorRarity(getTrueDiamondRarity(armorLevel)) + getTrueDiamondName(displayName, armorLevel)));
        // Lore
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        float stateMultiplier = diamondSacrifice / 5 > 10 ? 2F : 1 + diamondSacrifice / 5 * 10 / 100F;
        float damageScale = armorLevel * stateMultiplier * 2.5F;
        float healthValue = armorLevel * stateMultiplier / 2;
        float movementSpeedScale = armorLevel * stateMultiplier * 1.75F;
        float movementSpeedValue = armorLevel * stateMultiplier * 0.00175F;
        lore.add(plugin.convertColoredString("&6Item Ability: Ultimate Unit"));
        if(diamondSacrifice / 5 > 10) lore.add(plugin.convertColoredString("&e(+100% from Diamond Sacrifice)"));
        else lore.add(plugin.convertColoredString("&e(+" + (diamondSacrifice / 5 * 10) + "% from Diamond Sacrifice)"));
        lore.add(plugin.convertColoredString("&7Increases &cDamage &7by &c" + decimalFormat.format(damageScale) + "%&7."));
        lore.add(plugin.convertColoredString("&7Increases &aMax Health &7by &a" + decimalFormat.format(healthValue) + "&7."));
        lore.add(plugin.convertColoredString("&7Increases &aMovement Speed &7by &a" + decimalFormat.format(movementSpeedScale) + "%&7."));
        lore.add("");
        lore.add(plugin.convertColoredString("&6Piece Upgrade: Diamond Sacrifice"));
        lore.add(plugin.convertColoredString("&7Sacrifice &5Diamond Singularities &7into this armor"));
        lore.add(plugin.convertColoredString("&7piece to improve the stats of it."));
        if(stateMultiplier == 10) lore.add(plugin.convertColoredString("&7Current Bonus (&a5&8/&a5&7): &e+100% Stats &a&lMAXED"));
        else {
            lore.add(plugin.convertColoredString("&7Current Bonus (&a" + (diamondSacrifice / 5) + "&8/&a10&7):" + (diamondSacrifice / 5 * 10) + "% Stats"));
            lore.add(plugin.convertColoredString("&7Next Bonus: &e+" + (diamondSacrifice / 5 * 10 + 10) + "% Stats &8(&a" + (diamondSacrifice % 5) + "&7/&c5&8)"));
            lore.add(plugin.convertColoredString("&8Max +100% Stats"));
        }
        lore.add("");
        lore.add(plugin.convertColoredString("&6Full Set Bonus: Army"));
        lore.add(plugin.convertColoredString("&7Increases &cDamage &7by &c2% &7for each"));
        lore.add(plugin.convertColoredString("&7player within &a10 blocks&7."));
        lore.add(plugin.convertColoredString("&8Max of 10 players"));
        lore.add("");
        lore.add(plugin.convertColoredString(Rarity.getColorRarity(rarity) + "&l" + rarity));
        itemMeta.setLore(lore);
        // Flags
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        // Attributes
        itemMeta.removeAttributeModifier(equipmentSlot);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "armorToughness", armorToughness, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "maxHealth", healthValue, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "movementSpeed", movementSpeedValue, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
        item.setItemMeta(itemMeta);
        // NBT Values
        NBTItem nbtItem = addNBT(item, "truediamond", rarity, armor, armorToughness, equipmentSlot);
        nbtItem.setInteger("ArmorLevel", armorLevel);
        nbtItem.setInteger("DiamondSacrifice", diamondSacrifice);
        return nbtItem.getItem();
    }
}