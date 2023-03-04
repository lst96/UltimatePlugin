package io.github.xBlackPoison357x.Information.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Motd implements CommandExecutor {
	private UltimatePlugin plugin;

	public Motd(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("motd")) {
			// empty if block
		}
		if (sender.isOp() || sender.hasPermission("information.motd")) {
			sender.sendMessage(
					ChatColor.DARK_RED + plugin.pdfFile.getName() + ChatColor.YELLOW + Bukkit.getMotd().toString());
			return true;
		}
		sender.sendMessage(ChatColor.DARK_RED + plugin.pdfFile.getName() + ChatColor.RED
				+ " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		return true;
	}
}
