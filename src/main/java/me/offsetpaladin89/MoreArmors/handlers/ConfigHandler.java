package me.offsetpaladin89.MoreArmors.handlers;

import java.io.File;
import java.io.IOException;

import me.offsetpaladin89.MoreArmors.MoreArmorsMain;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public record ConfigHandler(MoreArmorsMain plugin) {

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
            }
            catch (Exception ignored) {}
        }
        else { config = YamlConfiguration.loadConfiguration(file); }
        return config;
    }

    public void saveConfig(FileConfiguration config, String name) {
        File file = new File(plugin.getDataFolder() + "/" + name + ".yml");
        fixPermissions(file);
        try { config.save(file); }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void fixPermissions(File file) {
        if ((file.canWrite() || file.canRead())) {
            file.setWritable(true, false);
            file.setReadable(true, false);
        }
    }

}