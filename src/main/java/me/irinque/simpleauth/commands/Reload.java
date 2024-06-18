package me.irinque.simpleauth.commands;

import me.irinque.simpleauth.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.irinque.simpleauth.getdata.GetMessage;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor
{
    static Main plugin = Main.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;
        if (player.hasPermission("reload-simpleauth"))
        {
            plugin.reloadConfig();
            player.sendMessage(ChatColor.GREEN + GetMessage.getMsg("Reload"));
        }
        else
        {
            player.sendMessage(ChatColor.RED + GetMessage.getMsg("NoPermission"));
        }
        return true;
    }
}
