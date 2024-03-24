package me.offsetpaladin89.MoreArmors;

import me.offsetpaladin89.MoreArmors.armors.ArmorSets;
import me.offsetpaladin89.MoreArmors.armors.emeraldarmor.EmeraldArmor;
import me.offsetpaladin89.MoreArmors.armors.emeraldarmor.EmeraldArmorData;
import me.offsetpaladin89.MoreArmors.armors.endarmor.EndArmor;
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
import me.offsetpaladin89.MoreArmors.materials.Materials;
import me.offsetpaladin89.MoreArmors.materials.MaterialsData;
import me.offsetpaladin89.MorePluginsCore.MorePluginsCoreMain;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class MoreArmorsMain extends JavaPlugin {
  
  public EmeraldArmor emerald;
  
  public EmeraldArmorData emeralddata;
  
  public EndArmor end;
  
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

  public void onEnable() { RegistryHandler(); }
  
  public String convertColoredString(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }
  
  public void RegistryHandler() {
    armorConstructor = new ArmorConstructor(this);
    armorSets = new ArmorSets(this);
    emerald = new EmeraldArmor(this);
    emeralddata = new EmeraldArmorData(this);
    end = new EndArmor(this);
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
