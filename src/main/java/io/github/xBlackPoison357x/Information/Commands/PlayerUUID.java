package io.github.xBlackPoison357x.Information.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;


public class PlayerUUID implements CommandExecutor {
	public UltimatePlugin plugin;

	public PlayerUUID(UltimatePlugin instance) {
		plugin = instance;
	}

    @Override
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

        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);

        if (player.hasPlayedBefore()) {
            sender.sendMessage(ChatColor.GOLD + args[0] + " UUID: " + ChatColor.WHITE + player.getUniqueId());
        } else {
            sender.sendMessage(ChatColor.RED + args[0] + " does not exist. Please check your spelling and try again.");
        }

        return true;
    }
}