package io.github.xBlackPoison357x.DisableEXP.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class PlayerFishEvents implements Listener {
	public UltimatePlugin plugin;

	public PlayerFishEvents(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onFish(PlayerFishEvent e) {
		if (plugin.getDisableEXPConfig().getBoolean("EXP.Fish")) {
			e.setExpToDrop(0);
		}
	}
}
