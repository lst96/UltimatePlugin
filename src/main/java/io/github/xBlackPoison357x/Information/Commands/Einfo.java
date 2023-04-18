package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;


public class Einfo implements CommandExecutor {
	public UltimatePlugin plugin;


	public Einfo(UltimatePlugin instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if (!label.equalsIgnoreCase("einfo")) {
	        return false;
	    }

	    if (!sender.hasPermission("information.extra")) {
	        sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
	        return true;
	    }

	    List<String> extraList = plugin.getInformationConfig().getStringList("Extra");
	    sender.sendMessage(ChatColor.BLUE + "--Extras--");
	    for (String extra : extraList) {
	        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', extra));
	    }
	    return true;
	}
}