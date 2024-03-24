package me.offsetpaladin89.MoreArmors.materials;

import com.cryptomorin.xseries.SkullUtils;
import de.tr7zw.changeme.nbtapi.NBTItem;
import dev.dbassett.skullcreator.SkullCreator;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public record Materials(MoreArmorsMain plugin) {

  public ItemStack CompactedSugarCane(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.SUGAR_CANE, amount), Rarity.UNCOMMON, "Compacted Sugar Cane", "compacted_sugar_cane"); }

  public ItemStack CompactedCobblestone(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.COBBLESTONE, amount), Rarity.COMMON, "Compacted Cobblestone", "compacted_cobblestone"); }

  public ItemStack CompactedSoulSand(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.SOUL_SAND, amount), Rarity.UNCOMMON, "Compacted Soul Sand", "compacted_soul_sand"); }

  public ItemStack CompactedBlazeRod(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.BLAZE_ROD, amount), Rarity.UNCOMMON, "Compacted Blaze Rod", "compacted_blaze_rod"); }

  public ItemStack NetherCrown() {
    ItemStack item = SkullCreator.createSkull();
    ItemMeta itemmeta = item.getItemMeta();
    itemmeta = SkullUtils.applySkin(itemmeta, "3e4f49535a276aacc4dc84133bfe81be5f2a4799a4c04d9a4ddb72d819ec2b2b");
    item.setItemMeta(itemmeta);

    item = plugin.materialsdata.addInfo(item, Rarity.EPIC, "Nether Crown", "nether_crown");

    NBTItem nbtItem = new NBTItem(item);
    nbtItem.setString("UUID", UUID.randomUUID().toString());
    return nbtItem.getItem();
  }

  public ItemStack CompactedEndStone(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.END_STONE, amount), Rarity.COMMON, "Compacted End Stone", "compacted_end_stone"); }

  public ItemStack CompactedEyeOfEnder(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.ENDER_EYE, amount), Rarity.UNCOMMON, "Compacted Eye of Ender", "compacted_eye_of_ender"); }

  public ItemStack CompactedDiamond(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.DIAMOND, amount), Rarity.UNCOMMON, "Compacted Diamond", "compacted_diamond"); }

  public ItemStack CompactedDiamondBlock(Integer amount) { return plugin.materialsdata.addInfo(new ItemStack(Material.DIAMOND_BLOCK, amount), Rarity.RARE, "Compacted Diamond Block", "compacted_diamond_block"); }

  public ItemStack DiamondSingularity() {
    ItemStack item = SkullCreator.createSkull();
    ItemMeta itemmeta = item.getItemMeta();
    itemmeta = SkullUtils.applySkin(itemmeta, "f073d84d6fda6a3404e77ad8d0f190893ae66d195fbb44d3c4607a6b71d9b9d5");
    item.setItemMeta(itemmeta);

    item = plugin.materialsdata.addInfo(item, Rarity.EPIC, "Diamond Singularity", "diamond_singularity");

    NBTItem nbtItem = new NBTItem(item);
    nbtItem.setString("UUID", UUID.randomUUID().toString());
    return nbtItem.getItem();
  }

  public void CompactedSugarCaneRecipe() {
    ItemStack item = CompactedSugarCane(1);
    NamespacedKey key = new NamespacedKey(plugin, "compacted_sugar_cane");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.SUGAR_CANE);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void CompactedCobblestoneRecipe() {
    ItemStack item = CompactedCobblestone(1);
    NamespacedKey key = new NamespacedKey(plugin, "compacted_cobblestone");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.COBBLESTONE);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void CompactedSoulSandRecipe() {
    ItemStack item = CompactedSoulSand(1);
    NamespacedKey key = new NamespacedKey(plugin, "compacted_soul_sand");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.SOUL_SAND);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void CompactedBlazeRodRecipe() {
    ItemStack item = CompactedBlazeRod(1);
    NamespacedKey key = new NamespacedKey(plugin, "compacted_blaze_rod");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.BLAZE_ROD);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void NetherCrownRecipe() {
    ItemStack item = NetherCrown();
    NamespacedKey key = new NamespacedKey(plugin, "nether_crown");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "XSX", "XXX");
    ItemRecipe.setIngredient('X', Material.BLAZE_ROD);
    ItemRecipe.setIngredient('S', Material.NETHER_STAR);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void CompactedEndStoneRecipe() {
    ItemStack item = CompactedEndStone(1);
    NamespacedKey key = new NamespacedKey(plugin, "compacted_end_stone");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.END_STONE);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void CompactedEyeOfEnderRecipe() {
    ItemStack item = CompactedEyeOfEnder(1);
    NamespacedKey key = new NamespacedKey(plugin, "compacted_eye_of_ender");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.ENDER_EYE);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void CompactedDiamondRecipe() {
    ItemStack item = CompactedDiamond(1);
    NamespacedKey key = new NamespacedKey(plugin, "compacted_diamond");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape(" X ", "XXX", " X ");
    ItemRecipe.setIngredient('X', Material.DIAMOND);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void CompactedDiamondBlockRecipe() {
    ItemStack item = CompactedDiamondBlock(1);
    NamespacedKey key = new NamespacedKey(plugin, "compacted_diamond_block");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "XXX", "XXX");
    ItemRecipe.setIngredient('X', Material.DIAMOND);
    plugin.getServer().addRecipe(ItemRecipe);
  }

  public void DiamondSingularityRecipe() {
    ItemStack item = DiamondSingularity();
    NamespacedKey key = new NamespacedKey(plugin, "diamond_singularity");
    ShapedRecipe ItemRecipe = new ShapedRecipe(key, item);
    ItemRecipe.shape("XXX", "XSX", "XXX");
    ItemRecipe.setIngredient('X', Material.DIAMOND_BLOCK);
    ItemRecipe.setIngredient('S', Material.DIAMOND);
    plugin.getServer().addRecipe(ItemRecipe);
  }
}
