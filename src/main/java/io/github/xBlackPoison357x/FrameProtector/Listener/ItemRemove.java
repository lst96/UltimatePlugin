package io.github.xBlackPoison357x.FrameProtector.Listener;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
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

	public void onBlockBreak(BlockBreakEvent event) {
	    Block brokenBlock = event.getBlock();

	    // Check for item frames within a radius of 2 blocks from the broken block
	    for (Entity entity : brokenBlock.getWorld().getNearbyEntities(brokenBlock.getLocation(), 2, 2, 2)) {
	        if (entity instanceof ItemFrame) {
	            ItemFrame itemFrame = (ItemFrame) entity;

	            // Check if the item frame is attached to the broken block
	            if (itemFrame.getLocation().getBlock().getRelative(itemFrame.getAttachedFace()).equals(brokenBlock)) {
	                Player player = event.getPlayer();
	                if (!player.hasPermission("frame.remove")) {
	                	 player.sendMessage(ChatColor.RED + plugin.getFrameProtectorConfig().getString("Messages.Remove Frame Deny Message"));
	                    event.setCancelled(true);
	                    return;
	                }
	                if (!canRemoveItemFrame(player, itemFrame)) {
	                    player.sendMessage(ChatColor.RED + plugin.getFrameProtectorConfig().getString("Messages.Remove Owner Deny Message"));
	                    event.setCancelled(true);
	                } else {
	                    removeItemFrameFromConfig(player, itemFrame);
	                    player.sendMessage(ChatColor.GREEN + plugin.getFrameProtectorConfig().getString("Messages.Remove Frame Success Message"));
	                }
	                return;
	            }
	        }
	    }
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onItemFrameRemove(HangingBreakByEntityEvent event) {
	    Entity entity = event.getEntity();
	    if (entity.getType() == EntityType.ITEM_FRAME) {
	        ItemFrame itemFrame = (ItemFrame) entity;
	        Player player = null;
	        if (event.getRemover() instanceof Player) {
	            player = (Player) event.getRemover();
	        }

	        if (player != null) {
	            if (canRemoveItemFrame(player, itemFrame)) {
	                if (player.hasPermission("frame.remove") || player.isOp()) {
	                    removeItemFrameFromConfig(player, itemFrame);
	                    player.sendMessage(ChatColor.GREEN + plugin.getFrameProtectorConfig().getString("Messages.Remove Frame Success Message"));
	                } else {
	                    player.sendMessage(ChatColor.RED + plugin.getFrameProtectorConfig().getString("Messages.Remove Frame Deny Message"));
	                    event.setCancelled(true);
	                }
	            } else {
	                event.setCancelled(true);
	            }
	        }
	    }
	}

//if player is allowed to place item frame, if not cancel event.
	@EventHandler(priority = EventPriority.LOWEST)
	public void OnPlaceByEntity(HangingPlaceEvent event) {
		String msg2 = plugin.getFrameProtectorConfig().getString("Messages.Place Deny Message");
		Player p = event.getPlayer();
		if (event.getEntity().getType() == EntityType.ITEM_FRAME) {
			p.hasPermission("frame.place");
		}
		if (!p.isOp() || !p.hasPermission("frame.place")) {
			event.setCancelled(true);
				p.sendMessage(ChatColor.RED + msg2);
				return;
			}
		}
	
//if player is allowed to rotate item frame, if not cancel event
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
		if (!player.isOp() || !player.hasPermission("frame.rotate")) {
			event.setCancelled(true);
				player.sendMessage(ChatColor.RED + msg3);
			}
		}
	
	public void ItemRemoval(EntityDamageByEntityEvent e) {
	    Player p = null;
	    String msg4 = plugin.getFrameProtectorConfig().getString("Messages.Remove Item Deny Message");

	    if (e.getDamager() instanceof Player) {
	        p = (Player) e.getDamager();
	        if (e.getEntity().getType() == EntityType.ITEM_FRAME && (p.isOp() || p.hasPermission("frame.item.remove"))
	                && canRemoveItemFrame(p, (ItemFrame) e.getEntity())) {
	            removeItemFrameFromConfig(p, (ItemFrame) e.getEntity());
	            p.sendMessage(ChatColor.GREEN + plugin.getFrameProtectorConfig().getString("Messages.Remove Frame Success Message"));
	        } else {
	            e.setCancelled(true);
	            if (p != null) {
	                p.sendMessage(ChatColor.RED + msg4);
	            }
	        }
	    }

	    if (p.hasPermission("frame.item.remove") || p.isOp()) {
	        if (e.getDamager() instanceof Projectile && e.getEntity().getType() == EntityType.ITEM_FRAME) {
	            if (canRemoveItemFrame(p, (ItemFrame) e.getEntity())) {
	                removeItemFrameFromConfig(p, (ItemFrame) e.getEntity());
	                if (p != null) {
	                    p.sendMessage(ChatColor.GREEN + plugin.getFrameProtectorConfig().getString("Messages.Remove Frame Success Message"));
	                }
	            } else {
	                e.setCancelled(true);
	                if (p != null) {
	                    p.sendMessage(ChatColor.RED + msg4);
	                }
	            }
	        }
	    } else {
	        e.setCancelled(true);
	        if (p != null) {
	        	p.sendMessage(ChatColor.RED + plugin.getFrameProtectorConfig().getString("Messages.Remove Frame Deny Message"));
	        }
	    }

	    if (e.getDamager() instanceof Player && e.getEntity() instanceof ItemFrame) {
	        Player player = (Player) e.getDamager();
	        ItemFrame itemFrame = (ItemFrame) e.getEntity();
	        if (canRemoveItemFrame(player, itemFrame)) {
	            removeItemFrameFromConfig(player, itemFrame);
	            player.sendMessage(ChatColor.GREEN + plugin.getFrameProtectorConfig().getString("Messages.Remove Frame Success Message"));
	        } else {
	            player.sendMessage(ChatColor.RED + plugin.getFrameProtectorConfig().getString("Messages.Remove Frame Deny Message"));
	            e.setCancelled(true);
	        }
	    }
	 }
	
	//checks if player is owner of itemframe and is allowed to remove it.
	public boolean canRemoveItemFrame(Player player, ItemFrame itemFrame) {
	    if (player.isOp() || player.hasPermission("frame.bypass")) {
	        return true; // allow OP to bypass all checks
	    }
	    String uuid = player.getUniqueId().toString();
	    ConfigurationSection itemFrames = plugin.getFrameProtectorConfig().getConfigurationSection("ItemFrames");
	    if (itemFrames != null) {
	        ConfigurationSection playerSection = itemFrames.getConfigurationSection(uuid);
	        if (playerSection != null) {
	            List<Map<?, ?>> framesList = playerSection.getMapList("frames");
	            for (Map<?, ?> element : framesList) {
	                @SuppressWarnings("unchecked")
	                Map<String, Object> frameMap = (Map<String, Object>) element;
	                if (frameMap != null && frameMap.get("x").equals(itemFrame.getLocation().getBlockX())
	                        && frameMap.get("y").equals(itemFrame.getLocation().getBlockY())
	                        && frameMap.get("z").equals(itemFrame.getLocation().getBlockZ())
	                        && frameMap.get("world").equals(itemFrame.getWorld().getUID().toString())) {
	                    String frameOwnerUUID = playerSection.getName();
	                    // Remove the configuration entry if the player is the owner
	                    if (uuid.equals(frameOwnerUUID)) {
	                        return true;
	                    } else {
	                        return false;
	                    }
	                }
	            }
	        }
	    }
	    return false;
	}

