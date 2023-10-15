package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Facebook implements CommandExecutor {
    private final UltimatePlugin plugin;

    public Facebook(UltimatePlugin instance) {
        this.plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!commandLabel.equalsIgnoreCase("facebook")) {
            return false;
        }

        if (!sender.isOp() && !sender.hasPermission("information.facebook")) {
            sender.sendMessage(ChatColor.RED + plugin.getInformationConfig().getString("Messages.Permission Denied"));
            return true;
        }

        List<String> facebookLinks = plugin.getInformationConfig().getStringList("Facebook");
        sender.sendMessage(ChatColor.BLUE + "--Facebook Link(s)--");
        for (String link : facebookLinks) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', link));
        }
        
        return true;
    }
}