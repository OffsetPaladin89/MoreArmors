package me.offsetpaladin89.MoreArmors.utils;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.text.NumberFormat;
import java.util.Base64;
import java.util.Locale;
import java.util.UUID;

public record Util(MoreArmorsMain plugin) {

    public static String colorString(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public void sendConsoleMessage(String m) {
        plugin.getServer().getConsoleSender().sendMessage(m);
    }

    public static void sendColoredMessage(CommandSender s, String m) {
        s.sendMessage(colorString(m));
    }

    public static String formatNumber(int n) {
        NumberFormat formatter = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        formatter.setMinimumFractionDigits(2);
        return formatter.format(n);
    }

    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static void addItem(PlayerInventory inventory, Player target, ItemStack item, int stackSize) {
        item.setAmount(stackSize);
        if (inventory.firstEmpty() == -1) target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
        else inventory.addItem(item);
    }

    public static boolean isAirOrNull(ItemStack item) {
        return item == null || item.getType().equals(Material.AIR);
    }

    public static void modifySkullSkin(ItemStack item, String textureValue, UUID uuid) {

        String convertBase64Texture = Base64.getEncoder().encodeToString(String.format("{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/%s\"}}}", textureValue).getBytes());

        NBT.modifyComponents(item, nbt -> {
            ReadWriteNBT profileNbt = nbt.getOrCreateCompound("minecraft:profile");
            profileNbt.setUUID("id", uuid);
            ReadWriteNBT propertiesNbt = profileNbt.getCompoundList("properties").addCompound();
            propertiesNbt.setString("name", "textures");
            propertiesNbt.setString("value", convertBase64Texture);
        });
    }

    public static boolean isCustomItem(ItemStack item) {
        boolean isMaterial = NBT.get(item, nbt -> nbt.getEnum("MaterialID", MaterialType.class) != null);
        boolean isArmor = NBT.get(item, nbt -> nbt.getEnum("ArmorID", ArmorType.class) != null);
        return isMaterial || isArmor;
    }

    public static boolean matchingCustomItem(ItemStack item, ArmorType itemID) {
        if(isAirOrNull(item)) return false;
        ArmorType type = NBT.get(item, nbt -> (ArmorType) nbt.getEnum("ArmorID", ArmorType.class));
        if(type == null) return false;
        return type.equals(itemID);
    }

    public static boolean IsFullCustomSet(ArmorType tag, PlayerInventory inventory) {
        ItemStack helmet = inventory.getHelmet();
        ItemStack chestplate = inventory.getChestplate();
        ItemStack leggings = inventory.getLeggings();
        ItemStack boots = inventory.getBoots();

        if(isAirOrNull(helmet) || isAirOrNull(chestplate) || isAirOrNull(leggings) || isAirOrNull(boots)) return false;

        boolean hasHelmet = matchingCustomItem(helmet, tag);
        boolean hasChestplate = matchingCustomItem(chestplate, tag);
        boolean hasLeggings = matchingCustomItem(leggings, tag);
        boolean hasBoots = matchingCustomItem(boots, tag);

        return hasHelmet && hasChestplate && hasLeggings && hasBoots;
    }
}
