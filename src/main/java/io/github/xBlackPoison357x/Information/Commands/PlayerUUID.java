package io.github.xBlackPoison357x.Information.Commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;


public class PlayerUUID implements CommandExecutor {
	public UltimatePlugin plugin;

	public PlayerUUID(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    if (cmd == null || sender == null || !commandLabel.equalsIgnoreCase("uuid")) {
	        return false;
	    }

	    if (!sender.hasPermission("information.uuid")) {
	        sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
	        return false;
	    }

	    if (args.length != 1) {
	        sender.sendMessage(ChatColor.RED + "Incorrect usage! Correct usage /uuid <playername>.");
	        return false;
	    }

	    UUID uuid = null;
	    String playerName = args[0];
	    Player player = Bukkit.getPlayer(playerName);

	    if (player != null) {
	        uuid = player.getUniqueId();
	    } else {
	        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);
	        if (offlinePlayer.hasPlayedBefore()) {
	            uuid = offlinePlayer.getUniqueId();
	        } else {
	            sender.sendMessage(ChatColor.RED + playerName + " does not exist. Please check your spelling and try again.");
	            return false;
	        }
	    }

	    sender.sendMessage(ChatColor.GOLD + playerName + " UUID: " + ChatColor.WHITE + uuid);
	    return true;
	}

}