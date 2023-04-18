package io.github.xBlackPoison357x.Information.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;


public class IP implements CommandExecutor {
	public UltimatePlugin plugin;


	public IP(UltimatePlugin instance) {
	plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    if (commandLabel.equalsIgnoreCase("ip")) {
	        if (sender.isOp() || sender.hasPermission("information.ip")) {
	            sender.sendMessage(Bukkit.getIp().toString());
	            return true;
	        } else {
	            sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
	            return true;
	        }
	    }
	    return false;
	}
}
