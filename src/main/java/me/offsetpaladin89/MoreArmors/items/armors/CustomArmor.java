package me.offsetpaladin89.MoreArmors.items.armors;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.utils.skills.BaseSkillTree;
import me.offsetpaladin89.MoreArmors.utils.skills.SkillTree;
import me.offsetpaladin89.MoreArmors.utils.skills.SkillTreeNode;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.items.misc.CustomItem;
import me.offsetpaladin89.MoreArmors.utils.stats.ArmorStats;
import me.offsetpaladin89.MoreArmors.utils.stats.Stats;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.UUID;

public class CustomArmor extends CustomItem {

    protected SlotType slot;
    protected ArmorType armorID;
    protected ArmorStats armorStats;
    protected Stats setStats;

    public CustomArmor() {
        setStats = new Stats();
    }

    public CustomArmor(ItemStack item) {
        this.item = item;
        createItemFromNBT();
    }

    CustomArmor(SlotType slot) {
        this.slot = slot;
        item = getBaseItem();
        rarity = Rarity.getRarity(getRarity(), tier);
        displayName = getFormattedName(getDefaultName());

        updateItem(true);
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public void increaseTier(int n) {
        this.tier += n;
    }

    public int getTier() {
        return tier;
    }

    public ArmorType getType() {
        return armorID;
    }

    public Stats getSetStats() {
        return setStats;
    }


    // Armor Creation

    public void updateItem(boolean resetPersistent) {
        armorID = getArmorID();
        rarity = Rarity.getRarity(getRarity(), tier);

        setDisplayName();
        setTextures();
        setLore();
        setArmorStats();

        setFlags();

        armorStats.setStats(item, armorID, slot);

        baseNBT(resetPersistent);
        armorNBT();
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

    protected void baseNBT(boolean resetPersistent) {
        NBT.modify(item, nbt -> {
            nbt.setEnum("Rarity", rarity);
            nbt.setInteger("Tier", tier);
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
            tier = nbt.getInteger("Tier");
            armorID = nbt.getEnum("ArmorID", ArmorType.class);
        });

        getSpecialValues();

        rarity = Rarity.getRarity(getRarity(), tier);
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
        skillTree.getBaseSkillTree(tier, new SkillTree(item).availableSkillPoints()).show(p);
    }

    // Overridden Methods

    protected void armorNBT() {
    }
    protected ArmorType getArmorID() { return ArmorType.INVALID; }

    protected ItemStack getBaseItem() {
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

    protected void setArmorStats() {
    }
    protected void setTextures() {
    }
}