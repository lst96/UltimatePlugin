package io.github.xBlackPoison357x.Information.Commands;

import java.lang.management.ManagementFactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.Information.Runnables.Tps;
import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Server implements CommandExecutor {
	public final long serverStart = System.currentTimeMillis();
	private UltimatePlugin plugin;
	

	public Server(UltimatePlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    if (!command.getName().equalsIgnoreCase("serverinfo")) {
	        return false;
	    }
	    if (sender.hasPermission("information.server")) {
	    long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
	    String uptimeStr = formatDuration(uptime);
		//double[] tps = MinecraftServer.getServer().recentTps;
	   Tps tps = new Tps(plugin);
	   double oneMinTps = tps.get1minTPS();
	   double fiveMinTps = tps.get5minTPS();
	   double fifteenMinTps = tps.get15minTPS();
	    
	    

	   ChatColor tps1minColor = oneMinTps >= 18.0 ? ChatColor.GREEN : (oneMinTps >= 15.0 ? ChatColor.YELLOW : ChatColor.RED);
	   ChatColor tps5minColor = fiveMinTps >= 18.0 ? ChatColor.GREEN : (fiveMinTps >= 15.0 ? ChatColor.YELLOW : ChatColor.RED);
	   ChatColor tps15minColor = fifteenMinTps >= 18.0 ? ChatColor.GREEN : (fifteenMinTps >= 15.0 ? ChatColor.YELLOW : ChatColor.RED);


	    sender.sendMessage(ChatColor.GOLD + "Server Info:");
	    sender.sendMessage(ChatColor.GOLD + "Uptime: " + ChatColor.RED + uptimeStr);
	    sender.sendMessage(ChatColor.GOLD + "TPS: " + "(1m): " + tps1minColor + String.format("%.2f", oneMinTps) +
	            ChatColor.GOLD + " (5m): " + tps5minColor + String.format("%.2f", fiveMinTps) +
	            ChatColor.GOLD + " (15m): " + tps15minColor + String.format("%.2f", fifteenMinTps));
	    sender.sendMessage(ChatColor.GOLD + "Processors: " + ChatColor.RED + Runtime.getRuntime().availableProcessors());
	    sender.sendMessage(ChatColor.GOLD + "Max Memory: " + ChatColor.RED + String.format("%,d", Runtime.getRuntime().maxMemory() / (1024 * 1024)) + " MB");
	    sender.sendMessage(ChatColor.GOLD + "Total Memory: " + ChatColor.RED + String.format("%,d", Runtime.getRuntime().totalMemory() / (1024 * 1024)) + " MB");
	    sender.sendMessage(ChatColor.GOLD + "Used Memory: " + ChatColor.RED + String.format("%,d", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024L * 1024L)) + " MB");
	    sender.sendMessage(ChatColor.GOLD + "Free Memory: " + ChatColor.RED + String.format("%,d", Runtime.getRuntime().freeMemory() / (1024 * 1024)) + " MB");

	    for (World w : Bukkit.getWorlds()) {
	        String worldType = "World";
	        switch (w.getEnvironment()) {
	            case NETHER:
	                worldType = "Nether";
	                break;
	            case THE_END:
	                worldType = "The End";
	                break;
			case CUSTOM:
				break;
			case NORMAL:
				break;
			default:
				break;
	        }


	        int loadedChunks = w.getLoadedChunks().length;
	        int loadedEntities = w.getEntities().size();
	        int tileEntities = 0;
	        for (Chunk chunk : w.getLoadedChunks()) {
	            tileEntities += chunk.getTileEntities().length;
	        }

	        sender.sendMessage(ChatColor.GOLD + worldType + " \"" + ChatColor.RED + w.getName() + ChatColor.GOLD + "\": " + ChatColor.RED + loadedChunks + ChatColor.GOLD + " chunks, " + ChatColor.RED + loadedEntities + ChatColor.GOLD + " entities, " + ChatColor.RED + tileEntities + ChatColor.GOLD + " tiles.");
	    }
	    return true;
	}
		return false;
	}

	private String formatDuration(long millis) {
	    long seconds = millis / 1000;
	    long minutes = seconds / 60;
	    long hours = minutes / 60;
	    long days = hours / 24;

	    StringBuilder builder = new StringBuilder();
	    if (days > 0) {
	        builder.append(days).append(" days ");
	        hours %= 24;
	    }
	    if (hours > 0) {
	        builder.append(hours).append(" hours ");
	        minutes %= 60;
	    }
	    if (minutes > 0) {
	        builder.append(minutes).append(" minutes ");
	        seconds %= 60;
	    }
	    builder.append(seconds).append(" seconds");

	    return builder.toString();
	}
}