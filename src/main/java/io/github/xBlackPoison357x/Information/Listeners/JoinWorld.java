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

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class JoinWorld implements Listener {
	public UltimatePlugin plugin;

	public JoinWorld(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
		Player p = event.getPlayer();
		Environment e = p.getWorld().getEnvironment();
		String msg = ChatColor.RED
				+ plugin.getInformationConfig().getString("Messages.Permission.Join World Disabled Error");
		String msg2 = ChatColor.RED
				+ plugin.getInformationConfig().getString("Messages.Permission.Join World Config Error");
		String msg3 = ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission.Join World Join Error");
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")
				&& (!p.isOp() || !p.hasPermission("information.joinbypass.end"))
				&& e.equals(World.Environment.THE_END)) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")) {
				p.teleport(getWorldSpawn());
				p.sendMessage(msg3 + " " + Bukkit.getServer().getWorlds().get(0).getName());
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")) {
				p.teleport(getNetherSpawn());
				p.sendMessage(msg + " " + Bukkit.getServer().getWorlds().get(1).getName());
			} else {
				p.kickPlayer(msg2);
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")
				&& (!p.isOp() || !p.hasPermission("information.joinbypass.nether"))
				&& e.equals(World.Environment.NETHER)) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")) {
				p.teleport(getNetherSpawn());
				p.sendMessage(msg3 + " " + Bukkit.getServer().getWorlds().get(1).getName());
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
				p.teleport(getEndSpawn());
				p.sendMessage(msg + " " + Bukkit.getServer().getWorlds().get(2).getName());
			} else {
				p.kickPlayer(msg2);
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")
				&& (!p.isOp() || !p.hasPermission("information.joinbypass.world"))
				&& e.equals(World.Environment.NORMAL)) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")) {
				p.teleport(getNetherSpawn());
				p.sendMessage(msg3 + " " + Bukkit.getServer().getWorlds().get(1).getName());
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
				p.teleport(getEndSpawn());
				p.sendMessage(msg + " " + Bukkit.getServer().getWorlds().get(2).getName());
			} else {
				p.kickPlayer(msg2);
			}
		}
		}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerChangeWorld(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		Environment e = p.getWorld().getEnvironment();
		String msg = ChatColor.RED
				+ plugin.getInformationConfig().getString("Messages.Permission.Join World Disabled Error");
		String msg2 = ChatColor.RED
				+ plugin.getInformationConfig().getString("Messages.Permission.Join World Config Error");
		String msg3 = ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission.Join World Join Error");
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")
				&& (!p.isOp() || !p.hasPermission("information.joinbypass.end"))
				&& e.equals(World.Environment.THE_END)) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")) {
				p.teleport(getWorldSpawn());
				p.sendMessage(msg3 + " " + Bukkit.getServer().getWorlds().get(0).getName());
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")) {
				p.teleport(getNetherSpawn());
				p.sendMessage(msg + " " + Bukkit.getServer().getWorlds().get(1).getName());
			} else {
				p.kickPlayer(msg2);
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")
				&& (!p.isOp() || !p.hasPermission("information.joinbypass.nether"))
				&& e.equals(World.Environment.NETHER)) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")) {
				p.teleport(getWorldSpawn());
				p.sendMessage(msg3 + " " + Bukkit.getServer().getWorlds().get(0).getName());
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
				p.teleport(getEndSpawn());
				p.sendMessage(msg + " " + Bukkit.getServer().getWorlds().get(2).getName());
			} else {
				p.kickPlayer(msg2);
			}
		}
		if (plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world")
				&& (!p.isOp() || !p.hasPermission("information.joinbypass.world"))
				&& e.equals(World.Environment.NORMAL)) {
			if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_nether")) {
				p.teleport(getNetherSpawn());
				p.sendMessage(msg3 + " " + Bukkit.getServer().getWorlds().get(1).getName());
			} else if (!plugin.getInformationConfig().getBoolean("Disabled Join Worlds.world_the_end")) {
				p.teleport(getEndSpawn());
				p.sendMessage(msg + " " + Bukkit.getServer().getWorlds().get(2).getName());
			} else {
				p.kickPlayer(msg2);
			}
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