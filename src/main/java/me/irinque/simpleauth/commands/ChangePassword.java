package me.irinque.simpleauth.commands;

import me.irinque.simpleauth.SimpleAuth;
import me.irinque.simpleauth.loaders.PlayersConfigLoader;
import me.irinque.simpleauth.parsers.MessageParser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangePassword implements CommandExecutor {
    static SimpleAuth simpleAuth = SimpleAuth.getInstance();
    PlayersConfigLoader playersConfigLoader = simpleAuth.getPlayersConfigLoader();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String playerUniqueId = player.getUniqueId().toString();
        String playerExistance = String.valueOf(playersConfigLoader.getPlayersConfig().get("players." + playerUniqueId));

        if (!playerExistance.equals("null")) {
            if (args.length > 0) {
                if (args[0].toString().equals(playersConfigLoader.getPlayersConfig().get("players." + playerUniqueId + ".password"))) {
                    if (args.length > 1) {
                        playersConfigLoader.getPlayersConfig().set("players." + playerUniqueId + ".password", args[1]);
                        playersConfigLoader.savePlayersConfig();
                        player.sendMessage(MessageParser.getMessage("PasswordChanged"));
                    } else {player.sendMessage(MessageParser.getMessage("PasswordIsEmpty"));}
                } else {player.sendMessage(MessageParser.getMessage("WarningOldPassword"));}
            } else {player.sendMessage(MessageParser.getMessage("PasswordIsEmpty"));}
        } else {
            String messageWarningRegistration = MessageParser.getMessage("WarningRegisterRequired");
            player.sendMessage(messageWarningRegistration);
        }

        return true;
    }
}
