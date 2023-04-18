package io.github.xBlackPoison357x.Information.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Enchant implements CommandExecutor {
	
	public Enchant(UltimatePlugin instance) {
		// constructor
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	    // Check if the command sender is a player
	    if (!(sender instanceof Player)) {
	        sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
	        return true;
	    }

	    Player player = (Player) sender;

	    // Check if the player is OP or has the "information.enchantall" permission
	    if (!player.isOp() || !player.hasPermission("information.enchantall")) {
	        player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
	        return true;
	    }

	    // Get the item that the player is holding
	    ItemStack item = player.getInventory().getItemInMainHand();

	    // Check if the item is null or air
	    if (item == null || item.getType() == Material.AIR) {
	        player.sendMessage(ChatColor.RED + "You must be holding an item to enchant.");
	        return true;
	    }

	    // Loop through all available enchantments
	    for (Enchantment enchantment : Enchantment.values()) {
	        // Check if the item can be enchanted with this enchantment, if the maximum level is greater than 0, and if it is not a cursed enchantment
	        if (enchantment.canEnchantItem(item) && enchantment.getMaxLevel() > 0 && !isCurseEnchantment(enchantment)) {
	            // Add the enchantment to the item with the maximum level
	            item.addUnsafeEnchantment(enchantment, enchantment.getMaxLevel());
	        }
	    }

	    // Set the item in the player's main hand to the newly enchanted item
	    player.getInventory().setItemInMainHand(item);

	    // Send a message to the player confirming that all available enchantments have been applied to the item in their hand
	    player.sendMessage(ChatColor.GREEN + "All available enchantments have been applied to the item in your hand.");

	    return true;
	}

	// Method to check if an enchantment is a cursed enchantment
	private boolean isCurseEnchantment(Enchantment enchantment) {
	    // Get the lowercase string of the enchantment's key
	    String name = enchantment.getKey().getKey().toLowerCase();
	    // Check if the name contains "curse", "vanish", or "binding"
	    return name.contains("curse") || name.contains("vanish") || name.contains("binding");
	}
}