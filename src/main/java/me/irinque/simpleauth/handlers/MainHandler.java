package me.irinque.simpleauth.handlers;

import me.irinque.simpleauth.Main;
import me.irinque.simpleauth.getdata.GetMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

import java.io.IOException;

public class MainHandler implements Listener
{
    static Main plugin = Main.getInstance();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        String UUID = player.getUniqueId().toString();
        String IP = player.getAddress().getAddress().toString();
        String is_user = String.valueOf(plugin.get_config_players().get("players-data." + UUID));

        if (!is_user.equals("null"))
        {
            if (IP.equals(plugin.get_config_players().getString(String.join(".", "players-data", UUID, "ip"))))
            {
                player.sendMessage(ChatColor.GREEN + GetMessage.getMsg("Success"));
            }
            else
            {
                // IP Отличается
                player.sendMessage(ChatColor.RED + GetMessage.getMsg("Login"));
                player.sendMessage(ChatColor.RED + GetMessage.getMsg("Login2"));
                plugin.get_config_players().set("players-data." + UUID + ".login-status", "false");
                try {
                    plugin.get_config_players().save(plugin.get_file_players());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        else
        {
            player.sendMessage(ChatColor.RED + GetMessage.getMsg("Register"));
            player.sendMessage(ChatColor.RED + GetMessage.getMsg("Register2"));
        }
    }
    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event)
    {
        String uuid = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(uuid));
    }
    @EventHandler(ignoreCancelled = true)
    public void onPlayerBlockBreak(BlockBreakEvent event)
    {
        String uuid = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(uuid));
    }
    @EventHandler(ignoreCancelled = true)
    public void onPlayerBlockPlace(BlockPlaceEvent event)
    {
        String uuid = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(uuid));
    }
    @EventHandler(ignoreCancelled = true)
    public void onHitPlayer(EntityDamageEvent event)
    {
        if (event.getEntity() instanceof Player) {
            String uuid = ((Player)event.getEntity()).getPlayer().getUniqueId().toString();
            event.setCancelled(CheckStatus(uuid));
        }
    }
    @EventHandler(ignoreCancelled = true)
    public void ondropItem(PlayerDropItemEvent event) {
        String uuid = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(uuid));
    }
    @EventHandler(ignoreCancelled = true)
    public void onpickupItem(PlayerPickupArrowEvent event) {
        String uuid = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(uuid));
    }
    @EventHandler(ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event) {
        String uuid = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(uuid));
    }
    @EventHandler(ignoreCancelled = true)
    public void onMessage(AsyncPlayerChatEvent event) {
        String uuid = event.getPlayer().getUniqueId().toString();
        event.setCancelled(CheckStatus(uuid));
    }
    public boolean CheckStatus(String UUID)
    {
        String is_user = String.valueOf(plugin.get_config_players().get("players-data." + UUID));
        if (is_user.equals("null")) {return true;}
        else if (String.valueOf(plugin.get_config_players().get("players-data." + UUID + ".login-status")).equals("false")) {return true;}
        else {return false;}
    }
}
