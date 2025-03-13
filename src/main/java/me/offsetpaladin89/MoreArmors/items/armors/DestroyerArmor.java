package me.offsetpaladin89.MoreArmors.items.armors;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.BaseSkillTree;
import me.offsetpaladin89.MoreArmors.SkillTreeNode;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import me.offsetpaladin89.MoreArmors.utils.Lore;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.offsetpaladin89.MoreArmors.enums.SlotType.HELMET;

public class DestroyerArmor extends CustomArmor {

    private int killCount = 0;
    private int damageBonus = 0;

    private static final Color LEATHER_COLOR = Color.fromRGB(228, 232, 235);
    private static final Rarity BASE_RARITY = Rarity.DIVINE;

    private static final int UPGRADE_THRESHOLD = 100;
    private static final int MAX_KILL_COUNT = 1000;

    public DestroyerArmor(ItemStack item) {
        super(item);
    }

    public DestroyerArmor(SlotType slot) {
        super(slot);
        item = getBaseItem();
        rarity = Rarity.getRarity(BASE_RARITY, tier);
        displayName = getFormattedName(getDefaultName());
        armor = getDefaultArmor();
        armorToughness = getDefaultArmorToughness();

        createItem();
        baseNBT();
    }

    private void setLore() {
        updateDamage();
        int currentStage = killCount / UPGRADE_THRESHOLD;
        int nextBonus = damageBonus + 1;
        int upgradeProgress = killCount % UPGRADE_THRESHOLD;
        String integerLimit = killCount == Integer.MAX_VALUE ? "★" : "";

        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        switch (slot) {
            case HELMET -> {
                lore.addColoredLine("&6Item Ability: Night Vision");
                lore.addColoredLine("&7Grants &5Night Vision&7.");
                lore.addEmpty();
            }
            case CHESTPLATE -> {
                lore.addColoredLine("&6Item Ability: True Shielding");
                lore.addColoredLine("&a20% &7chance to &anegate an attack&7.");
                lore.addEmpty();
            }
            case BOOTS -> {
                lore.addColoredLine("&6Item Ability: Boost");
                lore.addColoredLine("&7Launch yourself in your &afacing direction");
                lore.addColoredLine("&7and &acreate an explosion &7behind you.");
                lore.addColoredLine("&8Cooldown: &a1s");
                lore.addEmpty();
            }
        }
        lore.addColoredLine("&6Piece Upgrade: Slayer");
        lore.addColoredLine("&7Kill mobs to increase your damage.");
        if(killCount >= MAX_KILL_COUNT) {
            lore.addColoredLine("&7Current Bonus (&a10&8/&a10&7): &e+10 Damage &a&lMAXED OUT");
            lore.addColoredLine(String.format("&8%s Mobs Killed &6%s", Util.formatNumber(killCount), integerLimit));
        }
        else {
            lore.addColoredLine(String.format("&7Current Bonus (&a%d&8/&a10&7): &e+%d Damage", currentStage, damageBonus));
            lore.addColoredLine(String.format("&7Next Upgrade: &e+%d Damage &8(&a%d&7/&c100&8)", nextBonus, upgradeProgress));
            lore.addColoredLine("&8Max +10 Damage");
        }
        lore.addEmpty();
        lore.addColoredLine("&6Full Set Bonus: Warrior");
        lore.addColoredLine("&7Grants &aStrength II&7.");
        lore.addColoredLine("&7Grants &aRegeneration II&7.");
        lore.addColoredLine("&7Grants &aResistance II&7.");
        lore.addArmorRarity(rarity);
        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }

    public void updateItem() {
        displayName = getFormattedName(getDefaultName());

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);

        armorID = ArmorType.DESTROYER;
        if (slot.equals(HELMET)) assignSkull(item);
        else setLeatherColor(LEATHER_COLOR);
        setLore();

        setFlags();

        baseAttributes();
        setAttributes();

