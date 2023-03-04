package io.github.xBlackPoison357x.Information.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class JoinWorld implements Listener {
	public UltimatePlugin plugin;

	public JoinWorld(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
		World world;
		Location loc;
		World end;
		World nether;
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")
				&& (!event.getPlayer().isOp() || !event.getPlayer().hasPermission("information.joinbypass.end"))
				&& event.getPlayer().getWorld().getName().endsWith("end")) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")) {
				world = Bukkit.getWorld("world");
				loc = world.getSpawnLocation();
				event.getPlayer().teleport(loc);
				event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to join the end!");
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")) {
				nether = Bukkit.getWorld("world_nether");
				loc = nether.getSpawnLocation();
				event.getPlayer().teleport(loc);
				event.getPlayer().sendMessage("World disabled, teleporting to world_nether instead");
			} else {
				event.getPlayer().sendMessage(
						ChatColor.RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")
				&& (!event.getPlayer().isOp() || !event.getPlayer().hasPermission("information.joinbypass.nether"))
				&& event.getPlayer().getWorld().getName().endsWith("nether")) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")) {
				world = Bukkit.getWorld("world");
				loc = world.getSpawnLocation();
				event.getPlayer().teleport(loc);
				event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to join the nether!");
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
				end = Bukkit.getWorld("world_the_end");
				loc = end.getSpawnLocation();
				event.getPlayer().teleport(loc);
				event.getPlayer().sendMessage("World disabled, teleporting to world_end instead");
			} else {
				event.getPlayer().sendMessage(
						ChatColor.RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")
				&& (!event.getPlayer().isOp() || !event.getPlayer().hasPermission("information.joinbypass.world"))
				&& event.getPlayer().getWorld().getName().endsWith("world")) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")) {
				nether = Bukkit.getWorld("world_nether");
				loc = nether.getSpawnLocation();
				event.getPlayer().teleport(loc);
				event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to join the world!");
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
				end = Bukkit.getWorld("world_the_end");
				loc = end.getSpawnLocation();
				event.getPlayer().teleport(loc);
				event.getPlayer().sendMessage("World disabled, teleporting to world_the_end instead");
			} else {
				event.getPlayer().sendMessage(
						ChatColor.RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")
				&& plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_nether")
				&& plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
			event.getPlayer().kickPlayer("Config Error, all worlds are disabled, please undisable at least 1 world.");
			plugin.console.sendMessage(
					ChatColor.DARK_RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChangeWorld(PlayerJoinEvent event) {
		World world;
		Location loc;
		World end;
		World nether;
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")
				&& (!event.getPlayer().isOp() || !event.getPlayer().hasPermission("information.joinbypass.end"))
				&& event.getPlayer().getWorld().getName().endsWith("end")) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")) {
				world = Bukkit.getWorld("world");
				loc = world.getSpawnLocation();
				event.getPlayer().teleport(loc);
				event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to join the end!");
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")) {
				nether = Bukkit.getWorld("world_nether");
				loc = nether.getSpawnLocation();
				event.getPlayer().teleport(loc);
				event.getPlayer().sendMessage("World disabled, teleporting to world_nether instead");
			} else {
				event.getPlayer().sendMessage(
						ChatColor.RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")
				&& (!event.getPlayer().isOp() || !event.getPlayer().hasPermission("information.joinbypass.nether"))
				&& event.getPlayer().getWorld().getName().endsWith("nether")) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")) {
				world = Bukkit.getWorld("world");
				loc = world.getSpawnLocation();
				event.getPlayer().teleport(loc);
				event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to join the nether!");
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
				end = Bukkit.getWorld("world_the_end");
				loc = end.getSpawnLocation();
				event.getPlayer().teleport(loc);
				event.getPlayer().sendMessage("World disabled, teleporting to world_end instead");
			} else {
				event.getPlayer().sendMessage(
						ChatColor.RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")
				&& (!event.getPlayer().isOp() || !event.getPlayer().hasPermission("information.joinbypass.world"))
				&& event.getPlayer().getWorld().getName().endsWith("world")) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")) {
				nether = Bukkit.getWorld("world_nether");
				loc = nether.getSpawnLocation();
				event.getPlayer().teleport(loc);
				event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to join the world!");
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
				end = Bukkit.getWorld("world_the_end");
				loc = end.getSpawnLocation();
				event.getPlayer().teleport(loc);
				event.getPlayer().sendMessage("World disabled, teleporting to world_the_end instead");
			} else {
				event.getPlayer().sendMessage(
						ChatColor.RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")
				&& plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_nether")
				&& plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
			event.getPlayer().kickPlayer(
					ChatColor.DARK_RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
			plugin.console.sendMessage(
					ChatColor.DARK_RED + "Config Error, all worlds are disabled, please undisable at least 1 world.");
			Bukkit.broadcastMessage(
					(ChatColor.DARK_RED + "Config Error, all worlds are disabled, please undisable at least 1 world."));
		}
	}
}
