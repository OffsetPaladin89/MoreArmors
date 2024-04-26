package me.offsetpaladin89.MoreArmors.fonts;

import dev.dbassett.skullcreator.SkullCreator;
import me.offsetpaladin89.MoreArmors.Main;
import org.bukkit.inventory.ItemStack;

import static java.text.MessageFormat.format;

public record GrayFont(Main pl) {

	public ItemStack getText(char c) {
		return switch (c) {
			case '!' -> getSkull("18be147df49091736964dab3569b61c9e6a341c9a48f4af84b51a63bb6e85");
			case '←' -> getSkull("86971dd881dbaf4fd6bcaa93614493c612f869641ed59d1c9363a3666a5fa6");
			default -> getSkull("6e5286c470f66ffa1a18331cbffb9a3c2a4424a8c7259c4436fd2e35582a522");
		};
	}

	private ItemStack getSkull(String id) { return SkullCreator.itemFromBase64(format("http://textures.minecraft.net/texture/{0}", id)); }

}
