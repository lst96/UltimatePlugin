package io.github.xBlackPoison357x.DisableEXP.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class EntityDeathEvents implements Listener {
	private UltimatePlugin plugin;

	public EntityDeathEvents(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (plugin.getDisableEXPConfig().getBoolean("EXP.Mob")) {
			e.setDroppedExp(0);
		}
	}
}
