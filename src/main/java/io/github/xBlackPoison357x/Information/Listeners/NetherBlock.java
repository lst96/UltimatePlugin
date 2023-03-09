package io.github.xBlackPoison357x.Information.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
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
		Player p = event.getPlayer();
		Environment e = p.getWorld().getEnvironment();
		Location playerLoc = p.getLocation();
		String pname = p.getName();
		if (plugin.getInformationConfig().getBoolean("Blocktopofnetherbuilding")
				&& (!p.isOp() || !p.hasPermission("information.netherbuildbypass"))
				&& e.equals(World.Environment.NETHER) && playerLoc.getY() >= 128.0) {
			p.teleport(getWorldSpawn());
			p.sendMessage(ChatColor.RED + "You are not allowed to walk/build on top of nether!");
			Bukkit.broadcast((ChatColor.RED + pname + " attempted to walk/build on top of nether."),
					"information.netherbuild.notify");
		}
	}

	public Location getWorldSpawn() {
		for (final World world : Bukkit.getServer().getWorlds()) {
			if (world.getEnvironment() != World.Environment.NORMAL) {
				continue;
			}
			return world.getSpawnLocation();
		}
		return Bukkit.getServer().getWorlds().get(0).getSpawnLocation();
	}

}