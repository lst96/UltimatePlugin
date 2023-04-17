package io.github.xBlackPoison357x.Information.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;


public class Motd implements CommandExecutor {
	public UltimatePlugin plugin;


	public Motd(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    if (commandLabel.equalsIgnoreCase("motd")) {
	        if (sender.isOp() || sender.hasPermission("information.motd")) {
	            sender.sendMessage(ChatColor.RED + "[" + plugin.pdfFile.getName() + "] " + ChatColor.YELLOW + Bukkit.getMotd().toString());
	            return true;
	        } else {
	            sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
	            return false;
	        }
	    }
	    return false;
	}
}
