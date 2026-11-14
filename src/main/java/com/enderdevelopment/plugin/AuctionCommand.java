package com.enderdevelopment.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class AuctionCommand implements CommandExecutor {
    private final AuctionMenu auctionMenu;

    public AuctionCommand(AuctionMenu auctionMenu) {
        this.auctionMenu = auctionMenu;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can open the auction house.");
            return true;
        }
        auctionMenu.open(player);
        return true;
    }
}
