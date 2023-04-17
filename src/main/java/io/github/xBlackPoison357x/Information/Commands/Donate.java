package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;


public class Donate implements CommandExecutor {
	public UltimatePlugin plugin;


	public Donate(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if (!label.equalsIgnoreCase("donate")) {
	        return false;
	    }
	    
	    if (sender instanceof Player && !sender.hasPermission("information.donate")) {
	        sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
	        return true;
	    }
	    
	    List<String> donateLinks = plugin.getInformationConfig().getStringList("Donate");
	    sender.sendMessage(ChatColor.BLUE + "--Donation Link(s)--");
	    donateLinks.forEach(link -> sender.sendMessage(ChatColor.translateAlternateColorCodes('&', link)));
	    return true;
	}
}

