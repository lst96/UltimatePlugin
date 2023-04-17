package io.github.xBlackPoison357x.Information.Listeners;

import java.util.Objects;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Kits implements Listener {
	public UltimatePlugin plugin;

	public Kits(UltimatePlugin instance) {
		plugin = instance;
	}
	public void onPlayerJoin(PlayerJoinEvent event) {
	    Player player = event.getPlayer();
	    if (plugin.getInformationConfig().getBoolean("Starter Kit")
	            && player.hasPermission("information.starterkit") && !player.hasPlayedBefore()) {
	        ConfigurationSection kitSection = plugin.getInformationConfig().getConfigurationSection("Starter Kit Items");
	        ItemStack[] starterKit = kitSection.getKeys(false).stream()
	                .map(kitSection::getItemStack)
	                .filter(Objects::nonNull)
	                .toArray(ItemStack[]::new);
	        player.getInventory().addItem(starterKit);
	    }
	}
}
