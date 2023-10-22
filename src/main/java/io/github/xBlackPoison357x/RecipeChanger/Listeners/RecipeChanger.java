package io.github.xBlackPoison357x.RecipeChanger.Listeners;

import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

import java.io.*;
import java.util.*;


public class RecipeChanger implements Listener {

    private final UltimatePlugin plugin;
    private final Map<String, Recipe> customRecipes = new HashMap<>();

    public RecipeChanger(UltimatePlugin instance) {
        this.plugin = instance;
    }

    public void loadRecipes() {
        File configFile = new File(plugin.getDataFolder(), "RecipeChanger.yml");

        if (!configFile.exists()) {
            sendConsoleMessage(ChatColor.RED, "RecipeChanger.yml file not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(configFile)))) {
            YamlConfiguration config = new YamlConfiguration();
            config.load(reader);
            processRecipesInConfig(config);
        } catch (Exception ex) {
            sendConsoleMessage(ChatColor.RED, "Error loading recipes from configuration file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private void sendConsoleMessage(ChatColor color, String message) {
        Bukkit.getConsoleSender().sendMessage(color + "[UltimatePlugin] " + message);
    }

    private void processRecipesInConfig(YamlConfiguration config) {
        Set<String> existingRecipeKeys = new HashSet<>(customRecipes.keySet());

        for (String recipeKey : config.getKeys(false)) {
            ConfigurationSection recipeData = config.getConfigurationSection(recipeKey);
            Recipe recipe = parseRecipeData(recipeData);

            if (recipe != null) {
                handleRecipeUpdates(recipeKey, recipe, recipeData.getString("permission", ""));
            } else {
                sendConsoleMessage(ChatColor.RED, "Recipe data could not be parsed for: " + recipeKey);
            }

            existingRecipeKeys.remove(recipeKey);
        }

        removeAbsentRecipes(existingRecipeKeys);
    }
    
    private void handleRecipeUpdates(String recipeKey, Recipe recipe, String permission) {
        Recipe existingRecipe = customRecipes.get(recipeKey);

        if (existingRecipe != null) {
            Recipe updatedRecipe = updateRecipe(existingRecipe, recipe, permission);

            if (updatedRecipe != null) {
                Bukkit.addRecipe(updatedRecipe);
            }
        } else {
            customRecipes.put(recipeKey, recipe);
            Bukkit.addRecipe(recipe);
            sendConsoleMessage(ChatColor.GREEN, "Added recipe: " + recipeKey);
        }
    }

    private void removeAbsentRecipes(Set<String> absentRecipeKeys) {
        for (String removedRecipeKey : absentRecipeKeys) {
            Recipe removedRecipe = customRecipes.remove(removedRecipeKey);

            if (removedRecipe instanceof Keyed) {
                Bukkit.removeRecipe(((Keyed) removedRecipe).getKey());
                sendConsoleMessage(ChatColor.GREEN, "Removed recipe: " + removedRecipeKey);
            }
        }
    }
    

    public Recipe updateRecipe(Recipe existingRecipe, Recipe newRecipe, String permission) {
        if (existingRecipe instanceof ShapedRecipe && newRecipe instanceof ShapedRecipe) {
            ShapedRecipe newShapedRecipe = (ShapedRecipe) newRecipe;

            // Create a new ShapedRecipe instance with the new result
            ShapedRecipe updatedShapedRecipe = new ShapedRecipe(newShapedRecipe.getKey(), newShapedRecipe.getResult());

            updatedShapedRecipe.shape(newShapedRecipe.getShape());
            for (Map.Entry<Character, ItemStack> entry : newShapedRecipe.getIngredientMap().entrySet()) {
                char key = entry.getKey();
                ItemStack ingredient = entry.getValue();
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
                    // Prefix is used to differentiate from shaped recipe keys and maintain unique keys
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
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        Recipe recipe = event.getRecipe();

        if (recipe instanceof Keyed && ((Keyed) recipe).getKey().getNamespace().equals("ultimateplugin")) {
            String permission = getRecipePermission(recipe);

            if (permission != null && !permission.isEmpty() && !playerHasPermission(player, permission)) {
                cancelCrafting(event, recipe, player, permission);
            }
        }
    }

        private String getRecipePermission(Recipe recipe) {
            if (recipe instanceof ShapedRecipe) {
                return ((ShapedRecipe) recipe).getGroup();
            } else if (recipe instanceof ShapelessRecipe) {
                return ((ShapelessRecipe) recipe).getGroup();
            }
            return null;
        }

        private boolean playerHasPermission(Player player, String permission) {
            return player.hasPermission(permission) || player.isOp();
        }

        private void cancelCrafting(CraftItemEvent event, Recipe recipe, Player player, String permission) {
            event.setCancelled(true);
            String itemName = recipe.getResult().getType().toString().toLowerCase().replace("_", " ");
            player.closeInventory();
            player.sendMessage(ChatColor.RED + permission + " is required to craft " + itemName + ". Please contact the server administrator(s) if you believe that this is in error.");
    }
        public boolean addRecipe(String recipeKey, Recipe recipe, String permission) {
            // Check if the recipe key already exists
            if (customRecipes.containsKey(recipeKey)) {
                return false; // Recipe key already in use
            }

            // Step 1: Save the recipe to RecipeChanger.yml
            File configFile = new File(plugin.getDataFolder(), "RecipeChanger.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

            // Serialize and save the recipe to the config file
            ConfigurationSection recipeSection = config.createSection(recipeKey);
            saveRecipeToConfig(recipe, permission, recipeSection);

            try {
                config.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            // Step 2: Add the recipe to customRecipes map
            customRecipes.put(recipeKey, recipe);

            // Step 3: Add the recipe to the server using Bukkit API
            Bukkit.addRecipe(recipe);

            sendConsoleMessage(ChatColor.GREEN, "Added recipe: " + recipeKey);
            return true;
        }

        // Helper method to save a recipe to a configuration section
        private void saveRecipeToConfig(Recipe recipe, String permission, ConfigurationSection recipeSection) {
            if (recipe instanceof ShapedRecipe) {
                ShapedRecipe shapedRecipe = (ShapedRecipe) recipe;

                // Save the recipe type as "shaped"
                recipeSection.set("recipe_type", "shaped");

                // Save the shape
                recipeSection.set("shape", shapedRecipe.getShape());

                // Save the ingredients
                ConfigurationSection ingredientsSection = recipeSection.createSection("ingredients");
                Map<Character, ItemStack> ingredientMap = shapedRecipe.getIngredientMap();
                for (Character key : ingredientMap.keySet()) {
                    Material ingredientMaterial = ingredientMap.get(key).getType();
                    ingredientsSection.set(key.toString(), ingredientMaterial.toString());
                }
            } else if (recipe instanceof ShapelessRecipe) {
                ShapelessRecipe shapelessRecipe = (ShapelessRecipe) recipe;

                // Save the recipe type as "shapeless"
                recipeSection.set("recipe_type", "shapeless");

                // Save the ingredients as a list
                recipeSection.set("ingredients", shapelessRecipe.getIngredientList().stream()
                        .map(itemStack -> itemStack.getType().toString())
                        .toArray(String[]::new));
            }

            // Save the result
            ConfigurationSection resultSection = recipeSection.createSection("result");
            resultSection.set("type", recipe.getResult().getType().toString());
            resultSection.set("amount", recipe.getResult().getAmount());

            // Save the permission
            recipeSection.set("permission", permission);
        }




        public boolean deleteRecipe(String recipeKey) {
            // Step 1: Delete from RecipeChanger.yml
            File configFile = new File(plugin.getDataFolder(), "RecipeChanger.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            if (!config.contains(recipeKey)) {
                return false;  // The recipe doesn't exist.
            }
            
            config.set(recipeKey, null);  // Remove the recipe from the config.

            try {
                config.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            // Step 2: Remove from customRecipes map
            Recipe recipe = customRecipes.remove(recipeKey);

            // Step 3: Remove from server using Bukkit API
            if (recipe instanceof Keyed) {
                Bukkit.removeRecipe(((Keyed) recipe).getKey());
            }
            
            sendConsoleMessage(ChatColor.GREEN, "Deleted recipe: " + recipeKey);
            
            return true;
        }


}