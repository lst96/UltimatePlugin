package io.github.xBlackPoison357x.Information.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class NetherBlock implements Listener {
    public UltimatePlugin plugin;

    public NetherBlock(UltimatePlugin instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (world.getEnvironment() != World.Environment.NETHER) {
            return;
        }
        Location playerLoc = player.getLocation();
        if (playerLoc.getY() >= 128.0 && !player.isOp() || !player.hasPermission("information.netherbuildbypass")
                && plugin.getInformationConfig().getBoolean("Blocktopofnetherbuilding")) {
            player.teleport(getWorldSpawn(world));
            player.sendMessage(ChatColor.RED + "You are not allowed to walk/build on top of the Nether!");
            Bukkit.getOnlinePlayers().stream().filter(p -> p.hasPermission("information.netherbuild.notify"))
                    .forEach(p -> p.sendMessage(ChatColor.RED + player.getName() + " attempted to walk/build on top of the Nether!"));
        }
    }

    private Location getWorldSpawn(World world) {
        for (World w : Bukkit.getServer().getWorlds()) {
            if (w.getEnvironment() == World.Environment.NORMAL) {
                return w.getSpawnLocation();
            }
        }
        return world.getSpawnLocation();
    }
}
