package io.github.xBlackPoison357x.DisableCommands.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class ARLRCommand implements CommandExecutor {
	public UltimatePlugin plugin;

	public ARLRCommand(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String msg = plugin.getDisableCommandMessagesConfig().getString("Messages.Permission Deny Message");
		String fmsg = plugin.getDisableCommandMessagesConfig().getString("Messages.Already Forbidden Message");
		String nmsg = plugin.getDisableCommandMessagesConfig().getString("Messages.Not Forbidden Message");
		if (args.length == 0)
			return false;
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("list")) {
				if ((sender.isOp()) || (sender.hasPermission("disablecommands.list"))) {
					sender.sendMessage(ChatColor.GOLD + "Forbidden Commands");
					for (String command : plugin.getDisableCommandsConfig().getStringList("forbidden-commands")) {
						sender.sendMessage(ChatColor.GOLD + " - " + command);
					}
					return true;
				}
				sender.sendMessage(ChatColor.RED + msg);
				return true;
			}

			return false;
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("add")) {
				if ((sender.isOp()) || (sender.hasPermission("disablecommands.add"))) {
					List<String> forbiddenCommands = plugin.getDisableCommandsConfig()
							.getStringList("forbidden-commands");
					if (forbiddenCommands.contains(args[1].toLowerCase())) {
						sender.sendMessage(ChatColor.RED + fmsg);
						return true;
					}
					forbiddenCommands.add(args[1].toLowerCase());
					plugin.getDisableCommandsConfig().set("forbidden-commands", forbiddenCommands);
					plugin.saveDisableCommandsConfig();
					sender.sendMessage(ChatColor.GREEN + "Added " + ChatColor.AQUA + args[1].toLowerCase()
							+ ChatColor.GREEN + " to the forbidden commands list!");
					return true;
				}
				sender.sendMessage(ChatColor.RED + msg);
				return true;
			}
			if (args[0].equalsIgnoreCase("remove")) {
				if ((sender.isOp()) || (sender.hasPermission("disablecommands.remove"))) {
					List<String> forbiddenCommands = plugin.getDisableCommandsConfig()
							.getStringList("forbidden-commands");
					if (!forbiddenCommands.contains(args[1].toLowerCase())) {
						sender.sendMessage(ChatColor.RED + nmsg);
						return true;
					}
					forbiddenCommands.remove(args[1].toLowerCase());
					plugin.getDisableCommandsConfig().set("forbidden-commands", forbiddenCommands);
					plugin.saveDisableCommandsConfig();
					sender.sendMessage(ChatColor.GREEN + "Removed " + ChatColor.AQUA + args[1].toLowerCase()
							+ ChatColor.GREEN + " from the forbidden commands list!");
					return true;
				}
				sender.sendMessage(ChatColor.RED + msg);
				return true;
			}
			return false;
		}
		return false;
	}
}