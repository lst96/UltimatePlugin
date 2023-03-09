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
		String msg = ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Flight Denied");
		Player p = event.getPlayer();
		Environment e = p.getWorld().getEnvironment();
		if (plugin.getInformationConfig().getBoolean("Disabled Flight Worlds.world_the_end")
				&& (!p.isOp() || !p.hasPermission("information.flightbypass.end"))
				&& e.equals(Environment.THE_END) && p.isFlying()) {
			p.setAllowFlight(false);
			p.setFlying(false);
			p.sendMessage(msg);
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Flight Worlds.world_nether")
				&& (!p.isOp() || !p.hasPermission("information.flightbypass.nether"))
				&& e.equals(Environment.NETHER) && p.isFlying()) {
			p.setAllowFlight(false);
			p.setFlying(false);
			p.sendMessage(msg);
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Flight Worlds.world")
				&& (!p.isOp() || !p.hasPermission("information.flightbypass.world"))
				&& e.equals(Environment.NORMAL) && p.isFlying()) {
			p.setAllowFlight(false);
			p.setFlying(false);
			p.sendMessage(msg);
		}
	}
}
