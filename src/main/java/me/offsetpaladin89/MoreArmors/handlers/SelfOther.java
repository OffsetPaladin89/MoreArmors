package me.offsetpaladin89.MorePluginsCore.handlers;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum SelfOther {
	SELF, OTHER;
	
	public static SelfOther matchType(final CommandSender senderplayer, final Player target) {
		if(Bukkit.getPlayerExact(senderplayer.getName()) != null) {
			if(senderplayer.equals(target)) return SELF;
		}
		return OTHER;
	}
}
