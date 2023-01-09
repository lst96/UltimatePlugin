package io.github.xBlackPoison357x.FrameProtector.Listener;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingPlaceEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class ItemFramePlace implements Listener {
	private UltimatePlugin plugin;

	public ItemFramePlace(UltimatePlugin instance) {
		this.plugin = instance;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void AddConfigEntry(HangingPlaceEvent event) {
			Player p = (Player) event.getPlayer();
			Entity ee = event.getEntity();
			if (event.getEntity().getType() == EntityType.ITEM_FRAME) {
				this.plugin.getFrameProtectorConfig().set("Item Frame.world", p.getUniqueId().toString() + " " + ee.getLocation().getWorld().getName());
				this.plugin.getFrameProtectorConfig().set("Item Frame.x", p.getUniqueId().toString() + " " +  ee.getLocation().getBlockX());
				this.plugin.getFrameProtectorConfig().set("Item Frame.y", p.getUniqueId().toString() + " " + ee.getLocation().getBlockY());
				this.plugin.getFrameProtectorConfig().set("Item Frame.z", p.getUniqueId().toString() + " " + ee.getLocation().getBlockZ());
				Bukkit.broadcastMessage("Code Appears to be working!");
				try {
					this.plugin.getFrameProtectorConfig().save(this.plugin.frameprotectorf);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
	}
}