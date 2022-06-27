package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Twitter
implements CommandExecutor {
    private UltimatePlugin plugin;

    public Twitter(UltimatePlugin instance) {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("twitter")) {
            // empty if block
        }
        if (sender.isOp() || sender.hasPermission("information.twitter")) {
            List<String> Twitter2 = this.plugin.getInformationConfig().getStringList("Twitter");
            sender.sendMessage(ChatColor.DARK_BLUE + "--Twitter Link(s)--");
            for (String Twitter1 : Twitter2) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', Twitter1));
            }
            return true;
        }
        sender.sendMessage(ChatColor.DARK_RED + this.plugin.pdfFile.getName() + ChatColor.RED + " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
        return true;
    }
}

