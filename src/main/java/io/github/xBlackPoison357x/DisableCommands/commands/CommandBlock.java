package io.github.xBlackPoison357x.DisableCommands.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class CommandBlock implements Listener {
	private UltimatePlugin plugin;

	public CommandBlock(UltimatePlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPreprocess(PlayerCommandPreprocessEvent event) {
		String command = event.getMessage().substring(1);
		if (event.getPlayer().isOp() || event.getPlayer().hasPermission("disablecommands.bypass")) {
			return;
		}
		List<String> forbiddenCommands = plugin.getDisableCommandsConfig().getStringList("forbidden-commands");
		for (String forbiddenCommand : forbiddenCommands) {
			if (command.contains(forbiddenCommand)) {
				event.setCancelled(true);
				String msg = plugin.getDisableCommandMessagesConfig().getString("Messages.Command Deny Message");
				event.getPlayer().sendMessage(ChatColor.RED + msg);
				break;
			}
		}
	}
}
