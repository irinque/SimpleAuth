package me.irinque.simpleauth.loaders;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayersConfigLoader {
    private File pluginDirectory = new File("plugins/SimpleAuth");
    private File playersFile = new File(pluginDirectory, "players.yml");
    private FileConfiguration playersConfig;

    public void initPlayers() {
        if (!pluginDirectory.exists()) {
            pluginDirectory.mkdirs();
        }
        if (!playersFile.exists()) {
            try {
                playersFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        playersConfig = YamlConfiguration.loadConfiguration(playersFile);
    }
    public FileConfiguration getPlayersConfig() {
        if (playersConfig == null) {
            reloadPlayersConfig();
        }
        return playersConfig;
    }

    public void reloadPlayersConfig() {
        playersConfig = YamlConfiguration.loadConfiguration(playersFile);
    }
    public void savePlayersConfig() {
        if (playersConfig == null) {
            return;
        }
        try {
            playersConfig.save(playersFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
