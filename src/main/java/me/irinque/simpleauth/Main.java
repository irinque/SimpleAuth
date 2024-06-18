package me.irinque.simpleauth;

import me.irinque.simpleauth.handlers.MainHandler;
import me.irinque.simpleauth.loaders.CommandsLoader;
import me.irinque.simpleauth.loaders.ConfigLoader;
import me.irinque.simpleauth.loaders.PlayersLoader;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    File plugin_directory = new File("plugins/SimpleAuth");
    public File get_plugin_directory() {return plugin_directory;}
    File file_players = new File("plugins/SimpleAuth/players.yml");
    FileConfiguration config_players = YamlConfiguration.loadConfiguration(file_players);
    public FileConfiguration get_config_players() {return config_players;}
    public File get_file_players() {return file_players;}

    public static Main instance;
    public static Main getInstance()
    {return instance;}

    @Override
    public void onEnable()
    {
        if (instance == null) {instance = this;}

        if (!get_plugin_directory().exists())
        {get_plugin_directory().mkdirs();}

        if (!get_file_players().exists())
        {try {get_file_players().createNewFile();}
        catch (IOException e) {throw new RuntimeException(e);}}

        Bukkit.getPluginManager().registerEvents(new MainHandler(), this);
        Bukkit.getScheduler().runTaskAsynchronously(this, new ConfigLoader());
        Bukkit.getScheduler().runTaskAsynchronously(this, new CommandsLoader());
        Bukkit.getScheduler().runTaskAsynchronously(this, new PlayersLoader());
        getServer().getLogger().info("§a[SimpleAuth] Plugin is ready!");
    }

    @Override
    public void onDisable()
    {
        if (instance != null) {instance = null;}
        getServer().getLogger().info("§4[SimpleAuth] Plugin was shutdown!");
    }
}
