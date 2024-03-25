package me.offsetpaladin89.MoreArmors.handlers;

import com.cryptomorin.xseries.XMaterial;
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
import java.util.HashMap;
import java.util.Map;

public class CraftHandler implements Listener {

	private final MoreArmorsMain plugin;

	public CraftHandler(MoreArmorsMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	private boolean validCraft(NBTItem nbtResult, NBTItem nbtItem, String itemID, int amount, Map<String, Integer> materials) {
		if (nbtResult.getString("CustomItemID").equals(itemID)) {
			for (String s : materials.keySet()) {
				if (nbtItem.getString("CustomItemID").equals(s) && amount < materials.get(s)) {
					return false;
				}
			}
		}
		return true;
	}

	private ItemStack setAmount(NBTItem nbtResult, NBTItem nbtItem, String itemID, ItemStack material, Map<String, Integer> materials) {
		if (nbtResult.getString("CustomItemID").equals(itemID)) {
			for (String s : materials.keySet()) {
				if (nbtItem.getString("CustomItemID").equals(s)) {
					material.setAmount(material.getAmount() - materials.get(s) + 1);
					return material;
				}
			}
		}
		return material;
	}

	@EventHandler
	public void PrepareItemCraft(PrepareItemCraftEvent event) {
		if (event.getInventory() != null) {
			CraftingInventory ci = event.getInventory();
			if (!plugin.isAirOrNull(ci.getResult())) {
				ItemStack result = ci.getResult();
				NBTItem nbtResult = new NBTItem(result);
				if (ci.getMatrix() != null) {
					for (ItemStack item : ci.getMatrix()) {
						if (!plugin.isAirOrNull(item)) {
							NBTItem nbtItem = new NBTItem(item);
							//Compacted Diamond
							if (nbtResult.getString("CustomItemID").equals("compacted_diamond") && !(item.getAmount() >= 4)) {
								ci.setResult(null);
							}
							//Compacted Diamond Block
							if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) {
								if (!nbtItem.getBoolean("IsCustomItem")) {
									ci.setResult(new ItemStack(Material.DIAMOND_BLOCK, 1));
									return;
								} else {
									if (!(nbtItem.getString("CustomItemID").equals("compacted_diamond") && item.getAmount() >= 4)) {
										ci.setResult(null);
									}
								}
							}
							//Compacted Gold
							if (nbtResult.getString("CustomItemID").equals("compacted_gold_ingot") && !(item.getAmount() >= 4)) {
								ci.setResult(null);
							}
							//Compacted Gold Block
							if (nbtResult.getString("CustomItemID").equals("compacted_gold_block")) {
								if (!nbtItem.getBoolean("IsCustomItem")) {
									ci.setResult(new ItemStack(Material.GOLD_BLOCK, 1));
									return;
								} else {
									if (!(nbtItem.getString("CustomItemID").equals("compacted_gold_ingot") && item.getAmount() >= 4)) {
										ci.setResult(null);
									}
								}
							}
							//End Armor
							if (nbtResult.getString("CustomItemID").equals("end")) {
								if (!(nbtItem.getString("CustomItemID").equals("compacted_end_stone") || nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender") || item.getType().equals(XMaterial.DRAGON_HEAD.parseMaterial()))) {
									ci.setResult(null);
								}
								if (nbtItem.getString("CustomItemID").equals("compacted_end_stone") && !(item.getAmount() >= 32)) {
									ci.setResult(null);
								}
								if (nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender") && !(item.getAmount() >= 16)) {
									ci.setResult(null);
								}
							}
							//Miner Armor
							if (nbtResult.getString("CustomItemID").equals("miner")) {
								if (!(nbtItem.getString("CustomItemID").equals("compacted_cobblestone") && item.getAmount() >= 32)) {
									ci.setResult(null);
								}
							}
							//Nether Crown
							Map<String, Integer> netherCrownCraft = new HashMap<>();
							netherCrownCraft.put("compacted_blaze_rod", 8);
							if (!validCraft(nbtResult, nbtItem, "nether_crown", item.getAmount(), netherCrownCraft)) {
								ci.setResult(null);
							}
							//Nether Armor
							if (nbtResult.getString("CustomItemID").equals("nether")) {
								if (!(nbtItem.getString("CustomItemID").equals("compacted_soul_sand") || nbtItem.getString("CustomItemID").equals("nether_crown") || item.getType().equals(Material.NETHER_STAR))) {
									ci.setResult(null);
								}
								if (nbtItem.getString("CustomItemID").equals("compacted_soul_sand") && !(item.getAmount() >= 16)) {
									ci.setResult(null);
								}
							}
							//Speedster Armor
							if (nbtResult.getString("CustomItemID").equals("speedster")) {
								if (!nbtItem.getString("CustomItemID").equals("compacted_sugar_cane")) {
									ci.setResult(null);
								}
							}
							Map<String, Integer> seaGreedCraft = new HashMap<>();
							seaGreedCraft.put("compacted_diamond_block", 4);
							seaGreedCraft.put("compacted_prismarine", 16);
							seaGreedCraft.put("compacted_gold_block", 4);
							if (!validCraft(nbtResult, nbtItem, "seagreed", item.getAmount(), seaGreedCraft)) {
								ci.setResult(null);
							}
//							if(TextHandler.getPlugin("MoreArmorsExtra") != null) {
//								if(nbtResult.getString("CustomItemID").equals("destroyer")) {
//									if(!(nbtItem.getString("CustomItemID").equals("compacted_iron_block") || nbtItem.getString("CustomItemID").equals("machine_core") || nbtItem.getString("CustomItemID").equals("machine_part"))) { ci.setResult(null); }
//									if(nbtItem.getString("CustomItemID").equals("compacted_iron_block") && !(item.getAmount() >= 8)) { ci.setResult(null); }
//									if(nbtItem.getString("CustomItemID").equals("machine_part") && !(item.getAmount() >= 4)) { ci.setResult(null); }
//								}
//								if(nbtResult.getString("CustomItemID").equals("compacted_iron_ingot") && !(item.getAmount() >= 4)) {
//									ci.setResult(null);
//								}
//								if(nbtResult.getString("CustomItemID").equals("compacted_iron_block")) {
//									if(!nbtItem.getBoolean("IsCustomItem")) { ci.setResult(new ItemStack(Material.IRON_BLOCK, 1)); return; }
//									else {if(!(nbtItem.getString("CustomItemID").equals("compacted_iron_ingot") && item.getAmount() >= 4)) { ci.setResult(null); }}
//								}
//								if(nbtResult.getString("CustomItemID").equals("compacted_redstone") && !(item.getAmount() >= 4)) {
//									ci.setResult(null);
//								}
//								if(nbtResult.getString("CustomItemID").equals("star_dust")) {
//									if(!((nbtItem.getString("CustomItemID").equals("compacted_iron_ingot") && item.getAmount() >= 4) || item.getType().equals(Material.NETHER_STAR))) {
//										ci.setResult(null);
//									}
//								}
//								if(nbtResult.getString("CustomItemID").equals("machine_part")) {
//									if(!((nbtItem.getString("CustomItemID").equals("compacted_iron_ingot") && item.getAmount() >= 4) || nbtItem.getString("CustomItemID").equals("compacted_redstone") && item.getAmount() >= 4)) {
//										ci.setResult(null);
//									}
//								}
//								if(nbtResult.getString("CustomItemID").equals("energy_cell")) {
//									if(!((nbtItem.getString("CustomItemID").equals("compacted_iron_ingot") && item.getAmount() >= 4) || nbtItem.getString("CustomItemID").equals("star_dust"))) {
//										ci.setResult(null);
//									}
//								}
//								if(nbtResult.getString("CustomItemID").equals("machine_core")) {
//									if(!((nbtItem.getString("CustomItemID").equals("compacted_iron_block") && item.getAmount() >= 8) || (nbtItem.getString("CustomItemID").equals("machine_part") && item.getAmount() >= 4) || nbtItem.getString("CustomItemID").equals("energy_cell"))) {
//										ci.setResult(null);
//									}
//								}
//							}
							if (nbtResult.getBoolean("IsCustomItem") && nbtItem.getBoolean("IsCustomItem")) {
								if (nbtResult.getString("CustomItemID").equals(nbtItem.getString("CustomItemID"))) {
									ci.setResult(null);
								}
							}
							if (!nbtResult.getBoolean("IsCustomItem") && nbtItem.getBoolean("IsCustomItem")) {
								ci.setResult(null);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onItemCraft(CraftItemEvent event) {
		Player player = (Player) event.getWhoClicked();
		PlayerInventory inventory = player.getInventory();
		CraftingInventory ci = event.getInventory();

		if (!plugin.isAirOrNull(ci.getResult())) {
			ItemStack result = ci.getResult();
			NBTItem nbtResult = new NBTItem(result);

			if (event.isShiftClick()) {
				ArrayList<Integer> values = new ArrayList<>();
				int leastvalue = 0;

				for (ItemStack item : ci.getMatrix()) {
					//Gets the possible craft amount of items
					if (!plugin.isAirOrNull(item)) {
						NBTItem nbtItem = new NBTItem(item);
						if (plugin.getServer().getPluginManager().getPlugin("MoreArmors") != null) {
							//End Armor
							if (nbtResult.getString("CustomItemID").equals("end")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_end_stone")) {
									values.add((int) Math.floor(item.getAmount() / 32));
								}
								if (!SlotType.matchType(result).equals(SlotType.HELMET)) {
									if (nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender")) {
										values.add((int) Math.floor(item.getAmount() / 16));
									}
								}
							}
							//Miner Armor
							if (nbtResult.getString("CustomItemID").equals("miner")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_cobblestone")) {
									values.add(item.getAmount() / 32);
								}
							}
							//Nether Armor
							if (nbtResult.getString("CustomItemID").equals("nether")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_soul_sand")) {
									values.add(item.getAmount() / 16);
								}
							}
							if(nbtResult.getString("CustomItemID").equals("seagreed")) {
								if(nbtItem.getString("CustomItemID").equals("compacted_prismarine")) {
									values.add(item.getAmount() / 16);
								}
								if(nbtItem.getString("CustomItemID").equals("compacted_gold_block")) {
									values.add(item.getAmount() / 4);
								}
								if(nbtItem.getString("CustomItemID").equals("compacted_diamond_block")) {
									values.add(item.getAmount() / 4);
								}
							}
							//True Diamond Armor
							if (nbtResult.getString("CustomItemID").equals("truediamond")) {

								boolean upgrading = false;
								boolean singularityupgrading = false;

								if (nbtResult.getInteger("ArmorLevel") == 100) {
									upgrading = true;
								}
								if (nbtResult.getInteger("DiamondSacrifice") == 100) {
									singularityupgrading = true;
								}
								if (!(upgrading || singularityupgrading)) {
									values.add(item.getAmount());
								}
							}
							//Nether Crown
							if (nbtResult.getString("CustomItemID").equals("nether_crown")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_blaze_rod")) {
									values.add(item.getAmount() / 8);
								}
							}
							//Compacted Diamond
							if (nbtResult.getString("CustomItemID").equals("compacted_diamond")) {
								values.add(item.getAmount() / 4);
							}
							//Compacted Diamond Block
							if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_diamond")) {
									values.add(item.getAmount() / 4);
								}
							}
							//Compacted Gold
							if (nbtResult.getString("CustomItemID").equals("compacted_gold_ingot")) {
								values.add(item.getAmount() / 4);
							}
							//Compacted Gold Block
							if (nbtResult.getString("CustomItemID").equals("compacted_gold_block")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_gold_ingot")) {
									values.add(item.getAmount() / 4);
								}
							}
							//Diamond Singularity
							if (nbtResult.getString("CustomItemID").equals("diamond_singularity")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_diamond")) {
									values.add(item.getAmount() / 32);
								}
								if (nbtItem.getString("CustomItemID").equals("compacted_diamond_block")) {
									values.add(item.getAmount() / 4);
								}
							}
						}
						if (plugin.getServer().getPluginManager().getPlugin("MoreArmorsExtra") != null) {
							if (nbtResult.getString("CustomItemID").equals("destroyer")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_iron_block")) {
									values.add(item.getAmount() / 8);
								}
								if (nbtItem.getString("CustomItemID").equals("machine_part")) {
									values.add(item.getAmount() / 4);
								}
								if (nbtItem.getString("CustomItemID").equals("machine_core")) {
									values.add(item.getAmount());
								}
							}
							if (nbtResult.getString("CustomItemID").equals("compacted_iron_ingot")) {
								values.add(item.getAmount() / 4);
							}
							if (nbtResult.getString("CustomItemID").equals("compacted_iron_block")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) {
									values.add(item.getAmount() / 4);
								}
							}
							if (nbtResult.getString("CustomItemID").equals("compacted_redstone")) {
								values.add(item.getAmount() / 4);
							}
							if (nbtResult.getString("CustomItemID").equals("machine_part")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) {
									values.add(item.getAmount() / 4);
								}
								if (nbtItem.getString("CustomItemID").equals("compacted_redstone")) {
									values.add(item.getAmount() / 4);
								}
							}
							if (nbtResult.getString("CustomItemID").equals("star_dust")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) {
									values.add(item.getAmount() / 4);
								}
								if (item.getType().equals(Material.NETHER_STAR)) {
									values.add(item.getAmount());
								}
							}
							if (nbtResult.getString("CustomItemID").equals("energy_cell")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) {
									values.add(item.getAmount() / 4);
								}
								if (nbtItem.getString("CustomItemID").equals("star_dust")) {
									values.add(item.getAmount());
								}
							}
							if (nbtResult.getString("CustomItemID").equals("machine_core")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_iron_block")) {
									values.add(item.getAmount() / 8);
								}
								if (nbtItem.getString("CustomItemID").equals("machine_part")) {
									values.add(item.getAmount() / 4);
								}
								if (nbtItem.getString("CustomItemID").equals("energy_cell")) {
									values.add(item.getAmount());
								}
							}
						}
					}
				}
				Collections.sort(values);
				if (!values.isEmpty()) {
					leastvalue = values.get(0);
				} else {

				}
				//Gets how many empty slots there are in the player's inventory
				int empty = 0;
				for (int x = 0; x < 36; x++) {
					ItemStack item = inventory.getItem(x);
					if (plugin.isAirOrNull(item)) {
						empty++;
					}
				}
				//Checks if there is enough space in the player's inventory to add items
				if (leastvalue > empty) {
					if (nbtResult.getString("CustomItemID").equals("nether_crown") || nbtResult.getString("CustomItemID").equals("end") || nbtResult.getString("CustomItemID").equals("miner") || nbtResult.getString("CustomItemID").equals("nether")) {
						leastvalue = empty;
					}
					if (nbtResult.getString("CustomItemID").equals("compacted_cobblestone") || nbtResult.getString("CustomItemID").equals("compacted_sugar_cane") || nbtResult.getString("CustomItemID").equals("compacted_eye_of_ender") || nbtResult.getString("CustomItemID").equals("compacted_end_stone") || nbtResult.getString("CustomItemID").equals("compacted_soul_sand") || nbtResult.getString("CustomItemID").equals("compacted_blaze_rod")) {
						leastvalue = empty * 64;
					}
//					if(TextHandler.getPlugin("MoreArmorsExtra") != null) {
//						if(nbtResult.getString("CustomItemID").equals("machine_core") || nbtResult.getString("CustomItemID").equals("destroyer")) {
//							leastvalue = empty;
//						}
//						if(nbtResult.getString("CustomItemID").equals("compacted_iron_ingot") || nbtResult.getString("CustomItemID").equals("compacted_iron_block") || nbtResult.getString("CustomItemID").equals("compacted_redstone") || nbtResult.getString("CustomItemID").equals("machine_part") || nbtResult.getString("CustomItemID").equals("energy_cell")) {leastvalue = empty * 64; }
//					}
				}
				if (empty == 0) {
					event.setCancelled(true);
					return;
				}
				if (leastvalue > 0) {
					ci.setResult(null);
					for (ItemStack item : ci.getMatrix()) {
						//Removes materials from each slot depending on how many are supposed to be crafted
						if (!plugin.isAirOrNull(item)) {
							NBTItem nbtItem = new NBTItem(item);
//End Armor
							if (nbtResult.getString("CustomItemID").equals("end")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_end_stone")) {
									item.setAmount(item.getAmount() - (32 * leastvalue));
								}
								if (!SlotType.matchType(result).equals(SlotType.HELMET)) {
									if (nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender")) {
										item.setAmount(item.getAmount() - (16 * leastvalue));
									}
								} else {
									item.setAmount(item.getAmount() - leastvalue);
								}
							}
							//Miner Armor
							if (nbtResult.getString("CustomItemID").equals("miner")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_cobblestone")) {
									item.setAmount(item.getAmount() - (32 * leastvalue));
								}
							}
							//Nether Armor
							if (nbtResult.getString("CustomItemID").equals("nether")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_soul_sand")) {
									item.setAmount(item.getAmount() - (16 * leastvalue));
								}
								if (!SlotType.matchType(result).equals(SlotType.HELMET)) {
									if (item.getType().equals(Material.NETHER_STAR)) {
										item.setAmount(item.getAmount() - leastvalue);
									}
								} else if (nbtItem.getString("CustomItemID").equals("nether_crown")) {
									item.setAmount(item.getAmount() - leastvalue);
								}
							}
							if(nbtResult.getString("CustomItemID").equals("seagreed")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_prismarine")) {
									item.setAmount(item.getAmount() - (16 * leastvalue));
								}
								if (nbtItem.getString("CustomItemID").equals("compacted_gold_block")) {
									item.setAmount(item.getAmount() - (4 * leastvalue));
								}
								if (nbtItem.getString("CustomItemID").equals("compacted_diamond_block")) {
									item.setAmount(item.getAmount() - (4 * leastvalue));
								}
							}
							//Nether Crown
							if (nbtResult.getString("CustomItemID").equals("nether_crown")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_blaze_rod")) {
									item.setAmount(item.getAmount() - (8 * leastvalue));
								}
								if (item.getType().equals(Material.NETHER_STAR)) {
									item.setAmount(item.getAmount() - leastvalue);
								}
							}
							//Compacted Diamond
							if (nbtResult.getString("CustomItemID").equals("compacted_diamond")) {
								item.setAmount(item.getAmount() - (4 * leastvalue));
							}
							//Compacted Diamond Block
							if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) {
								item.setAmount(item.getAmount() - (4 * leastvalue));
							}
							//Compacted Gold
							if (nbtResult.getString("CustomItemID").equals("compacted_gold_ingot")) {
								item.setAmount(item.getAmount() - (4 * leastvalue));
							}
							//Compacted Gold Block
							if (nbtResult.getString("CustomItemID").equals("compacted_gold_block")) {
								item.setAmount(item.getAmount() - (4 * leastvalue));
							}
