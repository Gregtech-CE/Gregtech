package gregtech.loaders.recipe;

import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem.MetaValueItem;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials.Tier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTLog;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.wood.BlockGregLog.LogVariant;
import gregtech.common.crafting.FacadeRecipe;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.loaders.oreprocessing.ToolRecipeHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreIngredient;

import java.util.HashMap;
import java.util.Map;

import static gregtech.api.util.DyeUtil.*;

public class CraftingRecipeLoader {

    public static void init() {
        loadCraftingRecipes();
    }

    private static void loadCraftingRecipes() {
        registerFacadeRecipe(Materials.Aluminium, 5);
        registerFacadeRecipe(Materials.WroughtIron, 3);
        registerFacadeRecipe(Materials.Iron, 2);

        ToolRecipeHandler.registerPowerUnitRecipes();
        ModHandler.addShapedRecipe("small_wooden_pipe", OreDictUnifier.get(OrePrefix.pipeSmall, Materials.Wood, 4), "WWW", "h f", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood));
        ModHandler.addShapedRecipe("medium_wooden_pipe", OreDictUnifier.get(OrePrefix.pipeMedium, Materials.Wood, 2), "WWW", "f h", "WWW", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood));

        ModHandler.addShapelessRecipe("nether_quartz_block_to_nether_quartz", new ItemStack(Items.QUARTZ, 4), Blocks.QUARTZ_BLOCK);
        ModHandler.addShapelessRecipe("clay_block_to_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Clay, 4), 'm', Blocks.CLAY);
        ModHandler.addShapelessRecipe("clay_ball_to_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Clay), 'm', Items.CLAY_BALL);
        ModHandler.addShapelessRecipe("brick_block_to_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Brick, 4), 'm', Blocks.BRICK_BLOCK);
        ModHandler.addShapelessRecipe("brick_to_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Brick), 'm', Items.BRICK);
        ModHandler.addShapelessRecipe("wheat_to_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Wheat, 1), 'm', Items.WHEAT);
        ModHandler.addShapelessRecipe("gravel_to_flint", new ItemStack(Items.FLINT, 1), 'm', Blocks.GRAVEL);
        ModHandler.addShapelessRecipe("bone_to_bone_meal", new ItemStack(Items.DYE, 4, 15), 'm', Items.BONE);
        ModHandler.addShapelessRecipe("blaze_rod_to_powder", new ItemStack(Items.BLAZE_POWDER, 3), 'm', Items.BLAZE_ROD);
        ModHandler.addShapelessRecipe("integrated_circuit", IntCircuitIngredient.getIntegratedCircuit(0), new UnificationEntry(OrePrefix.circuit, Tier.Basic));

        ModHandler.addShapedRecipe("item_filter", MetaItems.ITEM_FILTER.getStackForm(), "XXX", "XYX", "XXX", 'X', new UnificationEntry(OrePrefix.foil, Materials.Zinc), 'Y', new UnificationEntry(OrePrefix.plate, Materials.Steel));
        ModHandler.addShapedRecipe("fluid_filter", MetaItems.FLUID_FILTER.getStackForm(), "XXX", "XYX", "XXX", 'X', new UnificationEntry(OrePrefix.foil, Materials.Zinc), 'Y', new UnificationEntry(OrePrefix.plate, Materials.Lapis));

        ModHandler.addShapedRecipe("ore_dictionary_filter_olivine", MetaItems.ORE_DICTIONARY_FILTER.getStackForm(), "XXX", "XYX", "XXX", 'X', new UnificationEntry(OrePrefix.foil, Materials.Zinc), 'Y', new UnificationEntry(OrePrefix.plate, Materials.Olivine));
        ModHandler.addShapedRecipe("ore_dictionary_filter_emerald", MetaItems.ORE_DICTIONARY_FILTER.getStackForm(), "XXX", "XYX", "XXX", 'X', new UnificationEntry(OrePrefix.foil, Materials.Zinc), 'Y', new UnificationEntry(OrePrefix.plate, Materials.Emerald));

        ModHandler.addShapedRecipe("smart_item_filter_olivine", MetaItems.SMART_FILTER.getStackForm(), "XEX", "XCX", "XEX", 'X', new UnificationEntry(OrePrefix.foil, Materials.Zinc), 'C', new UnificationEntry(OrePrefix.circuit, Tier.Basic), 'E', new UnificationEntry(OrePrefix.plate, Materials.Olivine));
        ModHandler.addShapedRecipe("smart_item_filter_emerald", MetaItems.SMART_FILTER.getStackForm(), "XEX", "XCX", "XEX", 'X', new UnificationEntry(OrePrefix.foil, Materials.Zinc), 'C', new UnificationEntry(OrePrefix.circuit, Tier.Basic), 'E', new UnificationEntry(OrePrefix.plate, Materials.Emerald));

        ModHandler.addShapedRecipe("plank_to_wooden_shape", MetaItems.WOODEN_FORM_EMPTY.getStackForm(), "   ", " X ", "s  ", 'X', new UnificationEntry(OrePrefix.plank, Materials.Wood));
        ModHandler.addShapedRecipe("wooden_shape_brick", MetaItems.WOODEN_FORM_BRICK.getStackForm(), "k ", " X", 'X', MetaItems.WOODEN_FORM_EMPTY.getStackForm());
        ModHandler.addShapedRecipe("compressed_clay", MetaItems.COMPRESSED_CLAY.getStackForm(8), "XXX", "XYX", "XXX", 'Y', MetaItems.WOODEN_FORM_BRICK.getStackForm(), 'X', Items.CLAY_BALL);
        ModHandler.addShapelessRecipe("fireclay_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Fireclay, 2), new UnificationEntry(OrePrefix.dust, Materials.Brick), new UnificationEntry(OrePrefix.dust, Materials.Clay));
        ModHandler.addSmeltingRecipe(MetaItems.COMPRESSED_CLAY.getStackForm(), MetaItems.COKE_OVEN_BRICK.getStackForm());
        ModHandler.addSmeltingRecipe(MetaItems.COMPRESSED_FIRECLAY.getStackForm(), MetaItems.FIRECLAY_BRICK.getStackForm());

        ModHandler.addSmeltingRecipe(new UnificationEntry(OrePrefix.nugget, Materials.Iron), OreDictUnifier.get(OrePrefix.nugget, Materials.WroughtIron));

        for (MetaValueItem batteryItem : ToolRecipeHandler.batteryItems[0]) {
            ModHandler.addShapedEnergyTransferRecipe("scanner_" + batteryItem.unlocalizedName, MetaItems.SCANNER.getStackForm(),
                batteryItem::isItemEqual, true,
                "DGD", "CGC", "SBS",
                'D', new UnificationEntry(OrePrefix.plate, Materials.Diamond),
                'G', new UnificationEntry(OrePrefix.paneGlass),
                'C', new UnificationEntry(OrePrefix.circuit, Tier.Basic),
                'S', new UnificationEntry(OrePrefix.plate, Materials.Steel),
                'B', batteryItem.getStackForm());
        }

        for(Material material : new Material[] {Materials.Lapis, Materials.Lazurite, Materials.Sodalite}) {
            String recipeName = "lapotron_crystal_" + material.toString();
            ModHandler.addShapedEnergyTransferRecipeWithOverride(recipeName, MetaItems.LAPOTRON_CRYSTAL.getStackForm(),
                Ingredient.fromStacks(MetaItems.ENERGY_CRYSTAL.getStackForm()), false, false,
                "XCX", "XEX", "XCX",
                'X', new UnificationEntry(OrePrefix.plate, material),
                'C', new UnificationEntry(OrePrefix.circuit, Tier.Advanced),
                'E', MetaItems.ENERGY_CRYSTAL.getStackForm());
        }

        for (MetaValueItem batteryItem : ToolRecipeHandler.batteryItems[1]) {
            ItemStack batteryStack = batteryItem.getStackForm();
            ModHandler.addShapedEnergyTransferRecipe("rebreather_" + batteryItem.unlocalizedName,
                MetaItems.REBREATHER.getStackForm(),
                Ingredient.fromStacks(batteryStack), true,
                "CEC", "PGP", "BUB",
                'C', new UnificationEntry(OrePrefix.circuit, Tier.Basic),
                'E', MetaTileEntities.ELECTROLYZER[0].getStackForm(),
                'G', new UnificationEntry(OrePrefix.glass, null),
                'P', new UnificationEntry(OrePrefix.pipeSmall, Materials.Steel),
                'B', batteryStack,
                'U', MetaItems.ELECTRIC_PUMP_LV.getStackForm());
        }

        ModHandler.addShapelessRecipe("rubber_wood_planks", new ItemStack(Blocks.PLANKS, 4, EnumType.JUNGLE.getMetadata()), new ItemStack(MetaBlocks.LOG, 1, LogVariant.RUBBER_WOOD.ordinal()));

        ModHandler.addShapedRecipe("paper_ring", OreDictUnifier.get(OrePrefix.ring, Materials.Paper), "k", "X", 'X', new UnificationEntry(OrePrefix.plate, Materials.Paper));
        ModHandler.addShapedRecipe("rubber_ring", OreDictUnifier.get(OrePrefix.ring, Materials.Rubber), "k", "X", 'X', new UnificationEntry(OrePrefix.plate, Materials.Rubber));
        ModHandler.addShapedRecipe("silicone_rubber_ring", OreDictUnifier.get(OrePrefix.ring, Materials.SiliconeRubber), "k", "P", 'P', OreDictUnifier.get(OrePrefix.plate, Materials.SiliconeRubber));
        ModHandler.addShapedRecipe("styrene_rubber_ring", OreDictUnifier.get(OrePrefix.ring, Materials.StyreneButadieneRubber), "k", "P", 'P', OreDictUnifier.get(OrePrefix.plate, Materials.StyreneButadieneRubber));

        ModHandler.addShapedRecipe("rubber_drop_torch", new ItemStack(Blocks.TORCH, 3), "X", "Y", 'X', MetaItems.RUBBER_DROP, 'Y', new UnificationEntry(OrePrefix.stick, Materials.Wood));
        ModHandler.addShapedRecipe("lignite_coal_torch", new ItemStack(Blocks.TORCH, 4), "X", "Y", 'X', new UnificationEntry(OrePrefix.gem, Materials.Lignite), 'Y', new UnificationEntry(OrePrefix.stick, Materials.Wood));

        ModHandler.addShapelessRecipe("iron_magnetic_stick", OreDictUnifier.get(OrePrefix.stick, Materials.IronMagnetic), new UnificationEntry(OrePrefix.stick, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Redstone), new UnificationEntry(OrePrefix.dust, Materials.Redstone), new UnificationEntry(OrePrefix.dust, Materials.Redstone), new UnificationEntry(OrePrefix.dust, Materials.Redstone));

        ModHandler.addShapedRecipe("torch_sulfur", new ItemStack(Blocks.TORCH, 2), "C", "S", 'C', new UnificationEntry(OrePrefix.dust, Materials.Sulfur), 'S', new UnificationEntry(OrePrefix.stick, Materials.Wood));
        ModHandler.addShapedRecipe("torch_phosphor", new ItemStack(Blocks.TORCH, 6), "C", "S", 'C', new UnificationEntry(OrePrefix.dust, Materials.Phosphorus), 'S', new UnificationEntry(OrePrefix.stick, Materials.Wood));

        ModHandler.addShapedRecipe("piston_bronze", new ItemStack(Blocks.PISTON, 1), "WWW", "CBC", "CRC", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood), 'C', OrePrefix.stoneCobble, 'R', new UnificationEntry(OrePrefix.dust, Materials.Redstone), 'B', new UnificationEntry(OrePrefix.ingot, Materials.Bronze));
        ModHandler.addShapedRecipe("piston_aluminium", new ItemStack(Blocks.PISTON, 1), "WWW", "CBC", "CRC", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood), 'C', OrePrefix.stoneCobble, 'R', new UnificationEntry(OrePrefix.dust, Materials.Redstone), 'B', new UnificationEntry(OrePrefix.ingot, Materials.Aluminium));
        ModHandler.addShapedRecipe("piston_steel", new ItemStack(Blocks.PISTON, 1), "WWW", "CBC", "CRC", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood), 'C', OrePrefix.stoneCobble, 'R', new UnificationEntry(OrePrefix.dust, Materials.Redstone), 'B', new UnificationEntry(OrePrefix.ingot, Materials.Steel));
        ModHandler.addShapedRecipe("piston_titanium", new ItemStack(Blocks.PISTON, 1), "WWW", "CBC", "CRC", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood), 'C', OrePrefix.stoneCobble, 'R', new UnificationEntry(OrePrefix.dust, Materials.Redstone), 'B', new UnificationEntry(OrePrefix.ingot, Materials.Titanium));

        ModHandler.addShapelessRecipe("dynamite", MetaItems.DYNAMITE.getStackForm(), Items.STRING, Items.PAPER, Items.GUNPOWDER);
        GTLog.logger.info("Modifying vanilla recipes according to config. DON'T BE SCARED OF FML's WARNING ABOUT DANGEROUS ALTERNATIVE PREFIX.");

        if (ConfigHolder.vanillaRecipes.bucketRequirePlatesAndHammer) {
            ModHandler.addShapedRecipe("iron_bucket", new ItemStack(Items.BUCKET), "XhX", " X ", 'X', new UnificationEntry(OrePrefix.plate, Materials.Iron));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:bucket"));
        }
        if (ConfigHolder.vanillaRecipes.ironConsumingCraftingRecipesRequirePlates) {
            ModHandler.addShapedRecipe("iron_pressure_plate", new ItemStack(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), "XXh", 'X', new UnificationEntry(OrePrefix.plate, Materials.Iron));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:heavy_weighted_pressure_plate"));

            ModHandler.addShapedRecipe("gold_pressure_plate", new ItemStack(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE), "XXh", 'X', new UnificationEntry(OrePrefix.plate, Materials.Gold));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:light_weighted_pressure_plate"));

            ModHandler.addShapedRecipe("iron_door", new ItemStack(Items.IRON_DOOR, 3), "XX ", "XXh", "XX ", 'X', new UnificationEntry(OrePrefix.plate, Materials.Iron));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_door"));

            ModHandler.addShapedRecipe("iron_trapdoor", new ItemStack(Blocks.IRON_TRAPDOOR), "XX ", "XXh", 'X', new UnificationEntry(OrePrefix.plate, Materials.Iron));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_trapdoor"));

            ModHandler.addShapedRecipe("cauldron", new ItemStack(Items.CAULDRON), "X X", "XhX", "XXX", 'X', new UnificationEntry(OrePrefix.plate, Materials.Iron));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:cauldron"));

            ModHandler.addShapedRecipe("hopper", new ItemStack(Blocks.HOPPER), "XwX", "XCX", " X ", 'X', new UnificationEntry(OrePrefix.plate, Materials.Iron), 'C', "chestWood");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:hopper"));

            ModHandler.addShapedRecipe("iron_bars", new ItemStack(Blocks.IRON_BARS, 8), " w ", "XXX", "XXX", 'X', new UnificationEntry(OrePrefix.stick, Materials.Iron));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_bars"));
        }

        if (ConfigHolder.vanillaRecipes.bowlRequireKnife) {
            ModHandler.addShapedRecipe("bowl", new ItemStack(Items.BOWL), "k", "X", 'X', new UnificationEntry(OrePrefix.plank, Materials.Wood));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:bowl"));
        }

        if (ConfigHolder.vanillaRecipes.nerfStickCrafting) {
            ModHandler.addShapedRecipe("stick_saw", new ItemStack(Items.STICK, 4), "s", "P", "P", 'P', new UnificationEntry(OrePrefix.plank, Materials.Wood));
            ModHandler.addShapedRecipe("stick_normal", new ItemStack(Items.STICK, 2), "P", "P", 'P', new UnificationEntry(OrePrefix.plank, Materials.Wood));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:stick"));
        }

        ModHandler.addShapelessRecipe("borosilicate_glass", OreDictUnifier.get(OrePrefix.dust, Materials.BorosilicateGlass, 8), new UnificationEntry(OrePrefix.dust, Materials.Boron), new UnificationEntry(OrePrefix.dust, Materials.Glass), new UnificationEntry(OrePrefix.dust, Materials.Glass), new UnificationEntry(OrePrefix.dust, Materials.Glass), new UnificationEntry(OrePrefix.dust, Materials.Glass), new UnificationEntry(OrePrefix.dust, Materials.Glass), new UnificationEntry(OrePrefix.dust, Materials.Glass), new UnificationEntry(OrePrefix.dust, Materials.Glass));
        ModHandler.addShapelessRecipe("dust_ferrite_mixture", OreDictUnifier.get(OrePrefix.dust, Materials.FerriteMixture, 6), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.Zinc), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron));
        ModHandler.addShapelessRecipe("dust_indium_gallium_phosphide", OreDictUnifier.get(OrePrefix.dust, Materials.IndiumGalliumPhosphide, 3), new UnificationEntry(OrePrefix.dust, Materials.Indium), new UnificationEntry(OrePrefix.dust, Materials.Gallium), new UnificationEntry(OrePrefix.dust, Materials.Phosphorus));
        ModHandler.addShapelessRecipe("dust_electrum", OreDictUnifier.get(OrePrefix.dust, Materials.Electrum, 2), new UnificationEntry(OrePrefix.dust, Materials.Silver), new UnificationEntry(OrePrefix.dust, Materials.Gold));
        ModHandler.addShapelessRecipe("dust_brass", OreDictUnifier.get(OrePrefix.dust, Materials.Brass, 4), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Zinc));
        ModHandler.addShapelessRecipe("dust_bronze", OreDictUnifier.get(OrePrefix.dust, Materials.Bronze, 4), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Tin));
        ModHandler.addShapelessRecipe("dust_invar", OreDictUnifier.get(OrePrefix.dust, Materials.Invar, 3), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Nickel));
        ModHandler.addShapelessRecipe("dust_cupronickel", OreDictUnifier.get(OrePrefix.dust, Materials.Cupronickel, 2), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.Copper));

        ModHandler.addShapelessRecipe("dust_rose_gold", OreDictUnifier.get(OrePrefix.dust, Materials.RoseGold, 5), new UnificationEntry(OrePrefix.dust, Materials.Gold), new UnificationEntry(OrePrefix.dust, Materials.Gold), new UnificationEntry(OrePrefix.dust, Materials.Gold), new UnificationEntry(OrePrefix.dust, Materials.Gold), new UnificationEntry(OrePrefix.dust, Materials.Copper));
        ModHandler.addShapelessRecipe("dust_sterling_silver", OreDictUnifier.get(OrePrefix.dust, Materials.SterlingSilver, 5), new UnificationEntry(OrePrefix.dust, Materials.Silver), new UnificationEntry(OrePrefix.dust, Materials.Silver), new UnificationEntry(OrePrefix.dust, Materials.Silver), new UnificationEntry(OrePrefix.dust, Materials.Silver), new UnificationEntry(OrePrefix.dust, Materials.Copper));
        ModHandler.addShapelessRecipe("dust_black_bronze", OreDictUnifier.get(OrePrefix.dust, Materials.BlackBronze, 5), new UnificationEntry(OrePrefix.dust, Materials.Gold), new UnificationEntry(OrePrefix.dust, Materials.Silver), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper));
        ModHandler.addShapelessRecipe("dust_bismuth_bronze", OreDictUnifier.get(OrePrefix.dust, Materials.BismuthBronze, 5), new UnificationEntry(OrePrefix.dust, Materials.Bismuth), new UnificationEntry(OrePrefix.dust, Materials.Zinc), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper));
        ModHandler.addShapelessRecipe("dust_black_steel", OreDictUnifier.get(OrePrefix.dust, Materials.BlackSteel, 5), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.BlackBronze), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel));
        ModHandler.addShapelessRecipe("dust_red_steel", OreDictUnifier.get(OrePrefix.dust, Materials.RedSteel, 8), new UnificationEntry(OrePrefix.dust, Materials.SterlingSilver), new UnificationEntry(OrePrefix.dust, Materials.BismuthBronze), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.BlackSteel), new UnificationEntry(OrePrefix.dust, Materials.BlackSteel), new UnificationEntry(OrePrefix.dust, Materials.BlackSteel), new UnificationEntry(OrePrefix.dust, Materials.BlackSteel));
        ModHandler.addShapelessRecipe("dust_blue_steel", OreDictUnifier.get(OrePrefix.dust, Materials.BlueSteel, 8), new UnificationEntry(OrePrefix.dust, Materials.RoseGold), new UnificationEntry(OrePrefix.dust, Materials.Brass), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.BlackSteel), new UnificationEntry(OrePrefix.dust, Materials.BlackSteel), new UnificationEntry(OrePrefix.dust, Materials.BlackSteel), new UnificationEntry(OrePrefix.dust, Materials.BlackSteel));

        ModHandler.addShapelessRecipe("dust_ultimet", OreDictUnifier.get(OrePrefix.dust, Materials.Ultimet, 9), new UnificationEntry(OrePrefix.dust, Materials.Cobalt), new UnificationEntry(OrePrefix.dust, Materials.Cobalt), new UnificationEntry(OrePrefix.dust, Materials.Cobalt), new UnificationEntry(OrePrefix.dust, Materials.Cobalt), new UnificationEntry(OrePrefix.dust, Materials.Cobalt), new UnificationEntry(OrePrefix.dust, Materials.Chrome), new UnificationEntry(OrePrefix.dust, Materials.Chrome), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.Molybdenum));
        ModHandler.addShapelessRecipe("dust_cobalt_brass", OreDictUnifier.get(OrePrefix.dust, Materials.CobaltBrass, 9), new UnificationEntry(OrePrefix.dust, Materials.Brass), new UnificationEntry(OrePrefix.dust, Materials.Brass), new UnificationEntry(OrePrefix.dust, Materials.Brass), new UnificationEntry(OrePrefix.dust, Materials.Brass), new UnificationEntry(OrePrefix.dust, Materials.Brass), new UnificationEntry(OrePrefix.dust, Materials.Brass), new UnificationEntry(OrePrefix.dust, Materials.Brass), new UnificationEntry(OrePrefix.dust, Materials.Aluminium), new UnificationEntry(OrePrefix.dust, Materials.Cobalt));
        ModHandler.addShapelessRecipe("dust_stainless_steel", OreDictUnifier.get(OrePrefix.dust, Materials.StainlessSteel, 9), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.Manganese), new UnificationEntry(OrePrefix.dust, Materials.Chrome));
        ModHandler.addShapelessRecipe("dust_kanthal", OreDictUnifier.get(OrePrefix.dust, Materials.Kanthal, 3), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Aluminium), new UnificationEntry(OrePrefix.dust, Materials.Chrome));

        ModHandler.addShapelessRecipe("dust_tiny_ultimet", OreDictUnifier.get(OrePrefix.dust, Materials.Ultimet, 1), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Chrome), new UnificationEntry(OrePrefix.dustTiny, Materials.Chrome), new UnificationEntry(OrePrefix.dustTiny, Materials.Nickel), new UnificationEntry(OrePrefix.dustTiny, Materials.Molybdenum));
        ModHandler.addShapelessRecipe("dust_tiny_cobalt_brass", OreDictUnifier.get(OrePrefix.dust, Materials.CobaltBrass, 1), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Aluminium), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt));
        ModHandler.addShapelessRecipe("dust_tiny_stainless_steel", OreDictUnifier.get(OrePrefix.dust, Materials.StainlessSteel, 1), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Nickel), new UnificationEntry(OrePrefix.dustTiny, Materials.Manganese), new UnificationEntry(OrePrefix.dustTiny, Materials.Chrome));
        ModHandler.addShapelessRecipe("dust_tiny_kanthal", OreDictUnifier.get(OrePrefix.dustTiny, Materials.Kanthal, 3), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Aluminium), new UnificationEntry(OrePrefix.dustTiny, Materials.Chrome));

        ModHandler.addShapelessRecipe("dust_vanadium_steel", OreDictUnifier.get(OrePrefix.dust, Materials.VanadiumSteel, 9), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Vanadium), new UnificationEntry(OrePrefix.dust, Materials.Chrome));
        ModHandler.addShapelessRecipe("dust_hssg", OreDictUnifier.get(OrePrefix.dust, Materials.HSSG, 9), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.Chrome), new UnificationEntry(OrePrefix.dust, Materials.Molybdenum), new UnificationEntry(OrePrefix.dust, Materials.Molybdenum), new UnificationEntry(OrePrefix.dust, Materials.Vanadium));
        ModHandler.addShapelessRecipe("dust_hsse", OreDictUnifier.get(OrePrefix.dust, Materials.HSSE, 9), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.Cobalt), new UnificationEntry(OrePrefix.dust, Materials.Manganese), new UnificationEntry(OrePrefix.dust, Materials.Silicon));
        ModHandler.addShapelessRecipe("dust_hsss", OreDictUnifier.get(OrePrefix.dust, Materials.HSSS, 9), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.Iridium), new UnificationEntry(OrePrefix.dust, Materials.Iridium), new UnificationEntry(OrePrefix.dust, Materials.Osmium));

        ModHandler.addShapelessRecipe("powder_coal", new ItemStack(Items.GUNPOWDER, 6), new UnificationEntry(OrePrefix.dust, Materials.Coal), new UnificationEntry(OrePrefix.dust, Materials.Coal), new UnificationEntry(OrePrefix.dust, Materials.Coal), new UnificationEntry(OrePrefix.dust, Materials.Sulfur), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter));
        ModHandler.addShapelessRecipe("powder_charcoal", new ItemStack(Items.GUNPOWDER, 6), new UnificationEntry(OrePrefix.dust, Materials.Charcoal), new UnificationEntry(OrePrefix.dust, Materials.Charcoal), new UnificationEntry(OrePrefix.dust, Materials.Charcoal), new UnificationEntry(OrePrefix.dust, Materials.Sulfur), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter));
        ModHandler.addShapelessRecipe("powder_carbon", new ItemStack(Items.GUNPOWDER, 6), new UnificationEntry(OrePrefix.dust, Materials.Carbon), new UnificationEntry(OrePrefix.dust, Materials.Carbon), new UnificationEntry(OrePrefix.dust, Materials.Carbon), new UnificationEntry(OrePrefix.dust, Materials.Sulfur), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter));

        MetaBlocks.FRAMES.values().forEach(CraftingRecipeLoader::registerColoringRecipes);

        if (ConfigHolder.vanillaRecipes.nerfPaperCrafting) {
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:paper"));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:sugar"));
            ModHandler.addShapedRecipe("paper_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Paper, 2), "SSS", " m ", 'S', new ItemStack(Items.REEDS));
            ModHandler.addShapedRecipe("sugar", OreDictUnifier.get(OrePrefix.dust, Materials.Sugar, 1), "Sm ", 'S', new ItemStack(Items.REEDS));
            ItemStack paperStack = OreDictUnifier.get(OrePrefix.plate, Materials.Paper, 2);
            Object[] paperRecipeIngredients = ModHandler.finalizeShapedRecipeInput(" C ", "SSS", " C ", 'S', OreDictUnifier.get(OrePrefix.dust, Materials.Paper, 1), 'C', new ItemStack(Blocks.STONE_SLAB));
            ForgeRegistries.RECIPES.register(new CustomItemReturnShapedOreRecipeRecipe(null, paperStack,
                stack -> Block.getBlockFromItem(stack.getItem()) == Blocks.STONE_SLAB, paperRecipeIngredients)
                .setMirrored(false).setRegistryName("paper"));
        }

        if (ConfigHolder.vanillaRecipes.flintAndSteelRequireSteel) {
            ModHandler.addShapedRecipe("flint_and_steel", new ItemStack(Items.FLINT_AND_STEEL), "S ", " F", 'F', new ItemStack(Items.FLINT, 1), 'S', new UnificationEntry(OrePrefix.nugget, Materials.Steel));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:flint_and_steel"));
        }

        ModHandler.addShapedRecipe("battery_hull_lv", MetaItems.BATTERY_HULL_LV.getStackForm(), "C", "P", "P", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'P', new UnificationEntry(OrePrefix.plate, Materials.BatteryAlloy));
        ModHandler.addShapedRecipe("battery_hull_mv", MetaItems.BATTERY_HULL_MV.getStackForm(), "C C", "PPP", "PPP", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'P', new UnificationEntry(OrePrefix.plate, Materials.BatteryAlloy));

        ModHandler.addShapedRecipe("carbon_mesh", MetaItems.CARBON_MESH.getStackForm(), "XX", "XX", 'X', MetaItems.CARBON_FIBERS.getStackForm());

        ModHandler.addShapedRecipe("component_grinder_diamond", MetaItems.COMPONENT_GRINDER_DIAMOND.getStackForm(), "XSX", "SDS", "XSX", 'X', new UnificationEntry(OrePrefix.dust, Materials.Diamond), 'S', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'D', new UnificationEntry(OrePrefix.gem, Materials.Diamond));
        ModHandler.addShapedRecipe("component_grinder_tungsten", MetaItems.COMPONENT_GRINDER_TUNGSTEN.getStackForm(), "WSW", "SDS", "WSW", 'W', new UnificationEntry(OrePrefix.plate, Materials.Tungsten), 'S', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'D', new UnificationEntry(OrePrefix.gem, Materials.Diamond));
        ModHandler.addShapedRecipe("component_sawblade_diamond", MetaItems.COMPONENT_SAW_BLADE_DIAMOND.getStackForm(), " D ", "DGD", " D ", 'D', new UnificationEntry(OrePrefix.dustSmall, Materials.Diamond), 'G', new UnificationEntry(OrePrefix.gear, Materials.CobaltBrass));

        ModHandler.addShapedRecipe("energy_field_projector", MetaItems.ENERGY_FIELD_PROJECTOR.getStackForm(), "PLP", "LFL", "PLP", 'P', MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(), 'L', MetaItems.LAPOTRON_CRYSTAL.getStackForm(), 'F', MetaItems.FIELD_GENERATOR_EV);

        ModHandler.addShapedRecipe("ingot_iridium_alloy", MetaItems.INGOT_IRIDIUM_ALLOY.getStackForm(), "IWI", "WDW", "IWI", 'I', new UnificationEntry(OrePrefix.plate, Materials.Iridium), 'W', new UnificationEntry(OrePrefix.plate, Materials.Tungsten), 'D', new UnificationEntry(OrePrefix.dust, Materials.Diamond));
        ModHandler.addShapedRecipe("ingot_mixed_metal", MetaItems.INGOT_MIXED_METAL.getStackForm(2), "TTT", "BBB", "III", 'T', new UnificationEntry(OrePrefix.plate, Materials.Tin), 'B', new UnificationEntry(OrePrefix.plate, Materials.Bronze), 'I', new UnificationEntry(OrePrefix.plate, Materials.Iron));

        ModHandler.addShapedRecipe("nano_saber", MetaItems.NANO_SABER.getStackForm(), "PIC", "PIC", "XEX", 'P', new UnificationEntry(OrePrefix.plate, Materials.Platinum), 'I', MetaItems.PLATE_IRIDIUM_ALLOY.getStackForm(), 'C', MetaItems.CARBON_PLATE.getStackForm(), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Extreme), 'E', MetaItems.ENERGY_CRYSTAL.getStackForm());

        ModHandler.addShapedRecipe("solar_panel/solar_panel_basic", MetaItems.COVER_SOLAR_PANEL.getStackForm(), "SGS", "CXC", "AAA", 'S', new UnificationEntry(OrePrefix.plate, Materials.Silicon), 'G', "paneGlass", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Basic), 'A', new UnificationEntry(OrePrefix.plate, Materials.Aluminium));
        ModHandler.addShapedRecipe("solar_panel/solar_panel_ulv", MetaItems.COVER_SOLAR_PANEL_ULV.getStackForm(), "SSS", "SXS", "SSS", 'S', MetaItems.COVER_SOLAR_PANEL.getStackForm(), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Basic));
        ModHandler.addShapedRecipe("solar_panel/solar_panel_lv", MetaItems.COVER_SOLAR_PANEL_LV.getStackForm(), "PSP", "SXS", "PSP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Silicon), 'S', MetaItems.COVER_SOLAR_PANEL_ULV.getStackForm(), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Good));

        ///////////////////////////////////////////////////
        //              Component Recipes                //
        ///////////////////////////////////////////////////
        ModHandler.addShapedRecipe("electric_motor/electric_motor_lv_steel", MetaItems.ELECTRIC_MOTOR_LV.getStackForm(), "CWR", "WMW", "RWC", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'W', new UnificationEntry(OrePrefix.wireGtSingle, Materials.Copper), 'R', new UnificationEntry(OrePrefix.stick, Materials.Steel), 'M', new UnificationEntry(OrePrefix.stick, Materials.SteelMagnetic));
        ModHandler.addShapedRecipe("electric_motor/electric_motor_lv_iron", MetaItems.ELECTRIC_MOTOR_LV.getStackForm(), "CWR", "WMW", "RWC", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'W', new UnificationEntry(OrePrefix.wireGtSingle, Materials.Copper), 'R', new UnificationEntry(OrePrefix.stick, Materials.Iron), 'M', new UnificationEntry(OrePrefix.stick, Materials.IronMagnetic));
        ModHandler.addShapedRecipe("electric_motor/electric_motor_mv", MetaItems.ELECTRIC_MOTOR_MV.getStackForm(), "CWR", "WMW", "RWC", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'W', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Copper), 'R', new UnificationEntry(OrePrefix.stick, Materials.Aluminium), 'M', new UnificationEntry(OrePrefix.stick, Materials.SteelMagnetic));
        ModHandler.addShapedRecipe("electric_motor/electric_motor_hv", MetaItems.ELECTRIC_MOTOR_HV.getStackForm(), "CWR", "WMW", "RWC", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'W', new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Copper), 'R', new UnificationEntry(OrePrefix.stick, Materials.StainlessSteel), 'M', new UnificationEntry(OrePrefix.stick, Materials.SteelMagnetic));
        ModHandler.addShapedRecipe("electric_motor/electric_motor_ev", MetaItems.ELECTRIC_MOTOR_EV.getStackForm(), "CWR", "WMW", "RWC", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium), 'W', new UnificationEntry(OrePrefix.wireGtOctal, Materials.AnnealedCopper), 'R', new UnificationEntry(OrePrefix.stick, Materials.Titanium), 'M', new UnificationEntry(OrePrefix.stick, Materials.NeodymiumMagnetic));
        ModHandler.addShapedRecipe("electric_motor/electric_motor_iv", MetaItems.ELECTRIC_MOTOR_IV.getStackForm(), "CWR", "WMW", "RWC", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten), 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.AnnealedCopper), 'R', new UnificationEntry(OrePrefix.stick, Materials.TungstenSteel), 'M', new UnificationEntry(OrePrefix.stick, Materials.NeodymiumMagnetic));

        ModHandler.addShapedRecipe("electric_piston/electric_piston_lv", MetaItems.ELECTRIC_PISTON_LV.getStackForm(), "PPP", "CRR", "CMG", 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'R', new UnificationEntry(OrePrefix.stick, Materials.Steel), 'G', new UnificationEntry(OrePrefix.gearSmall, Materials.Steel), 'M', MetaItems.ELECTRIC_MOTOR_LV.getStackForm());
        ModHandler.addShapedRecipe("electric_piston/electric_piston_mv", MetaItems.ELECTRIC_PISTON_MV.getStackForm(), "PPP", "CRR", "CMG", 'P', new UnificationEntry(OrePrefix.plate, Materials.Aluminium), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'R', new UnificationEntry(OrePrefix.stick, Materials.Aluminium), 'G', new UnificationEntry(OrePrefix.gearSmall, Materials.Aluminium), 'M', MetaItems.ELECTRIC_MOTOR_MV.getStackForm());
        ModHandler.addShapedRecipe("electric_piston/electric_piston_hv", MetaItems.ELECTRIC_PISTON_HV.getStackForm(), "PPP", "CRR", "CMG", 'P', new UnificationEntry(OrePrefix.plate, Materials.StainlessSteel), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'R', new UnificationEntry(OrePrefix.stick, Materials.StainlessSteel), 'G', new UnificationEntry(OrePrefix.gearSmall, Materials.StainlessSteel), 'M', MetaItems.ELECTRIC_MOTOR_HV.getStackForm());
        ModHandler.addShapedRecipe("electric_piston/electric_piston_ev", MetaItems.ELECTRIC_PISTON_EV.getStackForm(), "PPP", "CRR", "CMG", 'P', new UnificationEntry(OrePrefix.plate, Materials.Titanium), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium), 'R', new UnificationEntry(OrePrefix.stick, Materials.Titanium), 'G', new UnificationEntry(OrePrefix.gearSmall, Materials.Titanium), 'M', MetaItems.ELECTRIC_MOTOR_EV.getStackForm());
        ModHandler.addShapedRecipe("electric_piston/electric_piston_iv", MetaItems.ELECTRIC_PISTON_IV.getStackForm(), "PPP", "CRR", "CMG", 'P', new UnificationEntry(OrePrefix.plate, Materials.TungstenSteel), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten), 'R', new UnificationEntry(OrePrefix.stick, Materials.TungstenSteel), 'G', new UnificationEntry(OrePrefix.gearSmall, Materials.TungstenSteel), 'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm());

        ModHandler.addShapedRecipe("conveyor_module/conveyor_module_lv", MetaItems.CONVEYOR_MODULE_LV.getStackForm(), "RRR", "MCM", "RRR", 'R', new UnificationEntry(OrePrefix.plate, Materials.Rubber), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'M', MetaItems.ELECTRIC_MOTOR_LV.getStackForm());
        ModHandler.addShapedRecipe("conveyor_module/conveyor_module_mv", MetaItems.CONVEYOR_MODULE_MV.getStackForm(), "RRR", "MCM", "RRR", 'R', new UnificationEntry(OrePrefix.plate, Materials.Rubber), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'M', MetaItems.ELECTRIC_MOTOR_MV.getStackForm());
        ModHandler.addShapedRecipe("conveyor_module/conveyor_module_hv", MetaItems.CONVEYOR_MODULE_HV.getStackForm(), "RRR", "MCM", "RRR", 'R', new UnificationEntry(OrePrefix.plate, Materials.Rubber), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'M', MetaItems.ELECTRIC_MOTOR_HV.getStackForm());
        ModHandler.addShapedRecipe("conveyor_module/conveyor_module_ev", MetaItems.CONVEYOR_MODULE_EV.getStackForm(), "RRR", "MCM", "RRR", 'R', new UnificationEntry(OrePrefix.plate, Materials.Rubber), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium), 'M', MetaItems.ELECTRIC_MOTOR_EV.getStackForm());
        ModHandler.addShapedRecipe("conveyor_module/conveyor_module_iv", MetaItems.CONVEYOR_MODULE_IV.getStackForm(), "RRR", "MCM", "RRR", 'R', new UnificationEntry(OrePrefix.plate, Materials.Rubber), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten), 'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm());

        ModHandler.addShapedRecipe("robot_arm/robot_arm_lv", MetaItems.ROBOT_ARM_LV.getStackForm(), "CCC", "MRM", "PXR", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'R', new UnificationEntry(OrePrefix.stick, Materials.Steel), 'M', MetaItems.ELECTRIC_MOTOR_LV.getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_LV.getStackForm(), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Basic));
        ModHandler.addShapedRecipe("robot_arm/robot_arm_mv", MetaItems.ROBOT_ARM_MV.getStackForm(), "CCC", "MRM", "PXR", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'R', new UnificationEntry(OrePrefix.stick, Materials.Aluminium), 'M', MetaItems.ELECTRIC_MOTOR_MV.getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_MV.getStackForm(), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Good));
        ModHandler.addShapedRecipe("robot_arm/robot_arm_hv", MetaItems.ROBOT_ARM_HV.getStackForm(), "CCC", "MRM", "PXR", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'R', new UnificationEntry(OrePrefix.stick, Materials.StainlessSteel), 'M', MetaItems.ELECTRIC_MOTOR_HV.getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_HV.getStackForm(), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Advanced));
        ModHandler.addShapedRecipe("robot_arm/robot_arm_ev", MetaItems.ROBOT_ARM_EV.getStackForm(), "CCC", "MRM", "PXR", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium), 'R', new UnificationEntry(OrePrefix.stick, Materials.Titanium), 'M', MetaItems.ELECTRIC_MOTOR_EV.getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_EV.getStackForm(), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Extreme));
        ModHandler.addShapedRecipe("robot_arm/robot_arm_iv", MetaItems.ROBOT_ARM_IV.getStackForm(), "CCC", "MRM", "PXR", 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten), 'R', new UnificationEntry(OrePrefix.stick, Materials.TungstenSteel), 'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(), 'P', MetaItems.ELECTRIC_PISTON_IV.getStackForm(), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Elite));

        ModHandler.addShapedRecipe("sensor/sensor_lv", MetaItems.SENSOR_LV.getStackForm(), "P G", "PR ", "XPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'R', new UnificationEntry(OrePrefix.stick, Materials.Brass), 'G', new UnificationEntry(OrePrefix.gem, Materials.Quartzite), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Basic));
        ModHandler.addShapedRecipe("sensor/sensor_mv", MetaItems.SENSOR_MV.getStackForm(), "P G", "PR ", "XPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Aluminium), 'R', new UnificationEntry(OrePrefix.stick, Materials.Electrum), 'G', new UnificationEntry(OrePrefix.gem, Materials.NetherQuartz), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Good));
        ModHandler.addShapedRecipe("sensor/sensor_hv", MetaItems.SENSOR_HV.getStackForm(), "P G", "PR ", "XPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.StainlessSteel), 'R', new UnificationEntry(OrePrefix.stick, Materials.Chrome), 'G', new UnificationEntry(OrePrefix.gem, Materials.Emerald), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Advanced));
        ModHandler.addShapedRecipe("sensor/sensor_ev", MetaItems.SENSOR_EV.getStackForm(), "P G", "PR ", "XPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Titanium), 'R', new UnificationEntry(OrePrefix.stick, Materials.Platinum), 'G', new UnificationEntry(OrePrefix.gem, Materials.EnderPearl), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Extreme));
        ModHandler.addShapedRecipe("sensor/sensor_iv", MetaItems.SENSOR_IV.getStackForm(), "P G", "PR ", "XPP", 'P', new UnificationEntry(OrePrefix.plate, Materials.TungstenSteel), 'R', new UnificationEntry(OrePrefix.stick, Materials.Osmium), 'G', new UnificationEntry(OrePrefix.gem, Materials.EnderEye), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Elite));

        ModHandler.addShapedRecipe("emitter/emitter_lv", MetaItems.EMITTER_LV.getStackForm(), "RRX", "CGR", "XCR", 'R', new UnificationEntry(OrePrefix.stick, Materials.Brass), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'G', new UnificationEntry(OrePrefix.gem, Materials.Quartzite), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Basic));
        ModHandler.addShapedRecipe("emitter/emitter_mv", MetaItems.EMITTER_MV.getStackForm(), "RRX", "CGR", "XCR", 'R', new UnificationEntry(OrePrefix.stick, Materials.Electrum), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'G', new UnificationEntry(OrePrefix.gem, Materials.NetherQuartz), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Good));
        ModHandler.addShapedRecipe("emitter/emitter_hv", MetaItems.EMITTER_HV.getStackForm(), "RRX", "CGR", "XCR", 'R', new UnificationEntry(OrePrefix.stick, Materials.Chrome), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'G', new UnificationEntry(OrePrefix.gem, Materials.Emerald), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Advanced));
        ModHandler.addShapedRecipe("emitter/emitter_ev", MetaItems.EMITTER_EV.getStackForm(), "RRX", "CGR", "XCR", 'R', new UnificationEntry(OrePrefix.stick, Materials.Platinum), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium), 'G', new UnificationEntry(OrePrefix.gem, Materials.EnderPearl), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Extreme));
        ModHandler.addShapedRecipe("emitter/emitter_iv", MetaItems.EMITTER_IV.getStackForm(), "RRX", "CGR", "XCR", 'R', new UnificationEntry(OrePrefix.stick, Materials.Osmium), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten), 'G', new UnificationEntry(OrePrefix.gem, Materials.EnderEye), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Elite));

        ModHandler.addShapedRecipe("field_generator/field_generator_lv", MetaItems.FIELD_GENERATOR_LV.getStackForm(), "WXW", "XGX", "WXW", 'W', new UnificationEntry(OrePrefix.wireGtSingle, Materials.Osmium), 'G', new UnificationEntry(OrePrefix.gem, Materials.EnderPearl), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Basic));
        ModHandler.addShapedRecipe("field_generator/field_generator_mv", MetaItems.FIELD_GENERATOR_MV.getStackForm(), "WXW", "XGX", "WXW", 'W', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Osmium), 'G', new UnificationEntry(OrePrefix.gem, Materials.EnderEye), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Good));
        ModHandler.addShapedRecipe("field_generator/field_generator_hv", MetaItems.FIELD_GENERATOR_HV.getStackForm(), "WXW", "XGX", "WXW", 'W', new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Osmium), 'G', MetaItems.QUANTUM_EYE.getStackForm(), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Advanced));
        ModHandler.addShapedRecipe("field_generator/field_generator_ev", MetaItems.FIELD_GENERATOR_EV.getStackForm(), "WXW", "XGX", "WXW", 'W', new UnificationEntry(OrePrefix.wireGtOctal, Materials.Osmium), 'G', new ItemStack(Items.NETHER_STAR), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Extreme));
        ModHandler.addShapedRecipe("field_generator/field_generator_iv", MetaItems.FIELD_GENERATOR_IV.getStackForm(), "WXW", "XGX", "WXW", 'W', new UnificationEntry(OrePrefix.wireGtHex, Materials.Osmium), 'G', MetaItems.QUANTUM_STAR.getStackForm(), 'X', new UnificationEntry(OrePrefix.circuit, Tier.Elite));

        final Map<String, Material> ringMaterials = new HashMap<String, Material>() {{
            put("rubber", Materials.Rubber);
            put("silicone_rubber", Materials.SiliconeRubber);
            put("styrene_butadiene_rubber", Materials.StyreneButadieneRubber);
        }};
        ModHandler.addShapedRecipe("electric_pump/electric_pump_lv_paper", MetaItems.ELECTRIC_PUMP_LV.getStackForm(), "SXR", "P P", "RMC", 'S', new UnificationEntry(OrePrefix.screw, Materials.Tin), 'X', new UnificationEntry(OrePrefix.rotor, Materials.Tin),  'P', new UnificationEntry(OrePrefix.plate, Materials.Tin), 'R', new UnificationEntry(OrePrefix.ring, Materials.Paper), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'M', MetaItems.ELECTRIC_MOTOR_LV.getStackForm());
        for (Map.Entry<String, Material> materialEntry : ringMaterials.entrySet()) {
            ModHandler.addShapedRecipe(String.format("electric_pump/%s/electric_pump_lv", materialEntry.getKey()), MetaItems.ELECTRIC_PUMP_LV.getStackForm(), "SXR", "P P", "RMC", 'S', new UnificationEntry(OrePrefix.screw, Materials.Tin), 'X', new UnificationEntry(OrePrefix.rotor, Materials.Tin), 'P', new UnificationEntry(OrePrefix.plate, Materials.Tin), 'R', new UnificationEntry(OrePrefix.ring, materialEntry.getValue()), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'M', MetaItems.ELECTRIC_MOTOR_LV.getStackForm());
            ModHandler.addShapedRecipe(String.format("electric_pump/%s/electric_pump_mv", materialEntry.getKey()), MetaItems.ELECTRIC_PUMP_MV.getStackForm(), "SXR", "P P", "RMC", 'S', new UnificationEntry(OrePrefix.screw, Materials.Bronze), 'X', new UnificationEntry(OrePrefix.rotor, Materials.Bronze), 'P', new UnificationEntry(OrePrefix.plate, Materials.Bronze), 'R', new UnificationEntry(OrePrefix.ring, materialEntry.getValue()), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'M', MetaItems.ELECTRIC_MOTOR_MV.getStackForm());
            ModHandler.addShapedRecipe(String.format("electric_pump/%s/electric_pump_hv", materialEntry.getKey()), MetaItems.ELECTRIC_PUMP_HV.getStackForm(), "SXR", "P P", "RMC", 'S', new UnificationEntry(OrePrefix.screw, Materials.Steel), 'X', new UnificationEntry(OrePrefix.rotor, Materials.Steel), 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'R', new UnificationEntry(OrePrefix.ring, materialEntry.getValue()), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'M', MetaItems.ELECTRIC_MOTOR_HV.getStackForm());
            ModHandler.addShapedRecipe(String.format("electric_pump/%s/electric_pump_ev", materialEntry.getKey()), MetaItems.ELECTRIC_PUMP_EV.getStackForm(), "SXR", "P P", "RMC", 'S', new UnificationEntry(OrePrefix.screw, Materials.StainlessSteel), 'X', new UnificationEntry(OrePrefix.rotor, Materials.StainlessSteel), 'P', new UnificationEntry(OrePrefix.plate, Materials.StainlessSteel), 'R', new UnificationEntry(OrePrefix.ring, materialEntry.getValue()), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium), 'M', MetaItems.ELECTRIC_MOTOR_EV.getStackForm());
            ModHandler.addShapedRecipe(String.format("electric_pump/%s/electric_pump_iv", materialEntry.getKey()), MetaItems.ELECTRIC_PUMP_IV.getStackForm(), "SXR", "P P", "RMC", 'S', new UnificationEntry(OrePrefix.screw, Materials.TungstenSteel), 'X', new UnificationEntry(OrePrefix.rotor, Materials.TungstenSteel), 'P', new UnificationEntry(OrePrefix.plate, Materials.TungstenSteel), 'R', new UnificationEntry(OrePrefix.ring, materialEntry.getValue()), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten), 'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm());
        }
    }

    private static void registerFacadeRecipe(Material material, int facadeAmount) {
        OreIngredient ingredient = new OreIngredient(new UnificationEntry(OrePrefix.plate, material).toString());
        ForgeRegistries.RECIPES.register(new FacadeRecipe(null, ingredient, facadeAmount).setRegistryName("facade_" + material));
    }

    private static void registerColoringRecipes(BlockColored block) {
        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            String recipeName = String.format("%s_color_%s", block.getRegistryName().getPath(), getColorName(dyeColor));
            ModHandler.addShapedRecipe(recipeName, new ItemStack(block, 8, dyeColor.getMetadata()), "XXX", "XDX", "XXX",
                'X', new ItemStack(block, 1, GTValues.W), 'D', getOrdictColorName(dyeColor));
        }
    }
}
