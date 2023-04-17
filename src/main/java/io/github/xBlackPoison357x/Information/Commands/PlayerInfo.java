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

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if (!label.equalsIgnoreCase("player")) {
	        return false;
	    }

	    if (!sender.hasPermission("information.player")) {
	        sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
	        return false;
	    }

	    if (args.length != 1) {
	        sender.sendMessage(ChatColor.RED + "Incorrect usage! Correct usage /player <playername>.");
	        return false;
	    }

	    Player targetPlayer = Bukkit.getPlayer(args[0]);
	    if (targetPlayer == null) {
	        sender.sendMessage(ChatColor.RED + args[0] + ChatColor.RED + " is currently not online.");
	        return true;
	    }

	    sender.sendMessage(ChatColor.GOLD + "====== Player: " + ChatColor.RED + targetPlayer.getName()
	            + ChatColor.GOLD + " ======");
	    sender.sendMessage(ChatColor.GOLD + " Nick: " + ChatColor.WHITE + targetPlayer.getDisplayName());
	    sender.sendMessage(ChatColor.GOLD + " UUID: " + ChatColor.WHITE + targetPlayer.getUniqueId());
	    sender.sendMessage(ChatColor.GOLD + " Health: " + ChatColor.WHITE + targetPlayer.getHealth() + "/20");
	    sender.sendMessage(ChatColor.GOLD + " Hunger: " + ChatColor.WHITE + targetPlayer.getFoodLevel() + "/20"
	            + " (+" + targetPlayer.getSaturation() + " saturation)");
	    sender.sendMessage(ChatColor.GOLD + " Exp: " + ChatColor.WHITE + targetPlayer.getExp() + "(Level "
	            + targetPlayer.getLevel() + ")");
	    sender.sendMessage(ChatColor.GOLD + " Location: " + ChatColor.WHITE + "("
	            + targetPlayer.getLocation().getWorld().getName() + ", "
	            + targetPlayer.getLocation().getBlockX() + ", " + targetPlayer.getLocation().getBlockY() + ", "
	            + targetPlayer.getLocation().getBlockZ() + ")");
	    sender.sendMessage(ChatColor.GOLD + " IP Address: " + ChatColor.WHITE + targetPlayer.getAddress().getHostString());
	    sender.sendMessage(ChatColor.GOLD + " Gamemode: " + ChatColor.WHITE + targetPlayer.getGameMode());
	    sender.sendMessage(ChatColor.GOLD + " OP: " + ChatColor.GREEN + targetPlayer.isOp());
	    sender.sendMessage(ChatColor.GOLD + " Fly mode: " + ChatColor.GREEN + targetPlayer.isFlying());
	    return true;
	}
}