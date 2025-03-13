package me.offsetpaladin89.MoreArmors.utils.skilltree;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.enums.ItemType;
import me.offsetpaladin89.MoreArmors.items.armors.CustomArmor;
import me.offsetpaladin89.MoreArmors.items.misc.TierUpgraderItem;
import me.offsetpaladin89.MoreArmors.utils.Lore;
import me.offsetpaladin89.MoreArmors.utils.Util;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.offsetpaladin89.MoreArmors.enums.ArmorType.armorFromItem;
import static me.offsetpaladin89.MoreArmors.handlers.InventoryHandler.getEmptyPane;
import static me.offsetpaladin89.MoreArmors.handlers.InventoryHandler.getExitItem;
import static me.offsetpaladin89.MoreArmors.utils.Util.colorString;
import static me.offsetpaladin89.MoreArmors.utils.Util.sendColoredMessage;

public class BaseSkillTree {

    protected ArrayList<GuiItem> nodes;
    public BaseSkillTree(ArrayList<GuiItem> nodes) {
        this.nodes = nodes;
    }

    public ChestGui getBaseSkillTree(int tier, int skillPoints) {
        ChestGui gui = new ChestGui(6, colorString("&8Skill Tree"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        StaticPane emptyPanes = new StaticPane(0, 0, 9, 6);
        emptyPanes.setPriority(Pane.Priority.LOWEST);
        emptyPanes.fillWith(getEmptyPane());
        emptyPanes.addItem(getExitItem(), 4, 5);
        emptyPanes.addItem(upgradeItemItem(tier, skillPoints), 0, 0);

        int usedSkillPoints = tier / 5 - skillPoints;

        if(usedSkillPoints != 0) emptyPanes.addItem(resetSkillTree(usedSkillPoints), 8, 0);

        Pattern pattern = new Pattern("00a00", "bcdef", "g000h", "i0j0k", "lm0no");

        PatternPane skillTreeNodes = new PatternPane(2, 0, 5, 5, pattern);

        skillTreeNodes.bindItem('a', nodes.getFirst());
        skillTreeNodes.bindItem('b', nodes.get(1));
        skillTreeNodes.bindItem('c', nodes.get(2));
        skillTreeNodes.bindItem('d', nodes.get(3));
        skillTreeNodes.bindItem('e', nodes.get(4));
        skillTreeNodes.bindItem('f', nodes.get(5));
        skillTreeNodes.bindItem('g', nodes.get(6));
        skillTreeNodes.bindItem('h', nodes.get(7));
        skillTreeNodes.bindItem('i', nodes.get(8));
        skillTreeNodes.bindItem('j', nodes.get(9));
        skillTreeNodes.bindItem('k', nodes.get(10));
        skillTreeNodes.bindItem('l', nodes.get(11));
        skillTreeNodes.bindItem('m', nodes.get(12));
        skillTreeNodes.bindItem('n', nodes.get(13));
        skillTreeNodes.bindItem('o', nodes.get(14));

        gui.addPane(emptyPanes);
        gui.addPane(skillTreeNodes);

        return gui;
    }

    private GuiItem resetSkillTree(int usedSkillPoints) {
        ItemStack item = new ItemStack(Material.ANVIL);
        ItemMeta itemMeta = item.getItemMeta();
        Lore lore = new Lore();

        itemMeta.setDisplayName(colorString("&cReset Skill Tree"));
        lore.addColoredLine(String.format("&7Click to reset for &a%d Skill Point%s&7.", usedSkillPoints, usedSkillPoints == 1 ? "" : "s"));

        itemMeta.setLore(lore.getLore());
        item.setItemMeta(itemMeta);

        return new GuiItem(item, event -> {
            Player p = (Player) event.getWhoClicked();
            ItemStack hand = p.getInventory().getItemInMainHand();
            if(Util.isAirOrNull(hand)) return;

            CustomArmor armor = armorFromItem(hand);
            if(armor == null) return;

            armor.updateItem(true);

            sendColoredMessage(p, String.format("&aThe skill tree of %s &ahas successfully been reset!", armor.getItem().getItemMeta().getDisplayName()));

            armor.openSkillTree(p);
        });
    }

    private GuiItem upgradeItemItem(int tier, int skillPoints) {
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta itemMeta = item.getItemMeta();
        Lore lore = new Lore();

        String tierUpgraderName = new TierUpgraderItem().getItem().getItemMeta().getDisplayName();

        itemMeta.setDisplayName(colorString(String.format("&aUpgrade Item Tier - &bTier %d", tier)));
        lore.addColoredLine(String.format("&7Consume %ss &7to upgrade", tierUpgraderName));
        lore.addColoredLine("&7this item's tier. Gain &a+1 Skill Point");
        lore.addColoredLine("&7for every 5 item tiers.");
        lore.addColoredLine("&8Max Tier 50");
        if(skillPoints > 0) {
            lore.addEmpty();
            lore.addColoredLine(String.format("&eYou have %d Skill Point%s Remaining", skillPoints, skillPoints == 1 ? "" : "s"));
        }
        if(tier < 50) {
            lore.addEmpty();
            lore.addColoredLine("&6Left Click &7to increase Tier by &a1&7.");
            lore.addColoredLine("&6Shift Left Click &7to increase Tier by &a10&7.");
        }

        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);

        return new GuiItem(item, this::upgradeItem);
    }

    private void upgradeItem(InventoryClickEvent event) {
        ItemStack hand = event.getWhoClicked().getInventory().getItemInMainHand();
        if(Util.isAirOrNull(hand)) return;

        CustomArmor armor = armorFromItem(hand);
        if(armor == null) return;

        int tier = armor.getTier();

        if(tier >= 50) return;

        Player p = (Player) event.getWhoClicked();

        for (ItemStack i : event.getWhoClicked().getInventory().getContents()) {
            if(Util.isAirOrNull(i)) continue;
            ItemType itemID = NBT.get(i, nbt -> (ItemType) nbt.getEnum("ItemID", ItemType.class));
            if(itemID == null || !itemID.equals(ItemType.TIER_UPGRADER)) continue;

            if(event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
                int n = Math.min(i.getAmount(), 10);
                if(tier + n > 50) n = 50 - tier;

                increaseTier(n, armor, i, p);
                return;
            }
            else if(event.getAction().equals(InventoryAction.PICKUP_ALL)) {
                increaseTier(1, armor, i, p);
                return;
            }
        }
    }

    private void increaseTier(int n, CustomArmor armor, ItemStack i, Player p) {
        armor.increaseTier(n);
        i.setAmount(i.getAmount() - n);
        upgradeMessage(p, armor);
        armor.updateItem(false);
        armor.openSkillTree(p);
    }

    private void upgradeMessage(HumanEntity p, CustomArmor armor) {
        sendColoredMessage(p, String.format("&aSuccessfully upgraded your %s &ato &b(+%d)&a.", armor.getItem().getItemMeta().getDisplayName(), armor.getTier()));
    }
}