//							if(TextHandler.getPlugin("MoreArmorsExtra") != null) {
//								if(nbtResult.getString("CustomItemID").equals("destroyer")) {
//									if(nbtItem.getString("CustomItemID").equals("compacted_iron_block")) { item.setAmount(item.getAmount() - (8 * leastvalue)); }
//									if(nbtItem.getString("CustomItemID").equals("machine_part")) { item.setAmount(item.getAmount() - (4 * leastvalue)); }
//									if(nbtItem.getString("CustomItemID").equals("machine_core")) { item.setAmount(item.getAmount() - leastvalue); }
//								}
//								if(nbtResult.getString("CustomItemID").equals("compacted_iron_ingot")) { item.setAmount(item.getAmount() - (4 * leastvalue)); }
//								if(nbtResult.getString("CustomItemID").equals("compacted_iron_block")) { item.setAmount(item.getAmount() - (4 * leastvalue)); }
//								if(nbtResult.getString("CustomItemID").equals("compacted_redstone")) { item.setAmount(item.getAmount() - (4 * leastvalue)); }
//								if(nbtResult.getString("CustomItemID").equals("star_dust")) {
//									if(nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) { item.setAmount(item.getAmount() - (4 * leastvalue)); }
//									if(item.getType().equals(Material.NETHER_STAR)) { item.setAmount(item.getAmount() - leastvalue); }
//								}
//								if(nbtResult.getString("CustomItemID").equals("machine_part")) {
//									if(nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) { item.setAmount(item.getAmount() - (4 * leastvalue)); }
//									if(nbtItem.getString("CustomItemID").equals("compacted_redstone")) { item.setAmount(item.getAmount() - (4 * leastvalue)); }
//								}
//								if(nbtResult.getString("CustomItemID").equals("energy_cell")) {
//									if(nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) { item.setAmount(item.getAmount() - (4 * leastvalue)); }
//									if(nbtItem.getString("CustomItemID").equals("star_dust")) { item.setAmount(item.getAmount() - leastvalue); }
//								}
//								if(nbtResult.getString("CustomItemID").equals("machine_core")) {
//									if(nbtItem.getString("CustomItemID").equals("compacted_iron_block")) { item.setAmount(item.getAmount() - (8 * leastvalue)); }
//									if(nbtItem.getString("CustomItemID").equals("machine_part")) { item.setAmount(item.getAmount() - (4 * leastvalue)); }
//								}
//							}
						}
					}
					if (nbtResult.getString("CustomItemID").equals("compacted_blaze_rod")) {
						inventory.addItem(plugin.materials.CompactedBlazeRod(leastvalue));
					}
					if (nbtResult.getString("CustomItemID").equals("compacted_cobblestone")) {
						inventory.addItem(plugin.materials.CompactedCobblestone(leastvalue));
					}
					if (nbtResult.getString("CustomItemID").equals("compacted_diamond")) {
						inventory.addItem(plugin.materials.CompactedDiamond(leastvalue));
					}
					if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) {
						inventory.addItem(plugin.materials.CompactedDiamondBlock(leastvalue));
					}
					if (nbtResult.getString("CustomItemID").equals("compacted_gold_ingot")) {
						inventory.addItem(plugin.materials.CompactedGold(leastvalue));
					}
					if (nbtResult.getString("CustomItemID").equals("compacted_gold_block")) {
						inventory.addItem(plugin.materials.CompactedGoldBlock(leastvalue));
					}
					if (nbtResult.getString("CustomItemID").equals("compacted_prismarine")) {
						inventory.addItem(plugin.materials.CompactedPrismarine(leastvalue));
					}
					if (nbtResult.getString("CustomItemID").equals("compacted_end_stone")) {
						inventory.addItem(plugin.materials.CompactedEndStone(leastvalue));
					}
					if (nbtResult.getString("CustomItemID").equals("compacted_eye_of_ender")) {
						inventory.addItem(plugin.materials.CompactedEyeOfEnder(leastvalue));
					}
					if (nbtResult.getString("CustomItemID").equals("compacted_soul_sand")) {
						inventory.addItem(plugin.materials.CompactedSoulSand(leastvalue));
					}
					if (nbtResult.getString("CustomItemID").equals("compacted_sugar_cane")) {
						inventory.addItem(plugin.materials.CompactedSugarCane(leastvalue));
					}
