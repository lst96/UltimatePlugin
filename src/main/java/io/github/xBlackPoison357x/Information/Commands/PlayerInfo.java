package io.github.xBlackPoison357x.Information.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class PlayerInfo implements CommandExecutor {
	public UltimatePlugin plugin;

	public PlayerInfo(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("player")) {
			if (sender.isOp() || sender.hasPermission("information.player")) {
				if (args.length != 1) {
					sender.sendMessage(ChatColor.RED + "Incorrect usage! Correct usage /player <playername>.");
					return false;
				}
				Player targetplayer = Bukkit.getPlayer(args[0]);
				if (targetplayer == null) {
					sender.sendMessage(ChatColor.RED + args[0] + ChatColor.RED + " is currently not online.");
					return true;
				}
				sender.sendMessage(ChatColor.GOLD + "====== Player: " + ChatColor.RED + targetplayer.getName()
						+ ChatColor.GOLD + " ======");
				sender.sendMessage(ChatColor.GOLD + " Nick: " + ChatColor.WHITE + targetplayer.getDisplayName());
				sender.sendMessage(ChatColor.GOLD + " UUID: " + ChatColor.WHITE + targetplayer.getUniqueId());
				sender.sendMessage(ChatColor.GOLD + " Health: " + ChatColor.WHITE + targetplayer.getHealth() + "/20");
				sender.sendMessage(ChatColor.GOLD + " Hunger: " + ChatColor.WHITE + targetplayer.getFoodLevel() + "/20"
						+ " (+" + targetplayer.getSaturation() + " saturation)");
				sender.sendMessage(ChatColor.GOLD + " Exp: " + ChatColor.WHITE + targetplayer.getExp() + "(Level "
						+ targetplayer.getLevel() + ")");
				sender.sendMessage(ChatColor.GOLD + " Location: " + ChatColor.WHITE + "("
						+ targetplayer.getLocation().getWorld().getName() + ", "
						+ targetplayer.getLocation().getBlockX() + ", " + targetplayer.getLocation().getBlockY() + ", "
						+ targetplayer.getLocation().getBlockZ() + ")");
				sender.sendMessage(ChatColor.GOLD + " IP Address: " + ChatColor.WHITE + targetplayer.getAddress());
				sender.sendMessage(ChatColor.GOLD + " Gamemode: " + ChatColor.WHITE + targetplayer.getGameMode());
				sender.sendMessage(ChatColor.GOLD + " OP: " + ChatColor.GREEN + targetplayer.isOp());
				sender.sendMessage(ChatColor.GOLD + " Fly mode: " + ChatColor.GREEN + targetplayer.isFlying());
				return true;
			}
			sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
			return false;
		}
		return false;
	}
}
