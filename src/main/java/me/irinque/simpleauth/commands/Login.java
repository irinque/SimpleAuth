package me.irinque.simpleauth.commands;

import me.irinque.simpleauth.SimpleAuth;
import me.irinque.simpleauth.loaders.PlayersConfigLoader;
import me.irinque.simpleauth.parsers.MessageParser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Login implements CommandExecutor {
    static SimpleAuth simpleAuth = SimpleAuth.getInstance();
    PlayersConfigLoader playersConfigLoader = simpleAuth.getPlayersConfigLoader();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        String playerUniqueId = player.getUniqueId().toString();
        String playerIp = player.getAddress().getAddress().toString();
        String playerExistance = String.valueOf(playersConfigLoader.getPlayersConfig().get("players." + playerUniqueId));

        if (!playerExistance.equals("null")) {
            if (args.length > 0) {
                if (args[0].toString().equals(playersConfigLoader.getPlayersConfig().get("players." + playerUniqueId + ".password"))) {
                    playersConfigLoader.getPlayersConfig().set("players." + playerUniqueId + ".ip", playerIp);
                    playersConfigLoader.getPlayersConfig().set("players." + playerUniqueId + ".access", true);
                    playersConfigLoader.savePlayersConfig();
                    player.sendMessage(MessageParser.getMessage("SuccessfullyLoggedIn"));
                }
                else {
                    player.kickPlayer(MessageParser.getMessage("AccessDenied"));
                }
            }
            else {
                player.sendMessage(MessageParser.getMessage("PasswordIsEmpty"));
            }
        }
        else {
            String messageWarningRegistration = MessageParser.getMessage("WarningRegisterRequired");
            player.sendMessage(messageWarningRegistration);
        }
        return true;
    }
}

