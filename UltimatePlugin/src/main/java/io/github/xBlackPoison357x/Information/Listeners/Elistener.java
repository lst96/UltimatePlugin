package io.github.xBlackPoison357x.Information.Listeners;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Elistener implements Listener {
	private UltimatePlugin plugin;

	public Elistener(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (event.getPlayer().isOp() || event.getPlayer().hasPermission("information.extra")) {
			List<String> Extra = plugin.getInformationConfig().getStringList("Extra");
			event.getPlayer().sendMessage(ChatColor.DARK_BLUE + "--Extra(s)--");
			for (String Extra1 : Extra) {
				event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes((char) '&', Extra1));
			}
			return;
		}
	}
}
