package org.nikerino.menulib;

import org.bukkit.plugin.java.JavaPlugin;

public final class MenuLib
{
    public static void init(JavaPlugin plugin)
    {
        MenuFactory.plugin = plugin;
    }
}
