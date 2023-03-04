package io.github.xBlackPoison357x.DisableEXP.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class ExpBottleEvents implements Listener {
	private UltimatePlugin plugin;

	public ExpBottleEvents(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onBottle(ExpBottleEvent event) {
		if (plugin.getDisableEXPConfig().getBoolean("EXP.Exp Bottle")) {
			event.setExperience(0);
		}
	}
}
