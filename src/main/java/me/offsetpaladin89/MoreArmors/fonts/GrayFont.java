package me.offsetpaladin89.MoreArmors.fonts;

import dev.dbassett.skullcreator.SkullCreator;
import me.offsetpaladin89.MoreArmors.Main;
import org.bukkit.inventory.ItemStack;

public record GrayFont(Main pl) {

	public ItemStack getText(char c) {
		return switch (c) {
			case '!' -> getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMThiZTE0N2RmNDkwOTE3MzY5NjRkYWIzNTY5YjYxYzllNmEzNDFjOWE0OGY0YWY4NGI1MWE2M2JiNmU4NSJ9fX0=");
			case '←' -> getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY5NzFkZDg4MWRiYWY0ZmQ2YmNhYTkzNjE0NDkzYzYxMmY4Njk2NDFlZDU5ZDFjOTM2M2EzNjY2YTVmYTYifX19");
			default -> getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmU1Mjg2YzQ3MGY2NmZmYTFhMTgzMzFjYmZmYjlhM2MyYTQ0MjRhOGM3MjU5YzQ0MzZmZDJlMzU1ODJhNTIyIn19fQ==");
		};
	}

	private ItemStack getSkull(String id) { return SkullCreator.itemFromBase64(id); }

}