//removes the itemframe from the save file.
	public void removeItemFrameFromConfig(Player player, ItemFrame itemFrame) {
	    String uuid = player.getUniqueId().toString();
	    ConfigurationSection itemFrames = plugin.getFrameProtectorConfig().getConfigurationSection("ItemFrames");
	    if (itemFrames != null) {
	        ConfigurationSection playerSection = itemFrames.getConfigurationSection(uuid);
	        if (playerSection != null) {
	            List<Map<?, ?>> framesList = playerSection.getMapList("frames");
	            for (int i = 0; i < framesList.size(); i++) {
	                @SuppressWarnings("unchecked")
	                Map<String, Object> frameMap = (Map<String, Object>) framesList.get(i);
	                if (frameMap != null && frameMap.get("x").equals(itemFrame.getLocation().getBlockX())
	                        && frameMap.get("y").equals(itemFrame.getLocation().getBlockY())
	                        && frameMap.get("z").equals(itemFrame.getLocation().getBlockZ())
	                        && frameMap.get("world").equals(itemFrame.getWorld().getUID().toString())) {
	                    String frameOwnerUUID = playerSection.getName();
	                    // Remove the configuration entry if the player is the owner
	                    if (uuid.equals(frameOwnerUUID)) {
	                        framesList.remove(i);
	                        playerSection.set("frames", framesList);
	                        try {
	                            plugin.getFrameProtectorConfig().save(plugin.frameprotectorf);
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                    break;
	                }
	            }
	        }
	    }
	}
}