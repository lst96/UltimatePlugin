package io.github.xBlackPoison357x.Information.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class PlayerUUID implements CommandExecutor {
	public UltimatePlugin plugin;

	public PlayerUUID(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("uuid")) {
			if (sender.isOp() || sender.hasPermission("information.uuid")) {
				if (args.length != 1) {
					sender.sendMessage(ChatColor.RED + "Incorrect usage! Correct usage /uuid <playername>.");
					return false;
				}
				@SuppressWarnings("deprecation")
				OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);

				if (!offlinePlayer.equals(null)) {
					sender.sendMessage(
							ChatColor.GOLD + args[0] + " UUID: " + ChatColor.WHITE + offlinePlayer.getUniqueId());
				}
				return true;
			}
			sender.sendMessage(ChatColor.RED + args[0] + " does not exist, check your spelling and try again!");
			return false;
		}
		sender.sendMessage(ChatColor.DARK_RED + plugin.pdfFile.getName() + ChatColor.RED
				+ " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		return false;
	}
}
