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
import io.github.xBlackPoison357x.bstats.Metrics;
import io.github.xBlackPoison357x.gravity.updater.Updater;

public class UltimatePlugin
extends JavaPlugin {
    public PluginDescriptionFile pdfFile;
    public String PREFIX;
    public boolean autoUpdate = false;
    public boolean metrics = false;
    Updater updater;
    Server server = Bukkit.getServer();
    UltimateConfig ul = new UltimateConfig(this);
    public ConsoleCommandSender console = this.server.getConsoleSender();
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

    public void onEnable() {
        this.pdfFile = this.getDescription();
        this.PREFIX = ChatColor.GREEN + "[" + this.pdfFile.getName() + "]";
        this.console.sendMessage(String.valueOf(this.PREFIX) + ChatColor.GREEN + " UltimatePlugin version " + this.pdfFile.getVersion() + " has been enabled.");
        this.console.sendMessage(String.valueOf(this.PREFIX) + ChatColor.GREEN + " Developed by: " + this.pdfFile.getAuthors());
        this.createFiles();
        Crafting cr = new Crafting(this);
        cr.SetupCrafting();
        System.gc();
        int pluginID = 17259;
        Metrics metrics = new Metrics(this, pluginID);
        if (this.getDefaultConfig().getBoolean("Enabled Plugin Components.Information")) {
            this.getCommand("website").setExecutor((CommandExecutor)new Website(this));
            this.getCommand("donate").setExecutor((CommandExecutor)new Donate(this));
            this.getCommand("player").setExecutor((CommandExecutor)new PlayerInfo(this));
            this.getCommand("infovote").setExecutor((CommandExecutor)new Vote(this));
            this.getCommand("staff").setExecutor((CommandExecutor)new Staff(this));
            this.getCommand("rules").setExecutor((CommandExecutor)new Rules(this));
            this.getCommand("ram").setExecutor((CommandExecutor)new Ram(this));
            this.getCommand("motd").setExecutor((CommandExecutor)new Motd(this));
            this.getCommand("online").setExecutor((CommandExecutor)new Online(this));
            this.getCommand("ip").setExecutor((CommandExecutor)new Ip(this));
            this.getCommand("twitter").setExecutor((CommandExecutor)new Twitter(this));
            this.getCommand("facebook").setExecutor((CommandExecutor)new Facebook(this));
            this.getCommand("einfo").setExecutor((CommandExecutor)new Einfo(this));
            this.getCommand("youtube").setExecutor((CommandExecutor)new Youtube(this));
            this.getCommand("stats").setExecutor((CommandExecutor)new Stats(this));
            this.getCommand("enchantall").setExecutor((CommandExecutor)new Enchant(this));
            this.getCommand("uuid").setExecutor((CommandExecutor)new PlayerUUID(this));
            this.getServer().getPluginManager().registerEvents((Listener)new BossMessage(this), (Plugin)this);
            this.getServer().getPluginManager().registerEvents((Listener)new Elistener(this), (Plugin)this);
            this.getServer().getPluginManager().registerEvents((Listener)new Flight(this), (Plugin)this);
            this.getServer().getPluginManager().registerEvents((Listener)new Creative(this), (Plugin)this);
            this.getServer().getPluginManager().registerEvents((Listener)new JoinWorld(this), (Plugin)this);
            this.getServer().getPluginManager().registerEvents((Listener)new NetherBlock(this), (Plugin)this);
            this.getServer().getPluginManager().registerEvents((Listener)new Kits(this), (Plugin)this);
            this.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin)this, (Runnable)new Tps(), 100L, 1L);

            }
            if (this.getDefaultConfig().getBoolean("Enabled Plugin Components.RecipeChanger")) {
                this.getServer().getPluginManager().registerEvents((Listener)new Permissions(this), (Plugin)this);
                this.getServer().getPluginManager().registerEvents((Listener)new Crafting(this), (Plugin)this);
            }
            if (this.getDefaultConfig().getBoolean("Enabled Plugin Components.FrameProtector")) {
                this.getServer().getPluginManager().registerEvents((Listener)new ItemRemove(this), (Plugin)this);
                this.getServer().getPluginManager().registerEvents((Listener)new ItemFramePlace(this), (Plugin)this);
            }
            if (this.getDefaultConfig().getBoolean("Enabled Plugin Components.DisableEXP")) {
                this.getServer().getPluginManager().registerEvents((Listener)new EntityDeathEvents(this), (Plugin)this);
                this.getServer().getPluginManager().registerEvents((Listener)new BlockBreakEvents(this), (Plugin)this);
                this.getServer().getPluginManager().registerEvents((Listener)new FurnaceExtractEvents(this), (Plugin)this);
                this.getServer().getPluginManager().registerEvents((Listener)new PlayerFishEvents(this), (Plugin)this);
                this.getServer().getPluginManager().registerEvents((Listener)new ExpBottleEvents(this), (Plugin)this);
                this.getServer().getPluginManager().registerEvents((Listener)new PlayerExpChangeEvents(this), (Plugin)this);
                this.getServer().getPluginManager().registerEvents((Listener)new EntityBreedEvents(this), (Plugin)this);
            }
            if (this.getDefaultConfig().getBoolean("Enabled Plugin Components.DisableCommands")) {
                this.getCommand("disablecommands").setExecutor((CommandExecutor)new ARLRCommand(this));
                this.getServer().getPluginManager().registerEvents((Listener)new CommandBlock(this), (Plugin)this);
            }
            this.getCommand("ultimateupdate").setExecutor((CommandExecutor)new UltimateUpdate(this));
            this.autoUpdate = this.getDefaultConfig().getBoolean("autoupdate");
            if (this.autoUpdate) {
                this.setupUpdater();
            }
            }
    
    public FileConfiguration getDefaultConfig() {
        return this.config;
    }

    public FileConfiguration getDisableEXPConfig() {
        return this.DisableEXP;
    }

    public FileConfiguration getDisableCommandsConfig() {
        return this.DisableCommands;
    }

    public FileConfiguration getDisableCommandMessagesConfig() {
        return this.DisableCommandMessages;
    }

    public FileConfiguration getInformationConfig() {
        return this.Information;
    }

    public FileConfiguration getRecipeChangerConfig() {
        return this.RecipeChanger;
    }

    public FileConfiguration getFrameProtectorConfig() {
        return this.FrameProtector;
    }

    private void createFiles() {
        this.configf = new File(this.getDataFolder(), "config.yml");
        this.disableexpf = new File(this.getDataFolder(), "DisableEXP.yml");
        this.disablecommandsf = new File(this.getDataFolder(), "DisableCommands.yml");
        this.disablecommandmessagesf = new File(this.getDataFolder(), "DisableCommandMessages.yml");
        this.informationf = new File(this.getDataFolder(), "Information.yml");
        this.recipechangerf = new File(this.getDataFolder(), "RecipeChanger.yml");
        this.frameprotectorf = new File(this.getDataFolder(), "FrameProtector.yml");
        if (!this.configf.exists()) {
            this.configf.getParentFile().mkdirs();
            try {
                this.configf.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!this.disableexpf.exists()) {
            this.disableexpf.getParentFile().mkdirs();
            try {
                this.disableexpf.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!this.disablecommandsf.exists()) {
            this.disablecommandsf.getParentFile().mkdirs();
            try {
                this.disablecommandsf.createNewFile();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (!this.disablecommandmessagesf.exists()) {
            this.disablecommandmessagesf.getParentFile().mkdirs();
            try {
                this.disablecommandmessagesf.createNewFile();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (!this.informationf.exists()) {
            this.informationf.getParentFile().mkdirs();
            try {
                this.informationf.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!this.recipechangerf.exists()) {
            this.recipechangerf.getParentFile().mkdirs();
            try {
                this.recipechangerf.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!this.frameprotectorf.exists()) {
            this.frameprotectorf.getParentFile().mkdirs();
            try {
                this.frameprotectorf.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = new YamlConfiguration();
        this.DisableEXP = new YamlConfiguration();
        this.DisableCommands = new YamlConfiguration();
        this.DisableCommandMessages = new YamlConfiguration();
        this.Information = new YamlConfiguration();
        this.RecipeChanger = new YamlConfiguration();
        this.FrameProtector = new YamlConfiguration();
        try {
            this.config.load(this.configf);
            this.DisableEXP.load(this.disableexpf);
            this.DisableCommands.load(this.disablecommandsf);
            this.DisableCommandMessages.load(this.disablecommandmessagesf);
            this.Information.load(this.informationf);
            this.RecipeChanger.load(this.recipechangerf);
            this.FrameProtector.load(this.frameprotectorf);
        }
        catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        if (this.recipechangerf.length() == 0L) {
            this.ul.setRecipeChangerConfig(this.recipechangerf);
        }
        if (this.configf.length() == 0L) {
            this.ul.setDefaultConfig(this.configf);
        }
        if (this.disableexpf.length() == 0L) {
            this.ul.setDisableEXPConfig(this.disableexpf);
        }
        if (this.disablecommandmessagesf.length() == 0L) {
            this.ul.setDisableCommandMessagesConfig(this.disablecommandmessagesf);
        }
        if (this.frameprotectorf.length() == 0L) {
            this.ul.setFrameProtectorConfig(this.frameprotectorf);
        }
        if (this.informationf.length() == 0L) {
            this.ul.setInformationConfig(this.informationf);
        }
    }   
    public void saveDisableCommandsConfig() {
        try {
            this.DisableCommands.save(this.disablecommandsf);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDisable() {
        Bukkit.getServer().clearRecipes();
        if (this.autoUpdate) {
            this.setupUpdater();
        }
    }

    public void setupUpdater() {
        Updater updater = new Updater((Plugin)this, 102168, this.getFile(), Updater.UpdateType.DEFAULT, true);
        Updater.UpdateResult result = updater.getResult();
        if (updater.getResult() == Updater.UpdateResult.SUCCESS) {
            this.console.sendMessage(ChatColor.GREEN + "Update " + updater.getLatestName() + " was found and downloaded, will be loaded on next server restart!");
        }
        if (updater.getResult() == Updater.UpdateResult.NO_UPDATE) {
            this.console.sendMessage(ChatColor.GREEN + updater.getLatestName() + " is the latest version available!");
        }
        if (updater.getResult() == Updater.UpdateResult.DISABLED) {
            this.console.sendMessage(ChatColor.RED + this.pdfFile.getName() + " autoupdate is disabled in the configuration!");
        }
        if (updater.getResult() == Updater.UpdateResult.FAIL_DOWNLOAD) {
            this.console.sendMessage(ChatColor.RED + this.pdfFile.getName() + " failed to download " + updater.getLatestName());
        }
        if (updater.getResult() == Updater.UpdateResult.FAIL_DBO) {
            this.console.sendMessage(ChatColor.RED + this.pdfFile.getName() + " updater was unable to contact DBO to download the update!");
        }
        if (updater.getResult() == Updater.UpdateResult.FAIL_NOVERSION) {
            this.console.sendMessage(ChatColor.RED + this.pdfFile.getName() + " no file version found!");
        }
        if (updater.getResult() == Updater.UpdateResult.FAIL_BADID) {
            this.console.sendMessage(ChatColor.RED + this.pdfFile.getName() + " Invalid Plugin ID");
        }
        if (updater.getResult() == Updater.UpdateResult.FAIL_APIKEY) {
            this.console.sendMessage(ChatColor.RED + this.pdfFile.getName() + " Invalid Plugin API Key");
        }
        if (updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE) {
            this.console.sendMessage(ChatColor.RED + this.pdfFile.getName() + " has found update" + updater.getLatestName() + " , but it was not downloaded");
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