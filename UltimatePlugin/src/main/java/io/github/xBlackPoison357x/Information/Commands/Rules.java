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

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("rules")) {
			// empty if block
		}
		if (sender.isOp() || sender.hasPermission("information.rules")) {
			List<String> Rules2 = plugin.getInformationConfig().getStringList("Rules");
			sender.sendMessage(ChatColor.DARK_BLUE + "--Current Rule(s)--");
			for (String Rules1 : Rules2) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', Rules1));
			}
			return true;
		}
		sender.sendMessage(ChatColor.DARK_RED + plugin.pdfFile.getName() + ChatColor.RED
				+ " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		return true;
	}
}
