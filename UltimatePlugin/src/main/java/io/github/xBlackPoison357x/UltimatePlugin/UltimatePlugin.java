package io.github.xBlackPoison357x.UltimatePlugin;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bstats.bukkit.Metrics;
import net.gravitydevelopment.updater.Updater;
import net.gravitydevelopment.updater.Updater.ReleaseType;
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
	Server server = Bukkit.getServer();
	UltimateConfig ul = new UltimateConfig(this);
	public ConsoleCommandSender console = server.getConsoleSender();
	private File configf;
	private File disableexpf;
	public File frameprotectorf;
	private File recipechangerf;
	private File informationf;
	private File disablecommandsf;
	private File disablecommandmessagesf;
	private FileConfiguration config;
	private FileConfiguration DisableEXP;
	private FileConfiguration FrameProtector;
	private FileConfiguration RecipeChanger;
	private FileConfiguration Information;
	private FileConfiguration DisableCommands;
	private FileConfiguration DisableCommandMessages;
	public static boolean update = false;
	public static String name = "";
	public static ReleaseType type = null;
	public static String version = "";
	public static String link = "";

	public void onEnable() {
		pdfFile.getDescription();
		PREFIX = ChatColor.GREEN + "[" + pdfFile.getName() + "]";
		console.sendMessage(String.valueOf(PREFIX) + ChatColor.GREEN + " UltimatePlugin version " + pdfFile.getVersion()
				+ " has been enabled.");
		console.sendMessage(String.valueOf(PREFIX) + ChatColor.GREEN + " Developed by: " + pdfFile.getAuthors());
		createFiles();
		Crafting cr = new Crafting(this);
		cr.SetupCrafting();
		System.gc();
		int pluginID = 17259;
		Metrics metrics = new Metrics(this, pluginID);
		if (getDefaultConfig().getBoolean("Enabled Plugin Components.Information")) {
			getCommand("website").setExecutor((CommandExecutor) new Website(this));
			getCommand("donate").setExecutor((CommandExecutor) new Donate(this));
			getCommand("player").setExecutor((CommandExecutor) new PlayerInfo(this));
			getCommand("infovote").setExecutor((CommandExecutor) new Vote(this));
			getCommand("staff").setExecutor((CommandExecutor) new Staff(this));
			getCommand("rules").setExecutor((CommandExecutor) new Rules(this));
			getCommand("ram").setExecutor((CommandExecutor) new Ram(this));
			getCommand("motd").setExecutor((CommandExecutor) new Motd(this));
			getCommand("online").setExecutor((CommandExecutor) new Online(this));
			getCommand("ip").setExecutor((CommandExecutor) new Ip(this));
			getCommand("twitter").setExecutor((CommandExecutor) new Twitter(this));
			getCommand("facebook").setExecutor((CommandExecutor) new Facebook(this));
			getCommand("einfo").setExecutor((CommandExecutor) new Einfo(this));
			getCommand("youtube").setExecutor((CommandExecutor) new Youtube(this));
			getCommand("stats").setExecutor((CommandExecutor) new Stats(this));
			getCommand("enchantall").setExecutor((CommandExecutor) new Enchant(this));
			getCommand("uuid").setExecutor((CommandExecutor) new PlayerUUID(this));
			getServer().getPluginManager().registerEvents((Listener) new BossMessage(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new Elistener(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new Flight(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new Creative(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new JoinWorld(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new NetherBlock(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new Kits(this), (Plugin) this);
			getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) this, (Runnable) new Tps(), 100L, 1L);

		}
		if (getDefaultConfig().getBoolean("Enabled Plugin Components.RecipeChanger")) {
			getServer().getPluginManager().registerEvents((Listener) new Permissions(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new Crafting(this), (Plugin) this);
		}
		if (getDefaultConfig().getBoolean("Enabled Plugin Components.FrameProtector")) {
			getServer().getPluginManager().registerEvents((Listener) new ItemRemove(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new ItemFramePlace(this), (Plugin) this);
		}
		if (getDefaultConfig().getBoolean("Enabled Plugin Components.DisableEXP")) {
			getServer().getPluginManager().registerEvents((Listener) new EntityDeathEvents(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new BlockBreakEvents(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new FurnaceExtractEvents(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new PlayerFishEvents(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new ExpBottleEvents(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new PlayerExpChangeEvents(this), (Plugin) this);
			getServer().getPluginManager().registerEvents((Listener) new EntityBreedEvents(this), (Plugin) this);
		}
		if (getDefaultConfig().getBoolean("Enabled Plugin Components.DisableCommands")) {
			getCommand("disablecommands").setExecutor((CommandExecutor) new ARLRCommand(this));
			getServer().getPluginManager().registerEvents((Listener) new CommandBlock(this), (Plugin) this);
		}
		getCommand("ultimateupdate").setExecutor((CommandExecutor) new UltimateUpdate(this));
		joinUpdater();
		autoUpdate = getDefaultConfig().getBoolean("autoupdate");
		if (autoUpdate) {
			setupUpdater();
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

	private void createFiles() {
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

	public void joinUpdater() {
		Updater updater = new Updater(this, 102168, getFile(), Updater.UpdateType.NO_DOWNLOAD, false); // Start Updater
																										// but just do a
																										// version check
		update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE; // Determine if there is an update ready
																				// for us
		name = updater.getLatestName(); // Get the latest name
		version = updater.getLatestGameVersion(); // Get the latest game version
		type = updater.getLatestType(); // Get the latest file's type
		link = updater.getLatestFileLink(); // Get the latest link
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