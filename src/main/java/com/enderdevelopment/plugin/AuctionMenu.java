package com.enderdevelopment.plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class AuctionMenu {
    private final Main plugin;
    private final Map<UUID, BukkitTask> particleTasks = new HashMap<>();

    public AuctionMenu(Main plugin) {
        this.plugin = plugin;
    }

    public void open(Player player) {
        AuctionHolder holder = new AuctionHolder();
        Inventory inventory = Bukkit.createInventory(holder, 27, Component.text("Auction House").color(NamedTextColor.GOLD));
        holder.setInventory(inventory);
        inventory.setItem(10, createListing(Material.FOX_SPAWN_EGG, "A FoX Spaween Egg", "1,000,000 coins"));
        inventory.setItem(12, createListing(Material.DIAMOND_SWORD, "Sharpness Sword", "250,000 coins"));
        inventory.setItem(14, createListing(Material.ENCHANTED_GOLDEN_APPLE, "Enchanted Golden Apple", "75,000 coins"));
        inventory.setItem(16, createListing(Material.NETHERITE_INGOT, "Netherite Ingot", "100,000 coins"));
        inventory.setItem(22, createButton(Material.BARRIER, "Close"));
        player.openInventory(inventory);
        startParticles(player);
    }

    private ItemStack createListing(Material material, String name, String price) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.displayName(Component.text(name).color(NamedTextColor.AQUA));
            meta.lore(java.util.List.of(
                    Component.text("Auction listing").color(NamedTextColor.GRAY),
                    Component.text("Price: " + price).color(NamedTextColor.YELLOW),
                    Component.text("Click to purchase").color(NamedTextColor.GREEN)));
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createButton(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.displayName(Component.text(name).color(NamedTextColor.RED));
            item.setItemMeta(meta);
        }
        return item;
    }

    private void startParticles(Player player) {
        stopParticles(player);
        UUID playerId = player.getUniqueId();
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            private double angle;

            @Override
            public void run() {
                if (!player.isOnline() || !(player.getOpenInventory().getTopInventory().getHolder() instanceof AuctionHolder)) {
                    stopParticles(player);
                    return;
                }
                Location center = player.getLocation().add(0, 1.0, 0);
                for (int point = 0; point < 8; point++) {
                    double current = angle + (Math.PI * 2 * point / 8);
                    Location particleLocation = center.clone().add(Math.cos(current) * 1.2, Math.sin(current * 2) * 0.35, Math.sin(current) * 1.2);
                    player.getWorld().spawnParticle(Particle.DUST, particleLocation, 1, new Particle.DustOptions(Color.ORANGE, 1.0F));
                }
                angle += Math.PI / 12;
            }
        }, 0L, 2L);
        particleTasks.put(playerId, task);
    }

    public void stopParticles(Player player) {
        BukkitTask task = particleTasks.remove(player.getUniqueId());
        if (task != null) {
            task.cancel();
        }
    }

    public void stopAllParticles() {
        particleTasks.values().forEach(BukkitTask::cancel);
        particleTasks.clear();
    }
}
