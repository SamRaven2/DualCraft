package com.bats.dualcraft.integration;

import codechicken.nei.OffsetPositioner;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.DefaultOverlayHandler;
import com.bats.dualcraft.client.gui.GuiDualCraft;
import com.bats.dualcraft.reference.Reference;
import com.bats.dualcraft.util.LogHelper;

/**
 * Created by Bats on 4/13/2015.
 */
public class NEIDualCraftConfig implements IConfigureNEI
{
    @Override
    public void loadConfig()
    {
        LogHelper.info("Trying to Support NEI...");
        API.registerGuiOverlay(GuiDualCraft.class, "crafting", new OffsetPositioner(5,11));
        API.registerGuiOverlayHandler(GuiDualCraft.class, new DefaultOverlayHandler(), "crafting");
    }

    @Override
    public String getName()
    {
        return "DualCraft";
    }

    @Override
    public String getVersion()
    {
        return Reference.VERSION;
    }
}
