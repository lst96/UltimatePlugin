package io.github.xBlackPoison357x.Information.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Facebook
implements CommandExecutor {
    private UltimatePlugin plugin;

    public Facebook(UltimatePlugin instance) {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("facebook")) {
            // empty if block
        }
        if (sender.isOp() || sender.hasPermission("information.facebook")) {
            List<String> Facebook2 = this.plugin.getInformationConfig().getStringList("Facebook");
            sender.sendMessage(ChatColor.DARK_BLUE + "--Facebook Link(s)--");
            for (String Facebook1 : Facebook2) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', Facebook1));
            }
            return true;
        }
        sender.sendMessage(ChatColor.DARK_RED + this.plugin.pdfFile.getName() + ChatColor.RED + " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
        return true;
    }
}

