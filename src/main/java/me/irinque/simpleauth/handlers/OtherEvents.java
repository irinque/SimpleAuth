package me.irinque.simpleauth.handlers;

import me.irinque.simpleauth.SimpleAuth;
import me.irinque.simpleauth.loaders.PlayersConfigLoader;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

public class OtherEvents implements Listener {
    static SimpleAuth simpleAuth = SimpleAuth.getInstance();
    PlayersConfigLoader playersConfigLoader = simpleAuth.getPlayersConfigLoader();

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        String playerUniqueId = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(playerUniqueId));
    }
    @EventHandler(ignoreCancelled = true)
    public void onPlayerBlockBreak(BlockBreakEvent event) {
        String playerUniqueId = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(playerUniqueId));
    }
    @EventHandler(ignoreCancelled = true)
    public void onPlayerBlockPlace(BlockPlaceEvent event) {
        String playerUniqueId = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(playerUniqueId));
    }
    @EventHandler(ignoreCancelled = true)
    public void onHitPlayer(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            String playerUniqueId = ((Player)event.getEntity()).getPlayer().getUniqueId().toString();
            event.setCancelled(CheckStatus(playerUniqueId));
        }
    }
    @EventHandler(ignoreCancelled = true)
    public void onDropItem(PlayerDropItemEvent event) {
        String playerUniqueId = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(playerUniqueId));
    }
    @EventHandler(ignoreCancelled = true)
    public void onPickupItem(PlayerPickupArrowEvent event) {
        String playerUniqueId = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(playerUniqueId));
    }
    @EventHandler(ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event) {
        String playerUniqueId = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(playerUniqueId));
    }
    @EventHandler(ignoreCancelled = true)
    public void onMessage(AsyncPlayerChatEvent event) {
        String playerUniqueId = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(playerUniqueId));
    }
    public boolean CheckStatus(String playerUniqueId)
    {
        String joinedPlayerExistance = String.valueOf(playersConfigLoader.getPlayersConfig().get("players." + playerUniqueId));
        if (joinedPlayerExistance.equals("null")) {return true;}
        else if (String.valueOf(playersConfigLoader.getPlayersConfig().get("players." + playerUniqueId + ".access")).equals("false")) {return true;}
        else {return false;}
    }
}
