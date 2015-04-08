package com.bats.dualcraft.handler;

import com.bats.dualcraft.client.gui.GuiDualCraft;
import com.bats.dualcraft.inventory.ContainerDualCraft;
import com.bats.dualcraft.reference.GUIs;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Bats on 4/7/2015.
 */
public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GUIs.DUAL_WORKBENCH.ordinal())
        {
            return new ContainerDualCraft(player.inventory, world, x, y, z);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GUIs.DUAL_WORKBENCH.ordinal())
        {
            return new GuiDualCraft(player.inventory, world, x, y, z);
        }
        return null;
    }
}
