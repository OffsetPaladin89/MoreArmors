package me.offsetpaladin89.MoreArmors.handlers;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.SlotType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Collections;

public class CraftHandler implements Listener {

	private final MoreArmorsMain plugin;

	public CraftHandler(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void preCraftEvent(PrepareItemCraftEvent e) {
		CraftingInventory inv = e.getInventory();
		ItemStack result = inv.getResult();
		if (!plugin.isAirOrNull(result)) {
			NBTItem nbtResult = new NBTItem(result);
			String rCustomID = nbtResult.getString("CustomItemID");
			ItemStack[] cm = inv.getMatrix();
			for (int x = 0; x < cm.length; x++) {
				ItemStack i = cm[x];
				if (!plugin.isAirOrNull(i)) {
					NBTItem nbtItem = new NBTItem(i);
					String iCustomID = nbtItem.getString("CustomItemID");

					// Although checking for isEmpty() is not the most efficient method,
					// it makes the code much easier to understand.

					// Armors
					// Speedster Armor
					if (rCustomID.equals("speedster")) {
						if (!iCustomID.equals("compacted_sugar_cane")) inv.setResult(null);
					}
					// Miner Armor
					if (rCustomID.equals("miner")) {
						if (iCustomID.equals("compacted_cobblestone") && i.getAmount() < 32) inv.setResult(null);
						if (iCustomID.isEmpty()) inv.setResult(null);
					}
					// Nether Armor
					if (rCustomID.equals("nether")) {
						if (iCustomID.equals("compacted_soul_sand") && i.getAmount() < 16) inv.setResult(null);
						if (i.getType().equals(Material.PLAYER_HEAD) && !iCustomID.equals("nether_crown")) inv.setResult(null);
						if (!i.getType().equals(Material.NETHER_STAR) && iCustomID.isEmpty()) inv.setResult(null);
					}
					// End Armor
					if (rCustomID.equals("end")) {
						if (iCustomID.equals("compacted_end_stone") && i.getAmount() < 32) inv.setResult(null);
						if (iCustomID.equals("compacted_eye_of_ender") && i.getAmount() < 16) inv.setResult(null);
						if (!i.getType().equals(Material.DRAGON_HEAD) && iCustomID.isEmpty()) inv.setResult(null);
					}
					// Sea Greed Armor
					if (rCustomID.equals("seagreed")) {
						if (iCustomID.equals("compacted_prismarine") && i.getAmount() < 16) inv.setResult(null);
						if (iCustomID.equals("compacted_diamond_block") && i.getAmount() < 4) inv.setResult(null);
						if (iCustomID.equals("compacted_gold_block") && i.getAmount() < 4) inv.setResult(null);
						if (!i.getType().equals(Material.HEART_OF_THE_SEA) && iCustomID.isEmpty()) inv.setResult(null);
					}
					//  Destroyer Armor
					if (rCustomID.equals("destroyer")) {
						if (iCustomID.equals("compacted_iron_block") && i.getAmount() < 8) inv.setResult(null);
						if (iCustomID.equals("machine_part") && i.getAmount() < 4) inv.setResult(null);
						if (iCustomID.equals("machine_core") && x % 3 != 1) inv.setResult(null);
						if (iCustomID.isEmpty()) inv.setResult(null);
					}
					// Materials
					// Nether Crown
					if (rCustomID.equals("nether_crown")) {
						if (iCustomID.equals("compacted_blaze_rod") && i.getAmount() < 8) inv.setResult(null);
						if (i.getType().equals(Material.BLAZE_ROD) && iCustomID.isEmpty()) inv.setResult(null);
					}
					// Compacted Diamond
					if (rCustomID.equals("compacted_diamond")) {
						if (i.getAmount() < 4) inv.setResult(null);
					}
					// Compacted Diamond Block
					if (rCustomID.equals("compacted_diamond_block")) {
						if (iCustomID.equals("compacted_diamond") && i.getAmount() < 4) inv.setResult(null);
						if (iCustomID.isEmpty()) inv.setResult(null);
						if (isBlockRecipe(cm)) inv.setResult(new ItemStack(Material.DIAMOND_BLOCK, 1));
					}
					// Compacted Gold
					if (rCustomID.equals("compacted_gold")) {
						if (i.getAmount() < 4) inv.setResult(null);
					}
					// Compacted Gold Block
					if (rCustomID.equals("compacted_gold_block")) {
						if (iCustomID.equals("compacted_gold") && i.getAmount() < 4) inv.setResult(null);
						else if (iCustomID.isEmpty()) inv.setResult(null);
						if (isBlockRecipe(cm)) inv.setResult(new ItemStack(Material.GOLD_BLOCK, 1));
					}
					// Compacted Redstone
					if (rCustomID.equals("compacted_redstone")) {
						if (i.getAmount() < 4) inv.setResult(null);
					}
					// Compacted Iron
					if (rCustomID.equals("compacted_iron")) {
						if (i.getAmount() < 4) inv.setResult(null);
					}
					// Compacted Iron Block
					if (rCustomID.equals("compacted_iron_block")) {
						if (iCustomID.equals("compacted_iron") && i.getAmount() < 4) inv.setResult(null);
						else if (iCustomID.isEmpty()) inv.setResult(null);
						if (isBlockRecipe(cm)) inv.setResult(new ItemStack(Material.IRON_BLOCK, 1));

					}
					// Star Dust
					if (rCustomID.equals("star_dust")) {
						if (iCustomID.equals("compacted_iron") && i.getAmount() < 4) inv.setResult(null);
						if (i.getType().equals(Material.IRON_INGOT) && iCustomID.isEmpty()) inv.setResult(null);
					}
					//  Machine Part
					if (rCustomID.equals("machine_part")) {
						if (iCustomID.equals("compacted_iron") && i.getAmount() < 4) inv.setResult(null);
						if (iCustomID.equals("compacted_redstone") && i.getAmount() < 4) inv.setResult(null);
						if (iCustomID.isEmpty()) inv.setResult(null);
					}
					// Energy Cell
					if (rCustomID.equals("energy_cell")) {
						if (iCustomID.equals("compacted_iron") && i.getAmount() < 4) inv.setResult(null);
						if (iCustomID.isEmpty()) inv.setResult(null);
					}
					// Machine Core
					if (rCustomID.equals("machine_core")) {
						// Energy Cell must be in the middle crafting slot
						if (iCustomID.equals("energy_cell") && x != 4) inv.setResult(null);
						if (iCustomID.equals("machine_part") && i.getAmount() < 4) inv.setResult(null);
						if (iCustomID.equals("compacted_iron_block") && i.getAmount() < 8) inv.setResult(null);
						if (iCustomID.isEmpty()) inv.setResult(null);
					}
					// Custom items cannot craft themselves
					if (nbtResult.getBoolean("IsCustomItem") && nbtItem.getBoolean("IsCustomItem")) {
						if (nbtResult.getString("CustomItemID").equals(nbtItem.getString("CustomItemID"))) inv.setResult(null);
					}
					// If a custom item and a vanilla item have the same recipe,
					// cancel the recipe if custom items are in used in a vanilla recipe
					if (!nbtResult.getBoolean("IsCustomItem") && nbtItem.getBoolean("IsCustomItem")) inv.setResult(null);
				}
			}
		}
	}

	private boolean isBlockRecipe(ItemStack[] cm) {
		int y = 0;
		for(int x = 0; x < cm.length; x++) {
			ItemStack i = cm[x];
			NBTItem nbtItem = new NBTItem(i);
			if(!nbtItem.getBoolean("IsCustomItem")) y++;
		}
		return y == 9;
	}

	@EventHandler
	public void craftEvent(CraftItemEvent e) {
		Player p = (Player) e.getWhoClicked();
		CraftingInventory ci = e.getInventory();

		ItemStack result = ci.getResult();

		if (!plugin.isAirOrNull(result)) {
			NBTItem nbtResult = new NBTItem(result);
			String rCustomID = nbtResult.getString("CustomItemID");
			if (e.isShiftClick()) {
				ArrayList<Integer> slotValues = new ArrayList<>();
				int leastValue = 0;
				for (ItemStack i : ci.getMatrix()) {
					if (!plugin.isAirOrNull(i)) {
						NBTItem nbtItem = new NBTItem(i);
						String iCustomID = nbtItem.getString("CustomItemID");
						// Armors
						// Miner Armor
						if (rCustomID.equals("miner")) {
							if (iCustomID.equals("compacted_cobblestone")) slotValues.add(i.getAmount() / 32);
						}
						// Nether Armor
						if (rCustomID.equals("nether")) {
							if (iCustomID.equals("compacted_soul_sand")) slotValues.add(i.getAmount() / 16);
							if (iCustomID.equals("nether_crown")) slotValues.add(i.getAmount());
							if (i.getType().equals(Material.NETHER_STAR)) slotValues.add(i.getAmount());
						}
						// End Armor
						if (rCustomID.equals("end")) {
							if (iCustomID.equals("compacted_end_stone")) slotValues.add(i.getAmount() / 32);
							if (iCustomID.equals("compacted_eye_of_ender")) slotValues.add(i.getAmount() / 16);
							if (i.getType().equals(Material.DRAGON_HEAD)) slotValues.add(i.getAmount());
						}
						// Sea Greed Armor
						if (rCustomID.equals("seagreed")) {
							if (iCustomID.equals("compacted_prismarine")) slotValues.add(i.getAmount() / 16);
							if (iCustomID.equals("compacted_diamond_block")) slotValues.add(i.getAmount() / 4);
							if (iCustomID.equals("compacted_gold_block")) slotValues.add(i.getAmount() / 4);
							if(i.getType().equals(Material.HEART_OF_THE_SEA)) slotValues.add(i.getAmount());
						}
						// Destroyer Armor
						if (rCustomID.equals("destroyer")) {
							switch (iCustomID) {
								case "compacted_iron_block" -> slotValues.add(i.getAmount() / 8);
								case "machine_part" -> slotValues.add(i.getAmount() / 4);
								case "machine_core" -> slotValues.add(i.getAmount());
							}
						}
						// Materials
						// Nether Crown
						if (rCustomID.equals("nether_crown")) {
							if (iCustomID.equals("compacted_blaze_rod")) slotValues.add(i.getAmount() / 8);
							if (i.getType().equals(Material.NETHER_STAR)) slotValues.add(i.getAmount());
						}
						// Compacted Diamond
						if (rCustomID.equals("compacted_diamond")) slotValues.add(i.getAmount() / 4);
						// Compacted Diamond Block
						if (rCustomID.equals("compacted_diamond_block")) {
							if (iCustomID.equals("compacted_diamond")) slotValues.add(i.getAmount() / 4);
						}
						// Compacted Gold
						if (rCustomID.equals("compacted_gold")) slotValues.add(i.getAmount() / 4);
						// Compacted Gold Block
						if (rCustomID.equals("compacted_gold_block")) {
							if (iCustomID.equals("compacted_gold")) slotValues.add(i.getAmount() / 4);
						}
						// Compacted Redstone
						if (rCustomID.equals("compacted_redstone")) slotValues.add(i.getAmount() / 4);
						// Compacted Iron
						if (rCustomID.equals("compacted_iron")) slotValues.add(i.getAmount() / 4);
						// Compacted Iron Block
						if (rCustomID.equals("compacted_iron_block")) {
							if (iCustomID.equals("compacted_iron")) slotValues.add(i.getAmount() / 4);
						}
						// Star Dust
						if (rCustomID.equals("star_dust")) {
							if (iCustomID.equals("compacted_iron")) slotValues.add(i.getAmount() / 4);
							if (i.getType().equals(Material.NETHER_STAR)) slotValues.add(i.getAmount());
						}
						// Machine Part
						if (rCustomID.equals("machine_part")) {
							if (iCustomID.equals("compacted_iron")) slotValues.add(i.getAmount() / 4);
							if (iCustomID.equals("compacted_redstone")) slotValues.add(i.getAmount() / 4);
						}
						// Energy Cell
						if (rCustomID.equals("energy_cell")) {
							if (iCustomID.equals("compacted_iron")) slotValues.add(i.getAmount() / 4);
							if (iCustomID.equals("star_dust")) slotValues.add(i.getAmount());
						}
						// Machine Core
						if (rCustomID.equals("machine_core")) {
							if (iCustomID.equals("compacted_iron_block")) slotValues.add(i.getAmount() / 8);
							if (iCustomID.equals("machine_part")) slotValues.add(i.getAmount() / 4);
							if (iCustomID.equals("energy_cell")) slotValues.add(i.getAmount());
						}
					}
				}
				Collections.sort(slotValues);
				if (!slotValues.isEmpty()) leastValue = slotValues.get(0);
				if (leastValue > 0) {
					ci.setResult(null);
					for (ItemStack i : ci.getMatrix()) {
						if (!plugin.isAirOrNull(i)) {
							NBTItem nbtItem = new NBTItem(i);
							String iCustomID = nbtItem.getString("CustomItemID");
							// Armors
							// Miner Armor
							if (rCustomID.equals("miner")) {
								if (iCustomID.equals("compacted_cobblestone")) i.setAmount(i.getAmount() - 32 * leastValue);
							}
							// Nether Armor
							if (rCustomID.equals("nether")) {
								if (iCustomID.equals("compacted_soul_sand")) i.setAmount(i.getAmount() - 16 * leastValue);
								else i.setAmount(i.getAmount() - leastValue);
							}
							// End Armor
							if (rCustomID.equals("end")) {
								if (iCustomID.equals("compacted_end_stone")) i.setAmount(i.getAmount() - 32 * leastValue);
								else if (iCustomID.equals("compacted_eye_of_ender")) i.setAmount(i.getAmount() - 16 * leastValue);
								else i.setAmount(i.getAmount() - leastValue);
							}
							// Sea Greed Armor
							if (rCustomID.equals("seagreed")) {
								switch (iCustomID) {
									case "compacted_prismarine" -> i.setAmount(i.getAmount() - 16 * leastValue);
									case "compacted_diamond_block", "compacted_gold_block" -> i.setAmount(i.getAmount() - 4 * leastValue);
									default -> i.setAmount(i.getAmount() - leastValue);
								}
							}
							// Destroyer Armor
							if (rCustomID.equals("destroyer")) {
								switch (iCustomID) {
									case "compacted_iron_block" -> i.setAmount(i.getAmount() - 8 * leastValue);
									case "machine_part" -> i.setAmount(i.getAmount() - 4 * leastValue);
									default -> i.setAmount(i.getAmount() - leastValue);
								}
							}
							// Materials
							// Nether Crown
							if (rCustomID.equals("nether_crown")) {
								if (iCustomID.equals("compacted_blaze_rod")) i.setAmount(i.getAmount() - 8 * leastValue);
								else i.setAmount(i.getAmount() - leastValue);
							}
							// Compacted Diamond
							if (rCustomID.equals("compacted_diamond")) i.setAmount(i.getAmount() - 4 * leastValue);
							// Compacted Diamond Block
							if (rCustomID.equals("compacted_diamond_block")) {
								if (iCustomID.equals("compacted_diamond")) i.setAmount(i.getAmount() - 4 * leastValue);
							}
							// Compacted Gold
							if (rCustomID.equals("compacted_gold")) i.setAmount(i.getAmount() - 4 * leastValue);
							// Compacted Gold Block
							if (rCustomID.equals("compacted_gold_block")) {
								if (iCustomID.equals("compacted_gold")) i.setAmount(i.getAmount() - 4 * leastValue);
							}
							// Compacted Redstone
							if (rCustomID.equals("compacted_redstone")) i.setAmount(i.getAmount() - 4 * leastValue);
							// Compacted Iron
							if (rCustomID.equals("compacted_iron")) i.setAmount(i.getAmount() - 4 * leastValue);
							// Compacted Iron Block
							if (rCustomID.equals("compacted_iron_block")) {
								if (iCustomID.equals("compacted_iron")) i.setAmount(i.getAmount() - 4 * leastValue);
							}
							// Star Dust
							if (rCustomID.equals("star_dust")) {
								if (iCustomID.equals("compacted_iron")) i.setAmount(i.getAmount() - 4 * leastValue);
								else i.setAmount(i.getAmount() - leastValue);
							}
							// Machine Part
							if (rCustomID.equals("machine_part")) {
								if (iCustomID.equals("compacted_iron")) i.setAmount(i.getAmount() - 4 * leastValue);
								else if (iCustomID.equals("compacted_redstone")) i.setAmount(i.getAmount() - 4 * leastValue);
							}
							// Energy Cell
							if (rCustomID.equals("energy_cell")) {
								if (iCustomID.equals("compacted_iron")) i.setAmount(i.getAmount() - 4 * leastValue);
								else if (iCustomID.equals("star_dust")) i.setAmount(i.getAmount() - leastValue);
							}
							// Machine Core
							if (rCustomID.equals("machine_core")) {
								switch (iCustomID) {
									case "compacted_iron_block" -> i.setAmount(i.getAmount() - 8 * leastValue);
									case "machine_part", "energy_cell" -> i.setAmount(i.getAmount() - 4 * leastValue);
								}
							}
						}
					}
					if (rCustomID.equals("compacted_diamond")) craftItem(p, plugin.materials.CompactedDiamond(leastValue));
					if (rCustomID.equals("compacted_diamond_block")) craftItem(p, plugin.materials.CompactedDiamondBlock(leastValue));
					if (rCustomID.equals("compacted_gold")) craftItem(p, plugin.materials.CompactedGold(leastValue));
					if (rCustomID.equals("compacted_gold_block")) craftItem(p, plugin.materials.CompactedGoldBlock(leastValue));
					if (rCustomID.equals("compacted_redstone")) craftItem(p, plugin.materials.CompactedRedstone(leastValue));
					if (rCustomID.equals("compacted_iron")) craftItem(p, plugin.materials.CompactedIron(leastValue));
					if (rCustomID.equals("compacted_iron_block")) craftItem(p, plugin.materials.CompactedIronBlock(leastValue));
					if (rCustomID.equals("star_dust")) craftItem(p, plugin.materials.StarDust(leastValue * 8));
					if (rCustomID.equals("machine_part")) craftItem(p, plugin.materials.MachinePart(leastValue));
					for (int x = 0; x < leastValue; x++) {
						if (nbtResult.getString("CustomItemType").equals("armor")) {
//							EquipmentSlot slotType = SlotType.matchSlot(SlotType.matchType(result));
//							if (rCustomID.equals("miner")) craftItem(p, plugin.armorSets.MinerArmor(slotType));
//							if (rCustomID.equals("nether")) craftItem(p, plugin.armorSets.NetherArmor(slotType));
//							if (rCustomID.equals("end")) craftItem(p, plugin.armorSets.EndArmor(slotType));
//							if (rCustomID.equals("seagreed")) craftItem(p, plugin.armorSets.SeaGreedArmor(slotType));
//							if (rCustomID.equals("destroyer")) craftItem(p, plugin.armorSets.DestroyerArmor(slotType, 0));
						}
						if (rCustomID.equals("nether_crown")) craftItem(p, plugin.materials.NetherCrown());
						if (rCustomID.equals("energy_cell")) craftItem(p, plugin.materials.EnergyCell());
						if (rCustomID.equals("machine_core")) craftItem(p, plugin.materials.MachineCore());
					}
				}
			} else {
				for (ItemStack i : ci.getMatrix()) {
					if (!plugin.isAirOrNull(i)) {
						NBTItem nbtItem = new NBTItem(i);
						String iCustomID = nbtItem.getString("CustomItemID");
						// Armors
						// Miner Armor
						if (rCustomID.equals("miner")) {
							if (iCustomID.equals("compacted_cobblestone")) i.setAmount(i.getAmount() - 31);
						}
						// Nether Armor
						if (rCustomID.equals("nether")) {
							if (iCustomID.equals("compacted_soul_sand")) i.setAmount(i.getAmount() - 15);
						}
						// End Armor
						if (rCustomID.equals("end")) {
							if (iCustomID.equals("compacted_end_stone")) i.setAmount(i.getAmount() - 31);
							else if (iCustomID.equals("compacted_eye_of_ender")) i.setAmount(i.getAmount() - 15);
						}
						// Sea Greed Armor
						if (rCustomID.equals("seagreed")) {
							switch (iCustomID) {
								case "compacted_prismarine" -> i.setAmount(i.getAmount() - 15);
								case "compacted_diamond_block", "compacted_gold_block" -> i.setAmount(i.getAmount() - 3);
							}
						}
						// Destroyer Armor
						if (rCustomID.equals("destroyer")) {
							if (iCustomID.equals("compacted_iron_block")) i.setAmount(i.getAmount() - 7);
							else if (iCustomID.equals("machine_part")) i.setAmount(i.getAmount() - 3);
						}
						// Materials
						// Nether Crown
						if (rCustomID.equals("nether_crown")) {
							if (iCustomID.equals("compacted_blaze_rod")) i.setAmount(i.getAmount() - 7);
						}
						// Compacted Diamond
						if (rCustomID.equals("compacted_diamond")) i.setAmount(i.getAmount() - 3);
						// Compacted Diamond Block
						if (rCustomID.equals("compacted_diamond_block")) {
							if (iCustomID.equals("compacted_diamond")) i.setAmount(i.getAmount() - 3);
						}
						// Compacted Gold
						if (rCustomID.equals("compacted_gold")) i.setAmount(i.getAmount() - 3);
						// Compacted Gold Block
						if (rCustomID.equals("compacted_gold_block")) {
							if (iCustomID.equals("compacted_gold")) i.setAmount(i.getAmount() - 3);
						}
						// Compacted Redstone
						if (rCustomID.equals("compacted_redstone")) i.setAmount(i.getAmount() - 3);
						// Compacted Iron
						if (rCustomID.equals("compacted_iron")) i.setAmount(i.getAmount() - 3);
						// Compacted Iron Block
						if (rCustomID.equals("compacted_iron_block")) {
							if (iCustomID.equals("compacted_iron")) i.setAmount(i.getAmount() - 3);
						}
						// Star Dust
						if (rCustomID.equals("star_dust")) {
							if (iCustomID.equals("compacted_iron")) i.setAmount(i.getAmount() - 3);
						}
						// Machine Part
						if (rCustomID.equals("machine_part")) {
							if (iCustomID.equals("compacted_iron")) i.setAmount(i.getAmount() - 3);
							if (iCustomID.equals("compacted_redstone")) i.setAmount(i.getAmount() - 3);
						}
						// Energy Cell
						if (rCustomID.equals("energy_cell")) {
							if (iCustomID.equals("compacted_iron")) i.setAmount(i.getAmount() - 3);
						}
						// Machine Core
						if (rCustomID.equals("machine_core")) {
							if (iCustomID.equals("compacted_iron_block")) i.setAmount(i.getAmount() - 7);
							else if (iCustomID.equals("machine_part")) i.setAmount(i.getAmount() - 3);
						}
					}
				}
			}
		}
	}

	private void craftItem(Player p, ItemStack i) {
		PlayerInventory inv = p.getInventory();
		if (inv.firstEmpty() == -1) p.getWorld().dropItem(p.getLocation().add(0.0D, 0.5D, 0.0D), i);
		else inv.addItem(i);
	}
}