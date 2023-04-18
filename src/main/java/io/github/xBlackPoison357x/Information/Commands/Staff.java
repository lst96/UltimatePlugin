package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;


public class Staff implements CommandExecutor {
	public UltimatePlugin plugin;


	public Staff(UltimatePlugin instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if (!label.equalsIgnoreCase("staff")) {
	        return false;
	    }

	    if (!sender.hasPermission("information.staff")) {
	        sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
	        return false;
	    }

	    List<String> staffList = plugin.getInformationConfig().getStringList("Staff");
	    sender.sendMessage(ChatColor.BLUE + "--Current Staff(s)--");
	    for (String staff : staffList) {
	        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', staff));
	    }
	    return true;
	}
}
