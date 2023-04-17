package io.github.xBlackPoison357x.Information.Commands;

import java.lang.management.ManagementFactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.Information.Runnables.Tps;
import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Server implements CommandExecutor {
	public final long serverStart = System.currentTimeMillis();
	public UltimatePlugin plugin;

	public Server(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    if (!command.getName().equalsIgnoreCase("serverinfo")) {
	        return false;
	    }

	    if (!sender.hasPermission("information.server")) {
	        sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
	        return true;
	    }

	    long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
	    String uptimeStr = formatDuration(uptime);

	    double tps = Tps.getTPS();
	    ChatColor tpsColor = tps >= 18.0 ? ChatColor.GREEN : (tps >= 15.0 ? ChatColor.YELLOW : ChatColor.RED);

	    Runtime runtime = Runtime.getRuntime();

	    long maxMemory = runtime.maxMemory() / (1024L * 1024L);
	    long totalMemory = runtime.totalMemory() / (1024L * 1024L);
	    long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / (1024L * 1024L);
	    long freeMemory = runtime.freeMemory() / (1024L * 1024L);

        String cbVersion = "";
        String version = Bukkit.getVersion();
        if (version.contains("Spigot")) {
            cbVersion = "Spigot";
        } else if (version.contains("CraftBukkit")) {
            cbVersion = "CraftBukkit";
        } else if (version.contains("Paper")) {
            cbVersion = "PaperMC";
        } else {
            cbVersion = "Unknown";
        }

	    sender.sendMessage(ChatColor.GOLD + "Server Info:");
	    sender.sendMessage(ChatColor.GOLD + "Uptime: " + ChatColor.WHITE + uptimeStr);
	    sender.sendMessage(ChatColor.GOLD + "TPS: " + tpsColor + String.format("%.2f", tps));
	    sender.sendMessage(ChatColor.GOLD + "Processors: " + ChatColor.WHITE + runtime.availableProcessors());
	    sender.sendMessage(ChatColor.GOLD + "Max Memory: " + ChatColor.WHITE + maxMemory + " MB");
	    sender.sendMessage(ChatColor.GOLD + "Total Memory: " + ChatColor.WHITE + totalMemory + " MB");
	    sender.sendMessage(ChatColor.GOLD + "Used Memory: " + ChatColor.WHITE + usedMemory + " MB");
	    sender.sendMessage(ChatColor.GOLD + "Free Memory: " + ChatColor.WHITE + freeMemory + " MB");
	    sender.sendMessage(ChatColor.GOLD + "Server Implementation " + ChatColor.WHITE + cbVersion);
	    sender.sendMessage(ChatColor.GOLD + "API Version: " + ChatColor.WHITE + Bukkit.getBukkitVersion());
	    sender.sendMessage(ChatColor.GOLD + "Plugin Version: " + ChatColor.WHITE + plugin.pdfFile.getVersion());
	    return true;
	}

	private String formatDuration(long millis) {
	    long seconds = millis / 1000;
	    long minutes = seconds / 60;
	    long hours = minutes / 60;
	    long days = hours / 24;

	    StringBuilder builder = new StringBuilder();
	    if (days > 0) {
	        builder.append(days).append("d ");
	        hours %= 24;
	    }
	    if (hours > 0) {
	        builder.append(hours).append("h ");
	        minutes %= 60;
	    }
	    if (minutes > 0) {
	        builder.append(minutes).append("m ");
	        seconds %= 60;
	    }
	    builder.append(seconds).append("s");

	    return builder.toString();
	}
}