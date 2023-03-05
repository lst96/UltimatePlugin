package io.github.xBlackPoison357x.DisableEXP.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class FurnaceExtractEvents implements Listener {
	public UltimatePlugin plugin;

	public FurnaceExtractEvents(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onSmelt(FurnaceExtractEvent e) {
		if (plugin.getDisableEXPConfig().getBoolean("EXP.Smelt")) {
			e.setExpToDrop(0);
		}
	}
}
