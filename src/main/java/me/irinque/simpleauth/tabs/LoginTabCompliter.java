package me.irinque.simpleauth.tabs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class LoginTabCompliter implements TabCompleter
{
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String alias, String[] args) {
        if (args.length == 1)
        {
            return List.of("(password)");
        }
        if (args.length > 1)
        {
            return List.of("");
        }
        return null;
    }
}