package io.github.xBlackPoison357x.Information.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;


public class Online implements CommandExecutor {
	public UltimatePlugin plugin;


	public Online(UltimatePlugin instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    if (commandLabel.equalsIgnoreCase("online")) {
	        if (!sender.hasPermission("information.online")) {
	            sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
	            return true;
	        }

	        int onlinePlayers = Bukkit.getOnlinePlayers().size();
	        int maxPlayers = Bukkit.getMaxPlayers();
	        sender.sendMessage(ChatColor.RED + "[" + plugin.pdfFile.getName() + "] " + ChatColor.GREEN
	            + onlinePlayers + ChatColor.RESET + ChatColor.YELLOW + " of " + ChatColor.RESET
	            + ChatColor.GREEN + maxPlayers);
	        return true;
	    }
	    return false;
	}
}
