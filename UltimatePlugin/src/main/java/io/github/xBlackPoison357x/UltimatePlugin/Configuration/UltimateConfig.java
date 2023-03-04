package io.github.xBlackPoison357x.UltimatePlugin.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class UltimateConfig {
	public UltimatePlugin plugin;

	public UltimateConfig(UltimatePlugin instance) {
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
		ArrayList<String> website = new ArrayList<String>();
		website.add("&1 Website");
		ArrayList<String> donate = new ArrayList<String>();
		donate.add("&2 Donate");
		ArrayList<String> rules = new ArrayList<String>();
		rules.add("&3 Rules");
		ArrayList<String> staff = new ArrayList<String>();
		staff.add("&4 Staff");
		ArrayList<String> twitter = new ArrayList<String>();
		twitter.add("&6 Twitter");
		ArrayList<String> facebook = new ArrayList<String>();
		facebook.add("&7 Facebook");
		ArrayList<String> youtube = new ArrayList<String>();
		youtube.add("&8 Youtube");
		ArrayList<String> extra = new ArrayList<String>();
		extra.add("&9 Extra");
		ArrayList<String> vote = new ArrayList<String>();
		vote.add("&a Vote");
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
		plugin.getInformationConfig().set("Starter Kit", false);
		try {
			plugin.getInformationConfig().save(informationf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setRecipeChangerConfig(File recipechangerf) {
		plugin.getRecipeChangerConfig().set("Horse Armor.Iron", false);
		plugin.getRecipeChangerConfig().set("Horse Armor.Gold", false);
		plugin.getRecipeChangerConfig().set("Horse Armor.Diamond", false);
		plugin.getRecipeChangerConfig().set("Chain Armor.Helmet", false);
		plugin.getRecipeChangerConfig().set("Chain Armor.ChestPiece", false);
		plugin.getRecipeChangerConfig().set("Chain Armor.Leggings", false);
		plugin.getRecipeChangerConfig().set("Chain Armor.Boots", false);
		plugin.getRecipeChangerConfig().set("Spawn Eggs.Bat", false);
		plugin.getRecipeChangerConfig().set("Spawn Eggs.Chicken", false);
		plugin.getRecipeChangerConfig().set("Spawn Eggs.Cow", false);
		plugin.getRecipeChangerConfig().set("Spawn Eggs.Horse", false);
		plugin.getRecipeChangerConfig().set("Spawn Eggs.Mooshroom", false);
		plugin.getRecipeChangerConfig().set("Spawn Eggs.Ocelot", false);
		plugin.getRecipeChangerConfig().set("Spawn Eggs.Pig", false);
		plugin.getRecipeChangerConfig().set("Spawn Eggs.Sheep", false);
		plugin.getRecipeChangerConfig().set("Spawn Eggs.Squid", false);
		plugin.getRecipeChangerConfig().set("Spawn Eggs.Wolf", false);
		plugin.getRecipeChangerConfig().set("Music Discs.stal", false);
		plugin.getRecipeChangerConfig().set("Music Discs.11", false);
		plugin.getRecipeChangerConfig().set("Music Discs.far", false);
		plugin.getRecipeChangerConfig().set("Music Discs.ward", false);
		plugin.getRecipeChangerConfig().set("Music Discs.13", false);
		plugin.getRecipeChangerConfig().set("Music Discs.cat", false);
		plugin.getRecipeChangerConfig().set("Music Discs.blocks", false);
		plugin.getRecipeChangerConfig().set("Music Discs.mellohi", false);
		plugin.getRecipeChangerConfig().set("Music Discs.chirp", false);
		plugin.getRecipeChangerConfig().set("Music Discs.strad", false);
		plugin.getRecipeChangerConfig().set("Music Discs.mall", false);
		plugin.getRecipeChangerConfig().set("Misc.Name Tag", false);
		plugin.getRecipeChangerConfig().set("Misc.Saddle", false);
		plugin.getRecipeChangerConfig().set("Misc.Grass Block", false);
		plugin.getRecipeChangerConfig().set("Misc.Obsidian", false);
		plugin.getRecipeChangerConfig().set("Misc.Grass", false);
		plugin.getRecipeChangerConfig().set("Misc.Ice", false);
		plugin.getRecipeChangerConfig().set("Misc.Fire", false);
		plugin.getRecipeChangerConfig().set("Misc.Bottle o Enchanting", false);
		plugin.getRecipeChangerConfig().set("Misc.Sponge", false);
		plugin.getRecipeChangerConfig().set("Misc.Bedrock", false);
		plugin.getRecipeChangerConfig().set("Misc.Dragon Egg", false);
		plugin.getRecipeChangerConfig().set("Misc.Gunpowder", false);
		plugin.getRecipeChangerConfig().set("Misc.Monster Spawner", false);
		plugin.getRecipeChangerConfig().set("Misc.Command Block", false);
		plugin.getRecipeChangerConfig().set("Misc.Podzol", false);
		plugin.getRecipeChangerConfig().set("Misc.Double Tallgrass", false);
		plugin.getRecipeChangerConfig().set("Misc.Packed Ice", false);
		plugin.getRecipeChangerConfig().set("Misc.Leather", false);
		plugin.getRecipeChangerConfig().set("Misc.Cocoa Beans", false);
		plugin.getRecipeChangerConfig().set("Misc.Shulker Shell", false);
		plugin.getRecipeChangerConfig().set("Misc.Reinforced Deepslate", false);
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