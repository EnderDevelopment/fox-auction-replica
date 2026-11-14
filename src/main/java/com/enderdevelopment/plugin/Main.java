package com.enderdevelopment.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private AuctionMenu auctionMenu;

    @Override
    public void onEnable() {
        auctionMenu = new AuctionMenu(this);
        AuctionCommand command = new AuctionCommand(auctionMenu);
        if (getCommand("ah") != null) {
            getCommand("ah").setExecutor(command);
        }
        if (getCommand("foxah") != null) {
            getCommand("foxah").setExecutor(command);
        }
        Bukkit.getPluginManager().registerEvents(new AuctionListener(auctionMenu), this);
    }

    @Override
    public void onDisable() {
        if (auctionMenu != null) {
            auctionMenu.stopAllParticles();
        }
    }
}
