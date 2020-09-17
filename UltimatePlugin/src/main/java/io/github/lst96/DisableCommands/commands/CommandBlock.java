package io.github.lst96.DisableCommands.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import io.github.lst96.UltimatePlugin.UltimatePlugin;

public class CommandBlock implements Listener {
    private UltimatePlugin plugin;

    public CommandBlock(UltimatePlugin instance) {
        this.plugin = instance;
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPreprocess(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().substring(1);
        if (!event.getPlayer().isOp() && !event.getPlayer().hasPermission("disablecommands.bypass")) {
            List<String> forbiddenCommands = this.plugin.getDisableCommandsConfig().getStringList("forbidden-commands");
            for (String forbiddenCommand : forbiddenCommands) {
                if (!command.contains(forbiddenCommand)) continue;
                event.setCancelled(true);
                String msg = this.plugin.getConfig().getString("Messages.Command Deny Message");
                event.getPlayer().sendMessage((Object)ChatColor.RED + msg);
                break;
            }
        }
    }
}

