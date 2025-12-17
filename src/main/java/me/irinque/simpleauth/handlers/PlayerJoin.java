package me.irinque.simpleauth.handlers;

import me.irinque.simpleauth.SimpleAuth;
import me.irinque.simpleauth.loaders.PlayersConfigLoader;
import me.irinque.simpleauth.parsers.MessageParser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoin implements Listener {
    static SimpleAuth simpleAuth = SimpleAuth.getInstance();
    PlayersConfigLoader playersConfigLoader = simpleAuth.getPlayersConfigLoader();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player joinedPlayer = event.getPlayer();
        String joinedPlayerIp = joinedPlayer.getAddress().getAddress().toString();
        String joinedPlayerUniqueId = joinedPlayer.getUniqueId().toString();
        String joinedPlayerExistance = String.valueOf(playersConfigLoader.getPlayersConfig().get("players." + joinedPlayerUniqueId));
        if (joinedPlayerExistance.equals("null")) {
            String messageWarningRegistration = MessageParser.getMessage("WarningRegisterRequired");
            joinedPlayer.sendMessage(messageWarningRegistration);
        }
        else {
            if (!joinedPlayerIp.equals(playersConfigLoader.getPlayersConfig().get("players." + joinedPlayerUniqueId + ".ip"))) {
                playersConfigLoader.getPlayersConfig().set("players." + joinedPlayerUniqueId + ".access", false);
                String messageWarningLogin = MessageParser.getMessage("WarningLoginRequired");
                joinedPlayer.sendMessage(messageWarningLogin);
            }
            else {
                String messageWarningLogin = MessageParser.getMessage("SuccessfullyLoggedIn");
                joinedPlayer.sendMessage(messageWarningLogin);
            }
        }
    }
}
