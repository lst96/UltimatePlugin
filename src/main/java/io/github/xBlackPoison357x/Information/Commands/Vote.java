package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;


public class Vote implements CommandExecutor {
	public UltimatePlugin plugin;


	public Vote(UltimatePlugin instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    if (commandLabel.equalsIgnoreCase("vote")) {
	        if (sender.isOp() || sender.hasPermission("information.vote")) {
	            List<String> voteLinks = plugin.getInformationConfig().getStringList("Vote");
	            sender.sendMessage(ChatColor.BLUE + "--Current Voting Link(s)--");
	            for (String link : voteLinks) {
	                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', link));
	            }
	            return true;
	        }
	        sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
	        return false;
	    }
	    return false;
	}
}