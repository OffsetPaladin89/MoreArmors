package me.offsetpaladin89.MoreArmors.items.armors;

import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.utils.Lore;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.offsetpaladin89.MoreArmors.enums.SlotType.HELMET;

public class SeaGreedArmor extends CustomArmor {

    private static final Color LEATHER_COLOR = Color.fromRGB(130, 140, 100);
    private static final Rarity BASE_RARITY = Rarity.MYTHIC;

    public SeaGreedArmor(ItemStack item) {
        super(item);
    }

    public SeaGreedArmor(SlotType slot) {
        super(slot);
    }

    private void setLore() {
        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addColoredLine("&6Item Ability: Ore Greed");
        lore.addColoredLine("&7Gain &a+50% &7additional ore drops while in water.");
        lore.addColoredLine("&7Gain &a+100% &7mining speed while underwater.");
        lore.addEmpty();
        lore.addColoredLine("&6Full Set Bonus: Sea's Greed");
        lore.addColoredLine("&7Deal &c+100% &7damage while in water.");
        lore.addColoredLine("&7Gain &a+200% &7swim speed while in water.");
        lore.addColoredLine("&7Gain &5Conduit Power &7while in water.");
        lore.addEmpty();
        lore.addColoredLine("&4Special Item Ability: &cBlessing of the Sea God");
        lore.addColoredLine("&7There is a &a25% &7chance when killing an &5Elder Guardian");
        lore.addColoredLine("&7to trigger &4&lBlessing of the Sea God &7granting:");
        lore.addColoredLine("&7- &a25 &7Diamond Blocks");
        lore.addColoredLine("&7- &a100 &7Gold Blocks");
        lore.addColoredLine("&7- &aHaste III &7for &a1 hour");
        lore.addColoredLine("&7- &aStrength II &7for &a1 hour");
        lore.addColoredLine("&7- &aSpeed II &7for &a1 hour");
        lore.addColoredLine("&7- &aResistance II &7for &a1 hour");
        lore.addArmorRarity(rarity);
        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }

    public void updateItem() {
        this.armorID = ArmorType.SEA_GREED;

        if(slot.equals(HELMET)) assignSkull(item);
        else setLeatherColor(LEATHER_COLOR);
        setLore();

        setFlags();

        addAttributes();

        baseNBT();
    }

    private int getDefaultArmor() {
        return switch (slot) {
            case HELMET, BOOTS -> 3;
            case CHESTPLATE -> 8;
            case LEGGINGS -> 6;
            default -> 0;
        };
    }

    private int getDefaultArmorToughness() {
        return switch (slot) {
            case HELMET, CHESTPLATE, LEGGINGS, BOOTS -> 2;
            default -> 0;
        };
    }

    private String getDefaultName() {
        return switch (slot) {
            case HELMET -> "Sea Greed Helmet";
            case CHESTPLATE -> "Sea Greed Chestplate";
            case LEGGINGS -> "Sea Greed Leggings";
            case BOOTS -> "Sea Greed Boots";
            default -> null;
        };
    }

    private void addAttributes() {
        baseAttributes();
        AttributeModifier attribute = new AttributeModifier(pluginKey(), 1, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.ARMOR);

        attributeModifiers.put(Attribute.SUBMERGED_MINING_SPEED, attribute);

        setAttributes();
    }

    private ItemStack getBaseItem() {
        return switch (slot) {
            case HELMET -> new ItemStack(Material.PLAYER_HEAD);
            case CHESTPLATE -> new ItemStack(Material.LEATHER_CHESTPLATE);
            case LEGGINGS -> new ItemStack(Material.LEATHER_LEGGINGS);
            case BOOTS -> new ItemStack(Material.LEATHER_BOOTS);
            default -> null;
        };
    }

    private void assignSkull(ItemStack item) {
        Util.modifySkullSkin(item, "935541523f14c78d8de98cc296c798f0b867ba85344ed77f6dae12455a174", null);
    }
}
