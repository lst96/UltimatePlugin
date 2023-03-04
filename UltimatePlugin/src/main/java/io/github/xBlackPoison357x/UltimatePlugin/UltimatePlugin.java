package io.github.xBlackPoison357x.UltimatePlugin;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bstats.bukkit.Metrics;
import net.gravitydevelopment.updater.Updater;
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
import io.github.xBlackPoison357x.Information.Commands.Ip;
import io.github.xBlackPoison357x.Information.Commands.Motd;
import io.github.xBlackPoison357x.Information.Commands.Online;
import io.github.xBlackPoison357x.Information.Commands.PlayerInfo;
import io.github.xBlackPoison357x.Information.Commands.PlayerUUID;
import io.github.xBlackPoison357x.Information.Commands.Ram;
import io.github.xBlackPoison357x.Information.Commands.Rules;
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
import io.github.xBlackPoison357x.UltimatePlugin.Configuration.UltimateConfig;

public class UltimatePlugin extends JavaPlugin {
	public PluginDescriptionFile pdfFile;
	public String PREFIX;
	public boolean autoUpdate = false;
	public boolean metrics = false;
	Updater updater;
	UltimateConfig ul = new UltimateConfig(this);
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
		PREFIX = ChatColor.GREEN + "[" + pdfFile.getName() + "]";
		console.sendMessage(String.valueOf(PREFIX) + ChatColor.GREEN + " UltimatePlugin version " + pdfFile.getVersion()
				+ " has been enabled.");
		console.sendMessage(String.valueOf(PREFIX) + ChatColor.GREEN + " Developed by: " + pdfFile.getAuthors());
		createFiles();
		new Metrics(this, 17259);
		if (getDefaultConfig().getBoolean("Enabled Plugin Components.Information")) {
			getCommand("website").setExecutor(new Website(this));
			getCommand("donate").setExecutor(new Donate(this));
			getCommand("player").setExecutor(new PlayerInfo(this));
			getCommand("infovote").setExecutor(new Vote(this));
			getCommand("staff").setExecutor(new Staff(this));
			getCommand("rules").setExecutor(new Rules(this));
			getCommand("ram").setExecutor(new Ram(this));
			getCommand("motd").setExecutor(new Motd(this));
			getCommand("online").setExecutor(new Online(this));
			getCommand("ip").setExecutor(new Ip(this));
			getCommand("twitter").setExecutor(new Twitter(this));
			getCommand("facebook").setExecutor(new Facebook(this));
			getCommand("einfo").setExecutor(new Einfo(this));
			getCommand("youtube").setExecutor(new Youtube(this));
			getCommand("stats").setExecutor(new Stats(this));
			getCommand("enchantall").setExecutor(new Enchant(this));
			getCommand("uuid").setExecutor(new PlayerUUID(this));
			getServer().getPluginManager().registerEvents(new BossMessage(this), this);
			getServer().getPluginManager().registerEvents(new Elistener(this), this);
			getServer().getPluginManager().registerEvents(new Flight(this), this);
			getServer().getPluginManager().registerEvents(new Creative(this), this);
			getServer().getPluginManager().registerEvents(new JoinWorld(this), this);
			getServer().getPluginManager().registerEvents(new NetherBlock(this), this);
			getServer().getPluginManager().registerEvents(new Kits(this), this);
			getServer().getScheduler().scheduleSyncRepeatingTask(this, new Tps(), 100L, 1L);

		}
		if (getDefaultConfig().getBoolean("Enabled Plugin Components.RecipeChanger")) {
			getServer().getPluginManager().registerEvents(new Permissions(this), this);
			getServer().getPluginManager().registerEvents(new Crafting(this), this);
			Crafting.getInstance().SetupCrafting();
		}
		if (getDefaultConfig().getBoolean("Enabled Plugin Components.FrameProtector")) {
			getServer().getPluginManager().registerEvents(new ItemRemove(this), this);
			//getServer().getPluginManager().registerEvents(new ItemFramePlace(this), this);
		}
		if (getDefaultConfig().getBoolean("Enabled Plugin Components.DisableEXP")) {
			getServer().getPluginManager().registerEvents(new EntityDeathEvents(this), this);
			getServer().getPluginManager().registerEvents(new BlockBreakEvents(this), this);
			getServer().getPluginManager().registerEvents(new FurnaceExtractEvents(this), this);
			getServer().getPluginManager().registerEvents(new PlayerFishEvents(this), this);
			getServer().getPluginManager().registerEvents(new ExpBottleEvents(this), this);
			getServer().getPluginManager().registerEvents(new PlayerExpChangeEvents(this), this);
			getServer().getPluginManager().registerEvents(new EntityBreedEvents(this), this);
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
		if (!configf.exists()) {
			configf.getParentFile().mkdirs();
			try {
				configf.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!disableexpf.exists()) {
			disableexpf.getParentFile().mkdirs();
			try {
				disableexpf.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!disablecommandsf.exists()) {
			disablecommandsf.getParentFile().mkdirs();
			try {
				disablecommandsf.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (!disablecommandmessagesf.exists()) {
			disablecommandmessagesf.getParentFile().mkdirs();
			try {
				disablecommandmessagesf.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (!informationf.exists()) {
			informationf.getParentFile().mkdirs();
			try {
				informationf.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!recipechangerf.exists()) {
			recipechangerf.getParentFile().mkdirs();
			try {
				recipechangerf.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!frameprotectorf.exists()) {
			frameprotectorf.getParentFile().mkdirs();
			try {
				frameprotectorf.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		config = new YamlConfiguration();
		DisableEXP = new YamlConfiguration();
		DisableCommands = new YamlConfiguration();
		DisableCommandMessages = new YamlConfiguration();
		Information = new YamlConfiguration();
		RecipeChanger = new YamlConfiguration();
		FrameProtector = new YamlConfiguration();
		try {
			config.load(configf);
			DisableEXP.load(disableexpf);
			DisableCommands.load(disablecommandsf);
			DisableCommandMessages.load(disablecommandmessagesf);
			Information.load(informationf);
			RecipeChanger.load(recipechangerf);
			FrameProtector.load(frameprotectorf);
		} catch (IOException | InvalidConfigurationException e) {
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
		if (updater.getResult() == Updater.UpdateResult.SUCCESS) {
			console.sendMessage(ChatColor.GREEN + "Update " + updater.getLatestName()
					+ " was found and downloaded, will be loaded on next server restart!");
		}
		if (updater.getResult() == Updater.UpdateResult.NO_UPDATE) {
			console.sendMessage(ChatColor.GREEN + updater.getLatestName() + " is the latest version available!");
		}
		if (updater.getResult() == Updater.UpdateResult.DISABLED) {
			console.sendMessage(ChatColor.RED + pdfFile.getName() + " autoupdate is disabled in the configuration!");
		}
		if (updater.getResult() == Updater.UpdateResult.FAIL_DOWNLOAD) {
			console.sendMessage(ChatColor.RED + pdfFile.getName() + " failed to download " + updater.getLatestName());
		}
		if (updater.getResult() == Updater.UpdateResult.FAIL_DBO) {
			console.sendMessage(
					ChatColor.RED + pdfFile.getName() + " updater was unable to contact DBO to download the update!");
		}
		if (updater.getResult() == Updater.UpdateResult.FAIL_NOVERSION) {
			console.sendMessage(ChatColor.RED + pdfFile.getName() + " no file version found!");
		}
		if (updater.getResult() == Updater.UpdateResult.FAIL_BADID) {
			console.sendMessage(ChatColor.RED + pdfFile.getName() + " Invalid Plugin ID");
		}
		if (updater.getResult() == Updater.UpdateResult.FAIL_APIKEY) {
			console.sendMessage(ChatColor.RED + pdfFile.getName() + " Invalid Plugin API Key");
		}
		if (updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE) {
			console.sendMessage(ChatColor.RED + pdfFile.getName() + " has found update" + updater.getLatestName()
					+ " , but it was not downloaded");
		}
		switch (result) {
		case DISABLED: {
			break;
		}
		case FAIL_APIKEY: {
			break;
		}
		case FAIL_BADID: {
			break;
		}
		case FAIL_DBO: {
			break;
		}
		case FAIL_DOWNLOAD: {
			break;
		}
		case FAIL_NOVERSION: {
			break;
		}
		case NO_UPDATE: {
			break;
		}
		case SUCCESS: {
			break;
		}
		case UPDATE_AVAILABLE: {
			break;
		}
		}
	}
}