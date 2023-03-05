package io.github.xBlackPoison357x.RecipeChanger.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Permissions implements Listener {
	public UltimatePlugin plugin;

	public Permissions(UltimatePlugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void permission(CraftItemEvent e) {
		Player player = (Player) e.getWhoClicked();
		String craft = ChatColor.GREEN + plugin.getRecipeChangerConfig().getString("Messages.Permission Granted");
		String nocraft = ChatColor.RED + plugin.getRecipeChangerConfig().getString("Messages.Permission Denied");
		ItemStack saddle = new ItemStack(Material.SADDLE);
		ItemStack diamondhorse = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
		ItemStack goldhorse = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
		ItemStack ironhorse = new ItemStack(Material.IRON_HORSE_ARMOR);
		ItemStack nametag = new ItemStack(Material.NAME_TAG);
		ItemStack gunpowder = new ItemStack(Material.GUNPOWDER);
		ItemStack dragonegg = new ItemStack(Material.DRAGON_EGG);
		ItemStack redstoneore = new ItemStack(Material.REDSTONE_ORE);
		ItemStack sponge = new ItemStack(Material.SPONGE);
		ItemStack enchanting = new ItemStack(Material.EXPERIENCE_BOTTLE);
		ItemStack chainleggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemStack chainboots = new ItemStack(Material.CHAINMAIL_BOOTS);
		ItemStack chainhelmet = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemStack chainchest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemStack tallgrass = new ItemStack(Material.TALL_GRASS);
		ItemStack fire = new ItemStack(Material.FIRE);
		ItemStack ice = new ItemStack(Material.ICE);
		ItemStack grassblock = new ItemStack(Material.GRASS_BLOCK);
		ItemStack cobweb = new ItemStack(Material.COBWEB);
		ItemStack mobspawner = new ItemStack(Material.SPAWNER);
		ItemStack command = new ItemStack(Material.COMMAND_BLOCK);
		ItemStack podzol = new ItemStack(Material.PODZOL);
		ItemStack doubletallgrass = new ItemStack(Material.TALL_GRASS);
		ItemStack packedice = new ItemStack(Material.PACKED_ICE);
		ItemStack obsidian = new ItemStack(Material.OBSIDIAN);
		ItemStack bedrock = new ItemStack(Material.BEDROCK);
		ItemStack cowegg = new ItemStack(Material.COW_SPAWN_EGG);
		ItemStack chickenegg = new ItemStack(Material.CHICKEN_SPAWN_EGG);
		ItemStack sheepegg = new ItemStack(Material.SHEEP_SPAWN_EGG);
		ItemStack horseegg = new ItemStack(Material.HORSE_SPAWN_EGG);
		ItemStack bategg = new ItemStack(Material.BAT_SPAWN_EGG);
		ItemStack mooshroomegg = new ItemStack(Material.MOOSHROOM_SPAWN_EGG);
		ItemStack ocelotegg = new ItemStack(Material.OCELOT_SPAWN_EGG);
		ItemStack squidegg = new ItemStack(Material.SQUID_SPAWN_EGG);
		ItemStack wolfegg = new ItemStack(Material.WOLF_SPAWN_EGG);
		ItemStack pigegg = new ItemStack(Material.PIG_SPAWN_EGG);
		ItemStack leather = new ItemStack(Material.LEATHER);
		ItemStack bean = new ItemStack(Material.COCOA_BEANS);
		ItemStack blackdisc = new ItemStack(Material.MUSIC_DISC_STAL);
		ItemStack brokendisc = new ItemStack(Material.MUSIC_DISC_11);
		ItemStack cyandisc = new ItemStack(Material.MUSIC_DISC_FAR);
		ItemStack forestdisc = new ItemStack(Material.MUSIC_DISC_WARD);
		ItemStack golddisc = new ItemStack(Material.MUSIC_DISC_13);
		ItemStack greendisc = new ItemStack(Material.MUSIC_DISC_CAT);
		ItemStack orangedisc = new ItemStack(Material.MUSIC_DISC_BLOCKS);
		ItemStack purpledisc = new ItemStack(Material.MUSIC_DISC_MELLOHI);
		ItemStack reddisc = new ItemStack(Material.MUSIC_DISC_CHIRP);
		ItemStack whitedisc = new ItemStack(Material.MUSIC_DISC_STRAD);
		ItemStack bluedisc = new ItemStack(Material.MUSIC_DISC_MALL);
		ItemStack shulker = new ItemStack(Material.SHULKER_SHELL);
		ItemStack reinforceddeepslate = new ItemStack(Material.REINFORCED_DEEPSLATE);
		if (e.getCurrentItem().equals(saddle)) {
			if (player.hasPermission("recipe.saddle")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(diamondhorse)) {
			if (player.hasPermission("recipe.diamondhorse")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(goldhorse)) {
			if (player.hasPermission("recipe.goldhorse")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(ironhorse)) {
			if (player.hasPermission("recipe.ironhorse")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(nametag)) {
			if (player.hasPermission("recipe.nametag")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(gunpowder)) {
			if (player.hasPermission("recipe.gunpowder")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(dragonegg)) {
			if (player.hasPermission("recipe.dragonegg")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(redstoneore)) {
			if (player.hasPermission("recipe.redstoneore")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(sponge)) {
			if (player.hasPermission("recipe.sponge")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(enchanting)) {
			if (player.hasPermission("recipe.enchanting")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(chainleggings)) {
			if (player.hasPermission("recipe.chainleggings")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(chainboots)) {
			if (player.hasPermission("recipe.chainboots")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(chainhelmet)) {
			if (player.hasPermission("recipe.chainhelmet")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(chainchest)) {
			if (player.hasPermission("recipe.chainchest")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(tallgrass)) {
			if (player.hasPermission("recipe.tallgrass")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(fire)) {
			if (player.hasPermission("recipe.fire")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(ice)) {
			if (player.hasPermission("recipe.ice")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(grassblock)) {
			if (player.hasPermission("recipe.grassblock")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(cobweb)) {
			if (player.hasPermission("recipe.cobweb")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(mobspawner)) {
			if (player.hasPermission("recipe.mobspawner")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(command)) {
			if (player.hasPermission("recipe.command")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(podzol)) {
			if (player.hasPermission("recipe.podzol")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(doubletallgrass)) {
			if (player.hasPermission("recipe.doubletallgrass")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(packedice)) {
			if (player.hasPermission("recipe.packedice")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(obsidian)) {
			if (player.hasPermission("recipe.obsidian")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(bedrock)) {
			if (player.hasPermission("recipe.bedrock")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(cowegg)) {
			if (player.hasPermission("recipe.cowegg")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(chickenegg)) {
			if (player.hasPermission("recipe.chickenegg")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(sheepegg)) {
			if (player.hasPermission("recipe.sheepegg")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(horseegg)) {
			if (player.hasPermission("recipe.horseegg")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(bategg)) {
			if (player.hasPermission("recipe.bategg")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(mooshroomegg)) {
			if (player.hasPermission("recipe.mooshroomegg")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(ocelotegg)) {
			if (player.hasPermission("recipe.ocelotegg")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(squidegg)) {
			if (player.hasPermission("recipe.squidegg")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(wolfegg)) {
			if (player.hasPermission("recipe.wolfegg")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(pigegg)) {
			if (player.hasPermission("recipe.pigegg")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(leather)) {
			if (player.hasPermission("recipe.leather")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(bean)) {
			if (player.hasPermission("recipe.cocoabean")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(blackdisc)) {
			if (player.hasPermission("recipe.stal")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(brokendisc)) {
			if (player.hasPermission("recipe.11")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(cyandisc)) {
			if (player.hasPermission("recipe.far")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(forestdisc)) {
			if (player.hasPermission("recipe.ward")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(golddisc)) {
			if (player.hasPermission("recipe.13")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(greendisc)) {
			if (player.hasPermission("recipe.cat")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(orangedisc)) {
			if (player.hasPermission("recipe.blocks")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(purpledisc)) {
			if (player.hasPermission("recipe.mellohi")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(reddisc)) {
			if (player.hasPermission("recipe.chirp")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(whitedisc)) {
			if (player.hasPermission("recipe.strad")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(bluedisc)) {
			if (player.hasPermission("recipe.mall")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(shulker)) {
			if (player.hasPermission("recipe.shulker")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
		if (e.getCurrentItem().equals(reinforceddeepslate)) {
			if (player.hasPermission("recipe.reinforceddeepslate")) {
				player.sendMessage(craft);
			} else {
				e.setCancelled(true);
				player.closeInventory();
				player.sendMessage(nocraft);
			}
		}
	}
}
