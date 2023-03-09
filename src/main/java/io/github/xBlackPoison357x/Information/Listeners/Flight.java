package io.github.xBlackPoison357x.Information.Listeners;

import org.bukkit.ChatColor;
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

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChangeWorld(PlayerMoveEvent event) {
		String msg = ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Flight Denied");
		Player p = event.getPlayer();
		if (plugin.getInformationConfig().getBoolean("Disabled Flight Worlds.world_the_end")
				&& (!p.isOp() || !p.hasPermission("information.flightbypass.end"))
				&& p.getWorld().getName().endsWith("end") && p.isFlying()) {
			p.setAllowFlight(false);
			p.setFlying(false);
			p.sendMessage(msg);
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Flight Worlds.world_nether")
				&& (!p.isOp() || !p.hasPermission("information.flightbypass.nether"))
				&& p.getWorld().getName().endsWith("nether") && p.isFlying()) {
			p.setAllowFlight(false);
			p.setFlying(false);
			p.sendMessage(msg);
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Flight Worlds.world")
				&& (!p.isOp() || !p.hasPermission("information.flightbypass.world"))
				&& p.getWorld().getName().endsWith("world") && p.isFlying()) {
			p.setAllowFlight(false);
			p.setFlying(false);
			p.sendMessage(msg);
		}
	}
}
