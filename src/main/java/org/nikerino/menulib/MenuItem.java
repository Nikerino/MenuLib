package org.nikerino.menulib;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MenuItem
{
    private String title;
    public String getTitle() { return this.title; }

    private List<String> lores;
    public List<String> getLores() { return this.lores; }

    private ItemStack item;
    public ItemStack getItem() { return this.item; }

    public MenuItem(ItemStack item, String title, String ... lore)
    {
        this.item = item;
        this.title = title;
        this.lores = new ArrayList<String>();

        for (String l : lore)
        {
            this.lores.add(l);
        }

        ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName(title);
        meta.setLore(this.lores);

        this.item.setItemMeta(meta);
    }
}
