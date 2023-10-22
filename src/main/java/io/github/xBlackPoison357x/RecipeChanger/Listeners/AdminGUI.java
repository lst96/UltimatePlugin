package io.github.xBlackPoison357x.RecipeChanger.Listeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class AdminGUI implements CommandExecutor, Listener {
	private Map<UUID, ItemStack> selectedItemsForDeletion = new HashMap<>();

    private final UltimatePlugin plugin;
    private final RecipeChanger recipeChanger;

    public AdminGUI(UltimatePlugin plugin, RecipeChanger recipeChanger) {
        this.plugin = plugin;
        this.recipeChanger = recipeChanger;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        // Check if the player has permission to use the /admingui command
        if (!player.hasPermission("admin.gui") && !player.isOp()) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            // Display the Admin GUI
            displayAdminGUI(player);
        } else if (args[0].equalsIgnoreCase("add")) {
            // Open a blank workbench for adding recipes
            displayAddRecipeWorkbench(player);
        } else if (args[0].equalsIgnoreCase("delete")) {
            // Open a GUI to select a recipe to delete
            displayDeleteRecipeGUI(player);
        } else if (args[0].equalsIgnoreCase("permission")) {
            // If arguments are correct, change recipe permission
            // Example: /admingui permission <recipeName> <permissionString>
            if (args.length != 3) {
                player.sendMessage(ChatColor.RED + "Usage: /admingui permission <recipeName> <permissionString>");
            } else {
                updateRecipePermission(args[1], args[2], player);
            }
        } else {
            player.sendMessage(ChatColor.RED + "Invalid sub-command.");
        }

        return true;
    }


    private void displayAdminGUI(Player player) {
        Inventory adminGUI = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Admin GUI");

        // Crafting table item for adding a recipe
        ItemStack addRecipe = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta addMeta = addRecipe.getItemMeta();
        addMeta.setDisplayName(ChatColor.GREEN + "Add Recipe");
        addRecipe.setItemMeta(addMeta);
        adminGUI.setItem(0, addRecipe);

        // Icon for deleting a recipe
        ItemStack deleteRecipe = new ItemStack(Material.BARRIER);
        ItemMeta deleteMeta = deleteRecipe.getItemMeta();
        deleteMeta.setDisplayName(ChatColor.RED + "Delete Recipe");
        deleteRecipe.setItemMeta(deleteMeta);
        adminGUI.setItem(1, deleteRecipe);

        // Add placeholders for other items or custom recipes here as needed

        player.openInventory(adminGUI);
    }


 // In the AdminGUI class
    public void displayAddRecipeWorkbench(Player player) {
        // Create a workbench GUI
        Inventory workbench = Bukkit.createInventory(player, 9, ChatColor.AQUA + "Add Recipe Workbench");

        // Add slots for defining the recipe
        // For example, you can add 9 slots for a 3x3 crafting recipe
        // Populate these slots with the ingredients (e.g., Material.STONE) to define the recipe

        player.openInventory(workbench);
    }

    public void displayConfirmAddGUI(Player player) {
        Inventory confirmAddGUI = Bukkit.createInventory(player, 9, ChatColor.GREEN + "Confirm Recipe Addition");

        // Create items for confirmation or cancellation
        ItemStack confirmItem = new ItemStack(Material.GREEN_WOOL);
        ItemMeta confirmMeta = confirmItem.getItemMeta();
        confirmMeta.setDisplayName(ChatColor.GREEN + "Confirm Addition");
        confirmItem.setItemMeta(confirmMeta);

        ItemStack cancelItem = new ItemStack(Material.RED_WOOL);
        ItemMeta cancelMeta = cancelItem.getItemMeta();
        cancelMeta.setDisplayName(ChatColor.RED + "Cancel Addition");
        cancelItem.setItemMeta(cancelMeta);

        // Add items to the confirmation GUI
        confirmAddGUI.setItem(3, confirmItem);
        confirmAddGUI.setItem(5, cancelItem);

        player.openInventory(confirmAddGUI);
    }

    public void displayDeleteRecipeGUI(Player player) {
        Inventory deleteGUI = Bukkit.createInventory(player, 27, ChatColor.AQUA + "Delete Recipe");

        // Populate the inventory with available recipes
        Map<String, ItemStack> customRecipeItems = recipeChanger.getCustomRecipeItems();
        for (Map.Entry<String, ItemStack> entry : customRecipeItems.entrySet()) {
            deleteGUI.addItem(entry.getValue());
        }

        // Set up confirm and deny buttons
        ItemStack confirmButton = new ItemStack(Material.GREEN_WOOL);
        ItemMeta confirmMeta = confirmButton.getItemMeta();
        confirmMeta.setDisplayName(ChatColor.GREEN + "Confirm");
        confirmButton.setItemMeta(confirmMeta);

        ItemStack denyButton = new ItemStack(Material.RED_WOOL);
        ItemMeta denyMeta = denyButton.getItemMeta();
        denyMeta.setDisplayName(ChatColor.RED + "Deny");
        denyButton.setItemMeta(denyMeta);

        // Add the confirm and deny buttons to the inventory
        deleteGUI.setItem(18, confirmButton);
        deleteGUI.setItem(26, denyButton);

        player.openInventory(deleteGUI);

        // Store the custom recipes and confirm/deny buttons in player metadata
        player.setMetadata("customRecipes", new FixedMetadataValue(plugin, customRecipeItems));
        player.setMetadata("deleteConfirmButton", new FixedMetadataValue(plugin, confirmButton));
        player.setMetadata("deleteDenyButton", new FixedMetadataValue(plugin, denyButton));
    }



    public void displayConfirmDeleteGUI(Player player, String recipeKey) {
        Inventory confirmDeleteGUI = Bukkit.createInventory(player, 9, ChatColor.RED + "Confirm Deletion");

        // Create items for confirmation or cancellation
        ItemStack confirmItem = new ItemStack(Material.GREEN_WOOL);
        ItemMeta confirmMeta = confirmItem.getItemMeta();
        confirmMeta.setDisplayName(ChatColor.GREEN + "Confirm Deletion");
        confirmItem.setItemMeta(confirmMeta);

        ItemStack cancelItem = new ItemStack(Material.RED_WOOL);
        ItemMeta cancelMeta = cancelItem.getItemMeta();
        cancelMeta.setDisplayName(ChatColor.RED + "Cancel Deletion");
        cancelItem.setItemMeta(cancelMeta);

        // Set the recipe key as the item's lore to identify which recipe to delete
        ItemMeta recipeMeta = confirmItem.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Recipe Key: " + recipeKey);
        recipeMeta.setLore(lore);
        confirmItem.setItemMeta(recipeMeta);

        // Add items to the confirmation GUI
        confirmDeleteGUI.setItem(3, confirmItem);
        confirmDeleteGUI.setItem(5, cancelItem);

        player.openInventory(confirmDeleteGUI);
    }


    private void updateRecipePermission(String recipeName, String permission, Player player) {
        File configFile = new File(plugin.getDataFolder(), "RecipeChanger.yml");

        if (!configFile.exists()) {
            player.sendMessage(ChatColor.RED + "RecipeChanger.yml file not found.");
            return;
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        ConfigurationSection recipeSection = config.getConfigurationSection(recipeName);
        if (recipeSection != null) {
            recipeSection.set("permission", permission);
            try {
                config.save(configFile);
                // Assuming the RecipeChanger class is accessible and has a static method to reload recipes
                recipeChanger.loadRecipes();
                player.sendMessage(ChatColor.GREEN + "Recipe permission updated successfully!");
            } catch (IOException e) {
                player.sendMessage(ChatColor.RED + "Error saving changes to RecipeChanger.yml");
            }
        } else {
            player.sendMessage(ChatColor.RED + "Recipe not found.");
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();

        if (inventory == null) return;
        String inventoryTitle = event.getView().getTitle();

        if (inventoryTitle.equals(ChatColor.AQUA + "Add Recipe Workbench")) {
            event.setCancelled(true);

            if (event.getRawSlot() == 8) { // Clicked the Confirm button
                // Collect and validate recipe data here
                ItemStack recipeTypeItem = inventory.getItem(0); // Assuming this slot has the recipe type
                ItemStack resultTypeItem = inventory.getItem(2); // Assuming this slot has the result item
                ItemStack ingredientsItem = inventory.getItem(4); // Assuming this slot has ingredient data

                Recipe newRecipe = createRecipeFromData(recipeTypeItem, resultTypeItem, ingredientsItem);
                
                if (newRecipe != null) {
                    // Add the recipe using RecipeChanger
                    if (recipeChanger.addRecipe("your_recipe_key", newRecipe, "your_permission")) {
                        player.sendMessage(ChatColor.GREEN + "Recipe added successfully!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Recipe key already in use or other error occurred.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid recipe data. Please check your inputs.");
                }

                // Close the workbench inventory
                player.closeInventory();
            }


        } else if (inventoryTitle.equals(ChatColor.AQUA + "Delete Recipe")) {
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null && clickedItem.hasItemMeta()) {
                String displayName = clickedItem.getItemMeta().getDisplayName();

                if (displayName.equals(ChatColor.GREEN + "Confirm")) {
                    // Clicked the Confirm button
                    if (player.hasMetadata("selectedRecipeForDeletion")) {
                        String recipeName = player.getMetadata("selectedRecipeForDeletion").get(0).asString();
                        if (recipeChanger.deleteRecipe(recipeName)) {
                            player.sendMessage(ChatColor.GREEN + "Recipe deleted successfully!");
                            player.removeMetadata("selectedRecipeForDeletion", plugin);
                        } else {
                            player.sendMessage(ChatColor.RED + "Recipe not found or deletion failed.");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "No recipe selected for deletion.");
                    }
                    event.setCancelled(true);

                } else if (displayName.equals(ChatColor.RED + "Deny")) {
                    // Clicked the Deny button
                    if (player.hasMetadata("selectedRecipeForDeletion")) {
                        player.removeMetadata("selectedRecipeForDeletion", plugin);
                    }
                    player.sendMessage(ChatColor.YELLOW + "Recipe deletion canceled.");
                    event.setCancelled(true);

                } else {
                    // Player clicked a recipe item, so store it for deletion confirmation
                    if (clickedItem.hasItemMeta() && clickedItem.getItemMeta().hasDisplayName()) {
                        String recipeName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());
                        player.setMetadata("selectedRecipeForDeletion", new FixedMetadataValue(plugin, recipeName));
                    }
                }
            }
        }
    }


    private Recipe createRecipeFromData(ItemStack recipeTypeItem, ItemStack resultTypeItem, ItemStack ingredientsItem) {
        if (recipeTypeItem == null || resultTypeItem == null || ingredientsItem == null) {
            // Check if any of the item stacks are null
            return null;
        }

        String recipeType = ChatColor.stripColor(recipeTypeItem.getItemMeta().getDisplayName()); // Get the recipe type

        if (recipeType.equalsIgnoreCase("Shaped")) {
            // Create a ShapedRecipe
            ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(plugin, "your_recipe_key"), resultTypeItem);

            // Set the shape (you may need to adjust this based on your data)
            shapedRecipe.shape("ABC", "DEF", "GHI");

            // Map placeholders (A, B, C, etc.) to ingredients
            // You need to replace these with actual materials based on your data
            shapedRecipe.setIngredient('A', Material.STONE);
            shapedRecipe.setIngredient('B', Material.OAK_PLANKS);
            shapedRecipe.setIngredient('C', Material.GLASS);
            // Repeat for 'D', 'E', 'F', 'G', 'H', 'I'

            // Set the permission
            shapedRecipe.setGroup("your_permission");

            return shapedRecipe;
        } else if (recipeType.equalsIgnoreCase("Shapeless")) {
            // Create a ShapelessRecipe
            ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey(plugin, "your_recipe_key"), resultTypeItem);

            // Add ingredients (you need to replace these with actual materials based on your data)
            shapelessRecipe.addIngredient(Material.STONE);
            shapelessRecipe.addIngredient(Material.OAK_PLANKS);
            shapelessRecipe.addIngredient(Material.GLASS);
            // Repeat for other ingredients

            // Set the permission
            shapelessRecipe.setGroup("your_permission");

            return shapelessRecipe;
        }

        return null; // Return null for unknown recipe type
    }
    

}
