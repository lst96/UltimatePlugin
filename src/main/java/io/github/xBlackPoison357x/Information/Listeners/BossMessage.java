package io.github.xBlackPoison357x.Information.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class BossMessage implements Listener {
	public UltimatePlugin plugin;

	public BossMessage(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
	    if (event.getPlayer().hasPermission("information.bossmessage")) {
	        FileConfiguration infoConfig = plugin.getInformationConfig();
	        if (infoConfig.getBoolean("Boss Message.Enable")) {
	            String text = infoConfig.getString("Boss Message.Text");
	            BarColor color = BarColor.valueOf(infoConfig.getString("Boss Message.Color"));
	            BarStyle style = BarStyle.valueOf(infoConfig.getString("Boss Message.Style"));
	            BarFlag flag = BarFlag.valueOf(infoConfig.getString("Boss Message.Flag"));
	            BossBar boss = Bukkit.createBossBar(text, color, style, new BarFlag[] { flag });
	            boss.setProgress(1.0);
	            boss.addPlayer(event.getPlayer());
	        }
	    }
	}
}