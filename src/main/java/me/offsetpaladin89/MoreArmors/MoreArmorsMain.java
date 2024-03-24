package me.offsetpaladin89.MoreArmors;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.offsetpaladin89.MoreArmors.armors.ArmorSets;
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
import me.offsetpaladin89.MoreArmors.handlers.ArmorSetAbilityHandler;
import me.offsetpaladin89.MoreArmors.handlers.CraftHandler;
import me.offsetpaladin89.MoreArmors.handlers.DamageHandler;
import me.offsetpaladin89.MoreArmors.handlers.HologramHandler;
import me.offsetpaladin89.MoreArmors.listeners.MainListener;
import me.offsetpaladin89.MoreArmors.listeners.MaterialsListener;
import me.offsetpaladin89.MoreArmors.listeners.MoreArmorsListener;
import me.offsetpaladin89.MoreArmors.materials.Materials;
import me.offsetpaladin89.MoreArmors.materials.MaterialsData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.N;

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
    public ArrayList<Player> netherArmorBonus = new ArrayList<>();
    public ArrayList<Player> destroyerarmor = new ArrayList<>();
    public ArrayList<Player> destroyerhelmet = new ArrayList<>();
    public HashMap<Player, Float> armydamageincrease = new HashMap<>();

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

    public HologramHandler hologramHandler;
    public GiveMaterial givematerial;

    public GiveArmor givearmor;

    public ArmorConstructor armorConstructor;

    public ArmorSets armorSets;

    public ArmorSetAbilityHandler armorSetAbilities;

    public void sendConsoleMessage(String s) {
        getServer().getConsoleSender().sendMessage(convertColoredString(s));
    }

    public void sendPlayerMessage(Player p, String s) {
        p.sendMessage(convertColoredString(s));
    }

    public void onEnable() {
        RegistryHandler();
    }

    public boolean isAirOrNull(ItemStack item) {
        return item == null || item.getType().equals(Material.AIR);
    }

    public boolean isAirOrNull(ItemStack[] items) {
        for (ItemStack i : items) {
            if (i == null || i.getType().equals(Material.AIR)) {
                return true;
            }
        }
        return false;
    }

    public String convertColoredString(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public boolean IsFullCustomSet(String tag, PlayerInventory inventory) {
        if (WearingFullSet(inventory)) {
            return false;
        }

        return new NBTItem(inventory.getHelmet()).getString("CustomItemID").equals(tag) &&
                new NBTItem(inventory.getChestplate()).getString("CustomItemID").equals(tag) &&
                new NBTItem(inventory.getLeggings()).getString("CustomItemID").equals(tag) &&
                new NBTItem(inventory.getBoots()).getString("CustomItemID").equals(tag);
    }

    public boolean WearingFullSet(PlayerInventory inventory) {
        return isAirOrNull(new ItemStack[]{inventory.getHelmet(), inventory.getChestplate(), inventory.getLeggings(), inventory.getBoots()});
    }

    public void ArmorChecker() {
        new BukkitRunnable() {
            public void run() {
                armorSetAbilities.isWearingNetherArmor(getServer().getOnlinePlayers().toArray());
            }
        }.runTaskTimer(this, 0, 20);
    }

    public Boolean matchingCustomItem(ItemStack item, String itemID) {
        return !isAirOrNull(item) && new NBTItem(item).getString("CustomItemID").equals(itemID);
    }

    public void RegistryHandler() {

        new MainListener(this);
        new MaterialsListener(this);
        new MoreArmorsListener(this);
        new CraftHandler(this);
        new DamageHandler(this);

        hologramHandler = new HologramHandler(this);
        armorConstructor = new ArmorConstructor(this);
        armorSets = new ArmorSets(this);
        armorSetAbilities = new ArmorSetAbilityHandler(this);
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
        armorSets.NetherArmorRecipes();
        armorSets.SeaGreedArmorRecipes();
        armorSets.SpeedsterArmorRecipes();
        armorSets.TitanArmorRecipes();
        armorSets.TitanBoots();

        ArmorChecker();
        RegisterMaterials();
        removeHolograms();
    }

    public void removeHolograms() {
        for (World world : getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if(entity.getType().equals(EntityType.ARMOR_STAND) || entity.getType().equals(EntityType.BAT)) {
                    PersistentDataContainer pdc = entity.getPersistentDataContainer();
                    NamespacedKey key = new NamespacedKey(this, "HologramEntity");
                    if(Boolean.TRUE.equals(pdc.get(key, PersistentDataType.BOOLEAN))) {
                        entity.remove();
                    }
                }
            }
        }
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
