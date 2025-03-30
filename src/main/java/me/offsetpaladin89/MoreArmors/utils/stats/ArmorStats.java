package me.offsetpaladin89.MoreArmors.utils.stats;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBTCompoundList;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Location;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.enums.StatType;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class ArmorStats extends BaseStats {

    public ArmorStats(double armor) {
        super();
        setStat(Location.ALL, StatType.ARMOR, armor);
    }

    public ArmorStats(double armor, double armorTough) {
        super();
        setStat(Location.ALL, StatType.ARMOR, armor);
        setStat(Location.ALL, StatType.ARMOR_TOUGH, armorTough);
    }


    public void setStats(ItemStack item, ArmorType armorID, SlotType slot) {
        setAttributes(item, armorID, slot);
        setNBT(item);
    }

    public void setAttributes(ItemStack item, ArmorType armorID, SlotType slot) {
        ItemMeta itemMeta = item.getItemMeta();

        ListMultimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();
        NamespacedKey pluginKey = new NamespacedKey("morearmors", String.format("%s_%s", armorID, slot).toLowerCase());

        for(StatType type : StatType.values()) {
            if(type.attribute == null) continue;
            AttributeModifier attributeModifier = new AttributeModifier(pluginKey, stats.get(Location.ALL).get(type), StatType.getAttributeMethod(type), EquipmentSlotGroup.ARMOR);
            attributeModifiers.put(type.attribute, attributeModifier);
        }

        itemMeta.setAttributeModifiers(attributeModifiers);

        item.setItemMeta(itemMeta);
    }

    public void setNBT(ItemStack item) {
        NBT.modify(item, nbt -> {
            for(Location loc : stats.keySet()) {
                ReadWriteNBT base = nbt.resolveOrCreateCompound("Stats").resolveOrCreateCompound(loc.name);
                for(StatType stat : stats.get(loc).keySet()) if(stats.get(loc).get(stat) != 0) base.setDouble(stat.name, stats.get(loc).get(stat));

                ReadWriteNBTCompoundList effects = base.getCompoundList("PotionEffects");
                for(PotionEffect potion : potions.get(loc)) {
                    ReadWriteNBT effect = effects.addCompound();

                    String effectKey = potion.getType().getKeyOrNull().toString();
                    effect.setString("Type", effectKey);
                    effect.setInteger("Level", potion.getAmplifier());
                }
            }
        });
    }
}
