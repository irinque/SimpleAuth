package me.irinque.simpleauth.loaders;

import me.irinque.simpleauth.Main;
import java.io.File;


public class ConfigLoader implements Runnable
{
    File configFile = new File("plugins/SimpleAuth/config.yml");
    public File get_file_config() {return configFile;}

    static Main plugin = Main.getInstance();

    public void run()
    {

        if (!get_file_config().exists() || (plugin.getConfig().getString("message.NoPermission") == null) || (plugin.getConfig().getString("message.PasswordChanged") == null))
        {
            plugin.getConfig().set("message.Success", "You have successfully logged in!");
            plugin.getConfig().set("message.Login", "You need to log in! - /l");
            plugin.getConfig().set("message.Login2", "/login <password>");
            plugin.getConfig().set("message.Register", "It is necessary to register! - /r");
            plugin.getConfig().set("message.Register2", "/register <password>");
            plugin.getConfig().set("message.Cancel", "Failed login attempt!");
            plugin.getConfig().set("message.AlreadyReg", "You are already registered!");
            plugin.getConfig().set("message.AlreadyLog", "You are already logged in!");
            plugin.getConfig().set("message.SuccessfulLog", "You have successfully logged in, have a nice game!");
            plugin.getConfig().set("message.SuccessfulReg", "You have successfully registered, have a nice game!");
            plugin.getConfig().set("message.Reload", "Plugin config successfully reloaded.");
            plugin.getConfig().set("message.NoPermission", "You do not have permission to use this command.");
            plugin.getConfig().set("message.PasswordChanged", "The password has been successfully changed!"); //v1.0.1
            plugin.getConfig().set("message.ConfirmPassword", "Enter your old password to confirm!");
            plugin.getConfig().set("message.EmptyPassword", "The password field must not be empty!");

            plugin.saveConfig();
        }

    }
}
