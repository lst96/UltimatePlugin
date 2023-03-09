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
			sender.sendMessage(ChatColor.BLUE + "--Current Rule(s)--");
			for (String Rules1 : Rules2) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', Rules1));
			}
			return true;
		}
		sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
		return false;
	}
}
