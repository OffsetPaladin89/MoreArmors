package me.offsetpaladin89.MoreArmors.handlers;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public record ConfigHandler(MoreArmorsMain plugin) {

	public void saveConfigDefaults(String name, Map<String, Object> defaults) {
		FileConfiguration config = getConfig(name);
		config.addDefaults(defaults);
		config.options().copyDefaults(true);
		saveConfig(config, "config");
	}

	public FileConfiguration getConfig(String name) {
		File file = new File(plugin.getDataFolder() + "/" + name + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		if (!file.exists()) {
			fixPermissions(file);
			try {
				boolean dirCreated = file.mkdir();
				if (dirCreated) {
					file.createNewFile();
					config.save(file);
				}
			} catch (Exception ignored) {}
		} else {config = YamlConfiguration.loadConfiguration(file);}
		return config;
	}


	public void saveConfig(FileConfiguration config, String name) {
		File file = new File(plugin.getDataFolder() + "/" + name + ".yml");
		fixPermissions(file);
		try {config.save(file);} catch (IOException e) {e.printStackTrace();}
	}

	private void fixPermissions(File file) {
		if ((file.canWrite() || file.canRead())) {
			file.setWritable(true, false);
			file.setReadable(true, false);
		}
	}

}