package io.github.xBlackPoison357x.RecipeChanger.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;
import org.bukkit.inventory.ShapelessRecipe;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;

public class RecipeChanger implements Listener {
    public UltimatePlugin plugin;

    public RecipeChanger(UltimatePlugin instance) {
        plugin = instance;
    }

    public void loadRecipes() {
        // Load recipe data from configuration file
        File configFile = new File(plugin.getDataFolder(), "RecipeChanger.yml");

        try (InputStream inputStream = new FileInputStream(configFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            YamlConfiguration config = new YamlConfiguration();
            config.load(reader);

            // Iterate over each recipe in the configuration file
            for (String recipeKey : config.getKeys(false)) {
                ConfigurationSection recipeData = config.getConfigurationSection(recipeKey);

                // Parse recipe data and add recipe to game registry
                Recipe recipe = parseRecipeData(recipeData);
                if (recipe != null) {
                    Bukkit.addRecipe(recipe);
                }
            }
        } catch (Exception ex) {
            plugin.getLogger().severe("Error loading recipes from configuration file: " + ex.getMessage());
        }
    }



    private Recipe parseRecipeData(ConfigurationSection recipeData) {
        // Check that recipe data contains required fields
        if (!recipeData.contains("recipe_type") || !recipeData.contains("key") || !recipeData.contains("result")) {
            plugin.getLogger().warning("Invalid recipe data for recipe " + recipeData.getName() + ": required fields missing");
            return null;
        }

        // Parse recipe type
        String recipeType = recipeData.getString("recipe_type");

        // Parse recipe shape
        List<String> shape = recipeData.getStringList("shape");
        if (shape.size() != 3) {
            plugin.getLogger().warning("Invalid recipe shape for recipe " + recipeData.getName() + ": shape must have exactly three rows");
            return null;
        }

        // Parse recipe key
        Map<Character, Material> key = new HashMap<>();
        ConfigurationSection keyData = recipeData.getConfigurationSection("key");
        for (String keyChar : keyData.getKeys(false)) {
            String keyMaterialName = keyData.getString(keyChar);
            if (keyMaterialName == null) {
                plugin.getLogger().warning("Invalid recipe key material for recipe " + recipeData.getName() + ": null key material for key '" + keyChar + "'");
                return null;
            }
            Material keyMaterial = Material.matchMaterial(keyMaterialName);
            if (keyMaterial == null) {
                plugin.getLogger().warning("Invalid recipe key material for recipe " + recipeData.getName() + ": unknown material '" + keyMaterialName + "' for key '" + keyChar + "'");
                return null;
            }
            if (recipeType.equalsIgnoreCase("shapeless") && keyMaterial == Material.AIR) {
                continue; // ignore "AIR" key in shapeless recipes
            }
            key.put(keyChar.charAt(0), keyMaterial);
        }

        // Parse recipe result
        String resultType = recipeData.getString("result.type");
        Material resultMaterial = Material.matchMaterial(resultType);
        if (resultMaterial == null) {
            plugin.getLogger().warning("Invalid recipe result material for recipe " + recipeData.getName() + ": unknown material '" + resultType + "'");
            return null;
        }
        ItemStack result = new ItemStack(resultMaterial, recipeData.getInt("result.amount", 1));

        // Parse recipe permission
        String permission = recipeData.getString("permission", "");


     // Create and return recipe object
        NamespacedKey recipeKey = new NamespacedKey(plugin, recipeData.getName());
        if (recipeType.equalsIgnoreCase("shaped")) {
            ShapedRecipe recipe = new ShapedRecipe(recipeKey, result);
            recipe.shape(shape.toArray(new String[0]));
            for (Character keyChar : key.keySet()) {
                recipe.setIngredient(keyChar, key.get(keyChar));
            }
            if (!permission.isEmpty()) {
                recipe.setGroup(permission);
            }
            return recipe;
        } else if (recipeType.equalsIgnoreCase("shapeless")) {
            ShapelessRecipe recipe = new ShapelessRecipe(recipeKey, result);
            for (Material keyMaterial : key.values()) {
                if (keyMaterial != Material.AIR) {
                    recipe.addIngredient(keyMaterial);
                }
            }
            if (!permission.isEmpty()) {
                recipe.setGroup(permission);
            }
            return recipe;
        } else {
            plugin.getLogger().warning("Invalid recipe type for recipe " + recipeData.getName() + ": " + recipeType);
            return null;
        }
    }
    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            Recipe recipe = event.getRecipe();
            if (recipe instanceof ShapedRecipe || recipe instanceof ShapelessRecipe) {
                String permission = null;
                if (recipe instanceof ShapedRecipe) {
                    ShapedRecipe shapedRecipe = (ShapedRecipe) recipe;
                    permission = shapedRecipe.getGroup();
                } else if (recipe instanceof ShapelessRecipe) {
                    ShapelessRecipe shapelessRecipe = (ShapelessRecipe) recipe;
                    permission = shapelessRecipe.getGroup();
                }
                if (permission != null && !permission.isEmpty() && !player.hasPermission("recipe." + permission)) {
                    event.setCancelled(true);
                    String itemName = recipe.getResult().getType().toString().toLowerCase().replace("_", " ");
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + permission + " is required to craft " + itemName + ". Please contact the server administrator(s) if you believe that this is in error.");
                }
            }
        }
    }
}