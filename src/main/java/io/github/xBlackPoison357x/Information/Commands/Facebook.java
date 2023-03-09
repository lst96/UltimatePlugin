package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Facebook implements CommandExecutor {
	public UltimatePlugin plugin;

	public Facebook(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("facebook")) {
		}
		if (sender.isOp() || sender.hasPermission("information.facebook")) {
			List<String> Facebook2 = plugin.getInformationConfig().getStringList("Facebook");
			sender.sendMessage(ChatColor.BLUE + "--Facebook Link(s)--");
			for (String Facebook1 : Facebook2) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', Facebook1));
			}
		sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
		return true;
	}
		return false;
}
}