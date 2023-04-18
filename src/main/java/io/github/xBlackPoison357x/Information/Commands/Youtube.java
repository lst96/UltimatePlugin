package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;


public class Youtube implements CommandExecutor {
	public UltimatePlugin plugin;


	public Youtube(UltimatePlugin instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    if (!commandLabel.equalsIgnoreCase("youtube")) {
	        return false;
	    }

	    if (!sender.hasPermission("information.youtube")) {
	        sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
	        return true;
	    }

	    List<String> youtubeLinks = plugin.getInformationConfig().getStringList("Youtube");

	    sender.sendMessage(ChatColor.BLUE + "--Youtube Link(s)--");

	    for (String link : youtubeLinks) {
	        sender.sendMessage(ChatColor.AQUA + link);
	    }

	    return true;
	}
}
