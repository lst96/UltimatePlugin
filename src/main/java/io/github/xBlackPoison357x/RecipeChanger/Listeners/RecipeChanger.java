package io.github.xBlackPoison357x.RecipeChanger.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Keyed;
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
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;


public class RecipeChanger implements Listener {
    public UltimatePlugin plugin;
    private Map<String, Recipe> customRecipes = new HashMap<>();


    public RecipeChanger(UltimatePlugin instance) {
        plugin = instance;
    }

    public void loadRecipes() {
        // Load recipe data from configuration file
        File configFile = new File(plugin.getDataFolder(), "RecipeChanger.yml");

        if (!configFile.exists()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[UltimatePlugin] RecipeChanger.yml file not found.");
            return;
        }

        try (InputStream inputStream = new FileInputStream(configFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            YamlConfiguration config = new YamlConfiguration();
            config.load(reader);

            // Create a copy of the existing recipe keys in the map
            Set<String> existingRecipeKeys = new HashSet<>(customRecipes.keySet());

            // Iterate over each recipe in the configuration file
            for (String recipeKey : config.getKeys(false)) {
                ConfigurationSection recipeData = config.getConfigurationSection(recipeKey);

                // Parse recipe data
                Recipe recipe = parseRecipeData(recipeData);
                if (recipe != null) {
                    if (customRecipes.containsKey(recipeKey)) {
                        // Modify the existing recipe
                        Recipe updatedRecipe = updateRecipe(customRecipes.get(recipeKey), recipe, recipeData.getString("permission", ""));

                        if (updatedRecipe != null) {
                            Bukkit.addRecipe(updatedRecipe);
                        }
                    } else {
                        // Add the new custom recipe to the game registry and the map
                        customRecipes.put(recipeKey, recipe);
                        Bukkit.addRecipe(recipe);
                        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[UltimatePlugin] Added recipe: " + recipeKey);
                    }
                } else {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[UltimatePlugin] Recipe data could not be parsed for: " + recipeKey);
                }

                // Remove the processed recipe key from the existing keys set
                existingRecipeKeys.remove(recipeKey);
            }

            // Remove recipes from the map that were not found in the configuration file
            for (String removedRecipeKey : existingRecipeKeys) {
                Recipe removedRecipe = customRecipes.remove(removedRecipeKey);
                if (removedRecipe != null) {
                    Bukkit.removeRecipe(((Keyed) removedRecipe).getKey());
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[UltimatePlugin] Removed recipe: " + removedRecipeKey);
                }
            }

        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[UltimatePlugin] Error loading recipes from configuration file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }



    public Recipe updateRecipe(Recipe existingRecipe, Recipe newRecipe, String permission) {
        if (existingRecipe instanceof ShapedRecipe && newRecipe instanceof ShapedRecipe) {
            ShapedRecipe newShapedRecipe = (ShapedRecipe) newRecipe;

            // Create a new ShapedRecipe instance with the new result
            ShapedRecipe updatedShapedRecipe = new ShapedRecipe(newShapedRecipe.getKey(), newShapedRecipe.getResult());

            updatedShapedRecipe.shape(newShapedRecipe.getShape());
            for (char key : newShapedRecipe.getIngredientMap().keySet()) {
                ItemStack ingredient = newShapedRecipe.getIngredientMap().get(key);
                if (ingredient != null) {
                    updatedShapedRecipe.setIngredient(key, ingredient.getType());
                }
            }
            updatedShapedRecipe.setGroup(permission);

            return updatedShapedRecipe;
        } else if (existingRecipe instanceof ShapelessRecipe && newRecipe instanceof ShapelessRecipe) {
            ShapelessRecipe newShapelessRecipe = (ShapelessRecipe) newRecipe;

            // Create a new ShapelessRecipe instance with the new result
            ShapelessRecipe updatedShapelessRecipe = new ShapelessRecipe(newShapelessRecipe.getKey(), newShapelessRecipe.getResult());

            for (ItemStack ingredient : newShapelessRecipe.getIngredientList()) {
                updatedShapelessRecipe.addIngredient(ingredient.getAmount(), ingredient.getType());
            }
            updatedShapelessRecipe.setGroup(permission);

            return updatedShapelessRecipe;
        }

        return null;
    }

         
    public static void removeRecipesByPrefix(String prefix) {
        List<Recipe> recipes = new ArrayList<>();
        Bukkit.recipeIterator().forEachRemaining(recipe -> {
            if (recipe instanceof Keyed) {
                NamespacedKey key = ((Keyed) recipe).getKey();
                if (!key.getNamespace().startsWith(prefix)) {
                    recipes.add(recipe);
                }
            } else {
                recipes.add(recipe);
            }
        });

        Bukkit.clearRecipes();
        recipes.forEach(Bukkit::addRecipe);
    }


    Recipe parseRecipeData(ConfigurationSection recipeData) {
        // Check that recipe data contains required fields
        if (!recipeData.contains("recipe_type") || !recipeData.contains("ingredients") || !recipeData.contains("result")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[UltimatePlugin] " + "Invalid recipe data for recipe " + recipeData.getName() + ": required fields missing");
            return null;
        }

        // Parse recipe type
        String recipeType = recipeData.getString("recipe_type");

        // Parse recipe shape
        List<String> shape = recipeData.getStringList("shape");
        if (shape.size() != 3 && recipeType.equalsIgnoreCase("shaped")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[UltimatePlugin] " + "Invalid recipe shape for recipe " + recipeData.getName() + ": shape must have exactly three rows");
            return null;
        }
     // Parse recipe ingredients
        Map<String, Material> ingredients = new HashMap<>();
        if (recipeType.equalsIgnoreCase("shapeless")) {
            List<String> ingredientList = recipeData.getStringList("ingredients");
            int index = 0;
            for (String ingredientMaterialName : ingredientList) {
                Material ingredientMaterial = Material.matchMaterial(ingredientMaterialName);
                if (ingredientMaterial != null) {
                    ingredients.put("-" + index, ingredientMaterial);
                    index++;
                } else {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[UltimatePlugin] " + "Invalid recipe ingredient for recipe " + recipeData.getName() + ": unknown material '" + ingredientMaterialName + "'");
                    return null;
                }
            }
        } else {
            ConfigurationSection ingredientsData = recipeData.getConfigurationSection("ingredients");
            for (String ingredientKey : ingredientsData.getKeys(false)) {
                String ingredientMaterialName = ingredientsData.getString(ingredientKey);
                if (ingredientMaterialName == null) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[UltimatePlugin] " + "Invalid recipe ingredient for recipe " + recipeData.getName() + ": null ingredient material for key '" + ingredientKey + "'");
                    return null;
                }
                Material ingredientMaterial = Material.matchMaterial(ingredientMaterialName);
                if (ingredientMaterial == null) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[UltimatePlugin] " + "Invalid recipe ingredient for recipe " + recipeData.getName() + ": unknown material '" + ingredientMaterialName + "' for key '" + ingredientKey + "'");
                    return null;
                }
                ingredients.put(ingredientKey, ingredientMaterial);
            }
        }

        // Parse recipe result
        String resultType = recipeData.getString("result.type");
        Material resultMaterial = Material.matchMaterial(resultType);
        if (resultMaterial == null) {
        	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[UltimatePlugin] " + "Invalid recipe result material for recipe " + recipeData.getName() + ": unknown material '" + resultType + "'");
            return null;
        }
        ItemStack result = new ItemStack(resultMaterial, recipeData.getInt("result.amount", 1));

        // Parse recipe permission
        String permission = recipeData.getString("permission", "");

        // Create and return recipe object
        NamespacedKey recipeKey = new NamespacedKey(plugin, recipeData.getName());
        Recipe recipe;
        if (recipeType.equalsIgnoreCase("shaped")) {
            ShapedRecipe shapedRecipe = new ShapedRecipe(recipeKey, result);
            shapedRecipe.shape(shape.toArray(new String[0]));
            for (String ingredientKey : ingredients.keySet()) {
                Material ingredientMaterial = ingredients.get(ingredientKey);
                if (ingredientKey.startsWith("-")) {
                    shapedRecipe.setIngredient(ingredientKey.charAt(1), ingredientMaterial);
                } else {
                    shapedRecipe.setIngredient(ingredientKey.charAt(0), ingredientMaterial);
                }
            }
            if (!permission.isEmpty()) {
                shapedRecipe.setGroup(permission);
            }
            recipe = shapedRecipe;

        } else if (recipeType.equalsIgnoreCase("shapeless")) {
            ShapelessRecipe shapelessRecipe = new ShapelessRecipe(recipeKey, result);
            if (recipeData.isList("ingredients")) {
                List<String> ingredientList = recipeData.getStringList("ingredients");
                for (String ingredientMaterialName : ingredientList) {
                    Material ingredientMaterial = Material.matchMaterial(ingredientMaterialName);
                    if (ingredientMaterial != null && ingredientMaterial != Material.AIR) {
                        shapelessRecipe.addIngredient(ingredientMaterial);
                    }
                }
            } else {
                for (Material ingredientMaterial : ingredients.values()) {
                    if (ingredientMaterial != Material.AIR) {
                        shapelessRecipe.addIngredient(ingredientMaterial);
                    }
                }
            }
            if (!permission.isEmpty()) {
                shapelessRecipe.setGroup(permission);
            }
            recipe = shapelessRecipe;
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[UltimatePlugin] " + "Invalid recipe type for recipe " + recipeData.getName() + ": " + recipeType);
            return null;
        }
        return recipe;
    }
    
