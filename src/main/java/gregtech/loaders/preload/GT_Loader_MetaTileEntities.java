package gregtech.loaders.preload;

import codechicken.nei.api.API;
import cpw.mods.fml.common.Loader;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.metatileentity.implementations.*;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.common.tileentities.boilers.*;
import gregtech.common.tileentities.generators.GT_MetaTileEntity_LightningRod;
import gregtech.common.tileentities.machines.GT_MetaTileEntity_Hatch_OutputBus_ME;
import gregtech.common.tileentities.machines.basic.*;
import gregtech.common.tileentities.machines.long_distance.GT_MetaTileEntity_LongDistancePipelineFluid;
import gregtech.common.tileentities.machines.long_distance.GT_MetaTileEntity_LongDistancePipelineItem;
import gregtech.common.tileentities.machines.multi.*;
import gregtech.common.tileentities.machines.steam.*;
import gregtech.common.tileentities.storage.GT_MetaTileEntity_Locker;
import gregtech.common.tileentities.storage.GT_MetaTileEntity_QuantumTank;
import gregtech.common.tileentities.storage.GT_MetaTileEntity_SuperTank;
import gregtech.loaders.postload.GT_ProcessingArrayRecipeLoader;
import gregtech.loaders.preload.refactored.*;
import ic2.core.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtech.api.enums.ItemList.*;
import static gregtech.api.enums.Materials.*;
import static gregtech.api.enums.OrePrefixes.*;
import static gregtech.api.util.GT_ModHandler.addCraftingRecipe;

public class GT_Loader_MetaTileEntities implements Runnable {
    //TODO CHECK CIRCUIT RECIPES AND USAGES
    public static final long RECIPE_MASK = GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE | GT_ModHandler.RecipeBits.BUFFERED;
    public static final long DISMANTLEABLE_RECIPE_MASK = GT_ModHandler.RecipeBits.DISMANTLEABLE | RECIPE_MASK; //TODO: Remove Any Dismantleable stuff

