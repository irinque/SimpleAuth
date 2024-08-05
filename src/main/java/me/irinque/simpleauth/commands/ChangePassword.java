package me.irinque.simpleauth.commands;

import me.irinque.simpleauth.Main;
import me.irinque.simpleauth.getdata.GetMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class ChangePassword implements CommandExecutor
{
    static Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player)sender;
        String UUID = player.getUniqueId().toString();
        String IP = player.getAddress().getAddress().toString();
        if (String.valueOf(plugin.get_config_players().get("players-data." + UUID)) != "null")
        {
            if (args.length > 0)
            {
                if (args[0].toString().equals(plugin.get_config_players().getString("players-data." + UUID + ".password")))
                {
                    if (args.length > 1)
                    {
                        plugin.get_config_players().set("players-data." + UUID + ".password", args[1]);
                        plugin.get_config_players().set("players-data." + UUID + ".ip", IP);
                        plugin.get_config_players().set("players-data." + UUID + ".login-status", "true");
                        try {
                            plugin.get_config_players().save(plugin.get_file_players());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        player.sendMessage(ChatColor.GREEN + GetMessage.getMsg("PasswordChanged"));
                    }
                    else
                    {
                        player.sendMessage(ChatColor.RED + GetMessage.getMsg("EmptyPassword"));
                    }
                }
                else
                {
                    player.sendMessage(ChatColor.RED + GetMessage.getMsg("ConfirmPassword"));
                }
            }
            else
            {
                player.sendMessage(ChatColor.RED + GetMessage.getMsg("ConfirmPassword"));
            }
        }
        else
        {
            player.sendMessage(ChatColor.RED + GetMessage.getMsg("Register"));
            player.sendMessage(ChatColor.RED + GetMessage.getMsg("Register2"));
        }
        return true;
    }
}
