package gregtech.loaders.postload;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.MarkerMaterials.Tier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTLog;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.wood.BlockLogGT.LogVariant;
import gregtech.common.items.MetaItems;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CraftingRecipeLoader {

    public static void init() {
        loadCraftingRecipes();
    }

    private static void loadCraftingRecipes() {

        ModHandler.addSmeltingRecipe(new UnificationEntry(OrePrefix.nugget, Materials.Iron), OreDictUnifier.get(OrePrefix.nugget, Materials.WroughtIron));
        ModHandler.addShapedRecipe("primitive_circuit", MetaItems.CIRCUIT_PRIMITIVE.getStackForm(), "SR", "RT", 'S', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'R', new UnificationEntry(OrePrefix.wireGtSingle, Materials.RedAlloy), 'T', new UnificationEntry(OrePrefix.wireGtSingle, Materials.Tin));
        ModHandler.addShapedRecipe("basic_circuit", MetaItems.CIRCUIT_BASIC.getStackForm(), "XXX", "NPN", "XXX", 'P', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'N', new UnificationEntry(OrePrefix.circuit, Tier.Primitive), 'X', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper));
        ModHandler.addShapelessRecipe("integrated_circuit", MetaItems.INTEGRATED_CIRCUIT.getStackForm(), MetaItems.CIRCUIT_BASIC.getStackForm());

        ModHandler.addShapelessRecipe("rubber_wood_planks", new ItemStack(Blocks.PLANKS, 4, EnumType.JUNGLE.getMetadata()), new ItemStack(MetaBlocks.LOG, 1, LogVariant.RUBBER_WOOD.ordinal()));
        ModHandler.addShapelessRecipe("chum_on_stick", MetaItems.FOOD_CHUM_ON_STICK.getStackForm(), new UnificationEntry(OrePrefix.stick, Materials.Wood), MetaItems.FOOD_CHUM);
        ModHandler.addShapedRecipe("rubber_ring", OreDictUnifier.get(OrePrefix.ring, Materials.Rubber), "k", "X", 'X', new UnificationEntry(OrePrefix.plate, Materials.Rubber));

        ModHandler.addShapelessRecipe("iron_magnetic_stick", OreDictUnifier.get(OrePrefix.stick, Materials.IronMagnetic), new UnificationEntry(OrePrefix.stick, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Redstone), new UnificationEntry(OrePrefix.dust, Materials.Redstone), new UnificationEntry(OrePrefix.dust, Materials.Redstone), new UnificationEntry(OrePrefix.dust, Materials.Redstone));
        ModHandler.addShapedRecipe("paper_ring", OreDictUnifier.get(OrePrefix.ring, Materials.Paper), "PPk", 'P', new UnificationEntry(OrePrefix.plate, Materials.Paper));

        ModHandler.addShapedRecipe("torch_sulfur", new ItemStack(Blocks.TORCH, 2), "C", "S", 'C', new UnificationEntry(OrePrefix.dust, Materials.Sulfur), 'S', new UnificationEntry(OrePrefix.stick, Materials.Wood));
        ModHandler.addShapedRecipe("torch_phosphor", new ItemStack(Blocks.TORCH, 6), "C", "S", 'C', new UnificationEntry(OrePrefix.dust, Materials.Phosphorus), 'S', new UnificationEntry(OrePrefix.stick, Materials.Wood));

        ModHandler.addShapedRecipe("piston_bronze", new ItemStack(Blocks.PISTON, 1), "WWW", "CBC", "CRC", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood), 'C', OrePrefix.stoneCobble, 'R', new UnificationEntry(OrePrefix.dust, Materials.Redstone), 'B', new UnificationEntry(OrePrefix.ingot, Materials.Bronze));
        ModHandler.addShapedRecipe("piston_aluminium", new ItemStack(Blocks.PISTON, 1), "WWW", "CBC", "CRC", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood), 'C', OrePrefix.stoneCobble, 'R', new UnificationEntry(OrePrefix.dust, Materials.Redstone), 'B', new UnificationEntry(OrePrefix.ingot, Materials.Aluminium));
        ModHandler.addShapedRecipe("piston_steel", new ItemStack(Blocks.PISTON, 1), "WWW", "CBC", "CRC", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood), 'C', OrePrefix.stoneCobble, 'R', new UnificationEntry(OrePrefix.dust, Materials.Redstone), 'B', new UnificationEntry(OrePrefix.ingot, Materials.Steel));
        ModHandler.addShapedRecipe("piston_titanium", new ItemStack(Blocks.PISTON, 1), "WWW", "CBC", "CRC", 'W', new UnificationEntry(OrePrefix.plank, Materials.Wood), 'C', OrePrefix.stoneCobble, 'R', new UnificationEntry(OrePrefix.dust, Materials.Redstone), 'B', new UnificationEntry(OrePrefix.ingot, Materials.Titanium));

        ModHandler.addShapedRecipe("pipe_small_steel", MetaItems.SMALL_STEEL_PIPE.getStackForm(2), "WWW", "w h", "WWW", 'W', new UnificationEntry(OrePrefix.plate, Materials.Steel));
        ModHandler.addShapedRecipe("pipe_small_bronze", MetaItems.SMALL_BRONZE_PIPE.getStackForm(2), "WWW", "w h", "WWW", 'W', new UnificationEntry(OrePrefix.plate, Materials.Bronze));

        ModHandler.addShapedRecipe("component_grinder_diamond", MetaItems.COMPONENT_GRINDER_DIAMOND.getStackForm(), "CWC", "WDW", "CWC", 'W', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'C', new UnificationEntry(OrePrefix.dust, Materials.Diamond), 'D', new UnificationEntry(OrePrefix.gem, Materials.Diamond));
        ModHandler.addShapedRecipe("component_grinder_tungsten", MetaItems.COMPONENT_GRINDER_TUNGSTEN.getStackForm(), "WCW", "CDC", "WCW", 'W', new UnificationEntry(OrePrefix.plate, Materials.Tungsten), 'C', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'D', new UnificationEntry(OrePrefix.gem, Materials.Diamond));

        ModHandler.addShapedRecipe("emitter_lv", MetaItems.EMITTER_LV.getStackForm(), "WWC", "XDW", "CXW", 'W', new UnificationEntry(OrePrefix.stick, Materials.Brass), 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Basic), 'D', new UnificationEntry(OrePrefix.gem, Materials.Quartzite), 'X', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin));
        ModHandler.addShapedRecipe("emitter_mv", MetaItems.EMITTER_MV.getStackForm(), "WWC", "XDW", "CXW", 'W', new UnificationEntry(OrePrefix.stick, Materials.Electrum), 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Good), 'D', new UnificationEntry(OrePrefix.gem, Materials.NetherQuartz), 'X', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper));
        ModHandler.addShapedRecipe("emitter_hv", MetaItems.EMITTER_HV.getStackForm(), "WWC", "XDW", "CXW", 'W', new UnificationEntry(OrePrefix.stick, Materials.Chrome), 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Advanced), 'D', new UnificationEntry(OrePrefix.gem, Materials.Emerald), 'X', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold));
        ModHandler.addShapedRecipe("emitter_ev", MetaItems.EMITTER_EV.getStackForm(), "WWC", "XDW", "CXW", 'W', new UnificationEntry(OrePrefix.stick, Materials.Platinum), 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Elite), 'D', new UnificationEntry(OrePrefix.gem, Materials.EnderPearl), 'X', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium));
        ModHandler.addShapedRecipe("emitter_iv", MetaItems.EMITTER_IV.getStackForm(), "WWC", "XDW", "CXW", 'W', new UnificationEntry(OrePrefix.stick, Materials.Osmium), 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Master), 'D', new UnificationEntry(OrePrefix.gem, Materials.EnderEye), 'X', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten));

        ModHandler.addShapedRecipe("robot_arm_lv", MetaItems.ROBOT_ARM_LV.getStackForm(), "WWw", "RSR", "PCS", 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'R', MetaItems.ELECTRIC_MOTOR_LV, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Basic), 'S', new UnificationEntry(OrePrefix.stick, Materials.Steel), 'P', MetaItems.ELECTRIC_PISTON_LV);
        ModHandler.addShapedRecipe("robot_arm_mv", MetaItems.ROBOT_ARM_MV.getStackForm(), "WWw", "RSR", "PCS", 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'R', MetaItems.ELECTRIC_MOTOR_MV, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Good), 'S', new UnificationEntry(OrePrefix.stick, Materials.Aluminium), 'P', MetaItems.ELECTRIC_PISTON_MV);
        ModHandler.addShapedRecipe("robot_arm_hv", MetaItems.ROBOT_ARM_HV.getStackForm(), "WWw", "RSR", "PCS", 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'R', MetaItems.ELECTRIC_MOTOR_HV, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Advanced), 'S', new UnificationEntry(OrePrefix.stick, Materials.StainlessSteel), 'P', MetaItems.ELECTRIC_PISTON_HV);
        ModHandler.addShapedRecipe("robot_arm_ev", MetaItems.ROBOT_ARM_EV.getStackForm(), "WWw", "RSR", "PCS", 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium), 'R', MetaItems.ELECTRIC_MOTOR_EV, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Elite), 'S', new UnificationEntry(OrePrefix.stick, Materials.Titanium), 'P', MetaItems.ELECTRIC_PISTON_EV);
        ModHandler.addShapedRecipe("robot_arm_iv", MetaItems.ROBOT_ARM_IV.getStackForm(), "WWw", "RSR", "PCS", 'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten), 'R', MetaItems.ELECTRIC_MOTOR_IV, 'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.Master), 'S', new UnificationEntry(OrePrefix.stick, Materials.TungstenSteel), 'P', MetaItems.ELECTRIC_PISTON_IV);

        ModHandler.addShapedRecipe("electric_piston_lv", MetaItems.ELECTRIC_PISTON_LV.getStackForm(), "WWw", "CSS", "CRG", 'W', new UnificationEntry(OrePrefix.plate, Materials.Steel), 'R', MetaItems.ELECTRIC_MOTOR_LV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin), 'S', new UnificationEntry(OrePrefix.stick, Materials.Steel), 'G', new UnificationEntry(OrePrefix.gearSmall, Materials.Steel));
        ModHandler.addShapedRecipe("electric_piston_mv", MetaItems.ELECTRIC_PISTON_MV.getStackForm(), "WWw", "CSS", "CRG", 'W', new UnificationEntry(OrePrefix.plate, Materials.Aluminium), 'R', MetaItems.ELECTRIC_MOTOR_MV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper), 'S', new UnificationEntry(OrePrefix.stick, Materials.Aluminium), 'G', new UnificationEntry(OrePrefix.gearSmall, Materials.Aluminium));
        ModHandler.addShapedRecipe("electric_piston_hv", MetaItems.ELECTRIC_PISTON_HV.getStackForm(), "WWw", "CSS", "CRG", 'W', new UnificationEntry(OrePrefix.plate, Materials.StainlessSteel), 'R', MetaItems.ELECTRIC_MOTOR_HV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold), 'S', new UnificationEntry(OrePrefix.stick, Materials.StainlessSteel), 'G', new UnificationEntry(OrePrefix.gearSmall, Materials.StainlessSteel));
        ModHandler.addShapedRecipe("electric_piston_ev", MetaItems.ELECTRIC_PISTON_EV.getStackForm(), "WWw", "CSS", "CRG", 'W', new UnificationEntry(OrePrefix.plate, Materials.Titanium), 'R', MetaItems.ELECTRIC_MOTOR_EV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium), 'S', new UnificationEntry(OrePrefix.stick, Materials.Titanium), 'G', new UnificationEntry(OrePrefix.gearSmall, Materials.Titanium));
        ModHandler.addShapedRecipe("electric_piston_iv", MetaItems.ELECTRIC_PISTON_IV.getStackForm(), "WWw", "CSS", "CRG", 'W', new UnificationEntry(OrePrefix.plate, Materials.TungstenSteel), 'R', MetaItems.ELECTRIC_MOTOR_IV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten), 'S', new UnificationEntry(OrePrefix.stick, Materials.TungstenSteel), 'G', new UnificationEntry(OrePrefix.gearSmall, Materials.TungstenSteel));

        ModHandler.addShapedRecipe("conveyor_module_lv", MetaItems.CONVEYOR_MODULE_LV.getStackForm(), "WWW", "RCR", "WWW", 'W', new UnificationEntry(OrePrefix.plate, Materials.Rubber), 'R', MetaItems.ELECTRIC_MOTOR_LV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin));
        ModHandler.addShapedRecipe("conveyor_module_mv", MetaItems.CONVEYOR_MODULE_MV.getStackForm(), "WWW", "RCR", "WWW", 'W', new UnificationEntry(OrePrefix.plate, Materials.Rubber), 'R', MetaItems.ELECTRIC_MOTOR_MV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper));
        ModHandler.addShapedRecipe("conveyor_module_hv", MetaItems.CONVEYOR_MODULE_HV.getStackForm(), "WWW", "RCR", "WWW", 'W', new UnificationEntry(OrePrefix.plate, Materials.Rubber), 'R', MetaItems.ELECTRIC_MOTOR_HV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold));
        ModHandler.addShapedRecipe("conveyor_module_ev", MetaItems.CONVEYOR_MODULE_EV.getStackForm(), "WWW", "RCR", "WWW", 'W', new UnificationEntry(OrePrefix.plate, Materials.Rubber), 'R', MetaItems.ELECTRIC_MOTOR_EV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium));
        ModHandler.addShapedRecipe("conveyor_module_iv", MetaItems.CONVEYOR_MODULE_IV.getStackForm(), "WWW", "RCR", "WWW", 'W', new UnificationEntry(OrePrefix.plate, Materials.Rubber), 'R', MetaItems.ELECTRIC_MOTOR_IV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten));

        ModHandler.addShapedRecipe("electric_pump_lv", MetaItems.ELECTRIC_PUMP_LV.getStackForm(), "TXO", "dPw", "ORC", 'T', new UnificationEntry(OrePrefix.screw, Materials.Tin), 'X', new UnificationEntry(OrePrefix.rotor, Materials.Tin), 'O', new UnificationEntry(OrePrefix.ring, Materials.Paper), 'P', MetaItems.SMALL_BRONZE_PIPE, 'R', MetaItems.ELECTRIC_MOTOR_LV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin));
        ModHandler.addShapedRecipe("electric_pump_mv", MetaItems.ELECTRIC_PUMP_MV.getStackForm(), "TXO", "dPw", "ORC", 'T', new UnificationEntry(OrePrefix.screw, Materials.Bronze), 'X', new UnificationEntry(OrePrefix.rotor, Materials.Bronze), 'O', new UnificationEntry(OrePrefix.ring, Materials.Paper), 'P', MetaItems.SMALL_BRONZE_PIPE, 'R', MetaItems.ELECTRIC_MOTOR_MV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper));
        ModHandler.addShapedRecipe("electric_pump_hv", MetaItems.ELECTRIC_PUMP_HV.getStackForm(), "TXO", "dPw", "ORC", 'T', new UnificationEntry(OrePrefix.screw, Materials.Steel), 'X', new UnificationEntry(OrePrefix.rotor, Materials.Steel), 'O', new UnificationEntry(OrePrefix.ring, Materials.Paper), 'P', MetaItems.SMALL_BRONZE_PIPE, 'R', MetaItems.ELECTRIC_MOTOR_HV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold));
        ModHandler.addShapedRecipe("electric_pump_ev", MetaItems.ELECTRIC_PUMP_EV.getStackForm(), "TXO", "dPw", "ORC", 'T', new UnificationEntry(OrePrefix.screw, Materials.StainlessSteel), 'X', new UnificationEntry(OrePrefix.rotor, Materials.StainlessSteel), 'O', new UnificationEntry(OrePrefix.ring, Materials.Paper), 'P', MetaItems.SMALL_BRONZE_PIPE, 'R', MetaItems.ELECTRIC_MOTOR_EV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium));
        ModHandler.addShapedRecipe("electric_pump_iv", MetaItems.ELECTRIC_PUMP_IV.getStackForm(), "TXO", "dPw", "ORC", 'T', new UnificationEntry(OrePrefix.screw, Materials.TungstenSteel), 'X', new UnificationEntry(OrePrefix.rotor, Materials.TungstenSteel), 'O', new UnificationEntry(OrePrefix.ring, Materials.Paper), 'P', MetaItems.SMALL_BRONZE_PIPE, 'R', MetaItems.ELECTRIC_MOTOR_IV, 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten));

        ModHandler.addShapedRecipe("electric_motor_lv_iron", MetaItems.ELECTRIC_MOTOR_LV.getStackForm(), "CXS", "XPX", "SXC", 'S', new UnificationEntry(OrePrefix.stick, Materials.Steel), 'X', new UnificationEntry(OrePrefix.wireGtSingle, Materials.Copper), 'P', new UnificationEntry(OrePrefix.stick, Materials.SteelMagnetic), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin));
        ModHandler.addShapedRecipe("electric_motor_lv_steel", MetaItems.ELECTRIC_MOTOR_LV.getStackForm(), "CXS", "XPX", "SXC", 'S', new UnificationEntry(OrePrefix.stick, Materials.Iron), 'X', new UnificationEntry(OrePrefix.wireGtSingle, Materials.Copper), 'P', new UnificationEntry(OrePrefix.stick, Materials.IronMagnetic), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin));
        ModHandler.addShapedRecipe("electric_motor_mv", MetaItems.ELECTRIC_MOTOR_MV.getStackForm(), "CXS", "XPX", "SXC", 'S', new UnificationEntry(OrePrefix.stick, Materials.Aluminium), 'X', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Copper), 'P', new UnificationEntry(OrePrefix.stick, Materials.SteelMagnetic), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper));
        ModHandler.addShapedRecipe("electric_motor_hv", MetaItems.ELECTRIC_MOTOR_HV.getStackForm(), "CXS", "XPX", "SXC", 'S', new UnificationEntry(OrePrefix.stick, Materials.StainlessSteel), 'X', new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Copper), 'P', new UnificationEntry(OrePrefix.stick, Materials.SteelMagnetic), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold));
        ModHandler.addShapedRecipe("electric_motor_ev", MetaItems.ELECTRIC_MOTOR_EV.getStackForm(), "CXS", "XPX", "SXC", 'S', new UnificationEntry(OrePrefix.stick, Materials.Titanium), 'X', new UnificationEntry(OrePrefix.wireGtOctal, Materials.AnnealedCopper), 'P', new UnificationEntry(OrePrefix.stick, Materials.NeodymiumMagnetic), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium));
        ModHandler.addShapedRecipe("electric_motor_iv", MetaItems.ELECTRIC_MOTOR_IV.getStackForm(), "CXS", "XPX", "SXC", 'S', new UnificationEntry(OrePrefix.stick, Materials.TungstenSteel), 'X', new UnificationEntry(OrePrefix.wireGtHex, Materials.AnnealedCopper), 'P', new UnificationEntry(OrePrefix.stick, Materials.NeodymiumMagnetic), 'C', new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten));

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

            ModHandler.addShapedRecipe("iron_trapdor", new ItemStack(Blocks.IRON_TRAPDOOR), "XX ", "XXh", 'X', new UnificationEntry(OrePrefix.plate, Materials.Iron));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_trapdor"));

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

        ModHandler.addShapelessRecipe("dust_electrum", OreDictUnifier.get(OrePrefix.dust, Materials.Electrum, 2), new UnificationEntry(OrePrefix.dust, Materials.Silver), new UnificationEntry(OrePrefix.dust, Materials.Gold));
        ModHandler.addShapelessRecipe("dust_brass", OreDictUnifier.get(OrePrefix.dust, Materials.Brass, 4), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Zinc));
        ModHandler.addShapelessRecipe("dust_brass_tetra", OreDictUnifier.get(OrePrefix.dust, Materials.Brass, 3), new UnificationEntry(OrePrefix.dust, Materials.Tetrahedrite), new UnificationEntry(OrePrefix.dust, Materials.Tetrahedrite), new UnificationEntry(OrePrefix.dust, Materials.Tetrahedrite), new UnificationEntry(OrePrefix.dust, Materials.Zinc));
        ModHandler.addShapelessRecipe("dust_bronze", OreDictUnifier.get(OrePrefix.dust, Materials.Bronze, 4), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Tin));
        ModHandler.addShapelessRecipe("dust_bronze_tetra", OreDictUnifier.get(OrePrefix.dust, Materials.Bronze, 3), new UnificationEntry(OrePrefix.dust, Materials.Tetrahedrite), new UnificationEntry(OrePrefix.dust, Materials.Tetrahedrite), new UnificationEntry(OrePrefix.dust, Materials.Tetrahedrite), new UnificationEntry(OrePrefix.dust, Materials.Tin));
        ModHandler.addShapelessRecipe("dust_invar", OreDictUnifier.get(OrePrefix.dust, Materials.Invar, 3), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Nickel));
        ModHandler.addShapelessRecipe("dust_cupronickel", OreDictUnifier.get(OrePrefix.dust, Materials.Cupronickel, 2), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.Copper));
        ModHandler.addShapelessRecipe("dust_nichrome", OreDictUnifier.get(OrePrefix.dust, Materials.Nichrome, 5), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.Nickel), new UnificationEntry(OrePrefix.dust, Materials.Chrome));

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
        ModHandler.addShapelessRecipe("dust_yttrium_barium_cuprate", OreDictUnifier.get(OrePrefix.dust, Materials.YttriumBariumCuprate, 6), new UnificationEntry(OrePrefix.dust, Materials.Yttrium), new UnificationEntry(OrePrefix.dust, Materials.Barium), new UnificationEntry(OrePrefix.dust, Materials.Barium), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper), new UnificationEntry(OrePrefix.dust, Materials.Copper));
        ModHandler.addShapelessRecipe("dust_kanthal", OreDictUnifier.get(OrePrefix.dust, Materials.Kanthal, 3), new UnificationEntry(OrePrefix.dust, Materials.Iron), new UnificationEntry(OrePrefix.dust, Materials.Aluminium), new UnificationEntry(OrePrefix.dust, Materials.Chrome));

        ModHandler.addShapelessRecipe("dust_ultimet", OreDictUnifier.get(OrePrefix.dust, Materials.Ultimet, 1), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt), new UnificationEntry(OrePrefix.dustTiny, Materials.Chrome), new UnificationEntry(OrePrefix.dustTiny, Materials.Chrome), new UnificationEntry(OrePrefix.dustTiny, Materials.Nickel), new UnificationEntry(OrePrefix.dustTiny, Materials.Molybdenum));
        ModHandler.addShapelessRecipe("dust_cobalt_brass", OreDictUnifier.get(OrePrefix.dust, Materials.CobaltBrass, 1), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Brass), new UnificationEntry(OrePrefix.dustTiny, Materials.Aluminium), new UnificationEntry(OrePrefix.dustTiny, Materials.Cobalt));
        ModHandler.addShapelessRecipe("dust_stainless_steel", OreDictUnifier.get(OrePrefix.dust, Materials.StainlessSteel, 1), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Nickel), new UnificationEntry(OrePrefix.dustTiny, Materials.Manganese), new UnificationEntry(OrePrefix.dustTiny, Materials.Chrome));
        ModHandler.addShapelessRecipe("dust_tiny_yttrium_barium_cuprate", OreDictUnifier.get(OrePrefix.dustTiny, Materials.YttriumBariumCuprate, 6), new UnificationEntry(OrePrefix.dustTiny, Materials.Yttrium), new UnificationEntry(OrePrefix.dustTiny, Materials.Barium), new UnificationEntry(OrePrefix.dustTiny, Materials.Barium), new UnificationEntry(OrePrefix.dustTiny, Materials.Copper), new UnificationEntry(OrePrefix.dustTiny, Materials.Copper), new UnificationEntry(OrePrefix.dustTiny, Materials.Copper));
        ModHandler.addShapelessRecipe("dust_tiny_kanthal", OreDictUnifier.get(OrePrefix.dustTiny, Materials.Kanthal, 3), new UnificationEntry(OrePrefix.dustTiny, Materials.Iron), new UnificationEntry(OrePrefix.dustTiny, Materials.Aluminium), new UnificationEntry(OrePrefix.dustTiny, Materials.Chrome));

        ModHandler.addShapelessRecipe("dust_vanadium_steel", OreDictUnifier.get(OrePrefix.dust, Materials.VanadiumSteel, 9), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Steel), new UnificationEntry(OrePrefix.dust, Materials.Vanadium), new UnificationEntry(OrePrefix.dust, Materials.Chrome));
        ModHandler.addShapelessRecipe("dust_hssg", OreDictUnifier.get(OrePrefix.dust, Materials.HSSG, 9), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.TungstenSteel), new UnificationEntry(OrePrefix.dust, Materials.Chrome), new UnificationEntry(OrePrefix.dust, Materials.Molybdenum), new UnificationEntry(OrePrefix.dust, Materials.Molybdenum), new UnificationEntry(OrePrefix.dust, Materials.Vanadium));
        ModHandler.addShapelessRecipe("dust_hsse", OreDictUnifier.get(OrePrefix.dust, Materials.HSSE, 9), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.Cobalt), new UnificationEntry(OrePrefix.dust, Materials.Manganese), new UnificationEntry(OrePrefix.dust, Materials.Silicon));
        ModHandler.addShapelessRecipe("dust_hsss", OreDictUnifier.get(OrePrefix.dust, Materials.HSSS, 9), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.HSSG), new UnificationEntry(OrePrefix.dust, Materials.Iridium), new UnificationEntry(OrePrefix.dust, Materials.Iridium), new UnificationEntry(OrePrefix.dust, Materials.Osmium));

        ModHandler.addShapelessRecipe("powder_coal", new ItemStack(Items.GUNPOWDER, 6), new UnificationEntry(OrePrefix.dust, Materials.Coal), new UnificationEntry(OrePrefix.dust, Materials.Coal), new UnificationEntry(OrePrefix.dust, Materials.Coal), new UnificationEntry(OrePrefix.dust, Materials.Sulfur), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter));
        ModHandler.addShapelessRecipe("powder_charcoal", new ItemStack(Items.GUNPOWDER, 6), new UnificationEntry(OrePrefix.dust, Materials.Charcoal), new UnificationEntry(OrePrefix.dust, Materials.Charcoal), new UnificationEntry(OrePrefix.dust, Materials.Charcoal), new UnificationEntry(OrePrefix.dust, Materials.Sulfur), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter));
        ModHandler.addShapelessRecipe("powder_carbon", new ItemStack(Items.GUNPOWDER, 6), new UnificationEntry(OrePrefix.dust, Materials.Carbon), new UnificationEntry(OrePrefix.dust, Materials.Carbon), new UnificationEntry(OrePrefix.dust, Materials.Carbon), new UnificationEntry(OrePrefix.dust, Materials.Sulfur), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter), new UnificationEntry(OrePrefix.dust, Materials.Saltpeter));

        if (ConfigHolder.vanillaRecipes.nerfPaperCrafting) {
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:paper"));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:sugar"));
            ModHandler.addShapedRecipe("paper_dust", OreDictUnifier.get(OrePrefix.dust, Materials.Paper, 2), "SSS", " m ", 'S', new ItemStack(Items.REEDS));
            ModHandler.addShapedRecipe("sugar", OreDictUnifier.get(OrePrefix.dust, Materials.Sugar, 1), "Sm ", 'S', new ItemStack(Items.REEDS));
            ModHandler.addShapedRecipe("paper", OreDictUnifier.get(OrePrefix.plate, Materials.Paper, 2), " C ", "SSS", " C ", 'S', OreDictUnifier.get(OrePrefix.dust, Materials.Paper, 1), 'C', new ItemStack(Blocks.STONE_SLAB));
        }

        if (ConfigHolder.vanillaRecipes.flintAndSteelRequireSteel) {
            ModHandler.addShapedRecipe("flint_and_steel", new ItemStack(Items.FLINT_AND_STEEL), "S ", " F", 'F', new ItemStack(Items.FLINT, 1), 'S', new UnificationEntry(OrePrefix.nugget, Materials.Steel));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:flint_and_steel"));
        }

    }

}
