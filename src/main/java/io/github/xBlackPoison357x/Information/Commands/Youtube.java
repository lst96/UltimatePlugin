package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Youtube implements CommandExecutor {
	public UltimatePlugin plugin;

	public Youtube(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("youtube")) {
			// empty if block
		}
		if (sender.isOp() || sender.hasPermission("information.youtube")) {
			List<String> Youtube2 = plugin.getInformationConfig().getStringList("Youtube");
			sender.sendMessage(ChatColor.BLUE + "--Youtube Link(s)--");
			for (String Youtube1 : Youtube2) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', Youtube1));
			}
			return true;
		}
		sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
		return false;
	}
}
