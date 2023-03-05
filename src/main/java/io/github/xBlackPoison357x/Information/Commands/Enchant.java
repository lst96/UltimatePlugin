package io.github.xBlackPoison357x.Information.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Enchant implements CommandExecutor {
	public Enchant(UltimatePlugin instance) {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!commandLabel.equalsIgnoreCase("enchantall") || !(sender instanceof Player)) {
			sender.sendMessage(
					ChatColor.RED + "Error:" + ChatColor.DARK_RED + " Command can only be used by a player!");
			return true;
		}
		if (sender.isOp() || sender.hasPermission("information.enchantall")) {
			Player player = (Player) sender;
			Material i = player.getInventory().getItemInMainHand().getType();
			if (i == Material.DIAMOND_SWORD || i == Material.GOLDEN_SWORD || i == Material.IRON_SWORD
					|| i == Material.WOODEN_SWORD || i == Material.STONE_SWORD) {
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DURABILITY, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.FIRE_ASPECT, 2);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DAMAGE_ALL, 5);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.KNOCKBACK, 2);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
				return true;
			}
			if (i == Material.DIAMOND_PICKAXE || i == Material.GOLDEN_PICKAXE || i == Material.IRON_PICKAXE
					|| i == Material.WOODEN_PICKAXE || i == Material.STONE_PICKAXE) {
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DURABILITY, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DIG_SPEED, 5);
				return true;
			}
			if (i == Material.DIAMOND_AXE || i == Material.GOLDEN_AXE || i == Material.IRON_AXE
					|| i == Material.WOODEN_AXE || i == Material.STONE_AXE) {
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DURABILITY, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DIG_SPEED, 5);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DAMAGE_ALL, 5);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
				return true;
			}
			if (i == Material.DIAMOND_SHOVEL || i == Material.GOLDEN_SHOVEL || i == Material.IRON_SHOVEL
					|| i == Material.WOODEN_SHOVEL || i == Material.STONE_SHOVEL) {
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DURABILITY, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DIG_SPEED, 5);
				return true;
			}
			if (i == Material.DIAMOND_HOE || i == Material.GOLDEN_HOE || i == Material.IRON_HOE
					|| i == Material.WOODEN_HOE || i == Material.STONE_HOE) {
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DURABILITY, 3);
				return true;
			}
			if (i == Material.FISHING_ROD) {
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DURABILITY, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.LUCK, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.LURE, 3);
				return true;
			}
			if (i == Material.BOW) {
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.ARROW_INFINITE, 1);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.ARROW_DAMAGE, 5);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.ARROW_FIRE, 1);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DURABILITY, 3);
				return true;
			}
			if (i == Material.DIAMOND_HELMET || i == Material.GOLDEN_HELMET || i == Material.IRON_HELMET
					|| i == Material.LEATHER_HELMET || i == Material.CHAINMAIL_HELMET) {
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DURABILITY, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.WATER_WORKER, 1);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.OXYGEN, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_FIRE, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.THORNS, 3);
				return true;
			}
			if (i == Material.DIAMOND_CHESTPLATE || i == Material.GOLDEN_CHESTPLATE || i == Material.IRON_CHESTPLATE
					|| i == Material.LEATHER_CHESTPLATE || i == Material.CHAINMAIL_CHESTPLATE) {
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DURABILITY, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_FIRE, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.THORNS, 3);
				return true;
			}
			if (i == Material.DIAMOND_LEGGINGS || i == Material.GOLDEN_LEGGINGS || i == Material.IRON_LEGGINGS
					|| i == Material.LEATHER_LEGGINGS || i == Material.CHAINMAIL_LEGGINGS) {
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DURABILITY, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_FIRE, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.THORNS, 3);
				return true;
			}
			if (i == Material.DIAMOND_BOOTS || i == Material.GOLDEN_BOOTS || i == Material.IRON_BOOTS
					|| i == Material.LEATHER_BOOTS || i == Material.CHAINMAIL_BOOTS) {
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.DURABILITY, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_FIRE, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.THORNS, 3);
				player.getInventory().getItemInMainHand().addEnchantment(Enchantment.PROTECTION_FALL, 4);
				return true;
			}
			if (i != Material.DIAMOND_SWORD || i != Material.GOLDEN_SWORD || i != Material.IRON_SWORD
					|| i != Material.WOODEN_SWORD || i != Material.STONE_SWORD || i != Material.DIAMOND_PICKAXE
					|| i != Material.GOLDEN_PICKAXE || i != Material.IRON_PICKAXE || i != Material.WOODEN_PICKAXE
					|| i != Material.STONE_PICKAXE || i != Material.DIAMOND_AXE || i != Material.GOLDEN_AXE
					|| i != Material.IRON_AXE || i != Material.WOODEN_AXE || i != Material.STONE_AXE
					|| i != Material.DIAMOND_SHOVEL || i != Material.GOLDEN_SHOVEL || i != Material.IRON_SHOVEL
					|| i != Material.WOODEN_SHOVEL || i != Material.STONE_SHOVEL || i != Material.DIAMOND_HOE
					|| i != Material.GOLDEN_HOE || i != Material.IRON_HOE || i != Material.WOODEN_HOE
					|| i != Material.STONE_HOE || i != Material.FISHING_ROD || i != Material.BOW
					|| i != Material.DIAMOND_HELMET || i != Material.GOLDEN_HELMET || i != Material.IRON_HELMET
					|| i != Material.LEATHER_HELMET || i != Material.CHAINMAIL_HELMET
					|| i != Material.DIAMOND_CHESTPLATE || i != Material.GOLDEN_CHESTPLATE
					|| i != Material.IRON_CHESTPLATE || i != Material.LEATHER_CHESTPLATE
					|| i != Material.CHAINMAIL_CHESTPLATE || i != Material.DIAMOND_LEGGINGS
					|| i != Material.GOLDEN_LEGGINGS || i != Material.IRON_LEGGINGS || i != Material.LEATHER_LEGGINGS
					|| i != Material.CHAINMAIL_LEGGINGS || i != Material.DIAMOND_BOOTS || i != Material.GOLDEN_BOOTS
					|| i != Material.IRON_BOOTS || i != Material.LEATHER_BOOTS || i == Material.CHAINMAIL_BOOTS) {
				// empty if block
			}
			player.sendMessage(ChatColor.RED + "Error:" + ChatColor.DARK_RED + " Item in hand can't be enchanted!");
			return true;
		}
		return false;
	}
}
