package org.nikerino.menulib;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.function.Consumer;

public class Menu implements Listener
{
    private final String name;
    public String getName() { return this.name; }

    private final int width = 9;
    public int getWidth() { return this.width; }

    private final int height;
    public int getHeight() { return this.height; }

    private MenuItem[] slots;

    private Consumer<InventoryClickEvent>[] slotHandlers;

    Menu(String name, int height)
    {
        this.name = name;
        this.height = height;

        this.slots = new MenuItem[this.width*this.height];
        Arrays.fill(this.slots, null);

        this.slotHandlers = new Consumer[this.width*this.height];
        Arrays.fill(this.slotHandlers, null);
    }

    public void open(Player player)
    {
        Inventory inventory = Bukkit.createInventory(player, this.width*this.height, this.name);
        for (int slot = 0; slot < this.slots.length; slot++)
        {
            if (this.slots[slot] != null)
            {
                MenuItem item = this.slots[slot];
                inventory.setItem(slot, item.getItem());
            }
        }
        player.openInventory(inventory);
    }

    public void addMenuItem(MenuItem item, int slot, Consumer<InventoryClickEvent> handler)
    {
        this.slots[slot] = item;
        this.slotHandlers[slot] = handler;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        if (event.getView().getTitle().equals(this.name))
        {
            event.setCancelled(true);

            // For some reason, inventory clicks register slots OUTSIDE of the inventory
            // This check makes sure that the click was inside a valid inventory slot.
            if (event.getSlot() < 0 || event.getSlot() >= this.slots.length)
            {
                return;
            }

            Consumer<InventoryClickEvent> handler = this.slotHandlers[event.getSlot()];
            if (handler != null)
            {
                handler.accept(event);
            }
        }
    }
}
