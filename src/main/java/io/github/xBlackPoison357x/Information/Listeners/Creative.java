package io.github.xBlackPoison357x.Information.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Creative implements Listener {
	public UltimatePlugin plugin;

	public Creative(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChangeWorld(PlayerMoveEvent event) {
		String msg = ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Creative Denied");
		Player p = event.getPlayer();
		if (plugin.getInformationConfig().getBoolean("Disabled Creative Worlds.world_the_end")
				&& (!p.isOp() || !p.hasPermission("information.creativebypass.end"))
				&& p.getWorld().getName().endsWith("end")
				&& p.getGameMode().equals(GameMode.CREATIVE)) {
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage(msg);
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Creative Worlds.world_nether")
				&& (!p.isOp() || !p.hasPermission("information.creativebypass.nether"))
				&& p.getWorld().getName().endsWith("nether")
				&& p.getGameMode().equals(GameMode.CREATIVE)) {
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage(msg);
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Creative Worlds.world")
				&& (!p.isOp() || !p.hasPermission("information.creativebypass.world"))
				&& p.getWorld().getName().endsWith("world")
				&& p.getGameMode().equals(GameMode.CREATIVE)) {
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage(msg);
		}
	}
}
