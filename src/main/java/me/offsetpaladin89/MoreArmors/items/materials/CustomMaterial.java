package me.offsetpaladin89.MoreArmors.items.materials;

import de.tr7zw.changeme.nbtapi.NBT;
import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import me.offsetpaladin89.MoreArmors.enums.MaterialType;
import me.offsetpaladin89.MoreArmors.enums.Rarity;
import me.offsetpaladin89.MoreArmors.items.misc.CustomItem;
import me.offsetpaladin89.MoreArmors.utils.Lore;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomMaterial extends CustomItem {

    protected MaterialType materialID;
    protected ItemStack previousItem;

    protected CustomMaterial(Rarity rarity, int tier, MaterialType materialID) {
        super(Rarity.getRarity(rarity.ordinal() + tier), tier);
        this.materialID = materialID;
        this.baseMaterial = materialID.baseMaterial;
        this.item = new ItemStack(baseMaterial);

        if(tier == 0 && materialID.isBasic) this.previousItem = new ItemStack(baseMaterial);
        else if(tier <= materialID.maxTier && tier > 0) this.previousItem = getPrevious(tier);

        createItem();
    }

    protected void createItem() {
        setTexture();
        setDisplayName();
        setLore();
        addGlowing();
        baseNBT();
    }

    // Material Creation

    protected void setLore() {
        ItemMeta itemMeta = item.getItemMeta();

        Lore lore = new Lore();
        lore.addMaterialRarity(rarity);

        itemMeta.setLore(lore.getLore());

        item.setItemMeta(itemMeta);
    }

    protected void baseNBT() {
        NBT.modify(item, nbt -> {
            nbt.setEnum("Rarity", rarity);
            nbt.setEnum("MaterialID", materialID);
            nbt.setInteger("Tier", tier);
        });
    }

    public void setAmount(int amount) {
        item.setAmount(amount);
    }

    public String getID() {
        return materialID.toString().toLowerCase();
    }

    public MaterialType getType() {
        return this.materialID;
    }

    protected ItemStack getPrevious() {
        return previousItem;
    }

    public static void getRecipe(MaterialType type, MoreArmorsMain plugin) {
        for(int i = 0; i <= type.maxTier; i++) {
            CustomMaterial result = MaterialType.getMaterial(type, i);
            if (result == null || result.getPrevious() == null) return;

            NamespacedKey key = new NamespacedKey(plugin, String.format("%s_%d", result.getID(), i));

            ShapedRecipe recipe = new ShapedRecipe(key, result.getItem());
            recipe.shape("AAA", "AAA", "AAA");
            recipe.setIngredient('A', new RecipeChoice.ExactChoice(result.getPrevious()));

            plugin.getServer().addRecipe(recipe);
        }
    }

    // Overridden Methods

    protected void setTexture() {
    }

    protected ItemStack getPrevious(int tier) {
        return null;
    }
}
