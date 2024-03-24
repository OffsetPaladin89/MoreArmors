package me.offsetpaladin89.MoreArmors.handlers;

import com.cryptomorin.xseries.XMaterial;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.armors.ArmorSets;
import me.offsetpaladin89.MoreArmors.enums.ArmorType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
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
		if(nbtResult.getString("CustomItemID").equals(itemID)) {
			for (String s : materials.keySet()) {
				if(nbtItem.getString("CustomItemID").equals(s) && amount < materials.get(s)) { return false; }
			}
		}
		return true;
	}

	private ItemStack setAmount(NBTItem nbtResult, NBTItem nbtItem, String itemID, ItemStack material, Map<String, Integer> materials) {
		if(nbtResult.getString("CustomItemID").equals(itemID)) {
			for(String s : materials.keySet()) {
				if(nbtItem.getString("CustomItemID").equals(s)) {
					material.setAmount(material.getAmount() - materials.get(s) + 1);
					return material;
				}
			}
		}
		return material;
	}

	@EventHandler
	public void PrepareItemCraft(PrepareItemCraftEvent event) {
		if(event.getInventory() != null) {
			CraftingInventory ci = event.getInventory();
			if(!plugin.isAirOrNull(ci.getResult())) {
				ItemStack result = ci.getResult();
				NBTItem nbtResult = new NBTItem(result);
				if(ci.getMatrix() != null) {
					for(ItemStack item : ci.getMatrix()) {
						if(!plugin.isAirOrNull(item)) {
							NBTItem nbtItem = new NBTItem(item);
							if (nbtResult.getString("CustomItemID").equals("truediamond")) {
								boolean upgrading = false;
								boolean singularityupgrading = false;

								if (!(nbtItem.getString("CustomItemID").equals("truediamond")
										|| nbtItem.getString("CustomItemID").equals("diamond_singularity")
										|| nbtItem.getString("CustomItemID").equals("compacted_diamond_block"))) {
									ci.setResult(null);
								}

								if (nbtItem.getString("CustomItemID").equals("truediamond")) {
									if (nbtResult.getInteger("ArmorLevel") == 100) { upgrading = true; }
									if (nbtResult.getInteger("DiamondSacrifice") == 100) { singularityupgrading = true; }
								}
								if (upgrading || singularityupgrading) {
									int level = nbtItem.getInteger("ArmorLevel");
									int diamondsacrifice = nbtItem.getInteger("DiamondSacrifice");
									if ((upgrading && level >= 10) || (singularityupgrading && diamondsacrifice >= 50)) { ci.setResult(null); }
									else {
										switch (ArmorType.matchType(item)) {
											case HELMET -> {
												if (upgrading) { ci.setResult(plugin.truediamond.TrueDiamondHelmet(level + 1, diamondsacrifice)); return; }
												else if (singularityupgrading) { ci.setResult(plugin.truediamond.TrueDiamondHelmet(level, diamondsacrifice + 1)); return; }
											}
											case CHESTPLATE -> {
												if (upgrading) { ci.setResult(plugin.truediamond.TrueDiamondChestplate(level + 1, diamondsacrifice)); return; }
												else if (singularityupgrading) { ci.setResult(plugin.truediamond.TrueDiamondChestplate(level, diamondsacrifice + 1)); return; }
											}
											case LEGGINGS -> {
												if (upgrading) { ci.setResult(plugin.truediamond.TrueDiamondLeggings(level + 1, diamondsacrifice)); return; }
												else if (singularityupgrading) { ci.setResult(plugin.truediamond.TrueDiamondLeggings(level, diamondsacrifice + 1)); return; }
											}
											case BOOTS -> {
												if (upgrading) { ci.setResult(plugin.truediamond.TrueDiamondBoots(level + 1, diamondsacrifice)); return; }
												else if (singularityupgrading) { ci.setResult(plugin.truediamond.TrueDiamondBoots(level, diamondsacrifice + 1)); return; }
											}
										}
									}
								}
							}

							//Compacted Diamond
							if(nbtResult.getString("CustomItemID").equals("compacted_diamond") && !(item.getAmount() >= 4)) {
								ci.setResult(null);
							}
							//Compacted Diamond Block
							if(nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) {
								if(!nbtItem.getBoolean("IsCustomItem")) {ci.setResult(new ItemStack(Material.DIAMOND_BLOCK, 1)); return; }
								else {if(!(nbtItem.getString("CustomItemID").equals("compacted_diamond") && item.getAmount() >= 4)) {ci.setResult(null);}}
							}
							//Diamond Singularity
							if(nbtResult.getString("CustomItemID").equals("diamond_singularity")) {
								if(!((nbtItem.getString("CustomItemID").equals("compacted_diamond_block") && item.getAmount() >= 4) || (nbtItem.getString("CustomItemID").equals("compacted_diamond") && item.getAmount() >= 32))) {
									ci.setResult(null);
								}
							}
							//End Armor
							if(nbtResult.getString("CustomItemID").equals("end")) {
								if(!(nbtItem.getString("CustomItemID").equals("compacted_end_stone") || nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender") || item.getType().equals(XMaterial.DRAGON_HEAD.parseMaterial()))) {ci.setResult(null);}
								if(nbtItem.getString("CustomItemID").equals("compacted_end_stone") && !(item.getAmount() >= 32)) {ci.setResult(null);}
								if(nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender") && !(item.getAmount() >= 16)) {ci.setResult(null);}
							}
							//Miner Armor
							if(nbtResult.getString("CustomItemID").equals("miner")) {if(!(nbtItem.getString("CustomItemID").equals("compacted_cobblestone") && item.getAmount() >= 32)) {ci.setResult(null);}}
							//Nether Crown
							Map<String, Integer> netherCrownCraft = new HashMap<>();
							netherCrownCraft.put("compacted_blaze_rod", 8);
							if(!validCraft(nbtResult, nbtItem, "nether_crown", item.getAmount(), netherCrownCraft)) { ci.setResult(null); }
//							if (nbtResult.getString("CustomItemID").equals("nether_crown")) {
//								if (nbtItem.getString("CustomItemID").equals("compacted_blaze_rod") && !(item.getAmount() >= 8)) { ci.setResult(null); }
//							}
							//Nether Armor
							if(nbtResult.getString("CustomItemID").equals("nether")) {
								if(!(nbtItem.getString("CustomItemID").equals("compacted_soul_sand") || nbtItem.getString("CustomItemID").equals("nether_crown") || item.getType().equals(Material.NETHER_STAR))) {ci.setResult(null);}
								if(nbtItem.getString("CustomItemID").equals("compacted_soul_sand") && !(item.getAmount() >= 16)) {ci.setResult(null);}
							}
							//Speedster Armor
							if(nbtResult.getString("CustomItemID").equals("speedster")) {if(!nbtItem.getString("CustomItemID").equals("compacted_sugar_cane")) { ci.setResult(null); }}
							if(nbtResult.getString("CustomItemID").equals("truediamond")) {if(!(nbtItem.getString("CustomItemID").equals("compacted_diamond_block") || nbtItem.getString("CustomItemID").equals("truediamond") || nbtItem.getString("CustomItemID").equals("diamond_singularity"))) {ci.setResult(null);}}
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
							if(nbtResult.getBoolean("IsCustomItem") && nbtItem.getBoolean("IsCustomItem")) {
								if(nbtResult.getString("CustomItemID").equals(nbtItem.getString("CustomItemID"))) {
									ci.setResult(null);
								}
							}
							if(!nbtResult.getBoolean("IsCustomItem") && nbtItem.getBoolean("IsCustomItem")){
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
								if (nbtItem.getString("CustomItemID").equals("compacted_end_stone")) { values.add((int) Math.floor(item.getAmount() / 32)); }
								if (!ArmorType.matchType(result).equals(ArmorType.HELMET)) {
									if (nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender")) { values.add((int) Math.floor(item.getAmount() / 16)); }
								}
							}
							//Miner Armor
							if (nbtResult.getString("CustomItemID").equals("miner")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_cobblestone")) { values.add(item.getAmount() / 32); }
							}
							//Nether Armor
							if (nbtResult.getString("CustomItemID").equals("nether")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_soul_sand")) { values.add(item.getAmount() / 16); }
							}
							//True Diamond Armor
							if (nbtResult.getString("CustomItemID").equals("truediamond")) {

								boolean upgrading = false;
								boolean singularityupgrading = false;

								if (nbtResult.getInteger("ArmorLevel") == 100) { upgrading = true; }
								if (nbtResult.getInteger("DiamondSacrifice") == 100) { singularityupgrading = true; }
								if (!(upgrading || singularityupgrading)) { values.add(item.getAmount()); }
							}
							//Nether Crown
							if (nbtResult.getString("CustomItemID").equals("nether_crown")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_blaze_rod")) { values.add(item.getAmount() / 8); }
							}
							//Compacted Diamond
							if (nbtResult.getString("CustomItemID").equals("compacted_diamond")) { values.add(item.getAmount() / 4); }
							//Compacted Diamond Block
							if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) {
								if(nbtItem.getString("CustomItemID").equals("compacted_diamond")) { values.add(item.getAmount() / 4); }}
							//Diamond Singularity
							if (nbtResult.getString("CustomItemID").equals("diamond_singularity")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_diamond")) { values.add(item.getAmount() / 32); }
								if (nbtItem.getString("CustomItemID").equals("compacted_diamond_block")) { values.add(item.getAmount() / 4); }
							}
						}
						if(plugin.getServer().getPluginManager().getPlugin("MoreArmorsExtra") != null) {
							if(nbtResult.getString("CustomItemID").equals("destroyer")) {
								if(nbtItem.getString("CustomItemID").equals("compacted_iron_block")) { values.add(item.getAmount() / 8); }
								if(nbtItem.getString("CustomItemID").equals("machine_part")) { values.add(item.getAmount() / 4); }
								if(nbtItem.getString("CustomItemID").equals("machine_core")) { values.add(item.getAmount()); }
							}
							if(nbtResult.getString("CustomItemID").equals("compacted_iron_ingot")) {
								values.add(item.getAmount() / 4);
							}
							if(nbtResult.getString("CustomItemID").equals("compacted_iron_block")) {
								if(nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) { values.add(item.getAmount() / 4); }
							}
							if(nbtResult.getString("CustomItemID").equals("compacted_redstone")) {
								values.add(item.getAmount() / 4);
							}
							if(nbtResult.getString("CustomItemID").equals("machine_part")) {
								if(nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) { values.add(item.getAmount() / 4); }
								if(nbtItem.getString("CustomItemID").equals("compacted_redstone")) { values.add(item.getAmount() / 4); }
							}
							if(nbtResult.getString("CustomItemID").equals("star_dust")) {
								if(nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) { values.add(item.getAmount() / 4); }
								if(item.getType().equals(Material.NETHER_STAR)) { values.add(item.getAmount()); }
							}
							if(nbtResult.getString("CustomItemID").equals("energy_cell")) {
								if(nbtItem.getString("CustomItemID").equals("compacted_iron_ingot")) { values.add(item.getAmount() / 4); }
								if(nbtItem.getString("CustomItemID").equals("star_dust")) { values.add(item.getAmount()); }
							}
							if(nbtResult.getString("CustomItemID").equals("machine_core")) {
								if(nbtItem.getString("CustomItemID").equals("compacted_iron_block")) { values.add(item.getAmount() / 8); }
								if(nbtItem.getString("CustomItemID").equals("machine_part")) { values.add(item.getAmount() / 4); }
								if(nbtItem.getString("CustomItemID").equals("energy_cell")) { values.add(item.getAmount()); }
							}
						}
					}
				}
				Collections.sort(values);
				if (!values.isEmpty()) { leastvalue = values.get(0); }
				else {
					if (nbtResult.getString("CustomItemID").equals("truediamond")) {
						if (nbtResult.getInteger("ArmorLevel") == 100 || nbtResult.getInteger("DiamondSacrifice") == 100) { return; }
						int level = nbtResult.getInteger("ArmorLevel");
						int diamondsacrifice = nbtResult.getInteger("DiamondSacrifice");
						ci.setResult(null);
						for (ItemStack item : ci.getMatrix()) {
							if (!plugin.isAirOrNull(item)) { item.setAmount(item.getAmount() - 1); }
						}
						switch (ArmorType.matchType(result)) {
							case HELMET -> inventory.addItem(plugin.truediamond.TrueDiamondHelmet(level, diamondsacrifice));
							case CHESTPLATE -> inventory.addItem(plugin.truediamond.TrueDiamondChestplate(level, diamondsacrifice));
							case LEGGINGS -> inventory.addItem(plugin.truediamond.TrueDiamondLeggings(level, diamondsacrifice));
							case BOOTS -> inventory.addItem(plugin.truediamond.TrueDiamondBoots(level, diamondsacrifice));
						}
						return;
					}
				}
				//Gets how many empty slots there are in the player's inventory
				int empty = 0;
				for (int x = 0; x < 36; x++) {
					ItemStack item = inventory.getItem(x);
					if (plugin.isAirOrNull(item)) { empty++; }
				}
				//Checks if there is enough space in the player's inventory to add items
				if (leastvalue > empty) {
					if (nbtResult.getString("CustomItemID").equals("nether_crown") || nbtResult.getString("CustomItemID").equals("diamond_singularity") || nbtResult.getString("CustomItemID").equals("end") || nbtResult.getString("CustomItemID").equals("miner") || nbtResult.getString("CustomItemID").equals("nether") || nbtResult.getString("CustomItemID").equals("truediamond")) {
						leastvalue = empty;
					}
					if (nbtResult.getString("CustomItemID").equals("compacted_cobblestone") || nbtResult.getString("CustomItemID").equals("compacted_sugar_cane") || nbtResult.getString("CustomItemID").equals("compacted_eye_of_ender") || nbtResult.getString("CustomItemID").equals("compacted_end_stone") || nbtResult.getString("CustomItemID").equals("compacted_soul_sand") || nbtResult.getString("CustomItemID").equals("compacted_blaze_rod")) { leastvalue = empty * 64; }
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
								if (nbtItem.getString("CustomItemID").equals("compacted_end_stone")) { item.setAmount(item.getAmount() - (32 * leastvalue)); }
								if (!ArmorType.matchType(result).equals(ArmorType.HELMET)) {
									if (nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender")) { item.setAmount(item.getAmount() - (16 * leastvalue)); }
								}
								else { item.setAmount(item.getAmount() - leastvalue); }
							}
							//Miner Armor
							if (nbtResult.getString("CustomItemID").equals("miner")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_cobblestone")) { item.setAmount(item.getAmount() - (32 * leastvalue)); }
							}
							//Nether Armor
							if (nbtResult.getString("CustomItemID").equals("nether")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_soul_sand")) { item.setAmount(item.getAmount() - (16 * leastvalue)); }
								if (!ArmorType.matchType(result).equals(ArmorType.HELMET)) {
									if (item.getType().equals(Material.NETHER_STAR)) { item.setAmount(item.getAmount() - leastvalue); }
								}
								else if (nbtItem.getString("CustomItemID").equals("nether_crown")) { item.setAmount(item.getAmount() - leastvalue); }
							}
							//True Diamond Armor
							if (nbtResult.getString("CustomItemID").equals("truediamond")) { item.setAmount(item.getAmount() - leastvalue); }
							//Nether Crown
							if (nbtResult.getString("CustomItemID").equals("nether_crown")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_blaze_rod")) { item.setAmount(item.getAmount() - (8 * leastvalue)); }
								if (item.getType().equals(Material.NETHER_STAR)) { item.setAmount(item.getAmount() - leastvalue); }
							}
							//Compacted Diamond
							if (nbtResult.getString("CustomItemID").equals("compacted_diamond")) { item.setAmount(item.getAmount() - (4 * leastvalue)); }
							//Compacted Diamond Block
							if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) { item.setAmount(item.getAmount() - (4 * leastvalue)); }
							//Diamond Singularity
							if (nbtResult.getString("CustomItemID").equals("diamond_singularity")) {
								if (nbtItem.getString("CustomItemID").equals("compacted_diamond")) { item.setAmount(item.getAmount() - (32 * leastvalue)); }
								if (nbtItem.getString("CustomItemID").equals("compacted_diamond_block")) { item.setAmount(item.getAmount() - (4 * leastvalue)); }
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
					if (nbtResult.getString("CustomItemID").equals("compacted_blaze_rod")) { inventory.addItem(plugin.materials.CompactedBlazeRod(leastvalue)); }
					if (nbtResult.getString("CustomItemID").equals("compacted_cobblestone")) { inventory.addItem(plugin.materials.CompactedCobblestone(leastvalue)); }
					if (nbtResult.getString("CustomItemID").equals("compacted_diamond")) { inventory.addItem(plugin.materials.CompactedDiamond(leastvalue)); }
					if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) { inventory.addItem(plugin.materials.CompactedDiamondBlock(leastvalue)); }
					if (nbtResult.getString("CustomItemID").equals("compacted_end_stone")) { inventory.addItem(plugin.materials.CompactedEndStone(leastvalue)); }
					if (nbtResult.getString("CustomItemID").equals("compacted_eye_of_ender")) { inventory.addItem(plugin.materials.CompactedEyeOfEnder(leastvalue)); }
					if (nbtResult.getString("CustomItemID").equals("compacted_soul_sand")) { inventory.addItem(plugin.materials.CompactedSoulSand(leastvalue)); }
					if (nbtResult.getString("CustomItemID").equals("compacted_sugar_cane")) { inventory.addItem(plugin.materials.CompactedSugarCane(leastvalue)); }
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
							switch (ArmorType.matchType(result)) {
								case HELMET -> {
									//End Helmet
									if (nbtResult.getString("CustomItemID").equals("end")) { inventory.addItem(plugin.armorSets.EndHelmet()); }
									//Miner Helmet
									if (nbtResult.getString("CustomItemID").equals("miner")) { inventory.addItem(plugin.armorSets.MinerHelmet()); }
									//Nether Helmet
									if (nbtResult.getString("CustomItemID").equals("nether")) { inventory.addItem(plugin.armorSets.NetherHelmet()); }
									//True Diamond Helmet
//									if (nbtResult.getString("CustomItemID").equals("truediamond")) { inventory.addItem(plugin.armorSets.TrueDiamondHelmet(1, 0)); }
								}
								case CHESTPLATE -> {
									//End Chestplate
									if (nbtResult.getString("CustomItemID").equals("end")) { inventory.addItem(plugin.armorSets.EndChestplate()); }
									//Miner Chestplate
									if (nbtResult.getString("CustomItemID").equals("miner")) { inventory.addItem(plugin.armorSets.MinerChestplate()); }
									//Nether Chestplate
									if (nbtResult.getString("CustomItemID").equals("nether")) { inventory.addItem(plugin.armorSets.NetherChestplate()); }
									//True Diamond Chestplate
//									if (nbtResult.getString("CustomItemID").equals("truediamond")) { inventory.addItem(plugin.armorSets.TrueDiamondChestplate(1, 0)); }
								}
								case LEGGINGS -> {
									//End Leggings
									if (nbtResult.getString("CustomItemID").equals("end")) { inventory.addItem(plugin.armorSets.EndLeggings()); }
									//Miner Leggings
									if (nbtResult.getString("CustomItemID").equals("miner")) { inventory.addItem(plugin.armorSets.MinerLeggings()); }
									//Nether Leggings
									if (nbtResult.getString("CustomItemID").equals("nether")) { inventory.addItem(plugin.armorSets.NetherLeggings()); }
									//True Diamond Leggings
//									if (nbtResult.getString("CustomItemID").equals("truediamond")) { inventory.addItem(plugin.armorSets.TrueDiamondLeggings(1, 0)); }
								}
								case BOOTS -> {
									//End Boots
									if (nbtResult.getString("CustomItemID").equals("end")) { inventory.addItem(plugin.armorSets.EndBoots()); }
									//Miner Boots
									if (nbtResult.getString("CustomItemID").equals("miner")) { inventory.addItem(plugin.armorSets.MinerBoots()); }
									//Nether Boots
									if (nbtResult.getString("CustomItemID").equals("nether")) { inventory.addItem(plugin.armorSets.NetherBoots()); }
									//True Diamond Boots
//									if (nbtResult.getString("CustomItemID").equals("truediamond")) { inventory.addItem(plugin.armorSets.TrueDiamondBoots(1, 0)); }
								}
							}
						}
						//Diamond Singularities
						if (nbtResult.getString("CustomItemID").equals("diamond_singularity")) { inventory.addItem(plugin.materials.DiamondSingularity()); }
						//Nether Crowns
						if (nbtResult.getString("CustomItemID").equals("nether_crown")) { inventory.addItem(plugin.materials.NetherCrown()); }
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
			}
			else {
				for(ItemStack item : ci.getMatrix()) {
					//Removing items when not shift clicking
					if (!plugin.isAirOrNull(item)) {
						NBTItem nbtItem = new NBTItem(item);
						//End Armor
						if (nbtResult.getString("CustomItemID").equals("end")) {
							if (nbtItem.getString("CustomItemID").equals("compacted_end_stone")) { item.setAmount(item.getAmount() - 31); }
							if (!ArmorType.matchType(result).equals(ArmorType.HELMET)) {
								if (nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender")) { item.setAmount(item.getAmount() - 15); }
							}
						}
						//Miner Armor
						if (nbtResult.getString("CustomItemID").equals("miner")) {
							if (nbtItem.getString("CustomItemID").equals("compacted_cobblestone")) { item.setAmount(item.getAmount() - 31); }
						}
						//Nether Armor
						Map<String, Integer> netherArmorCraft = new HashMap<>();
						netherArmorCraft.put("compacted_soul_sand", 16);
						item = setAmount(nbtResult, nbtItem, "nether", item, netherArmorCraft);
						//Nether Crown
						if (nbtResult.getString("CustomItemID").equals("nether_crown")) {
							if (nbtItem.getString("CustomItemID").equals("compacted_blaze_rod")) { item.setAmount(item.getAmount() - 7); }
						}
						//Compacted Diamond
						if (nbtResult.getString("CustomItemID").equals("compacted_diamond")) { item.setAmount(item.getAmount() - 3); }
						//Compacted Diamond Block
						if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) { item.setAmount(item.getAmount() - 3); }
						//Diamond Singularity
						if (nbtResult.getString("CustomItemID").equals("diamond_singularity")) {
							if (nbtItem.getString("CustomItemID").equals("compacted_diamond")) { item.setAmount(item.getAmount() - 31); }
							if (nbtItem.getString("CustomItemID").equals("compacted_diamond_block")) { item.setAmount(item.getAmount() - 3); }
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