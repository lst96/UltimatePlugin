package io.github.xBlackPoison357x.RecipeChanger.Listeners;

import java.util.HashMap;
import java.util.Map;

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
	public void permission(CraftItemEvent event) {
		System.out.println("Event triggered");
		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		String craft = ChatColor.GREEN + plugin.getRecipeChangerConfig().getString("Messages.Permission Granted");
		String nocraft = ChatColor.RED + plugin.getRecipeChangerConfig().getString("Messages.Permission Denied");
		// Map of materials and their corresponding permissions
		Map<Material, String> itemPermissions = new HashMap<>();
	    itemPermissions.put(Material.SADDLE, "recipe.saddle");
	    itemPermissions.put(Material.DIAMOND_HORSE_ARMOR, "recipe.diamondhorse");
	    itemPermissions.put(Material.GOLDEN_HORSE_ARMOR, "recipe.goldhorse");
	    itemPermissions.put(Material.IRON_HORSE_ARMOR, "recipe.ironhorse");
	    itemPermissions.put(Material.NAME_TAG, "recipe.nametag");
	    itemPermissions.put(Material.GUNPOWDER, "recipe.gunpowder");
	    itemPermissions.put(Material.DRAGON_EGG, "recipe.dragonegg");
	    itemPermissions.put(Material.REDSTONE_ORE, "recipe.redstoneore");
	    itemPermissions.put(Material.SPONGE, "recipe.sponge");
	    itemPermissions.put(Material.EXPERIENCE_BOTTLE, "recipe.enchanting");
	    itemPermissions.put(Material.CHAINMAIL_LEGGINGS, "recipe.chainleggings");
	    itemPermissions.put(Material.CHAINMAIL_BOOTS, "recipe.chainboots");
	    itemPermissions.put(Material.CHAINMAIL_HELMET, "recipe.chainhelmet");
	    itemPermissions.put(Material.CHAINMAIL_CHESTPLATE, "recipe.chainchest");
	    itemPermissions.put(Material.TALL_GRASS, "recipe.tallgrass");
	    itemPermissions.put(Material.FIRE, "recipe.fire");
	    itemPermissions.put(Material.ICE, "recipe.ice");
	    itemPermissions.put(Material.GRASS_BLOCK, "recipe.grassblock");
	    itemPermissions.put(Material.COBWEB, "recipe.cobweb");
	    itemPermissions.put(Material.SPAWNER, "recipe.mobspawner");
	    itemPermissions.put(Material.COMMAND_BLOCK, "recipe.command");
	    itemPermissions.put(Material.PODZOL, "recipe.podzol");
	    itemPermissions.put(Material.TALL_GRASS, "recipe.tallgrass");
	    itemPermissions.put(Material.PACKED_ICE, "recipe.packedice");
	    itemPermissions.put(Material.OBSIDIAN, "recipe.obsidian");
	    itemPermissions.put(Material.BEDROCK, "recipe.bedrock");
	    itemPermissions.put(Material.COW_SPAWN_EGG, "recipe.cowegg");
	    itemPermissions.put(Material.CHICKEN_SPAWN_EGG, "recipe.chickenegg");
	    itemPermissions.put(Material.SHEEP_SPAWN_EGG, "recipe.sheepegg");
	    itemPermissions.put(Material.HORSE_SPAWN_EGG, "recipe.horseegg");
	    itemPermissions.put(Material.BAT_SPAWN_EGG, "recipe.bategg");
	    itemPermissions.put(Material.MOOSHROOM_SPAWN_EGG, "recipe.mooshroomegg");
	    itemPermissions.put(Material.OCELOT_SPAWN_EGG, "recipe.ocelotegg");
	    itemPermissions.put(Material.SQUID_SPAWN_EGG, "recipe.squidegg");
	    itemPermissions.put(Material.WOLF_SPAWN_EGG, "recipe.wolfegg");
	    itemPermissions.put(Material.PIG_SPAWN_EGG, "recipe.pigegg");
	    itemPermissions.put(Material.LEATHER, "recipe.leather");
	    itemPermissions.put(Material.COCOA_BEANS, "recipe.bean");
	    itemPermissions.put(Material.MUSIC_DISC_STAL, "recipe.stal");
	    itemPermissions.put(Material.MUSIC_DISC_11, "recipe.11");
	    itemPermissions.put(Material.MUSIC_DISC_FAR, "recipe.far");
		itemPermissions.put(Material.MUSIC_DISC_WARD, "recipe.ward");
		itemPermissions.put(Material.MUSIC_DISC_13, "recipe.13");
		itemPermissions.put(Material.MUSIC_DISC_CAT, "recipe.cat");
		itemPermissions.put(Material.MUSIC_DISC_BLOCKS, "recipe.blocks");
		itemPermissions.put(Material.MUSIC_DISC_MELLOHI, "recipe.mellohi");
		itemPermissions.put(Material.MUSIC_DISC_CHIRP, "recipe.chirp");
		itemPermissions.put(Material.MUSIC_DISC_STRAD, "recipe.strad");
		itemPermissions.put(Material.MUSIC_DISC_MALL, "recipe.mall");
		itemPermissions.put(Material.SHULKER_SHELL, "recipe.shulker");
		itemPermissions.put(Material.REINFORCED_DEEPSLATE, "recipe.reinforceddeepslate");
		// Iterate through materials and check if player has permission
	    if (item == null || item.getType() == Material.AIR) {
	        // Ignore null or air items
	        return;
	    }

	    // Check if the player has permission for the item being crafted
	    String permission = itemPermissions.get(item.getType());
	    if (permission == null || permission.isEmpty() || !player.hasPermission(permission)) {
	        player.sendMessage(ChatColor.RED + nocraft);
	        event.setCancelled(true);
	        player.closeInventory();
	        return;
	    }

	    player.sendMessage(ChatColor.GREEN + craft);
	}
}