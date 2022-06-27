package io.github.xBlackPoison357x.RecipeChanger.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Crafting implements Listener {
    private UltimatePlugin plugin;
    private static Crafting singleton;

    public Crafting(UltimatePlugin instance) {
        this.plugin = instance;
        singleton = this;
    }

    public static Crafting getInstance() {
        return singleton;
    }

    public void SetupCrafting() {
        if (this.plugin.getRecipeChangerConfig().getBoolean("Horse Armor.Diamond")) {
          ShapedRecipe diamondHorse = new ShapedRecipe(new NamespacedKey(this.plugin, "DIAMOND_HORSE_ARMOR"), (new ItemStack (Material.DIAMOND_HORSE_ARMOR)));

            diamondHorse.shape(new String[]{"NND", "DWD", "DDD"});
            diamondHorse.setIngredient('D', Material.DIAMOND);
            diamondHorse.setIngredient('W', Material.WHITE_WOOL);
            Bukkit.addRecipe(diamondHorse);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Horse Armor.Iron")) {
            ShapedRecipe ironHorse = new ShapedRecipe(new NamespacedKey(this.plugin, "IRON_HORSE_ARMOR"), (new ItemStack(Material.IRON_HORSE_ARMOR)));
            ironHorse.shape(new String[]{"NNI", "IWI", "III"});
            ironHorse.setIngredient('I', Material.IRON_INGOT);
            ironHorse.setIngredient('W', Material.WHITE_WOOL);
            Bukkit.addRecipe(ironHorse);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Horse Armor.Gold")) {
            ShapedRecipe goldHorse = new ShapedRecipe(new NamespacedKey(this.plugin, "GOLD_HORSE_ARMOR"), (new ItemStack(Material.GOLDEN_HORSE_ARMOR)));
            goldHorse.shape(new String[]{"NNG", "GWG", "GGG"});
            goldHorse.setIngredient('G', Material.GOLD_INGOT);
            goldHorse.setIngredient('W', Material.WHITE_WOOL);
            Bukkit.addRecipe(goldHorse);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Name Tag")) {
            ShapedRecipe nameTag = new ShapedRecipe(new NamespacedKey(this.plugin, "NAME_TAG"), (new ItemStack(Material.NAME_TAG)));
            nameTag.shape(new String[]{"NSN", "PPP", "ICI"});
            nameTag.setIngredient('S', Material.STRING);
            nameTag.setIngredient('P', Material.PAPER);
            nameTag.setIngredient('I', Material.IRON_INGOT);
            nameTag.setIngredient('C', Material.INK_SAC);
            Bukkit.addRecipe(nameTag);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Saddle")) {
            ShapedRecipe saddle = new ShapedRecipe(new NamespacedKey(this.plugin, "SADDLE"), (new ItemStack(Material.SADDLE)));
            saddle.shape(new String[]{"LLL", "LLL", "ISI"});
            saddle.setIngredient('L', Material.LEATHER);
            saddle.setIngredient('I', Material.IRON_INGOT);
            saddle.setIngredient('S', Material.STRING);
            Bukkit.addRecipe(saddle);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Grass Block")) {
            ShapedRecipe grassblock = new ShapedRecipe(new NamespacedKey(this.plugin, "GRASS_BLOCK"), (new ItemStack(Material.GRASS_BLOCK)));
            grassblock.shape(new String[]{"NNN", "NSN", "NDN"});
            grassblock.setIngredient('S', Material.WHEAT_SEEDS);
            grassblock.setIngredient('D', Material.DIRT);
            Bukkit.addRecipe(grassblock);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Cobweb")) {
            ShapedRecipe Web = new ShapedRecipe(new NamespacedKey(this.plugin, "COBWEB"), (new ItemStack(Material.COBWEB, 3)));
            Web.shape(new String[]{"SSS", "SSS", "SSS"});
            Web.setIngredient('S', Material.STRING);
            Bukkit.addRecipe(Web);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Obsidian")) {
            ShapedRecipe Obsidian = new ShapedRecipe(new NamespacedKey(this.plugin, "OBSIDIAN"), (new ItemStack(Material.OBSIDIAN)));
            Obsidian.shape(new String[]{"NNN", "NLN", "NWN"});
            Obsidian.setIngredient('W', Material.WATER_BUCKET);
            Obsidian.setIngredient('L', Material.LAVA_BUCKET);
            Bukkit.addRecipe(Obsidian);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Grass")) {
            ShapedRecipe TallGrass = new ShapedRecipe(new NamespacedKey(this.plugin, "GRASS"), (new ItemStack(Material.GRASS)));
            TallGrass.shape(new String[]{"NNN", "NSN", "NGN"});
            TallGrass.setIngredient('G', Material.GRASS);
            TallGrass.setIngredient('S', Material.WHEAT_SEEDS);
            Bukkit.addRecipe(TallGrass);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Ice")) {
            ShapedRecipe Ice = new ShapedRecipe(new NamespacedKey(this.plugin, "ICE"), (new ItemStack(Material.ICE)));
            Ice.shape(new String[]{"SSS", "SWS", "SSS"});
            Ice.setIngredient('W', Material.WATER_BUCKET);
            Ice.setIngredient('S', Material.SNOWBALL);
            Bukkit.addRecipe(Ice);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Fire")) {
            ShapedRecipe Fire = new ShapedRecipe(new NamespacedKey(this.plugin, "FIRE"), (new ItemStack(Material.FIRE, 5)));
            Fire.shape(new String[]{"NNN", "NFN", "NRN"});
            Fire.setIngredient('F', Material.FLINT_AND_STEEL);
            Fire.setIngredient('R', Material.NETHERRACK);
            Bukkit.addRecipe(Fire);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Chain Armor.Helmet")) {
            ShapedRecipe Chelmet = new ShapedRecipe(new NamespacedKey(this.plugin, "CHAINMAIL_HELMET"), (new ItemStack(Material.CHAINMAIL_HELMET)));
            Chelmet.shape(new String[]{"FFF", "FNF", "NNN"});
            Chelmet.setIngredient('F', Material.IRON_BARS);
            Bukkit.addRecipe(Chelmet);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Chain Armor.ChestPiece")) {
            ShapedRecipe Cchestpiece = new ShapedRecipe(new NamespacedKey(this.plugin, "CHAINMAIL_CHESTPLATE"), (new ItemStack(Material.CHAINMAIL_CHESTPLATE)));
            Cchestpiece.shape(new String[]{"FNF", "FFF", "FFF"});
            Cchestpiece.setIngredient('F', Material.IRON_BARS);
            Bukkit.addRecipe(Cchestpiece);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Chain Armor.Leggings")) {
            ShapedRecipe Cleggings = new ShapedRecipe(new NamespacedKey(this.plugin, "CHAINMAIL_LEGGINGS"), (new ItemStack(Material.CHAINMAIL_LEGGINGS)));
            Cleggings.shape(new String[]{"FFF", "FNF", "FNF"});
            Cleggings.setIngredient('F', Material.IRON_BARS);
            Bukkit.addRecipe(Cleggings);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Chain Armor.Boots")) {
            ShapedRecipe Cboots = new ShapedRecipe(new NamespacedKey(this.plugin, "CHAINMAIL_BOOTS"), (new ItemStack(Material.CHAINMAIL_BOOTS)));
            Cboots.shape(new String[]{"NNN", "FNF", "FNF"});
            Cboots.setIngredient('F', Material.IRON_BARS);
            Bukkit.addRecipe(Cboots);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Bottle o Enchanting")) {
            ShapedRecipe expbottle = new ShapedRecipe(new NamespacedKey(this.plugin, "EXPERIENCE_BOTTLE"), (new ItemStack(Material.EXPERIENCE_BOTTLE)));
            expbottle.shape(new String[]{"GLG", "LEL", "LLL"});
            expbottle.setIngredient('G', Material.GOLD_INGOT);
            expbottle.setIngredient('L', Material.GLASS);
            expbottle.setIngredient('E', Material.EMERALD);
            Bukkit.addRecipe(expbottle);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Sponge")) {
            ShapedRecipe sponge = new ShapedRecipe(new NamespacedKey(this.plugin, "SPONGE"), (new ItemStack(Material.SPONGE)));
            sponge.shape(new String[]{"SCS", "CSC", "SCS"});
            sponge.setIngredient('S', Material.SAND);
            sponge.setIngredient('C', Material.CLAY);
            Bukkit.addRecipe(sponge);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Bedrock")) {
            ShapedRecipe bedrock = new ShapedRecipe(new NamespacedKey(this.plugin, "BEDROCK"), (new ItemStack(Material.BEDROCK)));
            bedrock.shape(new String[]{"COC", "OCO", "COC"});
            bedrock.setIngredient('O', Material.OBSIDIAN);
            bedrock.setIngredient('C', Material.COBBLESTONE);
            Bukkit.addRecipe(bedrock);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Ore.Redstone")) {
            ShapedRecipe redstone = new ShapedRecipe(new NamespacedKey(this.plugin, "REDSTONE_ORE"), (new ItemStack(Material.REDSTONE_ORE)));
            redstone.shape(new String[]{"RRR", "RSR", "RRR"});
            redstone.setIngredient('S', Material.STONE);
            redstone.setIngredient('R', Material.REDSTONE);
            Bukkit.addRecipe(redstone);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Dragon Egg")) {
            ShapedRecipe dragonegg = new ShapedRecipe(new NamespacedKey(this.plugin, "DRAGON_EGG"), (new ItemStack(Material.DRAGON_EGG)));
            dragonegg.shape(new String[]{"NNN", "NON", "OEO"});
            dragonegg.setIngredient('O', Material.OBSIDIAN);
            dragonegg.setIngredient('E', Material.EGG);
            Bukkit.addRecipe(dragonegg);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Gunpowder")) {
            ShapedRecipe gunpowder = new ShapedRecipe(new NamespacedKey(this.plugin, "GUNPOWDER"), (new ItemStack(Material.GUNPOWDER)));
            gunpowder.shape(new String[]{"NNN", "NCN", "NRN"});
            gunpowder.setIngredient('C', Material.COAL_BLOCK);
            gunpowder.setIngredient('R', Material.REDSTONE_BLOCK);
            Bukkit.addRecipe(gunpowder);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Monster Spawner")) {
            ShapedRecipe spawner = new ShapedRecipe(new NamespacedKey(this.plugin, "SPAWNER"), (new ItemStack(Material.SPAWNER)));
            spawner.shape(new String[]{"OOO", "OIO", "OOO"});
            spawner.setIngredient('O', Material.OBSIDIAN);
            spawner.setIngredient('I', Material.IRON_INGOT);
            Bukkit.addRecipe(spawner);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Command Block")) {
            ShapedRecipe commandblock = new ShapedRecipe(new NamespacedKey(this.plugin, "COMMAND_BLOCK"), (new ItemStack(Material.COMMAND_BLOCK)));
            commandblock.shape(new String[]{"CWC", "WIW", "CWC"});
            commandblock.setIngredient('C', Material.CRAFTING_TABLE);
            commandblock.setIngredient('I', Material.IRON_INGOT);
            commandblock.setIngredient('W', Material.OAK_PLANKS);
            Bukkit.addRecipe(commandblock);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Podzol")) {
            ShapedRecipe pod = new ShapedRecipe(new NamespacedKey(this.plugin, "PODZOL"), (new ItemStack(Material.PODZOL)));
            pod.shape(new String[]{"NNN", "NNN", "DDD"});
            pod.setIngredient('D', Material.DIRT);
            Bukkit.addRecipe(pod);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Double Tallgrass")) {
            ShapedRecipe DTGrass = new ShapedRecipe(new NamespacedKey(this.plugin, "TALL_GRASS"), (new ItemStack(Material.TALL_GRASS)));
            DTGrass.shape(new String[]{"NSN", "NSN", "NDN"});
            DTGrass.setIngredient('S', Material.WHEAT_SEEDS);
            DTGrass.setIngredient('D', Material.GRASS);
            Bukkit.addRecipe(DTGrass);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Packed Ice")) {
            ShapedRecipe packedice = new ShapedRecipe(new NamespacedKey(this.plugin, "PACKED_ICE"), (new ItemStack(Material.PACKED_ICE)));
            packedice.shape(new String[]{"NNN", "NII", "NII"});
            packedice.setIngredient('I', Material.ICE);
            Bukkit.addRecipe(packedice);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Spawn Eggs.Pig")) {
            ShapedRecipe pigegg = new ShapedRecipe(new NamespacedKey(this.plugin, "PIG_SPAWN_EGG"), (new ItemStack(Material.PIG_SPAWN_EGG)));
            pigegg.shape(new String[]{"PPP", "PEP", "PPP"});
            pigegg.setIngredient('P', Material.PORKCHOP);
            pigegg.setIngredient('E', Material.EGG);
            Bukkit.addRecipe(pigegg);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Spawn Eggs.Cow")) {
            ShapedRecipe cowegg = new ShapedRecipe(new NamespacedKey(this.plugin, "COW_SPAWN_EGG"), (new ItemStack(Material.COW_SPAWN_EGG)));
            cowegg.shape(new String[]{"PPP", "PEP", "PPP"});
            cowegg.setIngredient('P', Material.BEEF);
            cowegg.setIngredient('E', Material.EGG);
            Bukkit.addRecipe(cowegg);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Spawn Eggs.Chicken")) {
            ShapedRecipe chickenegg = new ShapedRecipe(new NamespacedKey(this.plugin, "CHICKEN_SPAWN_EGG"), (new ItemStack(Material.CHICKEN_SPAWN_EGG)));
            chickenegg.shape(new String[]{"PPP", "PEP", "PPP"});
            chickenegg.setIngredient('P', Material.CHICKEN);
            chickenegg.setIngredient('E', Material.EGG);
            Bukkit.addRecipe(chickenegg);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Spawn Eggs.Bat")) {
            ShapedRecipe bategg = new ShapedRecipe(new NamespacedKey(this.plugin, "BAT_SPAWN_EGG"), (new ItemStack(Material.BAT_SPAWN_EGG)));
            bategg.shape(new String[]{"PPP", "PEP", "PPP"});
            bategg.setIngredient('P', Material.FEATHER);
            bategg.setIngredient('E', Material.EGG);
            Bukkit.addRecipe(bategg);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Spawn Eggs.Horse")) {
            ShapedRecipe horseegg = new ShapedRecipe(new NamespacedKey(this.plugin, "HORSE_SPAWN_EGG"), (new ItemStack(Material.HORSE_SPAWN_EGG)));
            horseegg.shape(new String[]{"PPP", "PEP", "PPP"});
            horseegg.setIngredient('P', Material.LEATHER);
            horseegg.setIngredient('E', Material.EGG);
            Bukkit.addRecipe(horseegg);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Spawn Eggs.Ocelot")) {
            ShapedRecipe ocelotegg = new ShapedRecipe(new NamespacedKey(this.plugin, "OCELOT_SPAWN_EGG"), (new ItemStack(Material.OCELOT_SPAWN_EGG)));
            ocelotegg.shape(new String[]{"PPP", "PEP", "PPP"});
            ocelotegg.setIngredient('P', Material.TROPICAL_FISH);
            ocelotegg.setIngredient('E', Material.EGG);
            Bukkit.addRecipe(ocelotegg);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Spawn Eggs.Wolf")) {
            ShapedRecipe wolfegg = new ShapedRecipe(new NamespacedKey(this.plugin, "WOLF_SPAWN_EGG"), (new ItemStack(Material.WOLF_SPAWN_EGG)));
            wolfegg.shape(new String[]{"PPP", "PEP", "PPP"});
            wolfegg.setIngredient('P', Material.BONE);
            wolfegg.setIngredient('E', Material.EGG);
            Bukkit.addRecipe(wolfegg);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Spawn Eggs.Squid")) {
            ShapedRecipe squidegg = new ShapedRecipe(new NamespacedKey(this.plugin, "SQUID_SPAWN_EGG"), (new ItemStack(Material.SQUID_SPAWN_EGG)));
            squidegg.shape(new String[]{"PPP", "PEP", "PPP"});
            squidegg.setIngredient('P', Material.INK_SAC);
            squidegg.setIngredient('E', Material.EGG);
            Bukkit.addRecipe(squidegg);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Spawn Eggs.Sheep")) {
            ShapedRecipe sheepegg = new ShapedRecipe(new NamespacedKey(this.plugin, "SHEEP_SPAWN_EGG"), (new ItemStack(Material.SHEEP_SPAWN_EGG)));
            sheepegg.shape(new String[]{"PPP", "PEP", "PPP"});
            sheepegg.setIngredient('P', Material.STRING);
            sheepegg.setIngredient('E', Material.EGG);
            Bukkit.addRecipe(sheepegg);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Spawn Eggs.Mooshroom")) {
            ShapedRecipe mooshroomegg = new ShapedRecipe(new NamespacedKey(this.plugin, "MOOSHROOM_SPAWN_EGG"), (new ItemStack(Material.MOOSHROOM_SPAWN_EGG)));
            mooshroomegg.shape(new String[]{"PPP", "PEP", "PPP"});
            mooshroomegg.setIngredient('P', Material.RED_MUSHROOM);
            mooshroomegg.setIngredient('E', Material.EGG);
            Bukkit.addRecipe(mooshroomegg);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Leather")) {
            ShapelessRecipe leather = new ShapelessRecipe(new NamespacedKey(this.plugin, "LEATHER"), (new ItemStack(Material.LEATHER)));
            leather.addIngredient(Material.ROTTEN_FLESH);
            leather.addIngredient(Material.ROTTEN_FLESH);
            leather.addIngredient(Material.ROTTEN_FLESH);
            leather.addIngredient(Material.ROTTEN_FLESH);
            Bukkit.addRecipe(leather);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Cocoa Beans")) {
            ShapedRecipe bean = new ShapedRecipe(new NamespacedKey(this.plugin, "COCOA_BEANS"), (new ItemStack(Material.COCOA_BEANS)));
            bean.shape(new String[]{"NNN", "INN", "NON"});
            bean.setIngredient('I', Material.INK_SAC);
            bean.setIngredient('O', Material.ORANGE_DYE);
            Bukkit.addRecipe(bean);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Music Discs.stal")) {
            ShapedRecipe blackdisc = new ShapedRecipe(new NamespacedKey(this.plugin, "MUSIC_DISC_STAL"), (new ItemStack(Material.MUSIC_DISC_STAL)));
            blackdisc.shape(new String[]{"BAB", "ACA", "BAB"});
            blackdisc.setIngredient('A', Material.OBSIDIAN);
            blackdisc.setIngredient('B', Material.GOLD_BLOCK);
            blackdisc.setIngredient('C', Material.INK_SAC);
            Bukkit.addRecipe(blackdisc);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Music Discs.11")) {
            ShapedRecipe brokendisc = new ShapedRecipe(new NamespacedKey(this.plugin, "MUSIC_DISC_11"), (new ItemStack(Material.MUSIC_DISC_11)));
            brokendisc.shape(new String[]{"BAB", "ACA", "BAB"});
            brokendisc.setIngredient('A', Material.OBSIDIAN);
            brokendisc.setIngredient('B', Material.GOLD_BLOCK);
            brokendisc.setIngredient('C', Material.DIAMOND);
            Bukkit.addRecipe(brokendisc);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Music Discs.far")) {
            ShapedRecipe cyandisc = new ShapedRecipe(new NamespacedKey(this.plugin, "MUSIC_DISC_FAR"), (new ItemStack(Material.MUSIC_DISC_FAR)));
            cyandisc.shape(new String[]{"BAB", "ACA", "BAB"});
            cyandisc.setIngredient('A', Material.OBSIDIAN);
            cyandisc.setIngredient('B', Material.GOLD_BLOCK);
            cyandisc.setIngredient('C', Material.CYAN_DYE);
            Bukkit.addRecipe(cyandisc);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Music Discs.ward")) {
            ShapedRecipe forestdisc = new ShapedRecipe(new NamespacedKey(this.plugin, "MUSIC_DISC_WARD"), (new ItemStack(Material.MUSIC_DISC_WARD)));
            forestdisc.shape(new String[]{"BAB", "ACA", "BAB"});
            forestdisc.setIngredient('A', Material.OBSIDIAN);
            forestdisc.setIngredient('B', Material.GOLD_BLOCK);
            forestdisc.setIngredient('C', Material.GREEN_DYE);
            Bukkit.addRecipe(forestdisc);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Music Discs.13")) {
            ShapedRecipe golddisc = new ShapedRecipe(new NamespacedKey(this.plugin, "MUSIC_DISC_13"), (new ItemStack(Material.MUSIC_DISC_13)));
            golddisc.shape(new String[]{"BAB", "ACA", "BAB"});
            golddisc.setIngredient('A', Material.OBSIDIAN);
            golddisc.setIngredient('B', Material.GOLD_BLOCK);
            golddisc.setIngredient('C', Material.YELLOW_DYE);
            Bukkit.addRecipe(golddisc);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Music Discs.cat")) {
            ShapedRecipe greendisc = new ShapedRecipe(new NamespacedKey(this.plugin, "MUSIC_DISC_CAT"), (new ItemStack(Material.MUSIC_DISC_CAT)));
            greendisc.shape(new String[]{"BAB", "ACA", "BAB"});
            greendisc.setIngredient('A', Material.OBSIDIAN);
            greendisc.setIngredient('B', Material.GOLD_BLOCK);
            greendisc.setIngredient('C', Material.LIME_DYE);
            Bukkit.addRecipe(greendisc);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Music Discs.blocks")) {
            ShapedRecipe orangedisc = new ShapedRecipe(new NamespacedKey(this.plugin, "MUSIC_DISC_BLOCKS"), (new ItemStack(Material.MUSIC_DISC_BLOCKS)));
            orangedisc.shape(new String[]{"BAB", "ACA", "BAB"});
            orangedisc.setIngredient('A', Material.OBSIDIAN);
            orangedisc.setIngredient('B', Material.GOLD_BLOCK);
            orangedisc.setIngredient('C', Material.ORANGE_DYE);
            Bukkit.addRecipe(orangedisc);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Music Discs.mellohi")) {
            ShapedRecipe purpledisc = new ShapedRecipe(new NamespacedKey(this.plugin, "MUSIC_DISC_MELLOHI"), (new ItemStack(Material.MUSIC_DISC_MELLOHI)));
            purpledisc.shape(new String[]{"BAB", "ACA", "BAB"});
            purpledisc.setIngredient('A', Material.OBSIDIAN);
            purpledisc.setIngredient('B', Material.GOLD_BLOCK);
            purpledisc.setIngredient('C', Material.PURPLE_DYE);
            Bukkit.addRecipe(purpledisc);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Music Discs.chirp")) {
            ShapedRecipe reddisc = new ShapedRecipe(new NamespacedKey(this.plugin, "MUSIC_DISC_CHIRP"), (new ItemStack(Material.MUSIC_DISC_CHIRP)));
            reddisc.shape(new String[]{"BAB", "ACA", "BAB"});
            reddisc.setIngredient('A', Material.OBSIDIAN);
            reddisc.setIngredient('B', Material.GOLD_BLOCK);
            reddisc.setIngredient('C', Material.RED_DYE);
            Bukkit.addRecipe(reddisc);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Music Discs.strad")) {
            ShapedRecipe whitedisc = new ShapedRecipe(new NamespacedKey(this.plugin, "MUSIC_DISC_STARD"), (new ItemStack(Material.MUSIC_DISC_STRAD)));
            whitedisc.shape(new String[]{"BAB", "ACA", "BAB"});
            whitedisc.setIngredient('A', Material.OBSIDIAN);
            whitedisc.setIngredient('B', Material.GOLD_BLOCK);
            whitedisc.setIngredient('C', Material.BONE_MEAL);
            Bukkit.addRecipe(whitedisc);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Music Discs.mall")) {
            ShapedRecipe bluedisc = new ShapedRecipe(new NamespacedKey(this.plugin, "MUSIC_DISC_MALL"), (new ItemStack(Material.MUSIC_DISC_MALL)));
            bluedisc.shape(new String[]{"BAB", "ACA", "BAB"});
            bluedisc.setIngredient('A', Material.OBSIDIAN);
            bluedisc.setIngredient('B', Material.GOLD_BLOCK);
            bluedisc.setIngredient('C', Material.PURPLE_DYE);
            Bukkit.addRecipe(bluedisc);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Shulker Shell")) {
            ShapedRecipe shulker = new ShapedRecipe(new NamespacedKey(this.plugin, "SHULKER_SHELL"), (new ItemStack(Material.SHULKER_SHELL)));
            shulker.shape(new String[]{"NNN", "AAA", "ANA"});
            shulker.setIngredient('A', Material.OBSIDIAN);
            Bukkit.addRecipe(shulker);
        }
        if (this.plugin.getRecipeChangerConfig().getBoolean("Misc.Reinforced Deepslate")) {
            ShapedRecipe rdeepslate = new ShapedRecipe(new NamespacedKey(this.plugin, "REINFORCED_DEEPSLATE"), (new ItemStack(Material.REINFORCED_DEEPSLATE)));
            rdeepslate.shape(new String[]{"AAA", "ARA", "AAA"});
            rdeepslate.setIngredient('A', Material.OBSIDIAN);
            rdeepslate.setIngredient('R', Material.DEEPSLATE);
            Bukkit.addRecipe(rdeepslate);        
        }
    }
}