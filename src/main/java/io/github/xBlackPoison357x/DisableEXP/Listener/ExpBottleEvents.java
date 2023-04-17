package io.github.xBlackPoison357x.DisableEXP.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class ExpBottleEvents implements Listener {
	public UltimatePlugin plugin;

	public ExpBottleEvents(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onBottle(ExpBottleEvent e) {
		if (plugin.getDisableEXPConfig().getBoolean("EXP.Exp Bottle")) {
			e.setExperience(0);
		}
	}
}
