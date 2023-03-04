package io.github.xBlackPoison357x.UltimatePlugin.Listener.UpdateJoinListener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;
import net.gravitydevelopment.updater.Updater;

public class UpdateJoinListener implements Listener {
    private UltimatePlugin plugin;
    
    public UpdateJoinListener(UltimatePlugin instance) {
        plugin = instance;
    }

@EventHandler
public void onPlayerJoin(PlayerJoinEvent event)
{
  Player player = event.getPlayer();
  if(player.hasPermission("ultimate.update") && plugin.autoUpdate)
  {
    player.sendMessage("An update is available: " + plugin.joinUpdater().Updater. UltimatePlugin.name, + ", a " + UltimatePlugin.type + " for " + UltimatePlugin.version + " available at " + UltimatePlugin.link);
    // Will look like - An update is available: AntiCheat v1.5.9, a release for CB 1.6.2-R0.1 available at http://media.curseforge.com/XYZ
    player.sendMessage("Type /ultimateupdate if you would like to automatically update.");
  }
  }
}

name=updater.getLatestName(); // Get the latest name
version=updater.getLatestGameVersion(); // Get the latest game version
type=updater.getLatestType(); // Get the latest file's type
link=updater.getLatestFileLink(); // Get the latest link


//CURRENTLY DOESN'T WORK, WIP