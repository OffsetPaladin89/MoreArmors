package me.offsetpaladin89.MoreArmors.items.armors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.SkillTreeNode;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
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
    protected int availableSkillPoints;

    protected ListMultimap<Attribute, AttributeModifier> attributeModifiers = ArrayListMultimap.create();

    public CustomArmor(ItemStack item) {
        this.item = item;
    }

    CustomArmor(SlotType slot) {
        this.slot = slot;
    }

    public void createItem() {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);

        updateItem();
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public void increaseTier() {
        tier++;
    }

    public void updateItem() {

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

    protected NamespacedKey pluginKey() {
        return new NamespacedKey("morearmors", String.format("%s_%s", armorID, slot).toLowerCase());
    }

    protected void setAttributes() {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setAttributeModifiers(attributeModifiers);
        item.setItemMeta(itemMeta);
    }

    protected ArrayList<SkillTreeNode> baseMinorNodes() {
        ArrayList<SkillTreeNode> nodes = new ArrayList<>();
        nodes.add(new SkillTreeNode(0, availableSkillPoints > 0, isNodeUnlocked(0)));
        nodes.add(new SkillTreeNode(2, canAccess(3), isNodeUnlocked(2)));
        nodes.add(new SkillTreeNode(4, canAccess(3), isNodeUnlocked(4)));
        nodes.add(new SkillTreeNode(6, canAccess(1), isNodeUnlocked(6)));
        nodes.add(new SkillTreeNode(7, canAccess(5), isNodeUnlocked(7)));
        nodes.add(new SkillTreeNode(11, canAccess(8), isNodeUnlocked(11)));
        nodes.add(new SkillTreeNode(14, canAccess(10), isNodeUnlocked(14)));

        return nodes;
    }

    protected ArrayList<SkillTreeNode> baseMajorNodes() {
        ArrayList<SkillTreeNode> nodes = new ArrayList<>();
        nodes.add(new SkillTreeNode(1, canAccess(2), isNodeUnlocked(1)));
        nodes.add(new SkillTreeNode(3, canAccess(0), isNodeUnlocked(3)));
        nodes.add(new SkillTreeNode(5, canAccess(4), isNodeUnlocked(5)));
        nodes.add(new SkillTreeNode(8, canAccess(6), isNodeUnlocked(8)));
        nodes.add(new SkillTreeNode(10, canAccess(7), isNodeUnlocked(10)));
        nodes.add(new SkillTreeNode(12, canAccess(11), isNodeUnlocked(12)));
        nodes.add(new SkillTreeNode(13, canAccess(14), isNodeUnlocked(13)));

        return nodes;
    }

    protected SkillTreeNode baseMainNode() {
        return new SkillTreeNode(9, availableSkillPoints > 1 && (isNodeUnlocked(12) || isNodeUnlocked(13)), isNodeUnlocked(9));
    }

    private boolean canAccess(int key) {
        return availableSkillPoints > 0 && isNodeUnlocked(key);
    }

    protected void getAvailableSkillPoints() {
        int unlockedNodes = 0;
        for(int n = 0; n < 15; n++) {
            if(isNodeUnlocked(n)) {
                unlockedNodes++;
                if(n == 9) unlockedNodes++;
            }
        }

        availableSkillPoints = NBT.get(item, nbt -> (int) nbt.resolveOrDefault("SkillTree.SkillPoints", 0)) - unlockedNodes;
    }

    protected boolean isNodeUnlocked(int key) {
        return NBT.get(item, nbt -> (boolean) nbt.resolveOrDefault("SkillTree.SkillTreeNodes.Node" + key, false));
    }

    protected void baseNBT() {
        NBT.modify(item, nbt -> {
            nbt.setEnum("Rarity", rarity);
            nbt.setInteger("Tier", tier);
            nbt.setInteger("Armor", armor);
            nbt.setInteger("ArmorToughness", armorToughness);
            nbt.resolveOrCreateCompound("SkillTree.SkillTreeNodes");
            for(int n = 0; n < 15; n++) nbt.resolveOrCreateCompound("SkillTree.SkillTreeNodes").setBoolean("Node" + n, false);
            nbt.resolveOrCreateCompound("SkillTree").setInteger("SkillPoints", tier > 50 ? 10 : tier / 5);
            if(item.getType().equals(Material.PLAYER_HEAD)) nbt.setUUID("UUID", UUID.randomUUID());
            nbt.setEnum("ArmorID", armorID);
        });
    }

    protected void updateNBT() {
        NBT.modify(item, nbt -> {
            nbt.setEnum("Rarity", rarity);
            nbt.setInteger("Tier", tier);
            nbt.setInteger("Armor", armor);
            nbt.setInteger("ArmorToughness", armorToughness);
            nbt.resolveOrCreateCompound("SkillTree").setInteger("SkillPoints", tier > 50 ? 10 : tier / 5);
        });
    }

    public void openSkillTree(HumanEntity player) {
    }

    public void createItemFromNBT() {
    }
}