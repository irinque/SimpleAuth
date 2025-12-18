package me.irinque.simpleauth;

import me.irinque.simpleauth.commands.ChangePassword;
import me.irinque.simpleauth.commands.Login;
import me.irinque.simpleauth.commands.Register;
import me.irinque.simpleauth.handlers.OtherEvents;
import me.irinque.simpleauth.handlers.PlayerJoin;
import me.irinque.simpleauth.loaders.PlayersConfigLoader;
import me.irinque.simpleauth.tabcompleters.ChangePasswordTabCompleter;
import me.irinque.simpleauth.tabcompleters.LoginTabCompleter;
import me.irinque.simpleauth.tabcompleters.RegisterTabCompleter;
import me.irinque.simpleauth.tabcompleters.SimpleAuthTabCompleter;
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
        getServer().getPluginCommand("changepassword").setExecutor(new ChangePassword());
        getServer().getPluginCommand("simpleauth").setExecutor(new me.irinque.simpleauth.commands.SimpleAuth());

        getServer().getPluginCommand("register").setTabCompleter(new RegisterTabCompleter());
        getServer().getPluginCommand("login").setTabCompleter(new LoginTabCompleter());
        getServer().getPluginCommand("changepassword").setTabCompleter(new ChangePasswordTabCompleter());
        getServer().getPluginCommand("simpleauth").setTabCompleter(new SimpleAuthTabCompleter());

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OtherEvents(), this);
    }

    @Override
    public void onDisable() {
        if (instance != null) {instance = this;}
    }
}
