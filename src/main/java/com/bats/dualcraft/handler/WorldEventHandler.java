package com.bats.dualcraft.handler;

import com.bats.dualcraft.DualCraft;
import com.bats.dualcraft.reference.GUIs;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

/**
 * Created by Bats on 4/5/2015.
 */
public class WorldEventHandler
{
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        EntityPlayer player = event.entityPlayer;
        World world = event.world;
        int x = event.x;
        int y = event.y;
        int z = event.z;

        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && !player.isSneaking())
        {
            Block clickedBlock = world.getBlock(x, y, z);
            if (clickedBlock == Blocks.crafting_table)
                if (validMultiblock(world, x, y, z))
                {
                    event.useBlock = Event.Result.DENY;
                    player.openGui(DualCraft.instance, GUIs.DUAL_WORKBENCH.ordinal(), world, x, y, z);
                }
        }
    }

    private boolean validMultiblock(World world, int x, int y, int z)
    {
        int tableCount = 0;
        if (world.getBlock(x + 1, y, z) == Blocks.crafting_table)
        {
            tableCount++;
            if (world.getBlock(x + 1, y, z + 1) == Blocks.crafting_table
                    || world.getBlock(x + 2, y, z) == Blocks.crafting_table
                    || world.getBlock(x + 1, y, z - 1) == Blocks.crafting_table)
                return false;
        }

        if (world.getBlock(x - 1, y, z) == Blocks.crafting_table)
        {
            tableCount++;
            if (world.getBlock(x - 1, y, z + 1) == Blocks.crafting_table
                    || world.getBlock(x - 2, y, z) == Blocks.crafting_table
                    || world.getBlock(x - 1, y, z - 1) == Blocks.crafting_table)
                return false;
        }

        if (world.getBlock(x, y, z + 1) == Blocks.crafting_table)
        {
            tableCount++;
            if (world.getBlock(x - 1, y, z + 1) == Blocks.crafting_table
                    || world.getBlock(x, y, z + 2) == Blocks.crafting_table
                    || world.getBlock(x + 1, y, z + 1) == Blocks.crafting_table)
                return false;
        }

        if (world.getBlock(x, y, z - 1) == Blocks.crafting_table)
        {
            tableCount++;
            if (world.getBlock(x - 1, y, z - 1) == Blocks.crafting_table
                    || world.getBlock(x, y, z - 2) == Blocks.crafting_table
                    || world.getBlock(x + 1, y, z - 1) == Blocks.crafting_table)
                return false;
        }

        return tableCount == 1;
    }
}
