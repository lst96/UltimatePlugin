package io.github.xBlackPoison357x.Information.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Kits implements Listener {
	private UltimatePlugin plugin;

	public Kits(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (plugin.getInformationConfig().getBoolean("Starter Kit")
				&& event.getPlayer().hasPermission("information.starterkit") && !player.hasPlayedBefore()) {
			ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
			ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE, 1);
			ItemStack legs = new ItemStack(Material.IRON_LEGGINGS, 1);
			ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
			ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
			ItemStack pick = new ItemStack(Material.IRON_PICKAXE, 1);
			ItemStack axe = new ItemStack(Material.IRON_AXE, 1);
			ItemStack shovel = new ItemStack(Material.IRON_SHOVEL, 1);
			ItemStack hoe = new ItemStack(Material.IRON_HOE, 1);
			ItemStack bow = new ItemStack(Material.BOW, 1);
			ItemStack arrows = new ItemStack(Material.ARROW, 32);
			ItemStack bread = new ItemStack(Material.BREAD, 16);
			event.getPlayer().getInventory().setHelmet(helmet);
			event.getPlayer().getInventory().setChestplate(chest);
			event.getPlayer().getInventory().setLeggings(legs);
			event.getPlayer().getInventory().setBoots(boots);
			event.getPlayer().getInventory().addItem(new ItemStack[] { sword });
			event.getPlayer().getInventory().addItem(new ItemStack[] { bow });
			event.getPlayer().getInventory().addItem(new ItemStack[] { pick });
			event.getPlayer().getInventory().addItem(new ItemStack[] { axe });
			event.getPlayer().getInventory().addItem(new ItemStack[] { shovel });
			event.getPlayer().getInventory().addItem(new ItemStack[] { hoe });
			event.getPlayer().getInventory().addItem(new ItemStack[] { bread });
			event.getPlayer().getInventory().addItem(new ItemStack[] { arrows });
		}
	}
}
