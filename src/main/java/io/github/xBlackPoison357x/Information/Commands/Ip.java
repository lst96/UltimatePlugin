package io.github.xBlackPoison357x.Information.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Ip implements CommandExecutor {
	public Ip(UltimatePlugin instance) {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("ip")) {
			// empty if block
		}
		if (sender.isOp() || sender.hasPermission("information.ip")) {
			sender.sendMessage(Bukkit.getIp().toString());
		}
		return false;
	}
}
