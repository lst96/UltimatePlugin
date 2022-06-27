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

public class NetherBlock
implements Listener {
    private UltimatePlugin plugin;

    public NetherBlock(UltimatePlugin instance) {
        this.plugin = instance;
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {
    	Player p = event.getPlayer();
        Location playerLoc = p.getLocation();
        World world = Bukkit.getWorld("world");
        Location loc = world.getSpawnLocation();
        String pname = p.getName();
        if (this.plugin.getInformationConfig().getBoolean("Blocktopofnetherbuilding") && (!event.getPlayer().isOp() || !event.getPlayer().hasPermission("information.netherbuildbypass")) && event.getPlayer().getWorld().getName().endsWith("nether") && playerLoc.getY() == 128.0) {
            event.getPlayer().teleport(loc);
            event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to walk/build on top of nether!");
            Bukkit.broadcast((ChatColor.RED + pname + " attempted to walk/build on top of nether."), "information.netherbuild.notify");
        }
    }
}

