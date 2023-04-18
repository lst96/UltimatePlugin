package io.github.xBlackPoison357x.FrameProtector.Listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
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

	@EventHandler(priority = EventPriority.LOWEST)
	public void AddConfigEntry(HangingPlaceEvent event) {
	    Player player = event.getPlayer();
	    Entity entity = event.getEntity();

	    if (entity.getType() == EntityType.ITEM_FRAME) {
	        String uuid = player.getUniqueId().toString();
	        UUID worldUUID = entity.getLocation().getWorld().getUID();
	        int x = entity.getLocation().getBlockX();
	        int y = entity.getLocation().getBlockY();
	        int z = entity.getLocation().getBlockZ();

	        // Get the player's existing item frames list or create a new one if it doesn't exist
	        ConfigurationSection itemFrames = plugin.getFrameProtectorConfig().getConfigurationSection("ItemFrames");
	        if (itemFrames == null) {
	            itemFrames = plugin.getFrameProtectorConfig().createSection("ItemFrames");
	        }

	        ConfigurationSection playerSection = itemFrames.getConfigurationSection(uuid);
	        if (playerSection == null) {
	            playerSection = itemFrames.createSection(uuid);
	        }

	        @SuppressWarnings("unchecked")
	        List<Map<String, Object>> framesList = (List<Map<String, Object>>) playerSection.getList("frames", new ArrayList<>());
	        Map<String, Object> newFrameMap = new HashMap<>();
	        newFrameMap.put("world", worldUUID.toString());
	        newFrameMap.put("x", x);
	        newFrameMap.put("y", y);
	        newFrameMap.put("z", z);

	        // Check if the current item frame location already exists in the config for this player
	        for (Map<String, Object> frameMap : framesList) {
	            if (frameMap.get("world").equals(worldUUID.toString()) && frameMap.get("x").equals(x)
	                    && frameMap.get("y").equals(y) && frameMap.get("z").equals(z)) {
	                return; // already exists, so return without saving
	            }
	        }

	        framesList.add(newFrameMap);
	        playerSection.set("frames", framesList);
	    }

	    if (!event.isCancelled()) {
	        try {
	            plugin.getFrameProtectorConfig().save(plugin.frameprotectorf);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
}