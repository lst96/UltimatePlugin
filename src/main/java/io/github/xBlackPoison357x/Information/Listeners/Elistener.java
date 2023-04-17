package io.github.xBlackPoison357x.Information.Listeners;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Elistener implements Listener {
	public UltimatePlugin plugin;

	public Elistener(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
	    Player player = event.getPlayer();
	    if (player.isOp() || player.hasPermission("information.extra")) {
	        List<String> extras = plugin.getInformationConfig().getStringList("Extra");
	        player.sendMessage(ChatColor.BLUE + "--Extras--");
	        extras.forEach(extra -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', extra)));
	    }
	}
}
