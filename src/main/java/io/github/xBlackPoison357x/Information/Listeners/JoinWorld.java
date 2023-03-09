package io.github.xBlackPoison357x.Information.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class JoinWorld implements Listener {
	public UltimatePlugin plugin;

	public JoinWorld(UltimatePlugin instance) {
		plugin = instance;

		// test join world custom name support and custom messages
	}

	String msg = ChatColor.RED
			+ plugin.getInformationConfig().getString("Messages.Permission.Join.World Disabled Error");
	String msg2 = ChatColor.RED
			+ plugin.getInformationConfig().getString("Messages.Permission.Join.World Config Error");
	String msg3 = ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission.Join.World Join Error");

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
		Player p = event.getPlayer();
		Environment e = p.getWorld().getEnvironment();

		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")
				&& (!p.isOp() || !p.hasPermission("information.joinbypass.end"))
				&& e.equals(World.Environment.THE_END)) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")) {
				p.sendMessage(msg3 + " " + e);
				p.teleport(getWorldSpawn());
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")) {
				p.sendMessage(msg + " " + e);
				p.teleport(getNetherSpawn());
			} else {
				p.sendMessage(msg2);
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")
				&& (!p.isOp() || !p.hasPermission("information.joinbypass.nether"))
				&& e.equals(World.Environment.NETHER)) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")) {
				p.sendMessage(msg3 + " " + e);
				p.teleport(getNetherSpawn());
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
				p.sendMessage(msg + " " + e);
				p.teleport(getEndSpawn());
			} else {
				p.sendMessage(msg2);
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")
				&& (!p.isOp() || !p.hasPermission("information.joinbypass.world"))
				&& e.equals(World.Environment.NORMAL)) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")) {
				p.sendMessage(msg3 + " " + e);
				p.teleport(getNetherSpawn());
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
				p.sendMessage(msg + " " + e);
				p.teleport(getEndSpawn());
			} else {
				p.sendMessage(msg2);
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")
				&& plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_nether")
				&& plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
			p.kickPlayer(msg2);
			plugin.console.sendMessage(msg2);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChangeWorld(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		Environment e = p.getWorld().getEnvironment();
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")
				&& (!p.isOp() || !p.hasPermission("information.joinbypass.end"))
				&& e.equals(World.Environment.THE_END)) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")) {
				p.sendMessage(msg3 + " " + e);
				p.teleport(getWorldSpawn());
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")) {
				p.sendMessage(msg + " " + e);
				p.teleport(getNetherSpawn());
			} else {
				p.sendMessage(msg2);
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")
				&& (!p.isOp() || !p.hasPermission("information.joinbypass.nether"))
				&& e.equals(World.Environment.NETHER)) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")) {
				p.sendMessage(msg3 + " " + e);
				p.teleport(getWorldSpawn());
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
				p.sendMessage(msg + " " + e);
				p.teleport(getEndSpawn());
			} else {
				p.sendMessage(msg2);
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")
				&& (!p.isOp() || !p.hasPermission("information.joinbypass.world"))
				&& e.equals(World.Environment.NORMAL)) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")) {
				p.sendMessage(msg3 + " " + e);
				p.teleport(getNetherSpawn());
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
				p.sendMessage(msg + " " + e);
				p.teleport(getEndSpawn());
			} else {
				p.sendMessage(msg2);
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")
				&& plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_nether")
				&& plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
			p.kickPlayer(msg2);
			plugin.console.sendMessage(msg2);
			Bukkit.broadcastMessage(msg2);
		}
	}

	public Location getWorldSpawn() {
		for (final World world : Bukkit.getServer().getWorlds()) {
			if (world.getEnvironment() != World.Environment.NORMAL) {
				continue;
			}
			return world.getSpawnLocation();
		}
		return Bukkit.getServer().getWorlds().get(0).getSpawnLocation();
	}

	public Location getNetherSpawn() {
		for (final World world : Bukkit.getServer().getWorlds()) {
			if (world.getEnvironment() != World.Environment.NETHER) {
				continue;
			}
			return world.getSpawnLocation();
		}
		return Bukkit.getServer().getWorlds().get(1).getSpawnLocation();
	}

	public Location getEndSpawn() {
		for (final World world : Bukkit.getServer().getWorlds()) {
			if (world.getEnvironment() != World.Environment.THE_END) {
				continue;
			}
			return world.getSpawnLocation();
		}
		return Bukkit.getServer().getWorlds().get(2).getSpawnLocation();
	}
}