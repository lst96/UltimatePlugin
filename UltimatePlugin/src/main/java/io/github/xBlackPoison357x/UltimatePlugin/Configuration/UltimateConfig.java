package io.github.xBlackPoison357x.UltimatePlugin.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class UltimateConfig {
    private UltimatePlugin plugin;

    public UltimateConfig(UltimatePlugin instance) {
        this.plugin = instance;
    }

    public void setDefaultConfig(File configf) {
        this.plugin.getDefaultConfig().set("UltimatePlugin", this.plugin.pdfFile.getVersion());
        this.plugin.getDefaultConfig().set("autoupdate", true);
        this.plugin.getDefaultConfig().set("Check Bukkit Compatibility", false);
        this.plugin.getDefaultConfig().set("Enabled Plugin Components.Information", true);
        this.plugin.getDefaultConfig().set("Enabled Plugin Components.RecipeChanger", true);
        this.plugin.getDefaultConfig().set("Enabled Plugin Components.DisableEXP", true);
        this.plugin.getDefaultConfig().set("Enabled Plugin Components.FrameProtector", true);
        this.plugin.getDefaultConfig().set("Enabled Plugin Components.DisableCommands", true);
        try {
            this.plugin.getDefaultConfig().save(configf);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDisableEXPConfig(File disableexpf) {
        this.plugin.getDisableEXPConfig().set("EXP.Ore", false);
        this.plugin.getDisableEXPConfig().set("EXP.Smelt", false);
        this.plugin.getDisableEXPConfig().set("EXP.Mob", false);
        this.plugin.getDisableEXPConfig().set("EXP.Fish", false);
        this.plugin.getDisableEXPConfig().set("EXP.Exp Bottle", false);
        this.plugin.getDisableEXPConfig().set("EXP.Breeding", false);
        this.plugin.getDisableEXPConfig().set("EXP.ALL", false);
        try {
            this.plugin.getDisableEXPConfig().save(disableexpf);
        }
        catch (IOException e) {
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
        this.plugin.getInformationConfig().set("Website", website);
        this.plugin.getInformationConfig().set("Donate", donate);
        this.plugin.getInformationConfig().set("Rules", rules);
        this.plugin.getInformationConfig().set("Staff", staff);
        this.plugin.getInformationConfig().set("Twitter", twitter);
        this.plugin.getInformationConfig().set("Facebook", facebook);
        this.plugin.getInformationConfig().set("Youtube", youtube);
        this.plugin.getInformationConfig().set("Extra", extra);
        this.plugin.getInformationConfig().set("Vote", vote);
        this.plugin.getInformationConfig().set("Disabled Flight Worlds.world", false);
        this.plugin.getInformationConfig().set("Disabled Flight Worlds.world_nether", false);
        this.plugin.getInformationConfig().set("Disabled Flight Worlds.world_the_end", false);
        this.plugin.getInformationConfig().set("Disabled Creative Worlds.world", false);
        this.plugin.getInformationConfig().set("Disabled Creative Worlds.world_nether", false);
        this.plugin.getInformationConfig().set("Disabled Creative Worlds.world_the_end", false);
        this.plugin.getInformationConfig().set("Disabled Join Worlds.world", false);
        this.plugin.getInformationConfig().set("Disabled Join Worlds.world_nether", false);
        this.plugin.getInformationConfig().set("Disabled Join Worlds.world_the_end", false);
        this.plugin.getInformationConfig().set("Boss Message.Color", "RED");
        this.plugin.getInformationConfig().set("Boss Message.Text", "Welcome to the Server!");
        this.plugin.getInformationConfig().set("Boss Message.Style", "SOLID");
        this.plugin.getInformationConfig().set("Boss Message.Flag", "DARKEN_SKY");
        this.plugin.getInformationConfig().set("Boss Message.Enable", true);
        this.plugin.getInformationConfig().set("Blocktopofnetherbuilding", false);
        this.plugin.getInformationConfig().set("Starter Kit", false);
        try {
            this.plugin.getInformationConfig().save(informationf);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRecipeChangerConfig(File recipechangerf) {
        this.plugin.getRecipeChangerConfig().set("Horse Armor.Iron", false);
        this.plugin.getRecipeChangerConfig().set("Horse Armor.Gold", false);
        this.plugin.getRecipeChangerConfig().set("Horse Armor.Diamond", false);
        this.plugin.getRecipeChangerConfig().set("Chain Armor.Helmet", false);
        this.plugin.getRecipeChangerConfig().set("Chain Armor.ChestPiece", false);
        this.plugin.getRecipeChangerConfig().set("Chain Armor.Leggings", false);
        this.plugin.getRecipeChangerConfig().set("Chain Armor.Boots", false);
        this.plugin.getRecipeChangerConfig().set("Spawn Eggs.Bat", false);
        this.plugin.getRecipeChangerConfig().set("Spawn Eggs.Chicken", false);
        this.plugin.getRecipeChangerConfig().set("Spawn Eggs.Cow", false);
        this.plugin.getRecipeChangerConfig().set("Spawn Eggs.Horse", false);
        this.plugin.getRecipeChangerConfig().set("Spawn Eggs.Mooshroom", false);
        this.plugin.getRecipeChangerConfig().set("Spawn Eggs.Ocelot", false);
        this.plugin.getRecipeChangerConfig().set("Spawn Eggs.Pig", false);
        this.plugin.getRecipeChangerConfig().set("Spawn Eggs.Sheep", false);
        this.plugin.getRecipeChangerConfig().set("Spawn Eggs.Squid", false);
        this.plugin.getRecipeChangerConfig().set("Spawn Eggs.Wolf", false);
        this.plugin.getRecipeChangerConfig().set("Music Discs.stal", false);
        this.plugin.getRecipeChangerConfig().set("Music Discs.11", false);
        this.plugin.getRecipeChangerConfig().set("Music Discs.far", false);
        this.plugin.getRecipeChangerConfig().set("Music Discs.ward", false);
        this.plugin.getRecipeChangerConfig().set("Music Discs.13", false);
        this.plugin.getRecipeChangerConfig().set("Music Discs.cat", false);
        this.plugin.getRecipeChangerConfig().set("Music Discs.blocks", false);
        this.plugin.getRecipeChangerConfig().set("Music Discs.mellohi", false);
        this.plugin.getRecipeChangerConfig().set("Music Discs.chirp", false);
        this.plugin.getRecipeChangerConfig().set("Music Discs.strad", false);
        this.plugin.getRecipeChangerConfig().set("Music Discs.mall", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Name Tag", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Saddle", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Grass Block", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Obsidian", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Grass", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Ice", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Fire", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Bottle o Enchanting", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Sponge", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Bedrock", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Dragon Egg", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Gunpowder", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Monster Spawner", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Command Block", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Podzol", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Double Tallgrass", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Packed Ice", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Leather", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Cocoa Beans", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Shulker Shell", false);
        this.plugin.getRecipeChangerConfig().set("Misc.Reinforced Deepslate", false);
        this.plugin.getRecipeChangerConfig().set("Messages.Permission Granted", "You have permission to craft this item!");
        this.plugin.getRecipeChangerConfig().set("Messages.Permission Denied", "I'm sorry, but you do not have permission to craft this item. Please contact the server administrators if you believe that this is in error.");
        try {
            this.plugin.getRecipeChangerConfig().save(recipechangerf);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDisableCommandMessagesConfig(File disablecommandmessagesf) {
        this.plugin.getDisableCommandMessagesConfig().set("Messages.Command Deny Message", "That Command is not allowed");
        this.plugin.getDisableCommandMessagesConfig().set("Messages.Permission Deny Message", "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
        this.plugin.getDisableCommandMessagesConfig().set("Messages.Already Forbidden Message", "That command is already forbidden!");
        this.plugin.getDisableCommandMessagesConfig().set("Messages.Not Forbiddien Message", "That command is not forbidden!");
        try {
            this.plugin.getDisableCommandMessagesConfig().save(disablecommandmessagesf);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFrameProtectorConfig(File frameprotectorf) {
        this.plugin.getFrameProtectorConfig().set("Messages.Enable", true);
        this.plugin.getFrameProtectorConfig().set("Messages.Remove Frame Deny Message", "You are not allowed to remove this ItemFrame!");
        this.plugin.getFrameProtectorConfig().set("Messages.Place Deny Message", "You are not allowed to place this ItemFrame!");
        this.plugin.getFrameProtectorConfig().set("Messages.Rotate Deny Message", "You are not allowed to rotate this ItemFrame!");
        this.plugin.getFrameProtectorConfig().set("Messages.Remove Item Deny Message", "You are not allowed to remove items from this ItemFrame!");
        this.plugin.getFrameProtectorConfig().set("Messages.Command Deny Message", "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
        try {
            this.plugin.getFrameProtectorConfig().save(frameprotectorf);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}