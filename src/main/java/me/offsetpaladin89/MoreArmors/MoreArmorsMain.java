package me.offsetpaladin89.MoreArmors;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.armors.ArmorSets;
import me.offsetpaladin89.MoreArmors.armors.emeraldarmor.EmeraldArmorData;
import me.offsetpaladin89.MoreArmors.armors.endarmor.EndArmorData;
import me.offsetpaladin89.MoreArmors.armors.experiencearmor.ExperienceArmor;
import me.offsetpaladin89.MoreArmors.armors.experiencearmor.ExperienceArmorData;
import me.offsetpaladin89.MoreArmors.armors.minerarmor.MinerArmor;
import me.offsetpaladin89.MoreArmors.armors.minerarmor.MinerArmorData;
import me.offsetpaladin89.MoreArmors.armors.netherarmor.NetherArmor;
import me.offsetpaladin89.MoreArmors.armors.netherarmor.NetherArmorData;
import me.offsetpaladin89.MoreArmors.armors.seagreedarmor.SeaGreedArmor;
import me.offsetpaladin89.MoreArmors.armors.seagreedarmor.SeaGreedArmorData;
import me.offsetpaladin89.MoreArmors.armors.speedsterarmor.SpeedsterArmor;
import me.offsetpaladin89.MoreArmors.armors.speedsterarmor.SpeedsterArmorData;
import me.offsetpaladin89.MoreArmors.armors.titanarmor.TitanArmor;
import me.offsetpaladin89.MoreArmors.armors.titanarmor.TitanArmorData;
import me.offsetpaladin89.MoreArmors.armors.truediamondarmor.TrueDiamondArmor;
import me.offsetpaladin89.MoreArmors.armors.truediamondarmor.TrueDiamondArmorData;
import me.offsetpaladin89.MoreArmors.commands.GiveArmor;
import me.offsetpaladin89.MoreArmors.commands.GiveMaterial;
import me.offsetpaladin89.MoreArmors.handlers.CraftHandler;
import me.offsetpaladin89.MoreArmors.listeners.MoreArmorsListener;
import me.offsetpaladin89.MoreArmors.materials.Materials;
import me.offsetpaladin89.MoreArmors.materials.MaterialsData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MoreArmorsMain extends JavaPlugin {

  private final String[] armors = {"emerald", "end", "experience", "miner", "nether", "seagreed", "speedster", "titan", "truediamond"};
  public List<String> validArmors = new ArrayList<>(Arrays.asList(armors));

  private final String[] slots = {"helmet", "chestplate", "leggings", "boots"};
  public List<String> validSlots = new ArrayList<>(Arrays.asList(armors));

  public ArrayList<Player> endarmor = new ArrayList<>();
  public ArrayList<Player> netherarmor = new ArrayList<>();
  public ArrayList<Player> destroyerarmor = new ArrayList<>();
  public ArrayList<Player> destroyerhelmet = new ArrayList<>();
  public HashMap<Player, Float> armydamageincrease = new HashMap<>();

  public EmeraldArmorData emeralddata;
  
  public EndArmorData enddata;
  
  public ExperienceArmor experience;
  
  public ExperienceArmorData experiencedata;
  
  public MinerArmor miner;
  
  public MinerArmorData minerdata;
  
  public NetherArmor nether;
  
  public NetherArmorData netherdata;
  
  public SpeedsterArmor speedster;
  
  public SpeedsterArmorData speedsterdata;
  
  public TitanArmor titan;
  
  public TitanArmorData titandata;
  
  public TrueDiamondArmor truediamond;
  
  public TrueDiamondArmorData truediamonddata;

  public SeaGreedArmor seagreed;

  public SeaGreedArmorData seagreeddata;

  public Materials materials;
  
  public MaterialsData materialsdata;
  
  public GiveMaterial givematerial;
  
  public GiveArmor givearmor;

  public ArmorConstructor armorConstructor;

  public ArmorSets armorSets;

  public void sendConsoleMessage(String s) { getServer().getConsoleSender().sendMessage(convertColoredString(s)); }
  public void sendPlayerMessage(Player p, String s) { p.sendMessage(convertColoredString(s)); }

  public void onEnable() { RegistryHandler(); }

  public boolean isAirOrNull(ItemStack item) {return item == null || item.getType().equals(Material.AIR);}
  public String convertColoredString(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }

  public boolean IsFullCustomSet(String tag, PlayerInventory inventory) {
    if(!WearingFullSet(inventory)) {
      return false;
    }
    if(isAirOrNull(inventory.getHelmet()) || isAirOrNull(inventory.getChestplate()) || isAirOrNull(inventory.getLeggings()) || isAirOrNull(inventory.getBoots())) { return false; }
    NBTItem nbtHelmet = new NBTItem(inventory.getHelmet());
    NBTItem nbtChestplate = new NBTItem(inventory.getChestplate());
    NBTItem nbtLeggings = new NBTItem(inventory.getLeggings());
    NBTItem nbtBoots = new NBTItem(inventory.getBoots());

    return nbtHelmet.getString("CustomItemID").equals(tag) &&
            nbtChestplate.getString("CustomItemID").equals(tag) &&
            nbtLeggings.getString("CustomItemID").equals(tag) &&
            nbtBoots.getString("CustomItemID").equals(tag);
  }

  public boolean WearingFullSet(PlayerInventory inventory) {
    ItemStack helmet = inventory.getHelmet();
    ItemStack chestplate = inventory.getChestplate();
    ItemStack leggings = inventory.getLeggings();
    ItemStack boots = inventory.getBoots();
    return helmet != null && chestplate != null && leggings != null && boots != null;
  }

  public void RegistryHandler() {

    new MoreArmorsListener(this);
    new CraftHandler(this);

    armorConstructor = new ArmorConstructor(this);
    armorSets = new ArmorSets(this);
    emeralddata = new EmeraldArmorData(this);
    enddata = new EndArmorData(this);
    experience = new ExperienceArmor(this);
    experiencedata = new ExperienceArmorData(this);
    miner = new MinerArmor(this);
    minerdata = new MinerArmorData(this);
    nether = new NetherArmor(this);
    netherdata = new NetherArmorData(this);
    speedster = new SpeedsterArmor(this);
    speedsterdata = new SpeedsterArmorData(this);
    titan = new TitanArmor(this);
    titandata = new TitanArmorData(this);
    truediamond = new TrueDiamondArmor(this);
    truediamonddata = new TrueDiamondArmorData(this);
    seagreed = new SeaGreedArmor(this);
    seagreeddata = new SeaGreedArmorData(this);
    materials = new Materials(this);
    materialsdata = new MaterialsData(this);
    givematerial = new GiveMaterial(this);
    givearmor = new GiveArmor(this);
    armorSets.EmeraldArmorRecipes();
    armorSets.EndArmorRecipes();
    armorSets.ExperienceArmorRecipes();
    armorSets.MinerArmorRecipes();
    RegisterRecipes();
  }
  
  public void RegisterRecipes() {
//    RegisterExperienceArmor();
//    RegisterMinerArmor();
//    RegisterNetherArmor();
//    RegisterSpeedsterArmor();
//    RegisterTitanArmor();
//    RegisterTrueDiamondArmor();
//    RegisterSeaGreedArmor();
//    RegisterMaterials();
  }
  
  public void RegisterExperienceArmor() {
    experience.ExperienceHelmetRecipe();
    experience.ExperienceChestplateRecipe();
    experience.ExperienceLeggingsRecipe();
    experience.ExperienceBootsRecipe();
  }
  
  public void RegisterMinerArmor() {
    miner.MinerHelmetRecipe();
    miner.MinerChestplateRecipe();
    miner.MinerLeggingsRecipe();
    miner.MinerBootsRecipe();
  }
  
  public void RegisterNetherArmor() {
    nether.NetherHelmetRecipe();
    nether.NetherChestplateRecipe();
    nether.NetherLeggingsRecipe();
    nether.NetherBootsRecipe();
  }
  
  public void RegisterSpeedsterArmor() {
    speedster.SpeedsterHelmetRecipe();
    speedster.SpeedsterChestplateRecipe();
    speedster.SpeedsterLeggingsRecipe();
    speedster.SpeedsterBootsRecipe();
  }
  
  public void RegisterTitanArmor() {
    titan.TitanHelmetRecipe();
    titan.TitanChestplateRecipe();
    titan.TitanLeggingsRecipe();
    titan.TitanBootsRecipe();
  }
  
  public void RegisterTrueDiamondArmor() {
    truediamond.TrueDiamondHelmetRecipe();
    truediamond.TrueDiamondHelmetUpgradeRecipe();
    truediamond.TrueDiamondHelmetSingularityUpgradeRecipe();
    truediamond.TrueDiamondChestplateRecipe();
    truediamond.TrueDiamondChestplateUpgradeRecipe();
    truediamond.TrueDiamondChestplateSingularityUpgradeRecipe();
    truediamond.TrueDiamondLeggingsRecipe();
    truediamond.TrueDiamondLeggingsUpgradeRecipe();
    truediamond.TrueDiamondLeggingsSingularityUpgradeRecipe();
    truediamond.TrueDiamondBootsRecipe();
    truediamond.TrueDiamondBootsUpgradeRecipe();
    truediamond.TrueDiamondBootsSingularityUpgradeRecipe();
  }

  public void RegisterSeaGreedArmor() {
    seagreed.SeaGreedHelmet();
    seagreed.SeaGreedHelmetRecipe();
    seagreed.SeaGreedChestplate();
    seagreed.SeaGreedChestplateRecipe();
    seagreed.SeaGreedLeggings();
    seagreed.SeaGreedLeggingsRecipe();
    seagreed.SeaGreedBoots();
    seagreed.SeaGreedBootsRecipe();

  }

  public void RegisterMaterials() {
    materials.CompactedBlazeRodRecipe();
    materials.CompactedCobblestoneRecipe();
    materials.CompactedDiamondRecipe();
    materials.CompactedDiamondBlockRecipe();
    materials.CompactedEndStoneRecipe();
    materials.CompactedEyeOfEnderRecipe();
    materials.CompactedSoulSandRecipe();
    materials.CompactedSugarCaneRecipe();
    materials.DiamondSingularityRecipe();
    materials.NetherCrownRecipe();
  }
}
