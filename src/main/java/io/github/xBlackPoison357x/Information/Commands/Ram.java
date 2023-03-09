package io.github.xBlackPoison357x.Information.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.Information.Runnables.Tps;
import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Ram implements CommandExecutor {
	public final long serverStart = System.currentTimeMillis();
	public UltimatePlugin plugin;

	public Ram(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		double tps = Tps.getTPS();
		ChatColor color = tps >= 18.0 ? ChatColor.GREEN : (tps >= 15.0 ? ChatColor.YELLOW : ChatColor.RED);
		Runtime runtime = Runtime.getRuntime();
		System.gc();
		if (sender.isOp() || sender.hasPermission("information.ram")) {
			long diff = System.currentTimeMillis() - serverStart;
			sender.sendMessage(ChatColor.GOLD + "[Uptime]: " + ChatColor.WHITE + (int) (diff / 604800000L) + "w "
					+ (int) (diff / 86400000L % 7L) + "d " + (int) (diff / 3600000L % 24L) + "h "
					+ (int) (diff / 60000L % 60L) + "m " + (int) (diff / 1000L % 60L) + "s");
			sender.sendMessage(ChatColor.GOLD + "[Tps]: " + color + Math.round(Tps.getTPS() / 100.0 * 100.0) + " "
					+ Math.round(Tps.getTPS() / 20.0 * 100.0 / 100.0 * 100.0) + "%");
			sender.sendMessage(ChatColor.GOLD + "[Processor(s)] " + ChatColor.WHITE + runtime.availableProcessors());
			sender.sendMessage(
					ChatColor.GOLD + "[Maximum Ram]: " + ChatColor.WHITE + runtime.maxMemory() / 1024L / 1024L + " MB"
							+ " " + runtime.maxMemory() * 100L / runtime.maxMemory() + "%");
			sender.sendMessage(
					ChatColor.GOLD + "[Allocated Ram]: " + ChatColor.WHITE + runtime.totalMemory() / 1024L / 1024L
							+ " MB" + " " + runtime.totalMemory() * 100L / runtime.maxMemory() + "%");
			sender.sendMessage(ChatColor.GOLD + "[Used Ram]: " + ChatColor.WHITE
					+ (runtime.totalMemory() - runtime.freeMemory()) / 1024L / 1024L + " MB" + " "
					+ (runtime.maxMemory() - runtime.freeMemory()) * 100L / runtime.maxMemory() + "%");
			sender.sendMessage(ChatColor.GOLD + "[Free Ram]: " + ChatColor.WHITE + runtime.freeMemory() / 1024L / 1024L
					+ " MB" + " " + runtime.freeMemory() * 100L / runtime.maxMemory() + "%");
			if (Bukkit.getVersion().startsWith("git-Bukkit")) {
				sender.sendMessage(ChatColor.GOLD + "[CraftBukkit Version]: " + ChatColor.WHITE + Bukkit.getVersion()
						+ " (Implementing API version " + Bukkit.getBukkitVersion() + ")");
			} else if (Bukkit.getVersion().startsWith("git-Spigot")) {
				sender.sendMessage(ChatColor.GOLD + "[Spigot Version]: " + ChatColor.WHITE + Bukkit.getVersion()
						+ " (Implementing API version " + Bukkit.getBukkitVersion() + ")");
			} else {
				sender.sendMessage(ChatColor.GOLD + "[Unknown Version]: " + ChatColor.WHITE + Bukkit.getVersion()
						+ " (Implementing API version " + Bukkit.getBukkitVersion() + ")");
			}
			sender.sendMessage(ChatColor.GOLD + "[" + plugin.pdfFile.getName() + "]: " + ChatColor.WHITE + "v"
					+ plugin.pdfFile.getVersion());
			return true;
		}
		sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
		return false;
	}
}
