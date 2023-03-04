package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Vote implements CommandExecutor {
	private UltimatePlugin plugin;

	public Vote(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("infovote")) {
			// empty if block
		}
		if (sender.isOp() || sender.hasPermission("information.vote")) {
			List<String> Vote2 = plugin.getInformationConfig().getStringList("Vote");
			sender.sendMessage(ChatColor.DARK_BLUE + "--Current Voting Link(s)--");
			for (String Vote1 : Vote2) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes((char) '&', Vote1));
			}
			return true;
		}
		sender.sendMessage(ChatColor.DARK_RED + plugin.pdfFile.getName() + ChatColor.RED
				+ " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		return true;
	}
}
