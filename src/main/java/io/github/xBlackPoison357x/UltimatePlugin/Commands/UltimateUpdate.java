package io.github.xBlackPoison357x.UltimatePlugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class UltimateUpdate implements CommandExecutor {
	public UltimatePlugin plugin;

	public UltimateUpdate(UltimatePlugin instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    if (commandLabel.equalsIgnoreCase("ultimateupdate")) {
	        if (sender.isOp() || sender.hasPermission("ultimate.update")) {
	            if (plugin != null) {
	                sender.sendMessage(ChatColor.GREEN + "Checking for update.");
	                plugin.setupUpdater();
	            } else {
	                sender.sendMessage(ChatColor.RED + "Could not find the UltimatePlugin. Is it installed?");
	            }
	        } else {
	            sender.sendMessage(ChatColor.RED + plugin.getDefaultConfig().getString("Messages.Permission Denied"));
	        }
	        return true;
	    }
	    return false;
	}
}