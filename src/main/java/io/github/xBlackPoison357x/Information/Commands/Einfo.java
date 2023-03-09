package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Einfo implements CommandExecutor {
	public UltimatePlugin plugin;

	public Einfo(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("einfo")) {
			// empty if block
		}
		if (sender.isOp() || sender.hasPermission("information.extra")) {
			List<String> Extra = plugin.getInformationConfig().getStringList("Extra");
			sender.sendMessage(ChatColor.BLUE + "--Extra(s)--");
			for (String Extra1 : Extra) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', Extra1));
			}
			return true;
		}
		sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
		return false;
	}
}
