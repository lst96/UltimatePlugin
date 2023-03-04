package io.github.xBlackPoison357x.DisableEXP.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class PlayerExpChangeEvents implements Listener {
	private UltimatePlugin plugin;

	public PlayerExpChangeEvents(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerExpChange(PlayerExpChangeEvent e) {
		if (plugin.getDisableEXPConfig().getBoolean("EXP.ALL")) {
			e.setAmount(0);
		}
	}
}
