package io.github.xBlackPoison357x.Information.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class BossMessage
implements Listener {
    private UltimatePlugin plugin;

    public BossMessage(UltimatePlugin instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
    	if (this.plugin.getInformationConfig().getBoolean("Boss Message.Enable") & (event.getPlayer().hasPermission("information.bossmessage"))) {
        Player player = event.getPlayer();
        String text = plugin.getInformationConfig().getString("Boss Message.Text");
        String color2 = plugin.getInformationConfig().getString("Boss Message.Color");
        BarColor color = BarColor.valueOf(color2);
        String style2 = plugin.getInformationConfig().getString("Boss Message.Style");
        BarStyle style = BarStyle.valueOf(style2);
        String flag2 = plugin.getInformationConfig().getString("Boss Message.Flag");
        BarFlag flag = BarFlag.valueOf(flag2);
        BossBar boss = Bukkit.createBossBar(text, (BarColor)color, (BarStyle)style, (BarFlag[])new BarFlag[]{flag});
        boss.setProgress(1.0);
        boss.addPlayer(player);
        boss.setColor(color);
        boss.addFlag(flag);
        boss.setStyle(style);
    	}
    }
}

