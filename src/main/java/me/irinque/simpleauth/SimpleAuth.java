package me.irinque.simpleauth;

import me.irinque.simpleauth.commands.Login;
import me.irinque.simpleauth.commands.Register;
import me.irinque.simpleauth.handlers.PlayerJoin;
import me.irinque.simpleauth.loaders.PlayersConfigLoader;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleAuth extends JavaPlugin {
    public static SimpleAuth instance;
    public static SimpleAuth getInstance() {return instance;}
    private PlayersConfigLoader playersConfigLoader;

    public PlayersConfigLoader getPlayersConfigLoader() {
        return playersConfigLoader;
    }

    @Override
    public void onEnable() {
        if (instance == null) {instance = this;}
        saveDefaultConfig();
        playersConfigLoader = new PlayersConfigLoader();
        playersConfigLoader.initPlayers();

        getServer().getPluginCommand("register").setExecutor(new Register());
        getServer().getPluginCommand("login").setExecutor(new Login());
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    @Override
    public void onDisable() {
        if (instance != null) {instance = this;}
    }
}
