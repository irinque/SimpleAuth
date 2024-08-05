package me.irinque.simpleauth.loaders;

import me.irinque.simpleauth.Main;
import me.irinque.simpleauth.commands.ChangePassword;
import me.irinque.simpleauth.commands.Login;
import me.irinque.simpleauth.commands.Register;
import me.irinque.simpleauth.commands.Reload;
import me.irinque.simpleauth.tabs.ChangePasswordTabComplete;
import me.irinque.simpleauth.tabs.LoginTabCompliter;
import me.irinque.simpleauth.tabs.RegisterTabCompliter;
import me.irinque.simpleauth.tabs.ReloadTabComplete;

public class CommandsLoader implements Runnable
{
    static Main plugin = Main.getInstance();

    public void run()
    {
        plugin.getServer().getPluginCommand("register").setExecutor(new Register());
        plugin.getServer().getPluginCommand("register").setTabCompleter(new RegisterTabCompliter());
        plugin.getServer().getPluginCommand("login").setExecutor(new Login());
        plugin.getServer().getPluginCommand("login").setTabCompleter(new LoginTabCompliter());
        plugin.getServer().getPluginCommand("reload-simpleauth").setExecutor(new Reload());
        plugin.getServer().getPluginCommand("reload-simpleauth").setTabCompleter(new ReloadTabComplete());
        plugin.getServer().getPluginCommand("changepassword").setExecutor(new ChangePassword());
        plugin.getServer().getPluginCommand("changepassword").setTabCompleter(new ChangePasswordTabComplete());
    }
}