    public Map<String, ItemStack> getCustomRecipeItems() {
        Map<String, ItemStack> customRecipes = new HashMap<>();

        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();
        while (recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();
            if (!(recipe instanceof ShapedRecipe) && !(recipe instanceof ShapelessRecipe)) {
                continue;
            }
            NamespacedKey key = ((Keyed) recipe).getKey();
            if (key != null && key.getNamespace().equals("ultimateplugin")) {
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

        return customRecipes;
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            Recipe recipe = event.getRecipe();

            // Check if the recipe was added by the UltimatePlugin
            if (recipe instanceof Keyed && ((Keyed) recipe).getKey().getNamespace().equals("ultimateplugin")) {
                String permission = null;
                if (recipe instanceof ShapedRecipe) {
                    ShapedRecipe shapedRecipe = (ShapedRecipe) recipe;
                    permission = shapedRecipe.getGroup();
                } else if (recipe instanceof ShapelessRecipe) {
                    ShapelessRecipe shapelessRecipe = (ShapelessRecipe) recipe;
                    permission = shapelessRecipe.getGroup();
                }
                if (permission != null && !permission.isEmpty() && !(player.hasPermission(permission) || player.isOp())) {
                    event.setCancelled(true);
                    String itemName = recipe.getResult().getType().toString().toLowerCase().replace("_", " ");
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + permission + " is required to craft " + itemName + ". Please contact the server administrator(s) if you believe that this is in error.");
                }
            }
        }
    }

}