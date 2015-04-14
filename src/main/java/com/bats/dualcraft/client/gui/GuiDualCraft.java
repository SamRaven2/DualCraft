package com.bats.dualcraft.client.gui;

import codechicken.nei.VisiblityData;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.TaggedInventoryArea;
import com.bats.dualcraft.inventory.ContainerDualCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.util.Collections;
import java.util.List;

/**
 * Created by Bats on 4/7/2015.
 */

@SideOnly(Side.CLIENT)
public class GuiDualCraft extends GuiContainer implements INEIGuiHandler
{
    private static final ResourceLocation dualWorkbenchTexture = new ResourceLocation("dualcraft:textures/guis/guiDualCraft.png");

    public GuiDualCraft(InventoryPlayer playerInv, World world, int x, int y, int z)
    {
        super(new ContainerDualCraft(playerInv, world, x, y, z));
        xSize = 176;
        ySize = 209;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float arg1, int arg2, int arg3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(dualWorkbenchTexture);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    @Override
    public VisiblityData modifyVisiblity(GuiContainer gui, VisiblityData currentVisibility)
    {
        return currentVisibility;
    }

    @Override
    public Iterable<Integer> getItemSpawnSlots(GuiContainer gui, ItemStack item)
    {
        return null;
    }

    @Override
    public List<TaggedInventoryArea> getInventoryAreas(GuiContainer gui)
    {
        return Collections.emptyList();
    }

    @Override
    public boolean handleDragNDrop(GuiContainer gui, int mousex, int mousey, ItemStack draggedStack, int button)
    {
        return false;
    }

    @Override
    public boolean hideItemPanelSlot(GuiContainer gui, int x, int y, int w, int h)
    {
        return false;
    }
}
