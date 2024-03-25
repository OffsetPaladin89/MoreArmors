package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.SetType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public record GiveArmor(MoreArmorsMain plugin) {

  public ItemStack GiveArmor(SetType type, ArmorType armorType, Integer specialValue) {
    return switch (type) {
      case EMERALD -> plugin.armorSets.EmeraldArmor(ArmorType.matchSlot(armorType), specialValue);
      case END -> plugin.armorSets.EndArmor(ArmorType.matchSlot(armorType));
      case EXPERIENCE -> plugin.armorSets.ExperienceArmor(ArmorType.matchSlot(armorType));
      case MINER -> plugin.armorSets.MinerArmor(ArmorType.matchSlot(armorType));
      case NETHER -> plugin.armorSets.NetherArmor(ArmorType.matchSlot(armorType));
      case SEA_GREED -> plugin.armorSets.SeaGreedArmor(ArmorType.matchSlot(armorType));
      case SPEEDSTER -> plugin.armorSets.SpeedsterArmor(ArmorType.matchSlot(armorType));
      case TITAN -> plugin.armorSets.TitanArmor(ArmorType.matchSlot(armorType));
//      case TRUE_DIAMOND -> switch (armorType) {
//        case HELMET -> plugin.truediamond.TrueDiamondHelmet(level, amount);
//        case CHESTPLATE -> plugin.truediamond.TrueDiamondChestplate(level, amount);
//        case LEGGINGS -> plugin.truediamond.TrueDiamondLeggings(level, amount);
//        case BOOTS -> plugin.truediamond.TrueDiamondBoots(level, amount);
//      };
      default -> null;
    };
  }

  public void GiveArmorCommand(CommandSender sender, Player target, String type, ArmorType armorType, Integer specialValue) {
    ItemStack item = GiveArmor(SetType.getSetType(type), armorType, specialValue);
    PlayerInventory inventory = target.getInventory();
    if (inventory.firstEmpty() == -1) target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
    else inventory.addItem(item);
    target.sendMessage(plugin.convertColoredString("&e(&6MoreArmors&e) Received " + item.getItemMeta().getDisplayName() + "&e."));
    sender.sendMessage(plugin.convertColoredString("&e(&6MoreArmors&e) Gave " + target.getName() + " " + item.getItemMeta().getDisplayName() + "&e."));
  }
}
