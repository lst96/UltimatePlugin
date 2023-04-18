package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;


public class Rules implements CommandExecutor {
	public UltimatePlugin plugin;


	public Rules(UltimatePlugin instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    if (!commandLabel.equalsIgnoreCase("rules")) {
	        return false;
	    }

	    if (!sender.hasPermission("information.rules")) {
	        sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
	        return true;
	    }

	    List<String> rules = plugin.getInformationConfig().getStringList("Rules");
	    sender.sendMessage(ChatColor.BLUE + "--Current Rules--");
	    for (String rule : rules) {
	        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', rule));
	    }

	    return true;
	}
}