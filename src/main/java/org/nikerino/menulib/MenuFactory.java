package org.nikerino.menulib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuFactory
{
    static JavaPlugin plugin;

    public static Menu createMenu(String name, int height)
    {
        Menu newMenu = new Menu(name, height);
        Bukkit.getPluginManager().registerEvents(newMenu, MenuFactory.plugin);
        return newMenu;
    }
}