        updateNBT();
        addNBT();
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public void increaseKillCount(int killCount) {
        if(this.killCount == Integer.MAX_VALUE) return;
        this.killCount += killCount;
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
            case HELMET -> "Destroyer Helmet";
            case CHESTPLATE -> "Destroyer Chestplate";
            case LEGGINGS -> "Destroyer Leggings";
            case BOOTS -> "Destroyer Boots";
            default -> null;
        };
    }

    public void createItemFromNBT() {
        NBT.get(item, nbt -> {
            rarity = nbt.getEnum("Rarity", Rarity.class);
            armor = nbt.getInteger("Armor");
            armorToughness = nbt.getInteger("ArmorToughness");
            killCount = nbt.getInteger("KillCount");
            armorID = nbt.getEnum("ArmorID", ArmorType.class);
        });

        slot = SlotType.matchType(item);
        displayName = getFormattedName(getDefaultName());
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

    private void addNBT() {
        NBT.modify(item, nbt -> {
            nbt.setInteger("KillCount", killCount);
            nbt.setInteger("DamageBonus", damageBonus);
        });
    }

    private String getDisplayName(SkillTreeNode node) {
        return switch (node.id) {
            case 0, 2, 4, 6, 7, 11, 14 -> "&aMinor Node";
            case 1, 3, 5, 8, 10, 12, 13 -> "&6Major Node";
            case 9 -> "&5Main Node";
            default -> "&4Impossible";
        };
//        return switch (node.id) {
//            case 0 -> "&5Testing 1";
//            case 1 -> "&5Testing 2";
//            case 2 -> "&5Testing 3";
//            case 3 -> "&5Testing 4";
//            case 4 -> "&5Testing 5";
//            case 5 -> "&5Testing 6";
//            case 6 -> "&5Testing 7";
//            case 7 -> "&5Testing 8";
//            case 8 -> "&5Testing 9";
//            case 9 -> "&5Testing 10";
//            case 10 -> "&6Testing 11";
//            case 11 -> "&5Testing 12";
//            case 12 -> "&5Testing 13";
//            case 13 -> "&5Testing 14";
//            case 14 -> "&5Testing 15";
//            case 15 -> "&5Testing 16";
//            case 16 -> "&5Testing 17";
//            default -> "&4How Did You Find This?";
//        };
    }

    private Lore getDescription(SkillTreeNode node) {
        Lore lore = new Lore();
        switch (node.id) {
            case 0, 2, 4, 6, 7, 11, 14, 1, 3, 5, 8, 10, 12, 13 -> lore.addColoredLine("&eUnlocks for 1 Skill Point");
            case 9 -> lore.addColoredLine("&eUnlocks for 2 Skill Points");
            default -> lore.addColoredLine("Impossible");
        }
//        switch (node.id) {
//            case 0 -> {
//                lore.addColoredLine("&4Testing 1");
//                lore.addColoredLine("&aHello World");
//            }
//            case 1 -> {
//                lore.addColoredLine("&4Testing 2");
//                lore.addColoredLine("&aHello World");
//            }
//            case 2 -> {
//                lore.addColoredLine("&4Testing 3");
//                lore.addColoredLine("&aHello World");
//            }
//            case 3 -> {
//                lore.addColoredLine("&4Testing 4");
//                lore.addColoredLine("&aHello World");
//            }
//            case 4 -> {
//                lore.addColoredLine("&4Testing 5");
//                lore.addColoredLine("&aHello World");
//            }
//            case 5 -> {
//                lore.addColoredLine("&4Testing 6");
//                lore.addColoredLine("&aHello World");
//            }
//            case 6 -> {
//                lore.addColoredLine("&4Testing 7");
//                lore.addColoredLine("&aHello World");
//            }
//            case 7 -> {
//                lore.addColoredLine("&4Testing 8");
//                lore.addColoredLine("&aHello World");
//            }
//            case 8 -> {
//                lore.addColoredLine("&4Testing 9");
//                lore.addColoredLine("&aHello World");
//            }
//            case 9 -> {
//                lore.addColoredLine("&4Testing 10");
//                lore.addColoredLine("&aHello World");
//            }
//            case 10 -> {
//                lore.addColoredLine("&4Testing 11");
//                lore.addColoredLine("&aHello World");
//            }
//            case 11 -> {
//                lore.addColoredLine("&4Testing 12");
//                lore.addColoredLine("&aHello World");
//            }
//            case 12 -> {
//                lore.addColoredLine("&4Testing 13");
//                lore.addColoredLine("&aHello World");
//            }
//            case 13 -> {
//                lore.addColoredLine("&4Testing 14");
//                lore.addColoredLine("&aHello World");
//            }
//            case 14 -> {
//                lore.addColoredLine("&4Testing 15");
//                lore.addColoredLine("&aHello World");
//            }
//            case 15 -> {
//                lore.addColoredLine("&4Testing 16");
//                lore.addColoredLine("&aHello World");
//            }
//            case 16 -> {
//                lore.addColoredLine("&4Testing 17");
//                lore.addColoredLine("&aHello World");
//            }
//            default -> lore.addColoredLine("&5How Did You Find This?");
//        }

        return lore;
    }

    protected ArrayList<GuiItem> minorNodes() {
        ArrayList<GuiItem> nodes = new ArrayList<>();
        for(SkillTreeNode node : baseMinorNodes()) {
            node.setDescription(getDescription(node));
            node.setDisplayName(getDisplayName(node));
            nodes.add(node.getItem());
        }

        return nodes;
    }

    protected ArrayList<GuiItem> majorNodes() {
        ArrayList<GuiItem> nodes = new ArrayList<>();
        for(SkillTreeNode node : baseMajorNodes()) {
            node.setDescription(getDescription(node));
            node.setDisplayName(getDisplayName(node));
            nodes.add(node.getItem());
        }

        return nodes;
    }

    protected GuiItem mainNode() {
        SkillTreeNode node = baseMainNode();
        node.setDescription(getDescription(node));
        node.setDisplayName(getDisplayName(node));
        return node.getItem();
    }

    public void openSkillTree(HumanEntity p) {
        getAvailableSkillPoints();

        BaseSkillTree skillTree = new BaseSkillTree(minorNodes(), majorNodes(), mainNode());
        skillTree.getBaseSkillTree().show(p);
    }

    private void assignSkull(ItemStack item) {
        Util.modifySkullSkin(item, "ea0076ab9a5c0ed8ebd08bb18137321df0fdc8abc7499465cc32221ca192ad43", null);
    }

    private void updateDamage() {
        if(killCount >= 1000) damageBonus = 10;
        else damageBonus = killCount / UPGRADE_THRESHOLD;
    }
}
