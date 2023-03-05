package io.github.xBlackPoison357x.Information.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.SpawnCategory;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Stats implements CommandExecutor {
	public UltimatePlugin plugin;

	public Stats(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("stats")) {
			// empty if block
		}
		if (sender.isOp() || sender.hasPermission("information.stats")) {
			sender.sendMessage(ChatColor.GOLD + "[Online Mode]: " + ChatColor.GREEN + Bukkit.getOnlineMode());
			sender.sendMessage(ChatColor.GOLD + "[Flight Allowed]: " + ChatColor.GREEN + Bukkit.getAllowFlight());
			sender.sendMessage(ChatColor.GOLD + "[Nether Allowed]: " + ChatColor.GREEN + Bukkit.getAllowNether());
			sender.sendMessage(ChatColor.GOLD + "[End Allowed]: " + ChatColor.GREEN + Bukkit.getAllowEnd());
			sender.sendMessage(ChatColor.GOLD + "[View Distance]: " + ChatColor.WHITE + Bukkit.getViewDistance());
			sender.sendMessage(ChatColor.GOLD + "[Default GameMode]: " + ChatColor.WHITE + Bukkit.getDefaultGameMode());
			sender.sendMessage(ChatColor.GOLD + "[Spawn Radius]: " + ChatColor.WHITE + Bukkit.getSpawnRadius());
			sender.sendMessage(ChatColor.GOLD + "[Animal Spawn Limit]: " + ChatColor.WHITE
					+ Bukkit.getSpawnLimit(SpawnCategory.ANIMAL));
			sender.sendMessage(ChatColor.GOLD + "[Monster Spawn Limit]: " + ChatColor.WHITE
					+ Bukkit.getSpawnLimit(SpawnCategory.MONSTER));
			sender.sendMessage(ChatColor.GOLD + "[Ambient Spawn Limit]: " + ChatColor.WHITE
					+ Bukkit.getSpawnLimit(SpawnCategory.AMBIENT));
			sender.sendMessage(ChatColor.GOLD + "[Ticks Per Animal Spawn]: " + ChatColor.WHITE
					+ Bukkit.getTicksPerSpawns(SpawnCategory.ANIMAL));
			sender.sendMessage(ChatColor.GOLD + "[Ticks Per Monster Spawn]: " + ChatColor.WHITE
					+ Bukkit.getTicksPerSpawns(SpawnCategory.MONSTER));
			sender.sendMessage(ChatColor.GOLD + "[Ticks Per Ambient Spawn]: " + ChatColor.WHITE
					+ Bukkit.getTicksPerSpawns(SpawnCategory.AMBIENT));
			sender.sendMessage(ChatColor.GOLD + "[Ops]: " + ChatColor.WHITE + Bukkit.getServer().getOperators());
			if (Bukkit.getVersion().contains("Bukkit")) {
				sender.sendMessage(ChatColor.GOLD + "[CraftBukkit Version]: " + ChatColor.WHITE + Bukkit.getVersion()
						+ " (Implementing API version " + Bukkit.getBukkitVersion() + ")");
			} else if (Bukkit.getVersion().contains("Spigot")) {
				sender.sendMessage(ChatColor.GOLD + "[Spigot Version]: " + ChatColor.WHITE + Bukkit.getVersion()
						+ " (Implementing API version " + Bukkit.getBukkitVersion() + ")");
			} else if (Bukkit.getVersion().contains("Paper")) {
				sender.sendMessage(ChatColor.GOLD + "[Paper Version]: " + ChatColor.WHITE + Bukkit.getVersion()
						+ " (Implementing API version " + Bukkit.getBukkitVersion() + ")");
			} else {
				sender.sendMessage(ChatColor.GOLD + "[Unknown Version]: " + ChatColor.WHITE + Bukkit.getVersion()
						+ " (Implementing API version " + Bukkit.getBukkitVersion() + ")");
			}
			sender.sendMessage(ChatColor.GOLD + "[" + plugin.pdfFile.getName() + "]: " + ChatColor.WHITE
					+ Bukkit.getServer().getPluginManager().getPlugin("UltimatePlugin").getDescription().getVersion());
			return true;
		}
		sender.sendMessage(ChatColor.DARK_RED + plugin.pdfFile.getName() + ChatColor.RED
				+ " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		return true;
	}
}
