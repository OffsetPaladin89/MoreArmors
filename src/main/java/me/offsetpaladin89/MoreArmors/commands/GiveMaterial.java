package me.offsetpaladin89.MoreArmors.commands;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public record GiveMaterial(MoreArmorsMain plugin) {

  public ItemStack GiveMaterialItem(String type, Integer amount) {
    return switch (type.toLowerCase()) {
      case "compactedblazerod" -> plugin.materials.CompactedBlazeRod(amount);
      case "compactedcobblestone" -> plugin.materials.CompactedCobblestone(amount);
      case "compactedendstone" -> plugin.materials.CompactedEndStone(amount);
      case "compactedeyeofender" -> plugin.materials.CompactedEyeOfEnder(amount);
      case "compactedsoulsand" -> plugin.materials.CompactedSoulSand(amount);
      case "compactedsugarcane" -> plugin.materials.CompactedSugarCane(amount);
      case "nethercrown" -> plugin.materials.NetherCrown();
      case "compacteddiamond" -> plugin.materials.CompactedDiamond(amount);
      case "compacteddiamondblock" -> plugin.materials.CompactedDiamondBlock(amount);
      case "diamondsingularity" -> plugin.materials.DiamondSingularity();
      default -> null;
    };
  }

  public void GiveMaterialCommand(CommandSender sender, Player target, String type, int amount) {
    ItemStack item = GiveMaterialItem(type, amount);
    PlayerInventory inventory = target.getInventory();
    if (type.equalsIgnoreCase("diamondsingularity") || type.equalsIgnoreCase("nethercrown")) {
      for (int x = 0; x < amount; x++) {
        item = GiveMaterialItem(type, amount);
        if (inventory.firstEmpty() == -1) target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
        else inventory.addItem(item);
      }
    }
    else if (inventory.firstEmpty() == -1) target.getWorld().dropItem(target.getLocation().add(0.0D, 0.5D, 0.0D), item);
    else inventory.addItem(item);
    target.sendMessage(plugin.convertColoredString("&e(&6MorePluginsCore&e) Recieved " + amount + "x " + item.getItemMeta().getDisplayName() + "&e."));
    sender.sendMessage(plugin.convertColoredString("&e(&6MorePluginsCore&e) Gave " + target.getName() + " " + amount + "x " + item.getItemMeta().getDisplayName() + "&e."));
  }
}
