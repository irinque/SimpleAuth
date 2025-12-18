package me.irinque.simpleauth.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class ChangePasswordTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return List.of("(old_password)");
        }
        else if (args.length == 2) {
            return List.of("(new_password)");
        }
        else {
            return List.of();
        }
    }
}
