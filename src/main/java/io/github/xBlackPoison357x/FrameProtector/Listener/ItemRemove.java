package io.github.xBlackPoison357x.FrameProtector.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class ItemRemove implements Listener {
	public UltimatePlugin plugin;

	public ItemRemove(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onDestroyByEntity(HangingBreakByEntityEvent event) {
		String msg1 = plugin.getFrameProtectorConfig().getString("Messages.Remove Frame Deny Message");
		Entity ee = event.getEntity();
		Player p = (Player) event.getRemover();
		if (event.getRemover() instanceof Player && event.getEntity().getType() == EntityType.ITEM_FRAME
				&& (!p.isOp() || !p.hasPermission("frame.remove"))
				&& plugin.getFrameProtectorConfig().get(p.getUniqueId().toString()) == p.getUniqueId().toString() + " "
						+ p.getLocation().getWorld().getName()
				&& plugin.getFrameProtectorConfig().get("Item Frame.x") == p.getUniqueId().toString() + " "
						+ ee.getLocation().getBlockX()
				&& plugin.getFrameProtectorConfig().get("Item Frame.y") == p.getUniqueId().toString() + " "
						+ ee.getLocation().getBlockY()
				&& plugin.getFrameProtectorConfig().get("Item Frame.z") == p.getUniqueId().toString() + " "
						+ ee.getLocation().getBlockZ()) {
			event.setCancelled(false);
			Bukkit.broadcastMessage("Event not Canceled");
		} else {
			event.setCancelled(true);
			if (plugin.getFrameProtectorConfig().getBoolean("Messages.Enable")) {
				p.sendMessage(ChatColor.RED + msg1);
				return;
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void OnPlaceByEntity(HangingPlaceEvent event) {
		String msg2 = plugin.getFrameProtectorConfig().getString("Messages.Place Deny Message");
		Player p = event.getPlayer();
		if (event.getEntity().getType() == EntityType.ITEM_FRAME) {
			p.hasPermission("frame.place");
		}
		if (!p.isOp() && !p.hasPermission("frame.place")) {
			event.setCancelled(true);
			if (plugin.getFrameProtectorConfig().getBoolean("Messages.Enable")) {
				p.sendMessage(ChatColor.RED + msg2);
				return;
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void canRotate(PlayerInteractEntityEvent event) {
		String msg3 = plugin.getFrameProtectorConfig().getString("Messages.Rotate Deny Message");
		Entity entity = event.getRightClicked();
		Player player = event.getPlayer();
		if (!entity.getType().equals(EntityType.ITEM_FRAME)) {
			return;
		}
		ItemFrame iFrame = (ItemFrame) entity;
		if (iFrame.getItem().equals(null) || iFrame.getItem().getType().equals(Material.AIR)) {
			return;
		}
		if (!player.isOp() && !player.hasPermission("frame.rotate")) {
			event.setCancelled(true);
			if (plugin.getFrameProtectorConfig().getBoolean("Messages.Enable")) {
				player.sendMessage(ChatColor.RED + msg3);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void ItemRemoval(EntityDamageByEntityEvent e) {
		Player p = null;
		String msg4 = plugin.getFrameProtectorConfig().getString("Messages.Remove Item Deny Message");
		if (e.getDamager() instanceof Player) {
			p = (Player) e.getDamager();
			if (e.getEntity().getType() == EntityType.ITEM_FRAME && !p.isOp()
					&& !p.hasPermission("frame.item.remove")) {
				e.setCancelled(true);
				if (plugin.getFrameProtectorConfig().getBoolean("Messages.Enable")) {
					p.sendMessage(ChatColor.RED + msg4);
				}
			}
		}
		if (e.getDamager() instanceof Projectile && e.getEntity().getType() == EntityType.ITEM_FRAME
				&& !(p.getPlayer().isOp()) && !p.hasPermission("frame.item.remove")) {
			e.setCancelled(true);
			if (plugin.getFrameProtectorConfig().getBoolean("Messages.Enable")) {
				p.sendMessage(ChatColor.RED + msg4);
			}
		}
	}

	// TODO: fix this
	public static final BlockFace[] SIDES = new BlockFace[] { BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH,
			BlockFace.SOUTH, BlockFace.WEST, BlockFace.EAST };

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockBreak(BlockBreakEvent e) {
		for (BlockFace side : SIDES) {
			final Block b = e.getBlock().getRelative(side);
			if (b.getState().getData() instanceof ItemFrame) {
				ItemFrame itemframe = (ItemFrame) b.getState().getData();
				if (b.getRelative(itemframe.getAttachedFace()).equals(e.getBlock())) {
					e.setCancelled(true);
				}
			}
		}
	}
}
