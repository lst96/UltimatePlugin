package io.github.xBlackPoison357x.Information.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Flight implements Listener {
	public UltimatePlugin plugin;

	public Flight(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerChangeWorld(PlayerMoveEvent event) {
	    Player player = event.getPlayer();
	    Environment environment = player.getWorld().getEnvironment();
	    String permissionMsg = ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Flight Denied");

	    if (!player.isFlying()) {
	        return;
	    }

	    if (plugin.getInformationConfig().getBoolean("Disabled Flight Worlds.world_the_end") &&
	            environment == Environment.THE_END &&
	            !player.hasPermission("information.flightbypass.end")) {
	        denyFlight(player, permissionMsg);
	    }

	    if (plugin.getInformationConfig().getBoolean("Disabled Flight Worlds.world_nether") &&
	            environment == Environment.NETHER &&
	            !player.hasPermission("information.flightbypass.nether")) {
	        denyFlight(player, permissionMsg);
	    }

	    if (plugin.getInformationConfig().getBoolean("Disabled Flight Worlds.world") &&
	            environment == Environment.NORMAL &&
	            !player.hasPermission("information.flightbypass.world")) {
	        denyFlight(player, permissionMsg);
	    }
	}

	private void denyFlight(Player player, String message) {
	    player.setAllowFlight(false);
	    player.setFlying(false);
	    player.sendMessage(message);
	}
}

