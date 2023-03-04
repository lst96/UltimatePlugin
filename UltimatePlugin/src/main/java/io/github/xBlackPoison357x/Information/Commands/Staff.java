package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Staff implements CommandExecutor {
	public UltimatePlugin plugin;

	public Staff(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("staff")) {
			// empty if block
		}
		if (sender.isOp() || sender.hasPermission("information.staff")) {
			List<String> Staff2 = plugin.getInformationConfig().getStringList("Staff");
			sender.sendMessage(ChatColor.DARK_BLUE + "--Current Staff(s)--");
			for (String Staff1 : Staff2) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', Staff1));
			}
			return true;
		}
		sender.sendMessage(ChatColor.DARK_RED + plugin.pdfFile.getName() + ChatColor.RED
				+ " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		return true;
	}
}
