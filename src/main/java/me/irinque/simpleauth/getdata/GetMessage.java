package me.irinque.simpleauth.getdata;

import me.irinque.simpleauth.Main;

public class GetMessage
{
    public static Main plugin = Main.getInstance();

    public GetMessage() {}
    public static String getMsg(String path)
    {
        String message = plugin.getConfig().getString("message." + path);
        String text = "[SimpleAuth] " + message;
        return text;
    }
}