    private static void run1() {
        CasingRecipeLoader.load();
        HullLoader.load();

        ItemList.Machine_Bricked_BlastFurnace.set(new GT_MetaTileEntity_BrickedBlastFurnace(140, "multimachine.brickedblastfurnace", "Bricked Blast Furnace").getStackForm(1L));
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Bricked_BlastFurnace.get(1L), GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"BFB", "FwF", "BFB", 'B', ItemList.Casing_Firebricks, 'F', OreDictNames.craftingIronFurnace});

        ItemList.Transformer_LV_ULV.set(new GT_MetaTileEntity_Transformer(20, "transformer.tier.00", "ULV Transformer", 0, "LV -> ULV (Use Soft Mallet to invert)").getStackForm(1L));
        ItemList.Transformer_MV_LV.set(new GT_MetaTileEntity_Transformer(21, "transformer.tier.01", "LV Transformer", 1, "MV -> LV (Use Soft Mallet to invert)").getStackForm(1L));
        ItemList.Transformer_HV_MV.set(new GT_MetaTileEntity_Transformer(22, "transformer.tier.02", "MV Transformer", 2, "HV -> MV (Use Soft Mallet to invert)").getStackForm(1L));
        ItemList.Transformer_EV_HV.set(new GT_MetaTileEntity_Transformer(23, "transformer.tier.03", "HV Transformer", 3, "EV -> HV (Use Soft Mallet to invert)").getStackForm(1L));
        ItemList.Transformer_IV_EV.set(new GT_MetaTileEntity_Transformer(24, "transformer.tier.04", "EV Transformer", 4, "IV -> EV (Use Soft Mallet to invert)").getStackForm(1L));
        ItemList.Transformer_LuV_IV.set(new GT_MetaTileEntity_Transformer(25, "transformer.tier.05", "IV Transformer", 5, "LuV -> IV (Use Soft Mallet to invert)").getStackForm(1L));
        ItemList.Transformer_ZPM_LuV.set(new GT_MetaTileEntity_Transformer(26, "transformer.tier.06", "LV Transformer", 6, "ZPM -> LuV (Use Soft Mallet to invert)").getStackForm(1L));
        ItemList.Transformer_UV_ZPM.set(new GT_MetaTileEntity_Transformer(27, "transformer.tier.07", "ZPM Transformer", 7, "UV -> ZPM (Use Soft Mallet to invert)").getStackForm(1L));
        ItemList.Transformer_MAX_UV.set(new GT_MetaTileEntity_Transformer(28, "transformer.tier.08", "UV Transformer", 8, "UHV -> UV (Use Soft Mallet to invert)").getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Transformer_LV_ULV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{" BB", "CM ", " BB", 'M', ItemList.Hull_ULV, 'C', OrePrefixes.cableGt01.get(Materials.Tin), 'B', OrePrefixes.cableGt01.get(Materials.RedAlloy)});
        GT_ModHandler.addCraftingRecipe(ItemList.Transformer_MV_LV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{" BB", "CM ", " BB", 'M', ItemList.Hull_LV, 'C', OrePrefixes.cableGt01.get(Materials.AnyCopper), 'B', OrePrefixes.cableGt01.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Transformer_HV_MV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"KBB", "CM ", "KBB", 'M', ItemList.Hull_MV, 'C', OrePrefixes.cableGt01.get(Materials.Gold), 'B', OrePrefixes.cableGt01.get(Materials.AnyCopper), 'K', ItemList.Circuit_Parts_Coil});
        GT_ModHandler.addCraftingRecipe(ItemList.Transformer_EV_HV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"KBB", "CM ", "KBB", 'M', ItemList.Hull_HV, 'C', OrePrefixes.cableGt01.get(Materials.Aluminium), 'B', OrePrefixes.cableGt01.get(Materials.Gold), 'K', ItemList.Circuit_Chip_ULPIC});
        GT_ModHandler.addCraftingRecipe(ItemList.Transformer_IV_EV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"KBB", "CM ", "KBB", 'M', ItemList.Hull_EV, 'C', OrePrefixes.cableGt01.get(Materials.Tungsten), 'B', OrePrefixes.cableGt01.get(Materials.Aluminium), 'K', ItemList.Circuit_Chip_LPIC});
        GT_ModHandler.addCraftingRecipe(ItemList.Transformer_LuV_IV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"KBB", "CM ", "KBB", 'M', ItemList.Hull_IV, 'C', OrePrefixes.cableGt01.get(Materials.VanadiumGallium), 'B', OrePrefixes.cableGt01.get(Materials.Tungsten), 'K', ItemList.Circuit_Chip_PIC});
        GT_ModHandler.addCraftingRecipe(ItemList.Transformer_ZPM_LuV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"KBB", "CM ", "KBB", 'M', ItemList.Hull_LuV, 'C', OrePrefixes.cableGt01.get(Materials.Naquadah), 'B', OrePrefixes.cableGt01.get(Materials.VanadiumGallium), 'K', ItemList.Circuit_Chip_HPIC});
        GT_ModHandler.addCraftingRecipe(ItemList.Transformer_UV_ZPM.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"KBB", "CM ", "KBB", 'M', ItemList.Hull_ZPM, 'C', OrePrefixes.cableGt01.get(Materials.NaquadahAlloy), 'B', OrePrefixes.cableGt01.get(Materials.Naquadah), 'K', ItemList.Circuit_Chip_UHPIC});
        GT_ModHandler.addCraftingRecipe(ItemList.Transformer_MAX_UV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"KBB", "CM ", "KBB", 'M', ItemList.Hull_UV, 'C', OrePrefixes.wireGt01.get(Materials.Bedrockium), 'B', OrePrefixes.cableGt01.get(Materials.NaquadahAlloy), 'K', ItemList.Circuit_Chip_NPIC});

        ItemList.Hatch_Dynamo_ULV.set(new GT_MetaTileEntity_Hatch_Dynamo(30, "hatch.dynamo.tier.00", "ULV Energy Output Hatch", 0).getStackForm(1L));
        ItemList.Hatch_Dynamo_LV.set(new GT_MetaTileEntity_Hatch_Dynamo(31, "hatch.dynamo.tier.01", "LV Energy Output Hatch", 1).getStackForm(1L));
        ItemList.Hatch_Dynamo_MV.set(new GT_MetaTileEntity_Hatch_Dynamo(32, "hatch.dynamo.tier.02", "MV Energy Output Hatch", 2).getStackForm(1L));
        ItemList.Hatch_Dynamo_HV.set(new GT_MetaTileEntity_Hatch_Dynamo(33, "hatch.dynamo.tier.03", "HV Energy Output Hatch", 3).getStackForm(1L));
        ItemList.Hatch_Dynamo_EV.set(new GT_MetaTileEntity_Hatch_Dynamo(34, "hatch.dynamo.tier.04", "EV Energy Output Hatch", 4).getStackForm(1L));
        ItemList.Hatch_Dynamo_IV.set(new GT_MetaTileEntity_Hatch_Dynamo(35, "hatch.dynamo.tier.05", "IV Energy Output Hatch", 5).getStackForm(1L));
        ItemList.Hatch_Dynamo_LuV.set(new GT_MetaTileEntity_Hatch_Dynamo(36, "hatch.dynamo.tier.06", "LuV Energy Output Hatch", 6).getStackForm(1L));
        ItemList.Hatch_Dynamo_ZPM.set(new GT_MetaTileEntity_Hatch_Dynamo(37, "hatch.dynamo.tier.07", "ZPM Energy Output Hatch", 7).getStackForm(1L));
        ItemList.Hatch_Dynamo_UV.set(new GT_MetaTileEntity_Hatch_Dynamo(38, "hatch.dynamo.tier.08", "UV Energy Output Hatch", 8).getStackForm(1L));
        ItemList.Hatch_Dynamo_MAX.set(new GT_MetaTileEntity_Hatch_Dynamo(39, "hatch.dynamo.tier.09", "UHV Energy Output Hatch", 9).getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_Dynamo_ULV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"XOL", "SMP", "XOL", 'M', ItemList.Hull_ULV, 'S', OrePrefixes.spring.get(Materials.Tin), 'X', OrePrefixes.circuit.get(Materials.Primitive), 'O', ItemList.ULV_Coil, 'L', OrePrefixes.cell.get(Materials.Lubricant), 'P', OrePrefixes.rotor.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_Dynamo_LV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"XOL", "SMP", "XOL", 'M', ItemList.Hull_LV, 'S', OrePrefixes.spring.get(Materials.Tin), 'X', OrePrefixes.circuit.get(Materials.Basic), 'O', ItemList.LV_Coil, 'L', OrePrefixes.cell.get(Materials.Lubricant), 'P', ItemList.Electric_Pump_LV});
        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_Dynamo_MV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"XOL", "SMP", "XOL", 'M', ItemList.Hull_MV, 'S', OrePrefixes.spring.get(Materials.Copper), 'X', ItemList.Circuit_Chip_ULPIC, 'O', ItemList.MV_Coil, 'L', OrePrefixes.cell.get(Materials.Lubricant), 'P', ItemList.Electric_Pump_MV});

        ItemList.Hatch_Energy_ULV.set(new GT_MetaTileEntity_Hatch_Energy(40, "hatch.energy.tier.00", "ULV Energy Input Hatch", 0).getStackForm(1L));
        ItemList.Hatch_Energy_LV.set(new GT_MetaTileEntity_Hatch_Energy(41, "hatch.energy.tier.01", "LV Energy Input Hatch", 1).getStackForm(1L));
        ItemList.Hatch_Energy_MV.set(new GT_MetaTileEntity_Hatch_Energy(42, "hatch.energy.tier.02", "MV Energy Input Hatch", 2).getStackForm(1L));
        ItemList.Hatch_Energy_HV.set(new GT_MetaTileEntity_Hatch_Energy(43, "hatch.energy.tier.03", "HV Energy Input Hatch", 3).getStackForm(1L));
        ItemList.Hatch_Energy_EV.set(new GT_MetaTileEntity_Hatch_Energy(44, "hatch.energy.tier.04", "EV Energy Input Hatch", 4).getStackForm(1L));
        ItemList.Hatch_Energy_IV.set(new GT_MetaTileEntity_Hatch_Energy(45, "hatch.energy.tier.05", "IV Energy Input Hatch", 5).getStackForm(1L));
        ItemList.Hatch_Energy_LuV.set(new GT_MetaTileEntity_Hatch_Energy(46, "hatch.energy.tier.06", "LuV Energy Input Hatch", 6).getStackForm(1L));
        ItemList.Hatch_Energy_ZPM.set(new GT_MetaTileEntity_Hatch_Energy(47, "hatch.energy.tier.07", "ZPM Energy Input Hatch", 7).getStackForm(1L));
        ItemList.Hatch_Energy_UV.set(new GT_MetaTileEntity_Hatch_Energy(48, "hatch.energy.tier.08", "UV Energy Input Hatch", 8).getStackForm(1L));
        ItemList.Hatch_Energy_MAX.set(new GT_MetaTileEntity_Hatch_Energy(49, "hatch.energy.tier.09", "UHV Energy Input Hatch", 9).getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_Energy_ULV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"COL", "XMP", "COL", 'M', ItemList.Hull_ULV, 'C', OrePrefixes.cableGt01.get(Materials.RedAlloy), 'X', OrePrefixes.circuit.get(Materials.Primitive), 'O', ItemList.ULV_Coil, 'L', OrePrefixes.cell.get(Materials.Lubricant), 'P', OrePrefixes.rotor.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_Energy_LV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"COL", "XMP", "COL", 'M', ItemList.Hull_LV, 'C', OrePrefixes.cableGt01.get(Materials.Tin), 'X', OrePrefixes.circuit.get(Materials.Basic), 'O', ItemList.LV_Coil, 'L', OrePrefixes.cell.get(Materials.Lubricant), 'P', ItemList.Electric_Pump_LV});
        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_Energy_MV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"XOL", "CMP", "XOL", 'M', ItemList.Hull_MV, 'C', OrePrefixes.cableGt01.get(Materials.Copper), 'X', ItemList.Circuit_Chip_ULPIC, 'O', ItemList.MV_Coil, 'L', OrePrefixes.cell.get(Materials.Lubricant), 'P', ItemList.Electric_Pump_MV});

        ItemList.Hatch_Input_ULV.set(new GT_MetaTileEntity_Hatch_Input(50, "hatch.input.tier.00", "ULV Fluid Input Hatch", 0).getStackForm(1L));
        ItemList.Hatch_Input_LV.set(new GT_MetaTileEntity_Hatch_Input(51, "hatch.input.tier.01", "LV Fluid Input Hatch", 1).getStackForm(1L));
        ItemList.Hatch_Input_MV.set(new GT_MetaTileEntity_Hatch_Input(52, "hatch.input.tier.02", "MV Fluid Input Hatch", 2).getStackForm(1L));
        ItemList.Hatch_Input_HV.set(new GT_MetaTileEntity_Hatch_Input(53, "hatch.input.tier.03", "HV Fluid Input Hatch", 3).getStackForm(1L));
        ItemList.Hatch_Input_EV.set(new GT_MetaTileEntity_Hatch_Input(54, "hatch.input.tier.04", "EV Fluid Input Hatch", 4).getStackForm(1L));
        ItemList.Hatch_Input_IV.set(new GT_MetaTileEntity_Hatch_Input(55, "hatch.input.tier.05", "IV Fluid Input Hatch", 5).getStackForm(1L));
        ItemList.Hatch_Input_LuV.set(new GT_MetaTileEntity_Hatch_Input(56, "hatch.input.tier.06", "LuV Fluid Input Hatch", 6).getStackForm(1L));
        ItemList.Hatch_Input_ZPM.set(new GT_MetaTileEntity_Hatch_Input(57, "hatch.input.tier.07", "ZPM Fluid Input Hatch", 7).getStackForm(1L));
        ItemList.Hatch_Input_UV.set(new GT_MetaTileEntity_Hatch_Input(58, "hatch.input.tier.08", "UV Fluid Input Hatch", 8).getStackForm(1L));
        ItemList.Hatch_Input_MAX.set(new GT_MetaTileEntity_Hatch_Input(59, "hatch.input.tier.09", "UHV Fluid Input Hatch", 9).getStackForm(1L));

        ItemList.Hatch_Output_ULV.set(new GT_MetaTileEntity_Hatch_Output(60, "hatch.output.tier.00", "ULV Fluid Output Hatch", 0).getStackForm(1L));
        ItemList.Hatch_Output_LV.set(new GT_MetaTileEntity_Hatch_Output(61, "hatch.output.tier.01", "LV Fluid Output Hatch", 1).getStackForm(1L));
        ItemList.Hatch_Output_MV.set(new GT_MetaTileEntity_Hatch_Output(62, "hatch.output.tier.02", "MV Fluid Output Hatch", 2).getStackForm(1L));
        ItemList.Hatch_Output_HV.set(new GT_MetaTileEntity_Hatch_Output(63, "hatch.output.tier.03", "HV Fluid Output Hatch", 3).getStackForm(1L));
        ItemList.Hatch_Output_EV.set(new GT_MetaTileEntity_Hatch_Output(64, "hatch.output.tier.04", "EV Fluid Output Hatch", 4).getStackForm(1L));
        ItemList.Hatch_Output_IV.set(new GT_MetaTileEntity_Hatch_Output(65, "hatch.output.tier.05", "IV Fluid Output Hatch", 5).getStackForm(1L));
        ItemList.Hatch_Output_LuV.set(new GT_MetaTileEntity_Hatch_Output(66, "hatch.output.tier.06", "LuV Fluid Output Hatch", 6).getStackForm(1L));
        ItemList.Hatch_Output_ZPM.set(new GT_MetaTileEntity_Hatch_Output(67, "hatch.output.tier.07", "ZPM Fluid Output Hatch", 7).getStackForm(1L));
        ItemList.Hatch_Output_UV.set(new GT_MetaTileEntity_Hatch_Output(68, "hatch.output.tier.08", "UV Fluid Output Hatch", 8).getStackForm(1L));
        ItemList.Hatch_Output_MAX.set(new GT_MetaTileEntity_Hatch_Output(69, "hatch.output.tier.09", "UHV Fluid Output Hatch", 9).getStackForm(1L));

        ItemList.Quantum_Tank_LV.set(new GT_MetaTileEntity_QuantumTank(120, "quantum.tank.tier.06", "Quantum Tank I", 6).getStackForm(1L));
        ItemList.Quantum_Tank_MV.set(new GT_MetaTileEntity_QuantumTank(121, "quantum.tank.tier.07", "Quantum Tank II", 7).getStackForm(1L));
        ItemList.Quantum_Tank_HV.set(new GT_MetaTileEntity_QuantumTank(122, "quantum.tank.tier.08", "Quantum Tank III", 8).getStackForm(1L));
        ItemList.Quantum_Tank_EV.set(new GT_MetaTileEntity_QuantumTank(123, "quantum.tank.tier.09", "Quantum Tank IV", 9).getStackForm(1L));
        ItemList.Quantum_Tank_IV.set(new GT_MetaTileEntity_QuantumTank(124, "quantum.tank.tier.10", "Quantum Tank V", 10).getStackForm(1L));

        ItemList.Super_Tank_LV.set(new GT_MetaTileEntity_SuperTank(130, "super.tank.tier.01", "Super Tank I", 1).getStackForm(1L));
        ItemList.Super_Tank_MV.set(new GT_MetaTileEntity_SuperTank(131, "super.tank.tier.02", "Super Tank II", 2).getStackForm(1L));
        ItemList.Super_Tank_HV.set(new GT_MetaTileEntity_SuperTank(132, "super.tank.tier.03", "Super Tank III", 3).getStackForm(1L));
        ItemList.Super_Tank_EV.set(new GT_MetaTileEntity_SuperTank(133, "super.tank.tier.04", "Super Tank IV", 4).getStackForm(1L));
        ItemList.Super_Tank_IV.set(new GT_MetaTileEntity_SuperTank(134, "super.tank.tier.05", "Super Tank V", 5).getStackForm(1L));

        ItemList.Long_Distance_Pipeline_Fluid.set(new GT_MetaTileEntity_LongDistancePipelineFluid(2700, "long.distance.pipeline.fluid", "Long Distance Fluid Pipeline", 1).getStackForm(1L));
        ItemList.Long_Distance_Pipeline_Item.set(new GT_MetaTileEntity_LongDistancePipelineItem(2701, "long.distance.pipeline.item", "Long Distance Item Pipeline", 1).getStackForm(1L));

        ItemList.Hatch_Output_Bus_ME.set(new GT_MetaTileEntity_Hatch_OutputBus_ME(2710, "hatch.output_bus.me", "ME Output Interface").getStackForm(1L));

        ItemList.Hatch_Input_Bus_ULV.set(new GT_MetaTileEntity_Hatch_InputBus(70, "hatch.input_bus.tier.00", "ULV Item Input Hatch", 0).getStackForm(1L));
        ItemList.Hatch_Input_Bus_LV.set(new GT_MetaTileEntity_Hatch_InputBus(71, "hatch.input_bus.tier.01", "LV Item Input Hatch", 1).getStackForm(1L));
        ItemList.Hatch_Input_Bus_MV.set(new GT_MetaTileEntity_Hatch_InputBus(72, "hatch.input_bus.tier.02", "MV Item Input Hatch", 2).getStackForm(1L));
        ItemList.Hatch_Input_Bus_HV.set(new GT_MetaTileEntity_Hatch_InputBus(73, "hatch.input_bus.tier.03", "HV Item Input Hatch)", 3).getStackForm(1L));
        ItemList.Hatch_Input_Bus_EV.set(new GT_MetaTileEntity_Hatch_InputBus(74, "hatch.input_bus.tier.04", "EV Item Input Hatch", 4).getStackForm(1L));
        ItemList.Hatch_Input_Bus_IV.set(new GT_MetaTileEntity_Hatch_InputBus(75, "hatch.input_bus.tier.05", "IV Item Input Hatch", 5).getStackForm(1L));
        ItemList.Hatch_Input_Bus_LuV.set(new GT_MetaTileEntity_Hatch_InputBus(76, "hatch.input_bus.tier.06", "LuV Item Input Hatch", 6).getStackForm(1L));
        ItemList.Hatch_Input_Bus_ZPM.set(new GT_MetaTileEntity_Hatch_InputBus(77, "hatch.input_bus.tier.07", "ZPM Item Input Hatch", 7).getStackForm(1L));
        ItemList.Hatch_Input_Bus_UV.set(new GT_MetaTileEntity_Hatch_InputBus(78, "hatch.input_bus.tier.08", "UV Item Input Hatch", 8).getStackForm(1L));
        ItemList.Hatch_Input_Bus_MAX.set(new GT_MetaTileEntity_Hatch_InputBus(79, "hatch.input_bus.tier.09", "UHV Item Input Hatch", 9).getStackForm(1L));

        ItemList.Hatch_Output_Bus_ULV.set(new GT_MetaTileEntity_Hatch_OutputBus(80, "hatch.output_bus.tier.00", "ULV Item Output Hatch", 0).getStackForm(1L));
        ItemList.Hatch_Output_Bus_LV.set(new GT_MetaTileEntity_Hatch_OutputBus(81, "hatch.output_bus.tier.01", "LV Item Output Hatch", 1).getStackForm(1L));
        ItemList.Hatch_Output_Bus_MV.set(new GT_MetaTileEntity_Hatch_OutputBus(82, "hatch.output_bus.tier.02", "MV Item Output Hatch", 2).getStackForm(1L));
        ItemList.Hatch_Output_Bus_HV.set(new GT_MetaTileEntity_Hatch_OutputBus(83, "hatch.output_bus.tier.03", "HV Item Output Hatch", 3).getStackForm(1L));
        ItemList.Hatch_Output_Bus_EV.set(new GT_MetaTileEntity_Hatch_OutputBus(84, "hatch.output_bus.tier.04", "EV Item Output Hatch", 4).getStackForm(1L));
        ItemList.Hatch_Output_Bus_IV.set(new GT_MetaTileEntity_Hatch_OutputBus(85, "hatch.output_bus.tier.05", "IV Item Output Hatch", 5).getStackForm(1L));
        ItemList.Hatch_Output_Bus_LuV.set(new GT_MetaTileEntity_Hatch_OutputBus(86, "hatch.output_bus.tier.06", "LuV Item Output Hatch", 6).getStackForm(1L));
        ItemList.Hatch_Output_Bus_ZPM.set(new GT_MetaTileEntity_Hatch_OutputBus(87, "hatch.output_bus.tier.07", "ZPM Item Output Hatch", 7).getStackForm(1L));
        ItemList.Hatch_Output_Bus_UV.set(new GT_MetaTileEntity_Hatch_OutputBus(88, "hatch.output_bus.tier.08", "UV Item Output Hatch", 8).getStackForm(1L));
        ItemList.Hatch_Output_Bus_MAX.set(new GT_MetaTileEntity_Hatch_OutputBus(89, "hatch.output_bus.tier.09", "UHV Item Output Hatch", 9).getStackForm(1L));

        ItemList.Hatch_Maintenance.set(new GT_MetaTileEntity_Hatch_Maintenance(90, "hatch.maintenance", "Manual Maintenance Hatch", 1).getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_Maintenance.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"dwx", "hMc", "fsr", 'M', ItemList.Hull_LV});
        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_Maintenance.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"dwx", "hMC", "fsr", 'M', ItemList.Hull_LV, 'C', GT_ModHandler.getModItem("Railcraft", "tool.crowbar", 1L, 0)});
        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_Maintenance.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"dwx", "hMC", "fsr", 'M', ItemList.Hull_LV, 'C', GT_ModHandler.getModItem("Railcraft", "tool.crowbar.reinforced", 1L, 0)});
        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_Maintenance.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"dwx", "hMC", "fsr", 'M', ItemList.Hull_LV, 'C', GT_ModHandler.getModItem("Railcraft", "tool.crowbar.magic", 1L, 0)});
        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_Maintenance.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"dwx", "hMC", "fsr", 'M', ItemList.Hull_LV, 'C', GT_ModHandler.getModItem("Railcraft", "tool.crowbar.void", 1L, 0)});

        ItemList.Hatch_AutoMaintenance.set(new GT_MetaTileEntity_Hatch_Maintenance(111, "hatch.maintenance.auto", "Auto Maintenance Hatch", 6, true).getStackForm(1L));
        ItemList.Hatch_DataAccess_EV.set(new GT_MetaTileEntity_Hatch_DataAccess(145, "hatch.dataaccess", "Data Access Hatch", 4).getStackForm(1L));
        ItemList.Hatch_DataAccess_LuV.set(new GT_MetaTileEntity_Hatch_DataAccess(146, "hatch.dataaccess.adv", "Advanced Data Access Hatch", 6).getStackForm(1L));
        ItemList.Hatch_DataAccess_UV.set(new GT_MetaTileEntity_Hatch_DataAccess(147, "hatch.dataaccess.auto", "Automatable Data Access Hatch", 8).getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_DataAccess_EV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"COC", "OMO", "COC", 'M', ItemList.Hull_EV, 'O', ItemList.Tool_DataStick, 'C', OrePrefixes.circuit.get(Materials.Elite)});
        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_DataAccess_LuV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"COC", "OMO", "COC", 'M', ItemList.Hull_LuV, 'O', ItemList.Tool_DataOrb, 'C', OrePrefixes.circuit.get(Materials.Ultimate)});
        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_DataAccess_UV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CRC", "OMO", "CRC", 'M', ItemList.Hull_UV, 'O', ItemList.Tool_DataOrb, 'C', OrePrefixes.circuit.get(Materials.Infinite), 'R', ItemList.Robot_Arm_UV});

        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_AutoMaintenance.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CHC", "AMA", "CHC", 'M', ItemList.Hull_LuV, 'H', ItemList.Hatch_Maintenance, 'A', ItemList.Robot_Arm_LuV, 'C', OrePrefixes.circuit.get(Materials.Ultimate)});

        ItemList.Hatch_Muffler_LV.set(new GT_MetaTileEntity_Hatch_Muffler(91, "hatch.muffler.tier.01", "LV Muffler Hatch", 1).getStackForm(1L));
        ItemList.Hatch_Muffler_MV.set(new GT_MetaTileEntity_Hatch_Muffler(92, "hatch.muffler.tier.02", "MV Muffler Hatch", 2).getStackForm(1L));
        ItemList.Hatch_Muffler_HV.set(new GT_MetaTileEntity_Hatch_Muffler(93, "hatch.muffler.tier.03", "HV Muffler Hatch", 3).getStackForm(1L));
        ItemList.Hatch_Muffler_EV.set(new GT_MetaTileEntity_Hatch_Muffler(94, "hatch.muffler.tier.04", "EV Muffler Hatch", 4).getStackForm(1L));
        ItemList.Hatch_Muffler_IV.set(new GT_MetaTileEntity_Hatch_Muffler(95, "hatch.muffler.tier.05", "IV Muffler Hatch", 5).getStackForm(1L));
        ItemList.Hatch_Muffler_LuV.set(new GT_MetaTileEntity_Hatch_Muffler(96, "hatch.muffler.tier.06", "LuV Muffler Hatch", 6).getStackForm(1L));
        ItemList.Hatch_Muffler_ZPM.set(new GT_MetaTileEntity_Hatch_Muffler(97, "hatch.muffler.tier.07", "ZPM Muffler Hatch", 7).getStackForm(1L));
        ItemList.Hatch_Muffler_UV.set(new GT_MetaTileEntity_Hatch_Muffler(98, "hatch.muffler.tier.08", "UV Muffler Hatch", 8).getStackForm(1L));
        ItemList.Hatch_Muffler_MAX.set(new GT_MetaTileEntity_Hatch_Muffler(99, "hatch.muffler.tier.09", "UHV Muffler Hatch", 9).getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_Muffler_LV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"MX ", "PR ", 'M', ItemList.Hull_LV, 'P', OrePrefixes.pipeMedium.get(Materials.Bronze), 'R', OrePrefixes.rotor.get(Materials.Bronze), 'X', ItemList.Electric_Motor_LV});
        GT_ModHandler.addCraftingRecipe(ItemList.Hatch_Muffler_MV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"MX ", "PR ", 'M', ItemList.Hull_MV, 'P', OrePrefixes.pipeMedium.get(Materials.Steel), 'R', OrePrefixes.rotor.get(Materials.Steel), 'X', ItemList.Electric_Motor_MV});

        ItemList.Machine_Bronze_Boiler.set(new GT_MetaTileEntity_Boiler_Bronze(100, "boiler.bronze", "Small Coal Boiler").getStackForm(1L));
        ItemList.Machine_Steel_Boiler.set(new GT_MetaTileEntity_Boiler_Steel(101, "boiler.steel", "High Pressure Coal Boiler").getStackForm(1L));
        ItemList.Machine_Steel_Boiler_Lava.set(new GT_MetaTileEntity_Boiler_Lava(102, "boiler.lava", "High Pressure Lava Boiler").getStackForm(1L));
        ItemList.Machine_Bronze_Boiler_Solar.set(new GT_MetaTileEntity_Boiler_Solar(105, "boiler.solar", "Simple Solar Boiler").getStackForm(1L));
        ItemList.Machine_HP_Solar.set(new GT_MetaTileEntity_Boiler_Solar_Steel(114, "boiler.steel.solar", "High Pressure Solar Boiler").getStackForm(1L));
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Bronze_Boiler.get(1L), RECIPE_MASK, new Object[]{"PPP", "PwP", "BFB", 'F', OreDictNames.craftingIronFurnace, 'P', OrePrefixes.plate.get(Materials.Bronze), 'B', new ItemStack(Blocks.brick_block, 1)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Steel_Boiler.get(1L), RECIPE_MASK, new Object[]{"PPP", "PwP", "BFB", 'F', OreDictNames.craftingIronFurnace, 'P', OrePrefixes.plate.get(Materials.Steel), 'B', new ItemStack(Blocks.brick_block, 1)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Steel_Boiler_Lava.get(1L), RECIPE_MASK, new Object[]{"PPP", "PTP", "PMP", 'M', ItemList.Hull_HP, 'P', OrePrefixes.plate.get(Materials.Steel), 'T', GT_ModHandler.getModItem("BuildCraft|Factory", "tankBlock", 1L, 0)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Bronze_Boiler_Solar.get(1L), RECIPE_MASK, new Object[]{"GGG", "SSS", "PMP", 'M', ItemList.Hull_Bronze_Bricks, 'P', OrePrefixes.pipeSmall.get(Materials.Bronze), 'S', OrePrefixes.plateDouble.get(Materials.Silver), 'G', new ItemStack(Blocks.glass, 1)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_HP_Solar.get(1L), RECIPE_MASK, new Object[]{"GGG", "SSS", "PMP", 'M', ItemList.Hull_HP_Bricks, 'P', OrePrefixes.pipeSmall.get(Materials.Steel), 'S', OrePrefixes.plateTriple.get(Materials.Silver), 'G', GT_ModHandler.getModItem("IC2", "blockAlloyGlass", 1L)});
        ItemList.Machine_Steel_Boiler_SemiFluid.set(new GT_MetaTileEntity_Boiler_Semi(9333, "boiler.semi", "High Pressure SemiFluid Boiler").getStackForm(1));

        ItemList.Machine_Bronze_BlastFurnace.set(new GT_MetaTileEntity_BronzeBlastFurnace(108, "bronzemachine.blastfurnace", "Bronze Plated Blast Furnace").getStackForm(1L));
        ItemList.Machine_Bronze_Furnace.set(new GT_MetaTileEntity_Furnace_Bronze(103, "bronzemachine.furnace", "Steam Furnace").getStackForm(1L));
        ItemList.Machine_HP_Furnace.set(new GT_MetaTileEntity_Furnace_Steel(104, "hpmachine.furnace", "High Pressure Furnace").getStackForm(1L));
        ItemList.Machine_Bronze_Macerator.set(new GT_MetaTileEntity_Macerator_Bronze(106, "bronzemachine.macerator", "Steam Macerator").getStackForm(1L));
        ItemList.Machine_HP_Macerator.set(new GT_MetaTileEntity_Macerator_Steel(107, "hpmachine.macerator", "High Pressure Macerator").getStackForm(1L));
        ItemList.Machine_Bronze_Extractor.set(new GT_MetaTileEntity_Extractor_Bronze(109, "bronzemachine.extractor", "Steam Extractor").getStackForm(1L));
        ItemList.Machine_HP_Extractor.set(new GT_MetaTileEntity_Extractor_Steel(110, "hpmachine.extractor", "High Pressure Extractor").getStackForm(1L));
        ItemList.Machine_Bronze_Hammer.set(new GT_MetaTileEntity_ForgeHammer_Bronze(112, "bronzemachine.hammer", "Steam Forge Hammer").getStackForm(1L));
        ItemList.Machine_HP_Hammer.set(new GT_MetaTileEntity_ForgeHammer_Steel(113, "hpmachine.hammer", "High Pressure Forge Hammer").getStackForm(1L));
        ItemList.Machine_Bronze_Compressor.set(new GT_MetaTileEntity_Compressor_Bronze(115, "bronzemachine.compressor", "Steam Compressor").getStackForm(1L));
        ItemList.Machine_HP_Compressor.set(new GT_MetaTileEntity_Compressor_Steel(116, "hpmachine.compressor", "High Pressure Compressor").getStackForm(1L));
        ItemList.Machine_Bronze_AlloySmelter.set(new GT_MetaTileEntity_AlloySmelter_Bronze(118, "bronzemachine.alloysmelter", "Steam Alloy Smelter").getStackForm(1L));
        ItemList.Machine_HP_AlloySmelter.set(new GT_MetaTileEntity_AlloySmelter_Steel(119, "hpmachine.alloysmelter", "High Pressure Alloy Smelter").getStackForm(1L));
        ItemList.Machine_HP_Sifter.set(new GT_MetaTileEntity_Sifter_Steel(9330, "hpmachine.sifter", "High Pressure Sifter", "Stay calm and keep sifting.").getStackForm(1L));
        ItemList.Machine_HP_Sifter.set(new GT_MetaTileEntity_Mixer_Steel(9331, "hpmachine.mixer", "High Pressure Mixer", "Will it Blend?").getStackForm(1L));
        ItemList.Machine_HP_Sifter.set(new GT_MetaTileEntity_Mixer_Bronze(9332, "bronzemachine.mixer", "Steam Mixer", "Will it Blend?").getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Bronze_Furnace.get(1L), RECIPE_MASK, new Object[]{"XXX", "XMX", "XFX", 'M', ItemList.Hull_Bronze_Bricks, 'X', OrePrefixes.pipeSmall.get(Materials.Bronze), 'F', OreDictNames.craftingFurnace});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_HP_Furnace.get(1L), RECIPE_MASK, new Object[]{"XSX", "PMP", "XXX", 'M', ItemList.Machine_Bronze_Furnace, 'X', OrePrefixes.pipeSmall.get(Materials.WroughtIron), 'P', OrePrefixes.plate.get(Materials.WroughtIron), 'S', OrePrefixes.plate.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Bronze_Macerator.get(1L), RECIPE_MASK, new Object[]{"DXD", "XMX", "PXP", 'M', ItemList.Hull_Bronze, 'X', OrePrefixes.pipeSmall.get(Materials.Bronze), 'P', OreDictNames.craftingPiston, 'D', OrePrefixes.gem.get(Materials.Diamond)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_HP_Macerator.get(1L), RECIPE_MASK, new Object[]{"PSP", "XMX", "PPP", 'M', ItemList.Machine_Bronze_Macerator, 'X', OrePrefixes.pipeSmall.get(Materials.WroughtIron), 'P', OrePrefixes.plate.get(Materials.WroughtIron), 'S', OrePrefixes.plate.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Bronze_Extractor.get(1L), RECIPE_MASK, new Object[]{"XXX", "PMG", "XXX", 'M', ItemList.Hull_Bronze, 'X', OrePrefixes.pipeSmall.get(Materials.Bronze), 'P', OreDictNames.craftingPiston, 'G', new ItemStack(Blocks.glass, 1)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_HP_Extractor.get(1L), RECIPE_MASK, new Object[]{"XSX", "PMP", "XXX", 'M', ItemList.Machine_Bronze_Extractor, 'X', OrePrefixes.pipeSmall.get(Materials.WroughtIron), 'P', OrePrefixes.plate.get(Materials.WroughtIron), 'S', OrePrefixes.plate.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Bronze_Hammer.get(1L), RECIPE_MASK, new Object[]{"XPX", "XMX", "XAX", 'M', ItemList.Hull_Bronze, 'X', OrePrefixes.pipeSmall.get(Materials.Bronze), 'P', OreDictNames.craftingPiston, 'A', OreDictNames.craftingAnvil});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_HP_Hammer.get(1L), RECIPE_MASK, new Object[]{"PSP", "XMX", "PPP", 'M', ItemList.Machine_Bronze_Hammer, 'X', OrePrefixes.pipeSmall.get(Materials.WroughtIron), 'P', OrePrefixes.plate.get(Materials.WroughtIron), 'S', OrePrefixes.plate.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Bronze_Compressor.get(1L), RECIPE_MASK, new Object[]{"XXX", "PMP", "XXX", 'M', ItemList.Hull_Bronze, 'X', OrePrefixes.pipeSmall.get(Materials.Bronze), 'P', OreDictNames.craftingPiston});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_HP_Compressor.get(1L), RECIPE_MASK, new Object[]{"XSX", "PMP", "XXX", 'M', ItemList.Machine_Bronze_Compressor, 'X', OrePrefixes.pipeSmall.get(Materials.WroughtIron), 'P', OrePrefixes.plate.get(Materials.WroughtIron), 'S', OrePrefixes.plate.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Bronze_AlloySmelter.get(1L), RECIPE_MASK, new Object[]{"XXX", "FMF", "XXX", 'M', ItemList.Hull_Bronze_Bricks, 'X', OrePrefixes.pipeSmall.get(Materials.Bronze), 'F', OreDictNames.craftingFurnace});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_HP_AlloySmelter.get(1L), RECIPE_MASK, new Object[]{"PSP", "PMP", "PXP", 'M', ItemList.Machine_Bronze_AlloySmelter, 'X', OrePrefixes.pipeSmall.get(Materials.WroughtIron), 'P', OrePrefixes.plate.get(Materials.WroughtIron), 'S', OrePrefixes.plate.get(Materials.Steel)});

        ItemList.Locker_ULV.set(new GT_MetaTileEntity_Locker(150, "locker.tier.00", "Locker I", 0).getStackForm(1L));
        ItemList.Locker_LV.set(new GT_MetaTileEntity_Locker(151, "locker.tier.01", "Locker II", 1).getStackForm(1L));
        ItemList.Locker_MV.set(new GT_MetaTileEntity_Locker(152, "locker.tier.02", "Locker III", 2).getStackForm(1L));
        ItemList.Locker_HV.set(new GT_MetaTileEntity_Locker(153, "locker.tier.03", "Locker IV", 3).getStackForm(1L));
        ItemList.Locker_EV.set(new GT_MetaTileEntity_Locker(154, "locker.tier.04", "Locker V", 4).getStackForm(1L));
        ItemList.Locker_IV.set(new GT_MetaTileEntity_Locker(155, "locker.tier.05", "Locker VI", 5).getStackForm(1L));
        ItemList.Locker_LuV.set(new GT_MetaTileEntity_Locker(156, "locker.tier.06", "Locker VII", 6).getStackForm(1L));
        ItemList.Locker_ZPM.set(new GT_MetaTileEntity_Locker(157, "locker.tier.07", "Locker VIII", 7).getStackForm(1L));
        ItemList.Locker_UV.set(new GT_MetaTileEntity_Locker(158, "locker.tier.08", "Locker IX", 8).getStackForm(1L));
        ItemList.Locker_MAX.set(new GT_MetaTileEntity_Locker(159, "locker.tier.09", "Locker X", 9).getStackForm(1L));

        ItemList.Battery_Buffer_1by1_ULV.set(new GT_MetaTileEntity_BasicBatteryBuffer(160, "batterybuffer.01.tier.00", "ULV Battery Buffer", 0, "", 1).getStackForm(1L));
        ItemList.Battery_Buffer_1by1_LV.set(new GT_MetaTileEntity_BasicBatteryBuffer(161, "batterybuffer.01.tier.01", "LV Battery Buffer", 1, "", 1).getStackForm(1L));
        ItemList.Battery_Buffer_1by1_MV.set(new GT_MetaTileEntity_BasicBatteryBuffer(162, "batterybuffer.01.tier.02", "MV Battery Buffer", 2, "", 1).getStackForm(1L));
        ItemList.Battery_Buffer_1by1_HV.set(new GT_MetaTileEntity_BasicBatteryBuffer(163, "batterybuffer.01.tier.03", "HV Battery Buffer", 3, "", 1).getStackForm(1L));
        ItemList.Battery_Buffer_1by1_EV.set(new GT_MetaTileEntity_BasicBatteryBuffer(164, "batterybuffer.01.tier.04", "EV Battery Buffer", 4, "", 1).getStackForm(1L));
        ItemList.Battery_Buffer_1by1_IV.set(new GT_MetaTileEntity_BasicBatteryBuffer(165, "batterybuffer.01.tier.05", "IV Battery Buffer", 5, "", 1).getStackForm(1L));
        ItemList.Battery_Buffer_1by1_LuV.set(new GT_MetaTileEntity_BasicBatteryBuffer(166, "batterybuffer.01.tier.06", "LuV Battery Buffer", 6, "", 1).getStackForm(1L));
        ItemList.Battery_Buffer_1by1_ZPM.set(new GT_MetaTileEntity_BasicBatteryBuffer(167, "batterybuffer.01.tier.07", "ZPM Battery Buffer", 7, "", 1).getStackForm(1L));
        ItemList.Battery_Buffer_1by1_UV.set(new GT_MetaTileEntity_BasicBatteryBuffer(168, "batterybuffer.01.tier.08", "UV Battery Buffer", 8, "", 1).getStackForm(1L));
        ItemList.Battery_Buffer_1by1_MAX.set(new GT_MetaTileEntity_BasicBatteryBuffer(169, "batterybuffer.01.tier.09", "UHV Battery Buffer", 9, "", 1).getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_1by1_ULV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_ULV, 'W', OrePrefixes.wireGt01.get(Materials.RedAlloy), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_1by1_LV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_LV, 'W', OrePrefixes.wireGt01.get(Materials.Tin), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_1by1_MV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_MV, 'W', OrePrefixes.wireGt01.get(Materials.AnyCopper), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_1by1_HV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_HV, 'W', OrePrefixes.wireGt01.get(Materials.Gold), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_1by1_EV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_EV, 'W', OrePrefixes.wireGt01.get(Materials.Aluminium), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_1by1_IV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_IV, 'W', OrePrefixes.wireGt01.get(Materials.Tungsten), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_1by1_LuV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_LuV, 'W', OrePrefixes.wireGt01.get(Materials.VanadiumGallium), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_1by1_ZPM.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_ZPM, 'W', OrePrefixes.wireGt01.get(Materials.Naquadah), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_1by1_UV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_UV, 'W', OrePrefixes.wireGt01.get(Materials.NaquadahAlloy), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_1by1_MAX.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_MAX, 'W', OrePrefixes.wireGt01.get(Materials.SuperconductorUHV), 'T', OreDictNames.craftingChest});

        ItemList.Battery_Buffer_2by2_ULV.set(new GT_MetaTileEntity_BasicBatteryBuffer(170, "batterybuffer.04.tier.00", "ULV Battery Buffer", 0, "", 4).getStackForm(1L));
        ItemList.Battery_Buffer_2by2_LV.set(new GT_MetaTileEntity_BasicBatteryBuffer(171, "batterybuffer.04.tier.01", "LV Battery Buffer", 1, "", 4).getStackForm(1L));
        ItemList.Battery_Buffer_2by2_MV.set(new GT_MetaTileEntity_BasicBatteryBuffer(172, "batterybuffer.04.tier.02", "MV Battery Buffer", 2, "", 4).getStackForm(1L));
        ItemList.Battery_Buffer_2by2_HV.set(new GT_MetaTileEntity_BasicBatteryBuffer(173, "batterybuffer.04.tier.03", "HV Battery Buffer", 3, "", 4).getStackForm(1L));
        ItemList.Battery_Buffer_2by2_EV.set(new GT_MetaTileEntity_BasicBatteryBuffer(174, "batterybuffer.04.tier.04", "EV Battery Buffer", 4, "", 4).getStackForm(1L));
        ItemList.Battery_Buffer_2by2_IV.set(new GT_MetaTileEntity_BasicBatteryBuffer(175, "batterybuffer.04.tier.05", "IV Battery Buffer", 5, "", 4).getStackForm(1L));
        ItemList.Battery_Buffer_2by2_LuV.set(new GT_MetaTileEntity_BasicBatteryBuffer(176, "batterybuffer.04.tier.06", "LuV Battery Buffer", 6, "", 4).getStackForm(1L));
        ItemList.Battery_Buffer_2by2_ZPM.set(new GT_MetaTileEntity_BasicBatteryBuffer(177, "batterybuffer.04.tier.07", "ZPM Battery Buffer", 7, "", 4).getStackForm(1L));
        ItemList.Battery_Buffer_2by2_UV.set(new GT_MetaTileEntity_BasicBatteryBuffer(178, "batterybuffer.04.tier.08", "UV Battery Buffer", 8, "", 4).getStackForm(1L));
        ItemList.Battery_Buffer_2by2_MAX.set(new GT_MetaTileEntity_BasicBatteryBuffer(179, "batterybuffer.04.tier.09", "UHV Battery Buffer", 9, "", 4).getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_2by2_ULV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_ULV, 'W', OrePrefixes.wireGt04.get(Materials.RedAlloy), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_2by2_LV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_LV, 'W', OrePrefixes.wireGt04.get(Materials.Tin), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_2by2_MV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_MV, 'W', OrePrefixes.wireGt04.get(Materials.AnyCopper), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_2by2_HV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_HV, 'W', OrePrefixes.wireGt04.get(Materials.Gold), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_2by2_EV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_EV, 'W', OrePrefixes.wireGt04.get(Materials.Aluminium), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_2by2_IV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_IV, 'W', OrePrefixes.wireGt04.get(Materials.Tungsten), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_2by2_LuV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_LuV, 'W', OrePrefixes.wireGt04.get(Materials.VanadiumGallium), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_2by2_ZPM.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_ZPM, 'W', OrePrefixes.wireGt04.get(Materials.Naquadah), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_2by2_UV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_UV, 'W', OrePrefixes.wireGt04.get(Materials.NaquadahAlloy), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_2by2_MAX.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_MAX, 'W', OrePrefixes.wireGt04.get(Materials.SuperconductorUHV), 'T', OreDictNames.craftingChest});

        ItemList.Battery_Buffer_3by3_ULV.set(new GT_MetaTileEntity_BasicBatteryBuffer(180, "batterybuffer.09.tier.00", "ULV Battery Buffer", 0, "", 9).getStackForm(1L));
        ItemList.Battery_Buffer_3by3_LV.set(new GT_MetaTileEntity_BasicBatteryBuffer(181, "batterybuffer.09.tier.01", "LV Battery Buffer", 1, "", 9).getStackForm(1L));
        ItemList.Battery_Buffer_3by3_MV.set(new GT_MetaTileEntity_BasicBatteryBuffer(182, "batterybuffer.09.tier.02", "MV Battery Buffer", 2, "", 9).getStackForm(1L));
        ItemList.Battery_Buffer_3by3_HV.set(new GT_MetaTileEntity_BasicBatteryBuffer(183, "batterybuffer.09.tier.03", "HV Battery Buffer", 3, "", 9).getStackForm(1L));
        ItemList.Battery_Buffer_3by3_EV.set(new GT_MetaTileEntity_BasicBatteryBuffer(184, "batterybuffer.09.tier.04", "EV Battery Buffer", 4, "", 9).getStackForm(1L));
        ItemList.Battery_Buffer_3by3_IV.set(new GT_MetaTileEntity_BasicBatteryBuffer(185, "batterybuffer.09.tier.05", "IV Battery Buffer", 5, "", 9).getStackForm(1L));
        ItemList.Battery_Buffer_3by3_LuV.set(new GT_MetaTileEntity_BasicBatteryBuffer(186, "batterybuffer.09.tier.06", "LuV Battery Buffer", 6, "", 9).getStackForm(1L));
        ItemList.Battery_Buffer_3by3_ZPM.set(new GT_MetaTileEntity_BasicBatteryBuffer(187, "batterybuffer.09.tier.07", "ZPM Battery Buffer", 7, "", 9).getStackForm(1L));
        ItemList.Battery_Buffer_3by3_UV.set(new GT_MetaTileEntity_BasicBatteryBuffer(188, "batterybuffer.09.tier.08", "UV Battery Buffer", 8, "", 9).getStackForm(1L));
        ItemList.Battery_Buffer_3by3_MAX.set(new GT_MetaTileEntity_BasicBatteryBuffer(189, "batterybuffer.09.tier.09", "UHV Battery Buffer", 9, "", 9).getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_3by3_ULV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_ULV, 'W', OrePrefixes.wireGt08.get(Materials.Tin), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_3by3_LV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_LV, 'W', OrePrefixes.wireGt08.get(Materials.Tin), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_3by3_MV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_MV, 'W', OrePrefixes.wireGt08.get(Materials.AnyCopper), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_3by3_HV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_HV, 'W', OrePrefixes.wireGt08.get(Materials.Gold), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_3by3_EV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_EV, 'W', OrePrefixes.wireGt08.get(Materials.Aluminium), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_3by3_IV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_IV, 'W', OrePrefixes.wireGt08.get(Materials.Tungsten), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_3by3_LuV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_LuV, 'W', OrePrefixes.wireGt08.get(Materials.VanadiumGallium), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_3by3_ZPM.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_ZPM, 'W', OrePrefixes.wireGt08.get(Materials.Naquadah), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_3by3_UV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_UV, 'W', OrePrefixes.wireGt08.get(Materials.NaquadahAlloy), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_3by3_MAX.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_MAX, 'W', OrePrefixes.wireGt08.get(Materials.SuperconductorUHV), 'T', OreDictNames.craftingChest});

        ItemList.Battery_Buffer_4by4_ULV.set(new GT_MetaTileEntity_BasicBatteryBuffer(190, "batterybuffer.16.tier.00", "ULV Battery Buffer", 0, "", 16).getStackForm(1L));
        ItemList.Battery_Buffer_4by4_LV.set(new GT_MetaTileEntity_BasicBatteryBuffer(191, "batterybuffer.16.tier.01", "LV Battery Buffer", 1, "", 16).getStackForm(1L));
        ItemList.Battery_Buffer_4by4_MV.set(new GT_MetaTileEntity_BasicBatteryBuffer(192, "batterybuffer.16.tier.02", "MV Battery Buffer", 2, "", 16).getStackForm(1L));
        ItemList.Battery_Buffer_4by4_HV.set(new GT_MetaTileEntity_BasicBatteryBuffer(193, "batterybuffer.16.tier.03", "HV Battery Buffer", 3, "", 16).getStackForm(1L));
        ItemList.Battery_Buffer_4by4_EV.set(new GT_MetaTileEntity_BasicBatteryBuffer(194, "batterybuffer.16.tier.04", "EV Battery Buffer", 4, "", 16).getStackForm(1L));
        ItemList.Battery_Buffer_4by4_IV.set(new GT_MetaTileEntity_BasicBatteryBuffer(195, "batterybuffer.16.tier.05", "IV Battery Buffer", 5, "", 16).getStackForm(1L));
        ItemList.Battery_Buffer_4by4_LuV.set(new GT_MetaTileEntity_BasicBatteryBuffer(196, "batterybuffer.16.tier.06", "LuV Battery Buffer", 6, "", 16).getStackForm(1L));
        ItemList.Battery_Buffer_4by4_ZPM.set(new GT_MetaTileEntity_BasicBatteryBuffer(197, "batterybuffer.16.tier.07", "ZPM Battery Buffer", 7, "", 16).getStackForm(1L));
        ItemList.Battery_Buffer_4by4_UV.set(new GT_MetaTileEntity_BasicBatteryBuffer(198, "batterybuffer.16.tier.08", "UV Battery Buffer", 8, "", 16).getStackForm(1L));
        ItemList.Battery_Buffer_4by4_MAX.set(new GT_MetaTileEntity_BasicBatteryBuffer(199, "batterybuffer.16.tier.09", "UHV Battery Buffer", 9, "", 16).getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_4by4_ULV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_ULV, 'W', OrePrefixes.wireGt16.get(Materials.Tin), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_4by4_LV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_LV, 'W', OrePrefixes.wireGt16.get(Materials.Tin), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_4by4_MV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_MV, 'W', OrePrefixes.wireGt16.get(Materials.AnyCopper), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_4by4_HV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_HV, 'W', OrePrefixes.wireGt16.get(Materials.Gold), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_4by4_EV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_EV, 'W', OrePrefixes.wireGt16.get(Materials.Aluminium), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_4by4_IV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_IV, 'W', OrePrefixes.wireGt16.get(Materials.Tungsten), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_4by4_LuV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_LuV, 'W', OrePrefixes.wireGt16.get(Materials.VanadiumGallium), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_4by4_ZPM.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_ZPM, 'W', OrePrefixes.wireGt16.get(Materials.Naquadah), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_4by4_UV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_UV, 'W', OrePrefixes.wireGt16.get(Materials.NaquadahAlloy), 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Buffer_4by4_MAX.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", 'M', ItemList.Hull_MAX, 'W', OrePrefixes.wireGt16.get(Materials.SuperconductorUHV), 'T', OreDictNames.craftingChest});

        ItemList.Battery_Charger_4by4_ULV.set(new GT_MetaTileEntity_Charger(690, "batterycharger.16.tier.00", "ULV Battery Charger", 0, "Each battery gives 8A in/4A out (min 4A/2A)", 4).getStackForm(1L));
        ItemList.Battery_Charger_4by4_LV.set(new GT_MetaTileEntity_Charger(691, "batterycharger.16.tier.01", "LV Battery Charger", 1, "Each battery gives 8A in/4A out (min 4A/2A)", 4).getStackForm(1L));
        ItemList.Battery_Charger_4by4_MV.set(new GT_MetaTileEntity_Charger(692, "batterycharger.16.tier.02", "MV Battery Charger", 2, "Each battery gives 8A in/4A out (min 4A/2A)", 4).getStackForm(1L));
        ItemList.Battery_Charger_4by4_HV.set(new GT_MetaTileEntity_Charger(693, "batterycharger.16.tier.03", "HV Battery Charger", 3, "Each battery gives 8A in/4A out (min 4A/2A)", 4).getStackForm(1L));
        ItemList.Battery_Charger_4by4_EV.set(new GT_MetaTileEntity_Charger(694, "batterycharger.16.tier.04", "EV Battery Charger", 4, "Each battery gives 8A in/4A out (min 4A/2A)", 4).getStackForm(1L));
        ItemList.Battery_Charger_4by4_IV.set(new GT_MetaTileEntity_Charger(695, "batterycharger.16.tier.05", "IV Battery Charger", 5, "Each battery gives 8A in/4A out (min 4A/2A)", 4).getStackForm(1L));
        ItemList.Battery_Charger_4by4_LuV.set(new GT_MetaTileEntity_Charger(696, "batterycharger.16.tier.06", "LuV Battery Charger", 6, "Each battery gives 8A in/4A out (min 4A/2A)", 4).getStackForm(1L));
        ItemList.Battery_Charger_4by4_ZPM.set(new GT_MetaTileEntity_Charger(697, "batterycharger.16.tier.07", "ZPM Battery Charger", 7, "Each battery gives 8A in/4A out (min 4A/2A)", 4).getStackForm(1L));
        ItemList.Battery_Charger_4by4_UV.set(new GT_MetaTileEntity_Charger(698, "batterycharger.16.tier.08", "UV Battery Charger", 8, "Each battery gives 8A in/4A out (min 4A/2A)", 4).getStackForm(1L));
        ItemList.Battery_Charger_4by4_MAX.set(new GT_MetaTileEntity_Charger(699, "batterycharger.16.tier.09", "UHV Battery Charger", 9, "Each battery gives 8A in/4A out (min 4A/2A)", 4).getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Charger_4by4_ULV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", "BCB", 'M', ItemList.Hull_ULV, 'W', OrePrefixes.wireGt16.get(Materials.RedAlloy), 'T', OreDictNames.craftingChest, 'B', ItemList.Battery_RE_HV_Sodium, 'C', OrePrefixes.circuit.get(Materials.Primitive)});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Charger_4by4_LV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", "BCB", 'M', ItemList.Hull_LV, 'W', OrePrefixes.wireGt16.get(Materials.Tin), 'T', OreDictNames.craftingChest, 'B', ItemList.Battery_RE_LV_Lithium, 'C', OrePrefixes.circuit.get(Materials.Basic)});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Charger_4by4_MV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", "BCB", 'M', ItemList.Hull_MV, 'W', OrePrefixes.wireGt16.get(Materials.AnyCopper), 'T', OreDictNames.craftingChest, 'B', ItemList.Battery_RE_MV_Lithium, 'C', OrePrefixes.circuit.get(Materials.Good)});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Charger_4by4_HV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", "BCB", 'M', ItemList.Hull_HV, 'W', OrePrefixes.wireGt16.get(Materials.Gold), 'T', OreDictNames.craftingChest, 'B', ItemList.Battery_RE_HV_Lithium, 'C', OrePrefixes.circuit.get(Materials.Advanced)});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Charger_4by4_EV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", "BCB", 'M', ItemList.Hull_EV, 'W', OrePrefixes.wireGt16.get(Materials.Aluminium), 'T', OreDictNames.craftingChest, 'B', OrePrefixes.battery.get(Materials.Master), 'C', OrePrefixes.circuit.get(Materials.Data)});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Charger_4by4_IV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", "BCB", 'M', ItemList.Hull_IV, 'W', OrePrefixes.wireGt16.get(Materials.Tungsten), 'T', OreDictNames.craftingChest, 'B', ItemList.Energy_LapotronicOrb, 'C', OrePrefixes.circuit.get(Materials.Elite)});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Charger_4by4_LuV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", "BCB", 'M', ItemList.Hull_LuV, 'W', OrePrefixes.wireGt16.get(Materials.VanadiumGallium), 'T', OreDictNames.craftingChest, 'B', ItemList.Energy_LapotronicOrb2, 'C', OrePrefixes.circuit.get(Materials.Master)});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Charger_4by4_ZPM.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", "BCB", 'M', ItemList.Hull_ZPM, 'W', OrePrefixes.wireGt16.get(Materials.Naquadah), 'T', OreDictNames.craftingChest, 'B', ItemList.Energy_LapotronicOrb2, 'C', OrePrefixes.circuit.get(Materials.Ultimate)});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Charger_4by4_UV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", "BCB", 'M', ItemList.Hull_UV, 'W', OrePrefixes.wireGt16.get(Materials.NaquadahAlloy), 'T', OreDictNames.craftingChest, 'B', ItemList.ZPM2, 'C', OrePrefixes.circuit.get(Materials.SuperconductorUHV)});
        GT_ModHandler.addCraftingRecipe(ItemList.Battery_Charger_4by4_MAX.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WTW", "WMW", "BCB", 'M', ItemList.Hull_MAX, 'W', OrePrefixes.wireGt16.get(Materials.SuperconductorUHV), 'T', OreDictNames.craftingChest, 'B', ItemList.ZPM2, 'C', OrePrefixes.circuit.get(Materials.Infinite)});

        GT_ModHandler.addCraftingRecipe(ItemList.Locker_ULV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"T", "M", 'M', ItemList.Battery_Buffer_2by2_ULV, 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Locker_LV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"T", "M", 'M', ItemList.Battery_Buffer_2by2_LV, 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Locker_MV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"T", "M", 'M', ItemList.Battery_Buffer_2by2_MV, 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Locker_HV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"T", "M", 'M', ItemList.Battery_Buffer_2by2_HV, 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Locker_EV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"T", "M", 'M', ItemList.Battery_Buffer_2by2_EV, 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Locker_IV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"T", "M", 'M', ItemList.Battery_Buffer_2by2_IV, 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Locker_LuV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"T", "M", 'M', ItemList.Battery_Buffer_2by2_LuV, 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Locker_ZPM.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"T", "M", 'M', ItemList.Battery_Buffer_2by2_ZPM, 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Locker_UV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"T", "M", 'M', ItemList.Battery_Buffer_2by2_UV, 'T', OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(ItemList.Locker_MAX.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"T", "M", 'M', ItemList.Battery_Buffer_2by2_MAX, 'T', OreDictNames.craftingChest});
    }

    private static void run2() {
        BasicMachineLoader.load();
        Machine_LV_Miner.set(new GT_MetaTileEntity_Miner(679, "basicmachine.miner.tier.01", "Ore Drilling Rig MKI", 1).getStackForm(1L));
        Machine_MV_Miner.set(new GT_MetaTileEntity_Miner(680, "basicmachine.miner.tier.02", "Ore Drilling Rig MKII", 2).getStackForm(1L));
        Machine_HV_Miner.set(new GT_MetaTileEntity_Miner(681, "basicmachine.miner.tier.03", "Ore Drilling Rig MKIII", 3).getStackForm(1L));

        addCraftingRecipe(Machine_LV_Miner.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"EEE", "WMW", "CSC", 'M', Hull_LV, 'E', Electric_Motor_LV, 'C', circuit.get(Basic), 'W', cableGt01.get(Tin), 'S', Sensor_LV});
        addCraftingRecipe(Machine_MV_Miner.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"PEP", "WMW", "CSC", 'M', Hull_MV, 'E', Electric_Motor_MV, 'P', Electric_Piston_MV, 'C', circuit.get(Good), 'W', cableGt02.get(Copper), 'S', Sensor_MV});
        addCraftingRecipe(Machine_HV_Miner.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"RPR", "WMW", "CSC", 'M', Hull_HV, 'E', Electric_Motor_HV, 'P', Electric_Piston_HV, 'R', Robot_Arm_HV, 'C', circuit.get(Advanced), 'W', cableGt04.get(Gold), 'S', Sensor_HV});
    }

    private static void run3() {
        ItemList.Machine_Multi_BlastFurnace.set(new GT_MetaTileEntity_ElectricBlastFurnace(1000, "multimachine.blastfurnace", "Electric Blast Furnace").getStackForm(1L));
        ItemList.Machine_Multi_ImplosionCompressor.set(new GT_MetaTileEntity_ImplosionCompressor(1001, "multimachine.implosioncompressor", "Implosion Compressor").getStackForm(1L));
        ItemList.Machine_Multi_VacuumFreezer.set(new GT_MetaTileEntity_VacuumFreezer(1002, "multimachine.vacuumfreezer", "Vacuum Freezer").getStackForm(1L));
        ItemList.Machine_Multi_Furnace.set(new GT_MetaTileEntity_MultiFurnace(1003, "multimachine.multifurnace", "Multi Smelter").getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_BlastFurnace.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"FFF", "CMC", "WCW", 'M', ItemList.Casing_HeatProof, 'F', OreDictNames.craftingIronFurnace, 'C', OrePrefixes.circuit.get(Materials.Basic), 'W', OrePrefixes.cableGt01.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_VacuumFreezer.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"PPP", "CMC", "WCW", 'M', ItemList.Casing_FrostProof, 'P', ItemList.Electric_Pump_HV, 'C', OrePrefixes.circuit.get(Materials.Data), 'W', OrePrefixes.cableGt01.get(Materials.Gold)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_ImplosionCompressor.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"OOO", "CMC", "WCW", 'M', ItemList.Casing_SolidSteel, 'O', Ic2Items.reinforcedStone, 'C', OrePrefixes.circuit.get(Materials.Advanced), 'W', OrePrefixes.cableGt01.get(Materials.Gold)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_Furnace.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"FFF", "CMC", "WCW", 'M', ItemList.Casing_HeatProof, 'F', OreDictNames.craftingIronFurnace, 'C', OrePrefixes.circuit.get(Materials.Advanced), 'W', OrePrefixes.cableGt01.get(Materials.AnnealedCopper)});

        ItemList.Machine_Multi_LargeBoiler_Bronze.set(new GT_MetaTileEntity_LargeBoiler_Bronze(1020, "multimachine.boiler.bronze", "Large Bronze Boiler").getStackForm(1L));
        ItemList.Machine_Multi_LargeBoiler_Steel.set(new GT_MetaTileEntity_LargeBoiler_Steel(1021, "multimachine.boiler.steel", "Large Steel Boiler").getStackForm(1L));
        ItemList.Machine_Multi_LargeBoiler_Titanium.set(new GT_MetaTileEntity_LargeBoiler_Titanium(1022, "multimachine.boiler.titanium", "Large Titanium Boiler").getStackForm(1L));
        ItemList.Machine_Multi_LargeBoiler_TungstenSteel.set(new GT_MetaTileEntity_LargeBoiler_TungstenSteel(1023, "multimachine.boiler.tungstensteel", "Large Tungstensteel Boiler").getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_LargeBoiler_Bronze.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WCW", "CMC", "WCW", 'M', ItemList.Casing_Firebox_Bronze, 'C', OrePrefixes.circuit.get(Materials.Good), 'W', OrePrefixes.cableGt01.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_LargeBoiler_Steel.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WCW", "CMC", "WCW", 'M', ItemList.Casing_Firebox_Steel, 'C', OrePrefixes.circuit.get(Materials.Advanced), 'W', OrePrefixes.cableGt01.get(Materials.AnyCopper)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_LargeBoiler_Titanium.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WCW", "CMC", "WCW", 'M', ItemList.Casing_Firebox_Titanium, 'C', OrePrefixes.circuit.get(Materials.Data), 'W', OrePrefixes.cableGt01.get(Materials.Gold)});
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_LargeBoiler_TungstenSteel.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WCW", "CMC", "WCW", 'M', ItemList.Casing_Firebox_TungstenSteel, 'C', OrePrefixes.circuit.get(Materials.Elite), 'W', OrePrefixes.cableGt01.get(Materials.Aluminium)});

        BasicGeneratorLoader.load();

        ItemList.FusionComputer_LuV.set(new GT_MetaTileEntity_FusionComputer1(1193, "fusioncomputer.tier.06", "Fusion Control Computer MKI").getStackForm(1L));
        ItemList.FusionComputer_ZPMV.set(new GT_MetaTileEntity_FusionComputer2(1194, "fusioncomputer.tier.07", "Fusion Control Computer MKII").getStackForm(1L));
        ItemList.FusionComputer_UV.set(new GT_MetaTileEntity_FusionComputer3(1195, "fusioncomputer.tier.08", "Fusion Control Computer MKIII").getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Casing_Fusion_Coil.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CTC", "FMF", "CTC", 'M', ItemList.Casing_Coil_Superconductor, 'C', OrePrefixes.circuit.get(Materials.Master), 'F', ItemList.Field_Generator_MV, 'T', ItemList.Neutron_Reflector});

        ItemList.Processing_Array.set(new GT_MetaTileEntity_ProcessingArray(1199, "multimachine.processingarray", "Processing Array").getStackForm(1L));
        GT_ModHandler.addCraftingRecipe(ItemList.Processing_Array.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CTC", "FMF", "CBC", 'M', ItemList.Hull_EV, 'B', OrePrefixes.pipeLarge.get(Materials.StainlessSteel), 'C', OrePrefixes.circuit.get(Materials.Elite), 'F', ItemList.Robot_Arm_EV, 'T', ItemList.Energy_LapotronicOrb});

        GT_ProcessingArrayRecipeLoader.registerDefaultGregtechMaps();

        ItemList.Distillation_Tower.set(new GT_MetaTileEntity_DistillationTower(1126, "multimachine.distillationtower", "Distillation Tower").getStackForm(1L));
        GT_ModHandler.addCraftingRecipe(ItemList.Distillation_Tower.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CBC", "FMF", "CBC", 'M', ItemList.Hull_HV, 'B', OrePrefixes.pipeLarge.get(Materials.StainlessSteel), 'C', OrePrefixes.circuit.get(Materials.Data), 'F', ItemList.Electric_Pump_HV});

        ItemList.LargeSteamTurbine.set(new GT_MetaTileEntity_LargeTurbine_Steam(1131, "multimachine.largeturbine", "Large Steam Turbine").getStackForm(1L));
        ItemList.LargeGasTurbine.set(new GT_MetaTileEntity_LargeTurbine_Gas(1151, "multimachine.largegasturbine", "Large Gas Turbine").getStackForm(1L));
        ItemList.LargeHPSteamTurbine.set(new GT_MetaTileEntity_LargeTurbine_HPSteam(1152, "multimachine.largehpturbine", "Large HP Steam Turbine").getStackForm(1L));
        ItemList.LargePlasmaTurbine.set(new GT_MetaTileEntity_LargeTurbine_Plasma(1153, "multimachine.largeplasmaturbine", "Large Plasma Generator").getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.LargeSteamTurbine.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CPC", "PMP", "BPB", 'M', ItemList.Hull_HV, 'B', OrePrefixes.pipeLarge.get(Materials.Steel), 'C', OrePrefixes.circuit.get(Materials.Advanced), 'P', OrePrefixes.gearGt.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(ItemList.LargeGasTurbine.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CPC", "PMP", "BPB", 'M', ItemList.Hull_EV, 'B', OrePrefixes.pipeLarge.get(Materials.StainlessSteel), 'C', OrePrefixes.circuit.get(Materials.Data), 'P', OrePrefixes.gearGt.get(Materials.StainlessSteel)});
        //GT_ModHandler.addCraftingRecipe(ItemList.LargeHPSteamTurbine.get(1L), bitsd, new Object[]{"CPC", aTextPlateMotor, "BPB", 'M', ItemList.Hull_IV, 'B', OrePrefixes.pipeLarge.get(Materials.Titanium), 'C', OrePrefixes.circuit.get(Materials.Elite), 'P', OrePrefixes.gearGt.get(Materials.Titanium)});
        //GT_ModHandler.addCraftingRecipe(ItemList.LargePlasmaTurbine.get(1L), bitsd, new Object[]{"CPC", aTextPlateMotor, "BPB", 'M', ItemList.Hull_UV, 'B', OrePrefixes.pipeHuge.get(Materials.TungstenSteel), 'C', OrePrefixes.circuit.get(Materials.Master), 'P', OrePrefixes.gearGt.get(Materials.TungstenSteel)});

        ItemList.Pump_LV.set(new GT_MetaTileEntity_Pump(1140, "basicmachine.pump.tier.01", "Basic Pump", 1).getStackForm(1L));
        ItemList.Pump_MV.set(new GT_MetaTileEntity_Pump(1141, "basicmachine.pump.tier.02", "Advanced Pump", 2).getStackForm(1L));
        ItemList.Pump_HV.set(new GT_MetaTileEntity_Pump(1142, "basicmachine.pump.tier.03", "Advanced Pump II", 3).getStackForm(1L));
        ItemList.Pump_EV.set(new GT_MetaTileEntity_Pump(1143, "basicmachine.pump.tier.04", "Advanced Pump III", 4).getStackForm(1L));
        ItemList.Pump_IV.set(new GT_MetaTileEntity_Pump(1144, "basicmachine.pump.tier.05", "Advanced Pump IV", 5).getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.Pump_LV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CPC", "PMP", "BPB", 'M', ItemList.Hull_LV, 'B', OrePrefixes.pipeLarge.get(Materials.Bronze), 'C', OrePrefixes.circuit.get(Materials.Basic), 'P', ItemList.Electric_Pump_LV});
        GT_ModHandler.addCraftingRecipe(ItemList.Pump_MV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CPC", "PMP", "BPB", 'M', ItemList.Hull_MV, 'B', OrePrefixes.pipeLarge.get(Materials.Steel), 'C', OrePrefixes.circuit.get(Materials.Good), 'P', ItemList.Electric_Pump_MV});
        GT_ModHandler.addCraftingRecipe(ItemList.Pump_HV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CPC", "PMP", "BPB", 'M', ItemList.Hull_HV, 'B', OrePrefixes.pipeLarge.get(Materials.StainlessSteel), 'C', OrePrefixes.circuit.get(Materials.Advanced), 'P', ItemList.Electric_Pump_HV});
        GT_ModHandler.addCraftingRecipe(ItemList.Pump_EV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CPC", "PMP", "BPB", 'M', ItemList.Hull_EV, 'B', OrePrefixes.pipeLarge.get(Materials.Titanium), 'C', OrePrefixes.circuit.get(Materials.Data), 'P', ItemList.Electric_Pump_EV});
        GT_ModHandler.addCraftingRecipe(ItemList.Pump_IV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CPC", "PMP", "BPB", 'M', ItemList.Hull_IV, 'B', OrePrefixes.pipeLarge.get(Materials.TungstenSteel), 'C', OrePrefixes.circuit.get(Materials.Elite), 'P', ItemList.Electric_Pump_IV});

        ItemList.Teleporter.set(new GT_MetaTileEntity_Teleporter(1145, "basicmachine.teleporter", "Teleporter", 9).getStackForm(1L));

        ItemList.MobRep_LV.set(new GT_MetaTileEntity_MonsterRepellent(1146, "basicmachine.mobrep.tier.01", "Basic Monster Repellator", 1).getStackForm(1L));
        ItemList.MobRep_MV.set(new GT_MetaTileEntity_MonsterRepellent(1147, "basicmachine.mobrep.tier.02", "Advanced Monster Repellator", 2).getStackForm(1L));
        ItemList.MobRep_HV.set(new GT_MetaTileEntity_MonsterRepellent(1148, "basicmachine.mobrep.tier.03", "Advanced Monster Repellator II", 3).getStackForm(1L));
        ItemList.MobRep_EV.set(new GT_MetaTileEntity_MonsterRepellent(1149, "basicmachine.mobrep.tier.04", "Advanced Monster Repellator III", 4).getStackForm(1L));
        ItemList.MobRep_IV.set(new GT_MetaTileEntity_MonsterRepellent(1150, "basicmachine.mobrep.tier.05", "Advanced Monster Repellator IV", 5).getStackForm(1L));
        ItemList.MobRep_LuV.set(new GT_MetaTileEntity_MonsterRepellent(1135, "basicmachine.mobrep.tier.06", "Advanced Monster Repellator V", 6).getStackForm(1L));
        ItemList.MobRep_ZPM.set(new GT_MetaTileEntity_MonsterRepellent(1136, "basicmachine.mobrep.tier.07", "Advanced Monster Repellator VI", 7).getStackForm(1L));
        ItemList.MobRep_UV.set(new GT_MetaTileEntity_MonsterRepellent(1137, "basicmachine.mobrep.tier.08", "Advanced Monster Repellator VII", 8).getStackForm(1L));

        GT_ModHandler.addCraftingRecipe(ItemList.MobRep_LV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"EEE", " M ", "CCC", 'M', ItemList.Hull_LV, 'E', ItemList.Emitter_LV.get(1L), 'C', OrePrefixes.circuit.get(Materials.Basic)});
        GT_ModHandler.addCraftingRecipe(ItemList.MobRep_MV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"EEE", " M ", "CCC", 'M', ItemList.Hull_MV, 'E', ItemList.Emitter_MV.get(1L), 'C', OrePrefixes.circuit.get(Materials.Good)});
        GT_ModHandler.addCraftingRecipe(ItemList.MobRep_HV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"EEE", " M ", "CCC", 'M', ItemList.Hull_HV, 'E', ItemList.Emitter_HV.get(1L), 'C', OrePrefixes.circuit.get(Materials.Advanced)});
        GT_ModHandler.addCraftingRecipe(ItemList.MobRep_EV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"EEE", " M ", "CCC", 'M', ItemList.Hull_EV, 'E', ItemList.Emitter_EV.get(1L), 'C', OrePrefixes.circuit.get(Materials.Data)});
        GT_ModHandler.addCraftingRecipe(ItemList.MobRep_IV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"EEE", " M ", "CCC", 'M', ItemList.Hull_IV, 'E', ItemList.Emitter_IV.get(1L), 'C', OrePrefixes.circuit.get(Materials.Elite)});
        GT_ModHandler.addCraftingRecipe(ItemList.MobRep_LuV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"EEE", " M ", "CCC", 'M', ItemList.Hull_LuV, 'E', ItemList.Emitter_LuV.get(1L), 'C', OrePrefixes.circuit.get(Materials.Master)});
        GT_ModHandler.addCraftingRecipe(ItemList.MobRep_ZPM.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"EEE", " M ", "CCC", 'M', ItemList.Hull_ZPM, 'E', ItemList.Emitter_ZPM.get(1L), 'C', OrePrefixes.circuit.get(Materials.Ultimate)});
        GT_ModHandler.addCraftingRecipe(ItemList.MobRep_UV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"EEE", " M ", "CCC", 'M', ItemList.Hull_UV, 'E', ItemList.Emitter_UV.get(1L), 'C', OrePrefixes.circuit.get(Materials.Superconductor)});

        ItemList.Machine_Multi_HeatExchanger.set(new GT_MetaTileEntity_HeatExchanger(1154, "multimachine.heatexchanger", "Large Heat Exchanger").getStackForm(1L));
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_HeatExchanger.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WCW", "CMC", "WCW", 'M', ItemList.Casing_Pipe_Titanium, 'C', OrePrefixes.pipeMedium.get(Materials.Titanium), 'W', ItemList.Electric_Pump_EV});


        ItemList.Charcoal_Pile.set(new GT_MetaTileEntity_Charcoal_Pit(1155, "multimachine.charcoalpile", "Charcoal Pile Igniter").getStackForm(1));
        GT_ModHandler.addCraftingRecipe(ItemList.Charcoal_Pile.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"EXE", "EME", " C ", 'M', ItemList.Hull_Bronze_Bricks, 'E', OrePrefixes.plate.get(Materials.AnyBronze), 'C', new ItemStack(Items.flint_and_steel, 1), 'X', OrePrefixes.rotor.get(Materials.Bronze),});

        ItemList.Seismic_Prospector_LV.set(new GT_MetaTileEntity_SeismicProspector(1156, "basicmachine.seismicprospector.01", "Seismic Prospector LV", 1).getStackForm(1));
        ItemList.Seismic_Prospector_MV.set(new GT_MetaTileEntity_SeismicProspector(2100, "basicmachine.seismicprospector.02", "Seismic Prospector MV", 2).getStackForm(1));
        ItemList.Seismic_Prospector_HV.set(new GT_MetaTileEntity_SeismicProspector(2101, "basicmachine.seismicprospector.03", "Seismic Prospector HV", 3).getStackForm(1));

        ItemList.Seismic_Prospector_Adv_LV.set(new GT_MetaTileEntity_AdvSeismicProspector(2102, "basicmachine.seismicprospector.07", "Advanced Seismic Prospector LV", 1, 5 * 16 / 2, 2).getStackForm(1));
        ItemList.Seismic_Prospector_Adv_MV.set(new GT_MetaTileEntity_AdvSeismicProspector(2103, "basicmachine.seismicprospector.06", "Advanced Seismic Prospector MV", 2, 7 * 16 / 2, 2).getStackForm(1));
        ItemList.Seismic_Prospector_Adv_HV.set(new GT_MetaTileEntity_AdvSeismicProspector(2104, "basicmachine.seismicprospector.05", "Advanced Seismic Prospector HV", 3, 9 * 16 / 2, 2).getStackForm(1));
        ItemList.Seismic_Prospector_Adv_EV.set(new GT_MetaTileEntity_AdvSeismicProspector(1173, "basicmachine.seismicprospector.04", "Advanced Seismic Prospector EV", 4, 11 * 16 / 2, 2).getStackForm(1));

        //Converter recipes in case you had old one lying around
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Seismic_Prospector_Adv_LV.get(1L), RECIPE_MASK, new Object[]{ItemList.Seismic_Prospector_LV});
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Seismic_Prospector_Adv_MV.get(1L), RECIPE_MASK, new Object[]{ItemList.Seismic_Prospector_MV});
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Seismic_Prospector_Adv_HV.get(1L), RECIPE_MASK, new Object[]{ItemList.Seismic_Prospector_HV});

        GT_ModHandler.addCraftingRecipe(ItemList.Seismic_Prospector_Adv_LV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WWW", "EME", "CXC", 'M', ItemList.Hull_LV, 'W', OrePrefixes.plateDouble.get(Materials.Steel), 'E', OrePrefixes.circuit.get(Materials.Basic), 'C', ItemList.Sensor_LV, 'X', OrePrefixes.cableGt02.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(ItemList.Seismic_Prospector_Adv_MV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WWW", "EME", "CXC", 'M', ItemList.Hull_MV, 'W', OrePrefixes.plateDouble.get(Materials.BlackSteel), 'E', OrePrefixes.circuit.get(Materials.Good), 'C', ItemList.Sensor_MV, 'X', OrePrefixes.cableGt02.get(Materials.Copper)});
        GT_ModHandler.addCraftingRecipe(ItemList.Seismic_Prospector_Adv_HV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WWW", "EME", "CXC", 'M', ItemList.Hull_HV, 'W', OrePrefixes.plateDouble.get(Materials.StainlessSteel), 'E', OrePrefixes.circuit.get(Materials.Advanced), 'C', ItemList.Sensor_HV, 'X', OrePrefixes.cableGt04.get(Materials.Gold)});
        GT_ModHandler.addCraftingRecipe(ItemList.Seismic_Prospector_Adv_EV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WWW", "EME", "CXC", 'M', ItemList.Hull_EV, 'W', OrePrefixes.plateDouble.get(Materials.VanadiumSteel), 'E', OrePrefixes.circuit.get(Materials.Data), 'C', ItemList.Sensor_EV, 'X', OrePrefixes.cableGt04.get(Materials.Aluminium)});

        ItemList.OilDrill1.set(new GT_MetaTileEntity_OilDrill1(1157, "multimachine.oildrill1", "Oil/Gas/Fluid Drilling Rig").getStackForm(1));
        ItemList.OilDrill2.set(new GT_MetaTileEntity_OilDrill2(141, "multimachine.oildrill2", "Oil/Gas/Fluid Drilling Rig II").getStackForm(1));
        ItemList.OilDrill3.set(new GT_MetaTileEntity_OilDrill3(142, "multimachine.oildrill3", "Oil/Gas/Fluid Drilling Rig III").getStackForm(1));

        ItemList.ConcreteBackfiller1.set(new GT_MetaTileEntity_ConcreteBackfiller1(143, "multimachine.concretebackfiller1", "Concrete Backfiller").getStackForm(1));
        ItemList.ConcreteBackfiller2.set(new GT_MetaTileEntity_ConcreteBackfiller2(144, "multimachine.concretebackfiller3", "Advanced Concrete Backfiller").getStackForm(1));
        GT_ModHandler.addCraftingRecipe(ItemList.ConcreteBackfiller1.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WPW", "EME", "CQC", 'M', ItemList.Hull_MV, 'W', OrePrefixes.frameGt.get(Materials.Steel), 'E', OrePrefixes.circuit.get(Materials.Good), 'C', ItemList.Electric_Motor_MV, 'P', OrePrefixes.pipeLarge.get(Materials.Steel), 'Q', ItemList.Electric_Pump_MV});
        GT_ModHandler.addCraftingRecipe(ItemList.ConcreteBackfiller2.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WPW", "EME", "CQC", 'M', ItemList.ConcreteBackfiller1, 'W', OrePrefixes.frameGt.get(Materials.Titanium), 'E', OrePrefixes.circuit.get(Materials.Data), 'C', ItemList.Electric_Motor_EV, 'P', OrePrefixes.pipeLarge.get(Materials.Steel), 'Q', ItemList.Electric_Pump_EV});

        ItemList.OreDrill1.set(new GT_MetaTileEntity_OreDrillingPlant1(1158, "multimachine.oredrill1", "Ore Drilling Rig MKIV").getStackForm(1));
        ItemList.OreDrill2.set(new GT_MetaTileEntity_OreDrillingPlant2(1177, "multimachine.oredrill2", "Ore Drilling Rig MKV").getStackForm(1));
        ItemList.OreDrill3.set(new GT_MetaTileEntity_OreDrillingPlant3(1178, "multimachine.oredrill3", "Ore Drilling Rig MKVI").getStackForm(1));
        ItemList.OreDrill4.set(new GT_MetaTileEntity_OreDrillingPlant4(1179, "multimachine.oredrill4", "Ore Drilling Rig MKVII").getStackForm(1));

        ItemList.PyrolyseOven.set(new GT_MetaTileEntity_PyrolyseOven(1159, "multimachine.pyro", "Pyrolyse Oven").getStackForm(1));
        GT_ModHandler.addCraftingRecipe(ItemList.PyrolyseOven.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WEP", "EME", "WCP", 'M', ItemList.Hull_LV, 'W', ItemList.Electric_Piston_LV, 'P', OrePrefixes.wireGt04.get(Materials.Cupronickel), 'E', OrePrefixes.circuit.get(Materials.Basic), 'C', ItemList.Electric_Pump_LV});

        ItemList.OilCracker.set(new GT_MetaTileEntity_OilCracker(1160, "multimachine.cracker", "Oil Cracking Unit").getStackForm(1));
        GT_ModHandler.addCraftingRecipe(ItemList.OilCracker.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WCW", "EME", "WCW", 'M', ItemList.Hull_HV, 'W', ItemList.Casing_Coil_Cupronickel, 'E', OrePrefixes.circuit.get(Materials.Advanced), 'C', ItemList.Electric_Pump_HV});

        ItemList.MicroTransmitter_HV.set(new GT_MetaTileEntity_MicrowaveEnergyTransmitter(1161, "basicmachine.microtransmitter.03", "HV Microwave Energy Transmitter", 3).getStackForm(1L));
        ItemList.MicroTransmitter_EV.set(new GT_MetaTileEntity_MicrowaveEnergyTransmitter(1162, "basicmachine.microtransmitter.04", "EV Microwave Energy Transmitter", 4).getStackForm(1L));
        ItemList.MicroTransmitter_IV.set(new GT_MetaTileEntity_MicrowaveEnergyTransmitter(1163, "basicmachine.microtransmitter.05", "IV Microwave Energy Transmitter", 5).getStackForm(1L));
        ItemList.MicroTransmitter_LUV.set(new GT_MetaTileEntity_MicrowaveEnergyTransmitter(1164, "basicmachine.microtransmitter.06", "LuV Microwave Energy Transmitter", 6).getStackForm(1L));
        ItemList.MicroTransmitter_ZPM.set(new GT_MetaTileEntity_MicrowaveEnergyTransmitter(1165, "basicmachine.microtransmitter.07", "ZPM Microwave Energy Transmitter", 7).getStackForm(1L));
        ItemList.MicroTransmitter_UV.set(new GT_MetaTileEntity_MicrowaveEnergyTransmitter(1166, "basicmachine.microtransmitter.08", "UV Microwave Energy Transmitter", 8).getStackForm(1L));
        GT_ModHandler.addCraftingRecipe(ItemList.MicroTransmitter_HV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CPC", "CMC", "GBG", 'M', ItemList.Hull_HV, 'B', ItemList.Battery_RE_HV_Lithium, 'C', ItemList.Emitter_HV, 'G', OrePrefixes.circuit.get(Materials.Advanced), 'P', ItemList.Field_Generator_HV});
        GT_ModHandler.addCraftingRecipe(ItemList.MicroTransmitter_EV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CPC", "CMC", "GBG", 'M', ItemList.Hull_EV, 'B', GT_ModHandler.getIC2Item("lapotronCrystal", 1L, GT_Values.W), 'C', ItemList.Emitter_EV, 'G', OrePrefixes.circuit.get(Materials.Data), 'P', ItemList.Field_Generator_EV});
        GT_ModHandler.addCraftingRecipe(ItemList.MicroTransmitter_IV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CPC", "CMC", "GBG", 'M', ItemList.Hull_IV, 'B', ItemList.Energy_LapotronicOrb, 'C', ItemList.Emitter_IV, 'G', OrePrefixes.circuit.get(Materials.Elite), 'P', ItemList.Field_Generator_IV});
        GT_ModHandler.addCraftingRecipe(ItemList.MicroTransmitter_LUV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CPC", "CMC", "GBG", 'M', ItemList.Hull_LuV, 'B', ItemList.Energy_LapotronicOrb2, 'C', ItemList.Emitter_LuV, 'G', OrePrefixes.circuit.get(Materials.Master), 'P', ItemList.Field_Generator_LuV});
        GT_ModHandler.addCraftingRecipe(ItemList.MicroTransmitter_ZPM.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CPC", "CMC", "GBG", 'M', ItemList.Hull_ZPM, 'B', GregTech_API.sOPStuff.get(ConfigCategories.Recipes.gregtechrecipes, "EnableZPMandUVBatteries", false) ? ItemList.Energy_Module : ItemList.ZPM2, 'C', ItemList.Emitter_ZPM, 'G', OrePrefixes.circuit.get(Materials.Ultimate), 'P', ItemList.Field_Generator_ZPM});
        GT_ModHandler.addCraftingRecipe(ItemList.MicroTransmitter_UV.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CPC", "CMC", "GBG", 'M', ItemList.Hull_UV, 'B', GregTech_API.sOPStuff.get(ConfigCategories.Recipes.gregtechrecipes, "EnableZPMandUVBatteries", false) ? ItemList.Energy_Module : ItemList.ZPM3, 'C', ItemList.Emitter_UV, 'G', OrePrefixes.circuit.get(Materials.Superconductor), 'P', ItemList.Field_Generator_UV});

        ItemList.Machine_Multi_Assemblyline.set(new GT_MetaTileEntity_AssemblyLine(1170, "multimachine.assemblyline", "Assembling Line").getStackForm(1L));
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_Assemblyline.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"WCW", "EME", "WCW", 'M', ItemList.Hull_IV, 'W', ItemList.Casing_Assembler, 'E', OrePrefixes.circuit.get(Materials.Elite), 'C', ItemList.Robot_Arm_IV});

        ItemList.Machine_Multi_DieselEngine.set(new GT_MetaTileEntity_DieselEngine(1171, "multimachine.dieselengine", "Combustion Engine").getStackForm(1L));
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_DieselEngine.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"PCP", "EME", "GWG", 'M', ItemList.Hull_EV, 'P', ItemList.Electric_Piston_EV, 'E', ItemList.Electric_Motor_EV, 'C', OrePrefixes.circuit.get(Materials.Elite), 'W', OrePrefixes.cableGt01.get(Materials.TungstenSteel), 'G', OrePrefixes.gearGt.get(Materials.Titanium)});
        GT_ModHandler.addCraftingRecipe(ItemList.Casing_EngineIntake.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"PhP", "RFR", "PwP", 'R', OrePrefixes.pipeMedium.get(Materials.Titanium), 'F', ItemList.Casing_StableTitanium, 'P', OrePrefixes.rotor.get(Materials.Titanium)});

        ItemList.Machine_Multi_ExtremeDieselEngine.set(new GT_MetaTileEntity_ExtremeDieselEngine(2105, "multimachine.extremedieselengine", "Extreme Combustion Engine").getStackForm(1L));
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_ExtremeDieselEngine.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"PCP", "EME", "GWG", 'M', ItemList.Hull_IV, 'P', ItemList.Electric_Piston_IV, 'E', ItemList.Electric_Motor_IV, 'C', OrePrefixes.circuit.get(Materials.Master), 'W', OrePrefixes.cableGt01.get(Materials.HSSG), 'G', OrePrefixes.gearGt.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(ItemList.Casing_ExtremeEngineIntake.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"PhP", "RFR", "PwP", 'R', OrePrefixes.pipeMedium.get(Materials.TungstenSteel), 'F', ItemList.Casing_RobustTungstenSteel, 'P', OrePrefixes.rotor.get(Materials.TungstenSteel)});

        ItemList.Machine_Multi_Cleanroom.set(new GT_MetaTileEntity_Cleanroom(1172, "multimachine.cleanroom", "Cleanroom Controller").getStackForm(1));
        //If Cleanroom is enabled, add a recipe, else hide from NEI.
        if (GT_Mod.gregtechproxy.mEnableCleanroom) {
            GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_Cleanroom.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"FFF", "RHR", "MCM", 'H', ItemList.Hull_HV, 'F', ItemList.Component_Filter, 'R', OrePrefixes.rotor.get(Materials.StainlessSteel), 'M', ItemList.Electric_Motor_HV, 'C', OrePrefixes.circuit.get(Materials.Advanced)});
        } else {
            if (Loader.isModLoaded("NotEnoughItems")) {
                API.hideItem(ItemList.Machine_Multi_Cleanroom.get(1L));
            }
        }

        ItemList.Machine_HV_LightningRod.set(new GT_MetaTileEntity_LightningRod(1174, "basicgenerator.lightningrod.03", "Lightning Rod", 3).getStackForm(1));
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_HV_LightningRod.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"LTL", "TMT", "LTL", 'M', ItemList.Hull_LuV, 'L', ItemList.Energy_LapotronicOrb, 'T', ItemList.Transformer_ZPM_LuV});
        ItemList.Machine_EV_LightningRod.set(new GT_MetaTileEntity_LightningRod(1175, "basicgenerator.lightningrod.04", "Lightning Rod II", 4).getStackForm(1));
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_EV_LightningRod.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"LTL", "TMT", "LTL", 'M', ItemList.Hull_ZPM, 'L', ItemList.Energy_LapotronicOrb2, 'T', ItemList.Transformer_UV_ZPM});
        ItemList.Machine_IV_LightningRod.set(new GT_MetaTileEntity_LightningRod(1176, "basicgenerator.lightningrod.05", "Lightning Rod III", 5).getStackForm(1));
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_IV_LightningRod.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"LTL", "TMT", "LTL", 'M', ItemList.Hull_UV, 'L', ItemList.ZPM2, 'T', ItemList.Transformer_MAX_UV});

        ItemList.Machine_Multi_LargeChemicalReactor.set(new GT_MetaTileEntity_LargeChemicalReactor(1169, "multimachine.chemicalreactor", "Large Chemical Reactor").getStackForm(1));
        GT_ModHandler.addCraftingRecipe(ItemList.Machine_Multi_LargeChemicalReactor.get(1L), DISMANTLEABLE_RECIPE_MASK, new Object[]{"CRC", "PMP", "CBC",
                                                                                                                                     'C', OrePrefixes.circuit.get(Materials.Advanced),
                                                                                                                                     'R', OrePrefixes.rotor.get(Materials.StainlessSteel),
                                                                                                                                     'P', OrePrefixes.pipeLarge.get(Materials.Polytetrafluoroethylene),
                                                                                                                                     'M', ItemList.Electric_Motor_HV,
                                                                                                                                     'B', ItemList.Hull_HV});
    }

    private static void run4() {
        long bits = GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE | GT_ModHandler.RecipeBits.BUFFERED;
        long bitsd = GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE | GT_ModHandler.RecipeBits.BUFFERED;
        for (int i = 0; i < GregTech_API.sGeneratedMaterials.length; i++) {
            if (((GregTech_API.sGeneratedMaterials[i] != null) && ((GregTech_API.sGeneratedMaterials[i].mTypes & 0x2) != 0)) || (GregTech_API.sGeneratedMaterials[i] == Materials.Wood)) {
                new GT_MetaPipeEntity_Frame(4096 + i, "GT_Frame_" + GregTech_API.sGeneratedMaterials[i], (GT_LanguageManager.i18nPlaceholder ? "%material" : GregTech_API.sGeneratedMaterials[i].mDefaultLocalName) + " Frame Box", GregTech_API.sGeneratedMaterials[i]);
            }
        }
        CableLoader.load();
        FluidPipeLoader.load();
        ItemPipeLoader.load();
        ItemManagementLoader.load();
    }

    @Override
    public void run() {
        GT_Log.out.println("GT_Mod: Registering MetaTileEntities.");
        run1();
        run2();
        run3();
        run4();
    }
}
