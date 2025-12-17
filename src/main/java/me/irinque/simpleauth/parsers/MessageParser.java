package me.irinque.simpleauth.parsers;

import me.irinque.simpleauth.SimpleAuth;

public class MessageParser {
    static SimpleAuth simpleAuth = SimpleAuth.getInstance();

    public static String getMessage(String path) {
        String message = simpleAuth.getConfig().getString("message." + path);
        String pluginPrefix = "[SimpleAuth] ";
        return pluginPrefix + message;
    }
}