//					if (TextHandler.getPlugin("MoreArmorsExtra") != null) {
//						if (nbtResult.getString("CustomItemID").equals("compacted_iron_ingot")) { inventory.addItem(plugin.morearmorsextra.materials.CompactedIronIngot(leastvalue)); }
//						if (nbtResult.getString("CustomItemID").equals("compacted_iron_block")) { inventory.addItem(plugin.morearmorsextra.materials.CompactedIronBlock(leastvalue)); }
//						if (nbtResult.getString("CustomItemID").equals("compacted_redstone")) { inventory.addItem(plugin.morearmorsextra.materials.CompactedRedstone(leastvalue)); }
//						if (nbtResult.getString("CustomItemID").equals("machine_part")) { inventory.addItem(plugin.morearmorsextra.materials.MachinePart(leastvalue)); }
//						if (nbtResult.getString("CustomItemID").equals("star_dust")) { inventory.addItem(plugin.morearmorsextra.materials.StarDust(leastvalue)); }
//						if (nbtResult.getString("CustomItemID").equals("energy_cell")) { inventory.addItem(plugin.morearmorsextra.materials.EnergyCell(leastvalue)); }
//					}
					for (int x = 0; x < leastvalue; x++) {
						if (nbtResult.getString("CustomItemType").equals("armor")) {
							switch (SlotType.matchType(result)) {
								case HELMET -> {
									//End Helmet
									if (nbtResult.getString("CustomItemID").equals("end")) {
										inventory.addItem(plugin.armorSets.EndArmor(EquipmentSlot.HEAD));
									}
									//Miner Helmet
									if (nbtResult.getString("CustomItemID").equals("miner")) {
										inventory.addItem(plugin.armorSets.MinerArmor(EquipmentSlot.HEAD));
									}
									//Nether Helmet
									if (nbtResult.getString("CustomItemID").equals("nether")) {
										inventory.addItem(plugin.armorSets.NetherArmor(EquipmentSlot.HEAD));
									}
									if(nbtResult.getString("CustomItemID").equals("seagreed")) {
										inventory.addItem(plugin.armorSets.SeaGreedArmor(EquipmentSlot.HEAD));
									}
								}
								case CHESTPLATE -> {
									//End Chestplate
									if (nbtResult.getString("CustomItemID").equals("end")) {
										inventory.addItem(plugin.armorSets.EndArmor(EquipmentSlot.CHEST));
									}
									//Miner Chestplate
									if (nbtResult.getString("CustomItemID").equals("miner")) {
										inventory.addItem(plugin.armorSets.MinerArmor(EquipmentSlot.CHEST));
									}
									//Nether Chestplate
									if (nbtResult.getString("CustomItemID").equals("nether")) {
										inventory.addItem(plugin.armorSets.NetherArmor(EquipmentSlot.CHEST));
									}
									if(nbtResult.getString("CustomItemID").equals("seagreed")) {
										inventory.addItem(plugin.armorSets.SeaGreedArmor(EquipmentSlot.CHEST));
									}
								}
								case LEGGINGS -> {
									//End Leggings
									if (nbtResult.getString("CustomItemID").equals("end")) {
										inventory.addItem(plugin.armorSets.EndArmor(EquipmentSlot.LEGS));
									}
									//Miner Leggings
									if (nbtResult.getString("CustomItemID").equals("miner")) {
										inventory.addItem(plugin.armorSets.MinerArmor(EquipmentSlot.LEGS));
									}
									//Nether Leggings
									if (nbtResult.getString("CustomItemID").equals("nether")) {
										inventory.addItem(plugin.armorSets.NetherArmor(EquipmentSlot.LEGS));
									}
									if(nbtResult.getString("CustomItemID").equals("seagreed")) {
										inventory.addItem(plugin.armorSets.SeaGreedArmor(EquipmentSlot.LEGS));
									}
								}
								case BOOTS -> {
									//End Boots
									if (nbtResult.getString("CustomItemID").equals("end")) {
										inventory.addItem(plugin.armorSets.EndArmor(EquipmentSlot.FEET));
									}
									//Miner Boots
									if (nbtResult.getString("CustomItemID").equals("miner")) {
										inventory.addItem(plugin.armorSets.MinerArmor(EquipmentSlot.FEET));
									}
									//Nether Boots
									if (nbtResult.getString("CustomItemID").equals("nether")) {
										inventory.addItem(plugin.armorSets.NetherArmor(EquipmentSlot.FEET));
									}
									if(nbtResult.getString("CustomItemID").equals("seagreed")) {
										inventory.addItem(plugin.armorSets.SeaGreedArmor(EquipmentSlot.FEET));
									}
								}
							}
						}
						//Nether Crowns
						if (nbtResult.getString("CustomItemID").equals("nether_crown")) {
							inventory.addItem(plugin.materials.NetherCrown());
						}
//						if(TextHandler.getPlugin("MoreArmorsExtra") != null) {
//							if (nbtResult.getString("CustomItemType").equals("armor")) {
//								switch (ArmorType.matchType(result)) {
//									case HELMET -> {
//										if (nbtResult.getString("CustomItemID").equals("destroyer")) { inventory.addItem(plugin.morearmorsextra.destroyer.DestroyerHelmet(0)); }
//									}
//									case CHESTPLATE -> {
//										if (nbtResult.getString("CustomItemID").equals("destroyer")) { inventory.addItem(plugin.morearmorsextra.destroyer.DestroyerChestplate(0)); }
//									}
//									case LEGGINGS -> {
//										if (nbtResult.getString("CustomItemID").equals("destroyer")) { inventory.addItem(plugin.morearmorsextra.destroyer.DestroyerLeggings(0)); }
//									}
//									case BOOTS -> {
//										if (nbtResult.getString("CustomItemID").equals("destroyer")) { inventory.addItem(plugin.morearmorsextra.destroyer.DestroyerBoots(0)); }
//									}
//								}
//							}
//							if (nbtResult.getString("CustomItemID").equals("machine_core")) { inventory.addItem(plugin.morearmorsextra.materials.MachineCore()); }
//						}
					}
				}
			} else {
				for (ItemStack item : ci.getMatrix()) {
					//Removing items when not shift clicking
					if (!plugin.isAirOrNull(item)) {
						NBTItem nbtItem = new NBTItem(item);
						//End Armor
						if (nbtResult.getString("CustomItemID").equals("end")) {
							if (nbtItem.getString("CustomItemID").equals("compacted_end_stone")) {
								item.setAmount(item.getAmount() - 31);
							}
							if (!SlotType.matchType(result).equals(SlotType.HELMET)) {
								if (nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender")) {
									item.setAmount(item.getAmount() - 15);
								}
							}
						}
						//Miner Armor
						if (nbtResult.getString("CustomItemID").equals("miner")) {
							if (nbtItem.getString("CustomItemID").equals("compacted_cobblestone")) {
								item.setAmount(item.getAmount() - 31);
							}
						}
						//Nether Armor
						Map<String, Integer> netherArmorCraft = new HashMap<>();
						netherArmorCraft.put("compacted_soul_sand", 16);
						item = setAmount(nbtResult, nbtItem, "nether", item, netherArmorCraft);

						//Sea Greed Armor
						Map<String, Integer> seaGreedArmorCraft = new HashMap<>();
						seaGreedArmorCraft.put("compacted_prismarine", 16);
						seaGreedArmorCraft.put("compacted_gold_block", 4);
						seaGreedArmorCraft.put("compacted_diamond_block", 4);
						item = setAmount(nbtResult, nbtItem, "seagreed", item, seaGreedArmorCraft);

						//Nether Crown
						if (nbtResult.getString("CustomItemID").equals("nether_crown")) {
							if (nbtItem.getString("CustomItemID").equals("compacted_blaze_rod")) {
								item.setAmount(item.getAmount() - 7);
							}
						}
						//Compacted Diamond
						if (nbtResult.getString("CustomItemID").equals("compacted_diamond")) {
							item.setAmount(item.getAmount() - 3);
						}
						//Compacted Diamond Block
						if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) {
							item.setAmount(item.getAmount() - 3);
						}
						//Compacted Gold
						if (nbtResult.getString("CustomItemID").equals("compacted_gold_ingot")) {
							item.setAmount(item.getAmount() - 3);
						}
						//Compacted Gold Block
						if (nbtResult.getString("CustomItemID").equals("compacted_gold_block")) {
							item.setAmount(item.getAmount() - 3);
						}
//						if(TextHandler.getPlugin("MoreArmorsExtra") != null) {
//							if(nbtResult.getString("CustomItemID").equals("destroyer")) {
//								if(nbtItem.getString("CustomItemID").equals("compacted_iron_block")) {item.setAmount(item.getAmount() - 7);}
//								if(nbtItem.getString("CustomItemID").equals("machine_part")) {item.setAmount(item.getAmount() - 3);}
//							}
//							if(nbtResult.getString("CustomItemID").equals("compacted_iron_ingot")) { item.setAmount(item.getAmount() - 3); }
//							if(nbtResult.getString("CustomItemID").equals("compacted_iron_block")) { item.setAmount(item.getAmount() - 3); }
//							if(nbtResult.getString("CustomItemID").equals("compacted_redstone")) { item.setAmount(item.getAmount() - 3); }
//							if(nbtResult.getString("CustomItemID").equals("star_dust")) {
//								if(nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) {item.setAmount(item.getAmount() - 3);}
//							}
//							if(nbtResult.getString("CustomItemID").equals("machine_part")) {
//								if(nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) {item.setAmount(item.getAmount() - 3);}
//								if(nbtItem.getString("CustomItemID").equals("compacted_redstone")) {item.setAmount(item.getAmount() - 3);}
//							}
//							if(nbtResult.getString("CustomItemID").equals("energy_cell")) {
//								if(nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) {item.setAmount(item.getAmount() - 3);}
//							}
//							if(nbtResult.getString("CustomItemID").equals("machine_core")) {
//								if(nbtItem.getString("CustomItemID").equals("compacted_iron_block")) {item.setAmount(item.getAmount() - 7);}
//								if(nbtItem.getString("CustomItemID").equals("machine_part")) {item.setAmount(item.getAmount() - 3);}
//							}
//						}
					}
				}
			}
		}
	}
}