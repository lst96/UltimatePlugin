package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Website
implements CommandExecutor {
    private UltimatePlugin plugin;

    public Website(UltimatePlugin instance) {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("website")) {
            // empty if block
        }
        if (sender.isOp() || sender.hasPermission("information.website")) {
            List<String> Website2 = this.plugin.getInformationConfig().getStringList("Website");
            sender.sendMessage((Object)ChatColor.DARK_BLUE + "--Website Link(s)--");
            for (String Website1 : Website2) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Website1));
            }
            return true;
        }
        sender.sendMessage((Object)ChatColor.DARK_RED + this.plugin.pdfFile.getName() + (Object)ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
        return true;
    }
}

