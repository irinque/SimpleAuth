package me.irinque.simpleauth.commands;

import me.irinque.simpleauth.loaders.PlayersConfigLoader;
import me.irinque.simpleauth.parsers.MessageParser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SimpleAuth implements CommandExecutor {
    static me.irinque.simpleauth.SimpleAuth simpleAuth = me.irinque.simpleauth.SimpleAuth.getInstance();
    PlayersConfigLoader playersConfigLoader = simpleAuth.getPlayersConfigLoader();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args[0].equals("reload") & player.hasPermission("simpleauth.reload")) {
            simpleAuth.reloadConfig();
            playersConfigLoader.reloadPlayersConfig();
            player.sendMessage(MessageParser.getMessage("Reload"));
        }
        else if (!player.hasPermission("simpleauth.reload")) {
            player.sendMessage(MessageParser.getMessage("PermissionError"));
        }
        return true;
    }
}
