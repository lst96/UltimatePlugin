package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;


public class Twitter implements CommandExecutor {
	public UltimatePlugin plugin;


	public Twitter(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    if (commandLabel.equalsIgnoreCase("twitter")) {
	        if (sender.isOp() || sender.hasPermission("information.twitter")) {
	            List<String> twitterLinks = plugin.getInformationConfig().getStringList("Twitter");
	            sender.sendMessage(ChatColor.BLUE + "--Twitter Link(s)--");
	            for (String link : twitterLinks) {
	                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', link));
	            }
	            return true;
	        }
	        sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
	        return false;
	    }
	    return false;
	}
}