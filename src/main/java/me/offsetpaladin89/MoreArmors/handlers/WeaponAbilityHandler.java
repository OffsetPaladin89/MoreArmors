package me.offsetpaladin89.MoreArmors.handlers;

import java.util.ArrayList;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.entity.Player;

public class WeaponAbilityHandler {

	private final MoreArmorsMain plugin;
	
	public WeaponAbilityHandler(MoreArmorsMain plugin) {this.plugin = plugin;}
	
	public ArrayList<Player> titansword = new ArrayList<>();
	public ArrayList<Player> titandagger = new ArrayList<>();
	public ArrayList<Player> titanbattleaxe = new ArrayList<>();
	public ArrayList<Player> titanbow = new ArrayList<>();
	
	public ArrayList<Player> netherbattleaxe = new ArrayList<>();
	
	public void TitanSwordAbility(Player player) {
		titansword.add(player);
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> titansword.remove(player), 100L);
	}
	
	public void TitanBowAbility(Player player) {
		titanbow.add(player);
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> titanbow.remove(player), 100L);
	}
	
	public void TitanDaggerAbility(Player player) {
		titandagger.add(player);
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> titandagger.remove(player), 100L);
	}
	
	public void TitanBattleAxeAbility(Player player) {
		titanbattleaxe.add(player);
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> titanbattleaxe.remove(player), 60L);
	}
	
	public void NetherBattleAxeAbility(Player player) {
		netherbattleaxe.add(player);
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> netherbattleaxe.add(player), 60L);
	}
}
