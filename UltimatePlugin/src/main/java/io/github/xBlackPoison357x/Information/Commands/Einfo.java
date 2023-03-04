package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Einfo implements CommandExecutor {
	private UltimatePlugin plugin;

	public Einfo(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("einfo")) {
			// empty if block
		}
		if (sender.isOp() || sender.hasPermission("information.extra")) {
			List<String> Extra = plugin.getInformationConfig().getStringList("Extra");
			sender.sendMessage(ChatColor.DARK_BLUE + "--Extra(s)--");
			for (String Extra1 : Extra) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', Extra1));
			}
			return true;
		}
		sender.sendMessage(ChatColor.DARK_RED + plugin.pdfFile.getName() + ChatColor.RED
				+ " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		return true;
	}
}
