package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public record GiveArmor(MoreArmorsMain plugin) {

  public ItemStack GiveArmorPiece(String type, ArmorType armorType, Integer level, Integer amount) {
    return switch (type.toLowerCase()) {
      case "emerald" -> switch (armorType) {
        case HELMET -> plugin.armorSets.EmeraldHelmet(amount);
        case CHESTPLATE -> plugin.armorSets.EmeraldChestplate(amount);
        case LEGGINGS -> plugin.armorSets.EmeraldLeggings(amount);
        case BOOTS -> plugin.armorSets.EmeraldBoots(amount);
      };
      case "end" -> switch (armorType) {
        case HELMET -> plugin.armorSets.EndHelmet();
        case CHESTPLATE -> plugin.armorSets.EndChestplate();
        case LEGGINGS -> plugin.armorSets.EndLeggings();
        case BOOTS -> plugin.armorSets.EndBoots();
      };
      case "experience" -> switch (armorType) {
        case HELMET -> plugin.armorSets.ExperienceHelmet();
        case CHESTPLATE -> plugin.armorSets.ExperienceChestplate();
        case LEGGINGS -> plugin.armorSets.ExperienceLeggings();
        case BOOTS -> plugin.armorSets.ExperienceBoots();
      };
      case "miner" -> switch (armorType) {
        case HELMET -> plugin.armorSets.MinerHelmet();
        case CHESTPLATE -> plugin.armorSets.MinerChestplate();
        case LEGGINGS -> plugin.armorSets.MinerLeggings();
        case BOOTS -> plugin.armorSets.MinerBoots();
      };
      case "nether" -> switch (armorType) {
        case HELMET -> plugin.armorSets.NetherHelmet();
        case CHESTPLATE -> plugin.armorSets.NetherChestplate();
        case LEGGINGS -> plugin.armorSets.NetherLeggings();
        case BOOTS -> plugin.armorSets.NetherBoots();
      };
      case "seagreed" -> switch (armorType) {
        case HELMET -> plugin.armorSets.SeaGreedHelmet();
        case CHESTPLATE -> plugin.armorSets.SeaGreedChestplate();
        case LEGGINGS -> plugin.armorSets.SeaGreedLeggings();
        case BOOTS -> plugin.armorSets.SeaGreedBoots();
      };
      case "speedster" -> switch (armorType) {
        case HELMET -> plugin.armorSets.SpeedsterHelmet();
        case CHESTPLATE -> plugin.armorSets.SpeedsterChestplate();
        case LEGGINGS -> plugin.armorSets.SpeedsterLeggings();
        case BOOTS -> plugin.armorSets.SpeedsterBoots();
      };
      case "titan" -> switch (armorType) {
        case HELMET -> plugin.armorSets.TitanHelmet();
        case CHESTPLATE -> plugin.armorSets.TitanChestplate();
        case LEGGINGS -> plugin.armorSets.TitanLeggings();
        case BOOTS -> plugin.armorSets.TitanBoots();
      };
      case "truediamond" -> switch (armorType) {
        case HELMET -> plugin.truediamond.TrueDiamondHelmet(level, amount);
        case CHESTPLATE -> plugin.truediamond.TrueDiamondChestplate(level, amount);
        case LEGGINGS -> plugin.truediamond.TrueDiamondLeggings(level, amount);
        case BOOTS -> plugin.truediamond.TrueDiamondBoots(level, amount);
      };
      default -> null;
    };
  }

  public void GiveArmorCommand(CommandSender sender, Player target, String type, ArmorType armorType, int level, int amount) {
    ItemStack item = GiveArmorPiece(type, armorType, level, amount);
    PlayerInventory inventory = target.getInventory();
    if (inventory.firstEmpty() == -1) target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
    else inventory.addItem(item);
    target.sendMessage(plugin.convertColoredString("&e(&6MoreArmors&e) Received " + item.getItemMeta().getDisplayName() + "&e."));
    sender.sendMessage(plugin.convertColoredString("&e(&6MoreArmors&e) Gave " + target.getName() + " " + item.getItemMeta().getDisplayName() + "&e."));
  }
}
