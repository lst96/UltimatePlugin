package io.github.xBlackPoison357x.DisableEXP.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class BlockBreakEvents implements Listener {
	public UltimatePlugin plugin;

	public BlockBreakEvents(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (plugin.getDisableEXPConfig().getBoolean("EXP.Ore")) {
			e.setExpToDrop(0);
		}
	}
}
