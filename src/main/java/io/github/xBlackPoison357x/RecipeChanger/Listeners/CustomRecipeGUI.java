package io.github.xBlackPoison357x.RecipeChanger.Listeners;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class CustomRecipeGUI implements CommandExecutor, Listener {
    private final UltimatePlugin plugin;
    private final RecipeChanger recipeChanger;
    private final AdminGUI adminGUI;
    private final Set<UUID> customWorkbenchPlayers = new HashSet<>();
    private final Map<UUID, ItemStack[]> craftingInventoryContents = new HashMap<>();

    public CustomRecipeGUI(UltimatePlugin plugin, RecipeChanger recipeChanger, AdminGUI adminGUI) {
        this.plugin = plugin;
        this.recipeChanger = recipeChanger;
        this.adminGUI = adminGUI;
        plugin.getCommand("recipegui").setExecutor(this);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("recipeload").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }
        Player player = (Player) sender;

        switch (command.getName().toLowerCase()) {
            case "recipegui":
                if (player.hasPermission("recipe.gui") || player.isOp()) {
                    openCustomRecipesGUI(player);
                    return true;
                }
                break;
            case "recipeload":
                if (player.isOp() || player.hasPermission("recipe.load")) {
                    RecipeChanger.removeRecipesByPrefix("ultimateplugin");
                    recipeChanger.loadRecipes();
                    sender.sendMessage(ChatColor.GREEN + "Recipes loaded");
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to perform this command.");
                    return true;
                }
        }
        return false;
    }

    private void openCustomRecipesGUI(Player player) {
        Map<String, ItemStack> customRecipes = getCustomRecipeItems();
        int inventorySize = (int) Math.ceil(customRecipes.size() / 9.0) * 9;
        Inventory customRecipesInventory = Bukkit.createInventory(null, inventorySize, ChatColor.AQUA + "Recipe Changer");
        customRecipesInventory.setContents(customRecipes.values().toArray(new ItemStack[0]));
        player.openInventory(customRecipesInventory);
    }

    private void openRecipeWorkbench(Player player, NamespacedKey recipeKey, ItemStack result) {
        InventoryView workbenchView = player.openWorkbench(null, true);
        CraftingInventory workbenchInventory = (CraftingInventory) workbenchView.getTopInventory();
        Recipe customRecipe = Bukkit.getRecipe(recipeKey);

        if (customRecipe instanceof ShapedRecipe) {
            ShapedRecipe shapedRecipe = (ShapedRecipe) customRecipe;
            String[] shape = shapedRecipe.getShape();
            Map<Character, ItemStack> ingredientMap = shapedRecipe.getIngredientMap();
            for (int row = 0; row < shape.length; row++) {
                char[] chars = shape[row].toCharArray();
                for (int col = 0; col < chars.length; col++) {
                    ItemStack ingredient = ingredientMap.get(chars[col]);
                    if (ingredient != null) {
                        workbenchInventory.setItem(row * 3 + col + 1, ingredient);
                    }
                }
            }
        } else if (customRecipe instanceof ShapelessRecipe) {
            ShapelessRecipe shapelessRecipe = (ShapelessRecipe) customRecipe;
            List<ItemStack> ingredients = shapelessRecipe.getIngredientList();
            for (int i = 0; i < ingredients.size(); i++) {
                workbenchInventory.setItem(i, ingredients.get(i));
            }
        }
        workbenchInventory.setResult(result);
        customWorkbenchPlayers.add(player.getUniqueId());
    }

    private Map<String, ItemStack> getCustomRecipeItems() {
        Map<String, ItemStack> customRecipes = new HashMap<>();
        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();
        while (recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();
            if (recipe instanceof Keyed) {
                NamespacedKey key = ((Keyed) recipe).getKey();
                if (key.getNamespace().equalsIgnoreCase(plugin.getName())) {
                    ItemStack item = recipe.getResult().clone();
                    ItemMeta meta = item.getItemMeta();
                    if (meta != null) {
                        meta.setDisplayName(ChatColor.GREEN + key.getKey().replace("_", " "));
                        meta.setLore(Collections.singletonList(ChatColor.GRAY + key.getNamespace() + ":" + ChatColor.YELLOW + key.getKey()));
                        item.setItemMeta(meta);
                    }
                    customRecipes.put("recipe." + key.getKey(), item);
                }
            }
        }
        return customRecipes;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || !clickedItem.hasItemMeta()) return;

        String title = event.getView().getTitle();
        if (title.equals(ChatColor.AQUA + "Admin GUI")) {
            event.setCancelled(true);  // Prevent modification in the Admin GUI

            String displayName = clickedItem.getItemMeta().getDisplayName();

            if (displayName.equals(ChatColor.GREEN + "Add Recipe")) {
                // Call your method for adding a recipe
                adminGUI.displayAddRecipeWorkbench(player);
                return;
            }

            if (displayName.equals(ChatColor.RED + "Delete Recipe")) {
                // Call your method for displaying the delete GUI
                adminGUI.displayDeleteRecipeGUI(player);
                return;
            }
        } 
        else if (event.getView().getTitle().equals(ChatColor.AQUA + "Recipe Changer")) {
            if (event.getClickedInventory() == event.getView().getTopInventory()) {  // Check if the clicked inventory is the GUI
                event.setCancelled(true);  // Prevent modification in the GUI
                if (clickedItem == null || !clickedItem.hasItemMeta()) {
                    // Catch for players clicking empty slots
                    return;
                }

                ItemMeta itemMeta = clickedItem.getItemMeta();
                if (!itemMeta.hasDisplayName() || !itemMeta.hasLore()) {
                    plugin.getLogger().info("Clicked item doesn't have display name or lore");
                    return;
                }

                NamespacedKey recipeKey = getNamespacedKeyFromLore(itemMeta.getLore().get(0));
                if (recipeKey != null) {
                    openRecipeWorkbench(player, recipeKey, clickedItem);
                }
            } 
        } else if (customWorkbenchPlayers.contains(event.getWhoClicked().getUniqueId())) {
            if (event.getSlot() >= 0 && event.getSlot() <= 9 && event.getClickedInventory() == event.getView().getTopInventory()) {  // Ensure the clicked slot is in the custom workbench
                event.setCancelled(true);  // Prevent modification in the custom workbench
            }
        }
    }


    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (customWorkbenchPlayers.contains(player.getUniqueId())) {
            craftingInventoryContents.put(player.getUniqueId(), player.getOpenInventory().getTopInventory().getContents());
            customWorkbenchPlayers.remove(player.getUniqueId());
            CraftingInventory craftingInventory = (CraftingInventory) event.getInventory();
            craftingInventory.clear();
        }
    }

    private NamespacedKey getNamespacedKeyFromLore(String loreLine) {
        String strippedLoreLine = ChatColor.stripColor(loreLine);
        String[] splitLoreLine = strippedLoreLine.split(":", 2);
        if (splitLoreLine.length == 2) {
            return NamespacedKey.fromString(strippedLoreLine);
        }
        return null;
    }
}
