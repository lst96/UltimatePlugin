package io.github.xBlackPoison357x.UltimatePlugin.Commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;
import io.github.xBlackPoison357x.UltimatePlugin.Updater.Updater;

public class UltimateUpdate implements CommandExecutor {
	public UltimatePlugin plugin;

	public UltimateUpdate(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    if (commandLabel.equalsIgnoreCase("ultimateupdate")) {
	        if (sender.isOp() || sender.hasPermission("ultimate.update")) {
	            Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("UltimatePlugin");
	            if (plugin != null) {
	                File pluginFile = plugin.getDataFolder().getParentFile();
	                Updater updater = new Updater(plugin, 102168, pluginFile, Updater.UpdateType.NO_VERSION_CHECK, true);
	                if (updater.getResult() == Updater.UpdateResult.SUCCESS) {
	                    sender.sendMessage(ChatColor.GREEN + "Update successful! Reload or restart the server to apply changes.");
	                } else {
	                    sender.sendMessage(ChatColor.RED + "Update failed. Please check console for errors.");
	                }
	            } else {
	                sender.sendMessage(ChatColor.RED + "Could not find the UltimatePlugin. Is it installed?");
	            }
	        } else {
	            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
	        }
	        return true;
	    }
	    return false;
	}
}
