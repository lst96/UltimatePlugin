package io.github.xBlackPoison357x.UltimatePlugin;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import io.github.xBlackPoison357x.DisableCommands.commands.ARLRCommand;
import io.github.xBlackPoison357x.DisableCommands.commands.CommandBlock;
import io.github.xBlackPoison357x.DisableEXP.Listener.BlockBreakEvents;
import io.github.xBlackPoison357x.DisableEXP.Listener.EntityBreedEvents;
import io.github.xBlackPoison357x.DisableEXP.Listener.EntityDeathEvents;
import io.github.xBlackPoison357x.DisableEXP.Listener.ExpBottleEvents;
import io.github.xBlackPoison357x.DisableEXP.Listener.FurnaceExtractEvents;
import io.github.xBlackPoison357x.DisableEXP.Listener.PlayerExpChangeEvents;
import io.github.xBlackPoison357x.DisableEXP.Listener.PlayerFishEvents;
import io.github.xBlackPoison357x.FrameProtector.Listener.ItemFramePlace;
import io.github.xBlackPoison357x.FrameProtector.Listener.ItemRemove;
import io.github.xBlackPoison357x.Information.Commands.Donate;
import io.github.xBlackPoison357x.Information.Commands.Einfo;
import io.github.xBlackPoison357x.Information.Commands.Enchant;
import io.github.xBlackPoison357x.Information.Commands.Facebook;
import io.github.xBlackPoison357x.Information.Commands.IP;
import io.github.xBlackPoison357x.Information.Commands.Motd;
import io.github.xBlackPoison357x.Information.Commands.Online;
import io.github.xBlackPoison357x.Information.Commands.PlayerInfo;
import io.github.xBlackPoison357x.Information.Commands.PlayerUUID;
import io.github.xBlackPoison357x.Information.Commands.Rules;
import io.github.xBlackPoison357x.Information.Commands.Server;
import io.github.xBlackPoison357x.Information.Commands.Staff;
import io.github.xBlackPoison357x.Information.Commands.Stats;
import io.github.xBlackPoison357x.Information.Commands.Twitter;
import io.github.xBlackPoison357x.Information.Commands.Vote;
import io.github.xBlackPoison357x.Information.Commands.Website;
import io.github.xBlackPoison357x.Information.Commands.Youtube;
import io.github.xBlackPoison357x.Information.Listeners.BossMessage;
import io.github.xBlackPoison357x.Information.Listeners.Creative;
import io.github.xBlackPoison357x.Information.Listeners.Elistener;
import io.github.xBlackPoison357x.Information.Listeners.Flight;
import io.github.xBlackPoison357x.Information.Listeners.JoinWorld;
import io.github.xBlackPoison357x.Information.Listeners.Kits;
import io.github.xBlackPoison357x.Information.Listeners.NetherBlock;
import io.github.xBlackPoison357x.Information.Runnables.Tps;
import io.github.xBlackPoison357x.RecipeChanger.Listeners.Crafting;
import io.github.xBlackPoison357x.RecipeChanger.Listeners.Permissions;
import io.github.xBlackPoison357x.UltimatePlugin.Commands.UltimateUpdate;
import io.github.xBlackPoison357x.UltimatePlugin.Configuration.ConfigurationMessages;
import io.github.xBlackPoison357x.UltimatePlugin.Updater.Updater;
import io.github.xBlackPoison357x.UltimatePlugin.bStats.Metrics;

public class UltimatePlugin extends JavaPlugin implements Listener {
	public PluginDescriptionFile pdfFile;
	public String PREFIX;
	public boolean autoUpdate = false;
	public boolean metrics = false;
	Updater updater;
	ConfigurationMessages ul = new ConfigurationMessages(this);
	public ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	public File configf;
	public File disableexpf;
	public File frameprotectorf;
	public File recipechangerf;
	public File informationf;
	public File disablecommandsf;
	public File disablecommandmessagesf;
	public FileConfiguration config;
	public FileConfiguration DisableEXP;
	public FileConfiguration FrameProtector;
	public FileConfiguration RecipeChanger;
	public FileConfiguration Information;
	public FileConfiguration DisableCommands;
	public FileConfiguration DisableCommandMessages;
	
