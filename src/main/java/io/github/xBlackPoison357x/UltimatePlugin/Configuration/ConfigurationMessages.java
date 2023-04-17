package io.github.xBlackPoison357x.UltimatePlugin.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class ConfigurationMessages {
	public UltimatePlugin plugin;

	public ConfigurationMessages(UltimatePlugin instance) {
		plugin = instance;
	}

	public void setDefaultConfig(File configf) {
		plugin.getDefaultConfig().set("UltimatePlugin", plugin.pdfFile.getVersion());
		plugin.getDefaultConfig().set("Enabled Plugin Components.Information", true);
		plugin.getDefaultConfig().set("Enabled Plugin Components.RecipeChanger", true);
		plugin.getDefaultConfig().set("Enabled Plugin Components.DisableEXP", true);
		plugin.getDefaultConfig().set("Enabled Plugin Components.FrameProtector", true);
		plugin.getDefaultConfig().set("Enabled Plugin Components.DisableCommands", true);
		plugin.getDefaultConfig().set("Enabled Plugin Components.UltimatePlugin", true);
		try {
			plugin.getDefaultConfig().save(configf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setDisableEXPConfig(File disableexpf) {
		plugin.getDisableEXPConfig().set("EXP.Ore", false);
		plugin.getDisableEXPConfig().set("EXP.Smelt", false);
		plugin.getDisableEXPConfig().set("EXP.Mob", false);
		plugin.getDisableEXPConfig().set("EXP.Fish", false);
		plugin.getDisableEXPConfig().set("EXP.Exp Bottle", false);
		plugin.getDisableEXPConfig().set("EXP.Breeding", false);
		plugin.getDisableEXPConfig().set("EXP.ALL", false);
		try {
			plugin.getDisableEXPConfig().save(disableexpf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setInformationConfig(File informationf) {
		ArrayList<String> website = new ArrayList<>(Arrays.asList("&1 Website"));
		ArrayList<String> donate = new ArrayList<>(Arrays.asList("&2 Donate"));
		ArrayList<String> rules = new ArrayList<>(Arrays.asList("&3 Rules"));
		ArrayList<String> staff = new ArrayList<>(Arrays.asList("&4 Staff"));
		ArrayList<String> twitter = new ArrayList<>(Arrays.asList("&6 Twitter"));
		ArrayList<String> facebook = new ArrayList<>(Arrays.asList("&7 Facebook"));
		ArrayList<String> youtube = new ArrayList<>(Arrays.asList("&8 Youtube"));
		ArrayList<String> extra = new ArrayList<>(Arrays.asList("&9 Extra"));
		ArrayList<String> vote = new ArrayList<>(Arrays.asList("&a Vote"));

		plugin.getInformationConfig().set("Website", website);
		plugin.getInformationConfig().set("Donate", donate);
		plugin.getInformationConfig().set("Rules", rules);
		plugin.getInformationConfig().set("Staff", staff);
		plugin.getInformationConfig().set("Twitter", twitter);
		plugin.getInformationConfig().set("Facebook", facebook);
		plugin.getInformationConfig().set("Youtube", youtube);
		plugin.getInformationConfig().set("Extra", extra);
		plugin.getInformationConfig().set("Vote", vote);

		plugin.getInformationConfig().set("Disabled Flight Worlds.world", false);
		plugin.getInformationConfig().set("Disabled Flight Worlds.world_nether", false);
		plugin.getInformationConfig().set("Disabled Flight Worlds.world_the_end", false);
		plugin.getInformationConfig().set("Disabled Creative Worlds.world", false);
		plugin.getInformationConfig().set("Disabled Creative Worlds.world_nether", false);
		plugin.getInformationConfig().set("Disabled Creative Worlds.world_the_end", false);
		plugin.getInformationConfig().set("Disabled Join Worlds.world", false);
		plugin.getInformationConfig().set("Disabled Join Worlds.world_nether", false);
		plugin.getInformationConfig().set("Disabled Join Worlds.world_the_end", false);
		plugin.getInformationConfig().set("Boss Message.Color", "RED");
		plugin.getInformationConfig().set("Boss Message.Text", "Welcome to the Server!");
		plugin.getInformationConfig().set("Boss Message.Style", "SOLID");
		plugin.getInformationConfig().set("Boss Message.Flag", "DARKEN_SKY");
		plugin.getInformationConfig().set("Boss Message.Enable", true);
		plugin.getInformationConfig().set("Blocktopofnetherbuilding", false);

		plugin.getInformationConfig().set("Starter Kit Items.helmet.type", Material.IRON_HELMET.toString());
		plugin.getInformationConfig().set("Starter Kit Items.chestplate.type", Material.IRON_CHESTPLATE.toString());
		plugin.getInformationConfig().set("Starter Kit Items.leggings.type", Material.IRON_LEGGINGS.toString());
		plugin.getInformationConfig().set("Starter Kit Items.boots.type", Material.IRON_BOOTS.toString());
		plugin.getInformationConfig().set("Starter Kit Items.sword.type", Material.IRON_SWORD.toString());
		plugin.getInformationConfig().set("Starter Kit Items.pickaxe.type", Material.IRON_PICKAXE.toString());
		plugin.getInformationConfig().set("Starter Kit Items.axe.type", Material.IRON_AXE.toString());
		plugin.getInformationConfig().set("Starter Kit Items.shovel.type", Material.IRON_SHOVEL.toString());
		plugin.getInformationConfig().set("Starter Kit Items.hoe.type", Material.IRON_HOE.toString());
		plugin.getInformationConfig().set("Starter Kit Items.bow.type", Material.BOW.toString());
		plugin.getInformationConfig().set("Starter Kit Items.arrows.type", Material.ARROW.toString());
		plugin.getInformationConfig().set("Starter Kit Items.arrows.amount", 32);
		plugin.getInformationConfig().set("Starter Kit Items.bread.type", Material.BREAD.toString());
		plugin.getInformationConfig().set("Starter Kit Items.bread.amount", 16);


		plugin.getInformationConfig().set("Messages.Permission Denied",
				"I'm sorry, but you do not have permission to perform this command. Please contact the server administrator(s) if you believe that this is in error.");
		plugin.getInformationConfig().set("Messages.Permission.Creative Denied", "You are not allowed to use creative in this world!");
		plugin.getInformationConfig().set("Messages.Permission.Flight Denied", "You are not allowed to fly in this world!");
		plugin.getInformationConfig().set("Messages.Permission.Join World Config Error", "Config Error, all worlds are disabled, please undisable at least 1 world.");
		plugin.getInformationConfig().set("Messages.Permission.Join World Disabled Error", "World disabled, teleporting to");
		plugin.getInformationConfig().set("Messages.Permission.Join World Join Error", "You are not allowed to join");
		
		try {
			plugin.getInformationConfig().save(informationf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setRecipeChangerConfig(File recipechangerf) {
	    String[] keys = {"Horse Armor.Iron", "Horse Armor.Gold", "Horse Armor.Diamond",
	                     "Chain Armor.Helmet", "Chain Armor.ChestPiece", "Chain Armor.Leggings",
	                     "Chain Armor.Boots", "Spawn Eggs.Bat", "Spawn Eggs.Chicken", "Spawn Eggs.Cow",
	                     "Spawn Eggs.Horse", "Spawn Eggs.Mooshroom", "Spawn Eggs.Ocelot", "Spawn Eggs.Pig",
	                     "Spawn Eggs.Sheep", "Spawn Eggs.Squid", "Spawn Eggs.Wolf", "Music Discs.stal",
	                     "Music Discs.11", "Music Discs.far", "Music Discs.ward", "Music Discs.13",
	                     "Music Discs.cat", "Music Discs.blocks", "Music Discs.mellohi", "Music Discs.chirp",
	                     "Music Discs.strad", "Music Discs.mall", "Misc.Name Tag", "Misc.Saddle",
	                     "Misc.Grass Block", "Misc.Obsidian", "Misc.Grass", "Misc.Ice", "Misc.Fire",
	                     "Misc.Bottle o Enchanting", "Misc.Sponge", "Misc.Bedrock", "Misc.Dragon Egg",
	                     "Misc.Gunpowder", "Misc.Monster Spawner", "Misc.Command Block", "Misc.Podzol",
	                     "Misc.Double Tallgrass", "Misc.Packed Ice", "Misc.Leather", "Misc.Cocoa Beans",
	                     "Misc.Shulker Shell", "Misc.Reinforced Deepslate"};

	    for (String key : keys) {
	        plugin.getRecipeChangerConfig().set(key, false);
	    }

	    plugin.getRecipeChangerConfig().set("Messages.Permission Granted", "You have permission to craft this item!");
	    plugin.getRecipeChangerConfig().set("Messages.Permission Denied",
	        "I'm sorry, but you do not have permission to craft this item. Please contact the server administrator(s) if you believe that this is in error.");
	    try {
	        plugin.getRecipeChangerConfig().save(recipechangerf);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public void setDisableCommandMessagesConfig(File disablecommandmessagesf) {
		plugin.getDisableCommandMessagesConfig().set("Messages.Command Deny Message", "That command is not allowed!");
		plugin.getDisableCommandMessagesConfig().set("Messages.Permission Deny Message",
				"I'm sorry, but you do not have permission to perform this command. Please contact the server administrator(s) if you believe that this is in error.");
		plugin.getDisableCommandMessagesConfig().set("Messages.Already Forbidden Message",
				"That command is already forbidden!");
		plugin.getDisableCommandMessagesConfig().set("Messages.Not Forbiddien Message",
				"That command is not forbidden!");
		try {
			plugin.getDisableCommandMessagesConfig().save(disablecommandmessagesf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFrameProtectorConfig(File frameprotectorf) {
		plugin.getFrameProtectorConfig().set("Messages.Enable", true);
		plugin.getFrameProtectorConfig().set("Messages.Remove Frame Deny Message",
				"You are not allowed to remove this ItemFrame!");
		plugin.getFrameProtectorConfig().set("Messages.Place Deny Message",
				"You are not allowed to place this ItemFrame!");
		plugin.getFrameProtectorConfig().set("Messages.Rotate Deny Message",
				"You are not allowed to rotate this ItemFrame!");
		plugin.getFrameProtectorConfig().set("Messages.Remove Item Deny Message",
				"You are not allowed to remove items from this ItemFrame!");
		plugin.getFrameProtectorConfig().set("Messages.Command Deny Message",
				"I'm sorry, but you do not have permission to perform this command. Please contact the server administrator(s) if you believe that this is in error.");
		try {
			plugin.getFrameProtectorConfig().save(frameprotectorf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}