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

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    String msg = plugin.getDisableCommandMessagesConfig().getString("Messages.Permission Deny Message");
	    String fmsg = plugin.getDisableCommandMessagesConfig().getString("Messages.Already Forbidden Message");
	    String nmsg = plugin.getDisableCommandMessagesConfig().getString("Messages.Not Forbidden Message");
	    boolean isOp = sender.isOp();
	    boolean canList = isOp || sender.hasPermission("disablecommands.list");
	    boolean canAdd = isOp || sender.hasPermission("disablecommands.add");
	    boolean canRemove = isOp || sender.hasPermission("disablecommands.remove");

	    if (args.length == 0) {
	        return false;
	    }

	    String subCommand = args[0].toLowerCase();

	    if (subCommand.equals("list")) {
	        if (canList) {
	            List<String> forbiddenCommands = plugin.getDisableCommandsConfig().getStringList("forbidden-commands");
	            sender.sendMessage(ChatColor.GOLD + "Forbidden Commands");
	            for (String command : forbiddenCommands) {
	                sender.sendMessage(ChatColor.GOLD + " - " + command);
	            }
	            return true;
	        } else {
	            sender.sendMessage(ChatColor.RED + msg);
	            return true;
	        }
	    }

	    if (args.length == 2) {
	        String forbiddenCommand = args[1].toLowerCase();

	        if (subCommand.equals("add")) {
	            if (canAdd) {
	                List<String> forbiddenCommands = plugin.getDisableCommandsConfig().getStringList("forbidden-commands");
	                if (forbiddenCommands.contains(forbiddenCommand)) {
	                    sender.sendMessage(ChatColor.RED + fmsg);
	                    return true;
	                }
	                forbiddenCommands.add(forbiddenCommand);
	                plugin.getDisableCommandsConfig().set("forbidden-commands", forbiddenCommands);
	                plugin.saveDisableCommandsConfig();
	                sender.sendMessage(ChatColor.GREEN + "Added " + ChatColor.AQUA + forbiddenCommand
	                        + ChatColor.GREEN + " to the forbidden commands list!");
	                return true;
	            } else {
	                sender.sendMessage(ChatColor.RED + msg);
	                return true;
	            }
	        }

	        if (subCommand.equals("remove")) {
	            if (canRemove) {
	                List<String> forbiddenCommands = plugin.getDisableCommandsConfig().getStringList("forbidden-commands");
	                if (!forbiddenCommands.contains(forbiddenCommand)) {
	                    sender.sendMessage(ChatColor.RED + nmsg);
	                    return true;
	                }
	                forbiddenCommands.remove(forbiddenCommand);
	                plugin.getDisableCommandsConfig().set("forbidden-commands", forbiddenCommands);
	                plugin.saveDisableCommandsConfig();
	                sender.sendMessage(ChatColor.GREEN + "Removed " + ChatColor.AQUA + forbiddenCommand
	                        + ChatColor.GREEN + " from the forbidden commands list!");
	                return true;
	            } else {
	                sender.sendMessage(ChatColor.RED + msg);
	                return true;
	            }
	        }
	    }

	    return false;
	}
}