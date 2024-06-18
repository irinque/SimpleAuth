package me.irinque.simpleauth.loaders;

import me.irinque.simpleauth.Main;

import java.io.IOException;

public class PlayersLoader implements Runnable
{
    static Main plugin = Main.getInstance();

    public void run()
    {

        plugin.get_config_players().set("players-data.uuid.password", "password");
        plugin.get_config_players().set("players-data.uuid.ip", "ip");
        plugin.get_config_players().set("players-data.uuid.login-status", "status");
        try {
            plugin.get_config_players().save(plugin.get_file_players());
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

}
