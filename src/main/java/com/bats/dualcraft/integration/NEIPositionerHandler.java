package com.bats.dualcraft.integration;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.IStackPositioner;
import codechicken.nei.recipe.GuiRecipe;
import com.bats.dualcraft.client.gui.GuiDualCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;

import java.util.ArrayList;

/**
 * Created by Bats on 4/13/2015.
 */
public class NEIPositionerHandler implements IStackPositioner
{
    @Override
    public ArrayList<PositionedStack> positionStacks(ArrayList<PositionedStack> stacks)
    {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiRecipe)
        {
            GuiRecipe recipeGui = (GuiRecipe) Minecraft.getMinecraft().currentScreen;

            if (!(recipeGui.firstGui instanceof GuiDualCraft))
            {
                return stacks;
            }

            GuiDualCraft gui = (GuiDualCraft) recipeGui.firstGui;

            for (PositionedStack stack : stacks)
            {
                stack.relx += 2;
                stack.rely += 2;
            }
        }
        return stacks;
    }
}
