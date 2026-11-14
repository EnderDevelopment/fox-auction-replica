package com.enderdevelopment.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class AuctionListener implements Listener {
    private final AuctionMenu auctionMenu;

    public AuctionListener(AuctionMenu auctionMenu) {
        this.auctionMenu = auctionMenu;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getView().getTopInventory().getHolder() instanceof AuctionHolder)) {
            return;
        }
        event.setCancelled(true);
        if (!(event.getWhoClicked() instanceof Player player) || event.getClickedInventory() != event.getView().getTopInventory()) {
            return;
        }
        if (event.getSlot() == 22) {
            player.closeInventory();
            return;
        }
        if (event.getCurrentItem() != null && event.getSlot() >= 10 && event.getSlot() <= 16) {
            player.sendMessage(Component.text("That listing is a replica display item.").color(NamedTextColor.YELLOW));
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player player && event.getInventory().getHolder() instanceof AuctionHolder) {
            auctionMenu.stopParticles(player);
        }
    }
}
