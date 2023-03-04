package io.github.xBlackPoison357x.UltimatePlugin.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;
import net.gravitydevelopment.updater.Updater;

public class UltimateUpdate implements CommandExecutor {
	private UltimatePlugin plugin;

	public UltimateUpdate(UltimatePlugin instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("ultimateupdate")
				&& (sender.isOp() || sender.hasPermission("ultimate.update"))) {
			Updater updater = new Updater((Plugin) this, 102168, getFile(), Updater.UpdateType.NO_VERSION_CHECK, true); // Go
																														// straight
																														// to
																														// downloading,
																														// and
																														// announce
																														// progress
																														// to
																														// console.
			plugin.setupUpdater();
		}
		return false;
	}

	protected File getFile() {

	}
}


//CURRENTLY DOESN'T WORK, WIP