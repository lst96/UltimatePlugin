package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Website implements CommandExecutor {
	public UltimatePlugin plugin;

	public Website(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("website")) {
			// empty if block
		}
		if (sender.isOp() || sender.hasPermission("information.website")) {
			List<String> Website2 = plugin.getInformationConfig().getStringList("Website");
			sender.sendMessage(ChatColor.BLUE + "--Website Link(s)--");
			for (String Website1 : Website2) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', Website1));
			}
			return true;
		}
		sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));;
		return false;
	}
}
