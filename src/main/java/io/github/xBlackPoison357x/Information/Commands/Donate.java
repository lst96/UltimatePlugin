package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Donate implements CommandExecutor {
	public UltimatePlugin plugin;

	public Donate(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("donate")) {
		}
		if (sender.isOp() || sender.hasPermission("information.donate")) {
			List<String> Donate2 = plugin.getInformationConfig().getStringList("Donate");
			sender.sendMessage(ChatColor.BLUE + "--Donation Link(s)--");
			for (String Donate1 : Donate2) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', Donate1));
			}
			return true;
		}
		sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
		return false;
	}
}
