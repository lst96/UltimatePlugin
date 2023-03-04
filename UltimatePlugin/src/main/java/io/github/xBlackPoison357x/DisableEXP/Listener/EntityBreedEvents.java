package io.github.xBlackPoison357x.DisableEXP.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class EntityBreedEvents implements Listener {
	public UltimatePlugin plugin;

	public EntityBreedEvents(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onBreak(EntityBreedEvent e) {
		if (plugin.getDisableEXPConfig().getBoolean("EXP.Breeding")) {
			e.setExperience(0);
			e.getBreeder().sendMessage("Breeding experience gain has been disabled on this server!");
		}
	}
}
