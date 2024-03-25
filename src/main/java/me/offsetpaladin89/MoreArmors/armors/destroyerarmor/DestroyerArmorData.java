package me.offsetpaladin89.MoreArmors.armors.destroyerarmor;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;

public record DestroyerArmorData(MoreArmorsMain plugin) {
//
//  public ItemStack addLore(ItemStack item, Integer killcount, ArmorType armortype) {
//    ItemMeta itemmeta = item.getItemMeta();
//    ArrayList<String> lore = new ArrayList<>();
//    boolean maxed = Math.floor(killcount / 250) >= 10.0D;
//    int currentkills = Math.floorMod(killcount, 250);
//    int currentbonus = (int) Math.floor(killcount / 250);
//    int nextbonus = (int) Math.floor(killcount / 250) + 1;
//    switch(armortype) {
//      case HELMET -> {
//        lore.add(this.plugin.convertColoredString("&6Item Ability: Night Vision"));
//        lore.add(this.plugin.convertColoredString("&7Grants &aNight Vision&7."));
//        lore.add("");
//      }
//      case CHESTPLATE -> {
//        lore.add(this.plugin.convertColoredString("&6Item Ability: Armored"));
//        lore.add(this.plugin.convertColoredString("&a20% &7chance to reduce damage taken by &a50%&7."));
//        lore.add("");
//      }
//      case BOOTS -> {
//        lore.add(this.plugin.convertColoredString("&6Item Ability: Boost"));
//        lore.add(this.plugin.convertColoredString("&7Launches you into the direction you are facing"));
//        lore.add(this.plugin.convertColoredString("&7and creates an explosion where you launched."));
//        lore.add(this.plugin.convertColoredString("&8Cooldown: &a1s"));
//        lore.add("");
//      }
//    }
//    lore.add(this.plugin.convertColoredString("&6Piece Upgrade: Slayer"));
//    lore.add(this.plugin.convertColoredString("&7Kill mobs to increase your damage."));
//    if (maxed) {
//      lore.add(this.plugin.convertColoredString("&7Current Bonus: &e+10 Damage"));
//      lore.add(this.plugin.convertColoredString("&7Next Bonus: &e+10 Damage &8(&a" + (killcount - 2500) + "&7/&c0&8) &a&lMAXED OUT"));
//    } else {
//      lore.add(this.plugin.convertColoredString("&7Current Bonus: &e+" + currentbonus + " Damage"));
//      lore.add(this.plugin.convertColoredString("&7Next Bonus: &e+" + nextbonus + " Damage &8(&a" + currentkills + "&7/&c250&8)"));
//    }
//    lore.add(this.plugin.convertColoredString("&8Max +10 Damage"));
//    lore.add("");
//    lore.add(this.plugin.convertColoredString("&6Full Set Bonus: Warrior"));
//    lore.add(this.plugin.convertColoredString("&7Grants &aStrength II&7."));
//    lore.add(this.plugin.convertColoredString("&7Grants &aRegeneration II&7."));
//    lore.add(this.plugin.convertColoredString("&7Grants &aResistance II&7."));
//    lore.add("");
//    lore.add(this.plugin.convertColoredString("&d&lMYTHIC"));
//    itemmeta.setLore(lore);
//    item.setItemMeta(itemmeta);
//    return item;
//  }
//
//  public ItemStack addFlags(ItemStack item) {
//    ItemMeta itemmeta = item.getItemMeta();
//    itemmeta.setUnbreakable(true);
//    itemmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
//    itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//    item.setItemMeta(itemmeta);
//    return item;
//  }
//
//  public ItemStack addAttributes(ItemStack item, int armor, EquipmentSlot slot, int kills) {
//    ItemMeta itemmeta = item.getItemMeta();
//    itemmeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
//    AttributeModifier armormodifier = new AttributeModifier(UUID.randomUUID(), "armor", armor, AttributeModifier.Operation.ADD_NUMBER, slot);
//    itemmeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armormodifier);
//    item.setItemMeta(itemmeta);
//    return item;
//  }
//
//  public ItemStack addNBTTags(ItemStack item, Integer kills) {
//    NBTItem nbtItem = new NBTItem(item);
//    nbtItem.setBoolean("IsCustomItem", true);
//    nbtItem.setString("CustomItemID", "destroyer");
//    nbtItem.setString("CustomItemType", "armor");
//    nbtItem.setInteger("Kills", kills);
//    return nbtItem.getItem();
//  }
}
