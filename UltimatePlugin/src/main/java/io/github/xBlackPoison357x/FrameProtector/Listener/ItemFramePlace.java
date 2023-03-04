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
	public UltimatePlugin plugin;

	public ItemFramePlace(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.HIGHEST) // fix this
	public void AddConfigEntry(HangingPlaceEvent event) {
		Player p = (Player) event.getPlayer();
		Entity ee = event.getEntity();
		if ((event.getEntity().getType() == EntityType.ITEM_FRAME) && p.hasPermission("frame.place")) {
			plugin.getFrameProtectorConfig().set(p.getUniqueId().toString(), ee.getLocation().getWorld().getName());
			plugin.getFrameProtectorConfig().set(p.getUniqueId().toString(), " " + ee.getLocation().getBlockX());
			plugin.getFrameProtectorConfig().set(p.getUniqueId().toString(), " " + ee.getLocation().getBlockY());
			plugin.getFrameProtectorConfig().set(p.getUniqueId().toString(), " " + ee.getLocation().getBlockZ());
			Bukkit.broadcastMessage("Code Appears to be working!");
			try {
				plugin.getFrameProtectorConfig().save(plugin.frameprotectorf);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
	}
}

//WIP, currently saves item frame place location but doesn't remove it on deletion nor does it continue saving past 1 item frame placement.