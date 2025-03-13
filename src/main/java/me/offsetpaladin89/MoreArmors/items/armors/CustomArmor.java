package me.offsetpaladin89.MoreArmors.items.armors;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.utils.skilltree.BaseSkillTree;
import me.offsetpaladin89.MoreArmors.utils.skilltree.SkillTree;
import me.offsetpaladin89.MoreArmors.utils.skilltree.SkillTreeNode;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.items.misc.CustomItem;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.UUID;

public class CustomArmor extends CustomItem {

    protected int armor = 0;
    protected int armorToughness = 0;
    protected SlotType slot;
    protected ArmorType armorID;

    protected ListMultimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();

    public CustomArmor(ItemStack item) {
        this.item = item;
        createItemFromNBT();
    }

    CustomArmor(SlotType slot) {
        this.slot = slot;
        item = getBaseItem();
        rarity = Rarity.getRarity(getRarity(), tier);
        displayName = getFormattedName(getDefaultName());
        armor = getDefaultArmor();
        armorToughness = getDefaultArmorToughness();

        createItem(true);
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    // Armor Creation

    protected void createItem(boolean resetPersistent) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);

        updateItem(resetPersistent);
    }

    public void updateItem(boolean resetPersistent) {
        armorID = getArmorID();

        setDisplayName();
        setTextures();
        setLore();

        setFlags();

        baseAttributes();
        armorAttributes();
        setAttributes();

        baseNBT(resetPersistent);
        armorNBT();
    }

    protected void setDisplayName() {
        displayName = getFormattedName(getDefaultName());

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);
    }

    protected void setFlags() {
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(itemMeta);
    }

    protected void setLeatherColor(Color LEATHER_COLOR) {
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();

        itemMeta.setColor(LEATHER_COLOR);

        item.setItemMeta(itemMeta);
    }

    protected void baseAttributes() {
        AttributeModifier armorAttribute = new AttributeModifier(pluginKey(), armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);
        AttributeModifier armorToughnessAttribute = new AttributeModifier(pluginKey(), armorToughness, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);

        attributeModifiers.put(Attribute.ARMOR, armorAttribute);
        attributeModifiers.put(Attribute.ARMOR_TOUGHNESS, armorToughnessAttribute);
    }

    protected void setAttributes() {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setAttributeModifiers(attributeModifiers);
        item.setItemMeta(itemMeta);
    }

    protected void baseNBT(boolean resetPersistent) {
        NBT.modify(item, nbt -> {
            nbt.setEnum("Rarity", rarity);
            nbt.setInteger("Tier", tier);
            nbt.setInteger("Armor", armor);
            nbt.setInteger("ArmorToughness", armorToughness);
            if(resetPersistent) {
                nbt.resolveOrCreateCompound("SkillTree.SkillTreeNodes");
                for (int n = 0; n < 15; n++) nbt.resolveOrCreateCompound("SkillTree.SkillTreeNodes").setBoolean("Node" + n, false);
                if(item.getType().equals(Material.PLAYER_HEAD)) nbt.setUUID("UUID", UUID.randomUUID());
                nbt.setEnum("ArmorID", armorID);
            }
            nbt.resolveOrCreateCompound("SkillTree").setInteger("SkillPoints", tier > 50 ? 10 : tier / 5);
        });
    }

    protected NamespacedKey pluginKey() {
        return new NamespacedKey("morearmors", String.format("%s_%s", armorID, slot).toLowerCase());
    }

    public void createItemFromNBT() {
        NBT.get(item, nbt -> {
            rarity = nbt.getEnum("Rarity", Rarity.class);
            armor = nbt.getInteger("Armor");
            armorToughness = nbt.getInteger("ArmorToughness");
            armorID = nbt.getEnum("ArmorID", ArmorType.class);
        });

        getSpecialValues();

        slot = SlotType.matchType(item);
        displayName = getFormattedName(getDefaultName());
    }

    // Skill Tree

    protected ArrayList<GuiItem> nodes() {
        ArrayList<GuiItem> nodes = new ArrayList<>();
        SkillTree skillTree = new SkillTree(item);
        for(SkillTreeNode node : skillTree.baseNodes()) {
            node.setDescription(getDescription(node));
            node.setDisplayName(getDisplayName(node));
            nodes.add(node.getItem());
        }

        return nodes;
    }

    public void openSkillTree(HumanEntity p) {
        BaseSkillTree skillTree = new BaseSkillTree(nodes());
        skillTree.getBaseSkillTree().show(p);
    }

    // Overridden Methods

    protected void armorNBT() {
    }
    protected void armorAttributes(){
    }

    protected ArmorType getArmorID() { return ArmorType.INVALID; }
    protected ItemStack getBaseItem() {
        return null;
    }
    protected int getDefaultArmor() {
        return 0;
    }
    protected int getDefaultArmorToughness() {
        return 0;
    }
    protected String getDefaultName() {
        return null;
    }
    protected ArrayList<String> getDescription(SkillTreeNode node) {
        return null;
    }
    protected String getDisplayName(SkillTreeNode node) {
        return null;
    }
    protected Rarity getRarity() {
        return Rarity.DEVELOPER;
    }
    protected void getSpecialValues() {
    }

    protected void setTextures() {
    }
    protected void setLore() {
    }
}