	public void onEnable() {
		pdfFile = getDescription();
		PREFIX = ChatColor.GREEN + "[" + pdfFile.getName() + "]";
		console.sendMessage(String.valueOf(PREFIX) + ChatColor.GREEN + " UltimatePlugin version " + pdfFile.getVersion()
				+ " has been enabled.");
		console.sendMessage(String.valueOf(PREFIX) + ChatColor.GREEN + " Developed by: " + pdfFile.getAuthors());
		createFiles();
		new Metrics(this, 17259);
		if (getDefaultConfig().getBoolean("Enabled Plugin Components.Information")) {
		    String[] commands = {"website", "donate", "player", "infovote", "staff", "rules", "serverinfo", "motd", "online", "ip", "twitter", "facebook", "einfo", "youtube", "stats", "enchantall", "uuid"};
		    Class<?>[] classes = {Website.class, Donate.class, PlayerInfo.class, Vote.class, Staff.class, Rules.class, Server.class, Motd.class, Online.class, IP.class, Twitter.class, Facebook.class, Einfo.class, Youtube.class, Stats.class, Enchant.class, PlayerUUID.class};
		    for (int i = 0; i < commands.length; i++) {
		        if (getCommand(commands[i]) != null) {
		            try {
						getCommand(commands[i]).setExecutor((CommandExecutor) classes[i].getConstructor(getClass()).newInstance(this));
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
		        }
		    }
		    Class<?>[] events = {BossMessage.class, Elistener.class, Flight.class, Creative.class, JoinWorld.class, NetherBlock.class, Kits.class};
		    for (Class<?> event : events) {
		        try {
					getServer().getPluginManager().registerEvents((Listener) event.getConstructor(getClass()).newInstance(this), this);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {				
					e.printStackTrace();
				}
		    }
		    getServer().getScheduler().scheduleSyncRepeatingTask(this, new Tps(), 100L, 1L);
		}
		if (getDefaultConfig().getBoolean("Enabled Plugin Components.RecipeChanger")) {
		    getServer().getPluginManager().registerEvents(new Permissions(this), this);
		    getServer().getPluginManager().registerEvents(new Crafting(this), this);
		    Crafting.getInstance().SetupCrafting();
		}
		if (getDefaultConfig().getBoolean("Enabled Plugin Components.FrameProtector")) {
		    getServer().getPluginManager().registerEvents(new ItemRemove(this), this);
		    getServer().getPluginManager().registerEvents(new ItemFramePlace(this), this);
		}

		if (getDefaultConfig().getBoolean("Enabled Plugin Components.DisableEXP")) {
		    Class<?>[] events = {EntityDeathEvents.class, BlockBreakEvents.class, FurnaceExtractEvents.class, PlayerFishEvents.class, ExpBottleEvents.class, PlayerExpChangeEvents.class, EntityBreedEvents.class};
		    for (Class<?> event : events) {
		        try {
					getServer().getPluginManager().registerEvents((Listener) event.getConstructor(getClass()).newInstance(this), this);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
		    }
		}

		if (getDefaultConfig().getBoolean("Enabled Plugin Components.DisableCommands")) {
		    getCommand("disablecommands").setExecutor(new ARLRCommand(this));
		    getServer().getPluginManager().registerEvents(new CommandBlock(this), this);
		}

		if (getDefaultConfig().getBoolean("Enabled Plugin Components.UltimatePlugin")) {
		    setupUpdater();
		    getCommand("ultimateupdate").setExecutor(new UltimateUpdate(this));
		}
		}
	
	public FileConfiguration getDefaultConfig() {
	    return config;
	}

	public FileConfiguration getDisableEXPConfig() {
	    return DisableEXP;
	}

	public FileConfiguration getDisableCommandsConfig() {
	    return DisableCommands;
	}

	public FileConfiguration getDisableCommandMessagesConfig() {
	    return DisableCommandMessages;
	}

	public FileConfiguration getInformationConfig() {
	    return Information;
	}

	public FileConfiguration getRecipeChangerConfig() {
	    return RecipeChanger;
	}

	public FileConfiguration getFrameProtectorConfig() {
	    return FrameProtector;
	}

	public void createFiles() {
	    configf = new File(getDataFolder(), "config.yml");
	    disableexpf = new File(getDataFolder(), "DisableEXP.yml");
	    disablecommandsf = new File(getDataFolder(), "DisableCommands.yml");
	    disablecommandmessagesf = new File(getDataFolder(), "DisableCommandMessages.yml");
	    informationf = new File(getDataFolder(), "Information.yml");
	    recipechangerf = new File(getDataFolder(), "RecipeChanger.yml");
	    frameprotectorf = new File(getDataFolder(), "FrameProtector.yml");
	    try {
	        for (File file : Arrays.asList(configf, disableexpf, disablecommandsf, disablecommandmessagesf, informationf, recipechangerf, frameprotectorf)) {
	            if (!file.exists()) {
	                file.getParentFile().mkdirs();
	                file.createNewFile();
	            }
	        }
	        config = YamlConfiguration.loadConfiguration(configf);
	        DisableEXP = YamlConfiguration.loadConfiguration(disableexpf);
	        DisableCommands = YamlConfiguration.loadConfiguration(disablecommandsf);
	        DisableCommandMessages = YamlConfiguration.loadConfiguration(disablecommandmessagesf);
	        Information = YamlConfiguration.loadConfiguration(informationf);
	        RecipeChanger = YamlConfiguration.loadConfiguration(recipechangerf);
	        FrameProtector = YamlConfiguration.loadConfiguration(frameprotectorf);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    if (recipechangerf.length() == 0L) {
	        ul.setRecipeChangerConfig(recipechangerf);
	    }
	    if (configf.length() == 0L) {
	        ul.setDefaultConfig(configf);
	    }
	    if (disableexpf.length() == 0L) {
	        ul.setDisableEXPConfig(disableexpf);
	    }
	    if (disablecommandmessagesf.length() == 0L) {
	        ul.setDisableCommandMessagesConfig(disablecommandmessagesf);
	    }
	    if (frameprotectorf.length() == 0L) {
	        ul.setFrameProtectorConfig(frameprotectorf);
	    }
	    if (informationf.length() == 0L) {
	        ul.setInformationConfig(informationf);
	    }
	}

	public void saveDisableCommandsConfig() {
	    try {
	        DisableCommands.save(disablecommandsf);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public void onDisable() {
		Bukkit.getServer().clearRecipes();
		if (autoUpdate) {
			setupUpdater();
		}
	}
	public void commandupdater() {
		new Updater(this, 102168, getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
	}

	public void setupUpdater() {
	    Updater updater = new Updater(this, 102168, getFile(), Updater.UpdateType.DEFAULT, true);
	    Updater.UpdateResult result = updater.getResult();

	    switch (result) {
	        case DISABLED:
	            console.sendMessage(ChatColor.RED + pdfFile.getName() + " autoupdate is disabled in the configuration!");
	            break;
	        case FAIL_APIKEY:
	            console.sendMessage(ChatColor.RED + pdfFile.getName() + " Invalid Plugin API Key");
	            break;
	        case FAIL_BADID:
	            console.sendMessage(ChatColor.RED + pdfFile.getName() + " Invalid Plugin ID");
	            break;
	        case FAIL_DBO:
	            console.sendMessage(ChatColor.RED + pdfFile.getName() + " updater was unable to contact DBO to download the update!");
	            break;
	        case FAIL_DOWNLOAD:
	            console.sendMessage(ChatColor.RED + pdfFile.getName() + " failed to download " + updater.getLatestName());
	            break;
	        case FAIL_NOVERSION:
	            console.sendMessage(ChatColor.RED + pdfFile.getName() + " no file version found!");
	            break;
	        case NO_UPDATE:
	            console.sendMessage(ChatColor.GREEN + updater.getLatestName() + " is the latest version available!");
	            break;
	        case SUCCESS:
	            console.sendMessage(ChatColor.GREEN + "Update " + updater.getLatestName() + " was found and downloaded, will be loaded on next server restart!");
	            break;
	        case UPDATE_AVAILABLE:
	            console.sendMessage(ChatColor.RED + pdfFile.getName() + " has found update" + updater.getLatestName() + ", but it was not downloaded");
	            break;
	        default:
	            break;
	    }
	}
}