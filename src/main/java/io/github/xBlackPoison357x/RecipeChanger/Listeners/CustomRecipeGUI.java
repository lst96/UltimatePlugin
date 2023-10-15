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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.CraftingInventory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;


public class CustomRecipeGUI implements CommandExecutor, Listener {
    private UltimatePlugin plugin;
    private RecipeChanger recipeChanger;
    private Set<UUID> customWorkbenchPlayers = new HashSet<>();
    private Map<UUID, ItemStack[]> craftingInventoryContents = new HashMap<>();

    public CustomRecipeGUI(UltimatePlugin plugin, RecipeChanger recipeChanger) {
        this.plugin = plugin;
        this.recipeChanger = recipeChanger;
        plugin.getCommand("recipegui").setExecutor(this);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("recipeload").setExecutor(this);
        
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("recipegui")) {
        	if((sender.hasPermission("recipe.gui") || sender.isOp())) {	
            openCustomRecipesGUI(player);
            return true;
        	}
        } else if (command.getName().equalsIgnoreCase("recipeload")) {  //possibly temporary, not certain, plan to implement an admin gui that allows recipes to be added in game.
            if (sender.isOp() || sender.hasPermission("recipe.load")) {
                RecipeChanger.removeRecipesByPrefix("ultimateplugin");
                recipeChanger.loadRecipes();
                sender.sendMessage(ChatColor.GREEN + "Recipes loaded");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrator(s) if you believe that this is in error.");
                return true;
            }
        }
        return false;
    }
	    

    public void openCustomRecipesGUI(Player player) {

        Map<String, ItemStack> customRecipes = getCustomRecipeItems();


        // Calculate inventory size
        int inventorySize = (int) Math.ceil(customRecipes.size() / 9.0) * 9;
        if (inventorySize < 9) {
            inventorySize = 9;
        }

        Inventory customRecipesInventory = Bukkit.createInventory(null, inventorySize, ChatColor.AQUA + "Recipe Changer");
        for (ItemStack recipe : customRecipes.values()) {

            customRecipesInventory.addItem(recipe);
        }

        player.openInventory(customRecipesInventory);
    }

    public void openRecipeWorkbench(Player player, NamespacedKey recipeKey, ItemStack result) {
        // Create a virtual workbench
        InventoryView workbenchView = player.openWorkbench(null, true);
        CraftingInventory workbenchInventory = (CraftingInventory) workbenchView.getTopInventory();

        // Get the recipe using the recipeKey
        Recipe customRecipe = Bukkit.getRecipe(recipeKey);
        if (customRecipe instanceof ShapedRecipe) {
            ShapedRecipe shapedRecipe = (ShapedRecipe) customRecipe;

            // Place the ingredients in the workbench inventory
            String[] shape = shapedRecipe.getShape();
            Map<Character, ItemStack> ingredientMap = shapedRecipe.getIngredientMap();
            int row = 0;
            for (String rowString : shape) {
                int col = 0;
                for (char c : rowString.toCharArray()) {
                    ItemStack ingredient = ingredientMap.get(c);
                    if (ingredient != null) {

                        int slot = row * 3 + col + 1; // Add 1 to the slot index
                        workbenchInventory.setItem(slot, ingredient);
                    }
                    col++;
                }
                row++;
            }
        } else if (customRecipe instanceof ShapelessRecipe) {
            ShapelessRecipe shapelessRecipe = (ShapelessRecipe) customRecipe;

            // Reset the workbench inventory
            for (int i = 0; i < 9; i++) {
                workbenchInventory.setItem(i, null);
            }

            // Place the ingredients in the workbench inventory
            List<ItemStack> ingredients = shapelessRecipe.getIngredientList();
            for (int i = 0; i < ingredients.size(); i++) {

                int slot = i + 1; // Add 1 to the slot index to offset for result slot
                workbenchInventory.setItem(slot, ingredients.get(i));
            }
        }

        // Set the result of the crafting recipe
        workbenchInventory.setResult(result);
        customWorkbenchPlayers.add(player.getUniqueId());
    }
    
    public Map<String, ItemStack> getCustomRecipeItems() {
        Map<String, ItemStack> customRecipes = new HashMap<>();

        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();
        while (recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();
            if (recipe instanceof ShapedRecipe || recipe instanceof ShapelessRecipe) {
                NamespacedKey key = ((Keyed) recipe).getKey();
                if (key != null && key.getNamespace().equals(plugin.getName().toLowerCase())) {
                    String permission = "recipe." + key.getKey();
                    ItemStack item = recipe.getResult().clone();
                    ItemMeta meta = item.getItemMeta();
                    if (meta != null) {
                        meta.setDisplayName(ChatColor.GREEN + key.getKey().toLowerCase().replace("_", " "));
                        List<String> lore = new ArrayList<>();
                        lore.add(ChatColor.GRAY + plugin.getName().toLowerCase() + ":" + ChatColor.YELLOW + key.getKey());
                        meta.setLore(lore);

                        item.setItemMeta(meta);
                    }
                    customRecipes.put(permission, item);
                }
            }
        }

        return customRecipes;
    }

  
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.AQUA + "Recipe Changer")) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || !clickedItem.hasItemMeta()) {
                //catch for players clicking empty slots
                return;
            }

            ItemMeta itemMeta = clickedItem.getItemMeta();
            if (!itemMeta.hasDisplayName() || !itemMeta.hasLore()) {
                plugin.getLogger().info("Clicked item doesn't have display name or lore");
                return;
            }

            NamespacedKey recipeKey = getNamespacedKeyFromLore(itemMeta.getLore().get(0));
            if (recipeKey != null) {
                Player player = (Player) event.getWhoClicked();
                openRecipeWorkbench(player, recipeKey, clickedItem);
            }
        } else if (customWorkbenchPlayers.contains(event.getWhoClicked().getUniqueId())) {
            if (event.getSlot() >= 0 && event.getSlot() <= 9) {
                event.setCancelled(true);
            }
        } 
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (customWorkbenchPlayers.contains(player.getUniqueId())) {
        	craftingInventoryContents.put(player.getUniqueId(), player.getOpenInventory().getTopInventory().getContents());
            customWorkbenchPlayers.remove(player.getUniqueId());

            // Clear the workbench inventory
            CraftingInventory craftingInventory = (CraftingInventory) event.getInventory();
            craftingInventory.clear();
            }
        }
       
    private NamespacedKey getNamespacedKeyFromLore(String loreLine) {
        String strippedLoreLine = ChatColor.stripColor(loreLine);
        String[] splitLoreLine = strippedLoreLine.split(":", 2);
        if (splitLoreLine.length == 2) {
            String namespace = splitLoreLine[0].trim();
            String key = splitLoreLine[1].trim();
            return NamespacedKey.fromString(namespace + ":" + key);
        }
        return null;
    }
}