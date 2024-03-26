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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
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
						if (iCustomID.equals("compacted_soul_sand") && i.getAmount() < 32) inv.setResult(null);
						if (i.getType().equals(Material.PLAYER_HEAD) && !iCustomID.equals("nether_crown")) inv.setResult(null);
						if (iCustomID.isEmpty()) inv.setResult(null);
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
						if (iCustomID.equals("machine_core")) {
							// Machine Core must be in the top middle crafting slot
							// if it is a chestplate but in the middle crafting slot
							// if it is any other piece.
							if (SlotType.matchType(result).equals(SlotType.CHESTPLATE)) {
								if (x != 1) inv.setResult(null);
							} else if (x != 4) inv.setResult(null);
						}
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
						if (!nbtItem.getBoolean("IsCustomItem")) inv.setResult(new ItemStack(Material.DIAMOND_BLOCK, 1));
						if (iCustomID.equals("compacted_diamond") && i.getAmount() < 4) inv.setResult(null);
						if (iCustomID.isEmpty()) inv.setResult(null);
					}
					// Compacted Gold
					if (rCustomID.equals("compacted_gold")) {
						if (i.getAmount() < 4) inv.setResult(null);
					}
					// Compacted Gold Block
					if (rCustomID.equals("compacted_gold_block")) {
						if (!nbtItem.getBoolean("IsCustomItem")) inv.setResult(new ItemStack(Material.GOLD_BLOCK, 1));
						if (iCustomID.equals("compacted_gold") && i.getAmount() < 4) inv.setResult(null);
						if (iCustomID.isEmpty()) inv.setResult(null);
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
						if (!nbtItem.getBoolean("IsCustomItem")) inv.setResult(new ItemStack(Material.IRON_BLOCK, 1));
						if (iCustomID.equals("compacted_iron") && i.getAmount() < 4) inv.setResult(null);
						if (iCustomID.isEmpty()) inv.setResult(null);
					}
					// Star Dust
					if (rCustomID.equals("star_dust")) {
						if (iCustomID.equals("compacted_iron") && i.getAmount() < 4) inv.setResult(null);
						if (iCustomID.isEmpty()) inv.setResult(null);
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

	@EventHandler
	public void craftEvent(CraftItemEvent e) {
		Player p = (Player) e.getWhoClicked();
		PlayerInventory inv = p.getInventory();
		CraftingInventory ci = e.getInventory();

		ItemStack result = ci.getResult();

		if (!plugin.isAirOrNull(result)) {
			NBTItem nbtResult = new NBTItem(result);

			if (e.isShiftClick()) {
				ArrayList<Integer> slotValues = new ArrayList<>();
				String rCustomID = nbtResult.getString("CustomItemID");
				ItemStack[] cm = ci.getMatrix();
				for (int x = 0; x < cm.length; x++) {
					ItemStack i = cm[x];
					if (!plugin.isAirOrNull(i)) {
						NBTItem nbtItem = new NBTItem(i);
						String iCustomID = nbtResult.getString("CustomItemID");
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
			}
		}
	}

	//	@EventHandler
	//	public void onItemCraft(CraftItemEvent event) {
	//		Player player = (Player) event.getWhoClicked();
	//		PlayerInventory inventory = player.getInventory();
	//		CraftingInventory ci = event.getInventory();
	//
	//		if (!plugin.isAirOrNull(ci.getResult())) {
	//			ItemStack result = ci.getResult();
	//			NBTItem nbtResult = new NBTItem(result);
	//
	//			if (event.isShiftClick()) {
	//				ArrayList<Integer> values = new ArrayList<>();
	//				int leastvalue = 0;
	//
	//				for (ItemStack item : ci.getMatrix()) {
	//					//Gets the possible craft amount of items
	//					if (!plugin.isAirOrNull(item)) {
	//						NBTItem nbtItem = new NBTItem(item);
	//						if (plugin.getServer().getPluginManager().getPlugin("MoreArmors") != null) {
	//							//End Armor
	//							if (nbtResult.getString("CustomItemID").equals("end")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_end_stone")) {
	//									values.add((int) Math.floor(item.getAmount() / 32));
	//								}
	//								if (!SlotType.matchType(result).equals(SlotType.HELMET)) {
	//									if (nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender")) {
	//										values.add((int) Math.floor(item.getAmount() / 16));
	//									}
	//								}
	//							}
	//							//Miner Armor
	//							if (nbtResult.getString("CustomItemID").equals("miner")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_cobblestone")) {
	//									values.add(item.getAmount() / 32);
	//								}
	//							}
	//							//Nether Armor
	//							if (nbtResult.getString("CustomItemID").equals("nether")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_soul_sand")) {
	//									values.add(item.getAmount() / 16);
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("seagreed")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_prismarine")) {
	//									values.add(item.getAmount() / 16);
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("compacted_gold_block")) {
	//									values.add(item.getAmount() / 4);
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("compacted_diamond_block")) {
	//									values.add(item.getAmount() / 4);
	//								}
	//							}
	//							//Nether Crown
	//							if (nbtResult.getString("CustomItemID").equals("nether_crown")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_blaze_rod")) {
	//									values.add(item.getAmount() / 8);
	//								}
	//							}
	//							//Compacted Diamond
	//							if (nbtResult.getString("CustomItemID").equals("compacted_diamond")) {
	//								values.add(item.getAmount() / 4);
	//							}
	//							//Compacted Diamond Block
	//							if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_diamond")) {
	//									values.add(item.getAmount() / 4);
	//								}
	//							}
	//							//Compacted Gold
	//							if (nbtResult.getString("CustomItemID").equals("compacted_gold_ingot")) {
	//								values.add(item.getAmount() / 4);
	//							}
	//							//Compacted Gold Block
	//							if (nbtResult.getString("CustomItemID").equals("compacted_gold_block")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_gold_ingot")) {
	//									values.add(item.getAmount() / 4);
	//								}
	//							}
	//							//Compacted Redstone
	//							if (nbtResult.getString("CustomItemID").equals("compacted_redstone")) {
	//								values.add(item.getAmount() / 4);
	//							}
	//							//Compacted Iron
	//							if (nbtResult.getString("CustomItemID").equals("compacted_iron")) {
	//								values.add(item.getAmount() / 4);
	//							}
	//							//Compacted Iron Block
	//							if (nbtResult.getString("CustomItemID").equals("compacted_iron_block")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_iron")) {
	//									values.add(item.getAmount() / 4);
	//								}
	//							}
	//						}
	//						if (plugin.getServer().getPluginManager().getPlugin("MoreArmorsExtra") != null) {
	//							if (nbtResult.getString("CustomItemID").equals("destroyer")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_iron_block")) {
	//									values.add(item.getAmount() / 8);
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("machine_part")) {
	//									values.add(item.getAmount() / 4);
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("machine_core")) {
	//									values.add(item.getAmount());
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("compacted_iron")) {
	//								values.add(item.getAmount() / 4);
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("compacted_iron_block")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_iron")) {
	//									values.add(item.getAmount() / 4);
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("compacted_redstone")) {
	//								values.add(item.getAmount() / 4);
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("machine_part")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_iron")) {
	//									values.add(item.getAmount() / 4);
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("compacted_redstone")) {
	//									values.add(item.getAmount() / 4);
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("star_dust")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_iron")) {
	//									values.add(item.getAmount() / 4);
	//								}
	//								if (item.getType().equals(Material.NETHER_STAR)) {
	//									values.add(item.getAmount());
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("energy_cell")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_iron")) {
	//									values.add(item.getAmount() / 4);
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("star_dust")) {
	//									values.add(item.getAmount());
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("machine_core")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_iron_block")) {
	//									values.add(item.getAmount() / 8);
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("machine_part")) {
	//									values.add(item.getAmount() / 4);
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("energy_cell")) {
	//									values.add(item.getAmount());
	//								}
	//							}
	//						}
	//					}
	//				}
	//				Collections.sort(values);
	//				if (!values.isEmpty()) {
	//					leastvalue = values.get(0);
	//				} else {
	//
	//				}
	//				//Gets how many empty slots there are in the player's inventory
	//				int empty = 0;
	//				for (int x = 0; x < 36; x++) {
	//					ItemStack item = inventory.getItem(x);
	//					if (plugin.isAirOrNull(item)) {
	//						empty++;
	//					}
	//				}
	//				//Checks if there is enough space in the player's inventory to add items
	//				if (leastvalue > empty) {
	//					if (nbtResult.getString("CustomItemID").equals("nether_crown") || nbtResult.getString("CustomItemID").equals("end") || nbtResult.getString("CustomItemID").equals("miner") || nbtResult.getString("CustomItemID").equals("nether") || nbtResult.getString("CustomItemID").equals("destroyer") || nbtResult.getString("CustomItemID").equals("machine_core") || nbtResult.getString("CustomItemID").equals("energy_cell")) {
	//						leastvalue = empty;
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_cobblestone") || nbtResult.getString("CustomItemID").equals("compacted_sugar_cane") || nbtResult.getString("CustomItemID").equals("compacted_eye_of_ender") || nbtResult.getString("CustomItemID").equals("compacted_end_stone") || nbtResult.getString("CustomItemID").equals("compacted_soul_sand") || nbtResult.getString("CustomItemID").equals("compacted_blaze_rod")) {
	//						leastvalue = empty * 64;
	//					}
	//				}
	//				if (empty == 0) {
	//					event.setCancelled(true);
	//					return;
	//				}
	//				if (leastvalue > 0) {
	//					ci.setResult(null);
	//					for (ItemStack item : ci.getMatrix()) {
	//						//Removes materials from each slot depending on how many are supposed to be crafted
	//						if (!plugin.isAirOrNull(item)) {
	//							NBTItem nbtItem = new NBTItem(item);
	////End Armor
	//							if (nbtResult.getString("CustomItemID").equals("end")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_end_stone")) {
	//									item.setAmount(item.getAmount() - (32 * leastvalue));
	//								}
	//								if (!SlotType.matchType(result).equals(SlotType.HELMET)) {
	//									if (nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender")) {
	//										item.setAmount(item.getAmount() - (16 * leastvalue));
	//									}
	//								} else {
	//									item.setAmount(item.getAmount() - leastvalue);
	//								}
	//							}
	//							//Miner Armor
	//							if (nbtResult.getString("CustomItemID").equals("miner")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_cobblestone")) {
	//									item.setAmount(item.getAmount() - (32 * leastvalue));
	//								}
	//							}
	//							//Nether Armor
	//							if (nbtResult.getString("CustomItemID").equals("nether")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_soul_sand")) {
	//									item.setAmount(item.getAmount() - (16 * leastvalue));
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("seagreed")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_prismarine")) {
	//									item.setAmount(item.getAmount() - (16 * leastvalue));
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("compacted_gold_block")) {
	//									item.setAmount(item.getAmount() - (4 * leastvalue));
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("compacted_diamond_block")) {
	//									item.setAmount(item.getAmount() - (4 * leastvalue));
	//								}
	//							}
	//							//Nether Crown
	//							if (nbtResult.getString("CustomItemID").equals("nether_crown")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_blaze_rod")) {
	//									item.setAmount(item.getAmount() - (8 * leastvalue));
	//								}
	//							}
	//							//Compacted Diamond
	//							if (nbtResult.getString("CustomItemID").equals("compacted_diamond")) {
	//								item.setAmount(item.getAmount() - (4 * leastvalue));
	//							}
	//							//Compacted Diamond Block
	//							if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) {
	//								item.setAmount(item.getAmount() - (4 * leastvalue));
	//							}
	//							//Compacted Gold
	//							if (nbtResult.getString("CustomItemID").equals("compacted_gold_ingot")) {
	//								item.setAmount(item.getAmount() - (4 * leastvalue));
	//							}
	//							//Compacted Gold Block
	//							if (nbtResult.getString("CustomItemID").equals("compacted_gold_block")) {
	//								item.setAmount(item.getAmount() - (4 * leastvalue));
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("destroyer")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_iron_block")) {
	//									item.setAmount(item.getAmount() - (8 * leastvalue));
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("machine_part")) {
	//									item.setAmount(item.getAmount() - (4 * leastvalue));
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("compacted_iron")) {
	//								item.setAmount(item.getAmount() - (4 * leastvalue));
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("compacted_iron_block")) {
	//								item.setAmount(item.getAmount() - (4 * leastvalue));
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("compacted_redstone")) {
	//								item.setAmount(item.getAmount() - (4 * leastvalue));
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("star_dust")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_iron")) {
	//									item.setAmount(item.getAmount() - (4 * leastvalue));
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("machine_part")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_iron")) {
	//									item.setAmount(item.getAmount() - (4 * leastvalue));
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("compacted_redstone")) {
	//									item.setAmount(item.getAmount() - (4 * leastvalue));
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("energy_cell")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_iron")) {
	//									item.setAmount(item.getAmount() - (4 * leastvalue));
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("star_dust")) {
	//									item.setAmount(item.getAmount() - leastvalue);
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("machine_core")) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_iron_block")) {
	//									item.setAmount(item.getAmount() - (8 * leastvalue));
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("machine_part")) {
	//									item.setAmount(item.getAmount() - (4 * leastvalue));
	//								}
	//							}
	//						}
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_blaze_rod")) {
	//						inventory.addItem(plugin.materials.CompactedBlazeRod(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_cobblestone")) {
	//						inventory.addItem(plugin.materials.CompactedCobblestone(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_diamond")) {
	//						inventory.addItem(plugin.materials.CompactedDiamond(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) {
	//						inventory.addItem(plugin.materials.CompactedDiamondBlock(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_gold_ingot")) {
	//						inventory.addItem(plugin.materials.CompactedGold(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_gold_block")) {
	//						inventory.addItem(plugin.materials.CompactedGoldBlock(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_prismarine")) {
	//						inventory.addItem(plugin.materials.CompactedPrismarine(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_end_stone")) {
	//						inventory.addItem(plugin.materials.CompactedEndStone(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_eye_of_ender")) {
	//						inventory.addItem(plugin.materials.CompactedEyeOfEnder(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_soul_sand")) {
	//						inventory.addItem(plugin.materials.CompactedSoulSand(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_sugar_cane")) {
	//						inventory.addItem(plugin.materials.CompactedSugarCane(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_iron")) {
	//						inventory.addItem(plugin.materials.CompactedIron(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_iron_block")) {
	//						inventory.addItem(plugin.materials.CompactedIronBlock(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("compacted_redstone")) {
	//						inventory.addItem(plugin.materials.CompactedRedstone(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("machine_part")) {
	//						inventory.addItem(plugin.materials.MachinePart(leastvalue));
	//					}
	//					if (nbtResult.getString("CustomItemID").equals("star_dust")) {
	//						inventory.addItem(plugin.materials.StarDust(leastvalue));
	//					}
	//					for (int x = 0; x < leastvalue; x++) {
	//						if (nbtResult.getString("CustomItemType").equals("armor")) {
	//							switch (SlotType.matchType(result)) {
	//								case HELMET -> {
	//									//End Helmet
	//									if (nbtResult.getString("CustomItemID").equals("end")) {
	//										inventory.addItem(plugin.armorSets.EndArmor(EquipmentSlot.HEAD));
	//									}
	//									//Miner Helmet
	//									if (nbtResult.getString("CustomItemID").equals("miner")) {
	//										inventory.addItem(plugin.armorSets.MinerArmor(EquipmentSlot.HEAD));
	//									}
	//									//Nether Helmet
	//									if (nbtResult.getString("CustomItemID").equals("nether")) {
	//										inventory.addItem(plugin.armorSets.NetherArmor(EquipmentSlot.HEAD));
	//									}
	//									if (nbtResult.getString("CustomItemID").equals("seagreed")) {
	//										inventory.addItem(plugin.armorSets.SeaGreedArmor(EquipmentSlot.HEAD));
	//									}
	//									if (nbtResult.getString("CustomItemID").equals("destroyer")) {
	//										inventory.addItem(plugin.armorSets.DestroyerArmor(EquipmentSlot.HEAD, 0));
	//									}
	//								}
	//								case CHESTPLATE -> {
	//									//End Chestplate
	//									if (nbtResult.getString("CustomItemID").equals("end")) {
	//										inventory.addItem(plugin.armorSets.EndArmor(EquipmentSlot.CHEST));
	//									}
	//									//Miner Chestplate
	//									if (nbtResult.getString("CustomItemID").equals("miner")) {
	//										inventory.addItem(plugin.armorSets.MinerArmor(EquipmentSlot.CHEST));
	//									}
	//									//Nether Chestplate
	//									if (nbtResult.getString("CustomItemID").equals("nether")) {
	//										inventory.addItem(plugin.armorSets.NetherArmor(EquipmentSlot.CHEST));
	//									}
	//									if (nbtResult.getString("CustomItemID").equals("seagreed")) {
	//										inventory.addItem(plugin.armorSets.SeaGreedArmor(EquipmentSlot.CHEST));
	//									}
	//									if (nbtResult.getString("CustomItemID").equals("destroyer")) {
	//										inventory.addItem(plugin.armorSets.DestroyerArmor(EquipmentSlot.CHEST, 0));
	//									}
	//								}
	//								case LEGGINGS -> {
	//									//End Leggings
	//									if (nbtResult.getString("CustomItemID").equals("end")) {
	//										inventory.addItem(plugin.armorSets.EndArmor(EquipmentSlot.LEGS));
	//									}
	//									//Miner Leggings
	//									if (nbtResult.getString("CustomItemID").equals("miner")) {
	//										inventory.addItem(plugin.armorSets.MinerArmor(EquipmentSlot.LEGS));
	//									}
	//									//Nether Leggings
	//									if (nbtResult.getString("CustomItemID").equals("nether")) {
	//										inventory.addItem(plugin.armorSets.NetherArmor(EquipmentSlot.LEGS));
	//									}
	//									if (nbtResult.getString("CustomItemID").equals("seagreed")) {
	//										inventory.addItem(plugin.armorSets.SeaGreedArmor(EquipmentSlot.LEGS));
	//									}
	//									if (nbtResult.getString("CustomItemID").equals("destroyer")) {
	//										inventory.addItem(plugin.armorSets.DestroyerArmor(EquipmentSlot.LEGS, 0));
	//									}
	//								}
	//								case BOOTS -> {
	//									//End Boots
	//									if (nbtResult.getString("CustomItemID").equals("end")) {
	//										inventory.addItem(plugin.armorSets.EndArmor(EquipmentSlot.FEET));
	//									}
	//									//Miner Boots
	//									if (nbtResult.getString("CustomItemID").equals("miner")) {
	//										inventory.addItem(plugin.armorSets.MinerArmor(EquipmentSlot.FEET));
	//									}
	//									//Nether Boots
	//									if (nbtResult.getString("CustomItemID").equals("nether")) {
	//										inventory.addItem(plugin.armorSets.NetherArmor(EquipmentSlot.FEET));
	//									}
	//									if (nbtResult.getString("CustomItemID").equals("seagreed")) {
	//										inventory.addItem(plugin.armorSets.SeaGreedArmor(EquipmentSlot.FEET));
	//									}
	//									if (nbtResult.getString("CustomItemID").equals("destroyer")) {
	//										inventory.addItem(plugin.armorSets.DestroyerArmor(EquipmentSlot.FEET, 0));
	//									}
	//								}
	//							}
	//						}
	//						//Nether Crowns
	//						if (nbtResult.getString("CustomItemID").equals("nether_crown")) {
	//							inventory.addItem(plugin.materials.NetherCrown());
	//						}
	//						if (nbtResult.getString("CustomItemID").equals("machine_core")) {
	//							inventory.addItem(plugin.materials.MachineCore());
	//						}
	//						if (nbtResult.getString("CustomItemID").equals("energy_cell")) {
	//							inventory.addItem(plugin.materials.EnergyCell());
	//						}
	//					}
	//				}
	//			} else {
	//				for (ItemStack item : ci.getMatrix()) {
	//					//Removing items when not shift clicking
	//					if (!plugin.isAirOrNull(item)) {
	//						NBTItem nbtItem = new NBTItem(item);
	//						//End Armor
	//						if (nbtResult.getString("CustomItemID").equals("end")) {
	//							if (nbtItem.getString("CustomItemID").equals("compacted_end_stone")) {
	//								item.setAmount(item.getAmount() - 31);
	//							}
	//							if (!SlotType.matchType(result).equals(SlotType.HELMET)) {
	//								if (nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender")) {
	//									item.setAmount(item.getAmount() - 15);
	//								}
	//							}
	//						}
	//						//Miner Armor
	//						if (nbtResult.getString("CustomItemID").equals("miner")) {
	//							if (nbtItem.getString("CustomItemID").equals("compacted_cobblestone")) {
	//								item.setAmount(item.getAmount() - 31);
	//							}
	//						}
	//						//Nether Armor
	//						Map<String, Integer> netherArmorCraft = new HashMap<>();
	//						netherArmorCraft.put("compacted_soul_sand", 16);
	//						item = setAmount(nbtResult, nbtItem, "nether", item, netherArmorCraft);
	//
	//						//Sea Greed Armor
	//						Map<String, Integer> seaGreedArmorCraft = new HashMap<>();
	//						seaGreedArmorCraft.put("compacted_prismarine", 16);
	//						seaGreedArmorCraft.put("compacted_gold_block", 4);
	//						seaGreedArmorCraft.put("compacted_diamond_block", 4);
	//						item = setAmount(nbtResult, nbtItem, "seagreed", item, seaGreedArmorCraft);
	//
	//						//Nether Crown
	//						if (nbtResult.getString("CustomItemID").equals("nether_crown")) {
	//							if (nbtItem.getString("CustomItemID").equals("compacted_blaze_rod")) {
	//								item.setAmount(item.getAmount() - 7);
	//							}
	//						}
	//						//Compacted Diamond
	//						if (nbtResult.getString("CustomItemID").equals("compacted_diamond")) {
	//							item.setAmount(item.getAmount() - 3);
	//						}
	//						//Compacted Diamond Block
	//						if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) {
	//							item.setAmount(item.getAmount() - 3);
	//						}
	//						//Compacted Gold
	//						if (nbtResult.getString("CustomItemID").equals("compacted_gold_ingot")) {
	//							item.setAmount(item.getAmount() - 3);
	//						}
	//						//Compacted Gold Block
	//						if (nbtResult.getString("CustomItemID").equals("compacted_gold_block")) {
	//							item.setAmount(item.getAmount() - 3);
	//						}
	//						if (nbtResult.getString("CustomItemID").equals("destroyer")) {
	//							if (nbtItem.getString("CustomItemID").equals("compacted_iron_block")) {
	//								item.setAmount(item.getAmount() - 7);
	//							}
	//							if (nbtItem.getString("CustomItemID").equals("machine_part")) {
	//								item.setAmount(item.getAmount() - 3);
	//							}
	//						}
	//						if (nbtResult.getString("CustomItemID").equals("compacted_iron")) {
	//							item.setAmount(item.getAmount() - 3);
	//						}
	//						if (nbtResult.getString("CustomItemID").equals("compacted_iron_block")) {
	//							item.setAmount(item.getAmount() - 3);
	//						}
	//						if (nbtResult.getString("CustomItemID").equals("compacted_redstone")) {
	//							item.setAmount(item.getAmount() - 3);
	//						}
	//						if (nbtResult.getString("CustomItemID").equals("star_dust")) {
	//							if (nbtItem.getString("CustomItemID").equals("compacted_iron")) {
	//								item.setAmount(item.getAmount() - 3);
	//							}
	//						}
	//						if (nbtResult.getString("CustomItemID").equals("machine_part")) {
	//							if (nbtItem.getString("CustomItemID").equals("compacted_iron")) {
	//								item.setAmount(item.getAmount() - 3);
	//							}
	//							if (nbtItem.getString("CustomItemID").equals("compacted_redstone")) {
	//								item.setAmount(item.getAmount() - 3);
	//							}
	//						}
	//						if (nbtResult.getString("CustomItemID").equals("energy_cell")) {
	//							if (nbtItem.getString("CustomItemID").equals("compacted_iron")) {
	//								item.setAmount(item.getAmount() - 3);
	//							}
	//						}
	//						if (nbtResult.getString("CustomItemID").equals("machine_core")) {
	//							if (nbtItem.getString("CustomItemID").equals("compacted_iron_block")) {
	//								item.setAmount(item.getAmount() - 7);
	//							}
	//							if (nbtItem.getString("CustomItemID").equals("machine_part")) {
	//								item.setAmount(item.getAmount() - 3);
	//							}
	//						}
	//					}
	//				}
	//			}
	//		}
	//	}

	//	@EventHandler
	//	public void PrepareItemCraft(PrepareItemCraftEvent event) {
	//		if (event.getInventory() != null) {
	//			CraftingInventory ci = event.getInventory();
	//			if (!plugin.isAirOrNull(ci.getResult())) {
	//				ItemStack result = ci.getResult();
	//				NBTItem nbtResult = new NBTItem(result);
	//				if (ci.getMatrix() != null) {
	//					for (ItemStack item : ci.getMatrix()) {
	//						if (!plugin.isAirOrNull(item)) {
	//							NBTItem nbtItem = new NBTItem(item);
	//							//Compacted Diamond
	//							if (nbtResult.getString("CustomItemID").equals("compacted_diamond") && !(item.getAmount() >= 4)) {
	//								ci.setResult(null);
	//							}
	//							//Compacted Diamond Block
	//							if (nbtResult.getString("CustomItemID").equals("compacted_diamond_block")) {
	//								if (!nbtItem.getBoolean("IsCustomItem")) {
	//									ci.setResult(new ItemStack(Material.DIAMOND_BLOCK, 1));
	//									return;
	//								} else {
	//									if (!(nbtItem.getString("CustomItemID").equals("compacted_diamond") && item.getAmount() >= 4)) {
	//										ci.setResult(null);
	//									}
	//								}
	//							}
	//							//Compacted Gold
	//							if (nbtResult.getString("CustomItemID").equals("compacted_gold_ingot") && !(item.getAmount() >= 4)) {
	//								ci.setResult(null);
	//							}
	//							//Compacted Gold Block
	//							if (nbtResult.getString("CustomItemID").equals("compacted_gold_block")) {
	//								if (!nbtItem.getBoolean("IsCustomItem")) {
	//									ci.setResult(new ItemStack(Material.GOLD_BLOCK, 1));
	//									return;
	//								} else {
	//									if (!(nbtItem.getString("CustomItemID").equals("compacted_gold_ingot") && item.getAmount() >= 4)) {
	//										ci.setResult(null);
	//									}
	//								}
	//							}
	//							//End Armor
	//							if (nbtResult.getString("CustomItemID").equals("end")) {
	//								if (!(nbtItem.getString("CustomItemID").equals("compacted_end_stone") || nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender") || item.getType().equals(XMaterial.DRAGON_HEAD.parseMaterial()))) {
	//									ci.setResult(null);
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("compacted_end_stone") && !(item.getAmount() >= 32)) {
	//									ci.setResult(null);
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("compacted_eye_of_ender") && !(item.getAmount() >= 16)) {
	//									ci.setResult(null);
	//								}
	//							}
	//							//Miner Armor
	//							if (nbtResult.getString("CustomItemID").equals("miner")) {
	//								if (!(nbtItem.getString("CustomItemID").equals("compacted_cobblestone") && item.getAmount() >= 32)) {
	//									ci.setResult(null);
	//								}
	//							}
	//							//Nether Crown
	//							Map<String, Integer> netherCrownCraft = new HashMap<>();
	//							netherCrownCraft.put("compacted_blaze_rod", 8);
	//							if (!validCraft(nbtResult, nbtItem, "nether_crown", item.getAmount(), netherCrownCraft)) {
	//								ci.setResult(null);
	//							}
	//							//Nether Armor
	//							if (nbtResult.getString("CustomItemID").equals("nether")) {
	//								if (!(nbtItem.getString("CustomItemID").equals("compacted_soul_sand") || nbtItem.getString("CustomItemID").equals("nether_crown") || item.getType().equals(Material.NETHER_STAR))) {
	//									ci.setResult(null);
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("compacted_soul_sand") && !(item.getAmount() >= 16)) {
	//									ci.setResult(null);
	//								}
	//							}
	//							//Speedster Armor
	//							if (nbtResult.getString("CustomItemID").equals("speedster")) {
	//								if (!nbtItem.getString("CustomItemID").equals("compacted_sugar_cane")) {
	//									ci.setResult(null);
	//								}
	//							}
	//							Map<String, Integer> seaGreedCraft = new HashMap<>();
	//							seaGreedCraft.put("compacted_diamond_block", 4);
	//							seaGreedCraft.put("compacted_prismarine", 16);
	//							seaGreedCraft.put("compacted_gold_block", 4);
	//							if (!validCraft(nbtResult, nbtItem, "seagreed", item.getAmount(), seaGreedCraft)) {
	//								ci.setResult(null);
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("destroyer")) {
	//								if (!(nbtItem.getString("CustomItemID").equals("compacted_iron_block") || nbtItem.getString("CustomItemID").equals("machine_core") || nbtItem.getString("CustomItemID").equals("machine_part"))) {
	//									ci.setResult(null);
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("compacted_iron_block") && !(item.getAmount() >= 8)) {
	//									ci.setResult(null);
	//								}
	//								if (nbtItem.getString("CustomItemID").equals("machine_part") && !(item.getAmount() >= 4)) {
	//									ci.setResult(null);
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("compacted_iron") && !(item.getAmount() >= 4)) {
	//								ci.setResult(null);
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("compacted_iron_block")) {
	//								if (!nbtItem.getBoolean("IsCustomItem")) {
	//									ci.setResult(new ItemStack(Material.IRON_BLOCK, 1));
	//									return;
	//								} else {
	//									if (!(nbtItem.getString("CustomItemID").equals("compacted_iron") && item.getAmount() >= 4)) {
	//										ci.setResult(null);
	//									}
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("compacted_redstone") && !(item.getAmount() >= 4)) {
	//								ci.setResult(null);
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("star_dust")) {
	//								if (!((nbtItem.getString("CustomItemID").equals("compacted_iron") && item.getAmount() >= 4) || item.getType().equals(Material.NETHER_STAR))) {
	//									ci.setResult(null);
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("machine_part")) {
	//								if (!((nbtItem.getString("CustomItemID").equals("compacted_iron") && item.getAmount() >= 4) || nbtItem.getString("CustomItemID").equals("compacted_redstone") && item.getAmount() >= 4)) {
	//									ci.setResult(null);
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("energy_cell")) {
	//								if (!((nbtItem.getString("CustomItemID").equals("compacted_iron") && item.getAmount() >= 4) || nbtItem.getString("CustomItemID").equals("star_dust"))) {
	//									ci.setResult(null);
	//								}
	//							}
	//							if (nbtResult.getString("CustomItemID").equals("machine_core")) {
	//								if (!((nbtItem.getString("CustomItemID").equals("compacted_iron_block") && item.getAmount() >= 8) || (nbtItem.getString("CustomItemID").equals("machine_part") && item.getAmount() >= 4) || nbtItem.getString("CustomItemID").equals("energy_cell"))) {
	//									ci.setResult(null);
	//								}
	//							}
	//							if (nbtResult.getBoolean("IsCustomItem") && nbtItem.getBoolean("IsCustomItem")) {
	//								if (nbtResult.getString("CustomItemID").equals(nbtItem.getString("CustomItemID"))) {
	//									ci.setResult(null);
	//								}
	//							}
	//							if (!nbtResult.getBoolean("IsCustomItem") && nbtItem.getBoolean("IsCustomItem")) {
	//								ci.setResult(null);
	//							}
	//						}
	//					}
	//				}
	//			}
	//		}
	//	}
}