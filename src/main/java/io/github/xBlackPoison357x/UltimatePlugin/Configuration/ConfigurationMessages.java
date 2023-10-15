package io.github.xBlackPoison357x.UltimatePlugin.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class ConfigurationMessages implements Listener {
	public UltimatePlugin plugin;

	public ConfigurationMessages(UltimatePlugin instance) {
		plugin = instance;
	}
	
	public void setRecipeChangerConfig(File recipechangerf) {
		String header = """
		        A few things to note: permission can be anything you want.
		        The recipe name has to be unique and is only used internally for creating recipes and /recipegui. Crafted items retain default naming; if duplicate names are used, only one will be used.
		        FORMATTING has to be exact; if you have any doubt, use any free YML syntax checker available online.
		        Remove the # on the left hand side from the example to use, remember to use space and NOT tab.
		        Notepad++ is the suggested editor for adding recipes; it will aid you in avoiding syntax errors.
		        Use /recipeload to load/change recipes without restarting the server.
		        Example Shaped Recipe
		        ---------------------
		        Recipe for diamond horse armor

		        gold_apple:
		          recipe_type: shaped
		          shape:
		            - NND
		            - DWD
		            - DDD
		          ingredients:
		            W: WHITE_WOOL
		            D: DIAMOND
		            N: AIR
		          result:
		            type: diamond_horse_armor
		            amount: 1
		          permission: recipe.diamondarmor

		        Example Shapeless Recipe
		        ------------------------
		        Recipe for compass

		        compass_recipe:
		          recipe_type: shapeless
		          ingredients:
		            - IRON_INGOT
		            - REDSTONE
		          result:
		            type: COMPASS
		            amount: 1
		          permission: recipe.compass""";
		header = header.replaceAll("(?m)^ ", "");

		try {
		    Files.writeString(recipechangerf.toPath(), "#" + header.replace("\n", "\n#"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}

	public void setDefaultConfig(File configf) {
		FileConfiguration config = plugin.getDefaultConfig();
		config.addDefault("UltimatePlugin", plugin.pdfFile.getVersion());
		config.addDefault("Auto Updater", true);
		config.addDefault("Enabled Plugin Components.Information", true);
		config.addDefault("Enabled Plugin Components.RecipeChanger", true);
		config.addDefault("Enabled Plugin Components.DisableEXP", true);
		config.addDefault("Enabled Plugin Components.FrameProtector", true);
		config.addDefault("Enabled Plugin Components.DisableCommands", true);
		config.addDefault("Enabled Plugin Components.UltimatePlugin", true);
		config.addDefault("Messages.Permission Denied",
				"I'm sorry, but you do not have permission to perform this command. Please contact the server administrator(s) if you believe that this is in error.");
		try {
			config.options().copyDefaults(true);
			config.save(configf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setDisableEXPConfig(File disableexpf) {
		FileConfiguration config = plugin.getDisableEXPConfig();
		config.addDefault("EXP.Ore", false);
		config.addDefault("EXP.Smelt", false);
		config.addDefault("EXP.Mob", false);
		config.addDefault("EXP.Fish", false);
		config.addDefault("EXP.Exp Bottle", false);
		config.addDefault("EXP.Breeding", false);
		config.addDefault("EXP.ALL", false);
		try {
			config.options().copyDefaults(true);
			config.save(disableexpf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setInformationConfig(File informationf) {
		FileConfiguration config = plugin.getInformationConfig();
		ArrayList<String> website = new ArrayList<>(Arrays.asList("&1 Website"));
		ArrayList<String> donate = new ArrayList<>(Arrays.asList("&2 Donate"));
		ArrayList<String> rules = new ArrayList<>(Arrays.asList("&3 Rules"));
		ArrayList<String> staff = new ArrayList<>(Arrays.asList("&4 Staff"));
		ArrayList<String> twitter = new ArrayList<>(Arrays.asList("&6 Twitter"));
		ArrayList<String> facebook = new ArrayList<>(Arrays.asList("&7 Facebook"));
		ArrayList<String> youtube = new ArrayList<>(Arrays.asList("&8 Youtube"));
		ArrayList<String> extra = new ArrayList<>(Arrays.asList("&9 Extra"));
		ArrayList<String> vote = new ArrayList<>(Arrays.asList("&a Vote"));

		config.addDefault("Website", website);
		config.addDefault("Donate", donate);
		config.addDefault("Rules", rules);
		config.addDefault("Staff", staff);
		config.addDefault("Twitter", twitter);
		config.addDefault("Facebook", facebook);
		config.addDefault("Youtube", youtube);
		config.addDefault("Extra", extra);
		config.addDefault("Vote", vote);

		config.addDefault("Disabled Flight Worlds.world", false);
		config.addDefault("Disabled Flight Worlds.world_nether", false);
		config.addDefault("Disabled Flight Worlds.world_the_end", false);
		config.addDefault("Disabled Creative Worlds.world", false);
		config.addDefault("Disabled Creative Worlds.world_nether", false);
		config.addDefault("Disabled Creative Worlds.world_the_end", false);
		config.addDefault("Disabled Join Worlds.world", false);
		config.addDefault("Disabled Join Worlds.world_nether", false);
		config.addDefault("Disabled Join Worlds.world_the_end", false);
		config.addDefault("Boss Message.Color", "RED");
		config.addDefault("Boss Message.Text", "Welcome to the Server!");
		config.addDefault("Boss Message.Style", "SOLID");
		config.addDefault("Boss Message.Flag", "DARKEN_SKY");
		config.addDefault("Boss Message.Enable", true);
		config.addDefault("Blocktopofnetherbuilding", false);

		config.addDefault("Starter Kit Items.helmet.type", Material.IRON_HELMET.toString());
		config.addDefault("Starter Kit Items.chestplate.type", Material.IRON_CHESTPLATE.toString());
		config.addDefault("Starter Kit Items.leggings.type", Material.IRON_LEGGINGS.toString());
		config.addDefault("Starter Kit Items.boots.type", Material.IRON_BOOTS.toString());
		config.addDefault("Starter Kit Items.sword.type", Material.IRON_SWORD.toString());
		config.addDefault("Starter Kit Items.pickaxe.type", Material.IRON_PICKAXE.toString());
		config.addDefault("Starter Kit Items.axe.type", Material.IRON_AXE.toString());
		config.addDefault("Starter Kit Items.shovel.type", Material.IRON_SHOVEL.toString());
		config.addDefault("Starter Kit Items.hoe.type", Material.IRON_HOE.toString());
		config.addDefault("Starter Kit Items.bow.type", Material.BOW.toString());
		config.addDefault("Starter Kit Items.arrows.type", Material.ARROW.toString());
		config.addDefault("Starter Kit Items.arrows.amount", 32);
		config.addDefault("Starter Kit Items.bread.type", Material.BREAD.toString());
		config.addDefault("Starter Kit Items.bread.amount", 16);


		config.addDefault("Messages.Permission Denied",
				"I'm sorry, but you do not have permission to perform this command. Please contact the server administrator(s) if you believe that this is in error.");
		config.addDefault("Messages.Permission.Creative Denied", "You are not allowed to use creative in this world!");
		config.addDefault("Messages.Permission.Flight Denied", "You are not allowed to fly in this world!");
		config.addDefault("Messages.Permission.Join World Config Error", "Config Error, all worlds are disabled, please undisable at least 1 world.");
		config.addDefault("Messages.Permission.Join World Disabled Error", "World disabled, teleporting to");
		config.addDefault("Messages.Permission.Join World Join Error", "You are not allowed to join");
		config.addDefault("Messages.Permission.Top of Nether", "You are not allowed to walk/build on top of the Nether!");
		config.addDefault("Messages.Permission.Top of Nether.Notify", "attempted to walk/build on top of the Nether!");

		try {
			config.options().copyDefaults(true);
			config.save(informationf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setDisableCommandMessagesConfig(File disablecommandmessagesf) {
		FileConfiguration config = plugin.getDisableCommandMessagesConfig();
		config.addDefault("Messages.Command Deny Message", "That command is not allowed!");
		config.addDefault("Messages.Permission Deny Message",
				"I'm sorry, but you do not have permission to perform this command. Please contact the server administrator(s) if you believe that this is in error.");
		config.addDefault("Messages.Already Forbidden Message",
				"That command is already forbidden!");
		config.addDefault("Messages.Not Forbiddien Message",
				"That command is not forbidden!");
		try {
			config.options().copyDefaults(true);
			config.save(disablecommandmessagesf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFrameProtectorConfig(File frameprotectorf) {
		FileConfiguration config = plugin.getFrameProtectorConfig();
		config.addDefault("Messages.Remove Frame Success Message", "Item frame removed successfully.");
		config.addDefault("Messages.Remove Owner Deny Message", "You are not the owner of this item frame and cannot remove it.");
		config.addDefault("Messages.Remove Frame Deny Message",
				"You are not allowed to remove this ItemFrame!");
		config.addDefault("Messages.Place Deny Message",
				"You are not allowed to place this ItemFrame!");
		config.addDefault("Messages.Rotate Deny Message",
				"You are not allowed to rotate this ItemFrame!");
		config.addDefault("Messages.Remove Item Deny Message",
				"You are not allowed to remove items from this ItemFrame!");
		config.addDefault("Messages.Command Deny Message",
				"I'm sorry, but you do not have permission to perform this command. Please contact the server administrator(s) if you believe that this is in error.");
		try {
			config.options().copyDefaults(true);
			config.save(frameprotectorf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}